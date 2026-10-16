package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzsp extends zzp<zzre> {
    public zzsp() {
        super("com.google.android.gms.ads.NativeAdViewHolderDelegateCreatorImpl");
    }

    public final zzrb zza(View view, HashMap<String, View> map, HashMap<String, View> map2) {
        try {
            IBinder iBinderZza = ((zzre) zzb(view.getContext())).zza(zzn.zza(view), zzn.zza(map), zzn.zza(map2));
            if (iBinderZza == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZza.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
            return iInterfaceQueryLocalInterface instanceof zzrb ? (zzrb) iInterfaceQueryLocalInterface : new zzrd(iBinderZza);
        } catch (RemoteException | zzq e) {
            zzaky.zzc("Could not create remote NativeAdViewHolderDelegate.", e);
            return null;
        }
    }

    protected final /* synthetic */ Object zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegateCreator");
        return iInterfaceQueryLocalInterface instanceof zzre ? (zzre) iInterfaceQueryLocalInterface : new zzrf(iBinder);
    }
}
