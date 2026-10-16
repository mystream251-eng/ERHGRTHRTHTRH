package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdtn {
    private static final Logger zza = Logger.getLogger(zzdtn.class.getName());
    private static final ConcurrentMap<String, zzdtf> zzb = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> zzc = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzdsz> zzd = new ConcurrentHashMap();

    public static <P> zzdsz<P> zza(String str) throws GeneralSecurityException {
        String strValueOf;
        String str2;
        if (str == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        zzdsz<P> zzdszVar = zzd.get(str.toLowerCase());
        if (zzdszVar != null) {
            return zzdszVar;
        }
        String strConcat = String.format("no catalogue found for %s. ", str);
        if (str.toLowerCase().startsWith("tinkaead")) {
            strConcat = String.valueOf(strConcat).concat("Maybe call AeadConfig.init().");
        }
        if (str.toLowerCase().startsWith("tinkdeterministicaead")) {
            strValueOf = String.valueOf(strConcat);
            str2 = "Maybe call DeterministicAeadConfig.init().";
        } else if (str.toLowerCase().startsWith("tinkstreamingaead")) {
            strValueOf = String.valueOf(strConcat);
            str2 = "Maybe call StreamingAeadConfig.init().";
        } else if (str.toLowerCase().startsWith("tinkhybriddecrypt") || str.toLowerCase().startsWith("tinkhybridencrypt")) {
            strValueOf = String.valueOf(strConcat);
            str2 = "Maybe call HybridConfig.init().";
        } else if (str.toLowerCase().startsWith("tinkmac")) {
            strValueOf = String.valueOf(strConcat);
            str2 = "Maybe call MacConfig.init().";
        } else {
            if (!str.toLowerCase().startsWith("tinkpublickeysign") && !str.toLowerCase().startsWith("tinkpublickeyverify")) {
                if (str.toLowerCase().startsWith("tink")) {
                    strValueOf = String.valueOf(strConcat);
                    str2 = "Maybe call TinkConfig.init().";
                }
                throw new GeneralSecurityException(strConcat);
            }
            strValueOf = String.valueOf(strConcat);
            str2 = "Maybe call SignatureConfig.init().";
        }
        strConcat = strValueOf.concat(str2);
        throw new GeneralSecurityException(strConcat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <P> zzdtl<P> zza(zzdtg zzdtgVar, zzdtf<P> zzdtfVar) throws GeneralSecurityException {
        zzdwp zzdwpVarZza = zzdtgVar.zza();
        if (zzdwpVarZza.zzd() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        int iZzb = zzdwpVarZza.zzb();
        boolean z = true;
        boolean z2 = false;
        for (zzdwp.zzb zzbVar : zzdwpVarZza.zzc()) {
            if (!zzbVar.zzb()) {
                throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(zzbVar.zze())));
            }
            if (zzbVar.zzf() == zzdxb.UNKNOWN_PREFIX) {
                throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(zzbVar.zze())));
            }
            if (zzbVar.zzd() == zzdwj.UNKNOWN_STATUS) {
                throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(zzbVar.zze())));
            }
            if (zzbVar.zzd() == zzdwj.ENABLED && zzbVar.zze() == iZzb) {
                if (z2) {
                    throw new GeneralSecurityException("keyset contains multiple primary keys");
                }
                z2 = true;
            }
            if (zzbVar.zzc().zzd() != zzdwg.zzb.ASYMMETRIC_PUBLIC) {
                z = false;
            }
        }
        if (!z2 && !z) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
        zzdtl<P> zzdtlVar = (zzdtl<P>) new zzdtl();
        for (zzdwp.zzb zzbVar2 : zzdtgVar.zza().zzc()) {
            if (zzbVar2.zzd() == zzdwj.ENABLED) {
                zzdtm zzdtmVarZza = zzdtlVar.zza(zza(zzbVar2.zzc().zzb(), zzbVar2.zzc().zzc()), zzbVar2);
                if (zzbVar2.zze() == zzdtgVar.zza().zzb()) {
                    zzdtlVar.zza(zzdtmVarZza);
                }
            }
        }
        return zzdtlVar;
    }

    public static <P> zzdwg zza(zzdwl zzdwlVar) throws GeneralSecurityException {
        zzdtf zzdtfVarZzb = zzb(zzdwlVar.zzb());
        if (zzc.get(zzdwlVar.zzb()).booleanValue()) {
            return zzdtfVarZzb.zzc(zzdwlVar.zzc());
        }
        String strValueOf = String.valueOf(zzdwlVar.zzb());
        throw new GeneralSecurityException(strValueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(strValueOf) : new String("newKey-operation not permitted for key type "));
    }

    public static <P> zzfjc zza(String str, zzfjc zzfjcVar) throws GeneralSecurityException {
        zzdtf zzdtfVarZzb = zzb(str);
        if (zzc.get(str).booleanValue()) {
            return zzdtfVarZzb.zzb(zzfjcVar);
        }
        String strValueOf = String.valueOf(str);
        throw new GeneralSecurityException(strValueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(strValueOf) : new String("newKey-operation not permitted for key type "));
    }

    private static <P> P zza(String str, zzfgs zzfgsVar) throws GeneralSecurityException {
        return (P) zzb(str).zza(zzfgsVar);
    }

    public static <P> P zza(String str, byte[] bArr) throws GeneralSecurityException {
        return (P) zza(str, zzfgs.zza(bArr));
    }

    public static synchronized <P> void zza(String str, zzdsz<P> zzdszVar) throws GeneralSecurityException {
        if (zzd.containsKey(str.toLowerCase())) {
            if (!zzdszVar.getClass().equals(zzd.get(str.toLowerCase()).getClass())) {
                Logger logger = zza;
                Level level = Level.WARNING;
                String strValueOf = String.valueOf(str);
                logger.logp(level, "com.google.crypto.tink.Registry", "addCatalogue", strValueOf.length() != 0 ? "Attempted overwrite of a catalogueName catalogue for name ".concat(strValueOf) : new String("Attempted overwrite of a catalogueName catalogue for name "));
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 47);
                sb.append("catalogue for name ");
                sb.append(str);
                sb.append(" has been already registered");
                throw new GeneralSecurityException(sb.toString());
            }
        }
        zzd.put(str.toLowerCase(), zzdszVar);
    }

    public static <P> void zza(String str, zzdtf<P> zzdtfVar) throws GeneralSecurityException {
        zza(str, zzdtfVar, true);
    }

    public static synchronized <P> void zza(String str, zzdtf<P> zzdtfVar, boolean z) throws GeneralSecurityException {
        try {
            if (zzdtfVar == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            if (zzb.containsKey(str)) {
                zzdtf zzdtfVarZzb = zzb(str);
                boolean zBooleanValue = zzc.get(str).booleanValue();
                if (!zzdtfVar.getClass().equals(zzdtfVarZzb.getClass()) || (!zBooleanValue && z)) {
                    Logger logger = zza;
                    Level level = Level.WARNING;
                    String strValueOf = String.valueOf(str);
                    logger.logp(level, "com.google.crypto.tink.Registry", "registerKeyManager", strValueOf.length() != 0 ? "Attempted overwrite of a registered key manager for key type ".concat(strValueOf) : new String("Attempted overwrite of a registered key manager for key type "));
                    throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", str, zzdtfVarZzb.getClass().getName(), zzdtfVar.getClass().getName()));
                }
            }
            zzb.put(str, zzdtfVar);
            zzc.put(str, Boolean.valueOf(z));
        } catch (Throwable th) {
            throw th;
        }
    }

    private static <P> zzdtf<P> zzb(String str) throws GeneralSecurityException {
        zzdtf<P> zzdtfVar = zzb.get(str);
        if (zzdtfVar != null) {
            return zzdtfVar;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 78);
        sb.append("No key manager found for key type: ");
        sb.append(str);
        sb.append(".  Check the configuration of the registry.");
        throw new GeneralSecurityException(sb.toString());
    }

    public static <P> zzfjc zzb(zzdwl zzdwlVar) throws GeneralSecurityException {
        zzdtf zzdtfVarZzb = zzb(zzdwlVar.zzb());
        if (zzc.get(zzdwlVar.zzb()).booleanValue()) {
            return zzdtfVarZzb.zzb(zzdwlVar.zzc());
        }
        String strValueOf = String.valueOf(zzdwlVar.zzb());
        throw new GeneralSecurityException(strValueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(strValueOf) : new String("newKey-operation not permitted for key type "));
    }

    public static <P> P zzb(String str, zzfjc zzfjcVar) throws GeneralSecurityException {
        return (P) zzb(str).zza(zzfjcVar);
    }
}
