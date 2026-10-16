package com.dotscreen.portal.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.leanback.widget.RowPresenter;
import kotlin.Metadata;

/* JADX INFO: compiled from: ViewRowPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/ui/ViewRowPresenter;", "Landroidx/leanback/widget/RowPresenter;", "resourceId", "", "(I)V", "createRowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "isUsingDefaultSelectEffect", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class ViewRowPresenter extends RowPresenter {
    private final int resourceId;

    @Override // androidx.leanback.widget.RowPresenter
    public boolean isUsingDefaultSelectEffect() {
        return false;
    }

    public ViewRowPresenter(int i) {
        this.resourceId = i;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        return new RowPresenter.ViewHolder(LayoutInflater.from(parent != null ? parent.getContext() : null).inflate(this.resourceId, (ViewGroup) null));
    }
}
