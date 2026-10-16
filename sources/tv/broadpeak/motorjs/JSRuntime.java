package tv.broadpeak.motorjs;

/* JADX INFO: loaded from: classes3.dex */
public class JSRuntime extends com.hippo.quickjs.android.JSRuntime {
    protected JSRuntime(long j, QuickJS quickJS) {
        super(j, quickJS);
    }

    public synchronized void setMaxStackSize(int i) {
        checkClosed();
        if (i == 0 || i < -1) {
            throw new IllegalArgumentException("Only positive number and -1 are accepted as malloc limit");
        }
        QuickJS.setMaxStackSize(this.pointer, i);
    }

    @Override // com.hippo.quickjs.android.JSRuntime
    public synchronized JSContext createJSContext() {
        long jCreateContext;
        checkClosed();
        jCreateContext = QuickJS.createContext(this.pointer);
        if (jCreateContext == 0) {
            throw new IllegalStateException("Cannot create JSContext instance");
        }
        return new JSContext(jCreateContext, this.quickJS, this);
    }
}
