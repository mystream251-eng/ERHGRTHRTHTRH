package com.google.android.gms.internal;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzic {
    private final zzhs zza;
    private final int zzb;
    private String zzc;
    private String zzd;
    private final boolean zze = false;
    private final int zzf;
    private final int zzg;

    public zzic(int i, int i2, int i3) {
        this.zzb = i;
        if (i2 > 64 || i2 < 0) {
            this.zzf = 64;
        } else {
            this.zzf = i2;
        }
        if (i3 <= 0) {
            this.zzg = 1;
        } else {
            this.zzg = i3;
        }
        this.zza = new zzib(this.zzf);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x005f  */
    private final boolean zza(String str, HashSet<String> hashSet) {
        boolean z;
        String[] strArrSplit = str.split("\n");
        if (strArrSplit.length == 0) {
            return true;
        }
        for (String str2 : strArrSplit) {
            if (str2.indexOf("'") != -1) {
                StringBuilder sb = new StringBuilder(str2);
                boolean z2 = false;
                int i = 1;
                while (true) {
                    int i2 = i + 2;
                    if (i2 > sb.length()) {
                        break;
                    }
                    if (sb.charAt(i) == '\'') {
                        if (sb.charAt(i - 1) != ' ') {
                            int i3 = i + 1;
                            if ((sb.charAt(i3) == 's' || sb.charAt(i3) == 'S') && (i2 == sb.length() || sb.charAt(i2) == ' ')) {
                                sb.insert(i, ' ');
                                i = i2;
                            } else {
                                sb.setCharAt(i, ' ');
                            }
                        } else {
                            sb.setCharAt(i, ' ');
                        }
                        z2 = true;
                    }
                    i++;
                }
                String string = z2 ? sb.toString() : null;
                if (string != null) {
                    this.zzd = string;
                    str2 = string;
                }
            }
            String[] strArrZza = zzhw.zza(str2, true);
            if (strArrZza.length >= this.zzg) {
                for (int i4 = 0; i4 < strArrZza.length; i4++) {
                    String strConcat = "";
                    int i5 = 0;
                    while (true) {
                        if (i5 >= this.zzg) {
                            z = true;
                            break;
                        }
                        int i6 = i4 + i5;
                        if (i6 >= strArrZza.length) {
                            z = false;
                            break;
                        }
                        if (i5 > 0) {
                            strConcat = String.valueOf(strConcat).concat(" ");
                        }
                        String strValueOf = String.valueOf(strConcat);
                        String strValueOf2 = String.valueOf(strArrZza[i6]);
                        strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                        i5++;
                    }
                    if (!z) {
                        break;
                    }
                    hashSet.add(strConcat);
                    if (hashSet.size() >= this.zzb) {
                        return false;
                    }
                }
                if (hashSet.size() >= this.zzb) {
                    return false;
                }
            }
        }
        return true;
    }

    public final String zza(ArrayList<String> arrayList, ArrayList<zzhr> arrayList2) {
        Collections.sort(arrayList2, new zzid(this));
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < arrayList2.size() && zza(Normalizer.normalize(arrayList.get(arrayList2.get(i).zze()), Normalizer.Form.NFKC).toLowerCase(Locale.US), hashSet); i++) {
        }
        zzhv zzhvVar = new zzhv();
        this.zzc = "";
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                zzhvVar.zza(this.zza.zza(it.next()));
            } catch (IOException e) {
                zzahw.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzhvVar.toString();
    }
}
