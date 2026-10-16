package com.google.android.gms.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
final class zznf extends zzlm {
    final /* synthetic */ zznd zza;

    private zznf(zznd zzndVar) {
        this.zza = zzndVar;
    }

    @Override // com.google.android.gms.internal.zzll
    public final String zza() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.zzll
    public final void zza(zzkk zzkkVar) throws RemoteException {
        zza(zzkkVar, 1);
    }

    @Override // com.google.android.gms.internal.zzll
    public final void zza(zzkk zzkkVar, int i) throws RemoteException {
        zzaky.zzc("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzako.zza.post(new zzng(this));
    }

    @Override // com.google.android.gms.internal.zzll
    public final String zzb() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.zzll
    public final boolean zzc() throws RemoteException {
        return false;
    }
}
