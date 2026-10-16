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
public final class zzki extends zzp<zzlr> {
    public zzki() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public final zzlo zza(Context context, String str, zzwf zzwfVar) {
        try {
            IBinder iBinderZza = ((zzlr) zzb(context)).zza(zzn.zza(context), str, zzwfVar, 12210000);
            if (iBinderZza == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return iInterfaceQueryLocalInterface instanceof zzlo ? (zzlo) iInterfaceQueryLocalInterface : new zzlq(iBinderZza);
        } catch (zzq | RemoteException e) {
            zzaky.zzc("Could not create remote builder for AdLoader.", e);
            return null;
        }
    }

    protected final /* synthetic */ Object zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        return iInterfaceQueryLocalInterface instanceof zzlr ? (zzlr) iInterfaceQueryLocalInterface : new zzls(iBinder);
    }
}
