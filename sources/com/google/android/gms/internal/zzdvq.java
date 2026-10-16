package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdvq extends zzfhu<zzdvq, zza> implements zzfje {
    private static final zzdvq zzg;
    private static volatile zzfjl<zzdvq> zzh;
    private int zzd;
    private zzdvs zze;
    private zzfgs zzf = zzfgs.zza;

    public static final class zza extends zzfhu.zza<zzdvq, zza> implements zzfje {
        private zza() {
            super(zzdvq.zzg);
        }

        /* synthetic */ zza(zzdvr zzdvrVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdvq) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzdvs zzdvsVar) {
            zzb();
            ((zzdvq) this.zza).zza(zzdvsVar);
            return this;
        }

        public final zza zza(zzfgs zzfgsVar) {
            zzb();
            ((zzdvq) this.zza).zzb(zzfgsVar);
            return this;
        }
    }

    static {
        zzdvq zzdvqVar = new zzdvq();
        zzg = zzdvqVar;
        zzdvqVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdvqVar).zzb.zzc();
    }

    private zzdvq() {
    }

    public static zzdvq zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdvq) zzfhu.zza(zzg, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdvs zzdvsVar) {
        if (zzdvsVar == null) {
            throw null;
        }
        this.zze = zzdvsVar;
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
        zzdvs zzdvsVarZzg = this.zze;
        if (zzdvsVarZzg != null) {
            if (zzdvsVarZzg == null) {
                zzdvsVarZzg = zzdvs.zzg();
            }
            iZzf += zzfhg.zzc(2, zzdvsVarZzg);
        }
        if (!this.zzf.zzb()) {
            iZzf += zzfhg.zzc(3, this.zzf);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdvs.zza zzaVar;
        boolean z = false;
        zzdvr zzdvrVar = null;
        switch (zzdvr.zza[i - 1]) {
            case 1:
                return new zzdvq();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzdvrVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdvq zzdvqVar = (zzdvq) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdvqVar.zzd != 0, zzdvqVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzdvqVar.zze);
                this.zzf = zzhVar.zza(this.zzf != zzfgs.zza, this.zzf, zzdvqVar.zzf != zzfgs.zza, zzdvqVar.zzf);
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
                                    zzdvs zzdvsVar = this.zze;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzdvsVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzdvsVar);
                                    zzaVar = (zzdvs.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzdvs zzdvsVar2 = (zzdvs) zzfhbVar.zza(zzdvs.zzg(), zzfhmVar);
                                this.zze = zzdvsVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdvsVar2);
                                    this.zze = (zzdvs) zzaVar.zzc();
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
                    synchronized (zzdvq.class) {
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
        zzdvs zzdvsVarZzg = this.zze;
        if (zzdvsVarZzg != null) {
            if (zzdvsVarZzg == null) {
                zzdvsVarZzg = zzdvs.zzg();
            }
            zzfhgVar.zza(2, zzdvsVarZzg);
        }
        if (!this.zzf.zzb()) {
            zzfhgVar.zza(3, this.zzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzdvs zzc() {
        zzdvs zzdvsVar = this.zze;
        return zzdvsVar == null ? zzdvs.zzg() : zzdvsVar;
    }

    public final zzfgs zzd() {
        return this.zzf;
    }
}
