package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface IGmsServiceBroker extends IInterface {
    void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public static abstract class Stub extends Binder implements IGmsServiceBroker {
        public Stub() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
        static class zza implements IGmsServiceBroker {
            private final IBinder zza;

            zza(IBinder iBinder) {
                this.zza = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.zza;
            }

            @Override // com.google.android.gms.common.internal.IGmsServiceBroker
            public final void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    parcelObtain.writeStrongBinder(iGmsCallbacks != null ? iGmsCallbacks.asBinder() : null);
                    if (getServiceRequest != null) {
                        parcelObtain.writeInt(1);
                        getServiceRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.zza.transact(46, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:61:0x00cf  */
        /* JADX WARN: Code duplicated, block: B:63:0x00db  */
        /* JADX WARN: Code duplicated, block: B:64:0x00e4  */
        /* JADX WARN: Code duplicated, block: B:66:0x00ea  */
        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IGmsCallbacks zzoVar;
            if (i > 16777215) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                zzoVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                if (iInterfaceQueryLocalInterface instanceof IGmsCallbacks) {
                    zzoVar = (IGmsCallbacks) iInterfaceQueryLocalInterface;
                } else {
                    zzoVar = new zzo(strongBinder);
                }
            }
            if (i == 46) {
                getService(zzoVar, parcel.readInt() != 0 ? GetServiceRequest.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 47) {
                if (parcel.readInt() != 0) {
                    zzv.CREATOR.createFromParcel(parcel);
                }
                throw new UnsupportedOperationException();
            }
            parcel.readInt();
            if (i != 4) {
                parcel.readString();
            }
            if (i != 1) {
                if (i != 2 && i != 23 && i != 25 && i != 27) {
                    if (i != 30) {
                        if (i != 34) {
                            if (i == 41 || i == 43 || i == 37 || i == 38) {
                                if (parcel.readInt() != 0) {
                                }
                            } else {
                                switch (i) {
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                    case 11:
                                    case 12:
                                    case 13:
                                    case 14:
                                    case 15:
                                    case 16:
                                    case 17:
                                    case 18:
                                        if (parcel.readInt() != 0) {
                                        }
                                        break;
                                    case 9:
                                        parcel.readString();
                                        parcel.createStringArray();
                                        parcel.readString();
                                        parcel.readStrongBinder();
                                        parcel.readString();
                                        if (parcel.readInt() != 0) {
                                        }
                                        break;
                                    case 10:
                                        parcel.readString();
                                        parcel.createStringArray();
                                        break;
                                    case 19:
                                        parcel.readStrongBinder();
                                        if (parcel.readInt() != 0) {
                                        }
                                        break;
                                    case 20:
                                        parcel.createStringArray();
                                        parcel.readString();
                                        if (parcel.readInt() != 0) {
                                        }
                                        break;
                                }
                            }
                        } else {
                            parcel.readString();
                        }
                    } else {
                        parcel.createStringArray();
                        parcel.readString();
                        if (parcel.readInt() != 0) {
                        }
                    }
                } else if (parcel.readInt() != 0) {
                }
            } else {
                parcel.readString();
                parcel.createStringArray();
                parcel.readString();
                if (parcel.readInt() != 0) {
                }
            }
            throw new UnsupportedOperationException();
        }
    }
}
