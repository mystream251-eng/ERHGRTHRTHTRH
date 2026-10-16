package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaij;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzl {
    public static void zza(Context context, AdOverlayInfoParcel adOverlayInfoParcel, boolean z) {
        if (adOverlayInfoParcel.zzk == 4 && adOverlayInfoParcel.zzc == null) {
            if (adOverlayInfoParcel.zzb != null) {
                adOverlayInfoParcel.zzb.onAdClicked();
            }
            zzbt.zzb();
            zza.zza(context, adOverlayInfoParcel.zza, adOverlayInfoParcel.zzi);
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzm.zzd);
        intent.putExtra("shouldCallOnOverlayOpened", z);
        AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
        if (!com.google.android.gms.common.util.zzs.zzg()) {
            intent.addFlags(524288);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        zzbt.zze();
        zzaij.zza(context, intent);
    }
}
