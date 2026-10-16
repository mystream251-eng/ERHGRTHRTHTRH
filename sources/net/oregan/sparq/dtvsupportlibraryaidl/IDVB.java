package net.oregan.sparq.dtvsupportlibraryaidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IDVB extends IInterface {
    int closeTunerAllIndex() throws RemoteException;

    int getNetworkID() throws RemoteException;

    int getTunerCount() throws RemoteException;

    DVBTunerMetrics getTunerSignalMetrics(int i) throws RemoteException;

    void setNetworkIdChangeListener(IDVBNetworkIdChangeListener iDVBNetworkIdChangeListener) throws RemoteException;

    void setTunerLockChangedListener(IDVBTunerLockChangedListener iDVBTunerLockChangedListener) throws RemoteException;

    boolean startScan(IDVBScanProgressListener iDVBScanProgressListener) throws RemoteException;

    void stopScan() throws RemoteException;

    boolean tuneToHomeMux() throws RemoteException;

    public static abstract class Stub extends Binder implements IDVB {
        private static final String DESCRIPTOR = "net.oregan.sparq.dtvsupportlibraryaidl.IDVB";
        static final int TRANSACTION_closeTunerAllIndex = 9;
        static final int TRANSACTION_getNetworkID = 8;
        static final int TRANSACTION_getTunerCount = 3;
        static final int TRANSACTION_getTunerSignalMetrics = 4;
        static final int TRANSACTION_setNetworkIdChangeListener = 6;
        static final int TRANSACTION_setTunerLockChangedListener = 7;
        static final int TRANSACTION_startScan = 1;
        static final int TRANSACTION_stopScan = 2;
        static final int TRANSACTION_tuneToHomeMux = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDVB asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDVB)) {
                return (IDVB) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartScan = startScan(IDVBScanProgressListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartScan ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopScan();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tunerCount = getTunerCount();
                    parcel2.writeNoException();
                    parcel2.writeInt(tunerCount);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    DVBTunerMetrics tunerSignalMetrics = getTunerSignalMetrics(parcel.readInt());
                    parcel2.writeNoException();
                    if (tunerSignalMetrics != null) {
                        parcel2.writeInt(1);
                        tunerSignalMetrics.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zTuneToHomeMux = tuneToHomeMux();
                    parcel2.writeNoException();
                    parcel2.writeInt(zTuneToHomeMux ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNetworkIdChangeListener(IDVBNetworkIdChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTunerLockChangedListener(IDVBTunerLockChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int networkID = getNetworkID();
                    parcel2.writeNoException();
                    parcel2.writeInt(networkID);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCloseTunerAllIndex = closeTunerAllIndex();
                    parcel2.writeNoException();
                    parcel2.writeInt(iCloseTunerAllIndex);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDVB {
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

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public boolean startScan(IDVBScanProgressListener iDVBScanProgressListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDVBScanProgressListener != null ? iDVBScanProgressListener.asBinder() : null);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public void stopScan() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public int getTunerCount() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public DVBTunerMetrics getTunerSignalMetrics(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? DVBTunerMetrics.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public boolean tuneToHomeMux() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public void setNetworkIdChangeListener(IDVBNetworkIdChangeListener iDVBNetworkIdChangeListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDVBNetworkIdChangeListener != null ? iDVBNetworkIdChangeListener.asBinder() : null);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public void setTunerLockChangedListener(IDVBTunerLockChangedListener iDVBTunerLockChangedListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDVBTunerLockChangedListener != null ? iDVBTunerLockChangedListener.asBinder() : null);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public int getNetworkID() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVB
            public int closeTunerAllIndex() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
