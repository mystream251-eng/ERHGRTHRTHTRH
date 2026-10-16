package kotlin.reflect.jvm.internal.impl.resolve;

import android.R;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* JADX INFO: compiled from: overridingUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class OverridingUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <H> Collection<H> selectMostSpecificInEachOverridableGroup(Collection<? extends H> selectMostSpecificInEachOverridableGroup, Function1<? super H, ? extends CallableDescriptor> descriptorByHandle) {
        Intrinsics.checkNotNullParameter(selectMostSpecificInEachOverridableGroup, "$this$selectMostSpecificInEachOverridableGroup");
        Intrinsics.checkNotNullParameter(descriptorByHandle, "descriptorByHandle");
        if (selectMostSpecificInEachOverridableGroup.size() <= 1) {
            return selectMostSpecificInEachOverridableGroup;
        }
        LinkedList linkedList = new LinkedList(selectMostSpecificInEachOverridableGroup);
        SmartSet smartSetCreate = SmartSet.Companion.create();
        while (true) {
            LinkedList linkedList2 = linkedList;
            if (!linkedList2.isEmpty()) {
                Object objFirst = CollectionsKt.first((List<? extends Object>) linkedList);
                final SmartSet smartSetCreate2 = SmartSet.Companion.create();
                Collection<R.attr> collectionExtractMembersOverridableInBothWays = OverridingUtil.extractMembersOverridableInBothWays(objFirst, linkedList2, descriptorByHandle, new Function1<H, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1
                    {
                        super(1);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                        invoke2(obj);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(H it) {
                        SmartSet smartSet = smartSetCreate2;
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        smartSet.add(it);
                    }
                });
                Intrinsics.checkNotNullExpressionValue(collectionExtractMembersOverridableInBothWays, "OverridingUtil.extractMe…nflictedHandles.add(it) }");
                if (collectionExtractMembersOverridableInBothWays.size() == 1 && smartSetCreate2.isEmpty()) {
                    Object objSingle = CollectionsKt.single(collectionExtractMembersOverridableInBothWays);
                    Intrinsics.checkNotNullExpressionValue(objSingle, "overridableGroup.single()");
                    smartSetCreate.add(objSingle);
                } else {
                    R.attr attrVar = (Object) OverridingUtil.selectMostSpecificMember(collectionExtractMembersOverridableInBothWays, descriptorByHandle);
                    Intrinsics.checkNotNullExpressionValue(attrVar, "OverridingUtil.selectMos…roup, descriptorByHandle)");
                    CallableDescriptor callableDescriptorInvoke = descriptorByHandle.invoke(attrVar);
                    for (R.attr it : collectionExtractMembersOverridableInBothWays) {
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        if (!OverridingUtil.isMoreSpecific(callableDescriptorInvoke, descriptorByHandle.invoke(it))) {
                            smartSetCreate2.add(it);
                        }
                    }
                    SmartSet smartSet = smartSetCreate2;
                    if (!smartSet.isEmpty()) {
                        smartSetCreate.addAll(smartSet);
                    }
                    smartSetCreate.add(attrVar);
                }
            } else {
                return smartSetCreate;
            }
        }
    }
}
