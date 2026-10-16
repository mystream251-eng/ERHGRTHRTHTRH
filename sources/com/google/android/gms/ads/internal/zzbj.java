package com.google.android.gms.ads.internal;

import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzkk;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzbj {
    private final zzbl zza;
    private final Runnable zzb;
    private zzkk zzc;
    private boolean zzd;
    private boolean zze;
    private long zzf;

    public zzbj(zza zzaVar) {
        this(zzaVar, new zzbl(zzaij.zza));
    }

    private zzbj(zza zzaVar, zzbl zzblVar) {
        this.zzd = false;
        this.zze = false;
        this.zzf = 0L;
        this.zza = zzblVar;
        this.zzb = new zzbk(this, new WeakReference(zzaVar));
    }

    static /* synthetic */ boolean zza(zzbj zzbjVar, boolean z) {
        zzbjVar.zzd = false;
        return false;
    }

    public final void zza() {
        this.zzd = false;
        this.zza.zza(this.zzb);
    }

    public final void zza(zzkk zzkkVar) {
        this.zzc = zzkkVar;
    }

    public final void zza(zzkk zzkkVar, long j) {
        if (this.zzd) {
            zzahw.zze("An ad refresh is already scheduled.");
            return;
        }
        this.zzc = zzkkVar;
        this.zzd = true;
        this.zzf = j;
        if (this.zze) {
            return;
        }
        StringBuilder sb = new StringBuilder(65);
        sb.append("Scheduling ad refresh ");
        sb.append(j);
        sb.append(" milliseconds from now.");
        zzahw.zzd(sb.toString());
        this.zza.zza(this.zzb, j);
    }

    public final void zzb() {
        this.zze = true;
        if (this.zzd) {
            this.zza.zza(this.zzb);
        }
    }

    public final void zzb(zzkk zzkkVar) {
        zza(zzkkVar, DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
    }

    public final void zzc() {
        this.zze = false;
        if (this.zzd) {
            this.zzd = false;
            zza(this.zzc, this.zzf);
        }
    }

    public final void zzd() {
        this.zze = false;
        this.zzd = false;
        zzkk zzkkVar = this.zzc;
        if (zzkkVar != null && zzkkVar.zzc != null) {
            this.zzc.zzc.remove("_ad");
        }
        zza(this.zzc, 0L);
    }

    public final boolean zze() {
        return this.zzd;
    }
}
