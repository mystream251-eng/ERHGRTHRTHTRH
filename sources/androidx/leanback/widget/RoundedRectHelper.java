package androidx.leanback.widget;

import android.os.Build;
import android.view.View;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes.dex */
final class RoundedRectHelper {
    static boolean supportsRoundedCorner() {
        return Build.VERSION.SDK_INT >= 21;
    }

    static void setClipToRoundedOutline(View view, boolean z, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            RoundedRectHelperApi21.setClipToRoundedOutline(view, z, i);
        }
    }

    static void setClipToRoundedOutline(View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            RoundedRectHelperApi21.setClipToRoundedOutline(view, z, view.getResources().getDimensionPixelSize(R.dimen.lb_rounded_rect_corner_radius));
        }
    }

    private RoundedRectHelper() {
    }
}
