package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.request.RequestListener;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatchupAsVODCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\nJ\u0010\u0010)\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\nJ\u0010\u0010*\u001a\u00020'2\b\u0010+\u001a\u0004\u0018\u00010,J\u0010\u0010-\u001a\u00020'2\b\u0010+\u001a\u0004\u0018\u00010,R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006."}, d2 = {"Lcom/dotscreen/portal/ui/cards/CatchupAsVODCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "airingId", "getAiringId", "()Ljava/lang/String;", "setAiringId", "(Ljava/lang/String;)V", "airingImageView", "Landroid/widget/ImageView;", "artworkFormat", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "getArtworkFormat", "()Lcom/dotscreen/bokit/model/ArtworkFormat;", "setArtworkFormat", "(Lcom/dotscreen/bokit/model/ArtworkFormat;)V", "channelLogoImageView", "mAiringChannelLogoUrl", "mAiringChannelNumber", "Landroid/widget/TextView;", "mAiringImgUrl", "mAiringTitle", "mImageLoader", "Lcom/android/volley/toolbox/ImageLoader;", "placeHolder", "getPlaceHolder", "()Ljava/lang/Integer;", "setPlaceHolder", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "setAiringImage", "", ImagesContract.URL, "setChannelLogoImage", "setChannelNumber", "text", "", "setTitle", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupAsVODCardView extends CardView {
    private final String TAG;
    private HashMap _$_findViewCache;
    private String airingId;
    private final ImageView airingImageView;
    private ArtworkFormat artworkFormat;
    private final ImageView channelLogoImageView;
    private String mAiringChannelLogoUrl;
    private final TextView mAiringChannelNumber;
    private String mAiringImgUrl;
    private final TextView mAiringTitle;
    private final ImageLoader mImageLoader;
    private Integer placeHolder;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ArtworkFormat.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ArtworkFormat.Poster.ordinal()] = 1;
            $EnumSwitchMapping$0[ArtworkFormat.Still.ordinal()] = 2;
        }
    }

    public CatchupAsVODCardView(Context context) {
        this(context, null, 0, 6, null);
    }

    public CatchupAsVODCardView(Context context, AttributeSet attributeSet) {
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
    public CatchupAsVODCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = CatchupAsVODCardView.class.getSimpleName();
        this.airingId = "";
        this.artworkFormat = ArtworkFormat.Still;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(getContext())");
        layoutInflaterFrom.inflate(R.layout.card_catchup, this);
        this.mAiringTitle = (TextView) findViewById(R.id.airing_title);
        View viewFindViewById = findViewById(R.id.airing_channel_logo);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.airing_channel_logo)");
        this.channelLogoImageView = (ImageView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.airing_channel_number);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.airing_channel_number)");
        this.mAiringChannelNumber = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.airing_img);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.airing_img)");
        this.airingImageView = (ImageView) viewFindViewById3;
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "getContext()");
        this.mImageLoader = companion.getSingleton(context2).getImageLoader();
        setFocusable(true);
        setFocusableInTouchMode(true);
        View viewFindViewById4 = findViewById(R.id.root);
        if (viewFindViewById4 != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById4.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById4.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
        }
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black0));
    }

    public /* synthetic */ CatchupAsVODCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getAiringId() {
        return this.airingId;
    }

    public final void setAiringId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.airingId = str;
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

    public final void setTitle(CharSequence text) {
        TextView textView = this.mAiringTitle;
        if (textView == null) {
            return;
        }
        textView.setText(text);
        if (this.artworkFormat == ArtworkFormat.Banner) {
            this.mAiringTitle.setTextAlignment(4);
        }
    }

    public final void setChannelNumber(CharSequence text) {
        this.mAiringChannelNumber.setText(text);
    }

    public final void setAiringImage(String url) {
        View viewFindViewById = findViewById(R.id.root);
        if (viewFindViewById != null) {
            Resources resources = getResources();
            Integer num = this.placeHolder;
            int iIntValue = R.drawable.ph_vertical;
            if (num != null) {
                iIntValue = num.intValue();
            } else {
                int i = WhenMappings.$EnumSwitchMapping$0[this.artworkFormat.ordinal()];
                if (i != 1 && i == 2) {
                    iIntValue = R.drawable.ph_horizontal;
                }
            }
            viewFindViewById.setBackground(resources.getDrawable(iIntValue, null));
        }
        if (url == null || (!Intrinsics.areEqual(url, this.mAiringImgUrl))) {
            this.mAiringImgUrl = url;
            this.airingImageView.setImageBitmap(null);
            if (url != null) {
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                new ImageLoaderManager(context).loadImage(url, (1016 & 2) != 0 ? (ImageView) null : this.airingImageView, (1016 & 4) != 0 ? false : true, (1016 & 8) != 0 ? 300 : 0, (1016 & 16) != 0 ? 0 : 0, (1016 & 32) != 0 ? 0 : 0, (1016 & 64) != 0 ? (ImageView.ScaleType) null : null, (1016 & 128) != 0 ? 0 : 0, (1016 & 256) == 0 ? 0 : 0, (1016 & 512) != 0 ? (RequestListener) null : null);
            }
        }
    }

    public final void setChannelLogoImage(String url) {
        if (url == null || (!Intrinsics.areEqual(url, this.mAiringChannelLogoUrl))) {
            this.mAiringChannelLogoUrl = url;
            this.channelLogoImageView.setImageBitmap(null);
            if (url != null) {
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                new ImageLoaderManager(context).loadImage(url, (1016 & 2) != 0 ? (ImageView) null : this.channelLogoImageView, (1016 & 4) != 0 ? false : true, (1016 & 8) != 0 ? 300 : 0, (1016 & 16) != 0 ? 0 : 0, (1016 & 32) != 0 ? 0 : 0, (1016 & 64) != 0 ? (ImageView.ScaleType) null : null, (1016 & 128) != 0 ? 0 : 0, (1016 & 256) == 0 ? 0 : 0, (1016 & 512) != 0 ? (RequestListener) null : null);
            }
        }
    }
}
