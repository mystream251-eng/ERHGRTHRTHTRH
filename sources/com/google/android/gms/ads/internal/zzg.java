package com.google.android.gms.ads.internal;

import android.webkit.CookieManager;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzg implements Callable<String> {
    private /* synthetic */ zzd zza;

    zzg(zzd zzdVar) {
        this.zza = zzdVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        CookieManager cookieManagerZzc;
        return (!((Boolean) zzlc.zzf().zza(zzoi.zzfs)).booleanValue() || (cookieManagerZzc = zzbt.zzg().zzc(this.zza.zze.zzc)) == null) ? "" : cookieManagerZzc.getCookie("googleads.g.doubleclick.net");
    }
}
