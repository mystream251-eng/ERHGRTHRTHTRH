package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzap;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzkf;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class AdOverlayInfoParcel extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<AdOverlayInfoParcel> CREATOR = new zzm();
    public final zzc zza;
    public final zzkf zzb;
    public final zzn zzc;
    public final zzaof zzd;
    public final com.google.android.gms.ads.internal.gmsg.zzb zze;
    public final String zzf;
    public final boolean zzg;
    public final String zzh;
    public final zzt zzi;
    public final int zzj;
    public final int zzk;
    public final String zzl;
    public final zzala zzm;
    public final String zzn;
    public final zzap zzo;

    AdOverlayInfoParcel(zzc zzcVar, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i, int i2, String str3, zzala zzalaVar, String str4, zzap zzapVar) {
        this.zza = zzcVar;
        this.zzb = (zzkf) com.google.android.gms.dynamic.zzn.zza(IObjectWrapper.zza.zza(iBinder));
        this.zzc = (zzn) com.google.android.gms.dynamic.zzn.zza(IObjectWrapper.zza.zza(iBinder2));
        this.zzd = (zzaof) com.google.android.gms.dynamic.zzn.zza(IObjectWrapper.zza.zza(iBinder3));
        this.zze = (com.google.android.gms.ads.internal.gmsg.zzb) com.google.android.gms.dynamic.zzn.zza(IObjectWrapper.zza.zza(iBinder4));
        this.zzf = str;
        this.zzg = z;
        this.zzh = str2;
        this.zzi = (zzt) com.google.android.gms.dynamic.zzn.zza(IObjectWrapper.zza.zza(iBinder5));
        this.zzj = i;
        this.zzk = i2;
        this.zzl = str3;
        this.zzm = zzalaVar;
        this.zzn = str4;
        this.zzo = zzapVar;
    }

    public AdOverlayInfoParcel(zzc zzcVar, zzkf zzkfVar, zzn zznVar, zzt zztVar, zzala zzalaVar) {
        this.zza = zzcVar;
        this.zzb = zzkfVar;
        this.zzc = zznVar;
        this.zzd = null;
        this.zze = null;
        this.zzf = null;
        this.zzg = false;
        this.zzh = null;
        this.zzi = zztVar;
        this.zzj = -1;
        this.zzk = 4;
        this.zzl = null;
        this.zzm = zzalaVar;
        this.zzn = null;
        this.zzo = null;
    }

    public AdOverlayInfoParcel(zzkf zzkfVar, zzn zznVar, com.google.android.gms.ads.internal.gmsg.zzb zzbVar, zzt zztVar, zzaof zzaofVar, boolean z, int i, String str, zzala zzalaVar) {
        this.zza = null;
        this.zzb = zzkfVar;
        this.zzc = zznVar;
        this.zzd = zzaofVar;
        this.zze = zzbVar;
        this.zzf = null;
        this.zzg = z;
        this.zzh = null;
        this.zzi = zztVar;
        this.zzj = i;
        this.zzk = 3;
        this.zzl = str;
        this.zzm = zzalaVar;
        this.zzn = null;
        this.zzo = null;
    }

    public AdOverlayInfoParcel(zzkf zzkfVar, zzn zznVar, com.google.android.gms.ads.internal.gmsg.zzb zzbVar, zzt zztVar, zzaof zzaofVar, boolean z, int i, String str, String str2, zzala zzalaVar) {
        this.zza = null;
        this.zzb = zzkfVar;
        this.zzc = zznVar;
        this.zzd = zzaofVar;
        this.zze = zzbVar;
        this.zzf = str2;
        this.zzg = z;
        this.zzh = str;
        this.zzi = zztVar;
        this.zzj = i;
        this.zzk = 3;
        this.zzl = null;
        this.zzm = zzalaVar;
        this.zzn = null;
        this.zzo = null;
    }

    public AdOverlayInfoParcel(zzkf zzkfVar, zzn zznVar, zzt zztVar, zzaof zzaofVar, int i, zzala zzalaVar, String str, zzap zzapVar) {
        this.zza = null;
        this.zzb = zzkfVar;
        this.zzc = zznVar;
        this.zzd = zzaofVar;
        this.zze = null;
        this.zzf = null;
        this.zzg = false;
        this.zzh = null;
        this.zzi = zztVar;
        this.zzj = i;
        this.zzk = 1;
        this.zzl = null;
        this.zzm = zzalaVar;
        this.zzn = str;
        this.zzo = zzapVar;
    }

    public AdOverlayInfoParcel(zzkf zzkfVar, zzn zznVar, zzt zztVar, zzaof zzaofVar, boolean z, int i, zzala zzalaVar) {
        this.zza = null;
        this.zzb = zzkfVar;
        this.zzc = zznVar;
        this.zzd = zzaofVar;
        this.zze = null;
        this.zzf = null;
        this.zzg = z;
        this.zzh = null;
        this.zzi = zztVar;
        this.zzj = i;
        this.zzk = 2;
        this.zzl = null;
        this.zzm = zzalaVar;
        this.zzn = null;
        this.zzo = null;
    }

    public static AdOverlayInfoParcel zza(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception unused) {
            return null;
        }
    }

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, i, false);
        zzbgo.zza(parcel, 3, com.google.android.gms.dynamic.zzn.zza(this.zzb).asBinder(), false);
        zzbgo.zza(parcel, 4, com.google.android.gms.dynamic.zzn.zza(this.zzc).asBinder(), false);
        zzbgo.zza(parcel, 5, com.google.android.gms.dynamic.zzn.zza(this.zzd).asBinder(), false);
        zzbgo.zza(parcel, 6, com.google.android.gms.dynamic.zzn.zza(this.zze).asBinder(), false);
        zzbgo.zza(parcel, 7, this.zzf, false);
        zzbgo.zza(parcel, 8, this.zzg);
        zzbgo.zza(parcel, 9, this.zzh, false);
        zzbgo.zza(parcel, 10, com.google.android.gms.dynamic.zzn.zza(this.zzi).asBinder(), false);
        zzbgo.zza(parcel, 11, this.zzj);
        zzbgo.zza(parcel, 12, this.zzk);
        zzbgo.zza(parcel, 13, this.zzl, false);
        zzbgo.zza(parcel, 14, this.zzm, i, false);
        zzbgo.zza(parcel, 16, this.zzn, false);
        zzbgo.zza(parcel, 17, this.zzo, i, false);
        zzbgo.zza(parcel, iZza);
    }
}
