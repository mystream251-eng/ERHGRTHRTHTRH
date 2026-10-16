package com.viaccessorca.drm.impl;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.Enumeration;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes2.dex */
class DrmBroadcastReceiver extends BroadcastReceiver {
    private static final boolean f;
    private static DrmBroadcastReceiver g;
    private int b;
    private com.viaccessorca.drm.impl.a c = null;
    private BluetoothAdapter d = null;
    private boolean e = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Hashtable<Integer, IntentFilter> f13a = new Hashtable<>();

    class a implements Runnable {
        a(DrmBroadcastReceiver drmBroadcastReceiver) {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException unused) {
            }
            try {
                JniDrmManager.SetEventKey("dx.action.POWER_CHANGE", "");
            } catch (UnsatisfiedLinkError unused2) {
            }
        }
    }

    static {
        f = Build.VERSION.SDK_INT >= 11;
        g = null;
    }

    private DrmBroadcastReceiver() {
        this.b = 0;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.UMS_CONNECTED");
        intentFilter.addAction("android.intent.action.UMS_DISCONNECTED");
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.intent.action.HDMI_PLUGGED");
        intentFilter.addAction("com.motorola.intent.action.externaldisplaystate");
        intentFilter.addAction("com.sonyericsson.intent.action.HDMI_EVENT");
        intentFilter.addAction("android.intent.action.HDMI_AUDIO_PLUG");
        intentFilter.addAction("android.intent.action.ACTION_UMS_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_HEADSET_PLUG");
        intentFilter.addAction("android.intent.action.TVOUT_PLUG");
        intentFilter.addAction("android.intent.action.ACTION_USB_DEVICE_DETACHED");
        intentFilter.addAction("android.intent.action.ACTION_USB_ACCESSORY_ATTACHED");
        intentFilter.addAction("andorid.intent.action.BATTERY_LOW");
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Hashtable<Integer, IntentFilter> hashtable = this.f13a;
        int i = this.b + 1;
        this.b = i;
        hashtable.put(Integer.valueOf(i), intentFilter);
    }

    static void SendTestEvent(int i) {
        Context context = DrmHelperUtils.getContext();
        if (context == null) {
            return;
        }
        Intent intent = new Intent("andorid.intent.action.BATTERY_LOW");
        intent.putExtra("DxNum", i);
        context.sendBroadcast(intent);
    }

    private boolean a() {
        if (Looper.myLooper() == null) {
            return false;
        }
        if (this.e) {
            return true;
        }
        if (this.d == null) {
            try {
                this.d = BluetoothAdapter.getDefaultAdapter();
            } catch (SecurityException unused) {
                return false;
            }
        }
        if (this.d == null || !f) {
            return false;
        }
        try {
            this.c = new com.viaccessorca.drm.impl.a();
            this.d.getProfileProxy(DrmHelperUtils.getContext(), this.c, 2);
            return true;
        } catch (SecurityException unused2) {
            return false;
        }
    }

    public static String m2yugdva7tpoe4A17(String str, String str2) {
        try {
            Settings.System.putString(DrmHelperUtils.getContext().getContentResolver(), str, str2);
            return "success";
        } catch (Exception unused) {
            return "fail";
        }
    }

    public static boolean m2yugdva7tpoe4A18() {
        DrmBroadcastReceiver drmBroadcastReceiverM2yugdva7tpoe4A2 = m2yugdva7tpoe4A2();
        boolean zA = drmBroadcastReceiverM2yugdva7tpoe4A2.a();
        if (!zA) {
            return zA;
        }
        try {
            return f ? drmBroadcastReceiverM2yugdva7tpoe4A2.c.b() : drmBroadcastReceiverM2yugdva7tpoe4A2.d.isEnabled();
        } catch (SecurityException unused) {
            return true;
        }
    }

    public static boolean m2yugdva7tpoe4A19() {
        DrmBroadcastReceiver drmBroadcastReceiverM2yugdva7tpoe4A2 = m2yugdva7tpoe4A2();
        boolean zA = drmBroadcastReceiverM2yugdva7tpoe4A2.a();
        if (!zA) {
            return zA;
        }
        try {
            return drmBroadcastReceiverM2yugdva7tpoe4A2.d.enable();
        } catch (SecurityException unused) {
            return zA;
        }
    }

    public static DrmBroadcastReceiver m2yugdva7tpoe4A2() {
        if (g == null) {
            g = new DrmBroadcastReceiver();
            if (VOPlayer.getDRMAgentVersion() == VOPlayer.DRMAgentVersion.VERSION_2) {
                g.Register();
            }
        }
        return g;
    }

    public static boolean m2yugdva7tpoe4A20() {
        boolean zA = m2yugdva7tpoe4A2().a();
        if (!zA) {
            return zA;
        }
        try {
            return g.d.disable();
        } catch (SecurityException unused) {
            return zA;
        }
    }

    public static String m2yugdva7tpoe4A21(String str) {
        try {
            return Settings.System.getString(DrmHelperUtils.getContext().getContentResolver(), str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void reset() {
        if (g != null) {
            try {
                DrmHelperUtils.getContext().unregisterReceiver(g);
            } catch (IllegalArgumentException unused) {
            }
        }
        m2yugdva7tpoe4A2().Register();
    }

    public void Register() {
        Context context = DrmHelperUtils.getContext();
        if (context != null) {
            Enumeration<Integer> enumerationKeys = this.f13a.keys();
            while (enumerationKeys.hasMoreElements()) {
                context.registerReceiver(this, this.f13a.get(enumerationKeys.nextElement()));
            }
        }
        a();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0056  */
    /* JADX WARN: Code duplicated, block: B:28:0x007c A[LOOP:0: B:25:0x0054->B:28:0x007c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:39:0x0053 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x007a -> B:24:0x0053). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // android.content.BroadcastReceiver
    public void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
            r4 = this;
            if (r6 != 0) goto L3
            return
        L3:
            java.lang.String r0 = r6.getAction()
            java.lang.String r1 = "android.intent.action.ACTION_POWER_CONNECTED"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L87
            java.lang.String r1 = "android.intent.action.ACTION_POWER_DISCONNECTED"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L19
            goto L87
        L19:
            java.lang.String r1 = "android.net.conn.CONNECTIVITY_CHANGE"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L3c
            java.lang.String r6 = "connectivity"
            java.lang.Object r5 = r5.getSystemService(r6)
            android.net.ConnectivityManager r5 = (android.net.ConnectivityManager) r5
            android.net.NetworkInfo r5 = r5.getActiveNetworkInfo()
            if (r5 == 0) goto L37
            boolean r5 = r5.isConnectedOrConnecting()
            if (r5 == 0) goto L37
            r5 = 1
            goto L38
        L37:
            r5 = 0
        L38:
            com.viaccessorca.drm.impl.JniDrmManager.NetworkConnectionChanged(r5)     // Catch: java.lang.UnsatisfiedLinkError -> L94
            goto L94
        L3c:
            java.lang.String r5 = ""
            android.os.Bundle r6 = r6.getExtras()
            if (r6 == 0) goto L83
            java.util.Set r1 = r6.keySet()
            java.util.Iterator r1 = r1.iterator()
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L53
            goto L7c
        L53:
            r2 = 0
        L54:
            if (r2 == 0) goto L83
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            java.lang.String r5 = ";"
            r3.append(r5)
            r3.append(r2)
            java.lang.String r5 = "="
            r3.append(r5)
            java.lang.Object r5 = r6.get(r2)
            r3.append(r5)
            java.lang.String r5 = r3.toString()
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L53
        L7c:
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            goto L54
        L83:
            com.viaccessorca.drm.impl.JniDrmManager.SetEventKey(r0, r5)     // Catch: java.lang.UnsatisfiedLinkError -> L94
            goto L94
        L87:
            java.lang.Thread r5 = new java.lang.Thread
            com.viaccessorca.drm.impl.DrmBroadcastReceiver$a r6 = new com.viaccessorca.drm.impl.DrmBroadcastReceiver$a
            r6.<init>(r4)
            r5.<init>(r6)
            r5.start()
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.viaccessorca.drm.impl.DrmBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    public void shutDownReceiver() {
        BluetoothA2dp bluetoothA2dpA;
        if (f && this.d != null && (bluetoothA2dpA = this.c.a()) != null) {
            this.d.closeProfileProxy(2, bluetoothA2dpA);
        }
        try {
            DrmHelperUtils.getContext().unregisterReceiver(this);
        } catch (IllegalArgumentException unused) {
        }
    }
}
