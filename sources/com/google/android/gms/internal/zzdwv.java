package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdwv extends zzfhu<zzdwv, zza> implements zzfje {
    private static final zzdwv zze;
    private static volatile zzfjl<zzdwv> zzf;
    private String zzd = "";

    public static final class zza extends zzfhu.zza<zzdwv, zza> implements zzfje {
        private zza() {
            super(zzdwv.zze);
        }

        /* synthetic */ zza(zzdww zzdwwVar) {
            this();
        }
    }

    static {
        zzdwv zzdwvVar = new zzdwv();
        zze = zzdwvVar;
        zzdwvVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
        ((zzfhu) zzdwvVar).zzb.zzc();
    }

    private zzdwv() {
    }

    public static zzdwv zza(zzfgs zzfgsVar) throws zzfie {
        return (zzdwv) zzfhu.zza(zze, zzfgsVar);
    }

    public static zzdwv zzc() {
        return zze;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int iZzb = (this.zzd.isEmpty() ? 0 : 0 + zzfhg.zzb(1, this.zzd)) + this.zzb.zze();
        this.zzc = iZzb;
        return iZzb;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        zzdww zzdwwVar = null;
        switch (zzdww.zza[i - 1]) {
            case 1:
                return new zzdwv();
            case 2:
                return zze;
            case 3:
                return null;
            case 4:
                return new zza(zzdwwVar);
            case 5:
                zzdwv zzdwvVar = (zzdwv) obj2;
                this.zzd = ((zzfhu.zzh) obj).zza(!this.zzd.isEmpty(), this.zzd, true ^ zzdwvVar.zzd.isEmpty(), zzdwvVar.zzd);
                return this;
            case 6:
                zzfhb zzfhbVar = (zzfhb) obj;
                if (((zzfhm) obj2) == null) {
                    throw null;
                }
                boolean z = false;
                while (!z) {
                    try {
                        int iZza = zzfhbVar.zza();
                        if (iZza != 0) {
                            if (iZza == 10) {
                                this.zzd = zzfhbVar.zzk();
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
                    synchronized (zzdwv.class) {
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
        if (!this.zzd.isEmpty()) {
            zzfhgVar.zza(1, this.zzd);
        }
        this.zzb.zza(zzfhgVar);
    }

    public final String zzb() {
        return this.zzd;
    }
}
