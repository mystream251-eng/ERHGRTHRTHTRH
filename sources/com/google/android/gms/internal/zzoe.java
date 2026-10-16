package com.google.android.gms.internal;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzoe {
    private final Collection<zzny<?>> zza = new ArrayList();
    private final Collection<zzny<String>> zzb = new ArrayList();
    private final Collection<zzny<String>> zzc = new ArrayList();

    public final List<String> zza() {
        ArrayList arrayList = new ArrayList();
        Iterator<zzny<String>> it = this.zzb.iterator();
        while (it.hasNext()) {
            String str = (String) zzlc.zzf().zza(it.next());
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final void zza(SharedPreferences.Editor editor, int i, JSONObject jSONObject) {
        for (zzny<?> zznyVar : this.zza) {
            if (zznyVar.zzc() == 1) {
                zznyVar.zza(editor, zznyVar.zza(jSONObject));
            }
        }
    }

    public final void zza(zzny zznyVar) {
        this.zza.add(zznyVar);
    }

    public final List<String> zzb() {
        List<String> listZza = zza();
        Iterator<zzny<String>> it = this.zzc.iterator();
        while (it.hasNext()) {
            String str = (String) zzlc.zzf().zza(it.next());
            if (str != null) {
                listZza.add(str);
            }
        }
        return listZza;
    }

    public final void zzb(zzny<String> zznyVar) {
        this.zzb.add(zznyVar);
    }

    public final void zzc(zzny<String> zznyVar) {
        this.zzc.add(zznyVar);
    }
}
