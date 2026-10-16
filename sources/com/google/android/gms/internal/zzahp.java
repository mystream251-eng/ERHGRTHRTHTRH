package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzahp {
    private final Object zza;
    private int zzb;
    private int zzc;
    private final zzahq zzd;
    private final String zze;

    private zzahp(zzahq zzahqVar, String str) {
        this.zza = new Object();
        this.zzd = zzahqVar;
        this.zze = str;
    }

    public zzahp(String str) {
        this(zzbt.zzj(), str);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            String str = this.zze;
            String str2 = ((zzahp) obj).zze;
            if (str != null) {
                return str.equals(str2);
            }
            if (str2 == null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.zze;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public final String zza() {
        return this.zze;
    }

    public final void zza(int i, int i2) {
        synchronized (this.zza) {
            this.zzb = i;
            this.zzc = i2;
            this.zzd.zza(this);
        }
    }

    public final Bundle zzb() {
        Bundle bundle;
        synchronized (this.zza) {
            bundle = new Bundle();
            bundle.putInt("pmnli", this.zzb);
            bundle.putInt("pmnll", this.zzc);
        }
        return bundle;
    }
}
