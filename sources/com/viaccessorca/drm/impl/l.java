package com.viaccessorca.drm.impl;

import android.content.Context;
import com.viaccessorca.drm.VOMediaDrmAgent;
import com.viaccessorca.drm.VOViaccessAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class l extends DrmAgent implements VOViaccessAgent {
    public l(Context context, VOPlayer vOPlayer) {
        super(context, DrmAgent.EDrmType.DRM_TYPE_VODRM, vOPlayer);
        try {
            DrmAgent.initialize(context);
        } catch (VOException e) {
            e.printStackTrace();
        }
    }

    private int b(int i) throws VOException {
        Map.Entry<VOStatusCode, Integer> entryGetContentMaturityRatingThresholdByHandler = JniDrmManager.GetContentMaturityRatingThresholdByHandler(i);
        if (entryGetContentMaturityRatingThresholdByHandler == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGetContentMaturityRatingThresholdByHandler.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return entryGetContentMaturityRatingThresholdByHandler.getValue().intValue();
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void enableMultiDrmSession() {
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public int getContentMaturityRatingThreshold() throws VOException {
        if (this.n == -1) {
            b();
        }
        return b(this.n);
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public int getMaturityRatingMaxAuthorizedThreshold() throws VOException {
        c();
        Map.Entry<VOStatusCode, Integer> entryGetMaturityRatingMaxAuthorizedThreshold = JniDrmManager.GetMaturityRatingMaxAuthorizedThreshold(this.c);
        if (entryGetMaturityRatingMaxAuthorizedThreshold == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGetMaturityRatingMaxAuthorizedThreshold.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return entryGetMaturityRatingMaxAuthorizedThreshold.getValue().intValue();
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public int getMaturityRatingThreshold() throws VOException {
        c();
        Map.Entry<VOStatusCode, Integer> entryGetMaturityRatingThreshold = JniDrmManager.GetMaturityRatingThreshold(this.c);
        if (entryGetMaturityRatingThreshold == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        VOStatusCode key = entryGetMaturityRatingThreshold.getKey();
        if (key != VOStatusCode.SUCCESS) {
            DrmHelperUtils.throwVOException(key);
        }
        return entryGetMaturityRatingThreshold.getValue().intValue();
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public int getMaxSecurityLevel() {
        return getSecurityLevel();
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public String getMaxSecurityLevelString() {
        return DrmAgent.buildSecurityLevelProperty(getMaxSecurityLevel());
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public int getSecurityLevel() {
        return 1;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public String getSecurityLevelString() {
        return DrmAgent.buildSecurityLevelProperty(getSecurityLevel());
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public boolean isSupported() {
        return false;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseAcquisitionType(VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType) {
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseDurationThreshold(long j) {
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseValidityThreshold(long j) {
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public void setMaturityRatingThreshold(int i) throws VOException {
        c();
        VOStatusCode vOStatusCodeSetMaturityRatingThreshold = JniDrmManager.SetMaturityRatingThreshold(this.c, i);
        if (vOStatusCodeSetMaturityRatingThreshold != VOStatusCode.SUCCESS) {
            throw new VOException(vOStatusCodeSetMaturityRatingThreshold);
        }
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setSecurityLevel(int i) {
    }

    @Override // com.viaccessorca.drm.VOViaccessAgent
    public void unlockSession() {
    }
}
