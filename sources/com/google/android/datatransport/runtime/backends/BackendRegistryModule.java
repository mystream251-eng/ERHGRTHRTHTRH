package com.google.android.datatransport.runtime.backends;

import dagger.Binds;
import dagger.Module;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
@Module
public abstract class BackendRegistryModule {
    @Binds
    abstract BackendRegistry backendRegistry(MetadataBackendRegistry metadataBackendRegistry);
}
