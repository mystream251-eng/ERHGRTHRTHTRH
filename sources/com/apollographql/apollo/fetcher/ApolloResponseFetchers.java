package com.apollographql.apollo.fetcher;

import com.apollographql.apollo.internal.fetcher.CacheAndNetworkFetcher;
import com.apollographql.apollo.internal.fetcher.CacheFirstFetcher;
import com.apollographql.apollo.internal.fetcher.CacheOnlyFetcher;
import com.apollographql.apollo.internal.fetcher.NetworkFirstFetcher;
import com.apollographql.apollo.internal.fetcher.NetworkOnlyFetcher;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloResponseFetchers {
    public static final ResponseFetcher CACHE_ONLY = new CacheOnlyFetcher();
    public static final ResponseFetcher NETWORK_ONLY = new NetworkOnlyFetcher();
    public static final ResponseFetcher CACHE_FIRST = new CacheFirstFetcher();
    public static final ResponseFetcher NETWORK_FIRST = new NetworkFirstFetcher();
    public static final ResponseFetcher CACHE_AND_NETWORK = new CacheAndNetworkFetcher();
}
