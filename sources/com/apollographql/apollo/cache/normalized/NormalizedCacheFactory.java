package com.apollographql.apollo.cache.normalized;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NormalizedCacheFactory.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0000J\u0015\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nR\u0018\u0010\u0005\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/NormalizedCacheFactory;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/apollographql/apollo/cache/normalized/NormalizedCache;", "", "()V", "nextFactory", "chain", "factory", "create", "recordFieldAdapter", "Lcom/apollographql/apollo/cache/normalized/RecordFieldJsonAdapter;", "(Lcom/apollographql/apollo/cache/normalized/RecordFieldJsonAdapter;)Lcom/apollographql/apollo/cache/normalized/NormalizedCache;", "createChain", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public abstract class NormalizedCacheFactory<T extends NormalizedCache> {
    private NormalizedCacheFactory<? extends NormalizedCache> nextFactory;

    public abstract T create(RecordFieldJsonAdapter recordFieldAdapter);

    public final NormalizedCache createChain(RecordFieldJsonAdapter recordFieldAdapter) {
        Intrinsics.checkParameterIsNotNull(recordFieldAdapter, "recordFieldAdapter");
        NormalizedCacheFactory<? extends NormalizedCache> normalizedCacheFactory = this.nextFactory;
        if (normalizedCacheFactory != null) {
            return create(recordFieldAdapter).chain(normalizedCacheFactory.createChain(recordFieldAdapter));
        }
        return create(recordFieldAdapter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final NormalizedCacheFactory<T> chain(NormalizedCacheFactory<?> factory) {
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        NormalizedCacheFactory<T> normalizedCacheFactory = this;
        NormalizedCacheFactory normalizedCacheFactory2 = normalizedCacheFactory;
        while (true) {
            NormalizedCacheFactory<? extends NormalizedCache> normalizedCacheFactory3 = normalizedCacheFactory2.nextFactory;
            if (normalizedCacheFactory3 == null) {
                normalizedCacheFactory2.nextFactory = factory;
                return normalizedCacheFactory;
            }
            if (normalizedCacheFactory3 == null) {
                Intrinsics.throwNpe();
            }
            normalizedCacheFactory2 = normalizedCacheFactory3;
        }
    }
}
