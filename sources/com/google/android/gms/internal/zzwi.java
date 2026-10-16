package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface zzwi extends IInterface {
    IObjectWrapper zza() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzafz zzafzVar, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, zzafz zzafzVar, String str2) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, zzwl zzwlVar) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, String str2, zzwl zzwlVar) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzkk zzkkVar, String str, String str2, zzwl zzwlVar, zzqh zzqhVar, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, zzkk zzkkVar, String str, zzwl zzwlVar) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzko zzkoVar, zzkk zzkkVar, String str, String str2, zzwl zzwlVar) throws RemoteException;

    void zza(zzkk zzkkVar, String str) throws RemoteException;

    void zza(zzkk zzkkVar, String str, String str2) throws RemoteException;

    void zza(boolean z) throws RemoteException;

    void zzb() throws RemoteException;

    void zzc() throws RemoteException;

    void zzd() throws RemoteException;

    void zze() throws RemoteException;

    void zzf() throws RemoteException;

    boolean zzg() throws RemoteException;

    zzwr zzh() throws RemoteException;

    zzwu zzi() throws RemoteException;

    Bundle zzj() throws RemoteException;

    Bundle zzk() throws RemoteException;

    Bundle zzl() throws RemoteException;

    boolean zzm() throws RemoteException;

    zzro zzn() throws RemoteException;

    zzmm zzo() throws RemoteException;

    zzwx zzp() throws RemoteException;
}
