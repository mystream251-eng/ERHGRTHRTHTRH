package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjj extends zzflm<zzjj> {
    private Integer zza = null;
    private zzjv zzb = null;
    private String zzc = null;
    private String zzd = null;

    public zzjj() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjj zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 40) {
                int iZzm = zzfljVar.zzm();
                try {
                    int iZzh = zzfljVar.zzh();
                    if (iZzh != 0 && iZzh != 1 && iZzh != 2) {
                        StringBuilder sb = new StringBuilder(40);
                        sb.append(iZzh);
                        sb.append(" is not a valid enum Platform");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.zza = Integer.valueOf(iZzh);
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
            } else if (iZza == 50) {
                if (this.zzb == null) {
                    this.zzb = new zzjv();
                }
                zzfljVar.zza(this.zzb);
            } else if (iZza == 58) {
                this.zzc = zzfljVar.zze();
            } else if (iZza == 66) {
                this.zzd = zzfljVar.zze();
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        Integer num = this.zza;
        if (num != null) {
            iZza += zzflk.zzb(5, num.intValue());
        }
        zzjv zzjvVar = this.zzb;
        if (zzjvVar != null) {
            iZza += zzflk.zzb(6, zzjvVar);
        }
        String str = this.zzc;
        if (str != null) {
            iZza += zzflk.zzb(7, str);
        }
        String str2 = this.zzd;
        return str2 != null ? iZza + zzflk.zzb(8, str2) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(5, num.intValue());
        }
        zzjv zzjvVar = this.zzb;
        if (zzjvVar != null) {
            zzflkVar.zza(6, zzjvVar);
        }
        String str = this.zzc;
        if (str != null) {
            zzflkVar.zza(7, str);
        }
        String str2 = this.zzd;
        if (str2 != null) {
            zzflkVar.zza(8, str2);
        }
        super.zza(zzflkVar);
    }
}
