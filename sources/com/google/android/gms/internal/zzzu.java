package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzzu extends zzahs {
    private final zzzn zza;
    private final zzacj zzb;
    private final zzahe zzc;
    private final zzzy zzd;
    private final Object zze;
    private Future<zzahd> zzf;

    public zzzu(Context context, com.google.android.gms.ads.internal.zzbb zzbbVar, zzahe zzaheVar, zzcv zzcvVar, zzzn zzznVar, zzov zzovVar) {
        this(zzaheVar, zzznVar, new zzzy(context, zzbbVar, new zzajr(context), zzcvVar, zzaheVar, zzovVar));
    }

    private zzzu(zzahe zzaheVar, zzzn zzznVar, zzzy zzzyVar) {
        this.zze = new Object();
        this.zzc = zzaheVar;
        this.zzb = zzaheVar.zzb;
        this.zza = zzznVar;
        this.zzd = zzzyVar;
    }

    @Override // com.google.android.gms.internal.zzahs
    public final void b_() {
        synchronized (this.zze) {
            if (this.zzf != null) {
                this.zzf.cancel(true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.zzahs
    public final void zza() {
        zzalt zzaltVarZza;
        int i = 0;
        zzahd zzahdVar = null;
        try {
            try {
                synchronized (this.zze) {
                    zzaltVarZza = zzaid.zza(zzaid.zza, this.zzd);
                    this.zzf = zzaltVarZza;
                }
                i = -2;
                zzahdVar = (zzahd) zzaltVarZza.get(DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS, TimeUnit.MILLISECONDS);
            } catch (TimeoutException unused) {
                zzahw.zze("Timed out waiting for native ad.");
                i = 2;
                this.zzf.cancel(true);
            }
        } catch (InterruptedException | CancellationException | ExecutionException unused2) {
        }
        int i2 = i;
        if (zzahdVar == null) {
            zzahdVar = new zzahd(this.zzc.zza.zzc, null, null, i2, null, null, this.zzb.zzk, this.zzb.zzj, this.zzc.zza.zzi, false, null, null, null, null, null, this.zzb.zzh, this.zzc.zzd, this.zzb.zzf, this.zzc.zzf, this.zzb.zzm, this.zzb.zzn, this.zzc.zzh, null, null, null, null, this.zzc.zzb.zzad, this.zzc.zzb.zzae, null, null, this.zzb.zzal, this.zzc.zzi, this.zzc.zzb.zzao, false);
        }
        zzaij.zza.post(new zzzv(this, zzahdVar));
    }
}
