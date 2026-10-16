package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zza {
    private static boolean zza(Context context, Intent intent, zzt zztVar) {
        try {
            String strValueOf = String.valueOf(intent.toURI());
            zzahw.zza(strValueOf.length() != 0 ? "Launching an intent: ".concat(strValueOf) : new String("Launching an intent: "));
            zzbt.zze();
            zzaij.zza(context, intent);
            if (zztVar == null) {
                return true;
            }
            zztVar.zzf();
            return true;
        } catch (ActivityNotFoundException e) {
            zzahw.zze(e.getMessage());
            return false;
        }
    }

    public static boolean zza(Context context, zzc zzcVar, zzt zztVar) {
        String str;
        int i = 0;
        if (zzcVar == null) {
            str = "No intent data for launcher overlay.";
        } else {
            zzoi.zza(context);
            if (zzcVar.zzf != null) {
                return zza(context, zzcVar.zzf, zztVar);
            }
            Intent intent = new Intent();
            if (!TextUtils.isEmpty(zzcVar.zza)) {
                if (TextUtils.isEmpty(zzcVar.zzb)) {
                    intent.setData(Uri.parse(zzcVar.zza));
                } else {
                    intent.setDataAndType(Uri.parse(zzcVar.zza), zzcVar.zzb);
                }
                intent.setAction("android.intent.action.VIEW");
                if (!TextUtils.isEmpty(zzcVar.zzc)) {
                    intent.setPackage(zzcVar.zzc);
                }
                if (!TextUtils.isEmpty(zzcVar.zzd)) {
                    String[] strArrSplit = zzcVar.zzd.split("/", 2);
                    if (strArrSplit.length < 2) {
                        String strValueOf = String.valueOf(zzcVar.zzd);
                        zzahw.zze(strValueOf.length() != 0 ? "Could not parse component name from open GMSG: ".concat(strValueOf) : new String("Could not parse component name from open GMSG: "));
                        return false;
                    }
                    intent.setClassName(strArrSplit[0], strArrSplit[1]);
                }
                String str2 = zzcVar.zze;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        i = Integer.parseInt(str2);
                    } catch (NumberFormatException unused) {
                        zzahw.zze("Could not parse intent flags.");
                    }
                    intent.addFlags(i);
                }
                if (((Boolean) zzlc.zzf().zza(zzoi.zzgd)).booleanValue()) {
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
                } else {
                    if (((Boolean) zzlc.zzf().zza(zzoi.zzgc)).booleanValue()) {
                        zzbt.zze();
                        zzaij.zzb(context, intent);
                    }
                }
                return zza(context, intent, zztVar);
            }
            str = "Open GMSG did not contain a URL.";
        }
        zzahw.zze(str);
        return false;
    }
}
