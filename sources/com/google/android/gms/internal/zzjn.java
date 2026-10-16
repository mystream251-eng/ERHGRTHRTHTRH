package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjn extends zzflm<zzjn> {
    private zzjl zza = null;
    private zzjt[] zzb = zzjt.zzb();
    private Integer zzc = null;
    private zzju zzd = null;

    public zzjn() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjn zza(zzflj zzfljVar) throws IOException {
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                if (this.zza == null) {
                    this.zza = new zzjl();
                }
                zzflsVar = this.zza;
            } else if (iZza == 18) {
                int iZza2 = zzflv.zza(zzfljVar, 18);
                zzjt[] zzjtVarArr = this.zzb;
                int length = zzjtVarArr == null ? 0 : zzjtVarArr.length;
                int i = iZza2 + length;
                zzfls[] zzflsVarArr = new zzjt[i];
                if (length != 0) {
                    System.arraycopy(this.zzb, 0, zzflsVarArr, 0, length);
                }
                while (length < i - 1) {
                    zzflsVarArr[length] = new zzjt();
                    zzfljVar.zza(zzflsVarArr[length]);
                    zzfljVar.zza();
                    length++;
                }
                zzflsVarArr[length] = new zzjt();
                zzfljVar.zza(zzflsVarArr[length]);
                this.zzb = zzflsVarArr;
            } else if (iZza == 24) {
                int iZzm = zzfljVar.zzm();
                try {
                    this.zzc = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
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
        zzjl zzjlVar = this.zza;
        if (zzjlVar != null) {
            iZza += zzflk.zzb(1, zzjlVar);
        }
        zzjt[] zzjtVarArr = this.zzb;
        if (zzjtVarArr != null && zzjtVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zzb;
                if (i >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i];
                if (zzflsVar != null) {
                    iZza += zzflk.zzb(2, zzflsVar);
                }
                i++;
            }
        }
        Integer num = this.zzc;
        if (num != null) {
            iZza += zzflk.zzb(3, num.intValue());
        }
        zzju zzjuVar = this.zzd;
        return zzjuVar != null ? iZza + zzflk.zzb(4, zzjuVar) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        zzjl zzjlVar = this.zza;
        if (zzjlVar != null) {
            zzflkVar.zza(1, zzjlVar);
        }
        zzjt[] zzjtVarArr = this.zzb;
        if (zzjtVarArr != null && zzjtVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zzb;
                if (i >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i];
                if (zzflsVar != null) {
                    zzflkVar.zza(2, zzflsVar);
                }
                i++;
            }
        }
        Integer num = this.zzc;
        if (num != null) {
            zzflkVar.zza(3, num.intValue());
        }
        zzju zzjuVar = this.zzd;
        if (zzjuVar != null) {
            zzflkVar.zza(4, zzjuVar);
        }
        super.zza(zzflkVar);
    }
}
