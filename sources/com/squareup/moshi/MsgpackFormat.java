package com.squareup.moshi;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MsgpackFormat.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u001a\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010=\u001a\u0004\u0018\u00010\u00052\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010?\u001a\u00020\u0013¢\u0006\u0002\u0010@R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u000e\u0010\u0012\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000bR\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000bR\u000e\u0010\u0019\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007R\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u000bR\u0011\u0010#\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u000bR\u000e\u0010%\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020'X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u000bR\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u000bR\u0011\u00100\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u000bR\u000e\u00102\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/squareup/moshi/MsgpackFormat;", "", "()V", "ARRAY", "", "Lcom/squareup/moshi/MsgpackFormatType;", "getARRAY", "()[Lcom/squareup/moshi/MsgpackFormatType;", "[Lcom/squareup/moshi/MsgpackFormatType;", "ARRAY_16", "getARRAY_16", "()Lcom/squareup/moshi/MsgpackFormatType;", "ARRAY_32", "getARRAY_32", "FALSE", "", "FIX_ARRAY", "getFIX_ARRAY", "FIX_INT_MAX", "", "FIX_INT_MIN", "FIX_MAP", "getFIX_MAP", "FIX_STR", "getFIX_STR", "FLOAT_32", "FLOAT_64", "INT_16", "INT_32", "INT_64", "INT_8", "MAP", "getMAP", "MAP_16", "getMAP_16", "MAP_32", "getMAP_32", "NIL", "SIZE_16", "", "SIZE_32", "SIZE_8", "STR", "getSTR", "STR_16", "getSTR_16", "STR_32", "getSTR_32", "STR_8", "getSTR_8", "TRUE", "UINT_16", "UINT_16_MAX", "UINT_16_MIN", "UINT_32", "UINT_32_MAX", "UINT_32_MIN", "UINT_64", "UINT_8", "UINT_8_MAX", "UINT_8_MIN", "tagFor", "type", "size", "([Lcom/squareup/moshi/MsgpackFormatType;I)Lcom/squareup/moshi/MsgpackFormatType;", "moshipack"}, k = 1, mv = {1, 1, 13})
public final class MsgpackFormat {
    private static final MsgpackFormatType[] ARRAY;
    private static final MsgpackFormatType ARRAY_16;
    private static final MsgpackFormatType ARRAY_32;
    public static final byte FALSE = -62;
    private static final MsgpackFormatType FIX_ARRAY;
    public static final int FIX_INT_MAX = 127;
    public static final int FIX_INT_MIN = -32;
    private static final MsgpackFormatType FIX_MAP;
    public static final byte FLOAT_32 = -54;
    public static final byte FLOAT_64 = -53;
    public static final byte INT_16 = -47;
    public static final byte INT_32 = -46;
    public static final byte INT_64 = -45;
    public static final byte INT_8 = -48;
    private static final MsgpackFormatType[] MAP;
    private static final MsgpackFormatType MAP_16;
    private static final MsgpackFormatType MAP_32;
    public static final byte NIL = -64;
    public static final long SIZE_32 = 4294967295L;
    public static final long SIZE_8 = 255;
    private static final MsgpackFormatType[] STR;
    private static final MsgpackFormatType STR_32;
    public static final byte TRUE = -61;
    public static final byte UINT_16 = -51;
    public static final int UINT_16_MAX = 32767;
    public static final int UINT_16_MIN = 256;
    public static final byte UINT_32 = -50;
    public static final int UINT_32_MAX = 536870911;
    public static final int UINT_32_MIN = 32768;
    public static final byte UINT_64 = -49;
    public static final byte UINT_8 = -52;
    public static final int UINT_8_MAX = 255;
    public static final int UINT_8_MIN = 128;
    public static final MsgpackFormat INSTANCE = new MsgpackFormat();
    private static final MsgpackFormatType FIX_STR = new MsgpackFormatType((byte) 160, 31, true);
    private static final MsgpackFormatType STR_8 = new MsgpackFormatType((byte) 217, 255, false, 4, null);
    public static final long SIZE_16 = 65535;
    private static final MsgpackFormatType STR_16 = new MsgpackFormatType((byte) 218, SIZE_16, false, 4, null);

    static {
        MsgpackFormatType msgpackFormatType = new MsgpackFormatType((byte) 219, SIZE_32, false, 4, null);
        STR_32 = msgpackFormatType;
        STR = new MsgpackFormatType[]{FIX_STR, STR_8, STR_16, msgpackFormatType};
        FIX_ARRAY = new MsgpackFormatType((byte) 144, 15L, true);
        ARRAY_16 = new MsgpackFormatType((byte) 220, SIZE_16, false, 4, null);
        MsgpackFormatType msgpackFormatType2 = new MsgpackFormatType((byte) 221, SIZE_32, false, 4, null);
        ARRAY_32 = msgpackFormatType2;
        ARRAY = new MsgpackFormatType[]{FIX_ARRAY, ARRAY_16, msgpackFormatType2};
        FIX_MAP = new MsgpackFormatType((byte) 128, 15L, true);
        MAP_16 = new MsgpackFormatType((byte) 222, SIZE_16, false, 4, null);
        MsgpackFormatType msgpackFormatType3 = new MsgpackFormatType((byte) 223, SIZE_32, false, 4, null);
        MAP_32 = msgpackFormatType3;
        MAP = new MsgpackFormatType[]{FIX_MAP, MAP_16, msgpackFormatType3};
    }

    private MsgpackFormat() {
    }

    public final MsgpackFormatType getFIX_STR() {
        return FIX_STR;
    }

    public final MsgpackFormatType getSTR_8() {
        return STR_8;
    }

    public final MsgpackFormatType getSTR_16() {
        return STR_16;
    }

    public final MsgpackFormatType getSTR_32() {
        return STR_32;
    }

    public final MsgpackFormatType[] getSTR() {
        return STR;
    }

    public final MsgpackFormatType getFIX_ARRAY() {
        return FIX_ARRAY;
    }

    public final MsgpackFormatType getARRAY_16() {
        return ARRAY_16;
    }

    public final MsgpackFormatType getARRAY_32() {
        return ARRAY_32;
    }

    public final MsgpackFormatType[] getARRAY() {
        return ARRAY;
    }

    public final MsgpackFormatType getFIX_MAP() {
        return FIX_MAP;
    }

    public final MsgpackFormatType getMAP_16() {
        return MAP_16;
    }

    public final MsgpackFormatType getMAP_32() {
        return MAP_32;
    }

    public final MsgpackFormatType[] getMAP() {
        return MAP;
    }

    public final MsgpackFormatType tagFor(MsgpackFormatType[] type, int size) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(type, "type");
        ArrayList arrayList = new ArrayList();
        for (MsgpackFormatType msgpackFormatType : type) {
            if (msgpackFormatType.getMaxSize() > ((long) size)) {
                arrayList.add(msgpackFormatType);
            }
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            long maxSize = ((MsgpackFormatType) next).getMaxSize();
            while (it.hasNext()) {
                Object next2 = it.next();
                long maxSize2 = ((MsgpackFormatType) next2).getMaxSize();
                if (maxSize > maxSize2) {
                    next = next2;
                    maxSize = maxSize2;
                }
            }
            obj = next;
        } else {
            obj = null;
        }
        return (MsgpackFormatType) obj;
    }
}
