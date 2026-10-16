package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzke extends zzflm<zzke> {
    private Integer zza = null;
    private zzjy zzb = null;

    public zzke() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzke zza(zzflj zzfljVar) throws IOException {
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
        return zzjyVar != null ? iZza + zzflk.zzb(2, zzjyVar) : iZza;
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
        super.zza(zzflkVar);
    }
}
