package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzduo extends zzfhu<zzduo, zza> implements zzfje {
    private static final zzduo zzf;
    private static volatile zzfjl<zzduo> zzg;
    private zzdus zzd;
    private zzdwc zze;

    public static final class zza extends zzfhu.zza<zzduo, zza> implements zzfje {
        private zza() {
            super(zzduo.zzf);
        }

        /* synthetic */ zza(zzdup zzdupVar) {
            this();
        }
    }

    static {
        zzduo zzduoVar = new zzduo();
        zzf = zzduoVar;
        zzduoVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzduoVar).zzb.zzc();
    }

    private zzduo() {
    }

    public static zzduo zza(zzfgs zzfgsVar) throws zzfie {
        return (zzduo) zzfhu.zza(zzf, zzfgsVar);
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        zzdus zzdusVarZzd = this.zzd;
        int iZzc = 0;
        if (zzdusVarZzd != null) {
            if (zzdusVarZzd == null) {
                zzdusVarZzd = zzdus.zzd();
            }
            iZzc = 0 + zzfhg.zzc(1, zzdusVarZzd);
        }
        zzdwc zzdwcVarZzd = this.zze;
        if (zzdwcVarZzd != null) {
            if (zzdwcVarZzd == null) {
                zzdwcVarZzd = zzdwc.zzd();
            }
            iZzc += zzfhg.zzc(2, zzdwcVarZzd);
        }
        int iZze = iZzc + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwc.zza zzaVar;
        zzdus.zza zzaVar2;
        zzdup zzdupVar = null;
        switch (zzdup.zza[i - 1]) {
            case 1:
                return new zzduo();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzdupVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzduo zzduoVar = (zzduo) obj2;
                this.zzd = zzhVar.zza(this.zzd, zzduoVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzduoVar.zze);
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                zzfhm zzfhmVar = (zzfhm) obj2;
                if (zzfhmVar == null) {
                    throw null;
                }
                boolean z = false;
                while (!z) {
                    try {
                        int iZza = zzfhbVar.zza();
                        if (iZza != 0) {
                            if (iZza == 10) {
                                if (this.zzd != null) {
                                    zzdus zzdusVar = this.zzd;
                                    zzfhu.zza zzaVar3 = (zzfhu.zza) zzdusVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar3.zza(zzdusVar);
                                    zzaVar2 = (zzdus.zza) zzaVar3;
                                } else {
                                    zzaVar2 = null;
                                }
                                zzdus zzdusVar2 = (zzdus) zzfhbVar.zza(zzdus.zzd(), zzfhmVar);
                                this.zzd = zzdusVar2;
                                if (zzaVar2 != null) {
                                    zzaVar2.zza(zzdusVar2);
                                    this.zzd = (zzdus) zzaVar2.zzc();
                                }
                            } else if (iZza == 18) {
                                if (this.zze != null) {
                                    zzdwc zzdwcVar = this.zze;
                                    zzfhu.zza zzaVar4 = (zzfhu.zza) zzdwcVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar4.zza(zzdwcVar);
                                    zzaVar = (zzdwc.zza) zzaVar4;
                                } else {
                                    zzaVar = null;
                                }
                                zzdwc zzdwcVar2 = (zzdwc) zzfhbVar.zza(zzdwc.zzd(), zzfhmVar);
                                this.zze = zzdwcVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdwcVar2);
                                    this.zze = (zzdwc) zzaVar.zzc();
                                }
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
                if (zzg == null) {
                    synchronized (zzduo.class) {
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
        zzdus zzdusVarZzd = this.zzd;
        if (zzdusVarZzd != null) {
            if (zzdusVarZzd == null) {
                zzdusVarZzd = zzdus.zzd();
            }
            zzfhgVar.zza(1, zzdusVarZzd);
        }
        zzdwc zzdwcVarZzd = this.zze;
        if (zzdwcVarZzd != null) {
            if (zzdwcVarZzd == null) {
                zzdwcVarZzd = zzdwc.zzd();
            }
            zzfhgVar.zza(2, zzdwcVarZzd);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzdus zzb() {
        zzdus zzdusVar = this.zzd;
        return zzdusVar == null ? zzdus.zzd() : zzdusVar;
    }

    public final zzdwc zzc() {
        zzdwc zzdwcVar = this.zze;
        return zzdwcVar == null ? zzdwc.zzd() : zzdwcVar;
    }
}
