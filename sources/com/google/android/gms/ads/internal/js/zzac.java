package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzami;

/* JADX INFO: loaded from: classes2.dex */
final class zzac implements zzami<zzaj> {
    private /* synthetic */ zzaa zza;

    zzac(zzaa zzaaVar) {
        this.zza = zzaaVar;
    }

    @Override // com.google.android.gms.internal.zzami
    public final /* synthetic */ void zza(zzaj zzajVar) {
        zzahw.zza("Releasing engine reference.");
        this.zza.zzb.zzb();
    }
}
