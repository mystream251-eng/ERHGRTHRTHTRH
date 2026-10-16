package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzduy extends zzfhu<zzduy, zza> implements zzfje {
    private static final zzduy zzf;
    private static volatile zzfjl<zzduy> zzg;
    private zzdva zzd;
    private int zze;

    public static final class zza extends zzfhu.zza<zzduy, zza> implements zzfje {
        private zza() {
            super(zzduy.zzf);
        }

        /* synthetic */ zza(zzduz zzduzVar) {
            this();
        }
    }

    static {
        zzduy zzduyVar = new zzduy();
        zzf = zzduyVar;
        zzduyVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzduyVar).zzb.zzc();
    }

    private zzduy() {
    }

    public static zzduy zza(zzfgs zzfgsVar) throws zzfie {
        return (zzduy) zzfhu.zza(zzf, zzfgsVar);
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        zzdva zzdvaVarZzc = this.zzd;
        int iZzf = 0;
        if (zzdvaVarZzc != null) {
            if (zzdvaVarZzc == null) {
                zzdvaVarZzc = zzdva.zzc();
            }
            iZzf = 0 + zzfhg.zzc(1, zzdvaVarZzc);
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
        zzdva.zza zzaVar;
        boolean z = false;
        zzduz zzduzVar = null;
        switch (zzduz.zza[i - 1]) {
            case 1:
                return new zzduy();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzduzVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzduy zzduyVar = (zzduy) obj2;
                this.zzd = zzhVar.zza(this.zzd, zzduyVar.zzd);
                this.zze = zzhVar.zza(this.zze != 0, this.zze, zzduyVar.zze != 0, zzduyVar.zze);
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
                                    zzdva zzdvaVar = this.zzd;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzdvaVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzdvaVar);
                                    zzaVar = (zzdva.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzdva zzdvaVar2 = (zzdva) zzfhbVar.zza(zzdva.zzc(), zzfhmVar);
                                this.zzd = zzdvaVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdvaVar2);
                                    this.zzd = (zzdva) zzaVar.zzc();
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
                    synchronized (zzduy.class) {
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
        zzdva zzdvaVarZzc = this.zzd;
        if (zzdvaVarZzc != null) {
            if (zzdvaVarZzc == null) {
                zzdvaVarZzc = zzdva.zzc();
            }
            zzfhgVar.zza(1, zzdvaVarZzc);
        }
        int i = this.zze;
        if (i != 0) {
            zzfhgVar.zzc(2, i);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzdva zzb() {
        zzdva zzdvaVar = this.zzd;
        return zzdvaVar == null ? zzdva.zzc() : zzdvaVar;
    }

    public final int zzc() {
        return this.zze;
    }
}
