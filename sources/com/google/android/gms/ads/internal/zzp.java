package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzp {
    private static String zza(Bundle bundle) {
        String strZza;
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            Object obj = bundle.get((String) it.next());
            if (obj == null) {
                strZza = "null";
            } else {
                strZza = obj instanceof Bundle ? zza((Bundle) obj) : obj.toString();
            }
            sb.append(strZza);
        }
        return sb.toString();
    }

    public static Object[] zza(String str, zzkk zzkkVar, String str2, int i, zzko zzkoVar) {
        HashSet hashSet = new HashSet(Arrays.asList(str.split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        if (hashSet.contains("formatString")) {
            arrayList.add(null);
        }
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains("birthday")) {
            arrayList.add(Long.valueOf(zzkkVar.zzb));
        }
        if (hashSet.contains("extras")) {
            arrayList.add(zza(zzkkVar.zzc));
        }
        if (hashSet.contains("gender")) {
            arrayList.add(Integer.valueOf(zzkkVar.zzd));
        }
        if (hashSet.contains("keywords")) {
            if (zzkkVar.zze != null) {
                arrayList.add(zzkkVar.zze.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(zzkkVar.zzf));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(zzkkVar.zzg));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(zzkkVar.zzh));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(zzkkVar.zzi);
        }
        if (hashSet.contains("location")) {
            if (zzkkVar.zzk != null) {
                arrayList.add(zzkkVar.zzk.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(zzkkVar.zzl);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(zza(zzkkVar.zzm));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(zza(zzkkVar.zzn));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (zzkkVar.zzo != null) {
                arrayList.add(zzkkVar.zzo.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(zzkkVar.zzp);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(zzkkVar.zzq);
        }
        if (hashSet.contains("isDesignedForFamilies")) {
            arrayList.add(Boolean.valueOf(zzkkVar.zzr));
        }
        return arrayList.toArray();
    }
}
