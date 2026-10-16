package com.google.firebase.iid;

import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzax extends com.google.android.gms.internal.firebase_messaging.zze {
    private final /* synthetic */ zzau zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzax(zzau zzauVar, Looper looper) {
        super(looper);
        this.zza = zzauVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.zza.zza(message);
    }
}
