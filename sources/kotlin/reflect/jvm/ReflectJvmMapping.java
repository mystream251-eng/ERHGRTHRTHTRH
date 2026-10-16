package kotlin.reflect.jvm;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KPackageImpl;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;

/* JADX INFO: compiled from: ReflectJvmMapping.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010%\u001a\u0004\u0018\u00010&*\u00020'H\u0002\"/\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u001b\u0010\b\u001a\u0004\u0018\u00010\t*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0015\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"-\u0010\u001d\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001e*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u001b\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010!\"\u001b\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n*\u00020\t8F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006("}, d2 = {"javaConstructor", "Ljava/lang/reflect/Constructor;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/reflect/KFunction;", "getJavaConstructor$annotations", "(Lkotlin/reflect/KFunction;)V", "getJavaConstructor", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Constructor;", "javaField", "Ljava/lang/reflect/Field;", "Lkotlin/reflect/KProperty;", "getJavaField", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Field;", "javaGetter", "Ljava/lang/reflect/Method;", "getJavaGetter", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Method;", "javaMethod", "getJavaMethod", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Method;", "javaSetter", "Lkotlin/reflect/KMutableProperty;", "getJavaSetter", "(Lkotlin/reflect/KMutableProperty;)Ljava/lang/reflect/Method;", "javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "kotlinFunction", "", "getKotlinFunction", "(Ljava/lang/reflect/Constructor;)Lkotlin/reflect/KFunction;", "(Ljava/lang/reflect/Method;)Lkotlin/reflect/KFunction;", "kotlinProperty", "getKotlinProperty", "(Ljava/lang/reflect/Field;)Lkotlin/reflect/KProperty;", "getKPackage", "Lkotlin/reflect/KDeclarationContainer;", "Ljava/lang/reflect/Member;", "kotlin-reflection"}, k = 2, mv = {1, 4, 0})
public final class ReflectJvmMapping {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KotlinClassHeader.Kind.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 1;
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.MULTIFILE_CLASS.ordinal()] = 2;
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 3;
        }
    }

    public static /* synthetic */ void getJavaConstructor$annotations(KFunction kFunction) {
    }

    public static final Field getJavaField(KProperty<?> javaField) {
        Intrinsics.checkNotNullParameter(javaField, "$this$javaField");
        KPropertyImpl<?> kPropertyImplAsKPropertyImpl = UtilKt.asKPropertyImpl(javaField);
        if (kPropertyImplAsKPropertyImpl != null) {
            return kPropertyImplAsKPropertyImpl.getJavaField();
        }
        return null;
    }

    public static final Method getJavaGetter(KProperty<?> javaGetter) {
        Intrinsics.checkNotNullParameter(javaGetter, "$this$javaGetter");
        return getJavaMethod(javaGetter.getGetter());
    }

    public static final Method getJavaSetter(KMutableProperty<?> javaSetter) {
        Intrinsics.checkNotNullParameter(javaSetter, "$this$javaSetter");
        return getJavaMethod(javaSetter.getSetter());
    }

    public static final Method getJavaMethod(KFunction<?> javaMethod) {
        Caller<?> caller;
        Intrinsics.checkNotNullParameter(javaMethod, "$this$javaMethod");
        KCallableImpl<?> kCallableImplAsKCallableImpl = UtilKt.asKCallableImpl(javaMethod);
        Member memberMo1260getMember = (kCallableImplAsKCallableImpl == null || (caller = kCallableImplAsKCallableImpl.getCaller()) == null) ? null : caller.mo1260getMember();
        return (Method) (memberMo1260getMember instanceof Method ? memberMo1260getMember : null);
    }

    public static final <T> Constructor<T> getJavaConstructor(KFunction<? extends T> javaConstructor) {
        Caller<?> caller;
        Intrinsics.checkNotNullParameter(javaConstructor, "$this$javaConstructor");
        KCallableImpl<?> kCallableImplAsKCallableImpl = UtilKt.asKCallableImpl(javaConstructor);
        Member memberMo1260getMember = (kCallableImplAsKCallableImpl == null || (caller = kCallableImplAsKCallableImpl.getCaller()) == null) ? null : caller.mo1260getMember();
        return (Constructor) (memberMo1260getMember instanceof Constructor ? memberMo1260getMember : null);
    }

    public static final Type getJavaType(KType javaType) {
        Intrinsics.checkNotNullParameter(javaType, "$this$javaType");
        Type javaType2 = ((KTypeImpl) javaType).getJavaType();
        return javaType2 != null ? javaType2 : TypesJVMKt.getJavaType(javaType);
    }

    public static final KProperty<?> getKotlinProperty(Field kotlinProperty) throws InvocationTargetException {
        Intrinsics.checkNotNullParameter(kotlinProperty, "$this$kotlinProperty");
        Object obj = null;
        if (kotlinProperty.isSynthetic()) {
            return null;
        }
        KDeclarationContainer kPackage = getKPackage(kotlinProperty);
        if (kPackage != null) {
            Collection<KCallable<?>> members = kPackage.getMembers();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : members) {
                if (obj2 instanceof KProperty) {
                    arrayList.add(obj2);
                }
            }
            for (Object obj3 : arrayList) {
                if (Intrinsics.areEqual(getJavaField((KProperty) obj3), kotlinProperty)) {
                    obj = obj3;
                    break;
                }
            }
            return (KProperty) obj;
        }
        for (Object obj4 : KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(kotlinProperty.getDeclaringClass()))) {
            if (Intrinsics.areEqual(getJavaField((KProperty1) obj4), kotlinProperty)) {
                obj = obj4;
                break;
            }
        }
        return (KProperty) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String, kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    private static final KDeclarationContainer getKPackage(Member member) throws InvocationTargetException {
        KotlinClassHeader classHeader;
        ReflectKotlinClass.Factory factory = ReflectKotlinClass.Factory;
        Class<?> declaringClass = member.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "declaringClass");
        ReflectKotlinClass reflectKotlinClassCreate = factory.create(declaringClass);
        ?? kPackageImpl = 0;
        kPackageImpl = 0;
        KotlinClassHeader.Kind kind = (reflectKotlinClassCreate == null || (classHeader = reflectKotlinClassCreate.getClassHeader()) == null) ? null : classHeader.getKind();
        if (kind != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
            int i2 = 2;
            if (i == 1 || i == 2 || i == 3) {
                Class<?> declaringClass2 = member.getDeclaringClass();
                Intrinsics.checkNotNullExpressionValue(declaringClass2, "declaringClass");
                kPackageImpl = new KPackageImpl(declaringClass2, kPackageImpl, i2, kPackageImpl);
            }
        }
        return (KDeclarationContainer) kPackageImpl;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00b9  */
    public static final KFunction<?> getKotlinFunction(Method kotlinFunction) throws InvocationTargetException {
        Object next;
        boolean z;
        Intrinsics.checkNotNullParameter(kotlinFunction, "$this$kotlinFunction");
        Object obj = null;
        if (Modifier.isStatic(kotlinFunction.getModifiers())) {
            KDeclarationContainer kPackage = getKPackage(kotlinFunction);
            if (kPackage != null) {
                Collection<KCallable<?>> members = kPackage.getMembers();
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : members) {
                    if (obj2 instanceof KFunction) {
                        arrayList.add(obj2);
                    }
                }
                for (Object obj3 : arrayList) {
                    if (Intrinsics.areEqual(getJavaMethod((KFunction) obj3), kotlinFunction)) {
                        obj = obj3;
                        break;
                    }
                }
                return (KFunction) obj;
            }
            KClass<?> companionObject = KClasses.getCompanionObject(JvmClassMappingKt.getKotlinClass(kotlinFunction.getDeclaringClass()));
            if (companionObject != null) {
                Iterator<T> it = KClasses.getFunctions(companionObject).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    Method javaMethod = getJavaMethod((KFunction) next);
                    if (javaMethod == null || !Intrinsics.areEqual(javaMethod.getName(), kotlinFunction.getName())) {
                        z = false;
                    } else {
                        Class<?>[] parameterTypes = javaMethod.getParameterTypes();
                        Intrinsics.checkNotNull(parameterTypes);
                        if (Arrays.equals(parameterTypes, kotlinFunction.getParameterTypes()) && Intrinsics.areEqual(javaMethod.getReturnType(), kotlinFunction.getReturnType())) {
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                } while (!z);
                KFunction<?> kFunction = (KFunction) next;
                if (kFunction != null) {
                    return kFunction;
                }
            }
        }
        for (Object obj4 : KClasses.getFunctions(JvmClassMappingKt.getKotlinClass(kotlinFunction.getDeclaringClass()))) {
            if (Intrinsics.areEqual(getJavaMethod((KFunction) obj4), kotlinFunction)) {
                obj = obj4;
                break;
            }
        }
        return (KFunction) obj;
    }

    public static final <T> KFunction<T> getKotlinFunction(Constructor<T> kotlinFunction) {
        T next;
        Intrinsics.checkNotNullParameter(kotlinFunction, "$this$kotlinFunction");
        Class<T> declaringClass = kotlinFunction.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "declaringClass");
        Iterator<T> it = JvmClassMappingKt.getKotlinClass(declaringClass).getConstructors().iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(getJavaConstructor((KFunction) next), kotlinFunction)) {
                return (KFunction) next;
            }
        }
        next = null;
        return (KFunction) next;
    }
}
