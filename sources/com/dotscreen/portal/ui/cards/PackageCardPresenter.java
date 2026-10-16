package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.common.bokit.RightVPackage;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PackageCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/portal/ui/cards/PackageCardPresenter;", "Landroidx/leanback/widget/Presenter;", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PackageCardPresenter extends Presenter {
    private final Integer height;
    private final Integer width;

    /* JADX WARN: Multi-variable type inference failed */
    public PackageCardPresenter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
    }

    public PackageCardPresenter(Integer num, Integer num2) {
        this.width = num;
        this.height = num2;
    }

    public /* synthetic */ PackageCardPresenter(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2);
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        int dimensionPixelSize;
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Context context = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "parent.context");
        PackageCardView packageCardView = new PackageCardView(context, null, 0, 6, null);
        Integer num = this.width;
        int iIntValue = num != null ? num.intValue() : -1;
        Integer num2 = this.height;
        if (num2 != null) {
            dimensionPixelSize = num2.intValue();
        } else {
            Context context2 = parent.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "parent.context");
            dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.package_card_height);
        }
        packageCardView.setLayoutParams(new ViewGroup.LayoutParams(iIntValue, dimensionPixelSize));
        packageCardView.setFocusable(true);
        packageCardView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(packageCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = viewHolder.view;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.PackageCardView");
        }
        ((PackageCardView) view).setItem((RightVPackage) item);
    }
}
