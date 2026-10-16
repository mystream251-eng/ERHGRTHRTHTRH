package com.squareup.moshi;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import javax.annotation.Nullable;
import okio.BufferedSink;

/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonWriter implements Closeable, Flushable {
    String indent;
    boolean lenient;
    boolean promoteValueToName;
    boolean serializeNulls;
    int stackSize = 0;
    final int[] scopes = new int[32];
    final String[] pathNames = new String[32];
    final int[] pathIndices = new int[32];

    public abstract JsonWriter beginArray() throws IOException;

    public abstract JsonWriter beginObject() throws IOException;

    public abstract JsonWriter endArray() throws IOException;

    public abstract JsonWriter endObject() throws IOException;

    public abstract JsonWriter name(String str) throws IOException;

    public abstract JsonWriter nullValue() throws IOException;

    public abstract JsonWriter value(double d) throws IOException;

    public abstract JsonWriter value(long j) throws IOException;

    public abstract JsonWriter value(@Nullable Boolean bool) throws IOException;

    public abstract JsonWriter value(@Nullable Number number) throws IOException;

    public abstract JsonWriter value(@Nullable String str) throws IOException;

    public abstract JsonWriter value(boolean z) throws IOException;

    public static JsonWriter of(BufferedSink bufferedSink) {
        return new JsonUtf8Writer(bufferedSink);
    }

    JsonWriter() {
    }

    final int peekScope() {
        int i = this.stackSize;
        if (i == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.scopes[i - 1];
    }

    final void pushScope(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.scopes;
        if (i2 == iArr.length) {
            throw new JsonDataException("Nesting too deep at " + getPath() + ": circular reference?");
        }
        this.stackSize = i2 + 1;
        iArr[i2] = i;
    }

    final void replaceTop(int i) {
        this.scopes[this.stackSize - 1] = i;
    }

    public void setIndent(String str) {
        if (str.isEmpty()) {
            str = null;
        }
        this.indent = str;
    }

    public final String getIndent() {
        String str = this.indent;
        return str != null ? str : "";
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    final void promoteValueToName() throws IOException {
        int iPeekScope = peekScope();
        if (iPeekScope != 5 && iPeekScope != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.promoteValueToName = true;
    }

    public final String getPath() {
        return JsonScope.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }
}
