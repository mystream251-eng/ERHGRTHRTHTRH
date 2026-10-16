package com.dotscreen.portal.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.dotscreen.megacable.portal.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PagerIndicatorView extends LinearLayout {
    private Integer numberOfPages;
    private List<PointPagerIndexView> pointsList;

    public PagerIndicatorView(Context context) {
        super(context);
        this.numberOfPages = 4;
        this.pointsList = null;
        setStaticTransformationsEnabled(true);
    }

    public PagerIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.numberOfPages = 4;
        this.pointsList = null;
        setStaticTransformationsEnabled(true);
        initWithNumberOfPages(this.numberOfPages);
        setSelectedPage(1);
    }

    public void initWithNumberOfPages(Integer num) {
        this.pointsList = new ArrayList();
        if (num.intValue() > 0) {
            this.numberOfPages = num;
            removeAllViews();
            setGravity(17);
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.pager_bull_off_dim);
            int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.pager_bull_padding);
            for (int i = 0; i < this.numberOfPages.intValue(); i++) {
                PointPagerIndexView pointPagerIndexView = new PointPagerIndexView(getContext());
                pointPagerIndexView.setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize));
                pointPagerIndexView.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
                this.pointsList.add(pointPagerIndexView);
                addView(pointPagerIndexView);
            }
            return;
        }
        removeAllViews();
    }

    public void setSelectedPage(Integer num) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.pager_bull_on_dim);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.pager_bull_padding);
        if (num.intValue() < 0 || num.intValue() >= this.numberOfPages.intValue() || num.intValue() >= this.pointsList.size()) {
            return;
        }
        unselectAllPages();
        PointPagerIndexView pointPagerIndexView = this.pointsList.get(num.intValue());
        pointPagerIndexView.setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize));
        pointPagerIndexView.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
        pointPagerIndexView.setSelected();
    }

    private void unselectAllPages() {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.pager_bull_off_dim);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.pager_bull_padding);
        for (PointPagerIndexView pointPagerIndexView : this.pointsList) {
            pointPagerIndexView.setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize));
            pointPagerIndexView.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
            pointPagerIndexView.setUnselected();
        }
    }
}
