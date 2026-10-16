package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdtv implements zzdtf<zzdsy> {
    zzdtv() {
    }

    private static zzdsy zzc(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdwt)) {
            throw new GeneralSecurityException("expected KmsAeadKey proto");
        }
        zzdwt zzdwtVar = (zzdwt) zzfjcVar;
        zzdyp.zza(zzdwtVar.zzb(), 0);
        String strZzb = zzdwtVar.zzc().zzb();
        return zzdti.zza(strZzb).zzb(strZzb);
    }

    private static zzdsy zzd(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzc((zzfjc) zzdwt.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected KmsAeadKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdsy zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        return zzd(zzfgsVar);
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdsy zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        return zzc(zzfjcVar);
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdwv.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized KmsAeadKeyFormat proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdwv)) {
            throw new GeneralSecurityException("expected KmsAeadKeyFormat proto");
        }
        return zzdwt.zzd().zza((zzdwv) zzfjcVar).zza(0).zzd();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.KmsAeadKey").zza(zzb(zzfgsVar).zzp()).zza(zzdwg.zzb.REMOTE).zzd();
    }
}
