package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class zzaik implements zzpg {
    private /* synthetic */ List zza;
    private /* synthetic */ zzpf zzb;
    private /* synthetic */ Context zzc;

    zzaik(zzaij zzaijVar, List list, zzpf zzpfVar, Context context) {
        this.zza = list;
        this.zzb = zzpfVar;
        this.zzc = context;
    }

    @Override // com.google.android.gms.internal.zzpg
    public final void zza() {
        for (String str : this.zza) {
            String strValueOf = String.valueOf(str);
            zzahw.zzd(strValueOf.length() != 0 ? "Pinging url: ".concat(strValueOf) : new String("Pinging url: "));
            this.zzb.zza(Uri.parse(str), null, null);
        }
        this.zzb.zza((Activity) this.zzc);
    }

    @Override // com.google.android.gms.internal.zzpg
    public final void zzb() {
    }
}
