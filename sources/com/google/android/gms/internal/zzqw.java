package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public interface zzqw extends IInterface {
    IObjectWrapper zza(String str) throws RemoteException;

    void zza() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zza(String str, IObjectWrapper iObjectWrapper) throws RemoteException;
}
