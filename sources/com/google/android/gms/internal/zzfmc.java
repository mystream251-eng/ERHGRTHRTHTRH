package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfmc extends zzflm<zzfmc> {
    private Integer zza = null;
    private byte[] zzb = null;
    private byte[] zzc = null;

    public zzfmc() {
        this.zzax = null;
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        Integer num = this.zza;
        if (num != null) {
            iZza += zzflk.zzb(1, num.intValue());
        }
        byte[] bArr = this.zzb;
        if (bArr != null) {
            iZza += zzflk.zzb(2, bArr);
        }
        byte[] bArr2 = this.zzc;
        return bArr2 != null ? iZza + zzflk.zzb(3, bArr2) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                this.zza = Integer.valueOf(zzfljVar.zzc());
            } else if (iZza == 18) {
                this.zzb = zzfljVar.zzf();
            } else if (iZza == 26) {
                this.zzc = zzfljVar.zzf();
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(1, num.intValue());
        }
        byte[] bArr = this.zzb;
        if (bArr != null) {
            zzflkVar.zza(2, bArr);
        }
        byte[] bArr2 = this.zzc;
        if (bArr2 != null) {
            zzflkVar.zza(3, bArr2);
        }
        super.zza(zzflkVar);
    }
}
