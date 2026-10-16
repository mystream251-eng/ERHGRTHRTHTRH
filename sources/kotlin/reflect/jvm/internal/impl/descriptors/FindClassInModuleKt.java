package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ResolutionAnchorProviderKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: findClassInModule.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class FindClassInModuleKt {
    public static final ClassDescriptor findClassAcrossModuleDependencies(ModuleDescriptor findClassAcrossModuleDependencies, ClassId classId) {
        Intrinsics.checkNotNullParameter(findClassAcrossModuleDependencies, "$this$findClassAcrossModuleDependencies");
        Intrinsics.checkNotNullParameter(classId, "classId");
        ClassifierDescriptor classifierDescriptorFindClassifierAcrossModuleDependencies = findClassifierAcrossModuleDependencies(findClassAcrossModuleDependencies, classId);
        if (!(classifierDescriptorFindClassifierAcrossModuleDependencies instanceof ClassDescriptor)) {
            classifierDescriptorFindClassifierAcrossModuleDependencies = null;
        }
        return (ClassDescriptor) classifierDescriptorFindClassifierAcrossModuleDependencies;
    }

    public static final ClassDescriptor findNonGenericClassAcrossDependencies(ModuleDescriptor findNonGenericClassAcrossDependencies, ClassId classId, NotFoundClasses notFoundClasses) {
        Intrinsics.checkNotNullParameter(findNonGenericClassAcrossDependencies, "$this$findNonGenericClassAcrossDependencies");
        Intrinsics.checkNotNullParameter(classId, "classId");
        Intrinsics.checkNotNullParameter(notFoundClasses, "notFoundClasses");
        ClassDescriptor classDescriptorFindClassAcrossModuleDependencies = findClassAcrossModuleDependencies(findNonGenericClassAcrossDependencies, classId);
        return classDescriptorFindClassAcrossModuleDependencies != null ? classDescriptorFindClassAcrossModuleDependencies : notFoundClasses.getClass(classId, SequencesKt.toList(SequencesKt.map(SequencesKt.generateSequence(classId, FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$1.INSTANCE), new Function1<ClassId, Integer>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(ClassId it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return 0;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(ClassId classId2) {
                return Integer.valueOf(invoke2(classId2));
            }
        })));
    }

    public static final TypeAliasDescriptor findTypeAliasAcrossModuleDependencies(ModuleDescriptor findTypeAliasAcrossModuleDependencies, ClassId classId) {
        Intrinsics.checkNotNullParameter(findTypeAliasAcrossModuleDependencies, "$this$findTypeAliasAcrossModuleDependencies");
        Intrinsics.checkNotNullParameter(classId, "classId");
        ClassifierDescriptor classifierDescriptorFindClassifierAcrossModuleDependencies = findClassifierAcrossModuleDependencies(findTypeAliasAcrossModuleDependencies, classId);
        if (!(classifierDescriptorFindClassifierAcrossModuleDependencies instanceof TypeAliasDescriptor)) {
            classifierDescriptorFindClassifierAcrossModuleDependencies = null;
        }
        return (TypeAliasDescriptor) classifierDescriptorFindClassifierAcrossModuleDependencies;
    }

    public static final ClassifierDescriptor findClassifierAcrossModuleDependencies(ModuleDescriptor findClassifierAcrossModuleDependencies, ClassId classId) {
        ClassDescriptor contributedClassifier;
        Intrinsics.checkNotNullParameter(findClassifierAcrossModuleDependencies, "$this$findClassifierAcrossModuleDependencies");
        Intrinsics.checkNotNullParameter(classId, "classId");
        ModuleDescriptor resolutionAnchorIfAny = ResolutionAnchorProviderKt.getResolutionAnchorIfAny(findClassifierAcrossModuleDependencies);
        if (resolutionAnchorIfAny == null) {
            FqName packageFqName = classId.getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(packageFqName, "classId.packageFqName");
            PackageViewDescriptor packageViewDescriptor = findClassifierAcrossModuleDependencies.getPackage(packageFqName);
            List<Name> listPathSegments = classId.getRelativeClassName().pathSegments();
            Intrinsics.checkNotNullExpressionValue(listPathSegments, "classId.relativeClassName.pathSegments()");
            MemberScope memberScope = packageViewDescriptor.getMemberScope();
            Object objFirst = CollectionsKt.first((List<? extends Object>) listPathSegments);
            Intrinsics.checkNotNullExpressionValue(objFirst, "segments.first()");
            contributedClassifier = memberScope.mo1270getContributedClassifier((Name) objFirst, NoLookupLocation.FROM_DESERIALIZATION);
            if (contributedClassifier == null) {
                return null;
            }
            for (Name name : listPathSegments.subList(1, listPathSegments.size())) {
                if (!(contributedClassifier instanceof ClassDescriptor)) {
                    return null;
                }
                MemberScope unsubstitutedInnerClassesScope = ((ClassDescriptor) contributedClassifier).getUnsubstitutedInnerClassesScope();
                Intrinsics.checkNotNullExpressionValue(name, "name");
                ClassifierDescriptor contributedClassifier2 = unsubstitutedInnerClassesScope.mo1270getContributedClassifier(name, NoLookupLocation.FROM_DESERIALIZATION);
                if (!(contributedClassifier2 instanceof ClassDescriptor)) {
                    contributedClassifier2 = null;
                }
                ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier2;
                if (classDescriptor == null) {
                    return null;
                }
                contributedClassifier = classDescriptor;
            }
        } else {
            FqName packageFqName2 = classId.getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(packageFqName2, "classId.packageFqName");
            PackageViewDescriptor packageViewDescriptor2 = resolutionAnchorIfAny.getPackage(packageFqName2);
            List<Name> listPathSegments2 = classId.getRelativeClassName().pathSegments();
            Intrinsics.checkNotNullExpressionValue(listPathSegments2, "classId.relativeClassName.pathSegments()");
            MemberScope memberScope2 = packageViewDescriptor2.getMemberScope();
            Object objFirst2 = CollectionsKt.first((List<? extends Object>) listPathSegments2);
            Intrinsics.checkNotNullExpressionValue(objFirst2, "segments.first()");
            ClassDescriptor contributedClassifier3 = memberScope2.mo1270getContributedClassifier((Name) objFirst2, NoLookupLocation.FROM_DESERIALIZATION);
            if (contributedClassifier3 == null) {
                contributedClassifier3 = null;
                break;
            }
            for (Name name2 : listPathSegments2.subList(1, listPathSegments2.size())) {
                if (contributedClassifier3 instanceof ClassDescriptor) {
                    MemberScope unsubstitutedInnerClassesScope2 = ((ClassDescriptor) contributedClassifier3).getUnsubstitutedInnerClassesScope();
                    Intrinsics.checkNotNullExpressionValue(name2, "name");
                    ClassifierDescriptor contributedClassifier4 = unsubstitutedInnerClassesScope2.mo1270getContributedClassifier(name2, NoLookupLocation.FROM_DESERIALIZATION);
                    if (!(contributedClassifier4 instanceof ClassDescriptor)) {
                        contributedClassifier4 = null;
                    }
                    ClassDescriptor classDescriptor2 = (ClassDescriptor) contributedClassifier4;
                    if (classDescriptor2 != null) {
                        contributedClassifier3 = classDescriptor2;
                    }
                }
                contributedClassifier3 = null;
            }
            if (contributedClassifier3 != null) {
                return contributedClassifier3;
            }
            FqName packageFqName3 = classId.getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(packageFqName3, "classId.packageFqName");
            PackageViewDescriptor packageViewDescriptor3 = findClassifierAcrossModuleDependencies.getPackage(packageFqName3);
            List<Name> listPathSegments3 = classId.getRelativeClassName().pathSegments();
            Intrinsics.checkNotNullExpressionValue(listPathSegments3, "classId.relativeClassName.pathSegments()");
            MemberScope memberScope3 = packageViewDescriptor3.getMemberScope();
            Object objFirst3 = CollectionsKt.first((List<? extends Object>) listPathSegments3);
            Intrinsics.checkNotNullExpressionValue(objFirst3, "segments.first()");
            contributedClassifier = memberScope3.mo1270getContributedClassifier((Name) objFirst3, NoLookupLocation.FROM_DESERIALIZATION);
            if (contributedClassifier == null) {
                return null;
            }
            for (Name name3 : listPathSegments3.subList(1, listPathSegments3.size())) {
                if (!(contributedClassifier instanceof ClassDescriptor)) {
                    return null;
                }
                MemberScope unsubstitutedInnerClassesScope3 = ((ClassDescriptor) contributedClassifier).getUnsubstitutedInnerClassesScope();
                Intrinsics.checkNotNullExpressionValue(name3, "name");
                ClassifierDescriptor contributedClassifier5 = unsubstitutedInnerClassesScope3.mo1270getContributedClassifier(name3, NoLookupLocation.FROM_DESERIALIZATION);
                if (!(contributedClassifier5 instanceof ClassDescriptor)) {
                    contributedClassifier5 = null;
                }
                ClassDescriptor classDescriptor3 = (ClassDescriptor) contributedClassifier5;
                if (classDescriptor3 == null) {
                    return null;
                }
                contributedClassifier = classDescriptor3;
            }
        }
        return contributedClassifier;
    }
}
