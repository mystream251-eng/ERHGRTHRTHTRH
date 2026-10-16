package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes2.dex */
public class zzaiy extends zzaiw {
    @Override // com.google.android.gms.internal.zzaiw, com.google.android.gms.internal.zzaip
    public final boolean zza(View view) {
        return view.isAttachedToWindow();
    }

    @Override // com.google.android.gms.internal.zzaip
    public final ViewGroup.LayoutParams zzd() {
        return new ViewGroup.LayoutParams(-1, -1);
    }
}
