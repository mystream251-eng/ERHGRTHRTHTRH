package com.google.android.gms.dynamic;

import android.os.Bundle;
import java.util.Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes2.dex */
final class zaa<T> implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zarj;

    zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zarj = deferredLifecycleHelper;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        this.zarj.zarf = lifecycleDelegate;
        Iterator it = this.zarj.zarh.iterator();
        while (it.hasNext()) {
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(this.zarj.zarf);
        }
        this.zarj.zarh.clear();
        DeferredLifecycleHelper.zaa(this.zarj, (Bundle) null);
    }
}
