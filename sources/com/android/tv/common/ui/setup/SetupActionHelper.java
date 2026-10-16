package com.android.tv.common.ui.setup;

import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public class SetupActionHelper {
    public static void onActionClick(Fragment fragment, String str, int i) {
        OnActionClickListener onActionClickListener = fragment instanceof SetupFragment ? ((SetupFragment) fragment).getOnActionClickListener() : null;
        if (onActionClickListener == null && (fragment.getActivity() instanceof OnActionClickListener)) {
            onActionClickListener = (OnActionClickListener) fragment.getActivity();
        }
        if (onActionClickListener != null) {
            onActionClickListener.onActionClick(str, i);
        }
    }

    public static View.OnClickListener createOnClickListenerForAction(OnActionClickListener onActionClickListener, String str, int i) {
        return new OnActionClickListenerForAction(onActionClickListener, str, i);
    }

    private static class OnActionClickListenerForAction implements View.OnClickListener {
        private final int mActionId;
        private final String mCategory;
        private final OnActionClickListener mListener;

        OnActionClickListenerForAction(OnActionClickListener onActionClickListener, String str, int i) {
            this.mListener = onActionClickListener;
            this.mCategory = str;
            this.mActionId = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OnActionClickListener onActionClickListener = this.mListener;
            if (onActionClickListener != null) {
                onActionClickListener.onActionClick(this.mCategory, this.mActionId);
            }
        }
    }

    private SetupActionHelper() {
    }
}
