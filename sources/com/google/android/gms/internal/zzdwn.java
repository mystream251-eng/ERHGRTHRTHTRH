package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwn extends zzfhu<zzdwn, zza> implements zzfje {
    private static final zzdwn zzi;
    private static volatile zzfjl<zzdwn> zzj;
    private int zzf;
    private boolean zzg;
    private String zzd = "";
    private String zze = "";
    private String zzh = "";

    public static final class zza extends zzfhu.zza<zzdwn, zza> implements zzfje {
        private zza() {
            super(zzdwn.zzi);
        }

        /* synthetic */ zza(zzdwo zzdwoVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdwn) this.zza).zza(0);
            return this;
        }

        public final zza zza(String str) {
            zzb();
            ((zzdwn) this.zza).zza(str);
            return this;
        }

        public final zza zza(boolean z) {
            zzb();
            ((zzdwn) this.zza).zza(true);
            return this;
        }

        public final zza zzb(String str) {
            zzb();
            ((zzdwn) this.zza).zzb(str);
            return this;
        }

        public final zza zzc(String str) {
            zzb();
            ((zzdwn) this.zza).zzc(str);
            return this;
        }
    }

    static {
        zzdwn zzdwnVar = new zzdwn();
        zzi = zzdwnVar;
        zzdwnVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwnVar).zzb.zzc();
    }

    private zzdwn() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str) {
        if (str == null) {
            throw null;
        }
        this.zzd = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(boolean z) {
        this.zzg = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(String str) {
        if (str == null) {
            throw null;
        }
        this.zze = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(String str) {
        if (str == null) {
            throw null;
        }
        this.zzh = str;
    }

    public static zza zzg() {
        return (zza) ((zzfhu.zza) zzi.zza(zzfhu.zzg.zzh, (Object) null, (Object) null));
    }

    public static zzdwn zzh() {
        return zzi;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int iZzb = this.zzd.isEmpty() ? 0 : 0 + zzfhg.zzb(1, this.zzd);
        if (!this.zze.isEmpty()) {
            iZzb += zzfhg.zzb(2, this.zze);
        }
        int i2 = this.zzf;
        if (i2 != 0) {
            iZzb += zzfhg.zzf(3, i2);
        }
        boolean z = this.zzg;
        if (z) {
            iZzb += zzfhg.zzb(4, z);
        }
        if (!this.zzh.isEmpty()) {
            iZzb += zzfhg.zzb(5, this.zzh);
        }
        int iZze = iZzb + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        boolean z = false;
        zzdwo zzdwoVar = null;
        switch (zzdwo.zza[i - 1]) {
            case 1:
                return new zzdwn();
            case 2:
                return zzi;
            case 3:
                return null;
            case 4:
                return new zza(zzdwoVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdwn zzdwnVar = (zzdwn) obj2;
                this.zzd = zzhVar.zza(!this.zzd.isEmpty(), this.zzd, !zzdwnVar.zzd.isEmpty(), zzdwnVar.zzd);
                this.zze = zzhVar.zza(!this.zze.isEmpty(), this.zze, !zzdwnVar.zze.isEmpty(), zzdwnVar.zze);
                this.zzf = zzhVar.zza(this.zzf != 0, this.zzf, zzdwnVar.zzf != 0, zzdwnVar.zzf);
                boolean z2 = this.zzg;
                boolean z3 = zzdwnVar.zzg;
                this.zzg = zzhVar.zza(z2, z2, z3, z3);
                this.zzh = zzhVar.zza(!this.zzh.isEmpty(), this.zzh, true ^ zzdwnVar.zzh.isEmpty(), zzdwnVar.zzh);
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                if (((zzfhm) obj2) == null) {
                    throw null;
                }
                while (!z) {
                    try {
                        try {
                            int iZza = zzfhbVar.zza();
                            if (iZza != 0) {
                                if (iZza == 10) {
                                    this.zzd = zzfhbVar.zzk();
                                } else if (iZza == 18) {
                                    this.zze = zzfhbVar.zzk();
                                } else if (iZza == 24) {
                                    this.zzf = zzfhbVar.zzm();
                                } else if (iZza == 32) {
                                    this.zzg = zzfhbVar.zzi();
                                } else if (iZza == 42) {
                                    this.zzh = zzfhbVar.zzk();
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
                if (zzj == null) {
                    synchronized (zzdwn.class) {
                        if (zzj == null) {
                            zzj = new zzfhu.zzb(zzi);
                        }
                        break;
                    }
                }
                return zzj;
            case 9:
                return (byte) 1;
            case 10:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
        return zzi;
    }

    public final void zza(zzfhg zzfhgVar) throws IOException {
        if (!this.zzd.isEmpty()) {
            zzfhgVar.zza(1, this.zzd);
        }
        if (!this.zze.isEmpty()) {
            zzfhgVar.zza(2, this.zze);
        }
        int i = this.zzf;
        if (i != 0) {
            zzfhgVar.zzc(3, i);
        }
        boolean z = this.zzg;
        if (z) {
            zzfhgVar.zza(4, z);
        }
        if (!this.zzh.isEmpty()) {
            zzfhgVar.zza(5, this.zzh);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final String zzb() {
        return this.zzd;
    }

    public final String zzc() {
        return this.zze;
    }

    public final int zzd() {
        return this.zzf;
    }

    public final boolean zze() {
        return this.zzg;
    }

    public final String zzf() {
        return this.zzh;
    }
}
