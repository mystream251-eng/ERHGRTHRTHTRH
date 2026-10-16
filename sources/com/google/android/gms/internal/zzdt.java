package com.google.android.gms.internal;

import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdt extends zzbu<Integer, Long> {
    public Long zza;
    public Long zzb;
    public Long zzc;

    public zzdt() {
    }

    public zzdt(String str) {
        zza(str);
    }

    @Override // com.google.android.gms.internal.zzbu
    protected final HashMap<Integer, Long> zza() {
        HashMap<Integer, Long> map = new HashMap<>();
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
            this.zzb = (Long) mapZzb.get(1);
            this.zzc = (Long) mapZzb.get(2);
        }
    }
}
