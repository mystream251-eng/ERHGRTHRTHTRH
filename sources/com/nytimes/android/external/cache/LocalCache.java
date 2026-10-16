package com.nytimes.android.external.cache;

import com.squareup.moshi.MsgpackFormat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.jvm.internal.CharCompanionObject;

/* JADX INFO: loaded from: classes2.dex */
class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    final int concurrencyLevel;
    final CacheLoader<? super K, V> defaultLoader;
    final EntryFactory entryFactory;
    Set<Map.Entry<K, V>> entrySet;
    final long expireAfterAccessNanos;
    final long expireAfterWriteNanos;

    @Nullable
    final Equivalence<Object> keyEquivalence;
    Set<K> keySet;

    @Nullable
    final Strength keyStrength;
    final long maxWeight;
    final long refreshNanos;

    @Nullable
    final RemovalListener<K, V> removalListener;

    @Nonnull
    final Queue<RemovalNotification<K, V>> removalNotificationQueue;
    final int segmentMask;
    final int segmentShift;

    @Nonnull
    final Segment<K, V>[] segments;
    final Ticker ticker;

    @Nullable
    final Equivalence<Object> valueEquivalence;

    @Nullable
    final Strength valueStrength;
    Collection<V> values;

    @Nullable
    final Weigher<K, V> weigher;
    static final Logger logger = Logger.getLogger(LocalCache.class.getName());

    @Nullable
    static final ValueReference<Object, Object> UNSET = new ValueReference<Object, Object>() { // from class: com.nytimes.android.external.cache.LocalCache.1
        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nonnull
        public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> referenceQueue, Object obj, ReferenceEntry<Object, Object> referenceEntry) {
            return this;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public Object get() {
            return null;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public ReferenceEntry<Object, Object> getEntry() {
            return null;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public int getWeight() {
            return 0;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isActive() {
            return false;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public void notifyNewValue(Object obj) {
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public Object waitForValue() {
            return null;
        }
    };

    @Nullable
    static final Queue<? extends Object> DISCARDING_QUEUE = new AbstractQueue<Object>() { // from class: com.nytimes.android.external.cache.LocalCache.2
        @Override // java.util.Queue
        public boolean offer(Object obj) {
            return true;
        }

        @Override // java.util.Queue
        public Object peek() {
            return null;
        }

        @Override // java.util.Queue
        public Object poll() {
            return null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return new HashSet().iterator();
        }
    };

    private enum NullEntry implements ReferenceEntry<Object, Object> {
        INSTANCE;

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return 0L;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public int getHash() {
            return 0;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public Object getKey() {
            return null;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<Object, Object> getNext() {
            return null;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nonnull
        public ReferenceEntry<Object, Object> getNextInAccessQueue() {
            return this;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nonnull
        public ReferenceEntry<Object, Object> getNextInWriteQueue() {
            return this;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nonnull
        public ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
            return this;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nonnull
        public ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
            return this;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ValueReference<Object, Object> getValueReference() {
            return null;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return 0L;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j) {
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setValueReference(ValueReference<Object, Object> valueReference) {
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j) {
        }
    }

    interface ReferenceEntry<K, V> {
        long getAccessTime();

        int getHash();

        @Nullable
        K getKey();

        @Nullable
        ReferenceEntry<K, V> getNext();

        ReferenceEntry<K, V> getNextInAccessQueue();

        ReferenceEntry<K, V> getNextInWriteQueue();

        ReferenceEntry<K, V> getPreviousInAccessQueue();

        ReferenceEntry<K, V> getPreviousInWriteQueue();

        @Nullable
        ValueReference<K, V> getValueReference();

        long getWriteTime();

        void setAccessTime(long j);

        void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry);

        void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry);

        void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry);

        void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry);

        void setValueReference(ValueReference<K, V> valueReference);

        void setWriteTime(long j);
    }

    enum Strength {
        STRONG { // from class: com.nytimes.android.external.cache.LocalCache.Strength.1
            @Override // com.nytimes.android.external.cache.LocalCache.Strength
            @Nonnull
            <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i) {
                if (i == 1) {
                    return new StrongValueReference(v);
                }
                return new WeightedStrongValueReference(v, i);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.Strength
            @Nonnull
            Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        },
        SOFT { // from class: com.nytimes.android.external.cache.LocalCache.Strength.2
            @Override // com.nytimes.android.external.cache.LocalCache.Strength
            @Nonnull
            <K, V> ValueReference<K, V> referenceValue(@Nonnull Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i) {
                if (i == 1) {
                    return new SoftValueReference(segment.valueReferenceQueue, v, referenceEntry);
                }
                return new WeightedSoftValueReference(segment.valueReferenceQueue, v, referenceEntry, i);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.Strength
            @Nonnull
            Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        },
        WEAK { // from class: com.nytimes.android.external.cache.LocalCache.Strength.3
            @Override // com.nytimes.android.external.cache.LocalCache.Strength
            @Nonnull
            <K, V> ValueReference<K, V> referenceValue(@Nonnull Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i) {
                if (i == 1) {
                    return new WeakValueReference(segment.valueReferenceQueue, v, referenceEntry);
                }
                return new WeightedWeakValueReference(segment.valueReferenceQueue, v, referenceEntry, i);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.Strength
            @Nonnull
            Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };

        @Nonnull
        abstract Equivalence<Object> defaultEquivalence();

        @Nonnull
        abstract <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i);
    }

    interface ValueReference<K, V> {
        @Nonnull
        ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry);

        @Nullable
        V get();

        @Nullable
        ReferenceEntry<K, V> getEntry();

        int getWeight();

        boolean isActive();

        boolean isLoading();

        void notifyNewValue(V v);

        @Nullable
        V waitForValue() throws ExecutionException;
    }

    static int rehash(int i) {
        int i2 = i + ((i << 15) ^ (-12931));
        int i3 = i2 ^ (i2 >>> 10);
        int i4 = i3 + (i3 << 3);
        int i5 = i4 ^ (i4 >>> 6);
        int i6 = i5 + (i5 << 2) + (i5 << 14);
        return i6 ^ (i6 >>> 16);
    }

    public static char saturatedCast(long j) {
        if (j > MsgpackFormat.SIZE_16) {
            return CharCompanionObject.MAX_VALUE;
        }
        if (j < 0) {
            return (char) 0;
        }
        return (char) j;
    }

    LocalCache(@Nonnull CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
        Queue<RemovalNotification<K, V>> concurrentLinkedQueue;
        this.concurrencyLevel = Math.min(cacheBuilder.getConcurrencyLevel(), 65536);
        this.keyStrength = cacheBuilder.getKeyStrength();
        this.valueStrength = cacheBuilder.getValueStrength();
        this.keyEquivalence = cacheBuilder.getKeyEquivalence();
        this.valueEquivalence = cacheBuilder.getValueEquivalence();
        this.maxWeight = cacheBuilder.getMaximumWeight();
        this.weigher = (Weigher<K, V>) cacheBuilder.getWeigher();
        this.expireAfterAccessNanos = cacheBuilder.getExpireAfterAccessNanos();
        this.expireAfterWriteNanos = cacheBuilder.getExpireAfterWriteNanos();
        this.refreshNanos = cacheBuilder.getRefreshNanos();
        CacheBuilder.NullListener nullListener = (RemovalListener<K, V>) cacheBuilder.getRemovalListener();
        this.removalListener = nullListener;
        if (nullListener == CacheBuilder.NullListener.INSTANCE) {
            concurrentLinkedQueue = discardingQueue();
        } else {
            concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        }
        this.removalNotificationQueue = concurrentLinkedQueue;
        this.ticker = cacheBuilder.getTicker(recordsTime());
        this.entryFactory = EntryFactory.getFactory(this.keyStrength, usesAccessEntries(), usesWriteEntries());
        this.defaultLoader = cacheLoader;
        int iMin = Math.min(cacheBuilder.getInitialCapacity(), 1073741824);
        if (evictsBySize() && !customWeigher()) {
            iMin = Math.min(iMin, (int) this.maxWeight);
        }
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        int i4 = 1;
        while (i4 < this.concurrencyLevel && (!evictsBySize() || i4 * 20 <= this.maxWeight)) {
            i3++;
            i4 <<= 1;
        }
        this.segmentShift = 32 - i3;
        this.segmentMask = i4 - 1;
        this.segments = newSegmentArray(i4);
        int i5 = iMin / i4;
        while (i2 < (i5 * i4 < iMin ? i5 + 1 : i5)) {
            i2 <<= 1;
        }
        if (evictsBySize()) {
            long j = this.maxWeight;
            long j2 = i4;
            long j3 = (j / j2) + 1;
            long j4 = j % j2;
            while (i < this.segments.length) {
                if (i == j4) {
                    j3--;
                }
                this.segments[i] = createSegment(i2, j3);
                i++;
            }
            return;
        }
        while (true) {
            Segment<K, V>[] segmentArr = this.segments;
            if (i >= segmentArr.length) {
                return;
            }
            segmentArr[i] = createSegment(i2, -1L);
            i++;
        }
    }

    boolean evictsBySize() {
        return this.maxWeight >= 0;
    }

    boolean customWeigher() {
        return this.weigher != CacheBuilder.OneWeigher.INSTANCE;
    }

    boolean expires() {
        return expiresAfterWrite() || expiresAfterAccess();
    }

    boolean expiresAfterWrite() {
        return this.expireAfterWriteNanos > 0;
    }

    boolean expiresAfterAccess() {
        return this.expireAfterAccessNanos > 0;
    }

    boolean refreshes() {
        return this.refreshNanos > 0;
    }

    boolean usesAccessQueue() {
        return expiresAfterAccess() || evictsBySize();
    }

    boolean usesWriteQueue() {
        return expiresAfterWrite();
    }

    boolean recordsWrite() {
        return expiresAfterWrite() || refreshes();
    }

    boolean recordsAccess() {
        return expiresAfterAccess();
    }

    boolean recordsTime() {
        return recordsWrite() || recordsAccess();
    }

    boolean usesWriteEntries() {
        return usesWriteQueue() || recordsWrite();
    }

    boolean usesAccessEntries() {
        return usesAccessQueue() || recordsAccess();
    }

    boolean usesKeyReferences() {
        return this.keyStrength != Strength.STRONG;
    }

    boolean usesValueReferences() {
        return this.valueStrength != Strength.STRONG;
    }

    enum EntryFactory {
        STRONG { // from class: com.nytimes.android.external.cache.LocalCache.EntryFactory.1
            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            @Nonnull
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry) {
                return new StrongEntry(k, i, referenceEntry);
            }
        },
        STRONG_ACCESS { // from class: com.nytimes.android.external.cache.LocalCache.EntryFactory.2
            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            @Nonnull
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry) {
                return new StrongAccessEntry(k, i, referenceEntry);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, @Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyAccessEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }
        },
        STRONG_WRITE { // from class: com.nytimes.android.external.cache.LocalCache.EntryFactory.3
            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            @Nonnull
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry) {
                return new StrongWriteEntry(k, i, referenceEntry);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, @Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyWriteEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }
        },
        STRONG_ACCESS_WRITE { // from class: com.nytimes.android.external.cache.LocalCache.EntryFactory.4
            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            @Nonnull
            <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry) {
                return new StrongAccessWriteEntry(k, i, referenceEntry);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, @Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyAccessEntry(referenceEntry, referenceEntryCopyEntry);
                copyWriteEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }
        },
        WEAK { // from class: com.nytimes.android.external.cache.LocalCache.EntryFactory.5
            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            @Nonnull
            <K, V> ReferenceEntry<K, V> newEntry(@Nonnull Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry) {
                return new WeakEntry(segment.keyReferenceQueue, k, i, referenceEntry);
            }
        },
        WEAK_ACCESS { // from class: com.nytimes.android.external.cache.LocalCache.EntryFactory.6
            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            @Nonnull
            <K, V> ReferenceEntry<K, V> newEntry(@Nonnull Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry) {
                return new WeakAccessEntry(segment.keyReferenceQueue, k, i, referenceEntry);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, @Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyAccessEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }
        },
        WEAK_WRITE { // from class: com.nytimes.android.external.cache.LocalCache.EntryFactory.7
            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            @Nonnull
            <K, V> ReferenceEntry<K, V> newEntry(@Nonnull Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry) {
                return new WeakWriteEntry(segment.keyReferenceQueue, k, i, referenceEntry);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, @Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyWriteEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }
        },
        WEAK_ACCESS_WRITE { // from class: com.nytimes.android.external.cache.LocalCache.EntryFactory.8
            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            @Nonnull
            <K, V> ReferenceEntry<K, V> newEntry(@Nonnull Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry) {
                return new WeakAccessWriteEntry(segment.keyReferenceQueue, k, i, referenceEntry);
            }

            @Override // com.nytimes.android.external.cache.LocalCache.EntryFactory
            <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, @Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyAccessEntry(referenceEntry, referenceEntryCopyEntry);
                copyWriteEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }
        };

        static final int ACCESS_MASK = 1;
        static final int WEAK_MASK = 4;
        static final int WRITE_MASK = 2;
        static final EntryFactory[] factories;

        @Nonnull
        abstract <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int i, ReferenceEntry<K, V> referenceEntry);

        static {
            EntryFactory entryFactory = WEAK_ACCESS_WRITE;
            factories = new EntryFactory[]{STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, entryFactory};
        }

        /* JADX WARN: Multi-variable type inference failed */
        static EntryFactory getFactory(Strength strength, boolean z, boolean z2) {
            return factories[((strength == Strength.WEAK ? (char) 4 : (char) 0) | (z ? 1 : 0) ? 1 : 0) | (z2 ? 2 : 0)];
        }

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, @Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            return newEntry(segment, referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry2);
        }

        <K, V> void copyAccessEntry(@Nonnull ReferenceEntry<K, V> referenceEntry, @Nonnull ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setAccessTime(referenceEntry.getAccessTime());
            LocalCache.connectAccessOrder(referenceEntry.getPreviousInAccessQueue(), referenceEntry2);
            LocalCache.connectAccessOrder(referenceEntry2, referenceEntry.getNextInAccessQueue());
            LocalCache.nullifyAccessOrder(referenceEntry);
        }

        <K, V> void copyWriteEntry(@Nonnull ReferenceEntry<K, V> referenceEntry, @Nonnull ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setWriteTime(referenceEntry.getWriteTime());
            LocalCache.connectWriteOrder(referenceEntry.getPreviousInWriteQueue(), referenceEntry2);
            LocalCache.connectWriteOrder(referenceEntry2, referenceEntry.getNextInWriteQueue());
            LocalCache.nullifyWriteOrder(referenceEntry);
        }
    }

    @Nullable
    static <K, V> ValueReference<K, V> unset() {
        return (ValueReference<K, V>) UNSET;
    }

    static abstract class AbstractReferenceEntry<K, V> implements ReferenceEntry<K, V> {
        AbstractReferenceEntry() {
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nullable
        public ValueReference<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nullable
        public ReferenceEntry<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public int getHash() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nullable
        public K getKey() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }
    }

    @Nonnull
    static <K, V> ReferenceEntry<K, V> nullEntry() {
        return NullEntry.INSTANCE;
    }

    @Nullable
    static <E> Queue<E> discardingQueue() {
        return (Queue<E>) DISCARDING_QUEUE;
    }

    static class StrongEntry<K, V> extends AbstractReferenceEntry<K, V> {
        final int hash;
        final K key;
        final ReferenceEntry<K, V> next;

        @Nullable
        volatile ValueReference<K, V> valueReference = LocalCache.unset();

        StrongEntry(K k, int i, ReferenceEntry<K, V> referenceEntry) {
            this.key = k;
            this.hash = i;
            this.next = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public K getKey() {
            return this.key;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nullable
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            this.valueReference = valueReference;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }
    }

    static final class StrongAccessEntry<K, V> extends StrongEntry<K, V> {
        volatile long accessTime;
        ReferenceEntry<K, V> nextAccess;
        ReferenceEntry<K, V> previousAccess;

        StrongAccessEntry(K k, int i, ReferenceEntry<K, V> referenceEntry) {
            super(k, i, referenceEntry);
            this.accessTime = Long.MAX_VALUE;
            this.nextAccess = LocalCache.nullEntry();
            this.previousAccess = LocalCache.nullEntry();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j) {
            this.accessTime = j;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }
    }

    static final class StrongWriteEntry<K, V> extends StrongEntry<K, V> {
        ReferenceEntry<K, V> nextWrite;
        ReferenceEntry<K, V> previousWrite;
        volatile long writeTime;

        StrongWriteEntry(K k, int i, ReferenceEntry<K, V> referenceEntry) {
            super(k, i, referenceEntry);
            this.writeTime = Long.MAX_VALUE;
            this.nextWrite = LocalCache.nullEntry();
            this.previousWrite = LocalCache.nullEntry();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j) {
            this.writeTime = j;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }
    }

    static final class StrongAccessWriteEntry<K, V> extends StrongEntry<K, V> {
        volatile long accessTime;
        ReferenceEntry<K, V> nextAccess;
        ReferenceEntry<K, V> nextWrite;
        ReferenceEntry<K, V> previousAccess;
        ReferenceEntry<K, V> previousWrite;
        volatile long writeTime;

        StrongAccessWriteEntry(K k, int i, ReferenceEntry<K, V> referenceEntry) {
            super(k, i, referenceEntry);
            this.accessTime = Long.MAX_VALUE;
            this.nextAccess = LocalCache.nullEntry();
            this.previousAccess = LocalCache.nullEntry();
            this.writeTime = Long.MAX_VALUE;
            this.nextWrite = LocalCache.nullEntry();
            this.previousWrite = LocalCache.nullEntry();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j) {
            this.accessTime = j;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j) {
            this.writeTime = j;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }
    }

    static class WeakEntry<K, V> extends WeakReference<K> implements ReferenceEntry<K, V> {
        final int hash;
        final ReferenceEntry<K, V> next;

        @Nullable
        volatile ValueReference<K, V> valueReference;

        WeakEntry(ReferenceQueue<K> referenceQueue, K k, int i, ReferenceEntry<K, V> referenceEntry) {
            super(k, referenceQueue);
            this.valueReference = LocalCache.unset();
            this.hash = i;
            this.next = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public K getKey() {
            return (K) get();
        }

        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        public void setAccessTime(long j) {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        public void setWriteTime(long j) {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        @Nullable
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            this.valueReference = valueReference;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }
    }

    static final class WeakAccessEntry<K, V> extends WeakEntry<K, V> {
        volatile long accessTime;
        ReferenceEntry<K, V> nextAccess;
        ReferenceEntry<K, V> previousAccess;

        WeakAccessEntry(ReferenceQueue<K> referenceQueue, K k, int i, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, i, referenceEntry);
            this.accessTime = Long.MAX_VALUE;
            this.nextAccess = LocalCache.nullEntry();
            this.previousAccess = LocalCache.nullEntry();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j) {
            this.accessTime = j;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }
    }

    static final class WeakWriteEntry<K, V> extends WeakEntry<K, V> {
        ReferenceEntry<K, V> nextWrite;
        ReferenceEntry<K, V> previousWrite;
        volatile long writeTime;

        WeakWriteEntry(ReferenceQueue<K> referenceQueue, K k, int i, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, i, referenceEntry);
            this.writeTime = Long.MAX_VALUE;
            this.nextWrite = LocalCache.nullEntry();
            this.previousWrite = LocalCache.nullEntry();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j) {
            this.writeTime = j;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }
    }

    static final class WeakAccessWriteEntry<K, V> extends WeakEntry<K, V> {
        volatile long accessTime;
        ReferenceEntry<K, V> nextAccess;
        ReferenceEntry<K, V> nextWrite;
        ReferenceEntry<K, V> previousAccess;
        ReferenceEntry<K, V> previousWrite;
        volatile long writeTime;

        WeakAccessWriteEntry(ReferenceQueue<K> referenceQueue, K k, int i, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k, i, referenceEntry);
            this.accessTime = Long.MAX_VALUE;
            this.nextAccess = LocalCache.nullEntry();
            this.previousAccess = LocalCache.nullEntry();
            this.writeTime = Long.MAX_VALUE;
            this.nextWrite = LocalCache.nullEntry();
            this.previousWrite = LocalCache.nullEntry();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j) {
            this.accessTime = j;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j) {
            this.writeTime = j;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }
    }

    static class WeakValueReference<K, V> extends WeakReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public void notifyNewValue(V v) {
        }

        WeakValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            super(v, referenceQueue);
            this.entry = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nonnull
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeakValueReference(referenceQueue, v, referenceEntry);
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    static class SoftValueReference<K, V> extends SoftReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public void notifyNewValue(V v) {
        }

        SoftValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            super(v, referenceQueue);
            this.entry = referenceEntry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nonnull
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new SoftValueReference(referenceQueue, v, referenceEntry);
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    static class StrongValueReference<K, V> implements ValueReference<K, V> {
        final V referent;

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nonnull
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nullable
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public void notifyNewValue(V v) {
        }

        StrongValueReference(V v) {
            this.referent = v;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public V get() {
            return this.referent;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    static final class WeightedWeakValueReference<K, V> extends WeakValueReference<K, V> {
        final int weight;

        WeightedWeakValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry, int i) {
            super(referenceQueue, v, referenceEntry);
            this.weight = i;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakValueReference, com.nytimes.android.external.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.weight;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.WeakValueReference, com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nonnull
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedWeakValueReference(referenceQueue, v, referenceEntry, this.weight);
        }
    }

    static final class WeightedSoftValueReference<K, V> extends SoftValueReference<K, V> {
        final int weight;

        WeightedSoftValueReference(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry, int i) {
            super(referenceQueue, v, referenceEntry);
            this.weight = i;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.SoftValueReference, com.nytimes.android.external.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.weight;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.SoftValueReference, com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nonnull
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedSoftValueReference(referenceQueue, v, referenceEntry, this.weight);
        }
    }

    static final class WeightedStrongValueReference<K, V> extends StrongValueReference<K, V> {
        final int weight;

        WeightedStrongValueReference(V v, int i) {
            super(v);
            this.weight = i;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.StrongValueReference, com.nytimes.android.external.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.weight;
        }
    }

    @Nonnull
    ReferenceEntry<K, V> newEntry(@Nonnull K k, int i, ReferenceEntry<K, V> referenceEntry) {
        Segment<K, V> segmentSegmentFor = segmentFor(i);
        segmentSegmentFor.lock();
        try {
            return segmentSegmentFor.newEntry(k, i, referenceEntry);
        } finally {
            segmentSegmentFor.unlock();
        }
    }

    @Nullable
    ReferenceEntry<K, V> copyEntry(@Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        return segmentFor(referenceEntry.getHash()).copyEntry(referenceEntry, referenceEntry2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Nonnull
    ValueReference<K, V> newValueReference(@Nonnull ReferenceEntry<K, V> referenceEntry, @Nonnull V v, int i) {
        return this.valueStrength.referenceValue(segmentFor(referenceEntry.getHash()), referenceEntry, Preconditions.checkNotNull(v), i);
    }

    int hash(Object obj) {
        return rehash(this.keyEquivalence.hash(obj));
    }

    void reclaimValue(@Nonnull ValueReference<K, V> valueReference) {
        ReferenceEntry<K, V> entry = valueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
    }

    void reclaimKey(@Nonnull ReferenceEntry<K, V> referenceEntry) {
        int hash = referenceEntry.getHash();
        segmentFor(hash).reclaimKey(referenceEntry, hash);
    }

    boolean isLive(@Nonnull ReferenceEntry<K, V> referenceEntry, long j) {
        return segmentFor(referenceEntry.getHash()).getLiveValue(referenceEntry, j) != null;
    }

    Segment<K, V> segmentFor(int i) {
        return this.segments[(i >>> this.segmentShift) & this.segmentMask];
    }

    @Nonnull
    Segment<K, V> createSegment(int i, long j) {
        return new Segment<>(this, i, j);
    }

    @Nullable
    V getLiveValue(@Nonnull ReferenceEntry<K, V> referenceEntry, long j) {
        V v;
        if (referenceEntry.getKey() == null || (v = referenceEntry.getValueReference().get()) == null || isExpired(referenceEntry, j)) {
            return null;
        }
        return v;
    }

    boolean isExpired(@Nonnull ReferenceEntry<K, V> referenceEntry, long j) {
        Preconditions.checkNotNull(referenceEntry);
        if (!expiresAfterAccess() || j - referenceEntry.getAccessTime() < this.expireAfterAccessNanos) {
            return expiresAfterWrite() && j - referenceEntry.getWriteTime() >= this.expireAfterWriteNanos;
        }
        return true;
    }

    static <K, V> void connectAccessOrder(@Nonnull ReferenceEntry<K, V> referenceEntry, @Nonnull ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextInAccessQueue(referenceEntry2);
        referenceEntry2.setPreviousInAccessQueue(referenceEntry);
    }

    static <K, V> void nullifyAccessOrder(@Nonnull ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry<K, V> referenceEntryNullEntry = nullEntry();
        referenceEntry.setNextInAccessQueue(referenceEntryNullEntry);
        referenceEntry.setPreviousInAccessQueue(referenceEntryNullEntry);
    }

    static <K, V> void connectWriteOrder(@Nonnull ReferenceEntry<K, V> referenceEntry, @Nonnull ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextInWriteQueue(referenceEntry2);
        referenceEntry2.setPreviousInWriteQueue(referenceEntry);
    }

    static <K, V> void nullifyWriteOrder(@Nonnull ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry<K, V> referenceEntryNullEntry = nullEntry();
        referenceEntry.setNextInWriteQueue(referenceEntryNullEntry);
        referenceEntry.setPreviousInWriteQueue(referenceEntryNullEntry);
    }

    void processPendingNotifications() {
        while (true) {
            RemovalNotification<K, V> removalNotificationPoll = this.removalNotificationQueue.poll();
            if (removalNotificationPoll == null) {
                return;
            }
            try {
                this.removalListener.onRemoval(removalNotificationPoll);
            } catch (Throwable th) {
                logger.log(Level.WARNING, "Exception thrown by removal listener", th);
            }
        }
    }

    @Nonnull
    final Segment<K, V>[] newSegmentArray(int i) {
        return new Segment[i];
    }

    static class Segment<K, V> extends ReentrantLock {

        @Nullable
        final Queue<ReferenceEntry<K, V>> accessQueue;
        volatile int count;

        @Nonnull
        final ReferenceQueue<K> keyReferenceQueue;

        @Nonnull
        final LocalCache<K, V> map;
        final long maxSegmentWeight;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();

        @Nullable
        final Queue<ReferenceEntry<K, V>> recencyQueue;
        volatile AtomicReferenceArray<ReferenceEntry<K, V>> table;
        int threshold;
        long totalWeight;

        @Nullable
        final ReferenceQueue<V> valueReferenceQueue;

        @Nullable
        final Queue<ReferenceEntry<K, V>> writeQueue;

        Segment(@Nonnull LocalCache<K, V> localCache, int i, long j) {
            Queue<ReferenceEntry<K, V>> queueDiscardingQueue;
            Queue<ReferenceEntry<K, V>> queueDiscardingQueue2;
            Queue<ReferenceEntry<K, V>> queueDiscardingQueue3;
            this.map = localCache;
            this.maxSegmentWeight = j;
            initTable(newEntryArray(i));
            this.keyReferenceQueue = localCache.usesKeyReferences() ? new ReferenceQueue<>() : null;
            this.valueReferenceQueue = localCache.usesValueReferences() ? new ReferenceQueue<>() : null;
            if (localCache.usesAccessQueue()) {
                queueDiscardingQueue = new ConcurrentLinkedQueue<>();
            } else {
                queueDiscardingQueue = LocalCache.discardingQueue();
            }
            this.recencyQueue = queueDiscardingQueue;
            if (localCache.usesWriteQueue()) {
                queueDiscardingQueue2 = new WriteQueue<>();
            } else {
                queueDiscardingQueue2 = LocalCache.discardingQueue();
            }
            this.writeQueue = queueDiscardingQueue2;
            if (localCache.usesAccessQueue()) {
                queueDiscardingQueue3 = new AccessQueue<>();
            } else {
                queueDiscardingQueue3 = LocalCache.discardingQueue();
            }
            this.accessQueue = queueDiscardingQueue3;
        }

        @Nonnull
        AtomicReferenceArray<ReferenceEntry<K, V>> newEntryArray(int i) {
            return new AtomicReferenceArray<>(i);
        }

        void initTable(@Nonnull AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            if (!this.map.customWeigher()) {
                int i = this.threshold;
                if (i == this.maxSegmentWeight) {
                    this.threshold = i + 1;
                }
            }
            this.table = atomicReferenceArray;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Nonnull
        ReferenceEntry<K, V> newEntry(@Nonnull K k, int i, ReferenceEntry<K, V> referenceEntry) {
            return this.map.entryFactory.newEntry(this, Preconditions.checkNotNull(k), i, referenceEntry);
        }

        @Nullable
        ReferenceEntry<K, V> copyEntry(@Nonnull ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            if (referenceEntry.getKey() == null) {
                return null;
            }
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            V v = valueReference.get();
            if (v == null && valueReference.isActive()) {
                return null;
            }
            ReferenceEntry<K, V> referenceEntryCopyEntry = this.map.entryFactory.copyEntry(this, referenceEntry, referenceEntry2);
            referenceEntryCopyEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, v, referenceEntryCopyEntry));
            return referenceEntryCopyEntry;
        }

        void setValue(@Nonnull ReferenceEntry<K, V> referenceEntry, K k, V v, long j) {
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            int iWeigh = this.map.weigher.weigh(k, v);
            Preconditions.checkState(iWeigh >= 0, "Weights must be non-negative");
            referenceEntry.setValueReference(this.map.valueStrength.referenceValue(this, referenceEntry, v, iWeigh));
            recordWrite(referenceEntry, iWeigh, j);
            valueReference.notifyNewValue(v);
        }

        @Nullable
        V get(@Nonnull K k, int i, @Nonnull CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            ReferenceEntry<K, V> entry;
            Preconditions.checkNotNull(k);
            Preconditions.checkNotNull(cacheLoader);
            try {
                try {
                    if (this.count != 0 && (entry = getEntry(k, i)) != null) {
                        long j = this.map.ticker.read();
                        V liveValue = getLiveValue(entry, j);
                        if (liveValue != null) {
                            recordRead(entry, j);
                            V vScheduleRefresh = scheduleRefresh(entry, k, i, liveValue, j, cacheLoader);
                            postReadCleanup();
                            return vScheduleRefresh;
                        }
                        ValueReference<K, V> valueReference = entry.getValueReference();
                        if (valueReference.isLoading()) {
                            V vWaitForLoadingValue = waitForLoadingValue(entry, k, valueReference);
                            postReadCleanup();
                            return vWaitForLoadingValue;
                        }
                    }
                    V vLockedGetOrLoad = lockedGetOrLoad(k, i, cacheLoader);
                    postReadCleanup();
                    return vLockedGetOrLoad;
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof Error) {
                        throw new ExecutionError((Error) cause);
                    }
                    if (cause instanceof RuntimeException) {
                        throw new UncheckedExecutionException(cause);
                    }
                    throw e;
                }
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        @Nullable
        V lockedGetOrLoad(@Nonnull K k, int i, @Nonnull CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            LoadingValueReference<K, V> loadingValueReference;
            ValueReference<K, V> valueReference;
            V vLoadSync;
            lock();
            try {
                long j = this.map.ticker.read();
                preWriteCleanup(j);
                boolean z = true;
                int i2 = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> referenceEntryNewEntry = referenceEntry;
                while (true) {
                    loadingValueReference = null;
                    if (referenceEntryNewEntry == null) {
                        valueReference = null;
                        break;
                    }
                    K key = referenceEntryNewEntry.getKey();
                    if (referenceEntryNewEntry.getHash() != i || key == null || !this.map.keyEquivalence.equivalent(k, key)) {
                        referenceEntryNewEntry = referenceEntryNewEntry.getNext();
                    } else {
                        valueReference = referenceEntryNewEntry.getValueReference();
                        if (!valueReference.isLoading()) {
                            V v = valueReference.get();
                            if (v == null) {
                                enqueueNotification(key, i, valueReference, RemovalCause.COLLECTED);
                            } else if (this.map.isExpired(referenceEntryNewEntry, j)) {
                                enqueueNotification(key, i, valueReference, RemovalCause.EXPIRED);
                            } else {
                                recordLockedRead(referenceEntryNewEntry, j);
                                unlock();
                                postWriteCleanup();
                                return v;
                            }
                            this.writeQueue.remove(referenceEntryNewEntry);
                            this.accessQueue.remove(referenceEntryNewEntry);
                            this.count = i2;
                            break;
                        }
                        z = false;
                        break;
                    }
                }
                if (z) {
                    loadingValueReference = new LoadingValueReference<>();
                    if (referenceEntryNewEntry == null) {
                        referenceEntryNewEntry = newEntry(k, i, referenceEntry);
                        referenceEntryNewEntry.setValueReference(loadingValueReference);
                        atomicReferenceArray.set(length, referenceEntryNewEntry);
                    } else {
                        referenceEntryNewEntry.setValueReference(loadingValueReference);
                    }
                }
                unlock();
                postWriteCleanup();
                if (z) {
                    synchronized (referenceEntryNewEntry) {
                        vLoadSync = loadSync(k, i, loadingValueReference, cacheLoader);
                    }
                    return vLoadSync;
                }
                return waitForLoadingValue(referenceEntryNewEntry, k, valueReference);
            } catch (Throwable th) {
                unlock();
                postWriteCleanup();
                throw th;
            }
        }

        @Nullable
        V waitForLoadingValue(@Nonnull ReferenceEntry<K, V> referenceEntry, K k, @Nonnull ValueReference<K, V> valueReference) throws ExecutionException {
            if (!valueReference.isLoading()) {
                throw new AssertionError();
            }
            Preconditions.checkState(!Thread.holdsLock(referenceEntry), "Recursive load of: %s", k);
            V vWaitForValue = valueReference.waitForValue();
            if (vWaitForValue == null) {
                throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + k + ".");
            }
            recordRead(referenceEntry, this.map.ticker.read());
            return vWaitForValue;
        }

        @Nullable
        V loadSync(@Nonnull K k, int i, @Nonnull LoadingValueReference<K, V> loadingValueReference, @Nonnull CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            return getAndRecordStats(k, i, loadingValueReference, loadingValueReference.loadFuture(k, cacheLoader));
        }

        @Nullable
        ListenableFuture<V> loadAsync(@Nonnull final K k, final int i, @Nonnull final LoadingValueReference<K, V> loadingValueReference, @Nonnull CacheLoader<? super K, V> cacheLoader) {
            final ListenableFuture<V> listenableFutureLoadFuture = loadingValueReference.loadFuture(k, cacheLoader);
            listenableFutureLoadFuture.addListener(new Runnable() { // from class: com.nytimes.android.external.cache.LocalCache.Segment.1
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Segment.this.getAndRecordStats(k, i, loadingValueReference, listenableFutureLoadFuture);
                    } catch (Throwable th) {
                        LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", th);
                        loadingValueReference.setException(th);
                    }
                }
            }, DirectExecutor.INSTANCE);
            return listenableFutureLoadFuture;
        }

        @Nullable
        V getAndRecordStats(@Nonnull K k, int i, @Nonnull LoadingValueReference<K, V> loadingValueReference, @Nonnull ListenableFuture<V> listenableFuture) throws Throwable {
            V v;
            try {
                v = (V) Uninterruptibles.getUninterruptibly(listenableFuture);
                try {
                    if (v == null) {
                        throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + k + ".");
                    }
                    storeLoadedValue(k, i, loadingValueReference, v);
                    if (v == null) {
                        removeLoadingValue(k, i, loadingValueReference);
                    }
                    return v;
                } catch (Throwable th) {
                    th = th;
                    if (v == null) {
                        removeLoadingValue(k, i, loadingValueReference);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                v = null;
            }
        }

        @Nullable
        V scheduleRefresh(@Nonnull ReferenceEntry<K, V> referenceEntry, @Nonnull K k, int i, V v, long j, @Nonnull CacheLoader<? super K, V> cacheLoader) {
            V vRefresh;
            return (!this.map.refreshes() || j - referenceEntry.getWriteTime() <= this.map.refreshNanos || referenceEntry.getValueReference().isLoading() || (vRefresh = refresh(k, i, cacheLoader, true)) == null) ? v : vRefresh;
        }

        @Nullable
        V refresh(@Nonnull K k, int i, @Nonnull CacheLoader<? super K, V> cacheLoader, boolean z) {
            LoadingValueReference<K, V> loadingValueReferenceInsertLoadingValueReference = insertLoadingValueReference(k, i, z);
            if (loadingValueReferenceInsertLoadingValueReference == null) {
                return null;
            }
            ListenableFuture<V> listenableFutureLoadAsync = loadAsync(k, i, loadingValueReferenceInsertLoadingValueReference, cacheLoader);
            if (listenableFutureLoadAsync.isDone()) {
                try {
                    return (V) Uninterruptibles.getUninterruptibly(listenableFutureLoadAsync);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        @Nullable
        LoadingValueReference<K, V> insertLoadingValueReference(@Nonnull K k, int i, boolean z) {
            lock();
            try {
                long j = this.map.ticker.read();
                preWriteCleanup(j);
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                ReferenceEntry<K, V> referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                for (ReferenceEntry next = referenceEntry; next != null; next = next.getNext()) {
                    Object key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        if (!valueReference.isLoading() && (!z || j - next.getWriteTime() >= this.map.refreshNanos)) {
                            this.modCount++;
                            LoadingValueReference<K, V> loadingValueReference = new LoadingValueReference<>(valueReference);
                            next.setValueReference(loadingValueReference);
                            return loadingValueReference;
                        }
                        return null;
                    }
                }
                this.modCount++;
                LoadingValueReference<K, V> loadingValueReference2 = new LoadingValueReference<>();
                ReferenceEntry<K, V> referenceEntryNewEntry = newEntry(k, i, referenceEntry);
                referenceEntryNewEntry.setValueReference(loadingValueReference2);
                atomicReferenceArray.set(length, referenceEntryNewEntry);
                return loadingValueReference2;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        void drainReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                drainKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                drainValueReferenceQueue();
            }
        }

        void drainKeyReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends K> referencePoll = this.keyReferenceQueue.poll();
                if (referencePoll == null) {
                    return;
                }
                this.map.reclaimKey((ReferenceEntry) referencePoll);
                i++;
            } while (i != 16);
        }

        void drainValueReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends V> referencePoll = this.valueReferenceQueue.poll();
                if (referencePoll == null) {
                    return;
                }
                this.map.reclaimValue((ValueReference) referencePoll);
                i++;
            } while (i != 16);
        }

        void clearReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                clearKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                clearValueReferenceQueue();
            }
        }

        void clearKeyReferenceQueue() {
            while (this.keyReferenceQueue.poll() != null) {
            }
        }

        void clearValueReferenceQueue() {
            while (this.valueReferenceQueue.poll() != null) {
            }
        }

        void recordRead(@Nonnull ReferenceEntry<K, V> referenceEntry, long j) {
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j);
            }
            this.recencyQueue.add(referenceEntry);
        }

        void recordLockedRead(@Nonnull ReferenceEntry<K, V> referenceEntry, long j) {
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j);
            }
            this.accessQueue.add(referenceEntry);
        }

        void recordWrite(@Nonnull ReferenceEntry<K, V> referenceEntry, int i, long j) {
            drainRecencyQueue();
            this.totalWeight += (long) i;
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j);
            }
            if (this.map.recordsWrite()) {
                referenceEntry.setWriteTime(j);
            }
            this.accessQueue.add(referenceEntry);
            this.writeQueue.add(referenceEntry);
        }

        void drainRecencyQueue() {
            while (true) {
                ReferenceEntry<K, V> referenceEntryPoll = this.recencyQueue.poll();
                if (referenceEntryPoll == null) {
                    return;
                }
                if (this.accessQueue.contains(referenceEntryPoll)) {
                    this.accessQueue.add(referenceEntryPoll);
                }
            }
        }

        void tryExpireEntries(long j) {
            if (tryLock()) {
                try {
                    expireEntries(j);
                } finally {
                    unlock();
                }
            }
        }

        void expireEntries(long j) {
            ReferenceEntry<K, V> referenceEntryPeek;
            ReferenceEntry<K, V> referenceEntryPeek2;
            drainRecencyQueue();
            do {
                referenceEntryPeek = this.writeQueue.peek();
                if (referenceEntryPeek == null || !this.map.isExpired(referenceEntryPeek, j)) {
                    do {
                        referenceEntryPeek2 = this.accessQueue.peek();
                        if (referenceEntryPeek2 == null || !this.map.isExpired(referenceEntryPeek2, j)) {
                            return;
                        }
                    } while (removeEntry(referenceEntryPeek2, referenceEntryPeek2.getHash(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (removeEntry(referenceEntryPeek, referenceEntryPeek.getHash(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        void enqueueNotification(@Nonnull ReferenceEntry<K, V> referenceEntry, RemovalCause removalCause) {
            enqueueNotification(referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry.getValueReference(), removalCause);
        }

        void enqueueNotification(K k, int i, @Nonnull ValueReference<K, V> valueReference, RemovalCause removalCause) {
            this.totalWeight -= (long) valueReference.getWeight();
            if (this.map.removalNotificationQueue != LocalCache.DISCARDING_QUEUE) {
                this.map.removalNotificationQueue.offer(RemovalNotification.create(k, valueReference.get(), removalCause));
            }
        }

        void evictEntries(@Nonnull ReferenceEntry<K, V> referenceEntry) {
            if (this.map.evictsBySize()) {
                drainRecencyQueue();
                if (referenceEntry.getValueReference().getWeight() > this.maxSegmentWeight && !removeEntry(referenceEntry, referenceEntry.getHash(), RemovalCause.SIZE)) {
                    throw new AssertionError();
                }
                while (this.totalWeight > this.maxSegmentWeight) {
                    ReferenceEntry<K, V> nextEvictable = getNextEvictable();
                    if (!removeEntry(nextEvictable, nextEvictable.getHash(), RemovalCause.SIZE)) {
                        throw new AssertionError();
                    }
                }
            }
        }

        @Nonnull
        ReferenceEntry<K, V> getNextEvictable() {
            for (ReferenceEntry<K, V> referenceEntry : this.accessQueue) {
                if (referenceEntry.getValueReference().getWeight() > 0) {
                    return referenceEntry;
                }
            }
            throw new AssertionError();
        }

        ReferenceEntry<K, V> getFirst(int i) {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            return atomicReferenceArray.get(i & (atomicReferenceArray.length() - 1));
        }

        @Nullable
        ReferenceEntry<K, V> getEntry(Object obj, int i) {
            for (ReferenceEntry<K, V> first = getFirst(i); first != null; first = first.getNext()) {
                if (first.getHash() == i) {
                    K key = first.getKey();
                    if (key == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(obj, key)) {
                        return first;
                    }
                }
            }
            return null;
        }

        @Nullable
        ReferenceEntry<K, V> getLiveEntry(Object obj, int i, long j) {
            ReferenceEntry<K, V> entry = getEntry(obj, i);
            if (entry == null) {
                return null;
            }
            if (!this.map.isExpired(entry, j)) {
                return entry;
            }
            tryExpireEntries(j);
            return null;
        }

        @Nullable
        V getLiveValue(@Nonnull ReferenceEntry<K, V> referenceEntry, long j) {
            if (referenceEntry.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V v = referenceEntry.getValueReference().get();
            if (v == null) {
                tryDrainReferenceQueues();
                return null;
            }
            if (!this.map.isExpired(referenceEntry, j)) {
                return v;
            }
            tryExpireEntries(j);
            return null;
        }

        @Nullable
        V get(Object obj, int i) {
            try {
                if (this.count != 0) {
                    long j = this.map.ticker.read();
                    ReferenceEntry<K, V> liveEntry = getLiveEntry(obj, i, j);
                    if (liveEntry == null) {
                        return null;
                    }
                    V v = liveEntry.getValueReference().get();
                    if (v != null) {
                        recordRead(liveEntry, j);
                        return scheduleRefresh(liveEntry, liveEntry.getKey(), i, v, j, this.map.defaultLoader);
                    }
                    tryDrainReferenceQueues();
                }
                return null;
            } finally {
                postReadCleanup();
            }
        }

        boolean containsKey(Object obj, int i) {
            try {
                if (this.count == 0) {
                    return false;
                }
                ReferenceEntry<K, V> liveEntry = getLiveEntry(obj, i, this.map.ticker.read());
                if (liveEntry == null) {
                    return false;
                }
                return liveEntry.getValueReference().get() != null;
            } finally {
                postReadCleanup();
            }
        }

        @Nullable
        V put(@Nonnull K k, int i, V v, boolean z) {
            int i2;
            lock();
            try {
                long j = this.map.ticker.read();
                preWriteCleanup(j);
                if (this.count + 1 > this.threshold) {
                    expand();
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        V v2 = valueReference.get();
                        if (v2 != null) {
                            if (z) {
                                recordLockedRead(next, j);
                            } else {
                                this.modCount++;
                                enqueueNotification(k, i, valueReference, RemovalCause.REPLACED);
                                setValue(next, k, v, j);
                                evictEntries(next);
                            }
                            return v2;
                        }
                        this.modCount++;
                        if (valueReference.isActive()) {
                            enqueueNotification(k, i, valueReference, RemovalCause.COLLECTED);
                            setValue(next, k, v, j);
                            i2 = this.count;
                        } else {
                            setValue(next, k, v, j);
                            i2 = this.count + 1;
                        }
                        this.count = i2;
                        evictEntries(next);
                        return null;
                    }
                }
                this.modCount++;
                ReferenceEntry<K, V> referenceEntryNewEntry = newEntry(k, i, referenceEntry);
                setValue(referenceEntryNewEntry, k, v, j);
                atomicReferenceArray.set(length, referenceEntryNewEntry);
                this.count++;
                evictEntries(referenceEntryNewEntry);
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        void expand() {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length >= 1073741824) {
                return;
            }
            int i = this.count;
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArrayNewEntryArray = newEntryArray(length << 1);
            this.threshold = (atomicReferenceArrayNewEntryArray.length() * 3) / 4;
            int length2 = atomicReferenceArrayNewEntryArray.length() - 1;
            for (int i2 = 0; i2 < length; i2++) {
                ReferenceEntry<K, V> next = atomicReferenceArray.get(i2);
                if (next != null) {
                    ReferenceEntry<K, V> next2 = next.getNext();
                    int hash = next.getHash() & length2;
                    if (next2 == null) {
                        atomicReferenceArrayNewEntryArray.set(hash, next);
                    } else {
                        ReferenceEntry<K, V> referenceEntry = next;
                        while (next2 != null) {
                            int hash2 = next2.getHash() & length2;
                            if (hash2 != hash) {
                                referenceEntry = next2;
                                hash = hash2;
                            }
                            next2 = next2.getNext();
                        }
                        atomicReferenceArrayNewEntryArray.set(hash, referenceEntry);
                        while (next != referenceEntry) {
                            int hash3 = next.getHash() & length2;
                            ReferenceEntry<K, V> referenceEntryCopyEntry = copyEntry(next, atomicReferenceArrayNewEntryArray.get(hash3));
                            if (referenceEntryCopyEntry != null) {
                                atomicReferenceArrayNewEntryArray.set(hash3, referenceEntryCopyEntry);
                            } else {
                                removeCollectedEntry(next);
                                i--;
                            }
                            next = next.getNext();
                        }
                    }
                }
            }
            this.table = atomicReferenceArrayNewEntryArray;
            this.count = i;
        }

        boolean replace(K k, int i, V v, V v2) {
            lock();
            try {
                long j = this.map.ticker.read();
                preWriteCleanup(j);
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        V v3 = valueReference.get();
                        if (v3 == null) {
                            if (!valueReference.isActive()) {
                                break;
                            }
                            this.modCount++;
                            ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry, next, key, i, valueReference, RemovalCause.COLLECTED);
                            int i2 = this.count - 1;
                            atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                            this.count = i2;
                            break;
                        }
                        if (this.map.valueEquivalence.equivalent(v, v3)) {
                            this.modCount++;
                            enqueueNotification(k, i, valueReference, RemovalCause.REPLACED);
                            setValue(next, k, v2, j);
                            evictEntries(next);
                            return true;
                        }
                        recordLockedRead(next, j);
                        break;
                    }
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        @Nullable
        V replace(K k, int i, V v) {
            lock();
            try {
                long j = this.map.ticker.read();
                preWriteCleanup(j);
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        V v2 = valueReference.get();
                        if (v2 == null) {
                            if (!valueReference.isActive()) {
                                break;
                            }
                            this.modCount++;
                            ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry, next, key, i, valueReference, RemovalCause.COLLECTED);
                            int i2 = this.count - 1;
                            atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                            this.count = i2;
                            break;
                        }
                        this.modCount++;
                        enqueueNotification(k, i, valueReference, RemovalCause.REPLACED);
                        setValue(next, k, v, j);
                        evictEntries(next);
                        return v2;
                    }
                }
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        @Nullable
        V remove(Object obj, int i) {
            RemovalCause removalCause;
            lock();
            try {
                preWriteCleanup(this.map.ticker.read());
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(obj, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        V v = valueReference.get();
                        if (v != null) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else {
                            if (!valueReference.isActive()) {
                                break;
                            }
                            removalCause = RemovalCause.COLLECTED;
                        }
                        RemovalCause removalCause2 = removalCause;
                        this.modCount++;
                        ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry, next, key, i, valueReference, removalCause2);
                        int i2 = this.count - 1;
                        atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                        this.count = i2;
                        return v;
                    }
                }
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        boolean storeLoadedValue(@Nonnull K k, int i, @Nonnull LoadingValueReference<K, V> loadingValueReference, V v) {
            lock();
            try {
                long j = this.map.ticker.read();
                preWriteCleanup(j);
                int i2 = this.count + 1;
                if (i2 > this.threshold) {
                    expand();
                    i2 = this.count + 1;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        V v2 = valueReference.get();
                        if (loadingValueReference != valueReference && (v2 != null || valueReference == LocalCache.UNSET)) {
                            enqueueNotification(k, i, new WeightedStrongValueReference(v, 0), RemovalCause.REPLACED);
                            return false;
                        }
                        this.modCount++;
                        if (loadingValueReference.isActive()) {
                            enqueueNotification(k, i, loadingValueReference, v2 == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED);
                            i2--;
                        }
                        setValue(next, k, v, j);
                        this.count = i2;
                        evictEntries(next);
                        return true;
                    }
                }
                this.modCount++;
                ReferenceEntry<K, V> referenceEntryNewEntry = newEntry(k, i, referenceEntry);
                setValue(referenceEntryNewEntry, k, v, j);
                atomicReferenceArray.set(length, referenceEntryNewEntry);
                this.count = i2;
                evictEntries(referenceEntryNewEntry);
                return true;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        boolean remove(Object obj, int i, Object obj2) {
            RemovalCause removalCause;
            lock();
            try {
                preWriteCleanup(this.map.ticker.read());
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(obj, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        V v = valueReference.get();
                        if (this.map.valueEquivalence.equivalent(obj2, v)) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else {
                            if (v != null || !valueReference.isActive()) {
                                break;
                                break;
                            }
                            removalCause = RemovalCause.COLLECTED;
                        }
                        this.modCount++;
                        ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry, next, key, i, valueReference, removalCause);
                        int i2 = this.count - 1;
                        atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                        this.count = i2;
                        return removalCause == RemovalCause.EXPLICIT;
                    }
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                    for (int i = 0; i < atomicReferenceArray.length(); i++) {
                        for (ReferenceEntry<K, V> next = atomicReferenceArray.get(i); next != null; next = next.getNext()) {
                            if (next.getValueReference().isActive()) {
                                enqueueNotification(next, RemovalCause.EXPLICIT);
                            }
                        }
                    }
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, null);
                    }
                    clearReferenceQueues();
                    this.writeQueue.clear();
                    this.accessQueue.clear();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                    postWriteCleanup();
                }
            }
        }

        @Nullable
        ReferenceEntry<K, V> removeValueFromChain(ReferenceEntry<K, V> referenceEntry, @Nonnull ReferenceEntry<K, V> referenceEntry2, K k, int i, @Nonnull ValueReference<K, V> valueReference, RemovalCause removalCause) {
            enqueueNotification(k, i, valueReference, removalCause);
            this.writeQueue.remove(referenceEntry2);
            this.accessQueue.remove(referenceEntry2);
            if (valueReference.isLoading()) {
                valueReference.notifyNewValue(null);
                return referenceEntry;
            }
            return removeEntryFromChain(referenceEntry, referenceEntry2);
        }

        @Nullable
        ReferenceEntry<K, V> removeEntryFromChain(ReferenceEntry<K, V> referenceEntry, @Nonnull ReferenceEntry<K, V> referenceEntry2) {
            int i = this.count;
            ReferenceEntry<K, V> next = referenceEntry2.getNext();
            while (referenceEntry != referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = copyEntry(referenceEntry, next);
                if (referenceEntryCopyEntry != null) {
                    next = referenceEntryCopyEntry;
                } else {
                    removeCollectedEntry(referenceEntry);
                    i--;
                }
                referenceEntry = referenceEntry.getNext();
            }
            this.count = i;
            return next;
        }

        void removeCollectedEntry(@Nonnull ReferenceEntry<K, V> referenceEntry) {
            enqueueNotification(referenceEntry, RemovalCause.COLLECTED);
            this.writeQueue.remove(referenceEntry);
            this.accessQueue.remove(referenceEntry);
        }

        boolean reclaimKey(ReferenceEntry<K, V> referenceEntry, int i) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                ReferenceEntry<K, V> referenceEntry2 = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry2; next != null; next = next.getNext()) {
                    if (next == referenceEntry) {
                        this.modCount++;
                        ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry2, next, next.getKey(), i, next.getValueReference(), RemovalCause.COLLECTED);
                        int i2 = this.count - 1;
                        atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                        this.count = i2;
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        boolean reclaimValue(K k, int i, @Nonnull ValueReference<K, V> valueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        if (next.getValueReference() != valueReference) {
                            return false;
                        }
                        this.modCount++;
                        ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry, next, key, i, valueReference, RemovalCause.COLLECTED);
                        int i2 = this.count - 1;
                        atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                        this.count = i2;
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
            }
        }

        boolean removeLoadingValue(K k, int i, @Nonnull LoadingValueReference<K, V> loadingValueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        if (next.getValueReference() != loadingValueReference) {
                            break;
                        }
                        if (loadingValueReference.isActive()) {
                            next.setValueReference(loadingValueReference.getOldValue());
                        } else {
                            atomicReferenceArray.set(length, removeEntryFromChain(referenceEntry, next));
                        }
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        boolean removeEntry(ReferenceEntry<K, V> referenceEntry, int i, RemovalCause removalCause) {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            int length = (atomicReferenceArray.length() - 1) & i;
            ReferenceEntry<K, V> referenceEntry2 = atomicReferenceArray.get(length);
            for (ReferenceEntry<K, V> next = referenceEntry2; next != null; next = next.getNext()) {
                if (next == referenceEntry) {
                    this.modCount++;
                    ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry2, next, next.getKey(), i, next.getValueReference(), removalCause);
                    int i2 = this.count - 1;
                    atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                    this.count = i2;
                    return true;
                }
            }
            return false;
        }

        void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                cleanUp();
            }
        }

        void preWriteCleanup(long j) {
            runLockedCleanup(j);
        }

        void postWriteCleanup() {
            runUnlockedCleanup();
        }

        void cleanUp() {
            runLockedCleanup(this.map.ticker.read());
            runUnlockedCleanup();
        }

        void runLockedCleanup(long j) {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                    expireEntries(j);
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        void runUnlockedCleanup() {
            if (isHeldByCurrentThread()) {
                return;
            }
            this.map.processPendingNotifications();
        }
    }

    static class LoadingValueReference<K, V> implements ValueReference<K, V> {
        final SettableFuture<V> futureValue;

        @Nullable
        volatile ValueReference<K, V> oldValue;
        final Stopwatch stopwatch;

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nonnull
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nullable
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return true;
        }

        public LoadingValueReference() {
            this(LocalCache.unset());
        }

        public LoadingValueReference(ValueReference<K, V> valueReference) {
            this.futureValue = SettableFuture.create();
            this.stopwatch = Stopwatch.createUnstarted();
            this.oldValue = valueReference;
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public boolean isActive() {
            return this.oldValue.isActive();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.oldValue.getWeight();
        }

        public boolean set(V v) {
            return this.futureValue.set(v);
        }

        public boolean setException(Throwable th) {
            return this.futureValue.setException(th);
        }

        @Nonnull
        private ListenableFuture<V> fullyFailedFuture(Throwable th) {
            return Futures.immediateFailedFuture(th);
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public void notifyNewValue(@Nullable V v) {
            if (v != null) {
                set(v);
            } else {
                this.oldValue = LocalCache.unset();
            }
        }

        @Nullable
        public ListenableFuture<V> loadFuture(@Nonnull K k, @Nonnull CacheLoader<? super K, V> cacheLoader) {
            try {
                this.stopwatch.start();
                V v = this.oldValue.get();
                if (v == null) {
                    V vLoad = cacheLoader.load(k);
                    return set(vLoad) ? this.futureValue : Futures.immediateFuture(vLoad);
                }
                ListenableFuture<V> listenableFutureReload = cacheLoader.reload(k, v);
                if (listenableFutureReload == null) {
                    return Futures.immediateFuture(null);
                }
                return Futures.transform(listenableFutureReload, new Function<V, V>() { // from class: com.nytimes.android.external.cache.LocalCache.LoadingValueReference.1
                    @Override // com.nytimes.android.external.cache.Function
                    public V apply(V v2) {
                        LoadingValueReference.this.set(v2);
                        return v2;
                    }
                });
            } catch (Throwable th) {
                ListenableFuture<V> listenableFutureFullyFailedFuture = setException(th) ? this.futureValue : fullyFailedFuture(th);
                if (th instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return listenableFutureFullyFailedFuture;
            }
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        public V waitForValue() throws ExecutionException {
            return (V) Uninterruptibles.getUninterruptibly(this.futureValue);
        }

        @Override // com.nytimes.android.external.cache.LocalCache.ValueReference
        @Nullable
        public V get() {
            return this.oldValue.get();
        }

        @Nullable
        public ValueReference<K, V> getOldValue() {
            return this.oldValue;
        }
    }

    static final class WriteQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>() { // from class: com.nytimes.android.external.cache.LocalCache.WriteQueue.1
            ReferenceEntry<K, V> nextWrite = this;
            ReferenceEntry<K, V> previousWrite = this;

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public long getWriteTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public void setWriteTime(long j) {
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public ReferenceEntry<K, V> getNextInWriteQueue() {
                return this.nextWrite;
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
                this.nextWrite = referenceEntry;
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public ReferenceEntry<K, V> getPreviousInWriteQueue() {
                return this.previousWrite;
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
                this.previousWrite = referenceEntry;
            }
        };

        WriteQueue() {
        }

        @Override // java.util.Queue
        public boolean offer(@Nonnull ReferenceEntry<K, V> referenceEntry) {
            LocalCache.connectWriteOrder(referenceEntry.getPreviousInWriteQueue(), referenceEntry.getNextInWriteQueue());
            LocalCache.connectWriteOrder(this.head.getPreviousInWriteQueue(), referenceEntry);
            LocalCache.connectWriteOrder(referenceEntry, this.head);
            return true;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            if (nextInWriteQueue == this.head) {
                return null;
            }
            return nextInWriteQueue;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            if (nextInWriteQueue == this.head) {
                return null;
            }
            remove(nextInWriteQueue);
            return nextInWriteQueue;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry<K, V> previousInWriteQueue = referenceEntry.getPreviousInWriteQueue();
            ReferenceEntry<K, V> nextInWriteQueue = referenceEntry.getNextInWriteQueue();
            LocalCache.connectWriteOrder(previousInWriteQueue, nextInWriteQueue);
            LocalCache.nullifyWriteOrder(referenceEntry);
            return nextInWriteQueue != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInWriteQueue() != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.head.getNextInWriteQueue() == this.head;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i = 0;
            for (ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue(); nextInWriteQueue != this.head; nextInWriteQueue = nextInWriteQueue.getNextInWriteQueue()) {
                i++;
            }
            return i;
        }

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.head;
                if (nextInWriteQueue != referenceEntry) {
                    ReferenceEntry<K, V> nextInWriteQueue2 = nextInWriteQueue.getNextInWriteQueue();
                    LocalCache.nullifyWriteOrder(nextInWriteQueue);
                    nextInWriteQueue = nextInWriteQueue2;
                } else {
                    referenceEntry.setNextInWriteQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.head;
                    referenceEntry2.setPreviousInWriteQueue(referenceEntry2);
                    return;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        @Nonnull
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) { // from class: com.nytimes.android.external.cache.LocalCache.WriteQueue.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.nytimes.android.external.cache.AbstractSequentialIterator
                @Nullable
                public ReferenceEntry<K, V> computeNext(@Nonnull ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> nextInWriteQueue = referenceEntry.getNextInWriteQueue();
                    if (nextInWriteQueue == WriteQueue.this.head) {
                        return null;
                    }
                    return nextInWriteQueue;
                }
            };
        }
    }

    static final class AccessQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>() { // from class: com.nytimes.android.external.cache.LocalCache.AccessQueue.1
            ReferenceEntry<K, V> nextAccess = this;
            ReferenceEntry<K, V> previousAccess = this;

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public long getAccessTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public void setAccessTime(long j) {
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public ReferenceEntry<K, V> getNextInAccessQueue() {
                return this.nextAccess;
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
                this.nextAccess = referenceEntry;
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public ReferenceEntry<K, V> getPreviousInAccessQueue() {
                return this.previousAccess;
            }

            @Override // com.nytimes.android.external.cache.LocalCache.AbstractReferenceEntry, com.nytimes.android.external.cache.LocalCache.ReferenceEntry
            public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
                this.previousAccess = referenceEntry;
            }
        };

        AccessQueue() {
        }

        @Override // java.util.Queue
        public boolean offer(@Nonnull ReferenceEntry<K, V> referenceEntry) {
            LocalCache.connectAccessOrder(referenceEntry.getPreviousInAccessQueue(), referenceEntry.getNextInAccessQueue());
            LocalCache.connectAccessOrder(this.head.getPreviousInAccessQueue(), referenceEntry);
            LocalCache.connectAccessOrder(referenceEntry, this.head);
            return true;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            if (nextInAccessQueue == this.head) {
                return null;
            }
            return nextInAccessQueue;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            if (nextInAccessQueue == this.head) {
                return null;
            }
            remove(nextInAccessQueue);
            return nextInAccessQueue;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry<K, V> previousInAccessQueue = referenceEntry.getPreviousInAccessQueue();
            ReferenceEntry<K, V> nextInAccessQueue = referenceEntry.getNextInAccessQueue();
            LocalCache.connectAccessOrder(previousInAccessQueue, nextInAccessQueue);
            LocalCache.nullifyAccessOrder(referenceEntry);
            return nextInAccessQueue != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInAccessQueue() != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.head.getNextInAccessQueue() == this.head;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i = 0;
            for (ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue(); nextInAccessQueue != this.head; nextInAccessQueue = nextInAccessQueue.getNextInAccessQueue()) {
                i++;
            }
            return i;
        }

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.head;
                if (nextInAccessQueue != referenceEntry) {
                    ReferenceEntry<K, V> nextInAccessQueue2 = nextInAccessQueue.getNextInAccessQueue();
                    LocalCache.nullifyAccessOrder(nextInAccessQueue);
                    nextInAccessQueue = nextInAccessQueue2;
                } else {
                    referenceEntry.setNextInAccessQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.head;
                    referenceEntry2.setPreviousInAccessQueue(referenceEntry2);
                    return;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        @Nonnull
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) { // from class: com.nytimes.android.external.cache.LocalCache.AccessQueue.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.nytimes.android.external.cache.AbstractSequentialIterator
                @Nullable
                public ReferenceEntry<K, V> computeNext(@Nonnull ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> nextInAccessQueue = referenceEntry.getNextInAccessQueue();
                    if (nextInAccessQueue == AccessQueue.this.head) {
                        return null;
                    }
                    return nextInAccessQueue;
                }
            };
        }
    }

    public void cleanUp() {
        for (Segment<K, V> segment : this.segments) {
            segment.cleanUp();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment<K, V>[] segmentArr = this.segments;
        long j = 0;
        for (int i = 0; i < segmentArr.length; i++) {
            if (segmentArr[i].count != 0) {
                return false;
            }
            j += (long) segmentArr[i].modCount;
        }
        if (j == 0) {
            return true;
        }
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].count != 0) {
                return false;
            }
            j -= (long) segmentArr[i2].modCount;
        }
        return j == 0;
    }

    long longSize() {
        long jMax = 0;
        for (Segment<K, V> segment : this.segments) {
            jMax += (long) Math.max(0, segment.count);
        }
        return jMax;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return saturatedCast(longSize());
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).get(obj, iHash);
    }

    @Nullable
    public V getIfPresent(@Nonnull Object obj) {
        int iHash = hash(Preconditions.checkNotNull(obj));
        return segmentFor(iHash).get(obj, iHash);
    }

    @Nullable
    V get(@Nonnull K k, @Nonnull CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
        int iHash = hash(Preconditions.checkNotNull(k));
        return segmentFor(iHash).get(k, iHash, cacheLoader);
    }

    @Nullable
    V getOrLoad(@Nonnull K k) throws ExecutionException {
        return get(k, this.defaultLoader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    Map<K, V> getAllPresent(@Nonnull Iterable<?> iterable) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : iterable) {
            V v = get(obj);
            if (v != null) {
                linkedHashMap.put(obj, v);
            }
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    Map<K, V> getAll(@Nonnull Iterable<? extends K> iterable) throws ExecutionException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (K k : iterable) {
            Object obj = get(k);
            if (!linkedHashMap.containsKey(k)) {
                linkedHashMap.put(k, obj);
                if (obj == null) {
                    linkedHashSet.add(k);
                }
            }
        }
        if (!linkedHashSet.isEmpty()) {
            try {
                Map mapLoadAll = loadAll(linkedHashSet, this.defaultLoader);
                for (Object obj2 : linkedHashSet) {
                    Object obj3 = mapLoadAll.get(obj2);
                    if (obj3 == null) {
                        throw new CacheLoader.InvalidCacheLoadException("loadAll failed to return a value for " + obj2);
                    }
                    linkedHashMap.put(obj2, obj3);
                }
            } catch (CacheLoader.UnsupportedLoadingOperationException unused) {
                for (Object obj4 : linkedHashSet) {
                    linkedHashMap.put(obj4, get(obj4, this.defaultLoader));
                }
            }
        }
        return linkedHashMap;
    }

    Map<K, V> loadAll(@Nonnull Set<? extends K> set, @Nonnull CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
        Preconditions.checkNotNull(cacheLoader);
        Preconditions.checkNotNull(set);
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted();
        try {
            Map<? super K, V> mapLoadAll = cacheLoader.loadAll(set);
            if (mapLoadAll == null) {
                throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null map from loadAll");
            }
            stopwatchCreateStarted.stop();
            boolean z = false;
            for (Map.Entry<K, V> entry : mapLoadAll.entrySet()) {
                K key = entry.getKey();
                V value = entry.getValue();
                if (key == null || value == null) {
                    z = true;
                } else {
                    put(key, value);
                }
            }
            if (!z) {
                return mapLoadAll;
            }
            throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null keys or values from loadAll");
        } catch (CacheLoader.UnsupportedLoadingOperationException e) {
            throw e;
        } catch (Error e2) {
            throw new ExecutionError(e2);
        } catch (InterruptedException e3) {
            Thread.currentThread().interrupt();
            throw new ExecutionException(e3);
        } catch (RuntimeException e4) {
            throw new UncheckedExecutionException(e4);
        } catch (Exception e5) {
            throw new ExecutionException(e5);
        }
    }

    @Nullable
    ReferenceEntry<K, V> getEntry(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).getEntry(obj, iHash);
    }

    void refresh(@Nonnull K k) {
        int iHash = hash(Preconditions.checkNotNull(k));
        segmentFor(iHash).refresh(k, iHash, this.defaultLoader, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).containsKey(obj, iHash);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    /* JADX WARN: Type inference failed for: r15v3 */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@Nullable Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        long j = this.ticker.read();
        Segment<K, V>[] segmentArr = this.segments;
        long j2 = -1;
        int i = 0;
        while (i < 3) {
            long j3 = 0;
            int length = segmentArr.length;
            for (?? r12 = z; r12 < length; r12++) {
                Segment<K, V> segment = segmentArr[r12];
                int i2 = segment.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = segment.table;
                for (?? r15 = z; r15 < atomicReferenceArray.length(); r15++) {
                    ReferenceEntry<K, V> next = atomicReferenceArray.get(r15);
                    while (next != null) {
                        Segment<K, V>[] segmentArr2 = segmentArr;
                        V liveValue = segment.getLiveValue(next, j);
                        long j4 = j;
                        if (liveValue != null && this.valueEquivalence.equivalent(obj, liveValue)) {
                            return true;
                        }
                        next = next.getNext();
                        segmentArr = segmentArr2;
                        j = j4;
                    }
                }
                j3 += (long) segment.modCount;
                j = j;
                z = false;
            }
            long j5 = j;
            Segment<K, V>[] segmentArr3 = segmentArr;
            if (j3 == j2) {
                return false;
            }
            i++;
            j2 = j3;
            segmentArr = segmentArr3;
            j = j5;
            z = false;
        }
        return z;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V put(@Nonnull K k, @Nonnull V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int iHash = hash(k);
        return segmentFor(iHash).put(k, iHash, v, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @Nullable
    public V putIfAbsent(@Nonnull K k, @Nonnull V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int iHash = hash(k);
        return segmentFor(iHash).put(k, iHash, v, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(@Nonnull Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nullable
    public V remove(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).remove(obj, iHash);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).remove(obj, iHash, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(@Nonnull K k, @Nullable V v, @Nonnull V v2) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v2);
        if (v == null) {
            return false;
        }
        int iHash = hash(k);
        return segmentFor(iHash).replace(k, iHash, v, v2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @Nullable
    public V replace(@Nonnull K k, @Nonnull V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int iHash = hash(k);
        return segmentFor(iHash).replace(k, iHash, v);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Segment<K, V> segment : this.segments) {
            segment.clear();
        }
    }

    void invalidateAll(@Nonnull Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nonnull
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet(this);
        this.keySet = keySet;
        return keySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nonnull
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values = new Values(this);
        this.values = values;
        return values;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @Nonnull
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet(this);
        this.entrySet = entrySet;
        return entrySet;
    }

    abstract class HashIterator<T> implements Iterator<T> {
        Segment<K, V> currentSegment;
        AtomicReferenceArray<ReferenceEntry<K, V>> currentTable;

        @Nullable
        LocalCache<K, V>.WriteThroughEntry lastReturned;

        @Nullable
        ReferenceEntry<K, V> nextEntry;

        @Nullable
        LocalCache<K, V>.WriteThroughEntry nextExternal;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        @Override // java.util.Iterator
        @Nullable
        public abstract T next();

        HashIterator() {
            this.nextSegmentIndex = LocalCache.this.segments.length - 1;
            advance();
        }

        final void advance() {
            this.nextExternal = null;
            if (nextInChain() || nextInTable()) {
                return;
            }
            while (this.nextSegmentIndex >= 0) {
                Segment<K, V>[] segmentArr = LocalCache.this.segments;
                int i = this.nextSegmentIndex;
                this.nextSegmentIndex = i - 1;
                Segment<K, V> segment = segmentArr[i];
                this.currentSegment = segment;
                if (segment.count != 0) {
                    AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.currentSegment.table;
                    this.currentTable = atomicReferenceArray;
                    this.nextTableIndex = atomicReferenceArray.length() - 1;
                    if (nextInTable()) {
                        return;
                    }
                }
            }
        }

        boolean nextInChain() {
            ReferenceEntry<K, V> referenceEntry = this.nextEntry;
            if (referenceEntry == null) {
                return false;
            }
            while (true) {
                this.nextEntry = referenceEntry.getNext();
                ReferenceEntry<K, V> referenceEntry2 = this.nextEntry;
                if (referenceEntry2 == null) {
                    return false;
                }
                if (advanceTo(referenceEntry2)) {
                    return true;
                }
                referenceEntry = this.nextEntry;
            }
        }

        boolean nextInTable() {
            while (true) {
                int i = this.nextTableIndex;
                if (i < 0) {
                    return false;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.currentTable;
                this.nextTableIndex = i - 1;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(i);
                this.nextEntry = referenceEntry;
                if (referenceEntry != null && (advanceTo(referenceEntry) || nextInChain())) {
                    return true;
                }
            }
        }

        boolean advanceTo(@Nonnull ReferenceEntry<K, V> referenceEntry) {
            boolean z;
            try {
                long j = LocalCache.this.ticker.read();
                K key = referenceEntry.getKey();
                Object liveValue = LocalCache.this.getLiveValue(referenceEntry, j);
                if (liveValue != null) {
                    this.nextExternal = new WriteThroughEntry(key, liveValue);
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } finally {
                this.currentSegment.postReadCleanup();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextExternal != null;
        }

        @Nullable
        LocalCache<K, V>.WriteThroughEntry nextEntry() {
            LocalCache<K, V>.WriteThroughEntry writeThroughEntry = this.nextExternal;
            if (writeThroughEntry == null) {
                throw new NoSuchElementException();
            }
            this.lastReturned = writeThroughEntry;
            advance();
            return this.lastReturned;
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.lastReturned != null);
            LocalCache.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    final class KeyIterator extends LocalCache<K, V>.HashIterator<K> {
        KeyIterator() {
            super();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.HashIterator, java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }
    }

    final class ValueIterator extends LocalCache<K, V>.HashIterator<V> {
        ValueIterator() {
            super();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.HashIterator, java.util.Iterator
        public V next() {
            return nextEntry().getValue();
        }
    }

    final class WriteThroughEntry implements Map.Entry<K, V> {
        final K key;
        V value;

        WriteThroughEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        @Override // java.util.Map.Entry
        @Nonnull
        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }

        @Nonnull
        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    final class EntryIterator extends LocalCache<K, V>.HashIterator<Map.Entry<K, V>> {
        EntryIterator() {
            super();
        }

        @Override // com.nytimes.android.external.cache.LocalCache.HashIterator, java.util.Iterator
        @Nullable
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    abstract class AbstractCacheSet<T> extends AbstractSet<T> {
        final ConcurrentMap<?, ?> map;

        AbstractCacheSet(ConcurrentMap<?, ?> concurrentMap) {
            this.map = concurrentMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Nonnull
        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Nonnull
        public <E> E[] toArray(E[] eArr) {
            return (E[]) LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nonnull
    public static <E> ArrayList<E> toArrayList(@Nonnull Collection<E> collection) {
        return new ArrayList<>(collection);
    }

    final class KeySet extends LocalCache<K, V>.AbstractCacheSet<K> {
        KeySet(ConcurrentMap<?, ?> concurrentMap) {
            super(concurrentMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        @Nonnull
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return this.map.remove(obj) != null;
        }
    }

    final class Values extends AbstractCollection<V> {
        private final ConcurrentMap<?, ?> map;

        Values(ConcurrentMap<?, ?> concurrentMap) {
            this.map = concurrentMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        @Nonnull
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <E> E[] toArray(E[] eArr) {
            return (E[]) LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    final class EntrySet extends LocalCache<K, V>.AbstractCacheSet<Map.Entry<K, V>> {
        EntrySet(ConcurrentMap<?, ?> concurrentMap) {
            super(concurrentMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        @Nonnull
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = LocalCache.this.get(key)) != null && LocalCache.this.valueEquivalence.equivalent(entry.getValue(), obj2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && LocalCache.this.remove(key, entry.getValue());
        }
    }

    static class ManualSerializationProxy<K, V> extends ForwardingCache<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        final int concurrencyLevel;
        transient Cache<K, V> delegate;
        final long expireAfterAccessNanos;
        final long expireAfterWriteNanos;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final CacheLoader<? super K, V> loader;
        final long maxWeight;
        final RemovalListener<? super K, ? super V> removalListener;

        @Nullable
        final Ticker ticker;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;
        final Weigher<K, V> weigher;

        ManualSerializationProxy(@Nonnull LocalCache<K, V> localCache) {
            this(localCache.keyStrength, localCache.valueStrength, localCache.keyEquivalence, localCache.valueEquivalence, localCache.expireAfterWriteNanos, localCache.expireAfterAccessNanos, localCache.maxWeight, localCache.weigher, localCache.concurrencyLevel, localCache.removalListener, localCache.ticker, localCache.defaultLoader);
        }

        private ManualSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j, long j2, long j3, Weigher<K, V> weigher, int i, RemovalListener<? super K, ? super V> removalListener, Ticker ticker, CacheLoader<? super K, V> cacheLoader) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.expireAfterWriteNanos = j;
            this.expireAfterAccessNanos = j2;
            this.maxWeight = j3;
            this.weigher = weigher;
            this.concurrencyLevel = i;
            this.removalListener = removalListener;
            this.ticker = (ticker == Ticker.systemTicker() || ticker == CacheBuilder.NULL_TICKER) ? null : ticker;
            this.loader = cacheLoader;
        }

        @Nonnull
        CacheBuilder<K, V> recreateCacheBuilder() {
            CacheBuilder<K, V> cacheBuilder = (CacheBuilder<K, V>) CacheBuilder.newBuilder().setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).valueEquivalence(this.valueEquivalence).concurrencyLevel(this.concurrencyLevel).removalListener(this.removalListener);
            cacheBuilder.strictParsing = false;
            long j = this.expireAfterWriteNanos;
            if (j > 0) {
                cacheBuilder.expireAfterWrite(j, TimeUnit.NANOSECONDS);
            }
            long j2 = this.expireAfterAccessNanos;
            if (j2 > 0) {
                cacheBuilder.expireAfterAccess(j2, TimeUnit.NANOSECONDS);
            }
            if (this.weigher != CacheBuilder.OneWeigher.INSTANCE) {
                cacheBuilder.weigher(this.weigher);
                long j3 = this.maxWeight;
                if (j3 != -1) {
                    cacheBuilder.maximumWeight(j3);
                }
            } else {
                long j4 = this.maxWeight;
                if (j4 != -1) {
                    cacheBuilder.maximumSize(j4);
                }
            }
            Ticker ticker = this.ticker;
            if (ticker != null) {
                cacheBuilder.ticker(ticker);
            }
            return cacheBuilder;
        }

        private void readObject(@Nonnull ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.delegate = (Cache<K, V>) recreateCacheBuilder().build();
        }

        private Object readResolve() {
            return this.delegate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.nytimes.android.external.cache.ForwardingCache, com.nytimes.android.external.cache.ForwardingObject
        public Cache<K, V> delegate() {
            return this.delegate;
        }
    }

    static final class LoadingSerializationProxy<K, V> extends ManualSerializationProxy<K, V> implements LoadingCache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        transient LoadingCache<K, V> autoDelegate;

        LoadingSerializationProxy(@Nonnull LocalCache<K, V> localCache) {
            super(localCache);
        }

        private void readObject(@Nonnull ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.autoDelegate = (LoadingCache<K, V>) recreateCacheBuilder().build(this.loader);
        }

        @Override // com.nytimes.android.external.cache.LoadingCache
        public V get(K k) throws ExecutionException {
            return this.autoDelegate.get(k);
        }

        @Override // com.nytimes.android.external.cache.LoadingCache
        public V getUnchecked(K k) {
            return this.autoDelegate.getUnchecked(k);
        }

        @Override // com.nytimes.android.external.cache.LoadingCache
        public Map<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
            return this.autoDelegate.getAll(iterable);
        }

        @Override // com.nytimes.android.external.cache.LoadingCache, com.nytimes.android.external.cache.Function
        public final V apply(K k) {
            return this.autoDelegate.apply(k);
        }

        @Override // com.nytimes.android.external.cache.LoadingCache
        public void refresh(K k) {
            this.autoDelegate.refresh(k);
        }

        private Object readResolve() {
            return this.autoDelegate;
        }
    }

    static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        final LocalCache<K, V> localCache;

        LocalManualCache(@Nonnull CacheBuilder<? super K, ? super V> cacheBuilder) {
            this(new LocalCache(cacheBuilder, null));
        }

        private LocalManualCache(LocalCache<K, V> localCache) {
            this.localCache = localCache;
        }

        @Override // com.nytimes.android.external.cache.Cache
        @Nullable
        public V getIfPresent(@Nonnull Object obj) {
            return this.localCache.getIfPresent(obj);
        }

        @Override // com.nytimes.android.external.cache.Cache
        @Nullable
        public V get(@Nonnull K k, @Nonnull final Callable<? extends V> callable) throws ExecutionException {
            Preconditions.checkNotNull(callable);
            return this.localCache.get(k, new CacheLoader<Object, V>() { // from class: com.nytimes.android.external.cache.LocalCache.LocalManualCache.1
                @Override // com.nytimes.android.external.cache.CacheLoader
                public V load(Object obj) throws Exception {
                    return (V) callable.call();
                }
            });
        }

        @Override // com.nytimes.android.external.cache.Cache
        @Nonnull
        public Map<K, V> getAllPresent(@Nonnull Iterable<?> iterable) {
            return this.localCache.getAllPresent(iterable);
        }

        @Override // com.nytimes.android.external.cache.Cache
        public void put(@Nonnull K k, @Nonnull V v) {
            this.localCache.put(k, v);
        }

        @Override // com.nytimes.android.external.cache.Cache
        public void putAll(@Nonnull Map<? extends K, ? extends V> map) {
            this.localCache.putAll(map);
        }

        @Override // com.nytimes.android.external.cache.Cache
        public void invalidate(@Nonnull Object obj) {
            Preconditions.checkNotNull(obj);
            this.localCache.remove(obj);
        }

        @Override // com.nytimes.android.external.cache.Cache
        public void invalidateAll(@Nonnull Iterable<?> iterable) {
            this.localCache.invalidateAll(iterable);
        }

        @Override // com.nytimes.android.external.cache.Cache
        public void invalidateAll() {
            this.localCache.clear();
        }

        @Override // com.nytimes.android.external.cache.Cache
        public long size() {
            return this.localCache.longSize();
        }

        @Override // com.nytimes.android.external.cache.Cache
        public ConcurrentMap<K, V> asMap() {
            return this.localCache;
        }

        @Override // com.nytimes.android.external.cache.Cache
        public void cleanUp() {
            this.localCache.cleanUp();
        }

        @Nonnull
        Object writeReplace() {
            return new ManualSerializationProxy(this.localCache);
        }
    }

    static class LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;

        LocalLoadingCache(@Nonnull CacheBuilder<? super K, ? super V> cacheBuilder, @Nonnull CacheLoader<? super K, V> cacheLoader) {
            super();
        }

        @Override // com.nytimes.android.external.cache.LoadingCache
        @Nullable
        public V get(@Nonnull K k) throws ExecutionException {
            return this.localCache.getOrLoad(k);
        }

        @Override // com.nytimes.android.external.cache.LoadingCache
        @Nullable
        public V getUnchecked(@Nonnull K k) {
            try {
                return get(k);
            } catch (ExecutionException e) {
                throw new UncheckedExecutionException(e.getCause());
            }
        }

        @Override // com.nytimes.android.external.cache.LoadingCache
        @Nonnull
        public Map<K, V> getAll(@Nonnull Iterable<? extends K> iterable) throws ExecutionException {
            return this.localCache.getAll(iterable);
        }

        @Override // com.nytimes.android.external.cache.LoadingCache
        public void refresh(@Nonnull K k) {
            this.localCache.refresh(k);
        }

        @Override // com.nytimes.android.external.cache.LoadingCache, com.nytimes.android.external.cache.Function
        @Nullable
        public final V apply(@Nonnull K k) {
            return getUnchecked(k);
        }

        @Override // com.nytimes.android.external.cache.LocalCache.LocalManualCache
        @Nonnull
        Object writeReplace() {
            return new LoadingSerializationProxy(this.localCache);
        }
    }
}
