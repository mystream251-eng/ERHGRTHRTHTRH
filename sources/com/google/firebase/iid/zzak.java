package com.google.firebase.iid;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzak extends zzan<Void> {
    zzak(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    @Override // com.google.firebase.iid.zzan
    final boolean zza() {
        return true;
    }

    @Override // com.google.firebase.iid.zzan
    final void zza(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            zza((Object) null);
        } else {
            zza(new zzam(4, "Invalid response to one way request"));
        }
    }
}
