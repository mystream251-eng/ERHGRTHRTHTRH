package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class zzahb implements Runnable {
    private /* synthetic */ Context zza;
    private /* synthetic */ zzamd zzb;

    zzahb(zzaha zzahaVar, Context context, zzamd zzamdVar) {
        this.zza = context;
        this.zzb = zzamdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzb.zzb(AdvertisingIdClient.getAdvertisingIdInfo(this.zza));
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            this.zzb.zza(e);
            zzaky.zzb("Exception while getting advertising Id info", e);
        }
    }
}
