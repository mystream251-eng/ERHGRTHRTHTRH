package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjm extends zzflm<zzjm> {
    private Integer zza = null;
    private int[] zzb = zzflv.zza;

    public zzjm() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjm zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                int iZzm = zzfljVar.zzm();
                try {
                    this.zza = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
            } else if (iZza == 16) {
                int iZza2 = zzflv.zza(zzfljVar, 16);
                int[] iArr = this.zzb;
                int length = iArr == null ? 0 : iArr.length;
                int i = iZza2 + length;
                int[] iArr2 = new int[i];
                if (length != 0) {
                    System.arraycopy(this.zzb, 0, iArr2, 0, length);
                }
                while (length < i - 1) {
                    iArr2[length] = zzfljVar.zzh();
                    zzfljVar.zza();
                    length++;
                }
                iArr2[length] = zzfljVar.zzh();
                this.zzb = iArr2;
            } else if (iZza == 18) {
                int iZzc = zzfljVar.zzc(zzfljVar.zzh());
                int iZzm2 = zzfljVar.zzm();
                int i2 = 0;
                while (zzfljVar.zzl() > 0) {
                    zzfljVar.zzh();
                    i2++;
                }
                zzfljVar.zze(iZzm2);
                int[] iArr3 = this.zzb;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int i3 = i2 + length2;
                int[] iArr4 = new int[i3];
                if (length2 != 0) {
                    System.arraycopy(this.zzb, 0, iArr4, 0, length2);
                }
                while (length2 < i3) {
                    iArr4[length2] = zzfljVar.zzh();
                    length2++;
                }
                this.zzb = iArr4;
                zzfljVar.zzd(iZzc);
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        Integer num = this.zza;
        if (num != null) {
            iZza += zzflk.zzb(1, num.intValue());
        }
        int[] iArr = this.zzb;
        if (iArr == null || iArr.length <= 0) {
            return iZza;
        }
        int i = 0;
        int iZza2 = 0;
        while (true) {
            int[] iArr2 = this.zzb;
            if (i >= iArr2.length) {
                return iZza + iZza2 + (iArr2.length * 1);
            }
            iZza2 += zzflk.zza(iArr2[i]);
            i++;
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(1, num.intValue());
        }
        int[] iArr = this.zzb;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzb;
                if (i >= iArr2.length) {
                    break;
                }
                zzflkVar.zza(2, iArr2[i]);
                i++;
            }
        }
        super.zza(zzflkVar);
    }
}
