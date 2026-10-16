package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzajn {
    private final String[] zza;
    private final double[] zzb;
    private final double[] zzc;
    private final int[] zzd;
    private int zze;

    private zzajn(zzajq zzajqVar) {
        int size = zzajqVar.zzb.size();
        this.zza = (String[]) zzajqVar.zza.toArray(new String[size]);
        this.zzb = zza((List<Double>) zzajqVar.zzb);
        this.zzc = zza((List<Double>) zzajqVar.zzc);
        this.zzd = new int[size];
        this.zze = 0;
    }

    private static double[] zza(List<Double> list) {
        int size = list.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = list.get(i).doubleValue();
        }
        return dArr;
    }

    public final List<zzajp> zza() {
        ArrayList arrayList = new ArrayList(this.zza.length);
        for (int i = 0; i < this.zza.length; i++) {
            String str = this.zza[i];
            double d = this.zzc[i];
            double d2 = this.zzb[i];
            int[] iArr = this.zzd;
            arrayList.add(new zzajp(str, d, d2, ((double) iArr[i]) / ((double) this.zze), iArr[i]));
        }
        return arrayList;
    }

    public final void zza(double d) {
        this.zze++;
        int i = 0;
        while (true) {
            double[] dArr = this.zzc;
            if (i >= dArr.length) {
                return;
            }
            if (dArr[i] <= d && d < this.zzb[i]) {
                int[] iArr = this.zzd;
                iArr[i] = iArr[i] + 1;
            }
            if (d < this.zzc[i]) {
                return;
            } else {
                i++;
            }
        }
    }
}
