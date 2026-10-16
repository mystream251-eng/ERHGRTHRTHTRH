package com.apollographql.apollo.api.internal.json;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;

/* JADX INFO: compiled from: JsonWriter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\u0010\u0004\n\u0002\b\u0002\b&\u0018\u0000 92\u00060\u0001j\u0002`\u00022\u00060\u0003j\u0002`\u0004:\u00019B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010'\u001a\u00020\u0000H&J\b\u0010(\u001a\u00020\u0000H&J\b\u0010)\u001a\u00020\u0000H&J\b\u0010*\u001a\u00020\u0000H&J\u0012\u0010+\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010-\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0007H&J\b\u0010.\u001a\u00020\u0000H&J\u0006\u0010/\u001a\u00020\"J\u000e\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\"J\u000e\u00103\u001a\u0002012\u0006\u00104\u001a\u00020\"J\u0017\u0010,\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u00105J\u0010\u0010,\u001a\u00020\u00002\u0006\u0010,\u001a\u000206H&J\u0010\u0010,\u001a\u00020\u00002\u0006\u0010,\u001a\u000207H&J\u0012\u0010,\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u000108H&J\u0012\u0010,\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\u0007H&R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\tR\u0014\u0010\u0013\u001a\u00020\u0014X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0018X\u0084\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u0014X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u001a\u0010\u001e\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010R\u001a\u0010!\u001a\u00020\"X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006:"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "Ljava/io/Closeable;", "Lcom/apollographql/apollo/api/internal/json/Closeable;", "Ljava/io/Flushable;", "Lcom/apollographql/apollo/api/internal/json/Flushable;", "()V", "indent", "", "getIndent", "()Ljava/lang/String;", "setIndent", "(Ljava/lang/String;)V", "isLenient", "", "()Z", "setLenient", "(Z)V", "path", "getPath", "pathIndices", "", "getPathIndices", "()[I", "pathNames", "", "getPathNames", "()[Ljava/lang/String;", "[Ljava/lang/String;", "scopes", "getScopes", "serializeNulls", "getSerializeNulls", "setSerializeNulls", "stackSize", "", "getStackSize", "()I", "setStackSize", "(I)V", "beginArray", "beginObject", "endArray", "endObject", "jsonValue", "value", "name", "nullValue", "peekScope", "pushScope", "", "newTop", "replaceTop", "topOfStack", "(Ljava/lang/Boolean;)Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "", "", "", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public abstract class JsonWriter implements Closeable, Flushable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String indent;
    private boolean isLenient;
    private boolean serializeNulls;
    private int stackSize;
    private final int[] scopes = new int[32];
    private final String[] pathNames = new String[32];
    private final int[] pathIndices = new int[32];

    @JvmStatic
    public static final JsonWriter of(BufferedSink bufferedSink) {
        return INSTANCE.of(bufferedSink);
    }

    public abstract JsonWriter beginArray() throws IOException;

    public abstract JsonWriter beginObject() throws IOException;

    public abstract JsonWriter endArray() throws IOException;

    public abstract JsonWriter endObject() throws IOException;

    public abstract JsonWriter jsonValue(String value) throws IOException;

    public abstract JsonWriter name(String name) throws IOException;

    public abstract JsonWriter nullValue() throws IOException;

    public abstract JsonWriter value(double value) throws IOException;

    public abstract JsonWriter value(long value) throws IOException;

    public abstract JsonWriter value(Boolean value) throws IOException;

    public abstract JsonWriter value(Number value) throws IOException;

    public abstract JsonWriter value(String value) throws IOException;

    protected final int getStackSize() {
        return this.stackSize;
    }

    protected final void setStackSize(int i) {
        this.stackSize = i;
    }

    protected final int[] getScopes() {
        return this.scopes;
    }

    protected final String[] getPathNames() {
        return this.pathNames;
    }

    protected final int[] getPathIndices() {
        return this.pathIndices;
    }

    public final String getIndent() {
        return this.indent;
    }

    public final void setIndent(String str) {
        this.indent = str;
    }

    /* JADX INFO: renamed from: isLenient, reason: from getter */
    public final boolean getIsLenient() {
        return this.isLenient;
    }

    public final void setLenient(boolean z) {
        this.isLenient = z;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
    }

    public final int peekScope() {
        if (!(this.stackSize != 0)) {
            throw new IllegalStateException("JsonWriter is closed.".toString());
        }
        return this.scopes[this.stackSize - 1];
    }

    public final void pushScope(int newTop) {
        int i = this.stackSize;
        int[] iArr = this.scopes;
        if (i == iArr.length) {
            throw new JsonDataException("Nesting too deep at " + getPath() + ": circular reference?");
        }
        this.stackSize = i + 1;
        iArr[i] = newTop;
    }

    public final void replaceTop(int topOfStack) {
        this.scopes[this.stackSize - 1] = topOfStack;
    }

    public final String getPath() {
        return JsonScope.INSTANCE.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }

    /* JADX INFO: compiled from: JsonWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/JsonWriter$Companion;", "", "()V", "of", "Lcom/apollographql/apollo/api/internal/json/JsonWriter;", "sink", "Lokio/BufferedSink;", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final JsonWriter of(BufferedSink sink) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            return new JsonUtf8Writer(sink);
        }
    }
}
