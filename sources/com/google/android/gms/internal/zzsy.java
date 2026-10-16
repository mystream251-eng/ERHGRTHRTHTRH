package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzsy extends zzbgl {
    public static final Parcelable.Creator<zzsy> CREATOR = new zzsz();
    private String zza;
    private String[] zzb;
    private String[] zzc;

    zzsy(String str, String[] strArr, String[] strArr2) {
        this.zza = str;
        this.zzb = strArr;
        this.zzc = strArr2;
    }

    public static zzsy zza(zzr zzrVar) throws zza {
        Map mapZzf = zzrVar.zzf();
        int size = mapZzf.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int i = 0;
        for (Map.Entry entry : mapZzf.entrySet()) {
            strArr[i] = (String) entry.getKey();
            strArr2[i] = (String) entry.getValue();
            i++;
        }
        return new zzsy(zzrVar.zzc(), strArr, strArr2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza, false);
        zzbgo.zza(parcel, 2, this.zzb, false);
        zzbgo.zza(parcel, 3, this.zzc, false);
        zzbgo.zza(parcel, iZza);
    }
}
