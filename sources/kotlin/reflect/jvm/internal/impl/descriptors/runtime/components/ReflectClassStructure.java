package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* JADX INFO: compiled from: ReflectKotlinClass.kt */
/* JADX INFO: loaded from: classes3.dex */
final class ReflectClassStructure {
    public static final ReflectClassStructure INSTANCE = new ReflectClassStructure();

    private ReflectClassStructure() {
    }

    public final void loadClassAnnotations(Class<?> klass, KotlinJvmBinaryClass.AnnotationVisitor visitor) throws InvocationTargetException {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        for (Annotation annotation : klass.getDeclaredAnnotations()) {
            Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
            processAnnotation(visitor, annotation);
        }
        visitor.visitEnd();
    }

    public final void visitMembers(Class<?> klass, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws InvocationTargetException {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(memberVisitor, "memberVisitor");
        loadMethodAnnotations(klass, memberVisitor);
        loadConstructorAnnotations(klass, memberVisitor);
        loadFieldAnnotations(klass, memberVisitor);
    }

    private final void loadMethodAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws InvocationTargetException {
        Method[] methodArr;
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        while (i < length) {
            Method method = declaredMethods[i];
            Intrinsics.checkNotNullExpressionValue(method, "method");
            Name nameIdentifier = Name.identifier(method.getName());
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(method.name)");
            KotlinJvmBinaryClass.MethodAnnotationVisitor methodAnnotationVisitorVisitMethod = memberVisitor.visitMethod(nameIdentifier, SignatureSerializer.INSTANCE.methodDesc(method));
            if (methodAnnotationVisitorVisitMethod != null) {
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(methodAnnotationVisitorVisitMethod, annotation);
                }
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "method.parameterAnnotations");
                int length2 = parameterAnnotations.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    Annotation[] annotationArr = parameterAnnotations[i2];
                    int length3 = annotationArr.length;
                    int i3 = 0;
                    while (i3 < length3) {
                        Annotation annotation2 = annotationArr[i3];
                        Class<?> javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2));
                        ClassId classId = ReflectClassUtilKt.getClassId(javaClass);
                        Method[] methodArr2 = declaredMethods;
                        Intrinsics.checkNotNullExpressionValue(annotation2, "annotation");
                        KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitParameterAnnotation = methodAnnotationVisitorVisitMethod.visitParameterAnnotation(i2, classId, new ReflectAnnotationSource(annotation2));
                        if (annotationArgumentVisitorVisitParameterAnnotation != null) {
                            INSTANCE.processAnnotationArguments(annotationArgumentVisitorVisitParameterAnnotation, annotation2, javaClass);
                        }
                        i3++;
                        declaredMethods = methodArr2;
                    }
                }
                methodArr = declaredMethods;
                methodAnnotationVisitorVisitMethod.visitEnd();
            } else {
                methodArr = declaredMethods;
            }
            i++;
            declaredMethods = methodArr;
        }
    }

    private final void loadConstructorAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws InvocationTargetException {
        Constructor<?>[] constructorArr;
        int i;
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        int i2 = 0;
        while (i2 < length) {
            Constructor<?> constructor = declaredConstructors[i2];
            Name nameSpecial = Name.special("<init>");
            Intrinsics.checkNotNullExpressionValue(nameSpecial, "Name.special(\"<init>\")");
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(constructor, "constructor");
            KotlinJvmBinaryClass.MethodAnnotationVisitor methodAnnotationVisitorVisitMethod = memberVisitor.visitMethod(nameSpecial, signatureSerializer.constructorDesc(constructor));
            if (methodAnnotationVisitorVisitMethod != null) {
                for (Annotation annotation : constructor.getDeclaredAnnotations()) {
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(methodAnnotationVisitorVisitMethod, annotation);
                }
                Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "parameterAnnotations");
                Annotation[][] annotationArr = parameterAnnotations;
                if (!(annotationArr.length == 0)) {
                    int length2 = constructor.getParameterTypes().length - annotationArr.length;
                    int length3 = parameterAnnotations.length;
                    for (int i3 = 0; i3 < length3; i3++) {
                        Annotation[] annotationArr2 = parameterAnnotations[i3];
                        int length4 = annotationArr2.length;
                        int i4 = 0;
                        while (i4 < length4) {
                            Annotation annotation2 = annotationArr2[i4];
                            Class<?> javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2));
                            Constructor<?>[] constructorArr2 = declaredConstructors;
                            int i5 = length;
                            ClassId classId = ReflectClassUtilKt.getClassId(javaClass);
                            int i6 = length2;
                            Intrinsics.checkNotNullExpressionValue(annotation2, "annotation");
                            KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitParameterAnnotation = methodAnnotationVisitorVisitMethod.visitParameterAnnotation(i3 + length2, classId, new ReflectAnnotationSource(annotation2));
                            if (annotationArgumentVisitorVisitParameterAnnotation != null) {
                                INSTANCE.processAnnotationArguments(annotationArgumentVisitorVisitParameterAnnotation, annotation2, javaClass);
                            }
                            i4++;
                            declaredConstructors = constructorArr2;
                            length = i5;
                            length2 = i6;
                        }
                    }
                }
                constructorArr = declaredConstructors;
                i = length;
                methodAnnotationVisitorVisitMethod.visitEnd();
            } else {
                constructorArr = declaredConstructors;
                i = length;
            }
            i2++;
            declaredConstructors = constructorArr;
            length = i;
        }
    }

    private final void loadFieldAnnotations(Class<?> cls, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws InvocationTargetException {
        for (Field field : cls.getDeclaredFields()) {
            Intrinsics.checkNotNullExpressionValue(field, "field");
            Name nameIdentifier = Name.identifier(field.getName());
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(field.name)");
            KotlinJvmBinaryClass.AnnotationVisitor annotationVisitorVisitField = memberVisitor.visitField(nameIdentifier, SignatureSerializer.INSTANCE.fieldDesc(field), null);
            if (annotationVisitorVisitField != null) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                    processAnnotation(annotationVisitorVisitField, annotation);
                }
                annotationVisitorVisitField.visitEnd();
            }
        }
    }

    private final void processAnnotation(KotlinJvmBinaryClass.AnnotationVisitor annotationVisitor, Annotation annotation) throws InvocationTargetException {
        Class<?> javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation));
        KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitAnnotation = annotationVisitor.visitAnnotation(ReflectClassUtilKt.getClassId(javaClass), new ReflectAnnotationSource(annotation));
        if (annotationArgumentVisitorVisitAnnotation != null) {
            INSTANCE.processAnnotationArguments(annotationArgumentVisitorVisitAnnotation, annotation, javaClass);
        }
    }

    private final void processAnnotationArguments(KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitor, Annotation annotation, Class<?> cls) throws InvocationTargetException {
        for (Method method : cls.getDeclaredMethods()) {
            try {
                Object objInvoke = method.invoke(annotation, new Object[0]);
                Intrinsics.checkNotNull(objInvoke);
                Intrinsics.checkNotNullExpressionValue(method, "method");
                Name nameIdentifier = Name.identifier(method.getName());
                Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(method.name)");
                processAnnotationArgumentValue(annotationArgumentVisitor, nameIdentifier, objInvoke);
            } catch (IllegalAccessException unused) {
            }
        }
        annotationArgumentVisitor.visitEnd();
    }

    private final ClassLiteralValue classLiteralValue(Class<?> cls) {
        int i = 0;
        while (cls.isArray()) {
            i++;
            cls = cls.getComponentType();
            Intrinsics.checkNotNullExpressionValue(cls, "currentClass.componentType");
        }
        if (cls.isPrimitive()) {
            if (Intrinsics.areEqual(cls, Void.TYPE)) {
                ClassId classId = ClassId.topLevel(StandardNames.FqNames.unit.toSafe());
                Intrinsics.checkNotNullExpressionValue(classId, "ClassId.topLevel(Standar…es.FqNames.unit.toSafe())");
                return new ClassLiteralValue(classId, i);
            }
            JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(cls.getName());
            Intrinsics.checkNotNullExpressionValue(jvmPrimitiveType, "JvmPrimitiveType.get(currentClass.name)");
            PrimitiveType primitiveType = jvmPrimitiveType.getPrimitiveType();
            Intrinsics.checkNotNullExpressionValue(primitiveType, "JvmPrimitiveType.get(cur…Class.name).primitiveType");
            if (i > 0) {
                ClassId classId2 = ClassId.topLevel(primitiveType.getArrayTypeFqName());
                Intrinsics.checkNotNullExpressionValue(classId2, "ClassId.topLevel(primitiveType.arrayTypeFqName)");
                return new ClassLiteralValue(classId2, i - 1);
            }
            ClassId classId3 = ClassId.topLevel(primitiveType.getTypeFqName());
            Intrinsics.checkNotNullExpressionValue(classId3, "ClassId.topLevel(primitiveType.typeFqName)");
            return new ClassLiteralValue(classId3, i);
        }
        ClassId classId4 = ReflectClassUtilKt.getClassId(cls);
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqName fqNameAsSingleFqName = classId4.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqNameAsSingleFqName, "javaClassId.asSingleFqName()");
        ClassId classIdMapJavaToKotlin = javaToKotlinClassMap.mapJavaToKotlin(fqNameAsSingleFqName);
        if (classIdMapJavaToKotlin != null) {
            classId4 = classIdMapJavaToKotlin;
        }
        return new ClassLiteralValue(classId4, i);
    }

    private final void processAnnotationArgumentValue(KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitor, Name name, Object obj) throws InvocationTargetException {
        Class<?> enclosingClass = obj.getClass();
        if (Intrinsics.areEqual(enclosingClass, Class.class)) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<*>");
            }
            annotationArgumentVisitor.visitClassLiteral(name, classLiteralValue((Class) obj));
            return;
        }
        if (ReflectKotlinClassKt.TYPES_ELIGIBLE_FOR_SIMPLE_VISIT.contains(enclosingClass)) {
            annotationArgumentVisitor.visit(name, obj);
            return;
        }
        if (ReflectClassUtilKt.isEnumClassOrSpecializedEnumEntryClass(enclosingClass)) {
            if (!enclosingClass.isEnum()) {
                enclosingClass = enclosingClass.getEnclosingClass();
                Intrinsics.checkNotNullExpressionValue(enclosingClass, "clazz.enclosingClass");
            }
            ClassId classId = ReflectClassUtilKt.getClassId(enclosingClass);
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Enum<*>");
            }
            Name nameIdentifier = Name.identifier(((Enum) obj).name());
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier((value as Enum<*>).name)");
            annotationArgumentVisitor.visitEnum(name, classId, nameIdentifier);
            return;
        }
        if (Annotation.class.isAssignableFrom(enclosingClass)) {
            Class<?>[] interfaces = enclosingClass.getInterfaces();
            Intrinsics.checkNotNullExpressionValue(interfaces, "clazz.interfaces");
            Class<?> annotationClass = (Class) ArraysKt.single(interfaces);
            KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitAnnotation = annotationArgumentVisitor.visitAnnotation(name, ReflectClassUtilKt.getClassId(annotationClass));
            if (annotationArgumentVisitorVisitAnnotation != null) {
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Annotation");
                }
                Intrinsics.checkNotNullExpressionValue(annotationClass, "annotationClass");
                processAnnotationArguments(annotationArgumentVisitorVisitAnnotation, (Annotation) obj, annotationClass);
                return;
            }
            return;
        }
        if (enclosingClass.isArray()) {
            KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor annotationArrayArgumentVisitorVisitArray = annotationArgumentVisitor.visitArray(name);
            if (annotationArrayArgumentVisitorVisitArray != null) {
                Class<?> componentType = enclosingClass.getComponentType();
                int i = 0;
                if (componentType.isEnum()) {
                    ClassId classId2 = ReflectClassUtilKt.getClassId(componentType);
                    if (obj != null) {
                        Object[] objArr = (Object[]) obj;
                        int length = objArr.length;
                        while (i < length) {
                            Object obj2 = objArr[i];
                            if (obj2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Enum<*>");
                            }
                            Name nameIdentifier2 = Name.identifier(((Enum) obj2).name());
                            Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "Name.identifier((element as Enum<*>).name)");
                            annotationArrayArgumentVisitorVisitArray.visitEnum(classId2, nameIdentifier2);
                            i++;
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<*>");
                    }
                } else if (Intrinsics.areEqual(componentType, Class.class)) {
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<*>");
                    }
                    Object[] objArr2 = (Object[]) obj;
                    int length2 = objArr2.length;
                    while (i < length2) {
                        Object obj3 = objArr2[i];
                        if (obj3 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<*>");
                        }
                        annotationArrayArgumentVisitorVisitArray.visitClassLiteral(classLiteralValue((Class) obj3));
                        i++;
                    }
                } else if (obj != null) {
                    Object[] objArr3 = (Object[]) obj;
                    int length3 = objArr3.length;
                    while (i < length3) {
                        annotationArrayArgumentVisitorVisitArray.visit(objArr3[i]);
                        i++;
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<*>");
                }
                annotationArrayArgumentVisitorVisitArray.visitEnd();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Unsupported annotation argument value (" + enclosingClass + "): " + obj);
    }
}
