package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwl extends zzfhu<zzdwl, zza> implements zzfje {
    private static final zzdwl zzg;
    private static volatile zzfjl<zzdwl> zzh;
    private String zzd = "";
    private zzfgs zze = zzfgs.zza;
    private int zzf;

    public static final class zza extends zzfhu.zza<zzdwl, zza> implements zzfje {
        private zza() {
            super(zzdwl.zzg);
        }

        /* synthetic */ zza(zzdwm zzdwmVar) {
            this();
        }
    }

    static {
        zzdwl zzdwlVar = new zzdwl();
        zzg = zzdwlVar;
        zzdwlVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwlVar).zzb.zzc();
    }

    private zzdwl() {
    }

    public static zzdwl zzd() {
        return zzg;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int iZzb = this.zzd.isEmpty() ? 0 : 0 + zzfhg.zzb(1, this.zzd);
        if (!this.zze.zzb()) {
            iZzb += zzfhg.zzc(2, this.zze);
        }
        if (this.zzf != zzdxb.UNKNOWN_PREFIX.zza()) {
            iZzb += zzfhg.zzh(3, this.zzf);
        }
        int iZze = iZzb + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwm zzdwmVar = null;
        boolean z = false;
        switch (zzdwm.zza[i - 1]) {
            case 1:
                return new zzdwl();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzdwmVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwl zzdwlVar = (zzdwl) obj2;
                this.zzd = zzhVar.zza(!this.zzd.isEmpty(), this.zzd, !zzdwlVar.zzd.isEmpty(), zzdwlVar.zzd);
                this.zze = zzhVar.zza(this.zze != zzfgs.zza, this.zze, zzdwlVar.zze != zzfgs.zza, zzdwlVar.zze);
                this.zzf = zzhVar.zza(this.zzf != 0, this.zzf, zzdwlVar.zzf != 0, zzdwlVar.zzf);
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
                            if (iZza == 10) {
                                this.zzd = zzfhbVar.zzk();
                            } else if (iZza == 18) {
                                this.zze = zzfhbVar.zzl();
                            } else if (iZza == 24) {
                                this.zzf = zzfhbVar.zzn();
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
                    synchronized (zzdwl.class) {
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
        if (!this.zzd.isEmpty()) {
            zzfhgVar.zza(1, this.zzd);
        }
        if (!this.zze.zzb()) {
            zzfhgVar.zza(2, this.zze);
        }
        if (this.zzf != zzdxb.UNKNOWN_PREFIX.zza()) {
            zzfhgVar.zzb(3, this.zzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final String zzb() {
        return this.zzd;
    }

    public final zzfgs zzc() {
        return this.zze;
    }
}
