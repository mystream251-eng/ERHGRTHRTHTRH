package androidx.work.impl.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.work.InitializationExceptionHandler;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabasePathHelper;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ForceStopRunnable implements Runnable {
    static final String ACTION_FORCE_STOP_RESCHEDULE = "ACTION_FORCE_STOP_RESCHEDULE";
    private static final int ALARM_ID = -1;
    private static final long BACKOFF_DURATION_MS = 300;
    static final int MAX_ATTEMPTS = 3;
    private static final String TAG = Logger.tagWithPrefix("ForceStopRunnable");
    private static final long TEN_YEARS = TimeUnit.DAYS.toMillis(3650);
    private final Context mContext;
    private int mRetryCount = 0;
    private final WorkManagerImpl mWorkManager;

    public ForceStopRunnable(Context context, WorkManagerImpl workManager) {
        this.mContext = context.getApplicationContext();
        this.mWorkManager = workManager;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        if (!multiProcessChecks()) {
            return;
        }
        while (true) {
            WorkDatabasePathHelper.migrateDatabase(this.mContext);
            Logger.get().debug(TAG, "Performing cleanup operations.", new Throwable[0]);
            try {
                forceStopRunnable();
                return;
            } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteTableLockedException e) {
                i = this.mRetryCount + 1;
                this.mRetryCount = i;
                if (i >= 3) {
                    Logger.get().error(TAG, "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e);
                    IllegalStateException illegalStateException = new IllegalStateException("The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e);
                    InitializationExceptionHandler exceptionHandler = this.mWorkManager.getConfiguration().getExceptionHandler();
                    if (exceptionHandler != null) {
                        Logger.get().debug(TAG, "Routing exception to the specified exception handler", illegalStateException);
                        exceptionHandler.handleException(illegalStateException);
                        return;
                    }
                    throw illegalStateException;
                }
                Logger.get().debug(TAG, String.format("Retrying after %s", Long.valueOf(((long) i) * BACKOFF_DURATION_MS)), e);
                sleep(((long) this.mRetryCount) * BACKOFF_DURATION_MS);
            }
            Logger.get().debug(TAG, String.format("Retrying after %s", Long.valueOf(((long) i) * BACKOFF_DURATION_MS)), e);
            sleep(((long) this.mRetryCount) * BACKOFF_DURATION_MS);
        }
    }

    public boolean isForceStopped() {
        if (getPendingIntent(this.mContext, 536870912) != null) {
            return false;
        }
        setAlarm(this.mContext);
        return true;
    }

    public void forceStopRunnable() {
        boolean zCleanUp = cleanUp();
        if (shouldRescheduleWorkers()) {
            Logger.get().debug(TAG, "Rescheduling Workers.", new Throwable[0]);
            this.mWorkManager.rescheduleEligibleWork();
            this.mWorkManager.getPreferenceUtils().setNeedsReschedule(false);
        } else if (isForceStopped()) {
            Logger.get().debug(TAG, "Application was force-stopped, rescheduling.", new Throwable[0]);
            this.mWorkManager.rescheduleEligibleWork();
        } else if (zCleanUp) {
            Logger.get().debug(TAG, "Found unfinished work, scheduling it.", new Throwable[0]);
            Schedulers.schedule(this.mWorkManager.getConfiguration(), this.mWorkManager.getWorkDatabase(), this.mWorkManager.getSchedulers());
        }
        this.mWorkManager.onForceStopRunnableCompleted();
    }

    public boolean cleanUp() {
        boolean zReconcileJobs = Build.VERSION.SDK_INT >= 23 ? SystemJobScheduler.reconcileJobs(this.mContext, this.mWorkManager) : false;
        WorkDatabase workDatabase = this.mWorkManager.getWorkDatabase();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkProgressDao workProgressDao = workDatabase.workProgressDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> runningWork = workSpecDao.getRunningWork();
            boolean z = (runningWork == null || runningWork.isEmpty()) ? false : true;
            if (z) {
                for (WorkSpec workSpec : runningWork) {
                    workSpecDao.setState(WorkInfo.State.ENQUEUED, workSpec.id);
                    workSpecDao.markWorkSpecScheduled(workSpec.id, -1L);
                }
            }
            workProgressDao.deleteAll();
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            return z || zReconcileJobs;
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    boolean shouldRescheduleWorkers() {
        return this.mWorkManager.getPreferenceUtils().getNeedsReschedule();
    }

    public boolean multiProcessChecks() {
        if (this.mWorkManager.getRemoteWorkManager() == null) {
            return true;
        }
        Logger.get().debug(TAG, "Found a remote implementation for WorkManager", new Throwable[0]);
        boolean zIsDefaultProcess = ProcessUtils.isDefaultProcess(this.mContext, this.mWorkManager.getConfiguration());
        Logger.get().debug(TAG, String.format("Is default app process = %s", Boolean.valueOf(zIsDefaultProcess)), new Throwable[0]);
        return zIsDefaultProcess;
    }

    public void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException unused) {
        }
    }

    private static PendingIntent getPendingIntent(Context context, int flags) {
        return PendingIntent.getBroadcast(context, -1, getIntent(context), flags);
    }

    static Intent getIntent(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) BroadcastReceiver.class));
        intent.setAction(ACTION_FORCE_STOP_RESCHEDULE);
        return intent;
    }

    static void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent pendingIntent = getPendingIntent(context, 134217728);
        long jCurrentTimeMillis = System.currentTimeMillis() + TEN_YEARS;
        if (alarmManager != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, jCurrentTimeMillis, pendingIntent);
            } else {
                alarmManager.set(0, jCurrentTimeMillis, pendingIntent);
            }
        }
    }

    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String TAG = Logger.tagWithPrefix("ForceStopRunnable$Rcvr");

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !ForceStopRunnable.ACTION_FORCE_STOP_RESCHEDULE.equals(intent.getAction())) {
                return;
            }
            Logger.get().verbose(TAG, "Rescheduling alarm that keeps track of force-stops.", new Throwable[0]);
            ForceStopRunnable.setAlarm(context);
        }
    }
}
