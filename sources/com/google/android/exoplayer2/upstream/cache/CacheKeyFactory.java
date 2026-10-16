package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSpec;

/* JADX INFO: loaded from: classes2.dex */
public interface CacheKeyFactory {
    String buildCacheKey(DataSpec dataSpec);
}
