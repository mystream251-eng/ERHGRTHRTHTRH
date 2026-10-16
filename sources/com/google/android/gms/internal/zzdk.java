package com.google.android.gms.internal;

import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdk extends zzbu<Integer, Object> {
    public Long zza;
    public Boolean zzb;
    public Boolean zzc;

    public zzdk() {
    }

    public zzdk(String str) {
        zza(str);
    }

    @Override // com.google.android.gms.internal.zzbu
    protected final HashMap<Integer, Object> zza() {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(0, this.zza);
        map.put(1, this.zzb);
        map.put(2, this.zzc);
        return map;
    }

    @Override // com.google.android.gms.internal.zzbu
    protected final void zza(String str) {
        HashMap mapZzb = zzb(str);
        if (mapZzb != null) {
            this.zza = (Long) mapZzb.get(0);
            this.zzb = (Boolean) mapZzb.get(1);
            this.zzc = (Boolean) mapZzb.get(2);
        }
    }
}
