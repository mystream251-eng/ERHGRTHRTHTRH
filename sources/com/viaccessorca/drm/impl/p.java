package com.viaccessorca.drm.impl;

import android.media.MediaDrm;
import android.os.Handler;
import com.viaccessorca.drm.VOMediaDrmAgent;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class p extends VOMediaDrmSession {
    public p(VOBaseMediaDrmAgent vOBaseMediaDrmAgent, JSONObject jSONObject, UUID uuid, MediaDrm mediaDrm, VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType, int i, com.viaccessorca.voplayer.a aVar, VOMediaDrmSession.OnMediaDrmSessionListener onMediaDrmSessionListener, long j, long j2, Handler handler, Handler handler2) {
        super(vOBaseMediaDrmAgent, jSONObject, uuid, mediaDrm, eDrmLicenseAcquisitionType, i, aVar, onMediaDrmSessionListener, j, j2, handler, handler2);
        StringBuilder sb = new StringBuilder();
        sb.append("WidevineMediaDrmSession#");
        int i2 = VOMediaDrmSession.C;
        VOMediaDrmSession.C = i2 + 1;
        sb.append(i2);
        new String(sb.toString());
    }

    /* JADX WARN: Code duplicated, block: B:72:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:74:0x00e3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:75:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:77:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:83:0x0102  */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0065, code lost:
    
        if (r18.n > r4) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00cc, code lost:
    
        if (r18.n > r12) goto L37;
     */
    @Override // com.viaccessorca.drm.impl.VOMediaDrmSession
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean g() {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.viaccessorca.drm.impl.p.g():boolean");
    }
}
