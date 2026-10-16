package com.google.android.gms.internal;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaec {
    private WeakHashMap<Context, zzaee> zza = new WeakHashMap<>();

    public final Future<zzaea> zza(Context context) {
        return zzaid.zza(zzaid.zza, new zzaed(this, context));
    }
}
