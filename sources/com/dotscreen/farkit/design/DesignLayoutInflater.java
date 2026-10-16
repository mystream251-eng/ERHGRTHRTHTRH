package com.dotscreen.farkit.design;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: DesignLayoutInflater.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0003()*B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B'\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J,\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J&\u0010\u0018\u001a\u0004\u0018\u00010\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\tH\u0016J(\u0010\u001e\u001a\u0004\u0018\u00010\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010$\u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020!H\u0002J\u0010\u0010'\u001a\u00020!2\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/dotscreen/farkit/design/DesignLayoutInflater;", "Landroid/view/LayoutInflater;", "context", "Landroid/content/Context;", "designManager", "Lcom/dotscreen/farkit/design/DesignManager;", "(Landroid/content/Context;Lcom/dotscreen/farkit/design/DesignManager;)V", "original", "cloned", "", "(Landroid/view/LayoutInflater;Landroid/content/Context;Lcom/dotscreen/farkit/design/DesignManager;Z)V", "mConstructorArgs", "Ljava/lang/reflect/Field;", "setPrivateFactory", "cloneInContext", "newContext", "createCustomViewInternal", "Landroid/view/View;", "view", "name", "", "viewContext", "attrs", "Landroid/util/AttributeSet;", "inflate", "parser", "Lorg/xmlpull/v1/XmlPullParser;", "root", "Landroid/view/ViewGroup;", "attachToRoot", "onCreateView", "parent", "setFactory", "", "factory", "Landroid/view/LayoutInflater$Factory;", "setFactory2", "Landroid/view/LayoutInflater$Factory2;", "setPrivateFactoryInternal", "setUpLayoutFactories", "Factory2Wrapper", "FactoryWrapper", "PrivateFactory2Wrapper", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class DesignLayoutInflater extends LayoutInflater {
    private final DesignManager designManager;
    private Field mConstructorArgs;
    private boolean setPrivateFactory;

    /* JADX INFO: compiled from: DesignLayoutInflater.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J(\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/farkit/design/DesignLayoutInflater$FactoryWrapper;", "Landroid/view/LayoutInflater$Factory;", "factory", "inflater", "Lcom/dotscreen/farkit/design/DesignLayoutInflater;", "(Landroid/view/LayoutInflater$Factory;Lcom/dotscreen/farkit/design/DesignLayoutInflater;)V", "getFactory", "()Landroid/view/LayoutInflater$Factory;", "getInflater", "()Lcom/dotscreen/farkit/design/DesignLayoutInflater;", "onCreateView", "Landroid/view/View;", "name", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "farkit_release"}, k = 1, mv = {1, 1, 16})
    private static final class FactoryWrapper implements LayoutInflater.Factory {
        private final LayoutInflater.Factory factory;
        private final DesignLayoutInflater inflater;

        public FactoryWrapper(LayoutInflater.Factory factory, DesignLayoutInflater inflater) {
            Intrinsics.checkParameterIsNotNull(inflater, "inflater");
            this.factory = factory;
            this.inflater = inflater;
        }

        public final LayoutInflater.Factory getFactory() {
            return this.factory;
        }

        public final DesignLayoutInflater getInflater() {
            return this.inflater;
        }

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            LayoutInflater.Factory factory = this.factory;
            return DesignLayoutInflaterKt.applyAttributesToView(factory != null ? factory.onCreateView(name, context, attrs) : null, this.inflater.designManager, attrs);
        }
    }

    /* JADX INFO: compiled from: DesignLayoutInflater.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J2\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J(\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/farkit/design/DesignLayoutInflater$Factory2Wrapper;", "Landroid/view/LayoutInflater$Factory2;", "factory", "inflater", "Lcom/dotscreen/farkit/design/DesignLayoutInflater;", "(Landroid/view/LayoutInflater$Factory2;Lcom/dotscreen/farkit/design/DesignLayoutInflater;)V", "getFactory", "()Landroid/view/LayoutInflater$Factory2;", "getInflater", "()Lcom/dotscreen/farkit/design/DesignLayoutInflater;", "onCreateView", "Landroid/view/View;", "parent", "name", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "farkit_release"}, k = 1, mv = {1, 1, 16})
    private static class Factory2Wrapper implements LayoutInflater.Factory2 {
        private final LayoutInflater.Factory2 factory;
        private final DesignLayoutInflater inflater;

        public Factory2Wrapper(LayoutInflater.Factory2 factory2, DesignLayoutInflater inflater) {
            Intrinsics.checkParameterIsNotNull(inflater, "inflater");
            this.factory = factory2;
            this.inflater = inflater;
        }

        protected final LayoutInflater.Factory2 getFactory() {
            return this.factory;
        }

        protected final DesignLayoutInflater getInflater() {
            return this.inflater;
        }

        @Override // android.view.LayoutInflater.Factory2
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            LayoutInflater.Factory2 factory2 = this.factory;
            return DesignLayoutInflaterKt.applyAttributesToView(factory2 != null ? factory2.onCreateView(parent, name, context, attrs) : null, this.inflater.designManager, attrs);
        }

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            LayoutInflater.Factory2 factory2 = this.factory;
            return DesignLayoutInflaterKt.applyAttributesToView(factory2 != null ? factory2.onCreateView(name, context, attrs) : null, this.inflater.designManager, attrs);
        }
    }

    /* JADX INFO: compiled from: DesignLayoutInflater.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J2\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/farkit/design/DesignLayoutInflater$PrivateFactory2Wrapper;", "Lcom/dotscreen/farkit/design/DesignLayoutInflater$Factory2Wrapper;", "factory", "Landroid/view/LayoutInflater$Factory2;", "inflater", "Lcom/dotscreen/farkit/design/DesignLayoutInflater;", "(Landroid/view/LayoutInflater$Factory2;Lcom/dotscreen/farkit/design/DesignLayoutInflater;)V", "onCreateView", "Landroid/view/View;", "parent", "name", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "farkit_release"}, k = 1, mv = {1, 1, 16})
    private static final class PrivateFactory2Wrapper extends Factory2Wrapper {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PrivateFactory2Wrapper(LayoutInflater.Factory2 factory2, DesignLayoutInflater inflater) {
            super(factory2, inflater);
            Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        }

        @Override // com.dotscreen.farkit.design.DesignLayoutInflater.Factory2Wrapper, android.view.LayoutInflater.Factory2
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            DesignLayoutInflater inflater = getInflater();
            LayoutInflater.Factory2 factory = getFactory();
            View viewOnCreateView = factory != null ? factory.onCreateView(parent, name, context, attrs) : null;
            if (name == null) {
                Intrinsics.throwNpe();
            }
            if (context == null) {
                Intrinsics.throwNpe();
            }
            if (attrs == null) {
                Intrinsics.throwNpe();
            }
            return DesignLayoutInflaterKt.applyAttributesToView(inflater.createCustomViewInternal(viewOnCreateView, name, context, attrs), getInflater().designManager, attrs);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DesignLayoutInflater(Context context, DesignManager designManager) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(designManager, "designManager");
        this.designManager = designManager;
        setUpLayoutFactories(false);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DesignLayoutInflater(LayoutInflater original, Context context, DesignManager designManager, boolean z) {
        super(original, context);
        Intrinsics.checkParameterIsNotNull(original, "original");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(designManager, "designManager");
        this.designManager = designManager;
        setUpLayoutFactories(z);
    }

    private final void setUpLayoutFactories(boolean cloned) {
        if (cloned) {
            return;
        }
        if (getFactory2() != null && !(getFactory2() instanceof Factory2Wrapper)) {
            setFactory2(getFactory2());
        }
        if (getFactory() == null || (getFactory() instanceof FactoryWrapper)) {
            return;
        }
        setFactory(getFactory());
    }

    @Override // android.view.LayoutInflater
    public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot) {
        setPrivateFactoryInternal();
        return super.inflate(parser, root, attachToRoot);
    }

    @Override // android.view.LayoutInflater
    public LayoutInflater cloneInContext(Context newContext) {
        Intrinsics.checkParameterIsNotNull(newContext, "newContext");
        return new DesignLayoutInflater(this, newContext, this.designManager, true);
    }

    @Override // android.view.LayoutInflater
    public void setFactory(LayoutInflater.Factory factory) {
        if (factory instanceof FactoryWrapper) {
            super.setFactory(factory);
        } else {
            super.setFactory(new FactoryWrapper(factory, this));
        }
    }

    @Override // android.view.LayoutInflater
    public void setFactory2(LayoutInflater.Factory2 factory) {
        if (factory instanceof Factory2Wrapper) {
            super.setFactory2(factory);
        } else {
            super.setFactory2(new Factory2Wrapper(factory, this));
        }
    }

    private final void setPrivateFactoryInternal() {
        if (this.setPrivateFactory) {
            return;
        }
        if (!(getContext() instanceof LayoutInflater.Factory2)) {
            this.setPrivateFactory = true;
            return;
        }
        Method method$farkit_release = ReflectionUtils.INSTANCE.getMethod$farkit_release(LayoutInflater.class, "setPrivateFactory");
        if (method$farkit_release != null) {
            ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
            Object[] objArr = new Object[1];
            Object context = getContext();
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater.Factory2");
            }
            objArr[0] = new PrivateFactory2Wrapper((LayoutInflater.Factory2) context, this);
            reflectionUtils.invokeMethod$farkit_release(this, method$farkit_release, objArr);
        }
        this.setPrivateFactory = true;
    }

    @Override // android.view.LayoutInflater
    protected View onCreateView(View parent, String name, AttributeSet attrs) {
        return DesignLayoutInflaterKt.applyAttributesToView(onCreateView(name, attrs), this.designManager, attrs);
    }

    @Override // android.view.LayoutInflater
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        View viewOnCreateView = (View) null;
        String[] strArr = {"android.widget.", "android.webkit.", ""};
        for (int i = 0; i < 3; i++) {
            try {
                viewOnCreateView = super.createView(name, strArr[i], attrs);
            } catch (ClassNotFoundException unused) {
            }
        }
        if (viewOnCreateView == null) {
            viewOnCreateView = super.onCreateView(name, attrs);
        }
        if (viewOnCreateView == null) {
            Intrinsics.throwNpe();
        }
        return DesignLayoutInflaterKt.applyAttributesToView(viewOnCreateView, this.designManager, attrs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final View createCustomViewInternal(View view, String name, Context viewContext, AttributeSet attrs) {
        ReflectionUtils reflectionUtils;
        Field field;
        if (view != null || StringsKt.indexOf$default((CharSequence) name, '.', 0, false, 6, (Object) null) <= -1) {
            return view;
        }
        if (Build.VERSION.SDK_INT <= 28) {
            if (this.mConstructorArgs == null) {
                this.mConstructorArgs = ReflectionUtils.INSTANCE.getField$farkit_release(LayoutInflater.class, "mConstructorArgs");
            }
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Field field2 = this.mConstructorArgs;
            if (field2 == null) {
                Intrinsics.throwNpe();
            }
            Object[] objArr = (Object[]) reflectionUtils2.getValue$farkit_release(field2, this);
            if (objArr == null) {
                Intrinsics.throwNpe();
            }
            Object obj = objArr[0];
            objArr[0] = viewContext;
            ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
            Field field3 = this.mConstructorArgs;
            if (field3 == null) {
                Intrinsics.throwNpe();
            }
            reflectionUtils3.setValue$farkit_release(field3, this, objArr);
            try {
                view = createView(name, null, attrs);
                objArr[0] = obj;
                reflectionUtils = ReflectionUtils.INSTANCE;
                field = this.mConstructorArgs;
                if (field == null) {
                    Intrinsics.throwNpe();
                }
            } catch (ClassNotFoundException unused) {
                objArr[0] = obj;
                reflectionUtils = ReflectionUtils.INSTANCE;
                field = this.mConstructorArgs;
                if (field == null) {
                }
            } catch (Throwable th) {
                objArr[0] = obj;
                ReflectionUtils reflectionUtils4 = ReflectionUtils.INSTANCE;
                Field field4 = this.mConstructorArgs;
                if (field4 == null) {
                    Intrinsics.throwNpe();
                }
                reflectionUtils4.setValue$farkit_release(field4, this, objArr);
                throw th;
            }
            reflectionUtils.setValue$farkit_release(field, this, objArr);
            return view;
        }
        try {
            return createView(name, null, attrs);
        } catch (Throwable th2) {
            Log.w(DesignLayoutInflaterKt.TAG, "Error", th2);
            return view;
        }
    }
}
