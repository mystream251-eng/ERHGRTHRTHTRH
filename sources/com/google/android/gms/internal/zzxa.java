package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzxa extends zzmn {
    private final Object zza = new Object();
    private volatile zzmp zzb;

    @Override // com.google.android.gms.internal.zzmm
    public final void zza() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zza(zzmp zzmpVar) throws RemoteException {
        synchronized (this.zza) {
            this.zzb = zzmpVar;
        }
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zza(boolean z) throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zzb() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzc() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final int zzd() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zze() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zzf() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zzg() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzh() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.zzmm
    public final zzmp zzi() throws RemoteException {
        zzmp zzmpVar;
        synchronized (this.zza) {
            zzmpVar = this.zzb;
        }
        return zzmpVar;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzj() throws RemoteException {
        throw new RemoteException();
    }
}
