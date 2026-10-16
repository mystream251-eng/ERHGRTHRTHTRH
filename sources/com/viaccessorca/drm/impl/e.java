package com.viaccessorca.drm.impl;

import android.content.Context;
import android.net.Uri;
import com.viaccessorca.drm.VOCommonDrmAgent;
import com.viaccessorca.drm.VOMediaDrmAgent;
import com.viaccessorca.drm.VOPlayreadyAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements VOPlayreadyAgent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f38a = null;
    private String b = null;
    private String c = null;
    private String d = null;
    private Context e;

    public e(Context context) {
        this.e = null;
        this.e = context;
    }

    public String a() {
        return this.f38a;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void acquireRights() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void acquireRights(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void deletePersonalization() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void deleteRights() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void deleteRights(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void deleteStore() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void enableMultiDrmSession() {
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void executeInitiator(Uri uri) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void executeInitiator(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public VOCommonDrmAgent.VODrmChallenge generateInitiatorChallenge(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public VOCommonDrmAgent.VODrmChallenge generateLicenseAcquisitionChallenge() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public VOCommonDrmAgent.VODrmChallenge generateLicenseAcquisitionChallenge(String str, String str2) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public VOCommonDrmAgent.VODrmChallenge generatePersonalizationChallenge() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public VOCommonDrmAgent.VODrmChallenge generateTrustedTimeChallenge() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public int getMaxSecurityLevel() {
        return getSecurityLevel();
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public String getMaxSecurityLevelString() {
        return DrmAgent.buildSecurityLevelProperty(getMaxSecurityLevel());
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public VOCommonDrmAgent.VODrmRightsInfo[] getRightsInfo(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public int getSecurityLevel() {
        return 1;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public String getSecurityLevelString() {
        return DrmAgent.buildSecurityLevelProperty(getSecurityLevel());
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public String getVersion() throws VOException {
        return DrmAgent.isSupported(DrmAgent.EDrmType.DRM_TYPE_PLAYREADY, this.e) ? "PK 3.0.4019" : "N/A";
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public boolean isDrmContent() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public boolean isDrmContent(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public boolean isSupported() {
        return DrmAgent.isSupported(DrmAgent.EDrmType.DRM_TYPE_PLAYREADY, this.e);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void performPersonalization() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void setContentIdentifier(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void setLicenseAcquisitionCookies(String[] strArr) {
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setLicenseAcquisitionCustomData(String str) {
        this.f38a = str;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void setLicenseAcquisitionCustomHeaders(String[] strArr) {
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseAcquisitionType(VOMediaDrmAgent.EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType) {
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setLicenseAcquisitionURL(String str) {
        this.b = str;
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public synchronized void setLicenseAcquisitionURLParameters(String str) {
        this.c = str;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseDurationThreshold(long j) {
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setLicenseValidityThreshold(long j) {
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void setPersonalizationAppVersion(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public void setPersonalizationCustomData(String str) {
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void setPersonalizationURL(String str) {
        this.d = str;
    }

    @Override // com.viaccessorca.drm.VOMediaDrmAgent
    public void setSecurityLevel(int i) {
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public void useVOSecurityGateway(boolean z) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOCommonDrmAgent
    public boolean verifyPersonalization() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public boolean verifyRights() throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }

    @Override // com.viaccessorca.drm.VOPlayreadyAgent
    public boolean verifyRights(String str) throws VOException {
        throw new VOException(VOStatusCode.ERROR_OPERATION_NOT_SUPPORTED);
    }
}
