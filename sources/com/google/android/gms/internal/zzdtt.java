package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdtt implements zzdtf<zzdsy> {
    zzdtt() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdsy zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            zzdvc zzdvcVarZza = zzdvc.zza(zzfgsVar);
            if (!(zzdvcVarZza instanceof zzdvc)) {
                throw new GeneralSecurityException("expected AesGcmKey proto");
            }
            zzdvc zzdvcVar = zzdvcVarZza;
            zzdyp.zza(zzdvcVar.zzb(), 0);
            zzdyp.zza(zzdvcVar.zzc().zza());
            return new zzdxh(zzdvcVar.zzc().zzc());
        } catch (zzfie unused) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdsy zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdvc)) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
        zzdvc zzdvcVar = (zzdvc) zzfjcVar;
        zzdyp.zza(zzdvcVar.zzb(), 0);
        zzdyp.zza(zzdvcVar.zzc().zza());
        return new zzdxh(zzdvcVar.zzc().zzc());
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdve.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesGcmKeyFormat proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdve)) {
            throw new GeneralSecurityException("expected AesGcmKeyFormat proto");
        }
        zzdve zzdveVar = (zzdve) zzfjcVar;
        zzdyp.zza(zzdveVar.zzb());
        return zzdvc.zzd().zza(zzfgs.zza(zzdyl.zza(zzdveVar.zzb()))).zza(0).zzd();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.AesGcmKey").zza(zzb(zzfgsVar).zzp()).zza(zzdwg.zzb.SYMMETRIC).zzd();
    }
}
