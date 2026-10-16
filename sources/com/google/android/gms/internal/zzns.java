package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.VideoOptions;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzns extends zzbgl {
    public static final Parcelable.Creator<zzns> CREATOR = new zznt();
    public final boolean zza;
    public final boolean zzb;
    public final boolean zzc;

    public zzns(VideoOptions videoOptions) {
        this(videoOptions.getStartMuted(), videoOptions.getCustomControlsRequested(), videoOptions.getClickToExpandRequested());
    }

    public zzns(boolean z, boolean z2, boolean z3) {
        this.zza = z;
        this.zzb = z2;
        this.zzc = z3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza);
        zzbgo.zza(parcel, 3, this.zzb);
        zzbgo.zza(parcel, 4, this.zzc);
        zzbgo.zza(parcel, iZza);
    }
}
