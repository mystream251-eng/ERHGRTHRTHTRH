package com.dotscreen.tv.common.utils.notifications;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import io.reactivex.subjects.BehaviorSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\t\u0018\u00002\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0018J\u0006\u0010\u001d\u001a\u00020\u0018J\b\u0010\u001e\u001a\u00020\u0018H\u0003R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0016\u0010\u000b\u001a\n \u0007*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00140\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006 "}, d2 = {"Lcom/dotscreen/tv/common/utils/notifications/NotificationManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "contentObserver", "com/dotscreen/tv/common/utils/notifications/NotificationManager$contentObserver$1", "Lcom/dotscreen/tv/common/utils/notifications/NotificationManager$contentObserver$1;", "contentResolver", "Landroid/content/ContentResolver;", "getContext", "()Landroid/content/Context;", "handler", "Landroid/os/Handler;", "mainHandler", NotificationCompat.CATEGORY_STATUS, "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/dotscreen/tv/common/utils/notifications/NotificationManager$NotificationStatus;", "getStatus", "()Lio/reactivex/subjects/BehaviorSubject;", "automation", "", "openNotificationPannel", "activity", "Landroid/app/Activity;", "start", "stop", "updateNotificationsCount", "NotificationStatus", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NotificationManager {
    private final String TAG;
    private final NotificationManager$contentObserver$1 contentObserver;
    private final ContentResolver contentResolver;
    private final Context context;
    private final Handler handler;
    private Handler mainHandler;
    private final BehaviorSubject<NotificationStatus> status;

    public final void stop() {
    }

    /* JADX WARN: Type inference failed for: r8v6, types: [com.dotscreen.tv.common.utils.notifications.NotificationManager$contentObserver$1] */
    public NotificationManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = NotificationManager.class.getSimpleName();
        this.contentResolver = this.context.getContentResolver();
        this.handler = new Handler();
        final Handler handler = this.handler;
        this.contentObserver = new ContentObserver(handler) { // from class: com.dotscreen.tv.common.utils.notifications.NotificationManager$contentObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                Log.e(this.this$0.TAG, "ContentObserver#onChange(" + selfChange + ')');
                this.this$0.updateNotificationsCount();
                super.onChange(selfChange);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri url) {
                Log.e(this.this$0.TAG, "ContentObserver#onChange(" + selfChange + ", " + url + ')');
                this.this$0.updateNotificationsCount();
                super.onChange(selfChange);
            }

            @Override // android.database.ContentObserver
            public boolean deliverSelfNotifications() {
                Log.e(this.this$0.TAG, "ContentObserver#deliverSelfNotifications()");
                return true;
            }
        };
        BehaviorSubject<NotificationStatus> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(new NotificationStatus(0, false, false, 4, null));
        Intrinsics.checkExpressionValueIsNotNull(behaviorSubjectCreateDefault, "BehaviorSubject.createDe…ificationStatus(0,false))");
        this.status = behaviorSubjectCreateDefault;
        this.mainHandler = new Handler();
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: NotificationManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/dotscreen/tv/common/utils/notifications/NotificationManager$NotificationStatus;", "", NotificationsContract.COLUMN_COUNT, "", "unread", "", "keepSameState", "(IZZ)V", "getCount", "()I", "getKeepSameState", "()Z", "getUnread", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class NotificationStatus {
        private final int count;
        private final boolean keepSameState;
        private final boolean unread;

        public NotificationStatus(int i, boolean z, boolean z2) {
            this.count = i;
            this.unread = z;
            this.keepSameState = z2;
        }

        public /* synthetic */ NotificationStatus(int i, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, z, (i2 & 4) != 0 ? false : z2);
        }

        public final int getCount() {
            return this.count;
        }

        public final boolean getKeepSameState() {
            return this.keepSameState;
        }

        public final boolean getUnread() {
            return this.unread;
        }
    }

    public final BehaviorSubject<NotificationStatus> getStatus() {
        return this.status;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void automation() {
        this.mainHandler.postDelayed(new Runnable() { // from class: com.dotscreen.tv.common.utils.notifications.NotificationManager.automation.1
            @Override // java.lang.Runnable
            public final void run() {
                NotificationManager.this.updateNotificationsCount();
                NotificationManager.this.automation();
            }
        }, 1500L);
    }

    public final void start() {
        updateNotificationsCount();
        automation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNotificationsCount() {
        try {
            ContentResolver contentResolver = this.contentResolver;
            Uri notifs_count_uri = NotificationsContract.INSTANCE.getNOTIFS_COUNT_URI();
            if (notifs_count_uri == null) {
                Intrinsics.throwNpe();
            }
            Cursor cursorQuery = contentResolver.query(notifs_count_uri, null, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                final int i = cursorQuery.getInt(cursorQuery.getColumnIndex(NotificationsContract.COLUMN_COUNT));
                NotificationStatus value = this.status.getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value, "status.value!!");
                final NotificationStatus notificationStatus = value;
                if (i != notificationStatus.getCount()) {
                    if (i == 0) {
                        this.handler.post(new Runnable() { // from class: com.dotscreen.tv.common.utils.notifications.NotificationManager.updateNotificationsCount.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                NotificationManager.this.getStatus().onNext(new NotificationStatus(0, false, false, 4, null));
                            }
                        });
                    } else {
                        this.handler.post(new Runnable() { // from class: com.dotscreen.tv.common.utils.notifications.NotificationManager.updateNotificationsCount.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                BehaviorSubject<NotificationStatus> status = NotificationManager.this.getStatus();
                                int i2 = i;
                                status.onNext(new NotificationStatus(i2, true, i2 < notificationStatus.getCount()));
                            }
                        });
                    }
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e) {
            Log.e(this.TAG, "Can't update notification", e);
        }
    }

    public final void openNotificationPannel(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        activity.startActivity(new Intent(NotificationsContract.ACTION_OPEN_TV_NOTIFICATION_PANEL));
        NotificationStatus value = this.status.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "status.value!!");
        final NotificationStatus notificationStatus = value;
        this.handler.post(new Runnable() { // from class: com.dotscreen.tv.common.utils.notifications.NotificationManager.openNotificationPannel.1
            @Override // java.lang.Runnable
            public final void run() {
                NotificationManager.this.getStatus().onNext(new NotificationStatus(notificationStatus.getCount(), false, false, 4, null));
            }
        });
    }
}
