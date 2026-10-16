package com.viaccessorca.analytics.fma;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VoFmaEventHandler {
    protected VoFmaConfiguration mConfiguration;
    protected VoFmaDispatcher mDispatcher;
    protected VoFmaEventHandlerListener mListener;

    public abstract void handleEvent(VoFmaEvent voFmaEvent);

    public abstract void stop();

    public abstract long tick();

    public VoFmaEventHandler(VoFmaDispatcher voFmaDispatcher, VoFmaConfiguration voFmaConfiguration) {
        this.mDispatcher = voFmaDispatcher;
        this.mConfiguration = voFmaConfiguration;
    }

    public void setListener(VoFmaEventHandlerListener voFmaEventHandlerListener) {
        this.mListener = voFmaEventHandlerListener;
    }
}
