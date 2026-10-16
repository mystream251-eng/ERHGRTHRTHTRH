package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjw extends zzflm<zzjw> {
    private static volatile zzjw[] zza;
    private zzka zzb = null;
    private zzkc zzc = null;
    private zzkd zzd = null;
    private zzke zze = null;
    private zzjx zzf = null;
    private zzkb zzg = null;
    private zzjz zzh = null;
    private Integer zzi = null;
    private Integer zzj = null;
    private zzju zzk = null;
    private Integer zzl = null;
    private Integer zzm = null;
    private Integer zzn = null;
    private Integer zzo = null;
    private Integer zzp = null;

    public zzjw() {
        this.zzax = null;
        this.zzay = -1;
    }

    public static zzjw[] zzb() {
        if (zza == null) {
            synchronized (zzflq.zzb) {
                if (zza == null) {
                    zza = new zzjw[0];
                }
            }
        }
        return zza;
    }

    protected final int zza() {
        int iZza = super.zza();
        zzka zzkaVar = this.zzb;
        if (zzkaVar != null) {
            iZza += zzflk.zzb(5, zzkaVar);
        }
        zzkc zzkcVar = this.zzc;
        if (zzkcVar != null) {
            iZza += zzflk.zzb(6, zzkcVar);
        }
        zzkd zzkdVar = this.zzd;
        if (zzkdVar != null) {
            iZza += zzflk.zzb(7, zzkdVar);
        }
        zzke zzkeVar = this.zze;
        if (zzkeVar != null) {
            iZza += zzflk.zzb(8, zzkeVar);
        }
        zzjx zzjxVar = this.zzf;
        if (zzjxVar != null) {
            iZza += zzflk.zzb(9, zzjxVar);
        }
        zzkb zzkbVar = this.zzg;
        if (zzkbVar != null) {
            iZza += zzflk.zzb(10, zzkbVar);
        }
        zzjz zzjzVar = this.zzh;
        if (zzjzVar != null) {
            iZza += zzflk.zzb(11, zzjzVar);
        }
        Integer num = this.zzi;
        if (num != null) {
            iZza += zzflk.zzb(12, num.intValue());
        }
        Integer num2 = this.zzj;
        if (num2 != null) {
            iZza += zzflk.zzb(13, num2.intValue());
        }
        zzju zzjuVar = this.zzk;
        if (zzjuVar != null) {
            iZza += zzflk.zzb(14, zzjuVar);
        }
        Integer num3 = this.zzl;
        if (num3 != null) {
            iZza += zzflk.zzb(15, num3.intValue());
        }
        Integer num4 = this.zzm;
        if (num4 != null) {
            iZza += zzflk.zzb(16, num4.intValue());
        }
        Integer num5 = this.zzn;
        if (num5 != null) {
            iZza += zzflk.zzb(17, num5.intValue());
        }
        Integer num6 = this.zzo;
        if (num6 != null) {
            iZza += zzflk.zzb(18, num6.intValue());
        }
        Integer num7 = this.zzp;
        return num7 != null ? iZza + zzflk.zzb(19, num7.intValue()) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 42:
                    if (this.zzb == null) {
                        this.zzb = new zzka();
                    }
                    zzflsVar = this.zzb;
                    break;
                case 50:
                    if (this.zzc == null) {
                        this.zzc = new zzkc();
                    }
                    zzflsVar = this.zzc;
                    break;
                case 58:
                    if (this.zzd == null) {
                        this.zzd = new zzkd();
                    }
                    zzflsVar = this.zzd;
                    break;
                case 66:
                    if (this.zze == null) {
                        this.zze = new zzke();
                    }
                    zzflsVar = this.zze;
                    break;
                case 74:
                    if (this.zzf == null) {
                        this.zzf = new zzjx();
                    }
                    zzflsVar = this.zzf;
                    break;
                case 82:
                    if (this.zzg == null) {
                        this.zzg = new zzkb();
                    }
                    zzflsVar = this.zzg;
                    break;
                case 90:
                    if (this.zzh == null) {
                        this.zzh = new zzjz();
                    }
                    zzflsVar = this.zzh;
                    break;
                case 96:
                    this.zzi = Integer.valueOf(zzfljVar.zzh());
                    continue;
                case 104:
                    this.zzj = Integer.valueOf(zzfljVar.zzh());
                    continue;
                case 114:
                    if (this.zzk == null) {
                        this.zzk = new zzju();
                    }
                    zzflsVar = this.zzk;
                    break;
                case 120:
                    this.zzl = Integer.valueOf(zzfljVar.zzh());
                    continue;
                case 128:
                    this.zzm = Integer.valueOf(zzfljVar.zzh());
                    continue;
                case 136:
                    this.zzn = Integer.valueOf(zzfljVar.zzh());
                    continue;
                case 144:
                    this.zzo = Integer.valueOf(zzfljVar.zzh());
                    continue;
                case 152:
                    this.zzp = Integer.valueOf(zzfljVar.zzh());
                    continue;
                default:
                    if (!super.zza(zzfljVar, iZza)) {
                        return this;
                    }
                    continue;
                    break;
            }
            zzfljVar.zza(zzflsVar);
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        zzka zzkaVar = this.zzb;
        if (zzkaVar != null) {
            zzflkVar.zza(5, zzkaVar);
        }
        zzkc zzkcVar = this.zzc;
        if (zzkcVar != null) {
            zzflkVar.zza(6, zzkcVar);
        }
        zzkd zzkdVar = this.zzd;
        if (zzkdVar != null) {
            zzflkVar.zza(7, zzkdVar);
        }
        zzke zzkeVar = this.zze;
        if (zzkeVar != null) {
            zzflkVar.zza(8, zzkeVar);
        }
        zzjx zzjxVar = this.zzf;
        if (zzjxVar != null) {
            zzflkVar.zza(9, zzjxVar);
        }
        zzkb zzkbVar = this.zzg;
        if (zzkbVar != null) {
            zzflkVar.zza(10, zzkbVar);
        }
        zzjz zzjzVar = this.zzh;
        if (zzjzVar != null) {
            zzflkVar.zza(11, zzjzVar);
        }
        Integer num = this.zzi;
        if (num != null) {
            zzflkVar.zza(12, num.intValue());
        }
        Integer num2 = this.zzj;
        if (num2 != null) {
            zzflkVar.zza(13, num2.intValue());
        }
        zzju zzjuVar = this.zzk;
        if (zzjuVar != null) {
            zzflkVar.zza(14, zzjuVar);
        }
        Integer num3 = this.zzl;
        if (num3 != null) {
            zzflkVar.zza(15, num3.intValue());
        }
        Integer num4 = this.zzm;
        if (num4 != null) {
            zzflkVar.zza(16, num4.intValue());
        }
        Integer num5 = this.zzn;
        if (num5 != null) {
            zzflkVar.zza(17, num5.intValue());
        }
        Integer num6 = this.zzo;
        if (num6 != null) {
            zzflkVar.zza(18, num6.intValue());
        }
        Integer num7 = this.zzp;
        if (num7 != null) {
            zzflkVar.zza(19, num7.intValue());
        }
        super.zza(zzflkVar);
    }
}
