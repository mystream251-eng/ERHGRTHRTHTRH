package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdxy;
import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdxx<T_WRAPPER extends zzdxy<T_ENGINE>, T_ENGINE> {
    public static final zzdxx<zzdxz, Cipher> zza;
    public static final zzdxx<zzdyd, Mac> zzb;
    public static final zzdxx<zzdya, KeyAgreement> zzc;
    public static final zzdxx<zzdyc, KeyPairGenerator> zzd;
    public static final zzdxx<zzdyb, KeyFactory> zze;
    private static final Logger zzf = Logger.getLogger(zzdxx.class.getName());
    private static final List<Provider> zzg;
    private static zzdxx<zzdyf, Signature> zzh;
    private static zzdxx<zzdye, MessageDigest> zzi;
    private T_WRAPPER zzj;
    private List<Provider> zzk = zzg;
    private boolean zzl = true;

    static {
        if (zzdyo.zza()) {
            String[] strArr = {ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL"};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                String str = strArr[i];
                Provider provider = Security.getProvider(str);
                if (provider != null) {
                    arrayList.add(provider);
                } else {
                    zzf.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", str));
                }
            }
            zzg = arrayList;
        } else {
            zzg = new ArrayList();
        }
        zza = new zzdxx<>(new zzdxz());
        zzb = new zzdxx<>(new zzdyd());
        zzh = new zzdxx<>(new zzdyf());
        zzi = new zzdxx<>(new zzdye());
        zzc = new zzdxx<>(new zzdya());
        zzd = new zzdxx<>(new zzdyc());
        zze = new zzdxx<>(new zzdyb());
    }

    private zzdxx(T_WRAPPER t_wrapper) {
        this.zzj = t_wrapper;
    }

    private final boolean zza(String str, Provider provider) {
        try {
            this.zzj.zza(str, provider);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final T_ENGINE zza(String str) throws GeneralSecurityException {
        T_WRAPPER t_wrapper;
        Provider next;
        Iterator<Provider> it = this.zzk.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (zza(str, next)) {
                t_wrapper = this.zzj;
                return (T_ENGINE) t_wrapper.zza(str, next);
            }
        }
        if (!this.zzl) {
            throw new GeneralSecurityException("No good Provider found.");
        }
        t_wrapper = this.zzj;
        next = null;
        return (T_ENGINE) t_wrapper.zza(str, next);
    }
}
