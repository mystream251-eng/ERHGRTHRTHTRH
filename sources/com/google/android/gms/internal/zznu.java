package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbq;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zznu {
    private final Context zza;

    public zznu(Context context) {
        zzbq.zza(context, "Context can not be null");
        this.zza = context;
    }

    private final boolean zza(Intent intent) {
        zzbq.zza(intent, "Intent can not be null");
        return !this.zza.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public final boolean zza() {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return zza(intent);
    }

    public final boolean zzb() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return zza(intent);
    }

    public final boolean zzc() {
        return ((Boolean) zzakg.zza(this.zza, new zznv())).booleanValue() && zzbih.zza(this.zza).zza("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public final boolean zzd() {
        return zza(new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event"));
    }
}
