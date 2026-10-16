package io.sentry.android.core.internal.gestures;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ScrollView;
import androidx.core.view.ScrollingView;
import io.sentry.util.Objects;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes2.dex */
final class ViewUtils {
    ViewUtils() {
    }

    static View findTarget(View view, float f, float f2, ViewTargetSelector viewTargetSelector) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(view);
        int[] iArr = new int[2];
        View view2 = null;
        while (arrayDeque.size() > 0) {
            View view3 = (View) Objects.requireNonNull((View) arrayDeque.poll(), "view is required");
            if (viewTargetSelector.select(view3)) {
                if (viewTargetSelector.skipChildren()) {
                    return view3;
                }
                view2 = view3;
            }
            if (view3 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view3;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (touchWithinBounds(childAt, f, f2, iArr)) {
                        arrayDeque.add(childAt);
                    }
                }
            }
        }
        return view2;
    }

    private static boolean touchWithinBounds(View view, float f, float f2, int[] iArr) {
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return f >= ((float) i) && f <= ((float) (i + view.getWidth())) && f2 >= ((float) i2) && f2 <= ((float) (i2 + view.getHeight()));
    }

    static boolean isViewTappable(View view) {
        return view.isClickable() && view.getVisibility() == 0;
    }

    static boolean isViewScrollable(View view, boolean z) {
        return (isJetpackScrollingView(view, z) || AbsListView.class.isAssignableFrom(view.getClass()) || ScrollView.class.isAssignableFrom(view.getClass())) && view.getVisibility() == 0;
    }

    private static boolean isJetpackScrollingView(View view, boolean z) {
        if (z) {
            return ScrollingView.class.isAssignableFrom(view.getClass());
        }
        return false;
    }

    static String getResourceId(View view) {
        int id = view.getId();
        Resources resources = view.getContext().getResources();
        if (resources == null) {
            return "";
        }
        try {
            return resources.getResourceEntryName(id);
        } catch (Resources.NotFoundException unused) {
            return "0x" + Integer.toString(id, 16);
        }
    }
}
