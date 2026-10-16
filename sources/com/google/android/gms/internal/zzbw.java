package com.google.android.gms.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbw extends zzbu<Integer, Object> {
    public String zza;
    public long zzb;
    public String zzc;
    public String zzd;
    public String zze;

    public zzbw() {
        this.zza = ExifInterface.LONGITUDE_EAST;
        this.zzb = -1L;
        this.zzc = ExifInterface.LONGITUDE_EAST;
        this.zzd = ExifInterface.LONGITUDE_EAST;
        this.zze = ExifInterface.LONGITUDE_EAST;
    }

    public zzbw(String str) {
        this();
        zza(str);
    }

    @Override // com.google.android.gms.internal.zzbu
    protected final HashMap<Integer, Object> zza() {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(0, this.zza);
        map.put(4, this.zze);
        map.put(3, this.zzd);
        map.put(2, this.zzc);
        map.put(1, Long.valueOf(this.zzb));
        return map;
    }

    @Override // com.google.android.gms.internal.zzbu
    protected final void zza(String str) {
        HashMap mapZzb = zzb(str);
        if (mapZzb != null) {
            Object obj = mapZzb.get(0);
            String str2 = ExifInterface.LONGITUDE_EAST;
            this.zza = obj == null ? ExifInterface.LONGITUDE_EAST : (String) mapZzb.get(0);
            this.zzb = mapZzb.get(1) == null ? -1L : ((Long) mapZzb.get(1)).longValue();
            this.zzc = mapZzb.get(2) == null ? ExifInterface.LONGITUDE_EAST : (String) mapZzb.get(2);
            this.zzd = mapZzb.get(3) == null ? ExifInterface.LONGITUDE_EAST : (String) mapZzb.get(3);
            if (mapZzb.get(4) != null) {
                str2 = (String) mapZzb.get(4);
            }
            this.zze = str2;
        }
    }
}
