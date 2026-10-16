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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/cards/CustomInSettingsSwimlaneNativeAdPresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CustomInSettingsSwimlaneNativeAdPresenter extends Presenter {
    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Context context = parent.getContext();
        Ad.DSNativeAdType dSNativeAdType = Ad.DSNativeAdType.CUSTOM;
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        NativeSwimlaneAdView nativeSwimlaneAdView = new NativeSwimlaneAdView(dSNativeAdType, context, null, 0, 12, null);
        Resources resources = context.getResources();
        int dimensionPixelSize = resources != null ? resources.getDimensionPixelSize(R.dimen.settings_card_height) * 4 : 0;
        Resources resources2 = context.getResources();
        nativeSwimlaneAdView.setLayoutParams(new ConstraintLayout.LayoutParams(dimensionPixelSize, resources2 != null ? resources2.getDimensionPixelSize(R.dimen.settings_card_height) : 0));
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
