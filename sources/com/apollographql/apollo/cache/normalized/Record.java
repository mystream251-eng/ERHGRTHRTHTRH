package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.cache.normalized.internal.RecordWeigher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Record.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 *2\u00020\u0001:\u0002)*B5\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\u0002\u0010\tJ\u001c\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0019\u001a\u00020\u0000H\u0007J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001b\u001a\u00020\u0003J\u0016\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH\u0007J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u0003J\b\u0010\u0002\u001a\u00020\u0003H\u0007J\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fJ\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010!\u001a\u00020\u0000J\u0010\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bH\u0007J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#J\u0006\u0010%\u001a\u00020\u0014J\u0006\u0010&\u001a\u00020'J\b\u0010(\u001a\u00020\u0003H\u0016R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR.\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\u000e\u0010\u0010\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/Record;", "", "key", "", "_fields", "", "mutationId", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/UUID;)V", "fields", "", "getFields", "()Ljava/util/Map;", "getKey", "()Ljava/lang/String;", "<set-?>", "getMutationId", "()Ljava/util/UUID;", "sizeInBytes", "", "adjustSizeEstimate", "", "newFieldValue", "oldFieldValue", "clone", "field", "fieldKey", "hasField", "", "keys", "", "mergeWith", "otherRecord", "referencedFields", "", "Lcom/apollographql/apollo/cache/normalized/CacheReference;", "sizeEstimateBytes", "toBuilder", "Lcom/apollographql/apollo/cache/normalized/Record$Builder;", "toString", "Builder", "Companion", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public final class Record {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int UNKNOWN_SIZE_ESTIMATE = -1;
    private final Map<String, Object> _fields;
    private final String key;
    private volatile UUID mutationId;
    private int sizeInBytes;

    @JvmStatic
    public static final Builder builder(String str) {
        return INSTANCE.builder(str);
    }

    public Record(String key, Map<String, Object> _fields, UUID uuid) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(_fields, "_fields");
        this.key = key;
        this._fields = _fields;
        this.mutationId = uuid;
        this.sizeInBytes = -1;
    }

    public final String getKey() {
        return this.key;
    }

    public final Map<String, Object> getFields() {
        return this._fields;
    }

    public final UUID getMutationId() {
        return this.mutationId;
    }

    public final Builder toBuilder() {
        return new Builder(this.key, getFields(), this.mutationId);
    }

    public final Object field(String fieldKey) {
        Intrinsics.checkParameterIsNotNull(fieldKey, "fieldKey");
        return getFields().get(fieldKey);
    }

    public final boolean hasField(String fieldKey) {
        Intrinsics.checkParameterIsNotNull(fieldKey, "fieldKey");
        return getFields().containsKey(fieldKey);
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "key", imports = {}))
    public final String key() {
        return this.key;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "mutationId", imports = {}))
    public final UUID mutationId() {
        return this.mutationId;
    }

    @Deprecated(message = "Build from a new builder instead", replaceWith = @ReplaceWith(expression = "toBuilder().build()", imports = {}))
    public final Record clone() {
        return toBuilder().build();
    }

    public String toString() {
        return "Record(key='" + this.key + "', fields=" + getFields() + ", mutationId=" + this.mutationId + ')';
    }

    public final Set<String> mergeWith(Record otherRecord) {
        Intrinsics.checkParameterIsNotNull(otherRecord, "otherRecord");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Map.Entry<String, Object> entry : otherRecord.getFields().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            boolean zContainsKey = getFields().containsKey(key);
            Object obj = getFields().get(key);
            if (!zContainsKey || (!Intrinsics.areEqual(obj, value))) {
                this._fields.put(key, value);
                linkedHashSet.add(this.key + '.' + key);
                adjustSizeEstimate(value, obj);
            }
        }
        this.mutationId = otherRecord.mutationId;
        return linkedHashSet;
    }

    public final Set<String> keys() {
        Set<String> setKeySet = getFields().keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            arrayList.add(this.key + '.' + ((String) it.next()));
        }
        return CollectionsKt.toSet(arrayList);
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "fields", imports = {}))
    public final Map<String, Object> fields() {
        return getFields();
    }

    public final List<CacheReference> referencedFields() {
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it = getFields().values().iterator();
        while (it.hasNext()) {
            INSTANCE.findCacheReferences(it.next(), arrayList);
        }
        return arrayList;
    }

    public final synchronized int sizeEstimateBytes() {
        if (this.sizeInBytes == -1) {
            this.sizeInBytes = RecordWeigher.calculateBytes(this);
        }
        return this.sizeInBytes;
    }

    private final synchronized void adjustSizeEstimate(Object newFieldValue, Object oldFieldValue) {
        if (this.sizeInBytes != -1) {
            this.sizeInBytes += RecordWeigher.byteChange(newFieldValue, oldFieldValue);
        }
    }

    /* JADX INFO: compiled from: Record.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\u0002\u0010\tJ\u0018\u0010\r\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001J\u001c\u0010\u000f\u001a\u00020\u00002\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005J\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0002\u001a\u00020\u0003H\u0007J\u0016\u0010\u0006\u001a\u00020\u00002\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bR\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/Record$Builder;", "", "key", "", "fields", "", "mutationId", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/UUID;)V", "", "getKey", "()Ljava/lang/String;", "addField", "value", "addFields", "build", "Lcom/apollographql/apollo/cache/normalized/Record;", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class Builder {
        private final Map<String, Object> fields;
        private final String key;
        private UUID mutationId;

        public Builder(String key, Map<String, ? extends Object> fields, UUID uuid) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(fields, "fields");
            this.key = key;
            this.mutationId = uuid;
            this.fields = new LinkedHashMap(fields);
        }

        public final String getKey() {
            return this.key;
        }

        public final Builder addField(String key, Object value) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            this.fields.put(key, value);
            return this;
        }

        public final Builder addFields(Map<String, ? extends Object> fields) {
            Intrinsics.checkParameterIsNotNull(fields, "fields");
            this.fields.putAll(fields);
            return this;
        }

        @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "key", imports = {}))
        public final String key() {
            return this.key;
        }

        public final Builder mutationId(UUID mutationId) {
            this.mutationId = mutationId;
            return this;
        }

        public final Record build() {
            return new Record(this.key, this.fields, this.mutationId);
        }
    }

    /* JADX INFO: compiled from: Record.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J \u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/Record$Companion;", "", "()V", "UNKNOWN_SIZE_ESTIMATE", "", "builder", "Lcom/apollographql/apollo/cache/normalized/Record$Builder;", "key", "", "findCacheReferences", "", "cachedValue", "result", "", "Lcom/apollographql/apollo/cache/normalized/CacheReference;", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Builder builder(String key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new Builder(key, new LinkedHashMap(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void findCacheReferences(Object cachedValue, List<CacheReference> result) {
            if (cachedValue instanceof CacheReference) {
                result.add(cachedValue);
                return;
            }
            if (!(cachedValue instanceof Map)) {
                if (cachedValue instanceof List) {
                    Iterator it = ((Iterable) cachedValue).iterator();
                    while (it.hasNext()) {
                        Record.INSTANCE.findCacheReferences(it.next(), result);
                    }
                    return;
                }
                return;
            }
            Iterator it2 = ((Map) cachedValue).values().iterator();
            while (it2.hasNext()) {
                Record.INSTANCE.findCacheReferences(it2.next(), result);
            }
        }
    }
}
