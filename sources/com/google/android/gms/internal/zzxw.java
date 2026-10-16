package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzxw extends zzwy {
    private final zza zza;

    public zzxw(zza zzaVar) {
        this.zza = zzaVar;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zza() {
        return this.zza.zza();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final void zza(IObjectWrapper iObjectWrapper) {
        zzn.zza(iObjectWrapper);
    }

    @Override // com.google.android.gms.internal.zzwx
    public final void zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zza.zza((View) zzn.zza(iObjectWrapper), (HashMap) zzn.zza(iObjectWrapper2), (HashMap) zzn.zza(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.zzwx
    public final List zzb() {
        List<NativeAd.Image> listZzb = this.zza.zzb();
        ArrayList arrayList = new ArrayList();
        if (listZzb != null) {
            for (NativeAd.Image image : listZzb) {
                arrayList.add(new zzpj(image.getDrawable(), image.getUri(), image.getScale()));
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final void zzb(IObjectWrapper iObjectWrapper) {
        zzn.zza(iObjectWrapper);
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zzc() {
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final zzqs zzd() {
        NativeAd.Image imageZzd = this.zza.zzd();
        if (imageZzd != null) {
            return new zzpj(imageZzd.getDrawable(), imageZzd.getUri(), imageZzd.getScale());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zze() {
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zzf() {
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final double zzg() {
        if (this.zza.zzg() != null) {
            return this.zza.zzg().doubleValue();
        }
        return -1.0d;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final String zzi() {
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final zzmm zzj() {
        if (this.zza.zzj() != null) {
            return this.zza.zzj().zza();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final zzqo zzk() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final IObjectWrapper zzl() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final IObjectWrapper zzm() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzwx
    public final IObjectWrapper zzn() {
        Object objZzk = this.zza.zzk();
        if (objZzk == null) {
            return null;
        }
        return zzn.zza(objZzk);
    }

    @Override // com.google.android.gms.internal.zzwx
    public final Bundle zzo() {
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final boolean zzp() {
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final boolean zzq() {
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.zzwx
    public final void zzr() {
    }
}
