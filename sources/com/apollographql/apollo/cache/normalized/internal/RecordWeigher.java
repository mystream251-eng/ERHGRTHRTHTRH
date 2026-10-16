package com.apollographql.apollo.cache.normalized.internal;

import com.apollographql.apollo.cache.normalized.CacheReference;
import com.apollographql.apollo.cache.normalized.Record;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okio.internal._Utf8Kt;

/* JADX INFO: compiled from: RecordWeigher.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0012\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/RecordWeigher;", "", "()V", "SIZE_OF_ARRAY_OVERHEAD", "", "SIZE_OF_BIG_DECIMAL", "SIZE_OF_BOOLEAN", "SIZE_OF_CACHE_REFERENCE_OVERHEAD", "SIZE_OF_NULL", "SIZE_OF_RECORD_OVERHEAD", "byteChange", "newValue", "oldValue", "calculateBytes", "record", "Lcom/apollographql/apollo/cache/normalized/Record;", "weighField", "field", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public final class RecordWeigher {
    public static final RecordWeigher INSTANCE = new RecordWeigher();
    private static final int SIZE_OF_ARRAY_OVERHEAD = 16;
    private static final int SIZE_OF_BIG_DECIMAL = 32;
    private static final int SIZE_OF_BOOLEAN = 16;
    private static final int SIZE_OF_CACHE_REFERENCE_OVERHEAD = 16;
    private static final int SIZE_OF_NULL = 4;
    private static final int SIZE_OF_RECORD_OVERHEAD = 16;

    private RecordWeigher() {
    }

    @JvmStatic
    public static final int byteChange(Object newValue, Object oldValue) {
        return INSTANCE.weighField(newValue) - INSTANCE.weighField(oldValue);
    }

    @JvmStatic
    public static final int calculateBytes(Record record) {
        Intrinsics.checkParameterIsNotNull(record, "record");
        int length = _Utf8Kt.commonAsUtf8ToByteArray(record.getKey()).length + 16;
        for (Map.Entry<String, Object> entry : record.getFields().entrySet()) {
            length += _Utf8Kt.commonAsUtf8ToByteArray(entry.getKey()).length + INSTANCE.weighField(entry.getValue());
        }
        return length;
    }

    private final int weighField(Object field) {
        if (field == null) {
            return 4;
        }
        if (field instanceof String) {
            return _Utf8Kt.commonAsUtf8ToByteArray((String) field).length;
        }
        if (field instanceof Boolean) {
            return 16;
        }
        if (field instanceof BigDecimal) {
            return 32;
        }
        if (!(field instanceof List)) {
            if (field instanceof CacheReference) {
                return 16 + _Utf8Kt.commonAsUtf8ToByteArray(((CacheReference) field).getKey()).length;
            }
            throw new IllegalStateException(("Unknown field type in Record. " + Reflection.getOrCreateKotlinClass(field.getClass()).getQualifiedName()).toString());
        }
        int iWeighField = 0;
        Iterator it = ((Iterable) field).iterator();
        while (it.hasNext()) {
            iWeighField += INSTANCE.weighField(it.next());
        }
        return 16 + iWeighField;
    }
}
