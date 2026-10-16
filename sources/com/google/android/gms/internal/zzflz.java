package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzflz extends zzflm<zzflz> {
    private zzfma zzb = null;
    public zzfly[] zza = zzfly.zzb();
    private byte[] zzc = null;
    private byte[] zzd = null;
    private Integer zze = null;

    public zzflz() {
        this.zzax = null;
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        zzfma zzfmaVar = this.zzb;
        if (zzfmaVar != null) {
            iZza += zzflk.zzb(1, zzfmaVar);
        }
        zzfly[] zzflyVarArr = this.zza;
        if (zzflyVarArr != null && zzflyVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zza;
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
        byte[] bArr = this.zzc;
        if (bArr != null) {
            iZza += zzflk.zzb(3, bArr);
        }
        byte[] bArr2 = this.zzd;
        if (bArr2 != null) {
            iZza += zzflk.zzb(4, bArr2);
        }
        Integer num = this.zze;
        return num != null ? iZza + zzflk.zzb(5, num.intValue()) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                if (this.zzb == null) {
                    this.zzb = new zzfma();
                }
                zzfljVar.zza(this.zzb);
            } else if (iZza == 18) {
                int iZza2 = zzflv.zza(zzfljVar, 18);
                zzfly[] zzflyVarArr = this.zza;
                int length = zzflyVarArr == null ? 0 : zzflyVarArr.length;
                int i = iZza2 + length;
                zzfls[] zzflsVarArr = new zzfly[i];
                if (length != 0) {
                    System.arraycopy(this.zza, 0, zzflsVarArr, 0, length);
                }
                while (length < i - 1) {
                    zzflsVarArr[length] = new zzfly();
                    zzfljVar.zza(zzflsVarArr[length]);
                    zzfljVar.zza();
                    length++;
                }
                zzflsVarArr[length] = new zzfly();
                zzfljVar.zza(zzflsVarArr[length]);
                this.zza = zzflsVarArr;
            } else if (iZza == 26) {
                this.zzc = zzfljVar.zzf();
            } else if (iZza == 34) {
                this.zzd = zzfljVar.zzf();
            } else if (iZza == 40) {
                this.zze = Integer.valueOf(zzfljVar.zzc());
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        zzfma zzfmaVar = this.zzb;
        if (zzfmaVar != null) {
            zzflkVar.zza(1, zzfmaVar);
        }
        zzfly[] zzflyVarArr = this.zza;
        if (zzflyVarArr != null && zzflyVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zza;
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
        byte[] bArr = this.zzc;
        if (bArr != null) {
            zzflkVar.zza(3, bArr);
        }
        byte[] bArr2 = this.zzd;
        if (bArr2 != null) {
            zzflkVar.zza(4, bArr2);
        }
        Integer num = this.zze;
        if (num != null) {
            zzflkVar.zza(5, num.intValue());
        }
        super.zza(zzflkVar);
    }
}
