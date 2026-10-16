package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;

/* JADX INFO: compiled from: SpecialTypes.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class SpecialTypesKt {
    public static final AbbreviatedType getAbbreviatedType(KotlinType getAbbreviatedType) {
        Intrinsics.checkNotNullParameter(getAbbreviatedType, "$this$getAbbreviatedType");
        UnwrappedType unwrappedTypeUnwrap = getAbbreviatedType.unwrap();
        if (!(unwrappedTypeUnwrap instanceof AbbreviatedType)) {
            unwrappedTypeUnwrap = null;
        }
        return (AbbreviatedType) unwrappedTypeUnwrap;
    }

    public static final SimpleType getAbbreviation(KotlinType getAbbreviation) {
        Intrinsics.checkNotNullParameter(getAbbreviation, "$this$getAbbreviation");
        AbbreviatedType abbreviatedType = getAbbreviatedType(getAbbreviation);
        if (abbreviatedType != null) {
            return abbreviatedType.getAbbreviation();
        }
        return null;
    }

    public static final SimpleType withAbbreviation(SimpleType withAbbreviation, SimpleType abbreviatedType) {
        Intrinsics.checkNotNullParameter(withAbbreviation, "$this$withAbbreviation");
        Intrinsics.checkNotNullParameter(abbreviatedType, "abbreviatedType");
        return KotlinTypeKt.isError(withAbbreviation) ? withAbbreviation : new AbbreviatedType(withAbbreviation, abbreviatedType);
    }

    public static final boolean isDefinitelyNotNullType(KotlinType isDefinitelyNotNullType) {
        Intrinsics.checkNotNullParameter(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
        return isDefinitelyNotNullType.unwrap() instanceof DefinitelyNotNullType;
    }

    public static /* synthetic */ SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull$default(SimpleType simpleType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType, z);
    }

    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull, boolean z) {
        Intrinsics.checkNotNullParameter(makeSimpleTypeDefinitelyNotNullOrNotNull, "$this$makeSimpleTypeDefinitelyNotNullOrNotNull");
        DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(makeSimpleTypeDefinitelyNotNullOrNotNull, z);
        DefinitelyNotNullType definitelyNotNullTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull = definitelyNotNullTypeMakeDefinitelyNotNull$descriptors != null ? definitelyNotNullTypeMakeDefinitelyNotNull$descriptors : makeIntersectionTypeDefinitelyNotNullOrNotNull(makeSimpleTypeDefinitelyNotNullOrNotNull);
        return definitelyNotNullTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull != null ? definitelyNotNullTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull : makeSimpleTypeDefinitelyNotNullOrNotNull.makeNullableAsSpecified(false);
    }

    public static final NewCapturedType withNotNullProjection(NewCapturedType withNotNullProjection) {
        Intrinsics.checkNotNullParameter(withNotNullProjection, "$this$withNotNullProjection");
        return new NewCapturedType(withNotNullProjection.getCaptureStatus(), withNotNullProjection.getConstructor(), withNotNullProjection.getLowerType(), withNotNullProjection.getAnnotations(), withNotNullProjection.isMarkedNullable(), true);
    }

    public static /* synthetic */ UnwrappedType makeDefinitelyNotNullOrNotNull$default(UnwrappedType unwrappedType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return makeDefinitelyNotNullOrNotNull(unwrappedType, z);
    }

    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(UnwrappedType makeDefinitelyNotNullOrNotNull, boolean z) {
        Intrinsics.checkNotNullParameter(makeDefinitelyNotNullOrNotNull, "$this$makeDefinitelyNotNullOrNotNull");
        DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(makeDefinitelyNotNullOrNotNull, z);
        DefinitelyNotNullType definitelyNotNullTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull = definitelyNotNullTypeMakeDefinitelyNotNull$descriptors != null ? definitelyNotNullTypeMakeDefinitelyNotNull$descriptors : makeIntersectionTypeDefinitelyNotNullOrNotNull(makeDefinitelyNotNullOrNotNull);
        return definitelyNotNullTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull != null ? definitelyNotNullTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull : makeDefinitelyNotNullOrNotNull.makeNullableAsSpecified(false);
    }

    private static final SimpleType makeIntersectionTypeDefinitelyNotNullOrNotNull(KotlinType kotlinType) {
        IntersectionTypeConstructor intersectionTypeConstructorMakeDefinitelyNotNullOrNotNull;
        TypeConstructor constructor = kotlinType.getConstructor();
        if (!(constructor instanceof IntersectionTypeConstructor)) {
            constructor = null;
        }
        IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
        if (intersectionTypeConstructor == null || (intersectionTypeConstructorMakeDefinitelyNotNullOrNotNull = makeDefinitelyNotNullOrNotNull(intersectionTypeConstructor)) == null) {
            return null;
        }
        return intersectionTypeConstructorMakeDefinitelyNotNullOrNotNull.createType();
    }

    private static final IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull(IntersectionTypeConstructor intersectionTypeConstructor) {
        KotlinType kotlinType;
        Collection<KotlinType> collectionMo1269getSupertypes = intersectionTypeConstructor.mo1269getSupertypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionMo1269getSupertypes, 10));
        Iterator<T> it = collectionMo1269getSupertypes.iterator();
        boolean z = false;
        while (true) {
            kotlinType = null;
            if (!it.hasNext()) {
                break;
            }
            UnwrappedType unwrappedTypeMakeDefinitelyNotNullOrNotNull$default = (KotlinType) it.next();
            if (TypeUtils.isNullableType(unwrappedTypeMakeDefinitelyNotNullOrNotNull$default)) {
                unwrappedTypeMakeDefinitelyNotNullOrNotNull$default = makeDefinitelyNotNullOrNotNull$default(unwrappedTypeMakeDefinitelyNotNullOrNotNull$default.unwrap(), false, 1, null);
                z = true;
            }
            arrayList.add(unwrappedTypeMakeDefinitelyNotNullOrNotNull$default);
        }
        ArrayList arrayList2 = arrayList;
        if (!z) {
            return null;
        }
        UnwrappedType alternativeType = intersectionTypeConstructor.getAlternativeType();
        if (alternativeType != null) {
            if (TypeUtils.isNullableType(alternativeType)) {
                alternativeType = makeDefinitelyNotNullOrNotNull$default(alternativeType.unwrap(), false, 1, null);
            }
            kotlinType = alternativeType;
        }
        return new IntersectionTypeConstructor(arrayList2).setAlternative(kotlinType);
    }
}
