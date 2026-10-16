package com.google.android.gms.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
final class zzbz implements Runnable {
    private zzbz() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            MessageDigest unused = zzbx.zzc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused2) {
        } finally {
            zzbx.zza.countDown();
        }
    }
}
