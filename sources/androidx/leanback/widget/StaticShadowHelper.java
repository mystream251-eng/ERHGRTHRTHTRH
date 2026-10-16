package androidx.leanback.widget;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes.dex */
final class StaticShadowHelper {
    private StaticShadowHelper() {
    }

    static boolean supportsShadow() {
        return Build.VERSION.SDK_INT >= 21;
    }

    static void prepareParent(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            viewGroup.setLayoutMode(1);
        }
    }

    static Object addStaticShadow(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        viewGroup.setLayoutMode(1);
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lb_shadow, viewGroup, true);
        ShadowImpl shadowImpl = new ShadowImpl();
        shadowImpl.mNormalShadow = viewGroup.findViewById(R.id.lb_shadow_normal);
        shadowImpl.mFocusShadow = viewGroup.findViewById(R.id.lb_shadow_focused);
        return shadowImpl;
    }

    static void setShadowFocusLevel(Object obj, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            ShadowImpl shadowImpl = (ShadowImpl) obj;
            shadowImpl.mNormalShadow.setAlpha(1.0f - f);
            shadowImpl.mFocusShadow.setAlpha(f);
        }
    }

    static class ShadowImpl {
        View mFocusShadow;
        View mNormalShadow;

        ShadowImpl() {
        }
    }
}
