package kotlin.reflect.jvm.internal.calls;

import androidx.exifinterface.media.ExifInterface;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.text.Typography;

/* JADX INFO: compiled from: AnnotationConstructorCaller.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001aI\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\u0010\u000b\u001a$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u0014"}, d2 = {"createAnnotationInstance", ExifInterface.GPS_DIRECTION_TRUE, "", "annotationClass", "Ljava/lang/Class;", "values", "", "", "methods", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/Map;Ljava/util/List;)Ljava/lang/Object;", "throwIllegalArgumentType", "", "index", "", "name", "expectedJvmType", "transformKotlinToJvm", "expectedType", "kotlin-reflection"}, k = 2, mv = {1, 4, 0})
public final class AnnotationConstructorCallerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Object transformKotlinToJvm(Object obj, Class<?> cls) {
        if (obj instanceof Class) {
            return null;
        }
        if (obj instanceof KClass) {
            obj = JvmClassMappingKt.getJavaClass((KClass) obj);
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr instanceof Class[]) {
                return null;
            }
            if (!(objArr instanceof KClass[])) {
                obj = objArr;
            } else {
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
                }
                KClass[] kClassArr = (KClass[]) obj;
                ArrayList arrayList = new ArrayList(kClassArr.length);
                for (KClass kClass : kClassArr) {
                    arrayList.add(JvmClassMappingKt.getJavaClass(kClass));
                }
                obj = arrayList.toArray(new Class[0]);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
        }
        if (cls.isInstance(obj)) {
            return obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwIllegalArgumentType(int i, String str, Class<?> cls) {
        KClass kotlinClass;
        String qualifiedName;
        if (Intrinsics.areEqual(cls, Class.class)) {
            kotlinClass = Reflection.getOrCreateKotlinClass(KClass.class);
        } else if (cls.isArray() && Intrinsics.areEqual(cls.getComponentType(), Class.class)) {
            kotlinClass = Reflection.getOrCreateKotlinClass(KClass[].class);
        } else {
            kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
        }
        if (Intrinsics.areEqual(kotlinClass.getQualifiedName(), Reflection.getOrCreateKotlinClass(Object[].class).getQualifiedName())) {
            qualifiedName = kotlinClass.getQualifiedName() + Typography.less + JvmClassMappingKt.getKotlinClass(JvmClassMappingKt.getJavaClass(kotlinClass).getComponentType()).getQualifiedName() + Typography.greater;
        } else {
            qualifiedName = kotlinClass.getQualifiedName();
        }
        throw new IllegalArgumentException("Argument #" + i + ' ' + str + " is not of the required type " + qualifiedName);
    }

    public static /* synthetic */ Object createAnnotationInstance$default(Class cls, Map map, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            Set setKeySet = map.keySet();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
            Iterator it = setKeySet.iterator();
            while (it.hasNext()) {
                arrayList.add(cls.getDeclaredMethod((String) it.next(), new Class[0]));
            }
            list = arrayList;
        }
        return createAnnotationInstance(cls, map, list);
    }

    public static final <T> T createAnnotationInstance(final Class<T> annotationClass, final Map<String, ? extends Object> values, List<Method> methods) {
        Intrinsics.checkNotNullParameter(annotationClass, "annotationClass");
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(methods, "methods");
        final AnonymousClass2 anonymousClass2 = new AnonymousClass2(annotationClass, methods, values);
        final Lazy lazy = LazyKt.lazy(new Function0<Integer>() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$hashCode$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Integer invoke() {
                return Integer.valueOf(invoke2());
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2() {
                int iHashCode;
                int iHashCode2 = 0;
                for (Map.Entry entry : values.entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof boolean[]) {
                        iHashCode = Arrays.hashCode((boolean[]) value);
                    } else if (value instanceof char[]) {
                        iHashCode = Arrays.hashCode((char[]) value);
                    } else if (value instanceof byte[]) {
                        iHashCode = Arrays.hashCode((byte[]) value);
                    } else if (value instanceof short[]) {
                        iHashCode = Arrays.hashCode((short[]) value);
                    } else if (value instanceof int[]) {
                        iHashCode = Arrays.hashCode((int[]) value);
                    } else if (value instanceof float[]) {
                        iHashCode = Arrays.hashCode((float[]) value);
                    } else if (value instanceof long[]) {
                        iHashCode = Arrays.hashCode((long[]) value);
                    } else if (value instanceof double[]) {
                        iHashCode = Arrays.hashCode((double[]) value);
                    } else {
                        iHashCode = value instanceof Object[] ? Arrays.hashCode((Object[]) value) : value.hashCode();
                    }
                    iHashCode2 += iHashCode ^ (str.hashCode() * 127);
                }
                return iHashCode2;
            }
        });
        final Lazy lazy2 = LazyKt.lazy(new Function0<String>() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$toString$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                StringBuilder sb = new StringBuilder();
                sb.append('@');
                sb.append(annotationClass.getCanonicalName());
                CollectionsKt.joinTo(values.entrySet(), sb, (112 & 2) != 0 ? ", " : ", ", (112 & 4) != 0 ? "" : "(", (112 & 8) != 0 ? "" : ")", (112 & 16) != 0 ? -1 : 0, (112 & 32) != 0 ? "..." : null, (112 & 64) != 0 ? (Function1) null : new Function1<Map.Entry<? extends String, ? extends Object>, CharSequence>() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$toString$2$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ CharSequence invoke(Map.Entry<? extends String, ? extends Object> entry) {
                        return invoke2((Map.Entry<String, ? extends Object>) entry);
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final CharSequence invoke2(Map.Entry<String, ? extends Object> entry) {
                        String string;
                        Intrinsics.checkNotNullParameter(entry, "entry");
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value instanceof boolean[]) {
                            string = Arrays.toString((boolean[]) value);
                        } else if (value instanceof char[]) {
                            string = Arrays.toString((char[]) value);
                        } else if (value instanceof byte[]) {
                            string = Arrays.toString((byte[]) value);
                        } else if (value instanceof short[]) {
                            string = Arrays.toString((short[]) value);
                        } else if (value instanceof int[]) {
                            string = Arrays.toString((int[]) value);
                        } else if (value instanceof float[]) {
                            string = Arrays.toString((float[]) value);
                        } else if (value instanceof long[]) {
                            string = Arrays.toString((long[]) value);
                        } else if (value instanceof double[]) {
                            string = Arrays.toString((double[]) value);
                        } else {
                            string = value instanceof Object[] ? Arrays.toString((Object[]) value) : value.toString();
                        }
                        return key + '=' + string;
                    }
                });
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            }
        });
        final KProperty kProperty = null;
        final KProperty kProperty2 = null;
        T t = (T) Proxy.newProxyInstance(annotationClass.getClassLoader(), new Class[]{annotationClass}, new InvocationHandler() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$result$1
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                Intrinsics.checkNotNullExpressionValue(method, "method");
                String name = method.getName();
                if (name != null) {
                    int iHashCode = name.hashCode();
                    if (iHashCode != -1776922004) {
                        if (iHashCode != 147696667) {
                            if (iHashCode == 1444986633 && name.equals("annotationType")) {
                                return annotationClass;
                            }
                        } else if (name.equals("hashCode")) {
                            return lazy.getValue();
                        }
                    } else if (name.equals("toString")) {
                        return lazy2.getValue();
                    }
                }
                if (Intrinsics.areEqual(name, "equals") && objArr != null && objArr.length == 1) {
                    return Boolean.valueOf(anonymousClass2.invoke2(ArraysKt.single(objArr)));
                }
                if (values.containsKey(name)) {
                    return values.get(name);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Method is not supported: ");
                sb.append(method);
                sb.append(" (args: ");
                if (objArr == null) {
                    objArr = new Object[0];
                }
                sb.append(ArraysKt.toList(objArr));
                sb.append(')');
                throw new KotlinReflectionInternalError(sb.toString());
            }
        });
        if (t != null) {
            return t;
        }
        throw new NullPointerException("null cannot be cast to non-null type T");
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$2, reason: invalid class name */
    /* JADX INFO: compiled from: AnnotationConstructorCaller.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"equals", "", ExifInterface.GPS_DIRECTION_TRUE, "", "other", "invoke"}, k = 3, mv = {1, 4, 0})
    static final class AnonymousClass2 extends Lambda implements Function1<Object, Boolean> {
        final /* synthetic */ Class $annotationClass;
        final /* synthetic */ List $methods;
        final /* synthetic */ Map $values;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Class cls, List list, Map map) {
            super(1);
            this.$annotationClass = cls;
            this.$methods = list;
            this.$values = map;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
            return Boolean.valueOf(invoke2(obj));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(Object obj) throws IllegalAccessException, InvocationTargetException {
            boolean zAreEqual;
            boolean z;
            KClass annotationClass;
            Class javaClass = null;
            Annotation annotation = (Annotation) (!(obj instanceof Annotation) ? null : obj);
            if (annotation != null && (annotationClass = JvmClassMappingKt.getAnnotationClass(annotation)) != null) {
                javaClass = JvmClassMappingKt.getJavaClass(annotationClass);
            }
            if (Intrinsics.areEqual(javaClass, this.$annotationClass)) {
                List list = this.$methods;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        }
                        Method method = (Method) it.next();
                        Object obj2 = this.$values.get(method.getName());
                        Object objInvoke = method.invoke(obj, new Object[0]);
                        if (obj2 instanceof boolean[]) {
                            boolean[] zArr = (boolean[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.BooleanArray");
                            }
                            zAreEqual = Arrays.equals(zArr, (boolean[]) objInvoke);
                        } else if (obj2 instanceof char[]) {
                            char[] cArr = (char[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.CharArray");
                            }
                            zAreEqual = Arrays.equals(cArr, (char[]) objInvoke);
                        } else if (obj2 instanceof byte[]) {
                            byte[] bArr = (byte[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.ByteArray");
                            }
                            zAreEqual = Arrays.equals(bArr, (byte[]) objInvoke);
                        } else if (obj2 instanceof short[]) {
                            short[] sArr = (short[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.ShortArray");
                            }
                            zAreEqual = Arrays.equals(sArr, (short[]) objInvoke);
                        } else if (obj2 instanceof int[]) {
                            int[] iArr = (int[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.IntArray");
                            }
                            zAreEqual = Arrays.equals(iArr, (int[]) objInvoke);
                        } else if (obj2 instanceof float[]) {
                            float[] fArr = (float[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.FloatArray");
                            }
                            zAreEqual = Arrays.equals(fArr, (float[]) objInvoke);
                        } else if (obj2 instanceof long[]) {
                            long[] jArr = (long[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.LongArray");
                            }
                            zAreEqual = Arrays.equals(jArr, (long[]) objInvoke);
                        } else if (obj2 instanceof double[]) {
                            double[] dArr = (double[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.DoubleArray");
                            }
                            zAreEqual = Arrays.equals(dArr, (double[]) objInvoke);
                        } else if (obj2 instanceof Object[]) {
                            Object[] objArr = (Object[]) obj2;
                            if (objInvoke == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<*>");
                            }
                            zAreEqual = Arrays.equals(objArr, (Object[]) objInvoke);
                        } else {
                            zAreEqual = Intrinsics.areEqual(obj2, objInvoke);
                        }
                        if (!zAreEqual) {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = true;
                    break;
                }
                if (z) {
                    return true;
                }
            }
            return false;
        }
    }
}
