package com.viaccessorca.analytics.dca;

/* JADX INFO: loaded from: classes2.dex */
public interface VoDcaDispatcherListener {
    void onConfigurationReceived(VoDcaConfiguration voDcaConfiguration);

    void onError();

    void onEventSent(VoDcaEvent voDcaEvent);
}
