package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes2.dex */
final class zacq implements zacs {
    private final /* synthetic */ zacp zalb;

    zacq(zacp zacpVar) {
        this.zalb = zacpVar;
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    public final void zac(BasePendingResult<?> basePendingResult) {
        this.zalb.zakz.remove(basePendingResult);
    }
}
