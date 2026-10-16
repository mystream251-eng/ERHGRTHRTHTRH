package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.zzf;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzagf implements zzagq {
    private static List<Future<Void>> zza = Collections.synchronizedList(new ArrayList());
    private static ScheduledExecutorService zzb = Executors.newSingleThreadScheduledExecutor();
    private final zzflw zzc;
    private final LinkedHashMap<String, zzfme> zzd;
    private final Context zze;
    private final zzags zzf;
    private boolean zzg;
    private final zzagn zzh;
    private final Object zzi = new Object();
    private HashSet<String> zzj = new HashSet<>();
    private boolean zzk = false;
    private boolean zzl = false;
    private boolean zzm = false;

    public zzagf(Context context, zzala zzalaVar, zzagn zzagnVar, String str, zzags zzagsVar) {
        zzbq.zza(zzagnVar, "SafeBrowsing config is not present.");
        this.zze = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzd = new LinkedHashMap<>();
        this.zzf = zzagsVar;
        this.zzh = zzagnVar;
        Iterator<String> it = zzagnVar.zze.iterator();
        while (it.hasNext()) {
            this.zzj.add(it.next().toLowerCase(Locale.ENGLISH));
        }
        this.zzj.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzflw zzflwVar = new zzflw();
        zzflwVar.zza = 8;
        zzflwVar.zzb = str;
        zzflwVar.zzc = str;
        zzflwVar.zzd = new zzflx();
        zzflwVar.zzd.zza = this.zzh.zza;
        zzfmf zzfmfVar = new zzfmf();
        zzfmfVar.zza = zzalaVar.zza;
        zzfmfVar.zzc = Boolean.valueOf(zzbih.zza(this.zze).zza());
        zzf.zza();
        long jZzd = zzf.zzd(this.zze);
        if (jZzd > 0) {
            zzfmfVar.zzb = Long.valueOf(jZzd);
        }
        zzflwVar.zzh = zzfmfVar;
        this.zzc = zzflwVar;
    }

    static final /* synthetic */ Void zzb(String str) {
        return null;
    }

    private final zzfme zzc(String str) {
        zzfme zzfmeVar;
        synchronized (this.zzi) {
            zzfmeVar = this.zzd.get(str);
        }
        return zzfmeVar;
    }

    private final zzalt<Void> zzf() {
        zzalt<Void> zzaltVarZza;
        if (!((this.zzg && this.zzh.zzg) || (this.zzm && this.zzh.zzf) || (!this.zzg && this.zzh.zzd))) {
            return zzali.zza((Object) null);
        }
        synchronized (this.zzi) {
            this.zzc.zze = new zzfme[this.zzd.size()];
            this.zzd.values().toArray(this.zzc.zze);
            if (zzagp.zza()) {
                String str = this.zzc.zzb;
                String str2 = this.zzc.zzf;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 53 + String.valueOf(str2).length());
                sb.append("Sending SB report\n  url: ");
                sb.append(str);
                sb.append("\n  clickUrl: ");
                sb.append(str2);
                sb.append("\n  resources: \n");
                StringBuilder sb2 = new StringBuilder(sb.toString());
                for (zzfme zzfmeVar : this.zzc.zze) {
                    sb2.append("    [");
                    sb2.append(zzfmeVar.zze.length);
                    sb2.append("] ");
                    sb2.append(zzfmeVar.zzb);
                }
                zzagp.zza(sb2.toString());
            }
            zzalt<String> zzaltVarZza2 = new zzajr(this.zze).zza(1, this.zzh.zzb, null, zzfls.zza(this.zzc));
            if (zzagp.zza()) {
                zzaltVarZza2.zza(new zzagk(this), zzaid.zza);
            }
            zzaltVarZza = zzali.zza(zzaltVarZza2, zzagh.zza, zzaly.zzb);
        }
        return zzaltVarZza;
    }

    @Override // com.google.android.gms.internal.zzagq
    public final zzagn zza() {
        return this.zzh;
    }

    final /* synthetic */ zzalt zza(Map map) throws Exception {
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    JSONArray jSONArrayOptJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                    if (jSONArrayOptJSONArray != null) {
                        synchronized (this.zzi) {
                            int length = jSONArrayOptJSONArray.length();
                            zzfme zzfmeVarZzc = zzc(str);
                            if (zzfmeVarZzc == null) {
                                String strValueOf = String.valueOf(str);
                                zzagp.zza(strValueOf.length() != 0 ? "Cannot find the corresponding resource object for ".concat(strValueOf) : new String("Cannot find the corresponding resource object for "));
                            } else {
                                zzfmeVarZzc.zze = new String[length];
                                for (int i = 0; i < length; i++) {
                                    zzfmeVarZzc.zze[i] = jSONArrayOptJSONArray.getJSONObject(i).getString("threat_type");
                                }
                                this.zzg = (length > 0) | this.zzg;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzfr)).booleanValue()) {
                    zzahw.zza("Failed to get SafeBrowsing metadata", e);
                }
                return zzali.zza((Throwable) new Exception("Safebrowsing report transmission failed."));
            }
        }
        if (this.zzg) {
            synchronized (this.zzi) {
                this.zzc.zza = 9;
            }
        }
        return zzf();
    }

    @Override // com.google.android.gms.internal.zzagq
    public final void zza(View view) {
        if (this.zzh.zzc && !this.zzl) {
            zzbt.zze();
            Bitmap bitmapZzb = zzaij.zzb(view);
            if (bitmapZzb == null) {
                zzagp.zza("Failed to capture the webview bitmap.");
            } else {
                this.zzl = true;
                zzaij.zza(new zzagi(this, bitmapZzb));
            }
        }
    }

    @Override // com.google.android.gms.internal.zzagq
    public final void zza(String str) {
        synchronized (this.zzi) {
            this.zzc.zzf = str;
        }
    }

    @Override // com.google.android.gms.internal.zzagq
    public final void zza(String str, Map<String, String> map, int i) {
        synchronized (this.zzi) {
            if (i == 3) {
                try {
                    this.zzm = true;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (this.zzd.containsKey(str)) {
                if (i == 3) {
                    this.zzd.get(str).zzd = Integer.valueOf(i);
                }
                return;
            }
            zzfme zzfmeVar = new zzfme();
            zzfmeVar.zzd = Integer.valueOf(i);
            zzfmeVar.zza = Integer.valueOf(this.zzd.size());
            zzfmeVar.zzb = str;
            zzfmeVar.zzc = new zzflz();
            if (this.zzj.size() > 0 && map != null) {
                LinkedList linkedList = new LinkedList();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    try {
                        String key = entry.getKey() != null ? entry.getKey() : "";
                        String value = entry.getValue() != null ? entry.getValue() : "";
                        if (this.zzj.contains(key.toLowerCase(Locale.ENGLISH))) {
                            zzfly zzflyVar = new zzfly();
                            zzflyVar.zza = key.getBytes("UTF-8");
                            zzflyVar.zzb = value.getBytes("UTF-8");
                            linkedList.add(zzflyVar);
                        }
                    } catch (UnsupportedEncodingException unused) {
                        zzagp.zza("Cannot convert string to bytes, skip header.");
                    }
                }
                zzfly[] zzflyVarArr = new zzfly[linkedList.size()];
                linkedList.toArray(zzflyVarArr);
                zzfmeVar.zzc.zza = zzflyVarArr;
            }
            this.zzd.put(str, zzfmeVar);
        }
    }

    @Override // com.google.android.gms.internal.zzagq
    public final boolean zzb() {
        return zzs.zze() && this.zzh.zzc && !this.zzl;
    }

    @Override // com.google.android.gms.internal.zzagq
    public final void zzc() {
        this.zzk = true;
    }

    @Override // com.google.android.gms.internal.zzagq
    public final void zzd() {
        synchronized (this.zzi) {
            zzalt zzaltVarZza = zzali.zza(this.zzf.zza(this.zze, this.zzd.keySet()), new zzald(this) { // from class: com.google.android.gms.internal.zzagg
                private final zzagf zza;

                {
                    this.zza = this;
                }

                @Override // com.google.android.gms.internal.zzald
                public final zzalt zza(Object obj) {
                    return this.zza.zza((Map) obj);
                }
            }, zzaly.zzb);
            zzalt zzaltVarZza2 = zzali.zza(zzaltVarZza, 10L, TimeUnit.SECONDS, zzb);
            zzali.zza(zzaltVarZza, new zzagj(this, zzaltVarZza2), zzaly.zzb);
            zza.add(zzaltVarZza2);
        }
    }
}
