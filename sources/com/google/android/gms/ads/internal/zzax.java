package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaer;
import com.google.android.gms.internal.zzafy;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaly;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzmi;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzvp;
import com.google.android.gms.internal.zzvq;
import com.google.android.gms.internal.zzwi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzax extends zzmi {
    private static final Object zzb = new Object();
    private static zzax zzc;
    private final Context zza;
    private final Object zzd = new Object();
    private boolean zze = false;
    private zzala zzf;

    private zzax(Context context, zzala zzalaVar) {
        this.zza = context;
        this.zzf = zzalaVar;
    }

    public static zzax zza(Context context, zzala zzalaVar) {
        zzax zzaxVar;
        synchronized (zzb) {
            if (zzc == null) {
                zzc = new zzax(context.getApplicationContext(), zzalaVar);
            }
            zzaxVar = zzc;
        }
        return zzaxVar;
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza() {
        synchronized (zzb) {
            if (this.zze) {
                zzahw.zze("Mobile ads is initialized already.");
                return;
            }
            this.zze = true;
            zzoi.zza(this.zza);
            zzbt.zzi().zza(this.zza, this.zzf);
            zzbt.zzk().zza(this.zza);
        }
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(float f) {
        zzbt.zzac().zza(f);
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            zzahw.zzc("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
        if (context == null) {
            zzahw.zzc("Context is null. Failed to open debug menu.");
            return;
        }
        zzajc zzajcVar = new zzajc(context);
        zzajcVar.zza(str);
        zzajcVar.zzb(this.zzf.zza);
        zzajcVar.zza();
    }

    final /* synthetic */ void zza(Runnable runnable) {
        Context context = this.zza;
        com.google.android.gms.common.internal.zzbq.zzb("Adapters must be initialized on the main thread.");
        Map<String, zzvq> mapZze = zzbt.zzi().zzl().zzh().zze();
        if (mapZze == null || mapZze.isEmpty()) {
            return;
        }
        if (runnable != null) {
            try {
                runnable.run();
            } catch (Throwable th) {
                zzahw.zzc("Could not initialize rewarded ads.", th);
                return;
            }
        }
        zzaer zzaerVarZzaq = zzaer.zzaq();
        if (zzaerVarZzaq != null) {
            Collection<zzvq> collectionValues = mapZze.values();
            HashMap map = new HashMap();
            IObjectWrapper iObjectWrapperZza = com.google.android.gms.dynamic.zzn.zza(context);
            Iterator<zzvq> it = collectionValues.iterator();
            while (it.hasNext()) {
                for (zzvp zzvpVar : it.next().zza) {
                    String str = zzvpVar.zzj;
                    for (String str2 : zzvpVar.zzc) {
                        if (!map.containsKey(str2)) {
                            map.put(str2, new ArrayList());
                        }
                        if (str != null) {
                            ((Collection) map.get(str2)).add(str);
                        }
                    }
                }
            }
            for (Map.Entry entry : map.entrySet()) {
                String str3 = (String) entry.getKey();
                try {
                    zzafy zzafyVarZzb = zzaerVarZzaq.zzb(str3);
                    if (zzafyVarZzb != null) {
                        zzwi zzwiVarZza = zzafyVarZzb.zza();
                        if (!zzwiVarZza.zzg() && zzwiVarZza.zzm()) {
                            zzwiVarZza.zza(iObjectWrapperZza, zzafyVarZzb.zzb(), (List<String>) entry.getValue());
                            String strValueOf = String.valueOf(str3);
                            zzahw.zzb(strValueOf.length() != 0 ? "Initialized rewarded video mediation adapter ".concat(strValueOf) : new String("Initialized rewarded video mediation adapter "));
                        }
                    }
                } catch (Throwable th2) {
                    StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 56);
                    sb.append("Failed to initialize rewarded video mediation adapter \"");
                    sb.append(str3);
                    sb.append("\"");
                    zzahw.zzc(sb.toString(), th2);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(String str) {
        zzoi.zza(this.zza);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (((Boolean) zzlc.zzf().zza(zzoi.zzfi)).booleanValue()) {
            zzbt.zzm().zza(this.zza, this.zzf, str, null);
        }
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(String str, IObjectWrapper iObjectWrapper) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        zzoi.zza(this.zza);
        boolean zBooleanValue = ((Boolean) zzlc.zzf().zza(zzoi.zzfi)).booleanValue() | ((Boolean) zzlc.zzf().zza(zzoi.zzbx)).booleanValue();
        Runnable runnable = null;
        if (((Boolean) zzlc.zzf().zza(zzoi.zzbx)).booleanValue()) {
            zBooleanValue = true;
            final Runnable runnable2 = (Runnable) com.google.android.gms.dynamic.zzn.zza(iObjectWrapper);
            runnable = new Runnable(this, runnable2) { // from class: com.google.android.gms.ads.internal.zzay
                private final zzax zza;
                private final Runnable zzb;

                {
                    this.zza = this;
                    this.zzb = runnable2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    final zzax zzaxVar = this.zza;
                    final Runnable runnable3 = this.zzb;
                    zzaly.zza.execute(new Runnable(zzaxVar, runnable3) { // from class: com.google.android.gms.ads.internal.zzaz
                        private final zzax zza;
                        private final Runnable zzb;

                        {
                            this.zza = zzaxVar;
                            this.zzb = runnable3;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zza.zza(this.zzb);
                        }
                    });
                }
            };
        }
        if (zBooleanValue) {
            zzbt.zzm().zza(this.zza, this.zzf, str, runnable);
        }
    }

    @Override // com.google.android.gms.internal.zzmh
    public final void zza(boolean z) {
        zzbt.zzac().zza(z);
    }

    @Override // com.google.android.gms.internal.zzmh
    public final float zzb() {
        return zzbt.zzac().zza();
    }

    @Override // com.google.android.gms.internal.zzmh
    public final boolean zzc() {
        return zzbt.zzac().zzb();
    }
}
