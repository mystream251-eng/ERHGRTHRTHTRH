package com.apollographql.apollo.api;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.Operation.Data;
import com.apollographql.apollo.api.Operation.Variables;
import com.apollographql.apollo.api.internal.InputFieldMarshaller;
import com.apollographql.apollo.api.internal.InputFieldWriter;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.json.InputFieldJsonWriter;
import com.apollographql.apollo.api.internal.json.JsonWriter;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: Operation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u0000 \u001f*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u0003*\b\b\u0002\u0010\u0004*\u00020\u00052\u00020\u0006:\u0003\u001f !J\b\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J \u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\nH&J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u00132\u0006\u0010\u0016\u001a\u00020\bH&J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u00132\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u0017\u001a\u00020\u0011H&J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H&J\r\u0010\u001a\u001a\u00028\u0002H&¢\u0006\u0002\u0010\u001bJ\u0019\u0010\u001c\u001a\u0004\u0018\u00018\u00012\b\u0010\u001d\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\""}, d2 = {"Lcom/apollographql/apollo/api/Operation;", "D", "Lcom/apollographql/apollo/api/Operation$Data;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lcom/apollographql/apollo/api/Operation$Variables;", "", "composeRequestBody", "Lokio/ByteString;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "autoPersistQueries", "", "withQueryDocument", "name", "Lcom/apollographql/apollo/api/OperationName;", "operationId", "", "parse", "Lcom/apollographql/apollo/api/Response;", "source", "Lokio/BufferedSource;", "byteString", "queryDocument", "responseFieldMapper", "Lcom/apollographql/apollo/api/internal/ResponseFieldMapper;", "variables", "()Lcom/apollographql/apollo/api/Operation$Variables;", "wrapData", "data", "(Lcom/apollographql/apollo/api/Operation$Data;)Ljava/lang/Object;", "Companion", "Data", "Variables", "apollo-api"}, k = 1, mv = {1, 1, 16})
public interface Operation<D extends Data, T, V extends Variables> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Variables EMPTY_VARIABLES = new Variables();

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo/api/Operation$Data;", "", "marshaller", "Lcom/apollographql/apollo/api/internal/ResponseFieldMarshaller;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface Data {
        ResponseFieldMarshaller marshaller();
    }

    ByteString composeRequestBody();

    ByteString composeRequestBody(ScalarTypeAdapters scalarTypeAdapters);

    ByteString composeRequestBody(boolean autoPersistQueries, boolean withQueryDocument, ScalarTypeAdapters scalarTypeAdapters);

    OperationName name();

    String operationId();

    Response<T> parse(BufferedSource source) throws IOException;

    Response<T> parse(BufferedSource source, ScalarTypeAdapters scalarTypeAdapters) throws IOException;

    Response<T> parse(ByteString byteString) throws IOException;

    Response<T> parse(ByteString byteString, ScalarTypeAdapters scalarTypeAdapters) throws IOException;

    String queryDocument();

    ResponseFieldMapper<D> responseFieldMapper();

    V variables();

    T wrapData(D data);

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo/api/Operation$Variables;", "", "()V", "marshal", "", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "marshaller", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "valueMap", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static class Variables {
        public Map<String, Object> valueMap() {
            return MapsKt.emptyMap();
        }

        public InputFieldMarshaller marshaller() {
            return new InputFieldMarshaller() { // from class: com.apollographql.apollo.api.Operation$Variables$marshaller$1
                @Override // com.apollographql.apollo.api.internal.InputFieldMarshaller
                public void marshal(InputFieldWriter writer) {
                    Intrinsics.checkParameterIsNotNull(writer, "writer");
                }
            };
        }

        public final String marshal() throws IOException {
            return marshal(ScalarTypeAdapters.DEFAULT);
        }

        public final String marshal(ScalarTypeAdapters scalarTypeAdapters) throws IOException {
            Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
            Buffer buffer = new Buffer();
            JsonWriter jsonWriterOf = JsonWriter.INSTANCE.of(buffer);
            try {
                JsonWriter jsonWriter = jsonWriterOf;
                jsonWriter.setSerializeNulls(true);
                jsonWriter.beginObject();
                marshaller().marshal(new InputFieldJsonWriter(jsonWriter, scalarTypeAdapters));
                jsonWriter.endObject();
                if (jsonWriterOf != null) {
                    jsonWriterOf.close();
                }
                return buffer.readUtf8();
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

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo/api/Operation$Companion;", "", "()V", "EMPTY_VARIABLES", "Lcom/apollographql/apollo/api/Operation$Variables;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
