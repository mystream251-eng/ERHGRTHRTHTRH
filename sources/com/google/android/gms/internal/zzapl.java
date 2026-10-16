package com.google.android.gms.internal;

import android.view.View;
import android.webkit.WebChromeClient;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzapl extends zzapc {
    public zzapl(zzaof zzaofVar) {
        super(zzaofVar);
    }

    @Override // android.webkit.WebChromeClient
    public final void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        zza(view, i, customViewCallback);
    }
}
