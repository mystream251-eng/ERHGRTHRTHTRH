package com.google.android.gms.internal;

import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcz extends zzbu<Integer, Long> {
    public long zza;
    public long zzb;

    public zzcz() {
        this.zza = -1L;
        this.zzb = -1L;
    }

    public zzcz(String str) {
        this();
        zza(str);
    }

    @Override // com.google.android.gms.internal.zzbu
    protected final HashMap<Integer, Long> zza() {
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, Long.valueOf(this.zza));
        map.put(1, Long.valueOf(this.zzb));
        return map;
    }

    @Override // com.google.android.gms.internal.zzbu
    protected final void zza(String str) {
        HashMap mapZzb = zzb(str);
        if (mapZzb != null) {
            this.zza = ((Long) mapZzb.get(0)).longValue();
            this.zzb = ((Long) mapZzb.get(1)).longValue();
        }
    }
}
