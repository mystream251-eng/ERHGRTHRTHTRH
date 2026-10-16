package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzajv implements zzy {
    private /* synthetic */ String zza;
    private /* synthetic */ zzajy zzb;

    zzajv(zzajr zzajrVar, String str, zzajy zzajyVar) {
        this.zza = str;
        this.zzb = zzajyVar;
    }

    public final void zza(zzae zzaeVar) {
        String str = this.zza;
        String string = zzaeVar.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(string).length());
        sb.append("Failed to load URL: ");
        sb.append(str);
        sb.append("\n");
        sb.append(string);
        zzahw.zze(sb.toString());
        this.zzb.zza((Object) null);
    }
}
