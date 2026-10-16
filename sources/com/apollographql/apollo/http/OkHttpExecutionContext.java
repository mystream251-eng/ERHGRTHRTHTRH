package com.apollographql.apollo.http;

import com.apollographql.apollo.api.ExecutionContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;

/* JADX INFO: compiled from: OkHttpExecutionContext.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u000b\u001a\u00020\u0003*\u00020\u0003H\u0002R\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo/http/OkHttpExecutionContext;", "Lcom/apollographql/apollo/api/ExecutionContext$Element;", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "key", "Lcom/apollographql/apollo/api/ExecutionContext$Key;", "getKey", "()Lcom/apollographql/apollo/api/ExecutionContext$Key;", "getResponse", "()Lokhttp3/Response;", "strip", "Key", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
public final class OkHttpExecutionContext implements ExecutionContext.Element {
    public static final Companion KEY;

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final ExecutionContext.Key<?> key;
    private final Response response;

    public OkHttpExecutionContext(Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.response = strip(response);
        this.key = INSTANCE;
    }

    @Override // com.apollographql.apollo.api.ExecutionContext.Element, com.apollographql.apollo.api.ExecutionContext
    public <R> R fold(R r, Function2<? super R, ? super ExecutionContext.Element, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        return (R) ExecutionContext.Element.DefaultImpls.fold(this, r, operation);
    }

    @Override // com.apollographql.apollo.api.ExecutionContext.Element, com.apollographql.apollo.api.ExecutionContext
    public <E extends ExecutionContext.Element> E get(ExecutionContext.Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (E) ExecutionContext.Element.DefaultImpls.get(this, key);
    }

    @Override // com.apollographql.apollo.api.ExecutionContext.Element, com.apollographql.apollo.api.ExecutionContext
    public ExecutionContext minusKey(ExecutionContext.Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return ExecutionContext.Element.DefaultImpls.minusKey(this, key);
    }

    @Override // com.apollographql.apollo.api.ExecutionContext
    public ExecutionContext plus(ExecutionContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return ExecutionContext.Element.DefaultImpls.plus(this, context);
    }

    public final Response getResponse() {
        return this.response;
    }

    @Override // com.apollographql.apollo.api.ExecutionContext.Element
    public ExecutionContext.Key<?> getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: com.apollographql.apollo.http.OkHttpExecutionContext$Key, reason: from kotlin metadata */
    /* JADX INFO: compiled from: OkHttpExecutionContext.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo/http/OkHttpExecutionContext$Key;", "Lcom/apollographql/apollo/api/ExecutionContext$Key;", "Lcom/apollographql/apollo/http/OkHttpExecutionContext;", "()V", "KEY", "apollo-runtime"}, k = 1, mv = {1, 1, 16})
    public static final class Companion implements ExecutionContext.Key<OkHttpExecutionContext> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        KEY = companion;
    }

    private final Response strip(Response response) {
        Response.Builder builderNewBuilder = response.newBuilder();
        if (response.body() != null) {
            builderNewBuilder.body(null);
        }
        Response responseCacheResponse = response.cacheResponse();
        if (responseCacheResponse != null) {
            builderNewBuilder.cacheResponse(strip(responseCacheResponse));
        }
        Response responseNetworkResponse = response.networkResponse();
        if (responseNetworkResponse != null) {
            builderNewBuilder.networkResponse(strip(responseNetworkResponse));
        }
        Response responseBuild = builderNewBuilder.build();
        Intrinsics.checkExpressionValueIsNotNull(responseBuild, "builder.build()");
        return responseBuild;
    }
}
