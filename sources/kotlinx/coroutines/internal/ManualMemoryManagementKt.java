package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: ManualMemoryManagement.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u0081\b\u001a\u0017\u0010\u0005\u001a\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0081\b¨\u0006\u0007"}, d2 = {"disposeLockFreeLinkedList", "", "list", "Lkotlin/Function0;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "storeCyclicRef", "block", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
public final class ManualMemoryManagementKt {
    private static final void disposeLockFreeLinkedList(Function0<? extends LockFreeLinkedListNode> function0) {
    }

    private static final void storeCyclicRef(Function0<Unit> function0) {
        function0.invoke();
    }
}
