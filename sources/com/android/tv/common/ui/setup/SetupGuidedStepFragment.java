package com.android.tv.common.ui.setup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.leanback.app.GuidedStepSupportFragment;
import androidx.leanback.widget.GuidanceStylist;
import androidx.leanback.widget.GuidedAction;
import androidx.leanback.widget.VerticalGridView;
import com.android.tv.common.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class SetupGuidedStepFragment extends GuidedStepSupportFragment {
    public static final String KEY_THREE_PANE = "key_three_pane";

    protected abstract String getActionCategory();

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public boolean isFocusOutEndAllowed() {
        return true;
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    protected void onProvideFragmentTransitions() {
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        Bundle arguments = getArguments();
        viewOnCreateView.findViewById(R.id.action_fragment_root).setPadding(0, 0, 0, 0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewOnCreateView.findViewById(R.id.content_fragment).getLayoutParams();
        layoutParams.weight = 0.0f;
        if (arguments != null && arguments.getBoolean(KEY_THREE_PANE, false)) {
            layoutParams.width = getResources().getDimensionPixelOffset(R.dimen.setup_guidedstep_guidance_section_width_3pane);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.setup_done_button_container_width);
            viewOnCreateView.findViewById(R.id.guidedactions_list);
            viewOnCreateView.findViewById(R.id.guidedactions_list2);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewOnCreateView.findViewById(R.id.guidedactions_list).getLayoutParams();
            if (getResources().getConfiguration().getLayoutDirection() == 0) {
                marginLayoutParams.rightMargin = dimensionPixelOffset;
            } else {
                marginLayoutParams.leftMargin = dimensionPixelOffset;
            }
        } else {
            layoutParams.width = getResources().getDimensionPixelOffset(R.dimen.setup_guidedstep_guidance_section_width_2pane);
        }
        VerticalGridView actionsGridView = getGuidedActionsStylist().getActionsGridView();
        actionsGridView.setWindowAlignmentOffset(getResources().getDimensionPixelOffset(R.dimen.setup_guidedactions_selector_margin_top));
        actionsGridView.setWindowAlignmentOffsetPercent(0.0f);
        actionsGridView.setItemAlignmentOffsetPercent(0.0f);
        ((ViewGroup) viewOnCreateView.findViewById(R.id.guidedactions_list)).setTransitionGroup(false);
        ViewGroup viewGroup2 = (ViewGroup) viewOnCreateView.findViewById(R.id.content_frame);
        viewGroup2.setClipChildren(false);
        viewGroup2.setClipToPadding(false);
        viewOnCreateView.findViewById(R.id.guidedactions_list2).setFocusable(false);
        return viewOnCreateView;
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public GuidanceStylist onCreateGuidanceStylist() {
        return new GuidanceStylist() { // from class: com.android.tv.common.ui.setup.SetupGuidedStepFragment.1
            @Override // androidx.leanback.widget.GuidanceStylist
            public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, GuidanceStylist.Guidance guidance) {
                View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, guidance);
                if (guidance.getIconDrawable() == null) {
                    getIconView().setVisibility(8);
                }
                return viewOnCreateView;
            }
        };
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public void onGuidedActionClicked(GuidedAction guidedAction) {
        SetupActionHelper.onActionClick(this, getActionCategory(), (int) guidedAction.getId());
    }

    @Override // androidx.leanback.app.GuidedStepSupportFragment
    public int onProvideTheme() {
        return R.style.Theme_Setup_GuidedStep;
    }
}
