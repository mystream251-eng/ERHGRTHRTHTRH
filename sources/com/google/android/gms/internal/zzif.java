package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzif {
    private final Runnable zza = new zzig(this);
    private final Object zzb = new Object();
    private zzim zzc;
    private Context zzd;
    private zziq zze;

    static /* synthetic */ zzim zza(zzif zzifVar, zzim zzimVar) {
        zzifVar.zzc = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb() {
        synchronized (this.zzb) {
            if (this.zzd != null && this.zzc == null) {
                zzim zzimVar = new zzim(this.zzd, zzbt.zzt().zza(), new zzii(this), new zzij(this));
                this.zzc = zzimVar;
                zzimVar.zzz();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc() {
        synchronized (this.zzb) {
            if (this.zzc == null) {
                return;
            }
            if (this.zzc.zzs() || this.zzc.zzt()) {
                this.zzc.zzg();
            }
            this.zzc = null;
            this.zze = null;
            Binder.flushPendingCommands();
        }
    }

    public final zzik zza(zzin zzinVar) {
        synchronized (this.zzb) {
            if (this.zze == null) {
                return new zzik();
            }
            try {
                return this.zze.zza(zzinVar);
            } catch (RemoteException e) {
                zzahw.zzb("Unable to call into cache service.", e);
                return new zzik();
            }
        }
    }

    public final void zza() {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzfv)).booleanValue()) {
            synchronized (this.zzb) {
                zzb();
                zzbt.zze();
                zzaij.zza.removeCallbacks(this.zza);
                zzbt.zze();
                zzaij.zza.postDelayed(this.zza, ((Long) zzlc.zzf().zza(zzoi.zzfw)).longValue());
            }
        }
    }

    public final void zza(Context context) {
        if (context == null) {
            return;
        }
        synchronized (this.zzb) {
            if (this.zzd != null) {
                return;
            }
            this.zzd = context.getApplicationContext();
            if (((Boolean) zzlc.zzf().zza(zzoi.zzfu)).booleanValue()) {
                zzb();
            } else {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzft)).booleanValue()) {
                    zzbt.zzh().zza(new zzih(this));
                }
            }
        }
    }
}
