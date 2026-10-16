package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.RightVPackage;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PackageCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0014J\b\u0010\u0018\u001a\u00020\u0016H\u0014J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0018\u00010\fR\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/ui/cards/PackageCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "attachedToWindow", "", "imageContainer", "Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "Lcom/android/volley/toolbox/ImageLoader;", "imageView", "Landroid/widget/ImageView;", "package_subscribe", "Landroid/widget/TextView;", "package_subtitle", "package_title", "package_value", "fadeInImg", "", "onAttachedToWindow", "onDetachedFromWindow", "setItem", "data", "Lcom/dotscreen/tv/common/bokit/RightVPackage;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PackageCardView extends CardView {
    private HashMap _$_findViewCache;
    private boolean attachedToWindow;
    private ImageLoader.ImageContainer imageContainer;
    private final ImageView imageView;
    private final TextView package_subscribe;
    private final TextView package_subtitle;
    private final TextView package_title;
    private final TextView package_value;

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
    public PackageCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        LayoutInflater.from(getContext()).inflate(R.layout.card_package, this);
        View viewFindViewById = findViewById(R.id.imageView);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.imageView)");
        this.imageView = (ImageView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.package_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.package_title)");
        this.package_title = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.package_subtitle);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.package_subtitle)");
        this.package_subtitle = (TextView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.package_subscribe);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById(R.id.package_subscribe)");
        this.package_subscribe = (TextView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.package_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "findViewById(R.id.package_value)");
        this.package_value = (TextView) viewFindViewById5;
        setCardElevation(0.0f);
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (((PortalApplication) applicationContext).getConfig().getValue() != null) {
            setBackgroundResource(R.drawable.package_card_background);
        }
    }

    public /* synthetic */ PackageCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setItem(RightVPackage data) {
        String strReplace$default;
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.package_title.setText(Intrinsics.areEqual(data.getName(), "") ^ true ? data.getName() : data.getExternalId());
        this.package_subtitle.setText(Intrinsics.areEqual(data.getDescription(), "") ^ true ? data.getDescription() : "");
        this.package_value.setText("$" + data.getPrice());
        ImageLoader.ImageContainer imageContainer = this.imageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
        }
        if (data.getExtrafields().containsKey("Image_Url")) {
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            final ImageLoader imageLoader = companion.getSingleton(context).getImageLoader();
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            String str = data.getExtrafields().get("Image_Url");
            objectRef.element = (str == null || (strReplace$default = StringsKt.replace$default(str, "http:/", "api/tv/v1/0x0", false, 4, (Object) null)) == null) ? 0 : StringsKt.replace$default(strReplace$default, "https:/", "0x0", false, 4, (Object) null);
            if (((String) objectRef.element) != null) {
                PortalApplication.Companion companion2 = PortalApplication.INSTANCE;
                Context context2 = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                this.imageContainer = imageLoader.get(companion2.getSingleton(context2).getBOService().resolveUrl((String) objectRef.element).toString(), new ImageLoader.ImageListener() { // from class: com.dotscreen.portal.ui.cards.PackageCardView$setItem$$inlined$let$lambda$1
                    @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                    public void onResponse(ImageLoader.ImageContainer response, boolean z) {
                        Intrinsics.checkParameterIsNotNull(response, "response");
                        this.this$0.imageView.setImageBitmap(response.getBitmap());
                        this.this$0.fadeInImg();
                    }

                    @Override // com.android.volley.Response.ErrorListener
                    public void onErrorResponse(VolleyError error) {
                        Intrinsics.checkParameterIsNotNull(error, "error");
                        error.printStackTrace();
                        this.this$0.imageView.setImageBitmap(null);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fadeInImg() {
        this.imageView.setAlpha(0.0f);
        if (this.attachedToWindow) {
            ViewPropertyAnimator viewPropertyAnimatorAlpha = this.imageView.animate().alpha(1.0f);
            Intrinsics.checkExpressionValueIsNotNull(viewPropertyAnimatorAlpha, "imageView.animate()\n                    .alpha(1f)");
            viewPropertyAnimatorAlpha.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        this.imageView.animate().cancel();
        this.imageView.setAlpha(1.0f);
        ImageLoader.ImageContainer imageContainer = this.imageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        if (this.imageView.getAlpha() < 1.0f) {
            fadeInImg();
        }
    }
}
