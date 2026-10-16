package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzvu implements Runnable {
    private /* synthetic */ zzvs zza;
    private /* synthetic */ zzvt zzb;

    zzvu(zzvt zzvtVar, zzvs zzvsVar) {
        this.zzb = zzvtVar;
        this.zza = zzvsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzi) {
            if (this.zzb.zzs != -2) {
                return;
            }
            this.zzb.zzr = this.zzb.zzd();
            if (this.zzb.zzr == null) {
                this.zzb.zza(4);
                return;
            }
            if (!this.zzb.zze() || this.zzb.zzb(1)) {
                this.zza.zza(this.zzb);
                this.zzb.zza(this.zza);
                return;
            }
            String str = this.zzb.zza;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 56);
            sb.append("Ignoring adapter ");
            sb.append(str);
            sb.append(" as delayed impression is not supported");
            zzahw.zze(sb.toString());
            this.zzb.zza(2);
        }
    }
}
