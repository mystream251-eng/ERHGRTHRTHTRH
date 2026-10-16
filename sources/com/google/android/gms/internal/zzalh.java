package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
final class zzalh implements zzalf {
    private /* synthetic */ String zza;

    zzalh(String str) {
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.zzalf
    public final void zza(Object obj) {
    }

    @Override // com.google.android.gms.internal.zzalf
    public final void zza(Throwable th) {
        zzbt.zzi().zza(th, this.zza);
    }
}
