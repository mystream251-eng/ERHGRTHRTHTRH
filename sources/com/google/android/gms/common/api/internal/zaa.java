package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0183zaa> zacl;

    public zaa(Activity activity) {
        this(C0183zaa.zaa(activity));
    }

    private zaa(C0183zaa c0183zaa) {
        this.zacl = new WeakReference<>(c0183zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0183zaa c0183zaa = this.zacl.get();
        if (c0183zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0183zaa.zaa(runnable);
        return this;
    }

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.zaa$zaa, reason: collision with other inner class name */
    static class C0183zaa extends LifecycleCallback {
        private List<Runnable> zacm;

        /* JADX INFO: Access modifiers changed from: private */
        public static C0183zaa zaa(Activity activity) {
            C0183zaa c0183zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0183zaa = (C0183zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0183zaa.class);
                if (c0183zaa == null) {
                    c0183zaa = new C0183zaa(fragment);
                }
            }
            return c0183zaa;
        }

        private C0183zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacm = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacm.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacm;
                this.zacm = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }
}
