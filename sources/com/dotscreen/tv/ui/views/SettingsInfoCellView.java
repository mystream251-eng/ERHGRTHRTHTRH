package com.dotscreen.tv.ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.tv.common.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsInfoCellView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\tH\u0007J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0007R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R\u000e\u0010\u0019\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/dotscreen/tv/ui/views/SettingsInfoCellView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "value", "", "detail", "getDetail", "()Ljava/lang/String;", "setDetail", "(Ljava/lang/String;)V", "detail1", "Landroid/widget/TextView;", "title", "getTitle", "setTitle", "title1", "setDetailBackground", "", "res", "Landroid/graphics/drawable/Drawable;", "setHasButton", "onClickListener", "Landroid/view/View$OnClickListener;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SettingsInfoCellView extends FrameLayout {
    private final TextView detail1;
    private final TextView title1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsInfoCellView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        View.inflate(context, R.layout.view_settings_info_cell, this);
        View viewFindViewById = findViewById(R.id.title1);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.title1)");
        this.title1 = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.detail1);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.detail1)");
        this.detail1 = (TextView) viewFindViewById2;
    }

    public /* synthetic */ SettingsInfoCellView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SettingsInfoCellView(Context context) {
        this(context, null, 0, 0, 12, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SettingsInfoCellView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SettingsInfoCellView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    public /* synthetic */ SettingsInfoCellView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getTitle() {
        return this.title1.getText().toString();
    }

    public final void setTitle(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.title1.setText(value);
        this.title1.setVisibility(0);
    }

    public final String getDetail() {
        return this.detail1.getText().toString();
    }

    public final void setDetail(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (!Intrinsics.areEqual(this.detail1.getText(), value)) {
            this.detail1.setText(value);
            this.detail1.setVisibility(0);
        }
    }

    public final void setDetailBackground(Drawable res) {
        Intrinsics.checkParameterIsNotNull(res, "res");
        this.detail1.setBackground(res);
    }

    public final void setDetailBackground(int res) {
        this.detail1.setBackground(getContext().getDrawable(res));
    }

    public final void setHasButton(View.OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(onClickListener, "onClickListener");
        this.detail1.setForeground(getContext().getDrawable(R.drawable.info_detail_background_selector));
        this.detail1.setFocusable(1);
        this.detail1.setOnClickListener(onClickListener);
    }
}
