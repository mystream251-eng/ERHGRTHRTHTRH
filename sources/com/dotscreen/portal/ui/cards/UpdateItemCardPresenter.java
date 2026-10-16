package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.farkit.updater.UpdateManager;
import com.dotscreen.megacable.portal.R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateItemCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/cards/UpdateItemCardPresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class UpdateItemCardPresenter extends Presenter {
    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Context context = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "parent.context");
        UpdateItemCardView updateItemCardView = new UpdateItemCardView(context, null, 0, 6, null);
        Context context2 = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "parent.context");
        int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.update_item_card_width);
        Context context3 = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "parent.context");
        updateItemCardView.setLayoutParams(new ViewGroup.LayoutParams(dimensionPixelSize, context3.getResources().getDimensionPixelSize(R.dimen.update_item_card_height)));
        updateItemCardView.setFocusable(true);
        updateItemCardView.setFocusableInTouchMode(true);
        View childAt = updateItemCardView.getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "cardView.getChildAt(0)");
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        Intrinsics.checkExpressionValueIsNotNull(layoutParams, "cardView.getChildAt(0).layoutParams");
        Context context4 = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context4, "parent.context");
        layoutParams.width = context4.getResources().getDimensionPixelSize(R.dimen.update_item_card_width);
        Context context5 = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context5, "parent.context");
        layoutParams.height = context5.getResources().getDimensionPixelSize(R.dimen.update_item_card_height);
        View childAt2 = updateItemCardView.getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt2, "cardView.getChildAt(0)");
        childAt2.setLayoutParams(layoutParams);
        return new Presenter.ViewHolder(updateItemCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = viewHolder.view;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.UpdateItemCardView");
        }
        UpdateItemCardView updateItemCardView = (UpdateItemCardView) view;
        if (!(item instanceof UpdateManager.UpdateItem)) {
            item = null;
        }
        updateItemCardView.setUpdateItem((UpdateManager.UpdateItem) item);
    }
}
