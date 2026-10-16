package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdtu implements zzdtf<zzdsy> {
    zzdtu() {
    }

    private static zzdvg zzb() throws GeneralSecurityException {
        return (zzdvg) zzdvg.zzd().zza(0).zza(zzfgs.zza(zzdyl.zza(32))).zzd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdsy zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            zzdvg zzdvgVarZza = zzdvg.zza(zzfgsVar);
            if (!(zzdvgVarZza instanceof zzdvg)) {
                throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
            }
            zzdvg zzdvgVar = zzdvgVarZza;
            zzdyp.zza(zzdvgVar.zzb(), 0);
            if (zzdvgVar.zzc().zza() == 32) {
                return new zzdxl(zzdvgVar.zzc().zzc());
            }
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        } catch (zzfie e) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305 key", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdsy zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdvg)) {
            throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
        }
        zzdvg zzdvgVar = (zzdvg) zzfjcVar;
        zzdyp.zza(zzdvgVar.zzb(), 0);
        if (zzdvgVar.zzc().zza() == 32) {
            return new zzdxl(zzdvgVar.zzc().zzc());
        }
        throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        return zzb();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        return zzb();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").zza(zzb().zzp()).zza(zzdwg.zzb.SYMMETRIC).zzd();
    }
}
