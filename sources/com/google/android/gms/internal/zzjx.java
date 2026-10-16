package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjx extends zzflm<zzjx> {
    private Integer zza = null;
    private zzjy zzb = null;
    private zzju zzc = null;

    public zzjx() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjx zza(zzflj zzfljVar) throws IOException {
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza != 8) {
                if (iZza == 18) {
                    if (this.zzb == null) {
                        this.zzb = new zzjy();
                    }
                    zzflsVar = this.zzb;
                } else if (iZza == 26) {
                    if (this.zzc == null) {
                        this.zzc = new zzju();
                    }
                    zzflsVar = this.zzc;
                } else if (!super.zza(zzfljVar, iZza)) {
                    return this;
                }
                zzfljVar.zza(zzflsVar);
            } else {
                int iZzm = zzfljVar.zzm();
                try {
                    this.zza = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
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
        zzju zzjuVar = this.zzc;
        return zzjuVar != null ? iZza + zzflk.zzb(3, zzjuVar) : iZza;
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
        zzju zzjuVar = this.zzc;
        if (zzjuVar != null) {
            zzflkVar.zza(3, zzjuVar);
        }
        super.zza(zzflkVar);
    }
}
