package com.google.android.gms.internal;

import com.google.android.gms.internal.zzapa;
import com.google.android.gms.internal.zzapr;
import com.google.android.gms.internal.zzapt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaox<WebViewT extends zzapa & zzapr & zzapt> {
    private final zzaoz zza;
    private final WebViewT zzb;

    private zzaox(WebViewT webviewt, zzaoz zzaozVar) {
        this.zza = zzaozVar;
        this.zzb = webviewt;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.gms.internal.zzaoy, com.google.android.gms.internal.zzaoz] */
    public static zzaox<zzaof> zza(final zzaof zzaofVar) {
        return new zzaox<>(zzaofVar, new Object(zzaofVar) { // from class: com.google.android.gms.internal.zzaoy
            private final zzaof zza;

            {
                this.zza = zzaofVar;
            }
        });
    }
}
