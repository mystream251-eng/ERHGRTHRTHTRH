package com.apollographql.apollo.cache.normalized.internal;

import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.Record;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: compiled from: WriteableStore.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/WriteableStore;", "Lcom/apollographql/apollo/cache/normalized/internal/ReadableStore;", "merge", "", "", "record", "Lcom/apollographql/apollo/cache/normalized/Record;", "cacheHeaders", "Lcom/apollographql/apollo/cache/CacheHeaders;", "recordCollection", "", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public interface WriteableStore extends ReadableStore {
    Set<String> merge(Record record, CacheHeaders cacheHeaders);

    Set<String> merge(Collection<Record> recordCollection, CacheHeaders cacheHeaders);
}
