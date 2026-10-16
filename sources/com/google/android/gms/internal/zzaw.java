package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaw extends zzflm<zzaw> {
    public String zza = null;
    public Long zzb = null;
    private String zzc = null;
    private String zzd = null;
    private String zze = null;
    private Long zzf = null;
    private Long zzg = null;
    private String zzh = null;
    private Long zzi = null;
    private String zzj = null;

    public zzaw() {
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
        String str2 = this.zzc;
        if (str2 != null) {
            iZza += zzflk.zzb(3, str2);
        }
        String str3 = this.zzd;
        if (str3 != null) {
            iZza += zzflk.zzb(4, str3);
        }
        String str4 = this.zze;
        if (str4 != null) {
            iZza += zzflk.zzb(5, str4);
        }
        Long l2 = this.zzf;
        if (l2 != null) {
            iZza += zzflk.zze(6, l2.longValue());
        }
        Long l3 = this.zzg;
        if (l3 != null) {
            iZza += zzflk.zze(7, l3.longValue());
        }
        String str5 = this.zzh;
        if (str5 != null) {
            iZza += zzflk.zzb(8, str5);
        }
        Long l4 = this.zzi;
        if (l4 != null) {
            iZza += zzflk.zze(9, l4.longValue());
        }
        String str6 = this.zzj;
        return str6 != null ? iZza + zzflk.zzb(10, str6) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 10:
                    this.zza = zzfljVar.zze();
                    break;
                case 16:
                    this.zzb = Long.valueOf(zzfljVar.zzi());
                    break;
                case 26:
                    this.zzc = zzfljVar.zze();
                    break;
                case 34:
                    this.zzd = zzfljVar.zze();
                    break;
                case 42:
                    this.zze = zzfljVar.zze();
                    break;
                case 48:
                    this.zzf = Long.valueOf(zzfljVar.zzi());
                    break;
                case 56:
                    this.zzg = Long.valueOf(zzfljVar.zzi());
                    break;
                case 66:
                    this.zzh = zzfljVar.zze();
                    break;
                case 72:
                    this.zzi = Long.valueOf(zzfljVar.zzi());
                    break;
                case 82:
                    this.zzj = zzfljVar.zze();
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

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zza;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        Long l = this.zzb;
        if (l != null) {
            zzflkVar.zzb(2, l.longValue());
        }
        String str2 = this.zzc;
        if (str2 != null) {
            zzflkVar.zza(3, str2);
        }
        String str3 = this.zzd;
        if (str3 != null) {
            zzflkVar.zza(4, str3);
        }
        String str4 = this.zze;
        if (str4 != null) {
            zzflkVar.zza(5, str4);
        }
        Long l2 = this.zzf;
        if (l2 != null) {
            zzflkVar.zzb(6, l2.longValue());
        }
        Long l3 = this.zzg;
        if (l3 != null) {
            zzflkVar.zzb(7, l3.longValue());
        }
        String str5 = this.zzh;
        if (str5 != null) {
            zzflkVar.zza(8, str5);
        }
        Long l4 = this.zzi;
        if (l4 != null) {
            zzflkVar.zzb(9, l4.longValue());
        }
        String str6 = this.zzj;
        if (str6 != null) {
            zzflkVar.zza(10, str6);
        }
        super.zza(zzflkVar);
    }
}
