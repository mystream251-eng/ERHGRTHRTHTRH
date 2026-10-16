package com.apollographql.apollo.api.internal.json;

import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.io.EOFException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: BufferedSourceJsonReader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 L2\u00020\u0001:\u0001LB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001e\u001a\u00020\u0001H\u0016J\b\u0010\u001f\u001a\u00020\u0001H\u0016J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0016J\b\u0010#\u001a\u00020\u0017H\u0002J\b\u0010$\u001a\u00020\u0001H\u0016J\b\u0010%\u001a\u00020\u0001H\u0016J\b\u0010&\u001a\u00020\u0014H\u0016J\t\u0010'\u001a\u00020\bH\u0096\u0002J\u0010\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\bH\u0016J\b\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020\u0017H\u0016J\b\u0010/\u001a\u00020\u0019H\u0016J\b\u00100\u001a\u00020\u0014H\u0016J\u0010\u00101\u001a\u00020\u00172\u0006\u00102\u001a\u00020\bH\u0002J\u0015\u00103\u001a\u0004\u0018\u0001H4\"\u0004\b\u0000\u00104H\u0016¢\u0006\u0002\u00105J\u0010\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u000208H\u0002J\n\u00109\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010:\u001a\u00020\u0014H\u0002J\b\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020\u0017H\u0002J\b\u0010>\u001a\u00020\u0017H\u0002J\b\u0010?\u001a\u00020!H\u0016J\u0010\u0010@\u001a\u00020!2\u0006\u0010A\u001a\u00020\u0017H\u0002J\b\u0010B\u001a\u00020*H\u0002J\u0010\u0010C\u001a\u00020!2\u0006\u00107\u001a\u000208H\u0002J\u0010\u0010D\u001a\u00020\b2\u0006\u0010E\u001a\u00020\u0014H\u0002J\b\u0010F\u001a\u00020!H\u0002J\b\u0010G\u001a\u00020!H\u0002J\b\u0010H\u001a\u00020!H\u0016J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u0014H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/apollographql/apollo/api/internal/json/BufferedSourceJsonReader;", "Lcom/apollographql/apollo/api/internal/json/JsonReader;", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "buffer", "Lokio/Buffer;", "failOnUnknown", "", "getFailOnUnknown", "()Z", "setFailOnUnknown", "(Z)V", "lenient", "getLenient", "setLenient", "pathIndices", "", "pathNames", "", "", "[Ljava/lang/String;", "peeked", "", "peekedLong", "", "peekedNumberLength", "peekedString", "stack", "stackSize", "beginArray", "beginObject", "checkLenient", "", ToolboxAnalyticsModuleKt.EVENT_CLOSE, "doPeek", "endArray", "endObject", "getPath", "hasNext", "isLiteral", "c", "", "nextBoolean", "nextDouble", "", "nextInt", "nextLong", "nextName", "nextNonWhitespace", "throwOnEof", "nextNull", ExifInterface.GPS_DIRECTION_TRUE, "()Ljava/lang/Object;", "nextQuotedValue", "runTerminator", "Lokio/ByteString;", "nextString", "nextUnquotedValue", "peek", "Lcom/apollographql/apollo/api/internal/json/JsonReader$Token;", "peekKeyword", "peekNumber", "promoteNameToValue", "push", "newTop", "readEscapeCharacter", "skipQuotedValue", "skipTo", "toFind", "skipToEndOfLine", "skipUnquotedValue", "skipValue", "syntaxError", "Lcom/apollographql/apollo/api/internal/json/JsonEncodingException;", "message", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class BufferedSourceJsonReader implements JsonReader {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final Buffer buffer;
    private boolean failOnUnknown;
    private boolean lenient;
    private final int[] pathIndices;
    private final String[] pathNames;
    private int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private final BufferedSource source;
    private final int[] stack;
    private int stackSize;
    private static final ByteString SINGLE_QUOTE_OR_SLASH = ByteString.INSTANCE.encodeUtf8("'\\");
    private static final ByteString DOUBLE_QUOTE_OR_SLASH = ByteString.INSTANCE.encodeUtf8("\"\\");
    private static final ByteString UNQUOTED_STRING_TERMINALS = ByteString.INSTANCE.encodeUtf8("{}[]:, \n\t\r/\\;#=");
    private static final ByteString LINEFEED_OR_CARRIAGE_RETURN = ByteString.INSTANCE.encodeUtf8("\n\r");

    public BufferedSourceJsonReader(BufferedSource source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.source = source;
        this.buffer = source.getBuffer();
        int[] iArr = new int[32];
        iArr[0] = 6;
        this.stack = iArr;
        this.stackSize = 1;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public boolean getLenient() {
        return this.lenient;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public void setLenient(boolean z) {
        this.lenient = z;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public boolean getFailOnUnknown() {
        return this.failOnUnknown;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public void setFailOnUnknown(boolean z) {
        this.failOnUnknown = z;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public JsonReader beginArray() throws IOException {
        BufferedSourceJsonReader bufferedSourceJsonReader = this;
        Integer numValueOf = Integer.valueOf(bufferedSourceJsonReader.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : bufferedSourceJsonReader.doPeek()) == 3) {
            bufferedSourceJsonReader.push(1);
            bufferedSourceJsonReader.pathIndices[bufferedSourceJsonReader.stackSize - 1] = 0;
            bufferedSourceJsonReader.peeked = 0;
            return this;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + bufferedSourceJsonReader.peek() + " at path " + bufferedSourceJsonReader.getPath());
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public JsonReader endArray() throws IOException {
        BufferedSourceJsonReader bufferedSourceJsonReader = this;
        Integer numValueOf = Integer.valueOf(bufferedSourceJsonReader.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : bufferedSourceJsonReader.doPeek()) == 4) {
            int i = bufferedSourceJsonReader.stackSize - 1;
            bufferedSourceJsonReader.stackSize = i;
            int[] iArr = bufferedSourceJsonReader.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
            bufferedSourceJsonReader.peeked = 0;
            return this;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + bufferedSourceJsonReader.peek() + " at path " + bufferedSourceJsonReader.getPath());
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public JsonReader beginObject() throws IOException {
        BufferedSourceJsonReader bufferedSourceJsonReader = this;
        Integer numValueOf = Integer.valueOf(bufferedSourceJsonReader.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : bufferedSourceJsonReader.doPeek()) == 1) {
            bufferedSourceJsonReader.push(3);
            bufferedSourceJsonReader.peeked = 0;
            return this;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + bufferedSourceJsonReader.peek() + " at path " + bufferedSourceJsonReader.getPath());
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public JsonReader endObject() throws IOException {
        BufferedSourceJsonReader bufferedSourceJsonReader = this;
        Integer numValueOf = Integer.valueOf(bufferedSourceJsonReader.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : bufferedSourceJsonReader.doPeek()) == 2) {
            int i = bufferedSourceJsonReader.stackSize - 1;
            bufferedSourceJsonReader.stackSize = i;
            bufferedSourceJsonReader.pathNames[i] = (String) null;
            int[] iArr = bufferedSourceJsonReader.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
            bufferedSourceJsonReader.peeked = 0;
            return this;
        }
        throw new JsonDataException("Expected END_OBJECT but was " + bufferedSourceJsonReader.peek() + " at path " + bufferedSourceJsonReader.getPath());
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public boolean hasNext() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : doPeek();
        return (iIntValue == 2 || iIntValue == 4) ? false : true;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public JsonReader.Token peek() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        switch (numValueOf != null ? numValueOf.intValue() : doPeek()) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
                return JsonReader.Token.NAME;
            case 15:
            case 16:
                return JsonReader.Token.NUMBER;
            case 17:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    private final int doPeek() throws IOException {
        int[] iArr = this.stack;
        int i = this.stackSize;
        int i2 = iArr[i - 1];
        switch (i2) {
            case 1:
                iArr[i - 1] = 2;
                break;
            case 2:
                int iNextNonWhitespace = nextNonWhitespace(true);
                this.buffer.readByte();
                char c = (char) iNextNonWhitespace;
                if (c != ',') {
                    if (c == ';') {
                        checkLenient();
                    } else {
                        if (c == ']') {
                            this.peeked = 4;
                            return 4;
                        }
                        throw syntaxError("Unterminated array");
                    }
                }
                break;
            case 3:
            case 5:
                iArr[i - 1] = 4;
                if (i2 == 5) {
                    int iNextNonWhitespace2 = nextNonWhitespace(true);
                    this.buffer.readByte();
                    char c2 = (char) iNextNonWhitespace2;
                    if (c2 != ',') {
                        if (c2 != ';') {
                            if (c2 == '}') {
                                this.peeked = 2;
                                return 2;
                            }
                            throw syntaxError("Unterminated object");
                        }
                        checkLenient();
                    }
                }
                char cNextNonWhitespace = (char) nextNonWhitespace(true);
                if (cNextNonWhitespace == '\"') {
                    this.buffer.readByte();
                    this.peeked = 13;
                    return 13;
                }
                if (cNextNonWhitespace == '\'') {
                    this.buffer.readByte();
                    checkLenient();
                    this.peeked = 12;
                    return 12;
                }
                if (cNextNonWhitespace != '}') {
                    checkLenient();
                    if (isLiteral(cNextNonWhitespace)) {
                        this.peeked = 14;
                        return 14;
                    }
                    throw syntaxError("Expected name");
                }
                if (i2 != 5) {
                    this.buffer.readByte();
                    this.peeked = 2;
                    return 2;
                }
                throw syntaxError("Expected name");
            case 4:
                iArr[i - 1] = 5;
                int iNextNonWhitespace3 = nextNonWhitespace(true);
                this.buffer.readByte();
                char c3 = (char) iNextNonWhitespace3;
                if (c3 != ':') {
                    if (c3 == '=') {
                        checkLenient();
                        if (this.source.request(1L) && this.buffer.getByte(0L) == ((byte) 62)) {
                            this.buffer.readByte();
                        }
                    } else {
                        throw syntaxError("Expected ':'");
                    }
                }
                break;
            case 6:
                iArr[i - 1] = 7;
                break;
            case 7:
                if (nextNonWhitespace(false) == -1) {
                    this.peeked = 17;
                    return 17;
                }
                checkLenient();
                break;
                break;
            default:
                if (!(i2 != 8)) {
                    throw new IllegalStateException("JsonReader is closed".toString());
                }
                break;
        }
        char cNextNonWhitespace2 = (char) nextNonWhitespace(true);
        if (cNextNonWhitespace2 == '\"') {
            this.buffer.readByte();
            this.peeked = 9;
            return 9;
        }
        if (cNextNonWhitespace2 == '\'') {
            checkLenient();
            this.buffer.readByte();
            this.peeked = 8;
            return 8;
        }
        if (cNextNonWhitespace2 == ',' || cNextNonWhitespace2 == ';') {
            if (i2 == 1 || i2 == 2) {
                checkLenient();
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        }
        if (cNextNonWhitespace2 == '[') {
            this.buffer.readByte();
            this.peeked = 3;
            return 3;
        }
        if (cNextNonWhitespace2 == ']') {
            if (i2 == 1) {
                this.buffer.readByte();
                this.peeked = 4;
                return 4;
            }
            if (i2 == 1 || i2 == 2) {
                checkLenient();
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        }
        if (cNextNonWhitespace2 == '{') {
            this.buffer.readByte();
            this.peeked = 1;
            return 1;
        }
        int iPeekKeyword = peekKeyword();
        if (iPeekKeyword != 0) {
            return iPeekKeyword;
        }
        int iPeekNumber = peekNumber();
        if (iPeekNumber != 0) {
            return iPeekNumber;
        }
        if (!isLiteral((char) this.buffer.getByte(0L))) {
            throw syntaxError("Expected value");
        }
        checkLenient();
        this.peeked = 10;
        return 10;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0029  */
    /* JADX WARN: Code duplicated, block: B:17:0x002f  */
    private final int peekKeyword() throws IOException {
        int i;
        String str;
        String str2;
        byte b = this.buffer.getByte(0L);
        if (b == 70) {
            i = 6;
            str = "false";
            str2 = "FALSE";
        } else if (b == 78) {
            i = 7;
            str = "null";
            str2 = "NULL";
        } else {
            if (b != 84) {
                if (b == 102) {
                    i = 6;
                    str = "false";
                    str2 = "FALSE";
                } else if (b == 110) {
                    i = 7;
                    str = "null";
                    str2 = "NULL";
                } else if (b != 116) {
                    return 0;
                }
            }
            i = 5;
            str = "true";
            str2 = "TRUE";
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            long j = i2;
            if (!this.source.request(1 + j)) {
                return 0;
            }
            byte b2 = this.buffer.getByte(j);
            if (b2 != ((byte) str.charAt(i2)) && b2 != ((byte) str2.charAt(i2))) {
                return 0;
            }
        }
        long j2 = length;
        if (this.source.request(1 + j2) && isLiteral((char) this.buffer.getByte(j2))) {
            return 0;
        }
        this.buffer.skip(j2);
        this.peeked = i;
        return i;
    }

    private final int peekNumber() throws IOException {
        long j;
        boolean z = true;
        int i = 0;
        char c = 0;
        long j2 = 0;
        boolean z2 = false;
        while (true) {
            j = i;
            if (!this.source.request(1 + j)) {
                break;
            }
            byte b = this.buffer.getByte(j);
            char c2 = (char) b;
            char c3 = 3;
            if (c2 != '+') {
                if (c2 == 'E' || c2 == 'e') {
                    if (c != 2 && c != 4) {
                        return 0;
                    }
                    c = 5;
                } else if (c2 == '-') {
                    c3 = 6;
                    if (c == 0) {
                        c = 1;
                        z2 = true;
                    } else if (c != 5) {
                        return 0;
                    }
                } else if (c2 != '.') {
                    byte b2 = (byte) 48;
                    if (b < b2 || b > ((byte) 57)) {
                        if (!isLiteral(c2)) {
                            break;
                        }
                        return 0;
                    }
                    if (c == 0 || c == 1) {
                        j2 = -(b - b2);
                        c = 2;
                    } else if (c != 2) {
                        if (c == 3) {
                            c = 4;
                        } else if (c == 5 || c == 6) {
                            c = 7;
                        }
                    } else {
                        if (j2 == 0) {
                            return 0;
                        }
                        long j3 = (((long) 10) * j2) - ((long) (b - b2));
                        z = (z && ((j2 > (-922337203685477580L) ? 1 : (j2 == (-922337203685477580L) ? 0 : -1)) > 0)) || (j2 == -922337203685477580L && j3 < j2);
                        j2 = j3;
                    }
                } else if (c != 2) {
                    return 0;
                }
                i++;
            } else {
                c3 = 6;
                if (c != 5) {
                    return 0;
                }
            }
            c = c3;
            i++;
        }
        if (c == 2 && z && (j2 != Long.MIN_VALUE || z2)) {
            if (!z2) {
                j2 = -j2;
            }
            this.peekedLong = j2;
            this.buffer.skip(j);
            this.peeked = 15;
            return 15;
        }
        if (c != 2 && c != 4 && c != 7) {
            return 0;
        }
        this.peekedNumberLength = i;
        this.peeked = 16;
        return 16;
    }

    private final boolean isLiteral(char c) throws IOException {
        if (c == '\t' || c == '\n' || c == '\r' || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (c != '/' && c != '=') {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public String nextName() throws IOException {
        String strNextQuotedValue;
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        switch (numValueOf != null ? numValueOf.intValue() : doPeek()) {
            case 12:
                strNextQuotedValue = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
                break;
            case 13:
                strNextQuotedValue = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                break;
            case 14:
                strNextQuotedValue = nextUnquotedValue();
                break;
            default:
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = strNextQuotedValue;
        return strNextQuotedValue;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public String nextString() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        String strValueOf = null;
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : doPeek();
        if (iIntValue == 15) {
            strValueOf = String.valueOf(this.peekedLong);
        } else if (iIntValue != 16) {
            switch (iIntValue) {
                case 8:
                    strValueOf = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
                    break;
                case 9:
                    strValueOf = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                    break;
                case 10:
                    strValueOf = nextUnquotedValue();
                    break;
                case 11:
                    String str = this.peekedString;
                    if (str != null) {
                        this.peekedString = (String) null;
                        strValueOf = str;
                    }
                    break;
                default:
                    throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPath());
            }
        } else {
            strValueOf = this.buffer.readUtf8(this.peekedNumberLength);
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return strValueOf;
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public boolean nextBoolean() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : doPeek();
        if (iIntValue == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iIntValue == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        }
        throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + getPath());
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public <T> T nextNull() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : doPeek()) == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return null;
        }
        throw new JsonDataException("Expected null but was " + peek() + " at path " + getPath());
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public double nextDouble() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : doPeek();
        if (iIntValue == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (iIntValue == 16) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else if (iIntValue == 9) {
            this.peekedString = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (iIntValue == 8) {
            this.peekedString = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (iIntValue == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (iIntValue != 11) {
            throw new JsonDataException("Expected a double but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            double d = Double.parseDouble(str);
            if (!getLenient() && (Double.isNaN(d) || Double.isInfinite(d))) {
                throw new JsonEncodingException("JSON forbids NaN and infinities: " + d + " at path " + getPath());
            }
            this.peekedString = (String) null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return d;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.peekedString + " at path " + getPath());
        }
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public long nextLong() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : doPeek();
        if (iIntValue == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (iIntValue == 16) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else {
            if (iIntValue == 9 || iIntValue == 8) {
                String strNextQuotedValue = nextQuotedValue(iIntValue == 9 ? DOUBLE_QUOTE_OR_SLASH : SINGLE_QUOTE_OR_SLASH);
                this.peekedString = strNextQuotedValue;
                if (strNextQuotedValue == null) {
                    try {
                        Intrinsics.throwNpe();
                    } catch (NumberFormatException unused) {
                    }
                }
                long j = Long.parseLong(strNextQuotedValue);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            }
            if (iIntValue != 11) {
                throw new JsonDataException("Expected a long but was " + peek() + " at path " + getPath());
            }
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            double d = Double.parseDouble(str);
            long j2 = (long) d;
            if (j2 != d) {
                throw new JsonDataException("Expected a long but was " + this.peekedString + " at path " + getPath());
            }
            this.peekedString = (String) null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr3[i3] = iArr3[i3] + 1;
            return j2;
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected a long but was " + this.peekedString + " at path " + getPath());
        }
    }

    private final String nextQuotedValue(ByteString runTerminator) throws IOException {
        StringBuilder sb = (StringBuilder) null;
        while (true) {
            long jIndexOfElement = this.source.indexOfElement(runTerminator);
            if (jIndexOfElement == -1) {
                throw syntaxError("Unterminated string");
            }
            if (this.buffer.getByte(jIndexOfElement) != ((byte) 92)) {
                if (sb == null) {
                    String utf8 = this.buffer.readUtf8(jIndexOfElement);
                    this.buffer.readByte();
                    return utf8;
                }
                sb.append(this.buffer.readUtf8(jIndexOfElement));
                this.buffer.readByte();
                String string = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "builder.toString()");
                return string;
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.buffer.readUtf8(jIndexOfElement));
            this.buffer.readByte();
            sb.append(readEscapeCharacter());
        }
    }

    private final String nextUnquotedValue() throws IOException {
        long jIndexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        return jIndexOfElement != -1 ? this.buffer.readUtf8(jIndexOfElement) : this.buffer.readUtf8();
    }

    private final void skipQuotedValue(ByteString runTerminator) throws IOException {
        while (true) {
            long jIndexOfElement = this.source.indexOfElement(runTerminator);
            if (jIndexOfElement == -1) {
                throw syntaxError("Unterminated string");
            }
            if (this.buffer.getByte(jIndexOfElement) == ((byte) 92)) {
                this.buffer.skip(jIndexOfElement + 1);
                readEscapeCharacter();
            } else {
                this.buffer.skip(jIndexOfElement + 1);
                return;
            }
        }
    }

    private final void skipUnquotedValue() throws IOException {
        long jIndexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        Buffer buffer = this.buffer;
        if (jIndexOfElement == -1) {
            jIndexOfElement = buffer.size();
        }
        buffer.skip(jIndexOfElement);
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public int nextInt() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : doPeek();
        if (iIntValue == 15) {
            long j = this.peekedLong;
            int i = (int) j;
            if (j != i) {
                throw new JsonDataException("Expected an int but was " + this.peekedLong + " at path " + getPath());
            }
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        if (iIntValue == 16) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else {
            if (iIntValue == 9 || iIntValue == 8) {
                String strNextQuotedValue = nextQuotedValue(iIntValue == 9 ? DOUBLE_QUOTE_OR_SLASH : SINGLE_QUOTE_OR_SLASH);
                this.peekedString = strNextQuotedValue;
                if (strNextQuotedValue == null) {
                    try {
                        Intrinsics.throwNpe();
                    } catch (NumberFormatException unused) {
                    }
                }
                int i3 = Integer.parseInt(strNextQuotedValue);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            }
            if (iIntValue != 11) {
                throw new JsonDataException("Expected an int but was " + peek() + " at path " + getPath());
            }
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            double d = Double.parseDouble(str);
            int i5 = (int) d;
            if (i5 != d) {
                throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
            }
            this.peekedString = (String) null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i6 = this.stackSize - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return i5;
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public void skipValue() throws IOException {
        if (getFailOnUnknown()) {
            throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
        }
        int i = 0;
        do {
            Integer numValueOf = Integer.valueOf(this.peeked);
            if (numValueOf.intValue() == 0) {
                numValueOf = null;
            }
            switch (numValueOf != null ? numValueOf.intValue() : doPeek()) {
                case 1:
                    push(3);
                    i++;
                    break;
                case 2:
                    this.stackSize--;
                    i--;
                    break;
                case 3:
                    push(1);
                    i++;
                    break;
                case 4:
                    this.stackSize--;
                    i--;
                    break;
                case 8:
                case 12:
                    skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
                    break;
                case 9:
                case 13:
                    skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                    break;
                case 10:
                case 14:
                    skipUnquotedValue();
                    break;
                case 16:
                    this.buffer.skip(this.peekedNumberLength);
                    break;
            }
            this.peeked = 0;
        } while (i != 0);
        int[] iArr = this.pathIndices;
        int i2 = this.stackSize;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.pathNames[i2 - 1] = "null";
    }

    private final void push(int newTop) {
        int i = this.stackSize;
        int[] iArr = this.stack;
        if (i == iArr.length) {
            throw new JsonDataException("Nesting too deep at " + getPath());
        }
        this.stackSize = i + 1;
        iArr[i] = newTop;
    }

    private final int nextNonWhitespace(boolean throwOnEof) throws IOException {
        byte b;
        while (true) {
            int i = 0;
            while (true) {
                long j = i;
                if (!this.source.request(j + 1)) {
                    if (throwOnEof) {
                        throw new EOFException("End of input");
                    }
                    return -1;
                }
                i++;
                b = this.buffer.getByte(j);
                if (b == 10 || b == 32 || b == 13 || b == 9) {
                }
            }
            this.buffer.skip(((long) i) - 1);
            if (b == 47) {
                if (!this.source.request(2L)) {
                    return b;
                }
                checkLenient();
                char c = (char) this.buffer.getByte(1L);
                if (c == '*') {
                    this.buffer.readByte();
                    this.buffer.readByte();
                    if (!skipTo("*/")) {
                        throw syntaxError("Unterminated comment");
                    }
                    this.buffer.readByte();
                    this.buffer.readByte();
                } else {
                    if (c != '/') {
                        return b;
                    }
                    this.buffer.readByte();
                    this.buffer.readByte();
                    skipToEndOfLine();
                }
            } else {
                if (b != 35) {
                    return b;
                }
                checkLenient();
                skipToEndOfLine();
            }
        }
    }

    private final void checkLenient() throws IOException {
        if (!getLenient()) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private final void skipToEndOfLine() throws IOException {
        long jIndexOfElement = this.source.indexOfElement(LINEFEED_OR_CARRIAGE_RETURN);
        Buffer buffer = this.buffer;
        buffer.skip(jIndexOfElement != -1 ? jIndexOfElement + 1 : buffer.size());
    }

    private final boolean skipTo(String toFind) throws IOException {
        while (true) {
            if (!this.source.request(toFind.length())) {
                return false;
            }
            int length = toFind.length();
            for (int i = 0; i < length; i++) {
                if (this.buffer.getByte(i) != ((byte) toFind.charAt(i))) {
                    this.buffer.readByte();
                }
            }
            return true;
        }
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public String getPath() {
        return JsonScope.INSTANCE.getPath(this.stackSize, this.stack, this.pathNames, this.pathIndices);
    }

    private final char readEscapeCharacter() throws IOException {
        int i;
        if (!this.source.request(1L)) {
            throw syntaxError("Unterminated escape sequence");
        }
        char c = (char) this.buffer.readByte();
        if (c != 'u') {
            if (c == 't') {
                return '\t';
            }
            if (c == 'b') {
                return '\b';
            }
            if (c == 'n') {
                return '\n';
            }
            if (c == 'r') {
                return '\r';
            }
            if (c == '\n' || c == '\'' || c == '\"' || c == '\\' || c == '/' || getLenient()) {
                return c;
            }
            throw syntaxError("Invalid escape sequence: \\" + c);
        }
        if (!this.source.request(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + getPath());
        }
        char c2 = (char) 0;
        for (int i2 = 0; i2 < 4; i2++) {
            byte b = this.buffer.getByte(i2);
            char c3 = (char) (c2 << 4);
            byte b2 = (byte) 48;
            if (b < b2 || b > ((byte) 57)) {
                byte b3 = (byte) 97;
                if ((b < b3 || b > ((byte) 102)) && (b < (b3 = (byte) 65) || b > ((byte) 70))) {
                    throw syntaxError("\\u" + this.buffer.readUtf8(4L));
                }
                i = (b - b3) + 10;
            } else {
                i = b - b2;
            }
            c2 = (char) (c3 + i);
        }
        this.buffer.skip(4L);
        return c2;
    }

    private final JsonEncodingException syntaxError(String message) {
        return new JsonEncodingException(message + " at path " + getPath());
    }

    @Override // com.apollographql.apollo.api.internal.json.JsonReader
    public void promoteNameToValue() throws IOException {
        if (hasNext()) {
            this.peekedString = nextName();
            this.peeked = 11;
        }
    }
}
