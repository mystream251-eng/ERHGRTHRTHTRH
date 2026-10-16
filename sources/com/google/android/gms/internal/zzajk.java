package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzajk implements DialogInterface.OnClickListener {
    private /* synthetic */ zzajj zza;

    zzajk(zzajj zzajjVar) {
        this.zza = zzajjVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        zzbt.zze();
        zzaij.zza(this.zza.zza, Uri.parse("https://support.google.com/dfp_premium/answer/7160685#push"));
    }
}
