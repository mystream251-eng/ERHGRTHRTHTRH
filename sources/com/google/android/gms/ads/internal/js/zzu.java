package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaly;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
final class zzu implements Runnable {
    private /* synthetic */ zzae zza;
    private /* synthetic */ zzc zzb;
    private /* synthetic */ zzn zzc;

    zzu(zzn zznVar, zzae zzaeVar, zzc zzcVar) {
        this.zzc = zznVar;
        this.zza = zzaeVar;
        this.zzb = zzcVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzc.zza) {
            if (this.zza.zze() != -1 && this.zza.zze() != 1) {
                this.zza.zzd();
                Executor executor = zzaly.zza;
                zzc zzcVar = this.zzb;
                zzcVar.getClass();
                executor.execute(zzv.zza(zzcVar));
                zzahw.zza("Could not receive loaded message in a timely manner. Rejecting.");
            }
        }
    }
}
