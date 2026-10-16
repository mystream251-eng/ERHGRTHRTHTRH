package com.apollographql.apollo.interceptor;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.request.RequestHeaders;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.Executor;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public interface ApolloInterceptor {

    public interface CallBack {
        void onCompleted();

        void onFailure(ApolloException apolloException);

        void onFetch(FetchSourceType fetchSourceType);

        void onResponse(InterceptorResponse interceptorResponse);
    }

    public enum FetchSourceType {
        CACHE,
        NETWORK
    }

    void dispose();

    void interceptAsync(InterceptorRequest interceptorRequest, ApolloInterceptorChain apolloInterceptorChain, Executor executor, CallBack callBack);

    public static final class InterceptorResponse {
        public final Optional<Collection<Record>> cacheRecords;
        public final Optional<Response> httpResponse;
        public final Optional<com.apollographql.apollo.api.Response> parsedResponse;

        public InterceptorResponse(Response response) {
            this(response, null, null);
        }

        public InterceptorResponse(Response response, com.apollographql.apollo.api.Response response2, Collection<Record> collection) {
            this.httpResponse = Optional.fromNullable(response);
            this.parsedResponse = Optional.fromNullable(response2);
            this.cacheRecords = Optional.fromNullable(collection);
        }
    }

    public static final class InterceptorRequest {
        public final boolean autoPersistQueries;
        public final CacheHeaders cacheHeaders;
        public final boolean fetchFromCache;
        public final Operation operation;
        public final Optional<Operation.Data> optimisticUpdates;
        public final RequestHeaders requestHeaders;
        public final boolean sendQueryDocument;
        public final UUID uniqueId = UUID.randomUUID();
        public final boolean useHttpGetMethodForQueries;

        InterceptorRequest(Operation operation, CacheHeaders cacheHeaders, RequestHeaders requestHeaders, Optional<Operation.Data> optional, boolean z, boolean z2, boolean z3, boolean z4) {
            this.operation = operation;
            this.cacheHeaders = cacheHeaders;
            this.requestHeaders = requestHeaders;
            this.optimisticUpdates = optional;
            this.fetchFromCache = z;
            this.sendQueryDocument = z2;
            this.useHttpGetMethodForQueries = z3;
            this.autoPersistQueries = z4;
        }

        public Builder toBuilder() {
            return new Builder(this.operation).cacheHeaders(this.cacheHeaders).requestHeaders(this.requestHeaders).fetchFromCache(this.fetchFromCache).optimisticUpdates(this.optimisticUpdates.orNull()).sendQueryDocument(this.sendQueryDocument).useHttpGetMethodForQueries(this.useHttpGetMethodForQueries).autoPersistQueries(this.autoPersistQueries);
        }

        public static Builder builder(Operation operation) {
            return new Builder(operation);
        }

        public static final class Builder {
            private boolean autoPersistQueries;
            private boolean fetchFromCache;
            private final Operation operation;
            private boolean useHttpGetMethodForQueries;
            private CacheHeaders cacheHeaders = CacheHeaders.NONE;
            private RequestHeaders requestHeaders = RequestHeaders.NONE;
            private Optional<Operation.Data> optimisticUpdates = Optional.absent();
            private boolean sendQueryDocument = true;

            Builder(Operation operation) {
                this.operation = (Operation) Utils.checkNotNull(operation, "operation == null");
            }

            public Builder cacheHeaders(CacheHeaders cacheHeaders) {
                this.cacheHeaders = (CacheHeaders) Utils.checkNotNull(cacheHeaders, "cacheHeaders == null");
                return this;
            }

            public Builder requestHeaders(RequestHeaders requestHeaders) {
                this.requestHeaders = (RequestHeaders) Utils.checkNotNull(requestHeaders, "requestHeaders == null");
                return this;
            }

            public Builder fetchFromCache(boolean z) {
                this.fetchFromCache = z;
                return this;
            }

            public Builder optimisticUpdates(Operation.Data data) {
                this.optimisticUpdates = Optional.fromNullable(data);
                return this;
            }

            public Builder optimisticUpdates(Optional<Operation.Data> optional) {
                this.optimisticUpdates = (Optional) Utils.checkNotNull(optional, "optimisticUpdates == null");
                return this;
            }

            public Builder sendQueryDocument(boolean z) {
                this.sendQueryDocument = z;
                return this;
            }

            public Builder useHttpGetMethodForQueries(boolean z) {
                this.useHttpGetMethodForQueries = z;
                return this;
            }

            public Builder autoPersistQueries(boolean z) {
                this.autoPersistQueries = z;
                return this;
            }

            public InterceptorRequest build() {
                return new InterceptorRequest(this.operation, this.cacheHeaders, this.requestHeaders, this.optimisticUpdates, this.fetchFromCache, this.sendQueryDocument, this.useHttpGetMethodForQueries, this.autoPersistQueries);
            }
        }
    }
}
