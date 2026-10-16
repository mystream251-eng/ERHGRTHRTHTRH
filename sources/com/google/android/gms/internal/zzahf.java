package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahf {
    private final zzahq zza;
    private final LinkedList<zzahg> zzb;
    private final Object zzc;
    private final String zzd;
    private final String zze;
    private long zzf;
    private long zzg;
    private boolean zzh;
    private long zzi;
    private long zzj;
    private long zzk;
    private long zzl;

    private zzahf(zzahq zzahqVar, String str, String str2) {
        this.zzc = new Object();
        this.zzf = -1L;
        this.zzg = -1L;
        this.zzh = false;
        this.zzi = -1L;
        this.zzj = 0L;
        this.zzk = -1L;
        this.zzl = -1L;
        this.zza = zzahqVar;
        this.zzd = str;
        this.zze = str2;
        this.zzb = new LinkedList<>();
    }

    public zzahf(String str, String str2) {
        this(zzbt.zzj(), str, str2);
    }

    public final void zza() {
        synchronized (this.zzc) {
            if (this.zzl != -1 && this.zzg == -1) {
                this.zzg = SystemClock.elapsedRealtime();
                this.zza.zza(this);
            }
            this.zza.zzb();
        }
    }

    public final void zza(long j) {
        synchronized (this.zzc) {
            this.zzl = j;
            if (j != -1) {
                this.zza.zza(this);
            }
        }
    }

    public final void zza(zzkk zzkkVar) {
        synchronized (this.zzc) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.zzk = jElapsedRealtime;
            this.zza.zza(zzkkVar, jElapsedRealtime);
        }
    }

    public final void zza(boolean z) {
        synchronized (this.zzc) {
            if (this.zzl != -1) {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                this.zzi = jElapsedRealtime;
                if (!z) {
                    this.zzg = jElapsedRealtime;
                    this.zza.zza(this);
                }
            }
        }
    }

    public final void zzb() {
        synchronized (this.zzc) {
            if (this.zzl != -1) {
                zzahg zzahgVar = new zzahg();
                zzahgVar.zzc();
                this.zzb.add(zzahgVar);
                this.zzj++;
                this.zza.zza();
                this.zza.zza(this);
            }
        }
    }

    public final void zzb(long j) {
        synchronized (this.zzc) {
            if (this.zzl != -1) {
                this.zzf = j;
                this.zza.zza(this);
            }
        }
    }

    public final void zzb(boolean z) {
        synchronized (this.zzc) {
            if (this.zzl != -1) {
                this.zzh = z;
                this.zza.zza(this);
            }
        }
    }

    public final void zzc() {
        synchronized (this.zzc) {
            if (this.zzl != -1 && !this.zzb.isEmpty()) {
                zzahg last = this.zzb.getLast();
                if (last.zza() == -1) {
                    last.zzb();
                    this.zza.zza(this);
                }
            }
        }
    }

    public final Bundle zzd() {
        Bundle bundle;
        synchronized (this.zzc) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.zzd);
            bundle.putString("slotid", this.zze);
            bundle.putBoolean("ismediation", this.zzh);
            bundle.putLong("treq", this.zzk);
            bundle.putLong("tresponse", this.zzl);
            bundle.putLong("timp", this.zzg);
            bundle.putLong("tload", this.zzi);
            bundle.putLong("pcc", this.zzj);
            bundle.putLong("tfetch", this.zzf);
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            Iterator<zzahg> it = this.zzb.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().zzd());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }
}
