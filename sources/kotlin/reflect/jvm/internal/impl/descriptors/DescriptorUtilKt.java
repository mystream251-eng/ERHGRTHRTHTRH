package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* JADX INFO: compiled from: descriptorUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class DescriptorUtilKt {
    public static final ClassDescriptor resolveClassByFqName(ModuleDescriptor resolveClassByFqName, FqName fqName, LookupLocation lookupLocation) {
        ClassifierDescriptor contributedClassifier;
        MemberScope unsubstitutedInnerClassesScope;
        Intrinsics.checkNotNullParameter(resolveClassByFqName, "$this$resolveClassByFqName");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(lookupLocation, "lookupLocation");
        if (fqName.isRoot()) {
            return null;
        }
        FqName fqNameParent = fqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "fqName.parent()");
        MemberScope memberScope = resolveClassByFqName.getPackage(fqNameParent).getMemberScope();
        Name nameShortName = fqName.shortName();
        Intrinsics.checkNotNullExpressionValue(nameShortName, "fqName.shortName()");
        ClassifierDescriptor contributedClassifier2 = memberScope.mo1270getContributedClassifier(nameShortName, lookupLocation);
        if (!(contributedClassifier2 instanceof ClassDescriptor)) {
            contributedClassifier2 = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier2;
        if (classDescriptor != null) {
            return classDescriptor;
        }
        FqName fqNameParent2 = fqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent2, "fqName.parent()");
        ClassDescriptor classDescriptorResolveClassByFqName = resolveClassByFqName(resolveClassByFqName, fqNameParent2, lookupLocation);
        if (classDescriptorResolveClassByFqName == null || (unsubstitutedInnerClassesScope = classDescriptorResolveClassByFqName.getUnsubstitutedInnerClassesScope()) == null) {
            contributedClassifier = null;
        } else {
            Name nameShortName2 = fqName.shortName();
            Intrinsics.checkNotNullExpressionValue(nameShortName2, "fqName.shortName()");
            contributedClassifier = unsubstitutedInnerClassesScope.mo1270getContributedClassifier(nameShortName2, lookupLocation);
        }
        return (ClassDescriptor) (contributedClassifier instanceof ClassDescriptor ? contributedClassifier : null);
    }
}
