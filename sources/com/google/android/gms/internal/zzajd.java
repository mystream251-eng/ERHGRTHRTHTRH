package com.google.android.gms.internal;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes2.dex */
final class zzajd implements DialogInterface.OnClickListener {
    private /* synthetic */ int zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ int zzc;
    private /* synthetic */ zzajc zzd;

    zzajd(zzajc zzajcVar, int i, int i2, int i3) {
        this.zzd = zzajcVar;
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == this.zza) {
            this.zzd.zzb();
            return;
        }
        if (i == this.zzb) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzge)).booleanValue()) {
                this.zzd.zzc();
                return;
            }
        }
        if (i == this.zzc) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzgf)).booleanValue()) {
                this.zzd.zzd();
            }
        }
    }
}
