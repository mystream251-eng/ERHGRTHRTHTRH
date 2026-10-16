package io.sentry.android.core;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import androidx.core.app.NotificationCompat;
import io.sentry.Breadcrumb;
import io.sentry.IHub;
import io.sentry.ISpan;
import io.sentry.ITransaction;
import io.sentry.Integration;
import io.sentry.Scope;
import io.sentry.ScopeCallback;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SpanStatus;
import io.sentry.TransactionFinishedCallback;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class ActivityLifecycleIntegration implements Integration, Closeable, Application.ActivityLifecycleCallbacks {
    static final String APP_START_COLD = "app.start.cold";
    static final String APP_START_WARM = "app.start.warm";
    static final String UI_LOAD_OP = "ui.load";
    private final ActivityFramesTracker activityFramesTracker;
    private ISpan appStartSpan;
    private final Application application;
    private boolean foregroundImportance;
    private IHub hub;
    private boolean isAllActivityCallbacksAvailable;
    private SentryAndroidOptions options;
    private boolean performanceEnabled = false;
    private boolean firstActivityCreated = false;
    private boolean firstActivityResumed = false;
    private final WeakHashMap<Activity, ITransaction> activitiesWithOngoingTransactions = new WeakHashMap<>();

    private String getAppStartDesc(boolean z) {
        return z ? "Cold Start" : "Warm Start";
    }

    private String getAppStartOp(boolean z) {
        return z ? APP_START_COLD : APP_START_WARM;
    }

    public ActivityLifecycleIntegration(Application application, IBuildInfoProvider iBuildInfoProvider, ActivityFramesTracker activityFramesTracker) {
        this.foregroundImportance = false;
        this.application = (Application) Objects.requireNonNull(application, "Application is required");
        Objects.requireNonNull(iBuildInfoProvider, "BuildInfoProvider is required");
        this.activityFramesTracker = (ActivityFramesTracker) Objects.requireNonNull(activityFramesTracker, "ActivityFramesTracker is required");
        if (iBuildInfoProvider.getSdkInfoVersion() >= 29) {
            this.isAllActivityCallbacksAvailable = true;
        }
        this.foregroundImportance = isForegroundImportance(this.application);
    }

    @Override // io.sentry.Integration
    public void register(IHub iHub, SentryOptions sentryOptions) {
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.hub = (IHub) Objects.requireNonNull(iHub, "Hub is required");
        this.options.getLogger().log(SentryLevel.DEBUG, "ActivityLifecycleIntegration enabled: %s", Boolean.valueOf(this.options.isEnableActivityLifecycleBreadcrumbs()));
        this.performanceEnabled = isPerformanceEnabled(this.options);
        if (this.options.isEnableActivityLifecycleBreadcrumbs() || this.performanceEnabled) {
            this.application.registerActivityLifecycleCallbacks(this);
            this.options.getLogger().log(SentryLevel.DEBUG, "ActivityLifecycleIntegration installed.", new Object[0]);
        }
    }

    private boolean isPerformanceEnabled(SentryAndroidOptions sentryAndroidOptions) {
        return sentryAndroidOptions.isTracingEnabled() && sentryAndroidOptions.isEnableAutoActivityLifecycleTracing();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.application.unregisterActivityLifecycleCallbacks(this);
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "ActivityLifecycleIntegration removed.", new Object[0]);
        }
        this.activityFramesTracker.stop();
    }

    private void addBreadcrumb(Activity activity, String str) {
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions == null || this.hub == null || !sentryAndroidOptions.isEnableActivityLifecycleBreadcrumbs()) {
            return;
        }
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType(NotificationCompat.CATEGORY_NAVIGATION);
        breadcrumb.setData("state", str);
        breadcrumb.setData("screen", getActivityName(activity));
        breadcrumb.setCategory("ui.lifecycle");
        breadcrumb.setLevel(SentryLevel.INFO);
        this.hub.addBreadcrumb(breadcrumb);
    }

    private String getActivityName(Activity activity) {
        return activity.getClass().getSimpleName();
    }

    private void stopPreviousTransactions() {
        Iterator<Map.Entry<Activity, ITransaction>> it = this.activitiesWithOngoingTransactions.entrySet().iterator();
        while (it.hasNext()) {
            finishTransaction(it.next().getValue());
        }
    }

    private void startTracing(final Activity activity) {
        final ITransaction iTransactionStartTransaction;
        if (!this.performanceEnabled || isRunningTransaction(activity) || this.hub == null) {
            return;
        }
        stopPreviousTransactions();
        String activityName = getActivityName(activity);
        Date appStartTime = this.foregroundImportance ? AppStartState.getInstance().getAppStartTime() : null;
        Boolean boolIsColdStart = AppStartState.getInstance().isColdStart();
        if (this.firstActivityCreated || appStartTime == null || boolIsColdStart == null) {
            iTransactionStartTransaction = this.hub.startTransaction(activityName, UI_LOAD_OP, (Date) null, true, new TransactionFinishedCallback() { // from class: io.sentry.android.core.-$$Lambda$ActivityLifecycleIntegration$4nPJTfJPmYicrRoHyRXR7SUDAok
                @Override // io.sentry.TransactionFinishedCallback
                public final void execute(ITransaction iTransaction) {
                    this.f$0.lambda$startTracing$0$ActivityLifecycleIntegration(activity, iTransaction);
                }
            });
        } else {
            iTransactionStartTransaction = this.hub.startTransaction(activityName, UI_LOAD_OP, appStartTime, true, new TransactionFinishedCallback() { // from class: io.sentry.android.core.-$$Lambda$ActivityLifecycleIntegration$1Xa20D8rbI_RfOTCST8-8mCjmDU
                @Override // io.sentry.TransactionFinishedCallback
                public final void execute(ITransaction iTransaction) {
                    this.f$0.lambda$startTracing$1$ActivityLifecycleIntegration(activity, iTransaction);
                }
            });
            this.appStartSpan = iTransactionStartTransaction.startChild(getAppStartOp(boolIsColdStart.booleanValue()), getAppStartDesc(boolIsColdStart.booleanValue()), appStartTime);
        }
        this.hub.configureScope(new ScopeCallback() { // from class: io.sentry.android.core.-$$Lambda$ActivityLifecycleIntegration$m5wK_FFyhcFBSSy1Y5wE3bEmRwg
            @Override // io.sentry.ScopeCallback
            public final void run(Scope scope) {
                this.f$0.lambda$startTracing$2$ActivityLifecycleIntegration(iTransactionStartTransaction, scope);
            }
        });
        this.activitiesWithOngoingTransactions.put(activity, iTransactionStartTransaction);
    }

    public /* synthetic */ void lambda$startTracing$0$ActivityLifecycleIntegration(Activity activity, ITransaction iTransaction) {
        this.activityFramesTracker.setMetrics(activity, iTransaction.getEventId());
    }

    public /* synthetic */ void lambda$startTracing$1$ActivityLifecycleIntegration(Activity activity, ITransaction iTransaction) {
        this.activityFramesTracker.setMetrics(activity, iTransaction.getEventId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: applyScope, reason: merged with bridge method [inline-methods] */
    public void lambda$startTracing$2$ActivityLifecycleIntegration(final Scope scope, final ITransaction iTransaction) {
        scope.withTransaction(new Scope.IWithTransaction() { // from class: io.sentry.android.core.-$$Lambda$ActivityLifecycleIntegration$6fT25agdPIZfrwWfpumYS-6eQKQ
            @Override // io.sentry.Scope.IWithTransaction
            public final void accept(ITransaction iTransaction2) {
                this.f$0.lambda$applyScope$3$ActivityLifecycleIntegration(scope, iTransaction, iTransaction2);
            }
        });
    }

    public /* synthetic */ void lambda$applyScope$3$ActivityLifecycleIntegration(Scope scope, ITransaction iTransaction, ITransaction iTransaction2) {
        if (iTransaction2 == null) {
            scope.setTransaction(iTransaction);
            return;
        }
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "Transaction '%s' won't be bound to the Scope since there's one already in there.", iTransaction.getName());
        }
    }

    private boolean isRunningTransaction(Activity activity) {
        return this.activitiesWithOngoingTransactions.containsKey(activity);
    }

    private void stopTracing(Activity activity, boolean z) {
        if (this.performanceEnabled && z) {
            finishTransaction(this.activitiesWithOngoingTransactions.get(activity));
        }
    }

    private void finishTransaction(ITransaction iTransaction) {
        if (iTransaction == null || iTransaction.isFinished()) {
            return;
        }
        SpanStatus status = iTransaction.getStatus();
        if (status == null) {
            status = SpanStatus.OK;
        }
        iTransaction.finish(status);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public synchronized void onActivityCreated(Activity activity, Bundle bundle) {
        setColdStart(bundle);
        addBreadcrumb(activity, "created");
        startTracing(activity);
        this.firstActivityCreated = true;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public synchronized void onActivityStarted(Activity activity) {
        this.activityFramesTracker.addActivity(activity);
        addBreadcrumb(activity, "started");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public synchronized void onActivityResumed(Activity activity) {
        if (!this.firstActivityResumed) {
            if (this.foregroundImportance) {
                AppStartState.getInstance().setAppStartEnd();
            } else if (this.options != null) {
                this.options.getLogger().log(SentryLevel.DEBUG, "App Start won't be reported because Process wasn't of foregroundImportance.", new Object[0]);
            }
            if (this.performanceEnabled && this.appStartSpan != null) {
                this.appStartSpan.finish();
            }
            this.firstActivityResumed = true;
        }
        addBreadcrumb(activity, "resumed");
        if (!this.isAllActivityCallbacksAvailable && this.options != null) {
            stopTracing(activity, this.options.isEnableActivityLifecycleTracingAutoFinish());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public synchronized void onActivityPostResumed(Activity activity) {
        if (this.isAllActivityCallbacksAvailable && this.options != null) {
            stopTracing(activity, this.options.isEnableActivityLifecycleTracingAutoFinish());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public synchronized void onActivityPaused(Activity activity) {
        addBreadcrumb(activity, "paused");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public synchronized void onActivityStopped(Activity activity) {
        addBreadcrumb(activity, "stopped");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public synchronized void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        addBreadcrumb(activity, "saveInstanceState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public synchronized void onActivityDestroyed(Activity activity) {
        addBreadcrumb(activity, "destroyed");
        if (this.appStartSpan != null && !this.appStartSpan.isFinished()) {
            this.appStartSpan.finish(SpanStatus.CANCELLED);
        }
        stopTracing(activity, true);
        this.appStartSpan = null;
        if (this.performanceEnabled) {
            this.activitiesWithOngoingTransactions.remove(activity);
        }
    }

    WeakHashMap<Activity, ITransaction> getActivitiesWithOngoingTransactions() {
        return this.activitiesWithOngoingTransactions;
    }

    ISpan getAppStartSpan() {
        return this.appStartSpan;
    }

    private void setColdStart(Bundle bundle) {
        if (this.firstActivityCreated) {
            return;
        }
        AppStartState.getInstance().setColdStart(bundle == null);
    }

    private boolean isForegroundImportance(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            Object systemService = context.getSystemService("activity");
            if (!(systemService instanceof ActivityManager) || (runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses()) == null) {
                return false;
            }
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.importance == 100;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }
}
