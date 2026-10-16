package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.leanback.widget.Presenter;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ads.Ad;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultInSwimlaneNativeAdPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/cards/DefaultInSwimlaneNativeAdPresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DefaultInSwimlaneNativeAdPresenter extends Presenter {
    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Resources resources;
        Resources resources2;
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Context context = parent.getContext();
        Ad.DSNativeAdType dSNativeAdType = Ad.DSNativeAdType.IN_SWIMLANE;
        Context context2 = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "parent.context");
        NativeSwimlaneAdView nativeSwimlaneAdView = new NativeSwimlaneAdView(dSNativeAdType, context2, null, 0, 12, null);
        int dimensionPixelSize = 0;
        int dimensionPixelSize2 = (context == null || (resources2 = context.getResources()) == null) ? 0 : resources2.getDimensionPixelSize(R.dimen.airing_card_width);
        if (context != null && (resources = context.getResources()) != null) {
            dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.airing_card_height);
        }
        nativeSwimlaneAdView.setLayoutParams(new ConstraintLayout.LayoutParams(dimensionPixelSize2, dimensionPixelSize));
        nativeSwimlaneAdView.setFocusable(true);
        nativeSwimlaneAdView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(nativeSwimlaneAdView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, final Object item) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (item instanceof Ad) {
            Ad ad = (Ad) item;
            if (ad.getDsNativeAdType() == Ad.DSNativeAdType.IN_SWIMLANE) {
                View view = viewHolder.view;
                if (view == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.NativeSwimlaneAdView");
                }
                NativeSwimlaneAdView nativeSwimlaneAdView = (NativeSwimlaneAdView) view;
                nativeSwimlaneAdView.setNativeAd(ad);
                nativeSwimlaneAdView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.dotscreen.portal.ui.cards.DefaultInSwimlaneNativeAdPresenter.onBindViewHolder.1
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View v, boolean z) {
                        if (z) {
                            Intrinsics.checkExpressionValueIsNotNull(v, "v");
                            Context context = v.getContext();
                            Intrinsics.checkExpressionValueIsNotNull(context, "v.context");
                            Context applicationContext = context.getApplicationContext();
                            if (applicationContext == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                            }
                            ((PortalApplication) applicationContext).handleInSwimlaneNativeAdSelection((Ad) item);
                            return;
                        }
                        Intrinsics.checkExpressionValueIsNotNull(v, "v");
                        Context context2 = v.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context2, "v.context");
                        Context applicationContext2 = context2.getApplicationContext();
                        if (applicationContext2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        ((PortalApplication) applicationContext2).hideAllDismissibleFloatingAds();
                    }
                });
            }
        }
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        NativeSwimlaneAdView nativeSwimlaneAdView = (NativeSwimlaneAdView) (viewHolder != null ? viewHolder.view : null);
        if (nativeSwimlaneAdView != null) {
            nativeSwimlaneAdView.setOnFocusChangeListener((View.OnFocusChangeListener) null);
        }
    }
}
