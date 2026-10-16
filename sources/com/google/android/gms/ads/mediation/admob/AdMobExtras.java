package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class AdMobExtras implements NetworkExtras {
    private final Bundle zza;

    public AdMobExtras(Bundle bundle) {
        this.zza = bundle != null ? new Bundle(bundle) : null;
    }

    public final Bundle getExtras() {
        return this.zza;
    }
}
