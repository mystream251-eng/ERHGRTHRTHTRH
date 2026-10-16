package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaeo {
    private static final zzwe zza = new zzwe();
    private final zzwf zzb;
    private final com.google.android.gms.ads.internal.zzbu zzc;
    private final Map<String, zzafy> zzd = new HashMap();
    private final zzafs zze;
    private final zzzn zzf;

    public zzaeo(com.google.android.gms.ads.internal.zzbu zzbuVar, zzwf zzwfVar, zzafs zzafsVar, zzzn zzznVar) {
        this.zzc = zzbuVar;
        this.zzb = zzwfVar;
        this.zze = zzafsVar;
        this.zzf = zzznVar;
    }

    public static boolean zza(zzahd zzahdVar, zzahd zzahdVar2) {
        return true;
    }

    public final zzafy zza(String str) {
        zzafy zzafyVar = this.zzd.get(str);
        if (zzafyVar != null) {
            return zzafyVar;
        }
        try {
            zzwf zzwfVar = this.zzb;
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                zzwfVar = zza;
            }
            zzafy zzafyVar2 = new zzafy(zzwfVar.zza(str), this.zze);
            try {
                this.zzd.put(str, zzafyVar2);
                return zzafyVar2;
            } catch (Exception e) {
                e = e;
                zzafyVar = zzafyVar2;
                String strValueOf = String.valueOf(str);
                zzahw.zzc(strValueOf.length() != 0 ? "Fail to instantiate adapter ".concat(strValueOf) : new String("Fail to instantiate adapter "), e);
                return zzafyVar;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final zzagd zza(zzagd zzagdVar) {
        if (this.zzc.zzj != null && this.zzc.zzj.zzq != null && !TextUtils.isEmpty(this.zzc.zzj.zzq.zzj)) {
            zzagdVar = new zzagd(this.zzc.zzj.zzq.zzj, this.zzc.zzj.zzq.zzk);
        }
        if (this.zzc.zzj != null && this.zzc.zzj.zzn != null) {
            zzbt.zzw();
            zzvy.zza(this.zzc.zzc, this.zzc.zze.zza, this.zzc.zzj.zzn.zzl, this.zzc.zzac, zzagdVar);
        }
        return zzagdVar;
    }

    public final zzzn zza() {
        return this.zzf;
    }

    public final void zza(Context context) {
        Iterator<zzafy> it = this.zzd.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().zza().zza(zzn.zza(context));
            } catch (RemoteException e) {
                zzahw.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public final void zza(boolean z) {
        zzafy zzafyVarZza = zza(this.zzc.zzj.zzp);
        if (zzafyVarZza == null || zzafyVarZza.zza() == null) {
            return;
        }
        try {
            zzafyVarZza.zza().zza(z);
            zzafyVarZza.zza().zzf();
        } catch (RemoteException e) {
            zzahw.zzc("Could not call showVideo.", e);
        }
    }

    public final void zzb() {
        this.zzc.zzag = 0;
        com.google.android.gms.ads.internal.zzbu zzbuVar = this.zzc;
        zzbt.zzd();
        zzafv zzafvVar = new zzafv(this.zzc.zzc, this.zzc.zzk, this);
        String strValueOf = String.valueOf(zzafvVar.getClass().getName());
        zzahw.zzb(strValueOf.length() != 0 ? "AdRenderer: ".concat(strValueOf) : new String("AdRenderer: "));
        zzafvVar.zzc();
        zzbuVar.zzh = zzafvVar;
    }

    public final void zzc() {
        zzbq.zzb("pause must be called on the main UI thread.");
        for (String str : this.zzd.keySet()) {
            try {
                zzafy zzafyVar = this.zzd.get(str);
                if (zzafyVar != null && zzafyVar.zza() != null) {
                    zzafyVar.zza().zzd();
                }
            } catch (RemoteException unused) {
                String strValueOf = String.valueOf(str);
                zzahw.zze(strValueOf.length() != 0 ? "Fail to pause adapter: ".concat(strValueOf) : new String("Fail to pause adapter: "));
            }
        }
    }

    public final void zzd() {
        zzbq.zzb("resume must be called on the main UI thread.");
        for (String str : this.zzd.keySet()) {
            try {
                zzafy zzafyVar = this.zzd.get(str);
                if (zzafyVar != null && zzafyVar.zza() != null) {
                    zzafyVar.zza().zze();
                }
            } catch (RemoteException unused) {
                String strValueOf = String.valueOf(str);
                zzahw.zze(strValueOf.length() != 0 ? "Fail to resume adapter: ".concat(strValueOf) : new String("Fail to resume adapter: "));
            }
        }
    }

    public final void zze() {
        zzbq.zzb("destroy must be called on the main UI thread.");
        for (String str : this.zzd.keySet()) {
            try {
                zzafy zzafyVar = this.zzd.get(str);
                if (zzafyVar != null && zzafyVar.zza() != null) {
                    zzafyVar.zza().zzc();
                }
            } catch (RemoteException unused) {
                String strValueOf = String.valueOf(str);
                zzahw.zze(strValueOf.length() != 0 ? "Fail to destroy adapter: ".concat(strValueOf) : new String("Fail to destroy adapter: "));
            }
        }
    }

    public final void zzf() {
        if (this.zzc.zzj == null || this.zzc.zzj.zzn == null) {
            return;
        }
        zzbt.zzw();
        zzvy.zza(this.zzc.zzc, this.zzc.zze.zza, this.zzc.zzj, this.zzc.zzb, false, this.zzc.zzj.zzn.zzk);
    }

    public final void zzg() {
        if (this.zzc.zzj == null || this.zzc.zzj.zzn == null) {
            return;
        }
        zzbt.zzw();
        zzvy.zza(this.zzc.zzc, this.zzc.zze.zza, this.zzc.zzj, this.zzc.zzb, false, this.zzc.zzj.zzn.zzm);
    }
}
