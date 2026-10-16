package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzyb implements DialogInterface.OnClickListener {
    private /* synthetic */ zzya zza;

    zzyb(zzya zzyaVar) {
        this.zza = zzyaVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Intent intentZzb = this.zza.zzb();
        zzbt.zze();
        zzaij.zza(this.zza.zzb, intentZzb);
    }
}
