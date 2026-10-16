package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NativeAppInstallAdMapper extends NativeAdMapper {
    private String zza;
    private List<NativeAd.Image> zzb;
    private String zzc;
    private NativeAd.Image zzd;
    private String zze;
    private double zzf;
    private String zzg;
    private String zzh;

    public final String getBody() {
        return this.zzc;
    }

    public final String getCallToAction() {
        return this.zze;
    }

    public final String getHeadline() {
        return this.zza;
    }

    public final NativeAd.Image getIcon() {
        return this.zzd;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    public final String getPrice() {
        return this.zzh;
    }

    public final double getStarRating() {
        return this.zzf;
    }

    public final String getStore() {
        return this.zzg;
    }

    public final void setBody(String str) {
        this.zzc = str;
    }

    public final void setCallToAction(String str) {
        this.zze = str;
    }

    public final void setHeadline(String str) {
        this.zza = str;
    }

    public final void setIcon(NativeAd.Image image) {
        this.zzd = image;
    }

    public final void setImages(List<NativeAd.Image> list) {
        this.zzb = list;
    }

    public final void setPrice(String str) {
        this.zzh = str;
    }

    public final void setStarRating(double d) {
        this.zzf = d;
    }

    public final void setStore(String str) {
        this.zzg = str;
    }
}
