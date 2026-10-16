package com.dotscreen.portal.ui.views;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.leanback.app.BrowseSupportFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ads.Ad;
import com.dotscreen.portal.ads.TargetedAdsConfigProvider;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.utils.String_ExtensionKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AdBannerViews.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0012\u0010A\u001a\u0004\u0018\u00010B2\u0006\u0010C\u001a\u00020DH\u0016J\u0010\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010G\u001a\u00020\u0019J\u0012\u0010H\u001a\u00020\t2\b\u0010I\u001a\u0004\u0018\u00010BH\u0016J\b\u0010J\u001a\u0004\u0018\u000103J\u0012\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020NH\u0016J\u0006\u0010O\u001a\u00020'J\u0006\u0010P\u001a\u00020)J=\u0010Q\u001a\u0004\u0018\u00010)2\u0006\u0010R\u001a\u00020\u001f2\b\u0010S\u001a\u0004\u0018\u00010\u001f2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020'0U2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020)0UH\u0007¢\u0006\u0002\u0010WJ\u0094\u0001\u0010X\u001a\u00020'2\u0006\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00104\u001a\u0002032\u0006\u00109\u001a\u00020\t2d\b\u0002\u0010\u001d\u001a^\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\t¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0013\u0012\u00110%¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020'\u0018\u00010\u001eJ\u0006\u0010Y\u001a\u00020'R(\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000Rj\u0010\u001d\u001a^\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\t¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0013\u0012\u00110%¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020'\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010$\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u00102R\u0010\u0010#\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R(\u00104\u001a\u0004\u0018\u0001032\b\u0010\u000f\u001a\u0004\u0018\u000103@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0012\u00109\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u00102R(\u0010\"\u001a\u0004\u0018\u00010\u001f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u001f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0012\u0010>\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0004\n\u0002\u0010?R\u000e\u0010@\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lcom/dotscreen/portal/ui/views/AdBannerView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "networkStatus", "Lcom/dotscreen/farkit/network/NetworkStatus;", "(Landroid/content/Context;Landroid/util/AttributeSet;IILcom/dotscreen/farkit/network/NetworkStatus;)V", "value", "Lcom/dotscreen/portal/ads/Ad;", "ad", "getAd", "()Lcom/dotscreen/portal/ads/Ad;", "setAd", "(Lcom/dotscreen/portal/ads/Ad;)V", "adImageView", "Landroid/widget/ImageView;", "debugInfoContainer", "Landroid/view/View;", "debugTextview", "Landroid/widget/TextView;", "debugTextview2", "hideBannerCountDownCallback", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", "runtimeTag", "owningActivityName", "owningActivityHashcode", "", "whenResourceDisappearsInSec", "", "imageFinishedLoading", "", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "getNetworkStatus", "()Lcom/dotscreen/farkit/network/NetworkStatus;", "onGlobalLayoutListener", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "Ljava/lang/Integer;", "Lcom/dotscreen/portal/ads/Ad$ImageResource;", "resource", "getResource", "()Lcom/dotscreen/portal/ads/Ad$ImageResource;", "setResource", "(Lcom/dotscreen/portal/ads/Ad$ImageResource;)V", "resourceIndex", "getRuntimeTag", "()Ljava/lang/String;", "setRuntimeTag", "(Ljava/lang/String;)V", "trackingSent", "Ljava/lang/Boolean;", "widgetView", "drawableToBitmap", "Landroid/graphics/Bitmap;", "drawable", "Landroid/graphics/drawable/Drawable;", "getActivity", "Landroid/app/Activity;", "view", "getDominantColor", "bitmap", "getImageResource", "getVisibleFragment", "Landroidx/fragment/app/Fragment;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "hideBanner", "isCurrentlyVisibleOnScreen", "sendTrackingInfo", "adUnitId", "dsAdUnitId", "onSuccess", "Lkotlin/Function0;", "checkIfTrackingAlreadySent", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Boolean;", "setAdImageResource", "tryStartingBannerHidingCountdown", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class AdBannerView extends RelativeLayout {
    private HashMap _$_findViewCache;
    private Ad ad;
    private final ImageView adImageView;
    private final View debugInfoContainer;
    private final TextView debugTextview;
    private final TextView debugTextview2;
    private Function4<? super String, ? super String, ? super Integer, ? super Long, Unit> hideBannerCountDownCallback;
    private boolean imageFinishedLoading;
    private final ReentrantLock lock;
    private final NetworkStatus networkStatus;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private Integer owningActivityHashcode;
    private String owningActivityName;
    private Ad.ImageResource resource;
    private Integer resourceIndex;
    private String runtimeTag;
    private Boolean trackingSent;
    private final View widgetView;

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
    public AdBannerView(Context context, AttributeSet attributeSet, int i, int i2, NetworkStatus networkStatus) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.networkStatus = networkStatus;
        View viewInflate = View.inflate(getContext(), R.layout.view_ad_banner, this);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        ViewGroup viewGroup = (ViewGroup) viewInflate;
        this.widgetView = viewGroup;
        View viewFindViewById = viewGroup.findViewById(R.id.ad_banner_text_view_debug_info);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "widgetView.findViewById(…ner_text_view_debug_info)");
        this.debugTextview = (TextView) viewFindViewById;
        View viewFindViewById2 = this.widgetView.findViewById(R.id.ad_banner_text_view_debug_info2);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "widgetView.findViewById(…er_text_view_debug_info2)");
        this.debugTextview2 = (TextView) viewFindViewById2;
        View viewFindViewById3 = this.widgetView.findViewById(R.id.ad_banner_debug_info_container);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "widgetView.findViewById(…ner_debug_info_container)");
        this.debugInfoContainer = viewFindViewById3;
        View viewFindViewById4 = this.widgetView.findViewById(R.id.ad_banner_image_view);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "widgetView.findViewById(R.id.ad_banner_image_view)");
        this.adImageView = (ImageView) viewFindViewById4;
        setVisibility(8);
        getWindowSystemUiVisibility();
        if (TargetedAdsConfigProvider.INSTANCE.shouldDisplayAdBannerBounds()) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            if (((PortalApplication) applicationContext).getConfig().getValue() != null) {
                setBackgroundColor(context.getColor(R.color.white25));
            }
        }
        this.lock = new ReentrantLock();
    }

    public /* synthetic */ AdBannerView(Context context, AttributeSet attributeSet, int i, int i2, NetworkStatus networkStatus, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? (NetworkStatus) null : networkStatus);
    }

    public final NetworkStatus getNetworkStatus() {
        return this.networkStatus;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AdBannerView(Context context) {
        this(context, null, 0, 0, null, 16, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AdBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0, null, 16, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AdBannerView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0, null, 16, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    public final String getRuntimeTag() {
        return this.runtimeTag;
    }

    private final void setRuntimeTag(String str) {
        this.runtimeTag = str;
    }

    public final Ad getAd() {
        return this.ad;
    }

    private final void setAd(Ad ad) {
        this.ad = ad;
    }

    public final Ad.ImageResource getResource() {
        return this.resource;
    }

    private final void setResource(Ad.ImageResource imageResource) {
        this.resource = imageResource;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmapCreateBitmap;
        Intrinsics.checkParameterIsNotNull(drawable, "drawable");
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public int getDominantColor(Bitmap bitmap) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        Intrinsics.checkExpressionValueIsNotNull(bitmapCreateScaledBitmap, "Bitmap.createScaledBitmap(bitmap, 1, 1, true)");
        int pixel = bitmapCreateScaledBitmap.getPixel(0, 0);
        bitmapCreateScaledBitmap.recycle();
        return pixel;
    }

    /* JADX INFO: renamed from: getImageResource, reason: from getter */
    public final Ad.ImageResource getResource() {
        return this.resource;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void setAdImageResource$default(AdBannerView adBannerView, String str, int i, Ad ad, Ad.ImageResource imageResource, int i2, Function4 function4, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setAdImageResource");
        }
        if ((i3 & 32) != 0) {
            function4 = (Function4) null;
        }
        adBannerView.setAdImageResource(str, i, ad, imageResource, i2, function4);
    }

    public final void setAdImageResource(String owningActivityName, int owningActivityHashcode, Ad ad, Ad.ImageResource resource, int resourceIndex, Function4<? super String, ? super String, ? super Integer, ? super Long, Unit> hideBannerCountDownCallback) {
        String str;
        Intrinsics.checkParameterIsNotNull(owningActivityName, "owningActivityName");
        Intrinsics.checkParameterIsNotNull(ad, "ad");
        Intrinsics.checkParameterIsNotNull(resource, "resource");
        setVisibility(0);
        setRuntimeTag("VO-" + ad.getAdUnitId() + '-' + resourceIndex);
        setContentDescription(this.runtimeTag);
        this.owningActivityName = owningActivityName;
        this.owningActivityHashcode = Integer.valueOf(owningActivityHashcode);
        setAd(ad);
        setResource(resource);
        this.resourceIndex = Integer.valueOf(resourceIndex);
        this.hideBannerCountDownCallback = hideBannerCountDownCallback;
        String resourceURL = resource.getResourceURL();
        if (TargetedAdsConfigProvider.INSTANCE.shouldFetchImagesFromUIOP()) {
            String resourceURL2 = resource.getResourceURL();
            int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) resourceURL2, '/', 0, false, 6, (Object) null) + 1;
            if (resourceURL2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = resourceURL2.substring(iLastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            resourceURL = CommonUtils.TARGET_ADVERTISING_UIOP_IMG_API + '/' + StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removeSuffix(resourceURL2, (CharSequence) ('/' + strSubstring)), (CharSequence) "https://"), (CharSequence) "http://"), (CharSequence) "www.") + '/' + Uri.encode(strSubstring);
        }
        String start = resource.getTrackingURLs().getStart();
        RequestManager requestManagerWith = Glide.with(getContext());
        Intrinsics.checkExpressionValueIsNotNull(requestManagerWith, "Glide.with(context)");
        if (String_ExtensionKt.isGif(resourceURL)) {
            requestManagerWith.asGif();
        }
        DSLog.INSTANCE.adLogM("[LOAD AD IMAGE][VO-" + ad.getAdUnitId() + "] -> uiop=" + TargetedAdsConfigProvider.INSTANCE.shouldFetchImagesFromUIOP() + " url=" + resourceURL + ' ');
        requestManagerWith.load(resourceURL).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(ad.getDsNativeAdType() != Ad.DSNativeAdType.IN_SWIMLANE ? -1 : R.drawable.placeholder_horizontal).error(ad.getDsNativeAdType() == Ad.DSNativeAdType.IN_SWIMLANE ? R.drawable.placeholder_horizontal : -1).listener(new AnonymousClass1(ad, start)).into(this.adImageView);
        if (TargetedAdsConfigProvider.INSTANCE.shouldDisplayDebugInfo()) {
            this.debugInfoContainer.setVisibility(0);
            this.debugTextview.setTextSize(TargetedAdsConfigProvider.INSTANCE.getDebugInfoTextSize());
            this.debugTextview2.setTextSize(TargetedAdsConfigProvider.INSTANCE.getDebugInfoTextSize());
            TextView textView = this.debugTextview;
            StringBuilder sb = new StringBuilder();
            sb.append("DS:");
            sb.append(ad.getDsAdUnitId());
            sb.append('\n');
            sb.append("VO:");
            sb.append(ad.getAdUnitId());
            sb.append('\n');
            sb.append(StringsKt.repeat("=", ad.getAdUnitId().length()));
            sb.append('\n');
            if (resource.getWhenResourceIsDisplayed() != Ad.DisplayTrigger.OnOffset) {
                str = "";
            } else {
                str = "displayedOffset  =  " + resource.getWhenResourceIsDisplayedOffset() + '\n';
            }
            sb.append(str);
            sb.append("display  =   ");
            sb.append(resource.getResourceTemplate().getWidth());
            sb.append("px X ");
            sb.append(resource.getResourceTemplate().getHeight());
            sb.append("px  |  ");
            sb.append(resource.getResourceTemplate().getPosition());
            sb.append('-');
            sb.append(resource.getWhenResourceIsDisplayed());
            sb.append("  |  ");
            sb.append(resource.getResourceTemplate().getVertical());
            sb.append('+');
            sb.append(resource.getResourceTemplate().getVerticalOffset());
            sb.append("  ");
            sb.append(resource.getResourceTemplate().getHorizontal());
            sb.append('+');
            sb.append(resource.getResourceTemplate().getHorizontalOffset());
            sb.append('\n');
            sb.append("trackingEnabled  =   ");
            sb.append(this.trackingSent != null);
            textView.setText(sb.toString());
            this.debugTextview2.setText(getClass().getSimpleName());
            return;
        }
        this.debugInfoContainer.setVisibility(8);
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.views.AdBannerView$setAdImageResource$1, reason: invalid class name */
    /* JADX INFO: compiled from: AdBannerViews.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J4\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J>\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016¨\u0006\u0010"}, d2 = {"com/dotscreen/portal/ui/views/AdBannerView$setAdImageResource$1", "Lcom/bumptech/glide/request/RequestListener;", "Landroid/graphics/drawable/Drawable;", "onLoadFailed", "", "e", "Lcom/bumptech/glide/load/engine/GlideException;", "model", "", "target", "Lcom/bumptech/glide/request/target/Target;", "isFirstResource", "onResourceReady", "resource", "dataSource", "Lcom/bumptech/glide/load/DataSource;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class AnonymousClass1 implements RequestListener<Drawable> {
        final /* synthetic */ Ad $ad;
        final /* synthetic */ String $trackingURL;

        AnonymousClass1(Ad ad, String str) {
            this.$ad = ad;
            this.$trackingURL = str;
        }

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            DSLog.INSTANCE.adLogM("[LOAD AD IMAGE][VO-" + this.$ad.getAdUnitId() + "] -> SUCCESS ");
            if (TargetedAdsConfigProvider.INSTANCE.shouldDisplayAdBannerBounds()) {
                AdBannerView adBannerView = AdBannerView.this;
                if (resource == null) {
                    Intrinsics.throwNpe();
                }
                int dominantColor = adBannerView.getDominantColor(adBannerView.drawableToBitmap(resource));
                AdBannerView.this.setBackgroundColor(Color.argb(255, 255 - Color.red(dominantColor), 255 - Color.green(dominantColor), 255 - Color.blue(dominantColor)));
            }
            AdBannerView.this.imageFinishedLoading = true;
            if (AdBannerView.this.isCurrentlyVisibleOnScreen()) {
                DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + this.$ad.getAdUnitId() + "] -> Attempt #1");
                AdBannerView.this.tryStartingBannerHidingCountdown();
                AdBannerView.this.sendTrackingInfo(this.$ad.getAdUnitId(), this.$ad.getDsAdUnitId(), new Function0<Unit>() { // from class: com.dotscreen.portal.ui.views.AdBannerView$setAdImageResource$1$onResourceReady$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Context context = AdBannerView.this.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context, "context");
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        ((PortalApplication) applicationContext).visitAdTrackingURL(this.this$0.$ad.getAdUnitId(), this.this$0.$trackingURL);
                    }
                }, new Function0<Boolean>() { // from class: com.dotscreen.portal.ui.views.AdBannerView$setAdImageResource$1$onResourceReady$2
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Boolean invoke() {
                        return Boolean.valueOf(invoke2());
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2() {
                        Context context = AdBannerView.this.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context, "context");
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext != null) {
                            return ((PortalApplication) applicationContext).checkIfAdTrackingURLWasVisited(this.this$0.$trackingURL);
                        }
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                });
                return false;
            }
            AdBannerView.this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.dotscreen.portal.ui.views.AdBannerView$setAdImageResource$1$onResourceReady$3
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    if (AdBannerView.this.isCurrentlyVisibleOnScreen()) {
                        DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + this.this$0.$ad.getAdUnitId() + "] -> Attempt #2");
                        AdBannerView.this.tryStartingBannerHidingCountdown();
                        AdBannerView.this.sendTrackingInfo(this.this$0.$ad.getAdUnitId(), this.this$0.$ad.getDsAdUnitId(), new Function0<Unit>() { // from class: com.dotscreen.portal.ui.views.AdBannerView$setAdImageResource$1$onResourceReady$3.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                Context context = AdBannerView.this.getContext();
                                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                                Context applicationContext = context.getApplicationContext();
                                if (applicationContext == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                                }
                                ((PortalApplication) applicationContext).visitAdTrackingURL(AdBannerView$setAdImageResource$1$onResourceReady$3.this.this$0.$ad.getAdUnitId(), AdBannerView$setAdImageResource$1$onResourceReady$3.this.this$0.$trackingURL);
                            }
                        }, new Function0<Boolean>() { // from class: com.dotscreen.portal.ui.views.AdBannerView$setAdImageResource$1$onResourceReady$3.2
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Boolean invoke() {
                                return Boolean.valueOf(invoke2());
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final boolean invoke2() {
                                Context context = AdBannerView.this.getContext();
                                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                                Context applicationContext = context.getApplicationContext();
                                if (applicationContext != null) {
                                    return ((PortalApplication) applicationContext).checkIfAdTrackingURLWasVisited(AdBannerView$setAdImageResource$1$onResourceReady$3.this.this$0.$trackingURL);
                                }
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                            }
                        });
                        ViewTreeObserver viewTreeObserver = AdBannerView.this.getViewTreeObserver();
                        if (viewTreeObserver != null) {
                            viewTreeObserver.removeOnGlobalLayoutListener(AdBannerView.this.onGlobalLayoutListener);
                        }
                    }
                }
            };
            ViewTreeObserver viewTreeObserver = AdBannerView.this.getViewTreeObserver();
            if (viewTreeObserver == null) {
                return false;
            }
            viewTreeObserver.addOnGlobalLayoutListener(AdBannerView.this.onGlobalLayoutListener);
            return false;
        }

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            DSLog.INSTANCE.adLogE("[LOAD AD IMAGE][VO-" + this.$ad.getAdUnitId() + "] -> FAILURE : " + e);
            AdBannerView.this.hideBanner();
            return false;
        }
    }

    public final void tryStartingBannerHidingCountdown() {
        if (isCurrentlyVisibleOnScreen()) {
            Ad.ImageResource imageResource = this.resource;
            if (imageResource == null) {
                Intrinsics.throwNpe();
            }
            if (imageResource.getWhenResourceDisappears() > 0) {
                if (this.hideBannerCountDownCallback != null) {
                    Ad ad = this.ad;
                    if (ad == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!Intrinsics.areEqual((Object) ad.getDsIsSelectable(), (Object) true)) {
                        DSLog dSLog = DSLog.INSTANCE;
                        StringBuilder sb = new StringBuilder();
                        sb.append("[HIDE BANNER][");
                        sb.append(this.runtimeTag);
                        sb.append("] ->  Banner is visible. Hide it in ");
                        Ad.ImageResource imageResource2 = this.resource;
                        if (imageResource2 == null) {
                            Intrinsics.throwNpe();
                        }
                        sb.append(imageResource2.getWhenResourceDisappears());
                        sb.append(" seconds");
                        dSLog.adLogE(sb.toString());
                        Function4<? super String, ? super String, ? super Integer, ? super Long, Unit> function4 = this.hideBannerCountDownCallback;
                        if (function4 != null) {
                            String str = this.runtimeTag;
                            if (str == null) {
                                Intrinsics.throwNpe();
                            }
                            String str2 = this.owningActivityName;
                            if (str2 == null) {
                                Intrinsics.throwNpe();
                            }
                            Integer num = this.owningActivityHashcode;
                            if (num == null) {
                                Intrinsics.throwNpe();
                            }
                            Ad.ImageResource imageResource3 = this.resource;
                            if (imageResource3 == null) {
                                Intrinsics.throwNpe();
                            }
                            function4.invoke(str, str2, num, Long.valueOf(imageResource3.getWhenResourceDisappears()));
                            return;
                        }
                        return;
                    }
                }
                DSLog dSLog2 = DSLog.INSTANCE;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("[HIDE BANNER][");
                sb2.append(this.runtimeTag);
                sb2.append("] ->  KO, Banner can't be hidden - whenResourceIsDisplayed > 0 but hiding is not supported for ad with position=");
                Ad.ImageResource imageResource4 = this.resource;
                if (imageResource4 == null) {
                    Intrinsics.throwNpe();
                }
                sb2.append(imageResource4.getResourceTemplate().getPosition());
                sb2.append(" and dsIsSelectable=");
                Ad ad2 = this.ad;
                if (ad2 == null) {
                    Intrinsics.throwNpe();
                }
                sb2.append(ad2.getDsIsSelectable());
                dSLog2.adLogE(sb2.toString());
            }
        }
    }

    public final ReentrantLock getLock() {
        return this.lock;
    }

    public final Boolean sendTrackingInfo(String adUnitId, String dsAdUnitId, Function0<Unit> onSuccess, Function0<Boolean> checkIfTrackingAlreadySent) {
        Intrinsics.checkParameterIsNotNull(adUnitId, "adUnitId");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(checkIfTrackingAlreadySent, "checkIfTrackingAlreadySent");
        try {
            try {
                if (this.lock.tryLock()) {
                    if (this.resource == null) {
                        DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + adUnitId + "] -> FAILED - imageResource is null");
                    } else {
                        Ad.ImageResource imageResource = this.resource;
                        if (imageResource == null) {
                            Intrinsics.throwNpe();
                        }
                        String start = imageResource.getTrackingURLs().getStart();
                        String str = start;
                        if ((str == null || str.length() == 0) || StringsKt.isBlank(start)) {
                            DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + adUnitId + "][DS-" + dsAdUnitId + "] -> FAILED - Tracking url is null or empty");
                        } else if (!TargetedAdsConfigProvider.INSTANCE.isTrackingEnabled()) {
                            if (!isCurrentlyVisibleOnScreen()) {
                                DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + adUnitId + "][DS-" + dsAdUnitId + "] -> TRACKING DISABLED + Tracking would not be sent: Ad banner not visible on screen ");
                            } else {
                                DSLog.INSTANCE.adLogM("[AD-TRACKING][VO-" + adUnitId + "][DS-" + dsAdUnitId + "] ->  TRACKING DISABLED + Tacking would be sent here/now");
                            }
                        } else if (!isCurrentlyVisibleOnScreen()) {
                            DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + adUnitId + "][DS-" + dsAdUnitId + "] -> CANCELLED - Ad banner not visible on screen ");
                        } else if (checkIfTrackingAlreadySent.invoke().booleanValue()) {
                            DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + adUnitId + "][DS-" + dsAdUnitId + "] -> CANCELLED - Tracking already sent for the resource");
                        } else {
                            PortalApplication.Companion companion = PortalApplication.INSTANCE;
                            Context context = getContext();
                            Intrinsics.checkExpressionValueIsNotNull(context, "context");
                            com.dotscreen.farkit.network.RequestManager requestManager = companion.getSingleton(context).getRequestManager();
                            if (requestManager != null) {
                                requestManager.getString(0, new URL(start), null);
                            }
                            if (this.onGlobalLayoutListener != null) {
                                getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
                                this.onGlobalLayoutListener = (ViewTreeObserver.OnGlobalLayoutListener) null;
                            }
                            onSuccess.invoke();
                            DSLog.INSTANCE.adLogM("[AD-TRACKING][VO-" + adUnitId + "][DS-" + dsAdUnitId + "] ->  SUCCESS - url:" + start);
                        }
                    }
                } else {
                    DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + adUnitId + "][DS-" + dsAdUnitId + "] ->  KO - sending in another thread");
                }
            } catch (InterruptedException e) {
                DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + adUnitId + "][DS-" + dsAdUnitId + "] ->  KO - " + e);
            }
        } catch (Throwable unused) {
        }
        this.lock.unlock();
        return false;
    }

    public final boolean isCurrentlyVisibleOnScreen() {
        Ad ad;
        String dsAdUnitId;
        boolean z = false;
        if (!this.imageFinishedLoading) {
            DSLog dSLog = DSLog.INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append("[AD VISIBILITY][VO-");
            Ad ad2 = this.ad;
            sb.append(ad2 != null ? ad2.getAdUnitId() : null);
            sb.append("][DS-");
            Ad ad3 = this.ad;
            sb.append(ad3 != null ? ad3.getDsAdUnitId() : null);
            sb.append("] -> false : image is not done loading");
            dSLog.adLogE(sb.toString());
            return false;
        }
        Activity activity = getActivity(this);
        if (activity != null && (activity instanceof BaseFragmentActivity)) {
            FragmentManager supportFragmentManager = ((BaseFragmentActivity) activity).getSupportFragmentManager();
            Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "activity.supportFragmentManager");
            Fragment visibleFragment = getVisibleFragment(supportFragmentManager);
            if (visibleFragment != null && (visibleFragment instanceof BrowseSupportFragment) && (ad = this.ad) != null && (dsAdUnitId = ad.getDsAdUnitId()) != null) {
                if (dsAdUnitId == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String upperCase = dsAdUnitId.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
                if (upperCase != null && StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "TOP", false, 2, (Object) null)) {
                    DSLog dSLog2 = DSLog.INSTANCE;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("[AD VISIBILITY][VO-");
                    Ad ad4 = this.ad;
                    sb2.append(ad4 != null ? ad4.getAdUnitId() : null);
                    sb2.append("][DS-");
                    Ad ad5 = this.ad;
                    sb2.append(ad5 != null ? ad5.getDsAdUnitId() : null);
                    sb2.append("] -> (HEADER) visible=");
                    BrowseSupportFragment browseSupportFragment = (BrowseSupportFragment) visibleFragment;
                    sb2.append(browseSupportFragment.getSelectedPosition() == 0);
                    dSLog2.adLogM(sb2.toString());
                    return browseSupportFragment.getSelectedPosition() == 0;
                }
            }
        }
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        Ad ad6 = this.ad;
        if ((ad6 != null ? ad6.getDsNativeAdType() : null) == Ad.DSNativeAdType.IN_SWIMLANE && rect.top == 0 && rect.left == 0) {
            DSLog dSLog3 = DSLog.INSTANCE;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("[AD VISIBILITY][VO-");
            Ad ad7 = this.ad;
            sb3.append(ad7 != null ? ad7.getAdUnitId() : null);
            sb3.append("][DS-");
            Ad ad8 = this.ad;
            sb3.append(ad8 != null ? ad8.getDsAdUnitId() : null);
            sb3.append("] -> visible=false -- current alignment is (0, 0), operations based on visibility should be postponed");
            dSLog3.adLogM(sb3.toString());
            return false;
        }
        int i = rect.bottom - rect.top;
        int i2 = rect.right - rect.left;
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        int i3 = system.getDisplayMetrics().widthPixels;
        Resources system2 = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system2, "Resources.getSystem()");
        boolean zIntersect = rect.intersect(new Rect(0, 0, i3, system2.getDisplayMetrics().heightPixels));
        if (getVisibility() == 0 && zIntersect) {
            z = true;
        }
        DSLog dSLog4 = DSLog.INSTANCE;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("[AD VISIBILITY][VO-");
        Ad ad9 = this.ad;
        sb4.append(ad9 != null ? ad9.getAdUnitId() : null);
        sb4.append("][DS-");
        Ad ad10 = this.ad;
        sb4.append(ad10 != null ? ad10.getDsAdUnitId() : null);
        sb4.append("] -> visible=");
        sb4.append(z);
        sb4.append(" {visibleHeight:");
        sb4.append(i);
        sb4.append(", actualHeight:");
        sb4.append(measuredHeight);
        sb4.append(", visibleWidth:");
        sb4.append(i2);
        sb4.append(", actualWidth:");
        sb4.append(measuredWidth);
        sb4.append(", View.visibility:");
        sb4.append(getVisibility() == 0 ? "VISIBLE" : "*NOT VISIBLE*");
        sb4.append(", isPartiallyVisible=");
        sb4.append(zIntersect);
        sb4.append('}');
        dSLog4.adLogM(sb4.toString());
        DSLog dSLog5 = DSLog.INSTANCE;
        StringBuilder sb5 = new StringBuilder();
        sb5.append("[AD VISIBILITY][VO-");
        Ad ad11 = this.ad;
        sb5.append(ad11 != null ? ad11.getAdUnitId() : null);
        sb5.append("][DS-");
        Ad ad12 = this.ad;
        sb5.append(ad12 != null ? ad12.getDsAdUnitId() : null);
        sb5.append("] -> pos= {top:");
        sb5.append(rect.top);
        sb5.append(", left:");
        sb5.append(rect.left);
        sb5.append(", bottom:");
        sb5.append(rect.bottom);
        sb5.append(", parent:");
        sb5.append(getParent());
        sb5.append('}');
        dSLog5.adLogM(sb5.toString());
        return z;
    }

    public final Activity getActivity(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    public Fragment getVisibleFragment(FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        List<Fragment> fragments = fragmentManager.getFragments();
        Intrinsics.checkExpressionValueIsNotNull(fragments, "fragmentManager.fragments");
        if (fragments == null) {
            return null;
        }
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    public final void hideBanner() {
        this.imageFinishedLoading = false;
        if (this.onGlobalLayoutListener != null) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
        this.trackingSent = (Boolean) null;
        setContentDescription((CharSequence) null);
        setAd((Ad) null);
        setResource((Ad.ImageResource) null);
        this.resourceIndex = -1;
        setVisibility(8);
        DSLog.INSTANCE.adLogE("[HIDE BANNER][" + this.runtimeTag + "] ->  OK");
        setRuntimeTag((String) null);
    }
}
