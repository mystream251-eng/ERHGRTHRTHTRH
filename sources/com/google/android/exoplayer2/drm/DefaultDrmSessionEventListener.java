package com.google.android.exoplayer2.drm;

/* JADX INFO: loaded from: classes.dex */
public interface DefaultDrmSessionEventListener {
    void onDrmKeysLoaded();

    void onDrmKeysRemoved();

    void onDrmKeysRestored();

    default void onDrmSessionAcquired() {
    }

    void onDrmSessionManagerError(Exception exc);

    default void onDrmSessionReleased() {
    }
}
