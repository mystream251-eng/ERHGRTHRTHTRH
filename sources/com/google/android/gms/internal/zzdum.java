package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdum extends zzfhu<zzdum, zza> implements zzfje {
    private static final zzdum zzg;
    private static volatile zzfjl<zzdum> zzh;
    private int zzd;
    private zzduq zze;
    private zzdwa zzf;

    public static final class zza extends zzfhu.zza<zzdum, zza> implements zzfje {
        private zza() {
            super(zzdum.zzg);
        }

        /* synthetic */ zza(zzdun zzdunVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdum) this.zza).zza(i);
            return this;
        }

        public final zza zza(zzduq zzduqVar) {
            zzb();
            ((zzdum) this.zza).zza(zzduqVar);
            return this;
        }

        public final zza zza(zzdwa zzdwaVar) {
            zzb();
            ((zzdum) this.zza).zza(zzdwaVar);
            return this;
        }
    }

    static {
        zzdum zzdumVar = new zzdum();
        zzg = zzdumVar;
        zzdumVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdumVar).zzb.zzc();
    }

    private zzdum() {
    }

    public static zzdum zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdum) zzfhu.zza(zzg, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzduq zzduqVar) {
        if (zzduqVar == null) {
            throw null;
        }
        this.zze = zzduqVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdwa zzdwaVar) {
        if (zzdwaVar == null) {
            throw null;
        }
        this.zzf = zzdwaVar;
    }

    public static zza zze() {
        return (zza) ((zzfhu.zza) zzg.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzd;
        int iZzf = i2 != 0 ? 0 + zzfhg.zzf(1, i2) : 0;
        zzduq zzduqVarZzf = this.zze;
        if (zzduqVarZzf != null) {
            if (zzduqVarZzf == null) {
                zzduqVarZzf = zzduq.zzf();
            }
            iZzf += zzfhg.zzc(2, zzduqVarZzf);
        }
        zzdwa zzdwaVarZzf = this.zzf;
        if (zzdwaVarZzf != null) {
            if (zzdwaVarZzf == null) {
                zzdwaVarZzf = zzdwa.zzf();
            }
            iZzf += zzfhg.zzc(3, zzdwaVarZzf);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwa.zza zzaVar;
        zzduq.zza zzaVar2;
        boolean z = false;
        zzdun zzdunVar = null;
        switch (zzdun.zza[i - 1]) {
            case 1:
                return new zzdum();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzdunVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdum zzdumVar = (zzdum) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdumVar.zzd != 0, zzdumVar.zzd);
                this.zze = zzhVar.zza(this.zze, zzdumVar.zze);
                this.zzf = zzhVar.zza(this.zzf, zzdumVar.zzf);
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
                                    zzduq zzduqVar = this.zze;
                                    zzfhu.zza zzaVar3 = (zzfhu.zza) zzduqVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar3.zza(zzduqVar);
                                    zzaVar2 = (zzduq.zza) zzaVar3;
                                } else {
                                    zzaVar2 = null;
                                }
                                zzduq zzduqVar2 = (zzduq) zzfhbVar.zza(zzduq.zzf(), zzfhmVar);
                                this.zze = zzduqVar2;
                                if (zzaVar2 != null) {
                                    zzaVar2.zza(zzduqVar2);
                                    this.zze = (zzduq) zzaVar2.zzc();
                                }
                            } else if (iZza == 26) {
                                if (this.zzf != null) {
                                    zzdwa zzdwaVar = this.zzf;
                                    zzfhu.zza zzaVar4 = (zzfhu.zza) zzdwaVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                    zzaVar4.zza(zzdwaVar);
                                    zzaVar = (zzdwa.zza) zzaVar4;
                                } else {
                                    zzaVar = null;
                                }
                                zzdwa zzdwaVar2 = (zzdwa) zzfhbVar.zza(zzdwa.zzf(), zzfhmVar);
                                this.zzf = zzdwaVar2;
                                if (zzaVar != null) {
                                    zzaVar.zza(zzdwaVar2);
                                    this.zzf = (zzdwa) zzaVar.zzc();
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
                if (zzh == null) {
                    synchronized (zzdum.class) {
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
        zzduq zzduqVarZzf = this.zze;
        if (zzduqVarZzf != null) {
            if (zzduqVarZzf == null) {
                zzduqVarZzf = zzduq.zzf();
            }
            zzfhgVar.zza(2, zzduqVarZzf);
        }
        zzdwa zzdwaVarZzf = this.zzf;
        if (zzdwaVarZzf != null) {
            if (zzdwaVarZzf == null) {
                zzdwaVarZzf = zzdwa.zzf();
            }
            zzfhgVar.zza(3, zzdwaVarZzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzduq zzc() {
        zzduq zzduqVar = this.zze;
        return zzduqVar == null ? zzduq.zzf() : zzduqVar;
    }

    public final zzdwa zzd() {
        zzdwa zzdwaVar = this.zzf;
        return zzdwaVar == null ? zzdwa.zzf() : zzdwaVar;
    }
}
