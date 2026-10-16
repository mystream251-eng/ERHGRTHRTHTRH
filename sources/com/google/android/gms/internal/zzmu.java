package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzmu {
    private final Date zza;
    private final String zzb;
    private final int zzc;
    private final Set<String> zzd;
    private final Location zze;
    private final boolean zzf;
    private final Bundle zzg;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzh;
    private final String zzi;
    private final String zzj;
    private final SearchAdRequest zzk;
    private final int zzl;
    private final Set<String> zzm;
    private final Bundle zzn;
    private final Set<String> zzo;
    private final boolean zzp;

    public zzmu(zzmv zzmvVar) {
        this(zzmvVar, null);
    }

    public zzmu(zzmv zzmvVar, SearchAdRequest searchAdRequest) {
        this.zza = zzmvVar.zzg;
        this.zzb = zzmvVar.zzh;
        this.zzc = zzmvVar.zzi;
        this.zzd = Collections.unmodifiableSet(zzmvVar.zza);
        this.zze = zzmvVar.zzj;
        this.zzf = zzmvVar.zzk;
        this.zzg = zzmvVar.zzb;
        this.zzh = Collections.unmodifiableMap(zzmvVar.zzc);
        this.zzi = zzmvVar.zzl;
        this.zzj = zzmvVar.zzm;
        this.zzk = searchAdRequest;
        this.zzl = zzmvVar.zzn;
        this.zzm = Collections.unmodifiableSet(zzmvVar.zzd);
        this.zzn = zzmvVar.zze;
        this.zzo = Collections.unmodifiableSet(zzmvVar.zzf);
        this.zzp = zzmvVar.zzo;
    }

    @Deprecated
    public final <T extends NetworkExtras> T zza(Class<T> cls) {
        return (T) this.zzh.get(cls);
    }

    public final Date zza() {
        return this.zza;
    }

    public final boolean zza(Context context) {
        Set<String> set = this.zzm;
        zzlc.zza();
        return set.contains(zzako.zza(context));
    }

    public final Bundle zzb(Class<? extends MediationAdapter> cls) {
        return this.zzg.getBundle(cls.getName());
    }

    public final String zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zzc;
    }

    public final Bundle zzc(Class<? extends CustomEvent> cls) {
        Bundle bundle = this.zzg.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(cls.getName());
        }
        return null;
    }

    public final Set<String> zzd() {
        return this.zzd;
    }

    public final Location zze() {
        return this.zze;
    }

    public final boolean zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zzi;
    }

    public final String zzh() {
        return this.zzj;
    }

    public final SearchAdRequest zzi() {
        return this.zzk;
    }

    public final Map<Class<? extends NetworkExtras>, NetworkExtras> zzj() {
        return this.zzh;
    }

    public final Bundle zzk() {
        return this.zzg;
    }

    public final int zzl() {
        return this.zzl;
    }

    public final Bundle zzm() {
        return this.zzn;
    }

    public final Set<String> zzn() {
        return this.zzo;
    }

    public final boolean zzo() {
        return this.zzp;
    }
}
