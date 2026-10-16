package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: ModuleAwareClassDescriptor.kt */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ModuleAwareClassDescriptor implements ClassDescriptor {
    public static final Companion Companion = new Companion(null);

    protected abstract MemberScope getMemberScope(TypeSubstitution typeSubstitution, KotlinTypeRefiner kotlinTypeRefiner);

    protected abstract MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner);

    /* JADX INFO: compiled from: ModuleAwareClassDescriptor.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MemberScope getRefinedUnsubstitutedMemberScopeIfPossible$descriptors(ClassDescriptor getRefinedUnsubstitutedMemberScopeIfPossible, KotlinTypeRefiner kotlinTypeRefiner) {
            MemberScope unsubstitutedMemberScope;
            Intrinsics.checkNotNullParameter(getRefinedUnsubstitutedMemberScopeIfPossible, "$this$getRefinedUnsubstitutedMemberScopeIfPossible");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            ModuleAwareClassDescriptor moduleAwareClassDescriptor = (ModuleAwareClassDescriptor) (!(getRefinedUnsubstitutedMemberScopeIfPossible instanceof ModuleAwareClassDescriptor) ? null : getRefinedUnsubstitutedMemberScopeIfPossible);
            if (moduleAwareClassDescriptor != null && (unsubstitutedMemberScope = moduleAwareClassDescriptor.getUnsubstitutedMemberScope(kotlinTypeRefiner)) != null) {
                return unsubstitutedMemberScope;
            }
            MemberScope unsubstitutedMemberScope2 = getRefinedUnsubstitutedMemberScopeIfPossible.getUnsubstitutedMemberScope();
            Intrinsics.checkNotNullExpressionValue(unsubstitutedMemberScope2, "this.unsubstitutedMemberScope");
            return unsubstitutedMemberScope2;
        }

        public final MemberScope getRefinedMemberScopeIfPossible$descriptors(ClassDescriptor getRefinedMemberScopeIfPossible, TypeSubstitution typeSubstitution, KotlinTypeRefiner kotlinTypeRefiner) {
            MemberScope memberScope;
            Intrinsics.checkNotNullParameter(getRefinedMemberScopeIfPossible, "$this$getRefinedMemberScopeIfPossible");
            Intrinsics.checkNotNullParameter(typeSubstitution, "typeSubstitution");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            ModuleAwareClassDescriptor moduleAwareClassDescriptor = (ModuleAwareClassDescriptor) (!(getRefinedMemberScopeIfPossible instanceof ModuleAwareClassDescriptor) ? null : getRefinedMemberScopeIfPossible);
            if (moduleAwareClassDescriptor != null && (memberScope = moduleAwareClassDescriptor.getMemberScope(typeSubstitution, kotlinTypeRefiner)) != null) {
                return memberScope;
            }
            MemberScope memberScope2 = getRefinedMemberScopeIfPossible.getMemberScope(typeSubstitution);
            Intrinsics.checkNotNullExpressionValue(memberScope2, "this.getMemberScope(\n   …ubstitution\n            )");
            return memberScope2;
        }
    }
}
