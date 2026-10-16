package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.cache.CacheHeaders;
import com.nytimes.android.external.cache.Cache;
import com.nytimes.android.external.cache.CacheBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: OptimisticNormalizedCache.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016J$\u0010\n\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u000b0\u000bH\u0016J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00162\u0006\u0010\u0017\u001a\u00020\rJ\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0013J(\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u00162\u0006\u0010\u001b\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0016J\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u00162\u0006\u0010#\u001a\u00020$J\u0018\u0010%\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002R*\u0010\u0003\u001a\u001e\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00070\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/OptimisticNormalizedCache;", "Lcom/apollographql/apollo/cache/normalized/NormalizedCache;", "()V", "lruCache", "Lcom/nytimes/android/external/cache/Cache;", "", "kotlin.jvm.PlatformType", "Lcom/apollographql/apollo/cache/normalized/OptimisticNormalizedCache$RecordJournal;", "clearAll", "", "dump", "", "Lkotlin/reflect/KClass;", "Lcom/apollographql/apollo/cache/normalized/Record;", "loadRecord", "key", "cacheHeaders", "Lcom/apollographql/apollo/cache/CacheHeaders;", "loadRecords", "", "keys", "mergeOptimisticUpdate", "", "record", "mergeOptimisticUpdates", "recordSet", "performMerge", "apolloRecord", "oldRecord", "remove", "", "cacheKey", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "cascade", "removeOptimisticUpdates", "mutationId", "Ljava/util/UUID;", "mergeJournalRecord", "RecordJournal", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public final class OptimisticNormalizedCache extends NormalizedCache {
    private final Cache<String, RecordJournal> lruCache;

    public OptimisticNormalizedCache() {
        Cache cacheBuild = CacheBuilder.newBuilder().build();
        Intrinsics.checkExpressionValueIsNotNull(cacheBuild, "CacheBuilder.newBuilder(…<String, RecordJournal>()");
        this.lruCache = cacheBuild;
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public Record loadRecord(String key, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        try {
            NormalizedCache nextCache = getNextCache();
            return mergeJournalRecord(nextCache != null ? nextCache.loadRecord(key, cacheHeaders) : null, key);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public Collection<Record> loadRecords(Collection<String> keys, CacheHeaders cacheHeaders) {
        LinkedHashMap linkedHashMapEmptyMap;
        Collection<Record> collectionLoadRecords;
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        NormalizedCache nextCache = getNextCache();
        if (nextCache == null || (collectionLoadRecords = nextCache.loadRecords(keys, cacheHeaders)) == null) {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        } else {
            Collection<Record> collection = collectionLoadRecords;
            linkedHashMapEmptyMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection, 10)), 16));
            for (Object obj : collection) {
                linkedHashMapEmptyMap.put(((Record) obj).getKey(), obj);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (String str : keys) {
            Record recordMergeJournalRecord = mergeJournalRecord((Record) linkedHashMapEmptyMap.get(str), str);
            if (recordMergeJournalRecord != null) {
                arrayList.add(recordMergeJournalRecord);
            }
        }
        return arrayList;
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public void clearAll() {
        this.lruCache.invalidateAll();
        NormalizedCache nextCache = getNextCache();
        if (nextCache != null) {
            nextCache.clearAll();
        }
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public boolean remove(CacheKey cacheKey, boolean cascade) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        NormalizedCache nextCache = getNextCache();
        boolean zRemove = nextCache != null ? nextCache.remove(cacheKey, cascade) : false;
        RecordJournal ifPresent = this.lruCache.getIfPresent(cacheKey.getKey());
        if (ifPresent == null) {
            return zRemove;
        }
        this.lruCache.invalidate(cacheKey.getKey());
        if (!cascade) {
            return true;
        }
        while (true) {
            boolean z = true;
            for (CacheReference cacheReference : ifPresent.getSnapshot().referencedFields()) {
                if (!z || !remove(new CacheKey(cacheReference.getKey()), true)) {
                    z = false;
                }
            }
            return z;
        }
    }

    public final Set<String> mergeOptimisticUpdates(Collection<Record> recordSet) {
        Intrinsics.checkParameterIsNotNull(recordSet, "recordSet");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = recordSet.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, mergeOptimisticUpdate((Record) it.next()));
        }
        return CollectionsKt.toSet(arrayList);
    }

    public final Set<String> mergeOptimisticUpdate(Record record) {
        Intrinsics.checkParameterIsNotNull(record, "record");
        RecordJournal ifPresent = this.lruCache.getIfPresent(record.getKey());
        if (ifPresent == null) {
            this.lruCache.put(record.getKey(), new RecordJournal(record));
            return SetsKt.setOf(record.getKey());
        }
        return ifPresent.commit(record);
    }

    public final Set<String> removeOptimisticUpdates(UUID mutationId) {
        Intrinsics.checkParameterIsNotNull(mutationId, "mutationId");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        ConcurrentMap<String, RecordJournal> concurrentMapAsMap = this.lruCache.asMap();
        Intrinsics.checkExpressionValueIsNotNull(concurrentMapAsMap, "lruCache.asMap()");
        for (Map.Entry<String, RecordJournal> entry : concurrentMapAsMap.entrySet()) {
            String cacheKey = entry.getKey();
            RecordJournal value = entry.getValue();
            linkedHashSet.addAll(value.revert(mutationId));
            if (value.getHistory().isEmpty()) {
                Intrinsics.checkExpressionValueIsNotNull(cacheKey, "cacheKey");
                linkedHashSet2.add(cacheKey);
            }
        }
        this.lruCache.invalidateAll(linkedHashSet2);
        return linkedHashSet;
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    protected Set<String> performMerge(Record apolloRecord, Record oldRecord, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(apolloRecord, "apolloRecord");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        return SetsKt.emptySet();
    }

    @Override // com.apollographql.apollo.cache.normalized.NormalizedCache
    public Map<KClass<?>, Map<String, Record>> dump() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(getClass());
        ConcurrentMap<String, RecordJournal> concurrentMapAsMap = this.lruCache.asMap();
        Intrinsics.checkExpressionValueIsNotNull(concurrentMapAsMap, "lruCache.asMap()");
        ConcurrentMap<String, RecordJournal> concurrentMap = concurrentMapAsMap;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(concurrentMap.size()));
        Iterator<T> it = concurrentMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap2.put(entry.getKey(), ((RecordJournal) entry.getValue()).getSnapshot());
        }
        linkedHashMap.put(orCreateKotlinClass, linkedHashMap2);
        NormalizedCache nextCache = getNextCache();
        Map<KClass<?>, Map<String, Record>> mapDump = nextCache != null ? nextCache.dump() : null;
        if (mapDump == null) {
            mapDump = MapsKt.emptyMap();
        }
        linkedHashMap.putAll(mapDump);
        return linkedHashMap;
    }

    private final Record mergeJournalRecord(Record record, String str) {
        Record.Builder builder;
        Record recordBuild;
        RecordJournal ifPresent = this.lruCache.getIfPresent(str);
        if (ifPresent == null) {
            return record;
        }
        if (record != null && (builder = record.toBuilder()) != null && (recordBuild = builder.build()) != null) {
            recordBuild.mergeWith(ifPresent.getSnapshot());
            if (recordBuild != null) {
                return recordBuild;
            }
        }
        return ifPresent.getSnapshot().toBuilder().build();
    }

    /* JADX INFO: compiled from: OptimisticNormalizedCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0003J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\u0013R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0004¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/OptimisticNormalizedCache$RecordJournal;", "", "mutationRecord", "Lcom/apollographql/apollo/cache/normalized/Record;", "(Lcom/apollographql/apollo/cache/normalized/Record;)V", "history", "", "getHistory", "()Ljava/util/List;", "snapshot", "getSnapshot", "()Lcom/apollographql/apollo/cache/normalized/Record;", "setSnapshot", "commit", "", "", "record", "revert", "mutationId", "Ljava/util/UUID;", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
    private static final class RecordJournal {
        private final List<Record> history;
        private Record snapshot;

        public RecordJournal(Record mutationRecord) {
            Intrinsics.checkParameterIsNotNull(mutationRecord, "mutationRecord");
            this.snapshot = mutationRecord.toBuilder().build();
            this.history = CollectionsKt.mutableListOf(mutationRecord.toBuilder().build());
        }

        public final Record getSnapshot() {
            return this.snapshot;
        }

        public final void setSnapshot(Record record) {
            Intrinsics.checkParameterIsNotNull(record, "<set-?>");
            this.snapshot = record;
        }

        public final List<Record> getHistory() {
            return this.history;
        }

        public final Set<String> commit(Record record) {
            Intrinsics.checkParameterIsNotNull(record, "record");
            List<Record> list = this.history;
            list.add(list.size(), record.toBuilder().build());
            return this.snapshot.mergeWith(record);
        }

        public final Set<String> revert(UUID mutationId) {
            Intrinsics.checkParameterIsNotNull(mutationId, "mutationId");
            Iterator<Record> it = this.history.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (Intrinsics.areEqual(mutationId, it.next().getMutationId())) {
                    break;
                }
                i++;
            }
            if (i == -1) {
                return SetsKt.emptySet();
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.add(this.history.remove(i).getKey());
            int i2 = i - 1;
            int size = this.history.size();
            for (int iMax = Math.max(0, i2); iMax < size; iMax++) {
                Record record = this.history.get(iMax);
                if (iMax == Math.max(0, i2)) {
                    this.snapshot = record.toBuilder().build();
                } else {
                    linkedHashSet.addAll(this.snapshot.mergeWith(record));
                }
            }
            return linkedHashSet;
        }
    }
}
