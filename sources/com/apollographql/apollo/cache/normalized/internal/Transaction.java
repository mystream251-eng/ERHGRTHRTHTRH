package com.apollographql.apollo.cache.normalized.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX INFO: compiled from: Transaction.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u0017\u0010\u0004\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/Transaction;", ExifInterface.GPS_DIRECTION_TRUE, "R", "", "execute", "cache", "(Ljava/lang/Object;)Ljava/lang/Object;", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public interface Transaction<T, R> {
    R execute(T cache);
}
