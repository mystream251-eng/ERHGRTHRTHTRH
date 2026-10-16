package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzzs extends zzzi {
    protected zzvw zzg;
    private zzwf zzh;
    private zzvo zzi;
    private zzvq zzj;
    private final zzov zzk;
    private final zzaof zzl;
    private boolean zzm;

    zzzs(Context context, zzahe zzaheVar, zzwf zzwfVar, zzzn zzznVar, zzov zzovVar, zzaof zzaofVar) {
        super(context, zzaheVar, zzznVar);
        this.zzh = zzwfVar;
        this.zzj = zzaheVar.zzc;
        this.zzk = zzovVar;
        this.zzl = zzaofVar;
    }

    private static String zza(List<zzvw> list) {
        String string = "";
        if (list == null) {
            return "";
        }
        Iterator<zzvw> it = list.iterator();
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                return string.substring(0, Math.max(0, string.length() - 1));
            }
            zzvw next = it.next();
            if (next != null && next.zzb != null && !TextUtils.isEmpty(next.zzb.zzd)) {
                String strValueOf = String.valueOf(string);
                String str = next.zzb.zzd;
                int i2 = next.zza;
                if (i2 == -1) {
                    i = 4;
                } else if (i2 != 0) {
                    if (i2 == 1) {
                        i = 1;
                    } else if (i2 == 3) {
                        i = 2;
                    } else if (i2 != 4) {
                        i = i2 != 5 ? 6 : 5;
                    } else {
                        i = 3;
                    }
                }
                long j = next.zzg;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33);
                sb.append(str);
                sb.append(".");
                sb.append(i);
                sb.append(".");
                sb.append(j);
                String string2 = sb.toString();
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 1 + String.valueOf(string2).length());
                sb2.append(strValueOf);
                sb2.append(string2);
                sb2.append("_");
                string = sb2.toString();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzzi, com.google.android.gms.internal.zzahs
    public final void b_() {
        synchronized (this.zzd) {
            super.b_();
            if (this.zzi != null) {
                this.zzi.zza();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzzi
    protected final zzahd zza(int i) {
        zzacf zzacfVar = this.zze.zza;
        zzkk zzkkVar = zzacfVar.zzc;
        zzaof zzaofVar = this.zzl;
        List<String> list = this.zzf.zzc;
        List<String> list2 = this.zzf.zze;
        List<String> list3 = this.zzf.zzi;
        int i2 = this.zzf.zzk;
        long j = this.zzf.zzj;
        String str = zzacfVar.zzi;
        boolean z = this.zzf.zzg;
        zzvw zzvwVar = this.zzg;
        zzvp zzvpVar = zzvwVar != null ? zzvwVar.zzb : null;
        zzvw zzvwVar2 = this.zzg;
        zzwi zzwiVar = zzvwVar2 != null ? zzvwVar2.zzc : null;
        zzvw zzvwVar3 = this.zzg;
        String name = zzvwVar3 != null ? zzvwVar3.zzd : AdMobAdapter.class.getName();
        zzvq zzvqVar = this.zzj;
        zzvw zzvwVar4 = this.zzg;
        zzvs zzvsVar = zzvwVar4 != null ? zzvwVar4.zze : null;
        zzvp zzvpVar2 = zzvpVar;
        zzwi zzwiVar2 = zzwiVar;
        long j2 = this.zzf.zzh;
        zzko zzkoVar = this.zze.zzd;
        long j3 = this.zzf.zzf;
        long j4 = this.zze.zzf;
        long j5 = this.zzf.zzm;
        String str2 = this.zzf.zzn;
        JSONObject jSONObject = this.zze.zzh;
        zzagd zzagdVar = this.zzf.zzaa;
        List<String> list4 = this.zzf.zzab;
        List<String> list5 = this.zzf.zzac;
        zzvq zzvqVar2 = this.zzj;
        boolean z2 = zzvqVar2 != null ? zzvqVar2.zzn : false;
        zzacl zzaclVar = this.zzf.zzae;
        zzvo zzvoVar = this.zzi;
        return new zzahd(zzkkVar, zzaofVar, list, i, list2, list3, i2, j, str, z, zzvpVar2, zzwiVar2, name, zzvqVar, zzvsVar, j2, zzkoVar, j3, j4, j5, str2, jSONObject, null, zzagdVar, list4, list5, z2, zzaclVar, zzvoVar != null ? zza(zzvoVar.zzb()) : null, this.zzf.zzah, this.zzf.zzal, this.zze.zzi, this.zzf.zzao, this.zze.zzj);
    }

    @Override // com.google.android.gms.internal.zzzi
    protected final void zza(long j) throws zzzl {
        Bundle bundle;
        synchronized (this.zzd) {
            this.zzi = this.zzj.zzl != -1 ? new zzvz(this.zzb, this.zze.zza, this.zzh, this.zzj, this.zzf.zzs, this.zzf.zzz, this.zzf.zzaj, j, ((Long) zzlc.zzf().zza(zzoi.zzdt)).longValue(), 2, this.zze.zzj) : new zzwc(this.zzb, this.zze.zza, this.zzh, this.zzj, this.zzf.zzs, this.zzf.zzz, this.zzf.zzaj, j, ((Long) zzlc.zzf().zza(zzoi.zzdt)).longValue(), this.zzk, this.zze.zzj);
        }
        ArrayList arrayList = new ArrayList(this.zzj.zza);
        Bundle bundle2 = this.zze.zza.zzc.zzm;
        if ((bundle2 == null || (bundle = bundle2.getBundle("com.google.ads.mediation.admob.AdMobAdapter")) == null) ? false : bundle.getBoolean("_skipMediation")) {
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (!((zzvp) listIterator.next()).zzc.contains("com.google.ads.mediation.admob.AdMobAdapter")) {
                    listIterator.remove();
                }
            }
        }
        zzvw zzvwVarZza = this.zzi.zza(arrayList);
        this.zzg = zzvwVarZza;
        int i = zzvwVarZza.zza;
        if (i != 0) {
            if (i == 1) {
                throw new zzzl("No fill from any mediation ad networks.", 3);
            }
            int i2 = this.zzg.zza;
            StringBuilder sb = new StringBuilder(40);
            sb.append("Unexpected mediation result: ");
            sb.append(i2);
            throw new zzzl(sb.toString(), 0);
        }
        if (this.zzg.zzb == null || this.zzg.zzb.zzn == null) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        zzaij.zza.post(new zzzt(this, countDownLatch));
        try {
            countDownLatch.await(10L, TimeUnit.SECONDS);
            synchronized (this.zzd) {
                if (!this.zzm) {
                    throw new zzzl("View could not be prepared", 0);
                }
                if (this.zzl.zzaa()) {
                    throw new zzzl("Assets not loaded, web view is destroyed", 0);
                }
            }
        } catch (InterruptedException e) {
            String strValueOf = String.valueOf(e);
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 38);
            sb2.append("Interrupted while waiting for latch : ");
            sb2.append(strValueOf);
            throw new zzzl(sb2.toString(), 0);
        }
    }
}
