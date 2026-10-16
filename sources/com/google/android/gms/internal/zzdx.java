package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdx extends zzeu {
    private final Activity zzd;
    private final View zze;

    public zzdx(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2, View view, Activity activity) {
        super(zzdmVar, str, str2, zzbaVar, i, 62);
        this.zze = view;
        this.zzd = activity;
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zze == null) {
            return;
        }
        long[] jArr = (long[]) this.zzc.invoke(null, this.zze, this.zzd);
        synchronized (this.zzb) {
            this.zzb.zzaq = Long.valueOf(jArr[0]);
            this.zzb.zzar = Long.valueOf(jArr[1]);
        }
    }
}
