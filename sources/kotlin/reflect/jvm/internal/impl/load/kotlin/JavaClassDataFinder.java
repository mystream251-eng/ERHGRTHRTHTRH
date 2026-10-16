package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder;

/* JADX INFO: compiled from: JavaClassDataFinder.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class JavaClassDataFinder implements ClassDataFinder {
    private final DeserializedDescriptorResolver deserializedDescriptorResolver;
    private final KotlinClassFinder kotlinClassFinder;

    public JavaClassDataFinder(KotlinClassFinder kotlinClassFinder, DeserializedDescriptorResolver deserializedDescriptorResolver) {
        Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
        Intrinsics.checkNotNullParameter(deserializedDescriptorResolver, "deserializedDescriptorResolver");
        this.kotlinClassFinder = kotlinClassFinder;
        this.deserializedDescriptorResolver = deserializedDescriptorResolver;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder
    public ClassData findClassData(ClassId classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId);
        if (kotlinJvmBinaryClassFindKotlinClass == null) {
            return null;
        }
        boolean zAreEqual = Intrinsics.areEqual(kotlinJvmBinaryClassFindKotlinClass.getClassId(), classId);
        if (_Assertions.ENABLED && !zAreEqual) {
            throw new AssertionError("Class with incorrect id found: expected " + classId + ", actual " + kotlinJvmBinaryClassFindKotlinClass.getClassId());
        }
        return this.deserializedDescriptorResolver.readClassData$descriptors_jvm(kotlinJvmBinaryClassFindKotlinClass);
    }
}
