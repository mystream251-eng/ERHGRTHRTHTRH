package com.viaccessorca.analytics.fma;

/* JADX INFO: loaded from: classes2.dex */
public interface VoFmaControllerListener {
    void onConfigurationReloaded();

    void onConfigurationReloadedFailed();

    void onInitCompleted();

    void onInitFailed();
}
