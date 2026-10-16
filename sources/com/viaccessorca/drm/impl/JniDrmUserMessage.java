package com.viaccessorca.drm.impl;

/* JADX INFO: loaded from: classes2.dex */
class JniDrmUserMessage {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f20a;
    private String b;
    private String c;

    public String getCustomData() {
        return this.b;
    }

    public String getRedirectUrl() {
        return this.c;
    }

    public String getSoapMessage() {
        return this.f20a;
    }

    public boolean isSet() {
        return (this.f20a == null && this.b == null && this.c == null) ? false : true;
    }

    public void m2yugdva7tpoe4A14(String str) {
        this.f20a = str;
    }

    public void m2yugdva7tpoe4A15(String str) {
        this.b = str;
    }

    public void m2yugdva7tpoe4A16(String str) {
        this.c = str;
    }
}
