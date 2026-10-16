package com.google.android.gms.ads.internal;

import android.os.Build;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabi;
import com.google.android.gms.internal.zzaec;
import com.google.android.gms.internal.zzagu;
import com.google.android.gms.internal.zzahi;
import com.google.android.gms.internal.zzahq;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaip;
import com.google.android.gms.internal.zzaiu;
import com.google.android.gms.internal.zzaiv;
import com.google.android.gms.internal.zzaiw;
import com.google.android.gms.internal.zzaix;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzaiz;
import com.google.android.gms.internal.zzaja;
import com.google.android.gms.internal.zzaji;
import com.google.android.gms.internal.zzakb;
import com.google.android.gms.internal.zzakc;
import com.google.android.gms.internal.zzakk;
import com.google.android.gms.internal.zzame;
import com.google.android.gms.internal.zzaml;
import com.google.android.gms.internal.zzanx;
import com.google.android.gms.internal.zzaol;
import com.google.android.gms.internal.zzhi;
import com.google.android.gms.internal.zzie;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzit;
import com.google.android.gms.internal.zzon;
import com.google.android.gms.internal.zzuo;
import com.google.android.gms.internal.zzuy;
import com.google.android.gms.internal.zzvy;
import com.google.android.gms.internal.zzzm;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzbt {
    private static final Object zza = new Object();
    private static zzbt zzb;
    private final zzba zzaa;
    private final zzit zzab;
    private final zzagu zzac;
    private final zzanx zzad;
    private final zzaml zzae;
    private final com.google.android.gms.ads.internal.js.zzb zzaf;
    private final zzaja zzag;
    private final zzakk zzah;
    private final zzahq zzai;
    private final com.google.android.gms.ads.internal.overlay.zza zzc = new com.google.android.gms.ads.internal.overlay.zza();
    private final zzabi zzd = new zzabi();
    private final com.google.android.gms.ads.internal.overlay.zzl zze = new com.google.android.gms.ads.internal.overlay.zzl();
    private final zzzm zzf = new zzzm();
    private final zzaij zzg = new zzaij();
    private final zzaol zzh = new zzaol();
    private final zzaip zzi;
    private final zzhi zzj;
    private final zzahi zzk;
    private final zzie zzl;
    private final zzif zzm;
    private final com.google.android.gms.common.util.zze zzn;
    private final zzac zzo;
    private final zzon zzp;
    private final zzaji zzq;
    private final zzaec zzr;
    private final zzame zzs;
    private final zzuo zzt;
    private final zzuy zzu;
    private final zzakb zzv;
    private final com.google.android.gms.ads.internal.overlay.zzu zzw;
    private final com.google.android.gms.ads.internal.overlay.zzv zzx;
    private final zzvy zzy;
    private final zzakc zzz;

    static {
        zzbt zzbtVar = new zzbt();
        synchronized (zza) {
            zzb = zzbtVar;
        }
    }

    protected zzbt() {
        int i = Build.VERSION.SDK_INT;
        this.zzi = i >= 21 ? new zzaiz() : i >= 19 ? new zzaiy() : i >= 18 ? new zzaiw() : i >= 17 ? new zzaiv() : i >= 16 ? new zzaix() : new zzaiu();
        this.zzj = new zzhi();
        this.zzk = new zzahi();
        this.zzai = new zzahq();
        this.zzl = new zzie();
        this.zzm = new zzif();
        this.zzn = com.google.android.gms.common.util.zzi.zzd();
        this.zzo = new zzac();
        this.zzp = new zzon();
        this.zzq = new zzaji();
        this.zzr = new zzaec();
        this.zzaf = new com.google.android.gms.ads.internal.js.zzb();
        this.zzs = new zzame();
        this.zzt = new zzuo();
        this.zzu = new zzuy();
        this.zzv = new zzakb();
        this.zzw = new com.google.android.gms.ads.internal.overlay.zzu();
        this.zzx = new com.google.android.gms.ads.internal.overlay.zzv();
        this.zzy = new zzvy();
        this.zzz = new zzakc();
        this.zzaa = new zzba();
        this.zzab = new zzit();
        this.zzac = new zzagu();
        this.zzad = new zzanx();
        this.zzae = new zzaml();
        this.zzag = new zzaja();
        this.zzah = new zzakk();
    }

    public static zzabi zza() {
        return zzae().zzd;
    }

    public static zzagu zzaa() {
        return zzae().zzac;
    }

    public static com.google.android.gms.ads.internal.js.zzb zzab() {
        return zzae().zzaf;
    }

    public static zzaja zzac() {
        return zzae().zzag;
    }

    public static zzakk zzad() {
        return zzae().zzah;
    }

    private static zzbt zzae() {
        zzbt zzbtVar;
        synchronized (zza) {
            zzbtVar = zzb;
        }
        return zzbtVar;
    }

    public static com.google.android.gms.ads.internal.overlay.zza zzb() {
        return zzae().zzc;
    }

    public static com.google.android.gms.ads.internal.overlay.zzl zzc() {
        return zzae().zze;
    }

    public static zzzm zzd() {
        return zzae().zzf;
    }

    public static zzaij zze() {
        return zzae().zzg;
    }

    public static zzaol zzf() {
        return zzae().zzh;
    }

    public static zzaip zzg() {
        return zzae().zzi;
    }

    public static zzhi zzh() {
        return zzae().zzj;
    }

    public static zzahi zzi() {
        return zzae().zzk;
    }

    public static zzahq zzj() {
        return zzae().zzai;
    }

    public static zzif zzk() {
        return zzae().zzm;
    }

    public static com.google.android.gms.common.util.zze zzl() {
        return zzae().zzn;
    }

    public static zzac zzm() {
        return zzae().zzo;
    }

    public static zzon zzn() {
        return zzae().zzp;
    }

    public static zzaji zzo() {
        return zzae().zzq;
    }

    public static zzaec zzp() {
        return zzae().zzr;
    }

    public static zzame zzq() {
        return zzae().zzs;
    }

    public static zzuo zzr() {
        return zzae().zzt;
    }

    public static zzuy zzs() {
        return zzae().zzu;
    }

    public static zzakb zzt() {
        return zzae().zzv;
    }

    public static com.google.android.gms.ads.internal.overlay.zzu zzu() {
        return zzae().zzw;
    }

    public static com.google.android.gms.ads.internal.overlay.zzv zzv() {
        return zzae().zzx;
    }

    public static zzvy zzw() {
        return zzae().zzy;
    }

    public static zzakc zzx() {
        return zzae().zzz;
    }

    public static zzanx zzy() {
        return zzae().zzad;
    }

    public static zzaml zzz() {
        return zzae().zzae;
    }
}
