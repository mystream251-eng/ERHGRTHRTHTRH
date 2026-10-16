package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwe extends zzfhu<zzdwe, zza> implements zzfje {
    private static final zzdwe zzf;
    private static volatile zzfjl<zzdwe> zzg;
    private int zzd;
    private int zze;

    public static final class zza extends zzfhu.zza<zzdwe, zza> implements zzfje {
        private zza() {
            super(zzdwe.zzf);
        }

        /* synthetic */ zza(zzdwf zzdwfVar) {
            this();
        }
    }

    static {
        zzdwe zzdweVar = new zzdwe();
        zzf = zzdweVar;
        zzdweVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdweVar).zzb.zzc();
    }

    private zzdwe() {
    }

    public static zzdwe zzd() {
        return zzf;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int iZzh = this.zzd != zzdvy.UNKNOWN_HASH.zza() ? 0 + zzfhg.zzh(1, this.zzd) : 0;
        int i2 = this.zze;
        if (i2 != 0) {
            iZzh += zzfhg.zzf(2, i2);
        }
        int iZze = iZzh + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwf zzdwfVar = null;
        boolean z = false;
        switch (zzdwf.zza[i - 1]) {
            case 1:
                return new zzdwe();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzdwfVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwe zzdweVar = (zzdwe) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdweVar.zzd != 0, zzdweVar.zzd);
                this.zze = zzhVar.zza(this.zze != 0, this.zze, zzdweVar.zze != 0, zzdweVar.zze);
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                if (((zzfhm) obj2) == null) {
                    throw null;
                }
                while (!z) {
                    try {
                        try {
                            int iZza = zzfhbVar.zza();
                            if (iZza != 0) {
                                if (iZza == 8) {
                                    this.zzd = zzfhbVar.zzn();
                                } else if (iZza == 16) {
                                    this.zze = zzfhbVar.zzm();
                                } else if (!zza(iZza, zzfhbVar)) {
                                }
                            }
                            z = true;
                        } catch (zzfie e) {
                            throw new RuntimeException((Throwable) e.zza(this));
                        }
                    } catch (IOException e2) {
                        throw new RuntimeException((Throwable) new zzfie(e2.getMessage()).zza(this));
                    }
                }
                break;
                break;
            case 7:
                break;
            case 8:
                if (zzg == null) {
                    synchronized (zzdwe.class) {
                        if (zzg == null) {
                            zzg = new zzfhu.zzb(zzf);
                        }
                        break;
                    }
                }
                return zzg;
            case 9:
                return (byte) 1;
            case 10:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
        return zzf;
    }

    public final void zza(zzfhg zzfhgVar) throws IOException {
        if (this.zzd != zzdvy.UNKNOWN_HASH.zza()) {
            zzfhgVar.zzb(1, this.zzd);
        }
        int i = this.zze;
        if (i != 0) {
            zzfhgVar.zzc(2, i);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzdvy zzb() {
        zzdvy zzdvyVarZza = zzdvy.zza(this.zzd);
        return zzdvyVarZza == null ? zzdvy.UNRECOGNIZED : zzdvyVarZza;
    }

    public final int zzc() {
        return this.zze;
    }
}
