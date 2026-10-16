package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import kotlin._Assertions;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: compiled from: TypeAliasConstructorDescriptor.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeAliasConstructorDescriptorImpl extends FunctionDescriptorImpl implements TypeAliasConstructorDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TypeAliasConstructorDescriptorImpl.class), "withDispatchReceiver", "getWithDispatchReceiver()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;"))};
    public static final Companion Companion = new Companion(null);
    private final StorageManager storageManager;
    private final TypeAliasDescriptor typeAliasDescriptor;
    private ClassConstructorDescriptor underlyingConstructorDescriptor;
    private final NullableLazyValue withDispatchReceiver$delegate;

    public /* synthetic */ TypeAliasConstructorDescriptorImpl(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, ClassConstructorDescriptor classConstructorDescriptor, TypeAliasConstructorDescriptor typeAliasConstructorDescriptor, Annotations annotations, CallableMemberDescriptor.Kind kind, SourceElement sourceElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(storageManager, typeAliasDescriptor, classConstructorDescriptor, typeAliasConstructorDescriptor, annotations, kind, sourceElement);
    }

    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    public TypeAliasDescriptor getTypeAliasDescriptor() {
        return this.typeAliasDescriptor;
    }

    private TypeAliasConstructorDescriptorImpl(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, final ClassConstructorDescriptor classConstructorDescriptor, TypeAliasConstructorDescriptor typeAliasConstructorDescriptor, Annotations annotations, CallableMemberDescriptor.Kind kind, SourceElement sourceElement) {
        super(typeAliasDescriptor, typeAliasConstructorDescriptor, annotations, Name.special("<init>"), kind, sourceElement);
        this.storageManager = storageManager;
        this.typeAliasDescriptor = typeAliasDescriptor;
        setActual(getTypeAliasDescriptor().isActual());
        this.withDispatchReceiver$delegate = this.storageManager.createNullableLazyValue(new Function0<TypeAliasConstructorDescriptorImpl>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptorImpl$withDispatchReceiver$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final TypeAliasConstructorDescriptorImpl invoke() {
                StorageManager storageManager2 = this.this$0.getStorageManager();
                TypeAliasDescriptor typeAliasDescriptor2 = this.this$0.getTypeAliasDescriptor();
                ClassConstructorDescriptor classConstructorDescriptor2 = classConstructorDescriptor;
                TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = this.this$0;
                Annotations annotations2 = classConstructorDescriptor2.getAnnotations();
                CallableMemberDescriptor.Kind kind2 = classConstructorDescriptor.getKind();
                Intrinsics.checkNotNullExpressionValue(kind2, "underlyingConstructorDescriptor.kind");
                SourceElement source = this.this$0.getTypeAliasDescriptor().getSource();
                Intrinsics.checkNotNullExpressionValue(source, "typeAliasDescriptor.source");
                TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl2 = new TypeAliasConstructorDescriptorImpl(storageManager2, typeAliasDescriptor2, classConstructorDescriptor2, typeAliasConstructorDescriptorImpl, annotations2, kind2, source, null);
                TypeSubstitutor typeSubstitutorForUnderlyingClass = TypeAliasConstructorDescriptorImpl.Companion.getTypeSubstitutorForUnderlyingClass(this.this$0.getTypeAliasDescriptor());
                if (typeSubstitutorForUnderlyingClass == null) {
                    return null;
                }
                ReceiverParameterDescriptor dispatchReceiverParameter = classConstructorDescriptor.getDispatchReceiverParameter();
                typeAliasConstructorDescriptorImpl2.initialize(null, dispatchReceiverParameter != null ? dispatchReceiverParameter.substitute(typeSubstitutorForUnderlyingClass) : null, this.this$0.getTypeAliasDescriptor().getDeclaredTypeParameters(), this.this$0.getValueParameters(), this.this$0.getReturnType(), Modality.FINAL, this.this$0.getTypeAliasDescriptor().getVisibility());
                return typeAliasConstructorDescriptorImpl2;
            }
        });
        this.underlyingConstructorDescriptor = classConstructorDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor
    public ClassConstructorDescriptor getUnderlyingConstructorDescriptor() {
        return this.underlyingConstructorDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public boolean isPrimary() {
        return getUnderlyingConstructorDescriptor().isPrimary();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public TypeAliasDescriptor getContainingDeclaration() {
        return getTypeAliasDescriptor();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public ClassDescriptor getConstructedClass() {
        ClassDescriptor constructedClass = getUnderlyingConstructorDescriptor().getConstructedClass();
        Intrinsics.checkNotNullExpressionValue(constructedClass, "underlyingConstructorDescriptor.constructedClass");
        return constructedClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public KotlinType getReturnType() {
        KotlinType returnType = super.getReturnType();
        Intrinsics.checkNotNull(returnType);
        return returnType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public TypeAliasConstructorDescriptor getOriginal() {
        FunctionDescriptor original = super.getOriginal();
        if (original != null) {
            return (TypeAliasConstructorDescriptor) original;
        }
        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public TypeAliasConstructorDescriptor substitute(TypeSubstitutor substitutor) {
        Intrinsics.checkNotNullParameter(substitutor, "substitutor");
        FunctionDescriptor functionDescriptorSubstitute = super.substitute(substitutor);
        if (functionDescriptorSubstitute == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptorImpl");
        }
        TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = (TypeAliasConstructorDescriptorImpl) functionDescriptorSubstitute;
        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(typeAliasConstructorDescriptorImpl.getReturnType());
        Intrinsics.checkNotNullExpressionValue(typeSubstitutorCreate, "TypeSubstitutor.create(s…asConstructor.returnType)");
        ClassConstructorDescriptor classConstructorDescriptorSubstitute = getUnderlyingConstructorDescriptor().getOriginal().substitute(typeSubstitutorCreate);
        if (classConstructorDescriptorSubstitute == null) {
            return null;
        }
        typeAliasConstructorDescriptorImpl.underlyingConstructorDescriptor = classConstructorDescriptorSubstitute;
        return typeAliasConstructorDescriptorImpl;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public TypeAliasConstructorDescriptor copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean z) {
        Intrinsics.checkNotNullParameter(newOwner, "newOwner");
        Intrinsics.checkNotNullParameter(modality, "modality");
        Intrinsics.checkNotNullParameter(visibility, "visibility");
        Intrinsics.checkNotNullParameter(kind, "kind");
        FunctionDescriptor functionDescriptorBuild = newCopyBuilder().setOwner(newOwner).setModality(modality).setVisibility(visibility).setKind(kind).setCopyOverrides(z).build();
        if (functionDescriptorBuild != null) {
            return (TypeAliasConstructorDescriptor) functionDescriptorBuild;
        }
        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public TypeAliasConstructorDescriptorImpl createSubstitutedCopy(DeclarationDescriptor newOwner, FunctionDescriptor functionDescriptor, CallableMemberDescriptor.Kind kind, Name name, Annotations annotations, SourceElement source) {
        Intrinsics.checkNotNullParameter(newOwner, "newOwner");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(source, "source");
        boolean z = kind == CallableMemberDescriptor.Kind.DECLARATION || kind == CallableMemberDescriptor.Kind.SYNTHESIZED;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Creating a type alias constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + newOwner + "\nkind: " + kind);
        }
        boolean z2 = name == null;
        if (!_Assertions.ENABLED || z2) {
            return new TypeAliasConstructorDescriptorImpl(this.storageManager, getTypeAliasDescriptor(), getUnderlyingConstructorDescriptor(), this, annotations, CallableMemberDescriptor.Kind.DECLARATION, source);
        }
        throw new AssertionError("Renaming type alias constructor: " + this);
    }

    /* JADX INFO: compiled from: TypeAliasConstructorDescriptor.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final TypeSubstitutor getTypeSubstitutorForUnderlyingClass(TypeAliasDescriptor typeAliasDescriptor) {
            if (typeAliasDescriptor.getClassDescriptor() == null) {
                return null;
            }
            return TypeSubstitutor.create(typeAliasDescriptor.getExpandedType());
        }

        public final TypeAliasConstructorDescriptor createIfAvailable(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, ClassConstructorDescriptor constructor) {
            ClassConstructorDescriptor classConstructorDescriptorSubstitute;
            Intrinsics.checkNotNullParameter(storageManager, "storageManager");
            Intrinsics.checkNotNullParameter(typeAliasDescriptor, "typeAliasDescriptor");
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            TypeSubstitutor typeSubstitutorForUnderlyingClass = getTypeSubstitutorForUnderlyingClass(typeAliasDescriptor);
            ReceiverParameterDescriptor receiverParameterDescriptorCreateExtensionReceiverParameterForCallable = null;
            if (typeSubstitutorForUnderlyingClass != null && (classConstructorDescriptorSubstitute = constructor.substitute(typeSubstitutorForUnderlyingClass)) != null) {
                Annotations annotations = constructor.getAnnotations();
                CallableMemberDescriptor.Kind kind = constructor.getKind();
                Intrinsics.checkNotNullExpressionValue(kind, "constructor.kind");
                SourceElement source = typeAliasDescriptor.getSource();
                Intrinsics.checkNotNullExpressionValue(source, "typeAliasDescriptor.source");
                TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = new TypeAliasConstructorDescriptorImpl(storageManager, typeAliasDescriptor, classConstructorDescriptorSubstitute, null, annotations, kind, source, null);
                List<ValueParameterDescriptor> substitutedValueParameters = FunctionDescriptorImpl.getSubstitutedValueParameters(typeAliasConstructorDescriptorImpl, constructor.getValueParameters(), typeSubstitutorForUnderlyingClass);
                if (substitutedValueParameters != null) {
                    Intrinsics.checkNotNullExpressionValue(substitutedValueParameters, "FunctionDescriptorImpl.g…         ) ?: return null");
                    SimpleType simpleTypeLowerIfFlexible = FlexibleTypesKt.lowerIfFlexible(classConstructorDescriptorSubstitute.getReturnType().unwrap());
                    SimpleType defaultType = typeAliasDescriptor.getDefaultType();
                    Intrinsics.checkNotNullExpressionValue(defaultType, "typeAliasDescriptor.defaultType");
                    SimpleType simpleTypeWithAbbreviation = SpecialTypesKt.withAbbreviation(simpleTypeLowerIfFlexible, defaultType);
                    ReceiverParameterDescriptor it = constructor.getDispatchReceiverParameter();
                    if (it != null) {
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        receiverParameterDescriptorCreateExtensionReceiverParameterForCallable = DescriptorFactory.createExtensionReceiverParameterForCallable(typeAliasConstructorDescriptorImpl, typeSubstitutorForUnderlyingClass.safeSubstitute(it.getType(), Variance.INVARIANT), Annotations.Companion.getEMPTY());
                    }
                    typeAliasConstructorDescriptorImpl.initialize(receiverParameterDescriptorCreateExtensionReceiverParameterForCallable, null, typeAliasDescriptor.getDeclaredTypeParameters(), substitutedValueParameters, simpleTypeWithAbbreviation, Modality.FINAL, typeAliasDescriptor.getVisibility());
                    return typeAliasConstructorDescriptorImpl;
                }
            }
            return null;
        }
    }
}
