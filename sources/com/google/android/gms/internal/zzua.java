package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zzua extends zzafd {
    private /* synthetic */ zztl zza;

    zzua(zztl zztlVar) {
        this.zza = zztlVar;
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza() throws RemoteException {
        this.zza.zza.add(new zzub(this));
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza(int i) throws RemoteException {
        this.zza.zza.add(new zzuh(this, i));
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zza(zzaeu zzaeuVar) throws RemoteException {
        this.zza.zza.add(new zzuf(this, zzaeuVar));
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzb() throws RemoteException {
        this.zza.zza.add(new zzuc(this));
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzc() throws RemoteException {
        this.zza.zza.add(new zzud(this));
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzd() throws RemoteException {
        this.zza.zza.add(new zzue(this));
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zze() throws RemoteException {
        this.zza.zza.add(new zzug(this));
    }

    @Override // com.google.android.gms.internal.zzafc
    public final void zzf() throws RemoteException {
        this.zza.zza.add(new zzui(this));
    }
}
