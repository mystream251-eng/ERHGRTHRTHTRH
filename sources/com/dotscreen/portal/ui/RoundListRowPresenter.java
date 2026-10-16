package com.dotscreen.portal.ui;

import android.content.Context;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ShadowOverlayHelper;
import com.dotscreen.megacable.portal.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundListRowPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0014J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/portal/ui/RoundListRowPresenter;", "Landroidx/leanback/widget/ListRowPresenter;", "context", "Landroid/content/Context;", "focusZoomFactor", "", "useFocusDimmer", "", "(Landroid/content/Context;IZ)V", "getContext", "()Landroid/content/Context;", "createShadowOverlayOptions", "Landroidx/leanback/widget/ShadowOverlayHelper$Options;", "isUsingOutlineClipping", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class RoundListRowPresenter extends ListRowPresenter {
    private final Context context;

    public RoundListRowPresenter(Context context) {
        this(context, 0, false, 6, null);
    }

    public RoundListRowPresenter(Context context, int i) {
        this(context, i, false, 4, null);
    }

    @Override // androidx.leanback.widget.ListRowPresenter
    public boolean isUsingOutlineClipping(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoundListRowPresenter(Context context, int i, boolean z) {
        super(i, z);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    public /* synthetic */ RoundListRowPresenter(Context context, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? false : z);
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // androidx.leanback.widget.ListRowPresenter
    protected ShadowOverlayHelper.Options createShadowOverlayOptions() {
        ShadowOverlayHelper.Options options = new ShadowOverlayHelper.Options();
        options.roundedCornerRadius(this.context.getResources().getDimensionPixelSize(R.dimen.lb_rounded_rect_corner_radius));
        return options;
    }
}
