package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: AbstractTypeConstructor.kt */
/* JADX INFO: loaded from: classes3.dex */
final class AbstractTypeConstructor$supertypes$3 extends Lambda implements Function1<AbstractTypeConstructor.Supertypes, Unit> {
    final /* synthetic */ AbstractTypeConstructor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AbstractTypeConstructor$supertypes$3(AbstractTypeConstructor abstractTypeConstructor) {
        super(1);
        this.this$0 = abstractTypeConstructor;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(AbstractTypeConstructor.Supertypes supertypes) {
        invoke2(supertypes);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(AbstractTypeConstructor.Supertypes supertypes) {
        Intrinsics.checkNotNullParameter(supertypes, "supertypes");
        List listFindLoopsInSupertypesAndDisconnect = this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, supertypes.getAllSupertypes(), new Function1<TypeConstructor, Iterable<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Iterable<KotlinType> invoke(TypeConstructor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.this$0.computeNeighbours(it, false);
            }
        }, new Function1<KotlinType, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                invoke2(kotlinType);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KotlinType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.this$0.reportSupertypeLoopError(it);
            }
        });
        if (listFindLoopsInSupertypesAndDisconnect.isEmpty()) {
            KotlinType kotlinTypeDefaultSupertypeIfEmpty = this.this$0.defaultSupertypeIfEmpty();
            List listListOf = kotlinTypeDefaultSupertypeIfEmpty != null ? CollectionsKt.listOf(kotlinTypeDefaultSupertypeIfEmpty) : null;
            if (listListOf == null) {
                listListOf = CollectionsKt.emptyList();
            }
            listFindLoopsInSupertypesAndDisconnect = listListOf;
        }
        this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, listFindLoopsInSupertypesAndDisconnect, new Function1<TypeConstructor, Iterable<? extends KotlinType>>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Iterable<KotlinType> invoke(TypeConstructor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return AbstractTypeConstructor$supertypes$3.this.this$0.computeNeighbours(it, true);
            }
        }, new Function1<KotlinType, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KotlinType kotlinType) {
                invoke2(kotlinType);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KotlinType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AbstractTypeConstructor$supertypes$3.this.this$0.reportScopesLoopError(it);
            }
        });
        List<? extends KotlinType> list = (List) (listFindLoopsInSupertypesAndDisconnect instanceof List ? listFindLoopsInSupertypesAndDisconnect : null);
        if (list == null) {
            list = CollectionsKt.toList(listFindLoopsInSupertypesAndDisconnect);
        }
        supertypes.setSupertypesWithoutCycles(list);
    }
}
