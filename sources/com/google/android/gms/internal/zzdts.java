package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdts implements zzdtf<zzdsy> {
    zzdts() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdsy zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            zzduw zzduwVarZza = zzduw.zza(zzfgsVar);
            if (!(zzduwVarZza instanceof zzduw)) {
                throw new GeneralSecurityException("expected AesEaxKey proto");
            }
            zzduw zzduwVar = zzduwVarZza;
            zzdyp.zza(zzduwVar.zzb(), 0);
            zzdyp.zza(zzduwVar.zzd().zza());
            if (zzduwVar.zzc().zzb() != 12 && zzduwVar.zzc().zzb() != 16) {
                throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
            }
            return new zzdxg(zzduwVar.zzd().zzc(), zzduwVar.zzc().zzb());
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesEaxKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdsy zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzduw)) {
            throw new GeneralSecurityException("expected AesEaxKey proto");
        }
        zzduw zzduwVar = (zzduw) zzfjcVar;
        zzdyp.zza(zzduwVar.zzb(), 0);
        zzdyp.zza(zzduwVar.zzd().zza());
        if (zzduwVar.zzc().zzb() == 12 || zzduwVar.zzc().zzb() == 16) {
            return new zzdxg(zzduwVar.zzd().zzc(), zzduwVar.zzc().zzb());
        }
        throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzduy.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesEaxKeyFormat proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzduy)) {
            throw new GeneralSecurityException("expected AesEaxKeyFormat proto");
        }
        zzduy zzduyVar = (zzduy) zzfjcVar;
        zzdyp.zza(zzduyVar.zzc());
        if (zzduyVar.zzb().zzb() == 12 || zzduyVar.zzb().zzb() == 16) {
            return zzduw.zze().zza(zzfgs.zza(zzdyl.zza(zzduyVar.zzc()))).zza(zzduyVar.zzb()).zza(0).zzd();
        }
        throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.AesEaxKey").zza(zzb(zzfgsVar).zzp()).zza(zzdwg.zzb.SYMMETRIC).zzd();
    }
}
