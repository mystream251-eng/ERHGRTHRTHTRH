package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes2.dex */
final class zzav implements com.google.android.gms.ads.internal.gmsg.zzt<zzaof> {
    private /* synthetic */ CountDownLatch zza;

    zzav(CountDownLatch countDownLatch) {
        this.zza = countDownLatch;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        zzahw.zze("Adapter returned an ad, but assets substitution failed");
        this.zza.countDown();
        zzaofVar.destroy();
    }
}
