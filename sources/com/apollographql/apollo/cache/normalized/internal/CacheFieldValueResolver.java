package com.apollographql.apollo.cache.normalized.internal;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.internal.FieldValueResolver;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.CacheReference;
import com.apollographql.apollo.cache.normalized.Record;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CacheFieldValueResolver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ%\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013J%\u0010\u0014\u001a\u0004\u0018\u0001H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0016\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00172\f\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0017H\u0002J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/CacheFieldValueResolver;", "Lcom/apollographql/apollo/api/internal/FieldValueResolver;", "Lcom/apollographql/apollo/cache/normalized/Record;", "readableCache", "Lcom/apollographql/apollo/cache/normalized/internal/ReadableStore;", "variables", "Lcom/apollographql/apollo/api/Operation$Variables;", "cacheKeyResolver", "Lcom/apollographql/apollo/cache/normalized/CacheKeyResolver;", "cacheHeaders", "Lcom/apollographql/apollo/cache/CacheHeaders;", "cacheKeyBuilder", "Lcom/apollographql/apollo/cache/normalized/internal/CacheKeyBuilder;", "(Lcom/apollographql/apollo/cache/normalized/internal/ReadableStore;Lcom/apollographql/apollo/api/Operation$Variables;Lcom/apollographql/apollo/cache/normalized/CacheKeyResolver;Lcom/apollographql/apollo/cache/CacheHeaders;Lcom/apollographql/apollo/cache/normalized/internal/CacheKeyBuilder;)V", "fieldValue", ExifInterface.GPS_DIRECTION_TRUE, "record", "field", "Lcom/apollographql/apollo/api/ResponseField;", "(Lcom/apollographql/apollo/cache/normalized/Record;Lcom/apollographql/apollo/api/ResponseField;)Ljava/lang/Object;", "valueFor", "recordSet", "valueForList", "", "values", "valueForObject", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class CacheFieldValueResolver implements FieldValueResolver<Record> {
    private final CacheHeaders cacheHeaders;
    private final CacheKeyBuilder cacheKeyBuilder;
    private final CacheKeyResolver cacheKeyResolver;
    private final ReadableStore readableCache;
    private final Operation.Variables variables;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ResponseField.Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ResponseField.Type.OBJECT.ordinal()] = 1;
            $EnumSwitchMapping$0[ResponseField.Type.LIST.ordinal()] = 2;
        }
    }

    public CacheFieldValueResolver(ReadableStore readableCache, Operation.Variables variables, CacheKeyResolver cacheKeyResolver, CacheHeaders cacheHeaders, CacheKeyBuilder cacheKeyBuilder) {
        Intrinsics.checkParameterIsNotNull(readableCache, "readableCache");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        Intrinsics.checkParameterIsNotNull(cacheKeyResolver, "cacheKeyResolver");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        Intrinsics.checkParameterIsNotNull(cacheKeyBuilder, "cacheKeyBuilder");
        this.readableCache = readableCache;
        this.variables = variables;
        this.cacheKeyResolver = cacheKeyResolver;
        this.cacheHeaders = cacheHeaders;
        this.cacheKeyBuilder = cacheKeyBuilder;
    }

    @Override // com.apollographql.apollo.api.internal.FieldValueResolver
    public <T> T valueFor(Record recordSet, ResponseField field) {
        Intrinsics.checkParameterIsNotNull(recordSet, "recordSet");
        Intrinsics.checkParameterIsNotNull(field, "field");
        int i = WhenMappings.$EnumSwitchMapping$0[field.getType().ordinal()];
        if (i == 1) {
            return (T) valueForObject(recordSet, field);
        }
        if (i == 2) {
            return (T) valueForList((List) fieldValue(recordSet, field));
        }
        return (T) fieldValue(recordSet, field);
    }

    private final Record valueForObject(Record record, ResponseField field) {
        CacheKey cacheKeyFromFieldArguments = this.cacheKeyResolver.fromFieldArguments(field, this.variables);
        CacheReference cacheReference = Intrinsics.areEqual(cacheKeyFromFieldArguments, CacheKey.NO_KEY) ? (CacheReference) fieldValue(record, field) : new CacheReference(cacheKeyFromFieldArguments.getKey());
        if (cacheReference == null) {
            return null;
        }
        Record record2 = this.readableCache.read(cacheReference.getKey(), this.cacheHeaders);
        if (record2 != null) {
            return record2;
        }
        throw new IllegalStateException("Cache MISS: failed to find record in cache by reference".toString());
    }

    private final List<?> valueForList(List<?> values) {
        if (values == null) {
            return null;
        }
        List<?> list = values;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Object objValueForList : list) {
            if (objValueForList instanceof CacheReference) {
                objValueForList = this.readableCache.read(((CacheReference) objValueForList).getKey(), this.cacheHeaders);
                if (objValueForList == null) {
                    throw new IllegalStateException("Cache MISS: failed to find record in cache by reference".toString());
                }
            } else if (objValueForList instanceof List) {
                objValueForList = valueForList((List) objValueForList);
            }
            arrayList.add(objValueForList);
        }
        return arrayList;
    }

    private final <T> T fieldValue(Record record, ResponseField field) {
        String strBuild = this.cacheKeyBuilder.build(field, this.variables);
        if (!record.hasField(strBuild)) {
            throw new IllegalStateException(("Missing value: " + field.getFieldName()).toString());
        }
        return (T) record.field(strBuild);
    }
}
