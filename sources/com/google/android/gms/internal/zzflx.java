package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzflx extends zzflm<zzflx> {
    public String zza = null;

    public zzflx() {
        this.zzax = null;
        this.zzay = -1;
    }

    protected final int zza() {
        int iZza = super.zza();
        String str = this.zza;
        return str != null ? iZza + zzflk.zzb(1, str) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                this.zza = zzfljVar.zze();
            } else if (!super.zza(zzfljVar, iZza)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflkVar) throws IOException {
        String str = this.zza;
        if (str != null) {
            zzflkVar.zza(1, str);
        }
        super.zza(zzflkVar);
    }
}
