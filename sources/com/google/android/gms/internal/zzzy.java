package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.voplayer.VOPlayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzzy implements Callable<zzahd> {
    private static long zza = 10;
    private final Context zzb;
    private final zzajr zzc;
    private final com.google.android.gms.ads.internal.zzbb zzd;
    private final zzcv zze;
    private final zzaan zzf;
    private final zzahe zzh;
    private final zzov zzi;
    private JSONObject zzm;
    private final Object zzg = new Object();
    private boolean zzj = false;
    private int zzk = -2;
    private List<String> zzl = null;
    private String zzn = null;

    public zzzy(Context context, com.google.android.gms.ads.internal.zzbb zzbbVar, zzajr zzajrVar, zzcv zzcvVar, zzahe zzaheVar, zzov zzovVar) {
        this.zzb = context;
        this.zzd = zzbbVar;
        this.zzc = zzajrVar;
        this.zzh = zzaheVar;
        this.zze = zzcvVar;
        this.zzi = zzovVar;
        this.zzf = zzbbVar.zzav();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:15:0x004c A[Catch: Exception -> 0x018c, TimeoutException -> 0x0190, JSONException -> 0x0194, InterruptedException | CancellationException | ExecutionException -> 0x019a, InterruptedException | CancellationException | ExecutionException -> 0x019a, InterruptedException | CancellationException | ExecutionException -> 0x019a, TryCatch #1 {InterruptedException | CancellationException | ExecutionException -> 0x019a, blocks: (B:3:0x0004, B:5:0x0010, B:8:0x0038, B:8:0x0038, B:8:0x0038, B:10:0x003e, B:10:0x003e, B:10:0x003e, B:13:0x0046, B:13:0x0046, B:13:0x0046, B:16:0x0050, B:16:0x0050, B:16:0x0050, B:18:0x0068, B:18:0x0068, B:18:0x0068, B:20:0x0079, B:20:0x0079, B:20:0x0079, B:24:0x0083, B:24:0x0083, B:24:0x0083, B:26:0x0091, B:26:0x0091, B:26:0x0091, B:28:0x009b, B:28:0x009b, B:28:0x009b, B:30:0x00a3, B:30:0x00a3, B:30:0x00a3, B:32:0x00ad, B:32:0x00ad, B:32:0x00ad, B:34:0x00b5, B:34:0x00b5, B:34:0x00b5, B:50:0x011e, B:50:0x011e, B:50:0x011e, B:55:0x0129, B:55:0x0129, B:55:0x0129, B:59:0x013d, B:59:0x013d, B:59:0x013d, B:61:0x016d, B:61:0x016d, B:61:0x016d, B:63:0x0171, B:63:0x0171, B:63:0x0171, B:64:0x0187, B:64:0x0187, B:64:0x0187, B:58:0x0139, B:58:0x0139, B:58:0x0139, B:35:0x00bf, B:35:0x00bf, B:35:0x00bf, B:37:0x00c7, B:37:0x00c7, B:37:0x00c7, B:38:0x00d1, B:38:0x00d1, B:38:0x00d1, B:40:0x00d9, B:40:0x00d9, B:40:0x00d9, B:42:0x00f6, B:42:0x00f6, B:42:0x00f6, B:43:0x00fc, B:43:0x00fc, B:43:0x00fc, B:45:0x010c, B:45:0x010c, B:45:0x010c, B:47:0x0116, B:47:0x0116, B:47:0x0116, B:46:0x0111, B:46:0x0111, B:46:0x0111, B:48:0x011a, B:48:0x011a, B:48:0x011a, B:15:0x004c, B:15:0x004c, B:15:0x004c), top: B:79:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:19:0x0078  */
    /* JADX WARN: Code duplicated, block: B:75:0x019e  */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzahd call() {
        String str;
        JSONObject jSONObject;
        zzaae zzaaqVar;
        zzpx zzpxVarZza;
        try {
            try {
                String strZzat = this.zzd.zzat();
                if (zzb()) {
                    jSONObject = null;
                } else {
                    new zzamd();
                    new zzzx();
                    JSONObject jSONObject2 = new JSONObject(this.zzh.zzb.zzb);
                    JSONObject jSONObject3 = new JSONObject(this.zzh.zzb.zzb);
                    if (jSONObject3.length() == 0) {
                        zza(3);
                    } else {
                        JSONArray jSONArrayOptJSONArray = jSONObject3.optJSONArray("ads");
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray != null ? jSONArrayOptJSONArray.optJSONObject(0) : null;
                        if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() == 0) {
                            zza(3);
                        }
                    }
                    JSONObject jSONObject4 = this.zzf.zza(jSONObject2).get(zza, TimeUnit.SECONDS);
                    if (jSONObject4.optBoolean("success", false)) {
                        jSONObject = jSONObject4.getJSONObject("json").optJSONArray("ads").getJSONObject(0);
                    } else {
                        jSONObject = null;
                    }
                }
                if (zzb() || jSONObject == null) {
                    zzaaqVar = null;
                } else {
                    String string = jSONObject.getString("template_id");
                    boolean z = this.zzh.zza.zzy != null ? this.zzh.zza.zzy.zzb : false;
                    boolean z2 = this.zzh.zza.zzy != null ? this.zzh.zza.zzy.zzd : false;
                    if (ExifInterface.GPS_MEASUREMENT_2D.equals(string)) {
                        zzaaqVar = new zzaao(z, z2, this.zzh.zzj);
                    } else if (LocalSearchProvider.LIVE_CONTENTS.equals(string)) {
                        zzaaqVar = new zzaap(z, z2, this.zzh.zzj);
                    } else {
                        if (ExifInterface.GPS_MEASUREMENT_3D.equals(string)) {
                            String string2 = jSONObject.getString("custom_template_id");
                            zzamd zzamdVar = new zzamd();
                            zzaij.zza.post(new zzzz(this, zzamdVar, string2));
                            if (zzamdVar.get(zza, TimeUnit.SECONDS) != null) {
                                zzaaqVar = new zzaaq(z);
                            } else {
                                String strValueOf = String.valueOf(jSONObject.getString("custom_template_id"));
                                zzahw.zzc(strValueOf.length() != 0 ? "No handler for custom template: ".concat(strValueOf) : new String("No handler for custom template: "));
                            }
                        } else {
                            zza(0);
                        }
                        zzaaqVar = null;
                    }
                }
                if (zzb() || zzaaqVar == null || jSONObject == null) {
                    zzpxVarZza = null;
                } else {
                    JSONObject jSONObject5 = jSONObject.getJSONObject("tracking_urls_and_actions");
                    String[] strArrZzc = zzc(jSONObject5, "impression_tracking_urls");
                    this.zzl = strArrZzc == null ? null : Arrays.asList(strArrZzc);
                    this.zzm = jSONObject5.optJSONObject("active_view");
                    this.zzn = jSONObject.optString("debug_signals");
                    zzpxVarZza = zzaaqVar.zza(this, jSONObject);
                    zzpxVarZza.zza(new zzpz(this.zzb, this.zzd, this.zzf, this.zze, jSONObject, zzpxVarZza, this.zzh.zza.zzk, strZzat));
                }
                if (zzpxVarZza instanceof zzpo) {
                    zzzx zzzxVar = new zzzx();
                    zzaaa zzaaaVar = new zzaaa(this, (zzpo) zzpxVarZza);
                    zzzxVar.zza = zzaaaVar;
                    this.zzf.zza("/nativeAdCustomClick", zzaaaVar);
                }
                return zza(zzpxVarZza);
            } catch (InterruptedException | CancellationException | ExecutionException unused) {
                if (!this.zzj) {
                    zza(0);
                }
                return zza((zzpx) null);
            }
        } catch (TimeoutException e) {
            e = e;
            str = "Timeout when loading native ad.";
            zzahw.zzc(str, e);
            if (!this.zzj) {
                zza(0);
            }
            return zza((zzpx) null);
        } catch (JSONException e2) {
            e = e2;
            str = "Malformed native JSON response.";
            zzahw.zzc(str, e);
            if (!this.zzj) {
                zza(0);
            }
            return zza((zzpx) null);
        } catch (Exception e3) {
            e = e3;
            str = "Error occured while doing native ads initialization.";
            zzahw.zzc(str, e);
            if (!this.zzj) {
                zza(0);
            }
            return zza((zzpx) null);
        }
    }

    private final zzahd zza(zzpx zzpxVar) {
        int i;
        synchronized (this.zzg) {
            int i2 = this.zzk;
            if (zzpxVar == null && this.zzk == -2) {
                i2 = 0;
            }
            i = i2;
        }
        return new zzahd(this.zzh.zza.zzc, null, this.zzh.zzb.zzc, i, this.zzh.zzb.zze, this.zzl, this.zzh.zzb.zzk, this.zzh.zzb.zzj, this.zzh.zza.zzi, false, null, null, null, null, null, 0L, this.zzh.zzd, this.zzh.zzb.zzf, this.zzh.zzf, this.zzh.zzg, this.zzh.zzb.zzn, this.zzm, i != -2 ? null : zzpxVar, null, null, null, this.zzh.zzb.zzad, this.zzh.zzb.zzae, null, this.zzh.zzb.zzah, this.zzn, this.zzh.zzi, this.zzh.zzb.zzao, this.zzh.zzj);
    }

    private final zzalt<zzpj> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString(ImagesContract.URL) : jSONObject.optString(ImagesContract.URL);
        double dOptDouble = jSONObject.optDouble("scale", 1.0d);
        boolean zOptBoolean = jSONObject.optBoolean("is_transparent", true);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? zzali.zza(new zzpj(null, Uri.parse(string), dOptDouble)) : this.zzc.zza(string, new zzaac(this, z, dOptDouble, zOptBoolean, string));
        }
        zza(0, z);
        return zzali.zza((Object) null);
    }

    static zzaof zza(zzalt<zzaof> zzaltVar) {
        try {
            return zzaltVar.get(((Integer) zzlc.zzf().zza(zzoi.zzev)).intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzahw.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException e2) {
            e = e2;
            zzahw.zzc("Exception occurred while waiting for video to load", e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            zzahw.zzc("Exception occurred while waiting for video to load", e);
            return null;
        } catch (TimeoutException e4) {
            e = e4;
            zzahw.zzc("Exception occurred while waiting for video to load", e);
            return null;
        }
    }

    private final void zza(int i) {
        synchronized (this.zzg) {
            this.zzj = true;
            this.zzk = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzro zzroVar, String str) {
        try {
            zzry zzryVarZzb = this.zzd.zzb(zzroVar.zzl());
            if (zzryVarZzb != null) {
                zzryVarZzb.zza(zzroVar, str);
            }
        } catch (RemoteException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40);
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            zzahw.zzc(sb.toString(), e);
        }
    }

    private static <V> zzalt<List<V>> zzb(List<zzalt<V>> list) {
        zzamd zzamdVar = new zzamd();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Iterator<zzalt<V>> it = list.iterator();
        while (it.hasNext()) {
            it.next().zza(new zzaad(atomicInteger, size, zzamdVar, list), zzaid.zza);
        }
        return zzamdVar;
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    private final boolean zzb() {
        boolean z;
        synchronized (this.zzg) {
            z = this.zzj;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <V> List<V> zzc(List<zzalt<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        Iterator<zzalt<V>> it = list.iterator();
        while (it.hasNext()) {
            V v = it.next().get();
            if (v != null) {
                arrayList.add(v);
            }
        }
        return arrayList;
    }

    private static String[] zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        String[] strArr = new String[jSONArrayOptJSONArray.length()];
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            strArr[i] = jSONArrayOptJSONArray.getString(i);
        }
        return strArr;
    }

    public final zzalt<zzph> zza(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("attribution");
        if (jSONObjectOptJSONObject == null) {
            return zzali.zza((Object) null);
        }
        String strOptString = jSONObjectOptJSONObject.optString("text");
        int iOptInt = jSONObjectOptJSONObject.optInt("text_size", -1);
        Integer numZzb = zzb(jSONObjectOptJSONObject, "text_color");
        Integer numZzb2 = zzb(jSONObjectOptJSONObject, "bg_color");
        int iOptInt2 = jSONObjectOptJSONObject.optInt("animation_ms", 1000);
        int iOptInt3 = jSONObjectOptJSONObject.optInt("presentation_ms", VOPlayer.MEDIA_INFO_NETWORK);
        int i = (this.zzh.zza.zzy == null || this.zzh.zza.zzy.zza < 2) ? 1 : this.zzh.zza.zzy.zze;
        boolean zOptBoolean = jSONObjectOptJSONObject.optBoolean("allow_pub_rendering");
        List<zzalt<zzpj>> arrayList = new ArrayList<>();
        if (jSONObjectOptJSONObject.optJSONArray("images") != null) {
            arrayList = zza(jSONObjectOptJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(jSONObjectOptJSONObject, TtmlNode.TAG_IMAGE, false, false));
        }
        return zzali.zza(zzb(arrayList), new zzaab(this, strOptString, numZzb2, numZzb, iOptInt, iOptInt3, iOptInt2, i, zOptBoolean), zzaid.zza);
    }

    public final zzalt<zzaof> zza(JSONObject jSONObject, String str) throws JSONException {
        final JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str);
        if (jSONObjectOptJSONObject == null) {
            return zzali.zza((Object) null);
        }
        if (TextUtils.isEmpty(jSONObjectOptJSONObject.optString("vast_xml"))) {
            zzahw.zze("Required field 'vast_xml' is missing");
            return zzali.zza((Object) null);
        }
        final zzaaf zzaafVar = new zzaaf(this.zzb, this.zze, this.zzh, this.zzi, this.zzd);
        final zzamd zzamdVar = new zzamd();
        zzaly.zza.execute(new Runnable(zzaafVar, jSONObjectOptJSONObject, zzamdVar) { // from class: com.google.android.gms.internal.zzaag
            private final zzaaf zza;
            private final JSONObject zzb;
            private final zzamd zzc;

            {
                this.zza = zzaafVar;
                this.zzb = jSONObjectOptJSONObject;
                this.zzc = zzamdVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc);
            }
        });
        return zzamdVar;
    }

    public final zzalt<zzpj> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public final List<zzalt<zzpj>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            zza(0, false);
            return arrayList;
        }
        int length = z3 ? jSONArrayOptJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    public final Future<zzpj> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean zOptBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, zOptBoolean, z);
    }

    public final void zza(int i, boolean z) {
        if (z) {
            zza(i);
        }
    }
}
