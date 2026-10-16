package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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

/* JADX INFO: compiled from: DetailsActionCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/dotscreen/portal/ui/cards/DetailsActionCardView;", "Landroidx/leanback/widget/BaseCardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "Lcom/dotscreen/portal/model/HomeWidget;", "actionWidget", "getActionWidget", "()Lcom/dotscreen/portal/model/HomeWidget;", "setActionWidget", "(Lcom/dotscreen/portal/model/HomeWidget;)V", "iconView", "Landroid/widget/ImageView;", "titleView", "Landroid/widget/TextView;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DetailsActionCardView extends BaseCardView {
    private HashMap _$_findViewCache;
    private HomeWidget actionWidget;
    private final ImageView iconView;
    private final TextView titleView;

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
    public DetailsActionCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        LayoutInflater.from(getContext()).inflate(R.layout.card_details_action, this);
        View viewFindViewById = findViewById(R.id.action_icon);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.action_icon)");
        this.iconView = (ImageView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.action_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.action_title)");
        this.titleView = (TextView) viewFindViewById2;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
        if (value != null) {
            View viewFindViewById3 = findViewById(R.id.root);
            if (viewFindViewById3 != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById3.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "root.foreground");
                viewFindViewById3.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
            Integer color = value.getDesignManager().getColor("standard.textColor");
            Integer color2 = value.getDesignManager().getColor("standard.textColor");
            if (color == null || color2 == null) {
                return;
            }
            ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{-16842908, -16842913}, new int[]{android.R.attr.state_focused}, new int[]{android.R.attr.state_selected}}, new int[]{color.intValue(), color2.intValue(), color2.intValue()});
            this.iconView.setImageTintList(colorStateList);
            this.titleView.setTextColor(colorStateList);
        }
    }

    public /* synthetic */ DetailsActionCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final HomeWidget getActionWidget() {
        return this.actionWidget;
    }

    public final void setActionWidget(HomeWidget homeWidget) {
        this.actionWidget = homeWidget;
        if (homeWidget != null) {
            this.iconView.setImageResource(homeWidget.getIcon());
            if (homeWidget.getColorforced() != -1) {
                this.iconView.setColorFilter(ContextCompat.getColor(getContext(), homeWidget.getColorforced()));
            }
            this.titleView.setText(homeWidget.getTitle());
        }
    }
}
