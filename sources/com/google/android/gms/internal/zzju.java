package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzju extends zzflm<zzju> {
    private static volatile zzju[] zza;
    private Integer zzb = null;
    private Integer zzc = null;

    public zzju() {
        this.zzax = null;
        this.zzay = -1;
    }

    public static zzju[] zzb() {
        if (zza == null) {
            synchronized (zzflq.zzb) {
                if (zza == null) {
                    zza = new zzju[0];
                }
            }
        }
        return zza;
    }

    protected final int zza() {
        int iZza = super.zza();
        Integer num = this.zzb;
        if (num != null) {
            iZza += zzflk.zzb(1, num.intValue());
        }
        Integer num2 = this.zzc;
        return num2 != null ? iZza + zzflk.zzb(2, num2.intValue()) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                this.zzb = Integer.valueOf(zzfljVar.zzh());
            } else if (iZza == 16) {
                this.zzc = Integer.valueOf(zzfljVar.zzh());
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zzb;
        if (num != null) {
            zzflkVar.zza(1, num.intValue());
        }
        Integer num2 = this.zzc;
        if (num2 != null) {
            zzflkVar.zza(2, num2.intValue());
        }
        super.zza(zzflkVar);
    }
}
