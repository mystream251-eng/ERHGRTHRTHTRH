package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmv {
    private Date zzg;
    private String zzh;
    private Location zzj;
    private String zzl;
    private String zzm;
    private boolean zzo;
    private final HashSet<String> zza = new HashSet<>();
    private final Bundle zzb = new Bundle();
    private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzc = new HashMap<>();
    private final HashSet<String> zzd = new HashSet<>();
    private final Bundle zze = new Bundle();
    private final HashSet<String> zzf = new HashSet<>();
    private int zzi = -1;
    private boolean zzk = false;
    private int zzn = -1;

    public final void zza(int i) {
        this.zzi = i;
    }

    public final void zza(Location location) {
        this.zzj = location;
    }

    @Deprecated
    public final void zza(NetworkExtras networkExtras) {
        if (networkExtras instanceof AdMobExtras) {
            zza(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
        } else {
            this.zzc.put((Class<? extends NetworkExtras>) networkExtras.getClass(), networkExtras);
        }
    }

    public final void zza(Class<? extends MediationAdapter> cls, Bundle bundle) {
        this.zzb.putBundle(cls.getName(), bundle);
    }

    public final void zza(String str) {
        this.zza.add(str);
    }

    public final void zza(String str, String str2) {
        this.zze.putString(str, str2);
    }

    public final void zza(Date date) {
        this.zzg = date;
    }

    public final void zza(boolean z) {
        this.zzk = z;
    }

    public final void zzb(Class<? extends CustomEvent> cls, Bundle bundle) {
        if (this.zzb.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.zzb.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        this.zzb.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
    }

    public final void zzb(String str) {
        this.zzd.add(str);
    }

    public final void zzb(boolean z) {
        this.zzn = z ? 1 : 0;
    }

    public final void zzc(String str) {
        this.zzd.remove(str);
    }

    public final void zzc(boolean z) {
        this.zzo = z;
    }

    public final void zzd(String str) {
        this.zzh = str;
    }

    public final void zze(String str) {
        this.zzl = str;
    }

    public final void zzf(String str) {
        this.zzm = str;
    }

    public final void zzg(String str) {
        this.zzf.add(str);
    }
}
