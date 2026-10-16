package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
final class zzdtr implements zzdtf<zzdyi> {
    zzdtr() {
    }

    private static void zza(zzduu zzduuVar) throws GeneralSecurityException {
        if (zzduuVar.zzb() < 12 || zzduuVar.zzb() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    @Override // com.google.android.gms.internal.zzdtf
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzdxf zza(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            zzduq zzduqVarZza = zzduq.zza(zzfgsVar);
            if (!(zzduqVarZza instanceof zzduq)) {
                throw new GeneralSecurityException("expected AesCtrKey proto");
            }
            zzduq zzduqVar = zzduqVarZza;
            zzdyp.zza(zzduqVar.zzb(), 0);
            zzdyp.zza(zzduqVar.zzd().zza());
            zza(zzduqVar.zzc());
            return new zzdxf(zzduqVar.zzd().zzc(), zzduqVar.zzc().zzb());
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesCtrKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final /* synthetic */ zzdyi zza(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzduq)) {
            throw new GeneralSecurityException("expected AesCtrKey proto");
        }
        zzduq zzduqVar = (zzduq) zzfjcVar;
        zzdyp.zza(zzduqVar.zzb(), 0);
        zzdyp.zza(zzduqVar.zzd().zza());
        zza(zzduqVar.zzc());
        return new zzdxf(zzduqVar.zzd().zzc(), zzduqVar.zzc().zzb());
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfgs zzfgsVar) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdus.zza(zzfgsVar));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesCtrKeyFormat proto", e);
        }
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzfjc zzb(zzfjc zzfjcVar) throws GeneralSecurityException {
        if (!(zzfjcVar instanceof zzdus)) {
            throw new GeneralSecurityException("expected AesCtrKeyFormat proto");
        }
        zzdus zzdusVar = (zzdus) zzfjcVar;
        zzdyp.zza(zzdusVar.zzc());
        zza(zzdusVar.zzb());
        return zzduq.zze().zza(zzdusVar.zzb()).zza(zzfgs.zza(zzdyl.zza(zzdusVar.zzc()))).zza(0).zzd();
    }

    @Override // com.google.android.gms.internal.zzdtf
    public final zzdwg zzc(zzfgs zzfgsVar) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zze().zza("type.googleapis.com/google.crypto.tink.AesCtrKey").zza(zzb(zzfgsVar).zzp()).zza(zzdwg.zzb.SYMMETRIC).zzd();
    }
}
