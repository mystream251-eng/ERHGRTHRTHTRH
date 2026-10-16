package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zzcv;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzt implements com.google.android.gms.ads.internal.gmsg.zzt<zzaj> {
    private /* synthetic */ zzcv zza;
    private /* synthetic */ zzc zzb;
    private /* synthetic */ zzakf zzc;
    private /* synthetic */ zzn zzd;

    zzt(zzn zznVar, zzcv zzcvVar, zzc zzcVar, zzakf zzakfVar) {
        this.zzd = zznVar;
        this.zza = zzcvVar;
        this.zzb = zzcVar;
        this.zzc = zzakfVar;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaj zzajVar, Map map) {
        synchronized (this.zzd.zza) {
            zzahw.zzd("JS Engine is requesting an update");
            if (this.zzd.zzh == 0) {
                zzahw.zzd("Starting reload.");
                this.zzd.zzh = 2;
                this.zzd.zza(this.zza);
            }
            this.zzb.zzb("/requestReload", (com.google.android.gms.ads.internal.gmsg.zzt<? super zzaj>) this.zzc.zza());
        }
    }
}
