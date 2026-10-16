package com.hippo.quickjs.android;

/* JADX INFO: loaded from: classes2.dex */
public final class JSFloat64 extends JSNumber {
    private final double value;

    JSFloat64(long j, JSContext jSContext, double d) {
        super(j, jSContext);
        this.value = d;
    }

    private String wrongNumberMessage(String str, double d) {
        return "Can't treat " + d + " as " + str;
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public byte getByte() {
        double d = this.value;
        byte b = (byte) d;
        if (b == d) {
            return b;
        }
        throw new JSDataException(wrongNumberMessage("byte", d));
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public short getShort() {
        double d = this.value;
        short s = (short) d;
        if (s == d) {
            return s;
        }
        throw new JSDataException(wrongNumberMessage("short", d));
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public int getInt() {
        double d = this.value;
        int i = (int) d;
        if (i == d) {
            return i;
        }
        throw new JSDataException(wrongNumberMessage("int", d));
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public long getLong() {
        double d = this.value;
        long j = (long) d;
        if (j == d) {
            return j;
        }
        throw new JSDataException(wrongNumberMessage("long", d));
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public float getFloat() {
        return (float) this.value;
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public double getDouble() {
        return this.value;
    }
}
