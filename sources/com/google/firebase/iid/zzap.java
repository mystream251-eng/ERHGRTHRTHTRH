package com.google.firebase.iid;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzap extends zzan<Bundle> {
    zzap(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    @Override // com.google.firebase.iid.zzan
    final boolean zza() {
        return false;
    }

    @Override // com.google.firebase.iid.zzan
    final void zza(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        zza(bundle2);
    }
}
