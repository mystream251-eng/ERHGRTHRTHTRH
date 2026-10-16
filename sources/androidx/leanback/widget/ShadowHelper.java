package androidx.leanback.widget;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
final class ShadowHelper {
    private ShadowHelper() {
    }

    static boolean supportsDynamicShadow() {
        return Build.VERSION.SDK_INT >= 21;
    }

    static Object addDynamicShadow(View view, float f, float f2, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return ShadowHelperApi21.addDynamicShadow(view, f, f2, i);
        }
        return null;
    }

    static void setShadowFocusLevel(Object obj, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            ShadowHelperApi21.setShadowFocusLevel(obj, f);
        }
    }
}
