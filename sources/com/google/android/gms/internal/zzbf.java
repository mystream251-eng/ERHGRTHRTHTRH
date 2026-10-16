package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbf extends zzflm<zzbf> {
    public Long zza = null;
    private String zzb = null;
    private byte[] zzc = null;

    public zzbf() {
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        Long l = this.zza;
        if (l != null) {
            iZza += zzflk.zze(1, l.longValue());
        }
        String str = this.zzb;
        if (str != null) {
            iZza += zzflk.zzb(3, str);
        }
        byte[] bArr = this.zzc;
        return bArr != null ? iZza + zzflk.zzb(4, bArr) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                this.zza = Long.valueOf(zzfljVar.zzi());
            } else if (iZza == 26) {
                this.zzb = zzfljVar.zze();
            } else if (iZza == 34) {
                this.zzc = zzfljVar.zzf();
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Long l = this.zza;
        if (l != null) {
            zzflkVar.zzb(1, l.longValue());
        }
        String str = this.zzb;
        if (str != null) {
            zzflkVar.zza(3, str);
        }
        byte[] bArr = this.zzc;
        if (bArr != null) {
            zzflkVar.zza(4, bArr);
        }
        super.zza(zzflkVar);
    }
}
