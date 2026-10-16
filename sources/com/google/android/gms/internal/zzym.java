package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzym extends zzyn implements zzt<zzaof> {
    private final zzaof zza;
    private final Context zzb;
    private final WindowManager zzc;
    private final zznu zzd;
    private DisplayMetrics zze;
    private float zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;

    public zzym(zzaof zzaofVar, Context context, zznu zznuVar) {
        super(zzaofVar);
        this.zzg = -1;
        this.zzh = -1;
        this.zzj = -1;
        this.zzk = -1;
        this.zzl = -1;
        this.zzm = -1;
        this.zza = zzaofVar;
        this.zzb = context;
        this.zzd = zznuVar;
        this.zzc = (WindowManager) context.getSystemService("window");
    }

    public final void zza(int i, int i2) {
        int i3 = this.zzb instanceof Activity ? zzbt.zze().zzc((Activity) this.zzb)[0] : 0;
        if (this.zza.zzt() == null || !this.zza.zzt().zzd()) {
            zzlc.zza();
            this.zzl = zzako.zzb(this.zzb, this.zza.getWidth());
            zzlc.zza();
            this.zzm = zzako.zzb(this.zzb, this.zza.getHeight());
        }
        zzb(i, i2 - i3, this.zzl, this.zzm);
        this.zza.zzv().zza(i, i2);
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzaof zzaofVar, Map map) {
        int iZzb;
        this.zze = new DisplayMetrics();
        Display defaultDisplay = this.zzc.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zze);
        this.zzf = this.zze.density;
        this.zzi = defaultDisplay.getRotation();
        zzlc.zza();
        DisplayMetrics displayMetrics = this.zze;
        this.zzg = zzako.zzb(displayMetrics, displayMetrics.widthPixels);
        zzlc.zza();
        DisplayMetrics displayMetrics2 = this.zze;
        this.zzh = zzako.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity activityZzd = this.zza.zzd();
        if (activityZzd == null || activityZzd.getWindow() == null) {
            this.zzj = this.zzg;
            iZzb = this.zzh;
        } else {
            zzbt.zze();
            int[] iArrZza = zzaij.zza(activityZzd);
            zzlc.zza();
            this.zzj = zzako.zzb(this.zze, iArrZza[0]);
            zzlc.zza();
            iZzb = zzako.zzb(this.zze, iArrZza[1]);
        }
        this.zzk = iZzb;
        if (this.zza.zzt().zzd()) {
            this.zzl = this.zzg;
            this.zzm = this.zzh;
        } else {
            this.zza.measure(0, 0);
        }
        zza(this.zzg, this.zzh, this.zzj, this.zzk, this.zzf, this.zzi);
        this.zza.zza("onDeviceFeaturesReceived", new zzyj(new zzyl().zzb(this.zzd.zza()).zza(this.zzd.zzb()).zzc(this.zzd.zzd()).zzd(this.zzd.zzc()).zze(true)).zza());
        int[] iArr = new int[2];
        this.zza.getLocationOnScreen(iArr);
        zzlc.zza();
        int iZzb2 = zzako.zzb(this.zzb, iArr[0]);
        zzlc.zza();
        zza(iZzb2, zzako.zzb(this.zzb, iArr[1]));
        if (zzahw.zza(2)) {
            zzahw.zzd("Dispatching Ready Event.");
        }
        zzb(this.zza.zzi().zza);
    }
}
