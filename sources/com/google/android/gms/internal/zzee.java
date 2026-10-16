package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzee extends zzeu {
    public zzee(zzdm zzdmVar, String str, String str2, zzba zzbaVar, int i, int i2) {
        super(zzdmVar, str, str2, zzbaVar, i, 24);
    }

    private final void zzc() {
        AdvertisingIdClient advertisingIdClientZzm = this.zza.zzm();
        if (advertisingIdClientZzm == null) {
            return;
        }
        try {
            AdvertisingIdClient.Info info = advertisingIdClientZzm.getInfo();
            String strZza = zzds.zza(info.getId());
            if (strZza != null) {
                synchronized (this.zzb) {
                    this.zzb.zzas = strZza;
                    this.zzb.zzau = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                    this.zzb.zzat = 5;
                }
            }
        } catch (IOException unused) {
        }
    }

    @Override // com.google.android.gms.internal.zzeu, java.util.concurrent.Callable
    public final /* synthetic */ Object call() throws Exception {
        return call();
    }

    @Override // com.google.android.gms.internal.zzeu
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zza.zzg()) {
            zzc();
            return;
        }
        synchronized (this.zzb) {
            this.zzb.zzas = (String) this.zzc.invoke(null, this.zza.zza());
        }
    }

    @Override // com.google.android.gms.internal.zzeu
    /* JADX INFO: renamed from: zzb */
    public final Void call() throws Exception {
        if (this.zza.zzb()) {
            return super.call();
        }
        if (!this.zza.zzg()) {
            return null;
        }
        zzc();
        return null;
    }
}
