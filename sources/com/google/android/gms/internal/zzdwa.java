package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwa extends zzfhu<zzdwa, zza> implements zzfje {
    private static final zzdwa zzg;
    private static volatile zzfjl<zzdwa> zzh;
    private int zzd;
    private zzdwe zze;
    private zzfgs zzf = zzfgs.zza;

    public static final class zza extends zzfhu.zza<zzdwa, zza> implements zzfje {
        private zza() {
            super(zzdwa.zzg);
        }

        /* synthetic */ zza(zzdwb zzdwbVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdwa) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzdwe zzdweVar) {
            zzb();
            ((zzdwa) this.zza).zza(zzdweVar);
            return this;
        }

        public final zza zza(zzfgs zzfgsVar) {
            zzb();
            ((zzdwa) this.zza).zzb(zzfgsVar);
            return this;
        }
    }

    static {
        zzdwa zzdwaVar = new zzdwa();
        zzg = zzdwaVar;
        zzdwaVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwaVar).zzb.zzc();
    }

    private zzdwa() {
    }

    public static zzdwa zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdwa) zzfhu.zza(zzg, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdwe zzdweVar) {
        if (zzdweVar == null) {
            throw null;
        }
        this.zze = zzdweVar;
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

    public static zzdwa zzf() {
        return zzg;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzd;
        int iZzf = i2 != 0 ? 0 + zzfhg.zzf(1, i2) : 0;
        zzdwe zzdweVarZzd = this.zze;
        if (zzdweVarZzd != null) {
            if (zzdweVarZzd == null) {
                zzdweVarZzd = zzdwe.zzd();
            }
            iZzf += zzfhg.zzc(2, zzdweVarZzd);
        }
        if (!this.zzf.zzb()) {
            iZzf += zzfhg.zzc(3, this.zzf);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwe.zza zzaVar;
        boolean z = false;
        zzdwb zzdwbVar = null;
        switch (zzdwb.zza[i - 1]) {
            case 1:
                return new zzdwa();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzdwbVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwa zzdwaVar = (zzdwa) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdwaVar.zzd != 0, zzdwaVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzdwaVar.zze);
                this.zzf = zzhVar.zza(this.zzf != zzfgs.zza, this.zzf, zzdwaVar.zzf != zzfgs.zza, zzdwaVar.zzf);
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
                                    zzdwe zzdweVar = this.zze;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzdweVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzdweVar);
                                    zzaVar = (zzdwe.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzdwe zzdweVar2 = (zzdwe) zzfhbVar.zza(zzdwe.zzd(), zzfhmVar);
                                this.zze = zzdweVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdweVar2);
                                    this.zze = (zzdwe) zzaVar.zzc();
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
                    synchronized (zzdwa.class) {
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
        zzdwe zzdweVarZzd = this.zze;
        if (zzdweVarZzd != null) {
            if (zzdweVarZzd == null) {
                zzdweVarZzd = zzdwe.zzd();
            }
            zzfhgVar.zza(2, zzdweVarZzd);
        }
        if (!this.zzf.zzb()) {
            zzfhgVar.zza(3, this.zzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzdwe zzc() {
        zzdwe zzdweVar = this.zze;
        return zzdweVar == null ? zzdwe.zzd() : zzdweVar;
    }

    public final zzfgs zzd() {
        return this.zzf;
    }
}
