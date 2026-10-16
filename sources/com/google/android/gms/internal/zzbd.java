package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbd extends zzflm<zzbd> {
    private Long zza = null;
    private Integer zzb = null;
    private Boolean zzc = null;
    private int[] zzd = zzflv.zza;
    private Long zze = null;

    public zzbd() {
        this.zzay = -1;
    }

    protected final int zza() {
        int[] iArr;
        int iZza = super.zza();
        Long l = this.zza;
        if (l != null) {
            iZza += zzflk.zze(1, l.longValue());
        }
        Integer num = this.zzb;
        if (num != null) {
            iZza += zzflk.zzb(2, num.intValue());
        }
        Boolean bool = this.zzc;
        if (bool != null) {
            bool.booleanValue();
            iZza += zzflk.zzb(3) + 1;
        }
        int[] iArr2 = this.zzd;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int iZza2 = 0;
            while (true) {
                iArr = this.zzd;
                if (i >= iArr.length) {
                    break;
                }
                iZza2 += zzflk.zza(iArr[i]);
                i++;
            }
            iZza = iZza + iZza2 + (iArr.length * 1);
        }
        Long l2 = this.zze;
        if (l2 == null) {
            return iZza;
        }
        return iZza + zzflk.zzb(5) + zzflk.zza(l2.longValue());
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                this.zza = Long.valueOf(zzfljVar.zzi());
            } else if (iZza == 16) {
                this.zzb = Integer.valueOf(zzfljVar.zzh());
            } else if (iZza == 24) {
                this.zzc = Boolean.valueOf(zzfljVar.zzd());
            } else if (iZza == 32) {
                int iZza2 = zzflv.zza(zzfljVar, 32);
                int[] iArr = this.zzd;
                int length = iArr == null ? 0 : iArr.length;
                int i = iZza2 + length;
                int[] iArr2 = new int[i];
                if (length != 0) {
                    System.arraycopy(this.zzd, 0, iArr2, 0, length);
                }
                while (length < i - 1) {
                    iArr2[length] = zzfljVar.zzh();
                    zzfljVar.zza();
                    length++;
                }
                iArr2[length] = zzfljVar.zzh();
                this.zzd = iArr2;
            } else if (iZza == 34) {
                int iZzc = zzfljVar.zzc(zzfljVar.zzh());
                int iZzm = zzfljVar.zzm();
                int i2 = 0;
                while (zzfljVar.zzl() > 0) {
                    zzfljVar.zzh();
                    i2++;
                }
                zzfljVar.zze(iZzm);
                int[] iArr3 = this.zzd;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int i3 = i2 + length2;
                int[] iArr4 = new int[i3];
                if (length2 != 0) {
                    System.arraycopy(this.zzd, 0, iArr4, 0, length2);
                }
                while (length2 < i3) {
                    iArr4[length2] = zzfljVar.zzh();
                    length2++;
                }
                this.zzd = iArr4;
                zzfljVar.zzd(iZzc);
            } else if (iZza == 40) {
                this.zze = Long.valueOf(zzfljVar.zzi());
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Long l = this.zza;
        if (l != null) {
            zzflkVar.zzb(1, l.longValue());
        }
        Integer num = this.zzb;
        if (num != null) {
            zzflkVar.zza(2, num.intValue());
        }
        Boolean bool = this.zzc;
        if (bool != null) {
            zzflkVar.zza(3, bool.booleanValue());
        }
        int[] iArr = this.zzd;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzd;
                if (i >= iArr2.length) {
                    break;
                }
                zzflkVar.zza(4, iArr2[i]);
                i++;
            }
        }
        Long l2 = this.zze;
        if (l2 != null) {
            zzflkVar.zza(5, l2.longValue());
        }
        super.zza(zzflkVar);
    }
}
