package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzov {
    private boolean zza;
    private final List<zzot> zzb = new LinkedList();
    private final Map<String, String> zzc = new LinkedHashMap();
    private final Object zzd = new Object();
    private String zze;
    private zzov zzf;

    public zzov(boolean z, String str, String str2) {
        this.zza = z;
        this.zzc.put("action", str);
        this.zzc.put("ad_format", str2);
    }

    public final zzot zza() {
        return zza(zzbt.zzl().zzb());
    }

    public final zzot zza(long j) {
        if (this.zza) {
            return new zzot(j, null, null);
        }
        return null;
    }

    public final void zza(zzov zzovVar) {
        synchronized (this.zzd) {
            this.zzf = zzovVar;
        }
    }

    public final void zza(String str) {
        if (this.zza) {
            synchronized (this.zzd) {
                this.zze = str;
            }
        }
    }

    public final void zza(String str, String str2) {
        zzol zzolVarZzb;
        if (!this.zza || TextUtils.isEmpty(str2) || (zzolVarZzb = zzbt.zzi().zzb()) == null) {
            return;
        }
        synchronized (this.zzd) {
            zzop zzopVarZza = zzolVarZzb.zza(str);
            Map<String, String> map = this.zzc;
            map.put(str, zzopVarZza.zza(map.get(str), str2));
        }
    }

    public final boolean zza(zzot zzotVar, long j, String... strArr) {
        synchronized (this.zzd) {
            for (String str : strArr) {
                this.zzb.add(new zzot(j, str, zzotVar));
            }
        }
        return true;
    }

    public final boolean zza(zzot zzotVar, String... strArr) {
        if (!this.zza || zzotVar == null) {
            return false;
        }
        return zza(zzotVar, zzbt.zzl().zzb(), strArr);
    }

    public final String zzb() {
        String string;
        StringBuilder sb = new StringBuilder();
        synchronized (this.zzd) {
            for (zzot zzotVar : this.zzb) {
                long jZza = zzotVar.zza();
                String strZzb = zzotVar.zzb();
                zzot zzotVarZzc = zzotVar.zzc();
                if (zzotVarZzc != null && jZza > 0) {
                    long jZza2 = jZza - zzotVarZzc.zza();
                    sb.append(strZzb);
                    sb.append('.');
                    sb.append(jZza2);
                    sb.append(',');
                }
            }
            this.zzb.clear();
            if (!TextUtils.isEmpty(this.zze)) {
                sb.append(this.zze);
            } else if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            string = sb.toString();
        }
        return string;
    }

    final Map<String, String> zzc() {
        synchronized (this.zzd) {
            zzol zzolVarZzb = zzbt.zzi().zzb();
            if (zzolVarZzb != null && this.zzf != null) {
                return zzolVarZzb.zza(this.zzc, this.zzf.zzc());
            }
            return this.zzc;
        }
    }

    public final zzot zzd() {
        synchronized (this.zzd) {
        }
        return null;
    }
}
