package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zzdyo {
    public static boolean zza() {
        try {
            Class.forName("android.app.Application", false, null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
