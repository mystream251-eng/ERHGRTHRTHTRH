package com.hippo.quickjs.android;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes2.dex */
class StandardTypeAdapters {
    static final TypeAdapter.Factory FACTORY = new TypeAdapter.Factory() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.1
        @Override // com.hippo.quickjs.android.TypeAdapter.Factory
        public TypeAdapter<?> create(TypeAdapter.Depot depot, Type type) {
            if (type == Void.TYPE) {
                return StandardTypeAdapters.VOID_TYPE_ADAPTER;
            }
            if (type == Boolean.TYPE) {
                return StandardTypeAdapters.BOOLEAN_TYPE_ADAPTER;
            }
            if (type == Byte.TYPE) {
                return StandardTypeAdapters.BYTE_TYPE_ADAPTER;
            }
            if (type == Character.TYPE) {
                return StandardTypeAdapters.CHARACTER_TYPE_ADAPTER;
            }
            if (type == Short.TYPE) {
                return StandardTypeAdapters.SHORT_TYPE_ADAPTER;
            }
            if (type == Integer.TYPE) {
                return StandardTypeAdapters.INTEGER_TYPE_ADAPTER;
            }
            if (type == Long.TYPE) {
                return StandardTypeAdapters.LONG_TYPE_ADAPTER;
            }
            if (type == Float.TYPE) {
                return StandardTypeAdapters.FLOAT_TYPE_ADAPTER;
            }
            if (type == Double.TYPE) {
                return StandardTypeAdapters.DOUBLE_TYPE_ADAPTER;
            }
            if (type == Void.class) {
                return StandardTypeAdapters.VOID_TYPE_ADAPTER;
            }
            if (type == Boolean.class) {
                return StandardTypeAdapters.BOOLEAN_TYPE_ADAPTER.nullable();
            }
            if (type == Byte.class) {
                return StandardTypeAdapters.BYTE_TYPE_ADAPTER.nullable();
            }
            if (type == Character.class) {
                return StandardTypeAdapters.CHARACTER_TYPE_ADAPTER.nullable();
            }
            if (type == Short.class) {
                return StandardTypeAdapters.SHORT_TYPE_ADAPTER.nullable();
            }
            if (type == Integer.class) {
                return StandardTypeAdapters.INTEGER_TYPE_ADAPTER.nullable();
            }
            if (type == Long.class) {
                return StandardTypeAdapters.LONG_TYPE_ADAPTER.nullable();
            }
            if (type == Float.class) {
                return StandardTypeAdapters.FLOAT_TYPE_ADAPTER.nullable();
            }
            if (type == Double.class) {
                return StandardTypeAdapters.DOUBLE_TYPE_ADAPTER.nullable();
            }
            if (type == String.class) {
                return StandardTypeAdapters.STRING_TYPE_ADAPTER.nullable();
            }
            return null;
        }
    };
    private static final TypeAdapter<Void> VOID_TYPE_ADAPTER = new TypeAdapter<Void>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.2
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Void r3) {
            return context.createJSNull();
        }

        @Override // com.hippo.quickjs.android.TypeAdapter
        public Void fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            if ((jSValue instanceof JSNull) || (jSValue instanceof JSUndefined)) {
                return null;
            }
            throw new JSDataException("excepted: JSNull or JSUndefined, actual: " + jSValue.getClass().getSimpleName());
        }
    };
    private static final TypeAdapter<Boolean> BOOLEAN_TYPE_ADAPTER = new TypeAdapter<Boolean>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.3
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Boolean bool) {
            return context.createJSBoolean(bool.booleanValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hippo.quickjs.android.TypeAdapter
        public Boolean fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return Boolean.valueOf(((JSBoolean) jSValue.cast(JSBoolean.class)).getBoolean());
        }
    };
    private static final TypeAdapter<Byte> BYTE_TYPE_ADAPTER = new TypeAdapter<Byte>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.4
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Byte b) {
            return context.createJSNumber((int) b.byteValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hippo.quickjs.android.TypeAdapter
        public Byte fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return Byte.valueOf(((JSNumber) jSValue.cast(JSNumber.class)).getByte());
        }
    };
    private static final TypeAdapter<Character> CHARACTER_TYPE_ADAPTER = new TypeAdapter<Character>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.5
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Character ch) {
            return context.createJSString(ch.toString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hippo.quickjs.android.TypeAdapter
        public Character fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            String string = ((JSString) jSValue.cast(JSString.class)).getString();
            if (string.length() != 1) {
                throw new JSDataException("Can't treat \"" + string + "\" as char");
            }
            return Character.valueOf(string.charAt(0));
        }
    };
    private static final TypeAdapter<Short> SHORT_TYPE_ADAPTER = new TypeAdapter<Short>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.6
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Short sh) {
            return context.createJSNumber((int) sh.shortValue());
        }

        @Override // com.hippo.quickjs.android.TypeAdapter
        public Short fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return Short.valueOf(((JSNumber) jSValue.cast(JSNumber.class)).getShort());
        }
    };
    private static final TypeAdapter<Integer> INTEGER_TYPE_ADAPTER = new TypeAdapter<Integer>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.7
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Integer num) {
            return context.createJSNumber(num.intValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hippo.quickjs.android.TypeAdapter
        public Integer fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return Integer.valueOf(((JSNumber) jSValue.cast(JSNumber.class)).getInt());
        }
    };
    private static final TypeAdapter<Long> LONG_TYPE_ADAPTER = new TypeAdapter<Long>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.8
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Long l) {
            return context.createJSNumber(l.longValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hippo.quickjs.android.TypeAdapter
        public Long fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return Long.valueOf(((JSNumber) jSValue.cast(JSNumber.class)).getLong());
        }
    };
    private static final TypeAdapter<Float> FLOAT_TYPE_ADAPTER = new TypeAdapter<Float>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.9
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Float f) {
            return context.createJSNumber(f.floatValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hippo.quickjs.android.TypeAdapter
        public Float fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return Float.valueOf(((JSNumber) jSValue.cast(JSNumber.class)).getFloat());
        }
    };
    private static final TypeAdapter<Double> DOUBLE_TYPE_ADAPTER = new TypeAdapter<Double>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.10
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Double d) {
            return context.createJSNumber(d.doubleValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hippo.quickjs.android.TypeAdapter
        public Double fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return Double.valueOf(((JSNumber) jSValue.cast(JSNumber.class)).getDouble());
        }
    };
    private static final TypeAdapter<String> STRING_TYPE_ADAPTER = new TypeAdapter<String>() { // from class: com.hippo.quickjs.android.StandardTypeAdapters.11
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, String str) {
            return context.createJSString(str);
        }

        @Override // com.hippo.quickjs.android.TypeAdapter
        public String fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return ((JSString) jSValue.cast(JSString.class)).getString();
        }
    };

    StandardTypeAdapters() {
    }
}
