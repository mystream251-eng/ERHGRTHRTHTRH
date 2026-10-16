package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwr extends zzfhu<zzdwr, zza> implements zzfje {
    private static final zzdwr zzg;
    private static volatile zzfjl<zzdwr> zzh;
    private int zzd;
    private int zze;
    private zzfid<zzb> zzf = zzu();

    public static final class zza extends zzfhu.zza<zzdwr, zza> implements zzfje {
        private zza() {
            super(zzdwr.zzg);
        }

        /* synthetic */ zza(zzdws zzdwsVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdwr) this.zza).zza(i);
            return this;
        }

        public final zza zza(zzb zzbVar) {
            zzb();
            ((zzdwr) this.zza).zza(zzbVar);
            return this;
        }
    }

    public static final class zzb extends zzfhu<zzb, zza> implements zzfje {
        private static final zzb zzh;
        private static volatile zzfjl<zzb> zzi;
        private String zzd = "";
        private int zze;
        private int zzf;
        private int zzg;

        public static final class zza extends zzfhu.zza<zzb, zza> implements zzfje {
            private zza() {
                super(zzb.zzh);
            }

            /* synthetic */ zza(zzdws zzdwsVar) {
                this();
            }

            public final zza zza(int i) {
                zzb();
                ((zzb) this.zza).zza(i);
                return this;
            }

            public final zza zza(zzdwj zzdwjVar) {
                zzb();
                ((zzb) this.zza).zza(zzdwjVar);
                return this;
            }

            public final zza zza(zzdxb zzdxbVar) {
                zzb();
                ((zzb) this.zza).zza(zzdxbVar);
                return this;
            }

            public final zza zza(String str) {
                zzb();
                ((zzb) this.zza).zza(str);
                return this;
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

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i) {
            this.zzf = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzdwj zzdwjVar) {
            if (zzdwjVar == null) {
                throw null;
            }
            this.zze = zzdwjVar.zza();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzdxb zzdxbVar) {
            if (zzdxbVar == null) {
                throw null;
            }
            this.zzg = zzdxbVar.zza();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw null;
            }
            this.zzd = str;
        }

        public static zza zzb() {
            return (zza) ((zzfhu.zza) zzh.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
        }

        public static zzb zzc() {
            return zzh;
        }

        public final int zza() {
            int i = this.zzc;
            if (i != -1) {
                return i;
            }
            int iZzb = this.zzd.isEmpty() ? 0 : 0 + zzfhg.zzb(1, this.zzd);
            if (this.zze != zzdwj.UNKNOWN_STATUS.zza()) {
                iZzb += zzfhg.zzh(2, this.zze);
            }
            int i2 = this.zzf;
            if (i2 != 0) {
                iZzb += zzfhg.zzf(3, i2);
            }
            if (this.zzg != zzdxb.UNKNOWN_PREFIX.zza()) {
                iZzb += zzfhg.zzh(4, this.zzg);
            }
            int iZze = iZzb + this.zzb.zze();
            this.zzc = iZze;
            return iZze;
        }

        protected final Object zza(int i, Object obj, Object obj2) {
            zzdws zzdwsVar = null;
            boolean z = false;
            switch (zzdws.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return zzh;
                case 3:
                    return null;
                case 4:
                    return new zza(zzdwsVar);
                case 5:
                    zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                    zzb zzbVar = (zzb) obj2;
                    this.zzd = zzhVar.zza(!this.zzd.isEmpty(), this.zzd, !zzbVar.zzd.isEmpty(), zzbVar.zzd);
                    this.zze = zzhVar.zza(this.zze != 0, this.zze, zzbVar.zze != 0, zzbVar.zze);
                    this.zzf = zzhVar.zza(this.zzf != 0, this.zzf, zzbVar.zzf != 0, zzbVar.zzf);
                    this.zzg = zzhVar.zza(this.zzg != 0, this.zzg, zzbVar.zzg != 0, zzbVar.zzg);
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
            if (!this.zzd.isEmpty()) {
                zzfhgVar.zza(1, this.zzd);
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
    }

    static {
        zzdwr zzdwrVar = new zzdwr();
        zzg = zzdwrVar;
        zzdwrVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwrVar).zzb.zzc();
    }

    private zzdwr() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zze = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzb zzbVar) {
        if (zzbVar == null) {
            throw null;
        }
        if (!this.zzf.zza()) {
            zzfid<zzb> zzfidVar = this.zzf;
            int size = zzfidVar.size();
            this.zzf = zzfidVar.zzd(size == 0 ? 10 : size << 1);
        }
        this.zzf.add(zzbVar);
    }

    public static zza zzb() {
        return (zza) ((zzfhu.zza) zzg.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
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
        zzdws zzdwsVar = null;
        switch (zzdws.zza[i - 1]) {
            case 1:
                return new zzdwr();
            case 2:
                return zzg;
            case 3:
                this.zzf.zzb();
                return null;
            case 4:
                return new zza(zzdwsVar);
            case 5:
                zzfhu.zzf zzfVar = (zzfhu.zzh) obj;
                zzdwr zzdwrVar = (zzdwr) obj2;
                this.zze = zzfVar.zza(this.zze != 0, this.zze, zzdwrVar.zze != 0, zzdwrVar.zze);
                this.zzf = zzfVar.zza(this.zzf, zzdwrVar.zzf);
                if (zzfVar == zzfhu.zzf.zza) {
                    this.zzd |= zzdwrVar.zzd;
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
                                    this.zzf.add((zzb) zzfhbVar.zza(zzb.zzc(), zzfhmVar));
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
                    synchronized (zzdwr.class) {
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
}
