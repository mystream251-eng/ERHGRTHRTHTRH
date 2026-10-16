package com.google.android.gms.internal;

import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
final class zzanj implements Runnable {
    zzanj(zzani zzaniVar) {
    }

    @Override // java.lang.Runnable
    public final void run() {
        Looper.myLooper().quit();
    }
}
