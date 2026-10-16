package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpk extends zzrh implements zzpy {
    private String zza;
    private List<zzpj> zzb;
    private String zzc;
    private zzqs zzd;
    private String zze;
    private double zzf;
    private String zzg;
    private String zzh;
    private zzph zzi;
    private Bundle zzj;
    private zzmm zzk;
    private View zzl;
    private IObjectWrapper zzm;
    private String zzn;
    private Object zzo = new Object();
    private zzpv zzp;

    public zzpk(String str, List<zzpj> list, String str2, zzqs zzqsVar, String str3, double d, String str4, String str5, zzph zzphVar, Bundle bundle, zzmm zzmmVar, View view, IObjectWrapper iObjectWrapper, String str6) {
        this.zza = str;
        this.zzb = list;
        this.zzc = str2;
        this.zzd = zzqsVar;
        this.zze = str3;
        this.zzf = d;
        this.zzg = str4;
        this.zzh = str5;
        this.zzi = zzphVar;
        this.zzj = bundle;
        this.zzk = zzmmVar;
        this.zzl = view;
        this.zzm = iObjectWrapper;
        this.zzn = str6;
    }

    static /* synthetic */ zzpv zza(zzpk zzpkVar, zzpv zzpvVar) {
        zzpkVar.zzp = null;
        return null;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final void zza(Bundle bundle) {
        synchronized (this.zzo) {
            if (this.zzp == null) {
                zzahw.zzc("Attempt to perform click before app install ad initialized.");
            } else {
                this.zzp.zzb(bundle);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzpx
    public final void zza(zzpv zzpvVar) {
        synchronized (this.zzo) {
            this.zzp = zzpvVar;
        }
    }

    @Override // com.google.android.gms.internal.zzrg, com.google.android.gms.internal.zzpy
    public final List zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final boolean zzb(Bundle bundle) {
        synchronized (this.zzo) {
            if (this.zzp == null) {
                zzahw.zzc("Attempt to record impression before app install ad initialized.");
                return false;
            }
            return this.zzp.zza(bundle);
        }
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final void zzc(Bundle bundle) {
        synchronized (this.zzo) {
            if (this.zzp == null) {
                zzahw.zzc("Attempt to perform click before app install ad initialized.");
            } else {
                this.zzp.zzc(bundle);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzrg
    public final zzqs zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final double zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zzg() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zzh() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final zzmm zzi() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final IObjectWrapper zzj() {
        return zzn.zza(this.zzp);
    }

    @Override // com.google.android.gms.internal.zzpx
    public final String zzk() {
        return ExifInterface.GPS_MEASUREMENT_2D;
    }

    @Override // com.google.android.gms.internal.zzpx
    public final String zzl() {
        return "";
    }

    @Override // com.google.android.gms.internal.zzpx
    public final zzph zzm() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final Bundle zzn() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.zzpx
    public final View zzo() {
        return this.zzl;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final IObjectWrapper zzp() {
        return this.zzm;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final String zzq() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final zzqo zzr() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.zzrg
    public final void zzs() {
        zzaij.zza.post(new zzpl(this));
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzf = 0.0d;
        this.zzg = null;
        this.zzh = null;
        this.zzi = null;
        this.zzj = null;
        this.zzo = null;
        this.zzk = null;
        this.zzl = null;
    }
}
