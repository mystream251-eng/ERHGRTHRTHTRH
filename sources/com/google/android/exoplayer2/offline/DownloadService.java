package com.google.android.exoplayer2.offline;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.exoplayer2.scheduler.Requirements;
import com.google.android.exoplayer2.scheduler.RequirementsWatcher;
import com.google.android.exoplayer2.scheduler.Scheduler;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.NotificationUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class DownloadService extends Service {
    public static final String ACTION_ADD = "com.google.android.exoplayer.downloadService.action.ADD";
    public static final String ACTION_INIT = "com.google.android.exoplayer.downloadService.action.INIT";
    public static final String ACTION_RELOAD_REQUIREMENTS = "com.google.android.exoplayer.downloadService.action.RELOAD_REQUIREMENTS";
    private static final String ACTION_RESTART = "com.google.android.exoplayer.downloadService.action.RESTART";
    private static final boolean DEBUG = false;
    public static final long DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL = 1000;
    public static final int FOREGROUND_NOTIFICATION_ID_NONE = 0;
    public static final String KEY_DOWNLOAD_ACTION = "download_action";
    public static final String KEY_FOREGROUND = "foreground";
    private static final String TAG = "DownloadService";
    private final String channelId;
    private final int channelName;
    private DownloadManager downloadManager;
    private DownloadManagerListener downloadManagerListener;
    private final ForegroundNotificationUpdater foregroundNotificationUpdater;
    private int lastStartId;
    private boolean startedInForeground;
    private boolean taskRemoved;
    private static final HashMap<Class<? extends DownloadService>, RequirementsHelper> requirementsHelpers = new HashMap<>();
    private static final Requirements DEFAULT_REQUIREMENTS = new Requirements(1, false, false);

    private void logd(String str) {
    }

    protected abstract DownloadManager getDownloadManager();

    protected abstract Scheduler getScheduler();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void onTaskStateChanged(DownloadManager.TaskState taskState) {
    }

    protected DownloadService(int i) {
        this(i, 1000L);
    }

    protected DownloadService(int i, long j) {
        this(i, j, null, 0);
    }

    protected DownloadService(int i, long j, String str, int i2) {
        this.foregroundNotificationUpdater = i == 0 ? null : new ForegroundNotificationUpdater(i, j);
        this.channelId = str;
        this.channelName = i2;
    }

    public static Intent buildAddActionIntent(Context context, Class<? extends DownloadService> cls, DownloadAction downloadAction, boolean z) {
        return getIntent(context, cls, ACTION_ADD).putExtra(KEY_DOWNLOAD_ACTION, downloadAction.toByteArray()).putExtra(KEY_FOREGROUND, z);
    }

    public static void startWithAction(Context context, Class<? extends DownloadService> cls, DownloadAction downloadAction, boolean z) {
        Intent intentBuildAddActionIntent = buildAddActionIntent(context, cls, downloadAction, z);
        if (z) {
            Util.startForegroundService(context, intentBuildAddActionIntent);
        } else {
            context.startService(intentBuildAddActionIntent);
        }
    }

    public static void start(Context context, Class<? extends DownloadService> cls) {
        context.startService(getIntent(context, cls, ACTION_INIT));
    }

    public static void startForeground(Context context, Class<? extends DownloadService> cls) {
        Util.startForegroundService(context, getIntent(context, cls, ACTION_INIT).putExtra(KEY_FOREGROUND, true));
    }

    @Override // android.app.Service
    public void onCreate() {
        logd("onCreate");
        String str = this.channelId;
        if (str != null) {
            NotificationUtil.createNotificationChannel(this, str, this.channelName, 2);
        }
        this.downloadManager = getDownloadManager();
        DownloadManagerListener downloadManagerListener = new DownloadManagerListener();
        this.downloadManagerListener = downloadManagerListener;
        this.downloadManager.addListener(downloadManagerListener);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String action;
        this.lastStartId = i2;
        byte b = 0;
        this.taskRemoved = false;
        if (intent != null) {
            action = intent.getAction();
            this.startedInForeground |= intent.getBooleanExtra(KEY_FOREGROUND, false) || ACTION_RESTART.equals(action);
        } else {
            action = null;
        }
        if (action == null) {
            action = ACTION_INIT;
        }
        logd("onStartCommand action: " + action + " startId: " + i2);
        switch (action.hashCode()) {
            case -871181424:
                b = !action.equals(ACTION_RESTART) ? (byte) -1 : (byte) 1;
                break;
            case -608867945:
                b = !action.equals(ACTION_RELOAD_REQUIREMENTS) ? (byte) -1 : (byte) 3;
                break;
            case -382886238:
                b = !action.equals(ACTION_ADD) ? (byte) -1 : (byte) 2;
                break;
            case 1015676687:
                if (!action.equals(ACTION_INIT)) {
                    b = -1;
                }
                break;
            default:
                b = -1;
                break;
        }
        if (b != 0 && b != 1) {
            if (b == 2) {
                byte[] byteArrayExtra = intent.getByteArrayExtra(KEY_DOWNLOAD_ACTION);
                if (byteArrayExtra == null) {
                    Log.e(TAG, "Ignoring ADD action with no action data");
                } else {
                    try {
                        this.downloadManager.handleAction(byteArrayExtra);
                    } catch (IOException e) {
                        Log.e(TAG, "Failed to handle ADD action", e);
                    }
                }
            } else if (b == 3) {
                stopWatchingRequirements();
            } else {
                Log.e(TAG, "Ignoring unrecognized action: " + action);
            }
        }
        Requirements requirements = getRequirements();
        if (requirements.checkRequirements(this)) {
            this.downloadManager.startDownloads();
        } else {
            this.downloadManager.stopDownloads();
        }
        maybeStartWatchingRequirements(requirements);
        if (this.downloadManager.isIdle()) {
            stop();
        }
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        logd("onTaskRemoved rootIntent: " + intent);
        this.taskRemoved = true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        logd("onDestroy");
        ForegroundNotificationUpdater foregroundNotificationUpdater = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater != null) {
            foregroundNotificationUpdater.stopPeriodicUpdates();
        }
        this.downloadManager.removeListener(this.downloadManagerListener);
        maybeStopWatchingRequirements();
    }

    protected Requirements getRequirements() {
        return DEFAULT_REQUIREMENTS;
    }

    protected Notification getForegroundNotification(DownloadManager.TaskState[] taskStateArr) {
        throw new IllegalStateException(getClass().getName() + " is started in the foreground but getForegroundNotification() is not implemented.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeStartWatchingRequirements(Requirements requirements) {
        if (this.downloadManager.getDownloadCount() == 0) {
            return;
        }
        Class<?> cls = getClass();
        if (requirementsHelpers.get(cls) == null) {
            RequirementsHelper requirementsHelper = new RequirementsHelper(this, requirements, getScheduler(), cls);
            requirementsHelpers.put((Class<? extends DownloadService>) cls, requirementsHelper);
            requirementsHelper.start();
            logd("started watching requirements");
        }
    }

    private void maybeStopWatchingRequirements() {
        if (this.downloadManager.getDownloadCount() > 0) {
            return;
        }
        stopWatchingRequirements();
    }

    private void stopWatchingRequirements() {
        RequirementsHelper requirementsHelperRemove = requirementsHelpers.remove(getClass());
        if (requirementsHelperRemove != null) {
            requirementsHelperRemove.stop();
            logd("stopped watching requirements");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop() {
        ForegroundNotificationUpdater foregroundNotificationUpdater = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater != null) {
            foregroundNotificationUpdater.stopPeriodicUpdates();
            if (this.startedInForeground && Util.SDK_INT >= 26) {
                this.foregroundNotificationUpdater.showNotificationIfNotAlready();
            }
        }
        if (Util.SDK_INT < 28 && this.taskRemoved) {
            stopSelf();
            logd("stopSelf()");
            return;
        }
        logd("stopSelf(" + this.lastStartId + ") result: " + stopSelfResult(this.lastStartId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Intent getIntent(Context context, Class<? extends DownloadService> cls, String str) {
        return new Intent(context, cls).setAction(str);
    }

    private final class DownloadManagerListener implements DownloadManager.Listener {
        private DownloadManagerListener() {
        }

        @Override // com.google.android.exoplayer2.offline.DownloadManager.Listener
        public void onInitialized(DownloadManager downloadManager) {
            DownloadService downloadService = DownloadService.this;
            downloadService.maybeStartWatchingRequirements(downloadService.getRequirements());
        }

        @Override // com.google.android.exoplayer2.offline.DownloadManager.Listener
        public void onTaskStateChanged(DownloadManager downloadManager, DownloadManager.TaskState taskState) {
            DownloadService.this.onTaskStateChanged(taskState);
            if (DownloadService.this.foregroundNotificationUpdater != null) {
                if (taskState.state == 1) {
                    DownloadService.this.foregroundNotificationUpdater.startPeriodicUpdates();
                } else {
                    DownloadService.this.foregroundNotificationUpdater.update();
                }
            }
        }

        @Override // com.google.android.exoplayer2.offline.DownloadManager.Listener
        public final void onIdle(DownloadManager downloadManager) {
            DownloadService.this.stop();
        }
    }

    private final class ForegroundNotificationUpdater implements Runnable {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private boolean notificationDisplayed;
        private final int notificationId;
        private boolean periodicUpdatesStarted;
        private final long updateInterval;

        public ForegroundNotificationUpdater(int i, long j) {
            this.notificationId = i;
            this.updateInterval = j;
        }

        public void startPeriodicUpdates() {
            this.periodicUpdatesStarted = true;
            update();
        }

        public void stopPeriodicUpdates() {
            this.periodicUpdatesStarted = false;
            this.handler.removeCallbacks(this);
        }

        public void update() {
            DownloadManager.TaskState[] allTaskStates = DownloadService.this.downloadManager.getAllTaskStates();
            DownloadService downloadService = DownloadService.this;
            downloadService.startForeground(this.notificationId, downloadService.getForegroundNotification(allTaskStates));
            this.notificationDisplayed = true;
            if (this.periodicUpdatesStarted) {
                this.handler.removeCallbacks(this);
                this.handler.postDelayed(this, this.updateInterval);
            }
        }

        public void showNotificationIfNotAlready() {
            if (this.notificationDisplayed) {
                return;
            }
            update();
        }

        @Override // java.lang.Runnable
        public void run() {
            update();
        }
    }

    private static final class RequirementsHelper implements RequirementsWatcher.Listener {
        private final Context context;
        private final Requirements requirements;
        private final RequirementsWatcher requirementsWatcher;
        private final Scheduler scheduler;
        private final Class<? extends DownloadService> serviceClass;

        private RequirementsHelper(Context context, Requirements requirements, Scheduler scheduler, Class<? extends DownloadService> cls) {
            this.context = context;
            this.requirements = requirements;
            this.scheduler = scheduler;
            this.serviceClass = cls;
            this.requirementsWatcher = new RequirementsWatcher(context, this, requirements);
        }

        public void start() {
            this.requirementsWatcher.start();
        }

        public void stop() {
            this.requirementsWatcher.stop();
            Scheduler scheduler = this.scheduler;
            if (scheduler != null) {
                scheduler.cancel();
            }
        }

        @Override // com.google.android.exoplayer2.scheduler.RequirementsWatcher.Listener
        public void requirementsMet(RequirementsWatcher requirementsWatcher) {
            try {
                notifyService();
                Scheduler scheduler = this.scheduler;
                if (scheduler != null) {
                    scheduler.cancel();
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.google.android.exoplayer2.scheduler.RequirementsWatcher.Listener
        public void requirementsNotMet(RequirementsWatcher requirementsWatcher) {
            try {
                notifyService();
            } catch (Exception unused) {
            }
            if (this.scheduler != null) {
                if (this.scheduler.schedule(this.requirements, this.context.getPackageName(), DownloadService.ACTION_RESTART)) {
                    return;
                }
                Log.e(DownloadService.TAG, "Scheduling downloads failed.");
            }
        }

        private void notifyService() throws Exception {
            try {
                this.context.startService(DownloadService.getIntent(this.context, this.serviceClass, DownloadService.ACTION_INIT));
            } catch (IllegalStateException e) {
                throw new Exception(e);
            }
        }
    }
}
