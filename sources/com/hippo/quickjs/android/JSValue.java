package com.hippo.quickjs.android;

/* JADX INFO: loaded from: classes2.dex */
public abstract class JSValue {
    final JSContext jsContext;
    final long pointer;

    JSValue(long j, JSContext jSContext) {
        this.pointer = j;
        this.jsContext = jSContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends JSValue> T cast(Class<T> cls) {
        if (cls.isInstance(this)) {
            return this;
        }
        throw new JSDataException("expected: " + cls.getSimpleName() + ", actual: " + getClass().getSimpleName());
    }

    final void checkSameJSContext(JSValue jSValue) {
        if (jSValue.jsContext != this.jsContext) {
            throw new IllegalStateException("Two JSValues are not from the same JSContext");
        }
    }
}
