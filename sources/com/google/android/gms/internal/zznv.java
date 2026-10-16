package com.google.android.gms.internal;

import android.os.Environment;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zznv implements Callable<Boolean> {
    zznv() {
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Boolean call() throws Exception {
        return Boolean.valueOf("mounted".equals(Environment.getExternalStorageState()));
    }
}
