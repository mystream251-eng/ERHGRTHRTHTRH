package com.squareup.moshi;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okio.Buffer;
import okio.BufferedSink;

/* JADX INFO: compiled from: MsgpackWriter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\u0010\u0004\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0001H\u0016J\b\u0010\u001c\u001a\u00020\u0001H\u0016J\b\u0010\u001d\u001a\u00020\u0019H\u0016J\u0018\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0002J\b\u0010 \u001a\u00020\u0001H\u0016J\b\u0010!\u001a\u00020\u0001H\u0016J\b\u0010\"\u001a\u00020\u0019H\u0016J\u0012\u0010#\u001a\u00020\u00012\b\u0010#\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010$\u001a\u00020\u0001H\u0016J\u0010\u0010%\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\tH\u0002J\u0018\u0010&\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\rH\u0002J\u0010\u0010'\u001a\u00020\u00012\u0006\u0010'\u001a\u00020(H\u0016J\u0017\u0010'\u001a\u00020\u00012\b\u0010'\u001a\u0004\u0018\u00010(H\u0016¢\u0006\u0002\u0010)J\u0010\u0010'\u001a\u00020\u00012\u0006\u0010'\u001a\u00020*H\u0016J\u0010\u0010'\u001a\u00020\u00012\u0006\u0010'\u001a\u00020+H\u0016J\u0012\u0010'\u001a\u00020\u00012\b\u0010'\u001a\u0004\u0018\u00010,H\u0016J\u0012\u0010'\u001a\u00020\u00012\b\u0010'\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010-\u001a\u00020\u0019H\u0002R\u0014\u0010\u0005\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/squareup/moshi/MsgpackWriter;", "Lcom/squareup/moshi/JsonWriter;", "sink", "Lokio/BufferedSink;", "(Lokio/BufferedSink;)V", "currentBuffer", "getCurrentBuffer", "()Lokio/BufferedSink;", "currentIndex", "", "getCurrentIndex", "()I", "deferredName", "", "options", "Lcom/squareup/moshi/MsgpackWriterOptions;", "getOptions", "()Lcom/squareup/moshi/MsgpackWriterOptions;", "setOptions", "(Lcom/squareup/moshi/MsgpackWriterOptions;)V", "pathBuffers", "", "Lokio/Buffer;", "[Lokio/Buffer;", "beforeName", "", "beforeValue", "beginArray", "beginObject", ToolboxAnalyticsModuleKt.EVENT_CLOSE, "empty", "nonempty", "endArray", "endObject", "flush", "name", "nullValue", "open", "string", "value", "", "(Ljava/lang/Boolean;)Lcom/squareup/moshi/JsonWriter;", "", "", "", "writeDeferredName", "moshipack"}, k = 1, mv = {1, 1, 13})
public final class MsgpackWriter extends JsonWriter {
    private String deferredName;
    private MsgpackWriterOptions options;
    private final Buffer[] pathBuffers;
    private final BufferedSink sink;

    public MsgpackWriter(BufferedSink sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        this.sink = sink;
        this.options = new MsgpackWriterOptions(null, null, false, 7, null);
        Buffer[] bufferArr = new Buffer[32];
        for (int i = 0; i < 32; i++) {
            bufferArr[i] = null;
        }
        this.pathBuffers = bufferArr;
        pushScope(6);
    }

    public final MsgpackWriterOptions getOptions() {
        return this.options;
    }

    public final void setOptions(MsgpackWriterOptions msgpackWriterOptions) {
        Intrinsics.checkParameterIsNotNull(msgpackWriterOptions, "<set-?>");
        this.options = msgpackWriterOptions;
    }

    private final int getCurrentIndex() {
        return this.stackSize - 1;
    }

    private final BufferedSink getCurrentBuffer() {
        if (getCurrentIndex() == 0) {
            return this.sink;
        }
        Buffer buffer = this.pathBuffers[getCurrentIndex()];
        if (buffer != null) {
            return buffer;
        }
        throw new IllegalStateException("Path buffer not initialized.");
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = (String) null;
                return this;
            }
        }
        beforeValue();
        getCurrentBuffer().writeByte(-64);
        int[] iArr = this.pathIndices;
        int currentIndex = getCurrentIndex();
        iArr[currentIndex] = iArr[currentIndex] + 1;
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter value(String value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        if (this.promoteValueToName) {
            return name(value);
        }
        writeDeferredName();
        beforeValue();
        string(getCurrentBuffer(), value);
        int[] iArr = this.pathIndices;
        int currentIndex = getCurrentIndex();
        iArr[currentIndex] = iArr[currentIndex] + 1;
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter value(boolean value) throws IOException {
        writeDeferredName();
        beforeValue();
        getCurrentBuffer().writeByte(value ? -61 : -62);
        int[] iArr = this.pathIndices;
        int currentIndex = getCurrentIndex();
        iArr[currentIndex] = iArr[currentIndex] + 1;
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter value(Boolean value) {
        return value == null ? nullValue() : value(value.booleanValue());
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter value(double value) {
        if (!this.lenient && (Double.isNaN(value) || Double.isInfinite(value))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        }
        return value(Double.valueOf(value));
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter value(long value) throws IOException {
        if (this.promoteValueToName) {
            return name(String.valueOf(value));
        }
        if (this.options.getWriteAllIntsAsFloats()) {
            return value(value);
        }
        writeDeferredName();
        beforeValue();
        if (this.options.getWriteAllIntsAs() != null) {
            MsgpackIntByte writeAllIntsAs = this.options.getWriteAllIntsAs();
            if (writeAllIntsAs != null) {
                writeAllIntsAs.getWriter().invoke(getCurrentBuffer(), Byte.valueOf(writeAllIntsAs.getByte()), Long.valueOf(value));
            }
        } else {
            long j = 127;
            if (-32 <= value && j >= value) {
                getCurrentBuffer().writeByte((int) value);
            } else {
                long j2 = 255;
                if (128 <= value && j2 >= value) {
                    getCurrentBuffer().writeByte(-52);
                    getCurrentBuffer().writeByte((int) value);
                } else {
                    long j3 = 32767;
                    if (256 <= value && j3 >= value) {
                        getCurrentBuffer().writeByte(-51);
                        getCurrentBuffer().writeShort((int) value);
                    } else {
                        long j4 = 32768;
                        long j5 = MsgpackFormat.UINT_32_MAX;
                        if (j4 <= value && j5 >= value) {
                            getCurrentBuffer().writeByte(-50);
                            getCurrentBuffer().writeInt((int) value);
                        } else if (536870912 <= value && Long.MAX_VALUE >= value) {
                            getCurrentBuffer().writeByte(-49);
                            getCurrentBuffer().writeLong(value);
                        } else if (-32768 <= value && j3 >= value) {
                            getCurrentBuffer().writeByte(-47);
                            getCurrentBuffer().writeShort((int) value);
                        } else {
                            long j6 = Integer.MAX_VALUE;
                            if (Integer.MIN_VALUE <= value && j6 >= value) {
                                getCurrentBuffer().writeByte(-46);
                                getCurrentBuffer().writeInt((int) value);
                            } else if (Long.MIN_VALUE <= value && Long.MAX_VALUE >= value) {
                                getCurrentBuffer().writeByte(-45);
                                getCurrentBuffer().writeLong(value);
                            }
                        }
                    }
                }
            }
        }
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x005c  */
    /* JADX WARN: Code duplicated, block: B:24:0x0060  */
    /* JADX WARN: Code duplicated, block: B:25:0x0079  */
    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter value(Number value) throws IOException {
        double d;
        double dDoubleValue;
        if (value == null) {
            return nullValue();
        }
        if (value.doubleValue() % ((double) 1) == 0.0d && !this.options.getWriteAllIntsAsFloats()) {
            return value(value.longValue());
        }
        if (this.promoteValueToName) {
            return name(value.toString());
        }
        writeDeferredName();
        beforeValue();
        MsgpackFloatByte writeAllFloatsAs = this.options.getWriteAllFloatsAs();
        if (writeAllFloatsAs == null) {
            double d2 = -FloatCompanionObject.INSTANCE.getMAX_VALUE();
            double max_value = FloatCompanionObject.INSTANCE.getMAX_VALUE();
            double dDoubleValue2 = value.doubleValue();
            if (dDoubleValue2 >= d2 && dDoubleValue2 <= max_value) {
                getCurrentBuffer().writeByte(-54);
                getCurrentBuffer().writeInt(Float.floatToIntBits(value.floatValue()));
            } else if (writeAllFloatsAs == MsgpackFloatByte.FLOAT_32) {
                getCurrentBuffer().writeByte(-54);
                getCurrentBuffer().writeInt(Float.floatToIntBits(value.floatValue()));
            } else {
                d = -DoubleCompanionObject.INSTANCE.getMAX_VALUE();
                double max_value2 = DoubleCompanionObject.INSTANCE.getMAX_VALUE();
                dDoubleValue = value.doubleValue();
                if ((dDoubleValue < d && dDoubleValue <= max_value2) || writeAllFloatsAs == MsgpackFloatByte.FLOAT_64) {
                    getCurrentBuffer().writeByte(-53);
                    getCurrentBuffer().writeLong(Double.doubleToRawLongBits(value.doubleValue()));
                }
            }
        } else if (writeAllFloatsAs == MsgpackFloatByte.FLOAT_32) {
            getCurrentBuffer().writeByte(-54);
            getCurrentBuffer().writeInt(Float.floatToIntBits(value.floatValue()));
        } else {
            d = -DoubleCompanionObject.INSTANCE.getMAX_VALUE();
            double max_value3 = DoubleCompanionObject.INSTANCE.getMAX_VALUE();
            dDoubleValue = value.doubleValue();
            if (dDoubleValue < d) {
                getCurrentBuffer().writeByte(-53);
                getCurrentBuffer().writeLong(Double.doubleToRawLongBits(value.doubleValue()));
            } else {
                getCurrentBuffer().writeByte(-53);
                getCurrentBuffer().writeLong(Double.doubleToRawLongBits(value.doubleValue()));
            }
        }
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        return open(1);
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter endArray() {
        return close(1, 2);
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        return open(3);
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter endObject() {
        this.promoteValueToName = false;
        return close(3, 5);
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter name(String name) {
        if (!(name != null)) {
            throw new IllegalArgumentException("name == null ".toString());
        }
        if (!(this.stackSize > 0)) {
            throw new IllegalArgumentException("MsgpackWriter is closed.".toString());
        }
        if (!(this.deferredName == null)) {
            throw new IllegalArgumentException("Nesting problem".toString());
        }
        this.deferredName = name;
        this.pathNames[getCurrentIndex()] = name;
        this.promoteValueToName = false;
        return this;
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.sink.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sink.close();
        int i = this.stackSize;
        if (i > 1 || (i == 1 && this.scopes[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    private final void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            BufferedSink currentBuffer = getCurrentBuffer();
            String str = this.deferredName;
            if (str == null) {
                throw new IllegalStateException("Null name.");
            }
            string(currentBuffer, str);
            this.deferredName = (String) null;
        }
    }

    private final void beforeName() throws IOException {
        int iPeekScope = peekScope();
        if (!(iPeekScope == 3 || iPeekScope == 5)) {
            throw new IllegalArgumentException("Nesting problem, not in object.".toString());
        }
        replaceTop(4);
    }

    private final void beforeValue() throws IOException {
        int iPeekScope = peekScope();
        if (iPeekScope == 1) {
            replaceTop(2);
            return;
        }
        if (iPeekScope != 2) {
            if (iPeekScope == 4) {
                replaceTop(5);
            } else {
                if (iPeekScope == 6 || iPeekScope == 7) {
                    replaceTop(7);
                    return;
                }
                throw new IllegalStateException("Nesting problem.");
            }
        }
    }

    private final JsonWriter open(int empty) throws IOException {
        beforeValue();
        pushScope(empty);
        this.pathBuffers[getCurrentIndex()] = new Buffer();
        this.pathIndices[getCurrentIndex()] = 0;
        return this;
    }

    private final JsonWriter close(int empty, int nonempty) throws IOException {
        MsgpackFormatType[] array;
        int iPeekScope = peekScope();
        if (iPeekScope != nonempty && iPeekScope != empty) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
        if (nonempty == 2) {
            array = MsgpackFormat.INSTANCE.getARRAY();
        } else if (nonempty == 5) {
            array = MsgpackFormat.INSTANCE.getMAP();
        } else {
            throw new IllegalStateException("Wrong nonempty value used.");
        }
        MsgpackFormatType msgpackFormatTypeTagFor = MsgpackFormat.INSTANCE.tagFor(array, this.pathIndices[getCurrentIndex()]);
        if (msgpackFormatTypeTagFor == null) {
            throw new IllegalArgumentException("Size too long for msgpack format.");
        }
        BufferedSink currentBuffer = getCurrentBuffer();
        if (currentBuffer == null) {
            throw new TypeCastException("null cannot be cast to non-null type okio.Buffer");
        }
        int i = this.pathIndices[getCurrentIndex()];
        this.stackSize--;
        msgpackFormatTypeTagFor.writeTag(getCurrentBuffer(), i);
        ((Buffer) currentBuffer).copyTo(getCurrentBuffer().outputStream());
        this.pathNames[this.stackSize] = (String) null;
        int[] iArr = this.pathIndices;
        int currentIndex = getCurrentIndex();
        iArr[currentIndex] = iArr[currentIndex] + 1;
        if (this.stackSize == 1) {
            this.sink.emitCompleteSegments();
        }
        return this;
    }

    private final void string(BufferedSink sink, String value) throws IOException {
        Charset charset = Charsets.UTF_8;
        if (value == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = value.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        MsgpackFormatType msgpackFormatTypeTagFor = MsgpackFormat.INSTANCE.tagFor(MsgpackFormat.INSTANCE.getSTR(), bytes.length);
        if (msgpackFormatTypeTagFor == null) {
            throw new IllegalArgumentException("String size too long for msgpack format.");
        }
        msgpackFormatTypeTagFor.writeTag(sink, bytes.length);
        sink.writeUtf8(value);
    }
}
