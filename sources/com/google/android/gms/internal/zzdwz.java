package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwz extends zzfhu<zzdwz, zza> implements zzfje {
    private static final zzdwz zzf;
    private static volatile zzfjl<zzdwz> zzg;
    private String zzd = "";
    private zzdwl zze;

    public static final class zza extends zzfhu.zza<zzdwz, zza> implements zzfje {
        private zza() {
            super(zzdwz.zzf);
        }

        /* synthetic */ zza(zzdxa zzdxaVar) {
            this();
        }
    }

    static {
        zzdwz zzdwzVar = new zzdwz();
        zzf = zzdwzVar;
        zzdwzVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwzVar).zzb.zzc();
    }

    private zzdwz() {
    }

    public static zzdwz zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdwz) zzfhu.zza(zzf, zzfgsVar);
    }

    public static zzdwz zzd() {
        return zzf;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int iZzb = this.zzd.isEmpty() ? 0 : 0 + zzfhg.zzb(1, this.zzd);
        zzdwl zzdwlVarZzd = this.zze;
        if (zzdwlVarZzd != null) {
            if (zzdwlVarZzd == null) {
                zzdwlVarZzd = zzdwl.zzd();
            }
            iZzb += zzfhg.zzc(2, zzdwlVarZzd);
        }
        int iZze = iZzb + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwl.zza zzaVar;
        zzdxa zzdxaVar = null;
        switch (zzdxa.zza[i - 1]) {
            case 1:
                return new zzdwz();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzdxaVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwz zzdwzVar = (zzdwz) obj2;
                this.zzd = zzhVar.zza(!this.zzd.isEmpty(), this.zzd, true ^ zzdwzVar.zzd.isEmpty(), zzdwzVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzdwzVar.zze);
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
                                this.zzd = zzfhbVar.zzk();
                            } else if (iZza == 18) {
                                if (this.zze != null) {
                                    zzdwl zzdwlVar = this.zze;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzdwlVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzdwlVar);
                                    zzaVar = (zzdwl.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzdwl zzdwlVar2 = (zzdwl) zzfhbVar.zza(zzdwl.zzd(), zzfhmVar);
                                this.zze = zzdwlVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdwlVar2);
                                    this.zze = (zzdwl) zzaVar.zzc();
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
                    synchronized (zzdwz.class) {
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
        if (!this.zzd.isEmpty()) {
            zzfhgVar.zza(1, this.zzd);
        }
        zzdwl zzdwlVarZzd = this.zze;
        if (zzdwlVarZzd != null) {
            if (zzdwlVarZzd == null) {
                zzdwlVarZzd = zzdwl.zzd();
            }
            zzfhgVar.zza(2, zzdwlVarZzd);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final String zzb() {
        return this.zzd;
    }

    public final zzdwl zzc() {
        zzdwl zzdwlVar = this.zze;
        return zzdwlVar == null ? zzdwl.zzd() : zzdwlVar;
    }
}
