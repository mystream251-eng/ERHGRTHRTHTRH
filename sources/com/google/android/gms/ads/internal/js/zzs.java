package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzahw;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzs implements com.google.android.gms.ads.internal.gmsg.zzt<zzaj> {
    private /* synthetic */ zzae zza;
    private /* synthetic */ zzc zzb;
    private /* synthetic */ zzn zzc;

    zzs(zzn zznVar, zzae zzaeVar, zzc zzcVar) {
        this.zzc = zznVar;
        this.zza = zzaeVar;
        this.zzb = zzcVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaj zzajVar, Map map) {
        synchronized (this.zzc.zza) {
            if (this.zza.zze() != -1 && this.zza.zze() != 1) {
                this.zzc.zzh = 0;
                this.zzc.zze.zza(this.zzb);
                this.zza.zza(this.zzb);
                this.zzc.zzg = this.zza;
                zzahw.zza("Successfully loaded JS Engine.");
            }
        }
    }
}
