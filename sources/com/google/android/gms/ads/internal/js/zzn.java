package com.google.android.gms.ads.internal.js;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzajm;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaly;
import com.google.android.gms.internal.zzcv;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzn {
    private final Object zza;
    private final Context zzb;
    private final String zzc;
    private final zzala zzd;
    private zzajm<zzc> zze;
    private zzajm<zzc> zzf;
    private zzae zzg;
    private int zzh;

    public zzn(Context context, zzala zzalaVar, String str) {
        this.zza = new Object();
        this.zzh = 1;
        this.zzc = str;
        this.zzb = context.getApplicationContext();
        this.zzd = zzalaVar;
        this.zze = new zzz();
        this.zzf = new zzz();
    }

    public zzn(Context context, zzala zzalaVar, String str, zzajm<zzc> zzajmVar, zzajm<zzc> zzajmVar2) {
        this(context, zzalaVar, str);
        this.zze = zzajmVar;
        this.zzf = zzajmVar2;
    }

    protected final zzae zza(final zzcv zzcvVar) {
        final zzae zzaeVar = new zzae(this.zzf);
        zzaly.zza.execute(new Runnable(this, zzcvVar, zzaeVar) { // from class: com.google.android.gms.ads.internal.js.zzo
            private final zzn zza;
            private final zzcv zzb;
            private final zzae zzc;

            {
                this.zza = this;
                this.zzb = zzcvVar;
                this.zzc = zzaeVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc);
            }
        });
        zzaeVar.zza(new zzw(this, zzaeVar), new zzx(this, zzaeVar));
        return zzaeVar;
    }

    final /* synthetic */ void zza(zzae zzaeVar, zzc zzcVar) {
        synchronized (this.zza) {
            if (zzaeVar.zze() != -1 && zzaeVar.zze() != 1) {
                zzaeVar.zzd();
                Executor executor = zzaly.zza;
                zzcVar.getClass();
                executor.execute(zzr.zza(zzcVar));
                zzahw.zza("Could not receive loaded message in a timely manner. Rejecting.");
            }
        }
    }

    final /* synthetic */ void zza(zzcv zzcvVar, final zzae zzaeVar) {
        try {
            final zze zzeVar = new zze(this.zzb, this.zzd, zzcvVar, null);
            zzeVar.zza(new zzd(this, zzaeVar, zzeVar) { // from class: com.google.android.gms.ads.internal.js.zzp
                private final zzn zza;
                private final zzae zzb;
                private final zzc zzc;

                {
                    this.zza = this;
                    this.zzb = zzaeVar;
                    this.zzc = zzeVar;
                }

                @Override // com.google.android.gms.ads.internal.js.zzd
                public final void zza() {
                    final zzn zznVar = this.zza;
                    final zzae zzaeVar2 = this.zzb;
                    final zzc zzcVar = this.zzc;
                    zzaij.zza.postDelayed(new Runnable(zznVar, zzaeVar2, zzcVar) { // from class: com.google.android.gms.ads.internal.js.zzq
                        private final zzn zza;
                        private final zzae zzb;
                        private final zzc zzc;

                        {
                            this.zza = zznVar;
                            this.zzb = zzaeVar2;
                            this.zzc = zzcVar;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zza.zza(this.zzb, this.zzc);
                        }
                    }, zzy.zzb);
                }
            });
            zzeVar.zza("/jsLoaded", new zzs(this, zzaeVar, zzeVar));
            zzakf zzakfVar = new zzakf();
            zzt zztVar = new zzt(this, zzcvVar, zzeVar, zzakfVar);
            zzakfVar.zza(zztVar);
            zzeVar.zza("/requestReload", zztVar);
            if (this.zzc.endsWith(".js")) {
                zzeVar.zza(this.zzc);
            } else if (this.zzc.startsWith("<html>")) {
                zzeVar.zzc(this.zzc);
            } else {
                zzeVar.zzb(this.zzc);
            }
            zzaij.zza.postDelayed(new zzu(this, zzaeVar, zzeVar), zzy.zza);
        } catch (Throwable th) {
            zzahw.zzb("Error creating webview.", th);
            zzbt.zzi().zza(th, "SdkJavascriptFactory.loadJavascriptEngine");
            zzaeVar.zzd();
        }
    }

    public final zzaa zzb(zzcv zzcvVar) {
        synchronized (this.zza) {
            if (this.zzg != null && this.zzg.zze() != -1) {
                if (this.zzh == 0) {
                    return this.zzg.zza();
                }
                if (this.zzh == 1) {
                    this.zzh = 2;
                    zza((zzcv) null);
                    return this.zzg.zza();
                }
                if (this.zzh == 2) {
                    return this.zzg.zza();
                }
                return this.zzg.zza();
            }
            this.zzh = 2;
            zzae zzaeVarZza = zza((zzcv) null);
            this.zzg = zzaeVarZza;
            return zzaeVarZza.zza();
        }
    }
}
