package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface IAccountAccessor extends IInterface {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public static abstract class Stub extends com.google.android.gms.internal.common.zza implements IAccountAccessor {
        public Stub() {
            super("com.google.android.gms.common.internal.IAccountAccessor");
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
        public static class zza extends com.google.android.gms.internal.common.zzb implements IAccountAccessor {
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
            }

            @Override // com.google.android.gms.common.internal.IAccountAccessor
            public final Account zza() throws RemoteException {
                Parcel parcelZza = zza(2, a_());
                Account account = (Account) com.google.android.gms.internal.common.zzd.zza(parcelZza, Account.CREATOR);
                parcelZza.recycle();
                return account;
            }
        }

        public static IAccountAccessor asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            if (iInterfaceQueryLocalInterface instanceof IAccountAccessor) {
                return (IAccountAccessor) iInterfaceQueryLocalInterface;
            }
            return new zza(iBinder);
        }

        @Override // com.google.android.gms.internal.common.zza
        protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 2) {
                return false;
            }
            Account accountZza = zza();
            parcel2.writeNoException();
            com.google.android.gms.internal.common.zzd.zzb(parcel2, accountZza);
            return true;
        }
    }

    Account zza() throws RemoteException;
}
