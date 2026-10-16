package com.google.android.gms.internal;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes2.dex */
final class zzyc implements DialogInterface.OnClickListener {
    private /* synthetic */ zzya zza;

    zzyc(zzya zzyaVar) {
        this.zza = zzyaVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zza.zza("Operation denied by user.");
    }
}
