package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzje extends zzflm<zzje> {
    private static volatile zzje[] zza;
    private Integer zzb = null;
    private zzjs zzc = null;

    public zzje() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzje zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                int iZzm = zzfljVar.zzm();
                try {
                    int iZzh = zzfljVar.zzh();
                    switch (iZzh) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                            this.zzb = Integer.valueOf(iZzh);
                            continue;
                        default:
                            StringBuilder sb = new StringBuilder(44);
                            sb.append(iZzh);
                            sb.append(" is not a valid enum AdFormatType");
                            throw new IllegalArgumentException(sb.toString());
                    }
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
                zzfljVar.zze(iZzm);
                zza(zzfljVar, iZza);
            } else if (iZza == 18) {
                if (this.zzc == null) {
                    this.zzc = new zzjs();
                }
                zzfljVar.zza(this.zzc);
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public static zzje[] zzb() {
        if (zza == null) {
            synchronized (zzflq.zzb) {
                if (zza == null) {
                    zza = new zzje[0];
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
        zzjs zzjsVar = this.zzc;
        return zzjsVar != null ? iZza + zzflk.zzb(2, zzjsVar) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zzb;
        if (num != null) {
            zzflkVar.zza(1, num.intValue());
        }
        zzjs zzjsVar = this.zzc;
        if (zzjsVar != null) {
            zzflkVar.zza(2, zzjsVar);
        }
        super.zza(zzflkVar);
    }
}
