package com.google.android.gms.common.providers;

import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class PooledExecutorsProvider {
    private static PooledExecutorFactory zza;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public interface PooledExecutorFactory {
        @Deprecated
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    @Deprecated
    public static synchronized PooledExecutorFactory getInstance() {
        if (zza == null) {
            zza = new zza();
        }
        return zza;
    }

    private PooledExecutorsProvider() {
    }
}
