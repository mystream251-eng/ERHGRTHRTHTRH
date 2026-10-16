package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzin extends zzbgl {
    public static final Parcelable.Creator<zzin> CREATOR = new zzio();
    public final String zza;
    private long zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private Bundle zzf;
    private boolean zzg;
    private long zzh;

    zzin(String str, long j, String str2, String str3, String str4, Bundle bundle, boolean z, long j2) {
        this.zza = str;
        this.zzb = j;
        this.zzc = str2 == null ? "" : str2;
        this.zzd = str3 == null ? "" : str3;
        this.zze = str4 == null ? "" : str4;
        this.zzf = bundle == null ? new Bundle() : bundle;
        this.zzg = z;
        this.zzh = j2;
    }

    public static zzin zza(Uri uri) {
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                int size = pathSegments.size();
                StringBuilder sb = new StringBuilder(62);
                sb.append("Expected 2 path parts for namespace and id, found :");
                sb.append(size);
                zzahw.zze(sb.toString());
                return null;
            }
            String str = pathSegments.get(0);
            String str2 = pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter(ImagesContract.URL);
            boolean zEquals = LocalSearchProvider.LIVE_CONTENTS.equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long j = queryParameter2 == null ? 0L : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            for (String str3 : zzbt.zzg().zza(uri)) {
                if (str3.startsWith("tag.")) {
                    bundle.putString(str3.substring(4), uri.getQueryParameter(str3));
                }
            }
            return new zzin(queryParameter, j, host, str, str2, bundle, zEquals, 0L);
        } catch (NullPointerException | NumberFormatException e) {
            zzahw.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public static zzin zza(String str) {
        return zza(Uri.parse(str));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 3, this.zzb);
        zzbgo.zza(parcel, 4, this.zzc, false);
        zzbgo.zza(parcel, 5, this.zzd, false);
        zzbgo.zza(parcel, 6, this.zze, false);
        zzbgo.zza(parcel, 7, this.zzf, false);
        zzbgo.zza(parcel, 8, this.zzg);
        zzbgo.zza(parcel, 9, this.zzh);
        zzbgo.zza(parcel, iZza);
    }
}
