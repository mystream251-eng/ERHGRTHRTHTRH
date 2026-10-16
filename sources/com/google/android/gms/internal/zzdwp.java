package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwp extends zzfhu<zzdwp, zza> implements zzfje {
    private static final zzdwp zzg;
    private static volatile zzfjl<zzdwp> zzh;
    private int zzd;
    private int zze;
    private zzfid<zzb> zzf = zzu();

    public static final class zza extends zzfhu.zza<zzdwp, zza> implements zzfje {
        private zza() {
            super(zzdwp.zzg);
        }

        /* synthetic */ zza(zzdwq zzdwqVar) {
            this();
        }
    }

    public static final class zzb extends zzfhu<zzb, zza> implements zzfje {
        private static final zzb zzh;
        private static volatile zzfjl<zzb> zzi;
        private zzdwg zzd;
        private int zze;
        private int zzf;
        private int zzg;

        public static final class zza extends zzfhu.zza<zzb, zza> implements zzfje {
            private zza() {
                super(zzb.zzh);
            }

            /* synthetic */ zza(zzdwq zzdwqVar) {
                this();
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzh = zzbVar;
            zzbVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
            ((zzfhu) zzbVar).zzb.zzc();
        }

        private zzb() {
        }

        public static zzb zzg() {
            return zzh;
        }

        public final int zza() {
            int i = this.zzc;
            if (i != -1) {
                return i;
            }
            zzdwg zzdwgVarZzf = this.zzd;
            int iZzh = 0;
            if (zzdwgVarZzf != null) {
                if (zzdwgVarZzf == null) {
                    zzdwgVarZzf = zzdwg.zzf();
                }
                iZzh = 0 + zzfhg.zzc(1, zzdwgVarZzf);
            }
            if (this.zze != zzdwj.UNKNOWN_STATUS.zza()) {
                iZzh += zzfhg.zzh(2, this.zze);
            }
            int i2 = this.zzf;
            if (i2 != 0) {
                iZzh += zzfhg.zzf(3, i2);
            }
            if (this.zzg != zzdxb.UNKNOWN_PREFIX.zza()) {
                iZzh += zzfhg.zzh(4, this.zzg);
            }
            int iZze = iZzh + this.zzb.zze();
            this.zzc = iZze;
            return iZze;
        }

        protected final Object zza(int i, Object obj, Object obj2) {
            zzdwg.zza zzaVar;
            boolean z = false;
            zzdwq zzdwqVar = null;
            switch (zzdwq.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return zzh;
                case 3:
                    return null;
                case 4:
                    return new zza(zzdwqVar);
                case 5:
                    zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                    zzb zzbVar = (zzb) obj2;
                    this.zzd = zzhVar.zza(this.zzd, zzbVar.zzd);
                    this.zze = zzhVar.zza(this.zze != 0, this.zze, zzbVar.zze != 0, zzbVar.zze);
                    this.zzf = zzhVar.zza(this.zzf != 0, this.zzf, zzbVar.zzf != 0, zzbVar.zzf);
                    this.zzg = zzhVar.zza(this.zzg != 0, this.zzg, zzbVar.zzg != 0, zzbVar.zzg);
                    return this;
                case 6:
                    zzfhb zzfhbVar = (zzfhb) obj;
                    zzfhm zzfhmVar = (zzfhm) obj2;
                    if (zzfhmVar == null) {
                        throw null;
                    }
                    while (!z) {
                        try {
                            try {
                                int iZza = zzfhbVar.zza();
                                if (iZza != 0) {
                                    if (iZza == 10) {
                                        if (this.zzd != null) {
                                            zzdwg zzdwgVar = this.zzd;
                                            zzfhu.zza zzaVar2 = (zzfhu.zza) zzdwgVar.zza(zzfhu.zzg.zzh, (Object) null, (Object) null);
                                            zzaVar2.zza(zzdwgVar);
                                            zzaVar = (zzdwg.zza) zzaVar2;
                                        } else {
                                            zzaVar = null;
                                        }
                                        zzdwg zzdwgVar2 = (zzdwg) zzfhbVar.zza(zzdwg.zzf(), zzfhmVar);
                                        this.zzd = zzdwgVar2;
                                        if (zzaVar != null) {
                                            zzaVar.zza(zzdwgVar2);
                                            this.zzd = (zzdwg) zzaVar.zzc();
                                        }
                                    } else if (iZza == 16) {
                                        this.zze = zzfhbVar.zzn();
                                    } else if (iZza == 24) {
                                        this.zzf = zzfhbVar.zzm();
                                    } else if (iZza == 32) {
                                        this.zzg = zzfhbVar.zzn();
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
                    if (zzi == null) {
                        synchronized (zzb.class) {
                            if (zzi == null) {
                                zzi = new zzfhu.zzb(zzh);
                            }
                            break;
                        }
                    }
                    return zzi;
                case 9:
                    return (byte) 1;
                case 10:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
            return zzh;
        }

        public final void zza(zzfhg zzfhgVar) throws IOException {
            zzdwg zzdwgVarZzf = this.zzd;
            if (zzdwgVarZzf != null) {
                if (zzdwgVarZzf == null) {
                    zzdwgVarZzf = zzdwg.zzf();
                }
                zzfhgVar.zza(1, zzdwgVarZzf);
            }
            if (this.zze != zzdwj.UNKNOWN_STATUS.zza()) {
                zzfhgVar.zzb(2, this.zze);
            }
            int i = this.zzf;
            if (i != 0) {
                zzfhgVar.zzc(3, i);
            }
            if (this.zzg != zzdxb.UNKNOWN_PREFIX.zza()) {
                zzfhgVar.zzb(4, this.zzg);
            }
            this.zzb.zza(zzfhgVar);
        }

        public final boolean zzb() {
            return this.zzd != null;
        }

        public final zzdwg zzc() {
            zzdwg zzdwgVar = this.zzd;
            return zzdwgVar == null ? zzdwg.zzf() : zzdwgVar;
        }

        public final zzdwj zzd() {
            zzdwj zzdwjVarZza = zzdwj.zza(this.zze);
            return zzdwjVarZza == null ? zzdwj.UNRECOGNIZED : zzdwjVarZza;
        }

        public final int zze() {
            return this.zzf;
        }

        public final zzdxb zzf() {
            zzdxb zzdxbVarZza = zzdxb.zza(this.zzg);
            return zzdxbVarZza == null ? zzdxb.UNRECOGNIZED : zzdxbVarZza;
        }
    }

    static {
        zzdwp zzdwpVar = new zzdwp();
        zzg = zzdwpVar;
        zzdwpVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwpVar).zzb.zzc();
    }

    private zzdwp() {
    }

    public static zzdwp zza(byte[] bArr) throws zzfie {
        return (zzdwp) zzfhu.zza(zzg, bArr);
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zze;
        int iZzf = i2 != 0 ? zzfhg.zzf(1, i2) + 0 : 0;
        for (int i3 = 0; i3 < this.zzf.size(); i3++) {
            iZzf += zzfhg.zzc(2, (zzfjc) this.zzf.get(i3));
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        boolean z = false;
        zzdwq zzdwqVar = null;
        switch (zzdwq.zza[i - 1]) {
            case 1:
                return new zzdwp();
            case 2:
                return zzg;
            case 3:
                this.zzf.zzb();
                return null;
            case 4:
                return new zza(zzdwqVar);
            case 5:
                zzfhu.zzf zzfVar = (zzfhu.zzh) obj;
                zzdwp zzdwpVar = (zzdwp) obj2;
                this.zze = zzfVar.zza(this.zze != 0, this.zze, zzdwpVar.zze != 0, zzdwpVar.zze);
                this.zzf = zzfVar.zza(this.zzf, zzdwpVar.zzf);
                if (zzfVar == zzfhu.zzf.zza) {
                    this.zzd |= zzdwpVar.zzd;
                }
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                zzfhm zzfhmVar = (zzfhm) obj2;
                if (zzfhmVar == null) {
                    throw null;
                }
                while (!z) {
                    try {
                        try {
                            int iZza = zzfhbVar.zza();
                            if (iZza != 0) {
                                if (iZza == 8) {
                                    this.zze = zzfhbVar.zzm();
                                } else if (iZza == 18) {
                                    if (!this.zzf.zza()) {
                                        zzfid<zzb> zzfidVar = this.zzf;
                                        int size = zzfidVar.size();
                                        this.zzf = zzfidVar.zzd(size == 0 ? 10 : size << 1);
                                    }
                                    this.zzf.add((zzb) zzfhbVar.zza(zzb.zzg(), zzfhmVar));
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
                if (zzh == null) {
                    synchronized (zzdwp.class) {
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
        int i = this.zze;
        if (i != 0) {
            zzfhgVar.zzc(1, i);
        }
        for (int i2 = 0; i2 < this.zzf.size(); i2++) {
            zzfhgVar.zza(2, (zzfjc) this.zzf.get(i2));
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zze;
    }

    public final List<zzb> zzc() {
        return this.zzf;
    }

    public final int zzd() {
        return this.zzf.size();
    }
}
