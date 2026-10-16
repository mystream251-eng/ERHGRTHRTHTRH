package com.google.android.apps.tvsearch.operator;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAssistantOperatorService extends IInterface {

    public static class Default implements IAssistantOperatorService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
        public String getAccessToken() throws RemoteException {
            return null;
        }

        @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
        public String getCustomContext() throws RemoteException {
            return null;
        }

        @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
        public String getDeviceModelId() throws RemoteException {
            return null;
        }
    }

    String getAccessToken() throws RemoteException;

    String getCustomContext() throws RemoteException;

    String getDeviceModelId() throws RemoteException;

    public static abstract class Stub extends Binder implements IAssistantOperatorService {
        private static final String DESCRIPTOR = "com.google.android.apps.tvsearch.operator.IAssistantOperatorService";
        static final int TRANSACTION_getAccessToken = 1;
        static final int TRANSACTION_getCustomContext = 3;
        static final int TRANSACTION_getDeviceModelId = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAssistantOperatorService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAssistantOperatorService)) {
                return (IAssistantOperatorService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String accessToken = getAccessToken();
                parcel2.writeNoException();
                parcel2.writeString(accessToken);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                String deviceModelId = getDeviceModelId();
                parcel2.writeNoException();
                parcel2.writeString(deviceModelId);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            String customContext = getCustomContext();
            parcel2.writeNoException();
            parcel2.writeString(customContext);
            return true;
        }

        private static class Proxy implements IAssistantOperatorService {
            public static IAssistantOperatorService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
            public String getAccessToken() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccessToken();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
            public String getDeviceModelId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceModelId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.apps.tvsearch.operator.IAssistantOperatorService
            public String getCustomContext() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCustomContext();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAssistantOperatorService iAssistantOperatorService) {
            if (Proxy.sDefaultImpl != null || iAssistantOperatorService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAssistantOperatorService;
            return true;
        }

        public static IAssistantOperatorService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
