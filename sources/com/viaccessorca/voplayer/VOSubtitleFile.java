package com.viaccessorca.voplayer;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class VOSubtitleFile {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f91a;
    private String b;
    private String c;
    private String d;

    protected VOSubtitleFile(String str, int i, String str2, String str3, WeakReference<VOPlayer> weakReference) {
        this.b = str;
        this.f91a = i;
        this.c = str2;
        this.d = str3;
    }

    public int getFileId() {
        return this.f91a;
    }

    public String getLanguage() {
        return this.d;
    }

    public String getName() {
        return this.c;
    }

    public String getPath() {
        return this.b;
    }
}
