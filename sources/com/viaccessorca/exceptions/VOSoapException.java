package com.viaccessorca.exceptions;

/* JADX INFO: loaded from: classes2.dex */
public final class VOSoapException extends VOException {
    private static final long serialVersionUID = -2293389430541029149L;
    private String mCustomData;
    private String mRedirectUrl;
    private String mSoapMessage;

    public VOSoapException(VOStatusCode vOStatusCode) {
        this(vOStatusCode, null, null, null);
    }

    public VOSoapException(VOStatusCode vOStatusCode, String str, String str2) {
        this(vOStatusCode, null, str, str2);
    }

    public VOSoapException(VOStatusCode vOStatusCode, String str, String str2, String str3) {
        super(vOStatusCode);
        this.mCustomData = str2;
        this.mRedirectUrl = str3;
        this.mSoapMessage = str;
    }

    public String getCustomData() {
        return this.mCustomData;
    }

    public String getRedirectUrl() {
        return this.mRedirectUrl;
    }

    public String getSoapMessage() {
        return this.mSoapMessage;
    }
}
