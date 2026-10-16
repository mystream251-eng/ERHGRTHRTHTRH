package com.google.android.gms.ads.internal.js;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzajm;
import com.google.android.gms.internal.zzamh;
import com.google.android.gms.internal.zzamj;

/* JADX INFO: loaded from: classes2.dex */
public final class zzae extends zzamj<zzc> {
    private zzajm<zzc> zzb;
    private final Object zza = new Object();
    private boolean zzc = false;
    private int zzd = 0;

    public zzae(zzajm<zzc> zzajmVar) {
        this.zzb = zzajmVar;
    }

    private final void zzf() {
        synchronized (this.zza) {
            zzbq.zza(this.zzd >= 0);
            if (this.zzc && this.zzd == 0) {
                zzahw.zza("No reference is left (including root). Cleaning up engine.");
                zza(new zzah(this), new zzamh());
            } else {
                zzahw.zza("There are still references to the engine. Not destroying.");
            }
        }
    }

    public final zzaa zza() {
        zzaa zzaaVar = new zzaa(this);
        synchronized (this.zza) {
            zza(new zzaf(this, zzaaVar), new zzag(this, zzaaVar));
            zzbq.zza(this.zzd >= 0);
            this.zzd++;
        }
        return zzaaVar;
    }

    protected final void zzb() {
        synchronized (this.zza) {
            zzbq.zza(this.zzd > 0);
            zzahw.zza("Releasing 1 reference for JS Engine");
            this.zzd--;
            zzf();
        }
    }

    public final void zzc() {
        synchronized (this.zza) {
            zzbq.zza(this.zzd >= 0);
            zzahw.zza("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzc = true;
            zzf();
        }
    }
}
