package com.google.android.exoplayer2.util;

import android.os.Handler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class EventDispatcher<T> {
    private final CopyOnWriteArrayList<HandlerAndListener<T>> listeners = new CopyOnWriteArrayList<>();

    public interface Event<T> {
        void sendTo(T t);
    }

    public void addListener(Handler handler, T t) {
        Assertions.checkArgument((handler == null || t == null) ? false : true);
        removeListener(t);
        this.listeners.add(new HandlerAndListener<>(handler, t));
    }

    public void removeListener(T t) {
        for (HandlerAndListener<T> handlerAndListener : this.listeners) {
            if (((HandlerAndListener) handlerAndListener).listener == t) {
                handlerAndListener.release();
                this.listeners.remove(handlerAndListener);
            }
        }
    }

    public void dispatch(Event<T> event) {
        Iterator<HandlerAndListener<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().dispatch(event);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class HandlerAndListener<T> {
        private final Handler handler;
        private final T listener;
        private boolean released;

        public HandlerAndListener(Handler handler, T t) {
            this.handler = handler;
            this.listener = t;
        }

        public void release() {
            this.released = true;
        }

        public void dispatch(final Event<T> event) {
            this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.util.-$$Lambda$EventDispatcher$HandlerAndListener$uD_JKgYUi0f_RBL7K02WSc4AoE4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$dispatch$0$EventDispatcher$HandlerAndListener(event);
                }
            });
        }

        public /* synthetic */ void lambda$dispatch$0$EventDispatcher$HandlerAndListener(Event event) {
            if (this.released) {
                return;
            }
            event.sendTo(this.listener);
        }
    }
}
