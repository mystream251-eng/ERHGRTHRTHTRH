package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* JADX INFO: loaded from: classes2.dex */
final class zzaph implements DialogInterface.OnClickListener {
    private /* synthetic */ JsPromptResult zza;

    zzaph(JsPromptResult jsPromptResult) {
        this.zza = jsPromptResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zza.cancel();
    }
}
