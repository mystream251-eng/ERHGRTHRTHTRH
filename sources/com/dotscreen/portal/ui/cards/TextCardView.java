package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/cards/TextCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "", "subtext", "getSubtext", "()Ljava/lang/String;", "setSubtext", "(Ljava/lang/String;)V", "text", "getText", "setText", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TextCardView extends CardView {
    private HashMap _$_findViewCache;
    private String subtext;
    private String text;

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
    public TextCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        LayoutInflater.from(getContext()).inflate(R.layout.card_text, this);
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

    public /* synthetic */ TextCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        TextView textView;
        this.text = str;
        if (str == null || (textView = (TextView) findViewById(R.id.text)) == null) {
            return;
        }
        textView.setText(str);
    }

    public final String getSubtext() {
        return this.subtext;
    }

    public final void setSubtext(String str) {
        String text;
        this.subtext = str;
        TextView textView = (TextView) findViewById(R.id.subtext);
        if (textView != null) {
            if (str != null) {
                textView.setVisibility(0);
                if (Intrinsics.areEqual(str, "SEE_ALL_TAG")) {
                    Context context = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "context");
                    text = context.getResources().getText(R.string.more_content_subtitle);
                } else {
                    text = str;
                }
                textView.setText(text);
                return;
            }
            textView.setVisibility(8);
        }
    }
}
