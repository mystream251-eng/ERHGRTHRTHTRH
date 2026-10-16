package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpm;
import com.google.android.gms.internal.zzpx;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class zzbe implements Runnable {
    private /* synthetic */ zzpx zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ List zzc;
    private /* synthetic */ zzbb zzd;

    zzbe(zzbb zzbbVar, zzpx zzpxVar, int i, List list) {
        this.zzd = zzbbVar;
        this.zza = zzpxVar;
        this.zzb = i;
        this.zzc = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if ((this.zza instanceof zzpm) && this.zzd.zze.zzs != null) {
                this.zzd.zzc = this.zzb != this.zzc.size() - 1;
                this.zzd.zze.zzs.zza(zzbb.zzb(this.zza));
                return;
            }
            if ((this.zza instanceof zzpm) && this.zzd.zze.zzr != null) {
                this.zzd.zzc = this.zzb != this.zzc.size() - 1;
                this.zzd.zze.zzr.zza((zzpm) this.zza);
                return;
            }
            if ((this.zza instanceof zzpk) && this.zzd.zze.zzs != null) {
                this.zzd.zzc = this.zzb != this.zzc.size() - 1;
                this.zzd.zze.zzs.zza(zzbb.zzb(this.zza));
            } else if (!(this.zza instanceof zzpk) || this.zzd.zze.zzq == null) {
                this.zzd.zza(3, this.zzb != this.zzc.size() - 1);
            } else {
                this.zzd.zzc = this.zzb != this.zzc.size() - 1;
                this.zzd.zze.zzq.zza((zzpk) this.zza);
            }
        } catch (RemoteException e) {
            zzahw.zzc("Could not call native ad loaded", e);
        }
    }
}
