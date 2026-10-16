package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzwe extends zzwg {
    private Map<Class<? extends NetworkExtras>, NetworkExtras> zza;

    private final <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> zzwi zzc(String str) throws RemoteException {
        try {
            Class<?> cls = Class.forName(str, false, zzwe.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(cls)) {
                MediationAdapter mediationAdapter = (MediationAdapter) cls.newInstance();
                return new zzxh(mediationAdapter, (com.google.ads.mediation.NetworkExtras) this.zza.get(mediationAdapter.getAdditionalParametersType()));
            }
            if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(cls)) {
                return new zzxc((com.google.android.gms.ads.mediation.MediationAdapter) cls.newInstance());
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 64);
            sb.append("Could not instantiate mediation adapter: ");
            sb.append(str);
            sb.append(" (not a valid adapter).");
            zzaky.zze(sb.toString());
            throw new RemoteException();
        } catch (Throwable unused) {
            return zzd(str);
        }
    }

    private final zzwi zzd(String str) throws RemoteException {
        try {
            zzaky.zzb("Reflection failed, retrying using direct instantiation");
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                return new zzxc(new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                return new zzxc(new AdUrlAdapter());
            }
            if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                return new zzxc(new CustomEventAdapter());
            }
            if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
                return new zzxh(customEventAdapter, (CustomEventExtras) this.zza.get(customEventAdapter.getAdditionalParametersType()));
            }
            throw new RemoteException();
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 43);
            sb.append("Could not instantiate mediation adapter: ");
            sb.append(str);
            sb.append(". ");
            zzaky.zzc(sb.toString(), th);
        }
    }

    @Override // com.google.android.gms.internal.zzwf
    public final zzwi zza(String str) throws RemoteException {
        return zzc(str);
    }

    public final void zza(Map<Class<? extends NetworkExtras>, NetworkExtras> map) {
        this.zza = map;
    }

    @Override // com.google.android.gms.internal.zzwf
    public final boolean zzb(String str) throws RemoteException {
        try {
            return CustomEvent.class.isAssignableFrom(Class.forName(str, false, zzwe.class.getClassLoader()));
        } catch (Throwable unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 80);
            sb.append("Could not load custom event implementation class: ");
            sb.append(str);
            sb.append(", assuming old implementation.");
            zzaky.zze(sb.toString());
            return false;
        }
    }
}
