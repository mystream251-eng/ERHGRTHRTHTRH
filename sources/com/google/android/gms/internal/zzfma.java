package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfma extends zzflm<zzfma> {
    private byte[] zza = null;
    private byte[] zzb = null;
    private byte[] zzc = null;

    public zzfma() {
        this.zzax = null;
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
        return bArr3 != null ? iZza + zzflk.zzb(3, bArr3) : iZza;
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
        super.zza(zzflkVar);
    }
}
