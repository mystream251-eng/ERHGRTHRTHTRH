package com.apollographql.apollo.api.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResponseFieldMapper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u0007*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0007J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseFieldMapper;", ExifInterface.GPS_DIRECTION_TRUE, "", "map", "responseReader", "Lcom/apollographql/apollo/api/internal/ResponseReader;", "(Lcom/apollographql/apollo/api/internal/ResponseReader;)Ljava/lang/Object;", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface ResponseFieldMapper<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    T map(ResponseReader responseReader);

    /* JADX INFO: compiled from: ResponseFieldMapper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0014\b\u0004\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0086\n¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo/api/internal/ResponseFieldMapper$Companion;", "", "()V", "invoke", "Lcom/apollographql/apollo/api/internal/ResponseFieldMapper;", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function1;", "Lcom/apollographql/apollo/api/internal/ResponseReader;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final /* synthetic */ <T> ResponseFieldMapper<T> invoke(final Function1<? super ResponseReader, ? extends T> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return new ResponseFieldMapper<T>() { // from class: com.apollographql.apollo.api.internal.ResponseFieldMapper$Companion$invoke$1
                @Override // com.apollographql.apollo.api.internal.ResponseFieldMapper
                public T map(ResponseReader responseReader) {
                    Intrinsics.checkParameterIsNotNull(responseReader, "responseReader");
                    return (T) block.invoke(responseReader);
                }
            };
        }
    }
}
