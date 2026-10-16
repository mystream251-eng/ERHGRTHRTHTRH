package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zabi implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ GoogleApiManager zaim;

    zabi(GoogleApiManager googleApiManager) {
        this.zaim = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        this.zaim.handler.sendMessage(this.zaim.handler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
