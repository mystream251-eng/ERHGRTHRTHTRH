package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdtz implements zzdtf<zzdte> {
    zzdtz() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdte zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            zzdvs zzdvsVarZza = zzdvs.zza(zzfgsVar);
            if (!(zzdvsVarZza instanceof zzdvs)) {
                throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
            }
            zzdvs zzdvsVar = zzdvsVarZza;
            zzdyp.zza(zzdvsVar.zzb(), 0);
            zzduf.zza(zzdvsVar.zzc());
            zzdvo zzdvoVarZzc = zzdvsVar.zzc();
            zzdvu zzdvuVarZzb = zzdvoVarZzc.zzb();
            return new zzdxo(zzdxs.zza(zzduf.zza(zzdvuVarZzb.zzb()), zzdvsVar.zzd().zzc(), zzdvsVar.zze().zzc()), zzdvuVarZzb.zzd().zzc(), zzduf.zza(zzdvuVarZzb.zzc()), zzduf.zza(zzdvoVarZzc.zzd()), new zzduh(zzdvoVarZzc.zzc().zzb()));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdte zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdvs)) {
            throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
        }
        zzdvs zzdvsVar = (zzdvs) zzfjcVar;
        zzdyp.zza(zzdvsVar.zzb(), 0);
        zzduf.zza(zzdvsVar.zzc());
        zzdvo zzdvoVarZzc = zzdvsVar.zzc();
        zzdvu zzdvuVarZzb = zzdvoVarZzc.zzb();
        return new zzdxo(zzdxs.zza(zzduf.zza(zzdvuVarZzb.zzb()), zzdvsVar.zzd().zzc(), zzdvsVar.zze().zzc()), zzdvuVarZzb.zzd().zzc(), zzduf.zza(zzdvuVarZzb.zzc()), zzduf.zza(zzdvoVarZzc.zzd()), new zzduh(zzdvoVarZzc.zzc().zzb()));
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
}
