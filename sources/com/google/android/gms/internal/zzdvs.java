package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdvs extends zzfhu<zzdvs, zza> implements zzfje {
    private static final zzdvs zzh;
    private static volatile zzfjl<zzdvs> zzi;
    private int zzd;
    private zzdvo zze;
    private zzfgs zzf = zzfgs.zza;
    private zzfgs zzg = zzfgs.zza;

    public static final class zza extends zzfhu.zza<zzdvs, zza> implements zzfje {
        private zza() {
            super(zzdvs.zzh);
        }

        /* synthetic */ zza(zzdvt zzdvtVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdvs) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzdvo zzdvoVar) {
            zzb();
            ((zzdvs) this.zza).zza(zzdvoVar);
            return this;
        }

        public final zza zza(zzfgs zzfgsVar) {
            zzb();
            ((zzdvs) this.zza).zzb(zzfgsVar);
            return this;
        }

        public final zza zzb(zzfgs zzfgsVar) {
            zzb();
            ((zzdvs) this.zza).zzc(zzfgsVar);
            return this;
        }
    }

    static {
        zzdvs zzdvsVar = new zzdvs();
        zzh = zzdvsVar;
        zzdvsVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdvsVar).zzb.zzc();
    }

    private zzdvs() {
    }

    public static zzdvs zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdvs) zzfhu.zza(zzh, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdvo zzdvoVar) {
        if (zzdvoVar == null) {
            throw null;
        }
        this.zze = zzdvoVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzfgs zzfgsVar) {
        if (zzfgsVar == null) {
            throw null;
        }
        this.zzf = zzfgsVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(zzfgs zzfgsVar) {
        if (zzfgsVar == null) {
            throw null;
        }
        this.zzg = zzfgsVar;
    }

    public static zza zzf() {
        return (zza) ((zzfhu.zza) zzh.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
    }

    public static zzdvs zzg() {
        return zzh;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzd;
        int iZzf = i2 != 0 ? 0 + zzfhg.zzf(1, i2) : 0;
        zzdvo zzdvoVarZze = this.zze;
        if (zzdvoVarZze != null) {
            if (zzdvoVarZze == null) {
                zzdvoVarZze = zzdvo.zze();
            }
            iZzf += zzfhg.zzc(2, zzdvoVarZze);
        }
        if (!this.zzf.zzb()) {
            iZzf += zzfhg.zzc(3, this.zzf);
        }
        if (!this.zzg.zzb()) {
            iZzf += zzfhg.zzc(4, this.zzg);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdvo.zza zzaVar;
        boolean z = false;
        zzdvt zzdvtVar = null;
        switch (zzdvt.zza[i - 1]) {
            case 1:
                return new zzdvs();
            case 2:
                return zzh;
            case 3:
                return null;
            case 4:
                return new zza(zzdvtVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdvs zzdvsVar = (zzdvs) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdvsVar.zzd != 0, zzdvsVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzdvsVar.zze);
                this.zzf = zzhVar.zza(this.zzf != zzfgs.zza, this.zzf, zzdvsVar.zzf != zzfgs.zza, zzdvsVar.zzf);
                this.zzg = zzhVar.zza(this.zzg != zzfgs.zza, this.zzg, zzdvsVar.zzg != zzfgs.zza, zzdvsVar.zzg);
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                zzfhm zzfhmVar = (zzfhm) obj2;
                if (zzfhmVar == null) {
                    throw null;
                }
                while (!z) {
                    try {
                        try {
                            int iZza = zzfhbVar.zza();
                            if (iZza != 0) {
                                if (iZza == 8) {
                                    this.zzd = zzfhbVar.zzm();
                                } else if (iZza == 18) {
                                    if (this.zze != null) {
                                        zzdvo zzdvoVar = this.zze;
                                        zzfhu.zza zzaVar2 = (zzfhu.zza) zzdvoVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                        zzaVar2.zza(zzdvoVar);
                                        zzaVar = (zzdvo.zza) zzaVar2;
                                    } else {
                                        zzaVar = null;
                                    }
                                    zzdvo zzdvoVar2 = (zzdvo) zzfhbVar.zza(zzdvo.zze(), zzfhmVar);
                                    this.zze = zzdvoVar2;
                                    if (zzaVar != null) {
                                        zzaVar.zza(zzdvoVar2);
                                        this.zze = (zzdvo) zzaVar.zzc();
                                    }
                                } else if (iZza == 26) {
                                    this.zzf = zzfhbVar.zzl();
                                } else if (iZza == 34) {
                                    this.zzg = zzfhbVar.zzl();
                                } else if (!zza(iZza, zzfhbVar)) {
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new RuntimeException((Throwable) new zzfie(e.getMessage()).zza(this));
                        }
                    } catch (zzfie e2) {
                        throw new RuntimeException((Throwable) e2.zza(this));
                    }
                }
                break;
                break;
            case 7:
                break;
            case 8:
                if (zzi == null) {
                    synchronized (zzdvs.class) {
                        if (zzi == null) {
                            zzi = new zzfhu.zzb(zzh);
                        }
                        break;
                    }
                }
                return zzi;
            case 9:
                return (byte) 1;
            case 10:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
        return zzh;
    }

    public final void zza(zzfhg zzfhgVar) throws IOException {
        int i = this.zzd;
        if (i != 0) {
            zzfhgVar.zzc(1, i);
        }
        zzdvo zzdvoVarZze = this.zze;
        if (zzdvoVarZze != null) {
            if (zzdvoVarZze == null) {
                zzdvoVarZze = zzdvo.zze();
            }
            zzfhgVar.zza(2, zzdvoVarZze);
        }
        if (!this.zzf.zzb()) {
            zzfhgVar.zza(3, this.zzf);
        }
        if (!this.zzg.zzb()) {
            zzfhgVar.zza(4, this.zzg);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzdvo zzc() {
        zzdvo zzdvoVar = this.zze;
        return zzdvoVar == null ? zzdvo.zze() : zzdvoVar;
    }

    public final zzfgs zzd() {
        return this.zzf;
    }

    public final zzfgs zze() {
        return this.zzg;
    }
}
