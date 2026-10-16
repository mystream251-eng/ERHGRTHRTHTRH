package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin._Assertions;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: constantValues.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ArrayValue extends ConstantValue<List<? extends ConstantValue<?>>> {
    private final Function1<ModuleDescriptor, KotlinType> computeType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ArrayValue(List<? extends ConstantValue<?>> value, Function1<? super ModuleDescriptor, ? extends KotlinType> computeType) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(computeType, "computeType");
        this.computeType = computeType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(module, "module");
        KotlinType kotlinTypeInvoke = this.computeType.invoke(module);
        boolean z = KotlinBuiltIns.isArray(kotlinTypeInvoke) || KotlinBuiltIns.isPrimitiveArray(kotlinTypeInvoke);
        if (!_Assertions.ENABLED || z) {
            return kotlinTypeInvoke;
        }
        throw new AssertionError("Type should be an array, but was " + kotlinTypeInvoke + ": " + getValue());
    }
}
