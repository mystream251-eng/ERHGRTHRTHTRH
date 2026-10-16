package com.google.android.gms.internal;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
final class zzye implements View.OnClickListener {
    private /* synthetic */ zzyd zza;

    zzye(zzyd zzydVar) {
        this.zza = zzydVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zza.zza(true);
    }
}
