package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzakh {
    public static boolean zza(zzkk zzkkVar) {
        Bundle bundle = zzkkVar.zzm != null ? zzkkVar.zzm : new Bundle();
        return (bundle.getBundle(AdMobAdapter.class.getName()) != null ? bundle.getBundle(AdMobAdapter.class.getName()) : new Bundle()).getBoolean("render_test_label", false);
    }
}
