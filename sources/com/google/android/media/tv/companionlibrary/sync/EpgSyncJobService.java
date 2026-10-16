package com.google.android.media.tv.companionlibrary.sync;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.ModelUtils;
import com.google.android.media.tv.companionlibrary.model.Program;
import com.google.android.media.tv.companionlibrary.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import junit.framework.Assert;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EpgSyncJobService extends JobService {
    private static final int BATCH_OPERATION_COUNT = 100;
    private static final String BUNDLE_KEY_SYNC_PERIOD = "bundle_key_sync_period";
    private static final boolean DEBUG = true;
    private static final long DEFAULT_IMMEDIATE_EPG_DURATION_MILLIS = 3600000;
    private static final long DEFAULT_PERIODIC_EPG_DURATION_MILLIS = 172800000;
    private static final long DEFAULT_SYNC_PERIOD_MILLIS = 43200000;
    public static final int ERROR_DATABASE_INSERT = 5;
    public static final int ERROR_EPG_SYNC_CANCELED = 1;
    public static final int ERROR_INPUT_ID_NULL = 2;
    public static final int ERROR_NO_CHANNELS = 4;
    public static final int ERROR_NO_PROGRAMS = 3;
    public static final int ERROR_START_CUSTOM = 1000;
    private static final long OVERRIDE_DEADLINE_MILLIS = 1000;
    private static final int PERIODIC_SYNC_JOB_ID = 0;
    private static final int REQUEST_SYNC_JOB_ID = 1;
    public static final String SYNC_ERROR = "sync_error";
    public static final String SYNC_FINISHED = "sync_finished";
    public static final String SYNC_SCANNED = "sync_scanned";
    public static final String SYNC_STARTED = "sync_started";
    public static final String SYNC_STATUS = "sync_status";
    private static final String TAG = "EpgSyncJobService";
    private Context mContext;
    private final SparseArray<EpgSyncTask> mTaskArray = new SparseArray<>();
    public static final String ACTION_SYNC_STATUS_CHANGED = EpgSyncJobService.class.getPackage().getName() + ".ACTION_SYNC_STATUS_CHANGED";
    public static final String BUNDLE_KEY_INPUT_ID = EpgSyncJobService.class.getPackage().getName() + ".bundle_key_input_id";
    public static final String BUNDLE_KEY_CHANNELS_SCANNED = EpgSyncJobService.class.getPackage().getName() + ".bundle_key_channels_scanned";
    public static final String BUNDLE_KEY_CHANNEL_COUNT = EpgSyncJobService.class.getPackage().getName() + ".bundle_key_channel_count";
    public static final String BUNDLE_KEY_SCANNED_CHANNEL_DISPLAY_NAME = EpgSyncJobService.class.getPackage().getName() + ".bundle_key_scanned_channel_display_name";
    public static final String BUNDLE_KEY_SCANNED_CHANNEL_DISPLAY_NUMBER = EpgSyncJobService.class.getPackage().getName() + ".bundle_key_scanned_channel_display_number";
    public static final String BUNDLE_KEY_ERROR_REASON = EpgSyncJobService.class.getPackage().getName() + ".bundle_key_error_reason";
    public static final String PREFERENCE_EPG_SYNC = EpgSyncJobService.class.getPackage().getName() + ".preference_epg_sync";
    private static final ExecutorService SINGLE_THREAD_EXECUTOR = Executors.newSingleThreadExecutor();
    private static final Object mContextLock = new Object();

    public abstract List<Channel> getChannels() throws EpgSyncException;

    public abstract List<Program> getProgramsForChannel(Uri uri, Channel channel, long j, long j2) throws EpgSyncException;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Created EpgSyncJobService");
        synchronized (mContextLock) {
            if (this.mContext == null) {
                this.mContext = getApplicationContext();
            }
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "onStartJob(" + jobParameters.getJobId() + ")");
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(createSyncStartedIntent(jobParameters.getExtras().getString(BUNDLE_KEY_INPUT_ID)));
        EpgSyncTask epgSyncTask = new EpgSyncTask(jobParameters);
        synchronized (this.mTaskArray) {
            this.mTaskArray.put(jobParameters.getJobId(), epgSyncTask);
        }
        epgSyncTask.executeOnExecutor(SINGLE_THREAD_EXECUTOR, new Void[0]);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        synchronized (this.mTaskArray) {
            EpgSyncTask epgSyncTask = this.mTaskArray.get(jobParameters.getJobId());
            if (epgSyncTask != null) {
                epgSyncTask.cancel(true);
                this.mTaskArray.delete(jobParameters.getJobId());
            }
        }
        return false;
    }

    public boolean shouldUpdateProgramMetadata(Program program, Program program2) {
        return program.getTitle().equals(program2.getTitle()) && program.getStartTimeUtcMillis() <= program2.getEndTimeUtcMillis() && program2.getStartTimeUtcMillis() <= program.getEndTimeUtcMillis();
    }

    private static void scheduleJob(Context context, JobInfo jobInfo) {
        int iSchedule = ((JobScheduler) context.getSystemService("jobscheduler")).schedule(jobInfo);
        Assert.assertEquals(1, iSchedule);
        Log.d(TAG, "Scheduling result is " + iSchedule);
    }

    public static void setUpPeriodicSync(Context context, String str, ComponentName componentName) {
        setUpPeriodicSync(context, str, componentName, DEFAULT_SYNC_PERIOD_MILLIS, DEFAULT_PERIODIC_EPG_DURATION_MILLIS);
    }

    public static void setUpPeriodicSync(Context context, String str, ComponentName componentName, long j, long j2) {
        if (componentName.getClass().isAssignableFrom(EpgSyncJobService.class)) {
            throw new IllegalArgumentException("This class does not extend EpgSyncJobService");
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(BUNDLE_KEY_INPUT_ID, str);
        persistableBundle.putLong(BUNDLE_KEY_SYNC_PERIOD, j2);
        scheduleJob(context, new JobInfo.Builder(0, componentName).setExtras(persistableBundle).setPeriodic(j).setPersisted(true).setRequiredNetworkType(1).build());
        Log.d(TAG, "Job has been scheduled for every " + j + "ms");
    }

    public static void requestImmediateSync(Context context, String str, ComponentName componentName) {
        requestImmediateSync(context, str, DEFAULT_IMMEDIATE_EPG_DURATION_MILLIS, componentName);
    }

    public static void requestImmediateSync(Context context, String str, long j, ComponentName componentName) {
        if (componentName.getClass().isAssignableFrom(EpgSyncJobService.class)) {
            throw new IllegalArgumentException("This class does not extend EpgSyncJobService");
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        if (Build.VERSION.SDK_INT >= 22) {
            persistableBundle.putBoolean("force", true);
            persistableBundle.putBoolean("expedited", true);
        }
        persistableBundle.putString(BUNDLE_KEY_INPUT_ID, str);
        persistableBundle.putLong(BUNDLE_KEY_SYNC_PERIOD, j);
        scheduleJob(context, new JobInfo.Builder(1, componentName).setExtras(persistableBundle).setOverrideDeadline(1000L).setRequiredNetworkType(1).build());
        Log.d(TAG, "Single job scheduled");
    }

    public static void cancelAllSyncRequests(Context context) {
        ((JobScheduler) context.getSystemService("jobscheduler")).cancelAll();
    }

    public class EpgSyncTask extends AsyncTask<Void, Void, Void> {
        private String mInputId;
        private final JobParameters params;

        public EpgSyncTask(JobParameters jobParameters) {
            this.params = jobParameters;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) throws Throwable {
            PersistableBundle extras = this.params.getExtras();
            String string = extras.getString(EpgSyncJobService.BUNDLE_KEY_INPUT_ID);
            this.mInputId = string;
            Void r3 = null;
            Object[] objArr = 0;
            if (string == null) {
                broadcastError(2);
                return null;
            }
            int i = 1;
            if (isCancelled()) {
                broadcastError(1);
                return null;
            }
            try {
                ModelUtils.updateChannels(EpgSyncJobService.this.mContext, this.mInputId, EpgSyncJobService.this.getChannels(), new ModelUtils.OnChannelDeletedCallback() { // from class: com.google.android.media.tv.companionlibrary.sync.EpgSyncJobService.EpgSyncTask.1
                    @Override // com.google.android.media.tv.companionlibrary.model.ModelUtils.OnChannelDeletedCallback
                    public void onChannelDeleted(long j) {
                        SharedPreferences.Editor editorEdit = EpgSyncJobService.this.mContext.getSharedPreferences("com.google.android.media.tv.companionlibrary", 0).edit();
                        editorEdit.remove(Constants.SHARED_PREFERENCES_KEY_LAST_CHANNEL_AD_PLAY + j);
                        editorEdit.apply();
                    }
                });
                LongSparseArray<Channel> longSparseArrayBuildChannelMap = ModelUtils.buildChannelMap(EpgSyncJobService.this.mContext.getContentResolver(), this.mInputId);
                if (longSparseArrayBuildChannelMap == null) {
                    broadcastError(4);
                    return null;
                }
                long j = extras.getLong(EpgSyncJobService.BUNDLE_KEY_SYNC_PERIOD, EpgSyncJobService.DEFAULT_IMMEDIATE_EPG_DURATION_MILLIS);
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j2 = j + jCurrentTimeMillis;
                ChangeCount changeCount = new ChangeCount();
                int i2 = 0;
                while (i2 < longSparseArrayBuildChannelMap.size()) {
                    Uri uriBuildChannelUri = TvContract.buildChannelUri(longSparseArrayBuildChannelMap.keyAt(i2));
                    if (isCancelled()) {
                        broadcastError(i);
                        return r3;
                    }
                    try {
                        int i3 = i2;
                        List<Program> programsForChannel = EpgSyncJobService.this.getProgramsForChannel(uriBuildChannelUri, longSparseArrayBuildChannelMap.valueAt(i2), jCurrentTimeMillis, j2);
                        Log.d(EpgSyncJobService.TAG, programsForChannel.toString());
                        for (int i4 = 0; i4 < programsForChannel.size(); i4++) {
                            if (programsForChannel.get(i4).getChannelId() == -1) {
                                programsForChannel.set(i4, new Program.Builder(programsForChannel.get(i4)).setChannelId(longSparseArrayBuildChannelMap.valueAt(i3).getId()).build());
                            }
                        }
                        if (isCancelled()) {
                            broadcastError(1);
                            return null;
                        }
                        i = 1;
                        updatePrograms(uriBuildChannelUri, programsForChannel, changeCount);
                        i2 = i3 + 1;
                        LocalBroadcastManager.getInstance(EpgSyncJobService.this.mContext).sendBroadcast(EpgSyncJobService.createSyncScannedIntent(this.mInputId, i2, longSparseArrayBuildChannelMap.size(), longSparseArrayBuildChannelMap.valueAt(i3).getDisplayName(), longSparseArrayBuildChannelMap.valueAt(i3).getDisplayNumber()));
                        r3 = null;
                    } catch (EpgSyncException e) {
                        broadcastError(e.getReason());
                        return null;
                    }
                }
                Log.i(EpgSyncJobService.TAG, this.mInputId + " synced " + changeCount.total + " programs. Deleted " + changeCount.deleteCount + " updated " + changeCount.updateCount + " added " + changeCount.addCount);
                return null;
            } catch (EpgSyncException e2) {
                broadcastError(e2.getReason());
                return null;
            }
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            finishEpgSync(this.params);
        }

        @Override // android.os.AsyncTask
        public void onCancelled(Void r1) {
            finishEpgSync(this.params);
        }

        private void finishEpgSync(JobParameters jobParameters) {
            Log.d(EpgSyncJobService.TAG, "taskFinished(" + jobParameters.getJobId() + ")");
            EpgSyncJobService.this.mTaskArray.delete(jobParameters.getJobId());
            EpgSyncJobService.this.jobFinished(jobParameters, false);
            Log.d(EpgSyncJobService.TAG, "Send out broadcast");
            LocalBroadcastManager.getInstance(EpgSyncJobService.this.mContext).sendBroadcast(EpgSyncJobService.createSyncFinishedIntent(jobParameters.getExtras().getString(EpgSyncJobService.BUNDLE_KEY_INPUT_ID)));
        }

        private void broadcastError(int i) {
            LocalBroadcastManager.getInstance(EpgSyncJobService.this.mContext).sendBroadcast(EpgSyncJobService.createSyncErrorIntent(this.mInputId, i));
        }

        /* JADX WARN: Code duplicated, block: B:35:0x00de  */
        private void updatePrograms(Uri uri, List<Program> list, ChangeCount changeCount) {
            int size = list.size();
            changeCount.total += (long) size;
            if (size != 0) {
                List<Program> programs = ModelUtils.getPrograms(EpgSyncJobService.this.mContext.getContentResolver(), uri);
                Program program = list.get(0);
                int i = 0;
                for (Program program2 : programs) {
                    if (program2.getEndTimeUtcMillis() >= System.currentTimeMillis() && program2.getEndTimeUtcMillis() >= program.getStartTimeUtcMillis()) {
                        break;
                    } else {
                        i++;
                    }
                }
                ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
                if (isCancelled()) {
                    return;
                }
                int i2 = 0;
                while (i2 < size) {
                    Program program3 = i < programs.size() ? programs.get(i) : null;
                    Program program4 = list.get(i2);
                    boolean z = true;
                    if (program3 == null) {
                        i2++;
                    } else {
                        if (program3.equals(program4)) {
                            i++;
                            i2++;
                        } else if (EpgSyncJobService.this.shouldUpdateProgramMetadata(program3, program4)) {
                            arrayList.add(ContentProviderOperation.newUpdate(TvContract.buildProgramUri(program3.getId())).withValues(program4.toContentValues()).build());
                            changeCount.updateCount++;
                            i++;
                            i2++;
                        } else if (program3.getEndTimeUtcMillis() < program4.getEndTimeUtcMillis()) {
                            arrayList.add(ContentProviderOperation.newDelete(TvContract.buildProgramUri(program3.getId())).build());
                            changeCount.deleteCount++;
                            i++;
                        } else {
                            i2++;
                        }
                        z = false;
                    }
                    if (z) {
                        arrayList.add(ContentProviderOperation.newInsert(TvContract.Programs.CONTENT_URI).withValues(program4.toContentValues()).build());
                        changeCount.addCount++;
                    }
                    if (arrayList.size() > 100 || i2 >= size) {
                        try {
                            EpgSyncJobService.this.mContext.getContentResolver().applyBatch(TvContractCompat.AUTHORITY, arrayList);
                            arrayList.clear();
                        } catch (OperationApplicationException | RemoteException e) {
                            Log.e(EpgSyncJobService.TAG, "Failed to insert programs.", e);
                            broadcastError(5);
                            return;
                        }
                    }
                }
                return;
            }
            broadcastError(3);
        }
    }

    public static Intent createSyncStartedIntent(String str) {
        Intent intent = new Intent(ACTION_SYNC_STATUS_CHANGED);
        intent.putExtra(BUNDLE_KEY_INPUT_ID, str);
        intent.putExtra(SYNC_STATUS, SYNC_STARTED);
        return intent;
    }

    public static Intent createSyncScannedIntent(String str, int i, int i2, String str2, String str3) {
        Intent intent = new Intent(ACTION_SYNC_STATUS_CHANGED);
        intent.putExtra(BUNDLE_KEY_INPUT_ID, str);
        intent.putExtra(BUNDLE_KEY_CHANNELS_SCANNED, i);
        intent.putExtra(BUNDLE_KEY_CHANNEL_COUNT, i2);
        intent.putExtra(BUNDLE_KEY_SCANNED_CHANNEL_DISPLAY_NAME, str2);
        intent.putExtra(BUNDLE_KEY_SCANNED_CHANNEL_DISPLAY_NUMBER, str3);
        intent.putExtra(SYNC_STATUS, SYNC_SCANNED);
        return intent;
    }

    public static Intent createSyncFinishedIntent(String str) {
        Intent intent = new Intent(ACTION_SYNC_STATUS_CHANGED);
        intent.putExtra(BUNDLE_KEY_INPUT_ID, str);
        intent.putExtra(SYNC_STATUS, SYNC_FINISHED);
        return intent;
    }

    public static Intent createSyncErrorIntent(String str, int i) {
        Intent intent = new Intent(ACTION_SYNC_STATUS_CHANGED);
        intent.putExtra(BUNDLE_KEY_INPUT_ID, str);
        intent.putExtra(SYNC_STATUS, SYNC_ERROR);
        intent.putExtra(BUNDLE_KEY_ERROR_REASON, i);
        return intent;
    }

    public static class EpgSyncException extends Exception {
        private final int reason;

        public EpgSyncException(int i) {
            this.reason = i;
        }

        public int getReason() {
            return this.reason;
        }
    }

    private static class ChangeCount {
        long addCount;
        long deleteCount;
        long total;
        long updateCount;

        private ChangeCount() {
            this.total = 0L;
            this.deleteCount = 0L;
            this.updateCount = 0L;
            this.addCount = 0L;
        }
    }
}
