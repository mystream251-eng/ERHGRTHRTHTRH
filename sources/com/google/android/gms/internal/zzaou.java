package com.google.android.gms.internal;

import android.os.RemoteException;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.common.util.zzf;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaou extends zzmn {
    private final zzann zza;
    private final boolean zzc;
    private final boolean zzd;
    private final float zze;
    private int zzf;
    private zzmp zzg;
    private boolean zzh;
    private float zzj;
    private float zzk;
    private boolean zzm;
    private boolean zzn;
    private final Object zzb = new Object();
    private boolean zzi = true;
    private boolean zzl = true;

    public zzaou(zzann zzannVar, float f, boolean z, boolean z2) {
        this.zza = zzannVar;
        this.zze = f;
        this.zzc = z;
        this.zzd = z2;
    }

    private final void zza(String str, Map<String, String> map) {
        final HashMap map2 = map == null ? new HashMap() : new HashMap(map);
        map2.put("action", str);
        zzaly.zza.execute(new Runnable(this, map2) { // from class: com.google.android.gms.internal.zzaov
            private final zzaou zza;
            private final Map zzb;

            {
                this.zza = this;
                this.zzb = map2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb);
            }
        });
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zza() {
        zza("play", null);
    }

    public final void zza(float f, final int i, final boolean z, float f2) {
        final boolean z2;
        final int i2;
        synchronized (this.zzb) {
            this.zzj = f;
            z2 = this.zzi;
            this.zzi = z;
            i2 = this.zzf;
            this.zzf = i;
            float f3 = this.zzk;
            this.zzk = f2;
            if (Math.abs(f2 - f3) > 1.0E-4f) {
                this.zza.zzm().invalidate();
            }
        }
        zzaly.zza.execute(new Runnable(this, i2, i, z2, z) { // from class: com.google.android.gms.internal.zzaow
            private final zzaou zza;
            private final int zzb;
            private final int zzc;
            private final boolean zzd;
            private final boolean zze;

            {
                this.zza = this;
                this.zzb = i2;
                this.zzc = i;
                this.zzd = z2;
                this.zze = z;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:48:0x005a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:53:0x0068 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x0076 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:68:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:70:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    final /* synthetic */ void zza(int i, int i2, boolean z, boolean z2) {
        synchronized (this.zzb) {
            boolean z3 = i != i2;
            boolean z4 = !this.zzh && i2 == 1;
            boolean z5 = z3 && i2 == 1;
            boolean z6 = z3 && i2 == 2;
            boolean z7 = z3 && i2 == 3;
            boolean z8 = z != z2;
            this.zzh = this.zzh || z4;
            if (this.zzg == null) {
                return;
            }
            if (!z4) {
                if (z5) {
                    if (z6) {
                        if (z7) {
                            if (z8) {
                                this.zzg.zza(z2);
                            }
                            return;
                        } else {
                            this.zzg.zzd();
                            if (z8) {
                                this.zzg.zza(z2);
                            }
                            return;
                        }
                    }
                    this.zzg.zzc();
                    if (z7) {
                        if (z8) {
                            this.zzg.zza(z2);
                        }
                        return;
                    } else {
                        this.zzg.zzd();
                        if (z8) {
                            this.zzg.zza(z2);
                        }
                        return;
                    }
                }
                this.zzg.zzb();
                if (z6) {
                    if (z7) {
                        if (z8) {
                            this.zzg.zza(z2);
                        }
                        return;
                    } else {
                        this.zzg.zzd();
                        if (z8) {
                            this.zzg.zza(z2);
                        }
                        return;
                    }
                }
                this.zzg.zzc();
                if (z7) {
                    if (z8) {
                        this.zzg.zza(z2);
                    }
                    return;
                } else {
                    this.zzg.zzd();
                    if (z8) {
                        this.zzg.zza(z2);
                    }
                    return;
                }
            }
            try {
                this.zzg.zza();
            } catch (RemoteException e) {
                zzahw.zzc("Unable to call onVideoStart()", e);
            }
            if (z5) {
                if (z6) {
                    if (z7) {
                        if (z8) {
                            this.zzg.zza(z2);
                        }
                        return;
                    } else {
                        this.zzg.zzd();
                        if (z8) {
                            this.zzg.zza(z2);
                        }
                        return;
                    }
                }
                this.zzg.zzc();
                if (z7) {
                    if (z8) {
                        this.zzg.zza(z2);
                    }
                    return;
                } else {
                    this.zzg.zzd();
                    if (z8) {
                        this.zzg.zza(z2);
                    }
                    return;
                }
            }
            try {
                this.zzg.zzb();
            } catch (RemoteException e2) {
                zzahw.zzc("Unable to call onVideoPlay()", e2);
            }
            if (z6) {
                if (z7) {
                    if (z8) {
                        this.zzg.zza(z2);
                    }
                    return;
                } else {
                    this.zzg.zzd();
                    if (z8) {
                        this.zzg.zza(z2);
                    }
                    return;
                }
            }
            try {
                this.zzg.zzc();
            } catch (RemoteException e3) {
                zzahw.zzc("Unable to call onVideoPause()", e3);
            }
            if (z7) {
                if (z8) {
                    this.zzg.zza(z2);
                }
                return;
            }
            try {
                this.zzg.zzd();
            } catch (RemoteException e4) {
                zzahw.zzc("Unable to call onVideoEnd()", e4);
            }
            if (z8) {
                try {
                    this.zzg.zza(z2);
                } catch (RemoteException e5) {
                    zzahw.zzc("Unable to call onVideoMute()", e5);
                }
            }
            return;
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zza(zzmp zzmpVar) {
        synchronized (this.zzb) {
            this.zzg = zzmpVar;
        }
    }

    public final void zza(zzns zznsVar) {
        synchronized (this.zzb) {
            this.zzl = zznsVar.zza;
            this.zzm = zznsVar.zzb;
            this.zzn = zznsVar.zzc;
        }
        zza("initialState", zzf.zza("muteStart", zznsVar.zza ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS, "customControlsRequested", zznsVar.zzb ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS, "clickToExpandRequested", zznsVar.zzc ? LocalSearchProvider.LIVE_CONTENTS : LocalSearchProvider.NO_LIVE_CONTENTS));
    }

    final /* synthetic */ void zza(Map map) {
        this.zza.zza("pubVideoCmd", map);
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zza(boolean z) {
        zza(z ? "mute" : "unmute", null);
    }

    @Override // com.google.android.gms.internal.zzmm
    public final void zzb() {
        zza(ToolboxAnalyticsModuleKt.EVENT_PAUSE, null);
    }

    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzc() {
        boolean z;
        synchronized (this.zzb) {
            z = this.zzi;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final int zzd() {
        int i;
        synchronized (this.zzb) {
            i = this.zzf;
        }
        return i;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zzf() {
        float f;
        synchronized (this.zzb) {
            f = this.zzj;
        }
        return f;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final float zzg() {
        float f;
        synchronized (this.zzb) {
            f = this.zzk;
        }
        return f;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzh() {
        boolean z;
        synchronized (this.zzb) {
            z = this.zzc && this.zzm;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.zzmm
    public final zzmp zzi() throws RemoteException {
        zzmp zzmpVar;
        synchronized (this.zzb) {
            zzmpVar = this.zzg;
        }
        return zzmpVar;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0015  */
    @Override // com.google.android.gms.internal.zzmm
    public final boolean zzj() {
        boolean z;
        boolean zZzh = zzh();
        synchronized (this.zzb) {
            if (zZzh) {
                z = false;
            } else {
                try {
                    if (this.zzn && this.zzd) {
                        z = true;
                    } else {
                        z = false;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return z;
    }
}
