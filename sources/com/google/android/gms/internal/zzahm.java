package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahm {
    private String zzg;
    private long zzb = -1;
    private long zzc = -1;
    private int zzd = -1;
    int zza = -1;
    private long zze = 0;
    private final Object zzf = new Object();
    private int zzh = 0;
    private int zzi = 0;

    public zzahm(String str) {
        this.zzg = str;
    }

    private static boolean zza(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", TtmlNode.TAG_STYLE, "android");
        if (identifier == 0) {
            zzahw.zzd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzahw.zzd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            zzahw.zze("Fail to fetch AdActivity theme");
            zzahw.zzd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    public final Bundle zza(Context context, String str) {
        Bundle bundle;
        synchronized (this.zzf) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzg);
            bundle.putLong("basets", this.zzc);
            bundle.putLong("currts", this.zzb);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzd);
            bundle.putInt("preqs_in_session", this.zza);
            bundle.putLong("time_in_session", this.zze);
            bundle.putInt("pclick", this.zzh);
            bundle.putInt("pimp", this.zzi);
            bundle.putBoolean("support_transparent_background", zza(context));
        }
        return bundle;
    }

    public final void zza() {
        synchronized (this.zzf) {
            this.zzh++;
        }
    }

    public final void zza(zzkk zzkkVar, long j) {
        synchronized (this.zzf) {
            long jZzi = zzbt.zzi().zzl().zzi();
            long jZza = zzbt.zzl().zza();
            if (this.zzc == -1) {
                if (jZza - jZzi > ((Long) zzlc.zzf().zza(zzoi.zzcb)).longValue()) {
                    this.zza = -1;
                } else {
                    this.zza = zzbt.zzi().zzl().zzj();
                }
                this.zzc = j;
            }
            this.zzb = j;
            if (zzkkVar == null || zzkkVar.zzc == null || zzkkVar.zzc.getInt("gw", 2) != 1) {
                this.zzd++;
                int i = this.zza + 1;
                this.zza = i;
                if (i == 0) {
                    this.zze = 0L;
                    zzbt.zzi().zzl().zzb(jZza);
                } else {
                    this.zze = jZza - zzbt.zzi().zzl().zzk();
                }
            }
        }
    }

    public final void zzb() {
        synchronized (this.zzf) {
            this.zzi++;
        }
    }
}
