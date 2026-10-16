package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjo extends zzflm<zzjo> {
    private Integer zza = null;
    private Integer zzb = null;

    public zzjo() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjo zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                zzfljVar.zzm();
                int iZzh = zzfljVar.zzh();
                if (iZzh != 0 && iZzh != 1 && iZzh != 2) {
                    StringBuilder sb = new StringBuilder(43);
                    sb.append(iZzh);
                    sb.append(" is not a valid enum NetworkType");
                    throw new IllegalArgumentException(sb.toString());
                }
                this.zza = Integer.valueOf(iZzh);
            } else if (iZza == 16) {
                try {
                    int iZzh2 = zzfljVar.zzh();
                    if (iZzh2 != 0 && iZzh2 != 1 && iZzh2 != 2 && iZzh2 != 4) {
                        StringBuilder sb2 = new StringBuilder(51);
                        sb2.append(iZzh2);
                        sb2.append(" is not a valid enum CellularNetworkType");
                        throw new IllegalArgumentException(sb2.toString());
                    }
                    this.zzb = Integer.valueOf(iZzh2);
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(zzfljVar.zzm());
                    zza(zzfljVar, iZza);
                }
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
        Integer num2 = this.zzb;
        return num2 != null ? iZza + zzflk.zzb(2, num2.intValue()) : iZza;
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
        super.zza(zzflkVar);
    }
}
