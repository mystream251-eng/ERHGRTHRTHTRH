package com.apollographql.apollo.cache.normalized;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecordSet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bJ\u0013\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0002J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\r\u001a\u00020\u0006R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/RecordSet;", "", "()V", "recordMap", "", "", "Lcom/apollographql/apollo/cache/normalized/Record;", "allRecords", "", "get", "key", "merge", "", "apolloRecord", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public final class RecordSet {
    private final Map<String, Record> recordMap = new LinkedHashMap();

    public final Record get(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.recordMap.get(key);
    }

    public final Set<String> merge(Record apolloRecord) {
        Intrinsics.checkParameterIsNotNull(apolloRecord, "apolloRecord");
        Record record = this.recordMap.get(apolloRecord.getKey());
        if (record == null) {
            this.recordMap.put(apolloRecord.getKey(), apolloRecord);
            return SetsKt.emptySet();
        }
        return record.mergeWith(apolloRecord);
    }

    public final Collection<Record> allRecords() {
        return CollectionsKt.toList(this.recordMap.values());
    }
}
