package com.google.android.gms.internal;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.zzb;
import java.util.Date;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxu {
    public static int zza(AdRequest.ErrorCode errorCode) {
        int i = zzxv.zza[errorCode.ordinal()];
        if (i == 2) {
            return 1;
        }
        if (i != 3) {
            return i != 4 ? 0 : 3;
        }
        return 2;
    }

    public static AdSize zza(zzko zzkoVar) {
        AdSize[] adSizeArr = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
        for (int i = 0; i < 6; i++) {
            if (adSizeArr[i].getWidth() == zzkoVar.zze && adSizeArr[i].getHeight() == zzkoVar.zzb) {
                return adSizeArr[i];
            }
        }
        return new AdSize(zzb.zza(zzkoVar.zze, zzkoVar.zzb, zzkoVar.zza));
    }

    public static MediationAdRequest zza(zzkk zzkkVar, boolean z) {
        AdRequest.Gender gender;
        HashSet hashSet = zzkkVar.zze != null ? new HashSet(zzkkVar.zze) : null;
        Date date = new Date(zzkkVar.zzb);
        int i = zzkkVar.zzd;
        if (i != 1) {
            gender = i != 2 ? AdRequest.Gender.UNKNOWN : AdRequest.Gender.FEMALE;
        } else {
            gender = AdRequest.Gender.MALE;
        }
        return new MediationAdRequest(date, gender, hashSet, z, zzkkVar.zzk);
    }
}
