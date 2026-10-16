package com.viaccessorca.drm.impl;

import android.content.Context;
import android.os.Build;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOMissingPermissionException;
import com.viaccessorca.exceptions.VOStatusCode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
class DrmHelperUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Context f14a = null;
    private static int b = 60;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f15a;

        static {
            int[] iArr = new int[VOStatusCode.values().length];
            f15a = iArr;
            try {
                iArr[VOStatusCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15a[VOStatusCode.ERROR_SECURITY_MISSING_PERMISSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        sanityCheck();
    }

    DrmHelperUtils() {
    }

    public static String _subtitleTypeStringFromSqId(int i) {
        switch (i) {
            case 1:
                return "EXTERNAL";
            case 2:
                return "CEA608";
            case 3:
                return "WEBVTT";
            case 4:
                return "SMPTE";
            case 5:
                return "DVB";
            case 6:
                return "TTML";
            default:
                return "UNKNOWN";
        }
    }

    private static void a(Context context) throws VOException, ClassNotFoundException {
        String[] strArr = new String[6];
        if (context == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS, "Context is null");
        }
        String[] strArr2 = {"android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.BLUETOOTH_ADMIN"};
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            String str = strArr2[i2];
            if (JniDrmManager.checkSelfPermission(useAndroidXDependencies(), context, str) != 0) {
                strArr[i] = str;
                i++;
            }
        }
        if (i > 0) {
            String[] strArr3 = new String[i];
            for (int i3 = 0; i3 < i; i3++) {
                strArr3[i3] = strArr[i3];
            }
            throw new VOMissingPermissionException(strArr3);
        }
    }

    public static void checkPermissions() throws VOException {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                a(f14a);
            }
        } catch (ClassNotFoundException unused) {
            throw new VOException(VOStatusCode.ERROR_OPERATION_ABORTED, "Failed resolution of: PermissionChecker with useAndroidX: " + useAndroidXDependencies());
        } catch (NoClassDefFoundError unused2) {
            throw new VOException(VOStatusCode.ERROR_OPERATION_ABORTED, "Failed resolution of: PermissionChecker with useAndroidX: " + useAndroidXDependencies());
        }
    }

    public static void checkPermissions(Context context, boolean z) throws VOException {
        if (!z) {
            try {
                if (Build.VERSION.SDK_INT < 23) {
                    return;
                }
            } catch (ClassNotFoundException unused) {
                throw new VOException(VOStatusCode.ERROR_OPERATION_ABORTED, "Failed resolution of: Landroid/support/v4/content/PermissionChecker;");
            } catch (NoClassDefFoundError unused2) {
                throw new VOException(VOStatusCode.ERROR_OPERATION_ABORTED, "Failed resolution of: Landroid/support/v4/content/PermissionChecker;");
            }
        }
        a(context);
    }

    public static void deleteContentCache(Object obj) {
        try {
            File file = new File(f14a.getApplicationInfo().dataDir + "/" + String.format("%s", obj).hashCode());
            if (file.exists()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    public static void downloadFile(String str, String str2) throws Throwable {
        if (str == null) {
            throw new IOException("Url is null");
        }
        downloadFile(str, str2, (str.toLowerCase().endsWith(".ts") || str.toLowerCase().endsWith(".eny")) ? 1024 : -1);
    }

    public static void downloadFile(String str, String str2, int i) throws Throwable {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(str2);
            try {
                byte[] bArr = new byte[4096];
                boolean z = i == -1;
                URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
                uRLConnectionOpenConnection.setConnectTimeout(60000);
                uRLConnectionOpenConnection.setReadTimeout(60000);
                InputStream inputStream = uRLConnectionOpenConnection.getInputStream();
                int i2 = 0;
                while (true) {
                    if (!z && i2 >= i) {
                        break;
                    }
                    int i3 = i - i2;
                    if (z || i3 >= 4096) {
                        i3 = 4096;
                    }
                    int i4 = inputStream.read(bArr, 0, i3);
                    if (i4 == -1) {
                        break;
                    }
                    i2 += i4;
                    fileOutputStream.write(bArr, 0, i4);
                }
                fileOutputStream.close();
            } catch (Throwable th) {
                th = th;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
    }

    public static String generateFileName(Object obj, String str) throws IOException {
        try {
            String str2 = f14a.getApplicationInfo().dataDir + "/" + String.format("%s", obj).hashCode();
            if (!new File(str2).mkdirs() && !new File(str2).exists()) {
                throw new IOException("Cannot create content directory on application folder");
            }
            String strSubstring = str.substring(str.lastIndexOf(47) + 1);
            if (strSubstring.lastIndexOf(63) > 0) {
                strSubstring = strSubstring.substring(0, strSubstring.lastIndexOf(63));
            }
            return strSubstring.lastIndexOf(46) > 0 ? String.format("%s/%08X%s", str2, Integer.valueOf(str.hashCode()), strSubstring.substring(strSubstring.lastIndexOf(46))) : String.format("%s/%08X_%s", str2, Integer.valueOf(str.hashCode()), strSubstring);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static Context getContext() {
        return f14a;
    }

    public static int getHttpTimeoutInSec() {
        return b;
    }

    public static String getLocalPath(Object obj, String str) throws Throwable {
        if (str == null) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS, "The filename is null");
        }
        if (str.startsWith("/")) {
            return str;
        }
        File file = null;
        try {
            String strGenerateFileName = generateFileName(obj, str);
            File file2 = new File(strGenerateFileName);
            try {
                if (!file2.exists()) {
                    downloadFile(str, strGenerateFileName);
                }
                return strGenerateFileName;
            } catch (IOException unused) {
                file = file2;
                if (file != null && file.exists()) {
                    file.delete();
                }
                throw new VOException(VOStatusCode.ERROR_FILE_ACCESS, "cannot get local path of " + str);
            }
        } catch (IOException unused2) {
        }
    }

    public static Date getTimeFromString(String str) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US).parse(str);
        } catch (ParseException unused) {
            return null;
        }
    }

    public static void moveAssetFileInApplicationFolder(Context context, String str, String str2) throws VOException {
        try {
            String str3 = (str2 == null ? context.getFilesDir() : context.getDir(str2, 0)) + "/" + str;
            if (new File(str3).exists()) {
                return;
            }
            InputStream inputStreamOpen = context.getAssets().open(str);
            byte[] bArr = new byte[8000];
            int i = inputStreamOpen.read(bArr);
            FileOutputStream fileOutputStream = new FileOutputStream(str3);
            fileOutputStream.write(bArr, 0, i);
            fileOutputStream.close();
        } catch (IOException unused) {
            throw new VOException(VOStatusCode.ERROR_BAD_ARGUMENTS);
        }
    }

    public static void sanityCheck() {
        if (VOStatusCode.ERROR_BAD_ARGUMENTS.ordinal() != 2 || VOStatusCode.ERROR_SECURITY_MISSING_PERMISSION.ordinal() != 8 || VOStatusCode.ERROR_DRM_TRUSTED_TIME_NOT_SET.ordinal() != 15 || VOStatusCode.ERROR_FILE_ACCESS.ordinal() != 24 || VOStatusCode.INVALID_CODE.ordinal() != 27) {
            throw new RuntimeException("Check the VOStatusCode from native and Java");
        }
    }

    public static void setContext(Context context) {
        f14a = context;
    }

    public static void setHttpTimeoutInSec(int i) {
        b = i;
    }

    public static void throwVOException(VOStatusCode vOStatusCode) throws VOException {
        int i = a.f15a[vOStatusCode.ordinal()];
        if (i != 1) {
            if (i == 2) {
                throw new VOMissingPermissionException("android.permission.READ_PHONE_STATE");
            }
            throw new VOException(vOStatusCode);
        }
    }

    public static boolean useAndroidXDependencies() {
        try {
            Class.forName("androidx.core.content.PermissionChecker");
            return true;
        } catch (ClassNotFoundException | Exception unused) {
            return false;
        }
    }
}
