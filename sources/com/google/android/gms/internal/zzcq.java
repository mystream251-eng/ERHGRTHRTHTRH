package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzcq implements Runnable {
    private /* synthetic */ zzcp zza;

    zzcq(zzcp zzcpVar) {
        this.zza = zzcpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzb != null) {
            return;
        }
        synchronized (zzcp.zzd) {
            if (this.zza.zzb != null) {
                return;
            }
            boolean zBooleanValue = ((Boolean) zzlc.zzf().zza(zzoi.zzdu)).booleanValue();
            if (zBooleanValue) {
                try {
                    zzcp.zza = new zziz(this.zza.zzc.zza, "ADSHIELD", null);
                } catch (Throwable unused) {
                    zBooleanValue = false;
                }
            }
            this.zza.zzb = Boolean.valueOf(zBooleanValue);
            zzcp.zzd.open();
        }
    }
}
