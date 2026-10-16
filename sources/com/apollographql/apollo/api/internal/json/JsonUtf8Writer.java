package com.apollographql.apollo.api.internal.json;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;

/* JADX INFO: compiled from: JsonUtf8Writer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\u0010\u0004\n\u0002\b\u0003\b\u0000\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\r\u001a\u00020\u0001H\u0016J\b\u0010\u000e\u001a\u00020\u0001H\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0016J \u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\b\u0010\u0014\u001a\u00020\u0001H\u0016J\b\u0010\u0015\u001a\u00020\u0001H\u0016J\b\u0010\u0016\u001a\u00020\u000bH\u0016J\u0012\u0010\u0017\u001a\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0006H\u0016J\b\u0010\u001a\u001a\u00020\u000bH\u0002J\b\u0010\u001b\u001a\u00020\u0001H\u0016J\u0018\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J\u0017\u0010\u0018\u001a\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u001eH\u0016¢\u0006\u0002\u0010\u001fJ\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020 H\u0016J\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020!H\u0016J\u0012\u0010\u0018\u001a\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\"H\u0016J\u0012\u0010\u0018\u001a\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010#\u001a\u00020\u000bH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/JsonUtf8Writer;", "Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "sink", "Lokio/BufferedSink;", "(Lokio/BufferedSink;)V", "deferredName", "", "separator", "getSeparator", "()Ljava/lang/String;", "beforeName", "", "beforeValue", "beginArray", "beginObject", ToolboxAnalyticsModuleKt.EVENT_CLOSE, "empty", "", "nonempty", "closeBracket", "endArray", "endObject", "flush", "jsonValue", "value", "name", "newline", "nullValue", "open", "openBracket", "", "(Ljava/lang/Boolean;)Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "", "", "", "writeDeferredName", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class JsonUtf8Writer extends JsonWriter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HEX_ARRAY = "0123456789abcdef";
    private static final String[] REPLACEMENT_CHARS;
    private String deferredName;
    private final BufferedSink sink;

    /* JADX INFO: compiled from: JsonUtf8Writer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004J\f\u0010\r\u001a\u00020\u0004*\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/JsonUtf8Writer$Companion;", "", "()V", "HEX_ARRAY", "", "REPLACEMENT_CHARS", "", "[Ljava/lang/String;", "string", "", "sink", "Lokio/BufferedSink;", "value", "hexString", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String hexString(byte b) {
            StringBuilder sb = new StringBuilder();
            sb.append(JsonUtf8Writer.HEX_ARRAY.charAt(b >>> 4));
            sb.append(JsonUtf8Writer.HEX_ARRAY.charAt(b & 15));
            return sb.toString();
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0039  */
        public final void string(BufferedSink sink, String value) throws IOException {
            String str;
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            Intrinsics.checkParameterIsNotNull(value, "value");
            String[] strArr = JsonUtf8Writer.REPLACEMENT_CHARS;
            sink.writeByte(34);
            int length = value.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = value.charAt(i2);
                if (cCharAt < 128) {
                    str = strArr[cCharAt];
                    if (str != null) {
                        if (i < i2) {
                            sink.writeUtf8(value, i, i2);
                        }
                        sink.writeUtf8(str);
                        i = i2 + 1;
                    }
                } else {
                    if (cCharAt == 8232) {
                        str = "\\u2028";
                    } else if (cCharAt == 8233) {
                        str = "\\u2029";
                    }
                    if (i < i2) {
                        sink.writeUtf8(value, i, i2);
                    }
                    sink.writeUtf8(str);
                    i = i2 + 1;
                }
            }
            if (i < length) {
                sink.writeUtf8(value, i, length);
            }
            sink.writeByte(34);
        }
    }

    public JsonUtf8Writer(BufferedSink sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        this.sink = sink;
        pushScope(6);
    }

    static {
        String[] strArr = new String[128];
        for (int i = 0; i <= 31; i++) {
            strArr[i] = "\\u00" + INSTANCE.hexString((byte) i);
        }
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        REPLACEMENT_CHARS = strArr;
    }

    public final String getSeparator() {
        String indent = getIndent();
        return indent == null || indent.length() == 0 ? ":" : ": ";
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        return open(1, "[");
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter endArray() throws IOException {
        return close(1, 2, "]");
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        return open(3, "{");
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter endObject() throws IOException {
        return close(3, 5, "}");
    }

    private final JsonWriter open(int empty, String openBracket) throws IOException {
        beforeValue();
        pushScope(empty);
        getPathIndices()[getStackSize() - 1] = 0;
        this.sink.writeUtf8(openBracket);
        return this;
    }

    private final JsonWriter close(int empty, int nonempty, String closeBracket) throws IOException {
        int iPeekScope = peekScope();
        if (!(iPeekScope == nonempty || iPeekScope == empty)) {
            throw new IllegalStateException("Nesting problem.".toString());
        }
        if (!(this.deferredName == null)) {
            throw new IllegalStateException(("Dangling name: " + this.deferredName).toString());
        }
        setStackSize(getStackSize() - 1);
        getPathNames()[getStackSize()] = (String) null;
        int[] pathIndices = getPathIndices();
        int stackSize = getStackSize() - 1;
        pathIndices[stackSize] = pathIndices[stackSize] + 1;
        if (iPeekScope == nonempty) {
            newline();
        }
        this.sink.writeUtf8(closeBracket);
        return this;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter name(String name) throws IOException {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (!(getStackSize() != 0)) {
            throw new IllegalStateException("JsonWriter is closed.".toString());
        }
        if (!(this.deferredName == null)) {
            throw new IllegalStateException("Nesting problem.".toString());
        }
        this.deferredName = name;
        getPathNames()[getStackSize() - 1] = name;
        return this;
    }

    private final void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            Companion companion = INSTANCE;
            BufferedSink bufferedSink = this.sink;
            String str = this.deferredName;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            companion.string(bufferedSink, str);
            this.deferredName = (String) null;
        }
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter value(String value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        INSTANCE.string(this.sink, value);
        int[] pathIndices = getPathIndices();
        int stackSize = getStackSize() - 1;
        pathIndices[stackSize] = pathIndices[stackSize] + 1;
        return this;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter jsonValue(String value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(value);
        int[] pathIndices = getPathIndices();
        int stackSize = getStackSize() - 1;
        pathIndices[stackSize] = pathIndices[stackSize] + 1;
        return this;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (getSerializeNulls()) {
                writeDeferredName();
            } else {
                this.deferredName = (String) null;
                return this;
            }
        }
        beforeValue();
        this.sink.writeUtf8("null");
        int[] pathIndices = getPathIndices();
        int stackSize = getStackSize() - 1;
        pathIndices[stackSize] = pathIndices[stackSize] + 1;
        return this;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter value(Boolean value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(value.booleanValue() ? "true" : "false");
        int[] pathIndices = getPathIndices();
        int stackSize = getStackSize() - 1;
        pathIndices[stackSize] = pathIndices[stackSize] + 1;
        return this;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter value(double value) throws IOException {
        if (!(getIsLenient() || !(Double.isNaN(value) || Double.isInfinite(value)))) {
            throw new IllegalArgumentException(("Numeric values must be finite, but was " + value).toString());
        }
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(String.valueOf(value));
        int[] pathIndices = getPathIndices();
        int stackSize = getStackSize() - 1;
        pathIndices[stackSize] = pathIndices[stackSize] + 1;
        return this;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter value(long value) throws IOException {
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(String.valueOf(value));
        int[] pathIndices = getPathIndices();
        int stackSize = getStackSize() - 1;
        pathIndices[stackSize] = pathIndices[stackSize] + 1;
        return this;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonWriter
    public JsonWriter value(Number value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        String string = value.toString();
        if (!(getIsLenient() || !(Intrinsics.areEqual(string, "-Infinity") || Intrinsics.areEqual(string, "Infinity") || Intrinsics.areEqual(string, "NaN")))) {
            throw new IllegalArgumentException(("Numeric values must be finite, but was " + value).toString());
        }
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(string);
        int[] pathIndices = getPathIndices();
        int stackSize = getStackSize() - 1;
        pathIndices[stackSize] = pathIndices[stackSize] + 1;
        return this;
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        if (!(getStackSize() != 0)) {
            throw new IllegalStateException("JsonWriter is closed.".toString());
        }
        this.sink.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sink.close();
        int stackSize = getStackSize();
        if (stackSize > 1 || (stackSize == 1 && getScopes()[stackSize - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        setStackSize(0);
    }

    private final void newline() throws IOException {
        if (getIndent() == null) {
            return;
        }
        this.sink.writeByte(10);
        int stackSize = getStackSize();
        for (int i = 1; i < stackSize; i++) {
            BufferedSink bufferedSink = this.sink;
            String indent = getIndent();
            if (indent == null) {
                indent = "";
            }
            bufferedSink.writeUtf8(indent);
        }
    }

    private final void beforeName() throws IOException {
        int iPeekScope = peekScope();
        if (iPeekScope == 5) {
            this.sink.writeByte(44);
        } else {
            if (!(iPeekScope == 3)) {
                throw new IllegalStateException("Nesting problem.".toString());
            }
        }
        newline();
        replaceTop(4);
    }

    private final void beforeValue() throws IOException {
        int iPeekScope = peekScope();
        if (iPeekScope == 1) {
            replaceTop(2);
            newline();
            return;
        }
        if (iPeekScope == 2) {
            this.sink.writeByte(44);
            newline();
            return;
        }
        if (iPeekScope == 4) {
            this.sink.writeUtf8(getSeparator());
            replaceTop(5);
        } else if (iPeekScope == 6) {
            replaceTop(7);
        } else {
            if (iPeekScope == 7) {
                if (!getIsLenient()) {
                    throw new IllegalStateException("JSON must have only one top-level value.".toString());
                }
                replaceTop(7);
                return;
            }
            throw new IllegalStateException("Nesting problem.");
        }
    }
}
