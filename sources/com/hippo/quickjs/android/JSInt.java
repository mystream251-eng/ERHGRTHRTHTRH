package com.hippo.quickjs.android;

/* JADX INFO: loaded from: classes2.dex */
public final class JSInt extends JSNumber {
    private final int value;

    JSInt(long j, JSContext jSContext, int i) {
        super(j, jSContext);
        this.value = i;
    }

    private int getIntInRange(String str, int i, int i2) {
        int i3 = this.value;
        if (i <= i3 && i3 <= i2) {
            return i3;
        }
        throw new JSDataException("Can't treat " + i3 + " as " + str);
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public byte getByte() {
        return (byte) getIntInRange("byte", -128, 127);
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public short getShort() {
        return (short) getIntInRange("short", -32768, 32767);
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public int getInt() {
        return this.value;
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public long getLong() {
        return this.value;
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public float getFloat() {
        return this.value;
    }

    @Override // com.hippo.quickjs.android.JSNumber
    public double getDouble() {
        return this.value;
    }
}
