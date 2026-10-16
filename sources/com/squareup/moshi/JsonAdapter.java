package com.squareup.moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;

/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonAdapter<T> {

    public interface Factory {
        @Nullable
        JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi);
    }

    @Nullable
    public abstract T fromJson(JsonReader jsonReader) throws IOException;

    public abstract void toJson(JsonWriter jsonWriter, @Nullable T t) throws IOException;

    @Nullable
    public final T fromJson(BufferedSource bufferedSource) throws IOException {
        return fromJson(JsonReader.of(bufferedSource));
    }

    @Nullable
    public final T fromJson(String str) throws IOException {
        return fromJson(new Buffer().writeUtf8(str));
    }

    public final void toJson(BufferedSink bufferedSink, @Nullable T t) throws IOException {
        toJson(JsonWriter.of(bufferedSink), t);
    }

    public final String toJson(@Nullable T t) {
        Buffer buffer = new Buffer();
        try {
            toJson(buffer, t);
            return buffer.readUtf8();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    @Nullable
    public final Object toJsonValue(@Nullable T t) {
        JsonValueWriter jsonValueWriter = new JsonValueWriter();
        try {
            toJson(jsonValueWriter, t);
            return jsonValueWriter.root();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    @Nullable
    public final T fromJsonValue(@Nullable Object obj) {
        try {
            return fromJson(new JsonValueReader(obj));
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final JsonAdapter<T> serializeNulls() {
        return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.1
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public T fromJson(JsonReader jsonReader) throws IOException {
                return (T) this.fromJson(jsonReader);
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable T t) throws IOException {
                boolean serializeNulls = jsonWriter.getSerializeNulls();
                jsonWriter.setSerializeNulls(true);
                try {
                    this.toJson(jsonWriter, t);
                } finally {
                    jsonWriter.setSerializeNulls(serializeNulls);
                }
            }

            public String toString() {
                return this + ".serializeNulls()";
            }
        };
    }

    public final JsonAdapter<T> nullSafe() {
        return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.2
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public T fromJson(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonReader.Token.NULL) {
                    return (T) jsonReader.nextNull();
                }
                return (T) this.fromJson(jsonReader);
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable T t) throws IOException {
                if (t == null) {
                    jsonWriter.nullValue();
                } else {
                    this.toJson(jsonWriter, t);
                }
            }

            public String toString() {
                return this + ".nullSafe()";
            }
        };
    }

    public final JsonAdapter<T> lenient() {
        return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.3
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public T fromJson(JsonReader jsonReader) throws IOException {
                boolean zIsLenient = jsonReader.isLenient();
                jsonReader.setLenient(true);
                try {
                    return (T) this.fromJson(jsonReader);
                } finally {
                    jsonReader.setLenient(zIsLenient);
                }
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable T t) throws IOException {
                boolean zIsLenient = jsonWriter.isLenient();
                jsonWriter.setLenient(true);
                try {
                    this.toJson(jsonWriter, t);
                } finally {
                    jsonWriter.setLenient(zIsLenient);
                }
            }

            public String toString() {
                return this + ".lenient()";
            }
        };
    }

    public final JsonAdapter<T> failOnUnknown() {
        return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.4
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public T fromJson(JsonReader jsonReader) throws IOException {
                boolean zFailOnUnknown = jsonReader.failOnUnknown();
                jsonReader.setFailOnUnknown(true);
                try {
                    return (T) this.fromJson(jsonReader);
                } finally {
                    jsonReader.setFailOnUnknown(zFailOnUnknown);
                }
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable T t) throws IOException {
                this.toJson(jsonWriter, t);
            }

            public String toString() {
                return this + ".failOnUnknown()";
            }
        };
    }

    public JsonAdapter<T> indent(final String str) {
        if (str == null) {
            throw new NullPointerException("indent == null");
        }
        return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.5
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public T fromJson(JsonReader jsonReader) throws IOException {
                return (T) this.fromJson(jsonReader);
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable T t) throws IOException {
                String indent = jsonWriter.getIndent();
                jsonWriter.setIndent(str);
                try {
                    this.toJson(jsonWriter, t);
                } finally {
                    jsonWriter.setIndent(indent);
                }
            }

            public String toString() {
                return this + ".indent(\"" + str + "\")";
            }
        };
    }
}
