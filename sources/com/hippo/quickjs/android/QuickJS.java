package com.hippo.quickjs.android;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class QuickJS implements TypeAdapter.Depot {
    private static final List<TypeAdapter.Factory> BUILT_IN_FACTORIES;
    private final Map<Type, TypeAdapter<?>> adapterCache;
    private final List<TypeAdapter.Factory> factories;

    public static native long createContext(long j);

    protected static native long createRuntime();

    static native long createValueArray(long j);

    static native long createValueArrayBufferB(long j, byte[] bArr, int i, int i2);

    static native long createValueArrayBufferC(long j, char[] cArr, int i, int i2);

    static native long createValueArrayBufferD(long j, double[] dArr, int i, int i2);

    static native long createValueArrayBufferF(long j, float[] fArr, int i, int i2);

    static native long createValueArrayBufferI(long j, int[] iArr, int i, int i2);

    static native long createValueArrayBufferJ(long j, long[] jArr, int i, int i2);

    static native long createValueArrayBufferS(long j, short[] sArr, int i, int i2);

    static native long createValueArrayBufferZ(long j, boolean[] zArr, int i, int i2);

    static native long createValueBoolean(long j, boolean z);

    static native long createValueFloat64(long j, double d);

    static native long createValueFunction(long j, JSContext jSContext, Object obj, String str, String str2, Type type, Type[] typeArr, boolean z);

    static native long createValueFunctionS(long j, JSContext jSContext, String str, String str2, String str3, Type type, Type[] typeArr);

    static native long createValueInt(long j, int i);

    static native long createValueJavaObject(long j, Object obj);

    static native long createValueNull(long j);

    static native long createValueObject(long j);

    static native long[] createValuePromise(long j);

    static native long createValueString(long j, String str);

    static native long createValueUndefined(long j);

    static native boolean defineValueProperty(long j, long j2, int i, long j3, int i2);

    static native boolean defineValueProperty(long j, long j2, String str, long j3, int i);

    static native void destroyContext(long j);

    static native void destroyRuntime(long j);

    static native void destroyValue(long j, long j2);

    static native long evaluate(long j, String str, String str2, int i);

    static native int executePendingJob(long j);

    static native JSException getException(long j);

    static native long getGlobalObject(long j);

    static native boolean getValueBoolean(long j);

    static native double getValueFloat64(long j);

    static native int getValueInt(long j);

    static native Object getValueJavaObject(long j, long j2);

    static native long getValueProperty(long j, long j2, int i);

    static native long getValueProperty(long j, long j2, String str);

    static native String getValueString(long j, long j2);

    static native int getValueTag(long j);

    static native long invokeValueFunction(long j, long j2, long j3, long[] jArr);

    static native boolean isValueArray(long j, long j2);

    static native boolean isValueArrayBuffer(long j, long j2);

    static native boolean isValueFunction(long j, long j2);

    static native void setRuntimeInterruptHandler(long j, JSRuntime.InterruptHandler interruptHandler);

    static native void setRuntimeMallocLimit(long j, int i);

    static native boolean setValueProperty(long j, long j2, int i, long j3);

    static native boolean setValueProperty(long j, long j2, String str, long j3);

    static native boolean[] toBooleanArray(long j, long j2);

    static native byte[] toByteArray(long j, long j2);

    static native char[] toCharArray(long j, long j2);

    static native double[] toDoubleArray(long j, long j2);

    static native float[] toFloatArray(long j, long j2);

    static native int[] toIntArray(long j, long j2);

    static native long[] toLongArray(long j, long j2);

    static native short[] toShortArray(long j, long j2);

    static {
        ArrayList arrayList = new ArrayList(4);
        BUILT_IN_FACTORIES = arrayList;
        arrayList.add(StandardTypeAdapters.FACTORY);
        BUILT_IN_FACTORIES.add(JSValueAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(ArrayTypeAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(InterfaceTypeAdapter.FACTORY);
    }

    protected QuickJS(Builder builder) {
        this(builder, true);
    }

    protected QuickJS(Builder builder, boolean z) {
        if (z) {
            System.loadLibrary("quickjs-android");
        }
        ArrayList arrayList = new ArrayList(builder.factories.size() + BUILT_IN_FACTORIES.size());
        arrayList.addAll(builder.factories);
        arrayList.addAll(BUILT_IN_FACTORIES);
        this.factories = Collections.unmodifiableList(arrayList);
        this.adapterCache = new ConcurrentHashMap();
    }

    @Override // com.hippo.quickjs.android.TypeAdapter.Depot
    public <T> TypeAdapter<T> getAdapter(Type type) {
        Type typeRemoveSubtypeWildcard = Types.removeSubtypeWildcard(Types.canonicalize(type));
        TypeAdapter<T> typeAdapter = (TypeAdapter) this.adapterCache.get(typeRemoveSubtypeWildcard);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        int size = this.factories.size();
        for (int i = 0; i < size; i++) {
            StandardTypeAdapters.AnonymousClass6 anonymousClass6 = (TypeAdapter<T>) this.factories.get(i).create(this, typeRemoveSubtypeWildcard);
            if (anonymousClass6 != null) {
                this.adapterCache.put(typeRemoveSubtypeWildcard, anonymousClass6);
                return anonymousClass6;
            }
        }
        throw new IllegalArgumentException("Can't find TypeAdapter for " + type);
    }

    public JSRuntime createJSRuntime() {
        long jCreateRuntime = createRuntime();
        if (jCreateRuntime == 0) {
            throw new IllegalStateException("Cannot create JSRuntime instance");
        }
        return new JSRuntime(jCreateRuntime, this);
    }

    public static class Builder {
        private List<TypeAdapter.Factory> factories = new ArrayList();

        public <T> Builder registerTypeAdapter(final Type type, final TypeAdapter<T> typeAdapter) {
            return registerTypeAdapterFactory(new TypeAdapter.Factory() { // from class: com.hippo.quickjs.android.-$$Lambda$QuickJS$Builder$D5B9bgnAgy_BH0kn_oMH15ohUTg
                @Override // com.hippo.quickjs.android.TypeAdapter.Factory
                public final TypeAdapter create(TypeAdapter.Depot depot, Type type2) {
                    return QuickJS.Builder.lambda$registerTypeAdapter$0(type, typeAdapter, depot, type2);
                }
            });
        }

        static /* synthetic */ TypeAdapter lambda$registerTypeAdapter$0(Type type, TypeAdapter typeAdapter, TypeAdapter.Depot depot, Type type2) {
            if (Types.equals(type, type2)) {
                return typeAdapter;
            }
            return null;
        }

        public Builder registerTypeAdapterFactory(TypeAdapter.Factory factory) {
            this.factories.add(factory);
            return this;
        }

        public QuickJS build() {
            return new QuickJS(this);
        }
    }
}
