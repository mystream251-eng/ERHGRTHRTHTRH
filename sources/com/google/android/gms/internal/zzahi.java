package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahi implements zzaic {
    private zzfu zzb;
    private Context zzf;
    private zzala zzg;
    private String zzl;
    private final Object zza = new Object();
    private final zzaho zzc = new zzaho();
    private final zzahy zzd = new zzahy();
    private boolean zze = false;
    private zzol zzh = null;
    private zzhm zzi = null;
    private zzhh zzj = null;
    private Boolean zzk = null;
    private final AtomicInteger zzm = new AtomicInteger(0);
    private final zzahk zzn = new zzahk();

    private final zzhm zza(Context context, boolean z, boolean z2) {
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzam)).booleanValue()) {
            return null;
        }
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzau)).booleanValue()) {
            if (!((Boolean) zzlc.zzf().zza(zzoi.zzas)).booleanValue()) {
                return null;
            }
        }
        if (z && z2) {
            return null;
        }
        synchronized (this.zza) {
            if (Looper.getMainLooper() != null && context != null) {
                if (this.zzj == null) {
                    this.zzj = new zzhh();
                }
                if (this.zzi == null) {
                    this.zzi = new zzhm(this.zzj, zzabb.zza(context, this.zzg));
                }
                this.zzi.zza();
                zzahw.zzd("start fetching content...");
                return this.zzi;
            }
            return null;
        }
    }

    public final zzaho zza() {
        return this.zzc;
    }

    public final zzhm zza(Context context) {
        return zza(context, this.zzd.zzb(), this.zzd.zzd());
    }

    public final void zza(Context context, zzala zzalaVar) {
        synchronized (this.zza) {
            if (!this.zze) {
                this.zzf = context.getApplicationContext();
                this.zzg = zzalaVar;
                zzbt.zzh().zza(zzbt.zzj());
                this.zzd.zza(this.zzf);
                this.zzd.zza(this);
                zzabb.zza(this.zzf, this.zzg);
                this.zzl = zzbt.zze().zza(context, zzalaVar.zza);
                this.zzb = new zzfu(context.getApplicationContext(), this.zzg);
                zzok zzokVar = new zzok(this.zzf, this.zzg.zza);
                try {
                    zzbt.zzn();
                    this.zzh = zzon.zza(zzokVar);
                } catch (IllegalArgumentException e) {
                    zzahw.zzc("Cannot initialize CSI reporter.", e);
                }
                this.zze = true;
            }
        }
    }

    @Override // com.google.android.gms.internal.zzaic
    public final void zza(Bundle bundle) {
        if (bundle.containsKey("content_url_opted_out") && bundle.containsKey("content_vertical_opted_out")) {
            zza(this.zzf, bundle.getBoolean("content_url_opted_out"), bundle.getBoolean("content_vertical_opted_out"));
        }
    }

    public final void zza(Boolean bool) {
        synchronized (this.zza) {
            this.zzk = bool;
        }
    }

    public final void zza(Throwable th, String str) {
        zzabb.zza(this.zzf, this.zzg).zza(th, str);
    }

    public final void zza(boolean z) {
        this.zzn.zza(z);
    }

    public final zzol zzb() {
        zzol zzolVar;
        synchronized (this.zza) {
            zzolVar = this.zzh;
        }
        return zzolVar;
    }

    public final Boolean zzc() {
        Boolean bool;
        synchronized (this.zza) {
            bool = this.zzk;
        }
        return bool;
    }

    public final boolean zzd() {
        return this.zzn.zza();
    }

    public final boolean zze() {
        return this.zzn.zzb();
    }

    public final void zzf() {
        this.zzn.zzc();
    }

    public final zzfu zzg() {
        return this.zzb;
    }

    public final Resources zzh() {
        if (this.zzg.zzd) {
            return this.zzf.getResources();
        }
        try {
            DynamiteModule dynamiteModuleZza = DynamiteModule.zza(this.zzf, DynamiteModule.zza, ModuleDescriptor.MODULE_ID);
            if (dynamiteModuleZza != null) {
                return dynamiteModuleZza.zza().getResources();
            }
            return null;
        } catch (DynamiteModule.zzc e) {
            zzahw.zzc("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final void zzi() {
        this.zzm.incrementAndGet();
    }

    public final void zzj() {
        this.zzm.decrementAndGet();
    }

    public final int zzk() {
        return this.zzm.get();
    }

    public final zzahy zzl() {
        zzahy zzahyVar;
        synchronized (this.zza) {
            zzahyVar = this.zzd;
        }
        return zzahyVar;
    }
}
