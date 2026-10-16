package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbb extends zzflm<zzbb> {
    private static volatile zzbb[] zzt;
    public Integer zzg;
    public Integer zzk;
    public Long zza = null;
    public Long zzb = null;
    public Long zzc = null;
    public Long zzd = null;
    public Long zze = null;
    public Long zzf = null;
    public Long zzh = null;
    public Long zzi = null;
    public Long zzj = null;
    public Long zzl = null;
    public Long zzm = null;
    public Long zzn = null;
    public Long zzo = null;
    public Long zzp = null;
    public Long zzq = null;
    public Long zzr = null;
    public Long zzs = null;
    private Long zzu = null;
    private Long zzv = null;

    public zzbb() {
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzbb zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 8:
                    this.zza = Long.valueOf(zzfljVar.zzi());
                    break;
                case 16:
                    this.zzb = Long.valueOf(zzfljVar.zzi());
                    break;
                case 24:
                    this.zzc = Long.valueOf(zzfljVar.zzi());
                    break;
                case 32:
                    this.zzd = Long.valueOf(zzfljVar.zzi());
                    break;
                case 40:
                    this.zze = Long.valueOf(zzfljVar.zzi());
                    break;
                case 48:
                    this.zzf = Long.valueOf(zzfljVar.zzi());
                    break;
                case 56:
                    zzfljVar.zzm();
                    this.zzg = Integer.valueOf(zzaz.zza(zzfljVar.zzh()));
                    break;
                case 64:
                    this.zzh = Long.valueOf(zzfljVar.zzi());
                    break;
                case 72:
                    this.zzi = Long.valueOf(zzfljVar.zzi());
                    break;
                case 80:
                    this.zzj = Long.valueOf(zzfljVar.zzi());
                    break;
                case 88:
                    int iZzm = zzfljVar.zzm();
                    try {
                        this.zzk = Integer.valueOf(zzaz.zza(zzfljVar.zzh()));
                    } catch (IllegalArgumentException unused) {
                        zzfljVar.zze(iZzm);
                        zza(zzfljVar, iZza);
                    }
                    break;
                case 96:
                    this.zzl = Long.valueOf(zzfljVar.zzi());
                    break;
                case 104:
                    this.zzm = Long.valueOf(zzfljVar.zzi());
                    break;
                case 112:
                    this.zzn = Long.valueOf(zzfljVar.zzi());
                    break;
                case 120:
                    this.zzo = Long.valueOf(zzfljVar.zzi());
                    break;
                case 128:
                    this.zzp = Long.valueOf(zzfljVar.zzi());
                    break;
                case 136:
                    this.zzq = Long.valueOf(zzfljVar.zzi());
                    break;
                case 144:
                    this.zzr = Long.valueOf(zzfljVar.zzi());
                    break;
                case 152:
                    this.zzs = Long.valueOf(zzfljVar.zzi());
                    break;
                case 160:
                    this.zzu = Long.valueOf(zzfljVar.zzi());
                    break;
                case 168:
                    this.zzv = Long.valueOf(zzfljVar.zzi());
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

    public static zzbb[] zzb() {
        if (zzt == null) {
            synchronized (zzflq.zzb) {
                if (zzt == null) {
                    zzt = new zzbb[0];
                }
            }
        }
        return zzt;
    }

    protected final int zza() {
        int iZza = super.zza();
        Long l = this.zza;
        if (l != null) {
            iZza += zzflk.zze(1, l.longValue());
        }
        Long l2 = this.zzb;
        if (l2 != null) {
            iZza += zzflk.zze(2, l2.longValue());
        }
        Long l3 = this.zzc;
        if (l3 != null) {
            iZza += zzflk.zze(3, l3.longValue());
        }
        Long l4 = this.zzd;
        if (l4 != null) {
            iZza += zzflk.zze(4, l4.longValue());
        }
        Long l5 = this.zze;
        if (l5 != null) {
            iZza += zzflk.zze(5, l5.longValue());
        }
        Long l6 = this.zzf;
        if (l6 != null) {
            iZza += zzflk.zze(6, l6.longValue());
        }
        Integer num = this.zzg;
        if (num != null) {
            iZza += zzflk.zzb(7, num.intValue());
        }
        Long l7 = this.zzh;
        if (l7 != null) {
            iZza += zzflk.zze(8, l7.longValue());
        }
        Long l8 = this.zzi;
        if (l8 != null) {
            iZza += zzflk.zze(9, l8.longValue());
        }
        Long l9 = this.zzj;
        if (l9 != null) {
            iZza += zzflk.zze(10, l9.longValue());
        }
        Integer num2 = this.zzk;
        if (num2 != null) {
            iZza += zzflk.zzb(11, num2.intValue());
        }
        Long l10 = this.zzl;
        if (l10 != null) {
            iZza += zzflk.zze(12, l10.longValue());
        }
        Long l11 = this.zzm;
        if (l11 != null) {
            iZza += zzflk.zze(13, l11.longValue());
        }
        Long l12 = this.zzn;
        if (l12 != null) {
            iZza += zzflk.zze(14, l12.longValue());
        }
        Long l13 = this.zzo;
        if (l13 != null) {
            iZza += zzflk.zze(15, l13.longValue());
        }
        Long l14 = this.zzp;
        if (l14 != null) {
            iZza += zzflk.zze(16, l14.longValue());
        }
        Long l15 = this.zzq;
        if (l15 != null) {
            iZza += zzflk.zze(17, l15.longValue());
        }
        Long l16 = this.zzr;
        if (l16 != null) {
            iZza += zzflk.zze(18, l16.longValue());
        }
        Long l17 = this.zzs;
        if (l17 != null) {
            iZza += zzflk.zze(19, l17.longValue());
        }
        Long l18 = this.zzu;
        if (l18 != null) {
            iZza += zzflk.zze(20, l18.longValue());
        }
        Long l19 = this.zzv;
        return l19 != null ? iZza + zzflk.zze(21, l19.longValue()) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Long l = this.zza;
        if (l != null) {
            zzflkVar.zzb(1, l.longValue());
        }
        Long l2 = this.zzb;
        if (l2 != null) {
            zzflkVar.zzb(2, l2.longValue());
        }
        Long l3 = this.zzc;
        if (l3 != null) {
            zzflkVar.zzb(3, l3.longValue());
        }
        Long l4 = this.zzd;
        if (l4 != null) {
            zzflkVar.zzb(4, l4.longValue());
        }
        Long l5 = this.zze;
        if (l5 != null) {
            zzflkVar.zzb(5, l5.longValue());
        }
        Long l6 = this.zzf;
        if (l6 != null) {
            zzflkVar.zzb(6, l6.longValue());
        }
        Integer num = this.zzg;
        if (num != null) {
            zzflkVar.zza(7, num.intValue());
        }
        Long l7 = this.zzh;
        if (l7 != null) {
            zzflkVar.zzb(8, l7.longValue());
        }
        Long l8 = this.zzi;
        if (l8 != null) {
            zzflkVar.zzb(9, l8.longValue());
        }
        Long l9 = this.zzj;
        if (l9 != null) {
            zzflkVar.zzb(10, l9.longValue());
        }
        Integer num2 = this.zzk;
        if (num2 != null) {
            zzflkVar.zza(11, num2.intValue());
        }
        Long l10 = this.zzl;
        if (l10 != null) {
            zzflkVar.zzb(12, l10.longValue());
        }
        Long l11 = this.zzm;
        if (l11 != null) {
            zzflkVar.zzb(13, l11.longValue());
        }
        Long l12 = this.zzn;
        if (l12 != null) {
            zzflkVar.zzb(14, l12.longValue());
        }
        Long l13 = this.zzo;
        if (l13 != null) {
            zzflkVar.zzb(15, l13.longValue());
        }
        Long l14 = this.zzp;
        if (l14 != null) {
            zzflkVar.zzb(16, l14.longValue());
        }
        Long l15 = this.zzq;
        if (l15 != null) {
            zzflkVar.zzb(17, l15.longValue());
        }
        Long l16 = this.zzr;
        if (l16 != null) {
            zzflkVar.zzb(18, l16.longValue());
        }
        Long l17 = this.zzs;
        if (l17 != null) {
            zzflkVar.zzb(19, l17.longValue());
        }
        Long l18 = this.zzu;
        if (l18 != null) {
            zzflkVar.zzb(20, l18.longValue());
        }
        Long l19 = this.zzv;
        if (l19 != null) {
            zzflkVar.zzb(21, l19.longValue());
        }
        super.zza(zzflkVar);
    }
}
