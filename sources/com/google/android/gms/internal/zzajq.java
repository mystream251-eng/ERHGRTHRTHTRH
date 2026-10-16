package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzajq {
    private final List<String> zza = new ArrayList();
    private final List<Double> zzb = new ArrayList();
    private final List<Double> zzc = new ArrayList();

    public final zzajn zza() {
        return new zzajn(this);
    }

    public final zzajq zza(String str, double d, double d2) {
        int i = 0;
        while (i < this.zza.size()) {
            double dDoubleValue = this.zzc.get(i).doubleValue();
            double dDoubleValue2 = this.zzb.get(i).doubleValue();
            if (d < dDoubleValue || (dDoubleValue == d && d2 < dDoubleValue2)) {
                break;
            }
            i++;
        }
        this.zza.add(i, str);
        this.zzc.add(i, Double.valueOf(d));
        this.zzb.add(i, Double.valueOf(d2));
        return this;
    }
}
