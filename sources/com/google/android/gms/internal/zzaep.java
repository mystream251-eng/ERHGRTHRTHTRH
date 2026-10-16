package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaep extends zzaev {
    private final String zza;
    private final int zzb;

    public zzaep(String str, int i) {
        this.zza = str;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzaep)) {
            zzaep zzaepVar = (zzaep) obj;
            if (com.google.android.gms.common.internal.zzbg.zza(this.zza, zzaepVar.zza) && com.google.android.gms.common.internal.zzbg.zza(Integer.valueOf(this.zzb), Integer.valueOf(zzaepVar.zzb))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.zzaeu
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzaeu
    public final int zzb() {
        return this.zzb;
    }
}
