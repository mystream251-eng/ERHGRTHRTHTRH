package com.dotscreen.portal.ui.views;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import androidx.leanback.widget.CollectionGridView;
import androidx.leanback.widget.CollectionLayoutManager;
import com.dotscreen.megacable.portal.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: HomeHeroZoneCollectionGridView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/portal/ui/views/HomeHeroZoneCollectionGridView;", "Landroidx/leanback/widget/CollectionGridView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "myHandler", "Landroid/os/Handler;", "value", "numberOfPosterItems", "getNumberOfPosterItems", "()I", "setNumberOfPosterItems", "(I)V", "numberOfStillItems", "getNumberOfStillItems", "setNumberOfStillItems", "createLayoutManager", "Landroidx/leanback/widget/CollectionLayoutManager;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HomeHeroZoneCollectionGridView extends CollectionGridView {
    private HashMap _$_findViewCache;
    private final Handler myHandler;
    private int numberOfPosterItems;
    private int numberOfStillItems;

    public HomeHeroZoneCollectionGridView(Context context) {
        this(context, null, 0, 6, null);
    }

    public HomeHeroZoneCollectionGridView(Context context, AttributeSet attributeSet) {
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

    public HomeHeroZoneCollectionGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.myHandler = new Handler();
        setClipChildren(false);
        this.numberOfPosterItems = 10;
        this.numberOfStillItems = 4;
    }

    public /* synthetic */ HomeHeroZoneCollectionGridView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final int getNumberOfPosterItems() {
        return this.numberOfPosterItems;
    }

    public final void setNumberOfPosterItems(int i) {
        this.numberOfPosterItems = i;
        this.myHandler.post(new Runnable() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneCollectionGridView$numberOfPosterItems$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.requestLayout();
            }
        });
    }

    public final int getNumberOfStillItems() {
        return this.numberOfStillItems;
    }

    public final void setNumberOfStillItems(int i) {
        this.numberOfStillItems = i;
        this.myHandler.post(new Runnable() { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneCollectionGridView$numberOfStillItems$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.requestLayout();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.leanback.widget.CollectionGridView
    protected CollectionLayoutManager createLayoutManager() {
        return new CollectionLayoutManager(this, 0, 0 == true ? 1 : 0) { // from class: com.dotscreen.portal.ui.views.HomeHeroZoneCollectionGridView.createLayoutManager.1
            private final float margin;
            private final float landscapeItemRatio = 1.7777778f;
            private final float portraitItemRatio = 0.6666667f;

            {
                this.margin = HomeHeroZoneCollectionGridView.this.getResources().getDimension(R.dimen.lb_browse_item_vertical_spacing);
            }

            private final float getHeight(int divider) {
                float totalSize = getTotalSize();
                float f = 3;
                float f2 = this.margin;
                float f3 = (totalSize - (f * f2)) / 4.0f;
                if (divider == 1) {
                    return totalSize;
                }
                if (divider == 2) {
                    return (2 * f3) + f2;
                }
                if (divider == 3) {
                    return (f * f3) + (2 * f2);
                }
                if (divider != 4) {
                    return 0.0f;
                }
                return f3;
            }

            @Override // androidx.leanback.widget.CollectionLayoutManager
            protected List<Rect> buildLayout() {
                ArrayList arrayList = new ArrayList();
                Rect rectCreateRect = CollectionLayoutManager.createRect(0.0f, 0.0f, this.landscapeItemRatio * getHeight(1), getHeight(1));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect, "createRect(\n            …            getHeight(1))");
                arrayList.add(rectCreateRect);
                float fWidth = ((Rect) CollectionsKt.last((List) arrayList)).width() + this.margin + 0.0f;
                Rect rectCreateRect2 = CollectionLayoutManager.createRect(fWidth, 0.0f, this.portraitItemRatio * getHeight(2), getHeight(2));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect2, "createRect(\n            …            getHeight(2))");
                arrayList.add(rectCreateRect2);
                Rect rectCreateRect3 = CollectionLayoutManager.createRect(fWidth, getHeight(2) + this.margin, this.portraitItemRatio * getHeight(2), getHeight(2));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect3, "createRect(\n            …            getHeight(2))");
                arrayList.add(rectCreateRect3);
                float fWidth2 = fWidth + ((Rect) CollectionsKt.last((List) arrayList)).width() + this.margin;
                Rect rectCreateRect4 = CollectionLayoutManager.createRect(fWidth2, 0.0f, this.portraitItemRatio * getHeight(2), getHeight(2));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect4, "createRect(\n            …            getHeight(2))");
                arrayList.add(rectCreateRect4);
                Rect rectCreateRect5 = CollectionLayoutManager.createRect(fWidth2, getHeight(2) + this.margin, this.portraitItemRatio * getHeight(2), getHeight(2));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect5, "createRect(\n            …            getHeight(2))");
                arrayList.add(rectCreateRect5);
                float fWidth3 = fWidth2 + ((Rect) CollectionsKt.last((List) arrayList)).width() + this.margin;
                Rect rectCreateRect6 = CollectionLayoutManager.createRect(fWidth3, 0.0f, (this.portraitItemRatio * 2.0f * getHeight(2)) + this.margin, getHeight(4));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect6, "createRect(\n            …            getHeight(4))");
                arrayList.add(rectCreateRect6);
                Rect rectCreateRect7 = CollectionLayoutManager.createRect(fWidth3, getHeight(4) + this.margin, (this.portraitItemRatio * 2.0f * getHeight(2)) + this.margin, getHeight(4));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect7, "createRect(\n            …            getHeight(4))");
                arrayList.add(rectCreateRect7);
                Rect rectCreateRect8 = CollectionLayoutManager.createRect(fWidth3, getHeight(2) + this.margin, this.portraitItemRatio * getHeight(2), getHeight(2));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect8, "createRect(\n            …            getHeight(2))");
                arrayList.add(rectCreateRect8);
                Rect rectCreateRect9 = CollectionLayoutManager.createRect((this.portraitItemRatio * getHeight(2)) + fWidth3 + this.margin, getHeight(2) + this.margin, this.portraitItemRatio * getHeight(2), getHeight(2));
                Intrinsics.checkExpressionValueIsNotNull(rectCreateRect9, "createRect(\n            …            getHeight(2))");
                arrayList.add(rectCreateRect9);
                float f = 2;
                float height = fWidth3 + (this.portraitItemRatio * 2.0f * getHeight(2)) + (this.margin * f);
                IntProgression intProgressionStep = RangesKt.step(RangesKt.until(6, HomeHeroZoneCollectionGridView.this.getNumberOfPosterItems()), 2);
                int first = intProgressionStep.getFirst();
                int last = intProgressionStep.getLast();
                int step = intProgressionStep.getStep();
                if (step < 0 ? first >= last : first <= last) {
                    while (true) {
                        Rect rectCreateRect10 = CollectionLayoutManager.createRect(height, 0.0f, this.portraitItemRatio * getHeight(2), getHeight(2));
                        Intrinsics.checkExpressionValueIsNotNull(rectCreateRect10, "createRect(\n            …            getHeight(2))");
                        arrayList.add(rectCreateRect10);
                        Rect rectCreateRect11 = CollectionLayoutManager.createRect(height, getHeight(2) + this.margin, this.portraitItemRatio * getHeight(2), getHeight(2));
                        Intrinsics.checkExpressionValueIsNotNull(rectCreateRect11, "createRect(\n            …            getHeight(2))");
                        arrayList.add(rectCreateRect11);
                        height += ((Rect) CollectionsKt.last((List) arrayList)).width() + this.margin;
                        if (first == last) {
                            break;
                        }
                        first += step;
                    }
                }
                IntProgression intProgressionStep2 = RangesKt.step(RangesKt.until(0, HomeHeroZoneCollectionGridView.this.getNumberOfStillItems()), 2);
                int first2 = intProgressionStep2.getFirst();
                int last2 = intProgressionStep2.getLast();
                int step2 = intProgressionStep2.getStep();
                if (step2 < 0 ? first2 >= last2 : first2 <= last2) {
                    while (true) {
                        Rect rectCreateRect12 = CollectionLayoutManager.createRect(height, 0.0f, (this.portraitItemRatio * 2.0f * getHeight(2)) + this.margin, getHeight(2));
                        Intrinsics.checkExpressionValueIsNotNull(rectCreateRect12, "createRect(\n            …            getHeight(2))");
                        arrayList.add(rectCreateRect12);
                        Rect rectCreateRect13 = CollectionLayoutManager.createRect(height, getHeight(2) + this.margin, (this.portraitItemRatio * 2.0f * getHeight(2)) + this.margin, getHeight(2));
                        Intrinsics.checkExpressionValueIsNotNull(rectCreateRect13, "createRect(\n            …            getHeight(2))");
                        arrayList.add(rectCreateRect13);
                        height += (this.portraitItemRatio * 2.0f * getHeight(2)) + (this.margin * f);
                        if (first2 == last2) {
                            break;
                        }
                        first2 += step2;
                    }
                }
                return arrayList;
            }
        };
    }
}
