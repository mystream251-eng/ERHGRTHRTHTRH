package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfly extends zzflm<zzfly> {
    private static volatile zzfly[] zzc;
    public byte[] zza = null;
    public byte[] zzb = null;

    public zzfly() {
        this.zzax = null;
        this.zzay = -1;
    }

    public static zzfly[] zzb() {
        if (zzc == null) {
            synchronized (zzflq.zzb) {
                if (zzc == null) {
                    zzc = new zzfly[0];
                }
            }
        }
        return zzc;
    }

    protected final int zza() {
        int iZza = super.zza() + zzflk.zzb(1, this.zza);
        byte[] bArr = this.zzb;
        return bArr != null ? iZza + zzflk.zzb(2, bArr) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                this.zza = zzfljVar.zzf();
            } else if (iZza == 18) {
                this.zzb = zzfljVar.zzf();
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        zzflkVar.zza(1, this.zza);
        byte[] bArr = this.zzb;
        if (bArr != null) {
            zzflkVar.zza(2, bArr);
        }
        super.zza(zzflkVar);
    }
}
