package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbe extends zzflm<zzbe> {
    public byte[] zza = null;
    public byte[] zzb = null;
    public byte[] zzc = null;
    public byte[] zzd = null;

    public zzbe() {
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        byte[] bArr = this.zza;
        if (bArr != null) {
            iZza += zzflk.zzb(1, bArr);
        }
        byte[] bArr2 = this.zzb;
        if (bArr2 != null) {
            iZza += zzflk.zzb(2, bArr2);
        }
        byte[] bArr3 = this.zzc;
        if (bArr3 != null) {
            iZza += zzflk.zzb(3, bArr3);
        }
        byte[] bArr4 = this.zzd;
        return bArr4 != null ? iZza + zzflk.zzb(4, bArr4) : iZza;
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
            } else if (iZza == 26) {
                this.zzc = zzfljVar.zzf();
            } else if (iZza == 34) {
                this.zzd = zzfljVar.zzf();
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        byte[] bArr = this.zza;
        if (bArr != null) {
            zzflkVar.zza(1, bArr);
        }
        byte[] bArr2 = this.zzb;
        if (bArr2 != null) {
            zzflkVar.zza(2, bArr2);
        }
        byte[] bArr3 = this.zzc;
        if (bArr3 != null) {
            zzflkVar.zza(3, bArr3);
        }
        byte[] bArr4 = this.zzd;
        if (bArr4 != null) {
            zzflkVar.zza(4, bArr4);
        }
        super.zza(zzflkVar);
    }
}
