package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzbt;
import io.sentry.protocol.App;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahq implements zzhl {
    private final Object zza;
    private zzahm zzb;
    private HashSet<zzahf> zzc;
    private HashSet<zzahp> zzd;

    public zzahq() {
        this(zzlc.zzc());
    }

    private zzahq(String str) {
        this.zza = new Object();
        this.zzc = new HashSet<>();
        this.zzd = new HashSet<>();
        this.zzb = new zzahm(str);
    }

    public final Bundle zza(Context context, zzahn zzahnVar, String str) {
        Bundle bundle;
        synchronized (this.zza) {
            bundle = new Bundle();
            bundle.putBundle(App.TYPE, this.zzb.zza(context, str));
            Bundle bundle2 = new Bundle();
            for (zzahp zzahpVar : this.zzd) {
                bundle2.putBundle(zzahpVar.zza(), zzahpVar.zzb());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            Iterator<zzahf> it = this.zzc.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().zzd());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            zzahnVar.zza(this.zzc);
            this.zzc.clear();
        }
        return bundle;
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zzb.zza();
        }
    }

    public final void zza(zzahf zzahfVar) {
        synchronized (this.zza) {
            this.zzc.add(zzahfVar);
        }
    }

    public final void zza(zzahp zzahpVar) {
        synchronized (this.zza) {
            this.zzd.add(zzahpVar);
        }
    }

    public final void zza(zzkk zzkkVar, long j) {
        synchronized (this.zza) {
            this.zzb.zza(zzkkVar, j);
        }
    }

    public final void zza(HashSet<zzahf> hashSet) {
        synchronized (this.zza) {
            this.zzc.addAll(hashSet);
        }
    }

    @Override // com.google.android.gms.internal.zzhl
    public final void zza(boolean z) {
        long jZza = zzbt.zzl().zza();
        if (!z) {
            zzbt.zzi().zzl().zza(jZza);
            zzbt.zzi().zzl().zzb(this.zzb.zza);
            return;
        }
        if (jZza - zzbt.zzi().zzl().zzi() > ((Long) zzlc.zzf().zza(zzoi.zzcb)).longValue()) {
            this.zzb.zza = -1;
        } else {
            this.zzb.zza = zzbt.zzi().zzl().zzj();
        }
    }

    public final void zzb() {
        synchronized (this.zza) {
            this.zzb.zzb();
        }
    }
}
