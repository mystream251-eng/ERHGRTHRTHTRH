package com.dotscreen.portal.ui.screens.details;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.leanback.widget.RowPresenter;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.ui.ViewRowPresenter;
import kotlin.Metadata;

/* JADX INFO: compiled from: CatchupDetailsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/dotscreen/portal/ui/screens/details/CatchupDetailsFragment$MySelector$viewPresenter$1", "Lcom/dotscreen/portal/ui/ViewRowPresenter;", "createRowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupDetailsFragment$MySelector$viewPresenter$1 extends ViewRowPresenter {
    final /* synthetic */ CatchupDetailsFragment.MySelector this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CatchupDetailsFragment$MySelector$viewPresenter$1(CatchupDetailsFragment.MySelector mySelector, int i) {
        super(i);
        this.this$0 = mySelector;
    }

    @Override // com.dotscreen.portal.ui.ViewRowPresenter, androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        final RowPresenter.ViewHolder viewHolderCreateRowViewHolder = super.createRowViewHolder(parent);
        this.this$0.synopsisView = (ProgramDetailsView) viewHolderCreateRowViewHolder.view;
        ProgramDetailsView programDetailsView = this.this$0.synopsisView;
        if (programDetailsView != null) {
            programDetailsView.setDetailsContent(CatchupDetailsFragment.this.getDetailsResult());
        }
        viewHolderCreateRowViewHolder.view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.dotscreen.portal.ui.screens.details.CatchupDetailsFragment$MySelector$viewPresenter$1$createRowViewHolder$1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int dimensionPixelSize = CatchupDetailsFragment.this.getResources().getDimensionPixelSize(R.dimen.details_action_buttons_max_top);
                if ((CatchupDetailsFragment.this.getStartY() + i4) - i2 > dimensionPixelSize) {
                    int startY = ((CatchupDetailsFragment.this.getStartY() + i4) - i2) - dimensionPixelSize;
                    TextView textView = (TextView) viewHolderCreateRowViewHolder.view.findViewById(R.id.details_synopsis);
                    if (textView != null) {
                        textView.setMaxLines((textView.getHeight() - startY) / textView.getLineHeight());
                        return;
                    }
                    return;
                }
                CatchupDetailsFragment.this.fragmentAlignment = Integer.valueOf(i4);
                CatchupDetailsFragment.this.getRowsSupportFragment().setAlignment(i4);
            }
        });
        return viewHolderCreateRowViewHolder;
    }
}
