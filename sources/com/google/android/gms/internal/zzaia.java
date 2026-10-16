package com.google.android.gms.internal;

import android.os.Bundle;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
final class zzaia extends zzaib {
    private /* synthetic */ Bundle zza;
    private /* synthetic */ zzahy zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaia(zzahy zzahyVar, Bundle bundle) {
        super(null);
        this.zzb = zzahyVar;
        this.zza = bundle;
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        Iterator it = this.zzb.zzd.iterator();
        while (it.hasNext()) {
            ((zzaic) it.next()).zza(this.zza);
        }
    }
}
