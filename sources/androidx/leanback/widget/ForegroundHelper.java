package androidx.leanback.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
final class ForegroundHelper {
    static boolean supportsForeground() {
        return Build.VERSION.SDK_INT >= 23;
    }

    static Drawable getForeground(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getForeground();
        }
        return null;
    }

    static void setForeground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setForeground(drawable);
        }
    }

    private ForegroundHelper() {
    }
}
