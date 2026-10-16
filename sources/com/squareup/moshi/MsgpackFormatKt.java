package com.squareup.moshi;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MsgpackFormat.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\u001a \u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\u0002¢\u0006\u0002\u0010\u0006\u001a\u001f\u0010\u0007\u001a\u0004\u0018\u00010\u0003*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"contains", "", "", "Lcom/squareup/moshi/MsgpackFormatType;", "value", "", "([Lcom/squareup/moshi/MsgpackFormatType;B)Z", "typeFor", "([Lcom/squareup/moshi/MsgpackFormatType;B)Lcom/squareup/moshi/MsgpackFormatType;", "moshipack"}, k = 2, mv = {1, 1, 13})
public final class MsgpackFormatKt {
    public static final boolean contains(MsgpackFormatType[] receiver$0, byte b) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        for (MsgpackFormatType msgpackFormatType : receiver$0) {
            if (msgpackFormatType.contains(b)) {
                return true;
            }
        }
        return false;
    }

    public static final MsgpackFormatType typeFor(MsgpackFormatType[] receiver$0, byte b) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        for (MsgpackFormatType msgpackFormatType : receiver$0) {
            if (msgpackFormatType.contains(b)) {
                return msgpackFormatType;
            }
        }
        return null;
    }
}
