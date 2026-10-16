package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.IdGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SystemJobScheduler implements Scheduler {
    private static final String TAG = Logger.tagWithPrefix("SystemJobScheduler");
    private final Context mContext;
    private final JobScheduler mJobScheduler;
    private final SystemJobInfoConverter mSystemJobInfoConverter;
    private final WorkManagerImpl mWorkManager;

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    public SystemJobScheduler(Context context, WorkManagerImpl workManager) {
        this(context, workManager, (JobScheduler) context.getSystemService("jobscheduler"), new SystemJobInfoConverter(context));
    }

    public SystemJobScheduler(Context context, WorkManagerImpl workManager, JobScheduler jobScheduler, SystemJobInfoConverter systemJobInfoConverter) {
        this.mContext = context;
        this.mWorkManager = workManager;
        this.mJobScheduler = jobScheduler;
        this.mSystemJobInfoConverter = systemJobInfoConverter;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(WorkSpec... workSpecs) {
        List<Integer> pendingJobIds;
        int iNextJobSchedulerIdWithRange;
        WorkDatabase workDatabase = this.mWorkManager.getWorkDatabase();
        IdGenerator idGenerator = new IdGenerator(workDatabase);
        for (WorkSpec workSpec : workSpecs) {
            workDatabase.beginTransaction();
            try {
                WorkSpec workSpec2 = workDatabase.workSpecDao().getWorkSpec(workSpec.id);
                if (workSpec2 == null) {
                    Logger.get().warning(TAG, "Skipping scheduling " + workSpec.id + " because it's no longer in the DB", new Throwable[0]);
                    workDatabase.setTransactionSuccessful();
                } else if (workSpec2.state != WorkInfo.State.ENQUEUED) {
                    Logger.get().warning(TAG, "Skipping scheduling " + workSpec.id + " because it is no longer enqueued", new Throwable[0]);
                    workDatabase.setTransactionSuccessful();
                } else {
                    SystemIdInfo systemIdInfo = workDatabase.systemIdInfoDao().getSystemIdInfo(workSpec.id);
                    int iNextJobSchedulerIdWithRange2 = systemIdInfo != null ? systemIdInfo.systemId : idGenerator.nextJobSchedulerIdWithRange(this.mWorkManager.getConfiguration().getMinJobSchedulerId(), this.mWorkManager.getConfiguration().getMaxJobSchedulerId());
                    if (systemIdInfo == null) {
                        this.mWorkManager.getWorkDatabase().systemIdInfoDao().insertSystemIdInfo(new SystemIdInfo(workSpec.id, iNextJobSchedulerIdWithRange2));
                    }
                    scheduleInternal(workSpec, iNextJobSchedulerIdWithRange2);
                    if (Build.VERSION.SDK_INT == 23 && (pendingJobIds = getPendingJobIds(this.mContext, this.mJobScheduler, workSpec.id)) != null) {
                        int iIndexOf = pendingJobIds.indexOf(Integer.valueOf(iNextJobSchedulerIdWithRange2));
                        if (iIndexOf >= 0) {
                            pendingJobIds.remove(iIndexOf);
                        }
                        if (!pendingJobIds.isEmpty()) {
                            iNextJobSchedulerIdWithRange = pendingJobIds.get(0).intValue();
                        } else {
                            iNextJobSchedulerIdWithRange = idGenerator.nextJobSchedulerIdWithRange(this.mWorkManager.getConfiguration().getMinJobSchedulerId(), this.mWorkManager.getConfiguration().getMaxJobSchedulerId());
                        }
                        scheduleInternal(workSpec, iNextJobSchedulerIdWithRange);
                    }
                    workDatabase.setTransactionSuccessful();
                }
                workDatabase.endTransaction();
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    public void scheduleInternal(WorkSpec workSpec, int jobId) {
        JobInfo jobInfoConvert = this.mSystemJobInfoConverter.convert(workSpec, jobId);
        Logger.get().debug(TAG, String.format("Scheduling work ID %s Job ID %s", workSpec.id, Integer.valueOf(jobId)), new Throwable[0]);
        try {
            this.mJobScheduler.schedule(jobInfoConvert);
        } catch (IllegalStateException e) {
            List<JobInfo> pendingJobs = getPendingJobs(this.mContext, this.mJobScheduler);
            String str = String.format(Locale.getDefault(), "JobScheduler 100 job limit exceeded.  We count %d WorkManager jobs in JobScheduler; we have %d tracked jobs in our DB; our Configuration limit is %d.", Integer.valueOf(pendingJobs != null ? pendingJobs.size() : 0), Integer.valueOf(this.mWorkManager.getWorkDatabase().workSpecDao().getScheduledWork().size()), Integer.valueOf(this.mWorkManager.getConfiguration().getMaxSchedulerLimit()));
            Logger.get().error(TAG, str, new Throwable[0]);
            throw new IllegalStateException(str, e);
        } catch (Throwable th) {
            Logger.get().error(TAG, String.format("Unable to schedule %s", workSpec), th);
        }
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(String workSpecId) {
        List<Integer> pendingJobIds = getPendingJobIds(this.mContext, this.mJobScheduler, workSpecId);
        if (pendingJobIds == null || pendingJobIds.isEmpty()) {
            return;
        }
        Iterator<Integer> it = pendingJobIds.iterator();
        while (it.hasNext()) {
            cancelJobById(this.mJobScheduler, it.next().intValue());
        }
        this.mWorkManager.getWorkDatabase().systemIdInfoDao().removeSystemIdInfo(workSpecId);
    }

    private static void cancelJobById(JobScheduler jobScheduler, int id) {
        try {
            jobScheduler.cancel(id);
        } catch (Throwable th) {
            Logger.get().error(TAG, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(id)), th);
        }
    }

    public static void cancelAll(Context context) {
        List<JobInfo> pendingJobs;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null || (pendingJobs = getPendingJobs(context, jobScheduler)) == null || pendingJobs.isEmpty()) {
            return;
        }
        Iterator<JobInfo> it = pendingJobs.iterator();
        while (it.hasNext()) {
            cancelJobById(jobScheduler, it.next().getId());
        }
    }

    public static boolean reconcileJobs(Context context, WorkManagerImpl workManager) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        List<JobInfo> pendingJobs = getPendingJobs(context, jobScheduler);
        List<String> workSpecIds = workManager.getWorkDatabase().systemIdInfoDao().getWorkSpecIds();
        boolean z = false;
        HashSet hashSet = new HashSet(pendingJobs != null ? pendingJobs.size() : 0);
        if (pendingJobs != null && !pendingJobs.isEmpty()) {
            for (JobInfo jobInfo : pendingJobs) {
                String workSpecIdFromJobInfo = getWorkSpecIdFromJobInfo(jobInfo);
                if (!TextUtils.isEmpty(workSpecIdFromJobInfo)) {
                    hashSet.add(workSpecIdFromJobInfo);
                } else {
                    cancelJobById(jobScheduler, jobInfo.getId());
                }
            }
        }
        Iterator<String> it = workSpecIds.iterator();
        while (it.hasNext()) {
            if (!hashSet.contains(it.next())) {
                Logger.get().debug(TAG, "Reconciling jobs", new Throwable[0]);
                z = true;
                break;
            }
        }
        if (z) {
            WorkDatabase workDatabase = workManager.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                Iterator<String> it2 = workSpecIds.iterator();
                while (it2.hasNext()) {
                    workSpecDao.markWorkSpecScheduled(it2.next(), -1L);
                }
                workDatabase.setTransactionSuccessful();
            } finally {
                workDatabase.endTransaction();
            }
        }
        return z;
    }

    private static List<JobInfo> getPendingJobs(Context context, JobScheduler jobScheduler) {
        List<JobInfo> allPendingJobs;
        try {
            allPendingJobs = jobScheduler.getAllPendingJobs();
        } catch (Throwable th) {
            Logger.get().error(TAG, "getAllPendingJobs() is not reliable on this device.", th);
            allPendingJobs = null;
        }
        if (allPendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(allPendingJobs.size());
        ComponentName componentName = new ComponentName(context, (Class<?>) SystemJobService.class);
        for (JobInfo jobInfo : allPendingJobs) {
            if (componentName.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    private static List<Integer> getPendingJobIds(Context context, JobScheduler jobScheduler, String workSpecId) {
        List<JobInfo> pendingJobs = getPendingJobs(context, jobScheduler);
        if (pendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(2);
        for (JobInfo jobInfo : pendingJobs) {
            if (workSpecId.equals(getWorkSpecIdFromJobInfo(jobInfo))) {
                arrayList.add(Integer.valueOf(jobInfo.getId()));
            }
        }
        return arrayList;
    }

    private static String getWorkSpecIdFromJobInfo(JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            if (extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return extras.getString("EXTRA_WORK_SPEC_ID");
            }
            return null;
        } catch (NullPointerException unused) {
            return null;
        }
    }
}
