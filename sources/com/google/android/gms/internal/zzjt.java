package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjt extends zzflm<zzjt> {
    private static volatile zzjt[] zza;
    private String zzb = null;
    private Integer zzc = null;
    private zzju zzd = null;

    public zzjt() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjt zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                this.zzb = zzfljVar.zze();
            } else if (iZza == 16) {
                int iZzm = zzfljVar.zzm();
                try {
                    this.zzc = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
            } else if (iZza == 26) {
                if (this.zzd == null) {
                    this.zzd = new zzju();
                }
                zzfljVar.zza(this.zzd);
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public static zzjt[] zzb() {
        if (zza == null) {
            synchronized (zzflq.zzb) {
                if (zza == null) {
                    zza = new zzjt[0];
                }
            }
        }
        return zza;
    }

    protected final int zza() {
        int iZza = super.zza();
        String str = this.zzb;
        if (str != null) {
            iZza += zzflk.zzb(1, str);
        }
        Integer num = this.zzc;
        if (num != null) {
            iZza += zzflk.zzb(2, num.intValue());
        }
        zzju zzjuVar = this.zzd;
        return zzjuVar != null ? iZza + zzflk.zzb(3, zzjuVar) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zzb;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        Integer num = this.zzc;
        if (num != null) {
            zzflkVar.zza(2, num.intValue());
        }
        zzju zzjuVar = this.zzd;
        if (zzjuVar != null) {
            zzflkVar.zza(3, zzjuVar);
        }
        super.zza(zzflkVar);
    }
}
