package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.google.android.gms.common.internal.ImagesContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VODItemCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020'H\u0002J\b\u0010<\u001a\u00020=H\u0014J\b\u0010>\u001a\u00020=H\u0014J\u0012\u0010?\u001a\u00020=2\b\u0010@\u001a\u0004\u0018\u00010AH\u0002R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\f\"\u0004\b%\u0010\u000eR(\u0010(\u001a\u0004\u0018\u00010'2\b\u0010&\u001a\u0004\u0018\u00010'@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\f\"\u0004\b/\u0010\u000eR\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00102\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b3\u0010\u001c\"\u0004\b4\u0010\u001eR\u000e\u00105\u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00107\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b8\u0010\u001c\"\u0004\b9\u0010\u001e¨\u0006B"}, d2 = {"Lcom/dotscreen/portal/ui/cards/VODItemCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "adultBrowsing", "", "getAdultBrowsing", "()Z", "setAdultBrowsing", "(Z)V", "artworkFormat", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "getArtworkFormat", "()Lcom/dotscreen/bokit/model/ArtworkFormat;", "setArtworkFormat", "(Lcom/dotscreen/bokit/model/ArtworkFormat;)V", "attachedToWindow", "getAttachedToWindow", "setAttachedToWindow", "disposable", "Lio/reactivex/disposables/Disposable;", ToolboxAnalyticsModuleKt.M_HEIGHT, "getHeight", "()Ljava/lang/Integer;", "setHeight", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "imageView", "Landroid/widget/ImageView;", "getImageView", "()Landroid/widget/ImageView;", "isCatchup", "setCatchup", "value", "Lcom/dotscreen/bokit/model/BrowsableItem;", "item", "getItem", "()Lcom/dotscreen/bokit/model/BrowsableItem;", "setItem", "(Lcom/dotscreen/bokit/model/BrowsableItem;)V", "matchRatio", "getMatchRatio", "setMatchRatio", "obfuscateView", "Landroid/widget/TextView;", "placeHolder", "getPlaceHolder", "setPlaceHolder", "providerGradientView", "providerLogoView", ToolboxAnalyticsModuleKt.M_WIDTH, "getWidth", "setWidth", "isObfuscate", ToolboxAnalyticsModuleKt.KEY_CONTENT, "onAttachedToWindow", "", "onDetachedFromWindow", "setProviderLogoImage", ImagesContract.URL, "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODItemCardView extends CardView {
    private HashMap _$_findViewCache;
    private boolean adultBrowsing;
    private ArtworkFormat artworkFormat;
    private boolean attachedToWindow;
    private Disposable disposable;
    private Integer height;
    private final ImageView imageView;
    private boolean isCatchup;
    private BrowsableItem item;
    private boolean matchRatio;
    private final TextView obfuscateView;
    private Integer placeHolder;
    private final ImageView providerGradientView;
    private final ImageView providerLogoView;
    private Integer width;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ArtworkFormat.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ArtworkFormat.Poster.ordinal()] = 1;
            $EnumSwitchMapping$0[ArtworkFormat.Still.ordinal()] = 2;
            $EnumSwitchMapping$0[ArtworkFormat.Banner.ordinal()] = 3;
            int[] iArr2 = new int[ArtworkFormat.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[ArtworkFormat.Poster.ordinal()] = 1;
            $EnumSwitchMapping$1[ArtworkFormat.Still.ordinal()] = 2;
        }
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
    public VODItemCardView(Context context, AttributeSet attributeSet, int i) {
        View viewFindViewById;
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        LayoutInflater.from(getContext()).inflate(R.layout.card_movie, this);
        View viewFindViewById2 = findViewById(R.id.image);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.image)");
        this.imageView = (ImageView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.provider_logo);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.provider_logo)");
        this.providerLogoView = (ImageView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.provider_grad);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById(R.id.provider_grad)");
        this.providerGradientView = (ImageView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.obfuscate);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "findViewById(R.id.obfuscate)");
        this.obfuscateView = (TextView) viewFindViewById5;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
        if (value != null && (viewFindViewById = findViewById(R.id.root)) != null) {
            DesignManager designManager = value.getDesignManager();
            Drawable foreground = viewFindViewById.getForeground();
            Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
            viewFindViewById.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
        }
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black0));
        this.artworkFormat = ArtworkFormat.Poster;
    }

    public /* synthetic */ VODItemCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final ImageView getImageView() {
        return this.imageView;
    }

    public final boolean getAttachedToWindow() {
        return this.attachedToWindow;
    }

    public final void setAttachedToWindow(boolean z) {
        this.attachedToWindow = z;
    }

    public final boolean getAdultBrowsing() {
        return this.adultBrowsing;
    }

    public final void setAdultBrowsing(boolean z) {
        this.adultBrowsing = z;
    }

    /* JADX INFO: renamed from: isCatchup, reason: from getter */
    public final boolean getIsCatchup() {
        return this.isCatchup;
    }

    public final void setCatchup(boolean z) {
        this.isCatchup = z;
    }

    public final ArtworkFormat getArtworkFormat() {
        return this.artworkFormat;
    }

    public final void setArtworkFormat(ArtworkFormat artworkFormat) {
        Intrinsics.checkParameterIsNotNull(artworkFormat, "<set-?>");
        this.artworkFormat = artworkFormat;
    }

    public final Integer getPlaceHolder() {
        return this.placeHolder;
    }

    public final void setPlaceHolder(Integer num) {
        this.placeHolder = num;
    }

    public final boolean getMatchRatio() {
        return this.matchRatio;
    }

    public final void setMatchRatio(boolean z) {
        this.matchRatio = z;
    }

    @Override // android.view.View
    public final Integer getWidth() {
        return this.width;
    }

    public final void setWidth(Integer num) {
        this.width = num;
    }

    @Override // android.view.View
    public final Integer getHeight() {
        return this.height;
    }

    public final void setHeight(Integer num) {
        this.height = num;
    }

    public final BrowsableItem getItem() {
        return this.item;
    }

    public final void setItem(final BrowsableItem browsableItem) {
        Point point;
        URL urlUrlWithSize;
        String string;
        this.item = browsableItem;
        if (browsableItem != null) {
            if (!this.matchRatio || this.width == null || this.height == null) {
                int i = WhenMappings.$EnumSwitchMapping$0[this.artworkFormat.ordinal()];
                if (i == 1) {
                    Context context = getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension = context.getResources().getDimension(R.dimen.vod_content_card_width);
                    Context context2 = getContext();
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    int fraction = (int) (dimension * context2.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                    Context context3 = getContext();
                    if (context3 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension2 = context3.getResources().getDimension(R.dimen.vod_content_card_height);
                    Context context4 = getContext();
                    if (context4 == null) {
                        Intrinsics.throwNpe();
                    }
                    point = new Point(fraction, (int) (dimension2 * context4.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1)));
                } else if (i == 2) {
                    Context context5 = getContext();
                    if (context5 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension3 = context5.getResources().getDimension(R.dimen.catchup_content_card_width);
                    Context context6 = getContext();
                    if (context6 == null) {
                        Intrinsics.throwNpe();
                    }
                    int fraction2 = (int) (dimension3 * context6.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                    Context context7 = getContext();
                    if (context7 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension4 = context7.getResources().getDimension(R.dimen.catchup_content_card_height);
                    Context context8 = getContext();
                    if (context8 == null) {
                        Intrinsics.throwNpe();
                    }
                    point = new Point(fraction2, (int) (dimension4 * context8.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1)));
                } else if (i != 3) {
                    point = null;
                } else {
                    Context context9 = getContext();
                    if (context9 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension5 = context9.getResources().getDimension(R.dimen.highlight_card_view_width);
                    Context context10 = getContext();
                    if (context10 == null) {
                        Intrinsics.throwNpe();
                    }
                    int fraction3 = (int) (dimension5 * context10.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                    Context context11 = getContext();
                    if (context11 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension6 = context11.getResources().getDimension(R.dimen.highlight_card_view_height);
                    Context context12 = getContext();
                    if (context12 == null) {
                        Intrinsics.throwNpe();
                    }
                    point = new Point(fraction3, (int) (dimension6 * context12.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1)));
                }
            } else {
                Integer num = this.width;
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                float fIntValue = num.intValue();
                Context context13 = getContext();
                if (context13 == null) {
                    Intrinsics.throwNpe();
                }
                int fraction4 = (int) (fIntValue * context13.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                Integer num2 = this.height;
                if (num2 == null) {
                    Intrinsics.throwNpe();
                }
                float fIntValue2 = num2.intValue();
                Context context14 = getContext();
                if (context14 == null) {
                    Intrinsics.throwNpe();
                }
                point = new Point(fraction4, (int) (fIntValue2 * context14.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1)));
            }
            View viewFindViewById = findViewById(R.id.root);
            if (viewFindViewById != null) {
                Resources resources = getResources();
                Integer num3 = this.placeHolder;
                int iIntValue = R.drawable.ph_vertical;
                if (num3 != null) {
                    iIntValue = num3.intValue();
                } else {
                    int i2 = WhenMappings.$EnumSwitchMapping$1[this.artworkFormat.ordinal()];
                    if (i2 != 1 && i2 == 2) {
                        iIntValue = R.drawable.ph_horizontal;
                    }
                }
                viewFindViewById.setBackground(resources.getDrawable(iIntValue, null));
            }
            if (point != null) {
                Artwork artwork = (Artwork) CollectionsKt.firstOrNull((List) browsableItem.artworksWithFormat(this.artworkFormat));
                String str = (artwork == null || (urlUrlWithSize = artwork.urlWithSize(point, 1.0d)) == null || (string = urlUrlWithSize.toString()) == null) ? "" : string;
                Intrinsics.checkExpressionValueIsNotNull(str, "value.artworksWithFormat…e, 1.0)?.toString() ?: \"\"");
                if (isObfuscate(browsableItem)) {
                    this.obfuscateView.setVisibility(0);
                    this.obfuscateView.setText(getContext().getString(R.string.blocked_image));
                    this.imageView.setImageResource(0);
                } else {
                    this.obfuscateView.setVisibility(8);
                    this.obfuscateView.setText("");
                    Context context15 = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context15, "context");
                    new ImageLoaderManager(context15).loadImage(str, (1016 & 2) != 0 ? (ImageView) null : this.imageView, (1016 & 4) != 0 ? false : true, (1016 & 8) != 0 ? 300 : 0, (1016 & 16) != 0 ? 0 : point.x, (1016 & 32) != 0 ? 0 : point.y, (1016 & 64) != 0 ? (ImageView.ScaleType) null : null, (1016 & 128) != 0 ? 0 : 0, (1016 & 256) == 0 ? 0 : 0, (1016 & 512) != 0 ? (RequestListener) null : null);
                }
            }
            if (this.isCatchup) {
                View viewFindViewById2 = findViewById(R.id.title);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById<TextView>(R.id.title)");
                ((TextView) viewFindViewById2).setVisibility(4);
                View viewFindViewById3 = findViewById(R.id.catchup_title_layout);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById<FrameLayout….id.catchup_title_layout)");
                ((FrameLayout) viewFindViewById3).setVisibility(0);
                TextView textView = (TextView) findViewById(R.id.catchup_title);
                if (textView != null) {
                    textView.setText(!isObfuscate(browsableItem) ? browsableItem.getTitle() : "Blocked title");
                }
            } else {
                TextView textView2 = (TextView) findViewById(R.id.title);
                if (textView2 != null) {
                    setVisibility(0);
                    textView2.setText(!isObfuscate(browsableItem) ? browsableItem.getTitle() : "Blocked title");
                }
                View viewFindViewById4 = findViewById(R.id.catchup_title_layout);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById<FrameLayout….id.catchup_title_layout)");
                ((FrameLayout) viewFindViewById4).setVisibility(4);
            }
            if ((browsableItem instanceof Media) && !isObfuscate(browsableItem)) {
                this.disposable = ((Media) browsableItem).getOffers().firstElement().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Offer>() { // from class: com.dotscreen.portal.ui.cards.VODItemCardView$item$$inlined$let$lambda$1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Offer offer) {
                        Map<String, String> providers;
                        PortalApplication.Companion companion = PortalApplication.INSTANCE;
                        Context context16 = this.this$0.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context16, "context");
                        LiveData<PortalApplication.Config> config = companion.getSingleton(context16).getConfig();
                        Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSin…                  .config");
                        PortalApplication.Config value = config.getValue();
                        this.this$0.setProviderLogoImage((value == null || (providers = value.getProviders()) == null) ? null : providers.get(offer.getTitle()));
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.cards.VODItemCardView$item$1$6
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        th.printStackTrace();
                    }
                });
                return;
            }
            View viewFindViewById5 = findViewById(R.id.provider_grad);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "findViewById<View>(R.id.provider_grad)");
            viewFindViewById5.setVisibility(8);
            this.providerLogoView.setImageBitmap(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setProviderLogoImage(String url) {
        View viewFindViewById = findViewById(R.id.provider_grad);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<View>(R.id.provider_grad)");
        viewFindViewById.setVisibility(8);
        this.providerLogoView.setImageBitmap(null);
        if (url != null) {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            new ImageLoaderManager(context).loadImageAsBitmap(url, (3064 & 2) != 0 ? (ImageView) null : this.providerLogoView, (3064 & 4) != 0 ? false : false, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : new RequestListener<Bitmap>() { // from class: com.dotscreen.portal.ui.cards.VODItemCardView.setProviderLogoImage.1
                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    View viewFindViewById2 = VODItemCardView.this.findViewById(R.id.provider_grad);
                    Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById<View>(R.id.provider_grad)");
                    viewFindViewById2.setVisibility(0);
                    return false;
                }
            }, (3064 & 1024) == 0, (3064 & 2048) != 0 ? (Drawable) null : null);
        }
    }

    private final boolean isObfuscate(BrowsableItem content) {
        return !this.adultBrowsing && Constants.INSTANCE.itemIsAdult(content);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.attachedToWindow = false;
    }
}
