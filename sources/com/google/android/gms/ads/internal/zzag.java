package com.google.android.gms.ads.internal;

import android.content.Context;
import androidx.collection.SimpleArrayMap;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrv;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzsb;
import com.google.android.gms.internal.zzse;
import com.google.android.gms.internal.zzsh;
import com.google.android.gms.internal.zzwf;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzag extends zzlm {
    private final Context zza;
    private final zzli zzb;
    private final zzwf zzc;
    private final zzrs zzd;
    private final zzsh zze;
    private final zzrv zzf;
    private final zzse zzg;
    private final zzko zzh;
    private final PublisherAdViewOptions zzi;
    private final SimpleArrayMap<String, zzsb> zzj;
    private final SimpleArrayMap<String, zzry> zzk;
    private final zzqh zzl;
    private final zzme zzn;
    private final String zzo;
    private final zzala zzp;
    private WeakReference<zzd> zzq;
    private final zzv zzr;
    private final Object zzs = new Object();
    private final List<String> zzm = zzf();

    zzag(Context context, String str, zzwf zzwfVar, zzala zzalaVar, zzli zzliVar, zzrs zzrsVar, zzsh zzshVar, zzrv zzrvVar, SimpleArrayMap<String, zzsb> simpleArrayMap, SimpleArrayMap<String, zzry> simpleArrayMap2, zzqh zzqhVar, zzme zzmeVar, zzv zzvVar, zzse zzseVar, zzko zzkoVar, PublisherAdViewOptions publisherAdViewOptions) {
        this.zza = context;
        this.zzo = str;
        this.zzc = zzwfVar;
        this.zzp = zzalaVar;
        this.zzb = zzliVar;
        this.zzf = zzrvVar;
        this.zzd = zzrsVar;
        this.zze = zzshVar;
        this.zzj = simpleArrayMap;
        this.zzk = simpleArrayMap2;
        this.zzl = zzqhVar;
        this.zzn = zzmeVar;
        this.zzr = zzvVar;
        this.zzg = zzseVar;
        this.zzh = zzkoVar;
        this.zzi = publisherAdViewOptions;
        zzoi.zza(this.zza);
    }

    private static void zza(Runnable runnable) {
        zzaij.zza.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzkk zzkkVar) {
        zzq zzqVar = new zzq(this.zza, this.zzr, this.zzh, this.zzo, this.zzc, this.zzp);
        this.zzq = new WeakReference<>(zzqVar);
        zzse zzseVar = this.zzg;
        com.google.android.gms.common.internal.zzbq.zzb("setOnPublisherAdViewLoadedListener must be called on the main UI thread.");
        zzqVar.zze.zzy = zzseVar;
        PublisherAdViewOptions publisherAdViewOptions = this.zzi;
        if (publisherAdViewOptions != null) {
            if (publisherAdViewOptions.zza() != null) {
                zzqVar.zza(this.zzi.zza());
            }
            zzqVar.zza(this.zzi.getManualImpressionsEnabled());
        }
        zzrs zzrsVar = this.zzd;
        com.google.android.gms.common.internal.zzbq.zzb("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        zzqVar.zze.zzq = zzrsVar;
        zzrv zzrvVar = this.zzf;
        com.google.android.gms.common.internal.zzbq.zzb("setOnContentAdLoadedListener must be called on the main UI thread.");
        zzqVar.zze.zzr = zzrvVar;
        SimpleArrayMap<String, zzsb> simpleArrayMap = this.zzj;
        com.google.android.gms.common.internal.zzbq.zzb("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        zzqVar.zze.zzu = simpleArrayMap;
        SimpleArrayMap<String, zzry> simpleArrayMap2 = this.zzk;
        com.google.android.gms.common.internal.zzbq.zzb("setOnCustomClickListener must be called on the main UI thread.");
        zzqVar.zze.zzt = simpleArrayMap2;
        zzqh zzqhVar = this.zzl;
        com.google.android.gms.common.internal.zzbq.zzb("setNativeAdOptions must be called on the main UI thread.");
        zzqVar.zze.zzv = zzqhVar;
        zzqVar.zzb(zzf());
        zzqVar.zza(this.zzb);
        zzqVar.zza(this.zzn);
        ArrayList arrayList = new ArrayList();
        if (zze()) {
            arrayList.add(1);
        }
        if (this.zzg != null) {
            arrayList.add(2);
        }
        zzqVar.zzc(arrayList);
        if (zze()) {
            zzkkVar.zzc.putBoolean("ina", true);
        }
        if (this.zzg != null) {
            zzkkVar.zzc.putBoolean("iba", true);
        }
        zzqVar.zzb(zzkkVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzkk zzkkVar, int i) {
        Context context = this.zza;
        zzbb zzbbVar = new zzbb(context, this.zzr, zzko.zza(context), this.zzo, this.zzc, this.zzp);
        this.zzq = new WeakReference<>(zzbbVar);
        zzrs zzrsVar = this.zzd;
        com.google.android.gms.common.internal.zzbq.zzb("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        zzbbVar.zze.zzq = zzrsVar;
        zzsh zzshVar = this.zze;
        com.google.android.gms.common.internal.zzbq.zzb("setOnUnifiedNativeAdLoadedListener must be called on the main UI thread.");
        zzbbVar.zze.zzs = zzshVar;
        zzrv zzrvVar = this.zzf;
        com.google.android.gms.common.internal.zzbq.zzb("setOnContentAdLoadedListener must be called on the main UI thread.");
        zzbbVar.zze.zzr = zzrvVar;
        SimpleArrayMap<String, zzsb> simpleArrayMap = this.zzj;
        com.google.android.gms.common.internal.zzbq.zzb("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        zzbbVar.zze.zzu = simpleArrayMap;
        zzbbVar.zza(this.zzb);
        SimpleArrayMap<String, zzry> simpleArrayMap2 = this.zzk;
        com.google.android.gms.common.internal.zzbq.zzb("setOnCustomClickListener must be called on the main UI thread.");
        zzbbVar.zze.zzt = simpleArrayMap2;
        zzbbVar.zzb(zzf());
        zzqh zzqhVar = this.zzl;
        com.google.android.gms.common.internal.zzbq.zzb("setNativeAdOptions must be called on the main UI thread.");
        zzbbVar.zze.zzv = zzqhVar;
        zzbbVar.zza(this.zzn);
        zzbbVar.zzb(i);
        zzbbVar.zzb(zzkkVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzd() {
        return ((Boolean) zzlc.zzf().zza(zzoi.zzcf)).booleanValue() && this.zzg != null;
    }

    private final boolean zze() {
        if (this.zzd != null || this.zzf != null || this.zze != null) {
            return true;
        }
        SimpleArrayMap<String, zzsb> simpleArrayMap = this.zzj;
        return simpleArrayMap != null && simpleArrayMap.size() > 0;
    }

    private final List<String> zzf() {
        ArrayList arrayList = new ArrayList();
        if (this.zzf != null) {
            arrayList.add(LocalSearchProvider.LIVE_CONTENTS);
        }
        if (this.zzd != null) {
            arrayList.add(ExifInterface.GPS_MEASUREMENT_2D);
        }
        if (this.zze != null) {
            arrayList.add("6");
        }
        if (this.zzj.size() > 0) {
            arrayList.add(ExifInterface.GPS_MEASUREMENT_3D);
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.zzll
    public final String zza() {
        synchronized (this.zzs) {
            if (this.zzq == null) {
                return null;
            }
            zzd zzdVar = this.zzq.get();
            return zzdVar != null ? zzdVar.zzal() : null;
        }
    }

    @Override // com.google.android.gms.internal.zzll
    public final void zza(zzkk zzkkVar) {
        zza(new zzah(this, zzkkVar));
    }

    @Override // com.google.android.gms.internal.zzll
    public final void zza(zzkk zzkkVar, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Number of ads has to be more than 0");
        }
        zza(new zzai(this, zzkkVar, i));
    }

    @Override // com.google.android.gms.internal.zzll
    public final String zzb() {
        synchronized (this.zzs) {
            if (this.zzq == null) {
                return null;
            }
            zzd zzdVar = this.zzq.get();
            return zzdVar != null ? zzdVar.zzam() : null;
        }
    }

    @Override // com.google.android.gms.internal.zzll
    public final boolean zzc() {
        synchronized (this.zzs) {
            if (this.zzq == null) {
                return false;
            }
            zzd zzdVar = this.zzq.get();
            return zzdVar != null ? zzdVar.zzk() : false;
        }
    }
}
