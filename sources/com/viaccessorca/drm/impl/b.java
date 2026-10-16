package com.viaccessorca.drm.impl;

import com.viaccessorca.drm.VOCommonDrmAgent;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.exceptions.VOStatusCode;

/* JADX INFO: loaded from: classes2.dex */
final class b implements VOCommonDrmAgent.VODrmChallenge {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f36a;

    protected b(int i) throws VOException {
        if (i == 0) {
            throw new VOException(VOStatusCode.ERROR_GENERAL_FAILURE, "failed to generate personalization challenge.");
        }
        this.f36a = i;
    }

    protected void finalize() {
        try {
            JniDrmManager.DisposeChallenge(this.f36a);
            super.finalize();
        } catch (Throwable unused) {
        }
    }
}
