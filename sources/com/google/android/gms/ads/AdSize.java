package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;

/* JADX INFO: loaded from: classes2.dex */
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final int FULL_WIDTH = -1;
    private final int zzb;
    private final int zzc;
    private final String zzd;
    public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize zza = new AdSize(50, 50, "50x50_mb");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");

    public AdSize(int i, int i2) {
        String strValueOf = i == -1 ? "FULL" : String.valueOf(i);
        String strValueOf2 = i2 == -2 ? "AUTO" : String.valueOf(i2);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 4 + String.valueOf(strValueOf2).length());
        sb.append(strValueOf);
        sb.append("x");
        sb.append(strValueOf2);
        sb.append("_as");
        this(i, i2, sb.toString());
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Invalid width for AdSize: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzb = i;
            this.zzc = i2;
            this.zzd = str;
        } else {
            StringBuilder sb2 = new StringBuilder(38);
            sb2.append("Invalid height for AdSize: ");
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzb == adSize.zzb && this.zzc == adSize.zzc && this.zzd.equals(adSize.zzd);
    }

    public final int getHeight() {
        return this.zzc;
    }

    public final int getHeightInPixels(Context context) {
        int i = this.zzc;
        if (i == -4 || i == -3) {
            return -1;
        }
        if (i == -2) {
            return zzko.zzb(context.getResources().getDisplayMetrics());
        }
        zzlc.zza();
        return zzako.zza(context, this.zzc);
    }

    public final int getWidth() {
        return this.zzb;
    }

    public final int getWidthInPixels(Context context) {
        int i = this.zzb;
        if (i == -4 || i == -3) {
            return -1;
        }
        if (i == -1) {
            return zzko.zza(context.getResources().getDisplayMetrics());
        }
        zzlc.zza();
        return zzako.zza(context, this.zzb);
    }

    public final int hashCode() {
        return this.zzd.hashCode();
    }

    public final boolean isAutoHeight() {
        return this.zzc == -2;
    }

    public final boolean isFluid() {
        return this.zzb == -3 && this.zzc == -4;
    }

    public final boolean isFullWidth() {
        return this.zzb == -1;
    }

    public final String toString() {
        return this.zzd;
    }
}
