package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/dotscreen/portal/ui/cards/TextCardPresenter;", "Landroidx/leanback/widget/Presenter;", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Ljava/lang/Integer;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TextCardPresenter extends Presenter {
    private Context context;
    private final Integer height;
    private final Integer width;

    /* JADX WARN: Multi-variable type inference failed */
    public TextCardPresenter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    public TextCardPresenter(Integer num, Integer num2) {
        this.width = num;
        this.height = num2;
    }

    public /* synthetic */ TextCardPresenter(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        this.context = parent.getContext();
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        TextCardView textCardView = new TextCardView(context, null, 0, 6, null);
        if (this.width != null && this.height != null) {
            textCardView.setLayoutParams(new ViewGroup.LayoutParams(this.width.intValue(), this.height.intValue()));
        }
        textCardView.setFocusable(true);
        textCardView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(textCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        if (item != null) {
            BaseWidget baseWidget = (BaseWidget) item;
            View view = viewHolder != null ? viewHolder.view : null;
            if (view == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.TextCardView");
            }
            TextCardView textCardView = (TextCardView) view;
            textCardView.setText(baseWidget.getTitle());
            textCardView.setSubtext(baseWidget.getSubtitle());
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.model.BaseWidget");
    }
}
