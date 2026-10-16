package com.squareup.moshi;

import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okio.BufferedSink;
import okio.BufferedSource;

/* JADX INFO: compiled from: MsgpackFormat.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J\u0011\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0003H\u0086\u0002J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u0016\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0017H\u0002J\u0016\u0010\"\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006#"}, d2 = {"Lcom/squareup/moshi/MsgpackFormatType;", "", NotificationsContract.COLUMN_TAG, "", "maxSize", "", "isFix", "", "(BJZ)V", "()Z", "getMaxSize", "()J", "getTag", "()B", "component1", "component2", "component3", "contains", "value", "copy", "equals", "other", "hashCode", "", "readSize", "source", "Lokio/BufferedSource;", "toString", "", "writeSize", "", "sink", "Lokio/BufferedSink;", "size", "writeTag", "moshipack"}, k = 1, mv = {1, 1, 13})
public final /* data */ class MsgpackFormatType {
    private final boolean isFix;
    private final long maxSize;
    private final byte tag;

    public static /* synthetic */ MsgpackFormatType copy$default(MsgpackFormatType msgpackFormatType, byte b, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            b = msgpackFormatType.tag;
        }
        if ((i & 2) != 0) {
            j = msgpackFormatType.maxSize;
        }
        if ((i & 4) != 0) {
            z = msgpackFormatType.isFix;
        }
        return msgpackFormatType.copy(b, j, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final byte getTag() {
        return this.tag;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getMaxSize() {
        return this.maxSize;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsFix() {
        return this.isFix;
    }

    public final MsgpackFormatType copy(byte tag, long maxSize, boolean isFix) {
        return new MsgpackFormatType(tag, maxSize, isFix);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof MsgpackFormatType) {
                MsgpackFormatType msgpackFormatType = (MsgpackFormatType) other;
                if (this.tag == msgpackFormatType.tag) {
                    if (this.maxSize == msgpackFormatType.maxSize) {
                        if (this.isFix == msgpackFormatType.isFix) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public int hashCode() {
        int i = this.tag * 31;
        long j = this.maxSize;
        int i2 = (i + ((int) (j ^ (j >>> 32)))) * 31;
        boolean z = this.isFix;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return i2 + r1;
    }

    public String toString() {
        return "MsgpackFormatType(tag=" + ((int) this.tag) + ", maxSize=" + this.maxSize + ", isFix=" + this.isFix + ")";
    }

    public MsgpackFormatType(byte b, long j, boolean z) {
        this.tag = b;
        this.maxSize = j;
        this.isFix = z;
    }

    public /* synthetic */ MsgpackFormatType(byte b, long j, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(b, j, (i & 4) != 0 ? false : z);
    }

    public final long getMaxSize() {
        return this.maxSize;
    }

    public final byte getTag() {
        return this.tag;
    }

    public final boolean isFix() {
        return this.isFix;
    }

    public final boolean contains(byte value) {
        if (this.isFix) {
            byte b = this.tag;
            long j = b;
            long j2 = ((long) b) + this.maxSize;
            long j3 = value;
            if (j <= j3 && j2 >= j3) {
                return true;
            }
        } else if (value == this.tag) {
            return true;
        }
        return false;
    }

    public final void writeTag(BufferedSink sink, int size) throws IOException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        sink.writeByte(this.tag + (this.isFix ? size : 0));
        if (this.isFix) {
            return;
        }
        writeSize(sink, size);
    }

    public final long readSize(BufferedSource source, byte value) throws IOException {
        int i;
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (this.isFix) {
            return value - this.tag;
        }
        long j = this.maxSize;
        long j2 = 255;
        if (j == 255) {
            i = source.readByte();
        } else {
            j2 = MsgpackFormat.SIZE_16;
            if (j == MsgpackFormat.SIZE_16) {
                i = source.readShort();
            } else {
                j2 = MsgpackFormat.SIZE_32;
                if (j == MsgpackFormat.SIZE_32) {
                    i = source.readInt();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to read size for tag type: 0x");
                    String string = Integer.toString(value, CharsKt.checkRadix(CharsKt.checkRadix(16)));
                    Intrinsics.checkExpressionValueIsNotNull(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
                    sb.append(string);
                    throw new IllegalStateException(sb.toString());
                }
            }
        }
        return ((long) i) & j2;
    }

    private final void writeSize(BufferedSink sink, int size) throws IOException {
        long j = this.maxSize;
        if (j == 255) {
            sink.writeByte(size);
        } else if (j == MsgpackFormat.SIZE_16) {
            sink.writeShort(size);
        } else if (j == MsgpackFormat.SIZE_32) {
            sink.writeInt(size);
        }
    }
}
