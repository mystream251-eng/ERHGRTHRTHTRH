package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbc extends zzflm<zzbc> {
    private Long zzd = null;
    private Long zze = null;
    public Long zza = null;
    public Long zzb = null;
    public Long zzc = null;

    public zzbc() {
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        Long l = this.zzd;
        if (l != null) {
            iZza += zzflk.zze(1, l.longValue());
        }
        Long l2 = this.zze;
        if (l2 != null) {
            iZza += zzflk.zze(2, l2.longValue());
        }
        Long l3 = this.zza;
        if (l3 != null) {
            iZza += zzflk.zze(3, l3.longValue());
        }
        Long l4 = this.zzb;
        if (l4 != null) {
            iZza += zzflk.zze(4, l4.longValue());
        }
        Long l5 = this.zzc;
        return l5 != null ? iZza + zzflk.zze(5, l5.longValue()) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                this.zzd = Long.valueOf(zzfljVar.zzi());
            } else if (iZza == 16) {
                this.zze = Long.valueOf(zzfljVar.zzi());
            } else if (iZza == 24) {
                this.zza = Long.valueOf(zzfljVar.zzi());
            } else if (iZza == 32) {
                this.zzb = Long.valueOf(zzfljVar.zzi());
            } else if (iZza == 40) {
                this.zzc = Long.valueOf(zzfljVar.zzi());
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Long l = this.zzd;
        if (l != null) {
            zzflkVar.zzb(1, l.longValue());
        }
        Long l2 = this.zze;
        if (l2 != null) {
            zzflkVar.zzb(2, l2.longValue());
        }
        Long l3 = this.zza;
        if (l3 != null) {
            zzflkVar.zzb(3, l3.longValue());
        }
        Long l4 = this.zzb;
        if (l4 != null) {
            zzflkVar.zzb(4, l4.longValue());
        }
        Long l5 = this.zzc;
        if (l5 != null) {
            zzflkVar.zzb(5, l5.longValue());
        }
        super.zza(zzflkVar);
    }
}
