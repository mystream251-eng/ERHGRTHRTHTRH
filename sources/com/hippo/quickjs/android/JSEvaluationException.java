package com.hippo.quickjs.android;

/* JADX INFO: loaded from: classes2.dex */
public class JSEvaluationException extends RuntimeException {
    private JSException jsException;

    JSEvaluationException(JSException jSException) {
        super(jSException.toString());
    }

    public JSException getJSException() {
        return this.jsException;
    }
}
