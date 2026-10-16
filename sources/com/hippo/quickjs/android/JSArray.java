package com.hippo.quickjs.android;

/* JADX INFO: loaded from: classes2.dex */
public final class JSArray extends JSObject {
    JSArray(long j, JSContext jSContext) {
        super(j, jSContext, null);
    }

    public int getLength() {
        return ((JSNumber) getProperty("length").cast(JSNumber.class)).getInt();
    }
}
