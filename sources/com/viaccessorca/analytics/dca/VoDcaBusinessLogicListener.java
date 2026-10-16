package com.viaccessorca.analytics.dca;

/* JADX INFO: loaded from: classes2.dex */
public interface VoDcaBusinessLogicListener {
    void onConfigurationBusinessLogicReloaded();

    void onConfigurationBusinessLogicReloadedFailed();

    void onInitBusinessLogicCompleted();

    void onInitBusinessLogicFailed();
}
