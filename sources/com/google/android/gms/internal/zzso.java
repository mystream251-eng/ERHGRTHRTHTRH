package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzso extends zzp<zzqz> {
    public zzso() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    public final zzqw zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        try {
            IBinder iBinderZza = ((zzqz) zzb(context)).zza(zzn.zza(context), zzn.zza(frameLayout), zzn.zza(frameLayout2), 12210000);
            if (iBinderZza == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZza.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
            return iInterfaceQueryLocalInterface instanceof zzqw ? (zzqw) iInterfaceQueryLocalInterface : new zzqy(iBinderZza);
        } catch (RemoteException | zzq e) {
            zzaky.zzc("Could not create remote NativeAdViewDelegate.", e);
            return null;
        }
    }

    protected final /* synthetic */ Object zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
        return iInterfaceQueryLocalInterface instanceof zzqz ? (zzqz) iInterfaceQueryLocalInterface : new zzra(iBinder);
    }
}
