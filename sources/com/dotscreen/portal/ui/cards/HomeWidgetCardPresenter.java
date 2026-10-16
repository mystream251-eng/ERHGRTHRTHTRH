package com.dotscreen.portal.ui.cards;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.leanback.widget.Presenter;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.model.HomeWidget;

/* JADX INFO: loaded from: classes.dex */
public class HomeWidgetCardPresenter extends Presenter {
    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        HomeWidgetCardView homeWidgetCardView = new HomeWidgetCardView(viewGroup.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(viewGroup.getResources().getDimensionPixelSize(R.dimen.home_card_width), viewGroup.getResources().getDimensionPixelSize(R.dimen.home_card_height));
        layoutParams.setMargins(viewGroup.getResources().getDimensionPixelSize(R.dimen.home_card_margins_left), 0, viewGroup.getResources().getDimensionPixelSize(R.dimen.home_card_margins_right), 0);
        homeWidgetCardView.setLayoutParams(layoutParams);
        homeWidgetCardView.setFocusable(true);
        homeWidgetCardView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(homeWidgetCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        ((HomeWidgetCardView) viewHolder.view).setItem((HomeWidget) obj);
    }
}
