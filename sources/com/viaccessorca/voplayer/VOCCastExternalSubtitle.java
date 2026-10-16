package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public final class VOCCastExternalSubtitle {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f60a;
    private String b;
    private String c;

    public VOCCastExternalSubtitle(String str, String str2, String str3) {
        this.f60a = null;
        this.b = null;
        this.c = null;
        this.f60a = str;
        this.b = str2;
        this.c = str3;
    }

    public String getLanguage() {
        return this.b;
    }

    public String getName() {
        return this.f60a;
    }

    public String getUrl() {
        return this.c;
    }
}
