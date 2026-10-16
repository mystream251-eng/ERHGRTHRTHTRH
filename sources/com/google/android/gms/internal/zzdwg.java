package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwg extends zzfhu<zzdwg, zza> implements zzfje {
    private static final zzdwg zzg;
    private static volatile zzfjl<zzdwg> zzh;
    private String zzd = "";
    private zzfgs zze = zzfgs.zza;
    private int zzf;

    public static final class zza extends zzfhu.zza<zzdwg, zza> implements zzfje {
        private zza() {
            super(zzdwg.zzg);
        }

        /* synthetic */ zza(zzdwh zzdwhVar) {
            this();
        }

        public final zza zza(zzb zzbVar) {
            zzb();
            ((zzdwg) this.zza).zza(zzbVar);
            return this;
        }

        public final zza zza(zzfgs zzfgsVar) {
            zzb();
            ((zzdwg) this.zza).zza(zzfgsVar);
            return this;
        }

        public final zza zza(String str) {
            zzb();
            ((zzdwg) this.zza).zza(str);
            return this;
        }
    }

    public enum zzb implements zzfia {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);

        private static final zzfib<zzb> zzg = new zzdwi();
        private final int zzh;

        zzb(int i) {
            this.zzh = i;
        }

        public static zzb zza(int i) {
            if (i == 0) {
                return UNKNOWN_KEYMATERIAL;
            }
            if (i == 1) {
                return SYMMETRIC;
            }
            if (i == 2) {
                return ASYMMETRIC_PRIVATE;
            }
            if (i == 3) {
                return ASYMMETRIC_PUBLIC;
            }
            if (i != 4) {
                return null;
            }
            return REMOTE;
        }

        public final int zza() {
            if (this != UNRECOGNIZED) {
                return this.zzh;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    static {
        zzdwg zzdwgVar = new zzdwg();
        zzg = zzdwgVar;
        zzdwgVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwgVar).zzb.zzc();
    }

    private zzdwg() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzb zzbVar) {
        if (zzbVar == null) {
            throw null;
        }
        this.zzf = zzbVar.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzfgs zzfgsVar) {
        if (zzfgsVar == null) {
            throw null;
        }
        this.zze = zzfgsVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str) {
        if (str == null) {
            throw null;
        }
        this.zzd = str;
    }

    public static zza zze() {
        return (zza) ((zzfhu.zza) zzg.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
    }

    public static zzdwg zzf() {
        return zzg;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int iZzb = this.zzd.isEmpty() ? 0 : 0 + zzfhg.zzb(1, this.zzd);
        if (!this.zze.zzb()) {
            iZzb += zzfhg.zzc(2, this.zze);
        }
        if (this.zzf != zzb.UNKNOWN_KEYMATERIAL.zza()) {
            iZzb += zzfhg.zzh(3, this.zzf);
        }
        int iZze = iZzb + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdwh zzdwhVar = null;
        boolean z = false;
        switch (zzdwh.zza[i - 1]) {
            case 1:
                return new zzdwg();
            case 2:
                return zzg;
            case 3:
                return null;
            case 4:
                return new zza(zzdwhVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwg zzdwgVar = (zzdwg) obj2;
                this.zzd = zzhVar.zza(!this.zzd.isEmpty(), this.zzd, !zzdwgVar.zzd.isEmpty(), zzdwgVar.zzd);
                this.zze = zzhVar.zza(this.zze != zzfgs.zza, this.zze, zzdwgVar.zze != zzfgs.zza, zzdwgVar.zze);
                this.zzf = zzhVar.zza(this.zzf != 0, this.zzf, zzdwgVar.zzf != 0, zzdwgVar.zzf);
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                if (((zzfhm) obj2) == null) {
                    throw null;
                }
                while (!z) {
                    try {
                        int iZza = zzfhbVar.zza();
                        if (iZza != 0) {
                            if (iZza == 10) {
                                this.zzd = zzfhbVar.zzk();
                            } else if (iZza == 18) {
                                this.zze = zzfhbVar.zzl();
                            } else if (iZza == 24) {
                                this.zzf = zzfhbVar.zzn();
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
                if (zzh == null) {
                    synchronized (zzdwg.class) {
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
        if (!this.zzd.isEmpty()) {
            zzfhgVar.zza(1, this.zzd);
        }
        if (!this.zze.zzb()) {
            zzfhgVar.zza(2, this.zze);
        }
        if (this.zzf != zzb.UNKNOWN_KEYMATERIAL.zza()) {
            zzfhgVar.zzb(3, this.zzf);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final String zzb() {
        return this.zzd;
    }

    public final zzfgs zzc() {
        return this.zze;
    }

    public final zzb zzd() {
        zzb zzbVarZza = zzb.zza(this.zzf);
        return zzbVarZza == null ? zzb.UNRECOGNIZED : zzbVarZza;
    }
}
