package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjf extends zzflm<zzjf> {
    private String zza = null;
    private zzje[] zzb = zzje.zzb();
    private Integer zzc = null;

    public zzjf() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjf zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                this.zza = zzfljVar.zze();
            } else if (iZza == 18) {
                int iZza2 = zzflv.zza(zzfljVar, 18);
                zzje[] zzjeVarArr = this.zzb;
                int length = zzjeVarArr == null ? 0 : zzjeVarArr.length;
                int i = iZza2 + length;
                zzfls[] zzflsVarArr = new zzje[i];
                if (length != 0) {
                    System.arraycopy(this.zzb, 0, zzflsVarArr, 0, length);
                }
                while (length < i - 1) {
                    zzflsVarArr[length] = new zzje();
                    zzfljVar.zza(zzflsVarArr[length]);
                    zzfljVar.zza();
                    length++;
                }
                zzflsVarArr[length] = new zzje();
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
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        String str = this.zza;
        if (str != null) {
            iZza += zzflk.zzb(1, str);
        }
        zzje[] zzjeVarArr = this.zzb;
        if (zzjeVarArr != null && zzjeVarArr.length > 0) {
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
        return num != null ? iZza + zzflk.zzb(3, num.intValue()) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zza;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        zzje[] zzjeVarArr = this.zzb;
        if (zzjeVarArr != null && zzjeVarArr.length > 0) {
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
        super.zza(zzflkVar);
    }
}
