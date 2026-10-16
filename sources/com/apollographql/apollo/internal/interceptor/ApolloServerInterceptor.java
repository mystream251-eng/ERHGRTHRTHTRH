package com.apollographql.apollo.internal.interceptor;

import com.apollographql.apollo.api.FileUpload;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.InputType;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.api.internal.ApolloLogger;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.api.internal.json.InputFieldJsonWriter;
import com.apollographql.apollo.api.internal.json.JsonWriter;
import com.apollographql.apollo.cache.ApolloCacheHeaders;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.request.RequestHeaders;
import com.google.android.gms.actions.SearchIntents;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloServerInterceptor implements ApolloInterceptor {
    static final String ACCEPT_TYPE = "application/json";
    static final String CONTENT_TYPE = "application/json";
    static final String HEADER_ACCEPT_TYPE = "Accept";
    static final String HEADER_APOLLO_OPERATION_ID = "X-APOLLO-OPERATION-ID";
    static final String HEADER_APOLLO_OPERATION_NAME = "X-APOLLO-OPERATION-NAME";
    static final String HEADER_CONTENT_TYPE = "Content-Type";
    static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    final Optional<HttpCachePolicy.Policy> cachePolicy;
    volatile boolean disposed;
    final Call.Factory httpCallFactory;
    AtomicReference<Call> httpCallRef = new AtomicReference<>();
    final ApolloLogger logger;
    final boolean prefetch;
    final ScalarTypeAdapters scalarTypeAdapters;
    final HttpUrl serverUrl;

    public ApolloServerInterceptor(HttpUrl httpUrl, Call.Factory factory, HttpCachePolicy.Policy policy, boolean z, ScalarTypeAdapters scalarTypeAdapters, ApolloLogger apolloLogger) {
        this.serverUrl = (HttpUrl) Utils.checkNotNull(httpUrl, "serverUrl == null");
        this.httpCallFactory = (Call.Factory) Utils.checkNotNull(factory, "httpCallFactory == null");
        this.cachePolicy = Optional.fromNullable(policy);
        this.prefetch = z;
        this.scalarTypeAdapters = (ScalarTypeAdapters) Utils.checkNotNull(scalarTypeAdapters, "scalarTypeAdapters == null");
        this.logger = (ApolloLogger) Utils.checkNotNull(apolloLogger, "logger == null");
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
    public void interceptAsync(final ApolloInterceptor.InterceptorRequest interceptorRequest, ApolloInterceptorChain apolloInterceptorChain, Executor executor, final ApolloInterceptor.CallBack callBack) {
        executor.execute(new Runnable() { // from class: com.apollographql.apollo.internal.interceptor.ApolloServerInterceptor.1
            @Override // java.lang.Runnable
            public void run() {
                ApolloServerInterceptor.this.executeHttpCall(interceptorRequest, callBack);
            }
        });
    }

    @Override // com.apollographql.apollo.interceptor.ApolloInterceptor
    public void dispose() {
        this.disposed = true;
        Call andSet = this.httpCallRef.getAndSet(null);
        if (andSet != null) {
            andSet.cancel();
        }
    }

    void executeHttpCall(final ApolloInterceptor.InterceptorRequest interceptorRequest, final ApolloInterceptor.CallBack callBack) {
        final Call callHttpPostCall;
        if (this.disposed) {
            return;
        }
        callBack.onFetch(ApolloInterceptor.FetchSourceType.NETWORK);
        try {
            if (interceptorRequest.useHttpGetMethodForQueries && (interceptorRequest.operation instanceof Query)) {
                callHttpPostCall = httpGetCall(interceptorRequest.operation, interceptorRequest.cacheHeaders, interceptorRequest.requestHeaders, interceptorRequest.sendQueryDocument, interceptorRequest.autoPersistQueries);
            } else {
                callHttpPostCall = httpPostCall(interceptorRequest.operation, interceptorRequest.cacheHeaders, interceptorRequest.requestHeaders, interceptorRequest.sendQueryDocument, interceptorRequest.autoPersistQueries);
            }
            Call andSet = this.httpCallRef.getAndSet(callHttpPostCall);
            if (andSet != null) {
                andSet.cancel();
            }
            if (callHttpPostCall.isCanceled() || this.disposed) {
                this.httpCallRef.compareAndSet(callHttpPostCall, null);
            } else {
                callHttpPostCall.enqueue(new Callback() { // from class: com.apollographql.apollo.internal.interceptor.ApolloServerInterceptor.2
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        if (!ApolloServerInterceptor.this.disposed && ApolloServerInterceptor.this.httpCallRef.compareAndSet(callHttpPostCall, null)) {
                            ApolloServerInterceptor.this.logger.e(iOException, "Failed to execute http call for operation %s", interceptorRequest.operation.name().name());
                            callBack.onFailure(new ApolloNetworkException("Failed to execute http call", iOException));
                        }
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) {
                        if (!ApolloServerInterceptor.this.disposed && ApolloServerInterceptor.this.httpCallRef.compareAndSet(callHttpPostCall, null)) {
                            callBack.onResponse(new ApolloInterceptor.InterceptorResponse(response));
                            callBack.onCompleted();
                        }
                    }
                });
            }
        } catch (IOException e) {
            this.logger.e(e, "Failed to prepare http call for operation %s", interceptorRequest.operation.name().name());
            callBack.onFailure(new ApolloNetworkException("Failed to prepare http call", e));
        }
    }

    Call httpGetCall(Operation operation, CacheHeaders cacheHeaders, RequestHeaders requestHeaders, boolean z, boolean z2) throws IOException {
        Request.Builder builder = new Request.Builder().url(httpGetUrl(this.serverUrl, operation, this.scalarTypeAdapters, z, z2)).get();
        decorateRequest(builder, operation, cacheHeaders, requestHeaders);
        return this.httpCallFactory.newCall(builder.build());
    }

    Call httpPostCall(Operation operation, CacheHeaders cacheHeaders, RequestHeaders requestHeaders, boolean z, boolean z2) throws IOException {
        Request.Builder builderPost = new Request.Builder().url(this.serverUrl).header(HEADER_CONTENT_TYPE, "application/json").post(transformToMultiPartIfUploadExists(RequestBody.create(MEDIA_TYPE, httpPostRequestBody(operation, this.scalarTypeAdapters, z, z2)), operation));
        decorateRequest(builderPost, operation, cacheHeaders, requestHeaders);
        return this.httpCallFactory.newCall(builderPost.build());
    }

    void decorateRequest(Request.Builder builder, Operation operation, CacheHeaders cacheHeaders, RequestHeaders requestHeaders) throws IOException {
        builder.header(HEADER_ACCEPT_TYPE, "application/json").header(HEADER_APOLLO_OPERATION_ID, operation.operationId()).header(HEADER_APOLLO_OPERATION_NAME, operation.name().name()).tag(operation.operationId());
        for (String str : requestHeaders.headers()) {
            builder.header(str, requestHeaders.headerValue(str));
        }
        if (this.cachePolicy.isPresent()) {
            HttpCachePolicy.Policy policy = this.cachePolicy.get();
            builder.header("X-APOLLO-CACHE-KEY", cacheKey(operation, this.scalarTypeAdapters)).header("X-APOLLO-CACHE-FETCH-STRATEGY", policy.fetchStrategy.name()).header("X-APOLLO-EXPIRE-TIMEOUT", String.valueOf(policy.expireTimeoutMs())).header("X-APOLLO-EXPIRE-AFTER-READ", Boolean.toString(policy.expireAfterRead)).header("X-APOLLO-PREFETCH", Boolean.toString(this.prefetch)).header("X-APOLLO-CACHE-DO-NOT-STORE", Boolean.toString("true".equalsIgnoreCase(cacheHeaders.headerValue(ApolloCacheHeaders.DO_NOT_STORE))));
        }
    }

    static String cacheKey(Operation operation, ScalarTypeAdapters scalarTypeAdapters) throws IOException {
        return httpPostRequestBody(operation, scalarTypeAdapters, true, true).md5().hex();
    }

    static ByteString httpPostRequestBody(Operation operation, ScalarTypeAdapters scalarTypeAdapters, boolean z, boolean z2) throws IOException {
        return operation.composeRequestBody(z2, z, scalarTypeAdapters);
    }

    static HttpUrl httpGetUrl(HttpUrl httpUrl, Operation operation, ScalarTypeAdapters scalarTypeAdapters, boolean z, boolean z2) throws IOException {
        HttpUrl.Builder builderNewBuilder = httpUrl.newBuilder();
        if (!z2 || z) {
            builderNewBuilder.addQueryParameter(SearchIntents.EXTRA_QUERY, operation.queryDocument());
        }
        if (operation.variables() != Operation.EMPTY_VARIABLES) {
            addVariablesUrlQueryParameter(builderNewBuilder, operation, scalarTypeAdapters);
        }
        builderNewBuilder.addQueryParameter("operationName", operation.name().name());
        if (z2) {
            addExtensionsUrlQueryParameter(builderNewBuilder, operation);
        }
        return builderNewBuilder.build();
    }

    static void addVariablesUrlQueryParameter(HttpUrl.Builder builder, Operation operation, ScalarTypeAdapters scalarTypeAdapters) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter jsonWriterOf = JsonWriter.of(buffer);
        jsonWriterOf.setSerializeNulls(true);
        jsonWriterOf.beginObject();
        operation.variables().marshaller().marshal(new InputFieldJsonWriter(jsonWriterOf, scalarTypeAdapters));
        jsonWriterOf.endObject();
        jsonWriterOf.close();
        builder.addQueryParameter("variables", buffer.readUtf8());
    }

    static void addExtensionsUrlQueryParameter(HttpUrl.Builder builder, Operation operation) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter jsonWriterOf = JsonWriter.of(buffer);
        jsonWriterOf.setSerializeNulls(true);
        jsonWriterOf.beginObject();
        jsonWriterOf.name("persistedQuery").beginObject().name("version").value(1L).name("sha256Hash").value(operation.operationId()).endObject();
        jsonWriterOf.endObject();
        jsonWriterOf.close();
        builder.addQueryParameter("extensions", buffer.readUtf8());
    }

    private static void recursiveGetUploadData(Object obj, String str, ArrayList<FileUploadMeta> arrayList) {
        int i = 0;
        if (obj instanceof InputType) {
            try {
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                int length = declaredFields.length;
                while (i < length) {
                    Field field = declaredFields[i];
                    field.setAccessible(true);
                    recursiveGetUploadData(field.get(obj), str + "." + field.getName(), arrayList);
                    i++;
                }
                return;
            } catch (IllegalAccessException unused) {
                return;
            }
        }
        if (obj instanceof Input) {
            recursiveGetUploadData(((Input) obj).value, str, arrayList);
            return;
        }
        if (obj instanceof FileUpload) {
            FileUpload fileUpload = (FileUpload) obj;
            arrayList.add(new FileUploadMeta(str, fileUpload.getMimetype(), new File(fileUpload.getFilePath())));
            System.out.println(str);
            return;
        }
        if (obj instanceof FileUpload[]) {
            FileUpload[] fileUploadArr = (FileUpload[]) obj;
            int length2 = fileUploadArr.length;
            int i2 = 0;
            while (i < length2) {
                FileUpload fileUpload2 = fileUploadArr[i];
                String str2 = str + "." + i2;
                arrayList.add(new FileUploadMeta(str2, fileUpload2.getMimetype(), new File(fileUpload2.getFilePath())));
                System.out.println(str2);
                i2++;
                i++;
            }
            return;
        }
        if (obj instanceof Collection) {
            Object[] array = ((Collection) obj).toArray();
            while (i < array.length) {
                recursiveGetUploadData(array[i], str + "." + i, arrayList);
                i++;
            }
        }
    }

    static RequestBody transformToMultiPartIfUploadExists(RequestBody requestBody, Operation operation) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (String str : operation.variables().valueMap().keySet()) {
            recursiveGetUploadData(operation.variables().valueMap().get(str), "variables." + str, arrayList);
        }
        return arrayList.isEmpty() ? requestBody : httpMultipartRequestBody(requestBody, arrayList);
    }

    static RequestBody httpMultipartRequestBody(RequestBody requestBody, ArrayList<FileUploadMeta> arrayList) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter jsonWriterOf = JsonWriter.of(buffer);
        jsonWriterOf.beginObject();
        for (int i = 0; i < arrayList.size(); i++) {
            jsonWriterOf.name(String.valueOf(i)).beginArray();
            jsonWriterOf.value(arrayList.get(i).key);
            jsonWriterOf.endArray();
        }
        jsonWriterOf.endObject();
        jsonWriterOf.close();
        MultipartBody.Builder builderAddFormDataPart = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("operations", null, requestBody).addFormDataPart("map", null, RequestBody.create(MEDIA_TYPE, buffer.readByteString()));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            FileUploadMeta fileUploadMeta = arrayList.get(i2);
            builderAddFormDataPart.addFormDataPart(String.valueOf(i2), fileUploadMeta.file.getName(), RequestBody.create(MediaType.parse(fileUploadMeta.mimetype), fileUploadMeta.file));
        }
        return builderAddFormDataPart.build();
    }

    private static final class FileUploadMeta {
        public final File file;
        public final String key;
        public final String mimetype;

        FileUploadMeta(String str, String str2, File file) {
            this.key = str;
            this.mimetype = str2;
            this.file = file;
        }
    }
}
