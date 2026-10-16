package com.apollographql.apollo.interceptor;

import com.apollographql.apollo.api.Error;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Function;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.exception.ApolloException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class ApolloAutoPersistedOperationInterceptor implements ApolloInterceptor {
    private static final String PROTOCOL_NEGOTIATION_ERROR_NOT_SUPPORTED = "PersistedQueryNotSupported";
    private static final String PROTOCOL_NEGOTIATION_ERROR_QUERY_NOT_FOUND = "PersistedQueryNotFound";
    private volatile boolean disposed;
    private final ApolloLogger logger;
    final boolean useHttpGetMethodForPersistedOperations;

    public ApolloAutoPersistedOperationInterceptor(ApolloLogger apolloLogger, boolean z) {
        this.logger = apolloLogger;
        this.useHttpGetMethodForPersistedOperations = z;
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
    public void interceptAsync(final ApolloInterceptor.InterceptorRequest interceptorRequest, final ApolloInterceptorChain apolloInterceptorChain, final Executor executor, final ApolloInterceptor.CallBack callBack) {
        apolloInterceptorChain.proceedAsync(interceptorRequest.toBuilder().sendQueryDocument(false).autoPersistQueries(true).useHttpGetMethodForQueries(interceptorRequest.useHttpGetMethodForQueries || this.useHttpGetMethodForPersistedOperations).build(), executor, new ApolloInterceptor.CallBack() { // from class: com.apollographql.apollo.interceptor.ApolloAutoPersistedOperationInterceptor.1
            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onCompleted() {
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onResponse(ApolloInterceptor.InterceptorResponse interceptorResponse) {
                if (ApolloAutoPersistedOperationInterceptor.this.disposed) {
                    return;
                }
                Optional<ApolloInterceptor.InterceptorRequest> optionalHandleProtocolNegotiation = ApolloAutoPersistedOperationInterceptor.this.handleProtocolNegotiation(interceptorRequest, interceptorResponse);
                if (optionalHandleProtocolNegotiation.isPresent()) {
                    apolloInterceptorChain.proceedAsync(optionalHandleProtocolNegotiation.get(), executor, callBack);
                } else {
                    callBack.onResponse(interceptorResponse);
                    callBack.onCompleted();
                }
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onFetch(ApolloInterceptor.FetchSourceType fetchSourceType) {
                callBack.onFetch(fetchSourceType);
            }

            @Override // com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack
            public void onFailure(ApolloException apolloException) {
                callBack.onFailure(apolloException);
            }
        });
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
    public void dispose() {
        this.disposed = true;
    }

    Optional<ApolloInterceptor.InterceptorRequest> handleProtocolNegotiation(final ApolloInterceptor.InterceptorRequest interceptorRequest, ApolloInterceptor.InterceptorResponse interceptorResponse) {
        return interceptorResponse.parsedResponse.flatMap(new Function<Response, Optional<ApolloInterceptor.InterceptorRequest>>() { // from class: com.apollographql.apollo.interceptor.ApolloAutoPersistedOperationInterceptor.2
            @Override // com.apollographql.apollo.api.internal.Function
            public Optional<ApolloInterceptor.InterceptorRequest> apply(Response response) {
                if (response.hasErrors()) {
                    if (ApolloAutoPersistedOperationInterceptor.this.isPersistedQueryNotFound(response.getErrors())) {
                        ApolloAutoPersistedOperationInterceptor.this.logger.w("GraphQL server couldn't find Automatic Persisted Query for operation name: " + interceptorRequest.operation.name().name() + " id: " + interceptorRequest.operation.operationId(), new Object[0]);
                        return Optional.of(interceptorRequest.toBuilder().autoPersistQueries(true).sendQueryDocument(true).build());
                    }
                    if (ApolloAutoPersistedOperationInterceptor.this.isPersistedQueryNotSupported(response.getErrors())) {
                        ApolloAutoPersistedOperationInterceptor.this.logger.e("GraphQL server doesn't support Automatic Persisted Queries", new Object[0]);
                        return Optional.of(interceptorRequest);
                    }
                }
                return Optional.absent();
            }
        });
    }

    boolean isPersistedQueryNotFound(List<Error> list) {
        Iterator<Error> it = list.iterator();
        while (it.hasNext()) {
            if (PROTOCOL_NEGOTIATION_ERROR_QUERY_NOT_FOUND.equalsIgnoreCase(it.next().getMessage())) {
                return true;
            }
        }
        return false;
    }

    boolean isPersistedQueryNotSupported(List<Error> list) {
        Iterator<Error> it = list.iterator();
        while (it.hasNext()) {
            if (PROTOCOL_NEGOTIATION_ERROR_NOT_SUPPORTED.equalsIgnoreCase(it.next().getMessage())) {
                return true;
            }
        }
        return false;
    }

    public static class Factory implements ApolloInterceptorFactory {
        final boolean persistMutations;
        final boolean persistQueries;
        final boolean useHttpGet;

        public Factory(boolean z, boolean z2, boolean z3) {
            this.useHttpGet = z;
            this.persistQueries = z2;
            this.persistMutations = z3;
        }

        public Factory() {
            this(false, true, true);
        }

        @Override // com.apollographql.apollo.interceptor.ApolloInterceptorFactory
        public ApolloInterceptor newInterceptor(ApolloLogger apolloLogger, Operation<?, ?, ?> operation) {
            if ((operation instanceof Query) && !this.persistQueries) {
                return null;
            }
            if (!(operation instanceof Mutation) || this.persistMutations) {
                return new ApolloAutoPersistedOperationInterceptor(apolloLogger, this.useHttpGet);
            }
            return null;
        }
    }
}
