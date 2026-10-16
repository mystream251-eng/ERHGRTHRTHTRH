package com.viaccessorca.vodownloader;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.viaccessorca.common.VOLibraryLoader;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.drm.VOPlayreadyAgent;
import com.viaccessorca.drm.VOVerimatrixAgent;
import com.viaccessorca.drm.VOViaccessAgent;
import com.viaccessorca.drm.VOWidevineAgent;
import com.viaccessorca.drm.impl.DrmAgent;
import com.viaccessorca.drm.impl.VerimatrixAgent;
import com.viaccessorca.drm.impl.e;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class VODownloader {
    public static final int DOWNLOADER_ERROR_BAD_URL = 205;
    public static final int DOWNLOADER_ERROR_CACHEALLOWFLAG_NO = 206;
    public static final int DOWNLOADER_ERROR_DOWNLOAD_NETWORK_CONSTRAINT_NOT_VERIFIED = 215;
    public static final int DOWNLOADER_ERROR_LIVE_UNSUPPORTED = 204;
    public static final int DOWNLOADER_ERROR_NETWORK_BROKEN = 201;
    public static final int DOWNLOADER_ERROR_NOMORESPACE = 202;
    public static final int DOWNLOADER_ERROR_OFFLINE_NOT_ALLOWED = 212;
    public static final int DOWNLOADER_ERROR_UNKNOWN = 200;
    public static final int DOWNLOADER_ERROR_VERSION_UNSUPPORTED = 203;
    public static final int DOWNLOADER_INFO_DOWNLOAD_COMPLETED = 104;
    public static final int DOWNLOADER_INFO_DOWNLOAD_PROGRESS = 103;
    public static final int DOWNLOADER_INFO_DOWNLOAD_SPEED = 106;
    public static final int DOWNLOADER_INFO_FRAGMENT_SKIPPED = 111;
    public static final int DOWNLOADER_INFO_LOWSPACE = 105;
    public static final int DOWNLOADER_INFO_NETWORK_BACK = 102;
    public static final int DOWNLOADER_INFO_NETWORK_DOWN = 101;
    public static final int DOWNLOADER_INFO_OFFLINE_KEYS_RENEWAL_COMPLETED = 109;
    public static final int DOWNLOADER_INFO_OFFLINE_KEYS_RENEWAL_INTERRUPTED = 110;
    public static final int DOWNLOADER_INFO_OFFLINE_KEYS_RENEWAL_STARTED = 108;
    public static final int DOWNLOADER_INFO_OFFLINE_NOT_ALLOWED = 107;
    public static final int DOWNLOADER_INFO_UNKNOWN = 100;
    private static Context m = null;
    private static String n = null;
    private static boolean o = false;
    private static int p = 0;
    private static boolean q = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f48a;
    private VerimatrixAgent e;
    private e f;
    private OnPreparedListener g;
    private OnInfoListener h;
    private OnErrorListener i;
    private b j;
    private boolean k;
    private DownloadNetworkConstraint l;
    private long m329t7p639082847842 = 0;
    private String b = null;
    private String c = null;
    private boolean d = false;

    public enum Accessibility {
        UNSET,
        VISUALLY_IMPAIRED,
        HARD_OF_HEARING,
        DESCRIPTION,
        ENHANCED_AUDIO_INTELLIGIBILITY,
        CAPTION,
        SIGN,
        UNKNOWN
    }

    public enum AdaptationSetRole {
        UNSET,
        ALTERNATE,
        COMMENTARY,
        DESCRIPTION,
        DUB,
        EMERGENCY,
        MAIN,
        SUBTITLE,
        SUPPLEMENTARY,
        UNKNOWN
    }

    public static final class AudioTrackInfo {
        public Accessibility accessibility;
        public AdaptationSetRole adaptationSetRole;
        public int downloadedDuration;
        public boolean isDefault;
        public String language;
        public String name;

        public AudioTrackInfo(String str, String str2, boolean z, int i, int i2) {
            this.language = str;
            this.name = str2;
            this.isDefault = z;
            this.downloadedDuration = i;
            this.adaptationSetRole = AdaptationSetRole.values()[i2];
        }

        public AudioTrackInfo(String str, String str2, boolean z, int i, int i2, int i3) {
            this(str, str2, z, i, i2);
            this.accessibility = Accessibility.values()[i3];
        }
    }

    public enum DownloadNetworkConstraint {
        NONE,
        WIFI,
        ETHERNET,
        WIFI_ETHERNET
    }

    public interface OnErrorListener {
        boolean onError(VODownloader vODownloader, int i, int i2);
    }

    public interface OnInfoListener {
        boolean onInfo(VODownloader vODownloader, int i, int i2);
    }

    public interface OnPreparedListener {
        void onPrepared(VODownloader vODownloader);
    }

    public static final class SubtitleTrackInfo {
        public Accessibility accessibility;
        public AdaptationSetRole adaptationSetRole;
        public int downloadedDuration;
        public boolean isDefault;
        public boolean isForce;
        public String language;
        public String name;

        public SubtitleTrackInfo(String str, String str2, boolean z, boolean z2, int i, int i2) {
            this.language = str;
            this.name = str2;
            this.isDefault = z;
            this.isForce = z2;
            this.downloadedDuration = i;
            this.adaptationSetRole = AdaptationSetRole.values()[i2];
        }

        public SubtitleTrackInfo(String str, String str2, boolean z, boolean z2, int i, int i2, int i3) {
            this(str, str2, z, z2, i, i2);
            this.accessibility = Accessibility.values()[i3];
        }
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f49a;

        static {
            int[] iArr = new int[DownloadNetworkConstraint.values().length];
            f49a = iArr;
            try {
                iArr[DownloadNetworkConstraint.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f49a[DownloadNetworkConstraint.WIFI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f49a[DownloadNetworkConstraint.ETHERNET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f49a[DownloadNetworkConstraint.WIFI_ETHERNET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private class b extends CountDownTimer {
        public b(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            Message messageObtainMessage;
            if (VODownloader.this.d || VODownloader.this.k) {
                try {
                    if (VODownloader.this.c()) {
                        if (!VODownloader.this.k) {
                            return;
                        }
                        VODownloader.this.OERENkEwOTEwNUI4NDNGRThEQjIyNkE3ODJDNkRFMjI();
                        VODownloader.this.d = true;
                        VODownloader.this.k = false;
                        if (VODownloader.this.f48a == null) {
                            return;
                        } else {
                            messageObtainMessage = VODownloader.this.f48a.obtainMessage(100, 102, 0, null);
                        }
                    } else {
                        if (!VODownloader.this.d) {
                            return;
                        }
                        VODownloader.this.MEJBRjFDNEFENjk4NEFDQkJDODdDMzNERTQyMzkxRDA();
                        VODownloader.this.d = false;
                        VODownloader.this.k = true;
                        if (VODownloader.this.f48a == null) {
                            return;
                        } else {
                            messageObtainMessage = VODownloader.this.f48a.obtainMessage(100, 101, 0, null);
                        }
                    }
                    VODownloader.this.f48a.sendMessage(messageObtainMessage);
                } catch (Exception unused) {
                }
            }
        }
    }

    private static final class c {
    }

    private class d extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private VODownloader f51a;

        public d(VODownloader vODownloader, Looper looper) {
            super(looper);
            this.f51a = null;
            this.f51a = vODownloader;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (0 == this.f51a.m329t7p639082847842) {
                return;
            }
            int i = message.what;
            if (i == 0) {
                if (VODownloader.this.g != null) {
                    VODownloader.this.g.onPrepared(this.f51a);
                }
            } else if (i == 100) {
                if (VODownloader.this.h != null) {
                    VODownloader.this.h.onInfo(this.f51a, message.arg1, message.arg2);
                }
            } else {
                if (i != 200) {
                    return;
                }
                VODownloader.this.d = false;
                VODownloader.this.k = false;
                if (VODownloader.this.i != null) {
                    VODownloader.this.i.onError(this.f51a, message.arg1, message.arg2);
                }
            }
        }
    }

    static {
        boolean zB = VOLibraryLoader.b();
        q = zB;
        if (true == zB) {
            RjFCMDk0NDhFNjI4NEY4MDhEMTI2MjM0Nzc4M0ZEQTY();
        }
    }

    public VODownloader(Context context, String str) {
        DrmAgent.EDrmType eDrmType = DrmAgent.EDrmType.DRM_TYPE_UNKNOWN;
        this.e = null;
        this.f = null;
        this.j = null;
        this.k = false;
        this.l = DownloadNetworkConstraint.NONE;
        b(context, str);
    }

    private static native String[] JarSoAlignedMkQ1MjYzQjJCRkUzNDY2RUJEREJGMDcxQTU1QzUzQjE090210(String str);

    private native void M0I5MTU0MTJDQTQxNEQ0MUI0M0ZDOUNERDVGRUJGMEY();

    private native void M0UwMjlFRUY3RjVDNDk1OTg2Q0ZDMkEwRjUyMzUwMDQ(String str) throws IllegalStateException;

    private native void MDEyNDI1RTREM0ExNDU1MDhBMUEzQjA5Qjk4MzMxM0E(boolean z);

    private native void MDQ0RkZDM0FEREI4NDg2Rjk3NzIxQ0YzNkUwREIwQ0Q();

    private native void MDk4MjY1MDYzQzU1NDBGNDgzQ0I1Q0MzNzM0MDEzNzI();

    /* JADX INFO: Access modifiers changed from: private */
    public native void MEJBRjFDNEFENjk4NEFDQkJDODdDMzNERTQyMzkxRDA();

    private native void MTkyN0UzQjNGRTA0NEUyM0FDNjkyRkQ1OENBMkU3QTY(String str) throws IllegalStateException;

    private native void MUFFNjE5NDdDNTU1NEEzMDgzOURDQzRDQ0Y3QTU1QjI(String str);

    private native int MjNDOTY0ODk3MkVDNENDQjgzOTEwRjNBNEQzNzJFMUU();

    private native void MkQ2Njc4QjgwM0JDNDlCNkIxNENFNEE0MTYyODZDOUQ(String str);

    private native Object[] MzE1RUQzQjZDRUNENDM0N0FBNzk2RURDQTNDQTdBNDA() throws IllegalStateException;

    private native void MzIwRkIzNDYxNzAwNDM4OTg4QkNENzQyNzIyODYwNDk(String[] strArr) throws IllegalStateException;

    private native void MzRGMjVDMDgzNzI1NDZFMzg4MDgzMDdFOEU5ODRFMjM(Context context, String str, boolean z) throws IllegalStateException;

    private native int MzY0MzY2M0U2RTExNEIwNUIyMTA0NTJDMjI3N0FCOUU();

    private native void Mzc2MTcxQUQ3QUI1NEQ4Rjg0Q0IxRkRGMjAxNjg1QUE(String str, int i, int i2, String str2, String str3);

    private native void N0NGN0REMjI0N0JCNDlDNEE3QTI4MTY2RTQ5ODNGREI(String str, int i);

    private native void NDQ5MjFENkFBNkE2NEE4OEI1MDIyRDAwNUY4OTdCODA(String[] strArr) throws IllegalStateException;

    private static native void NTM1ODhCQTRFN0Q5NDYzRDhBQjhBQjkwMzRDQ0IyMzMK(int i);

    private native void NTVDMzJENDIzMUExNEExMkI3Q0NDM0QzMjdCMDIzMDQ(String str) throws IllegalStateException;

    private native Object[] NTY4QkIzNzZEMTAxNEYzNjhCN0Y1MDkwMDExQ0EyQUY() throws IllegalStateException;

    private native void NjRBRkU1RDc0RkRGNDg1NUEyRjkwQTdFNzNBQUNEM0I(String str);

    private native void NjdEQTlCNkQ1MTBDNDZFNjk1MkY3QThGNzQzODlGNTU(int i);

    private native void NjlBMDdDRUQ0QjQ1NEQ2NThCMjVBNjVDQjk1RUE1MkU(String str, String str2);

    private native void NkNCNTcxNTk0NjcyNDYwNDlEQkM4QzMxMzM2MDExODQ();

    private native int NkRGRDcyRjI1Q0E2NDM0QTgxNTFCOTVBQTAzRDg0QTc();

    private native void NzBFNzVGQzlENjE3NDY0RkE1NTlCRDVEODZGNDA0ODQ(String str, int i, String str2, int i2);

    private native void ODBBNzAxNDg2MTc0NDc0Nzk2QjQ3QzczRTBFQzZGMEU(String str);

    private native void ODM2OTY2NkI4M0VDNDBCRkFDQzQwQjJFMzc2RDRGMTA();

    private native void OEQwN0I2RTkyQTNFNEU4QUI1OUU5RkMzQTgzRTYxQzQ(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void OERENkEwOTEwNUI4NDNGRThEQjIyNkE3ODJDNkRFMjI() throws IllegalStateException, UnsupportedOperationException;

    private native void OTVGMkRCRTFFNTY1NDVBNTg3QzI1NjNCRTFFNDlGOTg(String str);

    private static native c[] Q0NFNDQ4ODIxQkI3NEMzNEJDQ0UzODU1NTkwRkFFNTc(String str, String str2);

    private native void Q0Q1Q0Y4MjZBMjg1NDgyMUE5MkMwMTZGNTcyRDk2Njg(String str);

    private native void Q0QzRDRBMDVCNzBGNDU4Mzg2NEVBQjQ5RDQzOTE0RDg();

    private static native String Q0ZGQkVGOUU4QjhDNERCQ0E0NUIyNDlEOTUwMTFGNDc();

    private native void QTBFNDM2QUNDMDkwNDg2QkE3M0JEQzUxMEZGRTVDMzA(boolean z) throws IllegalStateException;

    private native int QTM2NjRBMkE3Rjg2NDhEQTgxMEQ0ODYyNTQ0RDEzRTU();

    private native String QUU4ODExRTI2MEQxNEZDQTg5NTI2Q0ZGNzY4MDY3ODU();

    private native int QUVCQkMwQkRGOUZENEYwNTk0NjkzODQ5NTY3MzYyRkM();

    private native void QjRGNjcxMTA3ODJFNEQ4QkE2OTVGNTU2NzQxNDJBNDM(int i) throws IllegalStateException, IllegalArgumentException;

    private final native void QkUwRDExN0FDRjk4NDA3N0FGMzM1MjgyNEYwMEY1MTE(Object obj, String str, String str2) throws IllegalStateException;

    private native void QzRDNzVENTA5MDdBNDY3Qjg2NjMyQUM4NUJEMEQxMEY(String str);

    private native void QzU0NzBCRTZBMDVGNEU2RjlGRDA5MURDMTlGRTI2NTg(String str) throws IllegalStateException;

    private native int[] QzlCNjI1QkFFOUQ4NEZGQzk1MTZDNTg3Q0FEQ0UwOEM(int i);

    private native void QzlDNjQ4RUREQTQ2NDVBOEE0OTA0OURCQUFCOTg0RDA(int i);

    private native void QzlDNjQ4RUREQTQ2NDVBOEE0OTA0OURCQUFCOTg0RDAByIndex(int i);

    private native void RDU4NTM0NjQ0RkQxNDNGM0JEREZDMDQ1QUE3MjgzMTU();

    private native int RUQzNkZEQzUwMTA2NDhEODlGNzA5MEU5OTFDM0U3NTk(int i);

    private native void RUUzM0NBNTY5QjMxNDRGNkJDNkRBNTgwOTFGQTAzRDA();

    private static final native void RjFCMDk0NDhFNjI4NEY4MDhEMTI2MjM0Nzc4M0ZEQTY();

    private native void RjFCMDk0NDhFNjI4NEY4MDhEMTI2MjM0Nzc4M0ZEQTY2();

    private native int RjJGRDI2NkI0QjhGNEFEMDlDOEI1MTEzQkM3Q0JENEI(String str);

    private native void RjQwRDgxNUMyMzVCNDM4Qzk0QTIxM0QwNkVDOEY3NkM(String str);

    private int a(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("invalid parameter");
        }
        return RjJGRDI2NkI0QjhGNEFEMDlDOEI1MTEzQkM3Q0JENEI(str);
    }

    private String a(Context context, String str) {
        if (str == null || !str.contains("/android_asset/") || context == null) {
            return str;
        }
        File filesDir = context.getFilesDir();
        if (!o) {
            String strReplaceAll = str.replaceAll("file:///android_asset/", "").replaceAll("/android_asset/", "");
            try {
                AssetManager assets = context.getAssets();
                String[] list = assets.list((strReplaceAll.length() <= 0 || strReplaceAll.charAt(strReplaceAll.length() - 1) != '/') ? strReplaceAll : strReplaceAll.substring(0, strReplaceAll.length() - 1));
                if (list.length > 0) {
                    for (String str2 : list) {
                        if (str2 != null && str2.startsWith("dc=")) {
                            try {
                                InputStream inputStreamOpen = assets.open(strReplaceAll + str2);
                                File file = new File(filesDir.getAbsolutePath() + "/tmpforqp/" + str2);
                                file.getParentFile().mkdir();
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i = inputStreamOpen.read(bArr);
                                    if (i <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, i);
                                }
                                fileOutputStream.close();
                                inputStreamOpen.close();
                            } catch (IOException unused) {
                            }
                        }
                    }
                }
                o = true;
            } catch (IOException unused2) {
                return null;
            }
        }
        return filesDir.getAbsolutePath() + "/tmpforqp";
    }

    private void a() {
        b bVar = this.j;
        if (bVar != null) {
            bVar.cancel();
            this.j = null;
        }
        this.f48a = null;
        this.d = false;
        this.k = false;
        this.g = null;
        this.i = null;
        this.h = null;
        M0I5MTU0MTJDQTQxNEQ0MUI0M0ZDOUNERDVGRUJGMEY();
    }

    private static void a(Context context) {
        if (context == null) {
            return;
        }
        String str = n;
        if ((str != null ? new File(str).exists() : false) && n != null) {
            return;
        }
        try {
            InputStream inputStreamOpen = context.getAssets().open("quickplayer.lic");
            String str2 = context.getFilesDir().getAbsolutePath() + "/quickplayer.lic";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i <= 0) {
                    fileOutputStream.close();
                    inputStreamOpen.close();
                    n = str2;
                    return;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (IOException unused) {
        }
    }

    private void b() {
        this.d = false;
        this.k = false;
        RjFCMDk0NDhFNjI4NEY4MDhEMTI2MjM0Nzc4M0ZEQTY2();
    }

    private void b(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            this.b = filesDir.getAbsolutePath();
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003b  */
    /* JADX WARN: Code duplicated, block: B:14:0x004e  */
    /* JADX WARN: Code duplicated, block: B:16:? A[RETURN, SYNTHETIC] */
    private void b(Context context, String str) {
        d dVar;
        StringBuilder sb = new StringBuilder();
        sb.append("VODownloader#");
        int i = p;
        p = i + 1;
        sb.append(i);
        new String(sb.toString());
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper == null) {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                dVar = new d(this, mainLooper);
            } else {
                this.f48a = null;
            }
            if (context != null) {
                b(context);
            }
            this.c = str;
            QkUwRDExN0FDRjk4NDA3N0FGMzM1MjgyNEYwMEY1MTE(new WeakReference(this), this.b, this.c);
            if (context != null) {
                a(context);
                m = context;
                c(context);
                b();
                c(context, n);
            }
        }
        dVar = new d(this, looperMyLooper);
        this.f48a = dVar;
        if (context != null) {
            b(context);
        }
        this.c = str;
        QkUwRDExN0FDRjk4NDA3N0FGMzM1MjgyNEYwMEY1MTE(new WeakReference(this), this.b, this.c);
        if (context != null) {
            a(context);
            m = context;
            c(context);
            b();
            c(context, n);
        }
    }

    private void b(String str) {
        MUFFNjE5NDdDNTU1NEEzMDgzOURDQzRDQ0Y3QTU1QjI(a(m, str));
    }

    private void c(Context context) {
        a(context);
        MTkyN0UzQjNGRTA0NEUyM0FDNjkyRkQ1OENBMkU3QTY(n);
    }

    private void c(Context context, String str) {
        MzRGMjVDMDgzNzI1NDZFMzg4MDgzMDdFOEU5ODRFMjM(context, str, DrmAgent.useAndroidXDependency());
    }

    private void c(String str) {
        OTVGMkRCRTFFNTY1NDVBNTg3QzI1NjNCRTFFNDlGOTg(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        VOUtils.a aVar = VOUtils.a.UNKNOWN;
        boolean z = true;
        try {
            VOUtils.a networkType = VOUtils.getNetworkType(m);
            if (VOUtils.a.UNKNOWN == networkType) {
                return true;
            }
            int i = a.f49a[this.l.ordinal()];
            if (i != 2) {
                if (i != 3) {
                    if (i == 4 && VOUtils.a.WIFI != networkType && VOUtils.a.ETHERNET != networkType) {
                        z = false;
                    }
                } else if (VOUtils.a.ETHERNET != networkType) {
                    z = false;
                }
            } else if (VOUtils.a.WIFI != networkType) {
                z = false;
            }
        } catch (Exception unused) {
        }
        return z;
    }

    private void d(String str) {
        OEQwN0I2RTkyQTNFNEU4QUI1OUU5RkMzQTgzRTYxQzQ(str);
    }

    private void e(String str) {
        ODBBNzAxNDg2MTc0NDc0Nzk2QjQ3QzczRTBFQzZGMEU(str);
    }

    private void f(String str) {
        QzU0NzBCRTZBMDVGNEU2RjlGRDA5MURDMTlGRTI2NTg(str);
    }

    private void g(String str) {
        M0UwMjlFRUY3RjVDNDk1OTg2Q0ZDMkEwRjUyMzUwMDQ(str);
    }

    public static String[] getDownloadedURLs(String str) {
        if (str != null) {
            return JarSoAlignedMkQ1MjYzQjJCRkUzNDY2RUJEREJGMDcxQTU1QzUzQjE090210(str);
        }
        throw new IllegalArgumentException("rootPath == null");
    }

    public static String getUniqueIdentifier(Context context) throws IllegalStateException, SecurityException {
        try {
            return VOPlayer.getUniqueIdentifier(context);
        } catch (VOException unused) {
            return null;
        }
    }

    private static void m2yugdva7tpoe4A1(Object obj, int i, int i2, int i3, Object obj2) {
        d dVar;
        VODownloader vODownloader = (VODownloader) ((WeakReference) obj).get();
        if (vODownloader == null || (dVar = vODownloader.f48a) == null) {
            return;
        }
        vODownloader.f48a.sendMessage(dVar.obtainMessage(i, i2, i3, obj2));
    }

    public static void setMaximumRunningDownloadAllowed(int i) {
        NTM1ODhCQTRFN0Q5NDYzRDhBQjhBQjkwMzRDQ0IyMzMK(i);
    }

    public void asyncPrepare() {
        if (this.g == null || this.h == null || this.i == null) {
            throw new IllegalStateException("listener had not set");
        }
        if (VOPlayer.DRMAgentVersion.VERSION_1 == VOPlayer.getDRMAgentVersion()) {
            e eVar = this.f;
            if (eVar != null) {
                if (eVar.b() != null) {
                    e(this.f.b());
                }
                if (this.f.a() != null) {
                    c(this.f.a());
                }
                if (this.f.c() != null) {
                    d(this.f.c());
                }
                if (this.f.d() != null) {
                    b(this.f.d());
                }
            }
        } else {
            QTBFNDM2QUNDMDkwNDg2QkE3M0JEQzUxMEZGRTVDMzA(false);
        }
        VerimatrixAgent verimatrixAgent = this.e;
        if (verimatrixAgent != null) {
            f(verimatrixAgent.a());
            g(this.e.b());
        }
        NkNCNTcxNTk0NjcyNDYwNDlEQkM4QzMxMzM2MDExODQ();
    }

    public void deleteData() {
        Q0QzRDRBMDVCNzBGNDU4Mzg2NEVBQjQ5RDQzOTE0RDg();
    }

    protected void finalize() throws Throwable {
        a();
        super.finalize();
    }

    public VOPlayreadyAgent getAgentPlayready() {
        if (VOPlayer.DRMAgentVersion.VERSION_1 != VOPlayer.getDRMAgentVersion()) {
            return null;
        }
        if (this.f == null) {
            this.f = new e(m);
        }
        return this.f;
    }

    public VOViaccessAgent getAgentVOdrm() {
        return null;
    }

    public VOVerimatrixAgent getAgentVerimatrix() {
        VerimatrixAgent verimatrixAgentA = VerimatrixAgent.a(m);
        this.e = verimatrixAgentA;
        DrmAgent.EDrmType eDrmType = DrmAgent.EDrmType.DRM_TYPE_VERIMATRIX;
        return verimatrixAgentA;
    }

    public VOWidevineAgent getAgentWidevine() {
        return null;
    }

    public AudioTrackInfo[] getAudioTracks() {
        AudioTrackInfo[] audioTrackInfoArr = (AudioTrackInfo[]) NTY4QkIzNzZEMTAxNEYzNjhCN0Y1MDkwMDExQ0EyQUY();
        if (audioTrackInfoArr != null) {
            for (int i = 0; i < audioTrackInfoArr.length; i++) {
                int iA = a(audioTrackInfoArr[i].name);
                if (iA < 0) {
                    iA = 0;
                }
                audioTrackInfoArr[i].downloadedDuration = iA;
            }
        }
        return audioTrackInfoArr;
    }

    public int getBitrateByIndex(int i) {
        if (i >= 0) {
            return RUQzNkZEQzUwMTA2NDhEODlGNzA5MEU5OTFDM0U3NTk(i);
        }
        throw new IllegalArgumentException("index < 0");
    }

    public int getCurrentBitrate() {
        return MzY0MzY2M0U2RTExNEIwNUIyMTA0NTJDMjI3N0FCOUU();
    }

    public int getDownloadedDuration() throws IllegalStateException {
        return QTM2NjRBMkE3Rjg2NDhEQTgxMEQ0ODYyNTQ0RDEzRTU();
    }

    public int getDuration() {
        return NkRGRDcyRjI1Q0E2NDM0QTgxNTFCOTVBQTAzRDg0QTc();
    }

    public String getLocalServerUrl() {
        return QUU4ODExRTI2MEQxNEZDQTg5NTI2Q0ZGNzY4MDY3ODU();
    }

    public String getMediaId() {
        return this.c;
    }

    public int getNbQualities() {
        return QUVCQkMwQkRGOUZENEYwNTk0NjkzODQ5NTY3MzYyRkM();
    }

    public int getOfflineKeysExpiration() {
        return MjNDOTY0ODk3MkVDNENDQjgzOTEwRjNBNEQzNzJFMUU();
    }

    public int[] getResolutionByIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index < 0");
        }
        int[] iArrQzlCNjI1QkFFOUQ4NEZGQzk1MTZDNTg3Q0FEQ0UwOEM = QzlCNjI1QkFFOUQ4NEZGQzk1MTZDNTg3Q0FEQ0UwOEM(i);
        if (iArrQzlCNjI1QkFFOUQ4NEZGQzk1MTZDNTg3Q0FEQ0UwOEM == null) {
            throw new IllegalStateException("getResolutionByIndex returned null");
        }
        if (iArrQzlCNjI1QkFFOUQ4NEZGQzk1MTZDNTg3Q0FEQ0UwOEM.length == 2) {
            return iArrQzlCNjI1QkFFOUQ4NEZGQzk1MTZDNTg3Q0FEQ0UwOEM;
        }
        throw new IllegalStateException("getResolutionByIndex returned an array's length different than 2");
    }

    public SubtitleTrackInfo[] getSubtitleTracks() {
        return (SubtitleTrackInfo[]) MzE1RUQzQjZDRUNENDM0N0FBNzk2RURDQTNDQTdBNDA();
    }

    public void ignoreAllowCacheFlag(boolean z) {
        MDEyNDI1RTREM0ExNDU1MDhBMUEzQjA5Qjk4MzMxM0E(z);
    }

    public void renewOfflineKeys() {
        RDU4NTM0NjQ0RkQxNDNGM0JEREZDMDQ1QUE3MjgzMTU();
    }

    public void reset() {
        String str;
        if (this.i == null || this.h == null || this.g == null) {
            throw new IllegalStateException("listener had not set");
        }
        b bVar = this.j;
        if (bVar != null) {
            bVar.cancel();
            this.j = null;
        }
        Context context = m;
        if (context != null) {
            b(context);
        }
        Context context2 = m;
        if (context2 != null && (str = n) != null) {
            c(context2, str);
        }
        String str2 = this.b;
        if (str2 != null) {
            NTVDMzJENDIzMUExNEExMkI3Q0NDM0QzMjdCMDIzMDQ(str2);
        }
        String str3 = n;
        if (str3 != null) {
            MTkyN0UzQjNGRTA0NEUyM0FDNjkyRkQ1OENBMkU3QTY(str3);
        }
        MDQ0RkZDM0FEREI4NDg2Rjk3NzIxQ0YzNkUwREIwQ0Q();
        this.f48a.removeCallbacksAndMessages(null);
        this.f = null;
        this.d = false;
        this.k = false;
    }

    public void selectQuality(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("bitrate < 0");
        }
        QzlDNjQ4RUREQTQ2NDVBOEE0OTA0OURCQUFCOTg0RDA(i);
    }

    public void selectQualityByIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index < 0");
        }
        try {
            QzlDNjQ4RUREQTQ2NDVBOEE0OTA0OURCQUFCOTg0RDAByIndex(i);
        } catch (Exception unused) {
        }
    }

    public void setAESOfflineKeysExpirationTime(int i) {
        QjRGNjcxMTA3ODJFNEQ4QkE2OTVGNTU2NzQxNDJBNDM(i);
    }

    public void setAudioTracks(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException("invalid parameter");
        }
        for (String str : strArr) {
        }
        MzIwRkIzNDYxNzAwNDM4OTg4QkNENzQyNzIyODYwNDk(strArr);
    }

    public void setCACertificate(String str, int i) {
        N0NGN0REMjI0N0JCNDlDNEE3QTI4MTY2RTQ5ODNGREI(str, i);
    }

    public void setClientCertificate(String str, int i, String str2, int i2) {
        NzBFNzVGQzlENjE3NDY0RkE1NTlCRDVEODZGNDA0ODQ(str, i, str2, i2);
    }

    public void setCookie(String str) {
        NjRBRkU1RDc0RkRGNDg1NUEyRjkwQTdFNzNBQUNEM0I(str);
    }

    public void setDataSource(String str) {
        String str2;
        if (str == null) {
            throw new IllegalArgumentException("uri == null");
        }
        str.contains(" ");
        String str3 = n;
        if (str3 != null) {
            MTkyN0UzQjNGRTA0NEUyM0FDNjkyRkQ1OENBMkU3QTY(str3);
        }
        Context context = m;
        if (context != null && (str2 = n) != null) {
            c(context, str2);
        }
        String str4 = this.b;
        if (str4 != null) {
            NTVDMzJENDIzMUExNEExMkI3Q0NDM0QzMjdCMDIzMDQ(str4);
        }
        RjQwRDgxNUMyMzVCNDM4Qzk0QTIxM0QwNkVDOEY3NkM(str);
    }

    public void setDigestAuthentication(String str, String str2) {
        NjlBMDdDRUQ0QjQ1NEQ2NThCMjVBNjVDQjk1RUE1MkU(str, str2);
    }

    public void setDownloadNetworkConstraint(DownloadNetworkConstraint downloadNetworkConstraint) {
        this.l = downloadNetworkConstraint;
    }

    public void setDownloadRootPath(String str) throws VOException {
        if (str == null) {
            throw new IllegalArgumentException("uri == null");
        }
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file.canWrite()) {
                throw new VOException(VOStatusCode.ERROR_FILE_ACCESS, "Path uri is not accessible");
            }
            MkQ2Njc4QjgwM0JDNDlCNkIxNENFNEE0MTYyODZDOUQ(str);
        } catch (Exception unused) {
            throw new IllegalArgumentException("Path uri is not accessible");
        }
    }

    public void setHttpCustomHeader(String str) {
        QzRDNzVENTA5MDdBNDY3Qjg2NjMyQUM4NUJEMEQxMEY(str);
    }

    public void setLocalServerPort(int i) {
        Q0Q1Q0Y4MjZBMjg1NDgyMUE5MkMwMTZGNTcyRDk2Njg("http://127.0.0.1:" + i + "/");
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        if (onErrorListener == null) {
            throw new IllegalArgumentException("listener == null");
        }
        this.i = onErrorListener;
        ODM2OTY2NkI4M0VDNDBCRkFDQzQwQjJFMzc2RDRGMTA();
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        if (onInfoListener == null) {
            throw new IllegalArgumentException("listener == null");
        }
        this.h = onInfoListener;
        MDk4MjY1MDYzQzU1NDBGNDgzQ0I1Q0MzNzM0MDEzNzI();
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        if (onPreparedListener == null) {
            throw new IllegalArgumentException("listener == null");
        }
        this.g = onPreparedListener;
        RUUzM0NBNTY5QjMxNDRGNkJDNkRBNTgwOTFGQTAzRDA();
    }

    public void setProxy(String str, int i, int i2) {
        setProxy(str, i, i2, null, null);
    }

    public void setProxy(String str, int i, int i2, String str2, String str3) {
        Mzc2MTcxQUQ3QUI1NEQ4Rjg0Q0IxRkRGMjAxNjg1QUE(str, i, i2, str2, str3);
    }

    public void setSubtitleTracks(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException("invalid parameter");
        }
        for (String str : strArr) {
        }
        NDQ5MjFENkFBNkE2NEE4OEI1MDIyRDAwNUY4OTdCODA(strArr);
    }

    public void start() throws RuntimeException {
        d dVar;
        if (!c() && (dVar = this.f48a) != null) {
            this.f48a.sendMessage(dVar.obtainMessage(200, DOWNLOADER_ERROR_DOWNLOAD_NETWORK_CONSTRAINT_NOT_VERIFIED, 0, null));
            return;
        }
        OERENkEwOTEwNUI4NDNGRThEQjIyNkE3ODJDNkRFMjI();
        this.d = true;
        this.k = false;
        if (DownloadNetworkConstraint.NONE == this.l || this.j != null) {
            return;
        }
        b bVar = new b(Long.MAX_VALUE, 1000);
        this.j = bVar;
        bVar.start();
    }

    public void stop() throws RuntimeException {
        b bVar = this.j;
        if (bVar != null) {
            bVar.cancel();
            this.j = null;
        }
        if (!this.k) {
            MEJBRjFDNEFENjk4NEFDQkJDODdDMzNERTQyMzkxRDA();
        }
        this.d = false;
        this.k = false;
    }
}
