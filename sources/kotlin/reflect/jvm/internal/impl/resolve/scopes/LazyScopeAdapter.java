package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* JADX INFO: compiled from: LazyScopeAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class LazyScopeAdapter extends AbstractScopeAdapter {
    private final NotNullLazyValue<MemberScope> lazyScope;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyScopeAdapter(Function0<? extends MemberScope> function0) {
        this(null, function0, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ LazyScopeAdapter(StorageManager storageManager, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            storageManager = LockBasedStorageManager.NO_LOCKS;
            Intrinsics.checkNotNullExpressionValue(storageManager, "LockBasedStorageManager.NO_LOCKS");
        }
        this(storageManager, function0);
    }

    public LazyScopeAdapter(StorageManager storageManager, final Function0<? extends MemberScope> getScope) {
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(getScope, "getScope");
        this.lazyScope = storageManager.createLazyValue(new Function0<MemberScope>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.scopes.LazyScopeAdapter$lazyScope$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final MemberScope invoke() {
                MemberScope memberScope = (MemberScope) getScope.invoke();
                return memberScope instanceof AbstractScopeAdapter ? ((AbstractScopeAdapter) memberScope).getActualScope() : memberScope;
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter
    protected MemberScope getWorkerScope() {
        return this.lazyScope.invoke();
    }
}
