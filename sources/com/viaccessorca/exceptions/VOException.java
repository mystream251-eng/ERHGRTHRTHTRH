package com.viaccessorca.exceptions;

/* JADX INFO: loaded from: classes2.dex */
public class VOException extends Exception {
    private static final long serialVersionUID = -8236620270022167701L;
    private VOStatusCode mCode;

    public VOException(VOStatusCode vOStatusCode) {
        this(vOStatusCode, null);
    }

    public VOException(VOStatusCode vOStatusCode, String str) {
        super(str);
        this.mCode = vOStatusCode;
    }

    public VOStatusCode getStatus() {
        return this.mCode;
    }
}
