package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzwc implements zzvo {
    private final zzacf zza;
    private final zzwf zzb;
    private final Context zzc;
    private final zzvq zze;
    private final boolean zzf;
    private final long zzg;
    private final long zzh;
    private final zzov zzi;
    private final boolean zzj;
    private final String zzk;
    private zzvt zzm;
    private final boolean zzo;
    private final Object zzd = new Object();
    private boolean zzl = false;
    private List<zzvw> zzn = new ArrayList();

    public zzwc(Context context, zzacf zzacfVar, zzwf zzwfVar, zzvq zzvqVar, boolean z, boolean z2, String str, long j, long j2, zzov zzovVar, boolean z3) {
        this.zzc = context;
        this.zza = zzacfVar;
        this.zzb = zzwfVar;
        this.zze = zzvqVar;
        this.zzf = z;
        this.zzj = z2;
        this.zzk = str;
        this.zzg = j;
        this.zzh = j2;
        this.zzi = zzovVar;
        this.zzo = z3;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0165 */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0163, code lost:
    
        r0 = th;
     */
    @Override // com.google.android.gms.internal.zzvo
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.zzvw zza(java.util.List<com.google.android.gms.internal.zzvp> r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwc.zza(java.util.List):com.google.android.gms.internal.zzvw");
    }

    @Override // com.google.android.gms.internal.zzvo
    public final void zza() {
        synchronized (this.zzd) {
            this.zzl = true;
            if (this.zzm != null) {
                this.zzm.zza();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzvo
    public final List<zzvw> zzb() {
        return this.zzn;
    }
}
