package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdvo extends zzfhu<zzdvo, zza> implements zzfje {
    private static final zzdvo zzg;
    private static volatile zzfjl<zzdvo> zzh;
    private zzdvu zzd;
    private zzdvk zze;
    private int zzf;

    public static final class zza extends zzfhu.zza<zzdvo, zza> implements zzfje {
        private zza() {
            super(zzdvo.zzg);
        }

        /* synthetic */ zza(zzdvp zzdvpVar) {
            this();
        }
    }

    static {
        zzdvo zzdvoVar = new zzdvo();
        zzg = zzdvoVar;
        zzdvoVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdvoVar).zzb.zzc();
    }

    private zzdvo() {
    }

    public static zzdvo zze() {
        return zzg;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        zzdvu zzdvuVarZze = this.zzd;
        int iZzh = 0;
        if (zzdvuVarZze != null) {
            if (zzdvuVarZze == null) {
                zzdvuVarZze = zzdvu.zze();
            }
            iZzh = 0 + zzfhg.zzc(1, zzdvuVarZze);
        }
        zzdvk zzdvkVarZzc = this.zze;
        if (zzdvkVarZzc != null) {
            if (zzdvkVarZzc == null) {
                zzdvkVarZzc = zzdvk.zzc();
            }
            iZzh += zzfhg.zzc(2, zzdvkVarZzc);
        }
        if (this.zzf != zzdvi.UNKNOWN_FORMAT.zza()) {
            iZzh += zzfhg.zzh(3, this.zzf);
        }
        int iZze = iZzh + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdvk.zza zzaVar;
        zzdvu.zza zzaVar2;
        boolean z = false;
        zzdvp zzdvpVar = null;
        switch (zzdvp.zza[i - 1]) {
            case 1:
                return new zzdvo();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzdvpVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdvo zzdvoVar = (zzdvo) obj2;
                this.zzd = zzhVar.zza(this.zzd, zzdvoVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzdvoVar.zze);
                this.zzf = zzhVar.zza(this.zzf != 0, this.zzf, zzdvoVar.zzf != 0, zzdvoVar.zzf);
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
                                    zzdvu zzdvuVar = this.zzd;
                                    zzfhu.zza zzaVar3 = (zzfhu.zza) zzdvuVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar3.zza(zzdvuVar);
                                    zzaVar2 = (zzdvu.zza) zzaVar3;
                                } else {
                                    zzaVar2 = null;
                                }
                                zzdvu zzdvuVar2 = (zzdvu) zzfhbVar.zza(zzdvu.zze(), zzfhmVar);
                                this.zzd = zzdvuVar2;
                                if (zzaVar2 != null) {
                                    zzaVar2.zza(zzdvuVar2);
                                    this.zzd = (zzdvu) zzaVar2.zzc();
                                }
                            } else if (iZza == 18) {
                                if (this.zze != null) {
                                    zzdvk zzdvkVar = this.zze;
                                    zzfhu.zza zzaVar4 = (zzfhu.zza) zzdvkVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar4.zza(zzdvkVar);
                                    zzaVar = (zzdvk.zza) zzaVar4;
                                } else {
                                    zzaVar = null;
                                }
                                zzdvk zzdvkVar2 = (zzdvk) zzfhbVar.zza(zzdvk.zzc(), zzfhmVar);
                                this.zze = zzdvkVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdvkVar2);
                                    this.zze = (zzdvk) zzaVar.zzc();
                                }
                            } else if (iZza == 24) {
                                this.zzf = zzfhbVar.zzn();
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
                    synchronized (zzdvo.class) {
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
        zzdvu zzdvuVarZze = this.zzd;
        if (zzdvuVarZze != null) {
            if (zzdvuVarZze == null) {
                zzdvuVarZze = zzdvu.zze();
            }
            zzfhgVar.zza(1, zzdvuVarZze);
        }
        zzdvk zzdvkVarZzc = this.zze;
        if (zzdvkVarZzc != null) {
            if (zzdvkVarZzc == null) {
                zzdvkVarZzc = zzdvk.zzc();
            }
            zzfhgVar.zza(2, zzdvkVarZzc);
        }
        if (this.zzf != zzdvi.UNKNOWN_FORMAT.zza()) {
            zzfhgVar.zzb(3, this.zzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzdvu zzb() {
        zzdvu zzdvuVar = this.zzd;
        return zzdvuVar == null ? zzdvu.zze() : zzdvuVar;
    }

    public final zzdvk zzc() {
        zzdvk zzdvkVar = this.zze;
        return zzdvkVar == null ? zzdvk.zzc() : zzdvkVar;
    }

    public final zzdvi zzd() {
        zzdvi zzdviVarZza = zzdvi.zza(this.zzf);
        return zzdviVarZza == null ? zzdvi.UNRECOGNIZED : zzdviVarZza;
    }
}
