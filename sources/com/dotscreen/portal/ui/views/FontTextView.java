package com.dotscreen.portal.ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FontTextView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u0003J\"\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00072\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\u0010\u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020\u0013H\u0016R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006."}, d2 = {"Lcom/dotscreen/portal/ui/views/FontTextView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defaultFont", "Landroid/graphics/Typeface;", "getDefaultFont", "()Landroid/graphics/Typeface;", "setDefaultFont", "(Landroid/graphics/Typeface;)V", "focusedFont", "getFocusedFont", "setFocusedFont", "selection_v2", "", "getSelection_v2", "()Z", "setSelection_v2", "(Z)V", "textSize_base", "", "getTextSize_base", "()F", "setTextSize_base", "(F)V", "text_base", "", "getText_base", "()Ljava/lang/String;", "setText_base", "(Ljava/lang/String;)V", "convertPixelsToDp", "px", "onFocusChanged", "", "focused", "direction", "previouslyFocusedRect", "Landroid/graphics/Rect;", "setSelected", "selected", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class FontTextView extends TextView {
    private HashMap _$_findViewCache;
    private Typeface defaultFont;
    private Typeface focusedFont;
    private boolean selection_v2;
    private float textSize_base;
    private String text_base;

    public FontTextView(Context context) {
        this(context, null, 0, 6, null);
    }

    public FontTextView(Context context, AttributeSet attributeSet) {
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
    public FontTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.text_base = "";
        this.defaultFont = getTypeface();
    }

    public /* synthetic */ FontTextView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final Typeface getDefaultFont() {
        return this.defaultFont;
    }

    public final void setDefaultFont(Typeface typeface) {
        this.defaultFont = typeface;
    }

    public final Typeface getFocusedFont() {
        return this.focusedFont;
    }

    public final void setFocusedFont(Typeface typeface) {
        this.focusedFont = typeface;
    }

    public final boolean getSelection_v2() {
        return this.selection_v2;
    }

    public final void setSelection_v2(boolean z) {
        this.selection_v2 = z;
    }

    public final String getText_base() {
        return this.text_base;
    }

    public final void setText_base(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.text_base = str;
    }

    public final float getTextSize_base() {
        return this.textSize_base;
    }

    public final void setTextSize_base(float f) {
        this.textSize_base = f;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused || isSelected()) {
            Typeface typeface = this.focusedFont;
            if (typeface != null) {
                setTypeface(typeface);
                return;
            }
            return;
        }
        Typeface typeface2 = this.defaultFont;
        if (typeface2 != null) {
            setTypeface(typeface2);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected || isFocused()) {
            Typeface typeface = this.focusedFont;
            if (typeface != null) {
                setTypeface(typeface);
            }
        } else {
            Typeface typeface2 = this.defaultFont;
            if (typeface2 != null) {
                setTypeface(typeface2);
            }
        }
        if (this.selection_v2) {
            if (Intrinsics.areEqual(this.text_base, "")) {
                this.text_base = getText().toString();
            }
            if (this.textSize_base == 0.0f) {
                this.textSize_base = getTextSize();
            }
            if (selected) {
                String str = this.text_base;
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String upperCase = str.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
                setText(upperCase);
                float f = this.textSize_base;
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                setTextSize(convertPixelsToDp(f, context) * 1.5f);
                return;
            }
            setText(this.text_base);
            float f2 = this.textSize_base;
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            setTextSize(convertPixelsToDp(f2, context2));
        }
    }

    public final float convertPixelsToDp(float px, Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        return px / (resources.getDisplayMetrics().densityDpi / 160);
    }
}
