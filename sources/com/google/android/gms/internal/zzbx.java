package com.google.android.gms.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
final class zzbx {
    private static boolean zzb = false;
    private static MessageDigest zzc;
    private static final Object zzd = new Object();
    private static final Object zze = new Object();
    static CountDownLatch zza = new CountDownLatch(1);

    private static zzba zza(long j) {
        zzba zzbaVar = new zzba();
        zzbaVar.zzk = Long.valueOf(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        return zzbaVar;
    }

    static String zza(zzba zzbaVar, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] bArrZza;
        byte[] bArrZza2 = zzfls.zza(zzbaVar);
        if (((Boolean) zzlc.zzf().zza(zzoi.zzee)).booleanValue()) {
            bArrZza = zza(bArrZza2, str);
        } else {
            if (zzdq.zza == null) {
                throw new GeneralSecurityException();
            }
            byte[] bArrZza3 = zzdq.zza.zza(bArrZza2, str != null ? str.getBytes() : new byte[0]);
            zzbg zzbgVar = new zzbg();
            zzbgVar.zza = new byte[][]{bArrZza3};
            zzbgVar.zzc = 2;
            bArrZza = zzfls.zza(zzbgVar);
        }
        return zzbv.zza(bArrZza, true);
    }

    private static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + 255) - 1) / 255;
        Vector<byte[]> vector = new Vector<>();
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 255;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > 255 ? i3 + 255 : bArr.length));
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        }
        return vector;
    }

    static void zza() {
        synchronized (zze) {
            if (!zzb) {
                zzb = true;
                new Thread(new zzbz()).start();
            }
        }
    }

    public static byte[] zza(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] bArrDigest;
        synchronized (zzd) {
            MessageDigest messageDigestZzb = zzb();
            if (messageDigestZzb == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
            messageDigestZzb.reset();
            messageDigestZzb.update(bArr);
            bArrDigest = zzc.digest();
        }
        return bArrDigest;
    }

    private static byte[] zza(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Vector<byte[]> vectorZza = zza(bArr, 255);
        if (vectorZza == null || vectorZza.size() == 0) {
            return zza(zzfls.zza(zza(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)), str, true);
        }
        zzbg zzbgVar = new zzbg();
        zzbgVar.zza = new byte[vectorZza.size()][];
        Iterator<byte[]> it = vectorZza.iterator();
        int i = 0;
        while (it.hasNext()) {
            zzbgVar.zza[i] = zza(it.next(), str, false);
            i++;
        }
        zzbgVar.zzb = zza(bArr);
        return zzfls.zza(zzbgVar);
    }

    private static byte[] zza(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ByteBuffer byteBufferPut;
        int i = z ? 239 : 255;
        if (bArr.length > i) {
            bArr = zzfls.zza(zza(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        }
        if (bArr.length < i) {
            byte[] bArr2 = new byte[i - bArr.length];
            new SecureRandom().nextBytes(bArr2);
            byteBufferPut = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).put(bArr2);
        } else {
            byteBufferPut = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr);
        }
        byte[] bArrArray = byteBufferPut.array();
        if (z) {
            bArrArray = ByteBuffer.allocate(256).put(zza(bArrArray)).put(bArrArray).array();
        }
        byte[] bArr3 = new byte[256];
        new zzca().zza(bArrArray, bArr3);
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzffa(str.getBytes("UTF-8")).zza(bArr3);
        }
        return bArr3;
    }

    private static MessageDigest zzb() {
        boolean zAwait;
        MessageDigest messageDigest;
        zza();
        try {
            zAwait = zza.await(2L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            zAwait = false;
        }
        if (zAwait && (messageDigest = zzc) != null) {
            return messageDigest;
        }
        return null;
    }
}
