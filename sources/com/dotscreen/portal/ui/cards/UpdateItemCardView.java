package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.farkit.updater.UpdateManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateItemCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/ui/cards/UpdateItemCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "Lcom/dotscreen/farkit/updater/UpdateManager$UpdateItem;", "updateItem", "getUpdateItem", "()Lcom/dotscreen/farkit/updater/UpdateManager$UpdateItem;", "setUpdateItem", "(Lcom/dotscreen/farkit/updater/UpdateManager$UpdateItem;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class UpdateItemCardView extends CardView {
    private HashMap _$_findViewCache;
    private UpdateManager.UpdateItem updateItem;

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
    public UpdateItemCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        LayoutInflater.from(getContext()).inflate(R.layout.card_update_item, this);
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
        if (value != null) {
            View viewFindViewById = findViewById(R.id.root);
            if (viewFindViewById != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
            Integer color = value.getDesignManager().getColor("standard.item.default.background.color");
            if (color == null) {
                Intrinsics.throwNpe();
            }
            setBackgroundColor(color.intValue());
        }
    }

    public /* synthetic */ UpdateItemCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final UpdateManager.UpdateItem getUpdateItem() {
        return this.updateItem;
    }

    public final void setUpdateItem(UpdateManager.UpdateItem updateItem) {
        this.updateItem = updateItem;
        if (updateItem != null) {
            TextView title = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(title, "title");
            title.setText(updateItem.getTitle());
            TextView details = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.details);
            Intrinsics.checkExpressionValueIsNotNull(details, "details");
            details.setText("versionName: " + updateItem.getVersionName() + "\nversionCode: " + updateItem.getVersionCode() + '\n');
        }
    }
}
