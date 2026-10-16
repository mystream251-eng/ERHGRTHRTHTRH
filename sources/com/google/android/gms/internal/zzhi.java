package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzhi {
    private final Object zza = new Object();
    private zzhj zzb = null;
    private boolean zzc = false;

    public final Activity zza() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return null;
            }
            return this.zzb.zza();
        }
    }

    public final void zza(Context context) {
        synchronized (this.zza) {
            if (!this.zzc) {
                if (!((Boolean) zzlc.zzf().zza(zzoi.zzbz)).booleanValue()) {
                    return;
                }
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    applicationContext = context;
                }
                Application application = applicationContext instanceof Application ? (Application) applicationContext : null;
                if (application == null) {
                    zzahw.zze("Can not cast Context to Application");
                    return;
                }
                if (this.zzb == null) {
                    this.zzb = new zzhj();
                }
                this.zzb.zza(application, context);
                this.zzc = true;
            }
        }
    }

    public final void zza(zzhl zzhlVar) {
        synchronized (this.zza) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzbz)).booleanValue()) {
                if (this.zzb == null) {
                    this.zzb = new zzhj();
                }
                this.zzb.zza(zzhlVar);
            }
        }
    }

    public final Context zzb() {
        synchronized (this.zza) {
            if (this.zzb == null) {
                return null;
            }
            return this.zzb.zzb();
        }
    }
}
