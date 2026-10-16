package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: NewKotlinTypeChecker.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class NewKotlinTypeCheckerImpl implements NewKotlinTypeChecker {
    private final KotlinTypeRefiner kotlinTypeRefiner;
    private final OverridingUtil overridingUtil;

    public NewKotlinTypeCheckerImpl(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        this.kotlinTypeRefiner = kotlinTypeRefiner;
        OverridingUtil overridingUtilCreateWithTypeRefiner = OverridingUtil.createWithTypeRefiner(getKotlinTypeRefiner());
        Intrinsics.checkNotNullExpressionValue(overridingUtilCreateWithTypeRefiner, "OverridingUtil.createWit…efiner(kotlinTypeRefiner)");
        this.overridingUtil = overridingUtilCreateWithTypeRefiner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    public KotlinTypeRefiner getKotlinTypeRefiner() {
        return this.kotlinTypeRefiner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    public OverridingUtil getOverridingUtil() {
        return this.overridingUtil;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean isSubtypeOf(KotlinType subtype, KotlinType supertype) {
        Intrinsics.checkNotNullParameter(subtype, "subtype");
        Intrinsics.checkNotNullParameter(supertype, "supertype");
        return isSubtypeOf(new ClassicTypeCheckerContext(true, false, false, getKotlinTypeRefiner(), 6, null), subtype.unwrap(), supertype.unwrap());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean equalTypes(KotlinType a2, KotlinType b) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return equalTypes(new ClassicTypeCheckerContext(false, false, false, getKotlinTypeRefiner(), 6, null), a2.unwrap(), b.unwrap());
    }

    public final boolean equalTypes(ClassicTypeCheckerContext equalTypes, UnwrappedType a2, UnwrappedType b) {
        Intrinsics.checkNotNullParameter(equalTypes, "$this$equalTypes");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return AbstractTypeChecker.INSTANCE.equalTypes(equalTypes, a2, b);
    }

    public final boolean isSubtypeOf(ClassicTypeCheckerContext isSubtypeOf, UnwrappedType subType, UnwrappedType superType) {
        Intrinsics.checkNotNullParameter(isSubtypeOf, "$this$isSubtypeOf");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, isSubtypeOf, subType, superType, false, 8, null);
    }

    public final SimpleType transformToNewType(SimpleType type) {
        KotlinType type2;
        Intrinsics.checkNotNullParameter(type, "type");
        TypeConstructor constructor = type.getConstructor();
        boolean z = false;
        IntersectionTypeConstructor alternative = null;
        unwrappedTypeUnwrap = null;
        UnwrappedType unwrappedTypeUnwrap = null;
        if (constructor instanceof CapturedTypeConstructorImpl) {
            CapturedTypeConstructorImpl capturedTypeConstructorImpl = (CapturedTypeConstructorImpl) constructor;
            TypeProjection projection = capturedTypeConstructorImpl.getProjection();
            if (!(projection.getProjectionKind() == Variance.IN_VARIANCE)) {
                projection = null;
            }
            if (projection != null && (type2 = projection.getType()) != null) {
                unwrappedTypeUnwrap = type2.unwrap();
            }
            UnwrappedType unwrappedType = unwrappedTypeUnwrap;
            if (capturedTypeConstructorImpl.getNewTypeConstructor() == null) {
                TypeProjection projection2 = capturedTypeConstructorImpl.getProjection();
                Collection<KotlinType> collectionMo1269getSupertypes = capturedTypeConstructorImpl.mo1269getSupertypes();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionMo1269getSupertypes, 10));
                Iterator<T> it = collectionMo1269getSupertypes.iterator();
                while (it.hasNext()) {
                    arrayList.add(((KotlinType) it.next()).unwrap());
                }
                capturedTypeConstructorImpl.setNewTypeConstructor(new NewCapturedTypeConstructor(projection2, arrayList, null, 4, null));
            }
            CaptureStatus captureStatus = CaptureStatus.FOR_SUBTYPING;
            NewCapturedTypeConstructor newTypeConstructor = capturedTypeConstructorImpl.getNewTypeConstructor();
            Intrinsics.checkNotNull(newTypeConstructor);
            return new NewCapturedType(captureStatus, newTypeConstructor, unwrappedType, type.getAnnotations(), type.isMarkedNullable(), false, 32, null);
        }
        if (constructor instanceof IntegerValueTypeConstructor) {
            Collection<KotlinType> collectionMo1269getSupertypes2 = ((IntegerValueTypeConstructor) constructor).mo1269getSupertypes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionMo1269getSupertypes2, 10));
            Iterator<T> it2 = collectionMo1269getSupertypes2.iterator();
            while (it2.hasNext()) {
                KotlinType kotlinTypeMakeNullableAsSpecified = TypeUtils.makeNullableAsSpecified((KotlinType) it2.next(), type.isMarkedNullable());
                Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNullableAsSpecified, "TypeUtils.makeNullableAs…t, type.isMarkedNullable)");
                arrayList2.add(kotlinTypeMakeNullableAsSpecified);
            }
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(type.getAnnotations(), new IntersectionTypeConstructor(arrayList2), CollectionsKt.emptyList(), false, type.getMemberScope());
        }
        if (!(constructor instanceof IntersectionTypeConstructor) || !type.isMarkedNullable()) {
            return type;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
        Collection<KotlinType> collectionMo1269getSupertypes3 = intersectionTypeConstructor.mo1269getSupertypes();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionMo1269getSupertypes3, 10));
        Iterator<T> it3 = collectionMo1269getSupertypes3.iterator();
        while (it3.hasNext()) {
            arrayList3.add(TypeUtilsKt.makeNullable((KotlinType) it3.next()));
            z = true;
        }
        ArrayList arrayList4 = arrayList3;
        if (z) {
            KotlinType alternativeType = intersectionTypeConstructor.getAlternativeType();
            alternative = new IntersectionTypeConstructor(arrayList4).setAlternative(alternativeType != null ? TypeUtilsKt.makeNullable(alternativeType) : null);
        }
        if (alternative != null) {
            intersectionTypeConstructor = alternative;
        }
        return intersectionTypeConstructor.createType();
    }

    public UnwrappedType transformToNewType(UnwrappedType type) {
        SimpleType simpleTypeFlexibleType;
        Intrinsics.checkNotNullParameter(type, "type");
        if (type instanceof SimpleType) {
            simpleTypeFlexibleType = transformToNewType((SimpleType) type);
        } else if (type instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) type;
            SimpleType simpleTypeTransformToNewType = transformToNewType(flexibleType.getLowerBound());
            SimpleType simpleTypeTransformToNewType2 = transformToNewType(flexibleType.getUpperBound());
            simpleTypeFlexibleType = (simpleTypeTransformToNewType == flexibleType.getLowerBound() && simpleTypeTransformToNewType2 == flexibleType.getUpperBound()) ? type : KotlinTypeFactory.flexibleType(simpleTypeTransformToNewType, simpleTypeTransformToNewType2);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleTypeFlexibleType, type);
    }
}
