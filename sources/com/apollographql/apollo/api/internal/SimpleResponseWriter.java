package com.apollographql.apollo.api.internal;

import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;

/* JADX INFO: compiled from: SimpleResponseWriter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001(B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u0007J\u001f\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016J\u001f\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0002\u0010\u0016J\u0012\u0010\u0017\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001f\u0010\u001a\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u001bH\u0016¢\u0006\u0002\u0010\u001cJ4\u0010\u001d\u001a\u00020\f\"\u0004\b\u0000\u0010\u001e2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001e\u0018\u00010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001e0\"H\u0016J\u001f\u0010#\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010$H\u0016¢\u0006\u0002\u0010%J\u001a\u0010&\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010'\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007H\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/apollographql/apollo/api/internal/SimpleResponseWriter;", "Lcom/apollographql/apollo/api/internal/ResponseWriter;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "(Lcom/apollographql/apollo/api/ScalarTypeAdapters;)V", "data", "", "", "", "toJson", "indent", "writeBoolean", "", "field", "Lcom/apollographql/apollo/api/ResponseField;", "value", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Boolean;)V", "writeCustom", "Lcom/apollographql/apollo/api/ResponseField$CustomTypeField;", "writeDouble", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Double;)V", "writeFragment", "marshaller", "Lcom/apollographql/apollo/api/internal/ResponseFieldMarshaller;", "writeInt", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Integer;)V", "writeList", ExifInterface.GPS_DIRECTION_TRUE, "values", "", "listWriter", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListWriter;", "writeLong", "", "(Lcom/apollographql/apollo/api/ResponseField;Ljava/lang/Long;)V", "writeObject", "writeString", "CustomListItemWriter", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class SimpleResponseWriter implements ResponseWriter {
    private final Map<String, Object> data;
    private final ScalarTypeAdapters scalarTypeAdapters;

    public SimpleResponseWriter(ScalarTypeAdapters scalarTypeAdapters) {
        Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
        this.scalarTypeAdapters = scalarTypeAdapters;
        this.data = new LinkedHashMap();
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public <T> void writeList(ResponseField field, List<? extends T> list, Function2<? super List<? extends T>, ? super ResponseWriter.ListItemWriter, Unit> block) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(block, "block");
        ResponseWriter.DefaultImpls.writeList(this, field, list, block);
    }

    public final String toJson(String indent) throws IOException {
        Buffer buffer = new Buffer();
        JsonWriter jsonWriterOf = JsonWriter.INSTANCE.of(buffer);
        try {
            JsonWriter jsonWriter = jsonWriterOf;
            jsonWriter.setIndent(indent);
            jsonWriter.beginObject();
            jsonWriter.name("data");
            com.apollographql.apollo.api.internal.json.Utils.writeToJson(this.data, jsonWriter);
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

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeString(ResponseField field, String value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        this.data.put(field.getResponseName(), value);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeInt(ResponseField field, Integer value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        this.data.put(field.getResponseName(), value);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeLong(ResponseField field, Long value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        this.data.put(field.getResponseName(), value);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeDouble(ResponseField field, Double value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        this.data.put(field.getResponseName(), value);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeBoolean(ResponseField field, Boolean value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        this.data.put(field.getResponseName(), value);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeCustom(ResponseField.CustomTypeField field, Object value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (value == null) {
            this.data.put(field.getResponseName(), null);
        } else {
            this.data.put(field.getResponseName(), this.scalarTypeAdapters.adapterFor(field.getScalarType()).encode(value).value);
        }
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeObject(ResponseField field, ResponseFieldMarshaller marshaller) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        if (marshaller == null) {
            this.data.put(field.getResponseName(), null);
            return;
        }
        SimpleResponseWriter simpleResponseWriter = new SimpleResponseWriter(this.scalarTypeAdapters);
        marshaller.marshal(simpleResponseWriter);
        this.data.put(field.getResponseName(), simpleResponseWriter.data);
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public void writeFragment(ResponseFieldMarshaller marshaller) {
        if (marshaller != null) {
            marshaller.marshal(this);
        }
    }

    @Override // com.apollographql.apollo.api.internal.ResponseWriter
    public <T> void writeList(ResponseField field, List<? extends T> values, ResponseWriter.ListWriter<T> listWriter) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Intrinsics.checkParameterIsNotNull(listWriter, "listWriter");
        if (values == null) {
            this.data.put(field.getResponseName(), null);
            return;
        }
        CustomListItemWriter customListItemWriter = new CustomListItemWriter(this.scalarTypeAdapters);
        listWriter.write(values, customListItemWriter);
        this.data.put(field.getResponseName(), customListItemWriter.getData$apollo_api());
    }

    /* JADX INFO: compiled from: SimpleResponseWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0017\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u0010\u000fJ\u001a\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0016J\u0017\u0010\u0013\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0018J,\u0010\u0019\u001a\u00020\f\"\u0004\b\u0000\u0010\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001eH\u0016J\u0017\u0010\u001f\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0002\u0010!J\u0012\u0010\"\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010%\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010&H\u0016R(\u0010\u0005\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0007`\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/apollographql/apollo/api/internal/SimpleResponseWriter$CustomListItemWriter;", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListItemWriter;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "(Lcom/apollographql/apollo/api/ScalarTypeAdapters;)V", "data", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getData$apollo_api", "()Ljava/util/ArrayList;", "writeBoolean", "", "value", "", "(Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "writeDouble", "", "(Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/Integer;)V", "writeList", ExifInterface.GPS_DIRECTION_TRUE, "items", "", "listWriter", "Lcom/apollographql/apollo/api/internal/ResponseWriter$ListWriter;", "writeLong", "", "(Ljava/lang/Long;)V", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/ResponseFieldMarshaller;", "writeString", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    private static final class CustomListItemWriter implements ResponseWriter.ListItemWriter {
        private final ArrayList<Object> data;
        private final ScalarTypeAdapters scalarTypeAdapters;

        public CustomListItemWriter(ScalarTypeAdapters scalarTypeAdapters) {
            Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
            this.scalarTypeAdapters = scalarTypeAdapters;
            this.data = new ArrayList<>();
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public <T> void writeList(List<? extends T> list, Function2<? super List<? extends T>, ? super ResponseWriter.ListItemWriter, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            ResponseWriter.ListItemWriter.DefaultImpls.writeList(this, list, block);
        }

        public final ArrayList<Object> getData$apollo_api() {
            return this.data;
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeString(String value) {
            this.data.add(value);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeInt(Integer value) {
            this.data.add(value);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeLong(Long value) {
            this.data.add(value);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeDouble(Double value) {
            this.data.add(value);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeBoolean(Boolean value) {
            this.data.add(value);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeCustom(ScalarType scalarType, Object value) {
            Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
            if (value == null) {
                this.data.add(null);
            } else {
                this.data.add(this.scalarTypeAdapters.adapterFor(scalarType).encode(value).value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public void writeObject(ResponseFieldMarshaller marshaller) {
            if (marshaller == null) {
                this.data.add(null);
                return;
            }
            SimpleResponseWriter simpleResponseWriter = new SimpleResponseWriter(this.scalarTypeAdapters);
            marshaller.marshal(simpleResponseWriter);
            this.data.add(simpleResponseWriter.data);
        }

        @Override // com.apollographql.apollo.api.internal.ResponseWriter.ListItemWriter
        public <T> void writeList(List<? extends T> items, ResponseWriter.ListWriter<T> listWriter) {
            Intrinsics.checkParameterIsNotNull(listWriter, "listWriter");
            if (items == null) {
                this.data.add(null);
                return;
            }
            CustomListItemWriter customListItemWriter = new CustomListItemWriter(this.scalarTypeAdapters);
            listWriter.write(items, customListItemWriter);
            this.data.add(customListItemWriter.data);
        }
    }
}
