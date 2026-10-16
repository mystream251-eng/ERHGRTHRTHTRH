package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzduq extends zzfhu<zzduq, zza> implements zzfje {
    private static final zzduq zzg;
    private static volatile zzfjl<zzduq> zzh;
    private int zzd;
    private zzduu zze;
    private zzfgs zzf = zzfgs.zza;

    public static final class zza extends zzfhu.zza<zzduq, zza> implements zzfje {
        private zza() {
            super(zzduq.zzg);
        }

        /* synthetic */ zza(zzdur zzdurVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzduq) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzduu zzduuVar) {
            zzb();
            ((zzduq) this.zza).zza(zzduuVar);
            return this;
        }

        public final zza zza(zzfgs zzfgsVar) {
            zzb();
            ((zzduq) this.zza).zzb(zzfgsVar);
            return this;
        }
    }

    static {
        zzduq zzduqVar = new zzduq();
        zzg = zzduqVar;
        zzduqVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzduqVar).zzb.zzc();
    }

    private zzduq() {
    }

    public static zzduq zza(zzfgs zzfgsVar) throws zzfie {
        return (zzduq) zzfhu.zza(zzg, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzduu zzduuVar) {
        if (zzduuVar == null) {
            throw null;
        }
        this.zze = zzduuVar;
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

    public static zzduq zzf() {
        return zzg;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzd;
        int iZzf = i2 != 0 ? 0 + zzfhg.zzf(1, i2) : 0;
        zzduu zzduuVarZzc = this.zze;
        if (zzduuVarZzc != null) {
            if (zzduuVarZzc == null) {
                zzduuVarZzc = zzduu.zzc();
            }
            iZzf += zzfhg.zzc(2, zzduuVarZzc);
        }
        if (!this.zzf.zzb()) {
            iZzf += zzfhg.zzc(3, this.zzf);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzduu.zza zzaVar;
        boolean z = false;
        zzdur zzdurVar = null;
        switch (zzdur.zza[i - 1]) {
            case 1:
                return new zzduq();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzdurVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzduq zzduqVar = (zzduq) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzduqVar.zzd != 0, zzduqVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzduqVar.zze);
                this.zzf = zzhVar.zza(this.zzf != zzfgs.zza, this.zzf, zzduqVar.zzf != zzfgs.zza, zzduqVar.zzf);
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
                                    zzduu zzduuVar = this.zze;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzduuVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzduuVar);
                                    zzaVar = (zzduu.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzduu zzduuVar2 = (zzduu) zzfhbVar.zza(zzduu.zzc(), zzfhmVar);
                                this.zze = zzduuVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzduuVar2);
                                    this.zze = (zzduu) zzaVar.zzc();
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
                    synchronized (zzduq.class) {
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
        zzduu zzduuVarZzc = this.zze;
        if (zzduuVarZzc != null) {
            if (zzduuVarZzc == null) {
                zzduuVarZzc = zzduu.zzc();
            }
            zzfhgVar.zza(2, zzduuVarZzc);
        }
        if (!this.zzf.zzb()) {
            zzfhgVar.zza(3, this.zzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzduu zzc() {
        zzduu zzduuVar = this.zze;
        return zzduuVar == null ? zzduu.zzc() : zzduuVar;
    }

    public final zzfgs zzd() {
        return this.zzf;
    }
}
