package com.viaccessorca.drm.impl;

import android.content.Context;
import android.media.MediaDrm;
import android.net.Uri;
import android.os.Build;
import com.viaccessorca.drm.VOCommonDrmAgent;
import com.viaccessorca.drm.VOPlayreadyAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import com.viaccessorca.vodownloader.NanoHTTPD;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends VOBaseMediaDrmAgent implements VOPlayreadyAgent {
    public static final UUID f0 = new UUID(-7348484286925749626L, -6083546864340672619L);
    private static String g0 = "PlayreadyMediaDrmAgent";
    private static int h0;
    private static String i0;
    private static String j0;

    static {
        MediaDrm mediaDrm;
        Throwable th;
        String propertyString;
        h0 = 0;
        i0 = "Vendor: N/A";
        j0 = "Version: N/A";
        if (Build.VERSION.SDK_INT >= 18) {
            MediaDrm mediaDrm2 = null;
            MediaDrm mediaDrm3 = null;
            try {
                if (MediaDrm.isCryptoSchemeSupported(f0)) {
                    try {
                        mediaDrm = new MediaDrm(f0);
                        try {
                            if (Build.VERSION.SDK_INT >= 28) {
                                h0 = MediaDrm.getMaxSecurityLevel();
                                propertyString = "";
                            } else {
                                propertyString = mediaDrm.getPropertyString("securityLevel");
                            }
                            h0 = DrmAgent.convertSecurityLevelProperty(propertyString);
                            i0 = mediaDrm.getPropertyString("vendor");
                            String propertyString2 = mediaDrm.getPropertyString("version");
                            j0 = propertyString2;
                            mediaDrm.release();
                            mediaDrm2 = propertyString2;
                        } catch (Exception e) {
                            e = e;
                            mediaDrm3 = mediaDrm;
                            e.printStackTrace();
                            mediaDrm2 = mediaDrm3;
                            if (mediaDrm3 != null) {
                                mediaDrm3.release();
                                mediaDrm2 = mediaDrm3;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                    return;
                }
                return;
            } catch (Throwable th3) {
                mediaDrm = mediaDrm2;
                th = th3;
            }
            if (mediaDrm != null) {
                mediaDrm.release();
            }
            throw th;
        }
    }

    public f(Context context, VOPlayer vOPlayer) {
        super(context, DrmAgent.EDrmType.DRM_TYPE_PLAYREADY, f0, "playreadyOffline.json", vOPlayer);
        StringBuilder sb = new StringBuilder();
        sb.append(g0);
        sb.append("#");
        int i = VOBaseMediaDrmAgent.e0;
        VOBaseMediaDrmAgent.e0 = i + 1;
        sb.append(i);
        new String(sb.toString());
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent
    protected VOBaseMediaDrmAgent a(VOPlayer vOPlayer) {
        return (VOBaseMediaDrmAgent) vOPlayer.getAgentPlayready(VOPlayer.EDrmEngineType.MediaDrm);
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent
    protected VOMediaDrmSession a(Context context, com.viaccessorca.voplayer.a aVar, VOMediaDrmSession.OnMediaDrmSessionListener onMediaDrmSessionListener) {
        return new g(this, this.x, this.v, this.E, this.O, this.L, aVar, onMediaDrmSessionListener, this.M, this.N, this.b0, this.c0);
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent, com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public void acquireRights() throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public void deletePersonalization() throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void executeInitiator(Uri uri) throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void executeInitiator(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public VOCommonDrmAgent.VODrmChallenge generateInitiatorChallenge(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public VOCommonDrmAgent.VODrmChallenge generatePersonalizationChallenge() throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOPlayreadyAgent
    public VOCommonDrmAgent.VODrmChallenge generateTrustedTimeChallenge() throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent, com.viaccessorca.drm.VOMediaDrmAgent
    public int getMaxSecurityLevel() {
        return h0;
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent, com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo() throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOPlayreadyAgent
    public VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public String getVersion() throws VOException {
        return j0 + " " + i0;
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent
    protected Map<String, String> j() {
        HashMap map = new HashMap();
        map.put("Content-Type", NanoHTTPD.MIME_XML);
        map.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        return map;
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public void performPersonalization() throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void setPersonalizationCustomData(String str) {
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public boolean verifyPersonalization() throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOPlayreadyAgent
    public boolean verifyRights() throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOPlayreadyAgent
    public boolean verifyRights(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_NOT_SUPPORTED);
    }
}
