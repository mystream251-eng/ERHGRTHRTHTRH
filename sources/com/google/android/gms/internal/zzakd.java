package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzakd extends zzahs {
    private final zzakz zza;
    private final String zzb;

    public zzakd(Context context, String str, String str2) {
        this(str2, zzbt.zze().zza(context, str));
    }

    private zzakd(String str, String str2) {
        this.zza = new zzakz(str2);
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        this.zza.zza(this.zzb);
    }
}
