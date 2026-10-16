package com.viaccessorca.drm.impl;

import android.content.Context;
import android.media.MediaDrm;
import android.os.Build;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.drm.VOMediaDrmAgent;
import com.viaccessorca.drm.VOWidevineAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public final class o extends VOBaseMediaDrmAgent implements VOWidevineAgent {
    public static final UUID f0 = new UUID(-1301668207276963122L, -6645017420763422227L);
    private static int g0;
    private static String h0;
    private static String i0;
    private static String j0;
    private static byte[] k0;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f42a;

        static {
            int[] iArr = new int[VOWidevineAgent.EDrmLicenseAcquisitionType.values().length];
            f42a = iArr;
            try {
                iArr[VOWidevineAgent.EDrmLicenseAcquisitionType.TEMPORARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f42a[VOWidevineAgent.EDrmLicenseAcquisitionType.PERSISTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        MediaDrm mediaDrm;
        g0 = 0;
        h0 = "Vendor: N/A";
        i0 = "Version: N/A";
        j0 = "N/A";
        MediaDrm mediaDrm2 = null;
        k0 = null;
        if (Build.VERSION.SDK_INT < 18 || !MediaDrm.isCryptoSchemeSupported(f0)) {
            return;
        }
        try {
            try {
                mediaDrm = new MediaDrm(f0);
                try {
                    j0 = mediaDrm.getPropertyString("securityLevel");
                    h0 = mediaDrm.getPropertyString("vendor");
                    i0 = mediaDrm.getPropertyString("version");
                    k0 = mediaDrm.getPropertyByteArray("deviceUniqueId");
                    String str = Build.MODEL;
                    if (true == ((str.contains("Nexus 5") && !str.contains("Nexus 5X")) || str.contains("Nexus 7") || str.contains("Nexus 9"))) {
                        g0 = 1;
                    } else {
                        g0 = Build.VERSION.SDK_INT >= 28 ? MediaDrm.getMaxSecurityLevel() : DrmAgent.convertSecurityLevelProperty(j0);
                    }
                } catch (Exception e) {
                    e = e;
                    mediaDrm2 = mediaDrm;
                    e.printStackTrace();
                    if (mediaDrm2 == null) {
                        return;
                    } else {
                        mediaDrm = mediaDrm2;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (mediaDrm != null) {
                        mediaDrm.release();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            mediaDrm.release();
        } catch (Throwable th2) {
            th = th2;
            mediaDrm = mediaDrm2;
        }
    }

    public o(Context context, VOPlayer vOPlayer) {
        super(context, DrmAgent.EDrmType.DRM_TYPE_WIDEVINE, f0, "wvOffline.json", vOPlayer);
        StringBuilder sb = new StringBuilder();
        sb.append("WidevineMediaDrmAgent#");
        int i = VOBaseMediaDrmAgent.e0;
        VOBaseMediaDrmAgent.e0 = i + 1;
        sb.append(i);
        new String(sb.toString());
    }

    public static UUID p() {
        return VOUtils.bytesToUUID(k0);
    }

    public static String q() {
        return j0;
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent
    protected VOBaseMediaDrmAgent a(VOPlayer vOPlayer) {
        return (VOBaseMediaDrmAgent) vOPlayer.getAgentWidevine();
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent
    protected VOMediaDrmSession a(Context context, com.viaccessorca.voplayer.a aVar, VOMediaDrmSession.OnMediaDrmSessionListener onMediaDrmSessionListener) {
        return new p(this, this.x, this.v, this.E, this.O, this.L, aVar, onMediaDrmSessionListener, this.M, this.N, this.b0, this.c0);
    }

    @Override // com.viaccessorca.drm.impl.VOBaseMediaDrmAgent, com.viaccessorca.drm.VOMediaDrmAgent
    public int getMaxSecurityLevel() {
        return g0;
    }

    @Override // com.viaccessorca.drm.impl.DrmAgent, com.viaccessorca.drm.VOCommonDrmAgent
    public String getVersion() throws VOException {
        return i0 + " " + h0;
    }

    @Override // com.viaccessorca.drm.VOWidevineAgent
    public void setLicenseAcquisitionType(VOWidevineAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType) {
        VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType2;
        VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType3 = VOMediaDrmAgent.EDrmLicenseAcquisitionType.UNKNOWN;
        int i = a.f42a[eDrmLicenseAcquisitionType.ordinal()];
        if (i != 1) {
            eDrmLicenseAcquisitionType2 = i != 2 ? VOMediaDrmAgent.EDrmLicenseAcquisitionType.UNKNOWN : VOMediaDrmAgent.EDrmLicenseAcquisitionType.PERSISTENT;
        } else {
            eDrmLicenseAcquisitionType2 = VOMediaDrmAgent.EDrmLicenseAcquisitionType.TEMPORARY;
        }
        setLicenseAcquisitionType(eDrmLicenseAcquisitionType2);
    }
}
