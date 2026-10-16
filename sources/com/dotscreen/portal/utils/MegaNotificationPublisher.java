package com.dotscreen.portal.utils;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegaNotificationPublisher.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/dotscreen/portal/utils/MegaNotificationPublisher;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegaNotificationPublisher extends BroadcastReceiver {
    private static final String JSON_START = "start_time";
    private static final String JSON_TITLE = "title";
    private static final String JSON_USER_ID = "user_id";
    public static final String NOTIFICATION_DEEPLINK = "mega_notification_deeplink";
    public static final String NOTIFICATION_START = "mega_notification_text";
    public static final String NOTIFICATION_TEXT = "mega_notification_text";
    public static final String NOTIFICATION_TITLE = "mega_notification_title";
    private static boolean isCleaning = false;
    private static HashMap<String, PendingIntent> scheduledMap = null;
    private static int sendId_modifier = 0;
    private static final int time_before = 120000;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<Integer> emittedNotifications = new ArrayList();
    private static final int delta_after = 900000;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.e("MegaNotificationService", "received intent");
        if (context != null && intent != null && intent.hasExtra(NOTIFICATION_TITLE) && intent.hasExtra("mega_notification_text") && intent.hasExtra(NOTIFICATION_DEEPLINK)) {
            MegaNotificationService.Send send = MegaNotificationService.Send.INSTANCE;
            String stringExtra = intent.getStringExtra(NOTIFICATION_TITLE);
            Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(NOTIFICATION_TITLE)");
            String stringExtra2 = intent.getStringExtra("mega_notification_text");
            Intrinsics.checkExpressionValueIsNotNull(stringExtra2, "intent.getStringExtra(NOTIFICATION_TEXT)");
            emittedNotifications.add(Integer.valueOf(send.sendNotification(context, stringExtra, stringExtra2, intent.getStringExtra(NOTIFICATION_DEEPLINK))));
        }
    }

    /* JADX INFO: compiled from: MegaNotificationPublisher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u001e\u0010&\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020)J\u0016\u0010*\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010+\u001a\u00020\u0004J8\u0010,\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\b\u0010/\u001a\u0004\u0018\u00010\u00042\u0006\u0010(\u001a\u00020)R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017R:\u0010\u0018\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001a\u0018\u0001`\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/dotscreen/portal/utils/MegaNotificationPublisher$Companion;", "", "()V", "JSON_START", "", "JSON_TITLE", "JSON_USER_ID", "NOTIFICATION_DEEPLINK", "NOTIFICATION_START", "NOTIFICATION_TEXT", "NOTIFICATION_TITLE", "delta_after", "", "getDelta_after", "()I", "emittedNotifications", "", "getEmittedNotifications", "()Ljava/util/List;", "isCleaning", "", "()Z", "setCleaning", "(Z)V", "scheduledMap", "Ljava/util/HashMap;", "Landroid/app/PendingIntent;", "Lkotlin/collections/HashMap;", "getScheduledMap", "()Ljava/util/HashMap;", "setScheduledMap", "(Ljava/util/HashMap;)V", "sendId_modifier", "time_before", "cleanbase", "", "context", "Landroid/content/Context;", "removeNotification", "title", "timeInMs", "", "removeUserNotification", "currentUserId", "scheduleNotification", "message", "userId", "deeplink", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<Integer> getEmittedNotifications() {
            return MegaNotificationPublisher.emittedNotifications;
        }

        public final HashMap<String, PendingIntent> getScheduledMap() {
            return MegaNotificationPublisher.scheduledMap;
        }

        public final void setScheduledMap(HashMap<String, PendingIntent> map) {
            MegaNotificationPublisher.scheduledMap = map;
        }

        public final void scheduleNotification(Context context, String title, String message, String userId, String deeplink, long timeInMs) throws JSONException {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(title, "title");
            Intrinsics.checkParameterIsNotNull(message, "message");
            Intrinsics.checkParameterIsNotNull(userId, "userId");
            Companion companion = this;
            companion.cleanbase(context);
            Intent intent = new Intent(context, (Class<?>) MegaNotificationPublisher.class);
            intent.putExtra(MegaNotificationPublisher.NOTIFICATION_TITLE, title);
            intent.putExtra("mega_notification_text", timeInMs);
            intent.putExtra("mega_notification_text", message);
            if (deeplink != null) {
                intent.putExtra(MegaNotificationPublisher.NOTIFICATION_DEEPLINK, deeplink);
            }
            int i = ((int) timeInMs) + MegaNotificationPublisher.sendId_modifier;
            MegaNotificationPublisher.sendId_modifier = MegaNotificationPublisher.sendId_modifier == 999 ? 0 : MegaNotificationPublisher.sendId_modifier + 1;
            PendingIntent broadcast = PendingIntent.getBroadcast(context, i, intent, C.ENCODING_PCM_MU_LAW);
            Intrinsics.checkExpressionValueIsNotNull(broadcast, "PendingIntent.getBroadca…tent.FLAG_CANCEL_CURRENT)");
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("title", title);
            jSONObject.put("start_time", timeInMs);
            jSONObject.put(MegaNotificationPublisher.JSON_USER_ID, userId);
            String string = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(string, "jsonKey.toString()");
            if (companion.getScheduledMap() == null) {
                companion.setScheduledMap(new HashMap<>());
            } else {
                HashMap<String, PendingIntent> scheduledMap = companion.getScheduledMap();
                if (scheduledMap != null ? scheduledMap.containsKey(string) : false) {
                    companion.removeNotification(context, title, timeInMs);
                }
            }
            Log.d("MegaNotificationPublisher", "Add for " + title);
            HashMap<String, PendingIntent> scheduledMap2 = companion.getScheduledMap();
            if (scheduledMap2 != null) {
                scheduledMap2.put(string, broadcast);
            }
            if (alarmManager != null) {
                alarmManager.setExact(1, timeInMs - ((long) MegaNotificationPublisher.time_before), broadcast);
            }
        }

        public final int getDelta_after() {
            return MegaNotificationPublisher.delta_after;
        }

        public final boolean isCleaning() {
            return MegaNotificationPublisher.isCleaning;
        }

        public final void setCleaning(boolean z) {
            MegaNotificationPublisher.isCleaning = z;
        }

        public final void cleanbase(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Companion companion = this;
            if (companion.getScheduledMap() == null || companion.isCleaning()) {
                return;
            }
            companion.setCleaning(true);
            try {
                ArrayList<String> arrayList = new ArrayList();
                HashMap<String, PendingIntent> scheduledMap = getScheduledMap();
                if (scheduledMap == null) {
                    Intrinsics.throwNpe();
                }
                for (String str : scheduledMap.keySet()) {
                    if (new JSONObject(str).optLong("start_time", 0L) + ((long) getDelta_after()) < new Date().getTime()) {
                        arrayList.add(str);
                    }
                }
                if (true ^ arrayList.isEmpty()) {
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
                    for (String str2 : arrayList) {
                        HashMap<String, PendingIntent> scheduledMap2 = getScheduledMap();
                        if (scheduledMap2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (scheduledMap2.get(str2) != null && alarmManager != null) {
                            HashMap<String, PendingIntent> scheduledMap3 = getScheduledMap();
                            if (scheduledMap3 == null) {
                                Intrinsics.throwNpe();
                            }
                            alarmManager.cancel(scheduledMap3.get(str2));
                        }
                        HashMap<String, PendingIntent> scheduledMap4 = getScheduledMap();
                        if (scheduledMap4 == null) {
                            Intrinsics.throwNpe();
                        }
                        scheduledMap4.remove(str2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            companion.setCleaning(false);
        }

        public final void removeUserNotification(Context context, String currentUserId) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(currentUserId, "currentUserId");
            Log.d("MegaNotificationPublisher", "Ask remove reminder of user " + currentUserId);
            Object systemService = context.getApplicationContext().getSystemService("notification");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
            }
            NotificationManager notificationManager = (NotificationManager) systemService;
            Companion companion = this;
            Iterator<T> it = companion.getEmittedNotifications().iterator();
            while (it.hasNext()) {
                notificationManager.cancel(((Number) it.next()).intValue());
            }
            if (companion.getScheduledMap() == null || companion.isCleaning()) {
                return;
            }
            companion.setCleaning(true);
            try {
                ArrayList<String> arrayList = new ArrayList();
                HashMap<String, PendingIntent> scheduledMap = getScheduledMap();
                if (scheduledMap == null) {
                    Intrinsics.throwNpe();
                }
                for (String str : scheduledMap.keySet()) {
                    String string = new JSONObject(str).getString(MegaNotificationPublisher.JSON_USER_ID);
                    Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject(it).getString(JSON_USER_ID)");
                    if (Intrinsics.areEqual(string, currentUserId)) {
                        arrayList.add(str);
                    }
                }
                if (!arrayList.isEmpty()) {
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
                    for (String str2 : arrayList) {
                        HashMap<String, PendingIntent> scheduledMap2 = getScheduledMap();
                        if (scheduledMap2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (scheduledMap2.get(str2) != null && alarmManager != null) {
                            HashMap<String, PendingIntent> scheduledMap3 = getScheduledMap();
                            if (scheduledMap3 == null) {
                                Intrinsics.throwNpe();
                            }
                            alarmManager.cancel(scheduledMap3.get(str2));
                        }
                        HashMap<String, PendingIntent> scheduledMap4 = getScheduledMap();
                        if (scheduledMap4 == null) {
                            Intrinsics.throwNpe();
                        }
                        scheduledMap4.remove(str2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            companion.setCleaning(false);
        }

        public final void removeNotification(Context context, String title, long timeInMs) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(title, "title");
            Log.d("MegaNotificationPublisher", "Ask remove for " + title);
            Companion companion = this;
            companion.cleanbase(context);
            LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
            Log.d("MegaNotificationPublisher", "ScheduledMap => " + companion.getScheduledMap());
            if (companion.getScheduledMap() != null) {
                Log.d("MegaNotificationPublisher", "ScheduledMap is not empty");
                HashMap<String, PendingIntent> scheduledMap = companion.getScheduledMap();
                if (scheduledMap == null) {
                    Intrinsics.throwNpe();
                }
                for (String reminder : scheduledMap.keySet()) {
                    Log.d("MegaNotificationPublisher", "remove lookup => " + reminder);
                    JSONObject jSONObject = new JSONObject(reminder);
                    if (Intrinsics.areEqual(jSONObject.getString("title"), title) && jSONObject.getLong("start_time") == timeInMs) {
                        Log.d("MegaNotificationPublisher", "Remove for " + title);
                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
                        HashMap<String, PendingIntent> scheduledMap2 = companion.getScheduledMap();
                        if (scheduledMap2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (scheduledMap2.get(reminder) != null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("cancel ");
                            HashMap<String, PendingIntent> scheduledMap3 = companion.getScheduledMap();
                            if (scheduledMap3 == null) {
                                Intrinsics.throwNpe();
                            }
                            sb.append(scheduledMap3.get(reminder));
                            Log.d("MegaNotificationPublisher", sb.toString());
                            if (alarmManager != null) {
                                HashMap<String, PendingIntent> scheduledMap4 = companion.getScheduledMap();
                                if (scheduledMap4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                alarmManager.cancel(scheduledMap4.get(reminder));
                            }
                        }
                        Intrinsics.checkExpressionValueIsNotNull(reminder, "reminder");
                        linkedHashSet.add(reminder);
                    }
                }
                for (String str : linkedHashSet) {
                    HashMap<String, PendingIntent> scheduledMap5 = MegaNotificationPublisher.INSTANCE.getScheduledMap();
                    if (scheduledMap5 == null) {
                        Intrinsics.throwNpe();
                    }
                    scheduledMap5.remove(str);
                }
            }
        }
    }
}
