package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbg extends zzflm<zzbg> {
    public byte[][] zza = zzflv.zzg;
    public byte[] zzb = null;
    public Integer zzc;
    private Integer zzd;

    public zzbg() {
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzbg zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                int iZza2 = zzflv.zza(zzfljVar, 10);
                byte[][] bArr = this.zza;
                int length = bArr == null ? 0 : bArr.length;
                int i = iZza2 + length;
                byte[][] bArr2 = new byte[i][];
                if (length != 0) {
                    System.arraycopy(this.zza, 0, bArr2, 0, length);
                }
                while (length < i - 1) {
                    bArr2[length] = zzfljVar.zzf();
                    zzfljVar.zza();
                    length++;
                }
                bArr2[length] = zzfljVar.zzf();
                this.zza = bArr2;
            } else if (iZza == 18) {
                this.zzb = zzfljVar.zzf();
            } else if (iZza == 24) {
                zzfljVar.zzm();
                int iZzh = zzfljVar.zzh();
                if (iZzh != 0 && iZzh != 1 && iZzh != 2) {
                    StringBuilder sb = new StringBuilder(41);
                    sb.append(iZzh);
                    sb.append(" is not a valid enum ProtoName");
                    throw new IllegalArgumentException(sb.toString());
                }
                this.zzd = Integer.valueOf(iZzh);
            } else if (iZza == 32) {
                try {
                    int iZzh2 = zzfljVar.zzh();
                    if (iZzh2 != 0 && iZzh2 != 1 && iZzh2 != 2 && iZzh2 != 3) {
                        StringBuilder sb2 = new StringBuilder(48);
                        sb2.append(iZzh2);
                        sb2.append(" is not a valid enum EncryptionMethod");
                        throw new IllegalArgumentException(sb2.toString());
                    }
                    this.zzc = Integer.valueOf(iZzh2);
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(zzfljVar.zzm());
                    zza(zzfljVar, iZza);
                }
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    protected final int zza() {
        int iZza = super.zza();
        byte[][] bArr = this.zza;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            int iZzb = 0;
            int i2 = 0;
            while (true) {
                byte[][] bArr2 = this.zza;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    i2++;
                    iZzb += zzflk.zzb(bArr3);
                }
                i++;
            }
            iZza = iZza + iZzb + (i2 * 1);
        }
        byte[] bArr4 = this.zzb;
        if (bArr4 != null) {
            iZza += zzflk.zzb(2, bArr4);
        }
        Integer num = this.zzd;
        if (num != null) {
            iZza += zzflk.zzb(3, num.intValue());
        }
        Integer num2 = this.zzc;
        return num2 != null ? iZza + zzflk.zzb(4, num2.intValue()) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        byte[][] bArr = this.zza;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            while (true) {
                byte[][] bArr2 = this.zza;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    zzflkVar.zza(1, bArr3);
                }
                i++;
            }
        }
        byte[] bArr4 = this.zzb;
        if (bArr4 != null) {
            zzflkVar.zza(2, bArr4);
        }
        Integer num = this.zzd;
        if (num != null) {
            zzflkVar.zza(3, num.intValue());
        }
        Integer num2 = this.zzc;
        if (num2 != null) {
            zzflkVar.zza(4, num2.intValue());
        }
        super.zza(zzflkVar);
    }
}
