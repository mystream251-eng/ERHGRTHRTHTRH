package megacable.settings.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioInterface extends IInterface {

    public static class Default implements AudioInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // megacable.settings.aidl.AudioInterface
        public String getDigitalAudioFormat() throws RemoteException {
            return null;
        }

        @Override // megacable.settings.aidl.AudioInterface
        public void setDigitalAudioFormat(String str) throws RemoteException {
        }
    }

    String getDigitalAudioFormat() throws RemoteException;

    void setDigitalAudioFormat(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements AudioInterface {
        private static final String DESCRIPTOR = "megacable.settings.aidl.AudioInterface";
        static final int TRANSACTION_getDigitalAudioFormat = 1;
        static final int TRANSACTION_setDigitalAudioFormat = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AudioInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof AudioInterface)) {
                return (AudioInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String digitalAudioFormat = getDigitalAudioFormat();
                parcel2.writeNoException();
                parcel2.writeString(digitalAudioFormat);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            setDigitalAudioFormat(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements AudioInterface {
            public static AudioInterface sDefaultImpl;
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

            @Override // megacable.settings.aidl.AudioInterface
            public String getDigitalAudioFormat() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDigitalAudioFormat();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // megacable.settings.aidl.AudioInterface
            public void setDigitalAudioFormat(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDigitalAudioFormat(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(AudioInterface audioInterface) {
            if (Proxy.sDefaultImpl != null || audioInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = audioInterface;
            return true;
        }

        public static AudioInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
