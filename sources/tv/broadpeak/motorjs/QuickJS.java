package tv.broadpeak.motorjs;

/* JADX INFO: loaded from: classes3.dex */
public class QuickJS extends com.hippo.quickjs.android.QuickJS {
    static native void setMaxStackSize(long j, int i);

    static {
        try {
            System.loadLibrary("broadpeak-motorjs");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected QuickJS(Builder builder) {
        super(builder, false);
    }

    @Override // com.hippo.quickjs.android.QuickJS
    public JSRuntime createJSRuntime() {
        long jCreateRuntime = com.hippo.quickjs.android.QuickJS.createRuntime();
        if (jCreateRuntime == 0) {
            throw new IllegalStateException("Cannot create JSRuntime instance");
        }
        return new JSRuntime(jCreateRuntime, this);
    }

    public static class Builder extends com.hippo.quickjs.android.QuickJS.Builder {
        @Override // com.hippo.quickjs.android.QuickJS.Builder
        public QuickJS build() {
            return new QuickJS(this);
        }
    }
}
