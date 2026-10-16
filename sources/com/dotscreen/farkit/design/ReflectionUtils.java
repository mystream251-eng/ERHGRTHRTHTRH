package com.dotscreen.farkit.design;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReflectionUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\nJ#\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\r\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u000eJ\"\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0080\u0002¢\u0006\u0002\b\u0012J;\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00012\b\u0010\u0016\u001a\u0004\u0018\u00010\f2\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0018\"\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ*\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u0080\u0002¢\u0006\u0002\b\u001dR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/dotscreen/farkit/design/ReflectionUtils;", "", "()V", "TAG", "", "getField", "Ljava/lang/reflect/Field;", "clazz", "Ljava/lang/Class;", "fieldName", "getField$farkit_release", "getMethod", "Ljava/lang/reflect/Method;", "methodName", "getMethod$farkit_release", "getValue", "field", "obj", "getValue$farkit_release", "invokeMethod", "", "object", "method", "args", "", "invokeMethod$farkit_release", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)V", "setValue", "value", "setValue$farkit_release", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class ReflectionUtils {
    public static final ReflectionUtils INSTANCE = new ReflectionUtils();
    private static final String TAG = ReflectionUtils.class.getSimpleName();

    private ReflectionUtils() {
    }

    public final Field getField$farkit_release(Class<?> clazz, String fieldName) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        try {
            Field declaredField = clazz.getDeclaredField(fieldName);
            Intrinsics.checkExpressionValueIsNotNull(declaredField, "clazz.getDeclaredField(fieldName)");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    public final Object getValue$farkit_release(Field field, Object obj) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        try {
            return field.get(obj);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    public final void setValue$farkit_release(Field field, Object obj, Object value) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        try {
            field.set(obj, value);
        } catch (IllegalAccessException unused) {
        }
    }

    public final Method getMethod$farkit_release(Class<?> clazz, String methodName) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Method[] methods = clazz.getMethods();
        Intrinsics.checkExpressionValueIsNotNull(methods, "clazz.methods");
        for (Method method : methods) {
            if (Intrinsics.areEqual(method.getName(), methodName)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    public final void invokeMethod$farkit_release(Object object, Method method, Object... args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        if (method == null) {
            return;
        }
        try {
            method.invoke(object, Arrays.copyOf(args, args.length));
        } catch (IllegalAccessException e) {
            Log.d(TAG, "Can't invoke method using reflection", e);
        } catch (InvocationTargetException e2) {
            Log.d(TAG, "Can't invoke method using reflection", e2);
        }
    }
}
