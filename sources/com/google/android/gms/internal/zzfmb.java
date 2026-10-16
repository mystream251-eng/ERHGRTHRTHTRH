package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfmb extends zzflm<zzfmb> {
    private zzfmc zza = null;
    private zzfly[] zzb = zzfly.zzb();
    private byte[] zzc = null;
    private byte[] zzd = null;
    private Integer zze = null;
    private byte[] zzf = null;

    public zzfmb() {
        this.zzax = null;
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        zzfmc zzfmcVar = this.zza;
        if (zzfmcVar != null) {
            iZza += zzflk.zzb(1, zzfmcVar);
        }
        zzfly[] zzflyVarArr = this.zzb;
        if (zzflyVarArr != null && zzflyVarArr.length > 0) {
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
        byte[] bArr = this.zzc;
        if (bArr != null) {
            iZza += zzflk.zzb(3, bArr);
        }
        byte[] bArr2 = this.zzd;
        if (bArr2 != null) {
            iZza += zzflk.zzb(4, bArr2);
        }
        Integer num = this.zze;
        if (num != null) {
            iZza += zzflk.zzb(5, num.intValue());
        }
        byte[] bArr3 = this.zzf;
        return bArr3 != null ? iZza + zzflk.zzb(6, bArr3) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                if (this.zza == null) {
                    this.zza = new zzfmc();
                }
                zzfljVar.zza(this.zza);
            } else if (iZza == 18) {
                int iZza2 = zzflv.zza(zzfljVar, 18);
                zzfly[] zzflyVarArr = this.zzb;
                int length = zzflyVarArr == null ? 0 : zzflyVarArr.length;
                int i = iZza2 + length;
                zzfls[] zzflsVarArr = new zzfly[i];
                if (length != 0) {
                    System.arraycopy(this.zzb, 0, zzflsVarArr, 0, length);
                }
                while (length < i - 1) {
                    zzflsVarArr[length] = new zzfly();
                    zzfljVar.zza(zzflsVarArr[length]);
                    zzfljVar.zza();
                    length++;
                }
                zzflsVarArr[length] = new zzfly();
                zzfljVar.zza(zzflsVarArr[length]);
                this.zzb = zzflsVarArr;
            } else if (iZza == 26) {
                this.zzc = zzfljVar.zzf();
            } else if (iZza == 34) {
                this.zzd = zzfljVar.zzf();
            } else if (iZza == 40) {
                this.zze = Integer.valueOf(zzfljVar.zzc());
            } else if (iZza == 50) {
                this.zzf = zzfljVar.zzf();
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        zzfmc zzfmcVar = this.zza;
        if (zzfmcVar != null) {
            zzflkVar.zza(1, zzfmcVar);
        }
        zzfly[] zzflyVarArr = this.zzb;
        if (zzflyVarArr != null && zzflyVarArr.length > 0) {
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
        byte[] bArr3 = this.zzf;
        if (bArr3 != null) {
            zzflkVar.zza(6, bArr3);
        }
        super.zza(zzflkVar);
    }
}
