package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
final class zzdui implements zzdtf<zzdtj> {
    zzdui() {
    }

    private static void zza(zzdwe zzdweVar) throws GeneralSecurityException {
        if (zzdweVar.zzc() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        int i = zzduj.zza[zzdweVar.zzb().ordinal()];
        if (i == 1) {
            if (zzdweVar.zzc() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (i == 2) {
            if (zzdweVar.zzc() > 32) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            if (i != 3) {
                throw new GeneralSecurityException("unknown hash type");
            }
            if (zzdweVar.zzc() > 64) {
                throw new GeneralSecurityException("tag size too big");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdtj zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        zzdyj zzdyjVar;
        try {
            zzdwa zzdwaVarZza = zzdwa.zza(zzfgsVar);
            if (!(zzdwaVarZza instanceof zzdwa)) {
                throw new GeneralSecurityException("expected HmacKey proto");
            }
            zzdwa zzdwaVar = zzdwaVarZza;
            zzdyp.zza(zzdwaVar.zzb(), 0);
            if (zzdwaVar.zzd().zza() < 16) {
                throw new GeneralSecurityException("key too short");
            }
            zza(zzdwaVar.zzc());
            zzdvy zzdvyVarZzb = zzdwaVar.zzc().zzb();
            SecretKeySpec secretKeySpec = new SecretKeySpec(zzdwaVar.zzd().zzc(), "HMAC");
            int iZzc = zzdwaVar.zzc().zzc();
            int i = zzduj.zza[zzdvyVarZzb.ordinal()];
            if (i == 1) {
                zzdyjVar = new zzdyj("HMACSHA1", secretKeySpec, iZzc);
            } else if (i == 2) {
                zzdyjVar = new zzdyj("HMACSHA256", secretKeySpec, iZzc);
            } else {
                if (i != 3) {
                    throw new GeneralSecurityException("unknown hash");
                }
                zzdyjVar = new zzdyj("HMACSHA512", secretKeySpec, iZzc);
            }
            return zzdyjVar;
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized HmacKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdtj zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdwa)) {
            throw new GeneralSecurityException("expected HmacKey proto");
        }
        zzdwa zzdwaVar = (zzdwa) zzfjcVar;
        zzdyp.zza(zzdwaVar.zzb(), 0);
        if (zzdwaVar.zzd().zza() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zza(zzdwaVar.zzc());
        zzdvy zzdvyVarZzb = zzdwaVar.zzc().zzb();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzdwaVar.zzd().zzc(), "HMAC");
        int iZzc = zzdwaVar.zzc().zzc();
        int i = zzduj.zza[zzdvyVarZzb.ordinal()];
        if (i == 1) {
            return new zzdyj("HMACSHA1", secretKeySpec, iZzc);
        }
        if (i == 2) {
            return new zzdyj("HMACSHA256", secretKeySpec, iZzc);
        }
        if (i == 3) {
            return new zzdyj("HMACSHA512", secretKeySpec, iZzc);
        }
        throw new GeneralSecurityException("unknown hash");
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdwc.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized HmacKeyFormat proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdwc)) {
            throw new GeneralSecurityException("expected HmacKeyFormat proto");
        }
        zzdwc zzdwcVar = (zzdwc) zzfjcVar;
        if (zzdwcVar.zzc() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zza(zzdwcVar.zzb());
        return zzdwa.zze().zza(0).zza(zzdwcVar.zzb()).zza(zzfgs.zza(zzdyl.zza(zzdwcVar.zzc()))).zzd();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.HmacKey").zza(zzb(zzfgsVar).zzp()).zza(zzdwg.zzb.SYMMETRIC).zzd();
    }
}
