package com.viaccessorca.drm.impl;

import com.viaccessorca.drm.VOCommonDrmAgent;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
final class DrmRightsInfo implements VOCommonDrmAgent.VODrmRightsInfo {
    private long mCountLeft;
    private final DrmAgent.EDrmType mDrmType;
    private Date mEndTime;
    private boolean mHasCountConstraint;
    private boolean mHasIntervalConstraint;
    private boolean mHasTimeConstraint;
    private long mInitialCount;
    private long mIntervalPeriodInSeconds;
    private Date mStartTime;
    private VOCommonDrmAgent.VODrmRightsInfo.EDrmRightsInfo mStatus;

    DrmRightsInfo(DrmAgent.EDrmType eDrmType, VOCommonDrmAgent.VODrmRightsInfo.EDrmRightsInfo eDrmRightsInfo, boolean z, String str, String str2, boolean z2, long j, boolean z3, long j2, long j3) {
        this.mDrmType = eDrmType;
        this.mStatus = eDrmRightsInfo;
        this.mHasTimeConstraint = z;
        this.mStartTime = str != null ? DrmHelperUtils.getTimeFromString(str) : null;
        this.mEndTime = str2 != null ? DrmHelperUtils.getTimeFromString(str2) : null;
        this.mHasIntervalConstraint = z2;
        this.mIntervalPeriodInSeconds = j;
        this.mHasCountConstraint = z3;
        this.mInitialCount = j2;
        this.mCountLeft = j3;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getCountLeft() {
        return this.mCountLeft;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public Date getEndTime() {
        return this.mEndTime;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getInitialCount() {
        return this.mInitialCount;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getIntervalPeriodInSeconds() {
        if (hasIntervalConstraint()) {
            return this.mIntervalPeriodInSeconds;
        }
        return -1L;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public HashMap<String, String> getKeyStatus() {
        return null;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getLicenseDurationRemaining() {
        return 0L;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public VOCommonDrmAgent.VODrmRightsInfo.ELicenseType getLicenseType() {
        return VOCommonDrmAgent.VODrmRightsInfo.ELicenseType.STREAMING;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getPlaybackDurationRemaining() {
        return 0L;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public String getRenewalServerUrl() {
        return "";
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public Date getStartTime() {
        return this.mStartTime;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public VOCommonDrmAgent.VODrmRightsInfo.EDrmRightsInfo getStatus() {
        return this.mStatus;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean hasCountConstraint() {
        return this.mHasCountConstraint;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean hasIntervalConstraint() {
        return this.mHasIntervalConstraint;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean hasTimeConstraint() {
        return this.mHasTimeConstraint;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean isPersistAllowed() {
        return false;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean isPlayAllowed() {
        return false;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean isRenewAllowed() {
        return false;
    }
}
