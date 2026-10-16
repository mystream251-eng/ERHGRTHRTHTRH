package com.dotscreen.portal.ads;

import kotlin.Metadata;

/* JADX INFO: compiled from: AdRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/ads/IFloatingAdListener;", "", "onFloatingAdReceived", "", "overrideFloatingAdHandling", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public interface IFloatingAdListener {

    /* JADX INFO: compiled from: AdRequestManager.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static boolean overrideFloatingAdHandling(IFloatingAdListener iFloatingAdListener) {
            return false;
        }
    }

    void onFloatingAdReceived();

    boolean overrideFloatingAdHandling();
}
