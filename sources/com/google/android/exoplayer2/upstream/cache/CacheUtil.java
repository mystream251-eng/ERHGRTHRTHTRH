package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class CacheUtil {
    public static final int DEFAULT_BUFFER_SIZE_BYTES = 131072;
    public static final CacheKeyFactory DEFAULT_CACHE_KEY_FACTORY = new CacheKeyFactory() { // from class: com.google.android.exoplayer2.upstream.cache.-$$Lambda$u97poD-IIwob7OPYcVJkh9jokx0
        @Override // com.google.android.exoplayer2.upstream.cache.CacheKeyFactory
        public final String buildCacheKey(DataSpec dataSpec) {
            return CacheUtil.getKey(dataSpec);
        }
    };

    public static class CachingCounters {
        public volatile long alreadyCachedBytes;
        public volatile long contentLength = -1;
        public volatile long newlyCachedBytes;

        public long totalCachedBytes() {
            return this.alreadyCachedBytes + this.newlyCachedBytes;
        }
    }

    public static String generateKey(Uri uri) {
        return uri.toString();
    }

    public static String getKey(DataSpec dataSpec) {
        return dataSpec.key != null ? dataSpec.key : generateKey(dataSpec.uri);
    }

    public static void getCached(DataSpec dataSpec, Cache cache, CachingCounters cachingCounters) {
        String key = getKey(dataSpec);
        long j = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length != -1 ? dataSpec.length : cache.getContentLength(key);
        cachingCounters.contentLength = contentLength;
        cachingCounters.alreadyCachedBytes = 0L;
        cachingCounters.newlyCachedBytes = 0L;
        long j2 = j;
        long j3 = contentLength;
        while (j3 != 0) {
            long cachedLength = cache.getCachedLength(key, j2, j3 != -1 ? j3 : Long.MAX_VALUE);
            if (cachedLength > 0) {
                cachingCounters.alreadyCachedBytes += cachedLength;
            } else {
                cachedLength = -cachedLength;
                if (cachedLength == Long.MAX_VALUE) {
                    return;
                }
            }
            j2 += cachedLength;
            if (j3 == -1) {
                cachedLength = 0;
            }
            j3 -= cachedLength;
        }
    }

    public static void cache(DataSpec dataSpec, Cache cache, DataSource dataSource, CachingCounters cachingCounters, AtomicBoolean atomicBoolean) throws InterruptedException, IOException {
        cache(dataSpec, cache, new CacheDataSource(cache, dataSource), new byte[131072], null, 0, cachingCounters, atomicBoolean, false);
    }

    public static void cache(DataSpec dataSpec, Cache cache, CacheDataSource cacheDataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, CachingCounters cachingCounters, AtomicBoolean atomicBoolean, boolean z) throws InterruptedException, IOException {
        CachingCounters cachingCounters2 = cachingCounters;
        Assertions.checkNotNull(cacheDataSource);
        Assertions.checkNotNull(bArr);
        if (cachingCounters2 != null) {
            getCached(dataSpec, cache, cachingCounters2);
        } else {
            cachingCounters2 = new CachingCounters();
        }
        CachingCounters cachingCounters3 = cachingCounters2;
        String key = getKey(dataSpec);
        long j = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length != -1 ? dataSpec.length : cache.getContentLength(key);
        while (true) {
            long j2 = 0;
            if (contentLength == 0) {
                return;
            }
            throwExceptionIfInterruptedOrCancelled(atomicBoolean);
            long cachedLength = cache.getCachedLength(key, j, contentLength != -1 ? contentLength : Long.MAX_VALUE);
            if (cachedLength <= 0) {
                long j3 = -cachedLength;
                if (readAndDiscard(dataSpec, j, j3, cacheDataSource, bArr, priorityTaskManager, i, cachingCounters3, atomicBoolean) < j3) {
                    if (z && contentLength != -1) {
                        throw new EOFException();
                    }
                    return;
                }
                cachedLength = j3;
            }
            j += cachedLength;
            if (contentLength != -1) {
                j2 = cachedLength;
            }
            contentLength -= j2;
        }
    }

    private static long readAndDiscard(DataSpec dataSpec, long j, long j2, DataSource dataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, CachingCounters cachingCounters, AtomicBoolean atomicBoolean) throws InterruptedException, IOException {
        DataSpec dataSpec2;
        long j3;
        DataSpec dataSpec3 = dataSpec;
        loop0: while (true) {
            if (priorityTaskManager != null) {
                priorityTaskManager.proceed(i);
            }
            try {
                try {
                    throwExceptionIfInterruptedOrCancelled(atomicBoolean);
                    Uri uri = dataSpec3.uri;
                    int i2 = dataSpec3.httpMethod;
                    byte[] bArr2 = dataSpec3.httpBody;
                    long j4 = (dataSpec3.position + j) - dataSpec3.absoluteStreamPosition;
                    String str = dataSpec3.key;
                    int i3 = dataSpec3.flags | 2;
                    DataSpec dataSpec4 = dataSpec3;
                    dataSpec3 = dataSpec2;
                    try {
                        dataSpec2 = new DataSpec(uri, i2, bArr2, j, j4, -1L, str, i3);
                        try {
                            long jOpen = dataSource.open(dataSpec3);
                            if (cachingCounters.contentLength == -1 && jOpen != -1) {
                                cachingCounters.contentLength = dataSpec3.absoluteStreamPosition + jOpen;
                            }
                            j3 = 0;
                            while (j3 != j2) {
                                throwExceptionIfInterruptedOrCancelled(atomicBoolean);
                                int i4 = dataSource.read(bArr, 0, j2 != -1 ? (int) Math.min(bArr.length, j2 - j3) : bArr.length);
                                if (i4 == -1) {
                                    if (cachingCounters.contentLength != -1) {
                                        break;
                                    }
                                    cachingCounters.contentLength = dataSpec3.absoluteStreamPosition + j3;
                                    break;
                                }
                                long j5 = i4;
                                j3 += j5;
                                cachingCounters.newlyCachedBytes += j5;
                            }
                            break loop0;
                        } catch (PriorityTaskManager.PriorityTooLowException unused) {
                            continue;
                        }
                    } catch (PriorityTaskManager.PriorityTooLowException unused2) {
                        dataSpec3 = dataSpec4;
                    }
                } catch (Throwable th) {
                    Util.closeQuietly(dataSource);
                    throw th;
                }
            } catch (PriorityTaskManager.PriorityTooLowException unused3) {
            }
            Util.closeQuietly(dataSource);
        }
        Util.closeQuietly(dataSource);
        return j3;
    }

    public static void remove(Cache cache, String str) {
        Iterator<CacheSpan> it = cache.getCachedSpans(str).iterator();
        while (it.hasNext()) {
            try {
                cache.removeSpan(it.next());
            } catch (Cache.CacheException unused) {
            }
        }
    }

    private static void throwExceptionIfInterruptedOrCancelled(AtomicBoolean atomicBoolean) throws InterruptedException {
        if (Thread.interrupted() || (atomicBoolean != null && atomicBoolean.get())) {
            throw new InterruptedException();
        }
    }

    private CacheUtil() {
    }
}
