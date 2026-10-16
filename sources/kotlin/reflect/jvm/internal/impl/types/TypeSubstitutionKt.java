package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

/* JADX INFO: compiled from: TypeSubstitution.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeSubstitutionKt {
    public static /* synthetic */ KotlinType replace$default(KotlinType kotlinType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = kotlinType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = kotlinType.getAnnotations();
        }
        return replace(kotlinType, (List<? extends TypeProjection>) list, annotations);
    }

    public static final KotlinType replace(KotlinType replace, List<? extends TypeProjection> newArguments, Annotations newAnnotations) {
        SimpleType simpleTypeReplace;
        Intrinsics.checkNotNullParameter(replace, "$this$replace");
        Intrinsics.checkNotNullParameter(newArguments, "newArguments");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        if ((newArguments.isEmpty() || newArguments == replace.getArguments()) && newAnnotations == replace.getAnnotations()) {
            return replace;
        }
        UnwrappedType unwrappedTypeUnwrap = replace.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedTypeUnwrap;
            simpleTypeReplace = KotlinTypeFactory.flexibleType(replace(flexibleType.getLowerBound(), newArguments, newAnnotations), replace(flexibleType.getUpperBound(), newArguments, newAnnotations));
        } else {
            if (!(unwrappedTypeUnwrap instanceof SimpleType)) {
                throw new NoWhenBranchMatchedException();
            }
            simpleTypeReplace = replace((SimpleType) unwrappedTypeUnwrap, newArguments, newAnnotations);
        }
        return simpleTypeReplace;
    }

    public static /* synthetic */ SimpleType replace$default(SimpleType simpleType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = simpleType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = simpleType.getAnnotations();
        }
        return replace(simpleType, (List<? extends TypeProjection>) list, annotations);
    }

    public static final SimpleType replace(SimpleType replace, List<? extends TypeProjection> newArguments, Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(replace, "$this$replace");
        Intrinsics.checkNotNullParameter(newArguments, "newArguments");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        if (newArguments.isEmpty() && newAnnotations == replace.getAnnotations()) {
            return replace;
        }
        if (newArguments.isEmpty()) {
            return replace.replaceAnnotations(newAnnotations);
        }
        return KotlinTypeFactory.simpleType$default(newAnnotations, replace.getConstructor(), newArguments, replace.isMarkedNullable(), null, 16, null);
    }

    public static final SimpleType asSimpleType(KotlinType asSimpleType) {
        Intrinsics.checkNotNullParameter(asSimpleType, "$this$asSimpleType");
        UnwrappedType unwrappedTypeUnwrap = asSimpleType.unwrap();
        if (!(unwrappedTypeUnwrap instanceof SimpleType)) {
            unwrappedTypeUnwrap = null;
        }
        SimpleType simpleType = (SimpleType) unwrappedTypeUnwrap;
        if (simpleType != null) {
            return simpleType;
        }
        throw new IllegalStateException(("This is should be simple type: " + asSimpleType).toString());
    }
}
