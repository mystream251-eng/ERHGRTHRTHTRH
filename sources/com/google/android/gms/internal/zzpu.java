package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzpu extends zzpz {
    private zzwr zzb;
    private zzwu zzc;
    private final zzpw zzd;
    private zzpv zze;
    private boolean zzf;
    private Object zzg;

    private zzpu(Context context, zzpw zzpwVar, zzcv zzcvVar, zzpx zzpxVar) {
        super(context, zzpwVar, null, zzcvVar, null, zzpxVar, null, null);
        this.zzf = false;
        this.zzg = new Object();
        this.zzd = zzpwVar;
    }

    public zzpu(Context context, zzpw zzpwVar, zzcv zzcvVar, zzwr zzwrVar, zzpx zzpxVar) {
        this(context, zzpwVar, zzcvVar, zzpxVar);
        this.zzb = zzwrVar;
    }

    public zzpu(Context context, zzpw zzpwVar, zzcv zzcvVar, zzwu zzwuVar, zzpx zzpxVar) {
        this(context, zzpwVar, zzcvVar, zzpxVar);
        this.zzc = zzwuVar;
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap<String, View> map2 = new HashMap<>();
        if (map == null) {
            return map2;
        }
        synchronized (map) {
            for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                View view = entry.getValue().get();
                if (view != null) {
                    map2.put(entry.getKey(), view);
                }
            }
        }
        return map2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x002d, code lost:
    
        r4 = null;
     */
    @Override // com.google.android.gms.internal.zzpz, com.google.android.gms.internal.zzpv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View zza(android.view.View.OnClickListener r3, boolean r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.zzg
            monitor-enter(r0)
            com.google.android.gms.internal.zzpv r1 = r2.zze     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto Lf
            com.google.android.gms.internal.zzpv r1 = r2.zze     // Catch: java.lang.Throwable -> L39
            android.view.View r3 = r1.zza(r3, r4)     // Catch: java.lang.Throwable -> L39
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L39
            return r3
        Lf:
            r3 = 0
            com.google.android.gms.internal.zzwr r4 = r2.zzb     // Catch: android.os.RemoteException -> L26 java.lang.Throwable -> L39
            if (r4 == 0) goto L1b
            com.google.android.gms.internal.zzwr r4 = r2.zzb     // Catch: android.os.RemoteException -> L26 java.lang.Throwable -> L39
            com.google.android.gms.dynamic.IObjectWrapper r4 = r4.zzn()     // Catch: android.os.RemoteException -> L26 java.lang.Throwable -> L39
            goto L2d
        L1b:
            com.google.android.gms.internal.zzwu r4 = r2.zzc     // Catch: android.os.RemoteException -> L26 java.lang.Throwable -> L39
            if (r4 == 0) goto L2c
            com.google.android.gms.internal.zzwu r4 = r2.zzc     // Catch: android.os.RemoteException -> L26 java.lang.Throwable -> L39
            com.google.android.gms.dynamic.IObjectWrapper r4 = r4.zzk()     // Catch: android.os.RemoteException -> L26 java.lang.Throwable -> L39
            goto L2d
        L26:
            r4 = move-exception
            java.lang.String r1 = "Failed to call getAdChoicesContent"
            com.google.android.gms.internal.zzahw.zzc(r1, r4)     // Catch: java.lang.Throwable -> L39
        L2c:
            r4 = r3
        L2d:
            if (r4 == 0) goto L37
            java.lang.Object r3 = com.google.android.gms.dynamic.zzn.zza(r4)     // Catch: java.lang.Throwable -> L39
            android.view.View r3 = (android.view.View) r3     // Catch: java.lang.Throwable -> L39
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L39
            return r3
        L37:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L39
            return r3
        L39:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L39
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpu.zza(android.view.View$OnClickListener, boolean):android.view.View");
    }

    @Override // com.google.android.gms.internal.zzpz, com.google.android.gms.internal.zzpv
    public final void zza(View view, Map<String, WeakReference<View>> map) {
        zzpw zzpwVar;
        zzbq.zzb("recordImpression must be called on the main UI thread.");
        synchronized (this.zzg) {
            this.zza = true;
            if (this.zze != null) {
                this.zze.zza(view, map);
                this.zzd.zzak();
            } else {
                try {
                    if (this.zzb != null && !this.zzb.zzj()) {
                        this.zzb.zzi();
                        zzpwVar = this.zzd;
                    } else if (this.zzc != null && !this.zzc.zzh()) {
                        this.zzc.zzg();
                        zzpwVar = this.zzd;
                    }
                    zzpwVar.zzak();
                } catch (RemoteException e) {
                    zzahw.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.zzpz, com.google.android.gms.internal.zzpv
    public final void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        zzbq.zzb("performClick must be called on the main UI thread.");
        synchronized (this.zzg) {
            if (this.zze != null) {
                this.zze.zza(view, map, bundle, view2);
                this.zzd.onAdClicked();
            } else {
                try {
                    if (this.zzb != null && !this.zzb.zzk()) {
                        this.zzb.zza(zzn.zza(view));
                        this.zzd.onAdClicked();
                    }
                    if (this.zzc != null && !this.zzc.zzi()) {
                        this.zzc.zza(zzn.zza(view));
                        this.zzd.onAdClicked();
                    }
                } catch (RemoteException e) {
                    zzahw.zzc("Failed to call performClick", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.zzpz
    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        synchronized (this.zzg) {
            this.zzf = true;
            HashMap<String, View> mapZzb = zzb(map);
            HashMap<String, View> mapZzb2 = zzb(map2);
            try {
                if (this.zzb != null) {
                    this.zzb.zza(zzn.zza(view), zzn.zza(mapZzb), zzn.zza(mapZzb2));
                    this.zzb.zzb(zzn.zza(view));
                } else if (this.zzc != null) {
                    this.zzc.zza(zzn.zza(view), zzn.zza(mapZzb), zzn.zza(mapZzb2));
                    this.zzc.zzb(zzn.zza(view));
                }
            } catch (RemoteException e) {
                zzahw.zzc("Failed to call prepareAd", e);
            }
            this.zzf = false;
        }
    }

    public final void zza(zzpv zzpvVar) {
        synchronized (this.zzg) {
            this.zze = zzpvVar;
        }
    }

    @Override // com.google.android.gms.internal.zzpz, com.google.android.gms.internal.zzpv
    public final boolean zza() {
        synchronized (this.zzg) {
            if (this.zze != null) {
                return this.zze.zza();
            }
            return this.zzd.zzar();
        }
    }

    @Override // com.google.android.gms.internal.zzpz, com.google.android.gms.internal.zzpv
    public final void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.zzg) {
            try {
                if (this.zzb != null) {
                    this.zzb.zzc(zzn.zza(view));
                } else if (this.zzc != null) {
                    this.zzc.zzc(zzn.zza(view));
                }
            } catch (RemoteException e) {
                zzahw.zzc("Failed to call untrackView", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzpz, com.google.android.gms.internal.zzpv
    public final boolean zzb() {
        synchronized (this.zzg) {
            if (this.zze != null) {
                return this.zze.zzb();
            }
            return this.zzd.zzas();
        }
    }

    public final boolean zzc() {
        boolean z;
        synchronized (this.zzg) {
            z = this.zzf;
        }
        return z;
    }

    public final zzpv zzd() {
        zzpv zzpvVar;
        synchronized (this.zzg) {
            zzpvVar = this.zze;
        }
        return zzpvVar;
    }

    @Override // com.google.android.gms.internal.zzpz
    public final zzaof zze() {
        return null;
    }

    @Override // com.google.android.gms.internal.zzpz, com.google.android.gms.internal.zzpv
    public final void zzf() {
    }

    @Override // com.google.android.gms.internal.zzpz, com.google.android.gms.internal.zzpv
    public final void zzg() {
    }
}
