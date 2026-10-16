package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzduw extends zzfhu<zzduw, zza> implements zzfje {
    private static final zzduw zzg;
    private static volatile zzfjl<zzduw> zzh;
    private int zzd;
    private zzdva zze;
    private zzfgs zzf = zzfgs.zza;

    public static final class zza extends zzfhu.zza<zzduw, zza> implements zzfje {
        private zza() {
            super(zzduw.zzg);
        }

        /* synthetic */ zza(zzdux zzduxVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzduw) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzdva zzdvaVar) {
            zzb();
            ((zzduw) this.zza).zza(zzdvaVar);
            return this;
        }

        public final zza zza(zzfgs zzfgsVar) {
            zzb();
            ((zzduw) this.zza).zzb(zzfgsVar);
            return this;
        }
    }

    static {
        zzduw zzduwVar = new zzduw();
        zzg = zzduwVar;
        zzduwVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzduwVar).zzb.zzc();
    }

    private zzduw() {
    }

    public static zzduw zza(zzfgs zzfgsVar) throws zzfie {
        return (zzduw) zzfhu.zza(zzg, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdva zzdvaVar) {
        if (zzdvaVar == null) {
            throw null;
        }
        this.zze = zzdvaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzfgs zzfgsVar) {
        if (zzfgsVar == null) {
            throw null;
        }
        this.zzf = zzfgsVar;
    }

    public static zza zze() {
        return (zza) ((zzfhu.zza) zzg.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzd;
        int iZzf = i2 != 0 ? 0 + zzfhg.zzf(1, i2) : 0;
        zzdva zzdvaVarZzc = this.zze;
        if (zzdvaVarZzc != null) {
            if (zzdvaVarZzc == null) {
                zzdvaVarZzc = zzdva.zzc();
            }
            iZzf += zzfhg.zzc(2, zzdvaVarZzc);
        }
        if (!this.zzf.zzb()) {
            iZzf += zzfhg.zzc(3, this.zzf);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdva.zza zzaVar;
        boolean z = false;
        zzdux zzduxVar = null;
        switch (zzdux.zza[i - 1]) {
            case 1:
                return new zzduw();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzduxVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzduw zzduwVar = (zzduw) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzduwVar.zzd != 0, zzduwVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzduwVar.zze);
                this.zzf = zzhVar.zza(this.zzf != zzfgs.zza, this.zzf, zzduwVar.zzf != zzfgs.zza, zzduwVar.zzf);
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
                                    zzdva zzdvaVar = this.zze;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzdvaVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzdvaVar);
                                    zzaVar = (zzdva.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzdva zzdvaVar2 = (zzdva) zzfhbVar.zza(zzdva.zzc(), zzfhmVar);
                                this.zze = zzdvaVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdvaVar2);
                                    this.zze = (zzdva) zzaVar.zzc();
                                }
                            } else if (iZza == 26) {
                                this.zzf = zzfhbVar.zzl();
                            } else if (!zza(iZza, zzfhbVar)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new RuntimeException((Throwable) new zzfie(e.getMessage()).zza(this));
                    } catch (zzfie e2) {
                        throw new RuntimeException((Throwable) e2.zza(this));
                    }
                }
                break;
                break;
            case 7:
                break;
            case 8:
                if (zzh == null) {
                    synchronized (zzduw.class) {
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
        int i = this.zzd;
        if (i != 0) {
            zzfhgVar.zzc(1, i);
        }
        zzdva zzdvaVarZzc = this.zze;
        if (zzdvaVarZzc != null) {
            if (zzdvaVarZzc == null) {
                zzdvaVarZzc = zzdva.zzc();
            }
            zzfhgVar.zza(2, zzdvaVarZzc);
        }
        if (!this.zzf.zzb()) {
            zzfhgVar.zza(3, this.zzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzdva zzc() {
        zzdva zzdvaVar = this.zze;
        return zzdvaVar == null ? zzdva.zzc() : zzdvaVar;
    }

    public final zzfgs zzd() {
        return this.zzf;
    }
}
