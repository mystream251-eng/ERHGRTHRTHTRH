package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfmd extends zzflm<zzfmd> {
    public Integer zza = null;
    public String zzb = null;
    public byte[] zzc = null;

    public zzfmd() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzfmd zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 8) {
                int iZzm = zzfljVar.zzm();
                try {
                    int iZzc = zzfljVar.zzc();
                    if (iZzc != 0 && iZzc != 1) {
                        StringBuilder sb = new StringBuilder(36);
                        sb.append(iZzc);
                        sb.append(" is not a valid enum Type");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.zza = Integer.valueOf(iZzc);
                } catch (IllegalArgumentException unused) {
                    zzfljVar.zze(iZzm);
                    zza(zzfljVar, iZza);
                }
            } else if (iZza == 18) {
                this.zzb = zzfljVar.zze();
            } else if (iZza == 26) {
                this.zzc = zzfljVar.zzf();
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
        String str = this.zzb;
        if (str != null) {
            iZza += zzflk.zzb(2, str);
        }
        byte[] bArr = this.zzc;
        return bArr != null ? iZza + zzflk.zzb(3, bArr) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zza;
        if (num != null) {
            zzflkVar.zza(1, num.intValue());
        }
        String str = this.zzb;
        if (str != null) {
            zzflkVar.zza(2, str);
        }
        byte[] bArr = this.zzc;
        if (bArr != null) {
            zzflkVar.zza(3, bArr);
        }
        super.zza(zzflkVar);
    }
}
