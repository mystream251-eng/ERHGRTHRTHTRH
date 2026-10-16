package com.viaccessorca.drm.impl;

import android.media.MediaDrm;
import android.os.Build;
import android.os.Handler;
import android.util.Base64;
import com.viaccessorca.drm.VOMediaDrmAgent;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class n extends VOMediaDrmSession {
    private int F;

    public n(VOBaseMediaDrmAgent vOBaseMediaDrmAgent, JSONObject jSONObject, UUID uuid, MediaDrm mediaDrm, VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType, int i, com.viaccessorca.voplayer.a aVar, VOMediaDrmSession.OnMediaDrmSessionListener onMediaDrmSessionListener, long j, long j2, int i2, Handler handler, Handler handler2) {
        super(vOBaseMediaDrmAgent, jSONObject, uuid, mediaDrm, eDrmLicenseAcquisitionType, i, aVar, onMediaDrmSessionListener, j, j2, handler, handler2);
        this.F = 10;
        this.F = i2;
        StringBuilder sb = new StringBuilder();
        sb.append("VoDrmMediaDrmSession#");
        int i3 = VOMediaDrmSession.C;
        VOMediaDrmSession.C = i3 + 1;
        sb.append(i3);
        new String(sb.toString());
    }

    private HashMap<String, String> b(byte[] bArr) {
        HashMap<String, String> map = new HashMap<>();
        MediaDrm mediaDrm = this.d;
        return (mediaDrm == null || bArr == null || Build.VERSION.SDK_INT < 18) ? map : mediaDrm.queryKeyStatus(bArr);
    }

    private void c(byte[] bArr) {
        try {
            if (this.d != null) {
                this.d.setPropertyByteArray("unlockSession", bArr);
                a(7);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    protected void a(byte[] bArr, int i) {
        if (this.c) {
            return;
        }
        try {
            this.k = i;
            this.l = bArr;
            MediaDrm.KeyRequest keyRequest = this.d.getKeyRequest(this.f, this.p, this.q, i, null);
            if (keyRequest != null && keyRequest.getData() != null && keyRequest.getData().length > 0 && 3 != keyRequest.getRequestType()) {
                a(2);
                this.g.obtainMessage(1, keyRequest).sendToTarget();
                return;
            }
            this.h = 4;
            if (g()) {
                this.t = 2;
            } else {
                this.t = 1;
            }
            a(0);
        } catch (Exception e) {
            a(e);
        }
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    protected byte[] a(byte[] bArr) {
        String str = new String(bArr);
        int iLastIndexOf = str.lastIndexOf("</vodrm:ro>");
        int iLastIndexOf2 = str.lastIndexOf("<vodrm:ro");
        if (-1 == iLastIndexOf2 || -1 == iLastIndexOf2) {
            return bArr;
        }
        while (iLastIndexOf2 < iLastIndexOf) {
            char cCharAt = str.charAt(iLastIndexOf2);
            iLastIndexOf2++;
            if (cCharAt == '>') {
                break;
            }
        }
        return j.a(m.g0, null, Base64.decode(str.substring(iLastIndexOf2, iLastIndexOf), 0));
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    protected boolean g() {
        HashMap<String, String> mapB = b(this.f);
        return (mapB.isEmpty() || mapB.get("PlayAllowed").equals("False")) ? false : true;
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    public boolean h() {
        return b(this.f).get("sessionLocked").equals("True");
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    protected void j() throws Exception {
        if (this.f == null) {
            try {
                VOMediaDrmSession.E.lock();
                this.f = this.d.openSession();
                if (this.d != null) {
                    this.d.setPropertyByteArray("setMaturity", new byte[]{(byte) Math.min(Math.max(this.F, 0), 15)});
                }
            } finally {
                VOMediaDrmSession.E.unlock();
            }
        }
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    protected boolean k() {
        this.A = false;
        try {
            this.d.restoreKeys(this.f, this.p);
            if (g() || !(this.v == VOMediaDrmSession.b.MODE_PLAYBACK || this.v == VOMediaDrmSession.b.MODE_QUERY)) {
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

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    public void l() {
        c(this.f);
    }

    public int m() {
        HashMap<String, String> mapB = b(this.f);
        if (mapB.containsKey("maturityLevel")) {
            return Integer.parseInt(mapB.get("maturityLevel"));
        }
        return -1;
    }
}
