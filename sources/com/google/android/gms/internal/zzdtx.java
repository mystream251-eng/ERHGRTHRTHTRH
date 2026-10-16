package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdtx implements zzdtf<zzdsy> {
    zzdtx() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdsy zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            zzdwx zzdwxVarZza = zzdwx.zza(zzfgsVar);
            if (!(zzdwxVarZza instanceof zzdwx)) {
                throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
            }
            zzdwx zzdwxVar = zzdwxVarZza;
            zzdyp.zza(zzdwxVar.zzb(), 0);
            String strZzb = zzdwxVar.zzc().zzb();
            return new zzdtw(zzdwxVar.zzc().zzc(), zzdti.zza(strZzb).zzb(strZzb));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized KmSEnvelopeAeadKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdsy zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdwx)) {
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
        }
        zzdwx zzdwxVar = (zzdwx) zzfjcVar;
        zzdyp.zza(zzdwxVar.zzb(), 0);
        String strZzb = zzdwxVar.zzc().zzb();
        return new zzdtw(zzdwxVar.zzc().zzc(), zzdti.zza(strZzb).zzb(strZzb));
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdwz.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized KmsEnvelopeAeadKeyFormat proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdwz)) {
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKeyFormat proto");
        }
        return zzdwx.zzd().zza((zzdwz) zzfjcVar).zza(0).zzd();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").zza(zzb(zzfgsVar).zzp()).zza(zzdwg.zzb.REMOTE).zzd();
    }
}
