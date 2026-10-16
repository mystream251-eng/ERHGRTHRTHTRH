package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u001bH\u0014R \u0010\t\u001a\b\u0018\u00010\nR\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R(\u0010\u0017\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/portal/ui/cards/ProfileCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "imageContainer", "Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "Lcom/android/volley/toolbox/ImageLoader;", "getImageContainer", "()Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "setImageContainer", "(Lcom/android/volley/toolbox/ImageLoader$ImageContainer;)V", "value", "", "name", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "picture", "getPicture", "setPicture", "addButton", "", "onDetachedFromWindow", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProfileCardView extends CardView {
    private HashMap _$_findViewCache;
    private ImageLoader.ImageContainer imageContainer;
    private String name;
    private String picture;

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
    public ProfileCardView(Context context, AttributeSet attributeSet, int i) {
        View viewFindViewById;
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        LayoutInflater.from(getContext()).inflate(R.layout.card_profile, this);
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
        setBackgroundColor((int) 3271557120L);
    }

    public /* synthetic */ ProfileCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final ImageLoader.ImageContainer getImageContainer() {
        return this.imageContainer;
    }

    public final void setImageContainer(ImageLoader.ImageContainer imageContainer) {
        this.imageContainer = imageContainer;
    }

    public final void addButton() {
        setName(getContext().getString(R.string.add_profile));
        ImageView imageView = (ImageView) findViewById(R.id.picture);
        if (imageView != null) {
            imageView.setImageResource(R.drawable.ic_add_profile);
        }
    }

    public final String getPicture() {
        return this.picture;
    }

    public final void setPicture(String str) {
        final ImageView imageView;
        this.picture = str;
        Log.d("IconCardView", "new icon -> " + str);
        if (str == null || (imageView = (ImageView) findViewById(R.id.picture)) == null) {
            return;
        }
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.imageContainer = companion.getSingleton(context).getImageLoader().get(str, new ImageLoader.ImageListener() { // from class: com.dotscreen.portal.ui.cards.ProfileCardView$picture$1$1$1
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
            }

            @Override // com.android.volley.toolbox.ImageLoader.ImageListener
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                imageView.setImageBitmap(response.getBitmap());
            }
        });
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
        if (str != null) {
            View viewFindViewById = findViewById(R.id.name);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<TextView>(R.id.name)");
            ((TextView) viewFindViewById).setText(str);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ImageLoader.ImageContainer imageContainer = this.imageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
        }
    }
}
