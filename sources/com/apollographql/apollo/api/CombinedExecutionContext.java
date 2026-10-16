package com.apollographql.apollo.api;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExecutionContext.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0096\u0002¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00012\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo/api/CombinedExecutionContext;", "Lcom/apollographql/apollo/api/ExecutionContext;", TtmlNode.LEFT, "element", "Lcom/apollographql/apollo/api/ExecutionContext$Element;", "(Lcom/apollographql/apollo/api/ExecutionContext;Lcom/apollographql/apollo/api/ExecutionContext$Element;)V", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", ExifInterface.LONGITUDE_EAST, "key", "Lcom/apollographql/apollo/api/ExecutionContext$Key;", "(Lcom/apollographql/apollo/api/ExecutionContext$Key;)Lcom/apollographql/apollo/api/ExecutionContext$Element;", "minusKey", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class CombinedExecutionContext implements ExecutionContext {
    private final ExecutionContext.Element element;
    private final ExecutionContext left;

    public CombinedExecutionContext(ExecutionContext left, ExecutionContext.Element element) {
        Intrinsics.checkParameterIsNotNull(left, "left");
        Intrinsics.checkParameterIsNotNull(element, "element");
        this.left = left;
        this.element = element;
    }

    @Override // com.apollographql.apollo.api.ExecutionContext
    public ExecutionContext plus(ExecutionContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return ExecutionContext.DefaultImpls.plus(this, context);
    }

    @Override // com.apollographql.apollo.api.ExecutionContext
    public <E extends ExecutionContext.Element> E get(ExecutionContext.Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        ExecutionContext executionContext = this;
        do {
            CombinedExecutionContext combinedExecutionContext = (CombinedExecutionContext) executionContext;
            E e = (E) combinedExecutionContext.element.get(key);
            if (e != null) {
                return e;
            }
            executionContext = combinedExecutionContext.left;
        } while (executionContext instanceof CombinedExecutionContext);
        return (E) executionContext.get(key);
    }

    @Override // com.apollographql.apollo.api.ExecutionContext
    public <R> R fold(R initial, Function2<? super R, ? super ExecutionContext.Element, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        return operation.invoke((Object) this.left.fold(initial, operation), this.element);
    }

    @Override // com.apollographql.apollo.api.ExecutionContext
    public ExecutionContext minusKey(ExecutionContext.Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (this.element.get(key) != null) {
            return this.left;
        }
        ExecutionContext executionContextMinusKey = this.left.minusKey(key);
        if (executionContextMinusKey == this.left) {
            return this;
        }
        return executionContextMinusKey == EmptyExecutionContext.INSTANCE ? this.element : new CombinedExecutionContext(executionContextMinusKey, this.element);
    }
}
