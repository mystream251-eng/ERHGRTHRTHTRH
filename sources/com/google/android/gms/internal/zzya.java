package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.exoplayer2.C;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzya extends zzyn {
    private final Map<String, String> zza;
    private final Context zzb;
    private String zzc;
    private long zzd;
    private long zze;
    private String zzf;
    private String zzg;

    public zzya(zzaof zzaofVar, Map<String, String> map) {
        super(zzaofVar, "createCalendarEvent");
        this.zza = map;
        this.zzb = zzaofVar.zzd();
        this.zzc = zzd(TvContractCompat.Channels.COLUMN_DESCRIPTION);
        this.zzf = zzd("summary");
        this.zzd = zze("start_ticks");
        this.zze = zze("end_ticks");
        this.zzg = zzd("location");
    }

    private final String zzd(String str) {
        return TextUtils.isEmpty(this.zza.get(str)) ? "" : this.zza.get(str);
    }

    private final long zze(String str) {
        String str2 = this.zza.get(str);
        if (str2 == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public final void zza() {
        if (this.zzb == null) {
            zza("Activity context is not available.");
            return;
        }
        zzbt.zze();
        if (!zzaij.zzf(this.zzb).zzd()) {
            zza("This feature is not available on the device.");
            return;
        }
        zzbt.zze();
        AlertDialog.Builder builderZze = zzaij.zze(this.zzb);
        Resources resourcesZzh = zzbt.zzi().zzh();
        builderZze.setTitle(resourcesZzh != null ? resourcesZzh.getString(R.string.s5) : "Create calendar event");
        builderZze.setMessage(resourcesZzh != null ? resourcesZzh.getString(R.string.s6) : "Allow Ad to create a calendar event?");
        builderZze.setPositiveButton(resourcesZzh != null ? resourcesZzh.getString(R.string.s3) : "Accept", new zzyb(this));
        builderZze.setNegativeButton(resourcesZzh != null ? resourcesZzh.getString(R.string.s4) : "Decline", new zzyc(this));
        builderZze.create().show();
    }

    final Intent zzb() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra("title", this.zzc);
        data.putExtra("eventLocation", this.zzg);
        data.putExtra(TvContractCompat.Channels.COLUMN_DESCRIPTION, this.zzf);
        long j = this.zzd;
        if (j > -1) {
            data.putExtra("beginTime", j);
        }
        long j2 = this.zze;
        if (j2 > -1) {
            data.putExtra("endTime", j2);
        }
        data.setFlags(C.ENCODING_PCM_MU_LAW);
        return data;
    }
}
