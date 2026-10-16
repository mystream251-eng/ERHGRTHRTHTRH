package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzyp extends zzp<zzyt> {
    public zzyp() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public final zzyq zza(Activity activity) {
        try {
            IBinder iBinderZza = ((zzyt) zzb(activity)).zza(zzn.zza(activity));
            if (iBinderZza == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZza.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            return iInterfaceQueryLocalInterface instanceof zzyq ? (zzyq) iInterfaceQueryLocalInterface : new zzys(iBinderZza);
        } catch (RemoteException e) {
            zzaky.zzc("Could not create remote AdOverlay.", e);
            return null;
        } catch (zzq e2) {
            zzaky.zzc("Could not create remote AdOverlay.", e2);
            return null;
        }
    }

    protected final /* synthetic */ Object zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
        return iInterfaceQueryLocalInterface instanceof zzyt ? (zzyt) iInterfaceQueryLocalInterface : new zzyu(iBinder);
    }
}
