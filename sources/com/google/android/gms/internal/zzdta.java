package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdta {
    public static zzdwn zza(String str, String str2, String str3, int i, boolean z) {
        zzdwn.zza zzaVarZza = zzdwn.zzg().zza(str2);
        String strValueOf = String.valueOf(str3);
        return (zzdwn) zzaVarZza.zzb(strValueOf.length() != 0 ? "type.googleapis.com/google.crypto.tink.".concat(strValueOf) : new String("type.googleapis.com/google.crypto.tink.")).zza(0).zza(true).zzc(str).zzd();
    }
}
