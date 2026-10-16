package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes2.dex */
final class zao implements zal {
    private zao() {
    }

    @Override // com.google.android.gms.internal.base.zal
    public final ExecutorService zaa(int i, ThreadFactory threadFactory, int i2) {
        return Executors.newFixedThreadPool(2, threadFactory);
    }
}
