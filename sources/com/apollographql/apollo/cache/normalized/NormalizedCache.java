package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.cache.ApolloCacheHeaders;
import com.apollographql.apollo.cache.CacheHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: NormalizedCache.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000J\b\u0010\t\u001a\u00020\nH&J)\u0010\u000b\u001a#\u0012\r\u0012\u000b\u0012\u0002\b\u00030\r¢\u0006\u0002\b\u000e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\f0\fH\u0016J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H&J$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00192\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J(\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00192\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0014H$J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020 H&R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0000@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006%"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/NormalizedCache;", "", "()V", "<set-?>", "nextCache", "getNextCache", "()Lcom/apollographql/apollo/cache/normalized/NormalizedCache;", "chain", "cache", "clearAll", "", "dump", "", "Lkotlin/reflect/KClass;", "Lkotlin/jvm/JvmSuppressWildcards;", "", "Lcom/apollographql/apollo/cache/normalized/Record;", "loadRecord", "key", "cacheHeaders", "Lcom/apollographql/apollo/cache/CacheHeaders;", "loadRecords", "", "keys", "merge", "", "record", "recordSet", "performMerge", "apolloRecord", "oldRecord", "remove", "", "cacheKey", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "cascade", "Companion", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public abstract class NormalizedCache {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private NormalizedCache nextCache;

    @JvmStatic
    public static final String prettifyDump(Map<KClass<?>, ? extends Map<String, Record>> map) {
        return INSTANCE.prettifyDump(map);
    }

    public abstract void clearAll();

    public abstract Record loadRecord(String key, CacheHeaders cacheHeaders);

    protected abstract Set<String> performMerge(Record apolloRecord, Record oldRecord, CacheHeaders cacheHeaders);

    public abstract boolean remove(CacheKey cacheKey, boolean cascade);

    public final NormalizedCache getNextCache() {
        return this.nextCache;
    }

    public Collection<Record> loadRecords(Collection<String> keys, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        ArrayList arrayList = new ArrayList(keys.size());
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            Record recordLoadRecord = loadRecord(it.next(), cacheHeaders);
            if (recordLoadRecord != null) {
                arrayList.add(recordLoadRecord);
            }
        }
        return arrayList;
    }

    public Set<String> merge(Record record, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(record, "record");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        if (cacheHeaders.hasHeader(ApolloCacheHeaders.DO_NOT_STORE)) {
            return SetsKt.emptySet();
        }
        NormalizedCache normalizedCache = this.nextCache;
        Set<String> setMerge = normalizedCache != null ? normalizedCache.merge(record, cacheHeaders) : null;
        if (setMerge == null) {
            setMerge = SetsKt.emptySet();
        }
        Set<String> setPerformMerge = performMerge(record, loadRecord(record.getKey(), cacheHeaders), cacheHeaders);
        HashSet hashSet = new HashSet();
        hashSet.addAll(setMerge);
        hashSet.addAll(setPerformMerge);
        return hashSet;
    }

    public Set<String> merge(Collection<Record> recordSet, CacheHeaders cacheHeaders) {
        Intrinsics.checkParameterIsNotNull(recordSet, "recordSet");
        Intrinsics.checkParameterIsNotNull(cacheHeaders, "cacheHeaders");
        if (cacheHeaders.hasHeader(ApolloCacheHeaders.DO_NOT_STORE)) {
            return SetsKt.emptySet();
        }
        NormalizedCache normalizedCache = this.nextCache;
        Set<String> setMerge = normalizedCache != null ? normalizedCache.merge(recordSet, cacheHeaders) : null;
        if (setMerge == null) {
            setMerge = SetsKt.emptySet();
        }
        HashSet hashSet = new HashSet();
        Collection<Record> collection = recordSet;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((Record) it.next()).getKey());
        }
        Collection<Record> collectionLoadRecords = loadRecords(arrayList, cacheHeaders);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collectionLoadRecords, 10)), 16));
        for (Object obj : collectionLoadRecords) {
            linkedHashMap.put(((Record) obj).getKey(), obj);
        }
        for (Record record : recordSet) {
            hashSet.addAll(performMerge(record, (Record) linkedHashMap.get(record.getKey()), cacheHeaders));
        }
        HashSet hashSet2 = new HashSet();
        hashSet2.addAll(setMerge);
        hashSet2.addAll(hashSet);
        return hashSet2;
    }

    public final boolean remove(CacheKey cacheKey) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        return remove(cacheKey, false);
    }

    public final NormalizedCache chain(NormalizedCache cache) {
        Intrinsics.checkParameterIsNotNull(cache, "cache");
        NormalizedCache normalizedCache = this;
        NormalizedCache normalizedCache2 = normalizedCache;
        while (true) {
            NormalizedCache normalizedCache3 = normalizedCache2.nextCache;
            if (normalizedCache3 == null) {
                normalizedCache2.nextCache = cache;
                return normalizedCache;
            }
            if (normalizedCache3 == null) {
                Intrinsics.throwNpe();
            }
            normalizedCache2 = normalizedCache3;
        }
    }

    public Map<KClass<?>, Map<String, Record>> dump() {
        return MapsKt.mapOf(TuplesKt.to(Reflection.getOrCreateKotlinClass(getClass()), MapsKt.emptyMap()));
    }

    /* JADX INFO: compiled from: NormalizedCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042'\u0010\u0005\u001a#\u0012\r\u0012\u000b\u0012\u0002\b\u00030\u0007¢\u0006\u0002\b\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u00060\u0006H\u0007¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/NormalizedCache$Companion;", "", "()V", "prettifyDump", "", "dump", "", "Lkotlin/reflect/KClass;", "Lkotlin/jvm/JvmSuppressWildcards;", "Lcom/apollographql/apollo/cache/normalized/Record;", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final String prettifyDump(Map<KClass<?>, ? extends Map<String, Record>> dump) {
            Intrinsics.checkParameterIsNotNull(dump, "dump");
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<KClass<?>, ? extends Map<String, Record>> entry : dump.entrySet()) {
                KClass<?> key = entry.getKey();
                Map<String, Record> value = entry.getValue();
                sb.append(key.getSimpleName());
                sb.append(" {");
                for (Map.Entry<String, Record> entry2 : value.entrySet()) {
                    String key2 = entry2.getKey();
                    Record value2 = entry2.getValue();
                    sb.append("\n  \"");
                    sb.append(key2);
                    sb.append("\" : {");
                    for (Map.Entry<String, Object> entry3 : value2.getFields().entrySet()) {
                        String key3 = entry3.getKey();
                        Object value3 = entry3.getValue();
                        sb.append("\n    \"");
                        sb.append(key3);
                        sb.append("\" : ");
                        if (value3 instanceof CacheReference) {
                            sb.append("CacheRecordRef(");
                            sb.append(value3);
                            sb.append(")");
                        } else if (value3 instanceof List) {
                            sb.append("[");
                            for (Object obj : (List) value3) {
                                sb.append("\n      ");
                                boolean z = obj instanceof CacheReference;
                                String str = "";
                                sb.append(z ? "CacheRecordRef(" : "");
                                sb.append(obj);
                                if (z) {
                                    str = ")";
                                }
                                sb.append(str);
                            }
                            sb.append("\n    ]");
                        } else {
                            sb.append(value3);
                        }
                    }
                    sb.append("\n  }\n");
                }
                sb.append("}\n");
            }
            String string = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }
    }
}
