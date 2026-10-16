package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzn extends zzl {
    private final Callable<String> zzb;

    private zzn(Callable<String> callable) {
        super(false, null, null);
        this.zzb = callable;
    }

    @Override // com.google.android.gms.common.zzl
    final String zzb() {
        try {
            return this.zzb.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
