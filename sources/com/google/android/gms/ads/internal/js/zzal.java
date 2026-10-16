package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzal implements zzak {
    private final zzaj zza;
    private final HashSet<AbstractMap.SimpleEntry<String, com.google.android.gms.ads.internal.gmsg.zzt<? super zzaj>>> zzb = new HashSet<>();

    public zzal(zzaj zzajVar) {
        this.zza = zzajVar;
    }

    @Override // com.google.android.gms.ads.internal.js.zzak
    public final void zza() {
        for (AbstractMap.SimpleEntry<String, com.google.android.gms.ads.internal.gmsg.zzt<? super zzaj>> simpleEntry : this.zzb) {
            String strValueOf = String.valueOf(simpleEntry.getValue().toString());
            zzahw.zza(strValueOf.length() != 0 ? "Unregistering eventhandler: ".concat(strValueOf) : new String("Unregistering eventhandler: "));
            this.zza.zzb(simpleEntry.getKey(), simpleEntry.getValue());
        }
        this.zzb.clear();
    }

    @Override // com.google.android.gms.ads.internal.js.zzaj
    public final void zza(String str, com.google.android.gms.ads.internal.gmsg.zzt<? super zzaj> zztVar) {
        this.zza.zza(str, zztVar);
        this.zzb.add(new AbstractMap.SimpleEntry<>(str, zztVar));
    }

    @Override // com.google.android.gms.ads.internal.js.zza
    public final void zza(String str, Map<String, ?> map) {
        this.zza.zza(str, map);
    }

    @Override // com.google.android.gms.ads.internal.js.zza
    public final void zza(String str, JSONObject jSONObject) {
        this.zza.zza(str, jSONObject);
    }

    @Override // com.google.android.gms.ads.internal.js.zzaj
    public final void zzb(String str, com.google.android.gms.ads.internal.gmsg.zzt<? super zzaj> zztVar) {
        this.zza.zzb(str, zztVar);
        this.zzb.remove(new AbstractMap.SimpleEntry(str, zztVar));
    }

    @Override // com.google.android.gms.ads.internal.js.zzm
    public final void zzb(String str, JSONObject jSONObject) {
        this.zza.zzb(str, jSONObject);
    }
}
