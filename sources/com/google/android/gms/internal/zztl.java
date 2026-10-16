package com.google.android.gms.internal;

import android.os.Handler;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zztl {
    private final List<zzuk> zza = new LinkedList();

    zztl() {
    }

    final void zza(zzul zzulVar) {
        Handler handler = zzaij.zza;
        Iterator<zzuk> it = this.zza.iterator();
        while (it.hasNext()) {
            handler.post(new zzuj(this, it.next(), zzulVar));
        }
        this.zza.clear();
    }
}
