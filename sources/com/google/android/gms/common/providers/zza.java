package com.google.android.gms.common.providers;

import com.google.android.gms.internal.common.zze;
import com.google.android.gms.internal.common.zzj;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zza implements PooledExecutorsProvider.PooledExecutorFactory {
    zza() {
    }

    @Override // com.google.android.gms.common.providers.PooledExecutorsProvider.PooledExecutorFactory
    public final ScheduledExecutorService newSingleThreadScheduledExecutor() {
        return zze.zza().zza(1, zzj.zza);
    }
}
