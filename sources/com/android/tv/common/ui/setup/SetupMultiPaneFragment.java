package com.android.tv.common.ui.setup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.tv.common.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class SetupMultiPaneFragment extends SetupFragment {
    public static final int ACTION_DONE = Integer.MAX_VALUE;

    protected abstract String getActionCategory();

    protected boolean needsDoneButton() {
        return true;
    }

    protected abstract SetupGuidedStepFragment onCreateContentFragment();

    @Override // com.android.tv.common.ui.setup.SetupFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        getChildFragmentManager().beginTransaction().replace(R.id.guided_step_fragment_container, onCreateContentFragment()).commit();
        if (needsDoneButton()) {
            setOnClickAction(viewOnCreateView.findViewById(R.id.button_done), getActionCategory(), Integer.MAX_VALUE);
        } else {
            View viewFindViewById = viewOnCreateView.findViewById(R.id.done_button_container);
            if (getResources().getConfiguration().getLayoutDirection() == 0) {
                ((ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams()).rightMargin = -getResources().getDimensionPixelOffset(R.dimen.setup_done_button_container_width);
            } else {
                ((ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams()).leftMargin = -getResources().getDimensionPixelOffset(R.dimen.setup_done_button_container_width);
            }
            viewOnCreateView.findViewById(R.id.button_done).setFocusable(false);
        }
        return viewOnCreateView;
    }

    @Override // com.android.tv.common.ui.setup.SetupFragment
    protected int getLayoutResourceId() {
        return R.layout.fragment_setup_multi_pane;
    }

    @Override // com.android.tv.common.ui.setup.SetupFragment
    protected int[] getParentIdsForDelay() {
        return new int[]{R.id.content_fragment, R.id.guidedactions_list};
    }

    @Override // com.android.tv.common.ui.setup.SetupFragment
    public int[] getSharedElementIds() {
        return new int[]{R.id.action_fragment_background, R.id.done_button_container};
    }
}
