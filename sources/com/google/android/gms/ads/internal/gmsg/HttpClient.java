package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaks;
import com.google.android.gms.internal.zzala;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class HttpClient implements zzt<com.google.android.gms.ads.internal.js.zza> {
    private final Context zza;
    private final zzala zzb;

    @zzabh
    static class zza {
        private final String zza;
        private final String zzb;

        public zza(String str, String str2) {
            this.zza = str;
            this.zzb = str2;
        }

        public final String zza() {
            return this.zza;
        }

        public final String zzb() {
            return this.zzb;
        }
    }

    @zzabh
    static class zzb {
        private final String zza;
        private final URL zzb;
        private final ArrayList<zza> zzc;
        private final String zzd;

        zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zza = str;
            this.zzb = url;
            this.zzc = arrayList;
            this.zzd = str2;
        }

        public final String zza() {
            return this.zza;
        }

        public final URL zzb() {
            return this.zzb;
        }

        public final ArrayList<zza> zzc() {
            return this.zzc;
        }

        public final String zzd() {
            return this.zzd;
        }
    }

    @zzabh
    class zzc {
        private final zzd zza;
        private final boolean zzb;
        private final String zzc;

        public zzc(HttpClient httpClient, boolean z, zzd zzdVar, String str) {
            this.zzb = z;
            this.zza = zzdVar;
            this.zzc = str;
        }

        public final String zza() {
            return this.zzc;
        }

        public final zzd zzb() {
            return this.zza;
        }

        public final boolean zzc() {
            return this.zzb;
        }
    }

    @zzabh
    static class zzd {
        private final String zza;
        private final int zzb;
        private final List<zza> zzc;
        private final String zzd;

        zzd(String str, int i, List<zza> list, String str2) {
            this.zza = str;
            this.zzb = i;
            this.zzc = list;
            this.zzd = str2;
        }

        public final String zza() {
            return this.zza;
        }

        public final int zzb() {
            return this.zzb;
        }

        public final Iterable<zza> zzc() {
            return this.zzc;
        }

        public final String zzd() {
            return this.zzd;
        }
    }

    public HttpClient(Context context, zzala zzalaVar) {
        this.zza = context;
        this.zzb = zzalaVar;
    }

    private static zzb zza(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("http_request_id");
        String strOptString2 = jSONObject.optString(ImagesContract.URL);
        URL url = null;
        String strOptString3 = jSONObject.optString("post_body", null);
        try {
            url = new URL(strOptString2);
        } catch (MalformedURLException e) {
            zzahw.zzb("Error constructing http request.", e);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("headers");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                arrayList.add(new zza(jSONObjectOptJSONObject.optString("key"), jSONObjectOptJSONObject.optString("value")));
            }
        }
        return new zzb(strOptString, url, arrayList, strOptString3);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0104  */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0101: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:35:0x0101 */
    private final zzc zza(zzb zzbVar) throws Throwable {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        byte[] bytes;
        HttpURLConnection httpURLConnection3 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) zzbVar.zzb().openConnection();
                try {
                    zzbt.zze().zza(this.zza, this.zzb.zza, false, httpURLConnection);
                    ArrayList<zza> arrayListZzc = zzbVar.zzc();
                    int size = arrayListZzc.size();
                    int i = 0;
                    while (i < size) {
                        zza zzaVar = arrayListZzc.get(i);
                        i++;
                        zza zzaVar2 = zzaVar;
                        httpURLConnection.addRequestProperty(zzaVar2.zza(), zzaVar2.zzb());
                    }
                    if (TextUtils.isEmpty(zzbVar.zzd())) {
                        bytes = null;
                    } else {
                        httpURLConnection.setDoOutput(true);
                        bytes = zzbVar.zzd().getBytes();
                        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                        bufferedOutputStream.write(bytes);
                        bufferedOutputStream.close();
                    }
                    zzaks zzaksVar = new zzaks();
                    zzaksVar.zza(httpURLConnection, bytes);
                    ArrayList arrayList = new ArrayList();
                    if (httpURLConnection.getHeaderFields() != null) {
                        for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
                            Iterator<String> it = entry.getValue().iterator();
                            while (it.hasNext()) {
                                arrayList.add(new zza(entry.getKey(), it.next()));
                            }
                        }
                    }
                    String strZza = zzbVar.zza();
                    int responseCode = httpURLConnection.getResponseCode();
                    zzbt.zze();
                    zzd zzdVar = new zzd(strZza, responseCode, arrayList, zzaij.zza(new InputStreamReader(httpURLConnection.getInputStream())));
                    zzaksVar.zza(httpURLConnection, zzdVar.zzb());
                    zzaksVar.zza(zzdVar.zzd());
                    zzc zzcVar = new zzc(this, true, zzdVar, null);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return zzcVar;
                } catch (Exception e) {
                    e = e;
                    zzc zzcVar2 = new zzc(this, false, null, e.toString());
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return zzcVar2;
                }
            } catch (Throwable th) {
                th = th;
                httpURLConnection3 = httpURLConnection2;
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            httpURLConnection = null;
        } catch (Throwable th2) {
            th = th2;
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            throw th;
        }
    }

    private static JSONObject zza(zzd zzdVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", zzdVar.zza());
            if (zzdVar.zzd() != null) {
                jSONObject.put(TtmlNode.TAG_BODY, zzdVar.zzd());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza zzaVar : zzdVar.zzc()) {
                jSONArray.put(new JSONObject().put("key", zzaVar.zza()).put("value", zzaVar.zzb()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", zzdVar.zzb());
        } catch (JSONException e) {
            zzahw.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    public JSONObject send(JSONObject jSONObject) throws Throwable {
        JSONObject jSONObject2 = new JSONObject();
        String strOptString = "";
        try {
            strOptString = jSONObject.optString("http_request_id");
            zzc zzcVarZza = zza(zza(jSONObject));
            if (zzcVarZza.zzc()) {
                jSONObject2.put("response", zza(zzcVarZza.zzb()));
                jSONObject2.put("success", true);
            } else {
                jSONObject2.put("response", new JSONObject().put("http_request_id", strOptString));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", zzcVarZza.zza());
            }
        } catch (Exception e) {
            zzahw.zzb("Error executing http request.", e);
            try {
                jSONObject2.put("response", new JSONObject().put("http_request_id", strOptString));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", e.toString());
            } catch (JSONException e2) {
                zzahw.zzb("Error executing http request.", e2);
            }
        }
        return jSONObject2;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(com.google.android.gms.ads.internal.js.zza zzaVar, Map map) {
        zzaid.zza(new zzu(this, map, zzaVar));
    }
}
