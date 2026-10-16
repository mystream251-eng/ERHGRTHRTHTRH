package com.hippo.quickjs.android;

/* JADX INFO: loaded from: classes2.dex */
public final class JSString extends JSValue {
    private final String value;

    JSString(long j, JSContext jSContext, String str) {
        super(j, jSContext);
        this.value = str;
    }

    public String getString() {
        return this.value;
    }
}
