package com.hippo.quickjs.android;

/* JADX INFO: loaded from: classes2.dex */
public class JSException {
    private String exception;
    private boolean isError;
    private String stack;

    private JSException(boolean z, String str, String str2) {
        this.isError = z;
        this.exception = str;
        this.stack = str2;
    }

    public boolean isError() {
        return this.isError;
    }

    public String getException() {
        return this.exception;
    }

    public String getStack() {
        return this.stack;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.isError) {
            sb.append("Throw: ");
        }
        sb.append(this.exception);
        sb.append("\n");
        String str = this.stack;
        if (str != null) {
            sb.append(str);
        }
        return sb.toString();
    }
}
