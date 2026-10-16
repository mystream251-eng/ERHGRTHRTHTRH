package com.hippo.quickjs.android;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes2.dex */
class JNIHelper {
    private static Type VOID_PRIMITIVE_TYPE = Void.TYPE;
    private static Type CHAR_PRIMITIVE_TYPE = Character.TYPE;
    private static Type BOOLEAN_PRIMITIVE_TYPE = Boolean.TYPE;
    private static Type BYTE_PRIMITIVE_TYPE = Byte.TYPE;
    private static Type SHORT_PRIMITIVE_TYPE = Short.TYPE;
    private static Type INT_PRIMITIVE_TYPE = Integer.TYPE;
    private static Type LONG_PRIMITIVE_TYPE = Long.TYPE;
    private static Type FLOAT_PRIMITIVE_TYPE = Float.TYPE;
    private static Type DOUBLE_PRIMITIVE_TYPE = Double.TYPE;

    JNIHelper() {
    }

    private static Object jsValueToJavaValue(JSContext jSContext, Type type, long j) {
        Object objFromJSValue;
        synchronized (jSContext.jsRuntime) {
            JSValue jSValueWrapAsJSValue = null;
            try {
                jSContext.checkClosed();
                TypeAdapter adapter = jSContext.quickJS.getAdapter(type);
                jSValueWrapAsJSValue = jSContext.wrapAsJSValue(j);
                objFromJSValue = adapter.fromJSValue(jSContext.quickJS, jSContext, jSValueWrapAsJSValue);
                if (jSValueWrapAsJSValue == null) {
                    QuickJS.destroyValue(jSContext.pointer, j);
                }
            } catch (Throwable th) {
                if (jSValueWrapAsJSValue == null) {
                    QuickJS.destroyValue(jSContext.pointer, j);
                }
                throw th;
            }
        }
        return objFromJSValue;
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, boolean z) {
        return javaValueToJSValue(jSContext, type, Boolean.valueOf(z));
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, char c) {
        return javaValueToJSValue(jSContext, type, Character.valueOf(c));
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, byte b) {
        return javaValueToJSValue(jSContext, type, Byte.valueOf(b));
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, short s) {
        return javaValueToJSValue(jSContext, type, Short.valueOf(s));
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, int i) {
        return javaValueToJSValue(jSContext, type, Integer.valueOf(i));
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, long j) {
        return javaValueToJSValue(jSContext, type, Long.valueOf(j));
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, float f) {
        return javaValueToJSValue(jSContext, type, Float.valueOf(f));
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, double d) {
        return javaValueToJSValue(jSContext, type, Double.valueOf(d));
    }

    private static long javaValueToJSValue(JSContext jSContext, Type type, Object obj) {
        long j;
        synchronized (jSContext.jsRuntime) {
            jSContext.checkClosed();
            j = jSContext.quickJS.getAdapter(type).toJSValue(jSContext.quickJS, jSContext, obj).pointer;
        }
        return j;
    }

    private static boolean isPrimitiveType(Type type) {
        return (type instanceof Class) && ((Class) type).isPrimitive();
    }

    private static boolean isSameType(Type type, Type type2) {
        return type == type2 || (type != null && type.equals(type2));
    }

    private static boolean unbox(Boolean bool) {
        return bool.booleanValue();
    }

    private static char unbox(Character ch) {
        return ch.charValue();
    }

    private static byte unbox(Byte b) {
        return b.byteValue();
    }

    private static short unbox(Short sh) {
        return sh.shortValue();
    }

    private static int unbox(Integer num) {
        return num.intValue();
    }

    private static long unbox(Long l) {
        return l.longValue();
    }

    private static float unbox(Float f) {
        return f.floatValue();
    }

    private static double unbox(Double d) {
        return d.doubleValue();
    }
}
