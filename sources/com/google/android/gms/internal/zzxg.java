package com.google.android.gms.internal;

import android.location.Location;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxg implements NativeMediationAdRequest {
    private final Date zza;
    private final int zzb;
    private final Set<String> zzc;
    private final boolean zzd;
    private final Location zze;
    private final int zzf;
    private final zzqh zzg;
    private final boolean zzi;
    private final List<String> zzh = new ArrayList();
    private final Map<String, Boolean> zzj = new HashMap();

    public zzxg(Date date, int i, Set<String> set, Location location, boolean z, int i2, zzqh zzqhVar, List<String> list, boolean z2) {
        Map<String, Boolean> map;
        String str;
        boolean z3;
        this.zza = date;
        this.zzb = i;
        this.zzc = set;
        this.zze = location;
        this.zzd = z;
        this.zzf = i2;
        this.zzg = zzqhVar;
        this.zzi = z2;
        if (list != null) {
            for (String str2 : list) {
                if (str2.startsWith("custom:")) {
                    String[] strArrSplit = str2.split(":", 3);
                    if (strArrSplit.length == 3) {
                        if ("true".equals(strArrSplit[2])) {
                            map = this.zzj;
                            str = strArrSplit[1];
                            z3 = true;
                        } else if ("false".equals(strArrSplit[2])) {
                            map = this.zzj;
                            str = strArrSplit[1];
                            z3 = false;
                        }
                        map.put(str, z3);
                    }
                } else {
                    this.zzh.add(str2);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final float getAdVolume() {
        return zzmz.zza().zzb();
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Date getBirthday() {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final int getGender() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Set<String> getKeywords() {
        return this.zzc;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Location getLocation() {
        return this.zze;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final NativeAdOptions getNativeAdOptions() {
        if (this.zzg == null) {
            return null;
        }
        NativeAdOptions.Builder requestMultipleImages = new NativeAdOptions.Builder().setReturnUrlsForImageAssets(this.zzg.zzb).setImageOrientation(this.zzg.zzc).setRequestMultipleImages(this.zzg.zzd);
        if (this.zzg.zza >= 2) {
            requestMultipleImages.setAdChoicesPlacement(this.zzg.zze);
        }
        if (this.zzg.zza >= 3 && this.zzg.zzf != null) {
            requestMultipleImages.setVideoOptions(new VideoOptions(this.zzg.zzf));
        }
        return requestMultipleImages.build();
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isAdMuted() {
        return zzmz.zza().zzc();
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isAppInstallAdRequested() {
        List<String> list = this.zzh;
        return list != null && list.contains(ExifInterface.GPS_MEASUREMENT_2D);
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isContentAdRequested() {
        List<String> list = this.zzh;
        return list != null && list.contains(LocalSearchProvider.LIVE_CONTENTS);
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final boolean isDesignedForFamilies() {
        return this.zzi;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final boolean isTesting() {
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final int taggedForChildDirectedTreatment() {
        return this.zzf;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean zza() {
        List<String> list = this.zzh;
        return list != null && list.contains("6");
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean zzb() {
        List<String> list = this.zzh;
        return list != null && list.contains(ExifInterface.GPS_MEASUREMENT_3D);
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final Map<String, Boolean> zzc() {
        return this.zzj;
    }
}
