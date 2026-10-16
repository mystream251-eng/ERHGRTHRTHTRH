package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjl extends zzflm<zzjl> {
    private String zza = null;
    private Integer zzb = null;
    private int[] zzc = zzflv.zza;
    private zzju zzd = null;

    public zzjl() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjl zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                this.zza = zzfljVar.zze();
            } else if (iZza == 16) {
                int iZzm = zzfljVar.zzm();
                try {
                    this.zzb = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
            } else if (iZza == 24) {
                int iZza2 = zzflv.zza(zzfljVar, 24);
                int[] iArr = this.zzc;
                int length = iArr == null ? 0 : iArr.length;
                int i = iZza2 + length;
                int[] iArr2 = new int[i];
                if (length != 0) {
                    System.arraycopy(this.zzc, 0, iArr2, 0, length);
                }
                while (length < i - 1) {
                    iArr2[length] = zzfljVar.zzh();
                    zzfljVar.zza();
                    length++;
                }
                iArr2[length] = zzfljVar.zzh();
                this.zzc = iArr2;
            } else if (iZza == 26) {
                int iZzc = zzfljVar.zzc(zzfljVar.zzh());
                int iZzm2 = zzfljVar.zzm();
                int i2 = 0;
                while (zzfljVar.zzl() > 0) {
                    zzfljVar.zzh();
                    i2++;
                }
                zzfljVar.zze(iZzm2);
                int[] iArr3 = this.zzc;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int i3 = i2 + length2;
                int[] iArr4 = new int[i3];
                if (length2 != 0) {
                    System.arraycopy(this.zzc, 0, iArr4, 0, length2);
                }
                while (length2 < i3) {
                    iArr4[length2] = zzfljVar.zzh();
                    length2++;
                }
                this.zzc = iArr4;
                zzfljVar.zzd(iZzc);
            } else if (iZza == 34) {
                if (this.zzd == null) {
                    this.zzd = new zzju();
                }
                zzfljVar.zza(this.zzd);
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    protected final int zza() {
        int[] iArr;
        int iZza = super.zza();
        String str = this.zza;
        if (str != null) {
            iZza += zzflk.zzb(1, str);
        }
        Integer num = this.zzb;
        if (num != null) {
            iZza += zzflk.zzb(2, num.intValue());
        }
        int[] iArr2 = this.zzc;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int iZza2 = 0;
            while (true) {
                iArr = this.zzc;
                if (i >= iArr.length) {
                    break;
                }
                iZza2 += zzflk.zza(iArr[i]);
                i++;
            }
            iZza = iZza + iZza2 + (iArr.length * 1);
        }
        zzju zzjuVar = this.zzd;
        return zzjuVar != null ? iZza + zzflk.zzb(4, zzjuVar) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zza;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        Integer num = this.zzb;
        if (num != null) {
            zzflkVar.zza(2, num.intValue());
        }
        int[] iArr = this.zzc;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzc;
                if (i >= iArr2.length) {
                    break;
                }
                zzflkVar.zza(3, iArr2[i]);
                i++;
            }
        }
        zzju zzjuVar = this.zzd;
        if (zzjuVar != null) {
            zzflkVar.zza(4, zzjuVar);
        }
        super.zza(zzflkVar);
    }
}
