package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TeeDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class CacheDataSource implements DataSource {
    public static final int CACHE_IGNORED_REASON_ERROR = 0;
    public static final int CACHE_IGNORED_REASON_UNSET_LENGTH = 1;
    private static final int CACHE_NOT_IGNORED = -1;
    public static final long DEFAULT_MAX_CACHE_FILE_SIZE = 2097152;
    public static final int FLAG_BLOCK_ON_CACHE = 1;
    public static final int FLAG_IGNORE_CACHE_FOR_UNSET_LENGTH_REQUESTS = 4;
    public static final int FLAG_IGNORE_CACHE_ON_ERROR = 2;
    private static final long MIN_READ_BEFORE_CHECKING_CACHE = 102400;
    private Uri actualUri;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final CacheKeyFactory cacheKeyFactory;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    private DataSource currentDataSource;
    private boolean currentDataSpecLengthUnset;
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    private final EventListener eventListener;
    private int flags;
    private int httpMethod;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private String key;
    private long readPosition;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    private Uri uri;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheIgnoredReason {
    }

    public interface EventListener {
        void onCacheIgnored(int i);

        void onCachedBytesRead(long j, long j2);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public CacheDataSource(Cache cache, DataSource dataSource) {
        this(cache, dataSource, 0, 2097152L);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, int i) {
        this(cache, dataSource, i, 2097152L);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, int i, long j) {
        this(cache, dataSource, new FileDataSource(), new CacheDataSink(cache, j), i, null);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, EventListener eventListener) {
        this(cache, dataSource, dataSource2, dataSink, i, eventListener, null);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, EventListener eventListener, CacheKeyFactory cacheKeyFactory) {
        this.cache = cache;
        this.cacheReadDataSource = dataSource2;
        this.cacheKeyFactory = cacheKeyFactory == null ? CacheUtil.DEFAULT_CACHE_KEY_FACTORY : cacheKeyFactory;
        this.blockOnCache = (i & 1) != 0;
        this.ignoreCacheOnError = (i & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i & 4) != 0;
        this.upstreamDataSource = dataSource;
        if (dataSink != null) {
            this.cacheWriteDataSource = new TeeDataSource(dataSource, dataSink);
        } else {
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void addTransferListener(TransferListener transferListener) {
        this.cacheReadDataSource.addTransferListener(transferListener);
        this.upstreamDataSource.addTransferListener(transferListener);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.key = this.cacheKeyFactory.buildCacheKey(dataSpec);
            Uri uri = dataSpec.uri;
            this.uri = uri;
            this.actualUri = getRedirectedUriOrDefault(this.cache, this.key, uri);
            this.httpMethod = dataSpec.httpMethod;
            this.flags = dataSpec.flags;
            this.readPosition = dataSpec.position;
            int iShouldIgnoreCacheForRequest = shouldIgnoreCacheForRequest(dataSpec);
            boolean z = iShouldIgnoreCacheForRequest != -1;
            this.currentRequestIgnoresCache = z;
            if (z) {
                notifyCacheIgnored(iShouldIgnoreCacheForRequest);
            }
            if (dataSpec.length != -1 || this.currentRequestIgnoresCache) {
                this.bytesRemaining = dataSpec.length;
            } else {
                long contentLength = this.cache.getContentLength(this.key);
                this.bytesRemaining = contentLength;
                if (contentLength != -1) {
                    long j = contentLength - dataSpec.position;
                    this.bytesRemaining = j;
                    if (j <= 0) {
                        throw new DataSourceException(0);
                    }
                }
            }
            openNextSource(false);
            return this.bytesRemaining;
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            if (this.readPosition >= this.checkCachePosition) {
                openNextSource(true);
            }
            int i3 = this.currentDataSource.read(bArr, i, i2);
            if (i3 != -1) {
                if (isReadingFromCache()) {
                    this.totalCachedBytesRead += (long) i3;
                }
                long j = i3;
                this.readPosition += j;
                if (this.bytesRemaining != -1) {
                    this.bytesRemaining -= j;
                }
            } else if (this.currentDataSpecLengthUnset) {
                setNoBytesRemainingAndMaybeStoreLength();
            } else {
                if (this.bytesRemaining <= 0) {
                    if (this.bytesRemaining == -1) {
                    }
                }
                closeCurrentSource();
                openNextSource(false);
                return read(bArr, i, i2);
            }
            return i3;
        } catch (IOException e) {
            if (this.currentDataSpecLengthUnset && isCausedByPositionOutOfRange(e)) {
                setNoBytesRemainingAndMaybeStoreLength();
                return -1;
            }
            handleBeforeThrow(e);
            throw e;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.actualUri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        if (isReadingFromUpstream()) {
            return this.upstreamDataSource.getResponseHeaders();
        }
        return Collections.emptyMap();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        this.uri = null;
        this.actualUri = null;
        this.httpMethod = 1;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:40:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:42:0x00e3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:56:0x0109  */
    /* JADX WARN: Code duplicated, block: B:57:0x010b  */
    /* JADX WARN: Code duplicated, block: B:65:0x012d  */
    /* JADX WARN: Code duplicated, block: B:67:0x013e  */
    /* JADX WARN: Code duplicated, block: B:68:0x0144  */
    /* JADX WARN: Code duplicated, block: B:71:0x014d  */
    /* JADX WARN: Code duplicated, block: B:73:0x00e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:? A[RETURN, SYNTHETIC] */
    private void openNextSource(boolean z) throws IOException {
        CacheSpan cacheSpanStartReadWrite;
        long jMin;
        DataSpec dataSpec;
        CacheSpan cacheSpan;
        DataSource dataSource;
        DataSpec dataSpec2;
        long j;
        boolean z2;
        long jOpen;
        ContentMetadataMutations contentMetadataMutations;
        Uri uri;
        if (this.currentRequestIgnoresCache) {
            cacheSpanStartReadWrite = null;
        } else if (this.blockOnCache) {
            try {
                cacheSpanStartReadWrite = this.cache.startReadWrite(this.key, this.readPosition);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
        } else {
            cacheSpanStartReadWrite = this.cache.startReadWriteNonBlocking(this.key, this.readPosition);
        }
        if (cacheSpanStartReadWrite == null) {
            dataSource = this.upstreamDataSource;
            Uri uri2 = this.uri;
            int i = this.httpMethod;
            long j2 = this.readPosition;
            dataSpec2 = new DataSpec(uri2, i, null, j2, j2, this.bytesRemaining, this.key, this.flags);
        } else {
            if (cacheSpanStartReadWrite.isCached) {
                Uri uriFromFile = Uri.fromFile(cacheSpanStartReadWrite.file);
                long j3 = this.readPosition - cacheSpanStartReadWrite.position;
                long jMin2 = cacheSpanStartReadWrite.length - j3;
                long j4 = this.bytesRemaining;
                if (j4 != -1) {
                    jMin2 = Math.min(jMin2, j4);
                }
                dataSpec2 = new DataSpec(uriFromFile, this.readPosition, j3, jMin2, this.key, this.flags);
                dataSource = this.cacheReadDataSource;
            } else {
                if (cacheSpanStartReadWrite.isOpenEnded()) {
                    jMin = this.bytesRemaining;
                } else {
                    jMin = cacheSpanStartReadWrite.length;
                    long j5 = this.bytesRemaining;
                    if (j5 != -1) {
                        jMin = Math.min(jMin, j5);
                    }
                }
                Uri uri3 = this.uri;
                int i2 = this.httpMethod;
                long j6 = this.readPosition;
                DataSpec dataSpec3 = new DataSpec(uri3, i2, null, j6, j6, jMin, this.key, this.flags);
                DataSource dataSource2 = this.cacheWriteDataSource;
                if (dataSource2 != null) {
                    dataSource = dataSource2;
                    cacheSpan = cacheSpanStartReadWrite;
                    dataSpec = dataSpec3;
                } else {
                    DataSource dataSource3 = this.upstreamDataSource;
                    this.cache.releaseHoleSpan(cacheSpanStartReadWrite);
                    dataSpec = dataSpec3;
                    cacheSpan = null;
                    dataSource = dataSource3;
                }
            }
            if (this.currentRequestIgnoresCache && dataSource == this.upstreamDataSource) {
                j = this.readPosition + MIN_READ_BEFORE_CHECKING_CACHE;
            } else {
                j = Long.MAX_VALUE;
            }
            this.checkCachePosition = j;
            if (z) {
                Assertions.checkState(isBypassingCache());
                if (dataSource == this.upstreamDataSource) {
                    return;
                }
                try {
                    closeCurrentSource();
                } catch (Throwable th) {
                    if (cacheSpan.isHoleSpan()) {
                        this.cache.releaseHoleSpan(cacheSpan);
                        throw th;
                    }
                    throw th;
                }
            }
            if (cacheSpan != null && cacheSpan.isHoleSpan()) {
                this.currentHoleSpan = cacheSpan;
            }
            this.currentDataSource = dataSource;
            if (dataSpec.length == -1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.currentDataSpecLengthUnset = z2;
            jOpen = dataSource.open(dataSpec);
            contentMetadataMutations = new ContentMetadataMutations();
            if (this.currentDataSpecLengthUnset && jOpen != -1) {
                this.bytesRemaining = jOpen;
                ContentMetadataInternal.setContentLength(contentMetadataMutations, this.readPosition + jOpen);
            }
            if (isReadingFromUpstream()) {
                uri = this.currentDataSource.getUri();
                this.actualUri = uri;
                if (!this.uri.equals(uri)) {
                    ContentMetadataInternal.setRedirectedUri(contentMetadataMutations, this.actualUri);
                } else {
                    ContentMetadataInternal.removeRedirectedUri(contentMetadataMutations);
                }
            }
            if (isWritingToCache()) {
                this.cache.applyContentMetadataMutations(this.key, contentMetadataMutations);
            }
        }
        cacheSpan = cacheSpanStartReadWrite;
        dataSpec = dataSpec2;
        if (this.currentRequestIgnoresCache) {
            j = Long.MAX_VALUE;
        } else {
            j = Long.MAX_VALUE;
        }
        this.checkCachePosition = j;
        if (z) {
            Assertions.checkState(isBypassingCache());
            if (dataSource == this.upstreamDataSource) {
                return;
            } else {
                closeCurrentSource();
            }
        }
        if (cacheSpan != null) {
            this.currentHoleSpan = cacheSpan;
        }
        this.currentDataSource = dataSource;
        if (dataSpec.length == -1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.currentDataSpecLengthUnset = z2;
        jOpen = dataSource.open(dataSpec);
        contentMetadataMutations = new ContentMetadataMutations();
        if (this.currentDataSpecLengthUnset) {
            this.bytesRemaining = jOpen;
            ContentMetadataInternal.setContentLength(contentMetadataMutations, this.readPosition + jOpen);
        }
        if (isReadingFromUpstream()) {
            uri = this.currentDataSource.getUri();
            this.actualUri = uri;
            if (!this.uri.equals(uri)) {
                ContentMetadataInternal.setRedirectedUri(contentMetadataMutations, this.actualUri);
            } else {
                ContentMetadataInternal.removeRedirectedUri(contentMetadataMutations);
            }
        }
        if (isWritingToCache()) {
            this.cache.applyContentMetadataMutations(this.key, contentMetadataMutations);
        }
    }

    private void setNoBytesRemainingAndMaybeStoreLength() throws IOException {
        this.bytesRemaining = 0L;
        if (isWritingToCache()) {
            this.cache.setContentLength(this.key, this.readPosition);
        }
    }

    private static Uri getRedirectedUriOrDefault(Cache cache, String str, Uri uri) {
        Uri redirectedUri = ContentMetadataInternal.getRedirectedUri(cache.getContentMetadata(str));
        return redirectedUri == null ? uri : redirectedUri;
    }

    private static boolean isCausedByPositionOutOfRange(IOException iOException) {
        for (Throwable cause = iOException; cause != null; cause = cause.getCause()) {
            if ((cause instanceof DataSourceException) && ((DataSourceException) cause).reason == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isReadingFromUpstream() {
        return !isReadingFromCache();
    }

    private boolean isBypassingCache() {
        return this.currentDataSource == this.upstreamDataSource;
    }

    private boolean isReadingFromCache() {
        return this.currentDataSource == this.cacheReadDataSource;
    }

    private boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource == null) {
            return;
        }
        try {
            dataSource.close();
        } finally {
            this.currentDataSource = null;
            this.currentDataSpecLengthUnset = false;
            CacheSpan cacheSpan = this.currentHoleSpan;
            if (cacheSpan != null) {
                this.cache.releaseHoleSpan(cacheSpan);
                this.currentHoleSpan = null;
            }
        }
    }

    private void handleBeforeThrow(IOException iOException) {
        if (isReadingFromCache() || (iOException instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    private int shouldIgnoreCacheForRequest(DataSpec dataSpec) {
        if (this.ignoreCacheOnError && this.seenCacheError) {
            return 0;
        }
        return (this.ignoreCacheForUnsetLengthRequests && dataSpec.length == -1) ? 1 : -1;
    }

    private void notifyCacheIgnored(int i) {
        EventListener eventListener = this.eventListener;
        if (eventListener != null) {
            eventListener.onCacheIgnored(i);
        }
    }

    private void notifyBytesRead() {
        EventListener eventListener = this.eventListener;
        if (eventListener == null || this.totalCachedBytesRead <= 0) {
            return;
        }
        eventListener.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
        this.totalCachedBytesRead = 0L;
    }
}
