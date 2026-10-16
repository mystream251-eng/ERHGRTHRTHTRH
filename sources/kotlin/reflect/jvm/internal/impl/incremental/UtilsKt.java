package kotlin.reflect.jvm.internal.impl.incremental;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LocationInfo;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.incremental.components.Position;
import kotlin.reflect.jvm.internal.impl.incremental.components.ScopeKind;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class UtilsKt {
    public static final void record(LookupTracker record, LookupLocation from, ClassDescriptor scopeOwner, Name name) {
        LocationInfo location;
        Intrinsics.checkNotNullParameter(record, "$this$record");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(scopeOwner, "scopeOwner");
        Intrinsics.checkNotNullParameter(name, "name");
        if (record == LookupTracker.DO_NOTHING.INSTANCE || (location = from.getLocation()) == null) {
            return;
        }
        Position position = record.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION();
        String filePath = location.getFilePath();
        String strAsString = DescriptorUtils.getFqName(scopeOwner).asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "DescriptorUtils.getFqName(scopeOwner).asString()");
        ScopeKind scopeKind = ScopeKind.CLASSIFIER;
        String strAsString2 = name.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "name.asString()");
        record.record(filePath, position, strAsString, scopeKind, strAsString2);
    }

    public static final void record(LookupTracker record, LookupLocation from, PackageFragmentDescriptor scopeOwner, Name name) {
        Intrinsics.checkNotNullParameter(record, "$this$record");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(scopeOwner, "scopeOwner");
        Intrinsics.checkNotNullParameter(name, "name");
        String strAsString = scopeOwner.getFqName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "scopeOwner.fqName.asString()");
        String strAsString2 = name.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "name.asString()");
        recordPackageLookup(record, from, strAsString, strAsString2);
    }

    public static final void recordPackageLookup(LookupTracker recordPackageLookup, LookupLocation from, String packageFqName, String name) {
        LocationInfo location;
        Intrinsics.checkNotNullParameter(recordPackageLookup, "$this$recordPackageLookup");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        Intrinsics.checkNotNullParameter(name, "name");
        if (recordPackageLookup == LookupTracker.DO_NOTHING.INSTANCE || (location = from.getLocation()) == null) {
            return;
        }
        recordPackageLookup.record(location.getFilePath(), recordPackageLookup.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION(), packageFqName, ScopeKind.PACKAGE, name);
    }
}
