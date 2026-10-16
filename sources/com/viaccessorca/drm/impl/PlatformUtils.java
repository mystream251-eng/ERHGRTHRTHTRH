package com.viaccessorca.drm.impl;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;
import java.io.File;
import java.io.FileInputStream;
import java.net.NetworkInterface;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
class PlatformUtils {
    PlatformUtils() {
    }

    private static String a() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private static String a(WifiManager wifiManager) {
        try {
            int wifiState = wifiManager.getWifiState();
            boolean z = true;
            wifiManager.setWifiEnabled(true);
            FileInputStream fileInputStream = new FileInputStream(new File("/sys/class/net/wlan0/address"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i = fileInputStream.read();
                if (i == -1) {
                    break;
                }
                sb.append((char) i);
            }
            String string = sb.toString();
            try {
                fileInputStream.close();
                if (3 != wifiState) {
                    z = false;
                }
                wifiManager.setWifiEnabled(z);
                return string;
            } catch (Exception unused) {
                return string;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String m2yugdva7tpoe4A3() throws VOException {
        Context context = DrmHelperUtils.getContext();
        if (context == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
        try {
            return telephonyManager.getDeviceId();
        } catch (Exception unused) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE);
        }
    }

    public static String m2yugdva7tpoe4A4() {
        WifiManager wifiManager;
        Context context = DrmHelperUtils.getContext();
        String macAddress = null;
        if (context != null) {
            try {
                wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                if (wifiManager != null) {
                    try {
                        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                        if (connectionInfo != null) {
                            macAddress = connectionInfo.getMacAddress();
                        }
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception unused2) {
                wifiManager = null;
            }
        } else {
            wifiManager = null;
        }
        if ((macAddress == null || macAddress.equals("02:00:00:00:00:00")) && (macAddress = a()) == null && wifiManager != null) {
            macAddress = a(wifiManager);
        }
        return macAddress == null ? "02:00:00:00:00:00" : macAddress;
    }

    public static String m2yugdva7tpoe4A5() {
        try {
            if (Build.VERSION.SDK_INT > 5) {
                return Settings.Secure.getString(DrmHelperUtils.getContext().getContentResolver(), "android_id");
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String m2yugdva7tpoe4A6() {
        return DrmHelperUtils.getContext().getDir("DxDrm", 0).getPath();
    }

    public static String m2yugdva7tpoe4A7() {
        return DrmHelperUtils.getContext().getFilesDir().getPath() + "/";
    }
}
