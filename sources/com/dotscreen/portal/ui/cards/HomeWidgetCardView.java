package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.leanback.widget.BaseCardView;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.HomeWidget;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeWidgetCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/dotscreen/portal/ui/cards/HomeWidgetCardView;", "Landroidx/leanback/widget/BaseCardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "globalLayout", "Landroid/widget/RelativeLayout;", "imageView", "Landroid/widget/ImageView;", "secondTitle", "Landroid/widget/TextView;", "titleView", "setItem", "", "item", "Lcom/dotscreen/portal/model/HomeWidget;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HomeWidgetCardView extends BaseCardView {
    private HashMap _$_findViewCache;
    private final RelativeLayout globalLayout;
    private final ImageView imageView;
    private final TextView secondTitle;
    private final TextView titleView;

    public HomeWidgetCardView(Context context) {
        this(context, null, 0, 6, null);
    }

    public HomeWidgetCardView(Context context, AttributeSet attributeSet) {
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
    public HomeWidgetCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        View.inflate(context, R.layout.card_widget, this);
        View viewFindViewById = findViewById(R.id.widget_img);
        if (viewFindViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.imageView = (ImageView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.title);
        if (viewFindViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.titleView = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.widget_layout);
        if (viewFindViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        this.globalLayout = (RelativeLayout) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.second_title);
        if (viewFindViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.secondTitle = (TextView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.widget_layout);
        if (viewFindViewById5 != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable background = viewFindViewById5.getBackground();
                Intrinsics.checkExpressionValueIsNotNull(background, "it.background");
                viewFindViewById5.setBackground(designManager.tintDrawable(background, "standard.item.selected.outline.color"));
                DesignManager designManager2 = value.getDesignManager();
                Drawable foreground = viewFindViewById5.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById5.setForeground(designManager2.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
        }
    }

    public /* synthetic */ HomeWidgetCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void setItem(HomeWidget item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Drawable drawable = (Drawable) null;
        if (item.getIcon() != -1) {
            drawable = getResources().getDrawable(item.getIcon(), null);
        }
        if (item.getIcon() == R.drawable.menu_search) {
            this.imageView.clearColorFilter();
            this.imageView.setImageTintList((ColorStateList) null);
        } else {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null) {
                ImageView imageView = this.imageView;
                Integer color = value.getDesignManager().getColor("standard.item.default.textColor");
                if (color == null) {
                    Intrinsics.throwNpe();
                }
                imageView.setColorFilter(color.intValue());
                ImageView imageView2 = this.imageView;
                Integer color2 = value.getDesignManager().getColor("standard.item.default.textColor");
                if (color2 == null) {
                    Intrinsics.throwNpe();
                }
                imageView2.setImageTintList(ColorStateList.valueOf(color2.intValue()));
            }
        }
        if (drawable != null) {
            this.secondTitle.setVisibility(8);
            this.titleView.setText(item.getTitle());
            this.imageView.setImageDrawable(drawable);
            if (item.getColorforced() != -1) {
                this.imageView.setColorFilter(ContextCompat.getColor(getContext(), item.getColorforced()));
                return;
            }
            return;
        }
        this.titleView.setVisibility(8);
        this.secondTitle.setText(item.getTitle());
        this.secondTitle.setVisibility(0);
    }
}
