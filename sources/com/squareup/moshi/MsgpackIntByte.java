package com.squareup.moshi;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;

/* JADX INFO: compiled from: MsgpackWriterOptions.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001BR\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012A\u0010\u0004\u001a=\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f¢\u0006\u0002\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fRL\u0010\u0004\u001a=\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001b"}, d2 = {"Lcom/squareup/moshi/MsgpackIntByte;", "", "byte", "", "writer", "Lkotlin/Function3;", "Lokio/BufferedSink;", "Lkotlin/ParameterName;", "name", "", "value", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;IBLkotlin/jvm/functions/Function3;)V", "getByte", "()B", "getWriter", "()Lkotlin/jvm/functions/Function3;", "FIX_INT", "INT_8", "INT_16", "INT_32", "INT_64", "UINT_8", "UINT_16", "UINT_32", "UINT_64", "moshipack"}, k = 1, mv = {1, 1, 13})
public enum MsgpackIntByte {
    FIX_INT((byte) 0, MsgpackWriterOptionsKt.FIX_WRITER),
    INT_8(MsgpackFormat.INT_8, MsgpackWriterOptionsKt.BYTE_WRITER),
    INT_16(MsgpackFormat.INT_16, MsgpackWriterOptionsKt.SHORT_WRITER),
    INT_32(MsgpackFormat.INT_32, MsgpackWriterOptionsKt.INT_WRITER),
    INT_64(MsgpackFormat.INT_64, MsgpackWriterOptionsKt.LONG_WRITER),
    UINT_8(MsgpackFormat.UINT_8, MsgpackWriterOptionsKt.BYTE_WRITER),
    UINT_16(MsgpackFormat.UINT_16, MsgpackWriterOptionsKt.SHORT_WRITER),
    UINT_32(MsgpackFormat.UINT_32, MsgpackWriterOptionsKt.INT_WRITER),
    UINT_64(MsgpackFormat.UINT_64, MsgpackWriterOptionsKt.LONG_WRITER);

    private final byte byte;
    private final Function3<BufferedSink, Byte, Number, Unit> writer;

    MsgpackIntByte(byte b, Function3 writer) {
        Intrinsics.checkParameterIsNotNull(writer, "writer");
        this.byte = b;
        this.writer = writer;
    }

    public final byte getByte() {
        return this.byte;
    }

    public final Function3<BufferedSink, Byte, Number, Unit> getWriter() {
        return this.writer;
    }
}
