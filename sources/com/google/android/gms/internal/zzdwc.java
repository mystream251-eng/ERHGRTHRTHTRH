package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwc extends zzfhu<zzdwc, zza> implements zzfje {
    private static final zzdwc zzf;
    private static volatile zzfjl<zzdwc> zzg;
    private zzdwe zzd;
    private int zze;

    public static final class zza extends zzfhu.zza<zzdwc, zza> implements zzfje {
        private zza() {
            super(zzdwc.zzf);
        }

        /* synthetic */ zza(zzdwd zzdwdVar) {
            this();
        }
    }

    static {
        zzdwc zzdwcVar = new zzdwc();
        zzf = zzdwcVar;
        zzdwcVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwcVar).zzb.zzc();
    }

    private zzdwc() {
    }

    public static zzdwc zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdwc) zzfhu.zza(zzf, zzfgsVar);
    }

    public static zzdwc zzd() {
        return zzf;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        zzdwe zzdweVarZzd = this.zzd;
        int iZzf = 0;
        if (zzdweVarZzd != null) {
            if (zzdweVarZzd == null) {
                zzdweVarZzd = zzdwe.zzd();
            }
            iZzf = 0 + zzfhg.zzc(1, zzdweVarZzd);
        }
        int i2 = this.zze;
        if (i2 != 0) {
            iZzf += zzfhg.zzf(2, i2);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwe.zza zzaVar;
        boolean z = false;
        zzdwd zzdwdVar = null;
        switch (zzdwd.zza[i - 1]) {
            case 1:
                return new zzdwc();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzdwdVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwc zzdwcVar = (zzdwc) obj2;
                this.zzd = zzhVar.zza(this.zzd, zzdwcVar.zzd);
                this.zze = zzhVar.zza(this.zze != 0, this.zze, zzdwcVar.zze != 0, zzdwcVar.zze);
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                zzfhm zzfhmVar = (zzfhm) obj2;
                if (zzfhmVar == null) {
                    throw null;
                }
                while (!z) {
                    try {
                        int iZza = zzfhbVar.zza();
                        if (iZza != 0) {
                            if (iZza == 10) {
                                if (this.zzd != null) {
                                    zzdwe zzdweVar = this.zzd;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzdweVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzdweVar);
                                    zzaVar = (zzdwe.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzdwe zzdweVar2 = (zzdwe) zzfhbVar.zza(zzdwe.zzd(), zzfhmVar);
                                this.zzd = zzdweVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdweVar2);
                                    this.zzd = (zzdwe) zzaVar.zzc();
                                }
                            } else if (iZza == 16) {
                                this.zze = zzfhbVar.zzm();
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
                if (zzg == null) {
                    synchronized (zzdwc.class) {
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
        zzdwe zzdweVarZzd = this.zzd;
        if (zzdweVarZzd != null) {
            if (zzdweVarZzd == null) {
                zzdweVarZzd = zzdwe.zzd();
            }
            zzfhgVar.zza(1, zzdweVarZzd);
        }
        int i = this.zze;
        if (i != 0) {
            zzfhgVar.zzc(2, i);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzdwe zzb() {
        zzdwe zzdweVar = this.zzd;
        return zzdweVar == null ? zzdwe.zzd() : zzdweVar;
    }

    public final int zzc() {
        return this.zze;
    }
}
