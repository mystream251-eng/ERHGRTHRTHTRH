package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxf extends zzwv {
    private final NativeContentAdMapper zza;

    public zzxf(NativeContentAdMapper nativeContentAdMapper) {
        this.zza = nativeContentAdMapper;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final String zza() {
        return this.zza.getHeadline();
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zza(IObjectWrapper iObjectWrapper) {
        this.zza.handleClick((View) zzn.zza(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zza.trackViews((View) zzn.zza(iObjectWrapper), (HashMap) zzn.zza(iObjectWrapper2), (HashMap) zzn.zza(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.zzwu
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

    @Override // com.google.android.gms.internal.zzwu
    public final void zzb(IObjectWrapper iObjectWrapper) {
        this.zza.trackView((View) zzn.zza(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.zzwu
    public final String zzc() {
        return this.zza.getBody();
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.zza.untrackView((View) zzn.zza(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.zzwu
    public final zzqs zzd() {
        NativeAd.Image logo = this.zza.getLogo();
        if (logo != null) {
            return new zzpj(logo.getDrawable(), logo.getUri(), logo.getScale());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final String zze() {
        return this.zza.getCallToAction();
    }

    @Override // com.google.android.gms.internal.zzwu
    public final String zzf() {
        return this.zza.getAdvertiser();
    }

    @Override // com.google.android.gms.internal.zzwu
    public final void zzg() {
        this.zza.recordImpression();
    }

    @Override // com.google.android.gms.internal.zzwu
    public final boolean zzh() {
        return this.zza.getOverrideImpressionRecording();
    }

    @Override // com.google.android.gms.internal.zzwu
    public final boolean zzi() {
        return this.zza.getOverrideClickHandling();
    }

    @Override // com.google.android.gms.internal.zzwu
    public final Bundle zzj() {
        return this.zza.getExtras();
    }

    @Override // com.google.android.gms.internal.zzwu
    public final IObjectWrapper zzk() {
        View adChoicesContent = this.zza.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return zzn.zza(adChoicesContent);
    }

    @Override // com.google.android.gms.internal.zzwu
    public final zzmm zzl() {
        if (this.zza.zzb() != null) {
            return this.zza.zzb().zza();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final zzqo zzm() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwu
    public final IObjectWrapper zzn() {
        View viewZza = this.zza.zza();
        if (viewZza == null) {
            return null;
        }
        return zzn.zza(viewZza);
    }

    @Override // com.google.android.gms.internal.zzwu
    public final IObjectWrapper zzo() {
        return null;
    }
}
