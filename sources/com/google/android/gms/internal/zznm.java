package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class zznm extends zzaey {
    private zzafc zza;

    @Override // com.google.android.gms.internal.zzaex
    public final void zza() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(zzafc zzafcVar) throws RemoteException {
        this.zza = zzafcVar;
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(zzafi zzafiVar) throws RemoteException {
        zzaky.zzc("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzako.zza.post(new zznn(this));
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zza(boolean z) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final boolean zzb() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzc() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zzd() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final void zze() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.zzaex
    public final String zzf() throws RemoteException {
        return null;
    }
}
