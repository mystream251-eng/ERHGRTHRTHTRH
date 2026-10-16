package com.hippo.quickjs.android;

import java.io.Closeable;

/* JADX INFO: loaded from: classes2.dex */
public class JSContext implements Closeable, TypeAdapter.Context {
    private static final int EVAL_FLAG_MASK = 24;
    public static final int EVAL_FLAG_STRICT = 8;
    public static final int EVAL_FLAG_STRIP = 16;
    public static final int EVAL_TYPE_GLOBAL = 0;
    public static final int EVAL_TYPE_MODULE = 1;
    static final int TYPE_BOOLEAN = 1;
    static final int TYPE_EXCEPTION = 6;
    static final int TYPE_FLOAT64 = 7;
    static final int TYPE_INT = 0;
    static final int TYPE_NULL = 2;
    static final int TYPE_OBJECT = -1;
    static final int TYPE_STRING = -7;
    static final int TYPE_SYMBOL = -8;
    static final int TYPE_UNDEFINED = 3;
    private final NativeCleaner<JSValue> cleaner = new JSValueCleaner();
    final JSRuntime jsRuntime;
    long pointer;
    final QuickJS quickJS;

    protected JSContext(long j, QuickJS quickJS, JSRuntime jSRuntime) {
        this.pointer = j;
        this.quickJS = quickJS;
        this.jsRuntime = jSRuntime;
    }

    long checkClosed() {
        if (this.pointer == 0) {
            throw new IllegalStateException("The JSContext is closed");
        }
        this.cleaner.clean();
        return this.pointer;
    }

    public void evaluate(String str, String str2) {
        evaluateInternal(str, str2, 0, 0, null);
    }

    public void evaluate(String str, String str2, int i, int i2) {
        evaluateInternal(str, str2, i, i2, null);
    }

    public <T> T evaluate(String str, String str2, Class<T> cls) {
        return (T) evaluateInternal(str, str2, 0, 0, this.quickJS.getAdapter(cls));
    }

    public <T> T evaluate(String str, String str2, TypeAdapter<T> typeAdapter) {
        return (T) evaluateInternal(str, str2, 0, 0, typeAdapter);
    }

    public <T> T evaluate(String str, String str2, int i, int i2, Class<T> cls) {
        return (T) evaluateInternal(str, str2, i, i2, this.quickJS.getAdapter(cls));
    }

    public <T> T evaluate(String str, String str2, int i, int i2, TypeAdapter<T> typeAdapter) {
        return (T) evaluateInternal(str, str2, i, i2, typeAdapter);
    }

    private <T> T evaluateInternal(String str, String str2, int i, int i2, TypeAdapter<T> typeAdapter) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid type: " + i);
        }
        if ((i2 & (-25)) != 0) {
            throw new IllegalArgumentException("Invalid flags: " + i2);
        }
        synchronized (this.jsRuntime) {
            checkClosed();
            long jEvaluate = QuickJS.evaluate(this.pointer, str, str2, i | i2);
            if (typeAdapter != null) {
                return typeAdapter.fromJSValue(this.quickJS, this, wrapAsJSValue(jEvaluate));
            }
            try {
                if (QuickJS.getValueTag(jEvaluate) == 6) {
                    throw new JSEvaluationException(QuickJS.getException(this.pointer));
                }
                QuickJS.destroyValue(this.pointer, jEvaluate);
                return null;
            } catch (Throwable th) {
                QuickJS.destroyValue(this.pointer, jEvaluate);
                throw th;
            }
        }
    }

    public boolean executePendingJob() {
        boolean z;
        synchronized (this.jsRuntime) {
            checkClosed();
            int iExecutePendingJob = QuickJS.executePendingJob(this.pointer);
            if (iExecutePendingJob < 0) {
                throw new JSEvaluationException(QuickJS.getException(this.pointer));
            }
            z = iExecutePendingJob != 0;
        }
        return z;
    }

    public JSObject getGlobalObject() {
        JSObject jSObject;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSObject = (JSObject) wrapAsJSValue(QuickJS.getGlobalObject(this.pointer)).cast(JSObject.class);
        }
        return jSObject;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSUndefined createJSUndefined() {
        JSUndefined jSUndefined;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSUndefined = (JSUndefined) wrapAsJSValue(QuickJS.createValueUndefined(this.pointer)).cast(JSUndefined.class);
        }
        return jSUndefined;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSNull createJSNull() {
        JSNull jSNull;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSNull = (JSNull) wrapAsJSValue(QuickJS.createValueNull(this.pointer)).cast(JSNull.class);
        }
        return jSNull;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSBoolean createJSBoolean(boolean z) {
        JSBoolean jSBoolean;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSBoolean = (JSBoolean) wrapAsJSValue(QuickJS.createValueBoolean(this.pointer, z)).cast(JSBoolean.class);
        }
        return jSBoolean;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSNumber createJSNumber(int i) {
        JSNumber jSNumber;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSNumber = (JSNumber) wrapAsJSValue(QuickJS.createValueInt(this.pointer, i)).cast(JSNumber.class);
        }
        return jSNumber;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSNumber createJSNumber(double d) {
        JSNumber jSNumber;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSNumber = (JSNumber) wrapAsJSValue(QuickJS.createValueFloat64(this.pointer, d)).cast(JSNumber.class);
        }
        return jSNumber;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSString createJSString(String str) {
        JSString jSString;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSString = (JSString) wrapAsJSValue(QuickJS.createValueString(this.pointer, str)).cast(JSString.class);
        }
        return jSString;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSObject createJSObject() {
        JSObject jSObject;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSObject = (JSObject) wrapAsJSValue(QuickJS.createValueObject(this.pointer)).cast(JSObject.class);
        }
        return jSObject;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSObject createJSObject(Object obj) {
        JSObject jSObject;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSObject = (JSObject) wrapAsJSValue(QuickJS.createValueJavaObject(this.pointer, obj)).cast(JSObject.class);
        }
        return jSObject;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSArray createJSArray() {
        JSArray jSArray;
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArray = (JSArray) wrapAsJSValue(QuickJS.createValueArray(this.pointer)).cast(JSArray.class);
        }
        return jSArray;
    }

    private void checkArrayBounds(int i, int i2, int i3) {
        if (i2 < 0 || i3 <= 0 || i2 + i3 > i) {
            throw new IndexOutOfBoundsException("start = " + i2 + ", length = " + i3 + ", but array.length = " + i);
        }
    }

    public JSArrayBuffer createJSArrayBuffer(boolean[] zArr) {
        return createJSArrayBuffer(zArr, 0, zArr.length);
    }

    public JSArrayBuffer createJSArrayBuffer(boolean[] zArr, int i, int i2) {
        JSArrayBuffer jSArrayBuffer;
        checkArrayBounds(zArr.length, i, i2);
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArrayBuffer = (JSArrayBuffer) wrapAsJSValue(QuickJS.createValueArrayBufferZ(this.pointer, zArr, i, i2)).cast(JSArrayBuffer.class);
        }
        return jSArrayBuffer;
    }

    public JSArrayBuffer createJSArrayBuffer(byte[] bArr) {
        return createJSArrayBuffer(bArr, 0, bArr.length);
    }

    public JSArrayBuffer createJSArrayBuffer(byte[] bArr, int i, int i2) {
        JSArrayBuffer jSArrayBuffer;
        checkArrayBounds(bArr.length, i, i2);
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArrayBuffer = (JSArrayBuffer) wrapAsJSValue(QuickJS.createValueArrayBufferB(this.pointer, bArr, i, i2)).cast(JSArrayBuffer.class);
        }
        return jSArrayBuffer;
    }

    public JSArrayBuffer createJSArrayBuffer(char[] cArr) {
        return createJSArrayBuffer(cArr, 0, cArr.length);
    }

    public JSArrayBuffer createJSArrayBuffer(char[] cArr, int i, int i2) {
        JSArrayBuffer jSArrayBuffer;
        checkArrayBounds(cArr.length, i, i2);
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArrayBuffer = (JSArrayBuffer) wrapAsJSValue(QuickJS.createValueArrayBufferC(this.pointer, cArr, i, i2)).cast(JSArrayBuffer.class);
        }
        return jSArrayBuffer;
    }

    public JSArrayBuffer createJSArrayBuffer(short[] sArr) {
        return createJSArrayBuffer(sArr, 0, sArr.length);
    }

    public JSArrayBuffer createJSArrayBuffer(short[] sArr, int i, int i2) {
        JSArrayBuffer jSArrayBuffer;
        checkArrayBounds(sArr.length, i, i2);
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArrayBuffer = (JSArrayBuffer) wrapAsJSValue(QuickJS.createValueArrayBufferS(this.pointer, sArr, i, i2)).cast(JSArrayBuffer.class);
        }
        return jSArrayBuffer;
    }

    public JSArrayBuffer createJSArrayBuffer(int[] iArr) {
        return createJSArrayBuffer(iArr, 0, iArr.length);
    }

    public JSArrayBuffer createJSArrayBuffer(int[] iArr, int i, int i2) {
        JSArrayBuffer jSArrayBuffer;
        checkArrayBounds(iArr.length, i, i2);
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArrayBuffer = (JSArrayBuffer) wrapAsJSValue(QuickJS.createValueArrayBufferI(this.pointer, iArr, i, i2)).cast(JSArrayBuffer.class);
        }
        return jSArrayBuffer;
    }

    public JSArrayBuffer createJSArrayBuffer(long[] jArr) {
        return createJSArrayBuffer(jArr, 0, jArr.length);
    }

    public JSArrayBuffer createJSArrayBuffer(long[] jArr, int i, int i2) {
        JSArrayBuffer jSArrayBuffer;
        checkArrayBounds(jArr.length, i, i2);
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArrayBuffer = (JSArrayBuffer) wrapAsJSValue(QuickJS.createValueArrayBufferJ(this.pointer, jArr, i, i2)).cast(JSArrayBuffer.class);
        }
        return jSArrayBuffer;
    }

    public JSArrayBuffer createJSArrayBuffer(float[] fArr) {
        return createJSArrayBuffer(fArr, 0, fArr.length);
    }

    public JSArrayBuffer createJSArrayBuffer(float[] fArr, int i, int i2) {
        JSArrayBuffer jSArrayBuffer;
        checkArrayBounds(fArr.length, i, i2);
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArrayBuffer = (JSArrayBuffer) wrapAsJSValue(QuickJS.createValueArrayBufferF(this.pointer, fArr, i, i2)).cast(JSArrayBuffer.class);
        }
        return jSArrayBuffer;
    }

    public JSArrayBuffer createJSArrayBuffer(double[] dArr) {
        return createJSArrayBuffer(dArr, 0, dArr.length);
    }

    public JSArrayBuffer createJSArrayBuffer(double[] dArr, int i, int i2) {
        JSArrayBuffer jSArrayBuffer;
        checkArrayBounds(dArr.length, i, i2);
        synchronized (this.jsRuntime) {
            checkClosed();
            jSArrayBuffer = (JSArrayBuffer) wrapAsJSValue(QuickJS.createValueArrayBufferD(this.pointer, dArr, i, i2)).cast(JSArrayBuffer.class);
        }
        return jSArrayBuffer;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSFunction createJSFunction(Object obj, Method method) {
        JSFunction jSFunction;
        if (obj == null) {
            throw new NullPointerException("instance == null");
        }
        if (method == null) {
            throw new NullPointerException("method == null");
        }
        synchronized (this.jsRuntime) {
            checkClosed();
            jSFunction = (JSFunction) wrapAsJSValue(QuickJS.createValueFunction(this.pointer, this, obj, method.name, method.getSignature(), method.returnType, method.parameterTypes, false)).cast(JSFunction.class);
        }
        return jSFunction;
    }

    public JSFunction createJSFunction(JSFunctionCallback jSFunctionCallback) {
        JSFunction jSFunction;
        if (jSFunctionCallback == null) {
            throw new NullPointerException("callback == null");
        }
        synchronized (this.jsRuntime) {
            checkClosed();
            jSFunction = (JSFunction) wrapAsJSValue(QuickJS.createValueFunction(this.pointer, this, jSFunctionCallback, "invoke", "(Lcom/hippo/quickjs/android/JSContext;[Lcom/hippo/quickjs/android/JSValue;)Lcom/hippo/quickjs/android/JSValue;", JSValue.class, new Class[]{JSContext.class, JSValue[].class}, true)).cast(JSFunction.class);
        }
        return jSFunction;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Context
    public JSFunction createJSFunctionS(Class cls, Method method) {
        JSFunction jSFunction;
        if (cls == null) {
            throw new NullPointerException("clazz == null");
        }
        if (method == null) {
            throw new NullPointerException("method == null");
        }
        String name = cls.getName();
        StringBuilder sb = new StringBuilder(name.length());
        for (int i = 0; i < name.length(); i++) {
            char cCharAt = name.charAt(i);
            if (cCharAt == '.') {
                cCharAt = '/';
            }
            sb.append(cCharAt);
        }
        String string = sb.toString();
        synchronized (this.jsRuntime) {
            checkClosed();
            jSFunction = (JSFunction) wrapAsJSValue(QuickJS.createValueFunctionS(this.pointer, this, string, method.name, method.getSignature(), method.returnType, method.parameterTypes)).cast(JSFunction.class);
        }
        return jSFunction;
    }

    public JSObject createJSPromise(PromiseExecutor promiseExecutor) {
        JSValue jSValueWrapAsJSValue;
        JSValue jSValueWrapAsJSValue2;
        JSValue jSValueWrapAsJSValue3;
        synchronized (this.jsRuntime) {
            checkClosed();
            long[] jArrCreateValuePromise = QuickJS.createValuePromise(this.pointer);
            if (jArrCreateValuePromise == null) {
                throw new NullPointerException("result == null");
            }
            for (long j : jArrCreateValuePromise) {
                if (QuickJS.getValueTag(j) == 6) {
                    for (long j2 : jArrCreateValuePromise) {
                        QuickJS.destroyValue(this.pointer, j2);
                    }
                    throw new JSEvaluationException(QuickJS.getException(this.pointer));
                }
            }
            jSValueWrapAsJSValue = wrapAsJSValue(jArrCreateValuePromise[0]);
            jSValueWrapAsJSValue2 = wrapAsJSValue(jArrCreateValuePromise[1]);
            jSValueWrapAsJSValue3 = wrapAsJSValue(jArrCreateValuePromise[2]);
        }
        promiseExecutor.execute((JSFunction) jSValueWrapAsJSValue2.cast(JSFunction.class), (JSFunction) jSValueWrapAsJSValue3.cast(JSFunction.class));
        return (JSObject) jSValueWrapAsJSValue.cast(JSObject.class);
    }

    JSValue wrapAsJSValue(long j) {
        JSValue jSSymbol;
        if (j == 0) {
            throw new IllegalStateException("Can't wrap null pointer as JSValue");
        }
        int valueTag = QuickJS.getValueTag(j);
        if (valueTag == TYPE_SYMBOL) {
            jSSymbol = new JSSymbol(j, this);
        } else if (valueTag == -7) {
            jSSymbol = new JSString(j, this, QuickJS.getValueString(this.pointer, j));
        } else if (valueTag != -1) {
            if (valueTag == 0) {
                jSSymbol = new JSInt(j, this, QuickJS.getValueInt(j));
            } else if (valueTag == 1) {
                jSSymbol = new JSBoolean(j, this, QuickJS.getValueBoolean(j));
            } else if (valueTag == 2) {
                jSSymbol = new JSNull(j, this);
            } else if (valueTag == 3) {
                jSSymbol = new JSUndefined(j, this);
            } else {
                if (valueTag == 6) {
                    QuickJS.destroyValue(this.pointer, j);
                    throw new JSEvaluationException(QuickJS.getException(this.pointer));
                }
                if (valueTag == 7) {
                    jSSymbol = new JSFloat64(j, this, QuickJS.getValueFloat64(j));
                } else {
                    jSSymbol = new JSInternal(j, this);
                }
            }
        } else if (QuickJS.isValueFunction(this.pointer, j)) {
            jSSymbol = new JSFunction(j, this);
        } else if (QuickJS.isValueArray(this.pointer, j)) {
            jSSymbol = new JSArray(j, this);
        } else if (QuickJS.isValueArrayBuffer(this.pointer, j)) {
            jSSymbol = new JSArrayBuffer(j, this);
        } else {
            jSSymbol = new JSObject(j, this, QuickJS.getValueJavaObject(this.pointer, j));
        }
        this.cleaner.register(jSSymbol, j);
        return jSSymbol;
    }

    int getNotRemovedJSValueCount() {
        int size;
        synchronized (this.jsRuntime) {
            size = this.cleaner.size();
        }
        return size;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.jsRuntime) {
            if (this.pointer != 0) {
                this.cleaner.forceClean();
                long j = this.pointer;
                this.pointer = 0L;
                QuickJS.destroyContext(j);
            }
        }
    }

    private class JSValueCleaner extends NativeCleaner<JSValue> {
        private JSValueCleaner() {
        }

        @Override // com.hippo.quickjs.android.NativeCleaner
        public void onRemove(long j) {
            QuickJS.destroyValue(JSContext.this.pointer, j);
        }
    }
}
