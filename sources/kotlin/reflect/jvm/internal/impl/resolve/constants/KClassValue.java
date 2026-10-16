package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: constantValues.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class KClassValue extends ConstantValue<Value> {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: compiled from: constantValues.kt */
    public static abstract class Value {

        /* JADX INFO: compiled from: constantValues.kt */
        public static final class NormalClass extends Value {
            private final ClassLiteralValue value;

            public boolean equals(Object obj) {
                if (this != obj) {
                    return (obj instanceof NormalClass) && Intrinsics.areEqual(this.value, ((NormalClass) obj).value);
                }
                return true;
            }

            public int hashCode() {
                ClassLiteralValue classLiteralValue = this.value;
                if (classLiteralValue != null) {
                    return classLiteralValue.hashCode();
                }
                return 0;
            }

            public String toString() {
                return "NormalClass(value=" + this.value + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NormalClass(ClassLiteralValue value) {
                super(null);
                Intrinsics.checkNotNullParameter(value, "value");
                this.value = value;
            }

            public final ClassLiteralValue getValue() {
                return this.value;
            }

            public final ClassId getClassId() {
                return this.value.getClassId();
            }

            public final int getArrayDimensions() {
                return this.value.getArrayNestedness();
            }
        }

        private Value() {
        }

        public /* synthetic */ Value(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: constantValues.kt */
        public static final class LocalClass extends Value {
            private final KotlinType type;

            public boolean equals(Object obj) {
                if (this != obj) {
                    return (obj instanceof LocalClass) && Intrinsics.areEqual(this.type, ((LocalClass) obj).type);
                }
                return true;
            }

            public int hashCode() {
                KotlinType kotlinType = this.type;
                if (kotlinType != null) {
                    return kotlinType.hashCode();
                }
                return 0;
            }

            public String toString() {
                return "LocalClass(type=" + this.type + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LocalClass(KotlinType type) {
                super(null);
                Intrinsics.checkNotNullParameter(type, "type");
                this.type = type;
            }

            public final KotlinType getType() {
                return this.type;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassValue(Value value) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KClassValue(ClassLiteralValue value) {
        this(new Value.NormalClass(value));
        Intrinsics.checkNotNullParameter(value, "value");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KClassValue(ClassId classId, int i) {
        this(new ClassLiteralValue(classId, i));
        Intrinsics.checkNotNullParameter(classId, "classId");
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(module, "module");
        Annotations empty = Annotations.Companion.getEMPTY();
        ClassDescriptor kClass = module.getBuiltIns().getKClass();
        Intrinsics.checkNotNullExpressionValue(kClass, "module.builtIns.kClass");
        return KotlinTypeFactory.simpleNotNullType(empty, kClass, CollectionsKt.listOf(new TypeProjectionImpl(getArgumentType(module))));
    }

    public final KotlinType getArgumentType(ModuleDescriptor module) {
        Intrinsics.checkNotNullParameter(module, "module");
        Value value = getValue();
        if (value instanceof Value.LocalClass) {
            return ((Value.LocalClass) getValue()).getType();
        }
        if (value instanceof Value.NormalClass) {
            ClassLiteralValue value2 = ((Value.NormalClass) getValue()).getValue();
            ClassId classIdComponent1 = value2.component1();
            int iComponent2 = value2.component2();
            ClassDescriptor classDescriptorFindClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(module, classIdComponent1);
            if (classDescriptorFindClassAcrossModuleDependencies == null) {
                SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType("Unresolved type: " + classIdComponent1 + " (arrayDimensions=" + iComponent2 + ')');
                Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorType, "ErrorUtils.createErrorTy…sions=$arrayDimensions)\")");
                return simpleTypeCreateErrorType;
            }
            SimpleType defaultType = classDescriptorFindClassAcrossModuleDependencies.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(defaultType, "descriptor.defaultType");
            SimpleType simpleTypeReplaceArgumentsWithStarProjections = TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
            for (int i = 0; i < iComponent2; i++) {
                SimpleType arrayType = module.getBuiltIns().getArrayType(Variance.INVARIANT, simpleTypeReplaceArgumentsWithStarProjections);
                Intrinsics.checkNotNullExpressionValue(arrayType, "module.builtIns.getArray…Variance.INVARIANT, type)");
                simpleTypeReplaceArgumentsWithStarProjections = arrayType;
            }
            return simpleTypeReplaceArgumentsWithStarProjections;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: compiled from: constantValues.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConstantValue<?> create(KotlinType argumentType) {
            Intrinsics.checkNotNullParameter(argumentType, "argumentType");
            KClassValue kClassValue = null;
            if (KotlinTypeKt.isError(argumentType)) {
                return null;
            }
            KotlinType type = argumentType;
            int i = 0;
            while (KotlinBuiltIns.isArray(type)) {
                type = ((TypeProjection) CollectionsKt.single((List) type.getArguments())).getType();
                Intrinsics.checkNotNullExpressionValue(type, "type.arguments.single().type");
                i++;
            }
            ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = type.getConstructor().mo1268getDeclarationDescriptor();
            if (classifierDescriptorMo1268getDeclarationDescriptor instanceof ClassDescriptor) {
                ClassId classId = DescriptorUtilsKt.getClassId(classifierDescriptorMo1268getDeclarationDescriptor);
                if (classId == null) {
                    return new KClassValue(new Value.LocalClass(argumentType));
                }
                kClassValue = new KClassValue(classId, i);
            } else if (classifierDescriptorMo1268getDeclarationDescriptor instanceof TypeParameterDescriptor) {
                ClassId classId2 = ClassId.topLevel(StandardNames.FqNames.any.toSafe());
                Intrinsics.checkNotNullExpressionValue(classId2, "ClassId.topLevel(Standar…mes.FqNames.any.toSafe())");
                kClassValue = new KClassValue(classId2, 0);
            }
            return kClassValue;
        }
    }
}
