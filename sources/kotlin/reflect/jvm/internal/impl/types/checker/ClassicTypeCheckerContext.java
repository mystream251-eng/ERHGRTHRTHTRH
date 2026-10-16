package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

/* JADX INFO: compiled from: ClassicTypeCheckerContext.kt */
/* JADX INFO: loaded from: classes3.dex */
public class ClassicTypeCheckerContext extends AbstractTypeCheckerContext implements ClassicTypeSystemContext {
    public static final Companion Companion = new Companion(null);
    private final boolean allowedTypeVariable;
    private final boolean errorTypeEqualsToAnything;
    private final KotlinTypeRefiner kotlinTypeRefiner;
    private final boolean stubTypeEqualsToAnything;

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int argumentsCount(KotlinTypeMarker argumentsCount) {
        Intrinsics.checkNotNullParameter(argumentsCount, "$this$argumentsCount");
        return ClassicTypeSystemContext.DefaultImpls.argumentsCount(this, argumentsCount);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentListMarker asArgumentList(SimpleTypeMarker asArgumentList) {
        Intrinsics.checkNotNullParameter(asArgumentList, "$this$asArgumentList");
        return ClassicTypeSystemContext.DefaultImpls.asArgumentList(this, asArgumentList);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public CapturedTypeMarker asCapturedType(SimpleTypeMarker asCapturedType) {
        Intrinsics.checkNotNullParameter(asCapturedType, "$this$asCapturedType");
        return ClassicTypeSystemContext.DefaultImpls.asCapturedType(this, asCapturedType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(SimpleTypeMarker asDefinitelyNotNullType) {
        Intrinsics.checkNotNullParameter(asDefinitelyNotNullType, "$this$asDefinitelyNotNullType");
        return ClassicTypeSystemContext.DefaultImpls.asDefinitelyNotNullType(this, asDefinitelyNotNullType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public DynamicTypeMarker asDynamicType(FlexibleTypeMarker asDynamicType) {
        Intrinsics.checkNotNullParameter(asDynamicType, "$this$asDynamicType");
        return ClassicTypeSystemContext.DefaultImpls.asDynamicType(this, asDynamicType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public FlexibleTypeMarker asFlexibleType(KotlinTypeMarker asFlexibleType) {
        Intrinsics.checkNotNullParameter(asFlexibleType, "$this$asFlexibleType");
        return ClassicTypeSystemContext.DefaultImpls.asFlexibleType(this, asFlexibleType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext, kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public SimpleTypeMarker asSimpleType(KotlinTypeMarker asSimpleType) {
        Intrinsics.checkNotNullParameter(asSimpleType, "$this$asSimpleType");
        return ClassicTypeSystemContext.DefaultImpls.asSimpleType(this, asSimpleType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker asTypeArgument(KotlinTypeMarker asTypeArgument) {
        Intrinsics.checkNotNullParameter(asTypeArgument, "$this$asTypeArgument");
        return ClassicTypeSystemContext.DefaultImpls.asTypeArgument(this, asTypeArgument);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker captureFromArguments(SimpleTypeMarker type, CaptureStatus status) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(status, "status");
        return ClassicTypeSystemContext.DefaultImpls.captureFromArguments(this, type, status);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public List<SimpleTypeMarker> fastCorrespondingSupertypes(SimpleTypeMarker fastCorrespondingSupertypes, TypeConstructorMarker constructor) {
        Intrinsics.checkNotNullParameter(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        return ClassicTypeSystemContext.DefaultImpls.fastCorrespondingSupertypes(this, fastCorrespondingSupertypes, constructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker get(TypeArgumentListMarker get, int i) {
        Intrinsics.checkNotNullParameter(get, "$this$get");
        return ClassicTypeSystemContext.DefaultImpls.get(this, get, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker getArgument(KotlinTypeMarker getArgument, int i) {
        Intrinsics.checkNotNullParameter(getArgument, "$this$getArgument");
        return ClassicTypeSystemContext.DefaultImpls.getArgument(this, getArgument, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public TypeArgumentMarker getArgumentOrNull(SimpleTypeMarker getArgumentOrNull, int i) {
        Intrinsics.checkNotNullParameter(getArgumentOrNull, "$this$getArgumentOrNull");
        return ClassicTypeSystemContext.DefaultImpls.getArgumentOrNull(this, getArgumentOrNull, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public FqNameUnsafe getClassFqNameUnsafe(TypeConstructorMarker getClassFqNameUnsafe) {
        Intrinsics.checkNotNullParameter(getClassFqNameUnsafe, "$this$getClassFqNameUnsafe");
        return ClassicTypeSystemContext.DefaultImpls.getClassFqNameUnsafe(this, getClassFqNameUnsafe);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeParameterMarker getParameter(TypeConstructorMarker getParameter, int i) {
        Intrinsics.checkNotNullParameter(getParameter, "$this$getParameter");
        return ClassicTypeSystemContext.DefaultImpls.getParameter(this, getParameter, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public PrimitiveType getPrimitiveArrayType(TypeConstructorMarker getPrimitiveArrayType) {
        Intrinsics.checkNotNullParameter(getPrimitiveArrayType, "$this$getPrimitiveArrayType");
        return ClassicTypeSystemContext.DefaultImpls.getPrimitiveArrayType(this, getPrimitiveArrayType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public PrimitiveType getPrimitiveType(TypeConstructorMarker getPrimitiveType) {
        Intrinsics.checkNotNullParameter(getPrimitiveType, "$this$getPrimitiveType");
        return ClassicTypeSystemContext.DefaultImpls.getPrimitiveType(this, getPrimitiveType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public KotlinTypeMarker getRepresentativeUpperBound(TypeParameterMarker getRepresentativeUpperBound) {
        Intrinsics.checkNotNullParameter(getRepresentativeUpperBound, "$this$getRepresentativeUpperBound");
        return ClassicTypeSystemContext.DefaultImpls.getRepresentativeUpperBound(this, getRepresentativeUpperBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public KotlinTypeMarker getSubstitutedUnderlyingType(KotlinTypeMarker getSubstitutedUnderlyingType) {
        Intrinsics.checkNotNullParameter(getSubstitutedUnderlyingType, "$this$getSubstitutedUnderlyingType");
        return ClassicTypeSystemContext.DefaultImpls.getSubstitutedUnderlyingType(this, getSubstitutedUnderlyingType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker getType(TypeArgumentMarker getType) {
        Intrinsics.checkNotNullParameter(getType, "$this$getType");
        return ClassicTypeSystemContext.DefaultImpls.getType(this, getType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public TypeParameterMarker getTypeParameterClassifier(TypeConstructorMarker getTypeParameterClassifier) {
        Intrinsics.checkNotNullParameter(getTypeParameterClassifier, "$this$getTypeParameterClassifier");
        return ClassicTypeSystemContext.DefaultImpls.getTypeParameterClassifier(this, getTypeParameterClassifier);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeVariance getVariance(TypeArgumentMarker getVariance) {
        Intrinsics.checkNotNullParameter(getVariance, "$this$getVariance");
        return ClassicTypeSystemContext.DefaultImpls.getVariance(this, getVariance);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeVariance getVariance(TypeParameterMarker getVariance) {
        Intrinsics.checkNotNullParameter(getVariance, "$this$getVariance");
        return ClassicTypeSystemContext.DefaultImpls.getVariance(this, getVariance);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean hasAnnotation(KotlinTypeMarker hasAnnotation, FqName fqName) {
        Intrinsics.checkNotNullParameter(hasAnnotation, "$this$hasAnnotation");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        return ClassicTypeSystemContext.DefaultImpls.hasAnnotation(this, hasAnnotation, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean hasFlexibleNullability(KotlinTypeMarker hasFlexibleNullability) {
        Intrinsics.checkNotNullParameter(hasFlexibleNullability, "$this$hasFlexibleNullability");
        return ClassicTypeSystemContext.DefaultImpls.hasFlexibleNullability(this, hasFlexibleNullability);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext
    public boolean identicalArguments(SimpleTypeMarker a2, SimpleTypeMarker b) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return ClassicTypeSystemContext.DefaultImpls.identicalArguments(this, a2, b);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker intersectTypes(List<? extends KotlinTypeMarker> types) {
        Intrinsics.checkNotNullParameter(types, "types");
        return ClassicTypeSystemContext.DefaultImpls.intersectTypes(this, types);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isAnyConstructor(TypeConstructorMarker isAnyConstructor) {
        Intrinsics.checkNotNullParameter(isAnyConstructor, "$this$isAnyConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isAnyConstructor(this, isAnyConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isClassType(SimpleTypeMarker isClassType) {
        Intrinsics.checkNotNullParameter(isClassType, "$this$isClassType");
        return ClassicTypeSystemContext.DefaultImpls.isClassType(this, isClassType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isClassTypeConstructor(TypeConstructorMarker isClassTypeConstructor) {
        Intrinsics.checkNotNullParameter(isClassTypeConstructor, "$this$isClassTypeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isClassTypeConstructor(this, isClassTypeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isCommonFinalClassConstructor(TypeConstructorMarker isCommonFinalClassConstructor) {
        Intrinsics.checkNotNullParameter(isCommonFinalClassConstructor, "$this$isCommonFinalClassConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isCommonFinalClassConstructor(this, isCommonFinalClassConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isDefinitelyNotNullType(KotlinTypeMarker isDefinitelyNotNullType) {
        Intrinsics.checkNotNullParameter(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
        return ClassicTypeSystemContext.DefaultImpls.isDefinitelyNotNullType(this, isDefinitelyNotNullType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDenotable(TypeConstructorMarker isDenotable) {
        Intrinsics.checkNotNullParameter(isDenotable, "$this$isDenotable");
        return ClassicTypeSystemContext.DefaultImpls.isDenotable(this, isDenotable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isDynamic(KotlinTypeMarker isDynamic) {
        Intrinsics.checkNotNullParameter(isDynamic, "$this$isDynamic");
        return ClassicTypeSystemContext.DefaultImpls.isDynamic(this, isDynamic);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isError(KotlinTypeMarker isError) {
        Intrinsics.checkNotNullParameter(isError, "$this$isError");
        return ClassicTypeSystemContext.DefaultImpls.isError(this, isError);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isInlineClass(TypeConstructorMarker isInlineClass) {
        Intrinsics.checkNotNullParameter(isInlineClass, "$this$isInlineClass");
        return ClassicTypeSystemContext.DefaultImpls.isInlineClass(this, isInlineClass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isIntegerLiteralType(SimpleTypeMarker isIntegerLiteralType) {
        Intrinsics.checkNotNullParameter(isIntegerLiteralType, "$this$isIntegerLiteralType");
        return ClassicTypeSystemContext.DefaultImpls.isIntegerLiteralType(this, isIntegerLiteralType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker isIntegerLiteralTypeConstructor) {
        Intrinsics.checkNotNullParameter(isIntegerLiteralTypeConstructor, "$this$isIntegerLiteralTypeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isIntegerLiteralTypeConstructor(this, isIntegerLiteralTypeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntersection(TypeConstructorMarker isIntersection) {
        Intrinsics.checkNotNullParameter(isIntersection, "$this$isIntersection");
        return ClassicTypeSystemContext.DefaultImpls.isIntersection(this, isIntersection);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(KotlinTypeMarker isMarkedNullable) {
        Intrinsics.checkNotNullParameter(isMarkedNullable, "$this$isMarkedNullable");
        return ClassicTypeSystemContext.DefaultImpls.isMarkedNullable(this, isMarkedNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(SimpleTypeMarker isMarkedNullable) {
        Intrinsics.checkNotNullParameter(isMarkedNullable, "$this$isMarkedNullable");
        return ClassicTypeSystemContext.DefaultImpls.isMarkedNullable((ClassicTypeSystemContext) this, isMarkedNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isNothing(KotlinTypeMarker isNothing) {
        Intrinsics.checkNotNullParameter(isNothing, "$this$isNothing");
        return ClassicTypeSystemContext.DefaultImpls.isNothing(this, isNothing);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNothingConstructor(TypeConstructorMarker isNothingConstructor) {
        Intrinsics.checkNotNullParameter(isNothingConstructor, "$this$isNothingConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isNothingConstructor(this, isNothingConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNullableType(KotlinTypeMarker isNullableType) {
        Intrinsics.checkNotNullParameter(isNullableType, "$this$isNullableType");
        return ClassicTypeSystemContext.DefaultImpls.isNullableType(this, isNullableType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isPrimitiveType(SimpleTypeMarker isPrimitiveType) {
        Intrinsics.checkNotNullParameter(isPrimitiveType, "$this$isPrimitiveType");
        return ClassicTypeSystemContext.DefaultImpls.isPrimitiveType(this, isPrimitiveType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isProjectionNotNull(CapturedTypeMarker isProjectionNotNull) {
        Intrinsics.checkNotNullParameter(isProjectionNotNull, "$this$isProjectionNotNull");
        return ClassicTypeSystemContext.DefaultImpls.isProjectionNotNull(this, isProjectionNotNull);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isSingleClassifierType(SimpleTypeMarker isSingleClassifierType) {
        Intrinsics.checkNotNullParameter(isSingleClassifierType, "$this$isSingleClassifierType");
        return ClassicTypeSystemContext.DefaultImpls.isSingleClassifierType(this, isSingleClassifierType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStarProjection(TypeArgumentMarker isStarProjection) {
        Intrinsics.checkNotNullParameter(isStarProjection, "$this$isStarProjection");
        return ClassicTypeSystemContext.DefaultImpls.isStarProjection(this, isStarProjection);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStubType(SimpleTypeMarker isStubType) {
        Intrinsics.checkNotNullParameter(isStubType, "$this$isStubType");
        return ClassicTypeSystemContext.DefaultImpls.isStubType(this, isStubType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isUnderKotlinPackage(TypeConstructorMarker isUnderKotlinPackage) {
        Intrinsics.checkNotNullParameter(isUnderKotlinPackage, "$this$isUnderKotlinPackage");
        return ClassicTypeSystemContext.DefaultImpls.isUnderKotlinPackage(this, isUnderKotlinPackage);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker lowerBound(FlexibleTypeMarker lowerBound) {
        Intrinsics.checkNotNullParameter(lowerBound, "$this$lowerBound");
        return ClassicTypeSystemContext.DefaultImpls.lowerBound(this, lowerBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker lowerBoundIfFlexible(KotlinTypeMarker lowerBoundIfFlexible) {
        Intrinsics.checkNotNullParameter(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
        return ClassicTypeSystemContext.DefaultImpls.lowerBoundIfFlexible(this, lowerBoundIfFlexible);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker lowerType(CapturedTypeMarker lowerType) {
        Intrinsics.checkNotNullParameter(lowerType, "$this$lowerType");
        return ClassicTypeSystemContext.DefaultImpls.lowerType(this, lowerType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public KotlinTypeMarker makeNullable(KotlinTypeMarker makeNullable) {
        Intrinsics.checkNotNullParameter(makeNullable, "$this$makeNullable");
        return ClassicTypeSystemContext.DefaultImpls.makeNullable(this, makeNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int parametersCount(TypeConstructorMarker parametersCount) {
        Intrinsics.checkNotNullParameter(parametersCount, "$this$parametersCount");
        return ClassicTypeSystemContext.DefaultImpls.parametersCount(this, parametersCount);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public Collection<KotlinTypeMarker> possibleIntegerTypes(SimpleTypeMarker possibleIntegerTypes) {
        Intrinsics.checkNotNullParameter(possibleIntegerTypes, "$this$possibleIntegerTypes");
        return ClassicTypeSystemContext.DefaultImpls.possibleIntegerTypes(this, possibleIntegerTypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int size(TypeArgumentListMarker size) {
        Intrinsics.checkNotNullParameter(size, "$this$size");
        return ClassicTypeSystemContext.DefaultImpls.size(this, size);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public Collection<KotlinTypeMarker> supertypes(TypeConstructorMarker supertypes) {
        Intrinsics.checkNotNullParameter(supertypes, "$this$supertypes");
        return ClassicTypeSystemContext.DefaultImpls.supertypes(this, supertypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeConstructorMarker typeConstructor(KotlinTypeMarker typeConstructor) {
        Intrinsics.checkNotNullParameter(typeConstructor, "$this$typeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor(this, typeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext, kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public TypeConstructorMarker typeConstructor(SimpleTypeMarker typeConstructor) {
        Intrinsics.checkNotNullParameter(typeConstructor, "$this$typeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor((ClassicTypeSystemContext) this, typeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker upperBound(FlexibleTypeMarker upperBound) {
        Intrinsics.checkNotNullParameter(upperBound, "$this$upperBound");
        return ClassicTypeSystemContext.DefaultImpls.upperBound(this, upperBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker upperBoundIfFlexible(KotlinTypeMarker upperBoundIfFlexible) {
        Intrinsics.checkNotNullParameter(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
        return ClassicTypeSystemContext.DefaultImpls.upperBoundIfFlexible(this, upperBoundIfFlexible);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker withNullability(SimpleTypeMarker withNullability, boolean z) {
        Intrinsics.checkNotNullParameter(withNullability, "$this$withNullability");
        return ClassicTypeSystemContext.DefaultImpls.withNullability(this, withNullability, z);
    }

    public /* synthetic */ ClassicTypeCheckerContext(boolean z, boolean z2, boolean z3, KotlinTypeRefiner.Default r5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? KotlinTypeRefiner.Default.INSTANCE : r5);
    }

    public ClassicTypeCheckerContext(boolean z, boolean z2, boolean z3, KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        this.errorTypeEqualsToAnything = z;
        this.stubTypeEqualsToAnything = z2;
        this.allowedTypeVariable = z3;
        this.kotlinTypeRefiner = kotlinTypeRefiner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public KotlinTypeMarker prepareType(KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof KotlinType)) {
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(type).toString());
        }
        return NewKotlinTypeChecker.Companion.getDefault().transformToNewType(((KotlinType) type).unwrap());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public KotlinTypeMarker refineType(KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof KotlinType)) {
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(type).toString());
        }
        return this.kotlinTypeRefiner.refineType((KotlinType) type);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isErrorTypeEqualsToAnything() {
        return this.errorTypeEqualsToAnything;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isStubTypeEqualsToAnything() {
        return this.stubTypeEqualsToAnything;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean areEqualTypeConstructors(TypeConstructorMarker a2, TypeConstructorMarker b) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (!(a2 instanceof TypeConstructor)) {
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(a2).toString());
        }
        if (!(b instanceof TypeConstructor)) {
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(b).toString());
        }
        return areEqualTypeConstructors((TypeConstructor) a2, (TypeConstructor) b);
    }

    public boolean areEqualTypeConstructors(TypeConstructor a2, TypeConstructor b) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (a2 instanceof IntegerLiteralTypeConstructor) {
            return ((IntegerLiteralTypeConstructor) a2).checkConstructor(b);
        }
        return b instanceof IntegerLiteralTypeConstructor ? ((IntegerLiteralTypeConstructor) b).checkConstructor(a2) : Intrinsics.areEqual(a2, b);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform substitutionSupertypePolicy(SimpleTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return Companion.classicSubstitutionSupertypePolicy(this, type);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isAllowedTypeVariable(KotlinTypeMarker isAllowedTypeVariable) {
        Intrinsics.checkNotNullParameter(isAllowedTypeVariable, "$this$isAllowedTypeVariable");
        return (isAllowedTypeVariable instanceof UnwrappedType) && this.allowedTypeVariable && (((UnwrappedType) isAllowedTypeVariable).getConstructor() instanceof NewTypeVariableConstructor);
    }

    /* JADX INFO: compiled from: ClassicTypeCheckerContext.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform classicSubstitutionSupertypePolicy(final ClassicTypeSystemContext classicSubstitutionSupertypePolicy, SimpleTypeMarker type) {
            Intrinsics.checkNotNullParameter(classicSubstitutionSupertypePolicy, "$this$classicSubstitutionSupertypePolicy");
            Intrinsics.checkNotNullParameter(type, "type");
            if (!(type instanceof SimpleType)) {
                throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(type).toString());
            }
            final TypeSubstitutor typeSubstitutorBuildSubstitutor = TypeConstructorSubstitution.Companion.create((KotlinType) type).buildSubstitutor();
            return new AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeCheckerContext$Companion$classicSubstitutionSupertypePolicy$2
                @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
                /* JADX INFO: renamed from: transformType */
                public SimpleTypeMarker mo1272transformType(AbstractTypeCheckerContext context, KotlinTypeMarker type2) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(type2, "type");
                    ClassicTypeSystemContext classicTypeSystemContext = classicSubstitutionSupertypePolicy;
                    TypeSubstitutor typeSubstitutor = typeSubstitutorBuildSubstitutor;
                    Object objLowerBoundIfFlexible = classicTypeSystemContext.lowerBoundIfFlexible(type2);
                    if (objLowerBoundIfFlexible != null) {
                        KotlinType kotlinTypeSafeSubstitute = typeSubstitutor.safeSubstitute((KotlinType) objLowerBoundIfFlexible, Variance.INVARIANT);
                        Intrinsics.checkNotNullExpressionValue(kotlinTypeSafeSubstitute, "substitutor.safeSubstitu…ANT\n                    )");
                        SimpleTypeMarker simpleTypeMarkerAsSimpleType = classicTypeSystemContext.asSimpleType(kotlinTypeSafeSubstitute);
                        Intrinsics.checkNotNull(simpleTypeMarkerAsSimpleType);
                        return simpleTypeMarkerAsSimpleType;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.KotlinType");
                }
            };
        }
    }
}
