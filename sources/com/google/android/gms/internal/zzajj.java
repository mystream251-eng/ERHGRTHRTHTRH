package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes2.dex */
final class zzajj implements Runnable {
    final /* synthetic */ Context zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ boolean zzc;
    private /* synthetic */ boolean zzd;

    zzajj(zzaji zzajiVar, Context context, String str, boolean z, boolean z2) {
        this.zza = context;
        this.zzb = str;
        this.zzc = z;
        this.zzd = z2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.zza);
        builder.setMessage(this.zzb);
        builder.setTitle(this.zzc ? "Error" : "Info");
        if (this.zzd) {
            builder.setNeutralButton("Dismiss", (DialogInterface.OnClickListener) null);
        } else {
            builder.setPositiveButton("Learn More", new zzajk(this));
            builder.setNegativeButton("Dismiss", (DialogInterface.OnClickListener) null);
        }
        builder.create().show();
    }
}
