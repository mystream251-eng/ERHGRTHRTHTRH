package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdus extends zzfhu<zzdus, zza> implements zzfje {
    private static final zzdus zzf;
    private static volatile zzfjl<zzdus> zzg;
    private zzduu zzd;
    private int zze;

    public static final class zza extends zzfhu.zza<zzdus, zza> implements zzfje {
        private zza() {
            super(zzdus.zzf);
        }

        /* synthetic */ zza(zzdut zzdutVar) {
            this();
        }
    }

    static {
        zzdus zzdusVar = new zzdus();
        zzf = zzdusVar;
        zzdusVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdusVar).zzb.zzc();
    }

    private zzdus() {
    }

    public static zzdus zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdus) zzfhu.zza(zzf, zzfgsVar);
    }

    public static zzdus zzd() {
        return zzf;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        zzduu zzduuVarZzc = this.zzd;
        int iZzf = 0;
        if (zzduuVarZzc != null) {
            if (zzduuVarZzc == null) {
                zzduuVarZzc = zzduu.zzc();
            }
            iZzf = 0 + zzfhg.zzc(1, zzduuVarZzc);
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
        zzduu.zza zzaVar;
        boolean z = false;
        zzdut zzdutVar = null;
        switch (zzdut.zza[i - 1]) {
            case 1:
                return new zzdus();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzdutVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdus zzdusVar = (zzdus) obj2;
                this.zzd = zzhVar.zza(this.zzd, zzdusVar.zzd);
                this.zze = zzhVar.zza(this.zze != 0, this.zze, zzdusVar.zze != 0, zzdusVar.zze);
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
                                    zzduu zzduuVar = this.zzd;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzduuVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzduuVar);
                                    zzaVar = (zzduu.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzduu zzduuVar2 = (zzduu) zzfhbVar.zza(zzduu.zzc(), zzfhmVar);
                                this.zzd = zzduuVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzduuVar2);
                                    this.zzd = (zzduu) zzaVar.zzc();
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
                    synchronized (zzdus.class) {
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
        zzduu zzduuVarZzc = this.zzd;
        if (zzduuVarZzc != null) {
            if (zzduuVarZzc == null) {
                zzduuVarZzc = zzduu.zzc();
            }
            zzfhgVar.zza(1, zzduuVarZzc);
        }
        int i = this.zze;
        if (i != 0) {
            zzfhgVar.zzc(2, i);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzduu zzb() {
        zzduu zzduuVar = this.zzd;
        return zzduuVar == null ? zzduu.zzc() : zzduuVar;
    }

    public final int zzc() {
        return this.zze;
    }
}
