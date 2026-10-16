package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjv extends zzflm<zzjv> {
    public Integer zza = null;
    public Integer zzb = null;
    public Integer zzc = null;

    public zzjv() {
        this.zzax = null;
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        Integer num = this.zza;
        if (num != null) {
            iZza += zzflk.zzb(1, num.intValue());
        }
        Integer num2 = this.zzb;
        if (num2 != null) {
            iZza += zzflk.zzb(2, num2.intValue());
        }
        Integer num3 = this.zzc;
        return num3 != null ? iZza + zzflk.zzb(3, num3.intValue()) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                this.zza = Integer.valueOf(zzfljVar.zzh());
            } else if (iZza == 16) {
                this.zzb = Integer.valueOf(zzfljVar.zzh());
            } else if (iZza == 24) {
                this.zzc = Integer.valueOf(zzfljVar.zzh());
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
        Integer num2 = this.zzb;
        if (num2 != null) {
            zzflkVar.zza(2, num2.intValue());
        }
        Integer num3 = this.zzc;
        if (num3 != null) {
            zzflkVar.zza(3, num3.intValue());
        }
        super.zza(zzflkVar);
    }
}
