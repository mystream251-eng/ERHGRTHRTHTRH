package androidx.leanback.transition;

import android.content.Context;
import android.os.Build;
import android.view.animation.AnimationUtils;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes.dex */
public class LeanbackTransitionHelper {
    public static Object loadTitleInTransition(Context context) {
        if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 21) {
            return TransitionHelper.loadTransition(context, R.transition.lb_title_in);
        }
        SlideKitkat slideKitkat = new SlideKitkat();
        slideKitkat.setSlideEdge(48);
        slideKitkat.setInterpolator(AnimationUtils.loadInterpolator(context, android.R.anim.decelerate_interpolator));
        slideKitkat.addTarget(R.id.browse_title_group);
        return slideKitkat;
    }

    public static Object loadTitleOutTransition(Context context) {
        if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 21) {
            return TransitionHelper.loadTransition(context, R.transition.lb_title_out);
        }
        SlideKitkat slideKitkat = new SlideKitkat();
        slideKitkat.setSlideEdge(48);
        slideKitkat.setInterpolator(AnimationUtils.loadInterpolator(context, R.anim.lb_decelerator_4));
        slideKitkat.addTarget(R.id.browse_title_group);
        return slideKitkat;
    }

    private LeanbackTransitionHelper() {
    }
}
