package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdva extends zzfhu<zzdva, zza> implements zzfje {
    private static final zzdva zze;
    private static volatile zzfjl<zzdva> zzf;
    private int zzd;

    public static final class zza extends zzfhu.zza<zzdva, zza> implements zzfje {
        private zza() {
            super(zzdva.zze);
        }

        /* synthetic */ zza(zzdvb zzdvbVar) {
            this();
        }
    }

    static {
        zzdva zzdvaVar = new zzdva();
        zze = zzdvaVar;
        zzdvaVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdvaVar).zzb.zzc();
    }

    private zzdva() {
    }

    public static zzdva zzc() {
        return zze;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = this.zzd;
        int iZzf = (i2 != 0 ? 0 + zzfhg.zzf(1, i2) : 0) + this.zzb.zze();
        this.zzc = iZzf;
        return iZzf;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        boolean z = false;
        zzdvb zzdvbVar = null;
        switch (zzdvb.zza[i - 1]) {
            case 1:
                return new zzdva();
            case 2:
                return zze;
            case 3:
                return null;
            case 4:
                return new zza(zzdvbVar);
            case 5:
                zzdva zzdvaVar = (zzdva) obj2;
                this.zzd = ((zzfhu.zzh) obj).zza(this.zzd != 0, this.zzd, zzdvaVar.zzd != 0, zzdvaVar.zzd);
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
                            if (iZza == 8) {
                                this.zzd = zzfhbVar.zzm();
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
                if (zzf == null) {
                    synchronized (zzdva.class) {
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
        int i = this.zzd;
        if (i != 0) {
            zzfhgVar.zzc(1, i);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final int zzb() {
        return this.zzd;
    }
}
