package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzahg {
    private long zza = -1;
    private long zzb = -1;

    public final long zza() {
        return this.zzb;
    }

    public final void zzb() {
        this.zzb = SystemClock.elapsedRealtime();
    }

    public final void zzc() {
        this.zza = SystemClock.elapsedRealtime();
    }

    public final Bundle zzd() {
        Bundle bundle = new Bundle();
        bundle.putLong("topen", this.zza);
        bundle.putLong("tclose", this.zzb);
        return bundle;
    }
}
