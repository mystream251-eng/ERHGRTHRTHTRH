package com.dotscreen.portal.ui;

import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.BindingAdapter;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DataBindingHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0007¨\u0006\b"}, d2 = {"setLayoutMarginStart", "", "view", "Landroid/view/View;", "margin", "", "setLayoutWidth", ToolboxAnalyticsModuleKt.M_WIDTH, "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class DataBindingHelperKt {
    @BindingAdapter({"android:layout_width"})
    public static final void setLayoutWidth(View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"android:layout_marginStart"})
    public static final void setLayoutMarginStart(View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMarginStart(i);
        view.setLayoutParams(marginLayoutParams);
    }
}
