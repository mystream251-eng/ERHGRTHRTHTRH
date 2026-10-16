package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
final class zzbs {
    private final String zza;
    private final Map<String, String> zzb = new TreeMap();
    private String zzc;
    private String zzd;

    public zzbs(String str) {
        this.zza = str;
    }

    public final String zza() {
        return this.zzd;
    }

    public final void zza(zzkk zzkkVar, zzala zzalaVar) {
        this.zzc = zzkkVar.zzj.zza;
        Bundle bundle = zzkkVar.zzm != null ? zzkkVar.zzm.getBundle(AdMobAdapter.class.getName()) : null;
        if (bundle == null) {
            return;
        }
        String str = (String) zzlc.zzf().zza(zzoi.zzfo);
        for (String str2 : bundle.keySet()) {
            if (str.equals(str2)) {
                this.zzd = bundle.getString(str2);
            } else if (str2.startsWith("csa_")) {
                this.zzb.put(str2.substring(4), bundle.getString(str2));
            }
        }
        this.zzb.put("SDKVersion", zzalaVar.zza);
    }

    public final String zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zza;
    }

    public final Map<String, String> zzd() {
        return this.zzb;
    }
}
