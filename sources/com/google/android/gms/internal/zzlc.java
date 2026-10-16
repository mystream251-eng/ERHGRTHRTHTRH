package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzlc {
    private static final Object zza = new Object();
    private static zzlc zzb;
    private final zzako zzc = new zzako();
    private final zzks zzd = new zzks(new zzkj(), new zzki(), new zznc(), new zzso(), new zzafg(), new zzyp(), new zzsp());
    private final String zze = zzako.zzc();
    private final zzoe zzf = new zzoe();
    private final zzof zzg = new zzof();
    private final zzog zzh = new zzog();

    static {
        zzlc zzlcVar = new zzlc();
        synchronized (zza) {
            zzb = zzlcVar;
        }
    }

    protected zzlc() {
    }

    public static zzako zza() {
        return zzg().zzc;
    }

    public static zzks zzb() {
        return zzg().zzd;
    }

    public static String zzc() {
        return zzg().zze;
    }

    public static zzof zzd() {
        return zzg().zzg;
    }

    public static zzoe zze() {
        return zzg().zzf;
    }

    public static zzog zzf() {
        return zzg().zzh;
    }

    private static zzlc zzg() {
        zzlc zzlcVar;
        synchronized (zza) {
            zzlcVar = zzb;
        }
        return zzlcVar;
    }
}
