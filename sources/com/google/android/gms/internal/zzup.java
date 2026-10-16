package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzup {
    private final Object[] zza;

    zzup(zzkk zzkkVar, String str, int i) {
        this.zza = zzp.zza((String) zzlc.zzf().zza(zzoi.zzcn), zzkkVar, str, i, null);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzup) {
            return Arrays.equals(this.zza, ((zzup) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        String string = Arrays.toString(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 24);
        sb.append("[InterstitialAdPoolKey ");
        sb.append(string);
        sb.append("]");
        return sb.toString();
    }
}
