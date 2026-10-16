package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjq extends zzflm<zzjq> {
    private Integer zza = null;
    private Integer zzb = null;
    private Integer zzc = null;
    private Integer zzd = null;
    private Integer zze = null;
    private Integer zzf = null;
    private Integer zzg = null;
    private Integer zzh = null;
    private Integer zzi = null;
    private Integer zzj = null;
    private zzjr zzk = null;

    public zzjq() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjq zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 8:
                    zzfljVar.zzm();
                    this.zza = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                    break;
                case 16:
                    int iZzm = zzfljVar.zzm();
                    try {
                        this.zzb = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                    } catch (IllegalArgumentException unused) {
                        zzfljVar.zze(iZzm);
                        zza(zzfljVar, iZza);
                    }
                    break;
                case 24:
                    this.zzc = Integer.valueOf(zzfljVar.zzh());
                    break;
                case 32:
                    this.zzd = Integer.valueOf(zzfljVar.zzh());
                    break;
                case 40:
                    this.zze = Integer.valueOf(zzfljVar.zzh());
                    break;
                case 48:
                    this.zzf = Integer.valueOf(zzfljVar.zzh());
                    break;
                case 56:
                    this.zzg = Integer.valueOf(zzfljVar.zzh());
                    break;
                case 64:
                    this.zzh = Integer.valueOf(zzfljVar.zzh());
                    break;
                case 72:
                    this.zzi = Integer.valueOf(zzfljVar.zzh());
                    break;
                case 80:
                    this.zzj = Integer.valueOf(zzfljVar.zzh());
                    break;
                case 90:
                    if (this.zzk == null) {
                        this.zzk = new zzjr();
                    }
                    zzfljVar.zza(this.zzk);
                    break;
                default:
                    if (!super.zza(zzfljVar, iZza)) {
                        return this;
                    }
                    break;
                    break;
            }
        }
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
        if (num3 != null) {
            iZza += zzflk.zzb(3, num3.intValue());
        }
        Integer num4 = this.zzd;
        if (num4 != null) {
            iZza += zzflk.zzb(4, num4.intValue());
        }
        Integer num5 = this.zze;
        if (num5 != null) {
            iZza += zzflk.zzb(5, num5.intValue());
        }
        Integer num6 = this.zzf;
        if (num6 != null) {
            iZza += zzflk.zzb(6, num6.intValue());
        }
        Integer num7 = this.zzg;
        if (num7 != null) {
            iZza += zzflk.zzb(7, num7.intValue());
        }
        Integer num8 = this.zzh;
        if (num8 != null) {
            iZza += zzflk.zzb(8, num8.intValue());
        }
        Integer num9 = this.zzi;
        if (num9 != null) {
            iZza += zzflk.zzb(9, num9.intValue());
        }
        Integer num10 = this.zzj;
        if (num10 != null) {
            iZza += zzflk.zzb(10, num10.intValue());
        }
        zzjr zzjrVar = this.zzk;
        return zzjrVar != null ? iZza + zzflk.zzb(11, zzjrVar) : iZza;
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
        Integer num4 = this.zzd;
        if (num4 != null) {
            zzflkVar.zza(4, num4.intValue());
        }
        Integer num5 = this.zze;
        if (num5 != null) {
            zzflkVar.zza(5, num5.intValue());
        }
        Integer num6 = this.zzf;
        if (num6 != null) {
            zzflkVar.zza(6, num6.intValue());
        }
        Integer num7 = this.zzg;
        if (num7 != null) {
            zzflkVar.zza(7, num7.intValue());
        }
        Integer num8 = this.zzh;
        if (num8 != null) {
            zzflkVar.zza(8, num8.intValue());
        }
        Integer num9 = this.zzi;
        if (num9 != null) {
            zzflkVar.zza(9, num9.intValue());
        }
        Integer num10 = this.zzj;
        if (num10 != null) {
            zzflkVar.zza(10, num10.intValue());
        }
        zzjr zzjrVar = this.zzk;
        if (zzjrVar != null) {
            zzflkVar.zza(11, zzjrVar);
        }
        super.zza(zzflkVar);
    }
}
