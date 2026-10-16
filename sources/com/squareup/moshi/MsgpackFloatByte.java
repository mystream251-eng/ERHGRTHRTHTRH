package com.squareup.moshi;

import kotlin.Metadata;

/* JADX INFO: compiled from: MsgpackWriterOptions.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/squareup/moshi/MsgpackFloatByte;", "", "byte", "", "(Ljava/lang/String;IB)V", "getByte", "()B", "FLOAT_32", "FLOAT_64", "moshipack"}, k = 1, mv = {1, 1, 13})
public enum MsgpackFloatByte {
    FLOAT_32(MsgpackFormat.FLOAT_32),
    FLOAT_64(MsgpackFormat.FLOAT_64);

    private final byte byte;

    MsgpackFloatByte(byte b) {
        this.byte = b;
    }

    public final byte getByte() {
        return this.byte;
    }
}
