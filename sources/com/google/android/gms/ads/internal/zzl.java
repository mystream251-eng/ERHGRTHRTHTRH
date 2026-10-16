package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.internal.zzagq;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzzm;

/* JADX INFO: loaded from: classes2.dex */
final class zzl implements Runnable {
    final /* synthetic */ zzahe zza;
    final /* synthetic */ zzagq zzb;
    final /* synthetic */ zzi zzc;
    private /* synthetic */ zzov zzd;

    zzl(zzi zziVar, zzahe zzaheVar, zzagq zzagqVar, zzov zzovVar) {
        this.zzc = zziVar;
        this.zza = zzaheVar;
        this.zzb = zzagqVar;
        this.zzd = zzovVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.zzb.zzr && this.zzc.zze.zzaa != null) {
            String strZza = null;
            if (this.zza.zzb.zza != null) {
                zzbt.zze();
                strZza = zzaij.zza(this.zza.zzb.zza);
            }
            zzow zzowVar = new zzow(this.zzc, strZza, this.zza.zzb.zzb);
            this.zzc.zze.zzag = 1;
            try {
                this.zzc.zzc = false;
                this.zzc.zze.zzaa.zza(zzowVar);
                return;
            } catch (RemoteException e) {
                zzahw.zzc("Could not call the onCustomRenderedAdLoadedListener.", e);
                this.zzc.zzc = true;
            }
        }
        zzw zzwVar = new zzw(this.zzc.zze.zzc, this.zzb, this.zza.zzb.zzae);
        try {
            zzaof zzaofVarZza = this.zzc.zza(this.zza, zzwVar, this.zzb);
            zzaofVarZza.setOnTouchListener(new zzn(this, zzwVar));
            zzaofVarZza.setOnClickListener(new zzo(this, zzwVar));
            this.zzc.zze.zzag = 0;
            zzbu zzbuVar = this.zzc.zze;
            zzbt.zzd();
            Context context = this.zzc.zze.zzc;
            zzi zziVar = this.zzc;
            zzbuVar.zzh = zzzm.zza(context, zziVar, this.zza, zziVar.zze.zzd, zzaofVarZza, this.zzc.zzi, this.zzc, this.zzd);
        } catch (zzaop e2) {
            zzahw.zzb("Could not obtain webview.", e2);
            zzaij.zza.post(new zzm(this));
        }
    }
}
