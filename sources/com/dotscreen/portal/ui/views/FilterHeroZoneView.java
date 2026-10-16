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
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterHeroZoneView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 .2\u00020\u00012\u00020\u0002:\u0001.B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001c\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010,\u001a\u00020\bH\u0016J\b\u0010-\u001a\u00020(H\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR&\u0010\u0010\u001a\u000e\u0012\b\u0012\u00060\u0012j\u0002`\u0013\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\n\u0018\u00010\u0012j\u0004\u0018\u0001`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010$\u001a\u0004\u0018\u00010\u00122\b\u0010#\u001a\u0004\u0018\u00010\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010\u001cR\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/dotscreen/portal/ui/views/FilterHeroZoneView;", "Landroid/widget/RelativeLayout;", "Landroidx/leanback/widget/TitleViewAdapter$Provider;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "changeListener", "Lcom/dotscreen/portal/ui/views/FilterHeroZoneView$Companion$OnGenreChangedListener;", "getChangeListener", "()Lcom/dotscreen/portal/ui/views/FilterHeroZoneView$Companion$OnGenreChangedListener;", "setChangeListener", "(Lcom/dotscreen/portal/ui/views/FilterHeroZoneView$Companion$OnGenreChangedListener;)V", "channelGenres", "", "", "Lcom/dotscreen/bokit/model/Genre;", "getChannelGenres", "()Ljava/util/List;", "setChannelGenres", "(Ljava/util/List;)V", "currentGenre", "getCurrentGenre", "()Ljava/lang/String;", "setCurrentGenre", "(Ljava/lang/String;)V", "filterView", "Lcom/dotscreen/portal/ui/views/FontTextView;", "notifView", "Lcom/dotscreen/portal/components/NotificationView;", "scrollHandler", "Landroid/os/Handler;", "value", "title", "getTitle", "setTitle", "titleViewAdapter", "Landroidx/leanback/widget/TitleViewAdapter;", "focusSearch", "Landroid/view/View;", "focused", "direction", "getTitleViewAdapter", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class FilterHeroZoneView extends RelativeLayout implements TitleViewAdapter.Provider {
    private HashMap _$_findViewCache;
    private Companion.OnGenreChangedListener changeListener;
    private List<String> channelGenres;
    private String currentGenre;
    private final FontTextView filterView;
    private final NotificationView notifView;
    private Handler scrollHandler;
    private String title;
    private final TitleViewAdapter titleViewAdapter;

    public FilterHeroZoneView(Context context) {
        this(context, null, 0, 6, null);
    }

    public FilterHeroZoneView(Context context, AttributeSet attributeSet) {
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
    public FilterHeroZoneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.scrollHandler = new Handler();
        this.titleViewAdapter = new TitleViewAdapter() { // from class: com.dotscreen.portal.ui.views.FilterHeroZoneView$titleViewAdapter$1
            @Override // androidx.leanback.widget.TitleViewAdapter
            public View getSearchAffordanceView() {
                return null;
            }
        };
        View.inflate(context, R.layout.view_filter_herozone, this);
        View viewFindViewById = findViewById(R.id.notification_icon);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.notification_icon)");
        this.notifView = (NotificationView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.tabFilter);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.tabFilter)");
        FontTextView fontTextView = (FontTextView) viewFindViewById2;
        this.filterView = fontTextView;
        fontTextView.setText(context.getString(R.string.epg_tab_filter, context.getString(R.string.epg_filter_all)));
        fontTextView.setOnClickListener(new FilterHeroZoneView$$special$$inlined$run$lambda$1(fontTextView, this, context));
    }

    public /* synthetic */ FilterHeroZoneView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getCurrentGenre() {
        return this.currentGenre;
    }

    public final void setCurrentGenre(String str) {
        this.currentGenre = str;
    }

    public final List<String> getChannelGenres() {
        return this.channelGenres;
    }

    public final void setChannelGenres(List<String> list) {
        this.channelGenres = list;
    }

    public final Companion.OnGenreChangedListener getChangeListener() {
        return this.changeListener;
    }

    public final void setChangeListener(Companion.OnGenreChangedListener onGenreChangedListener) {
        this.changeListener = onGenreChangedListener;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(final String str) {
        this.title = str;
        this.scrollHandler.post(new Runnable() { // from class: com.dotscreen.portal.ui.views.FilterHeroZoneView$title$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView textView = (TextView) this.this$0.findViewById(R.id.title);
                if (textView != null) {
                    textView.setText(str);
                }
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        if (direction == 33) {
            return this.notifView;
        }
        if (direction == 17 || direction == 66) {
            if (Intrinsics.areEqual(focused, this.notifView)) {
                return this.notifView;
            }
        } else if (direction == 130 && Intrinsics.areEqual(focused, this.notifView)) {
            return this.filterView;
        }
        return super.focusSearch(focused, direction);
    }

    @Override // androidx.leanback.widget.TitleViewAdapter.Provider
    public TitleViewAdapter getTitleViewAdapter() {
        return this.titleViewAdapter;
    }
}
