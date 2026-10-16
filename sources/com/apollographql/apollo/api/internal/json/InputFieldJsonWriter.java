package com.apollographql.apollo.api.internal.json;

import com.apollographql.apollo.api.CustomTypeValue;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.InputFieldMarshaller;
import com.apollographql.apollo.api.internal.InputFieldWriter;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InputFieldJsonWriter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001&B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\rJ\"\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u0011H\u0016J\u001f\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0002\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001f\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001dJ$\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0002\b\u0003\u0018\u00010\u001fH\u0016J\u001a\u0010 \u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010!H\u0016J\u001a\u0010\"\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001a\u0010%\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/InputFieldJsonWriter;", "Lcom/apollographql/apollo/api/internal/InputFieldWriter;", "jsonWriter", "Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "(Lcom/apollographql/apollo/api/internal/json/JsonWriter;Lcom/apollographql/apollo/api/ScalarTypeAdapters;)V", "writeBoolean", "", "fieldName", "", "value", "", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "", "writeDouble", "", "(Ljava/lang/String;Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "writeList", "listWriter", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter;", "writeLong", "", "(Ljava/lang/String;Ljava/lang/Long;)V", "writeMap", "", "writeNumber", "", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "writeString", "JsonListItemWriter", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class InputFieldJsonWriter implements InputFieldWriter {
    private final JsonWriter jsonWriter;
    private final ScalarTypeAdapters scalarTypeAdapters;

    public InputFieldJsonWriter(JsonWriter jsonWriter, ScalarTypeAdapters scalarTypeAdapters) {
        Intrinsics.checkParameterIsNotNull(jsonWriter, "jsonWriter");
        Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
        this.jsonWriter = jsonWriter;
        this.scalarTypeAdapters = scalarTypeAdapters;
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeList(String fieldName, Function1<? super InputFieldWriter.ListItemWriter, Unit> block) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        Intrinsics.checkParameterIsNotNull(block, "block");
        InputFieldWriter.DefaultImpls.writeList(this, fieldName, block);
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeString(String fieldName, String value) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (value == null) {
            this.jsonWriter.name(fieldName).nullValue();
        } else {
            this.jsonWriter.name(fieldName).value(value);
        }
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeInt(String fieldName, Integer value) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (value == null) {
            this.jsonWriter.name(fieldName).nullValue();
        } else {
            this.jsonWriter.name(fieldName).value(value);
        }
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeLong(String fieldName, Long value) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (value == null) {
            this.jsonWriter.name(fieldName).nullValue();
        } else {
            this.jsonWriter.name(fieldName).value(value.longValue());
        }
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeDouble(String fieldName, Double value) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (value == null) {
            this.jsonWriter.name(fieldName).nullValue();
        } else {
            this.jsonWriter.name(fieldName).value(value.doubleValue());
        }
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeNumber(String fieldName, Number value) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (value == null) {
            this.jsonWriter.name(fieldName).nullValue();
        } else {
            this.jsonWriter.name(fieldName).value(value);
        }
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeBoolean(String fieldName, Boolean value) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (value == null) {
            this.jsonWriter.name(fieldName).nullValue();
        } else {
            this.jsonWriter.name(fieldName).value(value);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeCustom(String fieldName, ScalarType scalarType, Object value) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
        if (value == null) {
            this.jsonWriter.name(fieldName).nullValue();
            return;
        }
        CustomTypeValue<?> customTypeValueEncode = this.scalarTypeAdapters.adapterFor(scalarType).encode(value);
        if (customTypeValueEncode instanceof CustomTypeValue.GraphQLString) {
            writeString(fieldName, (String) ((CustomTypeValue.GraphQLString) customTypeValueEncode).value);
            return;
        }
        if (customTypeValueEncode instanceof CustomTypeValue.GraphQLBoolean) {
            writeBoolean(fieldName, (Boolean) ((CustomTypeValue.GraphQLBoolean) customTypeValueEncode).value);
            return;
        }
        if (customTypeValueEncode instanceof CustomTypeValue.GraphQLNumber) {
            writeNumber(fieldName, (Number) ((CustomTypeValue.GraphQLNumber) customTypeValueEncode).value);
            return;
        }
        if (customTypeValueEncode instanceof CustomTypeValue.GraphQLJsonObject) {
            Utils.writeToJson(((CustomTypeValue.GraphQLJsonObject) customTypeValueEncode).value, this.jsonWriter.name(fieldName));
        } else if (customTypeValueEncode instanceof CustomTypeValue.GraphQLJsonList) {
            Utils.writeToJson(((CustomTypeValue.GraphQLJsonList) customTypeValueEncode).value, this.jsonWriter.name(fieldName));
        }
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeObject(String fieldName, InputFieldMarshaller marshaller) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (marshaller == null) {
            this.jsonWriter.name(fieldName).nullValue();
            return;
        }
        this.jsonWriter.name(fieldName).beginObject();
        marshaller.marshal(this);
        this.jsonWriter.endObject();
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeList(String fieldName, InputFieldWriter.ListWriter listWriter) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (listWriter == null) {
            this.jsonWriter.name(fieldName).nullValue();
            return;
        }
        this.jsonWriter.name(fieldName).beginArray();
        listWriter.write(new JsonListItemWriter(this.jsonWriter, this.scalarTypeAdapters));
        this.jsonWriter.endArray();
    }

    @Override // com.apollographql.apollo.api.internal.InputFieldWriter
    public void writeMap(String fieldName, Map<String, ?> value) throws IOException {
        Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
        if (value == null) {
            this.jsonWriter.name(fieldName).nullValue();
        } else {
            this.jsonWriter.name(fieldName);
            Utils.writeToJson(value, this.jsonWriter);
        }
    }

    /* JADX INFO: compiled from: InputFieldJsonWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u001a\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u000fH\u0016J\u0017\u0010\u0010\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u0012\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0017\u0010\u0019\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u001aH\u0016¢\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0002\b\u0003\u0018\u00010\u001dH\u0016J\u0012\u0010\u001f\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010$\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u001eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/InputFieldJsonWriter$JsonListItemWriter;", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListItemWriter;", "jsonWriter", "Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "scalarTypeAdapters", "Lcom/apollographql/apollo/api/ScalarTypeAdapters;", "(Lcom/apollographql/apollo/api/internal/json/JsonWriter;Lcom/apollographql/apollo/api/ScalarTypeAdapters;)V", "writeBoolean", "", "value", "", "(Ljava/lang/Boolean;)V", "writeCustom", "scalarType", "Lcom/apollographql/apollo/api/ScalarType;", "", "writeDouble", "", "(Ljava/lang/Double;)V", "writeInt", "", "(Ljava/lang/Integer;)V", "writeList", "listWriter", "Lcom/apollographql/apollo/api/internal/InputFieldWriter$ListWriter;", "writeLong", "", "(Ljava/lang/Long;)V", "writeMap", "", "", "writeNumber", "", "writeObject", "marshaller", "Lcom/apollographql/apollo/api/internal/InputFieldMarshaller;", "writeString", "apollo-api"}, k = 1, mv = {1, 1, 16})
    private static final class JsonListItemWriter implements InputFieldWriter.ListItemWriter {
        private final JsonWriter jsonWriter;
        private final ScalarTypeAdapters scalarTypeAdapters;

        public JsonListItemWriter(JsonWriter jsonWriter, ScalarTypeAdapters scalarTypeAdapters) {
            Intrinsics.checkParameterIsNotNull(jsonWriter, "jsonWriter");
            Intrinsics.checkParameterIsNotNull(scalarTypeAdapters, "scalarTypeAdapters");
            this.jsonWriter = jsonWriter;
            this.scalarTypeAdapters = scalarTypeAdapters;
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeList(Function1<? super InputFieldWriter.ListItemWriter, Unit> block) throws IOException {
            Intrinsics.checkParameterIsNotNull(block, "block");
            InputFieldWriter.ListItemWriter.DefaultImpls.writeList(this, block);
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeString(String value) throws IOException {
            if (value == null) {
                this.jsonWriter.nullValue();
            } else {
                this.jsonWriter.value(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeInt(Integer value) throws IOException {
            if (value == null) {
                this.jsonWriter.nullValue();
            } else {
                this.jsonWriter.value(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeLong(Long value) throws IOException {
            if (value == null) {
                this.jsonWriter.nullValue();
            } else {
                this.jsonWriter.value(value.longValue());
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeDouble(Double value) throws IOException {
            if (value == null) {
                this.jsonWriter.nullValue();
            } else {
                this.jsonWriter.value(value.doubleValue());
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeNumber(Number value) throws IOException {
            if (value == null) {
                this.jsonWriter.nullValue();
            } else {
                this.jsonWriter.value(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeBoolean(Boolean value) throws IOException {
            if (value == null) {
                this.jsonWriter.nullValue();
            } else {
                this.jsonWriter.value(value);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeMap(Map<String, ?> value) throws IOException {
            Utils.writeToJson(value, this.jsonWriter);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeCustom(ScalarType scalarType, Object value) throws IOException {
            Intrinsics.checkParameterIsNotNull(scalarType, "scalarType");
            if (value == null) {
                this.jsonWriter.nullValue();
                return;
            }
            CustomTypeValue<?> customTypeValueEncode = this.scalarTypeAdapters.adapterFor(scalarType).encode(value);
            if (customTypeValueEncode instanceof CustomTypeValue.GraphQLString) {
                writeString((String) ((CustomTypeValue.GraphQLString) customTypeValueEncode).value);
                return;
            }
            if (customTypeValueEncode instanceof CustomTypeValue.GraphQLBoolean) {
                writeBoolean((Boolean) ((CustomTypeValue.GraphQLBoolean) customTypeValueEncode).value);
                return;
            }
            if (customTypeValueEncode instanceof CustomTypeValue.GraphQLNumber) {
                writeNumber((Number) ((CustomTypeValue.GraphQLNumber) customTypeValueEncode).value);
            } else if (customTypeValueEncode instanceof CustomTypeValue.GraphQLJsonObject) {
                Utils.writeToJson(((CustomTypeValue.GraphQLJsonObject) customTypeValueEncode).value, this.jsonWriter);
            } else if (customTypeValueEncode instanceof CustomTypeValue.GraphQLJsonList) {
                Utils.writeToJson(((CustomTypeValue.GraphQLJsonList) customTypeValueEncode).value, this.jsonWriter);
            }
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeObject(InputFieldMarshaller marshaller) throws IOException {
            if (marshaller == null) {
                this.jsonWriter.nullValue();
                return;
            }
            this.jsonWriter.beginObject();
            marshaller.marshal(new InputFieldJsonWriter(this.jsonWriter, this.scalarTypeAdapters));
            this.jsonWriter.endObject();
        }

        @Override // com.apollographql.apollo.api.internal.InputFieldWriter.ListItemWriter
        public void writeList(InputFieldWriter.ListWriter listWriter) throws IOException {
            if (listWriter == null) {
                this.jsonWriter.nullValue();
                return;
            }
            this.jsonWriter.beginArray();
            listWriter.write(new JsonListItemWriter(this.jsonWriter, this.scalarTypeAdapters));
            this.jsonWriter.endArray();
        }
    }
}
