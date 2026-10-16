package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zabk implements Runnable {
    private final /* synthetic */ GoogleApiManager.zaa zaiy;

    zabk(GoogleApiManager.zaa zaaVar) {
        this.zaiy = zaaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaiy.zabh();
    }
}
