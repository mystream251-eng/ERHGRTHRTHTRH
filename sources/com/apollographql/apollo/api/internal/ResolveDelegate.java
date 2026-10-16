package com.apollographql.apollo.api.internal;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ResolveDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH&J\u0014\u0010\f\u001a\u00020\u00042\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH&J\b\u0010\u000f\u001a\u00020\u0004H&J\u001f\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H&J\"\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H&J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH&J\u001f\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0019\u001a\u00020\u00042\u0012\u0010\u001a\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001bH&¨\u0006\u001c"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResolveDelegate;", "R", "", "didResolve", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "variables", "Lcom/apollographql/apollo/api/Operation$Variables;", "didResolveElement", "atIndex", "", "didResolveList", "array", "", "didResolveNull", "didResolveObject", "objectField", "objectSource", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Object;)V", "didResolveScalar", "value", "willResolve", "willResolveElement", "willResolveObject", "willResolveRootQuery", "operation", "Lcom/apollographql/apollo/api/Operation;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface ResolveDelegate<R> {
    void didResolve(ResponseField field, Operation.Variables variables);

    void didResolveElement(int atIndex);

    void didResolveList(List<?> array);

    void didResolveNull();

    void didResolveObject(ResponseField objectField, R objectSource);

    void didResolveScalar(Object value);

    void willResolve(ResponseField field, Operation.Variables variables, Object value);

    void willResolveElement(int atIndex);

    void willResolveObject(ResponseField objectField, R objectSource);

    void willResolveRootQuery(Operation<?, ?, ?> operation);
}
