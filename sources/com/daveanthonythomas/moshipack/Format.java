package com.daveanthonythomas.moshipack;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.MsgpackReader;
import com.squareup.moshi.MsgpackWriter;
import com.squareup.moshi.MsgpackWriterOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.BufferedSource;

/* JADX INFO: compiled from: FormatInterchange.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&\u0082\u0001\u0002\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/daveanthonythomas/moshipack/Format;", "", "()V", "reader", "Lcom/squareup/moshi/JsonReader;", "source", "Lokio/BufferedSource;", "writer", "Lcom/squareup/moshi/JsonWriter;", "sink", "Lokio/BufferedSink;", "Json", "Msgpack", "Lcom/daveanthonythomas/moshipack/Format$Json;", "Lcom/daveanthonythomas/moshipack/Format$Msgpack;", "moshipack"}, k = 1, mv = {1, 1, 13})
public abstract class Format {
    public abstract JsonReader reader(BufferedSource source);

    public abstract JsonWriter writer(BufferedSink sink);

    /* JADX INFO: compiled from: FormatInterchange.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\t0\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/daveanthonythomas/moshipack/Format$Json;", "Lcom/daveanthonythomas/moshipack/Format;", "()V", "reader", "Lcom/squareup/moshi/JsonReader;", "kotlin.jvm.PlatformType", "source", "Lokio/BufferedSource;", "writer", "Lcom/squareup/moshi/JsonWriter;", "sink", "Lokio/BufferedSink;", "moshipack"}, k = 1, mv = {1, 1, 13})
    public static final class Json extends Format {
        public Json() {
            super(null);
        }

        @Override // com.daveanthonythomas.moshipack.Format
        public JsonReader reader(BufferedSource source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            JsonReader jsonReaderOf = JsonReader.of(source);
            jsonReaderOf.setLenient(true);
            return jsonReaderOf;
        }

        @Override // com.daveanthonythomas.moshipack.Format
        public JsonWriter writer(BufferedSink sink) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            return JsonWriter.of(sink);
        }
    }

    private Format() {
    }

    public /* synthetic */ Format(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: FormatInterchange.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/daveanthonythomas/moshipack/Format$Msgpack;", "Lcom/daveanthonythomas/moshipack/Format;", "writerOptions", "Lcom/squareup/moshi/MsgpackWriterOptions;", "(Lcom/squareup/moshi/MsgpackWriterOptions;)V", "getWriterOptions", "()Lcom/squareup/moshi/MsgpackWriterOptions;", "reader", "Lcom/squareup/moshi/MsgpackReader;", "source", "Lokio/BufferedSource;", "writer", "Lcom/squareup/moshi/MsgpackWriter;", "sink", "Lokio/BufferedSink;", "moshipack"}, k = 1, mv = {1, 1, 13})
    public static final class Msgpack extends Format {
        private final MsgpackWriterOptions writerOptions;

        /* JADX WARN: Multi-variable type inference failed */
        public Msgpack() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Msgpack(MsgpackWriterOptions writerOptions) {
            super(null);
            Intrinsics.checkParameterIsNotNull(writerOptions, "writerOptions");
            this.writerOptions = writerOptions;
        }

        public /* synthetic */ Msgpack(MsgpackWriterOptions msgpackWriterOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new MsgpackWriterOptions(null, null, false, 7, null) : msgpackWriterOptions);
        }

        public final MsgpackWriterOptions getWriterOptions() {
            return this.writerOptions;
        }

        @Override // com.daveanthonythomas.moshipack.Format
        public MsgpackReader reader(BufferedSource source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new MsgpackReader(source);
        }

        @Override // com.daveanthonythomas.moshipack.Format
        public MsgpackWriter writer(BufferedSink sink) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            return new MsgpackWriter(sink);
        }
    }
}
