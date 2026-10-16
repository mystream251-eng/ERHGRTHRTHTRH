package com.apollographql.apollo.api.internal.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/Utils;", "", "()V", "writeToJson", "", "value", "jsonWriter", "Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class Utils {
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    @JvmStatic
    public static final void writeToJson(Object value, JsonWriter jsonWriter) throws IOException {
        Intrinsics.checkParameterIsNotNull(jsonWriter, "jsonWriter");
        if (value == null) {
            jsonWriter.nullValue();
            return;
        }
        if (value instanceof Map) {
            JsonWriter jsonWriterBeginObject = jsonWriter.beginObject();
            for (Map.Entry entry : ((Map) value).entrySet()) {
                Object key = entry.getKey();
                Object value2 = entry.getValue();
                jsonWriter.name(String.valueOf(key));
                writeToJson(value2, jsonWriterBeginObject);
            }
            jsonWriterBeginObject.endObject();
            return;
        }
        if (value instanceof List) {
            JsonWriter jsonWriterBeginArray = jsonWriter.beginArray();
            Iterator it = ((Iterable) value).iterator();
            while (it.hasNext()) {
                writeToJson(it.next(), jsonWriterBeginArray);
            }
            jsonWriterBeginArray.endArray();
            return;
        }
        if (value instanceof Boolean) {
            jsonWriter.value((Boolean) value);
        } else if (value instanceof Number) {
            jsonWriter.value((Number) value);
        } else {
            jsonWriter.value(value.toString());
        }
    }
}
