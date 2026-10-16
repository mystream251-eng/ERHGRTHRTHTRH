package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzduu extends zzfhu<zzduu, zza> implements zzfje {
    private static final zzduu zze;
    private static volatile zzfjl<zzduu> zzf;
    private int zzd;

    public static final class zza extends zzfhu.zza<zzduu, zza> implements zzfje {
        private zza() {
            super(zzduu.zze);
        }

        /* synthetic */ zza(zzduv zzduvVar) {
            this();
        }
    }

    static {
        zzduu zzduuVar = new zzduu();
        zze = zzduuVar;
        zzduuVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzduuVar).zzb.zzc();
    }

    private zzduu() {
    }

    public static zzduu zzc() {
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
        zzduv zzduvVar = null;
        switch (zzduv.zza[i - 1]) {
            case 1:
                return new zzduu();
            case 2:
                return zze;
            case 3:
                return null;
            case 4:
                return new zza(zzduvVar);
            case 5:
                zzduu zzduuVar = (zzduu) obj2;
                this.zzd = ((zzfhu.zzh) obj).zza(this.zzd != 0, this.zzd, zzduuVar.zzd != 0, zzduuVar.zzd);
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
                    synchronized (zzduu.class) {
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
