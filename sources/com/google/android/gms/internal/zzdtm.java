package com.google.android.gms.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdtm<P> {
    private final P zza;
    private final byte[] zzb;
    private final zzdwj zzc;
    private final zzdxb zzd;

    public zzdtm(P p, byte[] bArr, zzdwj zzdwjVar, zzdxb zzdxbVar) {
        this.zza = p;
        this.zzb = Arrays.copyOf(bArr, bArr.length);
        this.zzc = zzdwjVar;
        this.zzd = zzdxbVar;
    }

    public final P zza() {
        return this.zza;
    }

    public final byte[] zzb() {
        byte[] bArr = this.zzb;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }
}
