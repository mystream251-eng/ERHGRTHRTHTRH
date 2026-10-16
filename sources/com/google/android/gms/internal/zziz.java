package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zziz {
    zzfm zza;
    boolean zzb;

    public zziz() {
    }

    public zziz(Context context) {
        zzoi.zza(context);
        if (((Boolean) zzlc.zzf().zza(zzoi.zzgp)).booleanValue()) {
            try {
                this.zza = zzfn.zza(DynamiteModule.zza(context, DynamiteModule.zza, ModuleDescriptor.MODULE_ID).zza("com.google.android.gms.ads.clearcut.DynamiteClearcutLogger"));
                zzn.zza(context);
                this.zza.zza(zzn.zza(context), "GMA_SDK");
                this.zzb = true;
            } catch (DynamiteModule.zzc | RemoteException | NullPointerException unused) {
                zzaky.zzb("Cannot dynamite load clearcut");
            }
        }
    }

    public zziz(Context context, String str, String str2) {
        zzoi.zza(context);
        try {
            this.zza = zzfn.zza(DynamiteModule.zza(context, DynamiteModule.zza, ModuleDescriptor.MODULE_ID).zza("com.google.android.gms.ads.clearcut.DynamiteClearcutLogger"));
            zzn.zza(context);
            this.zza.zza(zzn.zza(context), str, null);
            this.zzb = true;
        } catch (DynamiteModule.zzc | RemoteException | NullPointerException unused) {
            zzaky.zzb("Cannot dynamite load clearcut");
        }
    }

    public final zzjb zza(byte[] bArr) {
        return new zzjb(this, bArr);
    }
}
