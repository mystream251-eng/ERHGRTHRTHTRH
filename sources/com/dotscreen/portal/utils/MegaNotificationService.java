package com.dotscreen.portal.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.work.PeriodicWorkRequest;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.home.HomeActivity;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import io.reactivex.functions.Consumer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegaNotificationService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/utils/MegaNotificationService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "onMessageReceived", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "sendRegistrationToServer", "Companion", "Send", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegaNotificationService extends FirebaseMessagingService {
    private static final String TAG = "MegaNotificationService";

    /* JADX WARN: Code duplicated, block: B:17:0x00a4  */
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        RemoteMessage.Notification it;
        Intrinsics.checkParameterIsNotNull(remoteMessage, "remoteMessage");
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Map<String, String> data = remoteMessage.getData();
        Intrinsics.checkExpressionValueIsNotNull(data, "remoteMessage.data");
        data.isEmpty();
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        boolean z = true;
        try {
            JSONObject jSONObject = new JSONObject(remoteMessage.getData());
            String strOptString = jSONObject.optString("title", "");
            Intrinsics.checkExpressionValueIsNotNull(strOptString, "json.optString(\"title\",\"\")");
            if (strOptString.length() > 0) {
                String strOptString2 = jSONObject.optString(TtmlNode.TAG_BODY, "");
                Intrinsics.checkExpressionValueIsNotNull(strOptString2, "json.optString(\"body\",\"\")");
                if (strOptString2.length() > 0) {
                    String string = jSONObject.getString("title");
                    Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"title\")");
                    String string2 = jSONObject.getString(TtmlNode.TAG_BODY);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"body\")");
                    Send.sendNotification$default(Send.INSTANCE, this, string, string2, null, 8, null);
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (z || (it = remoteMessage.getNotification()) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Message Notification Body: ");
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        sb.append(it.getBody());
        Log.d(TAG, sb.toString());
        if (it.getBody() == null || it.getTitle() == null) {
            return;
        }
        Send send = Send.INSTANCE;
        MegaNotificationService megaNotificationService = this;
        String title = it.getTitle();
        if (title == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(title, "it.title!!");
        String body = it.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(body, "it.body!!");
        Send.sendNotification$default(send, megaNotificationService, title, body, null, 8, null);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }

    private final void sendRegistrationToServer(String token) {
        BOService bOService = PortalApplication.INSTANCE.getSingleton(this).getBOService();
        if (bOService == null) {
            Intrinsics.throwNpe();
        }
        UserService userService = bOService.getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        MegacableUserService megacableUserService = (MegacableUserService) userService;
        if (token == null) {
            Intrinsics.throwNpe();
        }
        megacableUserService.registerOMPToken(token).subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.utils.MegaNotificationService.sendRegistrationToServer.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                Log.i(MegaNotificationService.TAG, "success = " + jSONObject);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.utils.MegaNotificationService.sendRegistrationToServer.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(MegaNotificationService.TAG, "error = " + th.getMessage());
            }
        });
    }

    /* JADX INFO: compiled from: MegaNotificationService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/dotscreen/portal/utils/MegaNotificationService$Send;", "", "()V", "sendId_modifier", "", "sendNotification", "context", "Landroid/content/Context;", "title", "", "messageBody", "deeplink", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Send {
        public static final Send INSTANCE = new Send();
        private static int sendId_modifier;

        private Send() {
        }

        public static /* synthetic */ int sendNotification$default(Send send, Context context, String str, String str2, String str3, int i, Object obj) {
            if ((i & 8) != 0) {
                str3 = (String) null;
            }
            return send.sendNotification(context, str, str2, str3);
        }

        public final int sendNotification(Context context, String title, String messageBody, String deeplink) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(title, "title");
            Intrinsics.checkParameterIsNotNull(messageBody, "messageBody");
            Intent intent = deeplink == null ? new Intent(context, (Class<?>) HomeActivity.class) : new Intent("android.intent.action.VIEW", Uri.parse(deeplink));
            Log.e(MegaNotificationService.TAG, "send notif title = " + title + " // messageBody = " + messageBody);
            intent.addFlags(67108864);
            int iCurrentTimeMillis = (int) System.currentTimeMillis();
            int i = sendId_modifier;
            int i2 = iCurrentTimeMillis + i;
            sendId_modifier = i == 999 ? 0 : i + 1;
            PendingIntent activity = PendingIntent.getActivity(context, i2, intent, 1073741824);
            String string = context.getString(R.string.default_notification_channel_id);
            String str = title;
            String str2 = messageBody;
            Notification.Builder builderExtend = new Notification.Builder(context, string).setSmallIcon(R.drawable.logo).setLocalOnly(true).setContentTitle(str).setContentText(str2).setTicker(str).setContentText(str2).setAutoCancel(true).setCategory(NotificationCompat.CATEGORY_SYSTEM).setVisibility(1).setTimeoutAfter(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS).setPriority(2).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(activity).extend((Notification.Extender) new Notification.TvExtender().setSuppressShowOverApps(false));
            Object systemService = context.getSystemService("notification");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
            }
            NotificationManager notificationManager = (NotificationManager) systemService;
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel(string, "Megacable portal channel", 5));
                builderExtend.setChannelId(string);
            }
            notificationManager.notify(i2, builderExtend.build());
            return i2;
        }
    }
}
