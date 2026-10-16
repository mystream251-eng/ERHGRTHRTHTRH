package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.internal.zzbt;
import io.sentry.protocol.App;
import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzok {
    private boolean zza = ((Boolean) zzlc.zzf().zza(zzoi.zzaj)).booleanValue();
    private String zzb = (String) zzlc.zzf().zza(zzoi.zzak);
    private Map<String, String> zzc;
    private Context zzd;
    private String zze;

    public zzok(Context context, String str) {
        this.zzd = null;
        this.zze = null;
        this.zzd = context;
        this.zze = str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zzc = linkedHashMap;
        linkedHashMap.put("s", "gmob_sdk");
        this.zzc.put("v", ExifInterface.GPS_MEASUREMENT_3D);
        this.zzc.put(OperatingSystem.TYPE, Build.VERSION.RELEASE);
        this.zzc.put("sdk", Build.VERSION.SDK);
        Map<String, String> map = this.zzc;
        zzbt.zze();
        map.put(Device.TYPE, zzaij.zzb());
        this.zzc.put(App.TYPE, context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        Map<String, String> map2 = this.zzc;
        zzbt.zze();
        map2.put("is_lite_sdk", zzaij.zzk(context) ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS);
        Future<zzaea> futureZza = zzbt.zzp().zza(this.zzd);
        try {
            futureZza.get();
            this.zzc.put("network_coarse", Integer.toString(futureZza.get().zzn));
            this.zzc.put("network_fine", Integer.toString(futureZza.get().zzo));
        } catch (Exception e) {
            zzbt.zzi().zza(e, "CsiConfiguration.CsiConfiguration");
        }
    }

    final boolean zza() {
        return this.zza;
    }

    final String zzb() {
        return this.zzb;
    }

    final Context zzc() {
        return this.zzd;
    }

    final String zzd() {
        return this.zze;
    }

    final Map<String, String> zze() {
        return this.zzc;
    }
}
