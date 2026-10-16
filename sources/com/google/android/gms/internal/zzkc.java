package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzkc extends zzflm<zzkc> {
    private Integer zza = null;
    private zzjy zzb = null;
    private Integer zzc = null;
    private Integer zzd = null;
    private Integer zze = null;

    public zzkc() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzkc zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                int iZzm = zzfljVar.zzm();
                try {
                    this.zza = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
            } else if (iZza == 18) {
                if (this.zzb == null) {
                    this.zzb = new zzjy();
                }
                zzfljVar.zza(this.zzb);
            } else if (iZza == 24) {
                this.zzc = Integer.valueOf(zzfljVar.zzh());
            } else if (iZza == 32) {
                this.zzd = Integer.valueOf(zzfljVar.zzh());
            } else if (iZza == 40) {
                this.zze = Integer.valueOf(zzfljVar.zzh());
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        Integer num = this.zza;
        if (num != null) {
            iZza += zzflk.zzb(1, num.intValue());
        }
        zzjy zzjyVar = this.zzb;
        if (zzjyVar != null) {
            iZza += zzflk.zzb(2, zzjyVar);
        }
        Integer num2 = this.zzc;
        if (num2 != null) {
            iZza += zzflk.zzb(3, num2.intValue());
        }
        Integer num3 = this.zzd;
        if (num3 != null) {
            iZza += zzflk.zzb(4, num3.intValue());
        }
        Integer num4 = this.zze;
        return num4 != null ? iZza + zzflk.zzb(5, num4.intValue()) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(1, num.intValue());
        }
        zzjy zzjyVar = this.zzb;
        if (zzjyVar != null) {
            zzflkVar.zza(2, zzjyVar);
        }
        Integer num2 = this.zzc;
        if (num2 != null) {
            zzflkVar.zza(3, num2.intValue());
        }
        Integer num3 = this.zzd;
        if (num3 != null) {
            zzflkVar.zza(4, num3.intValue());
        }
        Integer num4 = this.zze;
        if (num4 != null) {
            zzflkVar.zza(5, num4.intValue());
        }
        super.zza(zzflkVar);
    }
}
