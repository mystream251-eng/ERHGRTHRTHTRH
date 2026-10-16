package com.dotscreen.portal.ui.cards;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ads.Ad;
import com.dotscreen.portal.ui.views.AdBannerView;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NativeSwimlaneAdView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/portal/ui/cards/NativeSwimlaneAdView;", "Landroid/widget/RelativeLayout;", "dsNativeAdType", "Lcom/dotscreen/portal/ads/Ad$DSNativeAdType;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Lcom/dotscreen/portal/ads/Ad$DSNativeAdType;Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mAdBannerView", "Lcom/dotscreen/portal/ui/views/AdBannerView;", "nativeAd", "Lcom/dotscreen/portal/ads/Ad;", "setNativeAd", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NativeSwimlaneAdView extends RelativeLayout {
    private HashMap _$_findViewCache;
    private AdBannerView mAdBannerView;
    private Ad nativeAd;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Ad.DSNativeAdType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Ad.DSNativeAdType.CUSTOM.ordinal()] = 1;
            $EnumSwitchMapping$0[Ad.DSNativeAdType.IN_SWIMLANE.ordinal()] = 2;
        }
    }

    public NativeSwimlaneAdView(Ad.DSNativeAdType dSNativeAdType, Context context) {
        this(dSNativeAdType, context, null, 0, 12, null);
    }

    public NativeSwimlaneAdView(Ad.DSNativeAdType dSNativeAdType, Context context, AttributeSet attributeSet) {
        this(dSNativeAdType, context, attributeSet, 0, 8, null);
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

    public /* synthetic */ NativeSwimlaneAdView(Ad.DSNativeAdType dSNativeAdType, Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(dSNativeAdType, context, (i2 & 4) != 0 ? (AttributeSet) null : attributeSet, (i2 & 8) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeSwimlaneAdView(Ad.DSNativeAdType dsNativeAdType, Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(dsNativeAdType, "dsNativeAdType");
        Intrinsics.checkParameterIsNotNull(context, "context");
        int i2 = WhenMappings.$EnumSwitchMapping$0[dsNativeAdType.ordinal()];
        if (i2 == 1) {
            RelativeLayout.inflate(context, R.layout.card_custom_native_ad_bounding_box, this);
            View viewFindViewById = findViewById(R.id.card_custom_native_ad_bounding_box_ad_banner);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.card_c…d_bounding_box_ad_banner)");
            this.mAdBannerView = (AdBannerView) viewFindViewById;
            return;
        }
        if (i2 != 2) {
            return;
        }
        RelativeLayout.inflate(context, R.layout.card_in_swimlane_native_ad, this);
        View viewFindViewById2 = findViewById(R.id.card_in_swimlane_native_ad_ad_banner);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.card_i…lane_native_ad_ad_banner)");
        this.mAdBannerView = (AdBannerView) viewFindViewById2;
        setForeground(ContextCompat.getDrawable(context, R.drawable.item_selector));
        setBackground(ContextCompat.getDrawable(context, R.drawable.item_selector));
        View viewFindViewById3 = findViewById(R.id.root);
        if (viewFindViewById3 != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById3.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById3.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
        }
        setBackgroundColor(ContextCompat.getColor(context, R.color.black));
    }

    public final void setNativeAd(Ad nativeAd) {
        Intrinsics.checkParameterIsNotNull(nativeAd, "nativeAd");
        this.nativeAd = nativeAd;
        AdBannerView adBannerView = this.mAdBannerView;
        if (adBannerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdBannerView");
        }
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        Activity activity = ((PortalApplication) applicationContext).get_activeActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        String simpleName = activity.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "(context.applicationCont…ty!!.javaClass.simpleName");
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        Context applicationContext2 = context2.getApplicationContext();
        if (applicationContext2 != null) {
            Activity activity2 = ((PortalApplication) applicationContext2).get_activeActivity();
            if (activity2 == null) {
                Intrinsics.throwNpe();
            }
            AdBannerView.setAdImageResource$default(adBannerView, simpleName, activity2.hashCode(), nativeAd, (Ad.ImageResource) CollectionsKt.first((List) nativeAd.getImageResources()), 0, null, 32, null);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
    }
}
