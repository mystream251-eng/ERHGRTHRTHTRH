package com.benasher44.uuid;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.nio.ByteBuffer;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: uuid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a\r\u0010\u000b\u001a\u00060\u0002j\u0002`\fH\u0086\b\u001a\u0012\u0010\r\u001a\u00060\u0002j\u0002`\f2\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0012\u0010\u0010\u001a\u00060\u0002j\u0002`\f2\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u001c\u0010\u0011\u001a\u00020\u0012*\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0016\u0010\t\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\b*\n\u0010\u0015\"\u00020\u00022\u00020\u0002¨\u0006\u0016"}, d2 = {"bytes", "", "Ljava/util/UUID;", "getBytes", "(Ljava/util/UUID;)[B", "variant", "", "getVariant", "(Ljava/util/UUID;)I", "version", "getVersion", "uuid4", "Lcom/benasher44/uuid/Uuid;", "uuidFrom", "string", "", "uuidOf", "segmentToLong", "", "start", TtmlNode.END, "Uuid", "uuid"}, k = 2, mv = {1, 1, 16})
public final class UuidKt {
    public static /* synthetic */ void Uuid$annotations() {
    }

    public static final UUID uuidOf(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        if (!(bytes.length == 16)) {
            throw new IllegalArgumentException(("Invalid UUID bytes. Expected 16 bytes; found " + bytes.length).toString());
        }
        ByteBuffer it = ByteBuffer.wrap(bytes);
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        return new UUID(it.getLong(), it.getLong());
    }

    public static final UUID uuidFrom(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (string.length() == 36) {
            return new UUID(segmentToLong(string, 0, 19), segmentToLong(string, 19, 36));
        }
        throw new IllegalArgumentException("Invalid UUID string, expected exactly 36 characters but got " + string.length() + ": " + string);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0085  */
    /* JADX WARN: Code duplicated, block: B:27:0x0088  */
    /* JADX WARN: Code duplicated, block: B:28:0x008b  */
    /* JADX WARN: Code duplicated, block: B:29:0x008e  */
    /* JADX WARN: Code duplicated, block: B:30:0x0091  */
    /* JADX WARN: Code duplicated, block: B:31:0x0094  */
    private static final long segmentToLong(String str, int i, int i2) {
        long j;
        long j2 = 0;
        do {
            if (str.charAt(i) == '-') {
                if (!(i == 8 || i == 13 || i == 18 || i == 23)) {
                    throw new IllegalArgumentException(("Invalid UUID string, encountered dash at index " + i + " but it can occur only at 8, 13, 18, or 23: " + str).toString());
                }
            } else {
                j2 *= (long) 16;
                char cCharAt = str.charAt(i);
                switch (cCharAt) {
                    case '0':
                        break;
                    case '1':
                        j = 1;
                        break;
                    case '2':
                        j = 2;
                        break;
                    case '3':
                        j = 3;
                        break;
                    case '4':
                        j = 4;
                        break;
                    case '5':
                        j = 5;
                        break;
                    case '6':
                        j = 6;
                        break;
                    case '7':
                        j = 7;
                        break;
                    case '8':
                        j = 8;
                        break;
                    case '9':
                        j = 9;
                        break;
                    default:
                        switch (cCharAt) {
                            case 'A':
                                j = 10;
                                break;
                            case 'B':
                                j = 11;
                                break;
                            case 'C':
                                j = 12;
                                break;
                            case 'D':
                                j = 13;
                                break;
                            case 'E':
                                j = 14;
                                break;
                            case 'F':
                                j = 15;
                                break;
                            default:
                                switch (cCharAt) {
                                    case 'a':
                                        j = 10;
                                        break;
                                    case 'b':
                                        j = 11;
                                        break;
                                    case 'c':
                                        j = 12;
                                        break;
                                    case 'd':
                                        j = 13;
                                        break;
                                    case 'e':
                                        j = 14;
                                        break;
                                    case 'f':
                                        j = 15;
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Invalid UUID string, encountered non-hexadecimal digit `" + str.charAt(i) + "` at index " + i + " in: " + str);
                                }
                                break;
                        }
                        break;
                }
                j2 += j;
            }
            i++;
        } while (i < i2);
        return j2;
    }

    public static final UUID uuid4() {
        UUID uuidRandomUUID = UUID.randomUUID();
        Intrinsics.checkExpressionValueIsNotNull(uuidRandomUUID, "UUID.randomUUID()");
        return uuidRandomUUID;
    }

    public static final byte[] getBytes(UUID bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "$this$bytes");
        byte[] bArrArray = ByteBuffer.allocate(16).putLong(bytes.getMostSignificantBits()).putLong(bytes.getLeastSignificantBits()).array();
        Intrinsics.checkExpressionValueIsNotNull(bArrArray, "ByteBuffer.allocate(16).…tSignificantBits).array()");
        return bArrArray;
    }

    public static final int getVersion(UUID version) {
        Intrinsics.checkParameterIsNotNull(version, "$this$version");
        return version.version();
    }

    public static final int getVariant(UUID variant) {
        Intrinsics.checkParameterIsNotNull(variant, "$this$variant");
        return variant.variant();
    }
}
