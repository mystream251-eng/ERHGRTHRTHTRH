package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzd;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface IFragmentWrapper extends IInterface {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public static abstract class Stub extends com.google.android.gms.internal.common.zza implements IFragmentWrapper {
        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
        public static class zza extends zzb implements IFragmentWrapper {
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamic.IFragmentWrapper");
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zza() throws RemoteException {
                Parcel parcelZza = zza(2, a_());
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iObjectWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final Bundle zzb() throws RemoteException {
                Parcel parcelZza = zza(3, a_());
                Bundle bundle = (Bundle) zzd.zza(parcelZza, Bundle.CREATOR);
                parcelZza.recycle();
                return bundle;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final int zzc() throws RemoteException {
                Parcel parcelZza = zza(4, a_());
                int i = parcelZza.readInt();
                parcelZza.recycle();
                return i;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IFragmentWrapper zzd() throws RemoteException {
                Parcel parcelZza = zza(5, a_());
                IFragmentWrapper iFragmentWrapperAsInterface = Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iFragmentWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zze() throws RemoteException {
                Parcel parcelZza = zza(6, a_());
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iObjectWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzf() throws RemoteException {
                Parcel parcelZza = zza(7, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final String zzg() throws RemoteException {
                Parcel parcelZza = zza(8, a_());
                String string = parcelZza.readString();
                parcelZza.recycle();
                return string;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IFragmentWrapper zzh() throws RemoteException {
                Parcel parcelZza = zza(9, a_());
                IFragmentWrapper iFragmentWrapperAsInterface = Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iFragmentWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final int zzi() throws RemoteException {
                Parcel parcelZza = zza(10, a_());
                int i = parcelZza.readInt();
                parcelZza.recycle();
                return i;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzj() throws RemoteException {
                Parcel parcelZza = zza(11, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzk() throws RemoteException {
                Parcel parcelZza = zza(12, a_());
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
                parcelZza.recycle();
                return iObjectWrapperAsInterface;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzl() throws RemoteException {
                Parcel parcelZza = zza(13, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzm() throws RemoteException {
                Parcel parcelZza = zza(14, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzn() throws RemoteException {
                Parcel parcelZza = zza(15, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzo() throws RemoteException {
                Parcel parcelZza = zza(16, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzp() throws RemoteException {
                Parcel parcelZza = zza(17, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzq() throws RemoteException {
                Parcel parcelZza = zza(18, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean zzr() throws RemoteException {
                Parcel parcelZza = zza(19, a_());
                boolean zZza = zzd.zza(parcelZza);
                parcelZza.recycle();
                return zZza;
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel parcelA_ = a_();
                zzd.zza(parcelA_, iObjectWrapper);
                zzb(20, parcelA_);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zza(boolean z) throws RemoteException {
                Parcel parcelA_ = a_();
                zzd.zza(parcelA_, z);
                zzb(21, parcelA_);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zzb(boolean z) throws RemoteException {
                Parcel parcelA_ = a_();
                zzd.zza(parcelA_, z);
                zzb(22, parcelA_);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zzc(boolean z) throws RemoteException {
                Parcel parcelA_ = a_();
                zzd.zza(parcelA_, z);
                zzb(23, parcelA_);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zzd(boolean z) throws RemoteException {
                Parcel parcelA_ = a_();
                zzd.zza(parcelA_, z);
                zzb(24, parcelA_);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zza(Intent intent) throws RemoteException {
                Parcel parcelA_ = a_();
                zzd.zza(parcelA_, intent);
                zzb(25, parcelA_);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zza(Intent intent, int i) throws RemoteException {
                Parcel parcelA_ = a_();
                zzd.zza(parcelA_, intent);
                parcelA_.writeInt(i);
                zzb(26, parcelA_);
            }

            @Override // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel parcelA_ = a_();
                zzd.zza(parcelA_, iObjectWrapper);
                zzb(27, parcelA_);
            }
        }

        public static IFragmentWrapper asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            if (iInterfaceQueryLocalInterface instanceof IFragmentWrapper) {
                return (IFragmentWrapper) iInterfaceQueryLocalInterface;
            }
            return new zza(iBinder);
        }

        @Override // com.google.android.gms.internal.common.zza
        protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 2:
                    IObjectWrapper iObjectWrapperZza = zza();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, iObjectWrapperZza);
                    return true;
                case 3:
                    Bundle bundleZzb = zzb();
                    parcel2.writeNoException();
                    zzd.zzb(parcel2, bundleZzb);
                    return true;
                case 4:
                    int iZzc = zzc();
                    parcel2.writeNoException();
                    parcel2.writeInt(iZzc);
                    return true;
                case 5:
                    IFragmentWrapper iFragmentWrapperZzd = zzd();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, iFragmentWrapperZzd);
                    return true;
                case 6:
                    IObjectWrapper iObjectWrapperZze = zze();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, iObjectWrapperZze);
                    return true;
                case 7:
                    boolean zZzf = zzf();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzf);
                    return true;
                case 8:
                    String strZzg = zzg();
                    parcel2.writeNoException();
                    parcel2.writeString(strZzg);
                    return true;
                case 9:
                    IFragmentWrapper iFragmentWrapperZzh = zzh();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, iFragmentWrapperZzh);
                    return true;
                case 10:
                    int iZzi = zzi();
                    parcel2.writeNoException();
                    parcel2.writeInt(iZzi);
                    return true;
                case 11:
                    boolean zZzj = zzj();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzj);
                    return true;
                case 12:
                    IObjectWrapper iObjectWrapperZzk = zzk();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, iObjectWrapperZzk);
                    return true;
                case 13:
                    boolean zZzl = zzl();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzl);
                    return true;
                case 14:
                    boolean zZzm = zzm();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzm);
                    return true;
                case 15:
                    boolean zZzn = zzn();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzn);
                    return true;
                case 16:
                    boolean zZzo = zzo();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzo);
                    return true;
                case 17:
                    boolean zZzp = zzp();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzp);
                    return true;
                case 18:
                    boolean zZzq = zzq();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzq);
                    return true;
                case 19:
                    boolean zZzr = zzr();
                    parcel2.writeNoException();
                    zzd.zza(parcel2, zZzr);
                    return true;
                case 20:
                    zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    zza(zzd.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    zzb(zzd.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    zzc(zzd.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    zzd(zzd.zza(parcel));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    zza((Intent) zzd.zza(parcel, Intent.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    zza((Intent) zzd.zza(parcel, Intent.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return false;
            }
        }
    }

    IObjectWrapper zza() throws RemoteException;

    void zza(Intent intent) throws RemoteException;

    void zza(Intent intent, int i) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zza(boolean z) throws RemoteException;

    Bundle zzb() throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzb(boolean z) throws RemoteException;

    int zzc() throws RemoteException;

    void zzc(boolean z) throws RemoteException;

    IFragmentWrapper zzd() throws RemoteException;

    void zzd(boolean z) throws RemoteException;

    IObjectWrapper zze() throws RemoteException;

    boolean zzf() throws RemoteException;

    String zzg() throws RemoteException;

    IFragmentWrapper zzh() throws RemoteException;

    int zzi() throws RemoteException;

    boolean zzj() throws RemoteException;

    IObjectWrapper zzk() throws RemoteException;

    boolean zzl() throws RemoteException;

    boolean zzm() throws RemoteException;

    boolean zzn() throws RemoteException;

    boolean zzo() throws RemoteException;

    boolean zzp() throws RemoteException;

    boolean zzq() throws RemoteException;

    boolean zzr() throws RemoteException;
}
