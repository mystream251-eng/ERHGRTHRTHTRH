package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzkj extends zzp<zzlw> {
    public zzkj() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public final zzlt zza(Context context, zzko zzkoVar, String str, zzwf zzwfVar, int i) {
        try {
            IBinder iBinderZza = ((zzlw) zzb(context)).zza(zzn.zza(context), zzkoVar, str, zzwfVar, 12210000, i);
            if (iBinderZza == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            return iInterfaceQueryLocalInterface instanceof zzlt ? (zzlt) iInterfaceQueryLocalInterface : new zzlv(iBinderZza);
        } catch (zzq | RemoteException e) {
            zzaky.zza("Could not create remote AdManager.", e);
            return null;
        }
    }

    protected final /* synthetic */ Object zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return iInterfaceQueryLocalInterface instanceof zzlw ? (zzlw) iInterfaceQueryLocalInterface : new zzlx(iBinder);
    }
}
