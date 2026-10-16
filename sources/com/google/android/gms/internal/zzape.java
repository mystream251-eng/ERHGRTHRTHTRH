package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* JADX INFO: loaded from: classes2.dex */
final class zzape implements DialogInterface.OnClickListener {
    private /* synthetic */ JsResult zza;

    zzape(JsResult jsResult) {
        this.zza = jsResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zza.cancel();
    }
}
