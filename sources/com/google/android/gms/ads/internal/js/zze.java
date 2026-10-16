package com.google.android.gms.ads.internal.js;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zzlc;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zze implements zzc {
    private final zzaof zza;
    private final Context zzb;

    public zze(Context context, zzala zzalaVar, zzcv zzcvVar, com.google.android.gms.ads.internal.zzv zzvVar) throws zzaop {
        this.zzb = context;
        zzaof zzaofVarZza = zzbt.zzf().zza(context, zzaqa.zza(), "", false, false, zzcvVar, zzalaVar, null, null, null, zziu.zza());
        this.zza = zzaofVarZza;
        zzaofVarZza.zzm().setWillNotDraw(true);
    }

    private static void zza(Runnable runnable) {
        zzlc.zza();
        if (zzako.zzb()) {
            runnable.run();
        } else {
            zzaij.zza.post(runnable);
        }
    }

    @Override // com.google.android.gms.ads.internal.js.zzc
    public final void zza() {
        this.zza.destroy();
    }

    @Override // com.google.android.gms.ads.internal.js.zzc
    public final void zza(zzd zzdVar) {
        this.zza.zzv().zza(new zzk(this, zzdVar));
    }

    @Override // com.google.android.gms.ads.internal.js.zzc
    public final void zza(String str) {
        zza(new zzh(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", str)));
    }

    @Override // com.google.android.gms.ads.internal.js.zzaj
    public final void zza(String str, com.google.android.gms.ads.internal.gmsg.zzt<? super zzaj> zztVar) {
        this.zza.zzv().zza(str, new zzl(this, zztVar));
    }

    @Override // com.google.android.gms.ads.internal.js.zza
    public final void zza(String str, Map<String, ?> map) {
        this.zza.zza(str, map);
    }

    @Override // com.google.android.gms.ads.internal.js.zza
    public final void zza(String str, JSONObject jSONObject) {
        this.zza.zza(str, jSONObject);
    }

    @Override // com.google.android.gms.ads.internal.js.zzc
    public final zzak zzb() {
        return new zzal(this);
    }

    @Override // com.google.android.gms.ads.internal.js.zzc
    public final void zzb(String str) {
        zza(new zzj(this, str));
    }

    @Override // com.google.android.gms.ads.internal.js.zzaj
    public final void zzb(String str, final com.google.android.gms.ads.internal.gmsg.zzt<? super zzaj> zztVar) {
        this.zza.zzv().zza(str, new com.google.android.gms.common.util.zzt(zztVar) { // from class: com.google.android.gms.ads.internal.js.zzf
            private final com.google.android.gms.ads.internal.gmsg.zzt zza;

            {
                this.zza = zztVar;
            }

            public final boolean zza(Object obj) {
                com.google.android.gms.ads.internal.gmsg.zzt zztVar2 = (com.google.android.gms.ads.internal.gmsg.zzt) obj;
                return (zztVar2 instanceof zzl) && ((zzl) zztVar2).zza.equals(this.zza);
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.js.zzm
    public final void zzb(String str, JSONObject jSONObject) {
        zza(new zzg(this, str, jSONObject));
    }

    @Override // com.google.android.gms.ads.internal.js.zzc
    public final void zzc(String str) {
        zza(new zzi(this, str));
    }
}
