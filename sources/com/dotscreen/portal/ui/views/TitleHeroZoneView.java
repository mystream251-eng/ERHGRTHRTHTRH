package com.dotscreen.portal.ui.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.leanback.widget.TitleViewAdapter;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.components.NotificationView;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TitleHeroZoneView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\bH\u0016J\b\u0010\u001b\u001a\u00020\u0016H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/ui/views/TitleHeroZoneView;", "Landroid/widget/RelativeLayout;", "Landroidx/leanback/widget/TitleViewAdapter$Provider;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "notifView", "Lcom/dotscreen/portal/components/NotificationView;", "scrollHandler", "Landroid/os/Handler;", "value", "", "title", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "titleViewAdapter", "Landroidx/leanback/widget/TitleViewAdapter;", "focusSearch", "Landroid/view/View;", "focused", "direction", "getTitleViewAdapter", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TitleHeroZoneView extends RelativeLayout implements TitleViewAdapter.Provider {
    private HashMap _$_findViewCache;
    private final NotificationView notifView;
    private Handler scrollHandler;
    private String title;
    private final TitleViewAdapter titleViewAdapter;

    public TitleHeroZoneView(Context context) {
        this(context, null, 0, 6, null);
    }

    public TitleHeroZoneView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

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
    public TitleHeroZoneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.scrollHandler = new Handler();
        this.titleViewAdapter = new TitleViewAdapter() { // from class: com.dotscreen.portal.ui.views.TitleHeroZoneView$titleViewAdapter$1
            @Override // androidx.leanback.widget.TitleViewAdapter
            public View getSearchAffordanceView() {
                return null;
            }
        };
        View.inflate(context, R.layout.view_title_herozone, this);
        View viewFindViewById = findViewById(R.id.notification_icon);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.notification_icon)");
        this.notifView = (NotificationView) viewFindViewById;
    }

    public /* synthetic */ TitleHeroZoneView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(final String str) {
        this.title = str;
        this.scrollHandler.post(new Runnable() { // from class: com.dotscreen.portal.ui.views.TitleHeroZoneView$title$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView textView = (TextView) this.this$0.findViewById(R.id.title);
                if (textView != null) {
                    textView.setText(str);
                }
            }
        });
    }

    @Override // androidx.leanback.widget.TitleViewAdapter.Provider
    public TitleViewAdapter getTitleViewAdapter() {
        return this.titleViewAdapter;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        if (direction == 130 && Intrinsics.areEqual(focused, this.notifView)) {
            try {
                focusSearch(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
            } catch (NullPointerException unused) {
                return null;
            }
        }
        return super.focusSearch(focused, direction);
    }
}
