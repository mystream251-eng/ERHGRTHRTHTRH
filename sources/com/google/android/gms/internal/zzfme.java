package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfme extends zzflm<zzfme> {
    private static volatile zzfme[] zzf;
    public Integer zza = null;
    public String zzb = null;
    public zzflz zzc = null;
    private zzfmb zzg = null;
    private Integer zzh = null;
    private int[] zzi = zzflv.zza;
    private String zzj = null;
    public Integer zzd = null;
    public String[] zze = zzflv.zzf;

    public zzfme() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzfme zza(zzflj zzfljVar) throws IOException {
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 8:
                    this.zza = Integer.valueOf(zzfljVar.zzc());
                    continue;
                case 18:
                    this.zzb = zzfljVar.zze();
                    continue;
                case 26:
                    if (this.zzc == null) {
                        this.zzc = new zzflz();
                    }
                    zzflsVar = this.zzc;
                    break;
                case 34:
                    if (this.zzg == null) {
                        this.zzg = new zzfmb();
                    }
                    zzflsVar = this.zzg;
                    break;
                case 40:
                    this.zzh = Integer.valueOf(zzfljVar.zzc());
                    continue;
                case 48:
                    int iZza2 = zzflv.zza(zzfljVar, 48);
                    int[] iArr = this.zzi;
                    int length = iArr == null ? 0 : iArr.length;
                    int i = iZza2 + length;
                    int[] iArr2 = new int[i];
                    if (length != 0) {
                        System.arraycopy(this.zzi, 0, iArr2, 0, length);
                    }
                    while (length < i - 1) {
                        iArr2[length] = zzfljVar.zzc();
                        zzfljVar.zza();
                        length++;
                    }
                    iArr2[length] = zzfljVar.zzc();
                    this.zzi = iArr2;
                    continue;
                case 50:
                    int iZzc = zzfljVar.zzc(zzfljVar.zzh());
                    int iZzm = zzfljVar.zzm();
                    int i2 = 0;
                    while (zzfljVar.zzl() > 0) {
                        zzfljVar.zzc();
                        i2++;
                    }
                    zzfljVar.zze(iZzm);
                    int[] iArr3 = this.zzi;
                    int length2 = iArr3 == null ? 0 : iArr3.length;
                    int i3 = i2 + length2;
                    int[] iArr4 = new int[i3];
                    if (length2 != 0) {
                        System.arraycopy(this.zzi, 0, iArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        iArr4[length2] = zzfljVar.zzc();
                        length2++;
                    }
                    this.zzi = iArr4;
                    zzfljVar.zzd(iZzc);
                    continue;
                case 58:
                    this.zzj = zzfljVar.zze();
                    continue;
                case 64:
                    int iZzm2 = zzfljVar.zzm();
                    try {
                        int iZzc2 = zzfljVar.zzc();
                        if (iZzc2 != 0 && iZzc2 != 1 && iZzc2 != 2 && iZzc2 != 3) {
                            StringBuilder sb = new StringBuilder(46);
                            sb.append(iZzc2);
                            sb.append(" is not a valid enum AdResourceType");
                            throw new IllegalArgumentException(sb.toString());
                        }
                        this.zzd = Integer.valueOf(iZzc2);
                        continue;
                    } catch (IllegalArgumentException unused) {
                        zzfljVar.zze(iZzm2);
                        zza(zzfljVar, iZza);
                    }
                    break;
                case 74:
                    int iZza3 = zzflv.zza(zzfljVar, 74);
                    String[] strArr = this.zze;
                    int length3 = strArr == null ? 0 : strArr.length;
                    int i4 = iZza3 + length3;
                    String[] strArr2 = new String[i4];
                    if (length3 != 0) {
                        System.arraycopy(this.zze, 0, strArr2, 0, length3);
                    }
                    while (length3 < i4 - 1) {
                        strArr2[length3] = zzfljVar.zze();
                        zzfljVar.zza();
                        length3++;
                    }
                    strArr2[length3] = zzfljVar.zze();
                    this.zze = strArr2;
                    continue;
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

    public static zzfme[] zzb() {
        if (zzf == null) {
            synchronized (zzflq.zzb) {
                if (zzf == null) {
                    zzf = new zzfme[0];
                }
            }
        }
        return zzf;
    }

    protected final int zza() {
        int[] iArr;
        int iZza = super.zza() + zzflk.zzb(1, this.zza.intValue());
        String str = this.zzb;
        if (str != null) {
            iZza += zzflk.zzb(2, str);
        }
        zzflz zzflzVar = this.zzc;
        if (zzflzVar != null) {
            iZza += zzflk.zzb(3, zzflzVar);
        }
        zzfmb zzfmbVar = this.zzg;
        if (zzfmbVar != null) {
            iZza += zzflk.zzb(4, zzfmbVar);
        }
        Integer num = this.zzh;
        if (num != null) {
            iZza += zzflk.zzb(5, num.intValue());
        }
        int[] iArr2 = this.zzi;
        int i = 0;
        if (iArr2 != null && iArr2.length > 0) {
            int i2 = 0;
            int iZza2 = 0;
            while (true) {
                iArr = this.zzi;
                if (i2 >= iArr.length) {
                    break;
                }
                iZza2 += zzflk.zza(iArr[i2]);
                i2++;
            }
            iZza = iZza + iZza2 + (iArr.length * 1);
        }
        String str2 = this.zzj;
        if (str2 != null) {
            iZza += zzflk.zzb(7, str2);
        }
        Integer num2 = this.zzd;
        if (num2 != null) {
            iZza += zzflk.zzb(8, num2.intValue());
        }
        String[] strArr = this.zze;
        if (strArr == null || strArr.length <= 0) {
            return iZza;
        }
        int iZza3 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr2 = this.zze;
            if (i >= strArr2.length) {
                return iZza + iZza3 + (i3 * 1);
            }
            String str3 = strArr2[i];
            if (str3 != null) {
                i3++;
                iZza3 += zzflk.zza(str3);
            }
            i++;
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        zzflkVar.zza(1, this.zza.intValue());
        String str = this.zzb;
        if (str != null) {
            zzflkVar.zza(2, str);
        }
        zzflz zzflzVar = this.zzc;
        if (zzflzVar != null) {
            zzflkVar.zza(3, zzflzVar);
        }
        zzfmb zzfmbVar = this.zzg;
        if (zzfmbVar != null) {
            zzflkVar.zza(4, zzfmbVar);
        }
        Integer num = this.zzh;
        if (num != null) {
            zzflkVar.zza(5, num.intValue());
        }
        int[] iArr = this.zzi;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzi;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzflkVar.zza(6, iArr2[i2]);
                i2++;
            }
        }
        String str2 = this.zzj;
        if (str2 != null) {
            zzflkVar.zza(7, str2);
        }
        Integer num2 = this.zzd;
        if (num2 != null) {
            zzflkVar.zza(8, num2.intValue());
        }
        String[] strArr = this.zze;
        if (strArr != null && strArr.length > 0) {
            while (true) {
                String[] strArr2 = this.zze;
                if (i >= strArr2.length) {
                    break;
                }
                String str3 = strArr2[i];
                if (str3 != null) {
                    zzflkVar.zza(9, str3);
                }
                i++;
            }
        }
        super.zza(zzflkVar);
    }
}
