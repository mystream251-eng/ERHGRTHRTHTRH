package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
final class zzduh implements zzdxm {
    private final String zza;
    private final int zzb;
    private zzdvc zzc;
    private zzdum zzd;
    private int zze;

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.android.gms.internal.zzfie */
    zzduh(zzdwl zzdwlVar) throws GeneralSecurityException {
        String strZzb = zzdwlVar.zzb();
        this.zza = strZzb;
        if (strZzb.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzdve zzdveVarZza = zzdve.zza(zzdwlVar.zzc());
                this.zzc = zzdtn.zzb(zzdwlVar);
                this.zzb = zzdveVarZza.zzb();
                return;
            } catch (zzfie e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        }
        if (!this.zza.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            String strValueOf = String.valueOf(this.zza);
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "unsupported AEAD DEM key type: ".concat(strValueOf) : new String("unsupported AEAD DEM key type: "));
        }
        try {
            zzduo zzduoVarZza = zzduo.zza(zzdwlVar.zzc());
            this.zzd = zzdtn.zzb(zzdwlVar);
            this.zze = zzduoVarZza.zzb().zzc();
            this.zzb = this.zze + zzduoVarZza.zzc().zzc();
        } catch (zzfie e2) {
            throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e2);
        }
    }

    @Override // com.google.android.gms.internal.zzdxm
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.zzdxm
    public final zzdsy zza(byte[] bArr) throws GeneralSecurityException {
        zzfjc zzfjcVar;
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if (this.zza.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            zzfjcVar = (zzdvc) ((zzdvc.zza) zzdvc.zzd().zza(this.zzc)).zza(zzfgs.zza(bArr, 0, this.zzb)).zzd();
        } else {
            if (!this.zza.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
                throw new GeneralSecurityException("unknown DEM key type");
            }
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
            byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
            zzfjcVar = (zzdum) zzdum.zze().zza(this.zzd.zzb()).zza((zzduq) ((zzduq.zza) zzduq.zze().zza(this.zzd.zzc())).zza(zzfgs.zza(bArrCopyOfRange)).zzd()).zza((zzdwa) ((zzdwa.zza) zzdwa.zze().zza(this.zzd.zzd())).zza(zzfgs.zza(bArrCopyOfRange2)).zzd()).zzd();
        }
        return (zzdsy) zzdtn.zzb(this.zza, zzfjcVar);
    }
}
