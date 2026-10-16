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
public final class zzace extends zzd<zzacn> {
    private int zzd;

    public zzace(Context context, Looper looper, zzf zzfVar, zzg zzgVar, int i) {
        super(context.getApplicationContext() != null ? context.getApplicationContext() : context, looper, 8, zzfVar, zzgVar, null);
        this.zzd = i;
    }

    public final zzacn e_() throws DeadObjectException {
        return (zzacn) super.zzaf();
    }

    protected final /* synthetic */ IInterface zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        return iInterfaceQueryLocalInterface instanceof zzacn ? (zzacn) iInterfaceQueryLocalInterface : new zzacp(iBinder);
    }

    protected final String zza() {
        return "com.google.android.gms.ads.service.START";
    }

    protected final String zzb() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }
}
