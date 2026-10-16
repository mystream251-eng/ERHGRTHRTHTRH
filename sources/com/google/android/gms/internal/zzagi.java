package com.google.android.gms.internal;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes2.dex */
final class zzagi implements Runnable {
    private /* synthetic */ Bitmap zza;
    private /* synthetic */ zzagf zzb;

    zzagi(zzagf zzagfVar, Bitmap bitmap) {
        this.zzb = zzagfVar;
        this.zza = bitmap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.zza.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        synchronized (this.zzb.zzi) {
            this.zzb.zzc.zzg = new zzfmd();
            this.zzb.zzc.zzg.zzc = byteArrayOutputStream.toByteArray();
            this.zzb.zzc.zzg.zzb = "image/png";
            this.zzb.zzc.zzg.zza = 1;
        }
    }
}
