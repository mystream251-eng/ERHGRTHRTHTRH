package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$19 implements SQLiteEventStore.Function {
    private static final SQLiteEventStore$$Lambda$19 instance = new SQLiteEventStore$$Lambda$19();

    private SQLiteEventStore$$Lambda$19() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadActiveContexts$8((Cursor) obj);
    }
}
