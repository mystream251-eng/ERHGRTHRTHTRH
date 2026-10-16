package com.apollographql.apollo.api;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Response.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 6*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u000256B\u0015\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005Bq\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00018\u0000\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u0015\u0010%\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0011\u0010'\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\t\u0010)\u001a\u00020\u0010HÆ\u0003J\u0017\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0012HÆ\u0003J\t\u0010+\u001a\u00020\u0014HÆ\u0003J\u0084\u0001\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00018\u00002\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001¢\u0006\u0002\u0010-J\u000f\u0010\b\u001a\u0004\u0018\u00018\u0000H\u0007¢\u0006\u0002\u0010\u0017J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0007J\u0013\u0010.\u001a\u00020\u00102\b\u0010/\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\u0010\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0007J\u0016\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0012H\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\u0006\u00100\u001a\u00020\u0010J\b\u00101\u001a\u000202H\u0016J\u0014\u0010\u0006\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007H\u0007J\f\u00103\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004J\t\u00104\u001a\u00020\u000eHÖ\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001f\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001d\u0010\u0006\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00067"}, d2 = {"Lcom/apollographql/apollo/api/Response;", ExifInterface.GPS_DIRECTION_TRUE, "", "builder", "Lcom/apollographql/apollo/api/Response$Builder;", "(Lcom/apollographql/apollo/api/Response$Builder;)V", "operation", "Lcom/apollographql/apollo/api/Operation;", "data", "errors", "", "Lcom/apollographql/apollo/api/Error;", "dependentKeys", "", "", "fromCache", "", "extensions", "", "executionContext", "Lcom/apollographql/apollo/api/ExecutionContext;", "(Lcom/apollographql/apollo/api/Operation;Ljava/lang/Object;Ljava/util/List;Ljava/util/Set;ZLjava/util/Map;Lcom/apollographql/apollo/api/ExecutionContext;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getDependentKeys", "()Ljava/util/Set;", "getErrors", "()Ljava/util/List;", "getExecutionContext", "()Lcom/apollographql/apollo/api/ExecutionContext;", "getExtensions", "()Ljava/util/Map;", "getFromCache", "()Z", "getOperation", "()Lcom/apollographql/apollo/api/Operation;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lcom/apollographql/apollo/api/Operation;Ljava/lang/Object;Ljava/util/List;Ljava/util/Set;ZLjava/util/Map;Lcom/apollographql/apollo/api/ExecutionContext;)Lcom/apollographql/apollo/api/Response;", "equals", "other", "hasErrors", "hashCode", "", "toBuilder", "toString", "Builder", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final /* data */ class Response<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final T data;
    private final Set<String> dependentKeys;
    private final List<Error> errors;
    private final ExecutionContext executionContext;
    private final Map<String, Object> extensions;
    private final boolean fromCache;
    private final Operation<?, ?, ?> operation;

    @JvmStatic
    public static final <T> Builder<T> builder(Operation<?, ?, ?> operation) {
        return INSTANCE.builder(operation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Response copy$default(Response response, Operation operation, Object obj, List list, Set set, boolean z, Map map, ExecutionContext executionContext, int i, Object obj2) {
        if ((i & 1) != 0) {
            operation = response.operation;
        }
        if ((i & 2) != 0) {
            obj = response.data;
        }
        Object obj3 = obj;
        if ((i & 4) != 0) {
            list = response.errors;
        }
        List list2 = list;
        if ((i & 8) != 0) {
            set = response.dependentKeys;
        }
        Set set2 = set;
        if ((i & 16) != 0) {
            z = response.fromCache;
        }
        boolean z2 = z;
        if ((i & 32) != 0) {
            map = response.extensions;
        }
        Map map2 = map;
        if ((i & 64) != 0) {
            executionContext = response.executionContext;
        }
        return response.copy(operation, obj3, list2, set2, z2, map2, executionContext);
    }

    public final Operation<?, ?, ?> component1() {
        return this.operation;
    }

    public final T component2() {
        return this.data;
    }

    public final List<Error> component3() {
        return this.errors;
    }

    public final Set<String> component4() {
        return this.dependentKeys;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getFromCache() {
        return this.fromCache;
    }

    public final Map<String, Object> component6() {
        return this.extensions;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final ExecutionContext getExecutionContext() {
        return this.executionContext;
    }

    public final Response<T> copy(Operation<?, ?, ?> operation, T data, List<Error> errors, Set<String> dependentKeys, boolean fromCache, Map<String, ? extends Object> extensions, ExecutionContext executionContext) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(dependentKeys, "dependentKeys");
        Intrinsics.checkParameterIsNotNull(extensions, "extensions");
        Intrinsics.checkParameterIsNotNull(executionContext, "executionContext");
        return new Response<>(operation, data, errors, dependentKeys, fromCache, extensions, executionContext);
    }

    public String toString() {
        return "Response(operation=" + this.operation + ", data=" + this.data + ", errors=" + this.errors + ", dependentKeys=" + this.dependentKeys + ", fromCache=" + this.fromCache + ", extensions=" + this.extensions + ", executionContext=" + this.executionContext + ")";
    }

    public Response(Operation<?, ?, ?> operation, T t, List<Error> list, Set<String> dependentKeys, boolean z, Map<String, ? extends Object> extensions, ExecutionContext executionContext) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(dependentKeys, "dependentKeys");
        Intrinsics.checkParameterIsNotNull(extensions, "extensions");
        Intrinsics.checkParameterIsNotNull(executionContext, "executionContext");
        this.operation = operation;
        this.data = t;
        this.errors = list;
        this.dependentKeys = dependentKeys;
        this.fromCache = z;
        this.extensions = extensions;
        this.executionContext = executionContext;
    }

    public final Operation<?, ?, ?> getOperation() {
        return this.operation;
    }

    public final T getData() {
        return this.data;
    }

    public /* synthetic */ Response(Operation operation, Object obj, List list, Set set, boolean z, Map map, ExecutionContext executionContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(operation, obj, (i & 4) != 0 ? (List) null : list, (i & 8) != 0 ? SetsKt.emptySet() : set, (i & 16) != 0 ? false : z, (i & 32) != 0 ? MapsKt.emptyMap() : map, (i & 64) != 0 ? ExecutionContext.Empty : executionContext);
    }

    public final List<Error> getErrors() {
        return this.errors;
    }

    public final Set<String> getDependentKeys() {
        return this.dependentKeys;
    }

    public final boolean getFromCache() {
        return this.fromCache;
    }

    public final Map<String, Object> getExtensions() {
        return this.extensions;
    }

    public final ExecutionContext getExecutionContext() {
        return this.executionContext;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Response(Builder<T> builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        Operation<?, ?, ?> operation$apollo_api = builder.getOperation$apollo_api();
        T data$apollo_api = builder.getData$apollo_api();
        List<Error> errors$apollo_api = builder.getErrors$apollo_api();
        Set<String> dependentKeys$apollo_api = builder.getDependentKeys$apollo_api();
        Set<String> setEmptySet = dependentKeys$apollo_api == null ? SetsKt.emptySet() : dependentKeys$apollo_api;
        boolean fromCache = builder.getFromCache();
        Map<String, Object> extensions$apollo_api = builder.getExtensions$apollo_api();
        this(operation$apollo_api, data$apollo_api, errors$apollo_api, setEmptySet, fromCache, extensions$apollo_api == null ? MapsKt.emptyMap() : extensions$apollo_api, builder.getExecutionContext());
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "operation", imports = {}))
    public final Operation<?, ?, ?> operation() {
        return this.operation;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "data", imports = {}))
    public final T data() {
        return this.data;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "errors", imports = {}))
    public final List<Error> errors() {
        return this.errors;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "dependentKeys", imports = {}))
    public final Set<String> dependentKeys() {
        return this.dependentKeys;
    }

    public final boolean hasErrors() {
        List<Error> list = this.errors;
        return !(list == null || list.isEmpty());
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "fromCache", imports = {}))
    public final boolean fromCache() {
        return this.fromCache;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "extensions", imports = {}))
    public final Map<String, Object> extensions() {
        return this.extensions;
    }

    public final Builder<T> toBuilder() {
        return new Builder(this.operation).data(this.data).errors(this.errors).dependentKeys(this.dependentKeys).fromCache(this.fromCache).extensions(this.extensions).executionContext(this.executionContext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Response)) {
            return false;
        }
        Response response = (Response) other;
        return ((Intrinsics.areEqual(this.operation, response.operation) ^ true) || (Intrinsics.areEqual(this.data, response.data) ^ true) || (Intrinsics.areEqual(this.errors, response.errors) ^ true) || (Intrinsics.areEqual(this.dependentKeys, response.dependentKeys) ^ true) || this.fromCache != response.fromCache || (Intrinsics.areEqual(this.extensions, response.extensions) ^ true) || (Intrinsics.areEqual(this.executionContext, response.executionContext) ^ true)) ? false : true;
    }

    public int hashCode() {
        int iHashCode = this.operation.hashCode() * 31;
        T t = this.data;
        int iHashCode2 = (iHashCode + (t != null ? t.hashCode() : 0)) * 31;
        List<Error> list = this.errors;
        return ((((((iHashCode2 + (list != null ? list.hashCode() : 0)) * 31) + this.dependentKeys.hashCode()) * 31) + Boolean.hashCode(this.fromCache)) * 31) + this.extensions.hashCode();
    }

    /* JADX INFO: compiled from: Response.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u001b\b\u0000\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010/J\u001b\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\u0006\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u00100J\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rJ\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001a\u001a\u00020\u001bJ$\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0016\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010!J\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010&\u001a\u00020'R\u001e\u0010\u0006\u001a\u0004\u0018\u00018\u0001X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR*\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010!X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R \u0010\u0003\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-¨\u00061"}, d2 = {"Lcom/apollographql/apollo/api/Response$Builder;", ExifInterface.GPS_DIRECTION_TRUE, "", "operation", "Lcom/apollographql/apollo/api/Operation;", "(Lcom/apollographql/apollo/api/Operation;)V", "data", "getData$apollo_api", "()Ljava/lang/Object;", "setData$apollo_api", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "dependentKeys", "", "", "getDependentKeys$apollo_api", "()Ljava/util/Set;", "setDependentKeys$apollo_api", "(Ljava/util/Set;)V", "errors", "", "Lcom/apollographql/apollo/api/Error;", "getErrors$apollo_api", "()Ljava/util/List;", "setErrors$apollo_api", "(Ljava/util/List;)V", "executionContext", "Lcom/apollographql/apollo/api/ExecutionContext;", "getExecutionContext$apollo_api", "()Lcom/apollographql/apollo/api/ExecutionContext;", "setExecutionContext$apollo_api", "(Lcom/apollographql/apollo/api/ExecutionContext;)V", "extensions", "", "getExtensions$apollo_api", "()Ljava/util/Map;", "setExtensions$apollo_api", "(Ljava/util/Map;)V", "fromCache", "", "getFromCache$apollo_api", "()Z", "setFromCache$apollo_api", "(Z)V", "getOperation$apollo_api", "()Lcom/apollographql/apollo/api/Operation;", "build", "Lcom/apollographql/apollo/api/Response;", "(Ljava/lang/Object;)Lcom/apollographql/apollo/api/Response$Builder;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Builder<T> {
        private T data;
        private Set<String> dependentKeys;
        private List<Error> errors;
        private ExecutionContext executionContext;
        private Map<String, ? extends Object> extensions;
        private boolean fromCache;
        private final Operation<?, ?, ?> operation;

        public Builder(Operation<?, ?, ?> operation) {
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            this.operation = operation;
            this.executionContext = ExecutionContext.Empty;
        }

        public final Operation<?, ?, ?> getOperation$apollo_api() {
            return this.operation;
        }

        public final T getData$apollo_api() {
            return this.data;
        }

        public final void setData$apollo_api(T t) {
            this.data = t;
        }

        public final List<Error> getErrors$apollo_api() {
            return this.errors;
        }

        public final void setErrors$apollo_api(List<Error> list) {
            this.errors = list;
        }

        public final Set<String> getDependentKeys$apollo_api() {
            return this.dependentKeys;
        }

        public final void setDependentKeys$apollo_api(Set<String> set) {
            this.dependentKeys = set;
        }

        /* JADX INFO: renamed from: getFromCache$apollo_api, reason: from getter */
        public final boolean getFromCache() {
            return this.fromCache;
        }

        public final void setFromCache$apollo_api(boolean z) {
            this.fromCache = z;
        }

        public final Map<String, Object> getExtensions$apollo_api() {
            return this.extensions;
        }

        public final void setExtensions$apollo_api(Map<String, ? extends Object> map) {
            this.extensions = map;
        }

        /* JADX INFO: renamed from: getExecutionContext$apollo_api, reason: from getter */
        public final ExecutionContext getExecutionContext() {
            return this.executionContext;
        }

        public final void setExecutionContext$apollo_api(ExecutionContext executionContext) {
            Intrinsics.checkParameterIsNotNull(executionContext, "<set-?>");
            this.executionContext = executionContext;
        }

        public final Builder<T> data(T data) {
            Builder<T> builder = this;
            builder.data = data;
            return builder;
        }

        public final Builder<T> errors(List<Error> errors) {
            Builder<T> builder = this;
            builder.errors = errors;
            return builder;
        }

        public final Builder<T> dependentKeys(Set<String> dependentKeys) {
            Builder<T> builder = this;
            builder.dependentKeys = dependentKeys;
            return builder;
        }

        public final Builder<T> fromCache(boolean fromCache) {
            Builder<T> builder = this;
            builder.fromCache = fromCache;
            return builder;
        }

        public final Builder<T> extensions(Map<String, ? extends Object> extensions) {
            Builder<T> builder = this;
            builder.extensions = extensions;
            return builder;
        }

        public final Builder<T> executionContext(ExecutionContext executionContext) {
            Intrinsics.checkParameterIsNotNull(executionContext, "executionContext");
            Builder<T> builder = this;
            builder.executionContext = executionContext;
            return builder;
        }

        public final Response<T> build() {
            return new Response<>(this);
        }
    }

    /* JADX INFO: compiled from: Response.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo/api/Response$Companion;", "", "()V", "builder", "Lcom/apollographql/apollo/api/Response$Builder;", ExifInterface.GPS_DIRECTION_TRUE, "operation", "Lcom/apollographql/apollo/api/Operation;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final <T> Builder<T> builder(Operation<?, ?, ?> operation) {
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            return new Builder<>(operation);
        }
    }
}
