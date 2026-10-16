package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjc {
    public static int zza(int i) {
        if (i == 0 || i == 1 || i == 1000) {
            return i;
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append(i);
        sb.append(" is not a valid enum EnumBoolean");
        throw new IllegalArgumentException(sb.toString());
    }
}
