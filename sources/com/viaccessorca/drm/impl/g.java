package com.viaccessorca.drm.impl;

import android.media.MediaDrm;
import android.os.Handler;
import android.util.Base64;
import com.viaccessorca.drm.VOMediaDrmAgent;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends VOMediaDrmSession {
    public g(VOBaseMediaDrmAgent vOBaseMediaDrmAgent, JSONObject jSONObject, UUID uuid, MediaDrm mediaDrm, VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType, int i, com.viaccessorca.voplayer.a aVar, VOMediaDrmSession.OnMediaDrmSessionListener onMediaDrmSessionListener, long j, long j2, Handler handler, Handler handler2) {
        super(vOBaseMediaDrmAgent, jSONObject, uuid, mediaDrm, eDrmLicenseAcquisitionType, i, aVar, onMediaDrmSessionListener, j, j2, handler, handler2);
        StringBuilder sb = new StringBuilder();
        sb.append("PlayreadyMediaDrmSession#");
        int i2 = VOMediaDrmSession.C;
        VOMediaDrmSession.C = i2 + 1;
        sb.append(i2);
        new String(sb.toString());
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    protected byte[] a(byte[] bArr) {
        return j.a(f.f0, null, Base64.decode(bArr, 0));
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    protected boolean g() {
        return true;
    }

    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    protected void j() throws Exception {
        if (this.f == null) {
            try {
                VOMediaDrmSession.E.lock();
                this.f = this.d.openSession();
            } finally {
                VOMediaDrmSession.E.unlock();
            }
        }
    }
}
