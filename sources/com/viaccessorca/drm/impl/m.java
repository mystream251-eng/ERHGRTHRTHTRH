package com.viaccessorca.drm.impl;

import android.content.Context;
import android.media.MediaDrm;
import android.os.Build;
import com.viaccessorca.drm.VOViaccessAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public final class m extends VOBaseMediaDrmAgent implements VOViaccessAgent {
    public static final UUID g0 = new UUID(-5458022420440088656L, -7735543098249644349L);
    private static int h0;
    private static String i0;
    private static String j0;
    private int f0;

    static {
        MediaDrm mediaDrm;
        Throwable th;
        String propertyString;
        h0 = 0;
        i0 = "Vendor: N/A";
        j0 = "Version: N/A";
        if (Build.VERSION.SDK_INT < 18) {
            return;
        }
        MediaDrm mediaDrm2 = null;
        if (!MediaDrm.isCryptoSchemeSupported(g0)) {
            return;
        }
        try {
            try {
                mediaDrm = new MediaDrm(g0);
                try {
                    if (Build.VERSION.SDK_INT >= 28) {
                        h0 = MediaDrm.getMaxSecurityLevel();
                        propertyString = "";
                    } else {
                        propertyString = mediaDrm.getPropertyString("securityLevel");
                    }
                    h0 = DrmAgent.convertSecurityLevelProperty(propertyString);
                    i0 = mediaDrm.getPropertyString("vendor");
                    j0 = mediaDrm.getPropertyString("version");
                    mediaDrm.release();
                } catch (Exception e) {
                    e = e;
                    mediaDrm2 = mediaDrm;
                    e.printStackTrace();
                    if (mediaDrm2 != null) {
                        mediaDrm2.release();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (mediaDrm != null) {
                        mediaDrm.release();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th3) {
            mediaDrm = mediaDrm2;
            th = th3;
        }
    }

    public m(Context context, VOPlayer vOPlayer) {
        super(context, DrmAgent.EDrmType.DRM_TYPE_VODRM, g0, "vodrmOffline.json", vOPlayer);
        this.f0 = 0;
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent
    protected VOBaseMediaDrmAgent a(VOPlayer vOPlayer) {
        return (VOBaseMediaDrmAgent) vOPlayer.getAgentVodrm();
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent
    protected VOMediaDrmSession a(Context context, com.viaccessorca.voplayer.a aVar, VOMediaDrmSession.OnMediaDrmSessionListener onMediaDrmSessionListener) {
        return new n(this, this.x, this.v, this.E, this.O, this.L, aVar, onMediaDrmSessionListener, this.M, this.N, this.f0, this.b0, this.c0);
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public int getContentMaturityRatingThreshold() throws VOException {
        VOMediaDrmSession vOMediaDrmSession = this.D;
        if (vOMediaDrmSession != null) {
            return ((n) vOMediaDrmSession).m();
        }
        return 0;
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public int getMaturityRatingMaxAuthorizedThreshold() throws VOException {
        return 0;
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public int getMaturityRatingThreshold() throws VOException {
        return this.f0;
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent, com.viaccessorca.drm.VOMediaDrmAgent
    public int getMaxSecurityLevel() {
        return h0;
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public String getVersion() throws VOException {
        return j0 + " " + i0;
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public void setMaturityRatingThreshold(int i) throws VOException {
        this.f0 = i;
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public void unlockSession() {
        int contentMaturityRatingThreshold;
        if (this.D != null) {
            try {
                contentMaturityRatingThreshold = getContentMaturityRatingThreshold();
            } catch (VOException unused) {
                contentMaturityRatingThreshold = -1;
            }
            this.D.l();
            if (-1 != contentMaturityRatingThreshold) {
                this.f0 = contentMaturityRatingThreshold;
            }
        }
    }
}
