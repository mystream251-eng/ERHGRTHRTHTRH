package com.squareup.moshi;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MsgpackWriterOptions.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/squareup/moshi/MsgpackWriterOptions;", "", "writeAllIntsAs", "Lcom/squareup/moshi/MsgpackIntByte;", "writeAllFloatsAs", "Lcom/squareup/moshi/MsgpackFloatByte;", "writeAllIntsAsFloats", "", "(Lcom/squareup/moshi/MsgpackIntByte;Lcom/squareup/moshi/MsgpackFloatByte;Z)V", "getWriteAllFloatsAs", "()Lcom/squareup/moshi/MsgpackFloatByte;", "setWriteAllFloatsAs", "(Lcom/squareup/moshi/MsgpackFloatByte;)V", "getWriteAllIntsAs", "()Lcom/squareup/moshi/MsgpackIntByte;", "setWriteAllIntsAs", "(Lcom/squareup/moshi/MsgpackIntByte;)V", "getWriteAllIntsAsFloats", "()Z", "setWriteAllIntsAsFloats", "(Z)V", "moshipack"}, k = 1, mv = {1, 1, 13})
public final class MsgpackWriterOptions {
    private MsgpackFloatByte writeAllFloatsAs;
    private MsgpackIntByte writeAllIntsAs;
    private boolean writeAllIntsAsFloats;

    public MsgpackWriterOptions() {
        this(null, null, false, 7, null);
    }

    public MsgpackWriterOptions(MsgpackIntByte msgpackIntByte, MsgpackFloatByte msgpackFloatByte, boolean z) {
        this.writeAllIntsAs = msgpackIntByte;
        this.writeAllFloatsAs = msgpackFloatByte;
        this.writeAllIntsAsFloats = z;
    }

    public /* synthetic */ MsgpackWriterOptions(MsgpackIntByte msgpackIntByte, MsgpackFloatByte msgpackFloatByte, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (MsgpackIntByte) null : msgpackIntByte, (i & 2) != 0 ? (MsgpackFloatByte) null : msgpackFloatByte, (i & 4) != 0 ? false : z);
    }

    public final MsgpackIntByte getWriteAllIntsAs() {
        return this.writeAllIntsAs;
    }

    public final void setWriteAllIntsAs(MsgpackIntByte msgpackIntByte) {
        this.writeAllIntsAs = msgpackIntByte;
    }

    public final MsgpackFloatByte getWriteAllFloatsAs() {
        return this.writeAllFloatsAs;
    }

    public final void setWriteAllFloatsAs(MsgpackFloatByte msgpackFloatByte) {
        this.writeAllFloatsAs = msgpackFloatByte;
    }

    public final boolean getWriteAllIntsAsFloats() {
        return this.writeAllIntsAsFloats;
    }

    public final void setWriteAllIntsAsFloats(boolean z) {
        this.writeAllIntsAsFloats = z;
    }
}
