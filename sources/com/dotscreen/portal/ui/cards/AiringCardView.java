package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestListener;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.utils.String_ExtensionKt;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AiringCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0012\u001a\u00020$2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'J\u0006\u0010(\u001a\u00020$J\u0010\u0010)\u001a\u00020$2\b\u0010*\u001a\u0004\u0018\u00010\nJ\u0010\u0010+\u001a\u00020$2\b\u0010*\u001a\u0004\u0018\u00010\nJ\u0010\u0010,\u001a\u00020$2\b\u0010*\u001a\u0004\u0018\u00010\nJ\u0010\u0010-\u001a\u00020$2\b\u0010.\u001a\u0004\u0018\u00010/J\u0006\u00100\u001a\u00020$J\u0006\u00101\u001a\u00020$J\u000e\u00102\u001a\u00020$2\u0006\u00103\u001a\u00020\u0007J\u000e\u00104\u001a\u00020$2\u0006\u00105\u001a\u00020\nJ\u0016\u00106\u001a\u00020$2\u0006\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u0007J\u000e\u00109\u001a\u00020$2\u0006\u0010:\u001a\u00020\u0007J\u001e\u0010;\u001a\u00020$2\u0006\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020\n2\u0006\u0010>\u001a\u00020?J\u0010\u0010@\u001a\u00020$2\b\u0010.\u001a\u0004\u0018\u00010/R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/dotscreen/portal/ui/cards/AiringCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "airingId", "getAiringId", "()Ljava/lang/String;", "setAiringId", "(Ljava/lang/String;)V", "airingImageView", "Landroid/widget/ImageView;", "canShowInteractive", "", "channelLogoImageView", "mAiringChannelLogoUrl", "mAiringChannelNumber", "Landroid/widget/TextView;", "mAiringGrad", "mAiringImgUrl", "mAiringInteractive", "mAiringParentalRatingImg", "mAiringTime", "mAiringTitle", "mImageLoader", "Lcom/android/volley/toolbox/ImageLoader;", "mProgressBar", "Landroid/widget/ProgressBar;", "mRatingBar", "Landroid/widget/RatingBar;", "", "isInteractiveChannel", "channel", "Lcom/dotscreen/bokit/model/Channel;", "removeShadow", "setAdsImage", ImagesContract.URL, "setAiringImage", "setChannelLogoImage", "setChannelNumber", "text", "", "setNoChannelNumber", "setNoTime", "setParentalRatingLogo", "id", "setPastTime", "date", "setProgress", "duration", "livePosition", "setRating", "rating", "setTime", "start", TtmlNode.END, "ts", "", "setTitle", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class AiringCardView extends CardView {
    private final String TAG;
    private HashMap _$_findViewCache;
    private String airingId;
    private final ImageView airingImageView;
    private boolean canShowInteractive;
    private final ImageView channelLogoImageView;
    private String mAiringChannelLogoUrl;
    private final TextView mAiringChannelNumber;
    private final ImageView mAiringGrad;
    private String mAiringImgUrl;
    private final TextView mAiringInteractive;
    private final ImageView mAiringParentalRatingImg;
    private final TextView mAiringTime;
    private final TextView mAiringTitle;
    private final ImageLoader mImageLoader;
    private final ProgressBar mProgressBar;
    private final RatingBar mRatingBar;

    public AiringCardView(Context context) {
        this(context, null, 0, 6, null);
    }

    public AiringCardView(Context context, AttributeSet attributeSet) {
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

    public /* synthetic */ AiringCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AiringCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = AiringCardView.class.getSimpleName();
        this.airingId = "";
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(getContext())");
        layoutInflaterFrom.inflate(R.layout.card_airing, this);
        this.mAiringTitle = (TextView) findViewById(R.id.airing_title);
        View viewFindViewById = findViewById(R.id.airing_time);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.airing_time)");
        this.mAiringTime = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.airing_grad);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.airing_grad)");
        this.mAiringGrad = (ImageView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.live_progress);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.live_progress)");
        this.mProgressBar = (ProgressBar) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.airing_channel_logo);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById(R.id.airing_channel_logo)");
        this.channelLogoImageView = (ImageView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.airing_channel_number);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "findViewById(R.id.airing_channel_number)");
        this.mAiringChannelNumber = (TextView) viewFindViewById5;
        this.mAiringInteractive = (TextView) findViewById(R.id.airing_tag);
        View viewFindViewById6 = findViewById(R.id.airing_img);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "findViewById(R.id.airing_img)");
        this.airingImageView = (ImageView) viewFindViewById6;
        View viewFindViewById7 = findViewById(R.id.parental_rating);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "findViewById(R.id.parental_rating)");
        this.mAiringParentalRatingImg = (ImageView) viewFindViewById7;
        View viewFindViewById8 = findViewById(R.id.rating_bar);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "findViewById(R.id.rating_bar)");
        this.mRatingBar = (RatingBar) viewFindViewById8;
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "getContext()");
        this.mImageLoader = companion.getSingleton(context2).getImageLoader();
        setFocusable(true);
        setFocusableInTouchMode(true);
        View viewFindViewById9 = findViewById(R.id.root);
        if (viewFindViewById9 != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById9.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById9.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
        }
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black0));
    }

    public final String getAiringId() {
        return this.airingId;
    }

    public final void setAiringId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.airingId = str;
    }

    public final void setTitle(CharSequence text) {
        TextView textView = this.mAiringTitle;
        if (textView == null) {
            return;
        }
        textView.setText(text);
    }

    public final void canShowInteractive(boolean canShowInteractive) {
        this.canShowInteractive = canShowInteractive;
    }

    public final void isInteractiveChannel(Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        if (this.canShowInteractive) {
            if (channel.getCapabilities().getCatchup() && channel.getCapabilities().getStartover()) {
                TextView textView = this.mAiringInteractive;
                if (textView != null) {
                    textView.setVisibility(0);
                    return;
                }
                return;
            }
            TextView textView2 = this.mAiringInteractive;
            if (textView2 != null) {
                textView2.setVisibility(4);
                return;
            }
            return;
        }
        TextView textView3 = this.mAiringInteractive;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
    }

    public final void setRating(int rating) {
        if (rating > 0) {
            this.mRatingBar.setNumStars(4);
            this.mRatingBar.setMax(4);
            this.mRatingBar.setRating(rating);
            this.mRatingBar.setVisibility(0);
            return;
        }
        this.mRatingBar.setVisibility(8);
    }

    public final void setChannelNumber(CharSequence text) {
        this.mAiringChannelNumber.setText(text);
    }

    public final void setTime(String start, String end, long ts) {
        String string;
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(end, "end");
        long jCurrentTimeMillis = System.currentTimeMillis();
        long minutes = TimeUnit.SECONDS.toMinutes(jCurrentTimeMillis - ts) / ((long) 1000);
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        String quantityString = context.getResources().getQuantityString(R.plurals.start_program_now, (int) minutes, Long.valueOf(minutes));
        if (jCurrentTimeMillis <= ts) {
            this.mProgressBar.setVisibility(4);
            long jAbs = Math.abs(minutes);
            if (jAbs < 60) {
                Context context2 = getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                string = context2.getString(R.string.in_several_min, Long.valueOf(jAbs));
            } else {
                string = start + " - " + end;
            }
            quantityString = string;
        } else if (minutes < 1) {
            Context context3 = getContext();
            if (context3 == null) {
                Intrinsics.throwNpe();
            }
            quantityString = context3.getString(R.string.epg_tab_now);
        } else if (minutes >= 60) {
            Context context4 = getContext();
            if (context4 == null) {
                Intrinsics.throwNpe();
            }
            quantityString = context4.getString(R.string.for_more_than);
        }
        this.mAiringTime.setText(quantityString);
    }

    public final void setPastTime(String date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        this.mProgressBar.setVisibility(4);
        this.mAiringTime.setText(date);
    }

    public final void setNoTime() {
        this.mProgressBar.setVisibility(4);
        this.mAiringTime.setText("");
    }

    public final void setProgress(int duration, int livePosition) {
        this.mProgressBar.setVisibility(0);
        this.mProgressBar.setMax(duration);
        this.mProgressBar.setProgress(livePosition);
    }

    public final void setAiringImage(String url) {
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

    public final void setAdsImage(String url) {
        if (url != null) {
            RequestManager requestManagerWith = Glide.with(getContext());
            Intrinsics.checkExpressionValueIsNotNull(requestManagerWith, "Glide.with(context)");
            if (String_ExtensionKt.isGif(url)) {
                requestManagerWith.asGif();
            }
            requestManagerWith.load(url).placeholder(R.drawable.placeholder_horizontal).error(R.drawable.placeholder_horizontal).into(this.airingImageView);
        }
    }

    public final void setNoChannelNumber() {
        this.mAiringChannelNumber.setVisibility(4);
        this.mAiringChannelNumber.setText("");
    }

    public final void removeShadow() {
        this.mAiringGrad.setVisibility(4);
    }

    public final void setChannelLogoImage(String url) {
        if (url == null || !(!Intrinsics.areEqual(url, this.mAiringChannelLogoUrl))) {
            if (url == null) {
                this.mAiringChannelLogoUrl = url;
                this.channelLogoImageView.setImageDrawable(null);
                return;
            }
            return;
        }
        this.mAiringChannelLogoUrl = url;
        this.channelLogoImageView.setImageBitmap(null);
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        new ImageLoaderManager(context).loadImage(url, (1016 & 2) != 0 ? (ImageView) null : this.channelLogoImageView, (1016 & 4) != 0 ? false : true, (1016 & 8) != 0 ? 300 : 0, (1016 & 16) != 0 ? 0 : 0, (1016 & 32) != 0 ? 0 : 0, (1016 & 64) != 0 ? (ImageView.ScaleType) null : null, (1016 & 128) != 0 ? 0 : 0, (1016 & 256) == 0 ? 0 : 0, (1016 & 512) != 0 ? (RequestListener) null : null);
    }

    public final void setParentalRatingLogo(int id) {
        if (id > 0) {
            this.mAiringParentalRatingImg.setVisibility(0);
            this.mAiringParentalRatingImg.setImageResource(id);
        } else {
            this.mAiringParentalRatingImg.setVisibility(8);
        }
    }
}
