package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzsx extends zzd<zztc> {
    zzsx(Context context, Looper looper, zzf zzfVar, zzg zzgVar) {
        super(context, looper, 166, zzfVar, zzgVar, null);
    }

    public final zztc d_() throws DeadObjectException {
        return (zztc) super.zzaf();
    }

    protected final /* synthetic */ IInterface zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheService");
        return iInterfaceQueryLocalInterface instanceof zztc ? (zztc) iInterfaceQueryLocalInterface : new zztd(iBinder);
    }

    protected final String zza() {
        return "com.google.android.gms.ads.service.HTTP";
    }

    protected final String zzb() {
        return "com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheService";
    }
}
