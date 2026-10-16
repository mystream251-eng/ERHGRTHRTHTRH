package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzqv extends NativeAd.Image {
    private final zzqs zza;
    private final Drawable zzb;
    private final Uri zzc;
    private final double zzd;

    public zzqv(zzqs zzqsVar) {
        Drawable drawable;
        this.zza = zzqsVar;
        Uri uriZzb = null;
        try {
            IObjectWrapper iObjectWrapperZza = zzqsVar.zza();
            drawable = iObjectWrapperZza != null ? (Drawable) zzn.zza(iObjectWrapperZza) : null;
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get drawable.", e);
        }
        this.zzb = drawable;
        try {
            uriZzb = this.zza.zzb();
        } catch (RemoteException e2) {
            zzaky.zzb("Failed to get uri.", e2);
        }
        this.zzc = uriZzb;
        double dZzc = 1.0d;
        try {
            dZzc = this.zza.zzc();
        } catch (RemoteException e3) {
            zzaky.zzb("Failed to get scale.", e3);
        }
        this.zzd = dZzc;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public final Drawable getDrawable() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public final double getScale() {
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public final Uri getUri() {
        return this.zzc;
    }
}
