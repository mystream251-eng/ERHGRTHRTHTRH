package com.squareup.moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes2.dex */
final class StandardJsonAdapters {
    private static final String ERROR_FORMAT = "Expected %s but was %s at path %s";
    public static final JsonAdapter.Factory FACTORY = new JsonAdapter.Factory() { // from class: com.squareup.moshi.StandardJsonAdapters.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            if (!set.isEmpty()) {
                return null;
            }
            if (type == Boolean.TYPE) {
                return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER;
            }
            if (type == Byte.TYPE) {
                return StandardJsonAdapters.BYTE_JSON_ADAPTER;
            }
            if (type == Character.TYPE) {
                return StandardJsonAdapters.CHARACTER_JSON_ADAPTER;
            }
            if (type == Double.TYPE) {
                return StandardJsonAdapters.DOUBLE_JSON_ADAPTER;
            }
            if (type == Float.TYPE) {
                return StandardJsonAdapters.FLOAT_JSON_ADAPTER;
            }
            if (type == Integer.TYPE) {
                return StandardJsonAdapters.INTEGER_JSON_ADAPTER;
            }
            if (type == Long.TYPE) {
                return StandardJsonAdapters.LONG_JSON_ADAPTER;
            }
            if (type == Short.TYPE) {
                return StandardJsonAdapters.SHORT_JSON_ADAPTER;
            }
            if (type == Boolean.class) {
                return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER.nullSafe();
            }
            if (type == Byte.class) {
                return StandardJsonAdapters.BYTE_JSON_ADAPTER.nullSafe();
            }
            if (type == Character.class) {
                return StandardJsonAdapters.CHARACTER_JSON_ADAPTER.nullSafe();
            }
            if (type == Double.class) {
                return StandardJsonAdapters.DOUBLE_JSON_ADAPTER.nullSafe();
            }
            if (type == Float.class) {
                return StandardJsonAdapters.FLOAT_JSON_ADAPTER.nullSafe();
            }
            if (type == Integer.class) {
                return StandardJsonAdapters.INTEGER_JSON_ADAPTER.nullSafe();
            }
            if (type == Long.class) {
                return StandardJsonAdapters.LONG_JSON_ADAPTER.nullSafe();
            }
            if (type == Short.class) {
                return StandardJsonAdapters.SHORT_JSON_ADAPTER.nullSafe();
            }
            if (type == String.class) {
                return StandardJsonAdapters.STRING_JSON_ADAPTER.nullSafe();
            }
            if (type == Object.class) {
                return new ObjectJsonAdapter(moshi).nullSafe();
            }
            Class<?> rawType = Types.getRawType(type);
            if (rawType.isEnum()) {
                return new EnumJsonAdapter(rawType).nullSafe();
            }
            return null;
        }
    };
    static final JsonAdapter<Boolean> BOOLEAN_JSON_ADAPTER = new JsonAdapter<Boolean>() { // from class: com.squareup.moshi.StandardJsonAdapters.2
        public String toString() {
            return "JsonAdapter(Boolean)";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Boolean fromJson(JsonReader jsonReader) throws IOException {
            return Boolean.valueOf(jsonReader.nextBoolean());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool.booleanValue());
        }
    };
    static final JsonAdapter<Byte> BYTE_JSON_ADAPTER = new JsonAdapter<Byte>() { // from class: com.squareup.moshi.StandardJsonAdapters.3
        public String toString() {
            return "JsonAdapter(Byte)";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Byte fromJson(JsonReader jsonReader) throws IOException {
            return Byte.valueOf((byte) StandardJsonAdapters.rangeCheckNextInt(jsonReader, "a byte", -128, 255));
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Byte b) throws IOException {
            jsonWriter.value(b.intValue() & 255);
        }
    };
    static final JsonAdapter<Character> CHARACTER_JSON_ADAPTER = new JsonAdapter<Character>() { // from class: com.squareup.moshi.StandardJsonAdapters.4
        public String toString() {
            return "JsonAdapter(Character)";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Character fromJson(JsonReader jsonReader) throws IOException {
            String strNextString = jsonReader.nextString();
            if (strNextString.length() > 1) {
                throw new JsonDataException(String.format(StandardJsonAdapters.ERROR_FORMAT, "a char", Typography.quote + strNextString + Typography.quote, jsonReader.getPath()));
            }
            return Character.valueOf(strNextString.charAt(0));
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Character ch) throws IOException {
            jsonWriter.value(ch.toString());
        }
    };
    static final JsonAdapter<Double> DOUBLE_JSON_ADAPTER = new JsonAdapter<Double>() { // from class: com.squareup.moshi.StandardJsonAdapters.5
        public String toString() {
            return "JsonAdapter(Double)";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Double fromJson(JsonReader jsonReader) throws IOException {
            return Double.valueOf(jsonReader.nextDouble());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Double d) throws IOException {
            jsonWriter.value(d.doubleValue());
        }
    };
    static final JsonAdapter<Float> FLOAT_JSON_ADAPTER = new JsonAdapter<Float>() { // from class: com.squareup.moshi.StandardJsonAdapters.6
        public String toString() {
            return "JsonAdapter(Float)";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Float fromJson(JsonReader jsonReader) throws IOException {
            float fNextDouble = (float) jsonReader.nextDouble();
            if (!jsonReader.isLenient() && Float.isInfinite(fNextDouble)) {
                throw new JsonDataException("JSON forbids NaN and infinities: " + fNextDouble + " at path " + jsonReader.getPath());
            }
            return Float.valueOf(fNextDouble);
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Float f) throws IOException {
            if (f == null) {
                throw null;
            }
            jsonWriter.value(f);
        }
    };
    static final JsonAdapter<Integer> INTEGER_JSON_ADAPTER = new JsonAdapter<Integer>() { // from class: com.squareup.moshi.StandardJsonAdapters.7
        public String toString() {
            return "JsonAdapter(Integer)";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Integer fromJson(JsonReader jsonReader) throws IOException {
            return Integer.valueOf(jsonReader.nextInt());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Integer num) throws IOException {
            jsonWriter.value(num.intValue());
        }
    };
    static final JsonAdapter<Long> LONG_JSON_ADAPTER = new JsonAdapter<Long>() { // from class: com.squareup.moshi.StandardJsonAdapters.8
        public String toString() {
            return "JsonAdapter(Long)";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Long fromJson(JsonReader jsonReader) throws IOException {
            return Long.valueOf(jsonReader.nextLong());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Long l) throws IOException {
            jsonWriter.value(l.longValue());
        }
    };
    static final JsonAdapter<Short> SHORT_JSON_ADAPTER = new JsonAdapter<Short>() { // from class: com.squareup.moshi.StandardJsonAdapters.9
        public String toString() {
            return "JsonAdapter(Short)";
        }

        @Override // com.squareup.moshi.JsonAdapter
        public Short fromJson(JsonReader jsonReader) throws IOException {
            return Short.valueOf((short) StandardJsonAdapters.rangeCheckNextInt(jsonReader, "a short", -32768, 32767));
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Short sh) throws IOException {
            jsonWriter.value(sh.intValue());
        }
    };
    static final JsonAdapter<String> STRING_JSON_ADAPTER = new JsonAdapter<String>() { // from class: com.squareup.moshi.StandardJsonAdapters.10
        public String toString() {
            return "JsonAdapter(String)";
        }

        @Override // com.squareup.moshi.JsonAdapter
        public String fromJson(JsonReader jsonReader) throws IOException {
            return jsonReader.nextString();
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, String str) throws IOException {
            jsonWriter.value(str);
        }
    };

    private StandardJsonAdapters() {
    }

    static int rangeCheckNextInt(JsonReader jsonReader, String str, int i, int i2) throws IOException {
        int iNextInt = jsonReader.nextInt();
        if (iNextInt < i || iNextInt > i2) {
            throw new JsonDataException(String.format(ERROR_FORMAT, str, Integer.valueOf(iNextInt), jsonReader.getPath()));
        }
        return iNextInt;
    }

    static final class EnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {
        private final T[] constants;
        private final Class<T> enumType;
        private final String[] nameStrings;
        private final JsonReader.Options options;

        EnumJsonAdapter(Class<T> cls) {
            this.enumType = cls;
            try {
                T[] enumConstants = cls.getEnumConstants();
                this.constants = enumConstants;
                this.nameStrings = new String[enumConstants.length];
                for (int i = 0; i < this.constants.length; i++) {
                    T t = this.constants[i];
                    Json json = (Json) cls.getField(t.name()).getAnnotation(Json.class);
                    this.nameStrings[i] = json != null ? json.name() : t.name();
                }
                this.options = JsonReader.Options.of(this.nameStrings);
            } catch (NoSuchFieldException e) {
                throw new AssertionError("Missing field in " + cls.getName(), e);
            }
        }

        @Override // com.squareup.moshi.JsonAdapter
        public T fromJson(JsonReader jsonReader) throws IOException {
            int iSelectString = jsonReader.selectString(this.options);
            if (iSelectString != -1) {
                return this.constants[iSelectString];
            }
            throw new JsonDataException("Expected one of " + Arrays.asList(this.nameStrings) + " but was " + jsonReader.nextString() + " at path " + jsonReader.getPath());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(this.nameStrings[t.ordinal()]);
        }

        public String toString() {
            return "JsonAdapter(" + this.enumType.getName() + ")";
        }
    }

    static final class ObjectJsonAdapter extends JsonAdapter<Object> {
        private final Moshi moshi;

        public String toString() {
            return "JsonAdapter(Object)";
        }

        ObjectJsonAdapter(Moshi moshi) {
            this.moshi = moshi;
        }

        @Override // com.squareup.moshi.JsonAdapter
        public Object fromJson(JsonReader jsonReader) throws IOException {
            return jsonReader.readJsonValue();
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
            Class<?> cls = obj.getClass();
            if (cls == Object.class) {
                jsonWriter.beginObject();
                jsonWriter.endObject();
            } else {
                this.moshi.adapter(toJsonType(cls), Util.NO_ANNOTATIONS).toJson(jsonWriter, obj);
            }
        }

        private Class<?> toJsonType(Class<?> cls) {
            if (Map.class.isAssignableFrom(cls)) {
                return Map.class;
            }
            return Collection.class.isAssignableFrom(cls) ? Collection.class : cls;
        }
    }
}
