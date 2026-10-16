package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzakq extends Thread {
    private /* synthetic */ String zza;

    zzakq(zzakp zzakpVar, String str) {
        this.zza = str;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        new zzakz().zza(this.zza);
    }
}
