package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.Module;
import dagger.Provides;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
@Module
public abstract class SchedulingConfigModule {
    @Provides
    static SchedulerConfig config(Clock clock) {
        return SchedulerConfig.getDefault(clock);
    }
}
