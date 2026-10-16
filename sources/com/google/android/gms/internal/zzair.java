package com.google.android.gms.internal;

import android.app.DownloadManager;

/* JADX INFO: loaded from: classes2.dex */
public class zzair extends zzaip {
    public zzair() {
        super();
    }

    @Override // com.google.android.gms.internal.zzaip
    public final int zza() {
        return 6;
    }

    @Override // com.google.android.gms.internal.zzaip
    public boolean zza(DownloadManager.Request request) {
        request.setShowRunningNotification(true);
        return true;
    }

    @Override // com.google.android.gms.internal.zzaip
    public final int zzb() {
        return 7;
    }
}
