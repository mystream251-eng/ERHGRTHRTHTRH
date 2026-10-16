package com.google.android.gms.internal;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzzo extends zzzg implements zzapv {
    zzzo(Context context, zzahe zzaheVar, zzaof zzaofVar, zzzn zzznVar) {
        super(context, zzaheVar, zzaofVar, zzznVar);
    }

    @Override // com.google.android.gms.internal.zzzg
    protected final void zza() {
        if (this.zzc.zzd != -2) {
            return;
        }
        this.zzb.zzv().zza(this);
        zzd();
        zzahw.zzb("Loading HTML in WebView.");
        this.zzb.zza(this.zzc.zza, this.zzc.zzb, (String) null);
    }

    protected void zzd() {
    }
}
