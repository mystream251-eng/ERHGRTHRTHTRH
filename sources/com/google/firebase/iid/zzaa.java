package com.google.firebase.iid;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaa implements InstanceIdResult {
    private final String zza;
    private final String zzb;

    zzaa(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getId() {
        return this.zza;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getToken() {
        return this.zzb;
    }
}
