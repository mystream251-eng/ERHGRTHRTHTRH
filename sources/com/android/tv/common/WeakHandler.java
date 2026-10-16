package com.android.tv.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class WeakHandler<T> extends Handler {
    private final WeakReference<T> mRef;

    protected abstract void handleMessage(Message message, T t);

    public WeakHandler(Looper looper, T t) {
        super(looper);
        this.mRef = new WeakReference<>(t);
    }

    public WeakHandler(T t) {
        this.mRef = new WeakReference<>(t);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        T t = this.mRef.get();
        if (t == null) {
            return;
        }
        handleMessage(message, t);
    }
}
