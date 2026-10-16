package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzkq extends zzlz {
    private final AppEventListener zza;

    public zzkq(AppEventListener appEventListener) {
        this.zza = appEventListener;
    }

    public final AppEventListener zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzly
    public final void zza(String str, String str2) {
        this.zza.onAppEvent(str, str2);
    }
}
