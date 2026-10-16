package com.google.android.gms.internal;

import java.util.PriorityQueue;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzhz {
    private static long zza(long j, int i) {
        if (i == 0) {
            return 1L;
        }
        if (i == 1) {
            return j;
        }
        return (i % 2 == 0 ? zza((j * j) % 1073807359, i / 2) : j * (zza((j * j) % 1073807359, i / 2) % 1073807359)) % 1073807359;
    }

    private static String zza(String[] strArr, int i, int i2) {
        int i3 = i2 + i;
        if (strArr.length < i3) {
            zzahw.zzc("Unable to construct shingle");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i4 = i3 - 1;
            if (i >= i4) {
                sb.append(strArr[i4]);
                return sb.toString();
            }
            sb.append(strArr[i]);
            sb.append(' ');
            i++;
        }
    }

    private static void zza(int i, long j, String str, int i2, PriorityQueue<zzia> priorityQueue) {
        zzia zziaVar = new zzia(j, str, i2);
        if ((priorityQueue.size() != i || (priorityQueue.peek().zzc <= zziaVar.zzc && priorityQueue.peek().zza <= zziaVar.zza)) && !priorityQueue.contains(zziaVar)) {
            priorityQueue.add(zziaVar);
            if (priorityQueue.size() > i) {
                priorityQueue.poll();
            }
        }
    }

    public static void zza(String[] strArr, int i, int i2, PriorityQueue<zzia> priorityQueue) {
        if (strArr.length < i2) {
            zza(i, zzb(strArr, 0, strArr.length), zza(strArr, 0, strArr.length), strArr.length, priorityQueue);
            return;
        }
        long jZzb = zzb(strArr, 0, i2);
        zza(i, jZzb, zza(strArr, 0, i2), i2, priorityQueue);
        long jZza = zza(16785407L, i2 - 1);
        for (int i3 = 1; i3 < (strArr.length - i2) + 1; i3++) {
            jZzb = ((((((jZzb + 1073807359) - ((((((long) zzhw.zza(strArr[i3 - 1])) + 2147483647L) % 1073807359) * jZza) % 1073807359)) % 1073807359) * 16785407) % 1073807359) + ((((long) zzhw.zza(strArr[(i3 + i2) - 1])) + 2147483647L) % 1073807359)) % 1073807359;
            zza(i, jZzb, zza(strArr, i3, i2), strArr.length, priorityQueue);
        }
    }

    private static long zzb(String[] strArr, int i, int i2) {
        long jZza = (((long) zzhw.zza(strArr[0])) + 2147483647L) % 1073807359;
        for (int i3 = 1; i3 < i2; i3++) {
            jZza = (((jZza * 16785407) % 1073807359) + ((((long) zzhw.zza(strArr[i3])) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return jZza;
    }
}
