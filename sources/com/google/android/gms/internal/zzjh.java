package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjh extends zzflm<zzjh> {
    private Integer zza = null;
    private zzju zzb = null;
    private zzju zzc = null;
    private zzju zzd = null;
    private zzju[] zze = zzju.zzb();
    private Integer zzf = null;

    public zzjh() {
        this.zzax = null;
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        Integer num = this.zza;
        if (num != null) {
            iZza += zzflk.zzb(1, num.intValue());
        }
        zzju zzjuVar = this.zzb;
        if (zzjuVar != null) {
            iZza += zzflk.zzb(2, zzjuVar);
        }
        zzju zzjuVar2 = this.zzc;
        if (zzjuVar2 != null) {
            iZza += zzflk.zzb(3, zzjuVar2);
        }
        zzju zzjuVar3 = this.zzd;
        if (zzjuVar3 != null) {
            iZza += zzflk.zzb(4, zzjuVar3);
        }
        zzju[] zzjuVarArr = this.zze;
        if (zzjuVarArr != null && zzjuVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zze;
                if (i >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i];
                if (zzflsVar != null) {
                    iZza += zzflk.zzb(5, zzflsVar);
                }
                i++;
            }
        }
        Integer num2 = this.zzf;
        return num2 != null ? iZza + zzflk.zzb(6, num2.intValue()) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        zzju zzjuVar;
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza != 8) {
                if (iZza == 18) {
                    if (this.zzb == null) {
                        this.zzb = new zzju();
                    }
                    zzjuVar = this.zzb;
                } else if (iZza == 26) {
                    if (this.zzc == null) {
                        this.zzc = new zzju();
                    }
                    zzjuVar = this.zzc;
                } else if (iZza == 34) {
                    if (this.zzd == null) {
                        this.zzd = new zzju();
                    }
                    zzjuVar = this.zzd;
                } else if (iZza == 42) {
                    int iZza2 = zzflv.zza(zzfljVar, 42);
                    zzju[] zzjuVarArr = this.zze;
                    int length = zzjuVarArr == null ? 0 : zzjuVarArr.length;
                    int i = iZza2 + length;
                    zzfls[] zzflsVarArr = new zzju[i];
                    if (length != 0) {
                        System.arraycopy(this.zze, 0, zzflsVarArr, 0, length);
                    }
                    while (length < i - 1) {
                        zzflsVarArr[length] = new zzju();
                        zzfljVar.zza(zzflsVarArr[length]);
                        zzfljVar.zza();
                        length++;
                    }
                    zzflsVarArr[length] = new zzju();
                    zzfljVar.zza(zzflsVarArr[length]);
                    this.zze = zzflsVarArr;
                } else if (iZza == 48) {
                    this.zzf = Integer.valueOf(zzfljVar.zzh());
                } else if (!super.zza(zzfljVar, iZza)) {
                    return this;
                }
                zzfljVar.zza(zzjuVar);
            } else {
                this.zza = Integer.valueOf(zzfljVar.zzh());
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(1, num.intValue());
        }
        zzju zzjuVar = this.zzb;
        if (zzjuVar != null) {
            zzflkVar.zza(2, zzjuVar);
        }
        zzju zzjuVar2 = this.zzc;
        if (zzjuVar2 != null) {
            zzflkVar.zza(3, zzjuVar2);
        }
        zzju zzjuVar3 = this.zzd;
        if (zzjuVar3 != null) {
            zzflkVar.zza(4, zzjuVar3);
        }
        zzju[] zzjuVarArr = this.zze;
        if (zzjuVarArr != null && zzjuVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zze;
                if (i >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i];
                if (zzflsVar != null) {
                    zzflkVar.zza(5, zzflsVar);
                }
                i++;
            }
        }
        Integer num2 = this.zzf;
        if (num2 != null) {
            zzflkVar.zza(6, num2.intValue());
        }
        super.zza(zzflkVar);
    }
}
