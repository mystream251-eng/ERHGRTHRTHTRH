package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjr extends zzflm<zzjr> {
    private Integer zza = null;
    private Integer zzb = null;

    public zzjr() {
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
        return num2 != null ? iZza + zzflk.zzb(2, num2.intValue()) : iZza;
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
        super.zza(zzflkVar);
    }
}
