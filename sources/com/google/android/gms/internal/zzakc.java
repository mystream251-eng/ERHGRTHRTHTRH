package com.google.android.gms.internal;

import android.graphics.Bitmap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzakc {
    private Map<Integer, Bitmap> zza = new ConcurrentHashMap();
    private AtomicInteger zzb = new AtomicInteger(0);

    public final int zza(Bitmap bitmap) {
        if (bitmap == null) {
            zzahw.zzb("Bitmap is null. Skipping putting into the Memory Map.");
            return -1;
        }
        int andIncrement = this.zzb.getAndIncrement();
        this.zza.put(Integer.valueOf(andIncrement), bitmap);
        return andIncrement;
    }

    public final Bitmap zza(Integer num) {
        return this.zza.get(num);
    }

    public final void zzb(Integer num) {
        this.zza.remove(num);
    }
}
