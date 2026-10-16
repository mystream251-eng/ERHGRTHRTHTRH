package com.viaccessorca.analytics.fma;

/* JADX INFO: loaded from: classes2.dex */
public interface VoFmaDispatcherListener {
    void onConfigurationReceived(VoFmaConfiguration voFmaConfiguration);

    void onError();

    void onEventSent(VoFmaEvent voFmaEvent);
}
