package com.dotscreen.portal.ui.cards;

import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import com.dotscreen.bokit.model.Episode;
import kotlin.Metadata;

/* JADX INFO: compiled from: EpisodePresenterSelector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/dotscreen/portal/ui/cards/EpisodePresenterSelector;", "Landroidx/leanback/widget/PresenterSelector;", "w", "", "h", "(II)V", "getPresenter", "Landroidx/leanback/widget/Presenter;", "item", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class EpisodePresenterSelector extends PresenterSelector {
    private final int h;
    private final int w;

    public EpisodePresenterSelector(int i, int i2) {
        this.w = i;
        this.h = i2;
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object item) {
        if (item instanceof Episode) {
            return new EpisodeCardPresenter(Integer.valueOf(this.w), Integer.valueOf(this.h), null, 4, null);
        }
        return new LoadingCardPresenter(Integer.valueOf(this.w), Integer.valueOf(this.h));
    }
}
