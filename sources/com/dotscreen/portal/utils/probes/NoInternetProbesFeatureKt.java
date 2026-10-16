package com.dotscreen.portal.utils.probes;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

/* JADX INFO: compiled from: NoInternetProbesFeature.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"PAUSE_DELAY", "", "REPEAT_DELAY", "TEST", "", "UPTIME_DELAY", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class NoInternetProbesFeatureKt {
    private static final boolean TEST = false;
    private static final long UPTIME_DELAY = TimeUnit.SECONDS.toMillis(20);
    private static final long REPEAT_DELAY = TimeUnit.MINUTES.toMillis(1);
    private static final long PAUSE_DELAY = TimeUnit.MINUTES.toMillis(1);
}
