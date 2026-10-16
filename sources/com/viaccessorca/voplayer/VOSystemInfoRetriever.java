package com.viaccessorca.voplayer;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import androidx.core.os.EnvironmentCompat;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class VOSystemInfoRetriever {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static VOSystemInfo f108a = null;
    private static boolean b = false;

    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    }

    private static int a() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new a()).length;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static MediaCodecInfo a(String str) {
        return a(str, false);
    }

    private static MediaCodecInfo a(String str, boolean z) {
        int codecCount;
        MediaCodecList mediaCodecList;
        int i = Build.VERSION.SDK_INT;
        if (i >= 16) {
            if (i >= 21) {
                mediaCodecList = new MediaCodecList(1);
                codecCount = mediaCodecList.getCodecInfos().length;
            } else {
                codecCount = MediaCodecList.getCodecCount();
                mediaCodecList = null;
            }
            for (int i2 = 0; i2 < codecCount; i2++) {
                MediaCodecInfo codecInfoAt = Build.VERSION.SDK_INT >= 21 ? mediaCodecList.getCodecInfos()[i2] : MediaCodecList.getCodecInfoAt(i2);
                if (!codecInfoAt.isEncoder()) {
                    for (String str2 : codecInfoAt.getSupportedTypes()) {
                        if (str2.equals(str)) {
                            if (z) {
                                if (Build.VERSION.SDK_INT >= 21) {
                                    if (!codecInfoAt.getCapabilitiesForType(str).isFeatureSupported("secure-playback")) {
                                    }
                                } else if (!codecInfoAt.getName().endsWith("secure")) {
                                }
                            }
                            return codecInfoAt;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static void a(Context context, VOSystemInfo vOSystemInfo) {
        VOPlayer.getDRMAgentVersion();
        b(context, vOSystemInfo);
        c(context, vOSystemInfo);
        f(context, vOSystemInfo);
        d(vOSystemInfo);
        e(context, vOSystemInfo);
        c(vOSystemInfo);
        a(vOSystemInfo);
        d(context, vOSystemInfo);
    }

    private static void a(VOSystemInfo vOSystemInfo) {
        vOSystemInfo.cpuScore = VODeviceAdaptation.a();
    }

    private static boolean a(Context context) {
        PackageManager packageManager;
        if (Build.MODEL.startsWith("SS6B3AB_ME0") || Build.MODEL.startsWith("BRAVIA")) {
            return true;
        }
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return false;
        }
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (uiModeManager == null || uiModeManager.getCurrentModeType() != 4) {
            return (packageManager.hasSystemFeature("android.hardware.touchscreen") || packageManager.hasSystemFeature("android.hardware.telephony") || packageManager.hasSystemFeature("android.hardware.camera")) ? false : true;
        }
        return true;
    }

    protected static synchronized boolean a(boolean z) {
        if (f108a != null && (z || !b)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                return false;
            }
            f108a.cpuBenchmarkScore = VOPlatformAnalyzer.a();
            b = true;
        }
        return true;
    }

    private static VOPerformancePoint[] a(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        if (21 > Build.VERSION.SDK_INT) {
            return new VOPerformancePoint[]{new VOPerformancePoint(1920, 1080, 30)};
        }
        List<VOPerformancePoint> listA = VOPerformancePoint.a(codecCapabilities.getVideoCapabilities());
        return (VOPerformancePoint[]) listA.toArray(new VOPerformancePoint[listA.size()]);
    }

    private static String b(String str) {
        try {
            try {
                try {
                    return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, str);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                    return null;
                }
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            }
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
        }
    }

    private static void b(Context context, VOSystemInfo vOSystemInfo) {
        vOSystemInfo.androidVersion = Build.VERSION.SDK_INT;
        vOSystemInfo.deviceModel = Build.MODEL;
        vOSystemInfo.deviceManufacturer = Build.MANUFACTURER;
        vOSystemInfo.deviceProduct = Build.PRODUCT;
        vOSystemInfo.deviceBrand = Build.BRAND;
        vOSystemInfo.deviceName = Build.DEVICE;
        vOSystemInfo.deviceBoard = Build.BOARD;
        vOSystemInfo.hardware = Build.HARDWARE;
        vOSystemInfo.isEmulator = b();
        vOSystemInfo.isTV = a(context);
        b(vOSystemInfo);
    }

    private static void b(VOSystemInfo vOSystemInfo) {
        String strB = b("ro.product.cpu.abi");
        vOSystemInfo.osArch = strB;
        if (strB == null) {
            vOSystemInfo.osArch = (Build.CPU_ABI2.equalsIgnoreCase(EnvironmentCompat.MEDIA_UNKNOWN) || Build.CPU_ABI2.contains("arm")) ? Build.CPU_ABI.contains("arm") ^ true : true ? new String("x86") : Build.CPU_ABI;
        }
    }

    private static boolean b() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }

    /* JADX WARN: Code duplicated, block: B:82:0x012b A[Catch: Exception -> 0x03ad, TryCatch #0 {Exception -> 0x03ad, blocks: (B:3:0x0003, B:7:0x000e, B:9:0x0015, B:14:0x002d, B:16:0x0031, B:18:0x003c, B:163:0x0251, B:21:0x0048, B:23:0x0050, B:48:0x00ba, B:50:0x00be, B:52:0x00c6, B:54:0x00ce, B:56:0x00d3, B:57:0x00d9, B:59:0x00df, B:61:0x00e9, B:65:0x00f1, B:68:0x00fb, B:71:0x0105, B:74:0x010f, B:77:0x0119, B:80:0x0123, B:83:0x012e, B:82:0x012b, B:84:0x0134, B:86:0x0138, B:88:0x013e, B:90:0x0144, B:92:0x014a, B:94:0x0150, B:96:0x0156, B:98:0x015e, B:100:0x0163, B:101:0x0169, B:114:0x0195, B:102:0x016e, B:104:0x0174, B:106:0x017a, B:108:0x0180, B:110:0x0188, B:112:0x018d, B:113:0x0193, B:162:0x0243, B:115:0x019d, B:117:0x01a7, B:119:0x01ab, B:121:0x01b3, B:123:0x01bb, B:125:0x01c0, B:126:0x01c6, B:128:0x01cc, B:130:0x01d6, B:129:0x01d3, B:131:0x01dc, B:133:0x01e0, B:135:0x01e6, B:137:0x01ec, B:139:0x01f2, B:141:0x01f8, B:143:0x01fe, B:145:0x0206, B:147:0x020b, B:148:0x0211, B:161:0x023d, B:149:0x0216, B:151:0x021c, B:153:0x0222, B:155:0x0228, B:157:0x0230, B:159:0x0235, B:160:0x023b, B:17:0x0038, B:164:0x025d, B:167:0x0263, B:169:0x026b, B:171:0x0271, B:174:0x0278, B:176:0x028c, B:175:0x028a, B:177:0x0292, B:179:0x0296, B:181:0x029a, B:183:0x02a2, B:185:0x02a8, B:188:0x02af, B:190:0x02c3, B:189:0x02c1, B:191:0x02c9, B:195:0x02d0, B:197:0x02d6, B:198:0x02dc, B:200:0x02e0, B:202:0x02e7, B:203:0x02ed, B:205:0x02f1, B:207:0x02f5, B:208:0x0308, B:212:0x030f, B:214:0x0315, B:215:0x031b, B:217:0x031f, B:219:0x0326, B:220:0x032c, B:222:0x0330, B:224:0x0334, B:225:0x0347, B:229:0x034e, B:231:0x0354, B:232:0x035a, B:234:0x035e, B:236:0x0365, B:237:0x036b, B:239:0x036f, B:241:0x0373, B:10:0x0020, B:242:0x0386, B:245:0x038c), top: B:250:0x0003 }] */
    private static void c(Context context, VOSystemInfo vOSystemInfo) {
        int codecCount;
        MediaCodecInfo mediaCodecInfoA;
        MediaCodecInfo mediaCodecInfoA2;
        MediaCodecInfo mediaCodecInfoA3;
        String str;
        String str2;
        MediaCodecInfo mediaCodecInfo;
        int i = 0;
        try {
            vOSystemInfo.codecColorFormat = 0;
            if (Build.VERSION.SDK_INT >= 16) {
                MediaCodecList mediaCodecList = null;
                int i2 = 21;
                if (Build.VERSION.SDK_INT >= 21) {
                    mediaCodecList = new MediaCodecList(1);
                    codecCount = mediaCodecList.getCodecInfos().length;
                } else {
                    codecCount = MediaCodecList.getCodecCount();
                }
                int i3 = 0;
                while (i3 < codecCount) {
                    MediaCodecInfo codecInfoAt = Build.VERSION.SDK_INT >= i2 ? mediaCodecList.getCodecInfos()[i3] : MediaCodecList.getCodecInfoAt(i3);
                    if (!codecInfoAt.isEncoder()) {
                        String[] supportedTypes = codecInfoAt.getSupportedTypes();
                        int length = supportedTypes.length;
                        int i4 = i;
                        while (i4 < length) {
                            String str3 = supportedTypes[i4];
                            String name = codecInfoAt.getName();
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str3);
                            MediaCodecList mediaCodecList2 = mediaCodecList;
                            int i5 = codecCount;
                            if (str3.equalsIgnoreCase(MimeTypes.VIDEO_H264)) {
                                try {
                                    if (vOSystemInfo.codecColorFormat == 0) {
                                        vOSystemInfo.codecColorFormat = capabilitiesForType.colorFormats[0];
                                        int i6 = 0;
                                        while (true) {
                                            if (i6 < capabilitiesForType.colorFormats.length) {
                                                mediaCodecInfo = codecInfoAt;
                                                if (2135033992 != capabilitiesForType.colorFormats[i6]) {
                                                    try {
                                                        if ((2141391875 != capabilitiesForType.colorFormats[i6] || 19 > Build.VERSION.SDK_INT) && (2141391876 != capabilitiesForType.colorFormats[i6] || 19 > Build.VERSION.SDK_INT)) {
                                                            vOSystemInfo.codecColorFormat = capabilitiesForType.colorFormats[i6];
                                                        }
                                                    } catch (Exception unused) {
                                                    }
                                                }
                                                vOSystemInfo.codecColorFormat = capabilitiesForType.colorFormats[i6];
                                                i6++;
                                                codecInfoAt = mediaCodecInfo;
                                            } else {
                                                mediaCodecInfo = codecInfoAt;
                                            }
                                        }
                                    } else {
                                        mediaCodecInfo = codecInfoAt;
                                    }
                                } catch (Exception unused2) {
                                }
                                if (vOSystemInfo.codecName == null) {
                                    vOSystemInfo.codecName = name;
                                    if (Build.VERSION.SDK_INT >= 23 && (capabilitiesForType.getMaxSupportedInstances() < vOSystemInfo.maxVideoHwCodecSupportedInstances || -1 == vOSystemInfo.maxVideoHwCodecSupportedInstances)) {
                                        vOSystemInfo.maxVideoHwCodecSupportedInstances = capabilitiesForType.getMaxSupportedInstances();
                                    }
                                    if (19 <= Build.VERSION.SDK_INT) {
                                        vOSystemInfo.codecAdaptivePlaybackSupported = capabilitiesForType.isFeatureSupported("adaptive-playback");
                                        if (vOSystemInfo.codecName != null && Build.VERSION.SDK_INT < 24 && (("OMX.Nvidia.h264.decode".equals(vOSystemInfo.codecName) || "OMX.Nvidia.h264.decode.secure".equals(vOSystemInfo.codecName)) && ("flounder".equals(Build.DEVICE) || "flounder_lte".equals(Build.DEVICE) || "grouper".equals(Build.DEVICE) || "tilapia".equals(Build.DEVICE)))) {
                                            vOSystemInfo.codecAdaptivePlaybackSupported = false;
                                        }
                                    } else {
                                        vOSystemInfo.codecAdaptivePlaybackSupported = false;
                                    }
                                    vOSystemInfo.performancePointsForH264 = a(capabilitiesForType);
                                }
                                if (vOSystemInfo.secureCodecName == null) {
                                    String strB = b("ro.product.cpu.abi");
                                    if (strB != null && (strB.contentEquals("i686") || strB.contentEquals("x86_64") || strB.contentEquals("x86"))) {
                                        if (Build.VERSION.SDK_INT >= 23 && (capabilitiesForType.getMaxSupportedInstances() < vOSystemInfo.maxVideoHwCodecSupportedInstances || -1 == vOSystemInfo.maxVideoHwCodecSupportedInstances)) {
                                            vOSystemInfo.maxVideoHwCodecSupportedInstances = capabilitiesForType.getMaxSupportedInstances();
                                        }
                                        vOSystemInfo.secureCodecName = vOSystemInfo.codecName;
                                    } else if (19 <= Build.VERSION.SDK_INT && capabilitiesForType.isFeatureSupported("secure-playback")) {
                                        if (Build.VERSION.SDK_INT >= 23 && (capabilitiesForType.getMaxSupportedInstances() < vOSystemInfo.maxVideoHwCodecSupportedInstances || -1 == vOSystemInfo.maxVideoHwCodecSupportedInstances)) {
                                            vOSystemInfo.maxVideoHwCodecSupportedInstances = capabilitiesForType.getMaxSupportedInstances();
                                        }
                                        vOSystemInfo.secureCodecName = name;
                                    }
                                    vOSystemInfo.performancePointsForSecureH264 = a(capabilitiesForType);
                                }
                            } else {
                                mediaCodecInfo = codecInfoAt;
                                if (str3.equalsIgnoreCase(MimeTypes.VIDEO_H265)) {
                                    if (vOSystemInfo.codecNameHevc == null) {
                                        vOSystemInfo.codecNameHevc = name;
                                        if (Build.VERSION.SDK_INT >= 23 && (capabilitiesForType.getMaxSupportedInstances() < vOSystemInfo.maxVideoHwCodecSupportedInstances || -1 == vOSystemInfo.maxVideoHwCodecSupportedInstances)) {
                                            vOSystemInfo.maxVideoHwCodecSupportedInstances = capabilitiesForType.getMaxSupportedInstances();
                                        }
                                        if (19 <= Build.VERSION.SDK_INT) {
                                            vOSystemInfo.codecAdaptivePlaybackSupported = capabilitiesForType.isFeatureSupported("adaptive-playback");
                                        } else {
                                            vOSystemInfo.codecAdaptivePlaybackSupported = false;
                                        }
                                        vOSystemInfo.performancePointsForHEVC = a(capabilitiesForType);
                                    }
                                    if (vOSystemInfo.secureCodecNameHevc == null) {
                                        String strB2 = b("ro.product.cpu.abi");
                                        if (strB2 != null && (strB2.contentEquals("i686") || strB2.contentEquals("x86_64") || strB2.contentEquals("x86"))) {
                                            if (Build.VERSION.SDK_INT >= 23 && (capabilitiesForType.getMaxSupportedInstances() < vOSystemInfo.maxVideoHwCodecSupportedInstances || -1 == vOSystemInfo.maxVideoHwCodecSupportedInstances)) {
                                                vOSystemInfo.maxVideoHwCodecSupportedInstances = capabilitiesForType.getMaxSupportedInstances();
                                            }
                                            vOSystemInfo.secureCodecNameHevc = vOSystemInfo.codecName;
                                        } else if (19 <= Build.VERSION.SDK_INT && capabilitiesForType.isFeatureSupported("secure-playback")) {
                                            if (Build.VERSION.SDK_INT >= 23 && (capabilitiesForType.getMaxSupportedInstances() < vOSystemInfo.maxVideoHwCodecSupportedInstances || -1 == vOSystemInfo.maxVideoHwCodecSupportedInstances)) {
                                                vOSystemInfo.maxVideoHwCodecSupportedInstances = capabilitiesForType.getMaxSupportedInstances();
                                            }
                                            vOSystemInfo.secureCodecNameHevc = name;
                                        }
                                        vOSystemInfo.performancePointsForSecureHEVC = a(capabilitiesForType);
                                    }
                                }
                            }
                            i4++;
                            mediaCodecList = mediaCodecList2;
                            codecCount = i5;
                            codecInfoAt = mediaCodecInfo;
                        }
                    }
                    i3++;
                    mediaCodecList = mediaCodecList;
                    codecCount = codecCount;
                    i = 0;
                    i2 = 21;
                }
                if (vOSystemInfo.secureCodecName == null) {
                    String str4 = vOSystemInfo.osArch;
                    if (str4.contentEquals("i686") || str4.contentEquals("x86_64") || str4.contentEquals("x86")) {
                        str2 = vOSystemInfo.codecName;
                    } else {
                        str2 = vOSystemInfo.codecName + ".secure";
                    }
                    vOSystemInfo.secureCodecName = str2;
                    vOSystemInfo.performancePointsForSecureH264 = vOSystemInfo.performancePointsForH264;
                }
                if (vOSystemInfo.secureCodecNameHevc == null && vOSystemInfo.codecNameHevc != null) {
                    String str5 = vOSystemInfo.osArch;
                    if (str5.contentEquals("i686") || str5.contentEquals("x86_64") || str5.contentEquals("x86")) {
                        str = vOSystemInfo.codecNameHevc;
                    } else {
                        str = vOSystemInfo.codecNameHevc + ".secure";
                    }
                    vOSystemInfo.secureCodecNameHevc = str;
                    vOSystemInfo.performancePointsForSecureHEVC = vOSystemInfo.performancePointsForHEVC;
                }
                if (vOSystemInfo.codecNameAAC == null && (mediaCodecInfoA3 = a(MimeTypes.AUDIO_AAC, false)) != null) {
                    vOSystemInfo.codecNameAAC = mediaCodecInfoA3.getName();
                }
                if (vOSystemInfo.secureCodecNameAAC == null) {
                    MediaCodecInfo mediaCodecInfoA4 = a(MimeTypes.AUDIO_AAC, true);
                    if (mediaCodecInfoA4 != null) {
                        vOSystemInfo.secureCodecNameAAC = mediaCodecInfoA4.getName();
                    }
                    if (vOSystemInfo.secureCodecNameAAC == null && vOSystemInfo.codecNameAAC != null) {
                        vOSystemInfo.secureCodecNameAAC = vOSystemInfo.codecNameAAC + ".secure";
                    }
                }
                if (vOSystemInfo.codecNameAC3 == null && (mediaCodecInfoA2 = a(MimeTypes.AUDIO_RAW, false)) != null) {
                    vOSystemInfo.codecNameAC3 = mediaCodecInfoA2.getName();
                }
                if (vOSystemInfo.secureCodecNameAC3 == null) {
                    MediaCodecInfo mediaCodecInfoA5 = a(MimeTypes.AUDIO_RAW, true);
                    if (mediaCodecInfoA5 != null) {
                        vOSystemInfo.secureCodecNameAC3 = mediaCodecInfoA5.getName();
                    }
                    if (vOSystemInfo.secureCodecNameAC3 == null && vOSystemInfo.codecNameAC3 != null) {
                        vOSystemInfo.secureCodecNameAC3 = vOSystemInfo.codecNameAC3 + ".secure";
                    }
                }
                if (vOSystemInfo.codecNameEC3 == null && (mediaCodecInfoA = a(MimeTypes.AUDIO_E_AC3, false)) != null) {
                    vOSystemInfo.codecNameEC3 = mediaCodecInfoA.getName();
                }
                if (vOSystemInfo.secureCodecNameEC3 == null) {
                    MediaCodecInfo mediaCodecInfoA6 = a(MimeTypes.AUDIO_E_AC3, true);
                    if (mediaCodecInfoA6 != null) {
                        vOSystemInfo.secureCodecNameEC3 = mediaCodecInfoA6.getName();
                    }
                    if (vOSystemInfo.secureCodecNameEC3 == null && vOSystemInfo.codecNameEC3 != null) {
                        vOSystemInfo.secureCodecNameEC3 = vOSystemInfo.codecNameEC3 + ".secure";
                    }
                }
            }
            if (vOSystemInfo.codecName != null || context == null) {
                return;
            }
            String strB3 = VOPlatformAnalyzer.b(context);
            vOSystemInfo.codecName = strB3;
            vOSystemInfo.secureCodecName = strB3;
            vOSystemInfo.codecColorFormat = VOPlatformAnalyzer.a(context);
            VOPerformancePoint[] vOPerformancePointArr = (VOPerformancePoint[]) VOPerformancePoint.a(vOSystemInfo.codecName, MimeTypes.VIDEO_H264).toArray(new VOPerformancePoint[0]);
            vOSystemInfo.performancePointsForH264 = vOPerformancePointArr;
            vOSystemInfo.performancePointsForSecureH264 = vOPerformancePointArr;
        } catch (Exception unused3) {
        }
    }

    private static void c(VOSystemInfo vOSystemInfo) {
        try {
            vOSystemInfo.cpuMaxFrequency = VOPlatformAnalyzer.e();
            int iA = a();
            vOSystemInfo.cpuCount = iA;
            if (iA == 0) {
                vOSystemInfo.cpuCount = Runtime.getRuntime().availableProcessors();
            }
            vOSystemInfo.gpuTextureUpScore = VOPlatformAnalyzer.c();
            vOSystemInfo.copyBenchmarkScore = VOPlatformAnalyzer.b();
            vOSystemInfo.libArch = VOPlatformAnalyzer.f();
        } catch (Exception unused) {
        }
    }

    private static void d(Context context, VOSystemInfo vOSystemInfo) {
        int i;
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
            if (intentRegisterReceiver == null || intentRegisterReceiver.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
                vOSystemInfo.audioChannels = 2;
                vOSystemInfo.audioEncodings = new int[]{2};
                return;
            }
            vOSystemInfo.audioChannels = intentRegisterReceiver.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 2);
            int[] intArrayExtra = intentRegisterReceiver.getIntArrayExtra("android.media.extra.ENCODINGS");
            ArrayList arrayList = new ArrayList();
            if (intArrayExtra != null) {
                for (int i2 = 0; i2 < intArrayExtra.length; i2++) {
                    int i3 = intArrayExtra[i2];
                    if (i3 != 2) {
                        if (i3 != 5) {
                            if (i3 == 6 && a(MimeTypes.AUDIO_E_AC3) != null) {
                                i = intArrayExtra[i2];
                            }
                        } else if (a(MimeTypes.AUDIO_AC3) != null) {
                            i = intArrayExtra[i2];
                        }
                    } else {
                        i = intArrayExtra[i2];
                    }
                    arrayList.add(Integer.valueOf(i));
                }
            }
            vOSystemInfo.audioEncodings = new int[arrayList.size()];
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                vOSystemInfo.audioEncodings[i4] = ((Integer) arrayList.get(i4)).intValue();
            }
        } catch (Exception unused) {
        }
    }

    private static void d(VOSystemInfo vOSystemInfo) {
        StringBuilder sb;
        String str;
        String strB = b("ro.mediatek.platform");
        vOSystemInfo.devicePlatform = strB;
        if (strB == null || strB.length() <= 1) {
            vOSystemInfo.devicePlatform = b("ro.chipname");
        }
        String str2 = vOSystemInfo.devicePlatform;
        if (str2 == null || str2.length() <= 1) {
            vOSystemInfo.devicePlatform = b("ro.board.platform");
        }
        if (vOSystemInfo.devicePlatform == null) {
            vOSystemInfo.devicePlatform = new String(EnvironmentCompat.MEDIA_UNKNOWN);
        }
        if (Build.HARDWARE != null) {
            sb = new StringBuilder();
            sb.append(vOSystemInfo.devicePlatform);
            sb.append("_");
            str = Build.HARDWARE;
        } else {
            sb = new StringBuilder();
            sb.append(vOSystemInfo.devicePlatform);
            str = "_unknown";
        }
        sb.append(str);
        vOSystemInfo.devicePlatform = sb.toString();
    }

    private static void e(Context context, VOSystemInfo vOSystemInfo) {
        String str;
        vOSystemInfo.deviceId1 = Settings.Secure.getString(context.getContentResolver(), "android_id");
        try {
            vOSystemInfo.deviceId2 = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            vOSystemInfo.deviceId2 = e.getMessage();
        }
        if (18 <= Build.VERSION.SDK_INT) {
            str = com.viaccessorca.drm.impl.o.q() + "-" + com.viaccessorca.drm.impl.o.p();
        } else {
            str = "not implemented";
        }
        vOSystemInfo.deviceId3 = str;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
            StringBuilder sb = new StringBuilder();
            sb.append(wifiManager.getConnectionInfo().getBSSID());
            sb.append(" ");
            sb.append(String.format("%d.%d.%d.%d", Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf((ipAddress >> 24) & 255)));
            vOSystemInfo.deviceId4 = sb.toString();
        } catch (Exception e2) {
            vOSystemInfo.deviceId4 = e2.getMessage();
        }
    }

    protected static void f(Context context, VOSystemInfo vOSystemInfo) {
        int iIntValue;
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (Build.VERSION.SDK_INT >= 17) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                if (Build.VERSION.SDK_INT >= 23) {
                    Object objInvoke = defaultDisplay.getClass().getMethod("getMode", new Class[0]).invoke(defaultDisplay, new Object[0]);
                    Class<?> cls = Class.forName("android.view.Display$Mode");
                    Method method = cls.getMethod("getPhysicalWidth", new Class[0]);
                    Method method2 = cls.getMethod("getPhysicalHeight", new Class[0]);
                    vOSystemInfo.screenWidth = ((Integer) method.invoke(objInvoke, new Object[0])).intValue();
                    iIntValue = ((Integer) method2.invoke(objInvoke, new Object[0])).intValue();
                } else {
                    defaultDisplay.getRealMetrics(displayMetrics);
                    vOSystemInfo.screenWidth = displayMetrics.widthPixels;
                    iIntValue = displayMetrics.heightPixels;
                }
                vOSystemInfo.screenHeight = iIntValue;
                if (vOSystemInfo.isTV) {
                    String[] strArr = {"vendor.display-size", "sys.display-size"};
                    for (int i = 0; i < 2; i++) {
                        String strB = b(strArr[i]);
                        if (strB != null && !strB.isEmpty()) {
                            String[] strArrSplit = strB.split("x");
                            if (strArrSplit.length >= 2) {
                                int i2 = Integer.parseInt(strArrSplit[0]);
                                int i3 = Integer.parseInt(strArrSplit[1]);
                                if (i3 > vOSystemInfo.screenHeight && i2 > vOSystemInfo.screenWidth) {
                                    vOSystemInfo.screenHeight = i3;
                                    vOSystemInfo.screenWidth = i2;
                                }
                            }
                        }
                    }
                }
                if (Build.MODEL.equals("kunlun") || Build.MODEL.equals("SEI-S905X2") || Build.MODEL.equals("hk101") || Build.MODEL.equals("globe")) {
                    vOSystemInfo.screenWidth = 3840;
                    vOSystemInfo.screenHeight = 2160;
                }
            } else {
                try {
                    Method method3 = Display.class.getMethod("getRawHeight", new Class[0]);
                    vOSystemInfo.screenWidth = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(windowManager.getDefaultDisplay(), new Object[0])).intValue();
                    vOSystemInfo.screenHeight = ((Integer) method3.invoke(windowManager.getDefaultDisplay(), new Object[0])).intValue();
                } catch (Exception unused) {
                    windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                    vOSystemInfo.screenWidth = displayMetrics.heightPixels;
                    vOSystemInfo.screenHeight = displayMetrics.widthPixels;
                }
            }
            if (vOSystemInfo.screenHeight > vOSystemInfo.screenWidth) {
                int i4 = vOSystemInfo.screenHeight;
                vOSystemInfo.screenHeight = vOSystemInfo.screenWidth;
                vOSystemInfo.screenWidth = i4;
            }
        } catch (Exception unused2) {
        }
    }

    public static VOSystemInfo getSystemInfo(Context context) {
        VOSystemInfo vOSystemInfo = f108a;
        if (vOSystemInfo != null) {
            if (context != null) {
                if (-1 == vOSystemInfo.codecTypeException) {
                    vOSystemInfo.codecTypeException = VOFragmentationManager.getCodecTypeException(context, false);
                }
                VOSystemInfo vOSystemInfo2 = f108a;
                if (vOSystemInfo2.codecName == null) {
                    c(context, vOSystemInfo2);
                }
                d(context, f108a);
            }
            return f108a;
        }
        if (context == null) {
            VOSystemInfo vOSystemInfo3 = new VOSystemInfo();
            a((Context) null, vOSystemInfo3);
            return vOSystemInfo3;
        }
        VOSystemInfo vOSystemInfo4 = new VOSystemInfo();
        f108a = vOSystemInfo4;
        a(context, vOSystemInfo4);
        f108a.codecTypeException = VOFragmentationManager.getCodecTypeException(context, false);
        return f108a;
    }
}
