package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzay extends zzflm<zzay> {
    public String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;

    protected final int zza() {
        int iZza = super.zza();
        String str = this.zza;
        if (str != null) {
            iZza += zzflk.zzb(1, str);
        }
        String str2 = this.zzb;
        if (str2 != null) {
            iZza += zzflk.zzb(2, str2);
        }
        String str3 = this.zzc;
        if (str3 != null) {
            iZza += zzflk.zzb(3, str3);
        }
        String str4 = this.zzd;
        if (str4 != null) {
            iZza += zzflk.zzb(4, str4);
        }
        String str5 = this.zze;
        return str5 != null ? iZza + zzflk.zzb(5, str5) : iZza;
    }

    public final /* synthetic */ zzfls zza(zzflj zzfljVar) throws IOException {
        while (true) {
            int iZza = zzfljVar.zza();
            if (iZza == 0) {
                return this;
            }
            if (iZza == 10) {
                this.zza = zzfljVar.zze();
            } else if (iZza == 18) {
                this.zzb = zzfljVar.zze();
            } else if (iZza == 26) {
                this.zzc = zzfljVar.zze();
            } else if (iZza == 34) {
                this.zzd = zzfljVar.zze();
            } else if (iZza == 42) {
                this.zze = zzfljVar.zze();
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
        String str2 = this.zzb;
        if (str2 != null) {
            zzflkVar.zza(2, str2);
        }
        String str3 = this.zzc;
        if (str3 != null) {
            zzflkVar.zza(3, str3);
        }
        String str4 = this.zzd;
        if (str4 != null) {
            zzflkVar.zza(4, str4);
        }
        String str5 = this.zze;
        if (str5 != null) {
            zzflkVar.zza(5, str5);
        }
        super.zza(zzflkVar);
    }
}
