package com.google.android.gms.internal;

import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class zzajp {
    public final String zza;
    public final double zzb;
    public final int zzc;
    private double zzd;
    private double zze;

    public zzajp(String str, double d, double d2, double d3, int i) {
        this.zza = str;
        this.zze = d;
        this.zzd = d2;
        this.zzb = d3;
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzajp)) {
            return false;
        }
        zzajp zzajpVar = (zzajp) obj;
        return com.google.android.gms.common.internal.zzbg.zza(this.zza, zzajpVar.zza) && this.zzd == zzajpVar.zzd && this.zze == zzajpVar.zze && this.zzc == zzajpVar.zzc && Double.compare(this.zzb, zzajpVar.zzb) == 0;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Double.valueOf(this.zzd), Double.valueOf(this.zze), Double.valueOf(this.zzb), Integer.valueOf(this.zzc)});
    }

    public final String toString() {
        return com.google.android.gms.common.internal.zzbg.zza(this).zza("name", this.zza).zza("minBound", Double.valueOf(this.zze)).zza("maxBound", Double.valueOf(this.zzd)).zza("percent", Double.valueOf(this.zzb)).zza(NotificationsContract.COLUMN_COUNT, Integer.valueOf(this.zzc)).toString();
    }
}
