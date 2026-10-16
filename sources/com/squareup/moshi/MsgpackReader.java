package com.squareup.moshi;

import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UShort;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.CharsKt;
import okio.Buffer;
import okio.BufferedSource;

/* JADX INFO: compiled from: MsgpackReader.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020-H\u0016J\b\u0010/\u001a\u00020-H\u0016J\b\u00100\u001a\u00020\u0006H\u0002J\b\u00101\u001a\u00020-H\u0016J\b\u00102\u001a\u00020-H\u0016J\u0018\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020)2\u0006\u00105\u001a\u000206H\u0002J\u0018\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020)2\u0006\u00105\u001a\u000206H\u0002J\t\u00109\u001a\u00020+H\u0096\u0002J\b\u0010:\u001a\u00020+H\u0016J\b\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020\u0006H\u0016J\b\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u00020)H\u0016J\u0015\u0010A\u001a\u0004\u0018\u0001HB\"\u0004\b\u0000\u0010BH\u0016¢\u0006\u0002\u0010CJ\b\u0010D\u001a\u00020)H\u0016J\b\u0010E\u001a\u00020FH\u0016J\b\u0010G\u001a\u00020-H\u0014J\b\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u00020)H\u0002J\u0010\u0010K\u001a\u00020\u00062\u0006\u00105\u001a\u000206H\u0016J\u0010\u0010L\u001a\u00020\u00062\u0006\u00105\u001a\u000206H\u0016J\b\u0010M\u001a\u00020-H\u0002J\b\u0010N\u001a\u00020-H\u0002J\b\u0010O\u001a\u00020-H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"Lcom/squareup/moshi/MsgpackReader;", "Lcom/squareup/moshi/JsonReader;", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "PEEKED_BEGIN_ARRAY", "", "PEEKED_BEGIN_OBJECT", "PEEKED_BUFFERED", "PEEKED_BUFFERED_NAME", "PEEKED_DOUBLE", "PEEKED_END_ARRAY", "PEEKED_END_OBJECT", "PEEKED_EOF", "PEEKED_FALSE", "PEEKED_LONG", "PEEKED_NONE", "PEEKED_NULL", "PEEKED_STRING", "PEEKED_TRUE", "buffer", "Lokio/Buffer;", "kotlin.jvm.PlatformType", "currentTag", "", "getCurrentTag", "()B", "setCurrentTag", "(B)V", "pathSize", "", "getPathSize", "()[J", "setPathSize", "([J)V", "peeked", "getPeeked", "()I", "setPeeked", "(I)V", "peekedString", "", "promotedNameToValue", "", "beginArray", "", "beginObject", ToolboxAnalyticsModuleKt.EVENT_CLOSE, "doPeek", "endArray", "endObject", "findName", "name", "options", "Lcom/squareup/moshi/JsonReader$Options;", "findString", "string", "hasNext", "nextBoolean", "nextDouble", "", "nextInt", "nextLong", "", "nextName", "nextNull", ExifInterface.GPS_DIRECTION_TRUE, "()Ljava/lang/Object;", "nextString", "peek", "Lcom/squareup/moshi/JsonReader$Token;", "promoteNameToValue", "readNumber", "", "readString", "selectName", "selectString", "skipArray", "skipObject", "skipValue", "moshipack"}, k = 1, mv = {1, 1, 13})
public final class MsgpackReader extends JsonReader {
    private final int PEEKED_BEGIN_ARRAY;
    private final int PEEKED_BEGIN_OBJECT;
    private final int PEEKED_BUFFERED;
    private final int PEEKED_BUFFERED_NAME;
    private final int PEEKED_DOUBLE;
    private final int PEEKED_END_ARRAY;
    private final int PEEKED_END_OBJECT;
    private final int PEEKED_EOF;
    private final int PEEKED_FALSE;
    private final int PEEKED_LONG;
    private final int PEEKED_NONE;
    private final int PEEKED_NULL;
    private final int PEEKED_STRING;
    private final int PEEKED_TRUE;
    private final Buffer buffer;
    private byte currentTag;
    private long[] pathSize;
    private int peeked;
    private String peekedString;
    private boolean promotedNameToValue;
    private final BufferedSource source;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 1;
            $EnumSwitchMapping$0[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 2;
            $EnumSwitchMapping$0[JsonReader.Token.STRING.ordinal()] = 3;
            $EnumSwitchMapping$0[JsonReader.Token.NUMBER.ordinal()] = 4;
            $EnumSwitchMapping$0[JsonReader.Token.BOOLEAN.ordinal()] = 5;
            $EnumSwitchMapping$0[JsonReader.Token.NULL.ordinal()] = 6;
        }
    }

    public MsgpackReader(BufferedSource source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.source = source;
        this.PEEKED_BEGIN_OBJECT = 1;
        this.PEEKED_END_OBJECT = 2;
        this.PEEKED_BEGIN_ARRAY = 3;
        this.PEEKED_END_ARRAY = 4;
        this.PEEKED_TRUE = 5;
        this.PEEKED_FALSE = 6;
        this.PEEKED_NULL = 7;
        this.PEEKED_STRING = 8;
        this.PEEKED_LONG = 16;
        this.PEEKED_DOUBLE = 17;
        this.PEEKED_EOF = 18;
        this.PEEKED_BUFFERED = 19;
        this.PEEKED_BUFFERED_NAME = 20;
        this.peekedString = "";
        long[] jArr = new long[32];
        for (int i = 0; i < 32; i++) {
            jArr[i] = 0;
        }
        this.pathSize = jArr;
        this.buffer = this.source.getBufferField();
        this.peeked = this.PEEKED_NONE;
        pushScope(6);
    }

    public final long[] getPathSize() {
        return this.pathSize;
    }

    public final void setPathSize(long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "<set-?>");
        this.pathSize = jArr;
    }

    public final byte getCurrentTag() {
        return this.currentTag;
    }

    public final void setCurrentTag(byte b) {
        this.currentTag = b;
    }

    public final int getPeeked() {
        return this.peeked;
    }

    public final void setPeeked(int i) {
        this.peeked = i;
    }

    @Override // com.squareup.moshi.JsonReader
    public void beginArray() throws IOException {
        if (this.peeked == this.PEEKED_NONE) {
            doPeek();
        }
        pushScope(1);
        this.peeked = this.PEEKED_NONE;
        this.pathIndices[this.stackSize - 1] = 0;
        long[] jArr = this.pathSize;
        int i = this.stackSize - 1;
        MsgpackFormatType msgpackFormatTypeTypeFor = MsgpackFormatKt.typeFor(MsgpackFormat.INSTANCE.getARRAY(), this.currentTag);
        if (msgpackFormatTypeTypeFor != null) {
            jArr[i] = msgpackFormatTypeTypeFor.readSize(this.source, this.currentTag);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Current tag 0x");
        String string = Integer.toString(this.currentTag, CharsKt.checkRadix(CharsKt.checkRadix(16)));
        Intrinsics.checkExpressionValueIsNotNull(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        sb.append(string);
        sb.append(" is not an array tag.");
        throw new IllegalStateException(sb.toString());
    }

    @Override // com.squareup.moshi.JsonReader
    public void endArray() {
        this.stackSize--;
        this.pathNames[this.stackSize] = (String) null;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
    }

    @Override // com.squareup.moshi.JsonReader
    public void beginObject() throws IOException {
        if (this.peeked == this.PEEKED_NONE) {
            doPeek();
        }
        pushScope(3);
        this.peeked = this.PEEKED_NONE;
        this.pathIndices[this.stackSize - 1] = 0;
        long[] jArr = this.pathSize;
        int i = this.stackSize - 1;
        MsgpackFormatType msgpackFormatTypeTypeFor = MsgpackFormatKt.typeFor(MsgpackFormat.INSTANCE.getMAP(), this.currentTag);
        if (msgpackFormatTypeTypeFor != null) {
            jArr[i] = msgpackFormatTypeTypeFor.readSize(this.source, this.currentTag);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Current tag 0x");
        String string = Integer.toString(this.currentTag, CharsKt.checkRadix(CharsKt.checkRadix(16)));
        Intrinsics.checkExpressionValueIsNotNull(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        sb.append(string);
        sb.append(" is not a map tag.");
        throw new IllegalStateException(sb.toString());
    }

    @Override // com.squareup.moshi.JsonReader
    public void endObject() {
        this.stackSize--;
        this.pathNames[this.stackSize] = (String) null;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
    }

    private final String readString() throws IOException {
        if (this.peeked == this.PEEKED_NONE) {
            doPeek();
        }
        MsgpackFormatType msgpackFormatTypeTypeFor = MsgpackFormatKt.typeFor(MsgpackFormat.INSTANCE.getSTR(), this.currentTag);
        if (msgpackFormatTypeTypeFor == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Current tag 0x");
            String string = Integer.toString(this.currentTag, CharsKt.checkRadix(CharsKt.checkRadix(16)));
            Intrinsics.checkExpressionValueIsNotNull(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
            sb.append(string);
            sb.append(" is not a string tag.");
            throw new IllegalStateException(sb.toString());
        }
        String utf8 = this.source.readUtf8(msgpackFormatTypeTypeFor.readSize(this.source, this.currentTag));
        Intrinsics.checkExpressionValueIsNotNull(utf8, "source.readUtf8(readBytes)");
        return utf8;
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean nextBoolean() throws IOException {
        if (this.peeked == this.PEEKED_NONE) {
            doPeek();
        }
        this.peeked = this.PEEKED_NONE;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this.currentTag == -61;
    }

    @Override // com.squareup.moshi.JsonReader
    public <T> T nextNull() throws IOException {
        if (this.peeked == this.PEEKED_NONE) {
            doPeek();
        }
        this.peeked = this.PEEKED_NONE;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return null;
    }

    @Override // com.squareup.moshi.JsonReader
    public double nextDouble() {
        return readNumber().doubleValue();
    }

    @Override // com.squareup.moshi.JsonReader
    public long nextLong() {
        return readNumber().longValue();
    }

    @Override // com.squareup.moshi.JsonReader
    public int nextInt() {
        return readNumber().intValue();
    }

    private final Number readNumber() throws IOException {
        if (this.peeked == this.PEEKED_NONE) {
            doPeek();
        }
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        this.peeked = this.PEEKED_NONE;
        byte b = this.currentTag;
        if (MsgpackFormatKt.contains(MsgpackFormat.INSTANCE.getSTR(), b)) {
            MsgpackFormatType msgpackFormatTypeTypeFor = MsgpackFormatKt.typeFor(MsgpackFormat.INSTANCE.getSTR(), this.currentTag);
            Long lValueOf = msgpackFormatTypeTypeFor != null ? Long.valueOf(msgpackFormatTypeTypeFor.readSize(this.source, this.currentTag)) : null;
            if (lValueOf != null) {
                String utf8 = this.source.readUtf8(lValueOf.longValue());
                Intrinsics.checkExpressionValueIsNotNull(utf8, "source.readUtf8(readBytes)");
                return Double.valueOf(Double.parseDouble(utf8));
            }
            throw new AssertionError();
        }
        if (b == -52) {
            return Integer.valueOf(this.source.readByte() & UByte.MAX_VALUE);
        }
        if (b == -51) {
            return Integer.valueOf(this.source.readShort() & UShort.MAX_VALUE);
        }
        if (b == -50) {
            return Long.valueOf(((long) this.source.readInt()) & MsgpackFormat.SIZE_32);
        }
        if (b == -49) {
            return Long.valueOf(this.source.readLong());
        }
        if (-32 <= b && 127 >= b) {
            return Byte.valueOf(this.currentTag);
        }
        if (b == -53) {
            DoubleCompanionObject doubleCompanionObject = DoubleCompanionObject.INSTANCE;
            return Double.valueOf(Double.longBitsToDouble(this.source.readLong()));
        }
        if (b == -54) {
            return Float.valueOf(Float.intBitsToFloat(this.source.readInt()));
        }
        if (b == -48) {
            return Byte.valueOf(this.source.readByte());
        }
        if (b == -46) {
            return Integer.valueOf(this.source.readInt());
        }
        if (b == -47) {
            return Short.valueOf(this.source.readShort());
        }
        if (b == -45) {
            return Long.valueOf(this.source.readLong());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Current tag 0x");
        String string = Integer.toString(this.currentTag, CharsKt.checkRadix(CharsKt.checkRadix(16)));
        Intrinsics.checkExpressionValueIsNotNull(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        sb.append(string);
        sb.append(" is not a supported number tag.");
        throw new IllegalStateException(sb.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws EOFException {
        this.peeked = this.PEEKED_NONE;
        this.scopes[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean hasNext() throws IOException {
        if (this.peeked == this.PEEKED_NONE) {
            doPeek();
        }
        return ((long) this.pathIndices[this.stackSize - 1]) < this.pathSize[this.stackSize - 1];
    }

    @Override // com.squareup.moshi.JsonReader
    public String nextName() throws IOException {
        String string;
        int iDoPeek = this.peeked;
        if (iDoPeek == this.PEEKED_NONE) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == this.PEEKED_STRING) {
            string = readString();
        } else if (iDoPeek == this.PEEKED_BUFFERED_NAME) {
            string = this.peekedString;
        } else {
            throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
        }
        this.peeked = this.PEEKED_NONE;
        this.pathNames[this.stackSize - 1] = string;
        return string;
    }

    @Override // com.squareup.moshi.JsonReader
    public int selectName(JsonReader.Options options) throws IOException {
        Intrinsics.checkParameterIsNotNull(options, "options");
        int iDoPeek = this.peeked;
        if (iDoPeek == this.PEEKED_NONE) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != this.PEEKED_STRING && iDoPeek != this.PEEKED_BUFFERED_NAME) {
            return -1;
        }
        if (iDoPeek == this.PEEKED_BUFFERED_NAME) {
            return findName(this.peekedString, options);
        }
        String str = this.pathNames[this.stackSize - 1];
        String strNextName = nextName();
        int iFindName = findName(strNextName, options);
        if (iFindName == -1) {
            this.peeked = this.PEEKED_BUFFERED_NAME;
            this.peekedString = strNextName;
            this.pathNames[this.stackSize - 1] = str;
        }
        return iFindName;
    }

    private final int findName(String name, JsonReader.Options options) {
        int length = options.strings.length;
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual(name, options.strings[i])) {
                this.peeked = this.PEEKED_NONE;
                this.pathNames[this.stackSize - 1] = name;
                return i;
            }
        }
        return -1;
    }

    @Override // com.squareup.moshi.JsonReader
    public String nextString() throws IOException {
        String strValueOf;
        int iDoPeek = this.peeked;
        if (iDoPeek == this.PEEKED_NONE) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == this.PEEKED_STRING) {
            strValueOf = readString();
        } else if (iDoPeek == this.PEEKED_BUFFERED) {
            strValueOf = this.peekedString;
            this.peekedString = "";
        } else if (iDoPeek == this.PEEKED_LONG) {
            strValueOf = String.valueOf(nextDouble());
        } else {
            throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPath());
        }
        this.peeked = this.PEEKED_NONE;
        if (this.promotedNameToValue) {
            this.promotedNameToValue = false;
        } else {
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
        }
        return strValueOf;
    }

    @Override // com.squareup.moshi.JsonReader
    public int selectString(JsonReader.Options options) throws IOException {
        Intrinsics.checkParameterIsNotNull(options, "options");
        int iDoPeek = this.peeked;
        if (iDoPeek == this.PEEKED_NONE) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != this.PEEKED_STRING && iDoPeek != this.PEEKED_BUFFERED) {
            return -1;
        }
        if (iDoPeek == this.PEEKED_BUFFERED) {
            return findString(this.peekedString, options);
        }
        String strNextString = nextString();
        int iFindString = findString(strNextString, options);
        if (iFindString == -1) {
            this.peeked = this.PEEKED_BUFFERED;
            this.peekedString = strNextString;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] - 1;
        }
        return iFindString;
    }

    private final int findString(String string, JsonReader.Options options) {
        int length = options.strings.length;
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual(string, options.strings[i])) {
                this.peeked = this.PEEKED_NONE;
                int[] iArr = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr[i2] = iArr[i2] + 1;
                return i;
            }
        }
        return -1;
    }

    @Override // com.squareup.moshi.JsonReader
    protected void promoteNameToValue() {
        if (hasNext()) {
            this.peekedString = nextName();
            this.peeked = this.PEEKED_BUFFERED;
            this.promotedNameToValue = true;
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public void skipValue() throws IOException {
        if (this.peeked == this.PEEKED_NONE) {
            doPeek();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[peek().ordinal()]) {
            case 1:
                skipArray();
                break;
            case 2:
                skipObject();
                break;
            case 3:
                nextString();
                break;
            case 4:
                readNumber();
                break;
            case 5:
                nextBoolean();
                break;
            case 6:
                this.peeked = this.PEEKED_NONE;
                int[] iArr = this.pathIndices;
                int i = this.stackSize - 1;
                iArr[i] = iArr[i] + 1;
                break;
        }
    }

    private final void skipObject() throws IOException {
        beginObject();
        while (hasNext()) {
            nextName();
            skipValue();
        }
        endObject();
    }

    private final void skipArray() throws IOException {
        beginArray();
        while (hasNext()) {
            skipValue();
        }
        endArray();
    }

    @Override // com.squareup.moshi.JsonReader
    public JsonReader.Token peek() throws IOException {
        int i = this.peeked;
        if (i == this.PEEKED_NONE) {
            doPeek();
            i = this.peeked;
        }
        if (i == this.PEEKED_BEGIN_OBJECT) {
            return JsonReader.Token.BEGIN_OBJECT;
        }
        if (i == this.PEEKED_END_OBJECT) {
            return JsonReader.Token.END_OBJECT;
        }
        if (i == this.PEEKED_BEGIN_ARRAY) {
            return JsonReader.Token.BEGIN_ARRAY;
        }
        if (i == this.PEEKED_END_ARRAY) {
            return JsonReader.Token.END_ARRAY;
        }
        if (i == this.PEEKED_STRING || i == this.PEEKED_BUFFERED) {
            return JsonReader.Token.STRING;
        }
        if (i == this.PEEKED_TRUE || i == this.PEEKED_FALSE) {
            return JsonReader.Token.BOOLEAN;
        }
        if (i == this.PEEKED_NULL) {
            return JsonReader.Token.NULL;
        }
        if (i == this.PEEKED_DOUBLE || i == this.PEEKED_LONG) {
            return JsonReader.Token.NUMBER;
        }
        throw new AssertionError();
    }

    private final int doPeek() throws IOException {
        int i = this.scopes[this.stackSize - 1];
        if (i == 1) {
            this.scopes[this.stackSize - 1] = 2;
        } else if (i == 3 || i == 5) {
            this.scopes[this.stackSize - 1] = 4;
        } else if (i == 4) {
            this.scopes[this.stackSize - 1] = 5;
        } else if (i == 6) {
            this.scopes[this.stackSize - 1] = 7;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        if (this.buffer.size() == 0) {
            int i2 = this.PEEKED_EOF;
            this.peeked = i2;
            return i2;
        }
        byte b = this.buffer.readByte();
        if (MsgpackFormatKt.contains(MsgpackFormat.INSTANCE.getARRAY(), b)) {
            this.peeked = this.PEEKED_BEGIN_ARRAY;
        } else if (MsgpackFormatKt.contains(MsgpackFormat.INSTANCE.getMAP(), b)) {
            this.peeked = this.PEEKED_BEGIN_OBJECT;
        } else if (MsgpackFormatKt.contains(MsgpackFormat.INSTANCE.getSTR(), b)) {
            this.peeked = this.PEEKED_STRING;
        } else if ((-32 <= b && 127 >= b) || b == -52 || b == -51 || b == -50 || b == -49 || b == -48 || b == -47 || b == -46 || b == -45) {
            this.peeked = this.PEEKED_LONG;
        } else if (b == -54 || b == -53) {
            this.peeked = this.PEEKED_DOUBLE;
        } else if (b == -64) {
            this.peeked = this.PEEKED_NULL;
        } else if (b == -61) {
            this.peeked = this.PEEKED_TRUE;
        } else if (b == -62) {
            this.peeked = this.PEEKED_FALSE;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Msgpack format tag not yet supported: 0x");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
            Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(format, *args)");
            sb.append(str);
            throw new IllegalStateException(sb.toString());
        }
        this.currentTag = b;
        return this.peeked;
    }
}
