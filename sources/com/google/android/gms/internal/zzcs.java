package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzcs implements zzcr {
    protected MotionEvent zza;
    protected double zzj;
    protected float zzk;
    protected float zzl;
    protected float zzm;
    protected float zzn;
    protected DisplayMetrics zzp;
    private double zzq;
    private double zzr;
    protected LinkedList<MotionEvent> zzb = new LinkedList<>();
    protected long zzc = 0;
    protected long zzd = 0;
    protected long zze = 0;
    protected long zzf = 0;
    protected long zzg = 0;
    protected long zzh = 0;
    protected long zzi = 0;
    private boolean zzs = false;
    protected boolean zzo = false;

    protected zzcs(Context context) {
        try {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzee)).booleanValue()) {
                zzbx.zza();
            } else {
                zzdq.zza();
            }
            this.zzp = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    private final String zza(Context context, String str, boolean z, View view, Activity activity, byte[] bArr) {
        int i;
        zzba zzbaVarZza;
        try {
            if (z) {
                zzbaVarZza = zza(context, view, activity);
                this.zzs = true;
            } else {
                zzbaVarZza = zza(context, null);
            }
            if (zzbaVarZza != null && zzbaVarZza.zzf() != 0) {
                return zzbx.zza(zzbaVarZza, str);
            }
            return Integer.toString(5);
        } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
            i = 7;
            return Integer.toString(i);
        } catch (Throwable unused2) {
            i = 3;
            return Integer.toString(i);
        }
    }

    protected abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzdj;

    protected abstract zzba zza(Context context, View view, Activity activity);

    protected abstract zzba zza(Context context, zzax zzaxVar);

    @Override // com.google.android.gms.internal.zzcr
    public final String zza(Context context) {
        if (zzds.zza()) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzeg)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, null, false, null, null, null);
    }

    @Override // com.google.android.gms.internal.zzcr
    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    @Override // com.google.android.gms.internal.zzcr
    public final String zza(Context context, String str, View view, Activity activity) {
        return zza(context, str, true, view, activity, null);
    }

    @Override // com.google.android.gms.internal.zzcr
    public final void zza(int i, int i2, int i3) {
        MotionEvent motionEventObtain;
        MotionEvent motionEvent = this.zza;
        if (motionEvent != null) {
            motionEvent.recycle();
        }
        DisplayMetrics displayMetrics = this.zzp;
        if (displayMetrics != null) {
            motionEventObtain = MotionEvent.obtain(0L, i3, 1, i * displayMetrics.density, this.zzp.density * i2, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            motionEventObtain = null;
        }
        this.zza = motionEventObtain;
        this.zzo = false;
    }

    @Override // com.google.android.gms.internal.zzcr
    public final void zza(MotionEvent motionEvent) {
        boolean z = false;
        if (this.zzs) {
            this.zzf = 0L;
            this.zze = 0L;
            this.zzd = 0L;
            this.zzc = 0L;
            this.zzg = 0L;
            this.zzi = 0L;
            this.zzh = 0L;
            Iterator<MotionEvent> it = this.zzb.iterator();
            while (it.hasNext()) {
                it.next().recycle();
            }
            this.zzb.clear();
            this.zza = null;
            this.zzs = false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.zzj = 0.0d;
            this.zzq = motionEvent.getRawX();
            this.zzr = motionEvent.getRawY();
        } else if (action == 1 || action == 2) {
            double rawX = motionEvent.getRawX();
            double rawY = motionEvent.getRawY();
            double d = rawX - this.zzq;
            double d2 = rawY - this.zzr;
            this.zzj += Math.sqrt((d * d) + (d2 * d2));
            this.zzq = rawX;
            this.zzr = rawY;
        }
        int action2 = motionEvent.getAction();
        if (action2 != 0) {
            try {
                if (action2 == 1) {
                    MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                    this.zza = motionEventObtain;
                    this.zzb.add(motionEventObtain);
                    if (this.zzb.size() > 6) {
                        this.zzb.remove().recycle();
                    }
                    this.zze++;
                    this.zzg = zza(new Throwable().getStackTrace());
                } else if (action2 == 2) {
                    this.zzd += (long) (motionEvent.getHistorySize() + 1);
                    zzdr zzdrVarZzb = zzb(motionEvent);
                    if ((zzdrVarZzb == null || zzdrVarZzb.zzd == null || zzdrVarZzb.zzg == null) ? false : true) {
                        this.zzh += zzdrVarZzb.zzd.longValue() + zzdrVarZzb.zzg.longValue();
                    }
                    if (this.zzp != null && zzdrVarZzb != null && zzdrVarZzb.zze != null && zzdrVarZzb.zzh != null) {
                        z = true;
                    }
                    if (z) {
                        this.zzi += zzdrVarZzb.zze.longValue() + zzdrVarZzb.zzh.longValue();
                    }
                } else if (action2 == 3) {
                    this.zzf++;
                }
            } catch (zzdj unused) {
            }
        } else {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzeb)).booleanValue()) {
                this.zzk = motionEvent.getX();
                this.zzl = motionEvent.getY();
                this.zzm = motionEvent.getRawX();
                this.zzn = motionEvent.getRawY();
            }
            this.zzc++;
        }
        this.zzo = true;
    }

    @Override // com.google.android.gms.internal.zzcr
    public void zza(View view) {
    }

    protected abstract zzdr zzb(MotionEvent motionEvent) throws zzdj;
}
