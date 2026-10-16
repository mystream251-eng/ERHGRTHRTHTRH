package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.internal.js.zzn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzfu implements zzgc {
    private final Object zza = new Object();
    private final WeakHashMap<zzahd, zzfv> zzb = new WeakHashMap<>();
    private final ArrayList<zzfv> zzc = new ArrayList<>();
    private final Context zzd;
    private final zzala zze;
    private final zzn zzf;

    public zzfu(Context context, zzala zzalaVar) {
        this.zzd = context.getApplicationContext();
        this.zze = zzalaVar;
        this.zzf = new zzn(context.getApplicationContext(), zzalaVar, (String) zzlc.zzf().zza(zzoi.zza));
    }

    private final boolean zze(zzahd zzahdVar) {
        boolean z;
        synchronized (this.zza) {
            zzfv zzfvVar = this.zzb.get(zzahdVar);
            z = zzfvVar != null && zzfvVar.zzc();
        }
        return z;
    }

    public final void zza(zzahd zzahdVar) {
        synchronized (this.zza) {
            zzfv zzfvVar = this.zzb.get(zzahdVar);
            if (zzfvVar != null) {
                zzfvVar.zzb();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzgc
    public final void zza(zzfv zzfvVar) {
        synchronized (this.zza) {
            if (!zzfvVar.zzc()) {
                this.zzc.remove(zzfvVar);
                Iterator<Map.Entry<zzahd, zzfv>> it = this.zzb.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().getValue() == zzfvVar) {
                        it.remove();
                    }
                }
            }
        }
    }

    public final void zza(zzko zzkoVar, zzahd zzahdVar) {
        zza(zzkoVar, zzahdVar, zzahdVar.zzb.zzm());
    }

    public final void zza(zzko zzkoVar, zzahd zzahdVar, View view) {
        zza(zzkoVar, zzahdVar, new zzgb(view, zzahdVar), (zzaof) null);
    }

    public final void zza(zzko zzkoVar, zzahd zzahdVar, View view, zzaof zzaofVar) {
        zza(zzkoVar, zzahdVar, new zzgb(view, zzahdVar), zzaofVar);
    }

    public final void zza(zzko zzkoVar, zzahd zzahdVar, zzhf zzhfVar, zzaof zzaofVar) {
        zzfv zzfvVar;
        synchronized (this.zza) {
            if (zze(zzahdVar)) {
                zzfvVar = this.zzb.get(zzahdVar);
            } else {
                zzfv zzfvVar2 = new zzfv(this.zzd, zzkoVar, zzahdVar, this.zze, zzhfVar);
                zzfvVar2.zza(this);
                this.zzb.put(zzahdVar, zzfvVar2);
                this.zzc.add(zzfvVar2);
                zzfvVar = zzfvVar2;
            }
            zzfvVar.zza(zzaofVar != null ? new zzgd(zzfvVar, zzaofVar) : new zzgh(zzfvVar, this.zzf, this.zzd));
        }
    }

    public final void zzb(zzahd zzahdVar) {
        synchronized (this.zza) {
            zzfv zzfvVar = this.zzb.get(zzahdVar);
            if (zzfvVar != null) {
                zzfvVar.zzd();
            }
        }
    }

    public final void zzc(zzahd zzahdVar) {
        synchronized (this.zza) {
            zzfv zzfvVar = this.zzb.get(zzahdVar);
            if (zzfvVar != null) {
                zzfvVar.zze();
            }
        }
    }

    public final void zzd(zzahd zzahdVar) {
        synchronized (this.zza) {
            zzfv zzfvVar = this.zzb.get(zzahdVar);
            if (zzfvVar != null) {
                zzfvVar.zzf();
            }
        }
    }
}
