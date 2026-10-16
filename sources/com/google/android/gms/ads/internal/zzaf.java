package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzcr;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaf implements zzcr, Runnable {
    private final List<Object[]> zza;
    private final AtomicReference<zzcr> zzb;
    private Context zzc;
    private zzala zzd;
    private CountDownLatch zze;

    private zzaf(Context context, zzala zzalaVar) {
        this.zza = new Vector();
        this.zzb = new AtomicReference<>();
        this.zze = new CountDownLatch(1);
        this.zzc = context;
        this.zzd = zzalaVar;
        zzlc.zza();
        if (zzako.zzb()) {
            zzaid.zza(this);
        } else {
            run();
        }
    }

    public zzaf(zzbu zzbuVar) {
        this(zzbuVar.zzc, zzbuVar.zze);
    }

    private final boolean zza() {
        try {
            this.zze.await();
            return true;
        } catch (InterruptedException e) {
            zzahw.zzc("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    private static Context zzb(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    private final void zzb() {
        if (this.zza.isEmpty()) {
            return;
        }
        for (Object[] objArr : this.zza) {
            if (objArr.length == 1) {
                this.zzb.get().zza((MotionEvent) objArr[0]);
            } else if (objArr.length == 3) {
                this.zzb.get().zza(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
            }
        }
        this.zza.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        boolean z = false;
        try {
            boolean z2 = this.zzd.zzd;
            if (!((Boolean) zzlc.zzf().zza(zzoi.zzce)).booleanValue() && z2) {
                z = true;
            }
            this.zzb.set(zzcu.zza(this.zzd.zza, zzb(this.zzc), z));
        } finally {
            this.zze.countDown();
            this.zzc = null;
            this.zzd = null;
        }
    }

    @Override // com.google.android.gms.internal.zzcr
    public final String zza(Context context) {
        zzcr zzcrVar;
        if (!zza() || (zzcrVar = this.zzb.get()) == null) {
            return "";
        }
        zzb();
        return zzcrVar.zza(zzb(context));
    }

    @Override // com.google.android.gms.internal.zzcr
    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    @Override // com.google.android.gms.internal.zzcr
    public final String zza(Context context, String str, View view, Activity activity) {
        zzcr zzcrVar;
        if (!zza() || (zzcrVar = this.zzb.get()) == null) {
            return "";
        }
        zzb();
        return zzcrVar.zza(zzb(context), str, view, activity);
    }

    @Override // com.google.android.gms.internal.zzcr
    public final void zza(int i, int i2, int i3) {
        zzcr zzcrVar = this.zzb.get();
        if (zzcrVar == null) {
            this.zza.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
        } else {
            zzb();
            zzcrVar.zza(i, i2, i3);
        }
    }

    @Override // com.google.android.gms.internal.zzcr
    public final void zza(MotionEvent motionEvent) {
        zzcr zzcrVar = this.zzb.get();
        if (zzcrVar == null) {
            this.zza.add(new Object[]{motionEvent});
        } else {
            zzb();
            zzcrVar.zza(motionEvent);
        }
    }

    @Override // com.google.android.gms.internal.zzcr
    public final void zza(View view) {
        zzcr zzcrVar = this.zzb.get();
        if (zzcrVar != null) {
            zzcrVar.zza(view);
        }
    }
}
