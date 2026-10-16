package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjy extends zzflm<zzjy> {
    private Integer zza = null;

    public zzjy() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjy zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                int iZzm = zzfljVar.zzm();
                try {
                    int iZzh = zzfljVar.zzh();
                    if (iZzh != 0 && iZzh != 1 && iZzh != 2 && iZzh != 3) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(iZzh);
                        sb.append(" is not a valid enum VideoErrorCode");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.zza = Integer.valueOf(iZzh);
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
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
        return num != null ? iZza + zzflk.zzb(1, num.intValue()) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(1, num.intValue());
        }
        super.zza(zzflkVar);
    }
}
