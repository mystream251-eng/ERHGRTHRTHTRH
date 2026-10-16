package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzano {
    private final Context zza;
    private final String zzb;
    private final zzala zzc;
    private final zzot zzd;
    private final zzov zze;
    private final long[] zzg;
    private final String[] zzh;
    private boolean zzm;
    private zzamz zzn;
    private boolean zzo;
    private boolean zzp;
    private final zzajn zzf = new zzajq().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zza();
    private boolean zzi = false;
    private boolean zzj = false;
    private boolean zzk = false;
    private boolean zzl = false;
    private long zzq = -1;

    public zzano(Context context, zzala zzalaVar, String str, zzov zzovVar, zzot zzotVar) {
        this.zza = context;
        this.zzc = zzalaVar;
        this.zzb = str;
        this.zze = zzovVar;
        this.zzd = zzotVar;
        String str2 = (String) zzlc.zzf().zza(zzoi.zzq);
        if (str2 == null) {
            this.zzh = new String[0];
            this.zzg = new long[0];
            return;
        }
        String[] strArrSplit = TextUtils.split(str2, ",");
        this.zzh = new String[strArrSplit.length];
        this.zzg = new long[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            try {
                this.zzg[i] = Long.parseLong(strArrSplit[i]);
            } catch (NumberFormatException e) {
                zzahw.zzc("Unable to parse frame hash target time number.", e);
                this.zzg[i] = -1;
            }
        }
    }

    public final void zza() {
        if (!this.zzi || this.zzj) {
            return;
        }
        zzoo.zza(this.zze, this.zzd, "vfr2");
        this.zzj = true;
    }

    public final void zza(zzamz zzamzVar) {
        zzoo.zza(this.zze, this.zzd, "vpc2");
        this.zzi = true;
        zzov zzovVar = this.zze;
        if (zzovVar != null) {
            zzovVar.zza("vpn", zzamzVar.zza());
        }
        this.zzn = zzamzVar;
    }

    public final void zzb() {
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzp)).booleanValue() || this.zzo) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("type", "native-player-metrics");
        bundle.putString("request", this.zzb);
        bundle.putString("player", this.zzn.zza());
        for (zzajp zzajpVar : this.zzf.zza()) {
            String strValueOf = String.valueOf(zzajpVar.zza);
            bundle.putString(strValueOf.length() != 0 ? "fps_c_".concat(strValueOf) : new String("fps_c_"), Integer.toString(zzajpVar.zzc));
            String strValueOf2 = String.valueOf(zzajpVar.zza);
            bundle.putString(strValueOf2.length() != 0 ? "fps_p_".concat(strValueOf2) : new String("fps_p_"), Double.toString(zzajpVar.zzb));
        }
        int i = 0;
        while (true) {
            long[] jArr = this.zzg;
            if (i >= jArr.length) {
                zzbt.zze().zza(this.zza, this.zzc.zza, "gmob-apps", bundle, true);
                this.zzo = true;
                return;
            }
            String str = this.zzh[i];
            if (str != null) {
                String strValueOf3 = String.valueOf(Long.valueOf(jArr[i]));
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf3).length() + 3);
                sb.append("fh_");
                sb.append(strValueOf3);
                bundle.putString(sb.toString(), str);
            }
            i++;
        }
    }

    public final void zzb(zzamz zzamzVar) {
        if (this.zzk && !this.zzl) {
            zzoo.zza(this.zze, this.zzd, "vff2");
            this.zzl = true;
        }
        long jZzc = zzbt.zzl().zzc();
        if (this.zzm && this.zzp && this.zzq != -1) {
            this.zzf.zza(TimeUnit.SECONDS.toNanos(1L) / (jZzc - this.zzq));
        }
        this.zzp = this.zzm;
        this.zzq = jZzc;
        long jLongValue = ((Long) zzlc.zzf().zza(zzoi.zzr)).longValue();
        long jZzf = zzamzVar.zzf();
        int i = 0;
        while (true) {
            String[] strArr = this.zzh;
            if (i >= strArr.length) {
                return;
            }
            if (strArr[i] == null && jLongValue > Math.abs(jZzf - this.zzg[i])) {
                String[] strArr2 = this.zzh;
                int i2 = 8;
                Bitmap bitmap = zzamzVar.getBitmap(8, 8);
                long j = 63;
                int i3 = 0;
                long j2 = 0;
                while (i3 < i2) {
                    int i4 = 0;
                    while (i4 < i2) {
                        int pixel = bitmap.getPixel(i4, i3);
                        j2 |= ((Color.blue(pixel) + Color.red(pixel)) + Color.green(pixel) > 128 ? 1L : 0L) << ((int) j);
                        i4++;
                        j--;
                        i2 = 8;
                    }
                    i3++;
                    i2 = 8;
                }
                strArr2[i] = String.format("%016X", Long.valueOf(j2));
                return;
            }
            i++;
        }
    }

    public final void zzc() {
        this.zzm = true;
        if (!this.zzj || this.zzk) {
            return;
        }
        zzoo.zza(this.zze, this.zzd, "vfp2");
        this.zzk = true;
    }

    public final void zzd() {
        this.zzm = false;
    }
}
