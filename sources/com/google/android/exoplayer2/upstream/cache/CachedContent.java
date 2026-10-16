package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.util.Assertions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
final class CachedContent {
    private static final int VERSION_MAX = Integer.MAX_VALUE;
    private static final int VERSION_METADATA_INTRODUCED = 2;
    public final int id;
    public final String key;
    private boolean locked;
    private DefaultContentMetadata metadata = DefaultContentMetadata.EMPTY;
    private final TreeSet<SimpleCacheSpan> cachedSpans = new TreeSet<>();

    public static CachedContent readFromStream(int i, DataInputStream dataInputStream) throws IOException {
        CachedContent cachedContent = new CachedContent(dataInputStream.readInt(), dataInputStream.readUTF());
        if (i < 2) {
            long j = dataInputStream.readLong();
            ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
            ContentMetadataInternal.setContentLength(contentMetadataMutations, j);
            cachedContent.applyMetadataMutations(contentMetadataMutations);
        } else {
            cachedContent.metadata = DefaultContentMetadata.readFromStream(dataInputStream);
        }
        return cachedContent;
    }

    public CachedContent(int i, String str) {
        this.id = i;
        this.key = str;
    }

    public void writeToStream(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.id);
        dataOutputStream.writeUTF(this.key);
        this.metadata.writeToStream(dataOutputStream);
    }

    public ContentMetadata getMetadata() {
        return this.metadata;
    }

    public boolean applyMetadataMutations(ContentMetadataMutations contentMetadataMutations) {
        DefaultContentMetadata defaultContentMetadata = this.metadata;
        DefaultContentMetadata defaultContentMetadataCopyWithMutationsApplied = defaultContentMetadata.copyWithMutationsApplied(contentMetadataMutations);
        this.metadata = defaultContentMetadataCopyWithMutationsApplied;
        return !defaultContentMetadataCopyWithMutationsApplied.equals(defaultContentMetadata);
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean z) {
        this.locked = z;
    }

    public void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.cachedSpans.add(simpleCacheSpan);
    }

    public TreeSet<SimpleCacheSpan> getSpans() {
        return this.cachedSpans;
    }

    public SimpleCacheSpan getSpan(long j) {
        SimpleCacheSpan simpleCacheSpanCreateLookup = SimpleCacheSpan.createLookup(this.key, j);
        SimpleCacheSpan simpleCacheSpanFloor = this.cachedSpans.floor(simpleCacheSpanCreateLookup);
        if (simpleCacheSpanFloor != null && simpleCacheSpanFloor.position + simpleCacheSpanFloor.length > j) {
            return simpleCacheSpanFloor;
        }
        SimpleCacheSpan simpleCacheSpanCeiling = this.cachedSpans.ceiling(simpleCacheSpanCreateLookup);
        if (simpleCacheSpanCeiling == null) {
            return SimpleCacheSpan.createOpenHole(this.key, j);
        }
        return SimpleCacheSpan.createClosedHole(this.key, j, simpleCacheSpanCeiling.position - j);
    }

    public long getCachedBytesLength(long j, long j2) {
        SimpleCacheSpan span = getSpan(j);
        if (span.isHoleSpan()) {
            return -Math.min(span.isOpenEnded() ? Long.MAX_VALUE : span.length, j2);
        }
        long j3 = j + j2;
        long jMax = span.position + span.length;
        if (jMax < j3) {
            for (SimpleCacheSpan simpleCacheSpan : this.cachedSpans.tailSet(span, false)) {
                if (simpleCacheSpan.position > jMax) {
                    break;
                }
                jMax = Math.max(jMax, simpleCacheSpan.position + simpleCacheSpan.length);
                if (jMax >= j3) {
                    break;
                }
            }
        }
        return Math.min(jMax - j, j2);
    }

    public SimpleCacheSpan touch(SimpleCacheSpan simpleCacheSpan) throws Cache.CacheException {
        SimpleCacheSpan simpleCacheSpanCopyWithUpdatedLastAccessTime = simpleCacheSpan.copyWithUpdatedLastAccessTime(this.id);
        if (!simpleCacheSpan.file.renameTo(simpleCacheSpanCopyWithUpdatedLastAccessTime.file)) {
            throw new Cache.CacheException("Renaming of " + simpleCacheSpan.file + " to " + simpleCacheSpanCopyWithUpdatedLastAccessTime.file + " failed.");
        }
        Assertions.checkState(this.cachedSpans.remove(simpleCacheSpan));
        this.cachedSpans.add(simpleCacheSpanCopyWithUpdatedLastAccessTime);
        return simpleCacheSpanCopyWithUpdatedLastAccessTime;
    }

    public boolean isEmpty() {
        return this.cachedSpans.isEmpty();
    }

    public boolean removeSpan(CacheSpan cacheSpan) {
        if (!this.cachedSpans.remove(cacheSpan)) {
            return false;
        }
        cacheSpan.file.delete();
        return true;
    }

    public int headerHashCode(int i) {
        int i2;
        int iHashCode;
        int iHashCode2 = (this.id * 31) + this.key.hashCode();
        if (i < 2) {
            long contentLength = ContentMetadataInternal.getContentLength(this.metadata);
            i2 = iHashCode2 * 31;
            iHashCode = (int) (contentLength ^ (contentLength >>> 32));
        } else {
            i2 = iHashCode2 * 31;
            iHashCode = this.metadata.hashCode();
        }
        return i2 + iHashCode;
    }

    public int hashCode() {
        return (headerHashCode(Integer.MAX_VALUE) * 31) + this.cachedSpans.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CachedContent cachedContent = (CachedContent) obj;
        return this.id == cachedContent.id && this.key.equals(cachedContent.key) && this.cachedSpans.equals(cachedContent.cachedSpans) && this.metadata.equals(cachedContent.metadata);
    }
}
