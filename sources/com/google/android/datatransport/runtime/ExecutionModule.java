package com.google.android.datatransport.runtime;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
@Module
abstract class ExecutionModule {
    ExecutionModule() {
    }

    @Provides
    @Singleton
    static Executor executor() {
        return Executors.newSingleThreadExecutor();
    }
}
