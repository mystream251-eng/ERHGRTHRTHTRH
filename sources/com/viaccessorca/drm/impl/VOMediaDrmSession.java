package com.viaccessorca.drm.impl;

import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.drm.WidevineUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.drm.VOCommonDrmAgent;
import com.viaccessorca.drm.VOMediaDrmAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.UByte;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VOMediaDrmSession {
    protected static int C;
    private static int D;
    protected static ReentrantLock E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected VOBaseMediaDrmAgent f31a;
    private UUID b;
    protected MediaDrm d;
    protected Handler g;
    protected int h;
    private OnMediaDrmSessionListener j;
    protected long n;
    protected long o;
    protected byte[] p;
    protected String q;
    protected JSONObject r;
    private VOMediaDrmAgent.EDrmLicenseAcquisitionType w;
    private int x;
    private i y;
    protected boolean c = false;
    private MediaCrypto e = null;
    protected byte[] f = null;
    private boolean i = false;
    protected int k = 0;
    protected byte[] l = null;
    protected boolean m = false;
    protected byte[] s = null;
    protected int t = 0;
    protected long u = -1;
    protected b v = b.MODE_PLAYBACK;
    private boolean z = false;
    protected boolean A = false;
    protected h B = null;

    public interface OnMediaDrmSessionListener {
        void onDrmEvent(int i, VOMediaDrmSession vOMediaDrmSession);
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f32a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[b.values().length];
            b = iArr;
            try {
                iArr[b.MODE_PLAYBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[b.MODE_QUERY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[b.MODE_DOWNLOAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[b.MODE_RELEASE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[b.MODE_INFO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[VOMediaDrmAgent.EContentType.values().length];
            f32a = iArr2;
            try {
                iArr2[VOMediaDrmAgent.EContentType.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f32a[VOMediaDrmAgent.EContentType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public enum b {
        MODE_PLAYBACK,
        MODE_QUERY,
        MODE_DOWNLOAD,
        MODE_RELEASE,
        MODE_INFO
    }

    public VOMediaDrmSession(VOBaseMediaDrmAgent vOBaseMediaDrmAgent, JSONObject jSONObject, UUID uuid, MediaDrm mediaDrm, VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType, int i, com.viaccessorca.voplayer.a aVar, OnMediaDrmSessionListener onMediaDrmSessionListener, long j, long j2, Handler handler, Handler handler2) {
        String str;
        this.f31a = null;
        this.b = null;
        this.d = null;
        this.g = null;
        this.h = 0;
        this.j = null;
        this.n = 0L;
        this.o = 0L;
        this.p = null;
        this.q = null;
        this.r = null;
        this.w = VOMediaDrmAgent.EDrmLicenseAcquisitionType.PERSISTENT;
        this.x = 0;
        this.y = null;
        this.f31a = vOBaseMediaDrmAgent;
        this.b = uuid;
        this.d = mediaDrm;
        if (E == null) {
            E = new ReentrantLock();
        }
        this.n = j;
        this.o = j2;
        this.w = eDrmLicenseAcquisitionType;
        this.x = i;
        this.r = jSONObject;
        if (aVar != null) {
            if (aVar.getVideoHeader() != null) {
                this.p = a(aVar.getVideoHeader());
                str = MimeTypes.VIDEO_MP4;
            } else if (aVar.getAudioHeader() != null) {
                this.p = a(aVar.getAudioHeader());
                str = MimeTypes.AUDIO_MP4;
            }
            this.q = str;
        }
        this.j = onMediaDrmSessionListener;
        this.y = new i();
        q();
        new Handler(Looper.getMainLooper());
        this.h = 1;
        this.g = handler;
    }

    private void a(boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.t = 0;
        int i = 0;
        while (true) {
            for (boolean z2 = true; z2 && !this.c; z2 = false) {
                i++;
                if (1 < i) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused) {
                    }
                }
                try {
                    j();
                    D++;
                    if (this.c) {
                        return;
                    }
                    String strBytesToHex = VOUtils.bytesToHex(this.p);
                    if (this.r == null || !this.r.has(strBytesToHex)) {
                        this.s = null;
                    } else {
                        try {
                            this.s = VOUtils.hexStringToByteArray(this.r.getString(strBytesToHex));
                        } catch (JSONException unused2) {
                        }
                    }
                    this.h = 3;
                    m();
                } catch (NotProvisionedException e) {
                    e = e;
                    if (z) {
                        o();
                    } else {
                        b(e);
                    }
                } catch (Exception e2) {
                    e = e2;
                    if (System.currentTimeMillis() - jCurrentTimeMillis < 1250 && 2 < D) {
                    }
                    b(e);
                }
            }
            return;
        }
    }

    private void b(Exception exc) {
        if (!(exc instanceof IOException)) {
            a(1);
        } else if (this.m) {
            a(5);
        } else {
            a(4);
        }
        if (this.h != 4) {
            this.h = 5;
        }
    }

    private void m() {
        long jLongValue;
        VOCommonDrmAgent.VODrmRightsInfo.ELicenseType eLicenseType;
        if (true == this.c) {
            return;
        }
        int i = a.b[this.v.ordinal()];
        if (i == 1 || i == 2) {
            if (this.w == VOMediaDrmAgent.EDrmLicenseAcquisitionType.TEMPORARY) {
                a(this.f, 1);
                return;
            } else if (this.s == null) {
                a(this.f, 2);
                return;
            } else if (this.c || !k()) {
                return;
            } else {
                this.h = 4;
            }
        } else {
            if (i == 3) {
                if (this.s == null) {
                    a(this.f, 2);
                    return;
                } else {
                    if (this.c || !k()) {
                        return;
                    }
                    a(this.f, 2);
                    return;
                }
            }
            if (i != 4) {
                if (i != 5) {
                    return;
                }
                q();
                if (this.s == null) {
                    this.z = false;
                    a(1);
                    return;
                }
                try {
                    if (this.c || !k()) {
                        if (this.c) {
                            return;
                        }
                        this.A = false;
                        this.z = false;
                        a(1);
                        return;
                    }
                    HashMap<String, String> mapQueryKeyStatus = this.d.queryKeyStatus(this.f);
                    int i2 = 0;
                    while (this.A && !this.c) {
                        i2++;
                        if (1 < i2) {
                            try {
                                Thread.sleep(100L);
                            } catch (InterruptedException unused) {
                            }
                            int i3 = i2 % 10;
                        }
                    }
                    VOCommonDrmAgent.VODrmRightsInfo.ELicenseType eLicenseType2 = VOCommonDrmAgent.VODrmRightsInfo.ELicenseType.STREAMING;
                    String str = mapQueryKeyStatus.get("LicenseType");
                    VOCommonDrmAgent.VODrmRightsInfo.ELicenseType eLicenseType3 = VOCommonDrmAgent.VODrmRightsInfo.ELicenseType.UNKNOWN;
                    if (str != null) {
                        if (str.contains("Streaming")) {
                            eLicenseType = VOCommonDrmAgent.VODrmRightsInfo.ELicenseType.STREAMING;
                        } else if (str.contains("Offline")) {
                            eLicenseType = VOCommonDrmAgent.VODrmRightsInfo.ELicenseType.OFFLINE;
                        }
                        eLicenseType3 = eLicenseType;
                    }
                    long jLongValue2 = -1;
                    try {
                        jLongValue = Long.valueOf(mapQueryKeyStatus.get(WidevineUtil.PROPERTY_LICENSE_DURATION_REMAINING)).longValue();
                    } catch (NumberFormatException unused2) {
                        jLongValue = -1;
                    }
                    try {
                        jLongValue2 = Long.valueOf(mapQueryKeyStatus.get(WidevineUtil.PROPERTY_PLAYBACK_DURATION_REMAINING)).longValue();
                    } catch (NumberFormatException unused3) {
                    }
                    boolean zBooleanValue = Boolean.valueOf(mapQueryKeyStatus.get("PlayAllowed")).booleanValue();
                    boolean zBooleanValue2 = Boolean.valueOf(mapQueryKeyStatus.get("PersistAllowed")).booleanValue();
                    String str2 = mapQueryKeyStatus.get("RenewalServerUrl");
                    boolean zBooleanValue3 = Boolean.valueOf(mapQueryKeyStatus.get("RenewAllowed")).booleanValue();
                    this.y.a(eLicenseType3);
                    this.y.a(jLongValue);
                    this.y.b(zBooleanValue);
                    this.y.b(jLongValue2);
                    this.y.a(zBooleanValue2);
                    this.y.a(str2);
                    this.y.c(zBooleanValue3);
                    this.z = true;
                    this.h = 4;
                    a(0);
                    return;
                } catch (Exception e) {
                    this.A = false;
                    b(e);
                    return;
                }
            }
            if (!this.c && k()) {
                this.h = 4;
                MediaDrm mediaDrm = this.d;
                if (mediaDrm != null) {
                    mediaDrm.removeKeys(this.f);
                }
            }
            this.f31a.a(this.p, (byte[]) null);
        }
        a(0);
    }

    static boolean n() {
        String str = Build.MODEL;
        return (str.contains("Nexus 5") && !str.contains("Nexus 5X")) || str.contains("Nexus 7") || str.contains("Nexus 9");
    }

    private void o() {
        Handler handler;
        if (this.i || this.c || (handler = this.g) == null || this.d == null) {
            return;
        }
        try {
            this.i = true;
            handler.obtainMessage(0, this).sendToTarget();
        } catch (Exception unused) {
            b((Exception) new VOException(VOStatusCode.ERROR_GENERAL_FAILURE, "provisioning request failed"));
        }
    }

    private void p() {
        if (this.c) {
            return;
        }
        this.m = true;
        a(this.l, this.k);
    }

    private void q() {
        this.y.a(VOCommonDrmAgent.VODrmRightsInfo.ELicenseType.UNKNOWN);
        this.y.a(0L);
        this.y.b(false);
        this.y.b(0L);
        this.y.a(false);
        this.y.a("");
        this.y.c(false);
        this.z = false;
    }

    protected synchronized MediaCrypto a(VOMediaDrmAgent.EContentType eContentType) {
        MediaCrypto mediaCrypto;
        mediaCrypto = null;
        int i = a.f32a[eContentType.ordinal()];
        if (i == 1 || i == 2) {
            if (this.e == null) {
                try {
                    this.e = new MediaCrypto(this.b, this.f);
                } catch (Exception unused) {
                }
            }
            mediaCrypto = this.e;
        }
        return mediaCrypto;
    }

    protected void a() {
        if (1 == this.h) {
            return;
        }
        this.c = true;
        try {
            E.lock();
            this.h = 1;
            this.i = false;
            this.m = false;
            if (this.d != null && this.f != null) {
                byte[] bArr = this.f;
                this.f = null;
                try {
                    this.d.closeSession(bArr);
                } catch (Exception e) {
                    if (Build.VERSION.SDK_INT < 23) {
                        this.f = bArr;
                        throw e;
                    }
                    if (!(e instanceof MediaDrmResetException)) {
                        this.f = bArr;
                        throw e;
                    }
                }
                if (D > 0) {
                    D--;
                }
            }
            this.e = null;
            this.t = 0;
            if (this.B != null) {
                this.B.cancel();
                this.B = null;
            }
            this.g = null;
            this.d = null;
            E.unlock();
        } catch (Throwable th) {
            E.unlock();
            throw th;
        }
    }

    protected void a(int i) {
        OnMediaDrmSessionListener onMediaDrmSessionListener;
        if (this.c || (onMediaDrmSessionListener = this.j) == null) {
            return;
        }
        onMediaDrmSessionListener.onDrmEvent(i, this);
    }

    public void a(int i, int i2, byte[] bArr) {
        int i3 = this.h;
        if ((i3 == 3 || i3 == 4) && !this.c) {
            b bVar = this.v;
            if (bVar == b.MODE_PLAYBACK || bVar == b.MODE_QUERY) {
                if (i == 1) {
                    this.h = 3;
                    o();
                } else if (i == 2 || i == 3) {
                    this.s = null;
                    this.t = 1;
                }
            }
        }
    }

    public void a(long j) {
        if (j <= 0) {
            this.u = 0L;
            h hVar = this.B;
            if (hVar != null) {
                hVar.cancel();
                this.B = null;
                return;
            }
            return;
        }
        this.u = (j - System.currentTimeMillis()) / 1000;
        if (b.MODE_PLAYBACK != this.v || 1 == this.t) {
            return;
        }
        h hVar2 = this.B;
        if (hVar2 != null) {
            hVar2.cancel();
            this.B = null;
        }
        h hVar3 = new h(this.u, this.o, this);
        this.B = hVar3;
        hVar3.start();
        this.B.a();
    }

    public void a(b bVar) {
        this.v = bVar;
    }

    protected void a(Exception exc) {
        if (exc instanceof NotProvisionedException) {
            o();
        } else {
            b(exc);
        }
    }

    public void a(Object obj) {
        Exception iOException;
        int i = this.h;
        if ((i == 3 || i == 4) && !this.c) {
            if (obj == null && !this.m) {
                p();
                return;
            }
            if (obj == null && true == this.m) {
                iOException = new IOException("onKeyResponse, request failed");
            } else {
                if (!(obj instanceof Exception)) {
                    try {
                        if (this.c) {
                            return;
                        }
                        byte[] bArrProvideKeyResponse = this.d.provideKeyResponse(this.f, (byte[]) obj);
                        if (this.w == VOMediaDrmAgent.EDrmLicenseAcquisitionType.PERSISTENT) {
                            this.f31a.a(this.p, bArrProvideKeyResponse);
                        }
                        if (this.c) {
                            return;
                        }
                        a(3);
                        this.h = 4;
                        if (g()) {
                            this.t = 2;
                        } else {
                            this.t = 1;
                        }
                        a(0);
                        return;
                    } catch (Exception e) {
                        a(e);
                        return;
                    }
                }
                iOException = (Exception) obj;
            }
            a(iOException);
        }
    }

    public void a(List<MediaDrm.KeyStatus> list, boolean z) {
        String str;
        i iVar = this.y;
        if (iVar != null) {
            HashMap<String, String> keyStatus = iVar.getKeyStatus();
            for (MediaDrm.KeyStatus keyStatus2 : list) {
                byte[] keyId = keyStatus2.getKeyId();
                int statusCode = keyStatus2.getStatusCode();
                if (statusCode == 0) {
                    str = "USABLE";
                } else if (statusCode == 1) {
                    this.t = 1;
                    str = "EXPIRED";
                } else if (statusCode == 2) {
                    str = "OUTPUT_NOT_ALLOWED";
                } else if (statusCode == 3) {
                    str = "PENDING";
                } else if (statusCode != 4) {
                    str = "unknown : " + statusCode;
                } else {
                    this.t = 1;
                    str = "INTERNAL_ERROR";
                }
                if (keyStatus != null) {
                    keyStatus.put(VOUtils.bytesToHex(keyId), str);
                }
            }
        }
        this.A = false;
    }

    protected void a(byte[] bArr, int i) {
        if (this.c) {
            return;
        }
        try {
            this.k = i;
            this.l = bArr;
            a(2);
            if (this.g != null) {
                this.g.obtainMessage(1, this).sendToTarget();
            }
        } catch (Exception e) {
            a(e);
        }
    }

    protected byte[] a(byte[] bArr) {
        int i;
        if (VOUtils.SDK_INT >= 21) {
            return bArr;
        }
        if (bArr.length != (((bArr[0] & UByte.MAX_VALUE) << 24) | ((bArr[1] & UByte.MAX_VALUE) << 16) | ((bArr[2] & UByte.MAX_VALUE) << 8) | (bArr[3] & UByte.MAX_VALUE)) || bArr[4] != 112 || bArr[5] != 115 || bArr[6] != 115 || bArr[7] != 104 || (i = ((bArr[8] & UByte.MAX_VALUE) << 24) | ((bArr[9] & UByte.MAX_VALUE) << 16) | ((bArr[10] & UByte.MAX_VALUE) << 8) | (bArr[11] & UByte.MAX_VALUE)) > 1) {
            return bArr;
        }
        int i2 = i == 1 ? 28 + ((((bArr[28] & UByte.MAX_VALUE) << 24) | ((bArr[29] & UByte.MAX_VALUE) << 16) | ((bArr[30] & UByte.MAX_VALUE) << 8) | (bArr[31] & UByte.MAX_VALUE)) * 16) + 4 : 28;
        byte b2 = bArr[i2 + 0];
        byte b3 = bArr[i2 + 1];
        byte b4 = bArr[i2 + 2];
        byte b5 = bArr[i2 + 3];
        return Arrays.copyOfRange(bArr, i2 + 4, bArr.length);
    }

    protected void b() {
        MediaDrm mediaDrm = this.d;
        if (mediaDrm != null) {
            mediaDrm.removeKeys(this.f);
        }
        this.f31a.a(this.p, (byte[]) null);
    }

    public void b(Object obj) {
        this.i = false;
        int i = this.h;
        if ((i == 2 || i == 3 || i == 4) && !this.c) {
            if (obj == null) {
                if (this.x != 0) {
                    b((Exception) new IllegalArgumentException("provision response must not be null"));
                    return;
                }
                this.x = 1;
                if (this.h == 2) {
                    a(false);
                    return;
                }
                return;
            }
            if (obj instanceof Exception) {
                b((Exception) obj);
                return;
            }
            try {
                this.d.provideProvisionResponse((byte[]) obj);
                if (this.h == 2) {
                    a(false);
                } else {
                    m();
                }
            } catch (DeniedByServerException e) {
                b((Exception) e);
            }
        }
    }

    public MediaDrm.KeyRequest c() {
        try {
            return this.d.getKeyRequest(this.f, this.p, this.q, this.k, null);
        } catch (Exception e) {
            a(e);
            return null;
        }
    }

    public i d() {
        return this.y;
    }

    public boolean e() {
        return this.z;
    }

    protected int f() {
        MediaDrm mediaDrm;
        int securityLevel = this.x;
        byte[] bArr = this.f;
        if (bArr != null && (mediaDrm = this.d) != null) {
            try {
                securityLevel = Build.VERSION.SDK_INT >= 28 ? mediaDrm.getSecurityLevel(bArr) : DrmAgent.convertSecurityLevelProperty(mediaDrm.getPropertyString("securityLevel"));
            } catch (Exception unused) {
            }
        }
        return securityLevel;
    }

    protected abstract boolean g();

    public boolean h() {
        return false;
    }

    protected void i() {
        MediaDrm mediaDrm;
        byte[] bArr;
        if (1 != this.h) {
            return;
        }
        this.h = 2;
        a(true);
        if (this.t == 1) {
            b bVar = this.v;
            if (bVar == b.MODE_PLAYBACK || bVar == b.MODE_QUERY) {
                if (!this.c && (mediaDrm = this.d) != null && (bArr = this.f) != null) {
                    this.f = null;
                    try {
                        mediaDrm.closeSession(bArr);
                    } catch (Exception e) {
                        if (Build.VERSION.SDK_INT < 23) {
                            this.f = bArr;
                            throw e;
                        }
                        if (!(e instanceof MediaDrmResetException)) {
                            this.f = bArr;
                            throw e;
                        }
                    }
                    int i = D;
                    if (i > 0) {
                        D = i - 1;
                    }
                }
                a(true);
            }
        }
    }

    protected void j() throws Exception {
        MediaDrm mediaDrm;
        String str;
        Boolean bool = false;
        if (n()) {
            this.x = 1;
        }
        if (this.x != 0 && Build.VERSION.SDK_INT >= 28) {
            try {
                E.lock();
                this.f = this.d.openSession(this.x);
                E.unlock();
                try {
                    this.d.getSecurityLevel(this.f);
                } catch (Exception unused) {
                    bool = true;
                    this.f = null;
                }
            } catch (Throwable th) {
                E.unlock();
                throw th;
            }
        }
        if (this.f != null) {
            return;
        }
        int i = this.x;
        try {
            if (i != 1 && i != 2 && !bool.booleanValue()) {
                if (this.x != 0) {
                    mediaDrm = this.d;
                    str = "L1";
                }
                this.d.getPropertyString("securityLevel");
                E.lock();
                this.f = this.d.openSession();
                return;
            }
            mediaDrm = this.d;
            str = "L3";
            E.lock();
            this.f = this.d.openSession();
            return;
        } finally {
            E.unlock();
        }
        mediaDrm.setPropertyString("securityLevel", str);
        this.d.getPropertyString("securityLevel");
    }

    protected boolean k() {
        if (Build.VERSION.SDK_INT >= 23) {
            this.A = true;
        } else {
            this.A = false;
        }
        if (this.c) {
            return false;
        }
        try {
            this.d.restoreKeys(this.f, this.s);
            if (g() || !(this.v == b.MODE_PLAYBACK || this.v == b.MODE_QUERY)) {
                this.t = 2;
                return true;
            }
            this.t = 1;
            this.f31a.a(this.p, (byte[]) null);
            this.s = null;
            return false;
        } catch (Exception unused) {
            this.f31a.a(this.p, (byte[]) null);
            this.s = null;
            this.t = 1;
            return false;
        }
    }

    public void l() {
    }
}
