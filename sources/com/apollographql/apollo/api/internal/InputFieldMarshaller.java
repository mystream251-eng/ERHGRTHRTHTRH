package com.apollographql.apollo.api.internal;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InputFieldMarshaller.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "", "marshal", "", "writer", "Lcom/apollographql/apollo/api/internal/InputFieldWriter;", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface InputFieldMarshaller {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    void marshal(InputFieldWriter writer) throws IOException;

    /* JADX INFO: compiled from: InputFieldMarshaller.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0014\b\u0004\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0086\n¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo/api/internal/InputFieldMarshaller$Companion;", "", "()V", "invoke", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "block", "Lkotlin/Function1;", "Lcom/apollographql/apollo/api/internal/InputFieldWriter;", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final /* synthetic */ InputFieldMarshaller invoke(final Function1<? super InputFieldWriter, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return new InputFieldMarshaller() { // from class: com.apollographql.apollo.api.internal.InputFieldMarshaller$Companion$invoke$1
                @Override // com.apollographql.apollo.api.internal.InputFieldMarshaller
                public void marshal(InputFieldWriter writer) {
                    Intrinsics.checkParameterIsNotNull(writer, "writer");
                    block.invoke(writer);
                }
            };
        }
    }
}
