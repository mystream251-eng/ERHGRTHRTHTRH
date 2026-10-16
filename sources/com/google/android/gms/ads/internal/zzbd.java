package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.android.gms.internal.zzacf;
import com.google.android.gms.internal.zzacg;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzali;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpx;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class zzbd implements Callable<zzpx> {
    private /* synthetic */ int zza;
    private /* synthetic */ JSONArray zzb;
    private /* synthetic */ int zzc;
    private /* synthetic */ zzahe zzd;
    private /* synthetic */ zzbb zze;

    zzbd(zzbb zzbbVar, int i, JSONArray jSONArray, int i2, zzahe zzaheVar) {
        this.zze = zzbbVar;
        this.zza = i;
        this.zzb = jSONArray;
        this.zzc = i2;
        this.zzd = zzaheVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzpx call() throws Exception {
        if (this.zza >= this.zzb.length()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.zzb.get(this.zza));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ads", jSONArray);
        zzbb zzbbVar = new zzbb(this.zze.zze.zzc, this.zze.zzh, this.zze.zze.zzi, this.zze.zze.zzb, this.zze.zzi, this.zze.zze.zze, true);
        zzbb.zza(this.zze.zze, zzbbVar.zze);
        zzbbVar.zzau();
        zzbbVar.zza(this.zze.zzb);
        zzov zzovVar = zzbbVar.zza;
        int i = this.zza;
        zzovVar.zza("num_ads_requested", String.valueOf(this.zzc));
        zzovVar.zza("ad_index", String.valueOf(i));
        zzacf zzacfVar = this.zzd.zza;
        String string = jSONObject.toString();
        Bundle bundle = zzacfVar.zzc.zzc != null ? new Bundle(zzacfVar.zzc.zzc) : new Bundle();
        bundle.putString("_ad", string);
        zzbbVar.zza(new zzacg(zzacfVar.zzb, new zzkk(zzacfVar.zzc.zza, zzacfVar.zzc.zzb, bundle, zzacfVar.zzc.zzd, zzacfVar.zzc.zze, zzacfVar.zzc.zzf, zzacfVar.zzc.zzg, zzacfVar.zzc.zzh, zzacfVar.zzc.zzi, zzacfVar.zzc.zzj, zzacfVar.zzc.zzk, zzacfVar.zzc.zzl, zzacfVar.zzc.zzm, zzacfVar.zzc.zzn, zzacfVar.zzc.zzo, zzacfVar.zzc.zzp, zzacfVar.zzc.zzq, zzacfVar.zzc.zzr), zzacfVar.zzd, zzacfVar.zze, zzacfVar.zzf, zzacfVar.zzg, zzacfVar.zzi, zzacfVar.zzj, zzacfVar.zzk, zzacfVar.zzl, zzacfVar.zzn, zzacfVar.zzz, zzacfVar.zzo, zzacfVar.zzp, zzacfVar.zzq, zzacfVar.zzr, zzacfVar.zzs, zzacfVar.zzt, zzacfVar.zzu, zzacfVar.zzv, zzacfVar.zzw, zzacfVar.zzx, zzacfVar.zzy, zzacfVar.zzab, zzacfVar.zzac, zzacfVar.zzai, zzacfVar.zzad, zzacfVar.zzae, zzacfVar.zzaf, zzacfVar.zzag, zzali.zza(zzacfVar.zzah), zzacfVar.zzaj, zzacfVar.zzak, zzacfVar.zzal, zzacfVar.zzam, zzacfVar.zzan, zzacfVar.zzao, zzacfVar.zzap, zzacfVar.zzaq, zzacfVar.zzau, zzali.zza(zzacfVar.zzh), zzacfVar.zzav, zzacfVar.zzaw, zzacfVar.zzax, 1, zzacfVar.zzaz, zzacfVar.zzba, zzacfVar.zzbb), zzbbVar.zza);
        return zzbbVar.zzaw().get();
    }
}
