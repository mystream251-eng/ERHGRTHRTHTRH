package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.internal.zzabh;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zza {
    private String zza;
    private List<NativeAd.Image> zzb;
    private String zzc;
    private NativeAd.Image zzd;
    private String zze;
    private String zzf;
    private Double zzg;
    private String zzh;
    private String zzi;
    private VideoController zzj;
    private Object zzk;
    private Bundle zzl = new Bundle();
    private boolean zzm;
    private boolean zzn;

    public final String zza() {
        return this.zza;
    }

    public void zza(View view, Map<String, View> map, Map<String, View> map2) {
    }

    public final void zza(VideoController videoController) {
        this.zzj = videoController;
    }

    public final void zza(NativeAd.Image image) {
        this.zzd = image;
    }

    public final void zza(Double d) {
        this.zzg = d;
    }

    public final void zza(Object obj) {
        this.zzk = obj;
    }

    public final void zza(String str) {
        this.zza = str;
    }

    public final void zza(List<NativeAd.Image> list) {
        this.zzb = list;
    }

    public final void zza(boolean z) {
        this.zzm = true;
    }

    public final List<NativeAd.Image> zzb() {
        return this.zzb;
    }

    public final void zzb(String str) {
        this.zzc = str;
    }

    public final void zzb(boolean z) {
        this.zzn = true;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final void zzc(String str) {
        this.zze = str;
    }

    public final NativeAd.Image zzd() {
        return this.zzd;
    }

    public final void zzd(String str) {
        this.zzf = str;
    }

    public final String zze() {
        return this.zze;
    }

    public final void zze(String str) {
        this.zzh = str;
    }

    public final String zzf() {
        return this.zzf;
    }

    public final void zzf(String str) {
        this.zzi = str;
    }

    public final Double zzg() {
        return this.zzg;
    }

    public final String zzh() {
        return this.zzh;
    }

    public final String zzi() {
        return this.zzi;
    }

    public final VideoController zzj() {
        return this.zzj;
    }

    public final Object zzk() {
        return this.zzk;
    }

    public final Bundle zzl() {
        return this.zzl;
    }

    public final boolean zzm() {
        return this.zzm;
    }

    public final boolean zzn() {
        return this.zzn;
    }
}
