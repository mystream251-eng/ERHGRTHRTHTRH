package com.viaccessorca.drm.impl;

import com.viaccessorca.drm.VOCommonDrmAgent;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
final class i implements VOCommonDrmAgent.VODrmRightsInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private VOCommonDrmAgent.VODrmRightsInfo.ELicenseType f40a = VOCommonDrmAgent.VODrmRightsInfo.ELicenseType.STREAMING;
    private long b = 0;
    private boolean c = false;
    private long d = 0;
    private boolean e = false;
    private String f = "";
    private boolean g = false;
    private HashMap<String, String> h;

    i() {
        this.h = null;
        this.h = new HashMap<>();
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(VOCommonDrmAgent.VODrmRightsInfo.ELicenseType eLicenseType) {
        this.f40a = eLicenseType;
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b(long j) {
        this.d = j;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public void c(boolean z) {
        this.g = z;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getCountLeft() {
        return 0L;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public Date getEndTime() {
        return null;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getInitialCount() {
        return 0L;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getIntervalPeriodInSeconds() {
        return 0L;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public HashMap<String, String> getKeyStatus() {
        return this.h;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getLicenseDurationRemaining() {
        return this.b;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public VOCommonDrmAgent.VODrmRightsInfo.ELicenseType getLicenseType() {
        return this.f40a;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public long getPlaybackDurationRemaining() {
        return this.d;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public String getRenewalServerUrl() {
        return this.f;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public Date getStartTime() {
        return null;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public VOCommonDrmAgent.VODrmRightsInfo.EDrmRightsInfo getStatus() {
        return null;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean hasCountConstraint() {
        return false;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean hasIntervalConstraint() {
        return false;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean hasTimeConstraint() {
        return false;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean isPersistAllowed() {
        return this.e;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean isPlayAllowed() {
        return this.c;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent.VODrmRightsInfo
    public boolean isRenewAllowed() {
        return this.g;
    }
}
