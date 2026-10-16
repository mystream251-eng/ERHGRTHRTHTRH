package com.google.firebase.messaging;

import com.google.android.datatransport.Transformer;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzn implements Transformer {
    static final Transformer zza = new zzn();

    private zzn() {
    }

    @Override // com.google.android.datatransport.Transformer
    public final Object apply(Object obj) {
        return ((String) obj).getBytes();
    }
}
