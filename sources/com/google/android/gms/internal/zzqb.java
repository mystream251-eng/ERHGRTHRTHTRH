package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class zzqb {
    private final WeakReference<zzaof> zza;
    private String zzb;

    public zzqb(zzaof zzaofVar) {
        this.zza = new WeakReference<>(zzaofVar);
    }

    public final void zza(zzaan zzaanVar) {
        zzaanVar.zza("/loadHtml", new zzqc(this, zzaanVar));
        zzaanVar.zza("/showOverlay", new zzqe(this, zzaanVar));
        zzaanVar.zza("/hideOverlay", new zzqf(this, zzaanVar));
        zzaof zzaofVar = this.zza.get();
        if (zzaofVar != null) {
            zzaofVar.zzv().zza("/sendMessageToSdk", new zzqg(this, zzaanVar));
        }
    }
}
