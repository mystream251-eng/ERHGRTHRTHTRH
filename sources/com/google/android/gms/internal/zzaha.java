package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaha implements zzahc {
    @Override // com.google.android.gms.internal.zzahc
    public final zzalt<AdvertisingIdClient.Info> zza(Context context) {
        zzamd zzamdVar = new zzamd();
        zzlc.zza();
        if (zzako.zzf(context)) {
            zzaid.zza(new zzahb(this, context, zzamdVar));
        }
        return zzamdVar;
    }

    @Override // com.google.android.gms.internal.zzahc
    public final zzalt<String> zza(String str, PackageInfo packageInfo) {
        return zzali.zza(str);
    }
}
