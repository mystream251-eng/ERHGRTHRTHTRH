package com.viaccessorca.analytics.dca;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VoDcaEventHandler {
    protected VoDcaConfiguration mConfiguration;
    protected VoDcaDispatcher mDispatcher;
    protected VoDcaEventHandlerListener mListener;

    public abstract void handleEvent(VoDcaEvent voDcaEvent);

    public abstract void stop();

    public abstract long tick();

    public VoDcaEventHandler(VoDcaDispatcher voDcaDispatcher, VoDcaConfiguration voDcaConfiguration) {
        this.mDispatcher = voDcaDispatcher;
        this.mConfiguration = voDcaConfiguration;
    }

    public void setListener(VoDcaEventHandlerListener voDcaEventHandlerListener) {
        this.mListener = voDcaEventHandlerListener;
    }
}
