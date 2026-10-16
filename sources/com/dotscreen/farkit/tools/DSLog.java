package com.dotscreen.farkit.tools;

import android.app.ActivityManager;
import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DSLog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0004J\u001f\u0010\u001b\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u001eJ\u000e\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0004J\u0016\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\nJ\u000e\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u0004J\u001f\u0010%\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010\u00042\b\u0010'\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u001eJ\u000e\u0010(\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0004J\u0010\u0010)\u001a\u00020\u00042\b\u0010*\u001a\u0004\u0018\u00010+J\u0016\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\nJ\u000e\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u00020\u0004J\u001f\u00101\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010\u00042\b\u0010'\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/dotscreen/farkit/tools/DSLog;", "", "()V", "MEMORY_TAG", "", "SPEED_PERF_DURATION_TAG", "SPEED_PERF_TIMESTAMP_TAG", "TAG", "currentScreen", "isDebugConfig", "", "lastApplicationName", "lastEPGGridBegin", "", "Ljava/lang/Long;", "lastLivePlaybackBegin", "lastLivePlayerHash", "lastStartupBegin", "lastUserAction", "lastVODPlaybackBegin", "lastVODPlayerHash", "a", "", "action", "adLogE", "message", "adLogM", "appStartTimeStamp", "applicationName", "endCountDown", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "e", "epgGridTimeStamp", "gridHash", "isLoaded", "f", "screenfinish", "livePlaybackTimeStamp", "playerHash", "imageAppeared", "m", "printCurrentRAMUsage", "activityManager", "Landroid/app/ActivityManager;", "r", ImagesContract.URL, "cacheHit", "s", "screenStarted", "vodPlaybackTimeStamp", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class DSLog {
    private static String currentScreen = null;
    private static final boolean isDebugConfig = true;
    private static String lastApplicationName;
    private static Long lastEPGGridBegin;
    private static Long lastLivePlaybackBegin;
    private static String lastLivePlayerHash;
    private static Long lastStartupBegin;
    private static String lastUserAction;
    private static Long lastVODPlaybackBegin;
    private static String lastVODPlayerHash;
    public static final DSLog INSTANCE = new DSLog();
    private static String TAG = "[DS]";
    private static String SPEED_PERF_TIMESTAMP_TAG = TAG + "-SPEED PERF";
    private static String SPEED_PERF_DURATION_TAG = TAG + "-SPEED PERF RECAP";
    private static String MEMORY_TAG = TAG + "-MEMORY";

    private DSLog() {
    }

    public final void a(String action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        lastUserAction = action;
        Log.d(TAG, "        [USER ACTION] : " + lastUserAction);
    }

    public final void s(String screenStarted) {
        Intrinsics.checkParameterIsNotNull(screenStarted, "screenStarted");
        currentScreen = screenStarted;
        Log.d(TAG, "    [SCREEN STARTED] : " + currentScreen);
    }

    public final void f(String screenfinish) {
        Intrinsics.checkParameterIsNotNull(screenfinish, "screenfinish");
        Log.d(TAG, "    [SCREEN FINISHED] : " + screenfinish);
        currentScreen = (String) null;
    }

    public final void r(String url, boolean cacheHit) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Log.d(TAG, "    [REQUEST] :     URL -> " + url + " -> cache " + cacheHit);
    }

    public final void m(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.d(TAG, "    [INFO] : " + message);
    }

    public final void e(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.e(TAG, "    [ERROR] : " + message);
    }

    public final void adLogM(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.d(TAG, "    [ADVERTISING] : " + message);
    }

    public final void adLogE(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.e(TAG, "    [ADVERTISING] : " + message);
    }

    public final void appStartTimeStamp(String applicationName, Boolean endCountDown) {
        if (applicationName == null || endCountDown == null) {
            return;
        }
        if (!endCountDown.booleanValue()) {
            Log.d(SPEED_PERF_TIMESTAMP_TAG, "          [APP-STARTUP] : ▶ {" + applicationName + "} STARTUP ");
            lastApplicationName = applicationName;
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
            lastStartupBegin = Long.valueOf(calendar.getTimeInMillis());
            return;
        }
        if (lastStartupBegin != null) {
            Log.d(SPEED_PERF_TIMESTAMP_TAG, "          [APP-STARTUP] : ■ {" + applicationName + "} STARTUP COMPLETE ");
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar2, "Calendar.getInstance()");
            long timeInMillis = calendar2.getTimeInMillis();
            Long l = lastStartupBegin;
            if (l == null) {
                Intrinsics.throwNpe();
            }
            long jLongValue = (timeInMillis - l.longValue()) / ((long) 1000);
            Log.d(SPEED_PERF_DURATION_TAG, "    [APP-STARTUP] : done in " + jLongValue + " seconds {" + applicationName + '}');
            lastApplicationName = (String) null;
            lastStartupBegin = (Long) null;
        }
    }

    public final void vodPlaybackTimeStamp(String playerHash, Boolean imageAppeared) {
        if (playerHash == null || imageAppeared == null) {
            return;
        }
        if (!imageAppeared.booleanValue()) {
            Log.d(SPEED_PERF_TIMESTAMP_TAG, "          [VOD-PLAYBACK] : ▶ {" + playerHash + "} LOADING");
            lastVODPlayerHash = playerHash;
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
            lastVODPlaybackBegin = Long.valueOf(calendar.getTimeInMillis());
            return;
        }
        if (lastVODPlaybackBegin != null) {
            Log.d(SPEED_PERF_TIMESTAMP_TAG, "          [VOD-PLAYBACK] : ■ {" + playerHash + "} LOADING COMPLETE");
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar2, "Calendar.getInstance()");
            long timeInMillis = calendar2.getTimeInMillis();
            Long l = lastVODPlaybackBegin;
            if (l == null) {
                Intrinsics.throwNpe();
            }
            long jLongValue = (timeInMillis - l.longValue()) / ((long) 1000);
            Log.d(SPEED_PERF_DURATION_TAG, "    [VOD-PLAYBACK] : done in " + jLongValue + " seconds {" + playerHash + '}');
            lastVODPlayerHash = (String) null;
            lastVODPlaybackBegin = (Long) null;
        }
    }

    public final void livePlaybackTimeStamp(String playerHash, Boolean imageAppeared) {
        if (playerHash == null || imageAppeared == null) {
            return;
        }
        if (!imageAppeared.booleanValue()) {
            Log.d(SPEED_PERF_TIMESTAMP_TAG, "          [LIVE-PLAYBACK] : ▶ {" + playerHash + "} LOADING");
            lastLivePlayerHash = playerHash;
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
            lastLivePlaybackBegin = Long.valueOf(calendar.getTimeInMillis());
            return;
        }
        if (lastLivePlaybackBegin != null) {
            Log.d(SPEED_PERF_TIMESTAMP_TAG, "          [LIVE-PLAYBACK] : ■ {" + playerHash + "} LOADING COMPLETE");
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar2, "Calendar.getInstance()");
            long timeInMillis = calendar2.getTimeInMillis();
            Long l = lastLivePlaybackBegin;
            if (l == null) {
                Intrinsics.throwNpe();
            }
            long jLongValue = (timeInMillis - l.longValue()) / ((long) 1000);
            Log.d(SPEED_PERF_DURATION_TAG, "    [LIVE-PLAYBACK] : done in " + jLongValue + " seconds {" + playerHash + '}');
            lastLivePlayerHash = (String) null;
            lastLivePlaybackBegin = (Long) null;
        }
    }

    public final void epgGridTimeStamp(String gridHash, boolean isLoaded) {
        Intrinsics.checkParameterIsNotNull(gridHash, "gridHash");
        if (!isLoaded) {
            Log.d(SPEED_PERF_TIMESTAMP_TAG, "          [EPG-GRID] : ▶ {" + gridHash + "} LOADING");
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
            lastEPGGridBegin = Long.valueOf(calendar.getTimeInMillis());
            return;
        }
        if (lastEPGGridBegin != null) {
            Log.d(SPEED_PERF_TIMESTAMP_TAG, "          [EPG-GRID] : ■ {" + gridHash + "} LOADING COMPLETE");
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar2, "Calendar.getInstance()");
            long timeInMillis = calendar2.getTimeInMillis();
            Long l = lastEPGGridBegin;
            if (l == null) {
                Intrinsics.throwNpe();
            }
            long jLongValue = (timeInMillis - l.longValue()) / ((long) 1000);
            Log.d(SPEED_PERF_DURATION_TAG, "    [EPG-GRID] : done in " + jLongValue + " seconds {" + gridHash + '}');
            lastEPGGridBegin = (Long) null;
        }
    }

    public final String printCurrentRAMUsage(ActivityManager activityManager) {
        if (activityManager == null) {
            return "RAM usage unknown (ActivityManager = null)";
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        double d = 1073741824;
        double d2 = memoryInfo.totalMem / d;
        double d3 = (memoryInfo.totalMem - memoryInfo.availMem) / d;
        String str = MEMORY_TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("             -[RAM-USAGE] : ");
        sb.append(StringsKt.take(String.valueOf(d3), 4));
        sb.append('/');
        sb.append(StringsKt.take(String.valueOf(d2), 4));
        sb.append(" GB used (=");
        int i = (int) ((d3 / d2) * ((double) 100));
        sb.append(i);
        sb.append("%)");
        Log.d(str, sb.toString());
        return StringsKt.take(String.valueOf(d3), 4) + '/' + StringsKt.take(String.valueOf(d2), 4) + " GB used (=" + i + "%)";
    }
}
