package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzyg extends zzyn {
    private final Map<String, String> zza;
    private final Context zzb;

    public zzyg(zzaof zzaofVar, Map<String, String> map) {
        super(zzaofVar, "storePicture");
        this.zza = map;
        this.zzb = zzaofVar.zzd();
    }

    public final void zza() {
        if (this.zzb == null) {
            zza("Activity context is not available");
            return;
        }
        zzbt.zze();
        if (!zzaij.zzf(this.zzb).zzc()) {
            zza("Feature is not supported by the device.");
            return;
        }
        String str = this.zza.get("iurl");
        if (TextUtils.isEmpty(str)) {
            zza("Image url cannot be empty.");
            return;
        }
        if (!URLUtil.isValidUrl(str)) {
            String strValueOf = String.valueOf(str);
            zza(strValueOf.length() != 0 ? "Invalid image url: ".concat(strValueOf) : new String("Invalid image url: "));
            return;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        zzbt.zze();
        if (!zzaij.zzc(lastPathSegment)) {
            String strValueOf2 = String.valueOf(lastPathSegment);
            zza(strValueOf2.length() != 0 ? "Image type not recognized: ".concat(strValueOf2) : new String("Image type not recognized: "));
            return;
        }
        Resources resourcesZzh = zzbt.zzi().zzh();
        zzbt.zze();
        AlertDialog.Builder builderZze = zzaij.zze(this.zzb);
        builderZze.setTitle(resourcesZzh != null ? resourcesZzh.getString(R.string.s1) : "Save image");
        builderZze.setMessage(resourcesZzh != null ? resourcesZzh.getString(R.string.s2) : "Allow Ad to store image in Picture gallery?");
        builderZze.setPositiveButton(resourcesZzh != null ? resourcesZzh.getString(R.string.s3) : "Accept", new zzyh(this, str, lastPathSegment));
        builderZze.setNegativeButton(resourcesZzh != null ? resourcesZzh.getString(R.string.s4) : "Decline", new zzyi(this));
        builderZze.create().show();
    }
}
