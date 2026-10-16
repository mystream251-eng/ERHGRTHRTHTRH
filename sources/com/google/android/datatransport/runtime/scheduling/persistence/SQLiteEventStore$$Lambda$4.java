package com.google.android.datatransport.runtime.scheduling.persistence;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$4 implements SQLiteEventStore.Function {
    private static final SQLiteEventStore$$Lambda$4 instance = new SQLiteEventStore$$Lambda$4();

    private SQLiteEventStore$$Lambda$4() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$getDb$0((Throwable) obj);
    }
}
