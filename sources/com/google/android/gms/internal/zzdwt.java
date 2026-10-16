package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwt extends zzfhu<zzdwt, zza> implements zzfje {
    private static final zzdwt zzf;
    private static volatile zzfjl<zzdwt> zzg;
    private int zzd;
    private zzdwv zze;

    public static final class zza extends zzfhu.zza<zzdwt, zza> implements zzfje {
        private zza() {
            super(zzdwt.zzf);
        }

        /* synthetic */ zza(zzdwu zzdwuVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdwt) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzdwv zzdwvVar) {
            zzb();
            ((zzdwt) this.zza).zza(zzdwvVar);
            return this;
        }
    }

    static {
        zzdwt zzdwtVar = new zzdwt();
        zzf = zzdwtVar;
        zzdwtVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwtVar).zzb.zzc();
    }

    private zzdwt() {
    }

    public static zzdwt zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdwt) zzfhu.zza(zzf, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdwv zzdwvVar) {
        if (zzdwvVar == null) {
            throw null;
        }
        this.zze = zzdwvVar;
    }

    public static zza zzd() {
        return (zza) ((zzfhu.zza) zzf.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzd;
        int iZzf = i2 != 0 ? 0 + zzfhg.zzf(1, i2) : 0;
        zzdwv zzdwvVarZzc = this.zze;
        if (zzdwvVarZzc != null) {
            if (zzdwvVarZzc == null) {
                zzdwvVarZzc = zzdwv.zzc();
            }
            iZzf += zzfhg.zzc(2, zzdwvVarZzc);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwv.zza zzaVar;
        boolean z = false;
        zzdwu zzdwuVar = null;
        switch (zzdwu.zza[i - 1]) {
            case 1:
                return new zzdwt();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzdwuVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwt zzdwtVar = (zzdwt) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdwtVar.zzd != 0, zzdwtVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzdwtVar.zze);
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
                                    zzdwv zzdwvVar = this.zze;
                                    zzfhu.zza zzaVar2 = (zzfhu.zza) zzdwvVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar2.zza(zzdwvVar);
                                    zzaVar = (zzdwv.zza) zzaVar2;
                                } else {
                                    zzaVar = null;
                                }
                                zzdwv zzdwvVar2 = (zzdwv) zzfhbVar.zza(zzdwv.zzc(), zzfhmVar);
                                this.zze = zzdwvVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdwvVar2);
                                    this.zze = (zzdwv) zzaVar.zzc();
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
                    synchronized (zzdwt.class) {
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
        int i = this.zzd;
        if (i != 0) {
            zzfhgVar.zzc(1, i);
        }
        zzdwv zzdwvVarZzc = this.zze;
        if (zzdwvVarZzc != null) {
            if (zzdwvVarZzc == null) {
                zzdwvVarZzc = zzdwv.zzc();
            }
            zzfhgVar.zza(2, zzdwvVarZzc);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzdwv zzc() {
        zzdwv zzdwvVar = this.zze;
        return zzdwvVar == null ? zzdwv.zzc() : zzdwvVar;
    }
}
