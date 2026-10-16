package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzbt;
import com.viaccessorca.vodownloader.NanoHTTPD;

/* JADX INFO: loaded from: classes2.dex */
final class zzaje implements DialogInterface.OnClickListener {
    private /* synthetic */ String zza;
    private /* synthetic */ zzajc zzb;

    zzaje(zzajc zzajcVar, String str) {
        this.zzb = zzajcVar;
        this.zza = str;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        zzbt.zze();
        zzaij.zza(this.zzb.zza, Intent.createChooser(new Intent("android.intent.action.SEND").setType(NanoHTTPD.MIME_PLAINTEXT).putExtra("android.intent.extra.TEXT", this.zza), "Share via"));
    }
}
