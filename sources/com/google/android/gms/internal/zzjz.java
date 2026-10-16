package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjz extends zzflm<zzjz> {
    private zzjv zza = null;
    private Integer zzb = null;
    private zzjy zzc = null;
    private zzju zzd = null;

    public zzjz() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjz zza(zzflj zzfljVar) throws IOException {
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                if (this.zza == null) {
                    this.zza = new zzjv();
                }
                zzflsVar = this.zza;
            } else if (iZza == 16) {
                int iZzm = zzfljVar.zzm();
                try {
                    this.zzb = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
            } else if (iZza == 26) {
                if (this.zzc == null) {
                    this.zzc = new zzjy();
                }
                zzflsVar = this.zzc;
            } else if (iZza == 34) {
                if (this.zzd == null) {
                    this.zzd = new zzju();
                }
                zzflsVar = this.zzd;
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
            zzfljVar.zza(zzflsVar);
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        zzjv zzjvVar = this.zza;
        if (zzjvVar != null) {
            iZza += zzflk.zzb(1, zzjvVar);
        }
        Integer num = this.zzb;
        if (num != null) {
            iZza += zzflk.zzb(2, num.intValue());
        }
        zzjy zzjyVar = this.zzc;
        if (zzjyVar != null) {
            iZza += zzflk.zzb(3, zzjyVar);
        }
        zzju zzjuVar = this.zzd;
        return zzjuVar != null ? iZza + zzflk.zzb(4, zzjuVar) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        zzjv zzjvVar = this.zza;
        if (zzjvVar != null) {
            zzflkVar.zza(1, zzjvVar);
        }
        Integer num = this.zzb;
        if (num != null) {
            zzflkVar.zza(2, num.intValue());
        }
        zzjy zzjyVar = this.zzc;
        if (zzjyVar != null) {
            zzflkVar.zza(3, zzjyVar);
        }
        zzju zzjuVar = this.zzd;
        if (zzjuVar != null) {
            zzflkVar.zza(4, zzjuVar);
        }
        super.zza(zzflkVar);
    }
}
