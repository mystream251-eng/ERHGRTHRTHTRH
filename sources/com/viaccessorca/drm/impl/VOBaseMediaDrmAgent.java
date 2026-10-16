package com.viaccessorca.drm.impl;

import android.content.Context;
import android.media.MediaCrypto;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.drm.VOCommonDrmAgent;
import com.viaccessorca.drm.VOMediaDrmAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import com.viaccessorca.vodownloader.NanoHTTPD;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VOBaseMediaDrmAgent extends DrmAgent implements VOMediaDrmAgent, VOPlayer.OnInformationListener, VOPlayer.OnErrorListener, VOPlayer.OnPreparedListener {
    protected static int e0;
    private boolean A;
    private boolean B;
    protected OnMediaDrmAgentListener C;
    protected VOMediaDrmSession D;
    protected MediaDrm E;
    protected MediaCrypto F;
    protected MediaCrypto G;
    private boolean H;
    private c I;
    private i J;
    private String K;
    protected int L;
    protected long M;
    protected long N;
    protected VOMediaDrmAgent.EDrmLicenseAcquisitionType O;
    protected ArrayList<VOMediaDrmSession> P;
    protected ArrayList<VOMediaDrmSession> Q;
    protected Integer R;
    private ArrayList<com.viaccessorca.voplayer.a> S;
    protected int T;
    protected boolean U;
    protected int[] V;
    protected int[] W;
    protected Map<UUID, Integer> X;
    protected int Y;
    protected g Z;
    protected HandlerThread a0;
    protected d b0;
    protected e c0;
    protected f d0;
    protected UUID v;
    protected String w;
    protected JSONObject x;
    protected boolean y;
    protected boolean z;

    public interface OnMediaDrmAgentListener {
        void onDrmEvent(int i);
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f24a;

        static {
            int[] iArr = new int[c.values().length];
            f24a = iArr;
            try {
                iArr[c.ACQUIRE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f24a[c.DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f24a[c.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f24a[c.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private class b extends Thread {
        private b() {
        }

        /* synthetic */ b(VOBaseMediaDrmAgent vOBaseMediaDrmAgent, a aVar) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            VOBaseMediaDrmAgent.this.e();
        }
    }

    public enum c {
        ACQUIRE,
        DELETE,
        INFO,
        UNKNOWN
    }

    /* JADX INFO: Access modifiers changed from: private */
    class d extends Handler {
        protected d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            VOBaseMediaDrmAgent vOBaseMediaDrmAgent = VOBaseMediaDrmAgent.this;
            if (vOBaseMediaDrmAgent.y) {
                return;
            }
            Object obj = null;
            VOMediaDrmSession vOMediaDrmSession = (VOMediaDrmSession) message.obj;
            try {
                int i = message.what;
                if (i != 0) {
                    if (i != 1) {
                        throw new RuntimeException();
                    }
                    if (vOBaseMediaDrmAgent.Z != null) {
                        e = VOBaseMediaDrmAgent.this.Z.a(VOBaseMediaDrmAgent.this.v, vOMediaDrmSession.c());
                        obj = e;
                    }
                } else if (vOBaseMediaDrmAgent.Z != null) {
                    try {
                        e = VOBaseMediaDrmAgent.this.Z.a(VOBaseMediaDrmAgent.this.v, vOBaseMediaDrmAgent.E.getProvisionRequest());
                        obj = e;
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception e) {
                e = e;
            }
            if (vOMediaDrmSession != null) {
                int i2 = message.what;
                if (i2 == 0) {
                    vOMediaDrmSession.b(obj);
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    vOMediaDrmSession.a(obj);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class e extends Handler {
        protected e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            VOMediaDrmSession vOMediaDrmSession;
            VOBaseMediaDrmAgent vOBaseMediaDrmAgent = VOBaseMediaDrmAgent.this;
            if (vOBaseMediaDrmAgent.y) {
                return;
            }
            int i = message.what;
            if (i != 0) {
                if (i == 1 && (vOMediaDrmSession = vOBaseMediaDrmAgent.D) != null) {
                    vOMediaDrmSession.a(message.obj);
                    return;
                }
                return;
            }
            VOMediaDrmSession vOMediaDrmSession2 = vOBaseMediaDrmAgent.D;
            if (vOMediaDrmSession2 != null) {
                vOMediaDrmSession2.b(message.obj);
            }
        }
    }

    private class f implements MediaDrm.OnEventListener, VOMediaDrmSession.OnMediaDrmSessionListener {
        private f() {
        }

        /* synthetic */ f(VOBaseMediaDrmAgent vOBaseMediaDrmAgent, a aVar) {
            this();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001c  */
        /* JADX WARN: Code duplicated, block: B:25:0x0038  */
        private void a(int i) {
            int[] iArr;
            if (i != 0) {
                if (i == 1) {
                    iArr = VOBaseMediaDrmAgent.this.V;
                    if (iArr[0] == 0 && iArr[3] != 0) {
                        if (iArr[3] > 0) {
                            b(3);
                        }
                        if (VOBaseMediaDrmAgent.this.V[0] > 0) {
                            b(0);
                        }
                    }
                } else if (i == 3) {
                    b(i);
                    if (VOBaseMediaDrmAgent.this.V[0] > 0) {
                        b(0);
                    }
                } else if (i == 4 || i == 5) {
                    iArr = VOBaseMediaDrmAgent.this.V;
                    if (iArr[0] == 0) {
                    }
                }
                Arrays.fill(VOBaseMediaDrmAgent.this.V, 0);
                Arrays.fill(VOBaseMediaDrmAgent.this.W, 0);
            }
            if (VOBaseMediaDrmAgent.this.V[3] > 0) {
                b(3);
            }
            b(i);
            Arrays.fill(VOBaseMediaDrmAgent.this.V, 0);
            Arrays.fill(VOBaseMediaDrmAgent.this.W, 0);
        }

        private void b(int i) {
            OnMediaDrmAgentListener onMediaDrmAgentListener = VOBaseMediaDrmAgent.this.C;
            if (onMediaDrmAgentListener != null) {
                onMediaDrmAgentListener.onDrmEvent(i);
            }
            int[] iArr = VOBaseMediaDrmAgent.this.W;
            iArr[i] = iArr[i] + 1;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:16:0x002b A[Catch: all -> 0x008d, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0008, B:10:0x0012, B:12:0x001a, B:34:0x0088, B:13:0x0024, B:16:0x002b, B:18:0x0031, B:19:0x0034, B:21:0x003a, B:27:0x0046, B:28:0x0047, B:30:0x005c, B:31:0x0066, B:33:0x007e, B:22:0x003b, B:23:0x0042), top: B:42:0x0001, inners: #0 }] */
        /* JADX WARN: Code duplicated, block: B:34:0x0088 A[Catch: all -> 0x008d, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0008, B:10:0x0012, B:12:0x001a, B:34:0x0088, B:13:0x0024, B:16:0x002b, B:18:0x0031, B:19:0x0034, B:21:0x003a, B:27:0x0046, B:28:0x0047, B:30:0x005c, B:31:0x0066, B:33:0x007e, B:22:0x003b, B:23:0x0042), top: B:42:0x0001, inners: #0 }] */
        @Override // com.viaccessorca.drm.impl.VOMediaDrmSession.OnMediaDrmSessionListener
        public synchronized void onDrmEvent(int i, VOMediaDrmSession vOMediaDrmSession) {
            VOBaseMediaDrmAgent vOBaseMediaDrmAgent = VOBaseMediaDrmAgent.this;
            if (vOBaseMediaDrmAgent.y) {
                vOMediaDrmSession.a();
                return;
            }
            switch (i) {
                case 0:
                    vOBaseMediaDrmAgent.a(vOMediaDrmSession);
                    VOBaseMediaDrmAgent vOBaseMediaDrmAgent2 = VOBaseMediaDrmAgent.this;
                    Integer numValueOf = Integer.valueOf(vOBaseMediaDrmAgent2.R.intValue() - 1);
                    vOBaseMediaDrmAgent2.R = numValueOf;
                    if (numValueOf.intValue() > 0) {
                        int[] iArr = VOBaseMediaDrmAgent.this.V;
                        iArr[i] = iArr[i] + 1;
                    } else {
                        a(i);
                    }
                    return;
                case 1:
                case 4:
                case 5:
                    if (vOMediaDrmSession != null) {
                        vOMediaDrmSession.a();
                    }
                    ArrayList<VOMediaDrmSession> arrayList = VOBaseMediaDrmAgent.this.P;
                    if (arrayList != null) {
                        synchronized (arrayList) {
                            VOBaseMediaDrmAgent.this.P.remove(vOMediaDrmSession);
                            break;
                        }
                    }
                    VOBaseMediaDrmAgent vOBaseMediaDrmAgent3 = VOBaseMediaDrmAgent.this;
                    Integer numValueOf2 = Integer.valueOf(vOBaseMediaDrmAgent3.R.intValue() - 1);
                    vOBaseMediaDrmAgent3.R = numValueOf2;
                    if (numValueOf2.intValue() > 0) {
                        int[] iArr2 = VOBaseMediaDrmAgent.this.V;
                        iArr2[i] = iArr2[i] + 1;
                    } else {
                        a(i);
                    }
                    return;
                case 2:
                    if (vOBaseMediaDrmAgent.W[i] <= 0) {
                        b(i);
                    }
                    return;
                case 3:
                    if (vOBaseMediaDrmAgent.R.intValue() > 1) {
                        int[] iArr3 = VOBaseMediaDrmAgent.this.V;
                        iArr3[i] = iArr3[i] + 1;
                    } else {
                        a(i);
                    }
                    return;
                case 6:
                case 7:
                case 8:
                    b(i);
                    return;
                default:
                    return;
            }
        }

        @Override // android.media.MediaDrm.OnEventListener
        public void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
            VOMediaDrmSession vOMediaDrmSessionB;
            VOBaseMediaDrmAgent vOBaseMediaDrmAgent = VOBaseMediaDrmAgent.this;
            if (vOBaseMediaDrmAgent.y || (vOMediaDrmSessionB = vOBaseMediaDrmAgent.b(bArr)) == null) {
                return;
            }
            vOMediaDrmSessionB.a(i, i2, bArr2);
        }
    }

    protected class g {
        protected g() {
        }

        private String a(MediaDrm.KeyRequest keyRequest) {
            String licenseAcquisitionURL = VOBaseMediaDrmAgent.this.getLicenseAcquisitionURL();
            return TextUtils.isEmpty(licenseAcquisitionURL) ? keyRequest.getDefaultUrl() : licenseAcquisitionURL;
        }

        protected String a(String[] strArr) {
            if (strArr == null || strArr.length < 1) {
                return null;
            }
            String strConcat = "";
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = strArr[i] == null ? "" : strArr[i];
                strConcat = strConcat.concat(strArr[i] + "; ");
            }
            return strConcat;
        }

        protected byte[] a(UUID uuid, MediaDrm.KeyRequest keyRequest) throws IOException {
            VOUtils.RequestResponse requestResponseSendHTTPRequest;
            if (VOBaseMediaDrmAgent.this.y) {
                return null;
            }
            String strA = a(keyRequest);
            HashMap map = new HashMap();
            map.putAll(VOBaseMediaDrmAgent.this.j());
            String[] licenseAcquisitionCookies = VOBaseMediaDrmAgent.this.getLicenseAcquisitionCookies();
            if (licenseAcquisitionCookies != null) {
                map.put("cookie", a(licenseAcquisitionCookies));
            }
            String[] licenseAcquisitionCustomHeaders = VOBaseMediaDrmAgent.this.getLicenseAcquisitionCustomHeaders();
            if (licenseAcquisitionCustomHeaders != null) {
                for (String str : licenseAcquisitionCustomHeaders) {
                    String[] strArrSplit = str.split(":");
                    if (strArrSplit.length > 1) {
                        StringBuilder sb = new StringBuilder();
                        if (strArrSplit.length > 2) {
                            sb.append(strArrSplit[1]);
                            for (int i = 2; i < strArrSplit.length; i++) {
                                sb.append(':');
                                sb.append(strArrSplit[i]);
                            }
                        } else {
                            sb.append(strArrSplit[1]);
                        }
                        map.put(strArrSplit[0], sb.toString());
                    }
                }
            }
            if (VOBaseMediaDrmAgent.this.o) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("opid", DrmAgent.s);
                    jSONObject.put("token", VOBaseMediaDrmAgent.this.p);
                    jSONObject.put("challenge", new String(Base64.encode(keyRequest.getData(), 0)));
                    map.remove("Content-Type");
                    map.put("Content-Type", "application/json");
                    requestResponseSendHTTPRequest = VOUtils.sendHTTPRequest(strA, VOUtils.HTTP_POST, jSONObject.toString().getBytes(), map, false);
                } catch (JSONException | Exception unused) {
                    requestResponseSendHTTPRequest = null;
                }
            } else {
                requestResponseSendHTTPRequest = VOUtils.sendHTTPRequest(strA, VOUtils.HTTP_POST, keyRequest.getData(), map, false);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("executeKeyRequest properties:\n");
            for (Map.Entry entry : map.entrySet()) {
                sb2.append("\t\t");
                sb2.append((String) entry.getKey());
                sb2.append(" -> ");
                sb2.append((String) entry.getValue());
                sb2.append("\n");
            }
            if (requestResponseSendHTTPRequest == null) {
                throw new IOException("Exception 32000");
            }
            if (requestResponseSendHTTPRequest.responseCode > 0) {
                return requestResponseSendHTTPRequest.data;
            }
            return null;
        }

        protected byte[] a(UUID uuid, MediaDrm.ProvisionRequest provisionRequest) throws IOException {
            VOUtils.RequestResponse requestResponseSendHTTPRequest = VOUtils.sendHTTPRequest(provisionRequest.getDefaultUrl() + "&signedRequest=" + new String(provisionRequest.getData(), Charset.defaultCharset()), VOUtils.HTTP_POST, null, null, false);
            if (requestResponseSendHTTPRequest != null) {
                return requestResponseSendHTTPRequest.data;
            }
            throw new IOException("executeProvisionRequest, request failed");
        }
    }

    private class h extends f implements MediaDrm.OnKeyStatusChangeListener, MediaDrm.OnExpirationUpdateListener {
        private h() {
            super(VOBaseMediaDrmAgent.this, null);
        }

        /* synthetic */ h(VOBaseMediaDrmAgent vOBaseMediaDrmAgent, a aVar) {
            this();
        }

        @Override // android.media.MediaDrm.OnExpirationUpdateListener
        public void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j) {
            VOMediaDrmSession vOMediaDrmSessionB;
            VOBaseMediaDrmAgent vOBaseMediaDrmAgent = VOBaseMediaDrmAgent.this;
            if (vOBaseMediaDrmAgent.y || (vOMediaDrmSessionB = vOBaseMediaDrmAgent.b(bArr)) == null) {
                return;
            }
            vOMediaDrmSessionB.a(j);
        }

        @Override // android.media.MediaDrm.OnKeyStatusChangeListener
        public void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List<MediaDrm.KeyStatus> list, boolean z) {
            if (VOBaseMediaDrmAgent.this.y) {
                return;
            }
            for (MediaDrm.KeyStatus keyStatus : list) {
                byte[] keyId = keyStatus.getKeyId();
                int statusCode = keyStatus.getStatusCode();
                synchronized (VOBaseMediaDrmAgent.this.X) {
                    VOBaseMediaDrmAgent.this.X.put(VOUtils.bytesToUUID(keyId), Integer.valueOf(statusCode));
                }
            }
            VOMediaDrmSession vOMediaDrmSessionB = VOBaseMediaDrmAgent.this.b(bArr);
            if (vOMediaDrmSessionB != null) {
                vOMediaDrmSessionB.a(list, z);
            }
        }
    }

    public VOBaseMediaDrmAgent(Context context, DrmAgent.EDrmType eDrmType, UUID uuid, String str, VOPlayer vOPlayer) {
        super(context, eDrmType, vOPlayer);
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = false;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = false;
        this.I = c.UNKNOWN;
        this.J = null;
        this.K = null;
        this.L = 0;
        this.M = 0L;
        this.N = 60L;
        this.O = VOMediaDrmAgent.EDrmLicenseAcquisitionType.PERSISTENT;
        this.P = null;
        this.Q = null;
        this.R = 0;
        this.S = null;
        this.T = 0;
        this.U = false;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = 8;
        this.Z = null;
        this.a0 = null;
        this.b0 = null;
        this.c0 = null;
        this.d0 = null;
        this.v = uuid;
        this.w = str;
        this.x = j.a(this.f10a, str);
        this.P = new ArrayList<>();
        this.Q = new ArrayList<>();
        this.Z = new g();
        HandlerThread handlerThread = new HandlerThread("mHandlerThread");
        this.a0 = handlerThread;
        handlerThread.start();
        this.c0 = new e(this.a0.getLooper());
        this.b0 = new d(this.a0.getLooper());
        this.S = new ArrayList<>();
        this.X = new HashMap();
        this.V = new int[9];
        this.W = new int[9];
    }

    public static boolean a(UUID uuid) {
        if (Build.VERSION.SDK_INT >= 18) {
            return MediaDrm.isCryptoSchemeSupported(uuid);
        }
        return false;
    }

    private static int b(c cVar) {
        int i = a.f24a[cVar.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? 0 : 3;
        }
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VOMediaDrmSession b(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
        }
        synchronized (this.P) {
            VOMediaDrmSession vOMediaDrmSession = null;
            if (this.P != null && this.P.size() > 0) {
                for (int i = 0; i < this.P.size(); i++) {
                    VOMediaDrmSession vOMediaDrmSession2 = this.P.get(i);
                    if (vOMediaDrmSession2 != null && vOMediaDrmSession2.f != null && Arrays.equals(vOMediaDrmSession2.f, bArr)) {
                        vOMediaDrmSession = vOMediaDrmSession2;
                        break;
                    }
                }
                return vOMediaDrmSession;
            }
            return null;
        }
    }

    private i p() {
        VOMediaDrmSession vOMediaDrmSession = this.D;
        if (vOMediaDrmSession == null || !vOMediaDrmSession.e()) {
            return null;
        }
        return this.D.d();
    }

    private synchronized void q() throws VOException {
        VOStatusCode vOStatusCode;
        VOPlayer vOPlayer = new VOPlayer(this.f10a);
        vOPlayer.setUpdateRightsMode(b(this.I));
        vOPlayer.setOnInformationListener(this);
        vOPlayer.setOnErrorListener(this);
        vOPlayer.setOnPreparedListener(this);
        String str = this.K;
        if (str != null) {
            vOPlayer.setUserAgent(str);
        }
        try {
            vOPlayer.setDataSource(this.b.getStreamUri());
        } catch (IOException e2) {
            e2.printStackTrace();
            vOStatusCode = VOStatusCode.ERROR_FILE_ACCESS;
            DrmHelperUtils.throwVOException(vOStatusCode);
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            vOStatusCode = VOStatusCode.ERROR_BAD_ARGUMENTS;
            DrmHelperUtils.throwVOException(vOStatusCode);
        }
        VOBaseMediaDrmAgent vOBaseMediaDrmAgentA = a(vOPlayer);
        vOBaseMediaDrmAgentA.setLicenseAcquisitionURL(getLicenseAcquisitionURL());
        vOBaseMediaDrmAgentA.setLicenseAcquisitionURLParameters(getLicenseAcquisitionURLParameters());
        vOBaseMediaDrmAgentA.setLicenseAcquisitionCookies(getLicenseAcquisitionCookies());
        vOBaseMediaDrmAgentA.setLicenseAcquisitionCustomHeaders(getLicenseAcquisitionCustomHeaders());
        vOBaseMediaDrmAgentA.setLicenseAcquisitionType(g());
        vOBaseMediaDrmAgentA.setLicenseValidityThreshold(h());
        vOBaseMediaDrmAgentA.setSecurityLevel(getSecurityLevel());
        vOBaseMediaDrmAgentA.p = this.p;
        vOBaseMediaDrmAgentA.o = this.o;
        vOBaseMediaDrmAgentA.a(this.I);
        this.H = true;
        this.A = false;
        vOPlayer.prepareAsync();
        while (this.H) {
            try {
                if (this.y) {
                    break;
                } else {
                    Thread.sleep(50L);
                }
            } catch (InterruptedException e4) {
                e4.printStackTrace();
            }
        }
        if (this.I == c.INFO) {
            this.J = vOPlayer.getCurrentMediaDrmAgent().p();
        }
        vOPlayer.release();
        if (this.B) {
            DrmHelperUtils.throwVOException(VOStatusCode.ERROR_DRM_CONTENT_NOT_RECOGNIZED);
        }
        if ((c.INFO != this.I || this.J != null) && this.A) {
            DrmHelperUtils.throwVOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
    }

    public synchronized MediaCrypto a(VOMediaDrmAgent.EContentType eContentType) {
        VOMediaDrmSession vOMediaDrmSession;
        vOMediaDrmSession = this.D;
        return vOMediaDrmSession != null ? vOMediaDrmSession.a(eContentType) : null;
    }

    protected abstract VOBaseMediaDrmAgent a(VOPlayer vOPlayer);

    protected abstract VOMediaDrmSession a(Context context, com.viaccessorca.voplayer.a aVar, VOMediaDrmSession.OnMediaDrmSessionListener onMediaDrmSessionListener);

    public synchronized void a(OnMediaDrmAgentListener onMediaDrmAgentListener) {
        this.C = onMediaDrmAgentListener;
    }

    public void a(c cVar) {
        this.I = cVar;
    }

    public synchronized void a(VOMediaDrmSession vOMediaDrmSession) {
        OnMediaDrmAgentListener onMediaDrmAgentListener;
        VOMediaDrmSession vOMediaDrmSession2 = this.D;
        this.D = vOMediaDrmSession;
        if (vOMediaDrmSession.h() && (onMediaDrmAgentListener = this.C) != null) {
            onMediaDrmAgentListener.onDrmEvent(6);
        }
        if (vOMediaDrmSession2 == null) {
            this.F = this.D.a(VOMediaDrmAgent.EContentType.VIDEO);
            this.G = this.D.a(VOMediaDrmAgent.EContentType.AUDIO);
        } else if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (this.F == null) {
                    this.F = this.D.a(VOMediaDrmAgent.EContentType.VIDEO);
                }
                if (this.G == null) {
                    this.G = this.D.a(VOMediaDrmAgent.EContentType.AUDIO);
                }
                this.F.setMediaDrmSession(this.D.f);
                this.G.setMediaDrmSession(this.D.f);
            } catch (Exception unused) {
            }
        }
        if (vOMediaDrmSession2 != null) {
            this.Q.add(vOMediaDrmSession2);
        }
        while (this.Y < this.Q.size()) {
            VOMediaDrmSession vOMediaDrmSessionRemove = this.Q.remove(0);
            this.P.remove(vOMediaDrmSessionRemove);
            if (vOMediaDrmSessionRemove != null) {
                vOMediaDrmSessionRemove.a();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0037 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x0038  */
    /* JADX WARN: Code duplicated, block: B:30:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    protected void a(com.viaccessorca.voplayer.a aVar) throws VOException {
        VOMediaDrmSession.b bVar;
        if (aVar == null) {
            return;
        }
        l();
        VOMediaDrmSession vOMediaDrmSessionA = a(this.f10a, aVar, this.d0);
        int i = a.f24a[this.I.ordinal()];
        if (i == 1) {
            bVar = VOMediaDrmSession.b.MODE_QUERY;
        } else if (i == 2) {
            bVar = VOMediaDrmSession.b.MODE_RELEASE;
        } else {
            if (i != 3) {
                if (i == 4) {
                    bVar = VOMediaDrmSession.b.MODE_PLAYBACK;
                }
                if (this.y) {
                    return;
                }
                synchronized (this.P) {
                    this.P.add(vOMediaDrmSessionA);
                }
                this.R = Integer.valueOf(this.R.intValue() + 1);
                vOMediaDrmSessionA.i();
            }
            bVar = VOMediaDrmSession.b.MODE_INFO;
        }
        vOMediaDrmSessionA.a(bVar);
        if (this.y) {
            return;
        }
        synchronized (this.P) {
            this.P.add(vOMediaDrmSessionA);
            this.R = Integer.valueOf(this.R.intValue() + 1);
            vOMediaDrmSessionA.i();
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x001c A[Catch: IOException | JSONException -> 0x0043, TryCatch #0 {IOException | JSONException -> 0x0043, blocks: (B:9:0x000d, B:11:0x0010, B:13:0x0025, B:12:0x001c), top: B:16:0x000d }] */
    protected void a(byte[] bArr, byte[] bArr2) {
        JSONObject jSONObject;
        if (this.y || (jSONObject = this.x) == null) {
            return;
        }
        if (bArr2 != null) {
            try {
                if (bArr2.length > 0) {
                    jSONObject.put(VOUtils.bytesToHex(bArr), VOUtils.bytesToHex(bArr2));
                } else {
                    this.x.remove(VOUtils.bytesToHex(bArr));
                }
            } catch (IOException | JSONException unused) {
                return;
            }
        } else {
            this.x.remove(VOUtils.bytesToHex(bArr));
        }
        FileWriter fileWriter = new FileWriter(new File(this.f10a.getFilesDir(), this.w));
        fileWriter.write(this.x.toString());
        fileWriter.close();
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void acquireRights() throws VOException {
        this.I = c.ACQUIRE;
        q();
    }

    public void b(String str) {
        this.K = str;
    }

    public boolean b(com.viaccessorca.voplayer.a aVar) {
        if (this.y) {
            return false;
        }
        synchronized (this.S) {
            if (this.S.contains(aVar)) {
                return false;
            }
            this.S.add(aVar);
            return true;
        }
    }

    public void d() {
        if (this.y) {
            return;
        }
        new b(this, null).start();
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void deleteRights() throws VOException {
        this.I = c.DELETE;
        q();
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void deleteStore() throws VOException {
        byte[] bArrHexStringToByteArray;
        MediaDrm mediaDrm;
        l();
        if (this.E == null) {
            DrmHelperUtils.throwVOException(VOStatusCode.ERROR_NOT_SUPPORTED);
        }
        JSONObject jSONObject = this.x;
        if (jSONObject != null && jSONObject.length() >= 1) {
            Iterator<String> itKeys = this.x.keys();
            boolean z = true;
            while (itKeys.hasNext() && z) {
                byte[] bArrOpenSession = null;
                try {
                    String string = this.x.getString(itKeys.next());
                    bArrHexStringToByteArray = (string == null || string.length() <= 0) ? null : VOUtils.hexStringToByteArray(string);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (bArrHexStringToByteArray != null && bArrHexStringToByteArray.length >= 1) {
                    try {
                        bArrOpenSession = this.E.openSession();
                        this.E.restoreKeys(bArrOpenSession, bArrHexStringToByteArray);
                        this.E.removeKeys(bArrOpenSession);
                    } catch (NotProvisionedException e3) {
                        e3.printStackTrace();
                        z = false;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    if (bArrOpenSession != null && (mediaDrm = this.E) != null) {
                        mediaDrm.closeSession(bArrOpenSession);
                    }
                }
            }
            File filesDir = this.f10a.getFilesDir();
            if (filesDir != null && !new File(filesDir.getAbsolutePath(), this.w).delete()) {
                DrmHelperUtils.throwVOException(VOStatusCode.ERROR_FILE_ACCESS);
            }
            this.x = j.a(this.f10a, this.w);
        }
    }

    public void e() {
        if (this.y) {
            return;
        }
        this.y = true;
        o();
        synchronized (this.P) {
            for (int i = 0; i < this.P.size(); i++) {
                this.P.get(i).a();
            }
            this.P.clear();
            this.P = null;
        }
        MediaDrm mediaDrm = this.E;
        if (mediaDrm != null) {
            mediaDrm.release();
            this.E = null;
        }
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void enableMultiDrmSession() {
        this.U = true;
    }

    public void f() {
        VOMediaDrmSession vOMediaDrmSession = this.D;
        if (vOMediaDrmSession == null || VOMediaDrmAgent.EDrmLicenseAcquisitionType.PERSISTENT != this.O) {
            return;
        }
        vOMediaDrmSession.b();
    }

    public VOMediaDrmAgent.EDrmLicenseAcquisitionType g() {
        return this.O;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public abstract int getMaxSecurityLevel();

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public String getMaxSecurityLevelString() {
        return DrmAgent.buildSecurityLevelProperty(getMaxSecurityLevel());
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo() throws VOException {
        this.I = c.INFO;
        try {
            q();
            i iVar = this.J;
            if (iVar == null) {
                return null;
            }
            return new i[]{iVar};
        } catch (Exception e2) {
            throw e2;
        }
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public int getSecurityLevel() {
        int i = this.L;
        VOMediaDrmSession vOMediaDrmSession = this.D;
        return (vOMediaDrmSession == null || this.y) ? i : vOMediaDrmSession.f();
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public String getSecurityLevelString() {
        return DrmAgent.buildSecurityLevelProperty(getSecurityLevel());
    }

    public long h() {
        return this.M;
    }

    public synchronized Map<UUID, Integer> i() {
        HashMap map = new HashMap();
        if (this.y) {
            return map;
        }
        Map<UUID, Integer> map2 = this.X;
        if (map2 != null) {
            synchronized (map2) {
                map = new HashMap(this.X);
            }
        }
        return map;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public boolean isSupported() {
        return a(this.v);
    }

    protected Map<String, String> j() {
        return Collections.singletonMap("Content-Type", NanoHTTPD.MIME_DEFAULT_BINARY);
    }

    public c k() {
        return this.I;
    }

    protected void l() {
        if (this.z || this.y) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                MediaDrm mediaDrm = new MediaDrm(this.v);
                this.E = mediaDrm;
                mediaDrm.setPropertyString("sessionSharing", "enable");
            } catch (UnsupportedSchemeException | Exception unused) {
            }
        }
        if (this.E != null) {
            int i = Build.VERSION.SDK_INT;
            a aVar = null;
            if (i >= 23) {
                h hVar = new h(this, aVar);
                this.d0 = hVar;
                this.E.setOnKeyStatusChangeListener(hVar, (Handler) null);
                this.E.setOnExpirationUpdateListener((h) this.d0, (Handler) null);
            } else if (i >= 18) {
                f fVar = new f(this, aVar);
                this.d0 = fVar;
                this.E.setOnEventListener(fVar);
            }
        }
        this.z = true;
    }

    public boolean m() {
        return this.U;
    }

    public void n() throws VOException {
        synchronized (this.S) {
            for (int i = this.T; i < this.S.size(); i++) {
                com.viaccessorca.voplayer.a aVar = this.S.get(i);
                this.T++;
                if (this.U || aVar.getVideoHeader() != null || i >= this.S.size() - 1) {
                    if (this.y) {
                        return;
                    }
                    a(aVar);
                    if (!this.U) {
                        break;
                    }
                }
            }
        }
    }

    protected void o() {
        this.H = false;
        this.C = null;
        this.T = 0;
        ArrayList<com.viaccessorca.voplayer.a> arrayList = this.S;
        if (arrayList != null) {
            arrayList.clear();
            this.S = null;
        }
        Map<UUID, Integer> map = this.X;
        if (map != null) {
            map.clear();
            this.X = null;
        }
        ArrayList<VOMediaDrmSession> arrayList2 = this.Q;
        if (arrayList2 != null) {
            arrayList2.clear();
            this.Q = null;
        }
        if (this.D != null) {
            this.D = null;
        }
        this.R = 0;
        Arrays.fill(this.V, 0);
        Arrays.fill(this.W, 0);
        this.d0 = null;
        e eVar = this.c0;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
            this.c0 = null;
        }
        d dVar = this.b0;
        if (dVar != null) {
            dVar.removeCallbacksAndMessages(null);
            this.b0 = null;
        }
        HandlerThread handlerThread = this.a0;
        if (handlerThread != null) {
            handlerThread.quit();
            this.a0 = null;
        }
        this.Z = null;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnErrorListener
    public boolean onError(VOPlayer vOPlayer, int i, int i2) {
        this.H = false;
        this.A = true;
        return false;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnInformationListener
    public boolean onInformation(VOPlayer vOPlayer, int i, int i2, Object obj) {
        if (3002 == i2) {
            this.H = false;
        }
        return false;
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnPreparedListener
    public void onPrepared(VOPlayer vOPlayer) {
        this.H = false;
        this.B = true;
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setContentIdentifier(String str) throws VOException {
        this.p = str;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseAcquisitionType(VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType) {
        this.O = eDrmLicenseAcquisitionType;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseDurationThreshold(long j) {
        this.N = j;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseValidityThreshold(long j) {
        this.M = j;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setSecurityLevel(int i) {
        this.L = i;
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void useVOSecurityGateway(boolean z) throws VOException {
        this.o = z;
    }
}
