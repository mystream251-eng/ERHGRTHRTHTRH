package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbt;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzuo {
    private final Map<zzup, zzuq> zza = new HashMap();
    private final LinkedList<zzup> zzb = new LinkedList<>();
    private zztk zzc;

    static Set<String> zza(zzkk zzkkVar) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(zzkkVar.zzc.keySet());
        Bundle bundle = zzkkVar.zzm.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    private static void zza(Bundle bundle, String str) {
        while (true) {
            String[] strArrSplit = str.split("/", 2);
            if (strArrSplit.length == 0) {
                return;
            }
            String str2 = strArrSplit[0];
            if (strArrSplit.length == 1) {
                bundle.remove(str2);
                return;
            }
            bundle = bundle.getBundle(str2);
            if (bundle == null) {
                return;
            } else {
                str = strArrSplit[1];
            }
        }
    }

    private static void zza(String str, zzup zzupVar) {
        if (zzahw.zza(2)) {
            zzahw.zza(String.format(str, zzupVar));
        }
    }

    private static String[] zza(String str) {
        try {
            String[] strArrSplit = str.split("\u0000");
            for (int i = 0; i < strArrSplit.length; i++) {
                strArrSplit[i] = new String(Base64.decode(strArrSplit[i], 0), "UTF-8");
            }
            return strArrSplit;
        } catch (UnsupportedEncodingException unused) {
            return new String[0];
        }
    }

    static zzkk zzb(zzkk zzkkVar) {
        zzkk zzkkVarZzd = zzd(zzkkVar);
        Bundle bundle = zzkkVarZzd.zzm.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean("_skipMediation", true);
        }
        zzkkVarZzd.zzc.putBoolean("_skipMediation", true);
        return zzkkVarZzd;
    }

    private final String zzb() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator<zzup> it = this.zzb.iterator();
            while (it.hasNext()) {
                sb.append(Base64.encodeToString(it.next().toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    private static boolean zzb(String str) {
        try {
            return Pattern.matches((String) zzlc.zzf().zza(zzoi.zzcs), str);
        } catch (RuntimeException e) {
            zzbt.zzi().zza(e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    private static zzkk zzc(zzkk zzkkVar) {
        zzkk zzkkVarZzd = zzd(zzkkVar);
        for (String str : ((String) zzlc.zzf().zza(zzoi.zzco)).split(",")) {
            zza(zzkkVarZzd.zzm, str);
            if (str.startsWith("com.google.ads.mediation.admob.AdMobAdapter/")) {
                zza(zzkkVarZzd.zzc, str.replaceFirst("com.google.ads.mediation.admob.AdMobAdapter/", ""));
            }
        }
        return zzkkVarZzd;
    }

    private static String zzc(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            return matcher.matches() ? matcher.group(1) : str;
        } catch (RuntimeException unused) {
            return str;
        }
    }

    private static zzkk zzd(zzkk zzkkVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzkkVar.writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        zzkk zzkkVarCreateFromParcel = zzkk.CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return ((Boolean) zzlc.zzf().zza(zzoi.zzcg)).booleanValue() ? zzkkVarCreateFromParcel.zza() : zzkkVarCreateFromParcel;
    }

    final zzur zza(zzkk zzkkVar, String str) {
        if (zzb(str)) {
            return null;
        }
        int i = new zzaeb(this.zzc.zza()).zza().zzn;
        zzkk zzkkVarZzc = zzc(zzkkVar);
        String strZzc = zzc(str);
        zzup zzupVar = new zzup(zzkkVarZzc, strZzc, i);
        zzuq zzuqVar = this.zza.get(zzupVar);
        if (zzuqVar == null) {
            zza("Interstitial pool created at %s.", zzupVar);
            zzuqVar = new zzuq(zzkkVarZzc, strZzc, i);
            this.zza.put(zzupVar, zzuqVar);
        }
        this.zzb.remove(zzupVar);
        this.zzb.add(zzupVar);
        zzuqVar.zzg();
        while (this.zzb.size() > ((Integer) zzlc.zzf().zza(zzoi.zzcp)).intValue()) {
            zzup zzupVarRemove = this.zzb.remove();
            zzuq zzuqVar2 = this.zza.get(zzupVarRemove);
            zza("Evicting interstitial queue for %s.", zzupVarRemove);
            while (zzuqVar2.zzd() > 0) {
                zzur zzurVarZza = zzuqVar2.zza((zzkk) null);
                if (zzurVarZza.zze) {
                    zzus.zza().zzc();
                }
                zzurVarZza.zza.zzav();
            }
            this.zza.remove(zzupVarRemove);
        }
        while (zzuqVar.zzd() > 0) {
            zzur zzurVarZza2 = zzuqVar.zza(zzkkVarZzc);
            if (zzurVarZza2.zze) {
                if (zzbt.zzl().zza() - zzurVarZza2.zzd > ((long) ((Integer) zzlc.zzf().zza(zzoi.zzcr)).intValue()) * 1000) {
                    zza("Expired interstitial at %s.", zzupVar);
                    zzus.zza().zzb();
                }
            }
            String str2 = zzurVarZza2.zzb != null ? " (inline) " : " ";
            StringBuilder sb = new StringBuilder(str2.length() + 34);
            sb.append("Pooled interstitial");
            sb.append(str2);
            sb.append("returned at %s.");
            zza(sb.toString(), zzupVar);
            return zzurVarZza2;
        }
        return null;
    }

    final void zza() {
        int iZzd;
        int iZze;
        if (this.zzc == null) {
            return;
        }
        for (Map.Entry<zzup, zzuq> entry : this.zza.entrySet()) {
            zzup key = entry.getKey();
            zzuq value = entry.getValue();
            if (zzahw.zza(2) && (iZze = value.zze()) < (iZzd = value.zzd())) {
                zzahw.zza(String.format("Loading %s/%s pooled interstitials for %s.", Integer.valueOf(iZzd - iZze), Integer.valueOf(iZzd), key));
            }
            int iZzf = value.zzf() + 0;
            while (value.zzd() < ((Integer) zzlc.zzf().zza(zzoi.zzcq)).intValue()) {
                zza("Pooling and loading one new interstitial for %s.", key);
                if (value.zza(this.zzc)) {
                    iZzf++;
                }
            }
            zzus.zza().zza(iZzf);
        }
        zztk zztkVar = this.zzc;
        if (zztkVar != null) {
            SharedPreferences.Editor editorEdit = zztkVar.zza().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            editorEdit.clear();
            for (Map.Entry<zzup, zzuq> entry2 : this.zza.entrySet()) {
                zzup key2 = entry2.getKey();
                zzuq value2 = entry2.getValue();
                if (value2.zzh()) {
                    editorEdit.putString(key2.toString(), new zzuu(value2).zza());
                    zza("Saved interstitial queue for %s.", key2);
                }
            }
            editorEdit.putString("PoolKeys", zzb());
            editorEdit.apply();
        }
    }

    final void zza(zztk zztkVar) {
        if (this.zzc == null) {
            zztk zztkVarZzb = zztkVar.zzb();
            this.zzc = zztkVarZzb;
            if (zztkVarZzb != null) {
                SharedPreferences sharedPreferences = zztkVarZzb.zza().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
                while (this.zzb.size() > 0) {
                    zzup zzupVarRemove = this.zzb.remove();
                    zzuq zzuqVar = this.zza.get(zzupVarRemove);
                    zza("Flushing interstitial queue for %s.", zzupVarRemove);
                    while (zzuqVar.zzd() > 0) {
                        zzuqVar.zza((zzkk) null).zza.zzav();
                    }
                    this.zza.remove(zzupVarRemove);
                }
                try {
                    HashMap map = new HashMap();
                    for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                        if (!entry.getKey().equals("PoolKeys")) {
                            zzuu zzuuVarZza = zzuu.zza((String) entry.getValue());
                            zzup zzupVar = new zzup(zzuuVarZza.zza, zzuuVarZza.zzb, zzuuVarZza.zzc);
                            if (!this.zza.containsKey(zzupVar)) {
                                this.zza.put(zzupVar, new zzuq(zzuuVarZza.zza, zzuuVarZza.zzb, zzuuVarZza.zzc));
                                map.put(zzupVar.toString(), zzupVar);
                                zza("Restored interstitial queue for %s.", zzupVar);
                            }
                        }
                    }
                    for (String str : zza(sharedPreferences.getString("PoolKeys", ""))) {
                        zzup zzupVar2 = (zzup) map.get(str);
                        if (this.zza.containsKey(zzupVar2)) {
                            this.zzb.add(zzupVar2);
                        }
                    }
                } catch (IOException | RuntimeException e) {
                    zzbt.zzi().zza(e, "InterstitialAdPool.restore");
                    zzahw.zzc("Malformed preferences value for InterstitialAdPool.", e);
                    this.zza.clear();
                    this.zzb.clear();
                }
            }
        }
    }

    final void zzb(zzkk zzkkVar, String str) {
        zztk zztkVar = this.zzc;
        if (zztkVar == null) {
            return;
        }
        int i = new zzaeb(zztkVar.zza()).zza().zzn;
        zzkk zzkkVarZzc = zzc(zzkkVar);
        String strZzc = zzc(str);
        zzup zzupVar = new zzup(zzkkVarZzc, strZzc, i);
        zzuq zzuqVar = this.zza.get(zzupVar);
        if (zzuqVar == null) {
            zza("Interstitial pool created at %s.", zzupVar);
            zzuqVar = new zzuq(zzkkVarZzc, strZzc, i);
            this.zza.put(zzupVar, zzuqVar);
        }
        zzuqVar.zza(this.zzc, zzkkVar);
        zzuqVar.zzg();
        zza("Inline entry added to the queue at %s.", zzupVar);
    }
}
