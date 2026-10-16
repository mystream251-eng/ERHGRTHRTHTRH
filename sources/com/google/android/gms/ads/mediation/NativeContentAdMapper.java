package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NativeContentAdMapper extends NativeAdMapper {
    private String zza;
    private List<NativeAd.Image> zzb;
    private String zzc;
    private NativeAd.Image zzd;
    private String zze;
    private String zzf;

    public final String getAdvertiser() {
        return this.zzf;
    }

    public final String getBody() {
        return this.zzc;
    }

    public final String getCallToAction() {
        return this.zze;
    }

    public final String getHeadline() {
        return this.zza;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    public final NativeAd.Image getLogo() {
        return this.zzd;
    }

    public final void setAdvertiser(String str) {
        this.zzf = str;
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

    public final void setImages(List<NativeAd.Image> list) {
        this.zzb = list;
    }

    public final void setLogo(NativeAd.Image image) {
        this.zzd = image;
    }
}
