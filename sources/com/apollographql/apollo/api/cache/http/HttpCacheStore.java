package com.apollographql.apollo.api.cache.http;

import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: compiled from: HttpCacheStore.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo/api/cache/http/HttpCacheStore;", "", "cacheRecord", "Lcom/apollographql/apollo/api/cache/http/HttpCacheRecord;", "cacheKey", "", "cacheRecordEditor", "Lcom/apollographql/apollo/api/cache/http/HttpCacheRecordEditor;", "delete", "", "remove", "apollo-http-cache-api"}, k = 1, mv = {1, 1, 16})
public interface HttpCacheStore {
    HttpCacheRecord cacheRecord(String cacheKey) throws IOException;

    HttpCacheRecordEditor cacheRecordEditor(String cacheKey) throws IOException;

    void delete() throws IOException;

    void remove(String cacheKey) throws IOException;
}
