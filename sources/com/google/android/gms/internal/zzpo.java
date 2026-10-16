package com.google.android.gms.internal;

import android.view.View;
import android.widget.FrameLayout;
import androidx.collection.SimpleArrayMap;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpo extends zzrp implements zzpx {
    private final zzph zza;
    private final String zzb;
    private final SimpleArrayMap<String, zzpj> zzc;
    private final SimpleArrayMap<String, String> zzd;
    private zzmm zze;
    private View zzf;
    private final Object zzg = new Object();
    private zzpv zzh;

    public zzpo(String str, SimpleArrayMap<String, zzpj> simpleArrayMap, SimpleArrayMap<String, String> simpleArrayMap2, zzph zzphVar, zzmm zzmmVar, View view) {
        this.zzb = str;
        this.zzc = simpleArrayMap;
        this.zzd = simpleArrayMap2;
        this.zza = zzphVar;
        this.zze = zzmmVar;
        this.zzf = view;
    }

    static /* synthetic */ zzpv zza(zzpo zzpoVar, zzpv zzpvVar) {
        zzpoVar.zzh = null;
        return null;
    }

    @Override // com.google.android.gms.internal.zzro
    public final String zza(String str) {
        return this.zzd.get(str);
    }

    @Override // com.google.android.gms.internal.zzro
    public final List<String> zza() {
        String[] strArr = new String[this.zzc.size() + this.zzd.size()];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.zzc.size()) {
            strArr[i3] = this.zzc.keyAt(i2);
            i2++;
            i3++;
        }
        while (i < this.zzd.size()) {
            strArr[i3] = this.zzd.keyAt(i);
            i++;
            i3++;
        }
        return Arrays.asList(strArr);
    }

    @Override // com.google.android.gms.internal.zzpx
    public final void zza(zzpv zzpvVar) {
        synchronized (this.zzg) {
            this.zzh = zzpvVar;
        }
    }

    @Override // com.google.android.gms.internal.zzro
    public final boolean zza(IObjectWrapper iObjectWrapper) {
        if (this.zzh == null) {
            zzaky.zzc("Attempt to call renderVideoInMediaView before ad initialized.");
            return false;
        }
        if (this.zzf == null) {
            return false;
        }
        zzpp zzppVar = new zzpp(this);
        this.zzh.zza((FrameLayout) zzn.zza(iObjectWrapper), zzppVar);
        return true;
    }

    @Override // com.google.android.gms.internal.zzro
    public final IObjectWrapper zzb() {
        return zzn.zza(this.zzh);
    }

    @Override // com.google.android.gms.internal.zzro
    public final zzqs zzb(String str) {
        return this.zzc.get(str);
    }

    @Override // com.google.android.gms.internal.zzro
    public final zzmm zzc() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzro
    public final void zzc(String str) {
        synchronized (this.zzg) {
            if (this.zzh == null) {
                zzaky.zzc("Attempt to call performClick before ad initialized.");
            } else {
                this.zzh.zza(null, str, null, null, null);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzro
    public final void zzd() {
        synchronized (this.zzg) {
            if (this.zzh == null) {
                zzaky.zzc("Attempt to perform recordImpression before ad initialized.");
            } else {
                this.zzh.zza((View) null, (Map<String, WeakReference<View>>) null);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzro
    public final IObjectWrapper zze() {
        return zzn.zza(this.zzh.zzi().getApplicationContext());
    }

    @Override // com.google.android.gms.internal.zzro
    public final void zzf() {
        zzaij.zza.post(new zzpq(this));
        this.zze = null;
        this.zzf = null;
    }

    @Override // com.google.android.gms.internal.zzpx
    public final String zzk() {
        return ExifInterface.GPS_MEASUREMENT_3D;
    }

    @Override // com.google.android.gms.internal.zzro, com.google.android.gms.internal.zzpx
    public final String zzl() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzpx
    public final zzph zzm() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.zzpx
    public final View zzo() {
        return this.zzf;
    }
}
