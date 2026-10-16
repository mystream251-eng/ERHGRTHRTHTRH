package com.dotscreen.farkit.rx;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX INFO: compiled from: LifecycleAwareDisposable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\nH\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/farkit/rx/LifecycleAwareDisposable;", "Landroidx/lifecycle/LifecycleObserver;", "()V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "setCompositeDisposable", "(Lio/reactivex/disposables/CompositeDisposable;)V", "add", "", "disposable", "Lio/reactivex/disposables/Disposable;", "bindTo", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "onDestroy", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class LifecycleAwareDisposable implements LifecycleObserver {
    public CompositeDisposable compositeDisposable;

    public final CompositeDisposable getCompositeDisposable() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (compositeDisposable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("compositeDisposable");
        }
        return compositeDisposable;
    }

    public final void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        Intrinsics.checkParameterIsNotNull(compositeDisposable, "<set-?>");
        this.compositeDisposable = compositeDisposable;
    }

    public final void bindTo(Lifecycle lifecycle) {
        Intrinsics.checkParameterIsNotNull(lifecycle, "lifecycle");
        lifecycle.addObserver(this);
        this.compositeDisposable = new CompositeDisposable();
    }

    /* JADX INFO: renamed from: com.dotscreen.farkit.rx.LifecycleAwareDisposable$add$1, reason: invalid class name */
    /* JADX INFO: compiled from: LifecycleAwareDisposable.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    final /* synthetic */ class AnonymousClass1 extends MutablePropertyReference0 {
        AnonymousClass1(LifecycleAwareDisposable lifecycleAwareDisposable) {
            super(lifecycleAwareDisposable);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "compositeDisposable";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(LifecycleAwareDisposable.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getCompositeDisposable()Lio/reactivex/disposables/CompositeDisposable;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return ((LifecycleAwareDisposable) this.receiver).getCompositeDisposable();
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            ((LifecycleAwareDisposable) this.receiver).setCompositeDisposable((CompositeDisposable) obj);
        }
    }

    public final void add(Disposable disposable) {
        Intrinsics.checkParameterIsNotNull(disposable, "disposable");
        if (this.compositeDisposable != null) {
            CompositeDisposable compositeDisposable = this.compositeDisposable;
            if (compositeDisposable == null) {
                Intrinsics.throwUninitializedPropertyAccessException("compositeDisposable");
            }
            compositeDisposable.add(disposable);
            return;
        }
        throw new NotImplementedError("must bind AutoDisposable to a Lifecycle first");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onDestroy() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (compositeDisposable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("compositeDisposable");
        }
        compositeDisposable.dispose();
    }
}
