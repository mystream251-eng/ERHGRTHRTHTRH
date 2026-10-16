package com.hippo.quickjs.android;

import java.io.Closeable;

/* JADX INFO: loaded from: classes2.dex */
public class JSRuntime implements Closeable {
    protected long pointer;
    protected final QuickJS quickJS;

    public interface InterruptHandler {
        boolean onInterrupt();
    }

    protected JSRuntime(long j, QuickJS quickJS) {
        this.pointer = j;
        this.quickJS = quickJS;
    }

    protected void checkClosed() {
        if (this.pointer == 0) {
            throw new IllegalStateException("The JSRuntime is closed");
        }
    }

    public synchronized void setMallocLimit(int i) {
        checkClosed();
        if (i == 0 || i < -1) {
            throw new IllegalArgumentException("Only positive number and -1 are accepted as malloc limit");
        }
        QuickJS.setRuntimeMallocLimit(this.pointer, i);
    }

    public synchronized void setInterruptHandler(InterruptHandler interruptHandler) {
        checkClosed();
        QuickJS.setRuntimeInterruptHandler(this.pointer, interruptHandler);
    }

    public synchronized JSContext createJSContext() {
        long jCreateContext;
        checkClosed();
        jCreateContext = QuickJS.createContext(this.pointer);
        if (jCreateContext == 0) {
            throw new IllegalStateException("Cannot create JSContext instance");
        }
        return new JSContext(jCreateContext, this.quickJS, this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.pointer != 0) {
            long j = this.pointer;
            this.pointer = 0L;
            QuickJS.destroyRuntime(j);
        }
    }
}
