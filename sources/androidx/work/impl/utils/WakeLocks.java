package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import androidx.work.Logger;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class WakeLocks {
    private static final String TAG = Logger.tagWithPrefix("WakeLocks");
    private static final WeakHashMap<PowerManager.WakeLock, String> sWakeLocks = new WeakHashMap<>();

    public static PowerManager.WakeLock newWakeLock(Context context, String tag) {
        String str = "WorkManager: " + tag;
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getApplicationContext().getSystemService("power")).newWakeLock(1, str);
        synchronized (sWakeLocks) {
            sWakeLocks.put(wakeLockNewWakeLock, str);
        }
        return wakeLockNewWakeLock;
    }

    public static void checkWakeLocks() {
        HashMap map = new HashMap();
        synchronized (sWakeLocks) {
            map.putAll(sWakeLocks);
        }
        for (PowerManager.WakeLock wakeLock : map.keySet()) {
            if (wakeLock != null && wakeLock.isHeld()) {
                Logger.get().warning(TAG, String.format("WakeLock held for %s", map.get(wakeLock)), new Throwable[0]);
            }
        }
    }

    private WakeLocks() {
    }
}
