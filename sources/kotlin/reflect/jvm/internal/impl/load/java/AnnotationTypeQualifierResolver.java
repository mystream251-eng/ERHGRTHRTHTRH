package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

/* JADX INFO: compiled from: AnnotationTypeQualifierResolver.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class AnnotationTypeQualifierResolver {
    private final boolean disabled;
    private final Jsr305State jsr305State;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    /* JADX INFO: compiled from: AnnotationTypeQualifierResolver.kt */
    public enum QualifierApplicabilityType {
        METHOD_RETURN_TYPE,
        VALUE_PARAMETER,
        FIELD,
        TYPE_USE
    }

    public AnnotationTypeQualifierResolver(StorageManager storageManager, Jsr305State jsr305State) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(jsr305State, "jsr305State");
        this.jsr305State = jsr305State;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
        this.disabled = this.jsr305State.getDisabled();
    }

    /* JADX INFO: compiled from: AnnotationTypeQualifierResolver.kt */
    public static final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public TypeQualifierWithApplicability(AnnotationDescriptor typeQualifier, int i) {
            Intrinsics.checkNotNullParameter(typeQualifier, "typeQualifier");
            this.typeQualifier = typeQualifier;
            this.applicability = i;
        }

        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        public final List<QualifierApplicabilityType> component2() {
            QualifierApplicabilityType[] qualifierApplicabilityTypeArrValues = QualifierApplicabilityType.values();
            ArrayList arrayList = new ArrayList();
            for (QualifierApplicabilityType qualifierApplicabilityType : qualifierApplicabilityTypeArrValues) {
                if (isApplicableTo(qualifierApplicabilityType)) {
                    arrayList.add(qualifierApplicabilityType);
                }
            }
            return arrayList;
        }

        private final boolean isApplicableTo(QualifierApplicabilityType qualifierApplicabilityType) {
            return isApplicableConsideringMask(QualifierApplicabilityType.TYPE_USE) || isApplicableConsideringMask(qualifierApplicabilityType);
        }

        private final boolean isApplicableConsideringMask(QualifierApplicabilityType qualifierApplicabilityType) {
            return ((1 << qualifierApplicabilityType.ordinal()) & this.applicability) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (!classDescriptor.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_NICKNAME_FQNAME())) {
            return null;
        }
        Iterator<AnnotationDescriptor> it = classDescriptor.getAnnotations().iterator();
        while (it.hasNext()) {
            AnnotationDescriptor annotationDescriptorResolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(it.next());
            if (annotationDescriptorResolveTypeQualifierAnnotation != null) {
                return annotationDescriptorResolveTypeQualifierAnnotation;
            }
        }
        return null;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return this.resolvedNicknames.invoke(classDescriptor);
    }

    public final AnnotationDescriptor resolveTypeQualifierAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled() || (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) == null) {
            return null;
        }
        return AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass) ? annotationDescriptor : resolveTypeQualifierNickname(annotationClass);
    }

    public final NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = AnnotationTypeQualifierResolverKt.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(annotationDescriptor.getFqName());
        if (nullabilityQualifierWithApplicability == null) {
            return (NullabilityQualifierWithApplicability) null;
        }
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusComponent1 = nullabilityQualifierWithApplicability.component1();
        Collection<QualifierApplicabilityType> collectionComponent2 = nullabilityQualifierWithApplicability.component2();
        ReportLevel reportLevelResolveJsr305AnnotationState = resolveJsr305AnnotationState(annotationDescriptor);
        if (!(reportLevelResolveJsr305AnnotationState != ReportLevel.IGNORE)) {
            reportLevelResolveJsr305AnnotationState = null;
        }
        if (reportLevelResolveJsr305AnnotationState != null) {
            return new NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusComponent1, null, reportLevelResolveJsr305AnnotationState.isWarning(), 1, null), collectionComponent2);
        }
        return null;
    }

    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        AnnotationDescriptor next;
        List<QualifierApplicabilityType> listEmptyList;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        if (!this.jsr305State.getDisabled() && (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) != null) {
            if (!annotationClass.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME())) {
                annotationClass = null;
            }
            if (annotationClass != null) {
                ClassDescriptor annotationClass2 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
                Intrinsics.checkNotNull(annotationClass2);
                AnnotationDescriptor annotationDescriptorMo1263findAnnotation = annotationClass2.getAnnotations().mo1263findAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME());
                Intrinsics.checkNotNull(annotationDescriptorMo1263findAnnotation);
                Map<Name, ConstantValue<?>> allValueArguments = annotationDescriptorMo1263findAnnotation.getAllValueArguments();
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<Name, ConstantValue<?>> entry : allValueArguments.entrySet()) {
                    Name key = entry.getKey();
                    ConstantValue<?> value = entry.getValue();
                    if (Intrinsics.areEqual(key, JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                        listEmptyList = mapConstantToQualifierApplicabilityTypes(value);
                    } else {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    CollectionsKt.addAll(arrayList, listEmptyList);
                }
                Iterator it = arrayList.iterator();
                int iOrdinal = 0;
                while (it.hasNext()) {
                    iOrdinal |= 1 << ((QualifierApplicabilityType) it.next()).ordinal();
                }
                Iterator<AnnotationDescriptor> it2 = annotationClass.getAnnotations().iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                } while (!(resolveTypeQualifierAnnotation(next) != null));
                AnnotationDescriptor annotationDescriptor2 = next;
                if (annotationDescriptor2 != null) {
                    return new TypeQualifierWithApplicability(annotationDescriptor2, iOrdinal);
                }
            }
        }
        return null;
    }

    public final ReportLevel resolveJsr305AnnotationState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        ReportLevel reportLevelResolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        return reportLevelResolveJsr305CustomState != null ? reportLevelResolveJsr305CustomState : this.jsr305State.getGlobal();
    }

    public final ReportLevel resolveJsr305CustomState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        Map<String, ReportLevel> user = this.jsr305State.getUser();
        FqName fqName = annotationDescriptor.getFqName();
        ReportLevel reportLevel = user.get(fqName != null ? fqName.asString() : null);
        if (reportLevel != null) {
            return reportLevel;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            return migrationAnnotationStatus(annotationClass);
        }
        return null;
    }

    private final ReportLevel migrationAnnotationStatus(ClassDescriptor classDescriptor) {
        AnnotationDescriptor annotationDescriptorMo1263findAnnotation = classDescriptor.getAnnotations().mo1263findAnnotation(AnnotationTypeQualifierResolverKt.getMIGRATION_ANNOTATION_FQNAME());
        ConstantValue<?> constantValueFirstArgument = annotationDescriptorMo1263findAnnotation != null ? DescriptorUtilsKt.firstArgument(annotationDescriptorMo1263findAnnotation) : null;
        if (!(constantValueFirstArgument instanceof EnumValue)) {
            constantValueFirstArgument = null;
        }
        EnumValue enumValue = (EnumValue) constantValueFirstArgument;
        if (enumValue == null) {
            return null;
        }
        ReportLevel migration = this.jsr305State.getMigration();
        if (migration != null) {
            return migration;
        }
        String strAsString = enumValue.getEnumEntryName().asString();
        int iHashCode = strAsString.hashCode();
        if (iHashCode == -2137067054) {
            if (strAsString.equals("IGNORE")) {
                return ReportLevel.IGNORE;
            }
            return null;
        }
        if (iHashCode == -1838656823) {
            if (strAsString.equals("STRICT")) {
                return ReportLevel.STRICT;
            }
            return null;
        }
        if (iHashCode == 2656902 && strAsString.equals("WARN")) {
            return ReportLevel.WARN;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:26:0x0072  */
    private final List<QualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue) {
        QualifierApplicabilityType qualifierApplicabilityType;
        if (!(constantValue instanceof ArrayValue)) {
            if (constantValue instanceof EnumValue) {
                switch (((EnumValue) constantValue).getEnumEntryName().getIdentifier()) {
                    case "METHOD":
                        qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                        break;
                    case "FIELD":
                        qualifierApplicabilityType = QualifierApplicabilityType.FIELD;
                        break;
                    case "TYPE_USE":
                        qualifierApplicabilityType = QualifierApplicabilityType.TYPE_USE;
                        break;
                    case "PARAMETER":
                        qualifierApplicabilityType = QualifierApplicabilityType.VALUE_PARAMETER;
                        break;
                    default:
                        qualifierApplicabilityType = null;
                        break;
                }
                return CollectionsKt.listOfNotNull(qualifierApplicabilityType);
            }
            return CollectionsKt.emptyList();
        }
        List<? extends ConstantValue<?>> value = ((ArrayValue) constantValue).getValue();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = value.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, mapConstantToQualifierApplicabilityTypes((ConstantValue) it.next()));
        }
        return arrayList;
    }

    public final boolean getDisabled() {
        return this.disabled;
    }
}
