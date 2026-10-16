package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzacd extends zzabz implements zzf, zzg {
    private Context zza;
    private zzala zzb;
    private zzamf<zzacf> zzc;
    private final zzabx zzd;
    private final Object zze;
    private zzace zzf;

    public zzacd(Context context, zzala zzalaVar, zzamf<zzacf> zzamfVar, zzabx zzabxVar) {
        super(zzamfVar, zzabxVar);
        this.zze = new Object();
        this.zza = context;
        this.zzb = zzalaVar;
        this.zzc = zzamfVar;
        this.zzd = zzabxVar;
        zzace zzaceVar = new zzace(context, ((Boolean) zzlc.zzf().zza(zzoi.zzac)).booleanValue() ? zzbt.zzt().zza() : context.getMainLooper(), this, this, this.zzb.zzc);
        this.zzf = zzaceVar;
        zzaceVar.zzz();
    }

    @Override // com.google.android.gms.internal.zzabz
    public final void zza() {
        synchronized (this.zze) {
            if (this.zzf.zzs() || this.zzf.zzt()) {
                this.zzf.zzg();
            }
            Binder.flushPendingCommands();
        }
    }

    public final void zza(int i) {
        zzahw.zzb("Disconnected from remote ad request service.");
    }

    public final void zza(Bundle bundle) {
        zzc();
    }

    public final void zza(ConnectionResult connectionResult) {
        zzahw.zzb("Cannot connect to remote service, fallback to local instance.");
        new zzacc(this.zza, this.zzc, this.zzd).zzc();
        Bundle bundle = new Bundle();
        bundle.putString("action", "gms_connection_failed_fallback_to_local");
        zzbt.zze().zzb(this.zza, this.zzb.zza, "gmob-apps", bundle, true);
    }

    @Override // com.google.android.gms.internal.zzabz
    public final zzacn zzd() {
        zzacn zzacnVarE_;
        synchronized (this.zze) {
            try {
                try {
                    zzacnVarE_ = this.zzf.e_();
                } catch (DeadObjectException | IllegalStateException unused) {
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzacnVarE_;
    }
}
