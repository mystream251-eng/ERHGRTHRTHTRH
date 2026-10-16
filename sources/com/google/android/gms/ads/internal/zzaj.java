package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzll;
import com.google.android.gms.internal.zzlp;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrv;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzsb;
import com.google.android.gms.internal.zzse;
import com.google.android.gms.internal.zzsh;
import com.google.android.gms.internal.zzwf;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaj extends zzlp {
    private zzli zza;
    private zzrs zzb;
    private zzsh zzc;
    private zzrv zzd;
    private zzse zzg;
    private zzko zzh;
    private PublisherAdViewOptions zzi;
    private zzqh zzj;
    private zzme zzk;
    private final Context zzl;
    private final zzwf zzm;
    private final String zzn;
    private final zzala zzo;
    private final zzv zzp;
    private SimpleArrayMap<String, zzsb> zzf = new SimpleArrayMap<>();
    private SimpleArrayMap<String, zzry> zze = new SimpleArrayMap<>();

    public zzaj(Context context, String str, zzwf zzwfVar, zzala zzalaVar, zzv zzvVar) {
        this.zzl = context;
        this.zzn = str;
        this.zzm = zzwfVar;
        this.zzo = zzalaVar;
        this.zzp = zzvVar;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final zzll zza() {
        return new zzag(this.zzl, this.zzn, this.zzm, this.zzo, this.zza, this.zzb, this.zzc, this.zzd, this.zzf, this.zze, this.zzj, this.zzk, this.zzp, this.zzg, this.zzh, this.zzi);
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(PublisherAdViewOptions publisherAdViewOptions) {
        this.zzi = publisherAdViewOptions;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzli zzliVar) {
        this.zza = zzliVar;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzme zzmeVar) {
        this.zzk = zzmeVar;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzqh zzqhVar) {
        this.zzj = zzqhVar;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzrs zzrsVar) {
        this.zzb = zzrsVar;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzrv zzrvVar) {
        this.zzd = zzrvVar;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzse zzseVar, zzko zzkoVar) {
        this.zzg = zzseVar;
        this.zzh = zzkoVar;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(zzsh zzshVar) {
        this.zzc = zzshVar;
    }

    @Override // com.google.android.gms.internal.zzlo
    public final void zza(String str, zzsb zzsbVar, zzry zzryVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzf.put(str, zzsbVar);
        this.zze.put(str, zzryVar);
    }
}
