package com.apollographql.apollo.api.internal;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.json.JsonWriter;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;

/* JADX INFO: compiled from: OperationRequestBodyComposer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo/api/internal/OperationRequestBodyComposer;", "", "()V", "compose", "Lokio/ByteString;", "operation", "Lcom/apollographql/apollo/api/Operation;", "autoPersistQueries", "", "withQueryDocument", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class OperationRequestBodyComposer {
    public static final OperationRequestBodyComposer INSTANCE = new OperationRequestBodyComposer();

    private OperationRequestBodyComposer() {
    }

    @JvmStatic
    public static final ByteString compose(Operation<?, ?, ?> operation, boolean autoPersistQueries, boolean withQueryDocument, ScalarTypeAdapters scalarTypeAdapters) throws IOException {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
        Buffer buffer = new Buffer();
        JsonWriter jsonWriterOf = JsonWriter.INSTANCE.of(buffer);
        try {
            JsonWriter jsonWriter = jsonWriterOf;
            jsonWriter.setSerializeNulls(true);
            jsonWriter.beginObject();
            jsonWriter.name("operationName").value(operation.name().name());
            jsonWriter.name("variables").jsonValue(operation.variables().marshal(scalarTypeAdapters));
            if (autoPersistQueries) {
                jsonWriter.name("extensions");
                jsonWriter.beginObject();
                jsonWriter.name("persistedQuery");
                jsonWriter.beginObject();
                jsonWriter.name("version").value(1L);
                jsonWriter.name("sha256Hash").value(operation.operationId());
                jsonWriter.endObject();
                jsonWriter.endObject();
            }
            if (!autoPersistQueries || withQueryDocument) {
                jsonWriter.name(SearchIntents.EXTRA_QUERY).value(operation.queryDocument());
            }
            jsonWriter.endObject();
            if (jsonWriterOf != null) {
                jsonWriterOf.close();
            }
            return buffer.readByteString();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (jsonWriterOf != null) {
                    try {
                        jsonWriterOf.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }
}
