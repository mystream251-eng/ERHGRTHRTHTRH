package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdvc extends zzfhu<zzdvc, zza> implements zzfje {
    private static final zzdvc zzf;
    private static volatile zzfjl<zzdvc> zzg;
    private int zzd;
    private zzfgs zze = zzfgs.zza;

    public static final class zza extends zzfhu.zza<zzdvc, zza> implements zzfje {
        private zza() {
            super(zzdvc.zzf);
        }

        /* synthetic */ zza(zzdvd zzdvdVar) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzdvc) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzfgs zzfgsVar) {
            zzb();
            ((zzdvc) this.zza).zzb(zzfgsVar);
            return this;
        }
    }

    static {
        zzdvc zzdvcVar = new zzdvc();
        zzf = zzdvcVar;
        zzdvcVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdvcVar).zzb.zzc();
    }

    private zzdvc() {
    }

    public static zzdvc zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdvc) zzfhu.zza(zzf, zzfgsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzfgs zzfgsVar) {
        if (zzfgsVar == null) {
            throw null;
        }
        this.zze = zzfgsVar;
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
        if (!this.zze.zzb()) {
            iZzf += zzfhg.zzc(3, this.zze);
        }
        int iZze = iZzf + this.zzb.zze();
        this.zzc = iZze;
        return iZze;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdvd zzdvdVar = null;
        boolean z = false;
        switch (zzdvd.zza[i - 1]) {
            case 1:
                return new zzdvc();
            case 2:
                return zzf;
            case 3:
                return null;
            case 4:
                return new zza(zzdvdVar);
            case 5:
                zzfhu.zzh zzhVar = (zzfhu.zzh) obj;
                zzdvc zzdvcVar = (zzdvc) obj2;
                this.zzd = zzhVar.zza(this.zzd != 0, this.zzd, zzdvcVar.zzd != 0, zzdvcVar.zzd);
                this.zze = zzhVar.zza(this.zze != zzfgs.zza, this.zze, zzdvcVar.zze != zzfgs.zza, zzdvcVar.zze);
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
                                if (iZza == 8) {
                                    this.zzd = zzfhbVar.zzm();
                                } else if (iZza == 26) {
                                    this.zze = zzfhbVar.zzl();
                                } else if (!zza(iZza, zzfhbVar)) {
                                }
                            }
                            z = true;
                        } catch (zzfie e) {
                            throw new RuntimeException((Throwable) e.zza(this));
                        }
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
                    synchronized (zzdvc.class) {
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
        if (!this.zze.zzb()) {
            zzfhgVar.zza(3, this.zze);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzfgs zzc() {
        return this.zze;
    }
}
