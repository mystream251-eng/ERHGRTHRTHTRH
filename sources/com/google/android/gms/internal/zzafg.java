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
public final class zzafg extends zzp<zzafa> {
    public zzafg() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    public final zzaex zza(Context context, zzwf zzwfVar) {
        try {
            IBinder iBinderZza = ((zzafa) zzb(context)).zza(zzn.zza(context), zzwfVar, 12210000);
            if (iBinderZza == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZza.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
            return iInterfaceQueryLocalInterface instanceof zzaex ? (zzaex) iInterfaceQueryLocalInterface : new zzaez(iBinderZza);
        } catch (zzq | RemoteException e) {
            zzaky.zzc("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }

    protected final /* synthetic */ Object zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        return iInterfaceQueryLocalInterface instanceof zzafa ? (zzafa) iInterfaceQueryLocalInterface : new zzafb(iBinder);
    }
}
