package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zztm extends zzlj {
    private /* synthetic */ zztl zza;

    zztm(zztl zztlVar) {
        this.zza = zztlVar;
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zza() throws RemoteException {
        this.zza.zza.add(new zztn(this));
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zza(int i) throws RemoteException {
        this.zza.zza.add(new zzto(this, i));
        zzahw.zza("Pooled interstitial failed to load.");
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzb() throws RemoteException {
        this.zza.zza.add(new zztp(this));
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzc() throws RemoteException {
        this.zza.zza.add(new zztq(this));
        zzahw.zza("Pooled interstitial loaded.");
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzd() throws RemoteException {
        this.zza.zza.add(new zztr(this));
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zze() throws RemoteException {
        this.zza.zza.add(new zztt(this));
    }

    @Override // com.google.android.gms.internal.zzli
    public final void zzf() throws RemoteException {
        this.zza.zza.add(new zzts(this));
    }
}
