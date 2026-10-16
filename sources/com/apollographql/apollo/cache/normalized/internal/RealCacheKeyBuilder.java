package com.apollographql.apollo.cache.normalized.internal;

import com.apollographql.apollo.api.InputType;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.internal.json.JsonWriter;
import com.apollographql.apollo.api.internal.json.Utils;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;

/* JADX INFO: compiled from: RealCacheKeyBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J4\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J(\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/RealCacheKeyBuilder;", "Lcom/apollographql/apollo/cache/normalized/internal/CacheKeyBuilder;", "()V", "build", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "variables", "Lcom/apollographql/apollo/api/Operation$Variables;", "resolveArguments", "", "", "objectMap", "resolveVariableArgument", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class RealCacheKeyBuilder implements CacheKeyBuilder {
    @Override // com.apollographql.apollo.cache.normalized.internal.CacheKeyBuilder
    public String build(ResponseField field, Operation.Variables variables) throws IOException {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        if (field.getArguments().isEmpty()) {
            return field.getFieldName();
        }
        Map<String, Object> mapResolveArguments = resolveArguments(field.getArguments(), variables);
        try {
            Buffer buffer = new Buffer();
            JsonWriter jsonWriterOf = JsonWriter.INSTANCE.of(buffer);
            jsonWriterOf.setSerializeNulls(true);
            Utils.writeToJson(mapResolveArguments, jsonWriterOf);
            jsonWriterOf.close();
            return field.getFieldName() + '(' + buffer.readUtf8() + ')';
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final Object resolveVariableArgument(Map<String, ? extends Object> objectMap, Operation.Variables variables) throws IOException {
        Object obj = variables.valueMap().get(objectMap.get(ResponseField.VARIABLE_NAME_KEY));
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return resolveArguments((Map) obj, variables);
        }
        if (!(obj instanceof InputType)) {
            return obj;
        }
        SortedInputFieldMapWriter sortedInputFieldMapWriter = new SortedInputFieldMapWriter();
        ((InputType) obj).marshaller().marshal(sortedInputFieldMapWriter);
        return resolveArguments(sortedInputFieldMapWriter.map(), variables);
    }

    private final Map<String, Object> resolveArguments(Map<String, ? extends Object> objectMap, Operation.Variables variables) throws IOException {
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(objectMap.size()));
        Iterator<T> it = objectMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                Map<String, ? extends Object> map = (Map) value;
                if (ResponseField.INSTANCE.isArgumentValueVariableType(map)) {
                    value = resolveVariableArgument(map, variables);
                } else {
                    value = resolveArguments(map, variables);
                }
            }
            linkedHashMap.put(key, value);
        }
        return MapsKt.toMap(CollectionsKt.sortedWith(MapsKt.toList(linkedHashMap), new Comparator<T>() { // from class: com.apollographql.apollo.cache.normalized.internal.RealCacheKeyBuilder$resolveArguments$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((String) ((Pair) t).getFirst(), (String) ((Pair) t2).getFirst());
            }
        }));
    }
}
