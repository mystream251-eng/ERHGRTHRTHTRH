package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;

/* JADX INFO: compiled from: VisibilityUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class VisibilityUtilKt {
    public static final CallableMemberDescriptor findMemberWithMaxVisibility(Collection<? extends CallableMemberDescriptor> descriptors) {
        Integer numCompare;
        Intrinsics.checkNotNullParameter(descriptors, "descriptors");
        boolean z = !descriptors.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) null;
        for (CallableMemberDescriptor callableMemberDescriptor2 : descriptors) {
            if (callableMemberDescriptor == null || ((numCompare = DescriptorVisibilities.compare(callableMemberDescriptor.getVisibility(), callableMemberDescriptor2.getVisibility())) != null && numCompare.intValue() < 0)) {
                callableMemberDescriptor = callableMemberDescriptor2;
            }
        }
        Intrinsics.checkNotNull(callableMemberDescriptor);
        return callableMemberDescriptor;
    }
}
