package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzaet implements Runnable {
    private /* synthetic */ zzahe zza;
    private /* synthetic */ zzaer zzb;

    zzaet(zzaer zzaerVar, zzahe zzaheVar) {
        this.zzb = zzaerVar;
        this.zza = zzaheVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb(new zzahd(this.zza, null, null, null, null, null, null, null));
    }
}
