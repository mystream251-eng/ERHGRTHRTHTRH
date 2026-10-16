package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.portal.model.HomeWidget;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DetailsActionCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/cards/DetailsActionCardPresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "action", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class DetailsActionCardPresenter extends Presenter {
    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Context context = parent != null ? parent.getContext() : null;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        DetailsActionCardView detailsActionCardView = new DetailsActionCardView(context, null, 0, 6, null);
        detailsActionCardView.setFocusable(true);
        detailsActionCardView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(detailsActionCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object action) {
        View view = viewHolder != null ? viewHolder.view : null;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.DetailsActionCardView");
        }
        DetailsActionCardView detailsActionCardView = (DetailsActionCardView) view;
        if (action == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.model.HomeWidget");
        }
        detailsActionCardView.setActionWidget((HomeWidget) action);
    }
}
