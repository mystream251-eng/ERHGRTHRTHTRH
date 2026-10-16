package com.google.android.datatransport.runtime.time;

import dagger.Module;
import dagger.Provides;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
@Module
public abstract class TimeModule {
    @Provides
    static Clock eventClock() {
        return new WallTimeClock();
    }

    @Provides
    static Clock uptimeClock() {
        return new UptimeClock();
    }
}
