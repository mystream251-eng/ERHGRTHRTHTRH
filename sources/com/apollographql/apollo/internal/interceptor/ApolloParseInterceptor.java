package com.apollographql.apollo.internal.interceptor;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.cache.http.HttpCache;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloParseException;
import com.apollographql.apollo.http.OkHttpExecutionContext;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.response.OperationResponseParser;
import java.io.Closeable;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloParseInterceptor implements ApolloInterceptor {
    volatile boolean disposed;
    private final HttpCache httpCache;
    private final ApolloLogger logger;
    private final ResponseNormalizer<Map<String, Object>> normalizer;
    private final ResponseFieldMapper responseFieldMapper;
    private final ScalarTypeAdapters scalarTypeAdapters;

    public ApolloParseInterceptor(HttpCache httpCache, ResponseNormalizer<Map<String, Object>> responseNormalizer, ResponseFieldMapper responseFieldMapper, ScalarTypeAdapters scalarTypeAdapters, ApolloLogger apolloLogger) {
        this.httpCache = httpCache;
        this.normalizer = responseNormalizer;
        this.responseFieldMapper = responseFieldMapper;
        this.scalarTypeAdapters = scalarTypeAdapters;
        this.logger = apolloLogger;
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
    public void interceptAsync(final ApolloInterceptor.InterceptorRequest interceptorRequest, ApolloInterceptorChain apolloInterceptorChain, Executor executor, final ApolloInterceptor.CallBack callBack) {
        if (this.disposed) {
            return;
        }
        apolloInterceptorChain.proceedAsync(interceptorRequest, executor, new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.internal.interceptor.ApolloParseInterceptor.1
            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onCompleted() {
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) throws Throwable {
                try {
                    if (ApolloParseInterceptor.this.disposed) {
                        return;
                    }
                    callBack.onResponse(ApolloParseInterceptor.this.parse(interceptorRequest.operation, interceptorResponse.httpResponse.get()));
                    callBack.onCompleted();
                } catch (ApolloException e) {
                    onFailure(e);
                }
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onFailure(ApolloException apolloException) {
                if (ApolloParseInterceptor.this.disposed) {
                    return;
                }
                callBack.onFailure(apolloException);
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onFetch(ApolloInterceptor.FetchSourceType fetchSourceType) {
                callBack.onFetch(fetchSourceType);
            }
        });
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
    public void dispose() {
        this.disposed = true;
    }

    ApolloInterceptor.InterceptorResponse parse(Operation operation, Response response) throws Throwable {
        String strHeader = response.request().header("X-APOLLO-CACHE-KEY");
        if (response.isSuccessful()) {
            try {
                OperationResponseParser operationResponseParser = new OperationResponseParser(operation, this.responseFieldMapper, this.scalarTypeAdapters, this.normalizer);
                OkHttpExecutionContext okHttpExecutionContext = new OkHttpExecutionContext(response);
                com.apollographql.apollo.api.Response response2 = operationResponseParser.parse(response.body().source());
                com.apollographql.apollo.api.Response responseBuild = response2.toBuilder().fromCache(response.cacheResponse() != null).executionContext(response2.getExecutionContext().plus(okHttpExecutionContext)).build();
                if (responseBuild.hasErrors() && this.httpCache != null) {
                    this.httpCache.removeQuietly(strHeader);
                }
                return new ApolloInterceptor.InterceptorResponse(response, responseBuild, this.normalizer.records());
            } catch (Exception e) {
                this.logger.e(e, "Failed to parse network response for operation: %s", operation);
                closeQuietly(response);
                HttpCache httpCache = this.httpCache;
                if (httpCache != null) {
                    httpCache.removeQuietly(strHeader);
                }
                throw new ApolloParseException("Failed to parse http response", e);
            }
        }
        this.logger.e("Failed to parse network response: %s", response);
        throw new ApolloHttpException(response);
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }
}
