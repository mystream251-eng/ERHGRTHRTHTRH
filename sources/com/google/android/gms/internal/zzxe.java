package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxe extends zzws {
    private final NativeAppInstallAdMapper zza;

    public zzxe(NativeAppInstallAdMapper nativeAppInstallAdMapper) {
        this.zza = nativeAppInstallAdMapper;
    }

    @Override // com.google.android.gms.internal.zzwr
    public final String zza() {
        return this.zza.getHeadline();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final void zza(IObjectWrapper iObjectWrapper) {
        this.zza.handleClick((View) zzn.zza(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.zzwr
    public final void zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zza.trackViews((View) zzn.zza(iObjectWrapper), (HashMap) zzn.zza(iObjectWrapper2), (HashMap) zzn.zza(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.zzwr
    public final List zzb() {
        List<NativeAd.Image> images = this.zza.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeAd.Image image : images) {
            arrayList.add(new zzpj(image.getDrawable(), image.getUri(), image.getScale()));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.zzwr
    public final void zzb(IObjectWrapper iObjectWrapper) {
        this.zza.trackView((View) zzn.zza(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.zzwr
    public final String zzc() {
        return this.zza.getBody();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.zza.untrackView((View) zzn.zza(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.zzwr
    public final zzqs zzd() {
        NativeAd.Image icon = this.zza.getIcon();
        if (icon != null) {
            return new zzpj(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwr
    public final String zze() {
        return this.zza.getCallToAction();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final double zzf() {
        return this.zza.getStarRating();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final String zzg() {
        return this.zza.getStore();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final String zzh() {
        return this.zza.getPrice();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final void zzi() {
        this.zza.recordImpression();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final boolean zzj() {
        return this.zza.getOverrideImpressionRecording();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final boolean zzk() {
        return this.zza.getOverrideClickHandling();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final Bundle zzl() {
        return this.zza.getExtras();
    }

    @Override // com.google.android.gms.internal.zzwr
    public final zzmm zzm() {
        if (this.zza.zzb() != null) {
            return this.zza.zzb().zza();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwr
    public final IObjectWrapper zzn() {
        View adChoicesContent = this.zza.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return zzn.zza(adChoicesContent);
    }

    @Override // com.google.android.gms.internal.zzwr
    public final zzqo zzo() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwr
    public final IObjectWrapper zzp() {
        View viewZza = this.zza.zza();
        if (viewZza == null) {
            return null;
        }
        return zzn.zza(viewZza);
    }

    @Override // com.google.android.gms.internal.zzwr
    public final IObjectWrapper zzq() {
        return null;
    }
}
