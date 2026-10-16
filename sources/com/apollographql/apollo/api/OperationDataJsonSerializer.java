package com.apollographql.apollo.api;

import com.apollographql.apollo.api.internal.SimpleResponseWriter;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OperationDataExtensions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"toJson", "", "Lcom/apollographql/apollo/api/Operation$Data;", "indent", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "serialize", "apollo-api"}, k = 2, mv = {1, 1, 16})
public final class OperationDataJsonSerializer {
    public static final String serialize(Operation.Data data) {
        return serialize$default(data, null, null, 3, null);
    }

    public static final String serialize(Operation.Data data, String str) {
        return serialize$default(data, str, null, 2, null);
    }

    public static /* synthetic */ String serialize$default(Operation.Data data, String str, ScalarTypeAdapters scalarTypeAdapters, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        if ((i & 2) != 0) {
            scalarTypeAdapters = ScalarTypeAdapters.DEFAULT;
        }
        return serialize(data, str, scalarTypeAdapters);
    }

    public static final String serialize(Operation.Data toJson, String indent, ScalarTypeAdapters scalarTypeAdapters) {
        Intrinsics.checkParameterIsNotNull(toJson, "$this$toJson");
        Intrinsics.checkParameterIsNotNull(indent, "indent");
        Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
        try {
            SimpleResponseWriter simpleResponseWriter = new SimpleResponseWriter(scalarTypeAdapters);
            toJson.marshaller().marshal(simpleResponseWriter);
            return simpleResponseWriter.toJson(indent);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
