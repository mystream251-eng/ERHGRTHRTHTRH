package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpr extends zzsl implements zzpy {
    private String zza;
    private List<zzpj> zzb;
    private String zzc;
    private zzqs zzd;
    private String zze;
    private String zzf;
    private double zzg;
    private String zzh;
    private String zzi;
    private zzph zzj;
    private zzmm zzk;
    private View zzl;
    private IObjectWrapper zzm;
    private String zzn;
    private Object zzo = new Object();
    private zzpv zzp;

    public zzpr(String str, List<zzpj> list, String str2, zzqs zzqsVar, String str3, String str4, double d, String str5, String str6, zzph zzphVar, zzmm zzmmVar, View view, IObjectWrapper iObjectWrapper, String str7) {
        this.zza = str;
        this.zzb = list;
        this.zzc = str2;
        this.zzd = zzqsVar;
        this.zze = str3;
        this.zzf = str4;
        this.zzg = d;
        this.zzh = str5;
        this.zzi = str6;
        this.zzj = zzphVar;
        this.zzk = zzmmVar;
        this.zzl = view;
        this.zzm = iObjectWrapper;
        this.zzn = str7;
    }

    static /* synthetic */ zzpv zza(zzpr zzprVar, zzpv zzpvVar) {
        zzprVar.zzp = null;
        return null;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final void zza(Bundle bundle) {
        synchronized (this.zzo) {
            if (this.zzp == null) {
                zzahw.zzc("Attempt to perform click before unified native ad is initialized.");
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

    @Override // com.google.android.gms.internal.zzsk, com.google.android.gms.internal.zzpy
    public final List zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final boolean zzb(Bundle bundle) {
        synchronized (this.zzo) {
            if (this.zzp == null) {
                zzahw.zzc("Attempt to record impression before unified native ad is initialized.");
                return false;
            }
            return this.zzp.zza(bundle);
        }
    }

    @Override // com.google.android.gms.internal.zzsk
    public final String zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final void zzc(Bundle bundle) {
        synchronized (this.zzo) {
            if (this.zzp == null) {
                zzahw.zzc("Attempt to perform click before unified native ad is initialized.");
            } else {
                this.zzp.zzc(bundle);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzsk
    public final zzqs zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final String zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final String zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final double zzg() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final String zzh() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final String zzi() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final zzmm zzj() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.zzpx
    public final String zzk() {
        return "6";
    }

    @Override // com.google.android.gms.internal.zzpx
    public final String zzl() {
        return "";
    }

    @Override // com.google.android.gms.internal.zzpx
    public final zzph zzm() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final IObjectWrapper zzn() {
        return zzn.zza(this.zzp);
    }

    @Override // com.google.android.gms.internal.zzpx
    public final View zzo() {
        return this.zzl;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final IObjectWrapper zzp() {
        return this.zzm;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final String zzq() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final zzqo zzr() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.zzsk
    public final void zzs() {
        zzaij.zza.post(new zzps(this));
    }
}
