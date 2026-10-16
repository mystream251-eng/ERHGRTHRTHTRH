package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfmf extends zzflm<zzfmf> {
    public String zza = null;
    public Long zzb = null;
    public Boolean zzc = null;

    public zzfmf() {
        this.zzax = null;
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        String str = this.zza;
        if (str != null) {
            iZza += zzflk.zzb(1, str);
        }
        Long l = this.zzb;
        if (l != null) {
            iZza += zzflk.zze(2, l.longValue());
        }
        Boolean bool = this.zzc;
        if (bool == null) {
            return iZza;
        }
        bool.booleanValue();
        return iZza + zzflk.zzb(3) + 1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                this.zza = zzfljVar.zze();
            } else if (iZza == 16) {
                this.zzb = Long.valueOf(zzfljVar.zzb());
            } else if (iZza == 24) {
                this.zzc = Boolean.valueOf(zzfljVar.zzd());
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zza;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        Long l = this.zzb;
        if (l != null) {
            zzflkVar.zzb(2, l.longValue());
        }
        Boolean bool = this.zzc;
        if (bool != null) {
            zzflkVar.zza(3, bool.booleanValue());
        }
        super.zza(zzflkVar);
    }
}
