package com.google.android.gms.internal;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class zzaiw extends zzaiv {
    @Override // com.google.android.gms.internal.zzaip
    public boolean zza(View view) {
        return super.zza(view) || view.getWindowId() != null;
    }

    @Override // com.google.android.gms.internal.zzaip
    public final int zzc() {
        return 14;
    }
}
