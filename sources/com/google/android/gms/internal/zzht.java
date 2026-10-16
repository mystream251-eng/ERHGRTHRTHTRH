package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzht {
    private final int zzb;
    private final zzhs zzd = new zzhx();
    private final int zza = 6;
    private final int zzc = 0;

    public zzht(int i) {
        this.zzb = i;
    }

    private final String zza(String str) {
        String[] strArrSplit = str.split("\n");
        if (strArrSplit.length == 0) {
            return "";
        }
        zzhv zzhvVar = new zzhv();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzb, new zzhu(this));
        for (String str2 : strArrSplit) {
            String[] strArrZza = zzhw.zza(str2, false);
            if (strArrZza.length != 0) {
                zzhz.zza(strArrZza, this.zzb, this.zza, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzhvVar.zza(this.zzd.zza(((zzia) it.next()).zzb));
            } catch (IOException e) {
                zzahw.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzhvVar.toString();
    }

    public final String zza(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            String str = arrayList2.get(i);
            i++;
            sb.append(str.toLowerCase(Locale.US));
            sb.append('\n');
        }
        return zza(sb.toString());
    }
}
