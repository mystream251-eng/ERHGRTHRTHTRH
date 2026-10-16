package com.google.android.gms.internal;

import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaap implements zzaae<zzpm> {
    private final boolean zza;
    private final boolean zzb;
    private final boolean zzc;

    public zzaap(boolean z, boolean z2, boolean z3) {
        this.zza = z;
        this.zzb = z2;
        this.zzc = z3;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x00a4 A[PHI: r5
  0x00a4: PHI (r5v4 java.lang.String) = (r5v3 java.lang.String), (r5v3 java.lang.String), (r5v10 java.lang.String) binds: [B:7:0x0054, B:9:0x0066, B:15:0x007d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.gms.internal.zzaae
    public final /* synthetic */ zzpx zza(zzzy zzzyVar, JSONObject jSONObject) throws ExecutionException, JSONException, InterruptedException {
        String string;
        List<zzalt<zzpj>> listZza = zzzyVar.zza(jSONObject, "images", false, this.zza, this.zzb);
        zzalt<zzpj> zzaltVarZza = zzzyVar.zza(jSONObject, "secondary_image", false, this.zza);
        zzalt<zzph> zzaltVarZza2 = zzzyVar.zza(jSONObject);
        zzalt<zzaof> zzaltVarZza3 = zzzyVar.zza(jSONObject, MimeTypes.BASE_TYPE_VIDEO);
        ArrayList arrayList = new ArrayList();
        Iterator<zzalt<zzpj>> it = listZza.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().get());
        }
        zzaof zzaofVarZza = zzzy.zza(zzaltVarZza3);
        String string2 = jSONObject.getString("headline");
        if (this.zzc) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzgy)).booleanValue()) {
                Resources resourcesZzh = zzbt.zzi().zzh();
                string = resourcesZzh != null ? resourcesZzh.getString(R.string.s7) : "Test Ad";
                if (string2 != null) {
                    StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 3 + String.valueOf(string2).length());
                    sb.append(string);
                    sb.append(" : ");
                    sb.append(string2);
                    string2 = sb.toString();
                    string = string2;
                }
            } else {
                string = string2;
            }
        } else {
            string = string2;
        }
        return new zzpm(string, arrayList, jSONObject.getString(TtmlNode.TAG_BODY), zzaltVarZza.get(), jSONObject.getString("call_to_action"), jSONObject.getString("advertiser"), zzaltVarZza2.get(), new Bundle(), zzaofVarZza != null ? zzaofVarZza.zzb() : null, zzaofVarZza != null ? zzaofVarZza.zzm() : null, null, null);
    }
}
