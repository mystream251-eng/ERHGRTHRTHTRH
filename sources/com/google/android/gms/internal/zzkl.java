package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzkl {
    private long zza;
    private Bundle zzb;
    private int zzc;
    private List<String> zzd;
    private boolean zze;
    private int zzf;
    private boolean zzg;
    private String zzh;
    private zzno zzi;
    private Location zzj;
    private String zzk;
    private Bundle zzl;
    private Bundle zzm;
    private List<String> zzn;
    private String zzo;
    private String zzp;
    private boolean zzq;

    public zzkl() {
        this.zza = -1L;
        this.zzb = new Bundle();
        this.zzc = -1;
        this.zzd = new ArrayList();
        this.zze = false;
        this.zzf = -1;
        this.zzg = false;
        this.zzh = null;
        this.zzi = null;
        this.zzj = null;
        this.zzk = null;
        this.zzl = new Bundle();
        this.zzm = new Bundle();
        this.zzn = new ArrayList();
        this.zzo = null;
        this.zzp = null;
        this.zzq = false;
    }

    public zzkl(zzkk zzkkVar) {
        this.zza = zzkkVar.zzb;
        this.zzb = zzkkVar.zzc;
        this.zzc = zzkkVar.zzd;
        this.zzd = zzkkVar.zze;
        this.zze = zzkkVar.zzf;
        this.zzf = zzkkVar.zzg;
        this.zzg = zzkkVar.zzh;
        this.zzh = zzkkVar.zzi;
        this.zzi = zzkkVar.zzj;
        this.zzj = zzkkVar.zzk;
        this.zzk = zzkkVar.zzl;
        this.zzl = zzkkVar.zzm;
        this.zzm = zzkkVar.zzn;
        this.zzn = zzkkVar.zzo;
        this.zzo = zzkkVar.zzp;
        this.zzp = zzkkVar.zzq;
    }

    public final zzkk zza() {
        return new zzkk(7, this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, false);
    }

    public final zzkl zza(Location location) {
        this.zzj = null;
        return this;
    }
}
