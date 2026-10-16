package com.dotscreen.farkit.analytics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveAnalyticsModule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/farkit/analytics/LiveAnalyticsModule;", "Lcom/dotscreen/farkit/analytics/LivePlaybackAnalyticsListener;", "()V", "onZap", "", "channel", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
public abstract class LiveAnalyticsModule implements LivePlaybackAnalyticsListener {
    @Override // com.dotscreen.farkit.analytics.LivePlaybackAnalyticsListener
    public void onZap(Object channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
    }
}
