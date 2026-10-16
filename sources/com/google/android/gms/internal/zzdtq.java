package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
class zzdtq implements zzdtf<zzdsy> {
    private static final Logger zza = Logger.getLogger(zzdtq.class.getName());

    zzdtq() throws GeneralSecurityException {
        zzdtn.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", new zzdtr());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdsy zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            zzdum zzdumVarZza = zzdum.zza(zzfgsVar);
            if (!(zzdumVarZza instanceof zzdum)) {
                throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
            }
            zzdum zzdumVar = zzdumVarZza;
            zzdyp.zza(zzdumVar.zzb(), 0);
            return new zzdxw((zzdyi) zzdtn.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", zzdumVar.zzc()), (zzdtj) zzdtn.zzb("type.googleapis.com/google.crypto.tink.HmacKey", zzdumVar.zzd()), zzdumVar.zzd().zzc().zzc());
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdsy zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdum)) {
            throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
        }
        zzdum zzdumVar = (zzdum) zzfjcVar;
        zzdyp.zza(zzdumVar.zzb(), 0);
        return new zzdxw((zzdyi) zzdtn.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", zzdumVar.zzc()), (zzdtj) zzdtn.zzb("type.googleapis.com/google.crypto.tink.HmacKey", zzdumVar.zzd()), zzdumVar.zzd().zzc().zzc());
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzduo.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKeyFormat proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzduo)) {
            throw new GeneralSecurityException("expected AesCtrHmacAeadKeyFormat proto");
        }
        zzduo zzduoVar = (zzduo) zzfjcVar;
        zzduq zzduqVarZza = zzdtn.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", (zzfjc) zzduoVar.zzb());
        return zzdum.zze().zza(zzduqVarZza).zza(zzdtn.zza("type.googleapis.com/google.crypto.tink.HmacKey", (zzfjc) zzduoVar.zzc())).zza(0).zzd();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").zza(zzb(zzfgsVar).zzp()).zza(zzdwg.zzb.SYMMETRIC).zzd();
    }
}
