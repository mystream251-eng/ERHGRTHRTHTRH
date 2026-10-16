package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

/* JADX INFO: compiled from: TypeSubstitution.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class IndexedParametersSubstitution extends TypeSubstitution {
    private final boolean approximateCapturedTypes;
    private final TypeProjection[] arguments;
    private final TypeParameterDescriptor[] parameters;

    public final TypeParameterDescriptor[] getParameters() {
        return this.parameters;
    }

    public final TypeProjection[] getArguments() {
        return this.arguments;
    }

    public /* synthetic */ IndexedParametersSubstitution(TypeParameterDescriptor[] typeParameterDescriptorArr, TypeProjection[] typeProjectionArr, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeParameterDescriptorArr, typeProjectionArr, (i & 4) != 0 ? false : z);
    }

    public IndexedParametersSubstitution(TypeParameterDescriptor[] parameters, TypeProjection[] arguments, boolean z) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.parameters = parameters;
        this.arguments = arguments;
        this.approximateCapturedTypes = z;
        boolean z2 = parameters.length <= arguments.length;
        if (!_Assertions.ENABLED || z2) {
            return;
        }
        throw new AssertionError("Number of arguments should not be less than number of parameters, but: parameters=" + this.parameters.length + ", args=" + this.arguments.length);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public IndexedParametersSubstitution(List<? extends TypeParameterDescriptor> parameters, List<? extends TypeProjection> argumentsList) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(argumentsList, "argumentsList");
        Object[] array = parameters.toArray(new TypeParameterDescriptor[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        TypeParameterDescriptor[] typeParameterDescriptorArr = (TypeParameterDescriptor[]) array;
        Object[] array2 = argumentsList.toArray(new TypeProjection[0]);
        if (array2 != null) {
            this(typeParameterDescriptorArr, (TypeProjection[]) array2, false, 4, null);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return this.arguments.length == 0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean approximateContravariantCapturedTypes() {
        return this.approximateCapturedTypes;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    /* JADX INFO: renamed from: get */
    public TypeProjection mo1273get(KotlinType key) {
        Intrinsics.checkNotNullParameter(key, "key");
        ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = key.getConstructor().mo1268getDeclarationDescriptor();
        if (!(classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
            classifierDescriptorMo1268getDeclarationDescriptor = null;
        }
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) classifierDescriptorMo1268getDeclarationDescriptor;
        if (typeParameterDescriptor != null) {
            int index = typeParameterDescriptor.getIndex();
            TypeParameterDescriptor[] typeParameterDescriptorArr = this.parameters;
            if (index < typeParameterDescriptorArr.length && Intrinsics.areEqual(typeParameterDescriptorArr[index].getTypeConstructor(), typeParameterDescriptor.getTypeConstructor())) {
                return this.arguments[index];
            }
        }
        return null;
    }
}
