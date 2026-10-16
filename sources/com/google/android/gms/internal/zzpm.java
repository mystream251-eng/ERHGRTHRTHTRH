package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpm extends zzrl implements zzpy {
    private String zza;
    private List<zzpj> zzb;
    private String zzc;
    private zzqs zzd;
    private String zze;
    private String zzf;
    private zzph zzg;
    private Bundle zzh;
    private zzmm zzi;
    private View zzj;
    private IObjectWrapper zzk;
    private String zzl;
    private Object zzm = new Object();
    private zzpv zzn;

    public zzpm(String str, List<zzpj> list, String str2, zzqs zzqsVar, String str3, String str4, zzph zzphVar, Bundle bundle, zzmm zzmmVar, View view, IObjectWrapper iObjectWrapper, String str5) {
        this.zza = str;
        this.zzb = list;
        this.zzc = str2;
        this.zzd = zzqsVar;
        this.zze = str3;
        this.zzf = str4;
        this.zzg = zzphVar;
        this.zzh = bundle;
        this.zzi = zzmmVar;
        this.zzj = view;
        this.zzk = iObjectWrapper;
        this.zzl = str5;
    }

    static /* synthetic */ zzpv zza(zzpm zzpmVar, zzpv zzpvVar) {
        zzpmVar.zzn = null;
        return null;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final void zza(Bundle bundle) {
        synchronized (this.zzm) {
            if (this.zzn == null) {
                zzahw.zzc("Attempt to perform click before content ad initialized.");
            } else {
                this.zzn.zzb(bundle);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzpx
    public final void zza(zzpv zzpvVar) {
        synchronized (this.zzm) {
            this.zzn = zzpvVar;
        }
    }

    @Override // com.google.android.gms.internal.zzrk, com.google.android.gms.internal.zzpy
    public final List zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final boolean zzb(Bundle bundle) {
        synchronized (this.zzm) {
            if (this.zzn == null) {
                zzahw.zzc("Attempt to record impression before content ad initialized.");
                return false;
            }
            return this.zzn.zza(bundle);
        }
    }

    @Override // com.google.android.gms.internal.zzrk
    public final IObjectWrapper zzc() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final void zzc(Bundle bundle) {
        synchronized (this.zzm) {
            if (this.zzn == null) {
                zzahw.zzc("Attempt to perform click before app install ad initialized.");
            } else {
                this.zzn.zzc(bundle);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzrk
    public final String zzd() {
        return this.zzl;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final String zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final zzqs zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final String zzg() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final String zzh() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final zzmm zzi() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final IObjectWrapper zzj() {
        return zzn.zza(this.zzn);
    }

    @Override // com.google.android.gms.internal.zzpx
    public final String zzk() {
        return LocalSearchProvider.LIVE_CONTENTS;
    }

    @Override // com.google.android.gms.internal.zzpx
    public final String zzl() {
        return "";
    }

    @Override // com.google.android.gms.internal.zzpx
    public final zzph zzm() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final Bundle zzn() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.zzpx
    public final View zzo() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final zzqo zzp() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.zzrk
    public final void zzq() {
        zzaij.zza.post(new zzpn(this));
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzf = null;
        this.zzg = null;
        this.zzh = null;
        this.zzm = null;
        this.zzi = null;
        this.zzj = null;
    }
}
