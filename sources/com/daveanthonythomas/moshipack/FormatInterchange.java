package com.daveanthonythomas.moshipack;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;

/* JADX INFO: compiled from: FormatInterchange.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/daveanthonythomas/moshipack/FormatInterchange;", "", "formatIn", "Lcom/daveanthonythomas/moshipack/Format;", "formatOut", "(Lcom/daveanthonythomas/moshipack/Format;Lcom/daveanthonythomas/moshipack/Format;)V", "getFormatIn", "()Lcom/daveanthonythomas/moshipack/Format;", "getFormatOut", "doArray", "", "reader", "Lcom/squareup/moshi/JsonReader;", "writer", "Lcom/squareup/moshi/JsonWriter;", "doObject", "doValue", "transform", "Lokio/Buffer;", "source", "Lokio/BufferedSource;", "moshipack"}, k = 1, mv = {1, 1, 13})
public final class FormatInterchange {
    private final Format formatIn;
    private final Format formatOut;

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

    public FormatInterchange(Format formatIn, Format formatOut) {
        Intrinsics.checkParameterIsNotNull(formatIn, "formatIn");
        Intrinsics.checkParameterIsNotNull(formatOut, "formatOut");
        this.formatIn = formatIn;
        this.formatOut = formatOut;
    }

    public final Format getFormatIn() {
        return this.formatIn;
    }

    public final Format getFormatOut() {
        return this.formatOut;
    }

    private final void transform(JsonReader reader, JsonWriter writer) throws IOException {
        doValue(reader, writer);
        writer.close();
        reader.close();
    }

    private final void doValue(JsonReader reader, JsonWriter writer) throws IOException {
        JsonReader.Token tokenPeek = reader.peek();
        if (tokenPeek == null) {
            return;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[tokenPeek.ordinal()]) {
            case 1:
                doArray(reader, writer);
                break;
            case 2:
                doObject(reader, writer);
                break;
            case 3:
                writer.value(reader.nextString());
                break;
            case 4:
                writer.value(reader.nextDouble());
                break;
            case 5:
                writer.value(reader.nextBoolean());
                break;
            case 6:
                writer.nullValue();
                reader.nextNull();
                break;
        }
    }

    private final void doObject(JsonReader reader, JsonWriter writer) throws IOException {
        reader.beginObject();
        writer.beginObject();
        while (reader.hasNext()) {
            writer.name(reader.nextName());
            doValue(reader, writer);
        }
        reader.endObject();
        writer.endObject();
    }

    private final void doArray(JsonReader reader, JsonWriter writer) throws IOException {
        reader.beginArray();
        writer.beginArray();
        while (reader.hasNext()) {
            doValue(reader, writer);
        }
        reader.endArray();
        writer.endArray();
    }

    public final Buffer transform(BufferedSource source) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, "source");
        Buffer buffer = new Buffer();
        transform(this.formatIn.reader(source), this.formatOut.writer(buffer));
        return buffer;
    }
}
