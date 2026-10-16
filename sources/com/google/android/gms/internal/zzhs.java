package com.google.android.gms.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzhs {
    private static MessageDigest zzb;
    protected Object zza = new Object();

    protected final MessageDigest zza() {
        synchronized (this.zza) {
            if (zzb != null) {
                return zzb;
            }
            for (int i = 0; i < 2; i++) {
                try {
                    zzb = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return zzb;
        }
    }

    abstract byte[] zza(String str);
}
