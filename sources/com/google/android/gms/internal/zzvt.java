package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvt implements zzvx {
    private final String zza;
    private final zzwf zzb;
    private final long zzc;
    private final zzvq zzd;
    private final zzvp zze;
    private zzkk zzf;
    private final zzko zzg;
    private final Context zzh;
    private final zzala zzj;
    private final boolean zzk;
    private final zzqh zzl;
    private final List<String> zzm;
    private final List<String> zzn;
    private final List<String> zzo;
    private final boolean zzp;
    private final boolean zzq;
    private zzwi zzr;
    private zzwo zzt;
    private final Object zzi = new Object();
    private int zzs = -2;

    public zzvt(Context context, String str, zzwf zzwfVar, zzvq zzvqVar, zzvp zzvpVar, zzkk zzkkVar, zzko zzkoVar, zzala zzalaVar, boolean z, boolean z2, zzqh zzqhVar, List<String> list, List<String> list2, List<String> list3, boolean z3) {
        String str2 = str;
        this.zzh = context;
        this.zzb = zzwfVar;
        this.zze = zzvpVar;
        this.zza = "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str) ? zzb() : str2;
        this.zzd = zzvqVar;
        this.zzc = zzvpVar.zzs != -1 ? zzvpVar.zzs : zzvqVar.zzb != -1 ? zzvqVar.zzb : 10000L;
        this.zzf = zzkkVar;
        this.zzg = zzkoVar;
        this.zzj = zzalaVar;
        this.zzk = z;
        this.zzp = z2;
        this.zzl = zzqhVar;
        this.zzm = list;
        this.zzn = list2;
        this.zzo = list3;
        this.zzq = z3;
    }

    private static zzwi zza(MediationAdapter mediationAdapter) {
        return new zzxc(mediationAdapter);
    }

    private final String zza(String str) {
        if (str != null && zze() && !zzb(2)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.remove("cpm_floor_cents");
                return jSONObject.toString();
            } catch (JSONException unused) {
                zzahw.zze("Could not remove field. Returning the original value");
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzvs zzvsVar) {
        String strZza = zza(this.zze.zzj);
        try {
            if (this.zzj.zzc < 4100000) {
                if (this.zzg.zzd) {
                    this.zzr.zza(zzn.zza(this.zzh), this.zzf, strZza, zzvsVar);
                    return;
                } else {
                    this.zzr.zza(zzn.zza(this.zzh), this.zzg, this.zzf, strZza, zzvsVar);
                    return;
                }
            }
            if (!this.zzk && !this.zze.zzb()) {
                if (this.zzg.zzd) {
                    this.zzr.zza(zzn.zza(this.zzh), this.zzf, strZza, this.zze.zza, zzvsVar);
                    return;
                }
                if (!this.zzp) {
                    this.zzr.zza(zzn.zza(this.zzh), this.zzg, this.zzf, strZza, this.zze.zza, zzvsVar);
                    return;
                } else if (this.zze.zzn != null) {
                    this.zzr.zza(zzn.zza(this.zzh), this.zzf, strZza, this.zze.zza, zzvsVar, new zzqh(zzb(this.zze.zzr)), this.zze.zzq);
                    return;
                } else {
                    this.zzr.zza(zzn.zza(this.zzh), this.zzg, this.zzf, strZza, this.zze.zza, zzvsVar);
                    return;
                }
            }
            ArrayList arrayList = new ArrayList(this.zzm);
            if (this.zzn != null) {
                for (String str : this.zzn) {
                    String str2 = ":false";
                    if (this.zzo != null && this.zzo.contains(str)) {
                        str2 = ":true";
                    }
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 7 + str2.length());
                    sb.append("custom:");
                    sb.append(str);
                    sb.append(str2);
                    arrayList.add(sb.toString());
                }
            }
            this.zzr.zza(zzn.zza(this.zzh), this.zzf, strZza, this.zze.zza, zzvsVar, this.zzl, arrayList);
        } catch (RemoteException e) {
            zzahw.zzc("Could not request ad from mediation adapter.", e);
            zza(5);
        }
    }

    private static NativeAdOptions zzb(String str) {
        NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = 0;
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            String strOptString = jSONObject.optString("native_image_orientation", "any");
            if ("landscape".equals(strOptString)) {
                i = 2;
            } else if ("portrait".equals(strOptString)) {
                i = 1;
            } else if (!"any".equals(strOptString)) {
                i = -1;
            }
            builder.setImageOrientation(i);
        } catch (JSONException e) {
            zzahw.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private final String zzb() {
        try {
            return (TextUtils.isEmpty(this.zze.zze) || !this.zzb.zzb(this.zze.zze)) ? "com.google.ads.mediation.customevent.CustomEventAdapter" : "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        } catch (RemoteException unused) {
            zzahw.zze("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzb(int i) {
        Bundle bundleZzk;
        try {
            if (this.zzk) {
                bundleZzk = this.zzr.zzl();
            } else {
                bundleZzk = this.zzg.zzd ? this.zzr.zzk() : this.zzr.zzj();
            }
            return bundleZzk != null && (bundleZzk.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException unused) {
            zzahw.zze("Could not get adapter info. Returning false");
            return false;
        }
    }

    private final zzwo zzc() {
        if (this.zzs != 0 || !zze()) {
            return null;
        }
        try {
            if (zzb(4) && this.zzt != null && this.zzt.zza() != 0) {
                return this.zzt;
            }
        } catch (RemoteException unused) {
            zzahw.zze("Could not get cpm value from MediationResponseMetadata");
        }
        return new zzvv(zzf());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zzwi zzd() {
        String strValueOf = String.valueOf(this.zza);
        zzahw.zzd(strValueOf.length() != 0 ? "Instantiating mediation adapter: ".concat(strValueOf) : new String("Instantiating mediation adapter: "));
        if (!this.zzk && !this.zze.zzb()) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzdo)).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zza)) {
                return zza(new AdMobAdapter());
            }
            if (((Boolean) zzlc.zzf().zza(zzoi.zzdp)).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zza)) {
                return zza(new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zza)) {
                return new zzxc(new zzxx());
            }
        }
        try {
            return this.zzb.zza(this.zza);
        } catch (RemoteException e) {
            String strValueOf2 = String.valueOf(this.zza);
            zzahw.zza(strValueOf2.length() != 0 ? "Could not instantiate mediation adapter: ".concat(strValueOf2) : new String("Could not instantiate mediation adapter: "), e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zze() {
        return this.zzd.zzl != -1;
    }

    private final int zzf() {
        if (this.zze.zzj == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zze.zzj);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zza)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int iOptInt = zzb(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return iOptInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : iOptInt;
        } catch (JSONException unused) {
            zzahw.zze("Could not convert to json. Returning 0");
            return 0;
        }
    }

    public final zzvw zza(long j, long j2) {
        zzvw zzvwVar;
        synchronized (this.zzi) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            zzvs zzvsVar = new zzvs();
            zzaij.zza.post(new zzvu(this, zzvsVar));
            long j3 = this.zzc;
            while (this.zzs == -2) {
                long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                long j4 = j3 - (jElapsedRealtime2 - jElapsedRealtime);
                long j5 = j2 - (jElapsedRealtime2 - j);
                if (j4 <= 0 || j5 <= 0) {
                    zzahw.zzd("Timed out waiting for adapter.");
                    this.zzs = 3;
                } else {
                    try {
                        this.zzi.wait(Math.min(j4, j5));
                    } catch (InterruptedException unused) {
                        this.zzs = 5;
                    }
                }
            }
            zzvwVar = new zzvw(this.zze, this.zzr, this.zza, zzvsVar, this.zzs, zzc(), zzbt.zzl().zzb() - jElapsedRealtime);
        }
        return zzvwVar;
    }

    public final void zza() {
        synchronized (this.zzi) {
            try {
                if (this.zzr != null) {
                    this.zzr.zzc();
                }
            } catch (RemoteException e) {
                zzahw.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzs = -1;
            this.zzi.notify();
        }
    }

    @Override // com.google.android.gms.internal.zzvx
    public final void zza(int i) {
        synchronized (this.zzi) {
            this.zzs = i;
            this.zzi.notify();
        }
    }

    @Override // com.google.android.gms.internal.zzvx
    public final void zza(int i, zzwo zzwoVar) {
        synchronized (this.zzi) {
            this.zzs = 0;
            this.zzt = zzwoVar;
            this.zzi.notify();
        }
    }
}
