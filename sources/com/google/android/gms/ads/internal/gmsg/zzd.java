package com.google.android.gms.ads.internal.gmsg;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagx;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzakd;
import com.google.android.gms.internal.zzann;
import com.google.android.gms.internal.zzant;
import com.google.android.gms.internal.zzanu;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzapa;
import com.google.android.gms.internal.zzapr;
import com.google.android.gms.internal.zzaps;
import com.google.android.gms.internal.zzapt;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzcw;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzd {
    private static zzt<Object> zzs = new zzj();
    public static final zzt<zzaof> zza = zze.zza;
    public static final zzt<zzaof> zzb = zzf.zza;
    public static final zzt<zzaof> zzc = zzg.zza;
    public static final zzt<zzaof> zzd = new zzl();
    public static final zzt<zzaof> zze = new zzm();
    public static final zzt<zzaof> zzf = zzh.zza;
    public static final zzt<Object> zzg = new zzn();
    public static final zzt<zzaof> zzh = new zzo();
    public static final zzt<zzaof> zzi = zzi.zza;
    public static final zzt<zzaof> zzj = new zzp();
    public static final zzt<zzaof> zzk = new zzq();
    public static final zzt<zzann> zzl = new zzant();
    public static final zzt<zzann> zzm = new zzanu();
    public static final zzt<zzaof> zzn = new zzc();
    public static final zzad zzo = new zzad();
    public static final zzt<zzaof> zzp = new zzr();
    public static final zzt<zzaof> zzq = new zzs();
    public static final zzt<zzaof> zzr = new zzk();

    static final /* synthetic */ void zza(com.google.android.gms.ads.internal.js.zza zzaVar, Map map) throws zzcw {
        String str = (String) map.get("u");
        if (str == null) {
            zzahw.zze("URL missing from click GMSG.");
            return;
        }
        Uri uriZza = Uri.parse(str);
        try {
            zzcv zzcvVarZzx = ((zzapr) zzaVar).zzx();
            if (zzcvVarZzx != null && zzcvVarZzx.zza(uriZza)) {
                uriZza = zzcvVarZzx.zza(uriZza, ((zzapa) zzaVar).getContext(), ((zzapt) zzaVar).zzm(), ((zzapa) zzaVar).zzd());
            }
        } catch (zzcw unused) {
            String strValueOf = String.valueOf(str);
            zzahw.zze(strValueOf.length() != 0 ? "Unable to append parameter to URL: ".concat(strValueOf) : new String("Unable to append parameter to URL: "));
        }
        zzapa zzapaVar = (zzapa) zzaVar;
        new zzakd(zzapaVar.getContext(), ((zzaps) zzaVar).zzi().zza, zzagx.zza(uriZza, zzapaVar.getContext()).toString()).zzh();
    }

    static final /* synthetic */ void zza(zzapa zzapaVar, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzahw.zze("URL missing from httpTrack GMSG.");
        } else {
            new zzakd(zzapaVar.getContext(), ((zzaps) zzapaVar).zzi().zza, str).zzh();
        }
    }

    static final /* synthetic */ void zza(zzapr zzaprVar, Map map) {
        String str = (String) map.get("tx");
        String str2 = (String) map.get("ty");
        String str3 = (String) map.get("td");
        try {
            int i = Integer.parseInt(str);
            int i2 = Integer.parseInt(str2);
            int i3 = Integer.parseInt(str3);
            zzcv zzcvVarZzx = zzaprVar.zzx();
            if (zzcvVarZzx != null) {
                zzcvVarZzx.zza().zza(i, i2, i3);
            }
        } catch (NumberFormatException unused) {
            zzahw.zze("Could not parse touch parameters from gmsg.");
        }
    }

    static final /* synthetic */ void zzb(zzapa zzapaVar, Map map) {
        JSONException jSONException;
        String str;
        PackageManager packageManager = zzapaVar.getContext().getPackageManager();
        try {
            try {
                JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        String strOptString = jSONObject2.optString("id");
                        String strOptString2 = jSONObject2.optString("u");
                        String strOptString3 = jSONObject2.optString("i");
                        String strOptString4 = jSONObject2.optString("m");
                        String strOptString5 = jSONObject2.optString(TtmlNode.TAG_P);
                        String strOptString6 = jSONObject2.optString("c");
                        jSONObject2.optString("f");
                        jSONObject2.optString("e");
                        String strOptString7 = jSONObject2.optString("intent_url");
                        Intent uri = null;
                        if (!TextUtils.isEmpty(strOptString7)) {
                            try {
                                uri = Intent.parseUri(strOptString7, 0);
                            } catch (URISyntaxException e) {
                                String strValueOf = String.valueOf(strOptString7);
                                zzahw.zzb(strValueOf.length() != 0 ? "Error parsing the url: ".concat(strValueOf) : new String("Error parsing the url: "), e);
                            }
                        }
                        if (uri == null) {
                            uri = new Intent();
                            if (!TextUtils.isEmpty(strOptString2)) {
                                uri.setData(Uri.parse(strOptString2));
                            }
                            if (!TextUtils.isEmpty(strOptString3)) {
                                uri.setAction(strOptString3);
                            }
                            if (!TextUtils.isEmpty(strOptString4)) {
                                uri.setType(strOptString4);
                            }
                            if (!TextUtils.isEmpty(strOptString5)) {
                                uri.setPackage(strOptString5);
                            }
                            if (!TextUtils.isEmpty(strOptString6)) {
                                String[] strArrSplit = strOptString6.split("/", 2);
                                if (strArrSplit.length == 2) {
                                    uri.setComponent(new ComponentName(strArrSplit[0], strArrSplit[1]));
                                }
                            }
                        }
                        try {
                            jSONObject.put(strOptString, packageManager.resolveActivity(uri, 65536) != null);
                        } catch (JSONException e2) {
                            jSONException = e2;
                            str = "Error constructing openable urls response.";
                            zzahw.zzb(str, jSONException);
                        }
                    } catch (JSONException e3) {
                        jSONException = e3;
                        str = "Error parsing the intent data.";
                    }
                }
                ((com.google.android.gms.ads.internal.js.zza) zzapaVar).zza("openableIntents", jSONObject);
            } catch (JSONException unused) {
                ((com.google.android.gms.ads.internal.js.zza) zzapaVar).zza("openableIntents", new JSONObject());
            }
        } catch (JSONException unused2) {
            ((com.google.android.gms.ads.internal.js.zza) zzapaVar).zza("openableIntents", new JSONObject());
        }
    }

    static final /* synthetic */ void zzc(zzapa zzapaVar, Map map) {
        String str = (String) map.get("urls");
        if (TextUtils.isEmpty(str)) {
            zzahw.zze("URLs missing in canOpenURLs GMSG.");
            return;
        }
        String[] strArrSplit = str.split(",");
        HashMap map2 = new HashMap();
        PackageManager packageManager = zzapaVar.getContext().getPackageManager();
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = str2.split(";", 2);
            boolean z = true;
            if (packageManager.resolveActivity(new Intent(strArrSplit2.length > 1 ? strArrSplit2[1].trim() : "android.intent.action.VIEW", Uri.parse(strArrSplit2[0].trim())), 65536) == null) {
                z = false;
            }
            map2.put(str2, Boolean.valueOf(z));
        }
        ((com.google.android.gms.ads.internal.js.zza) zzapaVar).zza("openableURLs", map2);
    }
}
