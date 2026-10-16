package com.viaccessorca.drm.impl;

import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.media.ResourceBusyException;
import android.media.UnsupportedSchemeException;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public final class VoMediaDrm {

    static class a implements MediaDrm.OnEventListener {
        a() {
        }

        @Override // android.media.MediaDrm.OnEventListener
        public void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
            VoMediaDrm.VoMediaDrmOnEvent(mediaDrm, bArr, i, i2, bArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void VoMediaDrmOnEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2);

    private static String a(HashMap<String, String> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        String str = "";
        for (String str2 : map.keySet()) {
            str = str + str2 + ";" + map.get(str2) + ";";
        }
        return str;
    }

    private static HashMap<String, String> a(String str) {
        if (str == null || str.split(";") == null) {
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        String[] strArrSplit = str.split(";");
        for (int i = 0; i < strArrSplit.length; i += 2) {
            map.put(strArrSplit[i], strArrSplit[i + 1]);
        }
        return map;
    }

    public static void closeSession(MediaDrm mediaDrm, byte[] bArr) {
        try {
            mediaDrm.closeSession(bArr);
        } catch (NullPointerException unused) {
        }
    }

    public static MediaDrm.CryptoSession getCryptoSession(MediaDrm mediaDrm, byte[] bArr, String str, String str2) {
        try {
            return mediaDrm.getCryptoSession(bArr, str, str2);
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static byte[] getDeviceIDByteArray(MediaDrm mediaDrm) {
        return getPropertyByteArray(mediaDrm, "deviceUniqueId");
    }

    public static MediaDrm.KeyRequest getKeyRequest(MediaDrm mediaDrm, byte[] bArr, byte[] bArr2, String str, int i, String str2) throws NotProvisionedException {
        try {
            return mediaDrm.getKeyRequest(bArr, bArr2, str, i, a(str2));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static byte[] getPropertyByteArray(MediaDrm mediaDrm, String str) {
        try {
            return mediaDrm.getPropertyByteArray(str);
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static String getPropertyString(MediaDrm mediaDrm, String str) {
        try {
            return mediaDrm.getPropertyString(str);
        } catch (NullPointerException | Exception unused) {
            return null;
        }
    }

    public static MediaDrm.ProvisionRequest getProvisionRequest(MediaDrm mediaDrm) {
        try {
            return mediaDrm.getProvisionRequest();
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static boolean isCryptoSchemeSupported(long j, long j2) {
        return MediaDrm.isCryptoSchemeSupported(new UUID(j, j2));
    }

    public static MediaDrm mediaDrm(long j, long j2) throws UnsupportedSchemeException {
        MediaDrm mediaDrm = new MediaDrm(new UUID(j, j2));
        mediaDrm.setOnEventListener(new a());
        return mediaDrm;
    }

    public static byte[] openSession(MediaDrm mediaDrm) throws ResourceBusyException, NotProvisionedException {
        try {
            return mediaDrm.openSession();
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static byte[] provideKeyResponse(MediaDrm mediaDrm, byte[] bArr, byte[] bArr2) throws DeniedByServerException, NotProvisionedException {
        try {
            return mediaDrm.provideKeyResponse(bArr, bArr2);
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static void provideProvisionResponse(MediaDrm mediaDrm, byte[] bArr) throws DeniedByServerException {
        try {
            mediaDrm.provideProvisionResponse(bArr);
        } catch (NullPointerException unused) {
        }
    }

    public static String queryKeyStatus(MediaDrm mediaDrm, byte[] bArr) {
        try {
            return a(mediaDrm.queryKeyStatus(bArr));
        } catch (IllegalStateException e) {
            e.getMessage();
            return null;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static void release(MediaDrm mediaDrm) {
        try {
            mediaDrm.release();
        } catch (NullPointerException unused) {
        }
    }

    public static void removeKeys(MediaDrm mediaDrm, byte[] bArr) {
        try {
            mediaDrm.removeKeys(bArr);
        } catch (NullPointerException unused) {
        }
    }

    public static void restoreKeys(MediaDrm mediaDrm, byte[] bArr, byte[] bArr2) {
        try {
            mediaDrm.restoreKeys(bArr, bArr2);
        } catch (NullPointerException unused) {
        }
    }

    public static void setPropertyByteArray(MediaDrm mediaDrm, String str, byte[] bArr) {
        try {
            mediaDrm.setPropertyByteArray(str, bArr);
        } catch (NullPointerException unused) {
        }
    }

    public static void setPropertyString(MediaDrm mediaDrm, String str, String str2) {
        try {
            mediaDrm.setPropertyString(str, str2);
        } catch (NullPointerException unused) {
        }
    }
}
