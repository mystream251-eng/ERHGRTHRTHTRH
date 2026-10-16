package com.dotscreen.portal.ui.screens.guide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.dotscreen.megacable.portal.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimelineRow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0016J\u0018\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0016J\u0006\u0010\u0015\u001a\u00020\u0010J\b\u0010\u0016\u001a\u00020\u000eH\u0004J\u0016\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/TimelineRow;", "Lcom/dotscreen/portal/ui/screens/guide/TimelineGridView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mScrollPosition", "scrollOffset", "getScrollOffset", "()I", "leftFadingEdgeStrength", "", "onRtlPropertiesChanged", "", "layoutDirection", "onScrolled", "dx", "dy", "resetScroll", "rightFadingEdgeStrength", "scrollTo", "_scrollOffset", "smoothScroll", "", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TimelineRow extends TimelineGridView {
    private static final float FADING_EDGE_STRENGTH_START = 1.0f;
    private HashMap _$_findViewCache;
    private int mScrollPosition;

    public TimelineRow(Context context) {
        this(context, null, 0, 6, null);
    }

    public TimelineRow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    @Override // com.dotscreen.portal.ui.screens.guide.TimelineGridView
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.guide.TimelineGridView
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimelineRow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public /* synthetic */ TimelineRow(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void resetScroll() {
        scrollTo(getResources().getDimensionPixelSize(R.dimen.program_guide_table_width_per_hour) * 10, false);
    }

    public final int getScrollOffset() {
        return Math.abs(this.mScrollPosition);
    }

    public final void scrollTo(int _scrollOffset, boolean smoothScroll) {
        int scrollOffset = (_scrollOffset - getScrollOffset()) * (getLayoutDirection() == 0 ? 1 : -1);
        if (smoothScroll) {
            smoothScrollBy(scrollOffset, 0);
        } else {
            scrollBy(scrollOffset, 0);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        super.onRtlPropertiesChanged(layoutDirection);
        if (isAttachedToWindow()) {
            scrollTo(getScrollOffset(), false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrolled(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            this.mScrollPosition = 0;
        } else {
            this.mScrollPosition += dx;
        }
    }

    protected final float leftFadingEdgeStrength() {
        return getLayoutDirection() == 0 ? 1.0f : 0.0f;
    }

    protected final float rightFadingEdgeStrength() {
        return getLayoutDirection() == 1 ? 1.0f : 0.0f;
    }
}
