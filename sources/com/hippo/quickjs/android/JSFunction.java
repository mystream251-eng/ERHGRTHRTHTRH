package com.hippo.quickjs.android;

/* JADX INFO: loaded from: classes2.dex */
public final class JSFunction extends JSObject {
    JSFunction(long j, JSContext jSContext) {
        super(j, jSContext, null);
    }

    public JSValue invoke(JSValue jSValue, JSValue[] jSValueArr) {
        JSValue jSValueWrapAsJSValue;
        if (jSValue != null) {
            checkSameJSContext(jSValue);
        }
        for (JSValue jSValue2 : jSValueArr) {
            checkSameJSContext(jSValue2);
        }
        long[] jArr = new long[jSValueArr.length];
        for (int i = 0; i < jSValueArr.length; i++) {
            jArr[i] = jSValueArr[i].pointer;
        }
        synchronized (this.jsContext.jsRuntime) {
            jSValueWrapAsJSValue = this.jsContext.wrapAsJSValue(QuickJS.invokeValueFunction(this.jsContext.checkClosed(), this.pointer, jSValue != null ? jSValue.pointer : 0L, jArr));
        }
        return jSValueWrapAsJSValue;
    }
}
