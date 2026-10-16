package com.apollographql.apollo.cache.normalized.internal;

import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.Record;
import java.util.Collection;
import kotlin.Metadata;

/* JADX INFO: compiled from: ReadableStore.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/ReadableStore;", "", "read", "Lcom/apollographql/apollo/cache/normalized/Record;", "key", "", "cacheHeaders", "Lcom/apollographql/apollo/cache/CacheHeaders;", "", "keys", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public interface ReadableStore {
    Record read(String key, CacheHeaders cacheHeaders);

    Collection<Record> read(Collection<String> keys, CacheHeaders cacheHeaders);
}
