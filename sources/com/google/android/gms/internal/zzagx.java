package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzagx {
    public static Uri zza(Uri uri, Context context) {
        if (!zzbt.zzaa().zzf(context) || !TextUtils.isEmpty(uri.getQueryParameter("fbs_aeid"))) {
            return uri;
        }
        String strZzj = zzbt.zzaa().zzj(context);
        Uri uriZza = zza(uri.toString(), "fbs_aeid", strZzj);
        zzbt.zzaa().zzd(context, strZzj);
        return uriZza;
    }

    private static Uri zza(String str, String str2, String str3) {
        int iIndexOf = str.indexOf("&adurl");
        if (iIndexOf == -1) {
            iIndexOf = str.indexOf("?adurl");
        }
        if (iIndexOf == -1) {
            return Uri.parse(str).buildUpon().appendQueryParameter(str2, str3).build();
        }
        int i = iIndexOf + 1;
        return Uri.parse(str.substring(0, i) + str2 + "=" + str3 + "&" + str.substring(i));
    }

    public static String zza(String str, Context context) {
        String strZzj;
        if (!zzbt.zzaa().zza(context) || TextUtils.isEmpty(str) || (strZzj = zzbt.zzaa().zzj(context)) == null) {
            return str;
        }
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzbp)).booleanValue()) {
            if (str.contains("fbs_aeid")) {
                return str;
            }
            if (zzbt.zze().zzd(str)) {
                zzbt.zzaa().zzd(context, strZzj);
                return zza(str, "fbs_aeid", strZzj).toString();
            }
            if (!zzbt.zze().zze(str)) {
                return str;
            }
            zzbt.zzaa().zze(context, strZzj);
            return zza(str, "fbs_aeid", strZzj).toString();
        }
        CharSequence charSequence = (String) zzlc.zzf().zza(zzoi.zzbq);
        if (!str.contains(charSequence)) {
            return str;
        }
        if (zzbt.zze().zzd(str)) {
            zzbt.zzaa().zzd(context, strZzj);
            return str.replace(charSequence, strZzj);
        }
        if (!zzbt.zze().zze(str)) {
            return str;
        }
        zzbt.zzaa().zze(context, strZzj);
        return str.replace(charSequence, strZzj);
    }
}
