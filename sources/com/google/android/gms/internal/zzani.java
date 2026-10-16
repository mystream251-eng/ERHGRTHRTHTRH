package com.google.android.gms.internal;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
final class zzani implements SensorEventListener {
    private final SensorManager zza;
    private final Display zzc;
    private float[] zzf;
    private Handler zzg;
    private zzank zzh;
    private final float[] zzd = new float[9];
    private final float[] zze = new float[9];
    private final Object zzb = new Object();

    zzani(Context context) {
        this.zza = (SensorManager) context.getSystemService("sensor");
        this.zzc = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    private final void zza(int i, int i2) {
        float[] fArr = this.zze;
        float f = fArr[i];
        fArr[i] = fArr[i2];
        fArr[i2] = f;
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr[0] == 0.0f && fArr[1] == 0.0f && fArr[2] == 0.0f) {
            return;
        }
        synchronized (this.zzb) {
            if (this.zzf == null) {
                this.zzf = new float[9];
            }
        }
        SensorManager.getRotationMatrixFromVector(this.zzd, fArr);
        int rotation = this.zzc.getRotation();
        if (rotation == 1) {
            SensorManager.remapCoordinateSystem(this.zzd, 2, TsExtractor.TS_STREAM_TYPE_AC3, this.zze);
        } else if (rotation == 2) {
            SensorManager.remapCoordinateSystem(this.zzd, TsExtractor.TS_STREAM_TYPE_AC3, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, this.zze);
        } else if (rotation != 3) {
            System.arraycopy(this.zzd, 0, this.zze, 0, 9);
        } else {
            SensorManager.remapCoordinateSystem(this.zzd, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 1, this.zze);
        }
        zza(1, 3);
        zza(2, 6);
        zza(5, 7);
        synchronized (this.zzb) {
            System.arraycopy(this.zze, 0, this.zzf, 0, 9);
        }
        zzank zzankVar = this.zzh;
        if (zzankVar != null) {
            zzankVar.zza();
        }
    }

    final void zza() {
        if (this.zzg != null) {
            return;
        }
        Sensor defaultSensor = this.zza.getDefaultSensor(11);
        if (defaultSensor == null) {
            zzahw.zzc("No Sensor of TYPE_ROTATION_VECTOR");
            return;
        }
        HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.zzg = handler;
        if (this.zza.registerListener(this, defaultSensor, 0, handler)) {
            return;
        }
        zzahw.zzc("SensorManager.registerListener failed.");
        zzb();
    }

    final void zza(zzank zzankVar) {
        this.zzh = zzankVar;
    }

    final boolean zza(float[] fArr) {
        synchronized (this.zzb) {
            if (this.zzf == null) {
                return false;
            }
            System.arraycopy(this.zzf, 0, fArr, 0, this.zzf.length);
            return true;
        }
    }

    final void zzb() {
        if (this.zzg == null) {
            return;
        }
        this.zza.unregisterListener(this);
        this.zzg.post(new zzanj(this));
        this.zzg = null;
    }
}
