package com.google.android.gms.internal;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;

/* JADX INFO: loaded from: classes2.dex */
final class zzdty implements zzdtf<zzdtd> {
    zzdty() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdtd zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            zzdvq zzdvqVarZza = zzdvq.zza(zzfgsVar);
            if (!(zzdvqVarZza instanceof zzdvq)) {
                throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
            }
            zzdvq zzdvqVar = zzdvqVarZza;
            zzdyp.zza(zzdvqVar.zzb(), 0);
            zzduf.zza(zzdvqVar.zzc().zzc());
            zzdvo zzdvoVarZzc = zzdvqVar.zzc().zzc();
            zzdvu zzdvuVarZzb = zzdvoVarZzc.zzb();
            zzdxu zzdxuVarZza = zzduf.zza(zzdvuVarZzb.zzb());
            byte[] bArrZzc = zzdvqVar.zzd().zzc();
            return new zzdxn((ECPrivateKey) zzdxx.zze.zza("EC").generatePrivate(new ECPrivateKeySpec(new BigInteger(1, bArrZzc), zzdxs.zza(zzdxuVarZza))), zzdvuVarZzb.zzd().zzc(), zzduf.zza(zzdvuVarZzb.zzc()), zzduf.zza(zzdvoVarZzc.zzd()), new zzduh(zzdvoVarZzc.zzc().zzb()));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPrivateKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdtd zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdvq)) {
            throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
        }
        zzdvq zzdvqVar = (zzdvq) zzfjcVar;
        zzdyp.zza(zzdvqVar.zzb(), 0);
        zzduf.zza(zzdvqVar.zzc().zzc());
        zzdvo zzdvoVarZzc = zzdvqVar.zzc().zzc();
        zzdvu zzdvuVarZzb = zzdvoVarZzc.zzb();
        zzdxu zzdxuVarZza = zzduf.zza(zzdvuVarZzb.zzb());
        byte[] bArrZzc = zzdvqVar.zzd().zzc();
        return new zzdxn((ECPrivateKey) zzdxx.zze.zza("EC").generatePrivate(new ECPrivateKeySpec(new BigInteger(1, bArrZzc), zzdxs.zza(zzdxuVarZza))), zzdvuVarZzb.zzd().zzc(), zzduf.zza(zzdvuVarZzb.zzc()), zzduf.zza(zzdvoVarZzc.zzd()), new zzduh(zzdvoVarZzc.zzc().zzb()));
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdvm.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("invalid EciesAeadHkdf key format", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdvm)) {
            throw new GeneralSecurityException("expected EciesAeadHkdfKeyFormat proto");
        }
        zzdvm zzdvmVar = (zzdvm) zzfjcVar;
        zzduf.zza(zzdvmVar.zzb());
        KeyPair keyPairZza = zzdxs.zza(zzdxs.zza(zzduf.zza(zzdvmVar.zzb().zzb().zzb())));
        ECPublicKey eCPublicKey = (ECPublicKey) keyPairZza.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) keyPairZza.getPrivate();
        ECPoint w = eCPublicKey.getW();
        return zzdvq.zze().zza(0).zza((zzdvs) zzdvs.zzf().zza(0).zza(zzdvmVar.zzb()).zza(zzfgs.zza(w.getAffineX().toByteArray())).zzb(zzfgs.zza(w.getAffineY().toByteArray())).zzd()).zza(zzfgs.zza(eCPrivateKey.getS().toByteArray())).zzd();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").zza(zzb(zzfgsVar).zzp()).zza(zzdwg.zzb.ASYMMETRIC_PRIVATE).zzd();
    }
}
