package com.apollographql.apollo.cache.normalized.internal;

import com.apollographql.apollo.api.internal.json.JsonReader;
import com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader;
import com.apollographql.apollo.cache.normalized.CacheReference;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CacheJsonStreamReader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/CacheJsonStreamReader;", "Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader;", "jsonReader", "Lcom/apollographql/apollo/api/internal/json/JsonReader;", "(Lcom/apollographql/apollo/api/internal/json/JsonReader;)V", "nextScalar", "", "optional", "", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public final class CacheJsonStreamReader extends ResponseJsonStreamReader {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CacheJsonStreamReader(JsonReader jsonReader) {
        super(jsonReader);
        Intrinsics.checkParameterIsNotNull(jsonReader, "jsonReader");
    }

    @Override // com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader
    public Object nextScalar(boolean optional) throws IOException {
        Object objNextScalar = super.nextScalar(optional);
        if (!(objNextScalar instanceof String)) {
            return objNextScalar;
        }
        String str = (String) objNextScalar;
        return CacheReference.INSTANCE.canDeserialize(str) ? CacheReference.INSTANCE.deserialize(str) : objNextScalar;
    }
}
