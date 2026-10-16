package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
final class zzaed implements Callable<zzaea> {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzaec zzb;

    zzaed(zzaec zzaecVar, Context context) {
        this.zzb = zzaecVar;
        this.zza = context;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0054  */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzaea call() throws Exception {
        zzaea zzaeaVarZza;
        zzaee zzaeeVar = (zzaee) this.zzb.zza.get(this.zza);
        if (zzaeeVar != null) {
            if (zzaeeVar.zza + ((Long) zzlc.zzf().zza(zzoi.zzdi)).longValue() < zzbt.zzl().zza()) {
                zzaeaVarZza = new zzaeb(this.zza).zza();
            } else {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzdh)).booleanValue()) {
                    zzaeaVarZza = new zzaeb(this.zza, zzaeeVar.zzb).zza();
                } else {
                    zzaeaVarZza = new zzaeb(this.zza).zza();
                }
            }
        } else {
            zzaeaVarZza = new zzaeb(this.zza).zza();
        }
        this.zzb.zza.put(this.zza, new zzaee(this.zzb, zzaeaVarZza));
        return zzaeaVarZza;
    }
}
