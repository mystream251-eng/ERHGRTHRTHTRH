package com.squareup.moshi;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;

/* JADX INFO: compiled from: MsgpackWriterOptions.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"I\u0010\u0000\u001a=\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\u0002\b\nX\u0082\u0004¢\u0006\u0002\n\u0000\"I\u0010\u000b\u001a=\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\u0002\b\nX\u0082\u0004¢\u0006\u0002\n\u0000\"I\u0010\f\u001a=\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\u0002\b\nX\u0082\u0004¢\u0006\u0002\n\u0000\"I\u0010\r\u001a=\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\u0002\b\nX\u0082\u0004¢\u0006\u0002\n\u0000\"I\u0010\u000e\u001a=\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\u0002\b\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"BYTE_WRITER", "Lkotlin/Function3;", "Lokio/BufferedSink;", "", "Lkotlin/ParameterName;", "name", "byte", "", "value", "", "Lkotlin/ExtensionFunctionType;", "FIX_WRITER", "INT_WRITER", "LONG_WRITER", "SHORT_WRITER", "moshipack"}, k = 2, mv = {1, 1, 13})
public final class MsgpackWriterOptionsKt {
    private static final Function3<BufferedSink, Byte, Number, Unit> FIX_WRITER = new Function3<BufferedSink, Byte, Number, Unit>() { // from class: com.squareup.moshi.MsgpackWriterOptionsKt$FIX_WRITER$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BufferedSink bufferedSink, Byte b, Number number) throws IOException {
            invoke(bufferedSink, b.byteValue(), number);
            return Unit.INSTANCE;
        }

        public final void invoke(BufferedSink receiver$0, byte b, Number value) throws IOException {
            Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
            Intrinsics.checkParameterIsNotNull(value, "value");
            receiver$0.writeByte(value.intValue());
        }
    };
    private static final Function3<BufferedSink, Byte, Number, Unit> BYTE_WRITER = new Function3<BufferedSink, Byte, Number, Unit>() { // from class: com.squareup.moshi.MsgpackWriterOptionsKt$BYTE_WRITER$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BufferedSink bufferedSink, Byte b, Number number) throws IOException {
            invoke(bufferedSink, b.byteValue(), number);
            return Unit.INSTANCE;
        }

        public final void invoke(BufferedSink receiver$0, byte b, Number value) throws IOException {
            Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
            Intrinsics.checkParameterIsNotNull(value, "value");
            receiver$0.writeByte(b);
            receiver$0.writeByte(value.intValue());
        }
    };
    private static final Function3<BufferedSink, Byte, Number, Unit> SHORT_WRITER = new Function3<BufferedSink, Byte, Number, Unit>() { // from class: com.squareup.moshi.MsgpackWriterOptionsKt$SHORT_WRITER$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BufferedSink bufferedSink, Byte b, Number number) throws IOException {
            invoke(bufferedSink, b.byteValue(), number);
            return Unit.INSTANCE;
        }

        public final void invoke(BufferedSink receiver$0, byte b, Number value) throws IOException {
            Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
            Intrinsics.checkParameterIsNotNull(value, "value");
            receiver$0.writeByte(b);
            receiver$0.writeShort(value.intValue());
        }
    };
    private static final Function3<BufferedSink, Byte, Number, Unit> INT_WRITER = new Function3<BufferedSink, Byte, Number, Unit>() { // from class: com.squareup.moshi.MsgpackWriterOptionsKt$INT_WRITER$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BufferedSink bufferedSink, Byte b, Number number) throws IOException {
            invoke(bufferedSink, b.byteValue(), number);
            return Unit.INSTANCE;
        }

        public final void invoke(BufferedSink receiver$0, byte b, Number value) throws IOException {
            Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
            Intrinsics.checkParameterIsNotNull(value, "value");
            receiver$0.writeByte(b);
            receiver$0.writeInt(value.intValue());
        }
    };
    private static final Function3<BufferedSink, Byte, Number, Unit> LONG_WRITER = new Function3<BufferedSink, Byte, Number, Unit>() { // from class: com.squareup.moshi.MsgpackWriterOptionsKt$LONG_WRITER$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BufferedSink bufferedSink, Byte b, Number number) throws IOException {
            invoke(bufferedSink, b.byteValue(), number);
            return Unit.INSTANCE;
        }

        public final void invoke(BufferedSink receiver$0, byte b, Number value) throws IOException {
            Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
            Intrinsics.checkParameterIsNotNull(value, "value");
            receiver$0.writeByte(b);
            receiver$0.writeLong(value.longValue());
        }
    };
}
