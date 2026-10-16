package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface ICancelToken extends IInterface {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public static abstract class Stub extends com.google.android.gms.internal.common.zza implements ICancelToken {
        public Stub() {
            super("com.google.android.gms.common.internal.ICancelToken");
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
        public static class zza extends com.google.android.gms.internal.common.zzb implements ICancelToken {
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.ICancelToken");
            }

            @Override // com.google.android.gms.common.internal.ICancelToken
            public final void cancel() throws RemoteException {
                zzc(2, a_());
            }
        }

        public static ICancelToken asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            if (iInterfaceQueryLocalInterface instanceof ICancelToken) {
                return (ICancelToken) iInterfaceQueryLocalInterface;
            }
            return new zza(iBinder);
        }
    }

    void cancel() throws RemoteException;
}
