package com.dotscreen.portal.ui.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HorizontalGridPresenter;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.TitleViewAdapter;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.components.NotificationView;
import com.dotscreen.portal.ui.cards.HomePresenterSelector;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighlightsHeroZoneView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0087\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000*\u0001=\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010?\u001a\u00020@H\u0002J\u001c\u0010A\u001a\u0004\u0018\u00010B2\b\u0010C\u001a\u0004\u0018\u00010B2\u0006\u0010D\u001a\u00020\bH\u0016J\b\u0010E\u001a\u00020;H\u0016J\b\u0010F\u001a\u00020@H\u0014J\u001a\u0010G\u001a\u00020\r2\u0006\u0010D\u001a\u00020\b2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R4\u0010(\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R*\u0010-\u001a\u0004\u0018\u00010\b2\b\u0010%\u001a\u0004\u0018\u00010\b@FX\u0086\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R(\u00107\u001a\u0004\u0018\u00010\u001c2\b\u0010%\u001a\u0004\u0018\u00010\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u001e\"\u0004\b9\u0010 R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0004\n\u0002\u0010>¨\u0006J"}, d2 = {"Lcom/dotscreen/portal/ui/views/HighlightsHeroZoneView;", "Landroid/widget/RelativeLayout;", "Landroidx/leanback/widget/TitleViewAdapter$Provider;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "adultBrowsing", "", "getAdultBrowsing", "()Z", "setAdultBrowsing", "(Z)V", "autoScroll", "getAutoScroll", "setAutoScroll", "autoScrollInterval", "", "getAutoScrollInterval", "()J", "setAutoScrollInterval", "(J)V", "catalogType", "", "getCatalogType", "()Ljava/lang/String;", "setCatalogType", "(Ljava/lang/String;)V", "gridDock", "Landroid/widget/FrameLayout;", "gridView", "Landroidx/leanback/widget/HorizontalGridView;", "value", "", "Lcom/dotscreen/bokit/model/BrowsableItem;", "items", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "itemsCount", "getItemsCount", "()Ljava/lang/Integer;", "setItemsCount", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "notifView", "Lcom/dotscreen/portal/components/NotificationView;", "scrollHandler", "Landroid/os/Handler;", "title", "getTitle", "setTitle", "titleViewAdapter", "Landroidx/leanback/widget/TitleViewAdapter;", "updatePosition", "com/dotscreen/portal/ui/views/HighlightsHeroZoneView$updatePosition$1", "Lcom/dotscreen/portal/ui/views/HighlightsHeroZoneView$updatePosition$1;", "createGrid", "", "focusSearch", "Landroid/view/View;", "focused", "direction", "getTitleViewAdapter", "onDetachedFromWindow", "requestFocus", "previouslyFocusedRect", "Landroid/graphics/Rect;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HighlightsHeroZoneView extends RelativeLayout implements TitleViewAdapter.Provider {
    private HashMap _$_findViewCache;
    private ArrayObjectAdapter adapter;
    private boolean adultBrowsing;
    private boolean autoScroll;
    private long autoScrollInterval;
    private String catalogType;
    private final FrameLayout gridDock;
    private HorizontalGridView gridView;
    private List<? extends BrowsableItem> items;
    private Integer itemsCount;
    private final NotificationView notifView;
    private Handler scrollHandler;
    private String title;
    private final TitleViewAdapter titleViewAdapter;
    private final HighlightsHeroZoneView$updatePosition$1 updatePosition;

    public HighlightsHeroZoneView(Context context) {
        this(context, null, 0, 6, null);
    }

    public HighlightsHeroZoneView(Context context, AttributeSet attributeSet) {
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
    /* JADX WARN: Type inference failed for: r2v9, types: [com.dotscreen.portal.ui.views.HighlightsHeroZoneView$updatePosition$1] */
    public HighlightsHeroZoneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.scrollHandler = new Handler();
        this.catalogType = "vod";
        this.titleViewAdapter = new TitleViewAdapter() { // from class: com.dotscreen.portal.ui.views.HighlightsHeroZoneView$titleViewAdapter$1
            @Override // androidx.leanback.widget.TitleViewAdapter
            public View getSearchAffordanceView() {
                return null;
            }
        };
        Log.v("HighlightsHeroZoneView", "HighlightsHeroZoneView initialize");
        View.inflate(context, R.layout.view_highlights_herozone, this);
        View viewFindViewById = findViewById(R.id.grid_dock);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.grid_dock)");
        this.gridDock = (FrameLayout) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.notification_icon);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.notification_icon)");
        this.notifView = (NotificationView) viewFindViewById2;
        this.autoScroll = true;
        this.autoScrollInterval = 4000L;
        this.updatePosition = new Runnable() { // from class: com.dotscreen.portal.ui.views.HighlightsHeroZoneView$updatePosition$1
            @Override // java.lang.Runnable
            public void run() {
                if (this.this$0.gridView != null) {
                    HorizontalGridView horizontalGridView = this.this$0.gridView;
                    if (horizontalGridView == null) {
                        Intrinsics.throwNpe();
                    }
                    if (horizontalGridView.getFocusedChild() == null) {
                        HorizontalGridView horizontalGridView2 = this.this$0.gridView;
                        if (horizontalGridView2 == null) {
                            Intrinsics.throwNpe();
                        }
                        int selectedPosition = horizontalGridView2.getSelectedPosition();
                        List<BrowsableItem> items = this.this$0.getItems();
                        if (items == null) {
                            Intrinsics.throwNpe();
                        }
                        if (selectedPosition < items.size() - 1) {
                            HorizontalGridView horizontalGridView3 = this.this$0.gridView;
                            if (horizontalGridView3 == null) {
                                Intrinsics.throwNpe();
                            }
                            horizontalGridView3.smoothScrollToPosition(selectedPosition + 1);
                        } else {
                            List<BrowsableItem> items2 = this.this$0.getItems();
                            if (items2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (selectedPosition == items2.size() - 1) {
                                HorizontalGridView horizontalGridView4 = this.this$0.gridView;
                                if (horizontalGridView4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                horizontalGridView4.setSelectedPosition(0);
                                HorizontalGridView horizontalGridView5 = this.this$0.gridView;
                                if (horizontalGridView5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                horizontalGridView5.smoothScrollToPosition(0);
                            }
                        }
                    }
                }
                this.this$0.scrollHandler.postDelayed(this, this.this$0.getAutoScrollInterval());
            }
        };
    }

    public /* synthetic */ HighlightsHeroZoneView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getCatalogType() {
        return this.catalogType;
    }

    public final void setCatalogType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.catalogType = str;
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
            ArrayObjectAdapter arrayObjectAdapter = this.adapter;
            if ((arrayObjectAdapter != null ? arrayObjectAdapter.size() : 0) > 0) {
                return this.gridDock;
            }
            try {
                return focusSearch(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
            } catch (NullPointerException unused) {
                return null;
            }
        }
        return super.focusSearch(focused, direction);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if ((arrayObjectAdapter != null ? arrayObjectAdapter.size() : 0) == 0 && direction == 33) {
            this.notifView.requestFocus();
            return false;
        }
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createGrid() {
        if (this.adapter != null) {
            return;
        }
        View viewFindViewById = findViewById(R.id.grid_dock);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.grid_dock)");
        ViewGroup viewGroup = (ViewGroup) viewFindViewById;
        HomePresenterSelector homePresenterSelector = new HomePresenterSelector(Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.highlight_card_view_width)), Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.highlight_card_view_height)), Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.highlight_card_view_width)), Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.highlight_card_view_height)), Integer.valueOf(R.drawable.ph_header), Boolean.valueOf(this.adultBrowsing), null, 64, null);
        HorizontalGridPresenter horizontalGridPresenter = new HorizontalGridPresenter(0, false);
        horizontalGridPresenter.setNumberOfRows(1);
        horizontalGridPresenter.setShadowEnabled(true);
        horizontalGridPresenter.setKeepChildForeground(false);
        horizontalGridPresenter.enableChildRoundedCorners(true);
        this.adapter = new ArrayObjectAdapter(homePresenterSelector);
        HorizontalGridPresenter.ViewHolder viewHolderOnCreateViewHolder = horizontalGridPresenter.onCreateViewHolder(viewGroup);
        Intrinsics.checkExpressionValueIsNotNull(viewHolderOnCreateViewHolder, "presenter.onCreateViewHolder(gridDock)");
        this.gridView = (HorizontalGridView) viewHolderOnCreateViewHolder.view;
        viewGroup.addView(viewHolderOnCreateViewHolder.view);
        horizontalGridPresenter.onBindViewHolder(viewHolderOnCreateViewHolder, this.adapter);
        horizontalGridPresenter.setOnItemViewClickedListener(new OnItemViewClickedListener() { // from class: com.dotscreen.portal.ui.views.HighlightsHeroZoneView.createGrid.1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                if (obj instanceof BrowsableItem) {
                    BrowsableItem browsableItem = (BrowsableItem) obj;
                    String str = browsableItem.getExternalIds().get(ItemExternalIdScheme.GenericDeeplink);
                    if (str == null) {
                        str = browsableItem.getExternalIds().get(ItemExternalIdScheme.Netflix);
                    }
                    if (str == null) {
                        str = browsableItem.getExternalIds().get(ItemExternalIdScheme.AmazonPrime);
                    }
                    if (str != null) {
                        try {
                            HighlightsHeroZoneView.this.getContext().startActivity(CommonUtils.patchInteractiveIntent(new Intent("android.intent.action.VIEW", Uri.parse(str))));
                            return;
                        } catch (Throwable th) {
                            th.printStackTrace();
                            return;
                        }
                    }
                    Context context = HighlightsHeroZoneView.this.getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    VODCatalogService vodCatalogService = ((PortalApplication) applicationContext).getBOService().getVodCatalogService();
                    if (vodCatalogService == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
                    }
                    MegacableVODCatalogService.registerClick$default((MegacableVODCatalogService) vodCatalogService, browsableItem, null, null, Constants.INSTANCE.itemEngineType(browsableItem), 6, null).subscribe(new Action() { // from class: com.dotscreen.portal.ui.views.HighlightsHeroZoneView.createGrid.1.2
                        @Override // io.reactivex.functions.Action
                        public final void run() {
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.views.HighlightsHeroZoneView.createGrid.1.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable th2) {
                        }
                    });
                    HighlightsHeroZoneView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/" + HighlightsHeroZoneView.this.getCatalogType() + "/details?content_id=" + browsableItem.getId() + "&adult_browsing=" + HighlightsHeroZoneView.this.getAdultBrowsing())));
                }
            }
        });
        if (this.autoScroll) {
            this.scrollHandler.postDelayed(this.updatePosition, this.autoScrollInterval);
        }
    }

    public final boolean getAdultBrowsing() {
        return this.adultBrowsing;
    }

    public final void setAdultBrowsing(boolean z) {
        this.adultBrowsing = z;
    }

    public final Integer getItemsCount() {
        return this.itemsCount;
    }

    public final void setItemsCount(Integer num) {
        this.itemsCount = num;
    }

    public final List<BrowsableItem> getItems() {
        return this.items;
    }

    public final void setItems(final List<? extends BrowsableItem> list) {
        this.items = list;
        if (list != null) {
            this.scrollHandler.post(new Runnable() { // from class: com.dotscreen.portal.ui.views.HighlightsHeroZoneView$items$1
                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.createGrid();
                    List list2 = list;
                    if (list2 != null) {
                        int size = list2.size();
                        for (int i = 0; i < size; i++) {
                            ArrayObjectAdapter arrayObjectAdapter = this.this$0.adapter;
                            if (arrayObjectAdapter != null) {
                                arrayObjectAdapter.add(list.get(i));
                            }
                        }
                    }
                }
            });
        }
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(final String str) {
        this.title = str;
        this.scrollHandler.post(new Runnable() { // from class: com.dotscreen.portal.ui.views.HighlightsHeroZoneView$title$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView textView = (TextView) this.this$0.findViewById(R.id.title);
                if (textView != null) {
                    textView.setText(str);
                }
            }
        });
    }

    public final boolean getAutoScroll() {
        return this.autoScroll;
    }

    public final void setAutoScroll(boolean z) {
        this.autoScroll = z;
    }

    public final long getAutoScrollInterval() {
        return this.autoScrollInterval;
    }

    public final void setAutoScrollInterval(long j) {
        this.autoScrollInterval = j;
    }

    @Override // androidx.leanback.widget.TitleViewAdapter.Provider
    public TitleViewAdapter getTitleViewAdapter() {
        return this.titleViewAdapter;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.scrollHandler.removeCallbacks(this.updatePosition);
    }
}
