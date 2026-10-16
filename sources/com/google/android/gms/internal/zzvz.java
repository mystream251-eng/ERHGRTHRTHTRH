package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzvz implements zzvo {
    private final zzacf zza;
    private final zzwf zzb;
    private final Context zzc;
    private final zzvq zzd;
    private final boolean zze;
    private final long zzf;
    private final long zzg;
    private final boolean zzl;
    private final String zzm;
    private final boolean zzo;
    private final Object zzi = new Object();
    private boolean zzj = false;
    private final Map<zzalt<zzvw>, zzvt> zzk = new HashMap();
    private List<zzvw> zzn = new ArrayList();
    private final int zzh = 2;

    public zzvz(Context context, zzacf zzacfVar, zzwf zzwfVar, zzvq zzvqVar, boolean z, boolean z2, String str, long j, long j2, int i, boolean z3) {
        this.zzc = context;
        this.zza = zzacfVar;
        this.zzb = zzwfVar;
        this.zzd = zzvqVar;
        this.zze = z;
        this.zzl = z2;
        this.zzm = str;
        this.zzf = j;
        this.zzg = j2;
        this.zzo = z3;
    }

    private final void zza(zzalt<zzvw> zzaltVar) {
        zzaij.zza.post(new zzwb(this, zzaltVar));
    }

    private final zzvw zzb(List<zzalt<zzvw>> list) {
        synchronized (this.zzi) {
            if (this.zzj) {
                return new zzvw(-1);
            }
            for (zzalt<zzvw> zzaltVar : list) {
                try {
                    zzvw zzvwVar = zzaltVar.get();
                    this.zzn.add(zzvwVar);
                    if (zzvwVar != null && zzvwVar.zza == 0) {
                        zza(zzaltVar);
                        return zzvwVar;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    zzahw.zzc("Exception while processing an adapter; continuing with other adapters", e);
                }
            }
            zza((zzalt<zzvw>) null);
            return new zzvw(1);
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004e A[Catch: all -> 0x0071, TimeoutException -> 0x0073, RemoteException -> 0x0075, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0077, InterruptedException -> 0x0079, TryCatch #0 {all -> 0x0071, blocks: (B:19:0x0041, B:21:0x0047, B:22:0x004b, B:24:0x0055, B:26:0x005c, B:28:0x0060, B:30:0x0064, B:32:0x006a, B:23:0x004e, B:43:0x007a), top: B:55:0x0041 }] */
    private final zzvw zzc(List<zzalt<zzvw>> list) {
        zzvw zzvwVar;
        zzvw zzvwVar2;
        zzwo zzwoVar;
        synchronized (this.zzi) {
            int iZza = -1;
            if (this.zzj) {
                return new zzvw(-1);
            }
            long jMax = this.zzd.zzm != -1 ? this.zzd.zzm : 10000L;
            zzalt<zzvw> zzaltVar = null;
            zzvw zzvwVar3 = null;
            for (zzalt<zzvw> zzaltVar2 : list) {
                long jZza = zzbt.zzl().zza();
                if (jMax == 0) {
                    try {
                        try {
                            if (zzaltVar2.isDone()) {
                                zzvwVar = zzaltVar2.get();
                            } else {
                                zzvwVar = zzaltVar2.get(jMax, TimeUnit.MILLISECONDS);
                            }
                            zzvwVar2 = zzvwVar;
                            this.zzn.add(zzvwVar2);
                            if (zzvwVar2 == null && zzvwVar2.zza == 0 && (zzwoVar = zzvwVar2.zzf) != null && zzwoVar.zza() > iZza) {
                                iZza = zzwoVar.zza();
                                zzaltVar = zzaltVar2;
                                zzvwVar3 = zzvwVar2;
                            }
                        } catch (Throwable th) {
                            Math.max(jMax - (zzbt.zzl().zza() - jZza), 0L);
                            throw th;
                        }
                    } catch (RemoteException | InterruptedException | ExecutionException | TimeoutException e) {
                        zzahw.zzc("Exception while processing an adapter; continuing with other adapters", e);
                    }
                } else {
                    zzvwVar = zzaltVar2.get(jMax, TimeUnit.MILLISECONDS);
                    zzvwVar2 = zzvwVar;
                    this.zzn.add(zzvwVar2);
                    if (zzvwVar2 == null) {
                    }
                }
                jMax = Math.max(jMax - (zzbt.zzl().zza() - jZza), 0L);
            }
            zza(zzaltVar);
            return zzvwVar3 == null ? new zzvw(1) : zzvwVar3;
        }
    }

    @Override // com.google.android.gms.internal.zzvo
    public final zzvw zza(List<zzvp> list) {
        zzahw.zzb("Starting mediation.");
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        zzko zzkoVar = this.zza.zzd;
        int[] iArr = new int[2];
        if (zzkoVar.zzg != null) {
            zzbt.zzw();
            if (zzvy.zza(this.zzm, iArr)) {
                int i = iArr[0];
                int i2 = iArr[1];
                for (zzko zzkoVar2 : zzkoVar.zzg) {
                    if (i == zzkoVar2.zze && i2 == zzkoVar2.zzb) {
                        zzkoVar = zzkoVar2;
                        break;
                    }
                }
            }
        }
        Iterator<zzvp> it = list.iterator();
        while (it.hasNext()) {
            zzvp next = it.next();
            String strValueOf = String.valueOf(next.zzb);
            zzahw.zzd(strValueOf.length() != 0 ? "Trying mediation network: ".concat(strValueOf) : new String("Trying mediation network: "));
            for (Iterator<String> it2 = next.zzc.iterator(); it2.hasNext(); it2 = it2) {
                ArrayList arrayList2 = arrayList;
                zzvt zzvtVar = new zzvt(this.zzc, it2.next(), this.zzb, this.zzd, next, this.zza.zzc, zzkoVar, this.zza.zzk, this.zze, this.zzl, this.zza.zzy, this.zza.zzn, this.zza.zzz, this.zza.zzax, this.zzo);
                zzalt<zzvw> zzaltVarZza = zzaid.zza(executorServiceNewCachedThreadPool, new zzwa(this, zzvtVar));
                this.zzk.put(zzaltVarZza, zzvtVar);
                arrayList2.add(zzaltVarZza);
                it = it;
                arrayList = arrayList2;
            }
        }
        ArrayList arrayList3 = arrayList;
        return this.zzh != 2 ? zzb(arrayList3) : zzc(arrayList3);
    }

    @Override // com.google.android.gms.internal.zzvo
    public final void zza() {
        synchronized (this.zzi) {
            this.zzj = true;
            Iterator<zzvt> it = this.zzk.values().iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzvo
    public final List<zzvw> zzb() {
        return this.zzn;
    }
}
