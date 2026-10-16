package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzaot implements Runnable {
    private /* synthetic */ zzaor zza;

    zzaot(zzaor zzaorVar) {
        this.zza = zzaorVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        super/*android.webkit.WebView*/.destroy();
    }
}
