package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class zzia {
    final long zza;
    final String zzb;
    final int zzc;

    zzia(long j, String str, int i) {
        this.zza = j;
        this.zzb = str;
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzia)) {
            zzia zziaVar = (zzia) obj;
            if (zziaVar.zza == this.zza && zziaVar.zzc == this.zzc) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (int) this.zza;
    }
}
