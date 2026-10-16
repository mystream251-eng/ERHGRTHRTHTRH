package com.google.android.datatransport.runtime.scheduling.persistence;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$18 implements SQLiteEventStore.Function {
    private static final SQLiteEventStore$$Lambda$18 instance = new SQLiteEventStore$$Lambda$18();

    private SQLiteEventStore$$Lambda$18() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$ensureBeginTransaction$15((Throwable) obj);
    }
}
