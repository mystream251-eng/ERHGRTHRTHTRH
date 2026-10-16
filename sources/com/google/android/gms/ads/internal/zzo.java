package com.google.android.gms.ads.internal;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzo implements View.OnClickListener {
    private /* synthetic */ zzw zza;
    private /* synthetic */ zzl zzb;

    zzo(zzl zzlVar, zzw zzwVar) {
        this.zzb = zzlVar;
        this.zza = zzwVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zza.zza();
        if (this.zzb.zzb != null) {
            this.zzb.zzb.zzc();
        }
    }
}
