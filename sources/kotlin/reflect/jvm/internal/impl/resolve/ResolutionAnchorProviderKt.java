package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;

/* JADX INFO: compiled from: ResolutionAnchorProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ResolutionAnchorProviderKt {
    private static final ModuleCapability<ResolutionAnchorProvider> RESOLUTION_ANCHOR_PROVIDER_CAPABILITY = new ModuleCapability<>("ResolutionAnchorProvider");

    public static final ModuleDescriptor getResolutionAnchorIfAny(ModuleDescriptor getResolutionAnchorIfAny) {
        Intrinsics.checkNotNullParameter(getResolutionAnchorIfAny, "$this$getResolutionAnchorIfAny");
        ResolutionAnchorProvider resolutionAnchorProvider = (ResolutionAnchorProvider) getResolutionAnchorIfAny.getCapability(RESOLUTION_ANCHOR_PROVIDER_CAPABILITY);
        if (resolutionAnchorProvider != null) {
            return resolutionAnchorProvider.getResolutionAnchor(getResolutionAnchorIfAny);
        }
        return null;
    }
}
