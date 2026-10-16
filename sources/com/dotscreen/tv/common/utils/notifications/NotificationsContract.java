package com.dotscreen.tv.common.utils.notifications;

import android.net.Uri;
import kotlin.Metadata;

/* JADX INFO: compiled from: NotificationsContract.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010!\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u000e\u0010#\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/dotscreen/tv/common/utils/notifications/NotificationsContract;", "", "()V", "ACTION_NOTIFICATION_HIDE", "", "ACTION_OPEN_NOTIFICATION_PANEL", "ACTION_OPEN_TV_NOTIFICATION_PANEL", "ACTION_SHOW_UNSHOWN_NOTIFICATIONS", "AUTHORITY", "COLUMN_AUTODISMISS", "COLUMN_BIG_PICTURE", "COLUMN_CHANNEL", "COLUMN_CONTENT_BUTTON_LABEL", "COLUMN_COUNT", "COLUMN_DISMISSIBLE", "COLUMN_DISMISS_BUTTON_LABEL", "COLUMN_FLAGS", "COLUMN_HAS_CONTENT_INTENT", "COLUMN_NOTIFICATION_HIDDEN", "COLUMN_NOTIF_TEXT", "COLUMN_NOTIF_TITLE", "COLUMN_ONGOING", "COLUMN_PACKAGE_NAME", "COLUMN_PROGRESS", "COLUMN_PROGRESS_MAX", "COLUMN_SBN_KEY", "COLUMN_SMALL_ICON", "COLUMN_TAG", "CONTENT_URI", "Landroid/net/Uri;", "getCONTENT_URI", "()Landroid/net/Uri;", "NOTIFICATION_KEY", "NOTIFS_COUNT_URI", "getNOTIFS_COUNT_URI", "PATH_NOTIFS", "PATH_NOTIFS_COUNT", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NotificationsContract {
    public static final String ACTION_NOTIFICATION_HIDE = "android.tvservice.action.NOTIFICATION_HIDE";
    public static final String ACTION_OPEN_NOTIFICATION_PANEL = "com.android.tv.NOTIFICATIONS_PANEL";
    public static final String ACTION_OPEN_TV_NOTIFICATION_PANEL = "com.android.tv.action.OPEN_NOTIFICATIONS_PANEL";
    public static final String ACTION_SHOW_UNSHOWN_NOTIFICATIONS = "android.tvservice.action.SHOW_UNSHOWN_NOTIFICATIONS";
    private static final String AUTHORITY = "com.android.tv.notifications.NotificationContentProvider";
    public static final String COLUMN_AUTODISMISS = "is_auto_dismiss";
    public static final String COLUMN_BIG_PICTURE = "big_picture";
    public static final String COLUMN_CHANNEL = "channel";
    public static final String COLUMN_CONTENT_BUTTON_LABEL = "content_button_label";
    public static final String COLUMN_COUNT = "count";
    public static final String COLUMN_DISMISSIBLE = "dismissible";
    public static final String COLUMN_DISMISS_BUTTON_LABEL = "dismiss_button_label";
    public static final String COLUMN_FLAGS = "flags";
    public static final String COLUMN_HAS_CONTENT_INTENT = "has_content_intent";
    public static final String COLUMN_NOTIFICATION_HIDDEN = "notification_hidden";
    public static final String COLUMN_NOTIF_TEXT = "text";
    public static final String COLUMN_NOTIF_TITLE = "title";
    public static final String COLUMN_ONGOING = "ongoing";
    public static final String COLUMN_PACKAGE_NAME = "package_name";
    public static final String COLUMN_PROGRESS = "progress";
    public static final String COLUMN_PROGRESS_MAX = "progress_max";
    public static final String COLUMN_SBN_KEY = "sbn_key";
    public static final String COLUMN_SMALL_ICON = "small_icon";
    public static final String COLUMN_TAG = "tag";
    public static final String NOTIFICATION_KEY = "sbn_key";
    private static final String PATH_NOTIFS = "notifications";
    private static final String PATH_NOTIFS_COUNT = "notifications/count";
    public static final NotificationsContract INSTANCE = new NotificationsContract();
    private static final Uri CONTENT_URI = Uri.parse("content://com.android.tv.notifications.NotificationContentProvider/notifications");
    private static final Uri NOTIFS_COUNT_URI = Uri.parse("content://com.android.tv.notifications.NotificationContentProvider/notifications/count");

    private NotificationsContract() {
    }

    public final Uri getCONTENT_URI() {
        return CONTENT_URI;
    }

    public final Uri getNOTIFS_COUNT_URI() {
        return NOTIFS_COUNT_URI;
    }
}
