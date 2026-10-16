package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.api.internal.json.BufferedSourceJsonReader;
import com.apollographql.apollo.api.internal.json.JsonWriter;
import com.apollographql.apollo.cache.normalized.internal.CacheJsonStreamReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: RecordFieldJsonAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0005J \u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u001c\u0010\n\u001a\u00020\u00052\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/RecordFieldJsonAdapter;", "", "()V", "from", "", "", "jsonFieldSource", "fromBufferSource", "bufferedFieldSource", "Lokio/BufferedSource;", "toJson", "fields", "Companion", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
public final class RecordFieldJsonAdapter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "RecordFieldJsonAdapter()", imports = {}))
    @JvmStatic
    public static final RecordFieldJsonAdapter create() {
        return INSTANCE.create();
    }

    public final String toJson(Map<String, ? extends Object> fields) throws IOException {
        Intrinsics.checkParameterIsNotNull(fields, "fields");
        Buffer buffer = new Buffer();
        JsonWriter jsonWriterOf = JsonWriter.INSTANCE.of(buffer);
        try {
            JsonWriter jsonWriter = jsonWriterOf;
            jsonWriter.setSerializeNulls(true);
            try {
                jsonWriter.beginObject();
                for (Map.Entry<String, ? extends Object> entry : fields.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    jsonWriter.name(key);
                    INSTANCE.writeJsonValue(value, jsonWriter);
                }
                jsonWriter.endObject();
                jsonWriter.close();
                String utf8 = buffer.readUtf8();
                if (jsonWriterOf != null) {
                    jsonWriterOf.close();
                }
                return utf8;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    private final Map<String, Object> fromBufferSource(BufferedSource bufferedFieldSource) throws IOException {
        return new CacheJsonStreamReader(new BufferedSourceJsonReader(bufferedFieldSource)).toMap();
    }

    public final Map<String, Object> from(String jsonFieldSource) throws IOException {
        Intrinsics.checkParameterIsNotNull(jsonFieldSource, "jsonFieldSource");
        return fromBufferSource(new Buffer().write(ByteString.INSTANCE.encodeUtf8(jsonFieldSource)));
    }

    /* JADX INFO: compiled from: RecordFieldJsonAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/cache/normalized/RecordFieldJsonAdapter$Companion;", "", "()V", "create", "Lcom/apollographql/apollo/cache/normalized/RecordFieldJsonAdapter;", "writeJsonValue", "", "value", "jsonWriter", "Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "apollo-normalized-cache-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "RecordFieldJsonAdapter()", imports = {}))
        @JvmStatic
        public final RecordFieldJsonAdapter create() {
            return new RecordFieldJsonAdapter();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void writeJsonValue(Object value, JsonWriter jsonWriter) throws IOException {
            if (value == null) {
                jsonWriter.nullValue();
                return;
            }
            if (value instanceof String) {
                jsonWriter.value((String) value);
                return;
            }
            if (value instanceof Boolean) {
                jsonWriter.value((Boolean) value);
                return;
            }
            if (value instanceof Number) {
                jsonWriter.value((Number) value);
                return;
            }
            if (value instanceof CacheReference) {
                jsonWriter.value(((CacheReference) value).serialize());
                return;
            }
            if (value instanceof List) {
                jsonWriter.beginArray();
                Iterator it = ((Iterable) value).iterator();
                while (it.hasNext()) {
                    RecordFieldJsonAdapter.INSTANCE.writeJsonValue(it.next(), jsonWriter);
                }
                jsonWriter.endArray();
                return;
            }
            if (value instanceof Map) {
                jsonWriter.beginObject();
                if (value != null) {
                    for (Map.Entry entry : ((Map) value).entrySet()) {
                        jsonWriter.name((String) entry.getKey());
                        writeJsonValue(entry.getValue(), jsonWriter);
                    }
                    jsonWriter.endObject();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
            }
            throw new IllegalStateException(("Unsupported record value type: " + Reflection.getOrCreateKotlinClass(value.getClass()).getQualifiedName()).toString());
        }
    }
}
