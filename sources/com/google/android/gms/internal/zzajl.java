package com.google.android.gms.internal;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzajl extends zzaj {
    private final Context zza;

    private zzajl(Context context, zzar zzarVar) {
        super(zzarVar);
        this.zza = context;
    }

    public static zzv zza(Context context) {
        zzv zzvVar = new zzv(new zzam(new File(context.getCacheDir(), "admob_volley")), new zzajl(context, new zzas()));
        zzvVar.zza();
        return zzvVar;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zza */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzae */
    public final zzp zza(zzr<?> zzrVar) throws zza, zzae {
        if (zzrVar.zzh() && zzrVar.zza() == 0) {
            if (Pattern.matches((String) zzlc.zzf().zza(zzoi.zzfz), zzrVar.zzc())) {
                zzlc.zza();
                if (zzako.zzc(this.zza)) {
                    zzp zzpVarZza = new zzte(this.zza).zza(zzrVar);
                    if (zzpVarZza != null) {
                        String strValueOf = String.valueOf(zzrVar.zzc());
                        zzahw.zza(strValueOf.length() != 0 ? "Got gmscore asset response: ".concat(strValueOf) : new String("Got gmscore asset response: "));
                        return zzpVarZza;
                    }
                    String strValueOf2 = String.valueOf(zzrVar.zzc());
                    zzahw.zza(strValueOf2.length() != 0 ? "Failed to get gmscore asset response: ".concat(strValueOf2) : new String("Failed to get gmscore asset response: "));
                }
            }
        }
        return super.zza(zzrVar);
    }
}
