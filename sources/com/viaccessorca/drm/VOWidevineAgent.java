package com.viaccessorca.drm;

/* JADX INFO: loaded from: classes2.dex */
public interface VOWidevineAgent extends VOMediaDrmAgent {
    public static final int WIDEVINE_SECURITY_LEVEL_HW_SECURE_ALL = 5;
    public static final int WIDEVINE_SECURITY_LEVEL_HW_SECURE_CRYPTO = 3;
    public static final int WIDEVINE_SECURITY_LEVEL_HW_SECURE_DECODE = 4;
    public static final int WIDEVINE_SECURITY_LEVEL_SW_SECURE_CRYPTO = 1;
    public static final int WIDEVINE_SECURITY_LEVEL_SW_SECURE_DECODE = 2;
    public static final int WIDEVINE_SECURITY_LEVEL_UNKNOWN = 0;

    public enum EDrmLicenseAcquisitionType {
        PERSISTENT,
        TEMPORARY
    }

    void setLicenseAcquisitionType(EDrmLicenseAcquisitionType eDrmLicenseAcquisitionType);
}
