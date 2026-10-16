package com.viaccessorca.voplayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.preference.PreferenceManager;
import com.viaccessorca.common.VOUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class VOFragmentationManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f67a = null;
    private static boolean b = false;
    private static boolean c = false;

    private static class a extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Context f68a;
        private String b;
        private String c;
        private Semaphore d;
        private int e;
        private int f;
        private int g;

        a(Context context, String str, int i, Semaphore semaphore) {
            this.c = null;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.f68a = context;
            this.b = str;
            this.e = i;
            this.d = semaphore;
        }

        a(Context context, String str, int i, Semaphore semaphore, int i2, int i3) {
            this.c = null;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.f68a = context;
            this.b = str;
            this.c = context.getPackageName();
            this.e = i;
            this.d = semaphore;
            this.g = i3;
            this.f = i2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i = this.e;
            if (i == 0) {
                if (VOSystemInfoRetriever.a(false)) {
                    Context context = this.f68a;
                    VOFragmentationManager.b(context, VOSystemInfoRetriever.getSystemInfo(context), this.b);
                }
            } else if (1 == i) {
                VOFragmentationManager.c(this.f68a, this.b);
            } else if (2 == i && VOSystemInfoRetriever.a(false)) {
                VOFragmentationManager.b(VOSystemInfoRetriever.getSystemInfo(this.f68a), this.b, this.f, this.g, this.c, this.f68a);
            }
            Semaphore semaphore = this.d;
            if (semaphore != null) {
                semaphore.release();
            }
        }
    }

    private static final native int _getDefaultCodecMode(int i);

    private static final int a() {
        return _getDefaultCodecMode(Build.VERSION.SDK_INT);
    }

    private static int a(VOSystemInfo vOSystemInfo, String str, String str2) {
        int i;
        String strReplace = vOSystemInfo.deviceModel.replace(" ", "_");
        String strReplace2 = vOSystemInfo.deviceBrand.replace(" ", "_");
        String strReplace3 = vOSystemInfo.devicePlatform.replace(" ", "_");
        String strValueOf = String.valueOf(vOSystemInfo.isTV);
        try {
            strReplace = URLEncoder.encode(strReplace, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        String str3 = str + "/" + Build.VERSION.SDK_INT + "/" + strReplace + "/" + strReplace2 + "/" + vOSystemInfo.codecName + "/" + vOSystemInfo.codecColorFormat + "/" + vOSystemInfo.osArch + "/" + a() + "/" + strReplace3 + "/" + strValueOf;
        String strA = a("v1.5/", str3, (String) null);
        HashMap map = new HashMap(0);
        map.put("Payload-Hash", strA);
        VOUtils.RequestResponse requestResponseSendHTTPRequest = VOUtils.sendHTTPRequest(str2 + "/v1.5/" + str3, VOUtils.HTTP_GET, null, map, true);
        if (requestResponseSendHTTPRequest == null || (i = requestResponseSendHTTPRequest.responseCode) < 200 || i >= 300) {
            return -1;
        }
        return a("v1.5/" + str3, requestResponseSendHTTPRequest);
    }

    private static final int a(String str, VOUtils.RequestResponse requestResponse) {
        String str2 = requestResponse.headers.containsKey("Payload-Hash") ? requestResponse.headers.get("Payload-Hash").get(0) : null;
        byte[] bArr = requestResponse.data;
        if (bArr != null) {
            String str3 = new String(bArr);
            if (!b(str, str3, str2)) {
                return -1;
            }
            try {
                return new JSONObject(str3).getInt("codecException");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private static String a(int i, int i2) {
        return ("limit_fail=" + i2 + "\n") + "limit_success=" + i;
    }

    protected static String a(Context context) {
        try {
            return VOPlayer.f(context);
        } catch (Exception unused) {
            return null;
        }
    }

    private static String a(VOSystemInfo vOSystemInfo) {
        return ((((((((((("ColorFormat=0x" + Integer.toHexString(vOSystemInfo.codecColorFormat) + "\n") + "CodecName=" + vOSystemInfo.codecName + "\n") + "ScreenWidth=" + vOSystemInfo.screenWidth + "\n") + "ScreenHeight=" + vOSystemInfo.screenHeight + "\n") + "CPUCount=" + vOSystemInfo.cpuCount + "\n") + "CPUFrequency=" + vOSystemInfo.cpuMaxFrequency + "\n") + "CPUArch=" + vOSystemInfo.osArch + "\n") + "DevicePlatform=" + vOSystemInfo.devicePlatform + "\n") + "DeviceBrand=" + Build.BRAND + "\n") + "GPUBenchmark=" + vOSystemInfo.gpuTextureUpScore + "\n") + "CPUBenchmark=" + vOSystemInfo.cpuBenchmarkScore + "\n") + "MemcopyBenchmark=" + vOSystemInfo.copyBenchmarkScore;
    }

    private static String a(String str, String str2, String str3) {
        String str4 = str + str2 + (str.length() + str2.length()) + "#75jp96agf";
        if (str3 != null) {
            str4 = str4 + str3 + str3.length();
        }
        return VOUtils.bytesToHex(VOUtils.getSHA256Hash(str4)).toLowerCase();
    }

    private static void a(Context context, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("VOPlayerBenchmarkPrefs", 0).edit();
        editorEdit.putString("lastFAAS_OSSignature", Build.VERSION.RELEASE);
        editorEdit.putString("lastFAAS_SdkVersion", VOPlayer.g(context));
        editorEdit.putLong("lastFAAS_RequestTime", System.currentTimeMillis());
        editorEdit.putInt("lastCodecExceptionType", i);
        editorEdit.commit();
    }

    protected static void a(boolean z) {
        c = z;
    }

    private static boolean a(SharedPreferences sharedPreferences) {
        return !sharedPreferences.getString("lastFrag_OSSignature", "").equalsIgnoreCase(b());
    }

    private static boolean a(SharedPreferences sharedPreferences, String str) {
        return !sharedPreferences.getString("lastFrag_SdkVersion", "").equalsIgnoreCase(str);
    }

    protected static int b(Context context) {
        try {
            String strE = VOPlayer.e(context);
            if (strE == null || !strE.toLowerCase().contains("lattelecom")) {
                return -1;
            }
            Build.MODEL.equals("QM163E");
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    private static String b() {
        return Build.VERSION.RELEASE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, VOSystemInfo vOSystemInfo, String str) {
        int i;
        String strReplace = vOSystemInfo.deviceModel.replace(" ", "_");
        try {
            strReplace = URLEncoder.encode(strReplace, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        String str2 = "/" + str + "/" + strReplace + "/" + Build.VERSION.SDK_INT + "/";
        String str3 = "https://cdc-voplayer.viaccess-orca.com/v1/market_info" + str2;
        String strA = a(vOSystemInfo);
        String strA2 = a("/v1/market_info", str2, strA);
        HashMap map = new HashMap(0);
        map.put("payload-hash", strA2);
        VOUtils.RequestResponse requestResponseSendHTTPRequest = VOUtils.sendHTTPRequest(str3, VOUtils.HTTP_PUT, strA.getBytes(), map, true);
        if (requestResponseSendHTTPRequest == null || (i = requestResponseSendHTTPRequest.responseCode) < 200 || i >= 300) {
            return;
        }
        b(context, str);
    }

    private static void b(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("quickplayerActivationPrefs", 0).edit();
        editorEdit.putString("lastFrag_OSSignature", b());
        editorEdit.putString("lastFrag_SdkVersion", str);
        editorEdit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(VOSystemInfo vOSystemInfo, String str, int i, int i2, String str2, Context context) {
        int i3;
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        String strReplace = vOSystemInfo.deviceModel.replace(" ", "_");
        try {
            strReplace = URLEncoder.encode(strReplace, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        String str3 = "/" + str + "/" + strReplace + "/" + Build.VERSION.SDK_INT + "/" + str2 + "/";
        String str4 = "https://evalvexp.quickplayer-server.com/v1/ExperienceEvaluation" + str3;
        String strA = a(i2, i);
        String strA2 = a("/v1/ExperienceEvaluation", str3, strA);
        HashMap map = new HashMap(0);
        map.put("payload-hash", strA2);
        VOUtils.RequestResponse requestResponseSendHTTPRequest = VOUtils.sendHTTPRequest(str4, VOUtils.HTTP_PUT, strA.getBytes(), map, false);
        if (requestResponseSendHTTPRequest == null || (i3 = requestResponseSendHTTPRequest.responseCode) < 200 || i3 >= 300) {
            return;
        }
        editorEdit.putInt("SUCCESS_BUFFER", i2);
        editorEdit.putInt("FAIL_BUFFER", i);
        editorEdit.commit();
    }

    private static final boolean b(String str, String str2, String str3) {
        return VOUtils.bytesToHex(VOUtils.getSHA256Hash(str + str2 + (str.length() + str2.length()) + "#75jp96agf")).equalsIgnoreCase(str3);
    }

    private static final boolean c(Context context) {
        b = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("VOPlayerBenchmarkPrefs", 0);
        if (!sharedPreferences.contains("lastCodecExceptionType")) {
            return true;
        }
        long j = sharedPreferences.getLong("lastFAAS_RequestTime", 0L);
        if (j > 0) {
            String string = sharedPreferences.getString("lastFAAS_SdkVersion", null);
            String string2 = sharedPreferences.getString("lastFAAS_OSSignature", null);
            String strG = VOPlayer.g(context);
            if (Build.VERSION.RELEASE.equals(string2) && strG.equals(string)) {
                return (System.currentTimeMillis() - j) / 1000 > 86400;
            }
            b = true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean c(Context context, String str) {
        int iA;
        VOSystemInfo systemInfo = VOSystemInfoRetriever.getSystemInfo(context);
        String strH = VOPlayer.h(context);
        String strA = a(context);
        if (strA == null) {
            strA = "https://faas-voplayer.viaccess-orca.com";
        }
        String str2 = f67a;
        if (str2 == null) {
            iA = a(systemInfo, strH, strA);
            if (-1 == iA) {
                iA = a(systemInfo, strH, "https://faas-sec.squadeo.tv");
            }
        } else {
            iA = d.a(context, systemInfo, strH, str2);
            f67a = null;
        }
        if (-1 == iA) {
            return false;
        }
        a(context, iA);
        return true;
    }

    private static boolean d(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        } catch (SecurityException unused) {
        }
    }

    public static int getCodecTypeException(Context context, boolean z) {
        int iB = b(context);
        int i = -1;
        if (-1 != iB) {
            return iB;
        }
        if (z) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("VOPlayerBenchmarkPrefs", 0);
            if (c(context) && (d(context) || f67a != null)) {
                String strReplace = VOPlayer.g(context).replace(".", "_");
                int i2 = 5000;
                Semaphore semaphore = new Semaphore(0);
                new a(context, strReplace, 1, semaphore).start();
                if (sharedPreferences.contains("lastCodecExceptionType") && !b) {
                    i2 = 300;
                }
                try {
                    semaphore.tryAcquire(c ? 0 : i2, TimeUnit.MILLISECONDS);
                } catch (InterruptedException unused) {
                }
            } else if (sharedPreferences.contains("lastCodecExceptionType")) {
            }
            i = sharedPreferences.getInt("lastCodecExceptionType", -1);
        } else {
            SharedPreferences sharedPreferences2 = context.getSharedPreferences("VOPlayerBenchmarkPrefs", 0);
            if (sharedPreferences2.contains("lastCodecExceptionType")) {
                i = sharedPreferences2.getInt("lastCodecExceptionType", -1);
            }
        }
        if (3 == i || 4 == i) {
            return 7;
        }
        return i;
    }

    public static boolean isFragmentationReportRequired(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("quickplayerActivationPrefs", 0);
        return a(sharedPreferences) || a(sharedPreferences, str);
    }

    public static void sendExperienceReport(Context context, String str, int i, int i2) {
        new a(context, str, 2, null, i, i2).start();
    }

    public static void sendFragmentationReport(Context context, String str) {
        new a(context, str, 0, null).start();
    }

    public static void setOfflineMode(String str) {
        f67a = str;
    }
}
