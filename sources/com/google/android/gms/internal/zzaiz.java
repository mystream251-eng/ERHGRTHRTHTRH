package com.google.android.gms.internal;

import android.R;
import android.content.Context;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.zzbt;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaiz extends zzaiy {
    @Override // com.google.android.gms.internal.zzais, com.google.android.gms.internal.zzaip
    public final zzaog zza(zzaof zzaofVar, boolean z) {
        return new zzapn(zzaofVar, z);
    }

    @Override // com.google.android.gms.internal.zzaip
    public final CookieManager zzc(Context context) {
        if (zze()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zzahw.zzb("Failed to obtain CookieManager.", th);
            zzbt.zzi().zza(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    @Override // com.google.android.gms.internal.zzaiu, com.google.android.gms.internal.zzaip
    public final int zzf() {
        return R.style.Theme.Material.Dialog.Alert;
    }
}
