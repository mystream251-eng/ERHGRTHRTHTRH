package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzp extends com.google.android.gms.internal.common.zza implements zzq {
    public static zzq zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        if (iInterfaceQueryLocalInterface instanceof zzq) {
            return (zzq) iInterfaceQueryLocalInterface;
        }
        return new zzr(iBinder);
    }
}
