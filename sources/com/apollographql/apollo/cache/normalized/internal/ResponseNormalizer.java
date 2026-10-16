package com.apollographql.apollo.cache.normalized.internal;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.internal.ResolveDelegate;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.CacheReference;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.RecordSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResponseNormalizer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 8*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u00018B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0014\u0010\u001f\u001a\u00020\u00172\n\u0010 \u001a\u0006\u0012\u0002\b\u00030!H\u0016J\b\u0010\"\u001a\u00020\u0017H\u0016J\u001f\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00192\b\u0010%\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010&J\u0012\u0010'\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010)\u001a\u00020\bH\u0002J\u0012\u0010*\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010+H\u0016J\u001d\u0010,\u001a\u00020-2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010.\u001a\u00028\u0000H&¢\u0006\u0002\u0010/J\"\u00100\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010(\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u00101\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001f\u00102\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00192\b\u0010%\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010&J\u000e\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020-J\u001c\u00105\u001a\u00020\u00172\u0012\u00106\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u000307H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\fX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\fX\u0082.¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/internal/ResponseNormalizer;", "R", "Lcom/apollographql/apollo/api/internal/ResolveDelegate;", "()V", "currentRecordBuilder", "Lcom/apollographql/apollo/cache/normalized/Record$Builder;", "dependentKeys", "", "", "path", "", "pathStack", "Lcom/apollographql/apollo/cache/normalized/internal/SimpleStack;", "recordSet", "Lcom/apollographql/apollo/cache/normalized/RecordSet;", "recordStack", "Lcom/apollographql/apollo/cache/normalized/Record;", "valueStack", "", "cacheKeyBuilder", "Lcom/apollographql/apollo/cache/normalized/internal/CacheKeyBuilder;", "", "didResolve", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "variables", "Lcom/apollographql/apollo/api/Operation$Variables;", "didResolveElement", "atIndex", "", "didResolveList", "array", "", "didResolveNull", "didResolveObject", "objectField", "objectSource", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Object;)V", "didResolveScalar", "value", "pathToString", "records", "", "resolveCacheKey", "Lcom/apollographql/apollo/cache/normalized/CacheKey;", "record", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Object;)Lcom/apollographql/apollo/cache/normalized/CacheKey;", "willResolve", "willResolveElement", "willResolveObject", "willResolveRecord", "cacheKey", "willResolveRootQuery", "operation", "Lcom/apollographql/apollo/api/Operation;", "Companion", "apollo-normalized-cache"}, k = 1, mv = {1, 1, 16})
public abstract class ResponseNormalizer<R> implements ResolveDelegate<R> {
    public static final ResponseNormalizer<?> NO_OP_NORMALIZER = new ResponseNormalizer<Object>() { // from class: com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer$Companion$NO_OP_NORMALIZER$1
        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void didResolve(ResponseField field, Operation.Variables variables) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(variables, "variables");
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void didResolveElement(int atIndex) {
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void didResolveList(List<?> array) {
            Intrinsics.checkParameterIsNotNull(array, "array");
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void didResolveNull() {
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void didResolveObject(ResponseField objectField, Object objectSource) {
            Intrinsics.checkParameterIsNotNull(objectField, "objectField");
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void didResolveScalar(Object value) {
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void willResolve(ResponseField field, Operation.Variables variables, Object value) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            Intrinsics.checkParameterIsNotNull(variables, "variables");
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void willResolveElement(int atIndex) {
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void willResolveObject(ResponseField objectField, Object objectSource) {
            Intrinsics.checkParameterIsNotNull(objectField, "objectField");
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer, com.apollographql.apollo.api.internal.ResolveDelegate
        public void willResolveRootQuery(Operation<?, ?, ?> operation) {
            Intrinsics.checkParameterIsNotNull(operation, "operation");
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer
        public Collection<Record> records() {
            return CollectionsKt.emptyList();
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer
        public Set<String> dependentKeys() {
            return SetsKt.emptySet();
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer
        public CacheKey resolveCacheKey(ResponseField field, Object record) {
            Intrinsics.checkParameterIsNotNull(field, "field");
            return CacheKey.NO_KEY;
        }

        @Override // com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer
        public CacheKeyBuilder cacheKeyBuilder() {
            return new CacheKeyBuilder() { // from class: com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer$Companion$NO_OP_NORMALIZER$1$cacheKeyBuilder$1
                @Override // com.apollographql.apollo.cache.normalized.internal.CacheKeyBuilder
                public String build(ResponseField field, Operation.Variables variables) {
                    Intrinsics.checkParameterIsNotNull(field, "field");
                    Intrinsics.checkParameterIsNotNull(variables, "variables");
                    return CacheKey.NO_KEY.key();
                }
            };
        }
    };
    private Record.Builder currentRecordBuilder;
    private List<String> path;
    private SimpleStack<List<String>> pathStack;
    private SimpleStack<Record> recordStack;
    private SimpleStack<Object> valueStack;
    private RecordSet recordSet = new RecordSet();
    private Set<String> dependentKeys = new LinkedHashSet();

    public abstract CacheKeyBuilder cacheKeyBuilder();

    public abstract CacheKey resolveCacheKey(ResponseField field, R record);

    public Collection<Record> records() {
        return this.recordSet.allRecords();
    }

    public Set<String> dependentKeys() {
        return this.dependentKeys;
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void willResolveRootQuery(Operation<?, ?, ?> operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        willResolveRecord(CacheKeyResolver.INSTANCE.rootKeyForOperation(operation));
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void willResolve(ResponseField field, Operation.Variables variables, Object value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        String strBuild = cacheKeyBuilder().build(field, variables);
        List<String> list = this.path;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("path");
        }
        list.add(strBuild);
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void didResolve(ResponseField field, Operation.Variables variables) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(variables, "variables");
        List<String> list = this.path;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("path");
        }
        List<String> list2 = this.path;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("path");
        }
        list.remove(list2.size() - 1);
        SimpleStack<Object> simpleStack = this.valueStack;
        if (simpleStack == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueStack");
        }
        Object objPop = simpleStack.pop();
        String strBuild = cacheKeyBuilder().build(field, variables);
        StringBuilder sb = new StringBuilder();
        Record.Builder builder = this.currentRecordBuilder;
        if (builder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentRecordBuilder");
        }
        sb.append(builder.getKey());
        sb.append(".");
        sb.append(strBuild);
        this.dependentKeys.add(sb.toString());
        Record.Builder builder2 = this.currentRecordBuilder;
        if (builder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentRecordBuilder");
        }
        builder2.addField(strBuild, objPop);
        SimpleStack<Record> simpleStack2 = this.recordStack;
        if (simpleStack2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recordStack");
        }
        if (simpleStack2.isEmpty()) {
            RecordSet recordSet = this.recordSet;
            Record.Builder builder3 = this.currentRecordBuilder;
            if (builder3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentRecordBuilder");
            }
            recordSet.merge(builder3.build());
        }
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void didResolveScalar(Object value) {
        SimpleStack<Object> simpleStack = this.valueStack;
        if (simpleStack == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueStack");
        }
        simpleStack.push(value);
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void willResolveObject(ResponseField objectField, R objectSource) {
        CacheKey cacheKeyResolveCacheKey;
        Intrinsics.checkParameterIsNotNull(objectField, "objectField");
        SimpleStack<List<String>> simpleStack = this.pathStack;
        if (simpleStack == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pathStack");
        }
        List<String> list = this.path;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("path");
        }
        simpleStack.push(list);
        if (objectSource == null || (cacheKeyResolveCacheKey = resolveCacheKey(objectField, objectSource)) == null) {
            cacheKeyResolveCacheKey = CacheKey.NO_KEY;
        }
        String strKey = cacheKeyResolveCacheKey.key();
        if (cacheKeyResolveCacheKey.equals(CacheKey.NO_KEY)) {
            strKey = pathToString();
        } else {
            ArrayList arrayList = new ArrayList();
            this.path = arrayList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("path");
            }
            arrayList.add(strKey);
        }
        SimpleStack<Record> simpleStack2 = this.recordStack;
        if (simpleStack2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recordStack");
        }
        Record.Builder builder = this.currentRecordBuilder;
        if (builder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentRecordBuilder");
        }
        simpleStack2.push(builder.build());
        this.currentRecordBuilder = Record.INSTANCE.builder(strKey);
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void didResolveObject(ResponseField objectField, R objectSource) {
        Intrinsics.checkParameterIsNotNull(objectField, "objectField");
        SimpleStack<List<String>> simpleStack = this.pathStack;
        if (simpleStack == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pathStack");
        }
        this.path = simpleStack.pop();
        if (objectSource != null) {
            Record.Builder builder = this.currentRecordBuilder;
            if (builder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentRecordBuilder");
            }
            Record recordBuild = builder.build();
            SimpleStack<Object> simpleStack2 = this.valueStack;
            if (simpleStack2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("valueStack");
            }
            simpleStack2.push(new CacheReference(recordBuild.key()));
            this.dependentKeys.add(recordBuild.key());
            this.recordSet.merge(recordBuild);
        }
        SimpleStack<Record> simpleStack3 = this.recordStack;
        if (simpleStack3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recordStack");
        }
        this.currentRecordBuilder = simpleStack3.pop().toBuilder();
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void didResolveList(List<?> array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        ArrayList arrayList = new ArrayList(array.size());
        int size = array.size();
        for (int i = 0; i < size; i++) {
            SimpleStack<Object> simpleStack = this.valueStack;
            if (simpleStack == null) {
                Intrinsics.throwUninitializedPropertyAccessException("valueStack");
            }
            arrayList.add(0, simpleStack.pop());
        }
        SimpleStack<Object> simpleStack2 = this.valueStack;
        if (simpleStack2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueStack");
        }
        simpleStack2.push(arrayList);
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void willResolveElement(int atIndex) {
        List<String> list = this.path;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("path");
        }
        list.add(String.valueOf(atIndex));
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void didResolveElement(int atIndex) {
        List<String> list = this.path;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("path");
        }
        List<String> list2 = this.path;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("path");
        }
        list.remove(list2.size() - 1);
    }

    @Override // com.apollographql.apollo.api.internal.ResolveDelegate
    public void didResolveNull() {
        SimpleStack<Object> simpleStack = this.valueStack;
        if (simpleStack == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueStack");
        }
        simpleStack.push(null);
    }

    public final void willResolveRecord(CacheKey cacheKey) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        this.pathStack = new SimpleStack<>();
        this.recordStack = new SimpleStack<>();
        this.valueStack = new SimpleStack<>();
        this.dependentKeys = new HashSet();
        this.path = new ArrayList();
        this.currentRecordBuilder = Record.INSTANCE.builder(cacheKey.key());
        this.recordSet = new RecordSet();
    }

    private final String pathToString() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.path;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("path");
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            List<String> list2 = this.path;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("path");
            }
            sb.append(list2.get(i));
            if (i < size - 1) {
                sb.append(".");
            }
        }
        String string = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "stringBuilder.toString()");
        return string;
    }
}
