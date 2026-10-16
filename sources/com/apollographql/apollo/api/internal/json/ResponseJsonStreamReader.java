package com.apollographql.apollo.api.internal.json;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResponseJsonStreamReader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001:\u0002-.B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\t\u0010\u000f\u001a\u00020\u0006H\u0086\u0002J\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0002\u0010\u0017J,\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u0001H\u001a\u0018\u00010\u0019\"\u0004\b\u0000\u0010\u001a2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001cJ\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020!J)\u0010\"\u001a\u0004\u0018\u0001H\u001a\"\u0004\b\u0000\u0010\u001a2\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001a0$¢\u0006\u0002\u0010%J\u0012\u0010&\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\u0010\u0010'\u001a\u0004\u0018\u00010!2\u0006\u0010\u000e\u001a\u00020\u0006J\u0010\u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0019J\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020!\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010*J\u0006\u0010+\u001a\u00020\rJ\u0016\u0010,\u001a\u0012\u0012\u0004\u0012\u00020!\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010*R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0007R\u0014\u0010\t\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0007R\u0011\u0010\u000b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader;", "", "jsonReader", "Lcom/apollographql/apollo/api/internal/json/JsonReader;", "(Lcom/apollographql/apollo/api/internal/json/JsonReader;)V", "isNextBoolean", "", "()Z", "isNextList", "isNextNull", "isNextNumber", "isNextObject", "checkNextValue", "", "optional", "hasNext", "nextBoolean", "(Z)Ljava/lang/Boolean;", "nextDouble", "", "(Z)Ljava/lang/Double;", "nextInt", "", "(Z)Ljava/lang/Integer;", "nextList", "", ExifInterface.GPS_DIRECTION_TRUE, "listReader", "Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader$ListReader;", "nextLong", "", "(Z)Ljava/lang/Long;", "nextName", "", "nextObject", "objectReader", "Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader$ObjectReader;", "(ZLcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader$ObjectReader;)Ljava/lang/Object;", "nextScalar", "nextString", "readList", "readObject", "", "skipNext", "toMap", "ListReader", "ObjectReader", "apollo-api"}, k = 1, mv = {1, 1, 16})
public class ResponseJsonStreamReader {
    private final JsonReader jsonReader;

    /* JADX INFO: compiled from: ResponseJsonStreamReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader$ListReader;", ExifInterface.GPS_DIRECTION_TRUE, "", "read", "reader", "Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader;", "(Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader;)Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ListReader<T> {
        T read(ResponseJsonStreamReader reader) throws IOException;
    }

    /* JADX INFO: compiled from: ResponseJsonStreamReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader$ObjectReader;", ExifInterface.GPS_DIRECTION_TRUE, "", "read", "reader", "Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader;", "(Lcom/apollographql/apollo/api/internal/json/ResponseJsonStreamReader;)Ljava/lang/Object;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public interface ObjectReader<T> {
        T read(ResponseJsonStreamReader reader) throws IOException;
    }

    public ResponseJsonStreamReader(JsonReader jsonReader) {
        Intrinsics.checkParameterIsNotNull(jsonReader, "jsonReader");
        this.jsonReader = jsonReader;
    }

    public final boolean hasNext() throws IOException {
        return this.jsonReader.hasNext();
    }

    public final String nextName() throws IOException {
        return this.jsonReader.nextName();
    }

    public final void skipNext() throws IOException {
        this.jsonReader.skipValue();
    }

    public final String nextString(boolean optional) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() == JsonReader.Token.NULL) {
            return (String) this.jsonReader.nextNull();
        }
        return this.jsonReader.nextString();
    }

    public final Integer nextInt(boolean optional) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() == JsonReader.Token.NULL) {
            return (Integer) this.jsonReader.nextNull();
        }
        return Integer.valueOf(this.jsonReader.nextInt());
    }

    public final Long nextLong(boolean optional) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() == JsonReader.Token.NULL) {
            return (Long) this.jsonReader.nextNull();
        }
        return Long.valueOf(this.jsonReader.nextLong());
    }

    public final Double nextDouble(boolean optional) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() == JsonReader.Token.NULL) {
            return (Double) this.jsonReader.nextNull();
        }
        return Double.valueOf(this.jsonReader.nextDouble());
    }

    public final Boolean nextBoolean(boolean optional) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() == JsonReader.Token.NULL) {
            return (Boolean) this.jsonReader.nextNull();
        }
        return Boolean.valueOf(this.jsonReader.nextBoolean());
    }

    public final <T> T nextObject(boolean optional, ObjectReader<T> objectReader) throws IOException {
        Intrinsics.checkParameterIsNotNull(objectReader, "objectReader");
        checkNextValue(optional);
        if (this.jsonReader.peek() == JsonReader.Token.NULL) {
            return (T) this.jsonReader.nextNull();
        }
        this.jsonReader.beginObject();
        T t = objectReader.read(this);
        this.jsonReader.endObject();
        return t;
    }

    public final <T> List<T> nextList(boolean optional, ListReader<T> listReader) throws IOException {
        Intrinsics.checkParameterIsNotNull(listReader, "listReader");
        checkNextValue(optional);
        if (this.jsonReader.peek() == JsonReader.Token.NULL) {
            return (List) this.jsonReader.nextNull();
        }
        this.jsonReader.beginArray();
        ArrayList arrayList = new ArrayList();
        while (this.jsonReader.hasNext()) {
            arrayList.add(listReader.read(this));
        }
        this.jsonReader.endArray();
        return arrayList;
    }

    public Object nextScalar(boolean optional) throws IOException {
        checkNextValue(optional);
        if (isNextNull()) {
            skipNext();
            Unit unit = Unit.INSTANCE;
            return null;
        }
        if (isNextBoolean()) {
            return nextBoolean(false);
        }
        if (!isNextNumber()) {
            return nextString(false);
        }
        String strNextString = nextString(false);
        if (strNextString == null) {
            Intrinsics.throwNpe();
        }
        return new BigDecimal(strNextString);
    }

    public final Map<String, Object> readObject() throws IOException {
        return (Map) nextObject(false, new ObjectReader<Map<String, ? extends Object>>() { // from class: com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader.readObject.1
            @Override // com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader.ObjectReader
            public Map<String, ? extends Object> read(ResponseJsonStreamReader reader) throws IOException {
                Intrinsics.checkParameterIsNotNull(reader, "reader");
                return reader.toMap();
            }
        });
    }

    public final List<Object> readList() throws IOException {
        return nextList(false, new ListReader<Object>() { // from class: com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader.readList.1
            @Override // com.apollographql.apollo.api.internal.json.ResponseJsonStreamReader.ListReader
            public Object read(ResponseJsonStreamReader reader) throws IOException {
                Intrinsics.checkParameterIsNotNull(reader, "reader");
                if (ResponseJsonStreamReader.this.isNextList()) {
                    return ResponseJsonStreamReader.this.readList();
                }
                return ResponseJsonStreamReader.this.isNextObject() ? ResponseJsonStreamReader.this.readObject() : reader.nextScalar(true);
            }
        });
    }

    public final Map<String, Object> toMap() throws IOException {
        if (isNextObject()) {
            return readObject();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (hasNext()) {
            String strNextName = nextName();
            if (isNextNull()) {
                skipNext();
                Unit unit = Unit.INSTANCE;
                linkedHashMap.put(strNextName, null);
            } else if (isNextObject()) {
                linkedHashMap.put(strNextName, readObject());
            } else if (isNextList()) {
                linkedHashMap.put(strNextName, readList());
            } else {
                linkedHashMap.put(strNextName, nextScalar(true));
            }
        }
        return linkedHashMap;
    }

    public final boolean isNextObject() throws IOException {
        return this.jsonReader.peek() == JsonReader.Token.BEGIN_OBJECT;
    }

    public final boolean isNextList() throws IOException {
        return this.jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY;
    }

    private final boolean isNextNull() throws IOException {
        return this.jsonReader.peek() == JsonReader.Token.NULL;
    }

    private final boolean isNextBoolean() throws IOException {
        return this.jsonReader.peek() == JsonReader.Token.BOOLEAN;
    }

    private final boolean isNextNumber() throws IOException {
        return this.jsonReader.peek() == JsonReader.Token.NUMBER;
    }

    private final void checkNextValue(boolean optional) throws IOException {
        if (!optional && this.jsonReader.peek() == JsonReader.Token.NULL) {
            throw new NullPointerException("corrupted response reader, expected non null value");
        }
    }
}
