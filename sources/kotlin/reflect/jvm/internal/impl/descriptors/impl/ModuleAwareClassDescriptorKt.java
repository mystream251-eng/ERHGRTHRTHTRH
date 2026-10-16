package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: ModuleAwareClassDescriptor.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class ModuleAwareClassDescriptorKt {
    public static final MemberScope getRefinedUnsubstitutedMemberScopeIfPossible(ClassDescriptor getRefinedUnsubstitutedMemberScopeIfPossible, KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(getRefinedUnsubstitutedMemberScopeIfPossible, "$this$getRefinedUnsubstitutedMemberScopeIfPossible");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return ModuleAwareClassDescriptor.Companion.getRefinedUnsubstitutedMemberScopeIfPossible$descriptors(getRefinedUnsubstitutedMemberScopeIfPossible, kotlinTypeRefiner);
    }

    public static final MemberScope getRefinedMemberScopeIfPossible(ClassDescriptor getRefinedMemberScopeIfPossible, TypeSubstitution typeSubstitution, KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(getRefinedMemberScopeIfPossible, "$this$getRefinedMemberScopeIfPossible");
        Intrinsics.checkNotNullParameter(typeSubstitution, "typeSubstitution");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return ModuleAwareClassDescriptor.Companion.getRefinedMemberScopeIfPossible$descriptors(getRefinedMemberScopeIfPossible, typeSubstitution, kotlinTypeRefiner);
    }
}
