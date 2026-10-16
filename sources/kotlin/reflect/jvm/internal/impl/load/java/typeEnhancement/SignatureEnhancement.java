package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.DeprecationCausedByFunctionN;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.AnnotationDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.NullDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.StringDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.ValueParameterData;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.JavaDescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureBuildingUtilsKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.deprecation.DeprecationKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

/* JADX INFO: compiled from: signatureEnhancement.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class SignatureEnhancement {
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;
    private final Jsr305State jsr305State;
    private final JavaTypeEnhancement typeEnhancement;

    public SignatureEnhancement(AnnotationTypeQualifierResolver annotationTypeQualifierResolver, Jsr305State jsr305State, JavaTypeEnhancement typeEnhancement) {
        Intrinsics.checkNotNullParameter(annotationTypeQualifierResolver, "annotationTypeQualifierResolver");
        Intrinsics.checkNotNullParameter(jsr305State, "jsr305State");
        Intrinsics.checkNotNullParameter(typeEnhancement, "typeEnhancement");
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver;
        this.jsr305State = jsr305State;
        this.typeEnhancement = typeEnhancement;
    }

    private final NullabilityQualifierWithMigrationStatus extractNullabilityTypeFromArgument(AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus;
        ConstantValue<?> constantValueFirstArgument = DescriptorUtilsKt.firstArgument(annotationDescriptor);
        if (!(constantValueFirstArgument instanceof EnumValue)) {
            constantValueFirstArgument = null;
        }
        EnumValue enumValue = (EnumValue) constantValueFirstArgument;
        if (enumValue == null) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        }
        String strAsString = enumValue.getEnumEntryName().asString();
        switch (strAsString) {
            case "MAYBE":
            case "NEVER":
                if (!strAsString.equals("MAYBE")) {
                    return null;
                }
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
                return nullabilityQualifierWithMigrationStatus;
            case "UNKNOWN":
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
                return nullabilityQualifierWithMigrationStatus;
            case "ALWAYS":
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
                return nullabilityQualifierWithMigrationStatus;
            default:
                return null;
        }
    }

    public final NullabilityQualifierWithMigrationStatus extractNullability(AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations2 = extractNullabilityFromKnownAnnotations(annotationDescriptor);
        if (nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations2 != null) {
            return nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations2;
        }
        AnnotationDescriptor annotationDescriptorResolveTypeQualifierAnnotation = this.annotationTypeQualifierResolver.resolveTypeQualifierAnnotation(annotationDescriptor);
        if (annotationDescriptorResolveTypeQualifierAnnotation == null) {
            return null;
        }
        ReportLevel reportLevelResolveJsr305AnnotationState = this.annotationTypeQualifierResolver.resolveJsr305AnnotationState(annotationDescriptor);
        if (reportLevelResolveJsr305AnnotationState.isIgnore() || (nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations = extractNullabilityFromKnownAnnotations(annotationDescriptorResolveTypeQualifierAnnotation)) == null) {
            return null;
        }
        return NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations, null, reportLevelResolveJsr305AnnotationState.isWarning(), 1, null);
    }

    private final NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations(AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus;
        FqName fqName = annotationDescriptor.getFqName();
        if (fqName == null) {
            return null;
        }
        if (JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS().contains(fqName)) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
        } else if (JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS().contains(fqName)) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION())) {
            nullabilityQualifierWithMigrationStatus = extractNullabilityTypeFromArgument(annotationDescriptor);
        } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NULLABLE_ANNOTATION()) && this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
        } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NONNULL_ANNOTATION()) && this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
        } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NON_NULL_ANNOTATION())) {
            nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, true);
        } else {
            nullabilityQualifierWithMigrationStatus = Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NULLABLE_ANNOTATION()) ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, true) : null;
        }
        if (nullabilityQualifierWithMigrationStatus != null) {
            return (!nullabilityQualifierWithMigrationStatus.isForWarningOnly() && (annotationDescriptor instanceof PossiblyExternalAnnotationDescriptor) && ((PossiblyExternalAnnotationDescriptor) annotationDescriptor).isIdeExternalAnnotation()) ? NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatus, null, true, 1, null) : nullabilityQualifierWithMigrationStatus;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <D extends CallableMemberDescriptor> Collection<D> enhanceSignatures(LazyJavaResolverContext c, Collection<? extends D> platformSignatures) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(platformSignatures, "platformSignatures");
        Collection<? extends D> collection = platformSignatures;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(enhanceSignature((CallableMemberDescriptor) it.next(), c));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:110:0x0216  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b8  */
    private final <D extends CallableMemberDescriptor> D enhanceSignature(D d, LazyJavaResolverContext lazyJavaResolverContext) {
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl;
        PartEnhancementResult partEnhancementResultEnhance$default;
        PredefinedFunctionEnhancementInfo predefinedFunctionEnhancementInfo;
        AnnotationTypeQualifierResolver.QualifierApplicabilityType qualifierApplicabilityType;
        boolean z;
        boolean z2;
        KotlinType type;
        List<TypeEnhancementInfo> parametersInfo;
        JavaPropertyDescriptor javaPropertyDescriptor;
        PropertyGetterDescriptorImpl getter;
        if (!(d instanceof JavaCallableMemberDescriptor)) {
            return d;
        }
        JavaCallableMemberDescriptor javaCallableMemberDescriptor = (JavaCallableMemberDescriptor) d;
        boolean z3 = true;
        if (javaCallableMemberDescriptor.getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
            CallableMemberDescriptor original = javaCallableMemberDescriptor.getOriginal();
            Intrinsics.checkNotNullExpressionValue(original, "original");
            if (original.getOverriddenDescriptors().size() == 1) {
                return d;
            }
        }
        LazyJavaResolverContext lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, d.getAnnotations());
        if (!(d instanceof JavaPropertyDescriptor) || (getter = (javaPropertyDescriptor = (JavaPropertyDescriptor) d).getGetter()) == null || getter.isDefault()) {
            propertyGetterDescriptorImpl = d;
        } else {
            PropertyGetterDescriptorImpl getter2 = javaPropertyDescriptor.getGetter();
            Intrinsics.checkNotNull(getter2);
            Intrinsics.checkNotNullExpressionValue(getter2, "getter!!");
            propertyGetterDescriptorImpl = getter2;
        }
        if (javaCallableMemberDescriptor.getExtensionReceiverParameter() != null) {
            FunctionDescriptor functionDescriptor = (FunctionDescriptor) (!(propertyGetterDescriptorImpl instanceof FunctionDescriptor) ? null : propertyGetterDescriptorImpl);
            partEnhancementResultEnhance$default = SignatureParts.enhance$default(partsForValueParameter(d, functionDescriptor != null ? (ValueParameterDescriptor) functionDescriptor.getUserData(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER) : null, lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers, new Function1<CallableMemberDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1
                @Override // kotlin.jvm.functions.Function1
                public final KotlinType invoke(CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ReceiverParameterDescriptor extensionReceiverParameter = it.getExtensionReceiverParameter();
                    Intrinsics.checkNotNull(extensionReceiverParameter);
                    Intrinsics.checkNotNullExpressionValue(extensionReceiverParameter, "it.extensionReceiverParameter!!");
                    KotlinType type2 = extensionReceiverParameter.getType();
                    Intrinsics.checkNotNullExpressionValue(type2, "it.extensionReceiverParameter!!.type");
                    return type2;
                }
            }), null, 1, null);
        } else {
            partEnhancementResultEnhance$default = null;
        }
        JavaMethodDescriptor javaMethodDescriptor = (JavaMethodDescriptor) (!(d instanceof JavaMethodDescriptor) ? null : d);
        if (javaMethodDescriptor != null) {
            SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
            DeclarationDescriptor containingDeclaration = javaMethodDescriptor.getContainingDeclaration();
            if (containingDeclaration == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            String strSignature = MethodSignatureBuildingUtilsKt.signature(signatureBuildingComponents, (ClassDescriptor) containingDeclaration, MethodSignatureMappingKt.computeJvmDescriptor$default(javaMethodDescriptor, false, false, 3, null));
            if (strSignature != null) {
                predefinedFunctionEnhancementInfo = PredefinedEnhancementInfoKt.getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE().get(strSignature);
            } else {
                predefinedFunctionEnhancementInfo = null;
            }
        } else {
            predefinedFunctionEnhancementInfo = null;
        }
        if (predefinedFunctionEnhancementInfo != null) {
            boolean z4 = predefinedFunctionEnhancementInfo.getParametersInfo().size() == javaCallableMemberDescriptor.getValueParameters().size();
            if (_Assertions.ENABLED && !z4) {
                throw new AssertionError("Predefined enhancement info for " + d + " has " + predefinedFunctionEnhancementInfo.getParametersInfo().size() + ", but " + javaCallableMemberDescriptor.getValueParameters().size() + " expected");
            }
        }
        List<ValueParameterDescriptor> valueParameters = propertyGetterDescriptorImpl.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "annotationOwnerForMember.valueParameters");
        List<ValueParameterDescriptor> list = valueParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (final ValueParameterDescriptor p : list) {
            PartEnhancementResult partEnhancementResultEnhance = partsForValueParameter(d, p, lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers, new Function1<CallableMemberDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$enhancementResult$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final KotlinType invoke(CallableMemberDescriptor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ValueParameterDescriptor valueParameterDescriptor = it.getValueParameters().get(p.getIndex());
                    Intrinsics.checkNotNullExpressionValue(valueParameterDescriptor, "it.valueParameters[p.index]");
                    KotlinType type2 = valueParameterDescriptor.getType();
                    Intrinsics.checkNotNullExpressionValue(type2, "it.valueParameters[p.index].type");
                    return type2;
                }
            }).enhance((predefinedFunctionEnhancementInfo == null || (parametersInfo = predefinedFunctionEnhancementInfo.getParametersInfo()) == null) ? null : (TypeEnhancementInfo) CollectionsKt.getOrNull(parametersInfo, p.getIndex()));
            if (partEnhancementResultEnhance.getWereChanges()) {
                type = partEnhancementResultEnhance.getType();
            } else {
                Intrinsics.checkNotNullExpressionValue(p, "p");
                type = p.getType();
                Intrinsics.checkNotNullExpressionValue(type, "p.type");
            }
            Intrinsics.checkNotNullExpressionValue(p, "p");
            boolean zHasDefaultValueInAnnotation = hasDefaultValueInAnnotation(p, type);
            arrayList.add(new ValueParameterEnhancementResult(partEnhancementResultEnhance.getType(), zHasDefaultValueInAnnotation, partEnhancementResultEnhance.getWereChanges() || zHasDefaultValueInAnnotation != p.declaresDefaultValue(), partEnhancementResultEnhance.getContainsFunctionN()));
        }
        ArrayList arrayList2 = arrayList;
        CallableMemberDescriptor callableMemberDescriptor = propertyGetterDescriptorImpl;
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) (!(d instanceof PropertyDescriptor) ? null : d);
        if (propertyDescriptor != null && JavaDescriptorUtilKt.isJavaField(propertyDescriptor)) {
            qualifierApplicabilityType = AnnotationTypeQualifierResolver.QualifierApplicabilityType.FIELD;
        } else {
            qualifierApplicabilityType = AnnotationTypeQualifierResolver.QualifierApplicabilityType.METHOD_RETURN_TYPE;
        }
        PartEnhancementResult partEnhancementResultEnhance2 = parts(d, callableMemberDescriptor, true, lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers, qualifierApplicabilityType, new Function1<CallableMemberDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceSignature$returnTypeEnhancement$1
            @Override // kotlin.jvm.functions.Function1
            public final KotlinType invoke(CallableMemberDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                KotlinType returnType = it.getReturnType();
                Intrinsics.checkNotNull(returnType);
                return returnType;
            }
        }).enhance(predefinedFunctionEnhancementInfo != null ? predefinedFunctionEnhancementInfo.getReturnTypeInfo() : null);
        if ((partEnhancementResultEnhance$default == null || !partEnhancementResultEnhance$default.getContainsFunctionN()) && !partEnhancementResultEnhance2.getContainsFunctionN()) {
            ArrayList arrayList3 = arrayList2;
            if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
                Iterator it = arrayList3.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    if (((ValueParameterEnhancementResult) it.next()).getContainsFunctionN()) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
            }
            if (z) {
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            z2 = true;
        }
        if ((partEnhancementResultEnhance$default == null || !partEnhancementResultEnhance$default.getWereChanges()) && !partEnhancementResultEnhance2.getWereChanges()) {
            ArrayList arrayList4 = arrayList2;
            if (!(arrayList4 instanceof Collection) || !arrayList4.isEmpty()) {
                Iterator it2 = arrayList4.iterator();
                do {
                    if (!it2.hasNext()) {
                        z3 = false;
                        break;
                    }
                } while (!((ValueParameterEnhancementResult) it2.next()).getWereChanges());
            } else {
                z3 = false;
                break;
            }
            if (!z3 && !z2) {
                return d;
            }
        }
        Pair<CallableDescriptor.UserDataKey<?>, ?> pair = z2 ? TuplesKt.to(DeprecationKt.getDEPRECATED_FUNCTION_KEY(), new DeprecationCausedByFunctionN(d)) : null;
        KotlinType type2 = partEnhancementResultEnhance$default != null ? partEnhancementResultEnhance$default.getType() : null;
        ArrayList<ValueParameterEnhancementResult> arrayList5 = arrayList2;
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
        for (ValueParameterEnhancementResult valueParameterEnhancementResult : arrayList5) {
            arrayList6.add(new ValueParameterData(valueParameterEnhancementResult.getType(), valueParameterEnhancementResult.getHasDefaultValue()));
        }
        JavaCallableMemberDescriptor javaCallableMemberDescriptorEnhance = javaCallableMemberDescriptor.enhance(type2, arrayList6, partEnhancementResultEnhance2.getType(), pair);
        if (javaCallableMemberDescriptorEnhance != null) {
            return javaCallableMemberDescriptorEnhance;
        }
        throw new NullPointerException("null cannot be cast to non-null type D");
    }

    private final boolean hasDefaultValueInAnnotation(ValueParameterDescriptor valueParameterDescriptor, KotlinType kotlinType) {
        boolean zDeclaresDefaultValue;
        AnnotationDefaultValue defaultValueFromAnnotation = UtilKt.getDefaultValueFromAnnotation(valueParameterDescriptor);
        if (defaultValueFromAnnotation instanceof StringDefaultValue) {
            zDeclaresDefaultValue = UtilsKt.lexicalCastFrom(kotlinType, ((StringDefaultValue) defaultValueFromAnnotation).getValue()) != null;
        } else if (Intrinsics.areEqual(defaultValueFromAnnotation, NullDefaultValue.INSTANCE)) {
            zDeclaresDefaultValue = TypeUtils.acceptsNullable(kotlinType);
        } else if (defaultValueFromAnnotation == null) {
            zDeclaresDefaultValue = valueParameterDescriptor.declaresDefaultValue();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return zDeclaresDefaultValue && valueParameterDescriptor.getOverriddenDescriptors().isEmpty();
    }

    /* JADX INFO: compiled from: signatureEnhancement.kt */
    private final class SignatureParts {
        private final AnnotationTypeQualifierResolver.QualifierApplicabilityType containerApplicabilityType;
        private final LazyJavaResolverContext containerContext;
        private final Collection<KotlinType> fromOverridden;
        private final KotlinType fromOverride;
        private final boolean isCovariant;
        final /* synthetic */ SignatureEnhancement this$0;
        private final Annotated typeContainer;

        /* JADX WARN: Multi-variable type inference failed */
        public SignatureParts(SignatureEnhancement signatureEnhancement, Annotated annotated, KotlinType fromOverride, Collection<? extends KotlinType> fromOverridden, boolean z, LazyJavaResolverContext containerContext, AnnotationTypeQualifierResolver.QualifierApplicabilityType containerApplicabilityType) {
            Intrinsics.checkNotNullParameter(fromOverride, "fromOverride");
            Intrinsics.checkNotNullParameter(fromOverridden, "fromOverridden");
            Intrinsics.checkNotNullParameter(containerContext, "containerContext");
            Intrinsics.checkNotNullParameter(containerApplicabilityType, "containerApplicabilityType");
            this.this$0 = signatureEnhancement;
            this.typeContainer = annotated;
            this.fromOverride = fromOverride;
            this.fromOverridden = fromOverridden;
            this.isCovariant = z;
            this.containerContext = containerContext;
            this.containerApplicabilityType = containerApplicabilityType;
        }

        private final boolean isForVarargParameter() {
            Annotated annotated = this.typeContainer;
            if (!(annotated instanceof ValueParameterDescriptor)) {
                annotated = null;
            }
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) annotated;
            return (valueParameterDescriptor != null ? valueParameterDescriptor.getVarargElementType() : null) != null;
        }

        public static /* synthetic */ PartEnhancementResult enhance$default(SignatureParts signatureParts, TypeEnhancementInfo typeEnhancementInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                typeEnhancementInfo = (TypeEnhancementInfo) null;
            }
            return signatureParts.enhance(typeEnhancementInfo);
        }

        public final PartEnhancementResult enhance(final TypeEnhancementInfo typeEnhancementInfo) {
            final Function1<Integer, JavaTypeQualifiers> function1ComputeIndexedQualifiersForOverride = computeIndexedQualifiersForOverride();
            Function1<Integer, JavaTypeQualifiers> function1 = typeEnhancementInfo != null ? new Function1<Integer, JavaTypeQualifiers>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ JavaTypeQualifiers invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final JavaTypeQualifiers invoke(int i) {
                    JavaTypeQualifiers javaTypeQualifiers = typeEnhancementInfo.getMap().get(Integer.valueOf(i));
                    return javaTypeQualifiers != null ? javaTypeQualifiers : (JavaTypeQualifiers) function1ComputeIndexedQualifiersForOverride.invoke(Integer.valueOf(i));
                }
            } : null;
            boolean zContains = TypeUtils.contains(this.fromOverride, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(UnwrappedType unwrappedType) {
                    ClassifierDescriptor classifierDescriptorMo1268getDeclarationDescriptor = unwrappedType.getConstructor().mo1268getDeclarationDescriptor();
                    boolean z = false;
                    if (classifierDescriptorMo1268getDeclarationDescriptor == null) {
                        return false;
                    }
                    Intrinsics.checkNotNullExpressionValue(classifierDescriptorMo1268getDeclarationDescriptor, "it.constructor.declarati… ?: return@contains false");
                    if (Intrinsics.areEqual(classifierDescriptorMo1268getDeclarationDescriptor.getName(), JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME().shortName()) && Intrinsics.areEqual(DescriptorUtilsKt.fqNameOrNull(classifierDescriptorMo1268getDeclarationDescriptor), JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME())) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            });
            JavaTypeEnhancement javaTypeEnhancement = this.this$0.typeEnhancement;
            KotlinType kotlinType = this.fromOverride;
            if (function1 != null) {
                function1ComputeIndexedQualifiersForOverride = function1;
            }
            KotlinType kotlinTypeEnhance = javaTypeEnhancement.enhance(kotlinType, function1ComputeIndexedQualifiersForOverride);
            if (kotlinTypeEnhance != null) {
                return new PartEnhancementResult(kotlinTypeEnhance, true, zContains);
            }
            return new PartEnhancementResult(this.fromOverride, false, zContains);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0048  */
        /* JADX WARN: Code duplicated, block: B:17:0x004b  */
        /* JADX WARN: Code duplicated, block: B:19:0x0051  */
        /* JADX WARN: Code duplicated, block: B:20:0x0054  */
        private final JavaTypeQualifiers extractQualifiers(KotlinType kotlinType) {
            Pair pair;
            NullabilityQualifier nullabilityQualifier;
            NullabilityQualifier nullabilityQualifier2;
            MutabilityQualifier mutabilityQualifier;
            if (FlexibleTypesKt.isFlexible(kotlinType)) {
                FlexibleType flexibleTypeAsFlexibleType = FlexibleTypesKt.asFlexibleType(kotlinType);
                pair = new Pair(flexibleTypeAsFlexibleType.getLowerBound(), flexibleTypeAsFlexibleType.getUpperBound());
            } else {
                pair = new Pair(kotlinType, kotlinType);
            }
            KotlinType kotlinType2 = (KotlinType) pair.component1();
            KotlinType kotlinType3 = (KotlinType) pair.component2();
            JavaToKotlinClassMapper javaToKotlinClassMapper = JavaToKotlinClassMapper.INSTANCE;
            if (!kotlinType2.isMarkedNullable()) {
                if (kotlinType3.isMarkedNullable()) {
                    nullabilityQualifier = null;
                } else {
                    nullabilityQualifier2 = NullabilityQualifier.NOT_NULL;
                }
                if (javaToKotlinClassMapper.isReadOnly(kotlinType2)) {
                    mutabilityQualifier = MutabilityQualifier.READ_ONLY;
                } else if (javaToKotlinClassMapper.isMutable(kotlinType3)) {
                    mutabilityQualifier = MutabilityQualifier.MUTABLE;
                } else {
                    mutabilityQualifier = null;
                }
                return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, kotlinType.unwrap() instanceof NotNullTypeParameter, false, 8, null);
            }
            nullabilityQualifier2 = NullabilityQualifier.NULLABLE;
            nullabilityQualifier = nullabilityQualifier2;
            if (javaToKotlinClassMapper.isReadOnly(kotlinType2)) {
                mutabilityQualifier = MutabilityQualifier.READ_ONLY;
            } else if (javaToKotlinClassMapper.isMutable(kotlinType3)) {
                mutabilityQualifier = MutabilityQualifier.MUTABLE;
            } else {
                mutabilityQualifier = null;
            }
            return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, kotlinType.unwrap() instanceof NotNullTypeParameter, false, 8, null);
        }

        private final JavaTypeQualifiers extractQualifiersFromAnnotations(KotlinType kotlinType, boolean z, JavaTypeQualifiers javaTypeQualifiers) {
            final Annotations annotations;
            Annotated annotated;
            if (z && (annotated = this.typeContainer) != null) {
                annotations = AnnotationsKt.composeAnnotations(annotated.getAnnotations(), kotlinType.getAnnotations());
            } else {
                annotations = kotlinType.getAnnotations();
            }
            Function2<List<? extends FqName>, T, T> function2 = new Function2<List<? extends FqName>, T, T>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1
                {
                    super(2);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(List<? extends FqName> list, Object obj) {
                    return invoke2((List<FqName>) list, obj);
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final <T> T invoke2(List<FqName> ifPresent, T qualifier) {
                    Intrinsics.checkNotNullParameter(ifPresent, "$this$ifPresent");
                    Intrinsics.checkNotNullParameter(qualifier, "qualifier");
                    List<FqName> list = ifPresent;
                    boolean z2 = true;
                    if ((list instanceof Collection) && list.isEmpty()) {
                        z2 = false;
                    } else {
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            if (annotations.mo1263findAnnotation((FqName) it.next()) != null) {
                            }
                        }
                        z2 = false;
                    }
                    if (z2) {
                        return qualifier;
                    }
                    return null;
                }
            };
            SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 = SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2.INSTANCE;
            if (z) {
                JavaTypeQualifiersByElementType defaultTypeQualifiers = this.containerContext.getDefaultTypeQualifiers();
                javaTypeQualifiers = defaultTypeQualifiers != null ? defaultTypeQualifiers.get(this.containerApplicabilityType) : null;
            }
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullability = extractNullability(annotations);
            if (nullabilityQualifierWithMigrationStatusExtractNullability == null) {
                nullabilityQualifierWithMigrationStatusExtractNullability = (javaTypeQualifiers == null || javaTypeQualifiers.getNullability() == null) ? null : new NullabilityQualifierWithMigrationStatus(javaTypeQualifiers.getNullability(), javaTypeQualifiers.isNullabilityQualifierForWarning());
            }
            NullabilityQualifier qualifier = nullabilityQualifierWithMigrationStatusExtractNullability != null ? nullabilityQualifierWithMigrationStatusExtractNullability.getQualifier() : null;
            MutabilityQualifier mutabilityQualifier = (MutabilityQualifier) signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2.invoke(function2.invoke2(JvmAnnotationNamesKt.getREAD_ONLY_ANNOTATIONS(), MutabilityQualifier.READ_ONLY), function2.invoke2(JvmAnnotationNamesKt.getMUTABLE_ANNOTATIONS(), MutabilityQualifier.MUTABLE));
            boolean z2 = false;
            boolean z3 = (nullabilityQualifierWithMigrationStatusExtractNullability != null ? nullabilityQualifierWithMigrationStatusExtractNullability.getQualifier() : null) == NullabilityQualifier.NOT_NULL && TypeUtilsKt.isTypeParameter(kotlinType);
            if (nullabilityQualifierWithMigrationStatusExtractNullability != null && nullabilityQualifierWithMigrationStatusExtractNullability.isForWarningOnly()) {
                z2 = true;
            }
            return new JavaTypeQualifiers(qualifier, mutabilityQualifier, z3, z2);
        }

        private final NullabilityQualifierWithMigrationStatus extractNullability(Annotations annotations) {
            SignatureEnhancement signatureEnhancement = this.this$0;
            Iterator<AnnotationDescriptor> it = annotations.iterator();
            while (it.hasNext()) {
                NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullability = signatureEnhancement.extractNullability(it.next());
                if (nullabilityQualifierWithMigrationStatusExtractNullability != null) {
                    return nullabilityQualifierWithMigrationStatusExtractNullability;
                }
            }
            return null;
        }

        /* JADX WARN: Code duplicated, block: B:21:0x006a  */
        private final Function1<Integer, JavaTypeQualifiers> computeIndexedQualifiersForOverride() {
            boolean z;
            boolean z2;
            Collection<KotlinType> collection = this.fromOverridden;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                arrayList.add(toIndexed((KotlinType) it.next()));
            }
            ArrayList arrayList2 = arrayList;
            List<TypeAndDefaultQualifiers> indexed = toIndexed(this.fromOverride);
            if (this.isCovariant) {
                Collection<KotlinType> collection2 = this.fromOverridden;
                if (!(collection2 instanceof Collection) || !collection2.isEmpty()) {
                    Iterator<T> it2 = collection2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z2 = false;
                            break;
                        }
                        if (!KotlinTypeChecker.DEFAULT.equalTypes((KotlinType) it2.next(), this.fromOverride)) {
                            z2 = true;
                            break;
                        }
                    }
                } else {
                    z2 = false;
                    break;
                }
                if (z2) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            int size = z ? 1 : indexed.size();
            final JavaTypeQualifiers[] javaTypeQualifiersArr = new JavaTypeQualifiers[size];
            int i = 0;
            while (i < size) {
                boolean z3 = i == 0;
                boolean z4 = z3 || !z;
                if (_Assertions.ENABLED && !z4) {
                    throw new AssertionError("Only head type constructors should be computed");
                }
                TypeAndDefaultQualifiers typeAndDefaultQualifiers = indexed.get(i);
                KotlinType kotlinTypeComponent1 = typeAndDefaultQualifiers.component1();
                JavaTypeQualifiers javaTypeQualifiersComponent2 = typeAndDefaultQualifiers.component2();
                ArrayList arrayList3 = new ArrayList();
                Iterator it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    TypeAndDefaultQualifiers typeAndDefaultQualifiers2 = (TypeAndDefaultQualifiers) CollectionsKt.getOrNull((List) it3.next(), i);
                    KotlinType type = typeAndDefaultQualifiers2 != null ? typeAndDefaultQualifiers2.getType() : null;
                    if (type != null) {
                        arrayList3.add(type);
                    }
                }
                javaTypeQualifiersArr[i] = computeQualifiersForOverride(kotlinTypeComponent1, arrayList3, javaTypeQualifiersComponent2, z3);
                i++;
            }
            return new Function1<Integer, JavaTypeQualifiers>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ JavaTypeQualifiers invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final JavaTypeQualifiers invoke(int i2) {
                    JavaTypeQualifiers[] javaTypeQualifiersArr2 = javaTypeQualifiersArr;
                    return (i2 < 0 || i2 > ArraysKt.getLastIndex(javaTypeQualifiersArr2)) ? JavaTypeQualifiers.Companion.getNONE() : javaTypeQualifiersArr2[i2];
                }
            };
        }

        private final List<TypeAndDefaultQualifiers> toIndexed(KotlinType kotlinType) {
            final ArrayList arrayList = new ArrayList(1);
            new Function2<KotlinType, LazyJavaResolverContext, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$toIndexed$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType2, LazyJavaResolverContext lazyJavaResolverContext) {
                    invoke2(kotlinType2, lazyJavaResolverContext);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KotlinType type, LazyJavaResolverContext ownerContext) {
                    Intrinsics.checkNotNullParameter(type, "type");
                    Intrinsics.checkNotNullParameter(ownerContext, "ownerContext");
                    LazyJavaResolverContext lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(ownerContext, type.getAnnotations());
                    ArrayList arrayList2 = arrayList;
                    JavaTypeQualifiersByElementType defaultTypeQualifiers = lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
                    arrayList2.add(new TypeAndDefaultQualifiers(type, defaultTypeQualifiers != null ? defaultTypeQualifiers.get(AnnotationTypeQualifierResolver.QualifierApplicabilityType.TYPE_USE) : null));
                    for (TypeProjection typeProjection : type.getArguments()) {
                        if (typeProjection.isStarProjection()) {
                            ArrayList arrayList3 = arrayList;
                            KotlinType type2 = typeProjection.getType();
                            Intrinsics.checkNotNullExpressionValue(type2, "arg.type");
                            arrayList3.add(new TypeAndDefaultQualifiers(type2, null));
                        } else {
                            KotlinType type3 = typeProjection.getType();
                            Intrinsics.checkNotNullExpressionValue(type3, "arg.type");
                            invoke2(type3, lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers);
                        }
                    }
                }
            }.invoke2(kotlinType, this.containerContext);
            return arrayList;
        }

        /* JADX WARN: Code duplicated, block: B:70:0x0138  */
        private final JavaTypeQualifiers computeQualifiersForOverride(KotlinType kotlinType, Collection<? extends KotlinType> collection, JavaTypeQualifiers javaTypeQualifiers, boolean z) {
            boolean z2;
            boolean z3;
            Collection<? extends KotlinType> collection2 = collection;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
            Iterator<T> it = collection2.iterator();
            while (it.hasNext()) {
                arrayList.add(extractQualifiers((KotlinType) it.next()));
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                MutabilityQualifier mutability = ((JavaTypeQualifiers) it2.next()).getMutability();
                if (mutability != null) {
                    arrayList3.add(mutability);
                }
            }
            Set set = CollectionsKt.toSet(arrayList3);
            ArrayList arrayList4 = new ArrayList();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                NullabilityQualifier nullability = ((JavaTypeQualifiers) it3.next()).getNullability();
                if (nullability != null) {
                    arrayList4.add(nullability);
                }
            }
            Set set2 = CollectionsKt.toSet(arrayList4);
            ArrayList arrayList5 = new ArrayList();
            Iterator<T> it4 = collection2.iterator();
            while (it4.hasNext()) {
                NullabilityQualifier nullability2 = extractQualifiers(TypeWithEnhancementKt.unwrapEnhancement((KotlinType) it4.next())).getNullability();
                if (nullability2 != null) {
                    arrayList5.add(nullability2);
                }
            }
            Set set3 = CollectionsKt.toSet(arrayList5);
            JavaTypeQualifiers javaTypeQualifiersExtractQualifiersFromAnnotations = extractQualifiersFromAnnotations(kotlinType, z, javaTypeQualifiers);
            NullabilityQualifier nullabilityQualifier = null;
            JavaTypeQualifiers javaTypeQualifiers2 = javaTypeQualifiersExtractQualifiersFromAnnotations.isNullabilityQualifierForWarning() ^ true ? javaTypeQualifiersExtractQualifiersFromAnnotations : null;
            NullabilityQualifier nullability3 = javaTypeQualifiers2 != null ? javaTypeQualifiers2.getNullability() : null;
            NullabilityQualifier nullability4 = javaTypeQualifiersExtractQualifiersFromAnnotations.getNullability();
            boolean z4 = this.isCovariant && z;
            NullabilityQualifier nullabilityQualifierSelect = SignatureEnhancementKt.select(set2, nullability3, z4);
            if (nullabilityQualifierSelect != null) {
                if (!(isForVarargParameter() && z && nullabilityQualifierSelect == NullabilityQualifier.NULLABLE)) {
                    nullabilityQualifier = nullabilityQualifierSelect;
                }
            }
            MutabilityQualifier mutabilityQualifier = (MutabilityQualifier) SignatureEnhancementKt.select(set, MutabilityQualifier.MUTABLE, MutabilityQualifier.READ_ONLY, javaTypeQualifiersExtractQualifiersFromAnnotations.getMutability(), z4);
            boolean z5 = nullability4 != nullability3 || (Intrinsics.areEqual(set3, set2) ^ true);
            if (javaTypeQualifiersExtractQualifiersFromAnnotations.isNotNullTypeParameter()) {
                z2 = true;
            } else {
                if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                    Iterator it5 = arrayList2.iterator();
                    while (true) {
                        if (!it5.hasNext()) {
                            z3 = false;
                            break;
                        }
                        if (((JavaTypeQualifiers) it5.next()).isNotNullTypeParameter()) {
                            z3 = true;
                            break;
                        }
                    }
                } else {
                    z3 = false;
                    break;
                }
                if (z3) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            if (nullabilityQualifier == null && z5) {
                return SignatureEnhancementKt.createJavaTypeQualifiers(SignatureEnhancementKt.select(set3, nullability4, z4), mutabilityQualifier, true, z2);
            }
            return SignatureEnhancementKt.createJavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, nullabilityQualifier == null, z2);
        }
    }

    /* JADX INFO: compiled from: signatureEnhancement.kt */
    private static class PartEnhancementResult {
        private final boolean containsFunctionN;
        private final KotlinType type;
        private final boolean wereChanges;

        public PartEnhancementResult(KotlinType type, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
            this.wereChanges = z;
            this.containsFunctionN = z2;
        }

        public final KotlinType getType() {
            return this.type;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }

        public final boolean getContainsFunctionN() {
            return this.containsFunctionN;
        }
    }

    /* JADX INFO: compiled from: signatureEnhancement.kt */
    private static final class ValueParameterEnhancementResult extends PartEnhancementResult {
        private final boolean hasDefaultValue;

        public final boolean getHasDefaultValue() {
            return this.hasDefaultValue;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValueParameterEnhancementResult(KotlinType type, boolean z, boolean z2, boolean z3) {
            super(type, z2, z3);
            Intrinsics.checkNotNullParameter(type, "type");
            this.hasDefaultValue = z;
        }
    }

    private final SignatureParts partsForValueParameter(CallableMemberDescriptor callableMemberDescriptor, ValueParameterDescriptor valueParameterDescriptor, LazyJavaResolverContext lazyJavaResolverContext, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        LazyJavaResolverContext lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers;
        return parts(callableMemberDescriptor, valueParameterDescriptor, false, (valueParameterDescriptor == null || (lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, valueParameterDescriptor.getAnnotations())) == null) ? lazyJavaResolverContext : lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers, AnnotationTypeQualifierResolver.QualifierApplicabilityType.VALUE_PARAMETER, function1);
    }

    private final SignatureParts parts(CallableMemberDescriptor callableMemberDescriptor, Annotated annotated, boolean z, LazyJavaResolverContext lazyJavaResolverContext, AnnotationTypeQualifierResolver.QualifierApplicabilityType qualifierApplicabilityType, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        KotlinType kotlinTypeInvoke = function1.invoke(callableMemberDescriptor);
        Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "this.overriddenDescriptors");
        Collection<? extends CallableMemberDescriptor> collection = overriddenDescriptors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (CallableMemberDescriptor it : collection) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            arrayList.add(function1.invoke(it));
        }
        return new SignatureParts(this, annotated, kotlinTypeInvoke, arrayList, z, ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, function1.invoke(callableMemberDescriptor).getAnnotations()), qualifierApplicabilityType);
    }
}
