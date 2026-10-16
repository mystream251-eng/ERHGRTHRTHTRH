package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: SpecialTypes.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class AbbreviatedType extends DelegatingSimpleType {
    private final SimpleType abbreviation;
    private final SimpleType delegate;

    public AbbreviatedType(SimpleType delegate, SimpleType abbreviation) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(abbreviation, "abbreviation");
        this.delegate = delegate;
        this.abbreviation = abbreviation;
    }

    public final SimpleType getAbbreviation() {
        return this.abbreviation;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    protected SimpleType getDelegate() {
        return this.delegate;
    }

    public final SimpleType getExpandedType() {
        return getDelegate();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public AbbreviatedType replaceAnnotations(Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return new AbbreviatedType(getDelegate().replaceAnnotations(newAnnotations), this.abbreviation);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public AbbreviatedType makeNullableAsSpecified(boolean z) {
        return new AbbreviatedType(getDelegate().makeNullableAsSpecified(z), this.abbreviation.makeNullableAsSpecified(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public AbbreviatedType replaceDelegate(SimpleType delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        return new AbbreviatedType(delegate, this.abbreviation);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    public AbbreviatedType refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        KotlinType kotlinTypeRefineType = kotlinTypeRefiner.refineType(getDelegate());
        if (kotlinTypeRefineType == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        }
        SimpleType simpleType = (SimpleType) kotlinTypeRefineType;
        KotlinType kotlinTypeRefineType2 = kotlinTypeRefiner.refineType(this.abbreviation);
        if (kotlinTypeRefineType2 != null) {
            return new AbbreviatedType(simpleType, (SimpleType) kotlinTypeRefineType2);
        }
        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }
}
