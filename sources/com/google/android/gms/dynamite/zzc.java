package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzc implements DynamiteModule.VersionPolicy {
    zzc() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.zza zza(Context context, String str, DynamiteModule.VersionPolicy.zzb zzbVar) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.zza zzaVar = new DynamiteModule.VersionPolicy.zza();
        zzaVar.zzb = zzbVar.zza(context, str, false);
        if (zzaVar.zzb == 0) {
            zzaVar.zzc = 0;
        } else {
            zzaVar.zzc = 1;
        }
        return zzaVar;
    }
}
