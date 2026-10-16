package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzuv {
    private static final zzus zza = zzus.zza();
    private static final float zzb = ((Float) zzlc.zzf().zza(zzoi.zzcx)).floatValue();
    private static final long zzc = ((Long) zzlc.zzf().zza(zzoi.zzcv)).longValue();
    private static final float zzd = ((Float) zzlc.zzf().zza(zzoi.zzcy)).floatValue();
    private static final long zze = ((Long) zzlc.zzf().zza(zzoi.zzcw)).longValue();

    private static int zza(long j, int i) {
        return (int) ((j >>> ((i % 16) * 4)) & 15);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x003a  */
    /* JADX WARN: Code duplicated, block: B:16:0x004a  */
    /* JADX WARN: Code duplicated, block: B:18:0x0050  */
    /* JADX WARN: Code duplicated, block: B:7:0x002f  */
    /* JADX WARN: Code duplicated, block: B:9:0x0035  */
    static boolean zza() {
        float f;
        int iZza;
        float f2;
        int iZzh = zza.zzh();
        int iZzi = zza.zzi();
        int iZzg = zza.zzg() + zza.zzf();
        int iZza2 = Integer.MAX_VALUE;
        if (iZzh < 16) {
            long j = zze;
            if (j != 0) {
                iZza = zza(j, iZzh);
            } else {
                f = zzd;
                if (f != 0.0f) {
                    iZza = ((int) (f * iZzh)) + 1;
                } else {
                    iZza = Integer.MAX_VALUE;
                }
            }
        } else {
            f = zzd;
            if (f != 0.0f) {
                iZza = ((int) (f * iZzh)) + 1;
            } else {
                iZza = Integer.MAX_VALUE;
            }
        }
        if (iZzi > iZza) {
            return false;
        }
        if (iZzh < 16) {
            long j2 = zzc;
            if (j2 != 0) {
                iZza2 = zza(j2, iZzh);
            } else {
                f2 = zzb;
                if (f2 != 0.0f) {
                    iZza2 = (int) (f2 * iZzh);
                }
            }
        } else {
            f2 = zzb;
            if (f2 != 0.0f) {
                iZza2 = (int) (f2 * iZzh);
            }
        }
        return iZzg <= iZza2;
    }
}
