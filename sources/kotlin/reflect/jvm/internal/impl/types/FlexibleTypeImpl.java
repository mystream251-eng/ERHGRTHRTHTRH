package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: flexibleTypes.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class FlexibleTypeImpl extends FlexibleType implements CustomTypeVariable {
    public static final Companion Companion = new Companion(null);
    public static boolean RUN_SLOW_ASSERTIONS;
    private boolean assertionsDone;

    /* JADX INFO: compiled from: flexibleTypes.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlexibleTypeImpl(SimpleType lowerBound, SimpleType upperBound) {
        super(lowerBound, upperBound);
        Intrinsics.checkNotNullParameter(lowerBound, "lowerBound");
        Intrinsics.checkNotNullParameter(upperBound, "upperBound");
    }

    private final void runAssertions() {
        if (!RUN_SLOW_ASSERTIONS || this.assertionsDone) {
            return;
        }
        this.assertionsDone = true;
        boolean z = !FlexibleTypesKt.isFlexible(getLowerBound());
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Lower bound of a flexible type can not be flexible: " + getLowerBound());
        }
        boolean z2 = !FlexibleTypesKt.isFlexible(getUpperBound());
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("Upper bound of a flexible type can not be flexible: " + getUpperBound());
        }
        boolean zAreEqual = true ^ Intrinsics.areEqual(getLowerBound(), getUpperBound());
        if (_Assertions.ENABLED && !zAreEqual) {
            throw new AssertionError("Lower and upper bounds are equal: " + getLowerBound() + " == " + getUpperBound());
        }
        boolean zIsSubtypeOf = KotlinTypeChecker.DEFAULT.isSubtypeOf(getLowerBound(), getUpperBound());
        if (!_Assertions.ENABLED || zIsSubtypeOf) {
            return;
        }
        throw new AssertionError("Lower bound " + getLowerBound() + " of a flexible type must be a subtype of the upper bound " + getUpperBound());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public SimpleType getDelegate() {
        runAssertions();
        return getLowerBound();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.CustomTypeVariable
    public boolean isTypeVariable() {
        return (getLowerBound().getConstructor().mo1268getDeclarationDescriptor() instanceof TypeParameterDescriptor) && Intrinsics.areEqual(getLowerBound().getConstructor(), getUpperBound().getConstructor());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.CustomTypeVariable
    public KotlinType substitutionResult(KotlinType replacement) {
        UnwrappedType unwrappedTypeFlexibleType;
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        UnwrappedType unwrappedTypeUnwrap = replacement.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            unwrappedTypeFlexibleType = unwrappedTypeUnwrap;
        } else {
            if (!(unwrappedTypeUnwrap instanceof SimpleType)) {
                throw new NoWhenBranchMatchedException();
            }
            SimpleType simpleType = (SimpleType) unwrappedTypeUnwrap;
            unwrappedTypeFlexibleType = KotlinTypeFactory.flexibleType(simpleType, simpleType.makeNullableAsSpecified(true));
        }
        return TypeWithEnhancementKt.inheritEnhancement(unwrappedTypeFlexibleType, unwrappedTypeUnwrap);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAnnotations(Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return KotlinTypeFactory.flexibleType(getLowerBound().replaceAnnotations(newAnnotations), getUpperBound().replaceAnnotations(newAnnotations));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public String render(DescriptorRenderer renderer, DescriptorRendererOptions options) {
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        Intrinsics.checkNotNullParameter(options, "options");
        if (options.getDebugMode()) {
            return '(' + renderer.renderType(getLowerBound()) + ".." + renderer.renderType(getUpperBound()) + ')';
        }
        return renderer.renderFlexibleType(renderer.renderType(getLowerBound()), renderer.renderType(getUpperBound()), TypeUtilsKt.getBuiltIns(this));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return KotlinTypeFactory.flexibleType(getLowerBound().makeNullableAsSpecified(z), getUpperBound().makeNullableAsSpecified(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    public FlexibleType refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        KotlinType kotlinTypeRefineType = kotlinTypeRefiner.refineType(getLowerBound());
        if (kotlinTypeRefineType == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        }
        SimpleType simpleType = (SimpleType) kotlinTypeRefineType;
        KotlinType kotlinTypeRefineType2 = kotlinTypeRefiner.refineType(getUpperBound());
        if (kotlinTypeRefineType2 != null) {
            return new FlexibleTypeImpl(simpleType, (SimpleType) kotlinTypeRefineType2);
        }
        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }
}
