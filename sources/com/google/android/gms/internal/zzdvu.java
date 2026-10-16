package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdvu extends zzfhu<zzdvu, zza> implements zzfje {
    private static final zzdvu zzg;
    private static volatile zzfjl<zzdvu> zzh;
    private int zzd;
    private int zze;
    private zzfgs zzf = zzfgs.zza;

    public static final class zza extends zzfhu.zza<zzdvu, zza> implements zzfje {
        private zza() {
            super(zzdvu.zzg);
        }

        /* synthetic */ zza(zzdvv zzdvvVar) {
            this();
        }
    }

    static {
        zzdvu zzdvuVar = new zzdvu();
        zzg = zzdvuVar;
        zzdvuVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdvuVar).zzb.zzc();
    }

    private zzdvu() {
    }

    public static zzdvu zze() {
        return zzg;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int iZzh = this.zzd != zzdvw.UNKNOWN_CURVE.zza() ? 0 + zzfhg.zzh(1, this.zzd) : 0;
        if (this.zze != zzdvy.UNKNOWN_HASH.zza()) {
            iZzh += zzfhg.zzh(2, this.zze);
        }
        if (!this.zzf.zzb()) {
            iZzh += zzfhg.zzc(11, this.zzf);
        }
        int iZze = iZzh + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdvv zzdvvVar = null;
        boolean z = false;
        switch (zzdvv.zza[i - 1]) {
            case 1:
                return new zzdvu();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzdvvVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdvu zzdvuVar = (zzdvu) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdvuVar.zzd != 0, zzdvuVar.zzd);
                this.zze = zzhVar.zza(this.zze != 0, this.zze, zzdvuVar.zze != 0, zzdvuVar.zze);
                this.zzf = zzhVar.zza(this.zzf != zzfgs.zza, this.zzf, zzdvuVar.zzf != zzfgs.zza, zzdvuVar.zzf);
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                if (((zzfhm) obj2) == null) {
                    throw null;
                }
                while (!z) {
                    try {
                        int iZza = zzfhbVar.zza();
                        if (iZza != 0) {
                            if (iZza == 8) {
                                this.zzd = zzfhbVar.zzn();
                            } else if (iZza == 16) {
                                this.zze = zzfhbVar.zzn();
                            } else if (iZza == 90) {
                                this.zzf = zzfhbVar.zzl();
                            } else if (!zza(iZza, zzfhbVar)) {
                            }
                        }
                        z = true;
                    } catch (zzfie e) {
                        throw new RuntimeException((Throwable) e.zza(this));
                    } catch (IOException e2) {
                        throw new RuntimeException((Throwable) new zzfie(e2.getMessage()).zza(this));
                    }
                }
                break;
                break;
            case 7:
                break;
            case 8:
                if (zzh == null) {
                    synchronized (zzdvu.class) {
                        if (zzh == null) {
                            zzh = new zzfhu.zzb(zzg);
                        }
                        break;
                    }
                }
                return zzh;
            case 9:
                return (byte) 1;
            case 10:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
        return zzg;
    }

    public final void zza(zzfhg zzfhgVar) throws IOException {
        if (this.zzd != zzdvw.UNKNOWN_CURVE.zza()) {
            zzfhgVar.zzb(1, this.zzd);
        }
        if (this.zze != zzdvy.UNKNOWN_HASH.zza()) {
            zzfhgVar.zzb(2, this.zze);
        }
        if (!this.zzf.zzb()) {
            zzfhgVar.zza(11, this.zzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzdvw zzb() {
        zzdvw zzdvwVarZza = zzdvw.zza(this.zzd);
        return zzdvwVarZza == null ? zzdvw.UNRECOGNIZED : zzdvwVarZza;
    }

    public final zzdvy zzc() {
        zzdvy zzdvyVarZza = zzdvy.zza(this.zze);
        return zzdvyVarZza == null ? zzdvy.UNRECOGNIZED : zzdvyVarZza;
    }

    public final zzfgs zzd() {
        return this.zzf;
    }
}
