package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaol {
    public static zzalt<zzaof> zza(final Context context, final zzala zzalaVar, final String str, final zzcv zzcvVar, final zzv zzvVar) {
        return zzali.zza(zzali.zza((Object) null), new zzald(context, zzcvVar, zzalaVar, zzvVar, str) { // from class: com.google.android.gms.internal.zzaom
            private final Context zza;
            private final zzcv zzb;
            private final zzala zzc;
            private final zzv zzd;
            private final String zze;

            {
                this.zza = context;
                this.zzb = zzcvVar;
                this.zzc = zzalaVar;
                this.zzd = zzvVar;
                this.zze = str;
            }

            @Override // com.google.android.gms.internal.zzald
            public final zzalt zza(Object obj) throws zzaop {
                Context context2 = this.zza;
                zzcv zzcvVar2 = this.zzb;
                zzala zzalaVar2 = this.zzc;
                zzv zzvVar2 = this.zzd;
                String str2 = this.zze;
                zzaof zzaofVarZza = zzbt.zzf().zza(context2, zzaqa.zza(), "", false, false, zzcvVar2, zzalaVar2, null, null, zzvVar2, zziu.zza());
                final zzamc zzamcVarZza = zzamc.zza(zzaofVarZza);
                zzaofVarZza.zzv().zza(new zzapv(zzamcVarZza) { // from class: com.google.android.gms.internal.zzaon
                    private final zzamc zza;

                    {
                        this.zza = zzamcVarZza;
                    }

                    @Override // com.google.android.gms.internal.zzapv
                    public final void zza(zzaof zzaofVar, boolean z) {
                        this.zza.zza();
                    }
                });
                zzaofVarZza.loadUrl(str2);
                return zzamcVarZza;
            }
        }, zzaly.zza);
    }

    public final zzaof zza(Context context, zzaqa zzaqaVar, String str, boolean z, boolean z2, zzcv zzcvVar, zzala zzalaVar, zzov zzovVar, zzbm zzbmVar, zzv zzvVar, zziu zziuVar) throws zzaop {
        zzoi.zza(context);
        if (((Boolean) zzlc.zzf().zza(zzoi.zzbv)).booleanValue()) {
            return zzaqc.zza(context, zzaqaVar, str, z2, z, zzcvVar, zzalaVar, zzovVar, zzbmVar, zzvVar, zziuVar);
        }
        try {
            return (zzaof) zzakg.zzb(context, new zzaoo(this, context, zzaqaVar, str, z, z2, zzcvVar, zzalaVar, zzovVar, zzbmVar, zzvVar, zziuVar));
        } catch (Throwable th) {
            throw new zzaop("Webview initialization failed.", th);
        }
    }
}
