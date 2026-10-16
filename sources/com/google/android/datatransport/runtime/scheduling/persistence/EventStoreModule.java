package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
@Module
public abstract class EventStoreModule {
    @Binds
    abstract EventStore eventStore(SQLiteEventStore sQLiteEventStore);

    @Binds
    abstract SynchronizationGuard synchronizationGuard(SQLiteEventStore sQLiteEventStore);

    @Provides
    static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    @Provides
    @Named("SCHEMA_VERSION")
    static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }
}
