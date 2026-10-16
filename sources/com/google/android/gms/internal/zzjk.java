package com.google.android.gms.internal;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjk extends zzflm<zzjk> {
    private Integer zze = null;
    public String zza = null;
    private Integer zzf = null;
    private Integer zzg = null;
    private zzjv zzh = null;
    public long[] zzb = zzflv.zzb;
    public zzji zzc = null;
    private zzjj zzi = null;
    private zzjo zzj = null;
    public zzjd zzd = null;

    public zzjk() {
        this.zzax = null;
        this.zzay = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzjk zza(zzflj zzfljVar) throws IOException {
        zzfls zzflsVar;
        while (true) {
            int iZza = zzfljVar.zza();
            switch (iZza) {
                case 0:
                    return this;
                case 72:
                    this.zze = Integer.valueOf(zzfljVar.zzh());
                    continue;
                case 82:
                    this.zza = zzfljVar.zze();
                    continue;
                case 88:
                    this.zzf = Integer.valueOf(zzfljVar.zzh());
                    continue;
                case 96:
                    int iZzm = zzfljVar.zzm();
                    try {
                        this.zzg = Integer.valueOf(zzjc.zza(zzfljVar.zzh()));
                        continue;
                    } catch (IllegalArgumentException unused) {
                        zzfljVar.zze(iZzm);
                        zza(zzfljVar, iZza);
                    }
                    break;
                case 106:
                    if (this.zzh == null) {
                        this.zzh = new zzjv();
                    }
                    zzflsVar = this.zzh;
                    break;
                case 112:
                    int iZza2 = zzflv.zza(zzfljVar, 112);
                    long[] jArr = this.zzb;
                    int length = jArr == null ? 0 : jArr.length;
                    int i = iZza2 + length;
                    long[] jArr2 = new long[i];
                    if (length != 0) {
                        System.arraycopy(this.zzb, 0, jArr2, 0, length);
                    }
                    while (length < i - 1) {
                        jArr2[length] = zzfljVar.zzi();
                        zzfljVar.zza();
                        length++;
                    }
                    jArr2[length] = zzfljVar.zzi();
                    this.zzb = jArr2;
                    continue;
                case 114:
                    int iZzc = zzfljVar.zzc(zzfljVar.zzh());
                    int iZzm2 = zzfljVar.zzm();
                    int i2 = 0;
                    while (zzfljVar.zzl() > 0) {
                        zzfljVar.zzi();
                        i2++;
                    }
                    zzfljVar.zze(iZzm2);
                    long[] jArr3 = this.zzb;
                    int length2 = jArr3 == null ? 0 : jArr3.length;
                    int i3 = i2 + length2;
                    long[] jArr4 = new long[i3];
                    if (length2 != 0) {
                        System.arraycopy(this.zzb, 0, jArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        jArr4[length2] = zzfljVar.zzi();
                        length2++;
                    }
                    this.zzb = jArr4;
                    zzfljVar.zzd(iZzc);
                    continue;
                case 122:
                    if (this.zzc == null) {
                        this.zzc = new zzji();
                    }
                    zzflsVar = this.zzc;
                    break;
                case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                    if (this.zzi == null) {
                        this.zzi = new zzjj();
                    }
                    zzflsVar = this.zzi;
                    break;
                case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
                    if (this.zzj == null) {
                        this.zzj = new zzjo();
                    }
                    zzflsVar = this.zzj;
                    break;
                case 146:
                    if (this.zzd == null) {
                        this.zzd = new zzjd();
                    }
                    zzflsVar = this.zzd;
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
        long[] jArr;
        int iZza = super.zza();
        Integer num = this.zze;
        if (num != null) {
            iZza += zzflk.zzb(9, num.intValue());
        }
        String str = this.zza;
        if (str != null) {
            iZza += zzflk.zzb(10, str);
        }
        Integer num2 = this.zzf;
        if (num2 != null) {
            iZza += zzflk.zzb(11) + zzflk.zzd(num2.intValue());
        }
        Integer num3 = this.zzg;
        if (num3 != null) {
            iZza += zzflk.zzb(12, num3.intValue());
        }
        zzjv zzjvVar = this.zzh;
        if (zzjvVar != null) {
            iZza += zzflk.zzb(13, zzjvVar);
        }
        long[] jArr2 = this.zzb;
        if (jArr2 != null && jArr2.length > 0) {
            int i = 0;
            int iZza2 = 0;
            while (true) {
                jArr = this.zzb;
                if (i >= jArr.length) {
                    break;
                }
                iZza2 += zzflk.zza(jArr[i]);
                i++;
            }
            iZza = iZza + iZza2 + (jArr.length * 1);
        }
        zzji zzjiVar = this.zzc;
        if (zzjiVar != null) {
            iZza += zzflk.zzb(15, zzjiVar);
        }
        zzjj zzjjVar = this.zzi;
        if (zzjjVar != null) {
            iZza += zzflk.zzb(16, zzjjVar);
        }
        zzjo zzjoVar = this.zzj;
        if (zzjoVar != null) {
            iZza += zzflk.zzb(17, zzjoVar);
        }
        zzjd zzjdVar = this.zzd;
        return zzjdVar != null ? iZza + zzflk.zzb(18, zzjdVar) : iZza;
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        Integer num = this.zze;
        if (num != null) {
            zzflkVar.zza(9, num.intValue());
        }
        String str = this.zza;
        if (str != null) {
            zzflkVar.zza(10, str);
        }
        Integer num2 = this.zzf;
        int i = 0;
        if (num2 != null) {
            int iIntValue = num2.intValue();
            zzflkVar.zzc(11, 0);
            zzflkVar.zzc(iIntValue);
        }
        Integer num3 = this.zzg;
        if (num3 != null) {
            zzflkVar.zza(12, num3.intValue());
        }
        zzjv zzjvVar = this.zzh;
        if (zzjvVar != null) {
            zzflkVar.zza(13, zzjvVar);
        }
        long[] jArr = this.zzb;
        if (jArr != null && jArr.length > 0) {
            while (true) {
                long[] jArr2 = this.zzb;
                if (i >= jArr2.length) {
                    break;
                }
                zzflkVar.zza(14, jArr2[i]);
                i++;
            }
        }
        zzji zzjiVar = this.zzc;
        if (zzjiVar != null) {
            zzflkVar.zza(15, zzjiVar);
        }
        zzjj zzjjVar = this.zzi;
        if (zzjjVar != null) {
            zzflkVar.zza(16, zzjjVar);
        }
        zzjo zzjoVar = this.zzj;
        if (zzjoVar != null) {
            zzflkVar.zza(17, zzjoVar);
        }
        zzjd zzjdVar = this.zzd;
        if (zzjdVar != null) {
            zzflkVar.zza(18, zzjdVar);
        }
        super.zza(zzflkVar);
    }
}
