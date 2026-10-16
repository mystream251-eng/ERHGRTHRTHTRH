package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwx extends zzfhu<zzdwx, zza> implements zzfje {
    private static final zzdwx zzf;
    private static volatile zzfjl<zzdwx> zzg;
    private int zzd;
    private zzdwz zze;

    public static final class zza extends zzfhu.zza<zzdwx, zza> implements zzfje {
        private zza() {
            super(zzdwx.zzf);
        }

        /* synthetic */ zza(zzdwy zzdwyVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdwx) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzdwz zzdwzVar) {
            zzb();
            ((zzdwx) this.zza).zza(zzdwzVar);
            return this;
        }
    }

    static {
        zzdwx zzdwxVar = new zzdwx();
        zzf = zzdwxVar;
        zzdwxVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwxVar).zzb.zzc();
    }

    private zzdwx() {
    }

    public static zzdwx zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdwx) zzfhu.zza(zzf, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdwz zzdwzVar) {
        if (zzdwzVar == null) {
            throw null;
        }
        this.zze = zzdwzVar;
    }

    public static zza zzd() {
        return (zza) ((zzfhu.zza) zzf.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzd;
        int iZzf = i2 != 0 ? 0 + zzfhg.zzf(1, i2) : 0;
        zzdwz zzdwzVarZzd = this.zze;
        if (zzdwzVarZzd != null) {
            if (zzdwzVarZzd == null) {
                zzdwzVarZzd = zzdwz.zzd();
            }
            iZzf += zzfhg.zzc(2, zzdwzVarZzd);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwz.zza zzaVar;
        boolean z = false;
        zzdwy zzdwyVar = null;
        switch (zzdwy.zza[i - 1]) {
            case 1:
                return new zzdwx();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzdwyVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwx zzdwxVar = (zzdwx) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdwxVar.zzd != 0, zzdwxVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzdwxVar.zze);
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
                            if (iZza == 8) {
                                this.zzd = zzfhbVar.zzm();
                            } else if (iZza == 18) {
                                if (this.zze != null) {
                                    zzdwz zzdwzVar = this.zze;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzdwzVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzdwzVar);
                                    zzaVar = (zzdwz.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzdwz zzdwzVar2 = (zzdwz) zzfhbVar.zza(zzdwz.zzd(), zzfhmVar);
                                this.zze = zzdwzVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdwzVar2);
                                    this.zze = (zzdwz) zzaVar.zzc();
                                }
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
                    synchronized (zzdwx.class) {
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
        int i = this.zzd;
        if (i != 0) {
            zzfhgVar.zzc(1, i);
        }
        zzdwz zzdwzVarZzd = this.zze;
        if (zzdwzVarZzd != null) {
            if (zzdwzVarZzd == null) {
                zzdwzVarZzd = zzdwz.zzd();
            }
            zzfhgVar.zza(2, zzdwzVarZzd);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzdwz zzc() {
        zzdwz zzdwzVar = this.zze;
        return zzdwzVar == null ? zzdwz.zzd() : zzdwzVar;
    }
}
