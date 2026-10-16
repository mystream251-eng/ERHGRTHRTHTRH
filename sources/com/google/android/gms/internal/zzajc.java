package com.google.android.gms.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.zzbt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzajc {
    private final Context zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private final float zzf;
    private float zzg;
    private float zzh;
    private float zzi;
    private int zzj;

    public zzajc(Context context) {
        this.zzj = 0;
        this.zza = context;
        this.zzf = context.getResources().getDisplayMetrics().density;
    }

    public zzajc(Context context, String str) {
        this(context);
        this.zzb = str;
    }

    private static int zza(List<String> list, String str, boolean z) {
        if (!z) {
            return -1;
        }
        list.add(str);
        return list.size() - 1;
    }

    private final void zza(int i, float f, float f2) {
        if (i == 0) {
            this.zzj = 0;
            this.zzg = f;
            this.zzh = f2;
            this.zzi = f2;
            return;
        }
        int i2 = this.zzj;
        if (i2 == -1) {
            return;
        }
        if (i != 2) {
            if (i == 1 && i2 == 4) {
                zza();
                return;
            }
            return;
        }
        if (f2 > this.zzh) {
            this.zzh = f2;
        } else if (f2 < this.zzi) {
            this.zzi = f2;
        }
        if (this.zzh - this.zzi > this.zzf * 30.0f) {
            this.zzj = -1;
            return;
        }
        int i3 = this.zzj;
        if (i3 == 0 || i3 == 2 ? f - this.zzg >= this.zzf * 50.0f : !((i3 != 1 && i3 != 3) || f - this.zzg > this.zzf * (-50.0f))) {
            this.zzg = f;
            this.zzj++;
        }
        int i4 = this.zzj;
        if (i4 == 1 || i4 == 3) {
            if (f > this.zzg) {
                this.zzg = f;
            }
        } else {
            if (i4 != 2 || f >= this.zzg) {
                return;
            }
            this.zzg = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:15:0x006f  */
    public final void zzb() {
        String strTrim;
        if (!(this.zza instanceof Activity)) {
            zzahw.zzd("Can not create dialog without Activity Context");
            return;
        }
        String str = this.zzb;
        if (TextUtils.isEmpty(str)) {
            strTrim = "No debug information";
        } else {
            Uri uriBuild = new Uri.Builder().encodedQuery(str.replaceAll("\\+", "%20")).build();
            StringBuilder sb = new StringBuilder();
            zzbt.zze();
            Map<String, String> mapZza = zzaij.zza(uriBuild);
            for (String str2 : mapZza.keySet()) {
                sb.append(str2);
                sb.append(" = ");
                sb.append(mapZza.get(str2));
                sb.append("\n\n");
            }
            strTrim = sb.toString().trim();
            if (TextUtils.isEmpty(strTrim)) {
                strTrim = "No debug information";
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.zza);
        builder.setMessage(strTrim);
        builder.setTitle("Ad Information");
        builder.setPositiveButton("Share", new zzaje(this, strTrim));
        builder.setNegativeButton("Close", new zzajf(this));
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc() {
        zzahw.zzb("Debug mode [Creative Preview] selected.");
        zzaid.zza(new zzajg(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd() {
        zzahw.zzb("Debug mode [Troubleshooting] selected.");
        zzaid.zza(new zzajh(this));
    }

    public final void zza() {
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzgf)).booleanValue()) {
            if (!((Boolean) zzlc.zzf().zza(zzoi.zzge)).booleanValue()) {
                zzb();
                return;
            }
        }
        if (!(this.zza instanceof Activity)) {
            zzahw.zzd("Can not create dialog without Activity Context");
            return;
        }
        String str = !TextUtils.isEmpty(zzbt.zzo().zza()) ? "Creative Preview (Enabled)" : "Creative Preview";
        String str2 = zzbt.zzo().zzb() ? "Troubleshooting (Enabled)" : "Troubleshooting";
        ArrayList arrayList = new ArrayList();
        int iZza = zza((List<String>) arrayList, "Ad Information", true);
        int iZza2 = zza(arrayList, str, ((Boolean) zzlc.zzf().zza(zzoi.zzge)).booleanValue());
        int iZza3 = zza(arrayList, str2, ((Boolean) zzlc.zzf().zza(zzoi.zzgf)).booleanValue());
        AlertDialog.Builder builder = new AlertDialog.Builder(this.zza, zzbt.zzg().zzf());
        builder.setTitle("Select a Debug Mode").setItems((CharSequence[]) arrayList.toArray(new String[0]), new zzajd(this, iZza, iZza2, iZza3));
        builder.create().show();
    }

    public final void zza(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }

    public final void zza(String str) {
        this.zzc = str;
    }

    public final void zzb(String str) {
        this.zzd = str;
    }

    public final void zzc(String str) {
        this.zzb = str;
    }

    public final void zzd(String str) {
        this.zze = str;
    }
}
