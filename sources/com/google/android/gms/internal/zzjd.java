package com.google.android.gms.internal;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjd extends zzflm<zzjd> {
    public Integer zza = null;
    private Integer zzc = null;
    private zzjf zzd = null;
    public zzjg zzb = null;
    private zzje[] zze = zzje.zzb();
    private zzjh zzf = null;
    private zzjq zzg = null;
    private zzjp zzh = null;
    private zzjm zzi = null;
    private zzjn zzj = null;
    private zzjw[] zzk = zzjw.zzb();

    public zzjd() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjd zza(zzflj zzfljVar) throws IOException {
        int iZzm;
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 56:
                    iZzm = zzfljVar.zzm();
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
                            this.zza = Integer.valueOf(iZzh);
                            continue;
                        default:
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(iZzh);
                            sb.append(" is not a valid enum AdInitiater");
                            throw new IllegalArgumentException(sb.toString());
                    }
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                    break;
                case 64:
                    iZzm = zzfljVar.zzm();
                    try {
                        this.zzc = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                    } catch (IllegalArgumentException unused) {
                        zzfljVar.zze(iZzm);
                        zza(zzfljVar, iZza);
                    }
                    break;
                case 74:
                    if (this.zzd == null) {
                        this.zzd = new zzjf();
                    }
                    zzflsVar = this.zzd;
                    zzfljVar.zza(zzflsVar);
                    break;
                case 82:
                    if (this.zzb == null) {
                        this.zzb = new zzjg();
                    }
                    zzflsVar = this.zzb;
                    zzfljVar.zza(zzflsVar);
                    break;
                case 90:
                    int iZza2 = zzflv.zza(zzfljVar, 90);
                    zzje[] zzjeVarArr = this.zze;
                    int length = zzjeVarArr == null ? 0 : zzjeVarArr.length;
                    int i = iZza2 + length;
                    zzfls[] zzflsVarArr = new zzje[i];
                    if (length != 0) {
                        System.arraycopy(this.zze, 0, zzflsVarArr, 0, length);
                    }
                    while (length < i - 1) {
                        zzflsVarArr[length] = new zzje();
                        zzfljVar.zza(zzflsVarArr[length]);
                        zzfljVar.zza();
                        length++;
                    }
                    zzflsVarArr[length] = new zzje();
                    zzfljVar.zza(zzflsVarArr[length]);
                    this.zze = zzflsVarArr;
                    break;
                case 98:
                    if (this.zzf == null) {
                        this.zzf = new zzjh();
                    }
                    zzflsVar = this.zzf;
                    zzfljVar.zza(zzflsVar);
                    break;
                case 106:
                    if (this.zzg == null) {
                        this.zzg = new zzjq();
                    }
                    zzflsVar = this.zzg;
                    zzfljVar.zza(zzflsVar);
                    break;
                case 114:
                    if (this.zzh == null) {
                        this.zzh = new zzjp();
                    }
                    zzflsVar = this.zzh;
                    zzfljVar.zza(zzflsVar);
                    break;
                case 122:
                    if (this.zzi == null) {
                        this.zzi = new zzjm();
                    }
                    zzflsVar = this.zzi;
                    zzfljVar.zza(zzflsVar);
                    break;
                case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                    if (this.zzj == null) {
                        this.zzj = new zzjn();
                    }
                    zzflsVar = this.zzj;
                    zzfljVar.zza(zzflsVar);
                    break;
                case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
                    int iZza3 = zzflv.zza(zzfljVar, TsExtractor.TS_STREAM_TYPE_DTS);
                    zzjw[] zzjwVarArr = this.zzk;
                    int length2 = zzjwVarArr == null ? 0 : zzjwVarArr.length;
                    int i2 = iZza3 + length2;
                    zzfls[] zzflsVarArr2 = new zzjw[i2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzk, 0, zzflsVarArr2, 0, length2);
                    }
                    while (length2 < i2 - 1) {
                        zzflsVarArr2[length2] = new zzjw();
                        zzfljVar.zza(zzflsVarArr2[length2]);
                        zzfljVar.zza();
                        length2++;
                    }
                    zzflsVarArr2[length2] = new zzjw();
                    zzfljVar.zza(zzflsVarArr2[length2]);
                    this.zzk = zzflsVarArr2;
                    break;
                default:
                    if (!super.zza(zzfljVar, iZza)) {
                        return this;
                    }
                    break;
                    break;
            }
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        Integer num = this.zza;
        if (num != null) {
            iZza += zzflk.zzb(7, num.intValue());
        }
        Integer num2 = this.zzc;
        if (num2 != null) {
            iZza += zzflk.zzb(8, num2.intValue());
        }
        zzjf zzjfVar = this.zzd;
        if (zzjfVar != null) {
            iZza += zzflk.zzb(9, zzjfVar);
        }
        zzjg zzjgVar = this.zzb;
        if (zzjgVar != null) {
            iZza += zzflk.zzb(10, zzjgVar);
        }
        zzje[] zzjeVarArr = this.zze;
        int i = 0;
        if (zzjeVarArr != null && zzjeVarArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zze;
                if (i2 >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i2];
                if (zzflsVar != null) {
                    iZza += zzflk.zzb(11, zzflsVar);
                }
                i2++;
            }
        }
        zzjh zzjhVar = this.zzf;
        if (zzjhVar != null) {
            iZza += zzflk.zzb(12, zzjhVar);
        }
        zzjq zzjqVar = this.zzg;
        if (zzjqVar != null) {
            iZza += zzflk.zzb(13, zzjqVar);
        }
        zzjp zzjpVar = this.zzh;
        if (zzjpVar != null) {
            iZza += zzflk.zzb(14, zzjpVar);
        }
        zzjm zzjmVar = this.zzi;
        if (zzjmVar != null) {
            iZza += zzflk.zzb(15, zzjmVar);
        }
        zzjn zzjnVar = this.zzj;
        if (zzjnVar != null) {
            iZza += zzflk.zzb(16, zzjnVar);
        }
        zzjw[] zzjwVarArr = this.zzk;
        if (zzjwVarArr != null && zzjwVarArr.length > 0) {
            while (true) {
                zzfls[] zzflsVarArr2 = this.zzk;
                if (i >= zzflsVarArr2.length) {
                    break;
                }
                zzfls zzflsVar2 = zzflsVarArr2[i];
                if (zzflsVar2 != null) {
                    iZza += zzflk.zzb(17, zzflsVar2);
                }
                i++;
            }
        }
        return iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(7, num.intValue());
        }
        Integer num2 = this.zzc;
        if (num2 != null) {
            zzflkVar.zza(8, num2.intValue());
        }
        zzjf zzjfVar = this.zzd;
        if (zzjfVar != null) {
            zzflkVar.zza(9, zzjfVar);
        }
        zzjg zzjgVar = this.zzb;
        if (zzjgVar != null) {
            zzflkVar.zza(10, zzjgVar);
        }
        zzje[] zzjeVarArr = this.zze;
        int i = 0;
        if (zzjeVarArr != null && zzjeVarArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zze;
                if (i2 >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i2];
                if (zzflsVar != null) {
                    zzflkVar.zza(11, zzflsVar);
                }
                i2++;
            }
        }
        zzjh zzjhVar = this.zzf;
        if (zzjhVar != null) {
            zzflkVar.zza(12, zzjhVar);
        }
        zzjq zzjqVar = this.zzg;
        if (zzjqVar != null) {
            zzflkVar.zza(13, zzjqVar);
        }
        zzjp zzjpVar = this.zzh;
        if (zzjpVar != null) {
            zzflkVar.zza(14, zzjpVar);
        }
        zzjm zzjmVar = this.zzi;
        if (zzjmVar != null) {
            zzflkVar.zza(15, zzjmVar);
        }
        zzjn zzjnVar = this.zzj;
        if (zzjnVar != null) {
            zzflkVar.zza(16, zzjnVar);
        }
        zzjw[] zzjwVarArr = this.zzk;
        if (zzjwVarArr != null && zzjwVarArr.length > 0) {
            while (true) {
                zzfls[] zzflsVarArr2 = this.zzk;
                if (i >= zzflsVarArr2.length) {
                    break;
                }
                zzfls zzflsVar2 = zzflsVarArr2[i];
                if (zzflsVar2 != null) {
                    zzflkVar.zza(17, zzflsVar2);
                }
                i++;
            }
        }
        super.zza(zzflkVar);
    }
}
