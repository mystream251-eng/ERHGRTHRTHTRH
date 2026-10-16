package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
@CheckReturnValue
final class zzc {
    private static volatile zzq zza;
    private static final Object zzb = new Object();
    private static Context zzc;

    static synchronized void zza(Context context) {
        if (zzc != null) {
            Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
        } else if (context != null) {
            zzc = context.getApplicationContext();
        }
    }

    static zzl zza(String str, zzd zzdVar, boolean z, boolean z2) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return zzb(str, zzdVar, z, z2);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    private static zzl zzb(final String str, final zzd zzdVar, final boolean z, boolean z2) {
        try {
            if (zza == null) {
                Preconditions.checkNotNull(zzc);
                synchronized (zzb) {
                    if (zza == null) {
                        zza = zzp.zza(DynamiteModule.load(zzc, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                }
            }
            Preconditions.checkNotNull(zzc);
            try {
                if (zza.zza(new zzj(str, zzdVar, z, z2), ObjectWrapper.wrap(zzc.getPackageManager()))) {
                    return zzl.zza();
                }
                return zzl.zza((Callable<String>) new Callable(z, str, zzdVar) { // from class: com.google.android.gms.common.zze
                    private final boolean zza;
                    private final String zzb;
                    private final zzd zzc;

                    {
                        this.zza = z;
                        this.zzb = str;
                        this.zzc = zzdVar;
                    }

                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        boolean z3 = this.zza;
                        String str2 = this.zzb;
                        zzd zzdVar2 = this.zzc;
                        return zzl.zza(str2, zzdVar2, z3, !z3 && zzc.zzb(str2, zzdVar2, true, false).zza);
                    }
                });
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return zzl.zza("module call", e);
            }
        } catch (DynamiteModule.LoadingException e2) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            String strValueOf = String.valueOf(e2.getMessage());
            return zzl.zza(strValueOf.length() != 0 ? "module init: ".concat(strValueOf) : new String("module init: "), e2);
        }
    }
}
