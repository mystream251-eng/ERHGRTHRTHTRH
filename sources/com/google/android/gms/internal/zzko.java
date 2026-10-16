package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzko extends zzbgl {
    public static final Parcelable.Creator<zzko> CREATOR = new zzkp();
    public final String zza;
    public final int zzb;
    public final int zzc;
    public final boolean zzd;
    public final int zze;
    public final int zzf;
    public final zzko[] zzg;
    public final boolean zzh;
    public final boolean zzi;
    public boolean zzj;

    public zzko() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public zzko(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0063  */
    public zzko(Context context, AdSize[] adSizeArr) {
        int height;
        int i;
        String string;
        int iZzi;
        AdSize adSize = adSizeArr[0];
        this.zzd = false;
        boolean zIsFluid = adSize.isFluid();
        this.zzi = zIsFluid;
        if (zIsFluid) {
            this.zze = AdSize.BANNER.getWidth();
            height = AdSize.BANNER.getHeight();
        } else {
            this.zze = adSize.getWidth();
            height = adSize.getHeight();
        }
        this.zzb = height;
        boolean z = this.zze == -1;
        boolean z2 = this.zzb == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            zzlc.zza();
            if (zzako.zzg(context)) {
                zzlc.zza();
                if (zzako.zzh(context)) {
                    int i2 = displayMetrics.widthPixels;
                    zzlc.zza();
                    iZzi = i2 - zzako.zzi(context);
                } else {
                    iZzi = displayMetrics.widthPixels;
                }
            } else {
                iZzi = displayMetrics.widthPixels;
            }
            this.zzf = iZzi;
            double d = this.zzf / displayMetrics.density;
            i = (int) d;
            if (d - ((double) i) >= 0.01d) {
                i++;
            }
        } else {
            i = this.zze;
            zzlc.zza();
            this.zzf = zzako.zza(displayMetrics, this.zze);
        }
        int iZzc = z2 ? zzc(displayMetrics) : this.zzb;
        zzlc.zza();
        this.zzc = zzako.zza(displayMetrics, iZzc);
        if (z || z2) {
            StringBuilder sb = new StringBuilder(26);
            sb.append(i);
            sb.append("x");
            sb.append(iZzc);
            sb.append("_as");
            string = sb.toString();
        } else {
            string = this.zzi ? "320x50_mb" : adSize.toString();
        }
        this.zza = string;
        if (adSizeArr.length > 1) {
            this.zzg = new zzko[adSizeArr.length];
            for (int i3 = 0; i3 < adSizeArr.length; i3++) {
                this.zzg[i3] = new zzko(context, adSizeArr[i3]);
            }
        } else {
            this.zzg = null;
        }
        this.zzh = false;
        this.zzj = false;
    }

    public zzko(zzko zzkoVar, zzko[] zzkoVarArr) {
        this(zzkoVar.zza, zzkoVar.zzb, zzkoVar.zzc, zzkoVar.zzd, zzkoVar.zze, zzkoVar.zzf, zzkoVarArr, zzkoVar.zzh, zzkoVar.zzi, zzkoVar.zzj);
    }

    zzko(String str, int i, int i2, boolean z, int i3, int i4, zzko[] zzkoVarArr, boolean z2, boolean z3, boolean z4) {
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
        this.zzd = z;
        this.zze = i3;
        this.zzf = i4;
        this.zzg = zzkoVarArr;
        this.zzh = z2;
        this.zzi = z3;
        this.zzj = z4;
    }

    public static int zza(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static zzko zza() {
        return new zzko("reward_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public static zzko zza(Context context) {
        return new zzko("320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return (int) (zzc(displayMetrics) * displayMetrics.density);
    }

    private static int zzc(DisplayMetrics displayMetrics) {
        int i = (int) (displayMetrics.heightPixels / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [android.os.Parcelable[], com.google.android.gms.internal.zzko[]] */
    public void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 3, this.zzb);
        zzbgo.zza(parcel, 4, this.zzc);
        zzbgo.zza(parcel, 5, this.zzd);
        zzbgo.zza(parcel, 6, this.zze);
        zzbgo.zza(parcel, 7, this.zzf);
        zzbgo.zza(parcel, 8, (Parcelable[]) this.zzg, i, false);
        zzbgo.zza(parcel, 9, this.zzh);
        zzbgo.zza(parcel, 10, this.zzi);
        zzbgo.zza(parcel, 11, this.zzj);
        zzbgo.zza(parcel, iZza);
    }

    public final AdSize zzb() {
        return zzb.zza(this.zze, this.zzb, this.zza);
    }
}
