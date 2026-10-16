package com.viaccessorca.drm;

import com.viaccessorca.exceptions.VOException;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public interface VOCommonDrmAgent {

    public interface VODrmChallenge {
    }

    public interface VODrmRightsInfo {

        public enum EDrmRightsInfo {
            m329t7p6390828478441,
            m329t7p6390828478442,
            m329t7p6390828478443,
            m329t7p6390828478444,
            m329t7p6390828478445,
            m329t7p6390828478446
        }

        public enum ELicenseType {
            STREAMING,
            OFFLINE,
            UNKNOWN
        }

        long getCountLeft();

        Date getEndTime();

        long getInitialCount();

        long getIntervalPeriodInSeconds();

        HashMap<String, String> getKeyStatus();

        long getLicenseDurationRemaining();

        ELicenseType getLicenseType();

        long getPlaybackDurationRemaining();

        String getRenewalServerUrl();

        Date getStartTime();

        EDrmRightsInfo getStatus();

        boolean hasCountConstraint();

        boolean hasIntervalConstraint();

        boolean hasTimeConstraint();

        boolean isPersistAllowed();

        boolean isPlayAllowed();

        boolean isRenewAllowed();
    }

    void acquireRights() throws VOException;

    void deletePersonalization() throws VOException;

    void deleteRights() throws VOException;

    void deleteStore() throws VOException;

    VODrmChallenge generatePersonalizationChallenge() throws VOException;

    VODrmRightsInfo[] getRightsInfo() throws VOException;

    String getVersion() throws VOException;

    boolean isDrmContent() throws VOException;

    boolean isDrmContent(String str) throws VOException;

    void performPersonalization() throws VOException;

    void setContentIdentifier(String str) throws VOException;

    void setLicenseAcquisitionCookies(String[] strArr);

    void setLicenseAcquisitionCustomData(String str);

    void setLicenseAcquisitionCustomHeaders(String[] strArr);

    void setLicenseAcquisitionURL(String str);

    void setLicenseAcquisitionURLParameters(String str);

    void setPersonalizationURL(String str) throws VOException;

    void useVOSecurityGateway(boolean z) throws VOException;

    boolean verifyPersonalization() throws VOException;
}
