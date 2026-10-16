package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdvk extends zzfhu<zzdvk, zza> implements zzfje {
    private static final zzdvk zze;
    private static volatile zzfjl<zzdvk> zzf;
    private zzdwl zzd;

    public static final class zza extends zzfhu.zza<zzdvk, zza> implements zzfje {
        private zza() {
            super(zzdvk.zze);
        }

        /* synthetic */ zza(zzdvl zzdvlVar) {
            this();
        }
    }

    static {
        zzdvk zzdvkVar = new zzdvk();
        zze = zzdvkVar;
        zzdvkVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdvkVar).zzb.zzc();
    }

    private zzdvk() {
    }

    public static zzdvk zzc() {
        return zze;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        zzdwl zzdwlVarZzd = this.zzd;
        int iZzc = 0;
        if (zzdwlVarZzd != null) {
            if (zzdwlVarZzd == null) {
                zzdwlVarZzd = zzdwl.zzd();
            }
            iZzc = 0 + zzfhg.zzc(2, zzdwlVarZzd);
        }
        int iZze = iZzc + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwl.zza zzaVar;
        zzdvl zzdvlVar = null;
        switch (zzdvl.zza[i - 1]) {
            case 1:
                return new zzdvk();
            case 2:
                return zze;
            case 3:
                return null;
            case 4:
                return new zza(zzdvlVar);
            case 5:
                this.zzd = ((zzfhu.zzh) obj).zza(this.zzd, ((zzdvk) obj2).zzd);
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
                        try {
                            int iZza = zzfhbVar.zza();
                            if (iZza != 0) {
                                if (iZza == 18) {
                                    if (this.zzd != null) {
                                        zzdwl zzdwlVar = this.zzd;
                                        zzfhu.zza zzaVar2 = (zzfhu.zza) zzdwlVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                        zzaVar2.zza(zzdwlVar);
                                        zzaVar = (zzdwl.zza) zzaVar2;
                                    } else {
                                        zzaVar = null;
                                    }
                                    zzdwl zzdwlVar2 = (zzdwl) zzfhbVar.zza(zzdwl.zzd(), zzfhmVar);
                                    this.zzd = zzdwlVar2;
                                    if (zzaVar != null) {
                                        zzaVar.zza(zzdwlVar2);
                                        this.zzd = (zzdwl) zzaVar.zzc();
                                    }
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
                if (zzf == null) {
                    synchronized (zzdvk.class) {
                        if (zzf == null) {
                            zzf = new zzfhu.zzb(zze);
                        }
                        break;
                    }
                }
                return zzf;
            case 9:
                return (byte) 1;
            case 10:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
        return zze;
    }

    public final void zza(zzfhg zzfhgVar) throws IOException {
        zzdwl zzdwlVarZzd = this.zzd;
        if (zzdwlVarZzd != null) {
            if (zzdwlVarZzd == null) {
                zzdwlVarZzd = zzdwl.zzd();
            }
            zzfhgVar.zza(2, zzdwlVarZzd);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzdwl zzb() {
        zzdwl zzdwlVar = this.zzd;
        return zzdwlVar == null ? zzdwl.zzd() : zzdwlVar;
    }
}
