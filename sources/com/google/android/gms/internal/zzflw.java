package com.google.android.gms.internal;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzflw extends zzflm<zzflw> {
    public Integer zza = null;
    private Integer zzi = null;
    public String zzb = null;
    public String zzc = null;
    private String zzj = null;
    public zzflx zzd = null;
    public zzfme[] zze = zzfme.zzb();
    public String zzf = null;
    public zzfmd zzg = null;
    private Boolean zzk = null;
    private String[] zzl = zzflv.zzf;
    private String zzm = null;
    private Boolean zzn = null;
    private Boolean zzo = null;
    private byte[] zzp = null;
    public zzfmf zzh = null;

    public zzflw() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzflw zza(zzflj zzfljVar) throws IOException {
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 10:
                    this.zzb = zzfljVar.zze();
                    continue;
                case 18:
                    this.zzc = zzfljVar.zze();
                    continue;
                case 26:
                    this.zzj = zzfljVar.zze();
                    continue;
                case 34:
                    int iZza2 = zzflv.zza(zzfljVar, 34);
                    zzfme[] zzfmeVarArr = this.zze;
                    int length = zzfmeVarArr == null ? 0 : zzfmeVarArr.length;
                    int i = iZza2 + length;
                    zzfls[] zzflsVarArr = new zzfme[i];
                    if (length != 0) {
                        System.arraycopy(this.zze, 0, zzflsVarArr, 0, length);
                    }
                    while (length < i - 1) {
                        zzflsVarArr[length] = new zzfme();
                        zzfljVar.zza(zzflsVarArr[length]);
                        zzfljVar.zza();
                        length++;
                    }
                    zzflsVarArr[length] = new zzfme();
                    zzfljVar.zza(zzflsVarArr[length]);
                    this.zze = zzflsVarArr;
                    continue;
                case 40:
                    this.zzk = Boolean.valueOf(zzfljVar.zzd());
                    continue;
                case 50:
                    int iZza3 = zzflv.zza(zzfljVar, 50);
                    String[] strArr = this.zzl;
                    int length2 = strArr == null ? 0 : strArr.length;
                    int i2 = iZza3 + length2;
                    String[] strArr2 = new String[i2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzl, 0, strArr2, 0, length2);
                    }
                    while (length2 < i2 - 1) {
                        strArr2[length2] = zzfljVar.zze();
                        zzfljVar.zza();
                        length2++;
                    }
                    strArr2[length2] = zzfljVar.zze();
                    this.zzl = strArr2;
                    continue;
                case 58:
                    this.zzm = zzfljVar.zze();
                    continue;
                case 64:
                    this.zzn = Boolean.valueOf(zzfljVar.zzd());
                    continue;
                case 72:
                    this.zzo = Boolean.valueOf(zzfljVar.zzd());
                    continue;
                case 80:
                    zzfljVar.zzm();
                    int iZzc = zzfljVar.zzc();
                    switch (iZzc) {
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
                            this.zza = Integer.valueOf(iZzc);
                            continue;
                        default:
                            StringBuilder sb = new StringBuilder(42);
                            sb.append(iZzc);
                            sb.append(" is not a valid enum ReportType");
                            throw new IllegalArgumentException(sb.toString());
                    }
                    break;
                case 88:
                    try {
                        int iZzc2 = zzfljVar.zzc();
                        if (iZzc2 != 0 && iZzc2 != 1 && iZzc2 != 2 && iZzc2 != 3 && iZzc2 != 4) {
                            StringBuilder sb2 = new StringBuilder(39);
                            sb2.append(iZzc2);
                            sb2.append(" is not a valid enum Verdict");
                            throw new IllegalArgumentException(sb2.toString());
                        }
                        this.zzi = Integer.valueOf(iZzc2);
                        continue;
                    } catch (IllegalArgumentException unused) {
                        zzfljVar.zze(zzfljVar.zzm());
                        zza(zzfljVar, iZza);
                    }
                    break;
                case 98:
                    if (this.zzd == null) {
                        this.zzd = new zzflx();
                    }
                    zzflsVar = this.zzd;
                    break;
                case 106:
                    this.zzf = zzfljVar.zze();
                    continue;
                case 114:
                    if (this.zzg == null) {
                        this.zzg = new zzfmd();
                    }
                    zzflsVar = this.zzg;
                    break;
                case 122:
                    this.zzp = zzfljVar.zzf();
                    continue;
                case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
                    if (this.zzh == null) {
                        this.zzh = new zzfmf();
                    }
                    zzflsVar = this.zzh;
                    break;
                default:
                    if (!super.zza(zzfljVar, iZza)) {
                        return this;
                    }
                    continue;
                    break;
            }
            zzfljVar.zza(zzflsVar);
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        String str = this.zzb;
        if (str != null) {
            iZza += zzflk.zzb(1, str);
        }
        String str2 = this.zzc;
        if (str2 != null) {
            iZza += zzflk.zzb(2, str2);
        }
        String str3 = this.zzj;
        if (str3 != null) {
            iZza += zzflk.zzb(3, str3);
        }
        zzfme[] zzfmeVarArr = this.zze;
        int i = 0;
        if (zzfmeVarArr != null && zzfmeVarArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zze;
                if (i2 >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i2];
                if (zzflsVar != null) {
                    iZza += zzflk.zzb(4, zzflsVar);
                }
                i2++;
            }
        }
        Boolean bool = this.zzk;
        if (bool != null) {
            bool.booleanValue();
            iZza += zzflk.zzb(5) + 1;
        }
        String[] strArr = this.zzl;
        if (strArr != null && strArr.length > 0) {
            int iZza2 = 0;
            int i3 = 0;
            while (true) {
                String[] strArr2 = this.zzl;
                if (i >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i];
                if (str4 != null) {
                    i3++;
                    iZza2 += zzflk.zza(str4);
                }
                i++;
            }
            iZza = iZza + iZza2 + (i3 * 1);
        }
        String str5 = this.zzm;
        if (str5 != null) {
            iZza += zzflk.zzb(7, str5);
        }
        Boolean bool2 = this.zzn;
        if (bool2 != null) {
            bool2.booleanValue();
            iZza += zzflk.zzb(8) + 1;
        }
        Boolean bool3 = this.zzo;
        if (bool3 != null) {
            bool3.booleanValue();
            iZza += zzflk.zzb(9) + 1;
        }
        Integer num = this.zza;
        if (num != null) {
            iZza += zzflk.zzb(10, num.intValue());
        }
        Integer num2 = this.zzi;
        if (num2 != null) {
            iZza += zzflk.zzb(11, num2.intValue());
        }
        zzflx zzflxVar = this.zzd;
        if (zzflxVar != null) {
            iZza += zzflk.zzb(12, zzflxVar);
        }
        String str6 = this.zzf;
        if (str6 != null) {
            iZza += zzflk.zzb(13, str6);
        }
        zzfmd zzfmdVar = this.zzg;
        if (zzfmdVar != null) {
            iZza += zzflk.zzb(14, zzfmdVar);
        }
        byte[] bArr = this.zzp;
        if (bArr != null) {
            iZza += zzflk.zzb(15, bArr);
        }
        zzfmf zzfmfVar = this.zzh;
        return zzfmfVar != null ? iZza + zzflk.zzb(17, zzfmfVar) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zzb;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        String str2 = this.zzc;
        if (str2 != null) {
            zzflkVar.zza(2, str2);
        }
        String str3 = this.zzj;
        if (str3 != null) {
            zzflkVar.zza(3, str3);
        }
        zzfme[] zzfmeVarArr = this.zze;
        int i = 0;
        if (zzfmeVarArr != null && zzfmeVarArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfls[] zzflsVarArr = this.zze;
                if (i2 >= zzflsVarArr.length) {
                    break;
                }
                zzfls zzflsVar = zzflsVarArr[i2];
                if (zzflsVar != null) {
                    zzflkVar.zza(4, zzflsVar);
                }
                i2++;
            }
        }
        Boolean bool = this.zzk;
        if (bool != null) {
            zzflkVar.zza(5, bool.booleanValue());
        }
        String[] strArr = this.zzl;
        if (strArr != null && strArr.length > 0) {
            while (true) {
                String[] strArr2 = this.zzl;
                if (i >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i];
                if (str4 != null) {
                    zzflkVar.zza(6, str4);
                }
                i++;
            }
        }
        String str5 = this.zzm;
        if (str5 != null) {
            zzflkVar.zza(7, str5);
        }
        Boolean bool2 = this.zzn;
        if (bool2 != null) {
            zzflkVar.zza(8, bool2.booleanValue());
        }
        Boolean bool3 = this.zzo;
        if (bool3 != null) {
            zzflkVar.zza(9, bool3.booleanValue());
        }
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(10, num.intValue());
        }
        Integer num2 = this.zzi;
        if (num2 != null) {
            zzflkVar.zza(11, num2.intValue());
        }
        zzflx zzflxVar = this.zzd;
        if (zzflxVar != null) {
            zzflkVar.zza(12, zzflxVar);
        }
        String str6 = this.zzf;
        if (str6 != null) {
            zzflkVar.zza(13, str6);
        }
        zzfmd zzfmdVar = this.zzg;
        if (zzfmdVar != null) {
            zzflkVar.zza(14, zzfmdVar);
        }
        byte[] bArr = this.zzp;
        if (bArr != null) {
            zzflkVar.zza(15, bArr);
        }
        zzfmf zzfmfVar = this.zzh;
        if (zzfmfVar != null) {
            zzflkVar.zza(17, zzfmfVar);
        }
        super.zza(zzflkVar);
    }
}
