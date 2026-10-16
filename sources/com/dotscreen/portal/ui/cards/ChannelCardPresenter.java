package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.megacable.portal.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChannelCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/cards/ChannelCardPresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ChannelCardPresenter extends Presenter {
    private Context context;

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
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
        ChannelCardView channelCardView = new ChannelCardView(context, null, 0, 6, null);
        Context context2 = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "parent.context");
        int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.home_channel_card_width);
        Context context3 = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "parent.context");
        channelCardView.setLayoutParams(new ViewGroup.LayoutParams(dimensionPixelSize, context3.getResources().getDimensionPixelSize(R.dimen.home_channel_card_height)));
        channelCardView.setFocusable(true);
        channelCardView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(channelCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        View view = viewHolder != null ? viewHolder.view : null;
        ChannelCardView channelCardView = (ChannelCardView) (view instanceof ChannelCardView ? view : null);
        if (channelCardView != null) {
            if (item instanceof Channel) {
                channelCardView.setChannel((Channel) item);
            } else if (item instanceof CatalogSection) {
                channelCardView.setSection((CatalogSection) item);
            }
        }
    }
}
