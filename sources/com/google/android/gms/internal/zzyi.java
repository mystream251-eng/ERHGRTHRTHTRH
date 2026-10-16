package com.google.android.gms.internal;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes2.dex */
final class zzyi implements DialogInterface.OnClickListener {
    private /* synthetic */ zzyg zza;

    zzyi(zzyg zzygVar) {
        this.zza = zzygVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zza.zza("User canceled the download.");
    }
}
