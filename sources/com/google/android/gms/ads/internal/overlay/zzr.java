package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzr extends zzd {
    public zzr(Activity activity) {
        super(activity);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzd, com.google.android.gms.internal.zzyq
    public final void zza(Bundle bundle) {
        zzahw.zza("AdOverlayParcel is null or does not contain valid overlay type.");
        this.zzc = 3;
        this.zza.finish();
    }
}
