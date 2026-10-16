package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOSystemInfo {
    VOPerformancePoint[] m329t7p6390828478424;
    VOPerformancePoint[] m329t7p6390828478425;
    VOPerformancePoint[] m329t7p6390828478426;
    VOPerformancePoint[] m329t7p6390828478427;
    public int codecColorFormat = 0;
    public int androidVersion = 0;
    public int screenWidth = 0;
    public int screenHeight = 0;
    public int cpuCount = 0;
    public int cpuMaxFrequency = 0;
    public int cpuScore = 0;
    public int gpuTextureUpScore = 0;
    public int cpuBenchmarkScore = 0;
    public int copyBenchmarkScore = 0;
    public int codecTypeException = -1;
    public boolean codecAdaptivePlaybackSupported = false;
    public String codecName = null;
    public String secureCodecName = null;
    public String codecNameHevc = null;
    public String secureCodecNameHevc = null;
    public int maxVideoHwCodecSupportedInstances = -1;
    public String codecNameAAC = null;
    public String secureCodecNameAAC = null;
    public String codecNameAC3 = null;
    public String secureCodecNameAC3 = null;
    public String codecNameEC3 = null;
    public String secureCodecNameEC3 = null;
    public int[] audioEncodings = null;
    public int audioChannels = 0;
    public String deviceModel = null;
    public String deviceManufacturer = null;
    public String deviceProduct = null;
    public String deviceBrand = null;
    public String deviceName = null;
    public String deviceBoard = null;
    public String hardware = null;
    public String devicePlatform = null;
    public String deviceId1 = null;
    public String deviceId2 = null;
    public String deviceId3 = null;
    public String deviceId4 = null;
    public boolean isEmulator = false;
    public boolean isTV = false;
    public String osArch = null;
    public String libArch = null;
    public VOPerformancePoint[] performancePointsForH264 = null;
    public VOPerformancePoint[] performancePointsForSecureH264 = null;
    public VOPerformancePoint[] performancePointsForHEVC = null;
    public VOPerformancePoint[] performancePointsForSecureHEVC = null;

    private static String a(VOPerformancePoint[] vOPerformancePointArr) {
        StringBuilder sb = new StringBuilder("[");
        if (vOPerformancePointArr != null) {
            for (VOPerformancePoint vOPerformancePoint : vOPerformancePointArr) {
                sb.append(" " + vOPerformancePoint);
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        String str;
        switch (this.codecTypeException) {
            case -1:
                str = VOPlaybackSessionReport.UNKNOWN_SVALUE;
                break;
            case 0:
                str = "Default";
                break;
            case 1:
                str = "HW/MC_TU";
                break;
            case 2:
                str = "HW/MC_NT";
                break;
            case 3:
                str = "HW/SF_TU";
                break;
            case 4:
                str = "HW/SF_NT";
                break;
            case 5:
                str = "SW";
                break;
            case 6:
                str = "AML";
                break;
            case 7:
                str = "HW/MC_TU_TV";
                break;
            default:
                str = null;
                break;
        }
        StringBuilder sb = new StringBuilder("[");
        if (this.audioEncodings != null) {
            for (int i = 0; i < this.audioEncodings.length; i++) {
                sb.append(" " + this.audioEncodings[i]);
            }
        }
        sb.append(" ]");
        return new String("codecColorFormat=0x" + Integer.toHexString(this.codecColorFormat) + "\nmaxVideoHwCodecSupportedInstances=" + this.maxVideoHwCodecSupportedInstances + "\ncodecName=" + this.codecName + "\nsecureCodecName=" + this.secureCodecName + "\ncodecNameHevc=" + this.codecNameHevc + "\nsecureCodecNameHevc=" + this.secureCodecNameHevc + "\ncodecNameAAC=" + this.codecNameAAC + "\nsecureCodecNameAAC=" + this.secureCodecNameAAC + "\ncodecNameAC3=" + this.codecNameAC3 + "\nsecureCodecNameAC3=" + this.secureCodecNameAC3 + "\ncodecNameEC3=" + this.codecNameEC3 + "\nsecureCodecNameEC3=" + this.secureCodecNameEC3 + "\ncodecTypeException=" + str + "\ncodecAdaptivePlaybackSupported=" + this.codecAdaptivePlaybackSupported + "\naudioEncodings=" + ((Object) sb) + "\naudioChannels=" + this.audioChannels + "\nandroidVersion=" + this.androidVersion + "\nscreenWidth=" + this.screenWidth + "\nscreenHeight=" + this.screenHeight + "\ncpuCount=" + this.cpuCount + "\ncpuMaxFrequency=" + this.cpuMaxFrequency + "\ncpuScore=" + this.cpuScore + "\ngpuTextureUpScore=" + this.gpuTextureUpScore + "\ncpuBenchmarkScore=" + this.cpuBenchmarkScore + "\ncopyBenchmarkScore=" + this.copyBenchmarkScore + "\ndeviceModel=" + this.deviceModel + "\ndeviceManufacturer=" + this.deviceManufacturer + "\ndeviceProduct=" + this.deviceProduct + "\ndeviceBrand=" + this.deviceBrand + "\ndeviceName=" + this.deviceName + "\ndeviceBoard=" + this.deviceBoard + "\ndevicePlatform=" + this.devicePlatform + "\ndeviceHardware=" + this.hardware + "\ndeviceId1=" + this.deviceId1 + "\ndeviceId2=" + this.deviceId2 + "\ndeviceId3=" + this.deviceId3 + "\ndeviceId4=" + this.deviceId4 + "\nisEmulator=" + this.isEmulator + "\nisTV=" + this.isTV + "\nosArch=" + this.osArch + "\nlibArch=" + this.libArch + "\nperformancePointsForH264=" + a(this.performancePointsForH264) + "\nperformancePointsForSecureH264=" + a(this.performancePointsForSecureH264) + "\nperformancePointsForHEVC=" + a(this.performancePointsForHEVC) + "\nperformancePointsForSecureHEVC=" + a(this.performancePointsForSecureHEVC));
    }
}
