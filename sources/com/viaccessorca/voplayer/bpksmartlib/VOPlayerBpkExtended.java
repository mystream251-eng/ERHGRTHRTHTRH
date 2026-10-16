package com.viaccessorca.voplayer.bpksmartlib;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tv.broadpeak.smartlib.SmartLib;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;
import tv.broadpeak.smartlib.session.streaming.StreamingSession;
import tv.broadpeak.smartlib.session.streaming.StreamingSessionOptions;
import tv.broadpeak.smartlib.session.streaming.StreamingSessionResult;

/* JADX INFO: loaded from: classes2.dex */
public class VOPlayerBpkExtended extends VOPlayer {
    public static final int MEDIA_INFO_SMARTLIB = 25000;
    public static final int MEDIA_INFO_SMARTLIB_GET_URL_DONE = 25002;
    public static final int MEDIA_INFO_SMARTLIB_GET_URL_ERROR = 25001;
    public static final int SMARTLIB_LOG_LEVEL_BASIC = 0;
    public static final int SMARTLIB_LOG_LEVEL_DISABLED = -1;
    public static final int SMARTLIB_LOG_LEVEL_VERBOSE = 1;
    private IMAIDetectionState a2;
    private String b2;
    private StreamingSession c2;
    private StreamingSessionOptions d2;
    private boolean e2;
    private boolean f2;
    private d g2;
    private c h2;
    private boolean i2;
    private String j2;
    private String k2;
    private String l2;
    private CopyOnWriteArraySet<VOPlayer.OnInformationListener> m2;

    public enum BpkStreamingSessionOptions {
        SMARTLIB_MULTICAST_ONLY,
        SMARTLIB_ULTRA_LOW_LATENCY_SUPPORT,
        SMARTLIB_PIP_SESSION,
        SMARTLIB_REQUEST_LOCAL_NANO_CDN_IF_CONNECTED_TO_WIFI
    }

    public enum BpkStreamingSessionValues {
        SMARTLIB_SESSION_DISABLED,
        SMARTLIB_SESSION_ENABLED
    }

    private enum IMAIDetectionState {
        INIT,
        INVENTORY_MANAGER_DETECTED,
        PREPARE_ASYNC_REQUESTED,
        SSAI_VMAP_URL_PARSED,
        PREPARE_DONE
    }

    class a implements VOPlayer.OnInformationListener {

        /* JADX INFO: renamed from: com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$a$a, reason: collision with other inner class name */
        class C0186a extends Thread {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Object f120a;

            C0186a(a aVar, Object obj) {
                this.f120a = obj;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                VOUtils.sendHTTPRequest((String) this.f120a, VOUtils.HTTP_GET, null, null, false);
            }
        }

        a() {
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0043  */
        @Override // com.viaccessorca.voplayer.VOPlayer.OnInformationListener
        public boolean onInformation(VOPlayer vOPlayer, int i, int i2, Object obj) {
            boolean z;
            if (i == 1000 && i2 == 1018 && obj != null) {
                try {
                    URL url = new URL((String) obj);
                    String host = url.getHost();
                    if (host == null || !(host.contains("smartlib.broadpeak.tv") || host.contains("smartadserver.com"))) {
                        z = true;
                    } else {
                        String str = "DASH EVENT for Smart ou SmartLib: " + url;
                        new C0186a(this, obj).start();
                        z = false;
                    }
                } catch (MalformedURLException unused) {
                }
            } else {
                z = true;
            }
            if (VOPlayerBpkExtended.this.m2 != null && z) {
                Iterator it = VOPlayerBpkExtended.this.m2.iterator();
                while (it.hasNext()) {
                    ((VOPlayer.OnInformationListener) it.next()).onInformation(VOPlayerBpkExtended.this, i, i2, obj);
                }
            }
            return true;
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f121a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[BpkStreamingSessionOptions.values().length];
            b = iArr;
            try {
                iArr[BpkStreamingSessionOptions.SMARTLIB_PIP_SESSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[BpkStreamingSessionOptions.SMARTLIB_MULTICAST_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[BpkStreamingSessionOptions.SMARTLIB_ULTRA_LOW_LATENCY_SUPPORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[BpkStreamingSessionOptions.SMARTLIB_REQUEST_LOCAL_NANO_CDN_IF_CONNECTED_TO_WIFI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[IMAIDetectionState.values().length];
            f121a = iArr2;
            try {
                iArr2[IMAIDetectionState.INVENTORY_MANAGER_DETECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f121a[IMAIDetectionState.PREPARE_ASYNC_REQUESTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f121a[IMAIDetectionState.PREPARE_DONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object obj;
            String str = "handleMessage " + message;
            if (true == VOPlayerBpkExtended.this.i2) {
                return;
            }
            int i = message.what;
            if (i != 25002) {
                if (i != 25001 || (obj = message.obj) == null) {
                    return;
                }
                StreamingSessionResult streamingSessionResult = (StreamingSessionResult) obj;
                streamingSessionResult.getErrorMessage();
                if (VOPlayerBpkExtended.this.m2 != null) {
                    Iterator it = VOPlayerBpkExtended.this.m2.iterator();
                    while (it.hasNext()) {
                        ((VOPlayer.OnInformationListener) it.next()).onInformation(VOPlayerBpkExtended.this, 25000, VOPlayerBpkExtended.MEDIA_INFO_SMARTLIB_GET_URL_ERROR, streamingSessionResult);
                    }
                    return;
                }
                return;
            }
            Bundle data = message.getData();
            if (data.getString(ImagesContract.URL) != null && !data.getString(ImagesContract.URL).equals("")) {
                try {
                    VOPlayerBpkExtended.this.d(data.getString(ImagesContract.URL));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (VOPlayerBpkExtended.this.m2 != null) {
                    Iterator it2 = VOPlayerBpkExtended.this.m2.iterator();
                    while (it2.hasNext()) {
                        ((VOPlayer.OnInformationListener) it2.next()).onInformation(VOPlayerBpkExtended.this, 25000, VOPlayerBpkExtended.MEDIA_INFO_SMARTLIB_GET_URL_DONE, null);
                    }
                }
            }
            if (VOPlayerBpkExtended.this.e2 && data.getString(ImagesContract.URL) != null) {
                VOPlayerBpkExtended.this.e2 = false;
                VOPlayerBpkExtended.this.e();
                VOPlayerBpkExtended.this.a2 = IMAIDetectionState.PREPARE_DONE;
            }
            VOPlayerBpkExtended.this.f2 = false;
        }
    }

    private class d extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f123a = null;
        private String b;

        protected d(String str) {
            this.b = null;
            this.b = str;
        }

        protected String a() {
            return this.b;
        }

        /* JADX WARN: Code duplicated, block: B:40:0x0136 A[Catch: Exception -> 0x0222, TryCatch #1 {Exception -> 0x0222, blocks: (B:3:0x0017, B:11:0x0033, B:13:0x0039, B:15:0x0045, B:16:0x006a, B:18:0x0093, B:17:0x006d, B:19:0x0096, B:22:0x00b7, B:24:0x00bf, B:26:0x00c7, B:28:0x00cf, B:29:0x00e5, B:33:0x0109, B:34:0x010b, B:36:0x0113, B:38:0x011b, B:39:0x0131, B:43:0x0157, B:44:0x0159, B:45:0x0163, B:71:0x0219, B:72:0x021c, B:40:0x0136, B:42:0x013e, B:30:0x00ea, B:32:0x00f2, B:46:0x0169, B:48:0x016d, B:50:0x0175, B:52:0x017d, B:54:0x0185, B:55:0x019b, B:59:0x01bf, B:60:0x01c1, B:62:0x01c9, B:64:0x01d1, B:65:0x01e7, B:69:0x020b, B:70:0x020d, B:66:0x01ec, B:68:0x01f4, B:56:0x01a0, B:58:0x01a8), top: B:79:0x0017 }] */
        /* JADX WARN: Code duplicated, block: B:42:0x013e A[Catch: Exception -> 0x0222, TryCatch #1 {Exception -> 0x0222, blocks: (B:3:0x0017, B:11:0x0033, B:13:0x0039, B:15:0x0045, B:16:0x006a, B:18:0x0093, B:17:0x006d, B:19:0x0096, B:22:0x00b7, B:24:0x00bf, B:26:0x00c7, B:28:0x00cf, B:29:0x00e5, B:33:0x0109, B:34:0x010b, B:36:0x0113, B:38:0x011b, B:39:0x0131, B:43:0x0157, B:44:0x0159, B:45:0x0163, B:71:0x0219, B:72:0x021c, B:40:0x0136, B:42:0x013e, B:30:0x00ea, B:32:0x00f2, B:46:0x0169, B:48:0x016d, B:50:0x0175, B:52:0x017d, B:54:0x0185, B:55:0x019b, B:59:0x01bf, B:60:0x01c1, B:62:0x01c9, B:64:0x01d1, B:65:0x01e7, B:69:0x020b, B:70:0x020d, B:66:0x01ec, B:68:0x01f4, B:56:0x01a0, B:58:0x01a8), top: B:79:0x0017 }] */
        /* JADX WARN: Code duplicated, block: B:66:0x01ec A[Catch: Exception -> 0x0222, TryCatch #1 {Exception -> 0x0222, blocks: (B:3:0x0017, B:11:0x0033, B:13:0x0039, B:15:0x0045, B:16:0x006a, B:18:0x0093, B:17:0x006d, B:19:0x0096, B:22:0x00b7, B:24:0x00bf, B:26:0x00c7, B:28:0x00cf, B:29:0x00e5, B:33:0x0109, B:34:0x010b, B:36:0x0113, B:38:0x011b, B:39:0x0131, B:43:0x0157, B:44:0x0159, B:45:0x0163, B:71:0x0219, B:72:0x021c, B:40:0x0136, B:42:0x013e, B:30:0x00ea, B:32:0x00f2, B:46:0x0169, B:48:0x016d, B:50:0x0175, B:52:0x017d, B:54:0x0185, B:55:0x019b, B:59:0x01bf, B:60:0x01c1, B:62:0x01c9, B:64:0x01d1, B:65:0x01e7, B:69:0x020b, B:70:0x020d, B:66:0x01ec, B:68:0x01f4, B:56:0x01a0, B:58:0x01a8), top: B:79:0x0017 }] */
        /* JADX WARN: Code duplicated, block: B:68:0x01f4 A[Catch: Exception -> 0x0222, TryCatch #1 {Exception -> 0x0222, blocks: (B:3:0x0017, B:11:0x0033, B:13:0x0039, B:15:0x0045, B:16:0x006a, B:18:0x0093, B:17:0x006d, B:19:0x0096, B:22:0x00b7, B:24:0x00bf, B:26:0x00c7, B:28:0x00cf, B:29:0x00e5, B:33:0x0109, B:34:0x010b, B:36:0x0113, B:38:0x011b, B:39:0x0131, B:43:0x0157, B:44:0x0159, B:45:0x0163, B:71:0x0219, B:72:0x021c, B:40:0x0136, B:42:0x013e, B:30:0x00ea, B:32:0x00f2, B:46:0x0169, B:48:0x016d, B:50:0x0175, B:52:0x017d, B:54:0x0185, B:55:0x019b, B:59:0x01bf, B:60:0x01c1, B:62:0x01c9, B:64:0x01d1, B:65:0x01e7, B:69:0x020b, B:70:0x020d, B:66:0x01ec, B:68:0x01f4, B:56:0x01a0, B:58:0x01a8), top: B:79:0x0017 }] */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            StreamingSessionResult url;
            StringBuilder sb;
            StringBuilder sb2;
            VOPlayerBpkExtended vOPlayerBpkExtended;
            StringBuilder sb3;
            StringBuilder sb4;
            String str = "GetURLFromSmartLibThread - run, mStreamingSession = " + VOPlayerBpkExtended.this.c2;
            try {
                if (VOPlayerBpkExtended.this.c2 != null) {
                    try {
                        url = VOPlayerBpkExtended.this.c2.getURL(this.b);
                    } catch (Exception unused) {
                        url = null;
                    }
                    if (url == null || url.isError()) {
                        Message messageObtainMessage = VOPlayerBpkExtended.this.h2.obtainMessage();
                        messageObtainMessage.what = VOPlayerBpkExtended.MEDIA_INFO_SMARTLIB_GET_URL_ERROR;
                        messageObtainMessage.obj = url;
                        VOPlayerBpkExtended.this.h2.sendMessage(messageObtainMessage);
                        VOPlayerBpkExtended.this.c2.stopStreamingSession();
                        VOPlayerBpkExtended.this.c2 = null;
                    } else {
                        String url2 = url.getURL();
                        this.f123a = url2;
                        if (url2.equals("")) {
                            Message messageObtainMessage2 = VOPlayerBpkExtended.this.h2.obtainMessage();
                            messageObtainMessage2.what = VOPlayerBpkExtended.MEDIA_INFO_SMARTLIB_GET_URL_ERROR;
                            messageObtainMessage2.obj = url;
                            VOPlayerBpkExtended.this.h2.sendMessage(messageObtainMessage2);
                            VOPlayerBpkExtended.this.c2.stopStreamingSession();
                            VOPlayerBpkExtended.this.c2 = null;
                        }
                    }
                    VOPlayerBpkExtended.this.d2 = null;
                }
                Message messageObtainMessage3 = VOPlayerBpkExtended.this.h2.obtainMessage();
                messageObtainMessage3.what = VOPlayerBpkExtended.MEDIA_INFO_SMARTLIB_GET_URL_DONE;
                Bundle bundle = new Bundle();
                if (this.f123a == null || this.f123a.equals("")) {
                    if (this.b != null && !this.b.equals("")) {
                        if (VOPlayerBpkExtended.this.k2 == null || !this.b.contains("?")) {
                            if (VOPlayerBpkExtended.this.k2 != null) {
                                sb = new StringBuilder();
                                sb.append(this.b);
                                sb.append("?vovasturl=");
                                sb.append(VOPlayerBpkExtended.this.k2);
                            }
                            if (VOPlayerBpkExtended.this.l2 == null && this.b.contains("?")) {
                                sb2 = new StringBuilder();
                                sb2.append(this.b);
                                sb2.append("&vovmapurl=");
                                sb2.append(VOPlayerBpkExtended.this.l2);
                            } else {
                                if (VOPlayerBpkExtended.this.l2 != null) {
                                    sb2 = new StringBuilder();
                                    sb2.append(this.b);
                                    sb2.append("?vovasturl=");
                                    sb2.append(VOPlayerBpkExtended.this.k2);
                                }
                                bundle.putString(ImagesContract.URL, this.b);
                                messageObtainMessage3.setData(bundle);
                                vOPlayerBpkExtended = VOPlayerBpkExtended.this;
                            }
                            this.b = sb2.toString();
                            bundle.putString(ImagesContract.URL, this.b);
                            messageObtainMessage3.setData(bundle);
                            vOPlayerBpkExtended = VOPlayerBpkExtended.this;
                        } else {
                            sb = new StringBuilder();
                            sb.append(this.b);
                            sb.append("&vovasturl=");
                            sb.append(VOPlayerBpkExtended.this.k2);
                        }
                        this.b = sb.toString();
                        if (VOPlayerBpkExtended.this.l2 == null) {
                            if (VOPlayerBpkExtended.this.l2 != null) {
                                sb2 = new StringBuilder();
                                sb2.append(this.b);
                                sb2.append("?vovasturl=");
                                sb2.append(VOPlayerBpkExtended.this.k2);
                                this.b = sb2.toString();
                            }
                        } else if (VOPlayerBpkExtended.this.l2 != null) {
                            sb2 = new StringBuilder();
                            sb2.append(this.b);
                            sb2.append("?vovasturl=");
                            sb2.append(VOPlayerBpkExtended.this.k2);
                            this.b = sb2.toString();
                        }
                        bundle.putString(ImagesContract.URL, this.b);
                        messageObtainMessage3.setData(bundle);
                        vOPlayerBpkExtended = VOPlayerBpkExtended.this;
                    }
                    VOPlayerBpkExtended.this.g2 = null;
                }
                if (VOPlayerBpkExtended.this.k2 == null || !this.f123a.contains("?")) {
                    if (VOPlayerBpkExtended.this.k2 != null) {
                        sb3 = new StringBuilder();
                        sb3.append(this.f123a);
                        sb3.append("?vovasturl=");
                        sb3.append(VOPlayerBpkExtended.this.k2);
                    }
                    if (VOPlayerBpkExtended.this.l2 == null && this.b.contains("?")) {
                        sb4 = new StringBuilder();
                        sb4.append(this.f123a);
                        sb4.append("&vovmapurl=");
                        sb4.append(VOPlayerBpkExtended.this.l2);
                    } else {
                        if (VOPlayerBpkExtended.this.l2 != null) {
                            sb4 = new StringBuilder();
                            sb4.append(this.f123a);
                            sb4.append("?vovmapurl=");
                            sb4.append(VOPlayerBpkExtended.this.k2);
                        }
                        bundle.putString(ImagesContract.URL, this.f123a);
                        messageObtainMessage3.setData(bundle);
                        vOPlayerBpkExtended = VOPlayerBpkExtended.this;
                    }
                    this.f123a = sb4.toString();
                    bundle.putString(ImagesContract.URL, this.f123a);
                    messageObtainMessage3.setData(bundle);
                    vOPlayerBpkExtended = VOPlayerBpkExtended.this;
                } else {
                    sb3 = new StringBuilder();
                    sb3.append(this.f123a);
                    sb3.append("&vovasturl=");
                    sb3.append(VOPlayerBpkExtended.this.k2);
                }
                this.f123a = sb3.toString();
                if (VOPlayerBpkExtended.this.l2 == null) {
                    if (VOPlayerBpkExtended.this.l2 != null) {
                        sb4 = new StringBuilder();
                        sb4.append(this.f123a);
                        sb4.append("?vovmapurl=");
                        sb4.append(VOPlayerBpkExtended.this.k2);
                        this.f123a = sb4.toString();
                    }
                } else if (VOPlayerBpkExtended.this.l2 != null) {
                    sb4 = new StringBuilder();
                    sb4.append(this.f123a);
                    sb4.append("?vovmapurl=");
                    sb4.append(VOPlayerBpkExtended.this.k2);
                    this.f123a = sb4.toString();
                }
                bundle.putString(ImagesContract.URL, this.f123a);
                messageObtainMessage3.setData(bundle);
                vOPlayerBpkExtended = VOPlayerBpkExtended.this;
                vOPlayerBpkExtended.h2.sendMessage(messageObtainMessage3);
                VOPlayerBpkExtended.this.g2 = null;
            } catch (Exception e) {
                e.getMessage();
                VOPlayerBpkExtended.this.g2 = null;
                VOPlayerBpkExtended.this.f2 = false;
            }
        }
    }

    public VOPlayerBpkExtended(Context context) {
        super(context);
        this.a2 = IMAIDetectionState.INIT;
        this.b2 = null;
        this.c2 = null;
        this.d2 = null;
        this.e2 = false;
        this.f2 = false;
        this.g2 = null;
        this.i2 = false;
        this.j2 = null;
        this.k2 = null;
        this.l2 = null;
        setSmartLibDetected(true);
        this.m2 = new CopyOnWriteArraySet<>();
        this.h2 = new c(Looper.getMainLooper());
        super.setOnInformationListener(new a());
        this.a2 = IMAIDetectionState.INIT;
        if (CoreEngine.getInstance().isInitialized()) {
            return;
        }
        Thread.dumpStack();
    }

    private boolean a(String str) {
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0045  */
    /* JADX WARN: Code duplicated, block: B:16:0x0048 A[Catch: Exception -> 0x006a, TryCatch #1 {Exception -> 0x006a, blocks: (B:11:0x0035, B:13:0x003b, B:16:0x0048, B:18:0x0054), top: B:30:0x0035 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x0054 A[Catch: Exception -> 0x006a, TRY_LEAVE, TryCatch #1 {Exception -> 0x006a, blocks: (B:11:0x0035, B:13:0x003b, B:16:0x0048, B:18:0x0054), top: B:30:0x0035 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x0060 A[Catch: Exception -> 0x006b, TRY_LEAVE, TryCatch #0 {Exception -> 0x006b, blocks: (B:3:0x0002, B:5:0x0012, B:8:0x0023, B:10:0x002f, B:19:0x005a, B:21:0x0060), top: B:28:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:23:0x006a A[PHI: r3
  0x006a: PHI (r3v3 java.lang.String) = (r3v2 java.lang.String), (r3v4 java.lang.String), (r3v4 java.lang.String) binds: [B:26:0x006a, B:15:0x0046, B:17:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    private String b(String str) throws IOException {
        String strReplaceFirst;
        try {
            Matcher matcher = Pattern.compile("&?vovmapurl=([^&\\s]+)|&?vovasturl=([^&\\s]+)").matcher(str);
            if (matcher.find()) {
                String strGroup = matcher.group(1);
                String strGroup2 = matcher.group(2);
                if (strGroup != null) {
                    this.l2 = strGroup;
                    if (a(URLDecoder.decode(strGroup, "UTF-8"))) {
                        strReplaceFirst = str.replaceFirst("&?vovmapurl=[^&\\s]+", "");
                        try {
                            if (strReplaceFirst.endsWith("?")) {
                                strReplaceFirst = strReplaceFirst.substring(0, strReplaceFirst.length() - 1);
                            }
                        } catch (Exception unused) {
                        }
                    } else {
                        strReplaceFirst = str;
                    }
                    if (strGroup2 != null) {
                        this.k2 = strGroup2;
                        if (a(URLDecoder.decode(strGroup2, "UTF-8"))) {
                            str = str.replaceFirst("&?vovasturl=[^&\\s]+", "");
                            if (str.endsWith("?")) {
                                str = str.substring(0, str.length() - 1);
                            }
                        } else {
                            str = strReplaceFirst;
                        }
                    } else {
                        str = strReplaceFirst;
                    }
                } else {
                    strReplaceFirst = str;
                    if (strGroup2 != null) {
                        this.k2 = strGroup2;
                        if (a(URLDecoder.decode(strGroup2, "UTF-8"))) {
                            str = str.replaceFirst("&?vovasturl=[^&\\s]+", "");
                            if (str.endsWith("?")) {
                                str = str.substring(0, str.length() - 1);
                            }
                        } else {
                            str = strReplaceFirst;
                        }
                    } else {
                        str = strReplaceFirst;
                    }
                }
            }
        } catch (Exception unused2) {
        }
        return str.replace("?&", "?");
    }

    private void c(String str) {
        d dVar;
        d dVar2 = this.g2;
        if (dVar2 != null) {
            if (!str.equals(dVar2.a())) {
                this.g2.interrupt();
                dVar = new d(str);
            }
            this.f2 = true;
        }
        dVar = new d(str);
        this.g2 = dVar;
        dVar.start();
        this.f2 = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) throws IOException, IllegalArgumentException {
        try {
            super.setDataSource(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        super.prepareAsync();
    }

    public static void initSmartLib(Context context, String str, String str2, String str3) {
        String str4 = "initSmartLib " + context + ",analyticsAddress " + str + ",nanoCDNHost " + str2 + ",broadpeakDomainNames " + str3;
        setSmartLibLogLevel(-1);
        SmartLib.getInstance().init(context, str, str2, str3);
    }

    public static void releaseSmartLib() throws IllegalStateException {
        if (!CoreEngine.getInstance().isInitialized()) {
            throw new IllegalStateException("SmartLib was not initialized when released");
        }
        SmartLib.getInstance().release();
    }

    public static void setSmartLibLogLevel(int i) {
        LoggerManager.getInstance().setLogLevel(i);
    }

    public static void setSmartLibUUID(String str) {
        SmartLib.getInstance().setUUID(str);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void adSetRequest(String str, VOPlayer.AdRequestType adRequestType) throws IOException, IllegalArgumentException {
        a(str);
        super.adSetRequest(str, adRequestType);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    protected void finalize() {
        StreamingSession streamingSession;
        if (CoreEngine.getInstance().isInitialized() && (streamingSession = this.c2) != null) {
            streamingSession.stopStreamingSession();
            this.c2 = null;
        }
        this.d2 = null;
        this.b2 = null;
        super.finalize();
    }

    /* JADX WARN: Code duplicated, block: B:61:0x00b2  */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
    
        if (true == r4.f2) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0083, code lost:
    
        if (true == r4.f2) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a7, code lost:
    
        if (true == r4.f2) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a9, code lost:
    
        r4.e2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ac, code lost:
    
        super.prepareAsync();
        r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.IMAIDetectionState.PREPARE_DONE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:?, code lost:
    
        return;
     */
    @Override // com.viaccessorca.voplayer.VOPlayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void internalOnVASTEvent(int r5, java.lang.Object r6) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            r2 = 11010(0x2b02, float:1.5428E-41)
            if (r5 != r2) goto L5e
            if (r6 == 0) goto L38
            r5 = r6
            java.lang.String r5 = (java.lang.String) r5
            tv.broadpeak.smartlib.session.streaming.StreamingSession r5 = r4.c2     // Catch: java.lang.Exception -> L38
            if (r5 == 0) goto L38
            android.net.UrlQuerySanitizer r5 = new android.net.UrlQuerySanitizer     // Catch: java.lang.Exception -> L38
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Exception -> L38
            r5.<init>(r6)     // Catch: java.lang.Exception -> L38
            java.util.List r5 = r5.getParameterList()     // Catch: java.lang.Exception -> L38
            java.util.Iterator r5 = r5.iterator()     // Catch: java.lang.Exception -> L38
        L1e:
            boolean r6 = r5.hasNext()     // Catch: java.lang.Exception -> L38
            if (r6 == 0) goto L38
            java.lang.Object r6 = r5.next()     // Catch: java.lang.Exception -> L38
            android.net.UrlQuerySanitizer$ParameterValuePair r6 = (android.net.UrlQuerySanitizer.ParameterValuePair) r6     // Catch: java.lang.Exception -> L38
            java.lang.String r2 = r6.mParameter     // Catch: java.lang.Exception -> L38
            java.lang.String r2 = r6.mValue     // Catch: java.lang.Exception -> L38
            tv.broadpeak.smartlib.session.streaming.StreamingSession r2 = r4.c2     // Catch: java.lang.Exception -> L38
            java.lang.String r3 = r6.mParameter     // Catch: java.lang.Exception -> L38
            java.lang.String r6 = r6.mValue     // Catch: java.lang.Exception -> L38
            r2.setAdParameter(r3, r6)     // Catch: java.lang.Exception -> L38
            goto L1e
        L38:
            java.lang.String r5 = r4.b2
            if (r5 == 0) goto L46
            r4.c(r5)     // Catch: java.lang.Exception -> L42
            r4.b2 = r0     // Catch: java.lang.Exception -> L42
            goto L46
        L42:
            r5 = move-exception
            r5.printStackTrace()
        L46:
            int[] r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.b.f121a
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r6 = r4.a2
            int r6 = r6.ordinal()
            r5 = r5[r6]
            if (r5 == r1) goto L5b
            r6 = 2
            if (r5 == r6) goto L56
            goto Lb5
        L56:
            boolean r5 = r4.f2
            if (r1 != r5) goto Lac
            goto La9
        L5b:
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.IMAIDetectionState.SSAI_VMAP_URL_PARSED
            goto L66
        L5e:
            r6 = 11013(0x2b05, float:1.5432E-41)
            if (r6 != r5) goto L69
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.IMAIDetectionState.PREPARE_DONE
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.IMAIDetectionState.INVENTORY_MANAGER_DETECTED
        L66:
            r4.a2 = r5
            goto Lb5
        L69:
            r6 = 11011(0x2b03, float:1.543E-41)
            if (r6 != r5) goto L86
            java.lang.String r5 = r4.b2
            if (r5 == 0) goto L7b
            r4.c(r5)     // Catch: java.lang.Exception -> L77
            r4.b2 = r0     // Catch: java.lang.Exception -> L77
            goto L7b
        L77:
            r5 = move-exception
            r5.printStackTrace()
        L7b:
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.IMAIDetectionState.PREPARE_ASYNC_REQUESTED
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r6 = r4.a2
            if (r5 != r6) goto Lb2
            boolean r5 = r4.f2
            if (r1 != r5) goto Lac
            goto La9
        L86:
            r6 = 11012(0x2b04, float:1.5431E-41)
            if (r6 != r5) goto Lb5
            java.lang.String r5 = ""
            com.viaccessorca.voplayer.VOPlayer$AdRequestType r6 = com.viaccessorca.voplayer.VOPlayer.AdRequestType.AD_REQUEST_VMAP_URI     // Catch: java.io.IOException -> L91
            r4.adSetRequest(r5, r6)     // Catch: java.io.IOException -> L91
        L91:
            java.lang.String r5 = r4.b2
            if (r5 == 0) goto L9f
            r4.c(r5)     // Catch: java.lang.Exception -> L9b
            r4.b2 = r0     // Catch: java.lang.Exception -> L9b
            goto L9f
        L9b:
            r5 = move-exception
            r5.printStackTrace()
        L9f:
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.IMAIDetectionState.PREPARE_ASYNC_REQUESTED
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r6 = r4.a2
            if (r5 != r6) goto Lb2
            boolean r5 = r4.f2
            if (r1 != r5) goto Lac
        La9:
            r4.e2 = r1
            goto Lb5
        Lac:
            super.prepareAsync()
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.IMAIDetectionState.PREPARE_DONE
            goto L66
        Lb2:
            com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended$IMAIDetectionState r5 = com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.IMAIDetectionState.INIT
            goto L66
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended.internalOnVASTEvent(int, java.lang.Object):void");
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void prepareAsync() {
        IMAIDetectionState iMAIDetectionState;
        redirectStreamPlaybackIfRequired(this.j2);
        IMAIDetectionState iMAIDetectionState2 = this.a2;
        if (iMAIDetectionState2 != null && IMAIDetectionState.INVENTORY_MANAGER_DETECTED == iMAIDetectionState2) {
            iMAIDetectionState = IMAIDetectionState.PREPARE_ASYNC_REQUESTED;
        } else if (true == this.f2) {
            this.e2 = true;
            return;
        } else {
            super.prepareAsync();
            iMAIDetectionState = IMAIDetectionState.PREPARE_DONE;
        }
        this.a2 = iMAIDetectionState;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void release() {
        StreamingSession streamingSession;
        this.m2.clear();
        this.b2 = null;
        this.i2 = true;
        d dVar = this.g2;
        if (dVar != null) {
            dVar.interrupt();
            this.g2 = null;
        }
        if (CoreEngine.getInstance().isInitialized() && (streamingSession = this.c2) != null) {
            streamingSession.stopStreamingSession();
            this.c2 = null;
        }
        this.d2 = null;
        this.b2 = null;
        this.a2 = IMAIDetectionState.INIT;
        this.f2 = false;
        super.release();
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void removeOnInformationListener(VOPlayer.OnInformationListener onInformationListener) {
        this.m2.remove(onInformationListener);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void reset() {
        StreamingSession streamingSession;
        this.b2 = null;
        d dVar = this.g2;
        if (dVar != null) {
            dVar.interrupt();
            this.g2 = null;
        }
        if (CoreEngine.getInstance().isInitialized() && (streamingSession = this.c2) != null) {
            streamingSession.stopStreamingSession();
            this.c2 = null;
        }
        this.d2 = null;
        this.b2 = null;
        this.a2 = IMAIDetectionState.INIT;
        this.f2 = false;
        this.l2 = null;
        this.k2 = null;
        super.reset();
    }

    public void setBpkStreamingSessionOptions(Map<BpkStreamingSessionOptions, BpkStreamingSessionValues> map) throws IllegalStateException {
        StreamingSessionOptions streamingSessionOptions;
        String str = "setBpkStreamingSessionOptions " + map;
        if (this.c2 != null) {
            throw new IllegalStateException("setBpkStreamingSessionOptions called after setDataSource");
        }
        this.d2 = null;
        if (map != null) {
            this.d2 = StreamingSessionOptions.create();
            for (BpkStreamingSessionOptions bpkStreamingSessionOptions : map.keySet()) {
                boolean z = map.get(bpkStreamingSessionOptions) == BpkStreamingSessionValues.SMARTLIB_SESSION_ENABLED;
                int i = b.b[bpkStreamingSessionOptions.ordinal()];
                int i2 = 2;
                if (i == 1) {
                    streamingSessionOptions = this.d2;
                } else if (i == 2) {
                    this.d2.option(0, z);
                } else if (i == 3) {
                    this.d2.option(1, z);
                } else if (i == 4) {
                    streamingSessionOptions = this.d2;
                    i2 = 104;
                }
                streamingSessionOptions.option(i2, z);
            }
        }
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void setDataSource(Context context, Uri uri) throws IOException, SecurityException, IllegalArgumentException {
        String str = "setDataSource " + context + ", " + uri;
        this.j2 = uri.toString();
        if (this.d2 == null) {
            this.d2 = StreamingSessionOptions.create();
        }
        StreamingSession streamingSession = this.c2;
        if (streamingSession != null) {
            streamingSession.stopStreamingSession();
            this.c2 = null;
        }
        StreamingSession streamingSessionCreateStreamingSession = SmartLib.getInstance().createStreamingSession(this.d2);
        this.c2 = streamingSessionCreateStreamingSession;
        streamingSessionCreateStreamingSession.attachPlayer(this);
        Uri uri2 = Uri.parse(b(uri.toString()));
        String scheme = uri2.getScheme();
        if (!CoreEngine.getInstance().isInitialized() || scheme == null || scheme.equals("file") || scheme.equals("udp") || scheme.equals("rtp")) {
            super.setDataSource(context, uri2);
        } else if (IMAIDetectionState.INVENTORY_MANAGER_DETECTED == this.a2) {
            this.b2 = uri2.toString();
        } else {
            c(uri2.toString());
        }
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void setDataSource(String str) throws IOException, IllegalArgumentException {
        this.j2 = str;
        if (this.d2 == null) {
            this.d2 = StreamingSessionOptions.create();
        }
        StreamingSession streamingSession = this.c2;
        if (streamingSession != null) {
            streamingSession.stopStreamingSession();
            this.c2 = null;
        }
        StreamingSession streamingSessionCreateStreamingSession = SmartLib.getInstance().createStreamingSession(this.d2);
        this.c2 = streamingSessionCreateStreamingSession;
        streamingSessionCreateStreamingSession.attachPlayer(this);
        String strB = b(str);
        if (!CoreEngine.getInstance().isInitialized() || strB.contains("udp://") || strB.contains("rtp://")) {
            super.setDataSource(strB);
        } else if (IMAIDetectionState.INVENTORY_MANAGER_DETECTED == this.a2) {
            this.b2 = strB;
        } else {
            c(strB);
        }
    }

    @Deprecated
    public void setDataSourceWithSmartLib(Context context, Uri uri) throws IOException, SecurityException, IllegalArgumentException {
        String str = "setDataSourceWithSmartLib " + context + ", " + uri;
        if (!CoreEngine.getInstance().isInitialized()) {
            throw new IllegalStateException("SmartLib was not initialized when setDataSourceWithSmartLib");
        }
        String str2 = "setDataSourceWithSmartLib" + context + ", " + uri;
        String scheme = uri.getScheme();
        if (scheme == null || scheme.equals("file") || scheme.equals("udp") || scheme.equals("rtp")) {
            super.setDataSource(context, uri);
        } else {
            setDataSource(uri.toString());
        }
    }

    @Deprecated
    public void setDataSourceWithSmartLib(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        if (!CoreEngine.getInstance().isInitialized()) {
            throw new IllegalStateException("SmartLib was not initialized when setDataSourceWithSmartLib");
        }
        setDataSource(str);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void setOnInformationListener(VOPlayer.OnInformationListener onInformationListener) {
        Object[] objArr = new Object[1];
        this.m2.add(onInformationListener);
    }

    @Override // com.viaccessorca.voplayer.VOPlayer
    public void stop() {
        StreamingSession streamingSession;
        if (CoreEngine.getInstance().isInitialized() && (streamingSession = this.c2) != null) {
            streamingSession.stopStreamingSession();
            this.c2 = null;
        }
        this.d2 = null;
        this.b2 = null;
        this.f2 = false;
        super.stop();
    }
}
