package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzacl;
import com.google.android.gms.internal.zzagq;
import com.google.android.gms.internal.zzaij;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzw {
    private final Context zza;
    private boolean zzb;
    private zzagq zzc;
    private zzacl zzd;

    public zzw(Context context, zzagq zzagqVar, zzacl zzaclVar) {
        this.zza = context;
        this.zzc = zzagqVar;
        this.zzd = zzaclVar;
        if (zzaclVar == null) {
            this.zzd = new zzacl();
        }
    }

    private final boolean zzc() {
        zzagq zzagqVar = this.zzc;
        return (zzagqVar != null && zzagqVar.zza().zzf) || this.zzd.zza;
    }

    public final void zza() {
        this.zzb = true;
    }

    public final void zza(String str) {
        if (zzc()) {
            if (str == null) {
                str = "";
            }
            zzagq zzagqVar = this.zzc;
            if (zzagqVar != null) {
                zzagqVar.zza(str, null, 3);
                return;
            }
            if (!this.zzd.zza || this.zzd.zzb == null) {
                return;
            }
            for (String str2 : this.zzd.zzb) {
                if (!TextUtils.isEmpty(str2)) {
                    String strReplace = str2.replace("{NAVIGATION_URL}", Uri.encode(str));
                    zzbt.zze();
                    zzaij.zzb(this.zza, "", strReplace);
                }
            }
        }
    }

    public final boolean zzb() {
        return !zzc() || this.zzb;
    }
}
