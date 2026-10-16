package com.viaccessorca.voplayer;

import android.content.Context;
import android.os.Build;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class VODeviceAdaptation {
    public static final int BENCHMARK_ERROR = -2;
    public static final int DATABASE_ERROR = -1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final double[] f65a = {1.0d, 1.0d, 1.5d, 1.8d, 2.0d, 2.3d, 2.6d, 2.8d, 3.0d};
    private static final double[] b = {11.0d, 14.0d, 22.0d, 26.0d, 32.0d, 48.0d, 60.0d};

    protected VODeviceAdaptation() {
    }

    protected static int a() {
        int iE = VOPlatformAnalyzer.e();
        int i = -1;
        if (-1 == iE) {
            iE = 800000;
        }
        int iD = VOPlatformAnalyzer.d();
        if (-1 == iD) {
            iD = 1;
        }
        boolean zI = VOPlatformAnalyzer.i();
        double d = ((((double) iE) / 1000.0d) / 100.0d) * (iD <= 8 ? f65a[iD] : f65a[8]);
        if (zI) {
            d *= 1.2d;
        }
        double dB = (d * 1.0d) + b();
        for (int length = b.length - 1; length >= 0; length--) {
            if (dB > b[length]) {
                i = length + 1;
                break;
            }
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    private static int a(int i, c.C0187c c0187c, boolean z) {
        int iB;
        if (c0187c == null) {
            return -1;
        }
        String strC = c0187c.c();
        int iA = a(true == z ? c0187c.f() : c0187c.g(), strC);
        if (iA <= 0) {
            iA = a(c0187c.e(), strC);
        }
        if (iA > 0) {
            return iA;
        }
        if (true == z && (iB = c0187c.b()) > 0) {
            iA = iB;
        }
        if (iA > 0) {
            return iA;
        }
        if (!a(c0187c)) {
            return -1;
        }
        if (i < 0) {
            return -2;
        }
        Map<Integer, Integer> mapD = c0187c.d();
        if (i >= mapD.size()) {
            i = mapD.size() - 1;
        }
        return mapD.get(Integer.valueOf(i)).intValue();
    }

    private static int a(Map<String, Integer> map, String str) {
        StringBuilder sb;
        String str2;
        String string;
        if (map.containsKey(Build.DEVICE)) {
            string = Build.DEVICE;
        } else {
            if (map.containsKey(Build.DEVICE + str + Build.VERSION.SDK_INT)) {
                sb = new StringBuilder();
                str2 = Build.DEVICE;
            } else if (map.containsKey(Build.MODEL)) {
                string = Build.MODEL;
            } else {
                if (!map.containsKey(Build.MODEL + str + Build.VERSION.SDK_INT)) {
                    return -1;
                }
                sb = new StringBuilder();
                str2 = Build.MODEL;
            }
            sb.append(str2);
            sb.append(str);
            sb.append(Build.VERSION.SDK_INT);
            string = sb.toString();
        }
        return map.get(string).intValue();
    }

    private static boolean a(c.C0187c c0187c) {
        Map<Integer, Integer> mapD = c0187c.d();
        boolean z = true;
        for (int i = 0; i < mapD.size(); i++) {
            if (!mapD.containsKey(Integer.valueOf(i))) {
                z = false;
            }
        }
        return z;
    }

    private static double b() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 14) {
            return 3.0d;
        }
        switch (i) {
            case 8:
                return 1.0d;
            case 9:
            case 10:
                return 2.0d;
            case 11:
            case 12:
                return 2.1d;
            case 13:
                return 2.5d;
            default:
                return 0.0d;
        }
    }

    public static int getRecommendedMaxBitrate(Context context, boolean z) {
        c.C0187c c0187cA = new c().a(context);
        if (c0187cA == null) {
            return -1;
        }
        int iA = a(a(), c0187cA, z);
        return iA <= 0 ? c0187cA.a() : iA;
    }
}
