package com.apollographql.apollo.cache.normalized.internal;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import kotlin.Metadata;

/* JADX INFO: compiled from: CacheKeyBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/CacheKeyBuilder;", "", "build", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "variables", "Lcom/apollographql/apollo/api/Operation$Variables;", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public interface CacheKeyBuilder {
    String build(ResponseField field, Operation.Variables variables);
}
