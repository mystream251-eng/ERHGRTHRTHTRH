package io.sentry.android.core;

/* JADX INFO: loaded from: classes2.dex */
final class LoadClass {
    LoadClass() {
    }

    public Class<?> loadClass(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }
}
