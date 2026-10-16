package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzji extends zzflm<zzji> {
    public String zza = null;
    private zzju zzc = null;
    private Integer zzd = null;
    public zzjv zzb = null;
    private Integer zze = null;
    private Integer zzf = null;
    private Integer zzg = null;
    private Integer zzh = null;

    public zzji() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzji zza(zzflj zzfljVar) throws IOException {
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza != 10) {
                if (iZza == 18) {
                    if (this.zzc == null) {
                        this.zzc = new zzju();
                    }
                    zzflsVar = this.zzc;
                } else if (iZza == 24) {
                    this.zzd = Integer.valueOf(zzfljVar.zzh());
                } else if (iZza == 34) {
                    if (this.zzb == null) {
                        this.zzb = new zzjv();
                    }
                    zzflsVar = this.zzb;
                } else if (iZza == 40) {
                    this.zze = Integer.valueOf(zzfljVar.zzh());
                } else if (iZza == 48) {
                    zzfljVar.zzm();
                    this.zzf = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } else if (iZza == 56) {
                    zzfljVar.zzm();
                    this.zzg = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } else if (iZza == 64) {
                    int iZzm = zzfljVar.zzm();
                    try {
                        this.zzh = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                    } catch (IllegalArgumentException unused) {
                        zzfljVar.zze(iZzm);
                        zza(zzfljVar, iZza);
                    }
                } else if (!super.zza(zzfljVar, iZza)) {
                    return this;
                }
                zzfljVar.zza(zzflsVar);
            } else {
                this.zza = zzfljVar.zze();
            }
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        String str = this.zza;
        if (str != null) {
            iZza += zzflk.zzb(1, str);
        }
        zzju zzjuVar = this.zzc;
        if (zzjuVar != null) {
            iZza += zzflk.zzb(2, zzjuVar);
        }
        Integer num = this.zzd;
        if (num != null) {
            iZza += zzflk.zzb(3, num.intValue());
        }
        zzjv zzjvVar = this.zzb;
        if (zzjvVar != null) {
            iZza += zzflk.zzb(4, zzjvVar);
        }
        Integer num2 = this.zze;
        if (num2 != null) {
            iZza += zzflk.zzb(5, num2.intValue());
        }
        Integer num3 = this.zzf;
        if (num3 != null) {
            iZza += zzflk.zzb(6, num3.intValue());
        }
        Integer num4 = this.zzg;
        if (num4 != null) {
            iZza += zzflk.zzb(7, num4.intValue());
        }
        Integer num5 = this.zzh;
        return num5 != null ? iZza + zzflk.zzb(8, num5.intValue()) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zza;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        zzju zzjuVar = this.zzc;
        if (zzjuVar != null) {
            zzflkVar.zza(2, zzjuVar);
        }
        Integer num = this.zzd;
        if (num != null) {
            zzflkVar.zza(3, num.intValue());
        }
        zzjv zzjvVar = this.zzb;
        if (zzjvVar != null) {
            zzflkVar.zza(4, zzjvVar);
        }
        Integer num2 = this.zze;
        if (num2 != null) {
            zzflkVar.zza(5, num2.intValue());
        }
        Integer num3 = this.zzf;
        if (num3 != null) {
            zzflkVar.zza(6, num3.intValue());
        }
        Integer num4 = this.zzg;
        if (num4 != null) {
            zzflkVar.zza(7, num4.intValue());
        }
        Integer num5 = this.zzh;
        if (num5 != null) {
            zzflkVar.zza(8, num5.intValue());
        }
        super.zza(zzflkVar);
    }
}
