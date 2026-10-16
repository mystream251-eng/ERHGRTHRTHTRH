package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.common.util.zzs;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzzm {
    public static zzajb zza(Context context, zza zzaVar, zzahe zzaheVar, zzcv zzcvVar, zzaof zzaofVar, zzwf zzwfVar, zzzn zzznVar, zzov zzovVar) {
        zzajb zzzuVar;
        zzacj zzacjVar = zzaheVar.zzb;
        if (zzacjVar.zzg) {
            zzzuVar = new zzzs(context, zzaheVar, zzwfVar, zzznVar, zzovVar, zzaofVar);
        } else if (zzacjVar.zzs || (zzaVar instanceof com.google.android.gms.ads.internal.zzbb)) {
            zzzuVar = (zzacjVar.zzs && (zzaVar instanceof com.google.android.gms.ads.internal.zzbb)) ? new zzzu(context, (com.google.android.gms.ads.internal.zzbb) zzaVar, zzaheVar, zzcvVar, zzznVar, zzovVar) : new zzzp(zzaheVar, zzznVar);
        } else {
            zzzuVar = (((Boolean) zzlc.zzf().zza(zzoi.zzbd)).booleanValue() && zzs.zze() && !zzs.zzg() && zzaofVar != null && zzaofVar.zzt().zzd()) ? new zzzr(context, zzaheVar, zzaofVar, zzznVar) : new zzzo(context, zzaheVar, zzaofVar, zzznVar);
        }
        String strValueOf = String.valueOf(zzzuVar.getClass().getName());
        zzahw.zzb(strValueOf.length() != 0 ? "AdRenderer: ".concat(strValueOf) : new String("AdRenderer: "));
        zzzuVar.zzc();
        return zzzuVar;
    }
}
