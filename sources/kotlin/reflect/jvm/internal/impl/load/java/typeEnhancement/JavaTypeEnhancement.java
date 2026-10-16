package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: typeEnhancement.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class JavaTypeEnhancement {
    private final JavaResolverSettings javaResolverSettings;

    public JavaTypeEnhancement(JavaResolverSettings javaResolverSettings) {
        Intrinsics.checkNotNullParameter(javaResolverSettings, "javaResolverSettings");
        this.javaResolverSettings = javaResolverSettings;
    }

    /* JADX INFO: compiled from: typeEnhancement.kt */
    private static class Result {
        private final int subtreeSize;
        private final KotlinType type;
        private final boolean wereChanges;

        public Result(KotlinType type, int i, boolean z) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
            this.subtreeSize = i;
            this.wereChanges = z;
        }

        public final int getSubtreeSize() {
            return this.subtreeSize;
        }

        public KotlinType getType() {
            return this.type;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }

        public final KotlinType getTypeIfChanged() {
            KotlinType type = getType();
            if (this.wereChanges) {
                return type;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: typeEnhancement.kt */
    private static final class SimpleResult extends Result {
        private final SimpleType type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SimpleResult(SimpleType type, int i, boolean z) {
            super(type, i, z);
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeEnhancement.Result
        public SimpleType getType() {
            return this.type;
        }
    }

    public final KotlinType enhance(KotlinType enhance, Function1<? super Integer, JavaTypeQualifiers> qualifiers) {
        Intrinsics.checkNotNullParameter(enhance, "$this$enhance");
        Intrinsics.checkNotNullParameter(qualifiers, "qualifiers");
        return enhancePossiblyFlexible(enhance.unwrap(), qualifiers, 0).getTypeIfChanged();
    }

    private final Result enhancePossiblyFlexible(UnwrappedType unwrappedType, Function1<? super Integer, JavaTypeQualifiers> function1, int i) {
        UnwrappedType unwrappedType2 = unwrappedType;
        if (KotlinTypeKt.isError(unwrappedType2)) {
            return new Result(unwrappedType2, 1, false);
        }
        if (unwrappedType instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleResult simpleResultEnhanceInflexible = enhanceInflexible(flexibleType.getLowerBound(), function1, i, TypeComponentPosition.FLEXIBLE_LOWER);
            SimpleResult simpleResultEnhanceInflexible2 = enhanceInflexible(flexibleType.getUpperBound(), function1, i, TypeComponentPosition.FLEXIBLE_UPPER);
            boolean z = simpleResultEnhanceInflexible.getSubtreeSize() == simpleResultEnhanceInflexible2.getSubtreeSize();
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Different tree sizes of bounds: lower = (" + flexibleType.getLowerBound() + ", " + simpleResultEnhanceInflexible.getSubtreeSize() + "), upper = (" + flexibleType.getUpperBound() + ", " + simpleResultEnhanceInflexible2.getSubtreeSize() + ')');
            }
            boolean z2 = simpleResultEnhanceInflexible.getWereChanges() || simpleResultEnhanceInflexible2.getWereChanges();
            KotlinType enhancement = TypeWithEnhancementKt.getEnhancement(simpleResultEnhanceInflexible.getType());
            if (enhancement == null) {
                enhancement = TypeWithEnhancementKt.getEnhancement(simpleResultEnhanceInflexible2.getType());
            }
            if (z2) {
                unwrappedType = TypeWithEnhancementKt.wrapEnhancement(unwrappedType instanceof RawTypeImpl ? new RawTypeImpl(simpleResultEnhanceInflexible.getType(), simpleResultEnhanceInflexible2.getType()) : KotlinTypeFactory.flexibleType(simpleResultEnhanceInflexible.getType(), simpleResultEnhanceInflexible2.getType()), enhancement);
            }
            return new Result(unwrappedType, simpleResultEnhanceInflexible.getSubtreeSize(), z2);
        }
        if (unwrappedType instanceof SimpleType) {
            return enhanceInflexible((SimpleType) unwrappedType, function1, i, TypeComponentPosition.INFLEXIBLE);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final SimpleResult enhanceInflexible(SimpleType simpleType, Function1<? super Integer, JavaTypeQualifiers> function1, int i, TypeComponentPosition typeComponentPosition) {
        TypeProjection typeProjectionCreateProjection;
        Function1<? super Integer, JavaTypeQualifiers> function2 = function1;
        boolean z = false;
        if (!TypeEnhancementKt.shouldEnhance(typeComponentPosition) && simpleType.getArguments().isEmpty()) {
            return new SimpleResult(simpleType, 1, false);
        }
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = simpleType.getConstructor().mo1268getDeclarationDescriptor();
        if (classifierDescriptorMo1268getDeclarationDescriptor != null) {
            Intrinsics.checkNotNullExpressionValue(classifierDescriptorMo1268getDeclarationDescriptor, "constructor.declarationD…pleResult(this, 1, false)");
            JavaTypeQualifiers javaTypeQualifiersInvoke = function2.invoke(Integer.valueOf(i));
            EnhancementResult enhancementResultEnhanceMutability = TypeEnhancementKt.enhanceMutability(classifierDescriptorMo1268getDeclarationDescriptor, javaTypeQualifiersInvoke, typeComponentPosition);
            ClassifierDescriptor classifierDescriptor = (ClassifierDescriptor) enhancementResultEnhanceMutability.component1();
            Annotations annotationsComponent2 = enhancementResultEnhanceMutability.component2();
            TypeConstructor typeConstructor = classifierDescriptor.getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor, "enhancedClassifier.typeConstructor");
            int subtreeSize = i + 1;
            boolean z2 = annotationsComponent2 != null;
            List<TypeProjection> arguments = simpleType.getArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
            int i2 = 0;
            for (Object obj : arguments) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                TypeProjection typeProjection = (TypeProjection) obj;
                if (typeProjection.isStarProjection()) {
                    subtreeSize++;
                    TypeConstructor typeConstructor2 = classifierDescriptor.getTypeConstructor();
                    Intrinsics.checkNotNullExpressionValue(typeConstructor2, "enhancedClassifier.typeConstructor");
                    typeProjectionCreateProjection = TypeUtils.makeStarProjection(typeConstructor2.getParameters().get(i2));
                } else {
                    Result resultEnhancePossiblyFlexible = enhancePossiblyFlexible(typeProjection.getType().unwrap(), function2, subtreeSize);
                    z2 = (z2 || resultEnhancePossiblyFlexible.getWereChanges()) ? true : z;
                    subtreeSize += resultEnhancePossiblyFlexible.getSubtreeSize();
                    KotlinType type = resultEnhancePossiblyFlexible.getType();
                    Variance projectionKind = typeProjection.getProjectionKind();
                    Intrinsics.checkNotNullExpressionValue(projectionKind, "arg.projectionKind");
                    typeProjectionCreateProjection = TypeUtilsKt.createProjection(type, projectionKind, typeConstructor.getParameters().get(i2));
                }
                arrayList.add(typeProjectionCreateProjection);
                function2 = function1;
                i2 = i3;
                z = false;
            }
            ArrayList arrayList2 = arrayList;
            EnhancementResult enhancedNullability = TypeEnhancementKt.getEnhancedNullability(simpleType, javaTypeQualifiersInvoke, typeComponentPosition);
            boolean zBooleanValue = ((Boolean) enhancedNullability.component1()).booleanValue();
            Annotations annotationsComponent3 = enhancedNullability.component2();
            int i4 = subtreeSize - i;
            if (!(z2 || annotationsComponent3 != null)) {
                return new SimpleResult(simpleType, i4, false);
            }
            SimpleType simpleTypeSimpleType$default = KotlinTypeFactory.simpleType$default(TypeEnhancementKt.compositeAnnotationsOrSingle(CollectionsKt.listOfNotNull((Object[]) new Annotations[]{simpleType.getAnnotations(), annotationsComponent2, annotationsComponent3})), typeConstructor, arrayList2, zBooleanValue, null, 16, null);
            if (javaTypeQualifiersInvoke.isNotNullTypeParameter()) {
                simpleTypeSimpleType$default = notNullTypeParameter(simpleTypeSimpleType$default);
            }
            KotlinType kotlinTypeWrapEnhancement = annotationsComponent3 != null && javaTypeQualifiersInvoke.isNullabilityQualifierForWarning() ? TypeWithEnhancementKt.wrapEnhancement(simpleType, simpleTypeSimpleType$default) : simpleTypeSimpleType$default;
            if (kotlinTypeWrapEnhancement != null) {
                return new SimpleResult((SimpleType) kotlinTypeWrapEnhancement, i4, true);
            }
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        }
        return new SimpleResult(simpleType, 1, false);
    }

    private final SimpleType notNullTypeParameter(SimpleType simpleType) {
        if (this.javaResolverSettings.getCorrectNullabilityForNotNullTypeParameter()) {
            return SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType, true);
        }
        return new NotNullTypeParameter(simpleType);
    }
}
