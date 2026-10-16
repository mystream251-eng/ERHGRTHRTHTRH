package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;

/* JADX INFO: loaded from: classes2.dex */
final class zztf extends zzamd<ParcelFileDescriptor> {
    private /* synthetic */ zzte zza;

    zztf(zzte zzteVar) {
        this.zza = zzteVar;
    }

    @Override // com.google.android.gms.internal.zzamd, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        this.zza.zza();
        return super.cancel(z);
    }
}
