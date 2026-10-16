package com.google.android.datatransport.runtime.synchronization;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
public interface SynchronizationGuard {

    /* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    public interface CriticalSection<T> {
        T execute();
    }

    <T> T runCriticalSection(CriticalSection<T> criticalSection);
}
