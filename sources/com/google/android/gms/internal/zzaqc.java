package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbm;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaqc {
    public static zzaof zza(final Context context, final zzaqa zzaqaVar, final String str, final boolean z, final boolean z2, final zzcv zzcvVar, final zzala zzalaVar, final zzov zzovVar, final zzbm zzbmVar, final zzv zzvVar, final zziu zziuVar) throws zzaop {
        try {
            return (zzaof) zzakg.zzb(null, new Callable(context, zzaqaVar, str, z, z2, zzcvVar, zzalaVar, zzovVar, zzbmVar, zzvVar, zziuVar) { // from class: com.google.android.gms.internal.zzaqd
                private final Context zza;
                private final zzaqa zzb;
                private final String zzc;
                private final boolean zzd;
                private final boolean zze;
                private final zzcv zzf;
                private final zzala zzg;
                private final zzov zzh;
                private final zzbm zzi;
                private final zzv zzj;
                private final zziu zzk;

                {
                    this.zza = context;
                    this.zzb = zzaqaVar;
                    this.zzc = str;
                    this.zzd = z;
                    this.zze = z2;
                    this.zzf = zzcvVar;
                    this.zzg = zzalaVar;
                    this.zzh = zzovVar;
                    this.zzi = zzbmVar;
                    this.zzj = zzvVar;
                    this.zzk = zziuVar;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Context context2 = this.zza;
                    zzaqa zzaqaVar2 = this.zzb;
                    String str2 = this.zzc;
                    boolean z3 = this.zzd;
                    boolean z4 = this.zze;
                    zzaqe zzaqeVarZza = zzaqe.zza(context2, zzaqaVar2, str2, z3, z4, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk);
                    zzaqeVarZza.setWebViewClient(zzbt.zzg().zza(zzaqeVarZza, z4));
                    zzaqeVarZza.setWebChromeClient(zzbt.zzg().zzc((zzaof) zzaqeVarZza));
                    return new zzaoq(zzaqeVarZza);
                }
            });
        } catch (Throwable th) {
            zzbt.zzi().zza(th, "AdWebViewFactory.newAdWebView2");
            throw new zzaop("Webview initialization failed.", th);
        }
    }
}
