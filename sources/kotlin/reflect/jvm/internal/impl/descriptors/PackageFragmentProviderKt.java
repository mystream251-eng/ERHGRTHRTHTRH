package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: PackageFragmentProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class PackageFragmentProviderKt {
    public static final List<PackageFragmentDescriptor> packageFragments(PackageFragmentProvider packageFragments, FqName fqName) {
        Intrinsics.checkNotNullParameter(packageFragments, "$this$packageFragments");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        ArrayList arrayList = new ArrayList();
        collectPackageFragmentsOptimizedIfPossible(packageFragments, fqName, arrayList);
        return arrayList;
    }

    public static final void collectPackageFragmentsOptimizedIfPossible(PackageFragmentProvider collectPackageFragmentsOptimizedIfPossible, FqName fqName, Collection<PackageFragmentDescriptor> packageFragments) {
        Intrinsics.checkNotNullParameter(collectPackageFragmentsOptimizedIfPossible, "$this$collectPackageFragmentsOptimizedIfPossible");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(packageFragments, "packageFragments");
        if (collectPackageFragmentsOptimizedIfPossible instanceof PackageFragmentProviderOptimized) {
            ((PackageFragmentProviderOptimized) collectPackageFragmentsOptimizedIfPossible).collectPackageFragments(fqName, packageFragments);
        } else {
            packageFragments.addAll(collectPackageFragmentsOptimizedIfPossible.getPackageFragments(fqName));
        }
    }
}
