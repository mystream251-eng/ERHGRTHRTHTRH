package com.viaccessorca.analytics.dca;

/* JADX INFO: loaded from: classes2.dex */
public interface VoDcaControllerListener {
    void onConfigurationReloaded();

    void onConfigurationReloadedFailed();

    void onInitCompleted();

    void onInitFailed();
}
