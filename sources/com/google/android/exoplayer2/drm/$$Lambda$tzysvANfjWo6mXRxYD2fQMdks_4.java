package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher;

/* JADX INFO: renamed from: com.google.android.exoplayer2.drm.-$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4 implements EventDispatcher.Event {
    public static final /* synthetic */ $$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4 INSTANCE = new $$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4();

    private /* synthetic */ $$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4() {
    }

    @Override // com.google.android.exoplayer2.util.EventDispatcher.Event
    public final void sendTo(Object obj) {
        ((DefaultDrmSessionEventListener) obj).onDrmKeysRestored();
    }
}
