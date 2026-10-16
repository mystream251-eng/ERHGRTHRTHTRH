package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.internal.zzaan;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public abstract class zzzw<T extends zzaan> implements zzaan<T> {
    private final HashMap<String, List<zzt<? super T>>> zza = new HashMap<>();

    @Override // com.google.android.gms.internal.zzaan
    public void zza(String str, zzt<? super T> zztVar) {
        List<zzt<? super T>> copyOnWriteArrayList = this.zza.get(str);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.zza.put(str, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(zztVar);
    }

    @Override // com.google.android.gms.internal.zzaan
    public void zzb(String str, zzt<? super T> zztVar) {
        List<zzt<? super T>> list = this.zza.get(str);
        if (list == null) {
            return;
        }
        list.remove(zztVar);
    }
}
