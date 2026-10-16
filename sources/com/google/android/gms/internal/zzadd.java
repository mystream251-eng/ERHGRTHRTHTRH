package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzaj;

/* JADX INFO: loaded from: classes2.dex */
final class zzadd implements zzami<zzaj> {
    private /* synthetic */ zzadc zza;

    zzadd(zzadc zzadcVar) {
        this.zza = zzadcVar;
    }

    @Override // com.google.android.gms.internal.zzami
    public final /* synthetic */ void zza(zzaj zzajVar) {
        try {
            zzajVar.zzb("AFMA_getAdapterLessMediationAd", this.zza.zza);
        } catch (Exception e) {
            zzahw.zzb("Error requesting an ad url", e);
            zzada.zzf.zzb(this.zza.zzb);
        }
    }
}
