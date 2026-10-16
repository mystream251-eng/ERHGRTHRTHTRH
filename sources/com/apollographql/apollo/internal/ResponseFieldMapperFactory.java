package com.apollographql.apollo.internal;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.Utils;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseFieldMapperFactory {
    private final ConcurrentHashMap<Class, ResponseFieldMapper> pool = new ConcurrentHashMap<>();

    public ResponseFieldMapper create(Operation operation) {
        Utils.checkNotNull(operation, "operation == null");
        Class<?> cls = operation.getClass();
        ResponseFieldMapper responseFieldMapper = this.pool.get(cls);
        if (responseFieldMapper != null) {
            return responseFieldMapper;
        }
        this.pool.putIfAbsent(cls, operation.responseFieldMapper());
        return this.pool.get(cls);
    }
}
