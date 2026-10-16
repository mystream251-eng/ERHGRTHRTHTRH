package com.google.android.gms.internal.common;

import java.io.Serializable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzn {
    public static <T> zzo<T> zza(zzo<T> zzoVar) {
        if ((zzoVar instanceof zzp) || (zzoVar instanceof zzq)) {
            return zzoVar;
        }
        return zzoVar instanceof Serializable ? new zzq(zzoVar) : new zzp(zzoVar);
    }
}
