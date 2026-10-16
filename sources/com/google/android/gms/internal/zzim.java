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
public final class zzim extends zzd<zziq> {
    zzim(Context context, Looper looper, zzf zzfVar, zzg zzgVar) {
        super(context, looper, 123, zzfVar, zzgVar, null);
    }

    public final zziq c_() throws DeadObjectException {
        return (zziq) super.zzaf();
    }

    protected final /* synthetic */ IInterface zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.cache.ICacheService");
        return iInterfaceQueryLocalInterface instanceof zziq ? (zziq) iInterfaceQueryLocalInterface : new zzir(iBinder);
    }

    protected final String zza() {
        return "com.google.android.gms.ads.service.CACHE";
    }

    protected final String zzb() {
        return "com.google.android.gms.ads.internal.cache.ICacheService";
    }
}
