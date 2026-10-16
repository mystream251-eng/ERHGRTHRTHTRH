package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface zzro extends IInterface {
    String zza(String str) throws RemoteException;

    List<String> zza() throws RemoteException;

    boolean zza(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzb() throws RemoteException;

    zzqs zzb(String str) throws RemoteException;

    zzmm zzc() throws RemoteException;

    void zzc(String str) throws RemoteException;

    void zzd() throws RemoteException;

    IObjectWrapper zze() throws RemoteException;

    void zzf() throws RemoteException;

    String zzl() throws RemoteException;
}
