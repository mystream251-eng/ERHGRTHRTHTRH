package com.viaccessorca.drm;

/* JADX INFO: loaded from: classes2.dex */
public interface VOMediaDrmAgent extends VOCommonDrmAgent {
    public static final int SECURITY_LEVEL_HW_SECURE_ALL = 5;
    public static final int SECURITY_LEVEL_HW_SECURE_CRYPTO = 3;
    public static final int SECURITY_LEVEL_HW_SECURE_DECODE = 4;
    public static final int SECURITY_LEVEL_SW_SECURE_CRYPTO = 1;
    public static final int SECURITY_LEVEL_SW_SECURE_DECODE = 2;
    public static final int SECURITY_LEVEL_UNKNOWN = 0;

    public enum EContentType {
        AUDIO,
        VIDEO,
        UNKNOWN
    }

    public enum EDrmLicenseAcquisitionType {
        PERSISTENT,
        TEMPORARY,
        UNKNOWN
    }

    void enableMultiDrmSession();

    int getMaxSecurityLevel();

    String getMaxSecurityLevelString();

    int getSecurityLevel();

    String getSecurityLevelString();

    boolean isSupported();

    void setLicenseAcquisitionType(EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType);

    void setLicenseDurationThreshold(long j);

    void setLicenseValidityThreshold(long j);

    void setSecurityLevel(int i);
}
