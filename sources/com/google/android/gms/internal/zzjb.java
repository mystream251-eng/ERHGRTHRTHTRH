package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjb {
    private final byte[] zza;
    private int zzb;
    private int zzc;
    private /* synthetic */ zziz zzd;

    private zzjb(zziz zzizVar, byte[] bArr) {
        this.zzd = zzizVar;
        this.zza = bArr;
    }

    public final zzjb zza(int i) {
        this.zzb = i;
        return this;
    }

    public final synchronized void zza() {
        try {
            if (this.zzd.zzb) {
                this.zzd.zza.zza(this.zza);
                this.zzd.zza.zza(this.zzb);
                this.zzd.zza.zzb(this.zzc);
                this.zzd.zza.zza((int[]) null);
                this.zzd.zza.zza();
            }
        } catch (RemoteException e) {
            zzaky.zza("Clearcut log failed", e);
        }
    }

    public final zzjb zzb(int i) {
        this.zzc = i;
        return this;
    }
}
