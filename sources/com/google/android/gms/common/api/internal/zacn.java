package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: classes2.dex */
final class zacn implements Runnable {
    private final /* synthetic */ Result zakv;
    private final /* synthetic */ zacm zakw;

    zacn(zacm zacmVar, Result result) {
        this.zakw = zacmVar;
        this.zakv = result;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            BasePendingResult.zadn.set(true);
            this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(0, this.zakw.zako.onSuccess(this.zakv)));
        } catch (RuntimeException e) {
            this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(1, e));
        } finally {
            BasePendingResult.zadn.set(false);
            zacm zacmVar = this.zakw;
            zacm.zab(this.zakv);
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zakw.zadq.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zakw);
            }
        }
    }
}
