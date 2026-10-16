package androidx.work.impl.utils;

import android.os.Build;
import android.text.TextUtils;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTag;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation = new OperationImpl();
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(WorkContinuationImpl workContinuation) {
        this.mWorkContinuation = workContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mWorkContinuation.hasCycles()) {
                throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", this.mWorkContinuation));
            }
            if (addToDatabase()) {
                PackageManagerHelper.setComponentEnabled(this.mWorkContinuation.getWorkManagerImpl().getApplicationContext(), RescheduleReceiver.class, true);
                scheduleWorkInBackground();
            }
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.setState(new Operation.State.FAILURE(th));
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean zProcessContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return zProcessContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuation) {
        List<WorkContinuationImpl> parents = workContinuation.getParents();
        boolean z = false;
        if (parents != null) {
            boolean zProcessContinuation = false;
            for (WorkContinuationImpl workContinuationImpl : parents) {
                if (!workContinuationImpl.isEnqueued()) {
                    zProcessContinuation |= processContinuation(workContinuationImpl);
                } else {
                    Logger.get().warning(TAG, String.format("Already enqueued work ids (%s).", TextUtils.join(", ", workContinuationImpl.getIds())), new Throwable[0]);
                }
            }
            z = zProcessContinuation;
        }
        return enqueueContinuation(workContinuation) | z;
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuation) {
        boolean zEnqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuation.getWorkManagerImpl(), workContinuation.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuation).toArray(new String[0]), workContinuation.getName(), workContinuation.getExistingWorkPolicy());
        workContinuation.markEnqueued();
        return zEnqueueWorkWithPrerequisites;
    }

    /* JADX WARN: Code duplicated, block: B:113:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:115:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:120:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:123:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:125:0x01c6 A[LOOP:4: B:124:0x01c4->B:125:0x01c6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:129:0x01eb A[LOOP:5: B:127:0x01e5->B:129:0x01eb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:131:0x0204  */
    /* JADX WARN: Code duplicated, block: B:145:0x0214 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0154 A[PHI: r1 r9 r12 r13 r14
  0x0154: PHI (r1v1 java.lang.String[]) = (r1v0 java.lang.String[]), (r1v0 java.lang.String[]), (r1v4 java.lang.String[]) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]
  0x0154: PHI (r9v2 boolean) = (r9v1 boolean), (r9v1 boolean), (r9v7 boolean) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]
  0x0154: PHI (r12v2 boolean) = (r12v1 boolean), (r12v1 boolean), (r12v4 boolean) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]
  0x0154: PHI (r13v2 boolean) = (r13v1 boolean), (r13v1 boolean), (r13v5 boolean) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]
  0x0154: PHI (r14v2 boolean) = (r14v1 boolean), (r14v1 boolean), (r14v5 boolean) binds: [B:32:0x006c, B:34:0x007a, B:90:0x0153] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Instruction removed from duplicated block: B:123:0x01c2, please report this as an issue */
    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl, List<? extends WorkRequest> workList, String[] prerequisiteIds, String name, ExistingWorkPolicy existingWorkPolicy) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Iterator<String> it;
        int i;
        boolean z5;
        WorkManagerImpl workManagerImpl2 = workManagerImpl;
        String[] strArr = prerequisiteIds;
        long jCurrentTimeMillis = System.currentTimeMillis();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        boolean z6 = strArr != null && strArr.length > 0;
        if (z6) {
            z = true;
            z2 = false;
            z3 = false;
            for (String str : strArr) {
                WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(str);
                if (workSpec == null) {
                    Logger.get().error(TAG, String.format("Prerequisite %s doesn't exist; not enqueuing", str), new Throwable[0]);
                    return false;
                }
                WorkInfo.State state = workSpec.state;
                z &= state == WorkInfo.State.SUCCEEDED;
                if (state == WorkInfo.State.FAILED) {
                    z3 = true;
                } else if (state == WorkInfo.State.CANCELLED) {
                    z2 = true;
                }
            }
        } else {
            z = true;
            z2 = false;
            z3 = false;
        }
        boolean z7 = !TextUtils.isEmpty(name);
        if (z7 && !z6) {
            List<WorkSpec.IdAndState> workSpecIdAndStatesForName = workDatabase.workSpecDao().getWorkSpecIdAndStatesForName(name);
            if (workSpecIdAndStatesForName.isEmpty()) {
                z4 = false;
            } else if (existingWorkPolicy == ExistingWorkPolicy.APPEND || existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE) {
                DependencyDao dependencyDao = workDatabase.dependencyDao();
                List arrayList = new ArrayList();
                for (WorkSpec.IdAndState idAndState : workSpecIdAndStatesForName) {
                    if (!dependencyDao.hasDependents(idAndState.id)) {
                        boolean z8 = (idAndState.state == WorkInfo.State.SUCCEEDED) & z;
                        if (idAndState.state == WorkInfo.State.FAILED) {
                            z3 = true;
                        } else if (idAndState.state == WorkInfo.State.CANCELLED) {
                            z2 = true;
                        }
                        arrayList.add(idAndState.id);
                        z = z8;
                    }
                    dependencyDao = dependencyDao;
                }
                if (existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE && (z2 || z3)) {
                    WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                    Iterator<WorkSpec.IdAndState> it2 = workSpecDao.getWorkSpecIdAndStatesForName(name).iterator();
                    while (it2.hasNext()) {
                        workSpecDao.delete(it2.next().id);
                    }
                    arrayList = Collections.emptyList();
                    z5 = false;
                    z2 = false;
                } else {
                    z5 = z3;
                }
                strArr = (String[]) arrayList.toArray(strArr);
                z6 = strArr.length > 0;
                z3 = z5;
                z4 = false;
            } else {
                if (existingWorkPolicy == ExistingWorkPolicy.KEEP) {
                    for (WorkSpec.IdAndState idAndState2 : workSpecIdAndStatesForName) {
                        if (idAndState2.state == WorkInfo.State.ENQUEUED || idAndState2.state == WorkInfo.State.RUNNING) {
                            return false;
                        }
                    }
                }
                CancelWorkRunnable.forName(name, workManagerImpl2, false).run();
                WorkSpecDao workSpecDao2 = workDatabase.workSpecDao();
                Iterator<WorkSpec.IdAndState> it3 = workSpecIdAndStatesForName.iterator();
                while (it3.hasNext()) {
                    workSpecDao2.delete(it3.next().id);
                }
                z4 = true;
            }
        } else {
            z4 = false;
        }
        for (WorkRequest workRequest : workList) {
            WorkSpec workSpec2 = workRequest.getWorkSpec();
            if (!z6 || z) {
                if (!workSpec2.isPeriodic()) {
                    workSpec2.periodStartTime = jCurrentTimeMillis;
                } else {
                    workSpec2.periodStartTime = 0L;
                }
                if (Build.VERSION.SDK_INT < 23 && Build.VERSION.SDK_INT <= 25) {
                    tryDelegateConstrainedWorkSpec(workSpec2);
                } else if (Build.VERSION.SDK_INT <= 22 && usesScheduler(workManagerImpl2, Schedulers.GCM_SCHEDULER)) {
                    tryDelegateConstrainedWorkSpec(workSpec2);
                }
                if (workSpec2.state == WorkInfo.State.ENQUEUED) {
                    z4 = true;
                }
                workDatabase.workSpecDao().insertWorkSpec(workSpec2);
                if (z6) {
                    for (String str2 : strArr) {
                        workDatabase.dependencyDao().insertDependency(new Dependency(workRequest.getStringId(), str2));
                    }
                }
                it = workRequest.getTags().iterator();
                while (it.hasNext()) {
                    workDatabase.workTagDao().insert(new WorkTag(it.next(), workRequest.getStringId()));
                }
                if (z7) {
                    workDatabase.workNameDao().insert(new WorkName(name, workRequest.getStringId()));
                }
                workManagerImpl2 = workManagerImpl;
                jCurrentTimeMillis = jCurrentTimeMillis;
            } else if (z3) {
                workSpec2.state = WorkInfo.State.FAILED;
            } else if (z2) {
                workSpec2.state = WorkInfo.State.CANCELLED;
            } else {
                workSpec2.state = WorkInfo.State.BLOCKED;
            }
            if (Build.VERSION.SDK_INT < 23) {
                if (Build.VERSION.SDK_INT <= 22) {
                    tryDelegateConstrainedWorkSpec(workSpec2);
                }
            } else if (Build.VERSION.SDK_INT <= 22) {
                tryDelegateConstrainedWorkSpec(workSpec2);
            }
            if (workSpec2.state == WorkInfo.State.ENQUEUED) {
                z4 = true;
            }
            workDatabase.workSpecDao().insertWorkSpec(workSpec2);
            if (z6) {
                while (i < r4) {
                    workDatabase.dependencyDao().insertDependency(new Dependency(workRequest.getStringId(), str2));
                }
            }
            it = workRequest.getTags().iterator();
            while (it.hasNext()) {
                workDatabase.workTagDao().insert(new WorkTag(it.next(), workRequest.getStringId()));
            }
            if (z7) {
                workDatabase.workNameDao().insert(new WorkName(name, workRequest.getStringId()));
            }
            workManagerImpl2 = workManagerImpl;
            jCurrentTimeMillis = jCurrentTimeMillis;
        }
        return z4;
    }

    private static void tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        if (constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow()) {
            String str = workSpec.workerClassName;
            Data.Builder builder = new Data.Builder();
            builder.putAll(workSpec.input).putString(ConstraintTrackingWorker.ARGUMENT_CLASS_NAME, str);
            workSpec.workerClassName = ConstraintTrackingWorker.class.getName();
            workSpec.input = builder.build();
        }
    }

    private static boolean usesScheduler(WorkManagerImpl workManager, String className) {
        try {
            Class<?> cls = Class.forName(className);
            Iterator<Scheduler> it = workManager.getSchedulers().iterator();
            while (it.hasNext()) {
                if (cls.isAssignableFrom(it.next().getClass())) {
                    return true;
                }
            }
        } catch (ClassNotFoundException unused) {
        }
        return false;
    }
}
