package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzb implements DynamiteModule.VersionPolicy.zzb {
    zzb() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb
    public final int zza(Context context, String str, boolean z) throws DynamiteModule.LoadingException {
        return DynamiteModule.zza(context, str, z);
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb
    public final int zza(Context context, String str) {
        return DynamiteModule.getLocalVersion(context, str);
    }
}
