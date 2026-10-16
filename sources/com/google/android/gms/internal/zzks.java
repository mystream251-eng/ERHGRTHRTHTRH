package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.FrameLayout;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzks {
    private zzmb zza;
    private final Object zzb = new Object();
    private final zzkj zzc;
    private final zzki zzd;
    private final zznc zze;
    private final zzso zzf;
    private final zzafg zzg;
    private final zzyp zzh;
    private final zzsp zzi;

    abstract class zza<T> {
        zza() {
        }

        protected abstract T zza() throws RemoteException;

        protected abstract T zza(zzmb zzmbVar) throws RemoteException;

        protected final T zzb() {
            zzmb zzmbVarZzb = zzks.this.zzb();
            if (zzmbVarZzb == null) {
                zzaky.zze("ClientApi class cannot be loaded.");
                return null;
            }
            try {
                return zza(zzmbVarZzb);
            } catch (RemoteException e) {
                zzaky.zzc("Cannot invoke local loader using ClientApi class", e);
                return null;
            }
        }

        protected final T zzc() {
            try {
                return zza();
            } catch (RemoteException e) {
                zzaky.zzc("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public zzks(zzkj zzkjVar, zzki zzkiVar, zznc zzncVar, zzso zzsoVar, zzafg zzafgVar, zzyp zzypVar, zzsp zzspVar) {
        this.zzc = zzkjVar;
        this.zzd = zzkiVar;
        this.zze = zzncVar;
        this.zzf = zzsoVar;
        this.zzg = zzafgVar;
        this.zzh = zzypVar;
        this.zzi = zzspVar;
    }

    private static zzmb zza() {
        try {
            Object objNewInstance = zzks.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (objNewInstance instanceof IBinder) {
                return zzmc.asInterface((IBinder) objNewInstance);
            }
            zzaky.zze("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Exception e) {
            zzaky.zzc("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    static <T> T zza(Context context, boolean z, zza<T> zzaVar) {
        if (!z) {
            zzlc.zza();
            if (!zzako.zzc(context)) {
                zzaky.zzb("Google Play Services is not available");
                z = true;
            }
        }
        zzlc.zza();
        int iZze = zzako.zze(context);
        zzlc.zza();
        if (iZze <= zzako.zzd(context) ? z : true) {
            T tZzb = zzaVar.zzb();
            return tZzb == null ? zzaVar.zzc() : tZzb;
        }
        T tZzc = zzaVar.zzc();
        return tZzc == null ? zzaVar.zzb() : tZzc;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        zzlc.zza().zza(context, (String) null, "gmob-apps", bundle, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zzmb zzb() {
        zzmb zzmbVar;
        synchronized (this.zzb) {
            if (this.zza == null) {
                this.zza = zza();
            }
            zzmbVar = this.zza;
        }
        return zzmbVar;
    }

    public final zzlo zza(Context context, String str, zzwf zzwfVar) {
        return (zzlo) zza(context, false, (zza) new zzkw(this, context, str, zzwfVar));
    }

    public final zzqw zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzqw) zza(context, false, (zza) new zzky(this, frameLayout, frameLayout2, context));
    }

    public final zzrb zza(View view, HashMap<String, View> map, HashMap<String, View> map2) {
        return (zzrb) zza(view.getContext(), false, (zza) new zzkz(this, view, map, map2));
    }

    public final zzyq zza(Activity activity) {
        Intent intent = activity.getIntent();
        boolean booleanExtra = false;
        if (intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            booleanExtra = intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        } else {
            zzaky.zzc("useClientJar flag not found in activity intent extras.");
        }
        return (zzyq) zza(activity, booleanExtra, new zzlb(this, activity));
    }
}
