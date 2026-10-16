package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzanx implements Iterable<zzanv> {
    private final List<zzanv> zza = new LinkedList();

    public static boolean zza(zzann zzannVar) {
        zzanv zzanvVarZzb = zzb(zzannVar);
        if (zzanvVarZzb == null) {
            return false;
        }
        zzanvVarZzb.zzb.zza();
        return true;
    }

    static zzanv zzb(zzann zzannVar) {
        for (zzanv zzanvVar : zzbt.zzy()) {
            if (zzanvVar.zza == zzannVar) {
                return zzanvVar;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public final Iterator<zzanv> iterator() {
        return this.zza.iterator();
    }

    public final int zza() {
        return this.zza.size();
    }

    public final void zza(zzanv zzanvVar) {
        this.zza.add(zzanvVar);
    }

    public final void zzb(zzanv zzanvVar) {
        this.zza.remove(zzanvVar);
    }
}
