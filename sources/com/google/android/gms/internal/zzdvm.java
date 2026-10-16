package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdvm extends zzfhu<zzdvm, zza> implements zzfje {
    private static final zzdvm zze;
    private static volatile zzfjl<zzdvm> zzf;
    private zzdvo zzd;

    public static final class zza extends zzfhu.zza<zzdvm, zza> implements zzfje {
        private zza() {
            super(zzdvm.zze);
        }

        /* synthetic */ zza(zzdvn zzdvnVar) {
            this();
        }
    }

    static {
        zzdvm zzdvmVar = new zzdvm();
        zze = zzdvmVar;
        zzdvmVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdvmVar).zzb.zzc();
    }

    private zzdvm() {
    }

    public static zzdvm zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdvm) zzfhu.zza(zze, zzfgsVar);
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        zzdvo zzdvoVarZze = this.zzd;
        int iZzc = 0;
        if (zzdvoVarZze != null) {
            if (zzdvoVarZze == null) {
                zzdvoVarZze = zzdvo.zze();
            }
            iZzc = 0 + zzfhg.zzc(1, zzdvoVarZze);
        }
        int iZze = iZzc + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdvo.zza zzaVar;
        zzdvn zzdvnVar = null;
        switch (zzdvn.zza[i - 1]) {
            case 1:
                return new zzdvm();
            case 2:
                return zze;
            case 3:
                return null;
            case 4:
                return new zza(zzdvnVar);
            case 5:
                this.zzd = ((zzfhu.zzh) obj).zza(this.zzd, ((zzdvm) obj2).zzd);
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
                                if (iZza == 10) {
                                    if (this.zzd != null) {
                                        zzdvo zzdvoVar = this.zzd;
                                        zzfhu.zza zzaVar2 = (zzfhu.zza) zzdvoVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                        zzaVar2.zza(zzdvoVar);
                                        zzaVar = (zzdvo.zza) zzaVar2;
                                    } else {
                                        zzaVar = null;
                                    }
                                    zzdvo zzdvoVar2 = (zzdvo) zzfhbVar.zza(zzdvo.zze(), zzfhmVar);
                                    this.zzd = zzdvoVar2;
                                    if (zzaVar != null) {
                                        zzaVar.zza(zzdvoVar2);
                                        this.zzd = (zzdvo) zzaVar.zzc();
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
                    synchronized (zzdvm.class) {
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
        zzdvo zzdvoVarZze = this.zzd;
        if (zzdvoVarZze != null) {
            if (zzdvoVarZze == null) {
                zzdvoVarZze = zzdvo.zze();
            }
            zzfhgVar.zza(1, zzdvoVarZze);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final zzdvo zzb() {
        zzdvo zzdvoVar = this.zzd;
        return zzdvoVar == null ? zzdvo.zze() : zzdvoVar;
    }
}
