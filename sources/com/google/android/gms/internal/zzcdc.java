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
public final class zzcdc extends zzd<zzcdh> {
    public zzcdc(Context context, Looper looper, zzf zzfVar, zzg zzgVar) {
        super(context, looper, 116, zzfVar, zzgVar, null);
    }

    protected final /* synthetic */ IInterface zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.gass.internal.IGassService");
        return iInterfaceQueryLocalInterface instanceof zzcdh ? (zzcdh) iInterfaceQueryLocalInterface : new zzcdi(iBinder);
    }

    protected final String zza() {
        return "com.google.android.gms.gass.START";
    }

    protected final String zzb() {
        return "com.google.android.gms.gass.internal.IGassService";
    }

    public final zzcdh zzi() throws DeadObjectException {
        return (zzcdh) super.zzaf();
    }
}
