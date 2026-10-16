package com.google.android.gms.ads.internal.js;

import com.viaccessorca.vodownloader.NanoHTTPD;

/* JADX INFO: loaded from: classes2.dex */
final class zzi implements Runnable {
    private /* synthetic */ String zza;
    private /* synthetic */ zze zzb;

    zzi(zze zzeVar, String str) {
        this.zzb = zzeVar;
        this.zza = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.loadData(this.zza, NanoHTTPD.MIME_HTML, "UTF-8");
    }
}
