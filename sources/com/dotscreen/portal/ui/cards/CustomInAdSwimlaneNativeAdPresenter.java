package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.leanback.widget.Presenter;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.ads.Ad;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultInSwimlaneNativeAdPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/cards/CustomInAdSwimlaneNativeAdPresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class CustomInAdSwimlaneNativeAdPresenter extends Presenter {
    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Resources resources;
        Resources resources2;
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Context context = parent.getContext();
        Ad.DSNativeAdType dSNativeAdType = Ad.DSNativeAdType.CUSTOM;
        Context context2 = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "parent.context");
        NativeSwimlaneAdView nativeSwimlaneAdView = new NativeSwimlaneAdView(dSNativeAdType, context2, null, 0, 12, null);
        nativeSwimlaneAdView.setLayoutParams(new ConstraintLayout.LayoutParams((context == null || (resources2 = context.getResources()) == null) ? 0 : (resources2.getDimensionPixelSize(R.dimen.home_apps_card_width) * 6) + 17, (context == null || (resources = context.getResources()) == null) ? 0 : resources.getDimensionPixelSize(R.dimen.home_card_height) * 3));
        nativeSwimlaneAdView.setFocusable(false);
        nativeSwimlaneAdView.setFocusableInTouchMode(false);
        return new Presenter.ViewHolder(nativeSwimlaneAdView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (item instanceof Ad) {
            Ad ad = (Ad) item;
            if (ad.getDsNativeAdType() == Ad.DSNativeAdType.CUSTOM) {
                View view = viewHolder.view;
                if (view == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.NativeSwimlaneAdView");
                }
                ((NativeSwimlaneAdView) view).setNativeAd(ad);
            }
        }
    }
}
