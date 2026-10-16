package com.google.android.gms.internal;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzon {
    public static zzol zza(zzok zzokVar) {
        if (!zzokVar.zza()) {
            zzahw.zza("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
            return null;
        }
        if (zzokVar.zzc() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        }
        if (TextUtils.isEmpty(zzokVar.zzd())) {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
        return new zzol(zzokVar.zzc(), zzokVar.zzd(), zzokVar.zzb(), zzokVar.zze());
    }
}
