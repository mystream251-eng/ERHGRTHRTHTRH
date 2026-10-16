package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EpisodeCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/portal/ui/cards/EpisodeCardPresenter;", "Landroidx/leanback/widget/Presenter;", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "placeHolder", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Ljava/lang/Integer;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class EpisodeCardPresenter extends Presenter {
    private Context context;
    private final Integer height;
    private final Integer placeHolder;
    private final Integer width;

    public EpisodeCardPresenter() {
        this(null, null, null, 7, null);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    public EpisodeCardPresenter(Integer num, Integer num2, Integer num3) {
        this.width = num;
        this.height = num2;
        this.placeHolder = num3;
    }

    public /* synthetic */ EpisodeCardPresenter(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, (i & 4) != 0 ? (Integer) null : num3);
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
        EpisodeCardView episodeCardView = new EpisodeCardView(context, null, 0, 6, null);
        if (this.width != null && this.height != null) {
            episodeCardView.setLayoutParams(new ViewGroup.LayoutParams(this.width.intValue(), this.height.intValue()));
            if (this.width.intValue() > this.height.intValue() * 3) {
                episodeCardView.setArtworkFormat(ArtworkFormat.Banner);
            } else if (Intrinsics.compare(this.width.intValue(), this.height.intValue()) > 0) {
                episodeCardView.setArtworkFormat(ArtworkFormat.Still);
            } else {
                episodeCardView.setArtworkFormat(ArtworkFormat.Poster);
            }
        }
        episodeCardView.setPlaceHolder(this.placeHolder);
        episodeCardView.setFocusable(true);
        episodeCardView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(episodeCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        View view = viewHolder != null ? viewHolder.view : null;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.EpisodeCardView");
        }
        EpisodeCardView episodeCardView = (EpisodeCardView) view;
        if (item == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Episode");
        }
        episodeCardView.setItem((Episode) item);
    }
}
