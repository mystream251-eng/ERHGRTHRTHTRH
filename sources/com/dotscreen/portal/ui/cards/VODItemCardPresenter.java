package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VODItemCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BK\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\u001c\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/portal/ui/cards/VODItemCardPresenter;", "Landroidx/leanback/widget/Presenter;", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "placeHolder", "adultPresenter", "", "isCatchup", "matchRatio", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Z)V", "Ljava/lang/Boolean;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Ljava/lang/Integer;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODItemCardPresenter extends Presenter {
    private final Boolean adultPresenter;
    private Context context;
    private final Integer height;
    private final Boolean isCatchup;
    private final boolean matchRatio;
    private final Integer placeHolder;
    private final Integer width;

    public VODItemCardPresenter() {
        this(null, null, null, null, null, false, 63, null);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    public /* synthetic */ VODItemCardPresenter(Integer num, Integer num2, Integer num3, Boolean bool, Boolean bool2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, (i & 4) != 0 ? (Integer) null : num3, (i & 8) != 0 ? false : bool, (i & 16) != 0 ? false : bool2, (i & 32) != 0 ? false : z);
    }

    public VODItemCardPresenter(Integer num, Integer num2, Integer num3, Boolean bool, Boolean bool2, boolean z) {
        this.width = num;
        this.height = num2;
        this.placeHolder = num3;
        this.adultPresenter = bool;
        this.isCatchup = bool2;
        this.matchRatio = z;
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
        VODItemCardView vODItemCardView = new VODItemCardView(context, null, 0, 6, null);
        Boolean bool = this.adultPresenter;
        vODItemCardView.setAdultBrowsing(bool != null ? bool.booleanValue() : false);
        Boolean bool2 = this.isCatchup;
        vODItemCardView.setCatchup(bool2 != null ? bool2.booleanValue() : false);
        if (this.width != null && this.height != null) {
            vODItemCardView.setLayoutParams(new ViewGroup.LayoutParams(this.width.intValue(), this.height.intValue()));
            if (this.width.intValue() > this.height.intValue() * 3) {
                vODItemCardView.setArtworkFormat(ArtworkFormat.Banner);
            } else if (Intrinsics.compare(this.width.intValue(), this.height.intValue()) > 0) {
                vODItemCardView.setArtworkFormat(ArtworkFormat.Still);
            } else {
                vODItemCardView.setArtworkFormat(ArtworkFormat.Poster);
            }
        }
        vODItemCardView.setPlaceHolder(this.placeHolder);
        vODItemCardView.setFocusable(true);
        vODItemCardView.setFocusableInTouchMode(true);
        vODItemCardView.setMatchRatio(this.matchRatio);
        vODItemCardView.setWidth(this.width);
        vODItemCardView.setHeight(this.height);
        return new Presenter.ViewHolder(vODItemCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        View view = viewHolder != null ? viewHolder.view : null;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.VODItemCardView");
        }
        VODItemCardView vODItemCardView = (VODItemCardView) view;
        if (item == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.BrowsableItem");
        }
        vODItemCardView.setItem((BrowsableItem) item);
    }
}
