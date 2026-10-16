package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzhh {
    private int zzb;
    private final Object zza = new Object();
    private List<zzhg> zzc = new LinkedList();

    public final zzhg zza() {
        synchronized (this.zza) {
            zzhg zzhgVar = null;
            if (this.zzc.size() == 0) {
                zzahw.zzb("Queue empty");
                return null;
            }
            int i = 0;
            if (this.zzc.size() < 2) {
                zzhg zzhgVar2 = this.zzc.get(0);
                zzhgVar2.zze();
                return zzhgVar2;
            }
            int i2 = Integer.MIN_VALUE;
            int i3 = 0;
            for (zzhg zzhgVar3 : this.zzc) {
                int iZzi = zzhgVar3.zzi();
                if (iZzi > i2) {
                    i = i3;
                    zzhgVar = zzhgVar3;
                    i2 = iZzi;
                }
                i3++;
            }
            this.zzc.remove(i);
            return zzhgVar;
        }
    }

    public final boolean zza(zzhg zzhgVar) {
        synchronized (this.zza) {
            return this.zzc.contains(zzhgVar);
        }
    }

    public final boolean zzb(zzhg zzhgVar) {
        synchronized (this.zza) {
            Iterator<zzhg> it = this.zzc.iterator();
            while (it.hasNext()) {
                zzhg next = it.next();
                if (!((Boolean) zzlc.zzf().zza(zzoi.zzas)).booleanValue() || zzbt.zzi().zzl().zzb()) {
                    if (((Boolean) zzlc.zzf().zza(zzoi.zzau)).booleanValue() && !zzbt.zzi().zzl().zzd() && zzhgVar != next && next.zzd().equals(zzhgVar.zzd())) {
                        it.remove();
                        return true;
                    }
                } else if (zzhgVar != next && next.zzb().equals(zzhgVar.zzb())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzhg zzhgVar) {
        synchronized (this.zza) {
            if (this.zzc.size() >= 10) {
                int size = this.zzc.size();
                StringBuilder sb = new StringBuilder(41);
                sb.append("Queue is full, current size = ");
                sb.append(size);
                zzahw.zzb(sb.toString());
                this.zzc.remove(0);
            }
            int i = this.zzb;
            this.zzb = i + 1;
            zzhgVar.zza(i);
            this.zzc.add(zzhgVar);
        }
    }
}
