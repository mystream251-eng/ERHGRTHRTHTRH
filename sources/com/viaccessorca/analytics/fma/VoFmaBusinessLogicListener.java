package com.viaccessorca.analytics.fma;

/* JADX INFO: loaded from: classes2.dex */
public interface VoFmaBusinessLogicListener {
    void onConfigurationBusinessLogicReloaded();

    void onConfigurationBusinessLogicReloadedFailed();

    void onInitBusinessLogicCompleted();

    void onInitBusinessLogicFailed();
}
