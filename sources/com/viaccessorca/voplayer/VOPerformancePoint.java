package com.viaccessorca.voplayer;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

/* JADX INFO: loaded from: classes2.dex */
public class VOPerformancePoint {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Class f69a;
    private static Constructor b;
    private static Method c;
    private int m329t7p6390828478428;
    private int m329t7p6390828478429;
    private int m329t7p6390828478430;

    static class a implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ MediaCodecInfo.VideoCapabilities f70a;

        a(MediaCodecInfo.VideoCapabilities videoCapabilities) {
            this.f70a = videoCapabilities;
        }

        @Override // com.viaccessorca.voplayer.VOPerformancePoint.b
        public double For(int i, int i2) {
            int i3;
            int i4 = ((i + 15) / 16) * 16;
            int i5 = ((i2 + 15) / 16) * 16;
            int i6 = 0;
            int i7 = 24;
            while (true) {
                int i8 = i7;
                i3 = i6;
                i6 = i8;
                if (!this.f70a.areSizeAndRateSupported(i4, i5, i6)) {
                    break;
                }
                i7 = i6 * 2;
            }
            while (i6 - i3 > 1) {
                int i9 = (i3 + i6) / 2;
                if (this.f70a.areSizeAndRateSupported(i4, i5, i9)) {
                    i3 = i9;
                } else {
                    i6 = i9;
                }
            }
            return i3;
        }
    }

    private interface b {
        double For(int i, int i2);
    }

    public VOPerformancePoint(int i, int i2, int i3) {
        this.m329t7p6390828478428 = i;
        this.m329t7p6390828478429 = i2;
        this.m329t7p6390828478430 = i3;
    }

    private static Object a(int i, int i2, int i3) {
        try {
            return b.newInstance(Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        } catch (Exception unused) {
            return null;
        }
    }

    static List<VOPerformancePoint> a(MediaCodecInfo.VideoCapabilities videoCapabilities) {
        int[][] iArr = {new int[]{720, 480}, new int[]{720, 576}, new int[]{1280, 720}, new int[]{1920, 1080}, new int[]{3840, 2160}, new int[]{4096, 2160}, new int[]{7680, 4320}};
        if (videoCapabilities == null) {
            throw new IllegalArgumentException();
        }
        List<VOPerformancePoint> listA = 29 <= Build.VERSION.SDK_INT ? a(videoCapabilities, iArr) : null;
        return (listA != null || 21 > Build.VERSION.SDK_INT) ? listA : b(videoCapabilities, iArr);
    }

    private static List<VOPerformancePoint> a(MediaCodecInfo.VideoCapabilities videoCapabilities, int[][] iArr) {
        Object[] objArr;
        VOPerformancePoint vOPerformancePoint = null;
        if (!a()) {
            return null;
        }
        try {
            Object objInvoke = MediaCodecInfo.VideoCapabilities.class.getMethod("getSupportedPerformancePoints", new Class[0]).invoke(videoCapabilities, new Object[0]);
            objArr = (Object[]) objInvoke.getClass().getMethod("toArray", new Class[0]).invoke(objInvoke, new Object[0]);
        } catch (Exception unused) {
            objArr = null;
        }
        if (objArr == null) {
            return null;
        }
        Vector vector = new Vector();
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int[] iArr2 = iArr[i];
            int i2 = iArr2[0];
            int i3 = iArr2[1];
            int i4 = 24;
            int i5 = 0;
            while (a(objArr, a(i2, i3, i4))) {
                i5 = i4;
                i4 *= 2;
            }
            while (i4 - i5 > 1) {
                int i6 = (i5 + i4) / 2;
                if (a(objArr, a(i2, i3, i6))) {
                    i5 = i6;
                } else {
                    i4 = i6;
                }
            }
            if (i5 == 0) {
                break;
            }
            VOPerformancePoint vOPerformancePoint2 = new VOPerformancePoint(i2, i3, i5);
            if (vOPerformancePoint != null && vOPerformancePoint2.getFrameRate() < vOPerformancePoint.getFrameRate()) {
                vector.add(vOPerformancePoint);
            }
            i++;
            vOPerformancePoint = vOPerformancePoint2;
        }
        if (vOPerformancePoint != null) {
            vector.add(vOPerformancePoint);
        }
        return vector;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0040  */
    /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
    static List<VOPerformancePoint> a(String str, String str2) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (21 > Build.VERSION.SDK_INT) {
            Vector vector = new Vector();
            vector.add(new VOPerformancePoint(1920, 1080, 30));
            return vector;
        }
        for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(1).getCodecInfos()) {
            if (str.equalsIgnoreCase(mediaCodecInfo.getName())) {
                try {
                    videoCapabilities = mediaCodecInfo.getCapabilitiesForType(str2).getVideoCapabilities();
                } catch (IllegalArgumentException unused) {
                    videoCapabilities = null;
                }
                if (videoCapabilities != null) {
                    return a(videoCapabilities);
                }
                return null;
            }
        }
        videoCapabilities = null;
        if (videoCapabilities != null) {
            return a(videoCapabilities);
        }
        return null;
    }

    private static List<VOPerformancePoint> a(int[][] iArr, b bVar) {
        Vector vector = new Vector();
        VOPerformancePoint vOPerformancePoint = null;
        for (int[] iArr2 : iArr) {
            int i = iArr2[0];
            int i2 = iArr2[1];
            int iFor = (int) bVar.For(i, i2);
            if (iFor != 0) {
                if (vOPerformancePoint != null && iFor < vOPerformancePoint.getFrameRate()) {
                    vector.add(vOPerformancePoint);
                }
                vOPerformancePoint = new VOPerformancePoint(i, i2, iFor);
            }
        }
        if (vOPerformancePoint != null) {
            vector.add(vOPerformancePoint);
        }
        if (vector.isEmpty()) {
            return null;
        }
        return vector;
    }

    private static boolean a() {
        try {
            if (f69a == null) {
                f69a = Class.forName("android.media.MediaCodecInfo$VideoCapabilities$PerformancePoint");
            }
            if (b == null) {
                b = f69a.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE);
            }
            if (c == null) {
                c = f69a.getMethod("covers", f69a);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean a(Object[] objArr, Object obj) {
        try {
            for (Object obj2 : objArr) {
                if (Boolean.TRUE.equals(c.invoke(obj2, obj))) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static List<VOPerformancePoint> b(MediaCodecInfo.VideoCapabilities videoCapabilities, int[][] iArr) {
        return a(iArr, (b) new a(videoCapabilities));
    }

    public boolean covers(int i, int i2, int i3) {
        return i <= this.m329t7p6390828478428 && i2 <= this.m329t7p6390828478429 && i3 <= this.m329t7p6390828478430;
    }

    public boolean covers(VOPerformancePoint vOPerformancePoint) {
        return covers(vOPerformancePoint.getWidth(), vOPerformancePoint.getHeight(), vOPerformancePoint.getFrameRate());
    }

    public boolean equals(Object obj) {
        if (obj == null || !VOPerformancePoint.class.equals(obj.getClass())) {
            return false;
        }
        VOPerformancePoint vOPerformancePoint = (VOPerformancePoint) obj;
        return this.m329t7p6390828478428 == vOPerformancePoint.m329t7p6390828478428 && this.m329t7p6390828478429 == vOPerformancePoint.m329t7p6390828478429 && this.m329t7p6390828478430 == vOPerformancePoint.m329t7p6390828478430;
    }

    public int getFrameRate() {
        return this.m329t7p6390828478430;
    }

    public int getHeight() {
        return this.m329t7p6390828478429;
    }

    public int getWidth() {
        return this.m329t7p6390828478428;
    }

    public String toString() {
        return "(" + this.m329t7p6390828478428 + "x" + this.m329t7p6390828478429 + "@" + this.m329t7p6390828478430 + ")";
    }
}
