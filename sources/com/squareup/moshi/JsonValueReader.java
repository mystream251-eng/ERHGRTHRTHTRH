package com.squareup.moshi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class JsonValueReader extends JsonReader {
    private static final Object JSON_READER_CLOSED = new Object();
    private final Object[] stack = new Object[32];

    JsonValueReader(Object obj) {
        this.scopes[this.stackSize] = 7;
        Object[] objArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        objArr[i] = obj;
    }

    @Override // com.squareup.moshi.JsonReader
    public void beginArray() throws IOException {
        ListIterator listIterator = ((List) require(List.class, JsonReader.Token.BEGIN_ARRAY)).listIterator();
        this.stack[this.stackSize - 1] = listIterator;
        this.scopes[this.stackSize - 1] = 1;
        this.pathIndices[this.stackSize - 1] = 0;
        if (listIterator.hasNext()) {
            push(listIterator.next());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public void endArray() throws IOException {
        ListIterator listIterator = (ListIterator) require(ListIterator.class, JsonReader.Token.END_ARRAY);
        if (listIterator.hasNext()) {
            throw typeMismatch(listIterator, JsonReader.Token.END_ARRAY);
        }
        remove();
    }

    @Override // com.squareup.moshi.JsonReader
    public void beginObject() throws IOException {
        Iterator it = ((Map) require(Map.class, JsonReader.Token.BEGIN_OBJECT)).entrySet().iterator();
        this.stack[this.stackSize - 1] = it;
        this.scopes[this.stackSize - 1] = 3;
        if (it.hasNext()) {
            push(it.next());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public void endObject() throws IOException {
        Iterator it = (Iterator) require(Iterator.class, JsonReader.Token.END_OBJECT);
        if ((it instanceof ListIterator) || it.hasNext()) {
            throw typeMismatch(it, JsonReader.Token.END_OBJECT);
        }
        this.pathNames[this.stackSize - 1] = null;
        remove();
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean hasNext() throws IOException {
        if (this.stackSize == 0) {
            return true;
        }
        Object obj = this.stack[this.stackSize - 1];
        return !(obj instanceof Iterator) || ((Iterator) obj).hasNext();
    }

    @Override // com.squareup.moshi.JsonReader
    public JsonReader.Token peek() throws IOException {
        if (this.stackSize == 0) {
            return JsonReader.Token.END_DOCUMENT;
        }
        Object obj = this.stack[this.stackSize - 1];
        if (obj instanceof ListIterator) {
            return JsonReader.Token.END_ARRAY;
        }
        if (obj instanceof Iterator) {
            return JsonReader.Token.END_OBJECT;
        }
        if (obj instanceof List) {
            return JsonReader.Token.BEGIN_ARRAY;
        }
        if (obj instanceof Map) {
            return JsonReader.Token.BEGIN_OBJECT;
        }
        if (obj instanceof Map.Entry) {
            return JsonReader.Token.NAME;
        }
        if (obj instanceof String) {
            return JsonReader.Token.STRING;
        }
        if (obj instanceof Boolean) {
            return JsonReader.Token.BOOLEAN;
        }
        if (obj instanceof Number) {
            return JsonReader.Token.NUMBER;
        }
        if (obj == null) {
            return JsonReader.Token.NULL;
        }
        if (obj == JSON_READER_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw typeMismatch(obj, "a JSON value");
    }

    @Override // com.squareup.moshi.JsonReader
    public String nextName() throws IOException {
        Map.Entry<?, ?> entry = (Map.Entry) require(Map.Entry.class, JsonReader.Token.NAME);
        String strStringKey = stringKey(entry);
        this.stack[this.stackSize - 1] = entry.getValue();
        this.pathNames[this.stackSize - 2] = strStringKey;
        return strStringKey;
    }

    @Override // com.squareup.moshi.JsonReader
    public int selectName(JsonReader.Options options) throws IOException {
        Map.Entry<?, ?> entry = (Map.Entry) require(Map.Entry.class, JsonReader.Token.NAME);
        String strStringKey = stringKey(entry);
        int length = options.strings.length;
        for (int i = 0; i < length; i++) {
            if (options.strings[i].equals(strStringKey)) {
                this.stack[this.stackSize - 1] = entry.getValue();
                this.pathNames[this.stackSize - 2] = strStringKey;
                return i;
            }
        }
        return -1;
    }

    @Override // com.squareup.moshi.JsonReader
    public String nextString() throws IOException {
        String str = (String) require(String.class, JsonReader.Token.STRING);
        remove();
        return str;
    }

    @Override // com.squareup.moshi.JsonReader
    public int selectString(JsonReader.Options options) throws IOException {
        String str = (String) require(String.class, JsonReader.Token.STRING);
        int length = options.strings.length;
        for (int i = 0; i < length; i++) {
            if (options.strings[i].equals(str)) {
                remove();
                return i;
            }
        }
        return -1;
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean nextBoolean() throws IOException {
        Boolean bool = (Boolean) require(Boolean.class, JsonReader.Token.BOOLEAN);
        remove();
        return bool.booleanValue();
    }

    @Override // com.squareup.moshi.JsonReader
    @Nullable
    public <T> T nextNull() throws IOException {
        require(Void.class, JsonReader.Token.NULL);
        remove();
        return null;
    }

    @Override // com.squareup.moshi.JsonReader
    public double nextDouble() throws IOException {
        double dDoubleValue;
        Object objRequire = require(Object.class, JsonReader.Token.NUMBER);
        if (objRequire instanceof Number) {
            dDoubleValue = ((Number) objRequire).doubleValue();
        } else if (objRequire instanceof String) {
            try {
                dDoubleValue = Double.parseDouble((String) objRequire);
            } catch (NumberFormatException unused) {
                throw typeMismatch(objRequire, JsonReader.Token.NUMBER);
            }
        } else {
            throw typeMismatch(objRequire, JsonReader.Token.NUMBER);
        }
        if (!this.lenient && (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue))) {
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + dDoubleValue + " at path " + getPath());
        }
        remove();
        return dDoubleValue;
    }

    @Override // com.squareup.moshi.JsonReader
    public long nextLong() throws IOException {
        long jLongValueExact;
        Object objRequire = require(Object.class, JsonReader.Token.NUMBER);
        if (objRequire instanceof Number) {
            jLongValueExact = ((Number) objRequire).longValue();
        } else if (objRequire instanceof String) {
            try {
                try {
                    jLongValueExact = Long.parseLong((String) objRequire);
                } catch (NumberFormatException unused) {
                    throw typeMismatch(objRequire, JsonReader.Token.NUMBER);
                }
            } catch (NumberFormatException unused2) {
                jLongValueExact = new BigDecimal((String) objRequire).longValueExact();
            }
        } else {
            throw typeMismatch(objRequire, JsonReader.Token.NUMBER);
        }
        remove();
        return jLongValueExact;
    }

    @Override // com.squareup.moshi.JsonReader
    public int nextInt() throws IOException {
        int iIntValueExact;
        Object objRequire = require(Object.class, JsonReader.Token.NUMBER);
        if (objRequire instanceof Number) {
            iIntValueExact = ((Number) objRequire).intValue();
        } else if (objRequire instanceof String) {
            try {
                try {
                    iIntValueExact = Integer.parseInt((String) objRequire);
                } catch (NumberFormatException unused) {
                    throw typeMismatch(objRequire, JsonReader.Token.NUMBER);
                }
            } catch (NumberFormatException unused2) {
                iIntValueExact = new BigDecimal((String) objRequire).intValueExact();
            }
        } else {
            throw typeMismatch(objRequire, JsonReader.Token.NUMBER);
        }
        remove();
        return iIntValueExact;
    }

    @Override // com.squareup.moshi.JsonReader
    public void skipValue() throws IOException {
        if (this.failOnUnknown) {
            throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
        }
        if (this.stackSize > 1) {
            this.pathNames[this.stackSize - 2] = "null";
        }
        if ((this.stackSize != 0 ? this.stack[this.stackSize - 1] : null) instanceof Map.Entry) {
            this.stack[this.stackSize - 1] = ((Map.Entry) this.stack[this.stackSize - 1]).getValue();
        } else if (this.stackSize > 0) {
            remove();
        }
    }

    @Override // com.squareup.moshi.JsonReader
    void promoteNameToValue() throws IOException {
        if (hasNext()) {
            push(nextName());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Arrays.fill(this.stack, 0, this.stackSize, (Object) null);
        this.stack[0] = JSON_READER_CLOSED;
        this.scopes[0] = 8;
        this.stackSize = 1;
    }

    private void push(Object obj) {
        int i = this.stackSize;
        Object[] objArr = this.stack;
        if (i == objArr.length) {
            throw new JsonDataException("Nesting too deep at " + getPath());
        }
        int i2 = this.stackSize;
        this.stackSize = i2 + 1;
        objArr[i2] = obj;
    }

    @Nullable
    private <T> T require(Class<T> cls, JsonReader.Token token) throws IOException {
        Object obj = this.stackSize != 0 ? this.stack[this.stackSize - 1] : null;
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        if (obj == null && token == JsonReader.Token.NULL) {
            return null;
        }
        if (obj == JSON_READER_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw typeMismatch(obj, token);
    }

    private String stringKey(Map.Entry<?, ?> entry) {
        Object key = entry.getKey();
        if (key instanceof String) {
            return (String) key;
        }
        throw typeMismatch(key, JsonReader.Token.NAME);
    }

    private void remove() {
        this.stackSize--;
        this.stack[this.stackSize] = null;
        this.scopes[this.stackSize] = 0;
        if (this.stackSize > 0) {
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            Object obj = this.stack[this.stackSize - 1];
            if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                if (it.hasNext()) {
                    push(it.next());
                }
            }
        }
    }
}
