package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* JADX INFO: compiled from: AbstractTypeChecker.kt */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractTypeCheckerContext implements TypeSystemContext {
    private int argumentsDepth;
    private ArrayDeque<SimpleTypeMarker> supertypesDeque;
    private boolean supertypesLocked;
    private Set<SimpleTypeMarker> supertypesSet;

    /* JADX INFO: compiled from: AbstractTypeChecker.kt */
    public enum LowerCapturedTypePolicy {
        CHECK_ONLY_LOWER,
        CHECK_SUBTYPE_AND_LOWER,
        SKIP_LOWER
    }

    public Boolean addSubtypeConstraint(KotlinTypeMarker subType, KotlinTypeMarker superType, boolean z) {
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return null;
    }

    public boolean customIsSubtypeOf(KotlinTypeMarker subType, KotlinTypeMarker superType) {
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return true;
    }

    public abstract boolean isAllowedTypeVariable(KotlinTypeMarker kotlinTypeMarker);

    public abstract boolean isErrorTypeEqualsToAnything();

    public abstract boolean isStubTypeEqualsToAnything();

    public KotlinTypeMarker prepareType(KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type;
    }

    public KotlinTypeMarker refineType(KotlinTypeMarker type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type;
    }

    public abstract SupertypesPolicy substitutionSupertypePolicy(SimpleTypeMarker simpleTypeMarker);

    public List<SimpleTypeMarker> fastCorrespondingSupertypes(SimpleTypeMarker fastCorrespondingSupertypes, TypeConstructorMarker constructor) {
        Intrinsics.checkNotNullParameter(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        return TypeSystemContext.DefaultImpls.fastCorrespondingSupertypes(this, fastCorrespondingSupertypes, constructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker get(TypeArgumentListMarker get, int i) {
        Intrinsics.checkNotNullParameter(get, "$this$get");
        return TypeSystemContext.DefaultImpls.get(this, get, i);
    }

    public TypeArgumentMarker getArgumentOrNull(SimpleTypeMarker getArgumentOrNull, int i) {
        Intrinsics.checkNotNullParameter(getArgumentOrNull, "$this$getArgumentOrNull");
        return TypeSystemContext.DefaultImpls.getArgumentOrNull(this, getArgumentOrNull, i);
    }

    public boolean hasFlexibleNullability(KotlinTypeMarker hasFlexibleNullability) {
        Intrinsics.checkNotNullParameter(hasFlexibleNullability, "$this$hasFlexibleNullability");
        return TypeSystemContext.DefaultImpls.hasFlexibleNullability(this, hasFlexibleNullability);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext
    public boolean identicalArguments(SimpleTypeMarker a2, SimpleTypeMarker b) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return TypeSystemContext.DefaultImpls.identicalArguments(this, a2, b);
    }

    public boolean isClassType(SimpleTypeMarker isClassType) {
        Intrinsics.checkNotNullParameter(isClassType, "$this$isClassType");
        return TypeSystemContext.DefaultImpls.isClassType(this, isClassType);
    }

    public boolean isDefinitelyNotNullType(KotlinTypeMarker isDefinitelyNotNullType) {
        Intrinsics.checkNotNullParameter(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
        return TypeSystemContext.DefaultImpls.isDefinitelyNotNullType(this, isDefinitelyNotNullType);
    }

    public boolean isDynamic(KotlinTypeMarker isDynamic) {
        Intrinsics.checkNotNullParameter(isDynamic, "$this$isDynamic");
        return TypeSystemContext.DefaultImpls.isDynamic(this, isDynamic);
    }

    public boolean isIntegerLiteralType(SimpleTypeMarker isIntegerLiteralType) {
        Intrinsics.checkNotNullParameter(isIntegerLiteralType, "$this$isIntegerLiteralType");
        return TypeSystemContext.DefaultImpls.isIntegerLiteralType(this, isIntegerLiteralType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(KotlinTypeMarker isMarkedNullable) {
        Intrinsics.checkNotNullParameter(isMarkedNullable, "$this$isMarkedNullable");
        return TypeSystemContext.DefaultImpls.isMarkedNullable(this, isMarkedNullable);
    }

    public boolean isNothing(KotlinTypeMarker isNothing) {
        Intrinsics.checkNotNullParameter(isNothing, "$this$isNothing");
        return TypeSystemContext.DefaultImpls.isNothing(this, isNothing);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker lowerBoundIfFlexible(KotlinTypeMarker lowerBoundIfFlexible) {
        Intrinsics.checkNotNullParameter(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
        return TypeSystemContext.DefaultImpls.lowerBoundIfFlexible(this, lowerBoundIfFlexible);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int size(TypeArgumentListMarker size) {
        Intrinsics.checkNotNullParameter(size, "$this$size");
        return TypeSystemContext.DefaultImpls.size(this, size);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeConstructorMarker typeConstructor(KotlinTypeMarker typeConstructor) {
        Intrinsics.checkNotNullParameter(typeConstructor, "$this$typeConstructor");
        return TypeSystemContext.DefaultImpls.typeConstructor(this, typeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker upperBoundIfFlexible(KotlinTypeMarker upperBoundIfFlexible) {
        Intrinsics.checkNotNullParameter(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
        return TypeSystemContext.DefaultImpls.upperBoundIfFlexible(this, upperBoundIfFlexible);
    }

    public LowerCapturedTypePolicy getLowerCapturedTypePolicy(SimpleTypeMarker subType, CapturedTypeMarker superType) {
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER;
    }

    public static /* synthetic */ Boolean addSubtypeConstraint$default(AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addSubtypeConstraint");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return abstractTypeCheckerContext.addSubtypeConstraint(kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    public final ArrayDeque<SimpleTypeMarker> getSupertypesDeque() {
        return this.supertypesDeque;
    }

    public final Set<SimpleTypeMarker> getSupertypesSet() {
        return this.supertypesSet;
    }

    public final void initialize() {
        boolean z = !this.supertypesLocked;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        this.supertypesLocked = true;
        if (this.supertypesDeque == null) {
            this.supertypesDeque = new ArrayDeque<>(4);
        }
        if (this.supertypesSet == null) {
            this.supertypesSet = SmartSet.Companion.create();
        }
    }

    public final void clear() {
        ArrayDeque<SimpleTypeMarker> arrayDeque = this.supertypesDeque;
        Intrinsics.checkNotNull(arrayDeque);
        arrayDeque.clear();
        Set<SimpleTypeMarker> set = this.supertypesSet;
        Intrinsics.checkNotNull(set);
        set.clear();
        this.supertypesLocked = false;
    }

    /* JADX INFO: compiled from: AbstractTypeChecker.kt */
    public static abstract class SupertypesPolicy {
        /* JADX INFO: renamed from: transformType */
        public abstract SimpleTypeMarker mo1272transformType(AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker);

        private SupertypesPolicy() {
        }

        public /* synthetic */ SupertypesPolicy(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: AbstractTypeChecker.kt */
        public static final class None extends SupertypesPolicy {
            public static final None INSTANCE = new None();

            private None() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            /* JADX INFO: renamed from: transformType, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ SimpleTypeMarker mo1272transformType(AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker) {
                return (SimpleTypeMarker) transformType(abstractTypeCheckerContext, kotlinTypeMarker);
            }

            public Void transformType(AbstractTypeCheckerContext context, KotlinTypeMarker type) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(type, "type");
                throw new UnsupportedOperationException("Should not be called");
            }
        }

        /* JADX INFO: compiled from: AbstractTypeChecker.kt */
        public static final class UpperIfFlexible extends SupertypesPolicy {
            public static final UpperIfFlexible INSTANCE = new UpperIfFlexible();

            private UpperIfFlexible() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            /* JADX INFO: renamed from: transformType */
            public SimpleTypeMarker mo1272transformType(AbstractTypeCheckerContext context, KotlinTypeMarker type) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(type, "type");
                return context.upperBoundIfFlexible(type);
            }
        }

        /* JADX INFO: compiled from: AbstractTypeChecker.kt */
        public static final class LowerIfFlexible extends SupertypesPolicy {
            public static final LowerIfFlexible INSTANCE = new LowerIfFlexible();

            private LowerIfFlexible() {
                super(null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
            /* JADX INFO: renamed from: transformType */
            public SimpleTypeMarker mo1272transformType(AbstractTypeCheckerContext context, KotlinTypeMarker type) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(type, "type");
                return context.lowerBoundIfFlexible(type);
            }
        }

        /* JADX INFO: compiled from: AbstractTypeChecker.kt */
        public static abstract class DoCustomTransform extends SupertypesPolicy {
            public DoCustomTransform() {
                super(null);
            }
        }
    }
}
