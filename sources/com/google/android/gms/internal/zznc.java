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
public final class zznc extends zzp<zzmk> {
    public zznc() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }

    public final zzmh zza(Context context) {
        try {
            IBinder iBinderZza = ((zzmk) zzb(context)).zza(zzn.zza(context), 12210000);
            if (iBinderZza == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZza.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            return iInterfaceQueryLocalInterface instanceof zzmh ? (zzmh) iInterfaceQueryLocalInterface : new zzmj(iBinderZza);
        } catch (zzq e) {
            zzaky.zzc("Could not get remote MobileAdsSettingManager.", e);
            return null;
        } catch (RemoteException e2) {
            zzaky.zzc("Could not get remote MobileAdsSettingManager.", e2);
            return null;
        }
    }

    protected final /* synthetic */ Object zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
        return iInterfaceQueryLocalInterface instanceof zzmk ? (zzmk) iInterfaceQueryLocalInterface : new zzml(iBinder);
    }
}
