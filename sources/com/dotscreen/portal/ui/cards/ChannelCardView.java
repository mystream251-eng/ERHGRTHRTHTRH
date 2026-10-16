package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.bumptech.glide.request.RequestListener;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.ui.ImageLoaderManager;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: ChannelCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u001e\u001a\u00020\u001fH\u0014J\b\u0010 \u001a\u00020\u001fH\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u000b\u001a\u0004\u0018\u00010\u0014@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0016\u0010\u0017R(\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u000b\u001a\u0004\u0018\u00010\u0018@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/dotscreen/portal/ui/cards/ChannelCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "attachedToWindow", "", "value", "Lcom/dotscreen/bokit/model/Channel;", "channel", "getChannel", "()Lcom/dotscreen/bokit/model/Channel;", "setChannel", "(Lcom/dotscreen/bokit/model/Channel;)V", "logoImageView", "Landroid/widget/ImageView;", "Ljava/net/URL;", "logoUrl", "setLogoUrl", "(Ljava/net/URL;)V", "Lcom/dotscreen/bokit/model/CatalogSection;", "section", "getSection", "()Lcom/dotscreen/bokit/model/CatalogSection;", "setSection", "(Lcom/dotscreen/bokit/model/CatalogSection;)V", "onAttachedToWindow", "", "onDetachedFromWindow", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ChannelCardView extends CardView {
    private HashMap _$_findViewCache;
    private boolean attachedToWindow;
    private Channel channel;
    private ImageView logoImageView;
    private URL logoUrl;
    private CatalogSection section;

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
    public ChannelCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        LayoutInflater.from(getContext()).inflate(R.layout.card_channel, this);
        setBackgroundColor(getResources().getColor(R.color.black, null));
        this.logoImageView = (ImageView) findViewById(R.id.channelLogo);
        View viewFindViewById = findViewById(R.id.root);
        if (viewFindViewById != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
        }
    }

    public /* synthetic */ ChannelCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void setLogoUrl(URL url) {
        this.logoUrl = url;
        if (url != null) {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            new ImageLoaderManager(context).loadImageAsBitmap(url.toString(), (3064 & 2) != 0 ? (ImageView) null : this.logoImageView, (3064 & 4) != 0 ? false : true, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : null, (3064 & 1024) == 0, (3064 & 2048) != 0 ? (Drawable) null : null);
        }
    }

    public final Channel getChannel() {
        return this.channel;
    }

    public final void setChannel(Channel channel) {
        this.channel = channel;
        if (channel != null) {
            Artwork squareArtwork = channel.getSquareArtwork();
            setLogoUrl(squareArtwork != null ? Artwork.urlWithSize$default(squareArtwork, new Point(200, 200), 0.0d, 2, null) : null);
            TextView textView = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.channelName);
            if (textView != null) {
                textView.setText(channel.getTitle());
            }
            TextView textView2 = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.channelNumber);
            if (textView2 != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%04d", Arrays.copyOf(new Object[]{Integer.valueOf(channel.getZappingNumber())}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                textView2.setText(str);
            }
            if (channel.getCapabilities().getCatchup() && channel.getCapabilities().getStartover()) {
                TextView textView3 = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.channelInteractive);
                if (textView3 != null) {
                    textView3.setVisibility(0);
                    return;
                }
                return;
            }
            TextView textView4 = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.channelInteractive);
            if (textView4 != null) {
                textView4.setVisibility(4);
            }
        }
    }

    public final CatalogSection getSection() {
        return this.section;
    }

    public final void setSection(CatalogSection catalogSection) {
        this.section = catalogSection;
        if (catalogSection != null) {
            Artwork squareArtwork = catalogSection.getSquareArtwork();
            setLogoUrl(squareArtwork != null ? Artwork.urlWithSize$default(squareArtwork, new Point(200, 200), 0.0d, 2, null) : null);
            TextView textView = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.channelName);
            if (textView != null) {
                textView.setText(catalogSection.getTitle());
            }
            TextView textView2 = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.channelNumber);
            if (textView2 != null) {
                textView2.setText((CharSequence) null);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.attachedToWindow = false;
        super.onDetachedFromWindow();
    }
}
