package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpj extends zzqt {
    private final Drawable zza;
    private final Uri zzb;
    private final double zzc;

    public zzpj(Drawable drawable, Uri uri, double d) {
        this.zza = drawable;
        this.zzb = uri;
        this.zzc = d;
    }

    @Override // com.google.android.gms.internal.zzqs
    public final IObjectWrapper zza() throws RemoteException {
        return zzn.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.zzqs
    public final Uri zzb() throws RemoteException {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzqs
    public final double zzc() {
        return this.zzc;
    }
}
