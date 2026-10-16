package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

/* JADX INFO: loaded from: classes2.dex */
final class zzapi implements DialogInterface.OnClickListener {
    private /* synthetic */ JsPromptResult zza;
    private /* synthetic */ EditText zzb;

    zzapi(JsPromptResult jsPromptResult, EditText editText) {
        this.zza = jsPromptResult;
        this.zzb = editText;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zza.confirm(this.zzb.getText().toString());
    }
}
